/**
 * 
 */
package nc.bs.so.m30.rule.msg;

import java.math.BigDecimal;
import java.math.BigInteger;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import org.springframework.web.client.RestTemplate;

import com.agintl.entity.erpEquipment.ErpContRelat;

import nc.md.persist.framework.MDPersistenceService;
import nc.pubitf.so.m30.opc.mecc.SaleOrderInfoVO;
import nc.vo.lm.erphtdygx.ErphtdygxHVO;
import nc.bs.dao.BaseDAO;
import nc.bs.dao.DAOException;
import nc.bs.framework.common.NCLocator;
import nc.desktop.ui.Workbench;
import nc.impl.pubapp.pattern.data.bill.BillUpdate;
import nc.impl.pubapp.pattern.data.vo.VOInsert;
import nc.impl.pubapp.pattern.data.vo.VOQuery;
import nc.impl.pubapp.pattern.data.vo.VOUpdate;
import nc.impl.pubapp.pattern.rule.IRule;
import nc.itf.lm.IErphtdygxMaintain;
import nc.itf.so.m30.msg.ISend2Gf;
import nc.jdbc.framework.processor.ResultSetProcessor;
import nc.vo.lm.erphtdygx.AggErphtdygxHVO;
import nc.vo.lm.pgjdcght.Cgag000002HVO;
import nc.vo.pub.BusinessException;
import nc.vo.pub.lang.UFDate;
import nc.vo.pub.lang.UFDouble;
import nc.vo.pubapp.AppContext;
import nc.vo.pubapp.pattern.exception.ExceptionUtils;
import nc.vo.so.m30.entity.SaleOrderBVO;
import nc.vo.so.m30.entity.SaleOrderHVO;
import nc.vo.so.m30.entity.SaleOrderVO;
import nc.vo.so.m30.gfmsg.DTCGGMCGHTREQ;
import nc.vo.so.m30.gfmsg.ErpContMain;
import nc.vo.so.m30.gfmsg.ErpContSub;

/**
 * @author wangzym
 * @version 2017年4月17日 下午1:24:34 1.post请求现在有问题，需要配置表和url 2.只能根据目前的已知数据进行赋值
 */
public class SendToGfAndTable implements ISend2Gf {

	@Override
	public void process(SaleOrderVO[] vos) {
		// 由于对方只能一次接一个aggvo所以只能for循环
		List<ErpContMain> aggVO = new ArrayList<ErpContMain>();
		// 组合aggvo
		for (SaleOrderVO saleOrderVO : vos) {
			ErpContMain agg = sendGfData(saleOrderVO);
			aggVO.add(agg);
		}
		// 进行post操作
		postGfDATA(aggVO);
		// 填充中间表数据这个逻辑我忘了为什么要传和攀枝花的中间表先注掉
		// fillTable(vos);

	}

	/**
	 * 忘记为什么要传中间表了
	 * 
	 * @param vos
	 *            填充中间表
	 * 
	 */
	private void fillTable(SaleOrderVO[] vos) {
		// TODO 自动生成的方法存根
		// 每一条子表数据都是一条中间表数据
		List<Cgag000002HVO> msgvos = new ArrayList<Cgag000002HVO>();
		for (SaleOrderVO saleOrderVO : vos) {
			SaleOrderHVO hVO = saleOrderVO.getParentVO();
			SaleOrderBVO[] bvos = saleOrderVO.getChildrenVO();
			for (SaleOrderBVO saleOrderBVO : bvos) {
				Cgag000002HVO msgVO = new Cgag000002HVO();
				// 主表取值
				String need = hVO.getCbiztypeid();
				// 子表取值
				String mrid = (String) saleOrderBVO.getAttributeValue("mrid");
				msgVO.setMrid(mrid);
				String mrlineid = (String) saleOrderBVO
						.getAttributeValue("mrlineid");
				msgVO.setMrlineid(mrlineid);
				msgvos.add(msgVO);
			}
		}
		// 如果不存在弃审（前提），直接insert就好,不存在错误
		VOInsert<Cgag000002HVO> inset = new VOInsert<Cgag000002HVO>();
		inset.insert(msgvos.toArray(new Cgag000002HVO[msgvos.size()]));
	}

	/**
	 * @param aggVO
	 * @return
	 */
	private void postGfDATA(List<ErpContMain> aggVO) {
		// TODO 自动生成的方法存根
		List<ErpContRelat> li = new ArrayList<ErpContRelat>();
		for (ErpContMain erpcontmain : aggVO) {

			RestTemplate rt = new RestTemplate();
			ErpContRelat[] resp = null;
			try {
				resp = rt
						.postForObject(
						// getRestURL("ErpContMain")
								"http://192.1.103.156:8083/conn-xi/controller/equipment/erpContract",
								// "http://192.1.103.32:8083/controller/equipment/erpContract",
								erpcontmain, ErpContRelat[].class);
			} catch (Exception e) {
				// TODO: handle exception
				ExceptionUtils.wrappBusinessException("数据传输异常，请检查服务连接是否正常");
			}
			// 返回值为空
			if (resp == null) {
				ExceptionUtils.wrappBusinessException("返回值为空，传股份数据失败");
			}
			for (ErpContRelat erpContRelat : resp) {
				li.add(erpContRelat);
			}
		}
		AggErphtdygxHVO[] agg = this.transAggvo(li);
		// 保存数据到中间表
		this.saveNcTalbe(agg);

		// 保存到中间表后如果有异常再抛异常，不更新销售订单数据
		for (ErpContRelat erpContRelat : li
				.toArray(new ErpContRelat[li.size()])) {
			// 传值失败，将错误信息打出来，抛出错误信息到前台
			if (erpContRelat.getType().equalsIgnoreCase("e")) {

				ExceptionUtils
						.wrappBusinessException(erpContRelat.getMessage());
			}
		}
		// 更新销售订单的数据
		this.updateSaleOrder(li);

	}

	/**
	 * @param li
	 */
	private void updateSaleOrder(List<ErpContRelat> li) {
		// TODO 自动生成的方法存根
		VOQuery<SaleOrderBVO> query = new VOQuery<SaleOrderBVO>(
				SaleOrderBVO.class);
		List<SaleOrderBVO> bvosl = new ArrayList<SaleOrderBVO>();
		String pk_saleorder = null;
		SaleOrderHVO hvo = new SaleOrderHVO();
		for (int i = 0; i < li.size(); i++) {
			ErpContRelat orgvo = li.get(i);
			// 通过申请号和申请行号查询
			String banfn = orgvo.getBanfn();// ERP采购申请号
			String bnfpo = orgvo.getBnfpo();// ERP采购申请行号
			// 赋值字段
			String ebeln = orgvo.getEbeln();
			Integer ebelp = orgvo.getEbelp();

			// 根据什么一样查出来相关销售订单表体数据
			SaleOrderBVO[] bvos = query.query("and  application_no='" + banfn
					+ "' and application_line='" + bnfpo + "'", null);
			/**==============2018-02-26======可能查出的bvos 为空，这样会导致更新不了销售订单，但又不能第二次传股份，该如何处理======*/
			// 给销售订单表头赋值
			pk_saleorder = bvos[0].getCsaleorderid();
			hvo.setPrimaryKey(pk_saleorder);
			hvo.setAttributeValue("vcooppohcode", ebeln);

			// 给销售订单表体赋值
			bvos[0].setVbdef2(ebeln);
			bvos[0].setVbdef3(String.valueOf(ebelp));

			bvosl.add(bvos[0]);

		}

		VOUpdate<SaleOrderBVO> bUpdate = new VOUpdate<SaleOrderBVO>();
		VOUpdate<SaleOrderHVO> hUpdate = new VOUpdate<SaleOrderHVO>();
		/*
		 * 批量更新减少访问数据库次 数要更新哪两个字段
		 */
		bUpdate.update(bvosl.toArray(new SaleOrderBVO[bvosl.size()]),
				new String[] { "vbdef2", "vbdef3" });
		// 更新表頭的erp合同號，後來宋國強新增加的需求
		hUpdate.update(new SaleOrderHVO[] { hvo },
				new String[] { "vcooppohcode" });

	}

	/**
	 * @param agg
	 *            保存nc中间表
	 */
	private void saveNcTalbe(AggErphtdygxHVO[] agg) {
		// TODO 自动生成的方法存根
		// 存中间表
		IErphtdygxMaintain insert = NCLocator.getInstance().lookup(
				IErphtdygxMaintain.class);

		try {
			insert.insert(agg, null);
		} catch (BusinessException e) {
			// TODO 自动生成的 catch 块
			// 传中间表失败
			ExceptionUtils.wrappBusinessException("保存到中间表出现错误，本次传股份数据失败");
			e.printStackTrace();
		}

	}

	/**
	 * @param 传过来的数据
	 * @return 组织好的nc中间表单据
	 */
	private AggErphtdygxHVO[] transAggvo(List<ErpContRelat> li) {
		// TODO 自动生成的方法存根
		ErpContRelat[] org = li.toArray(new ErpContRelat[li.size()]);
		List<AggErphtdygxHVO> aggvo = new ArrayList<AggErphtdygxHVO>();
		for (int i = 0; i < org.length; i++) {
			AggErphtdygxHVO agg = new AggErphtdygxHVO();
			ErphtdygxHVO hvo = new ErphtdygxHVO();
			ErpContRelat erpContRelat = org[i];
			// 组织
			hvo.setPk_org("GLOBLE00000000000000");
			// 集团
			hvo.setPk_group(AppContext.getInstance().getPkGroup());
			// 单据类型
			// 不确定是pk还是编码hvo.setBillcode("SJO1");
			hvo.setBillcode("0001ZZ1000000006DLO5");
			// 单据状态
			hvo.setBillstatus(-1);
			// 单据日期
			hvo.setDbilldate(AppContext.getInstance().getServerTime().getDate());
			// 制单人
			// 制单人暂时为空
			// erp采购合同号
			hvo.setEbeln(erpContRelat.getEbeln());
			// erp采购合同行号
			hvo.setEbelp(erpContRelat.getEbelp());
			// 国贸销售合同号
			hvo.setSrcordergm(erpContRelat.getSrcordergm());
			// erp采购申请号
			hvo.setBanfn(erpContRelat.getBanfn());
			// erp采购申请行号
			hvo.setBnfpo(erpContRelat.getBnfpo());
			// 消息状态
			hvo.setType(erpContRelat.getType());
			// 消息信息
			hvo.setMessage(erpContRelat.getMessage());
			agg.setParentVO(hvo);
			aggvo.add(agg);

		}

		return aggvo.toArray(new AggErphtdygxHVO[aggvo.size()]);
	}

	/**
	 * 传国贸数据
	 * 
	 * @param saleOrderVO
	 * @return
	 */
	private ErpContMain sendGfData(SaleOrderVO saleOrderVO) {
		// TODO 自动生成的方法存根
		// 聚合电文
		// 主表就是聚合
		ErpContMain agg = new ErpContMain();
		// 主表电文
		// ErpContMain zb = new ErpContMain();
		// 子表电文
		List<ErpContSub> mx = new ArrayList<ErpContSub>();
		SaleOrderHVO hVO = saleOrderVO.getParentVO();
		SaleOrderBVO[] bVOS = saleOrderVO.getChildrenVO();
		// 主表中需要取得值，集团和组织
		String pk_group = hVO.getPk_group();
		String pk_org = hVO.getPk_org();

		String saleOrderNO = saleOrderVO.getParentVO().getVbillcode()
				.toString();

		agg.setSrcordergm(saleOrderNO);// 销售合同号
		agg.setLifnr("0001230000");// 供应商编码，固定为0001230000
		// agg.setBurks("BYQ05");//需修改公司代码，需要取需要取子表任意一行的公司代码
		agg.setBurks(nvl(bVOS[0].getAttributeValue("code_facty")));// 采购组，需要取子表任意一行的采购组
		agg.setEkgrp(nvl(bVOS[0].getAttributeValue("req_group")));// 采购组，需要取子表任意一行的采购组
		agg.setEkorg(nvl(bVOS[0].getAttributeValue("vbdef4")));// 采购组织，需要取子表任意一行的采购组织

		// 子表中需要取得值,先随便取
		for (SaleOrderBVO saleOrderBVO : bVOS) {
			ErpContSub item = new ErpContSub();
			// 需求号现在为空

			// 采购申请号
			String banfn = (String) saleOrderBVO
					.getAttributeValue("application_no");
			// 采购申请行号
			String bnfpo = (String) saleOrderBVO
					.getAttributeValue("application_line");// 项目主键
															// application_line
			/**
			 * time 2017-08-23 根据苏万斌和宋国强等的要求 传过去的数量规则如下
			 *  1.当数量（价审的订货数量）大于等于计划数量时传计划数量
			 *  2.当数量（价审的订货数量）小于计划数量时 传数量（订货数量）
			 */
			// 采购数量(上游订货数量)
			UFDouble menge = (UFDouble) saleOrderBVO
					.getAttributeValue("nastnum");// 项目主键 nastnum
			// 计划数量
			Integer plan_num = (Integer)saleOrderBVO.getAttributeValue("plan_num");
			if (menge!=null&&plan_num!=null&&menge.intValue() >= plan_num) {
				menge = new UFDouble(plan_num);
			}
			/*************************** end *********************/

			item.setBanfn(banfn);
			item.setBnfpo(bnfpo);
			item.setMenge(new BigDecimal(menge.getDouble()));
			// 子表的合同号=主表的合同号
			item.setSrcordergm(saleOrderNO);
			// 合同单价
			UFDouble norigprice = saleOrderBVO.getNqtorigprice();// 无税单价
			// nqtorigtaxprice
			item.setNetpr(BigDecimal.valueOf(norigprice.doubleValue())
					.setScale(2, BigDecimal.ROUND_HALF_UP));
			// 价格单位
			String epein = nvl(saleOrderBVO.getAttributeValue("unit_pric"));
			if (epein != null) {

				item.setEpein(BigInteger.valueOf(Long.valueOf(epein)));
			}
			// 交货日期
			//2018-04-02 根据苏万斌要求，交货期由requst_date改为jiaohuodate字段
			String data = nvl(saleOrderBVO.getAttributeValue("jiaohuodate"));
			UFDate dat = new UFDate(data);
			if (data != null) {
				String date = dat.toStdString().substring(0, 4)
						+ dat.toStdString().substring(5, 7)
						+ dat.toStdString().substring(8, 10);
				item.setBadat(date);
			}

			// 稅碼（固定稅率為17）
			//根据鞍钢苏万斌要求税率现在改为16对应税码为J7 2018-04-27
			//item.setMwskz("J1");
			item.setMwskz("J7");
			mx.add(item);
		}
		// agg.setCGHTTT(zb);
		agg.setErpContSubList(mx);
		return agg;

	}

	/**
	 * 通过pk_id获取对应的url
	 */
	public String getRestURL(String telId) {
		BaseDAO baseDao = new BaseDAO();
		String rsSql = "SELECT URL_PATH FROM RT_BASEURL WHERE PK_ID='" + telId
				+ "'";
		String retString = null;
		try {
			retString = (String) baseDao.executeQuery(rsSql,
					new ResultSetProcessor() {
						@Override
						public String handleResultSet(ResultSet rs)
								throws SQLException {
							String url = null;
							while (rs.next()) {
								url = rs.getString(1);
							}
							return url;
						}
					});
		} catch (DAOException e) {
			e.printStackTrace();
		}
		return retString.trim();
	}

	/**
	 * 判断是否为空
	 * 
	 * @param para
	 * @return
	 */
	private static String nvl(Object para) {
		if (para == null) {
			return null;
		} else {
			return para.toString();
		}
	}

}
