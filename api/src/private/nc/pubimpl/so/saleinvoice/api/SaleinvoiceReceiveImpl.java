package nc.pubimpl.so.saleinvoice.api;


import java.math.BigDecimal;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.text.DecimalFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.web.client.RestTemplate;

import nc.vo.pub.BusinessException;
import nc.vo.pub.CircularlyAccessibleValueObject;
import nc.vo.pub.VOStatus;
import nc.vo.pub.lang.UFBoolean;
import nc.vo.pub.lang.UFDate;
import nc.vo.pub.lang.UFDateTime;
import nc.vo.pub.lang.UFDouble;
import nc.vo.pubapp.AppContext;
import nc.vo.so.saleinvoice.MsgAGCG000002;
import nc.vo.so.saleinvoice.MsgAGCG000002Head;
import nc.vo.so.saleinvoice.MsgAGCG000003;
import nc.vo.so.saleinvoice.MsgAGCG000003Head;
import nc.bs.dao.BaseDAO;
import nc.bs.dao.DAOException;
import nc.bs.framework.common.NCLocator;
import nc.impl.so.m32.action.InsertSaleInvoiceAction;
import nc.itf.so.m32.ISaleInvoiceMaintain;
import nc.itf.uif.pub.IUifService;
import nc.jdbc.framework.processor.ResultSetProcessor;
import nc.md.data.access.NCObject;
import nc.md.model.MetaDataException;
import nc.md.persist.framework.MDPersistenceService;
import nc.pubitf.para.SysInitQuery;
import nc.pubitf.so.saleinvoice.api.ISaleinvoiceReceive;
import nc.vo.ecpubapp.pattern.exception.ExceptionUtils;
import nc.vo.lm.pgerpxsfp.Agcg000002HVO;
import nc.vo.lm.pgerpxsfpmx.Agcg000003HVO;
import nc.vo.lm.pgjdjjjsxx.AggCgag000003HVO;
import nc.vo.lm.pgjdjjjsxx.Cgag000003HVO;
import nc.vo.so.m32.entity.SaleInvoiceBVO;
import nc.vo.so.m32.entity.SaleInvoiceHVO;
import nc.vo.so.m32.entity.SaleInvoiceVO;

public class SaleinvoiceReceiveImpl implements ISaleinvoiceReceive {

	@Override
	public String receiveMsgCGAG000003() {
		// TODO 自动生成的方法存根
		String errorMsg = "";
		try{
			IUifService queryService = NCLocator.getInstance().lookup(IUifService.class);
			Cgag000003HVO[] hvos = (Cgag000003HVO[])queryService.queryByCondition(Cgag000003HVO.class, "msgflag=0 and dr=0");
			
			if(hvos!=null){
				Map<String, List<Cgag000003HVO>> mapData = new HashMap<String, List<Cgag000003HVO>>();
				for(int i=0;i<hvos.length;i++){
					//获取结算单单号，根据结算单号进行分组
					String balanceid = hvos[i].getBalanceid();
					
					List<Cgag000003HVO> list = mapData.get(balanceid);
					if(list == null){
						List<Cgag000003HVO> newList = new ArrayList<Cgag000003HVO>();
						newList.add(hvos[i]);
						mapData.put(balanceid, newList);
					}else{
						list.add(hvos[i]);
					}
				}
				
				errorMsg = genAggSaleInvoiceVO(mapData);
			}
				
	
		}catch (Exception e) {
			// TODO 自动生成的 catch 块/*////////////
			ExceptionUtils.wrappBusinessException("数据接收失败!");
		}
		return errorMsg;
	}

	private String genAggSaleInvoiceVO(Map<String, List<Cgag000003HVO>> mapData){
		String errorMsg = "";
		if(mapData == null){
			return "没有待接收数据";
		}else if(mapData.size()==0){
			return "没有待接收数据";
		}
		ISaleInvoiceMaintain iMaintain = NCLocator.getInstance().lookup(
				ISaleInvoiceMaintain.class);
		for(String keyString : mapData.keySet()){
			String vbillcode = getKeyValue(); 
			
			Cgag000003HVO voCgag000003hvo = mapData.get(keyString).get(0);
			SaleInvoiceHVO saleInvoiceHVO = new SaleInvoiceHVO();
			List<Map<String, Object>> cList = this.isHaveVbillcode(vbillcode);
			List<SaleInvoiceBVO> saleinvoiceList = new ArrayList<SaleInvoiceBVO>();
			if (cList!=null&&cList.size()>0) {
				Object obj = cList.get(0).get("csaleinvoiceid");
				String hid = obj + "";
				String []hids = {hid};
				SaleInvoiceVO saleInvoiceVO = new SaleInvoiceVO();
				try {
					SaleInvoiceVO []saleInvoiceVOs = iMaintain.querySaleInvoice(hids);
					saleInvoiceVO = saleInvoiceVOs[0];
				} catch (BusinessException e) {
					// TODO 自动生成的 catch 块
					e.printStackTrace();
				}
				saleInvoiceHVO = saleInvoiceVO.getParentVO();
				saleInvoiceHVO.setStatus(VOStatus.UPDATED);
				
				for (int i = 0; i < mapData.get(keyString).size(); i++) {
					SaleInvoiceBVO saleInvoiceBVO = new SaleInvoiceBVO();
					Cgag000003HVO cgag000003hvo1 = mapData.get(keyString).get(i);
					saleInvoiceBVO.setVbdef9(cgag000003hvo1.getBpoid());
					saleInvoiceBVO.setVbdef10(cgag000003hvo1.getBpolineid());
					saleInvoiceBVO.setVbdef11(cgag000003hvo1.getBalanceid());
					saleInvoiceBVO.setVbdef12(cgag000003hvo1.getBalancelineid());
					saleInvoiceBVO.setVbdef13(cgag000003hvo1.getOrderid());
					saleInvoiceBVO.setVbdef14(cgag000003hvo1.getOrderlineid());
					saleInvoiceBVO.setVbdef8(cgag000003hvo1.getDrymeasure());
					saleInvoiceBVO.setVbdef7(cgag000003hvo1.getWetmeasure()+"");
					//设置行号
					Integer it = (i + 1) * 10;
					String crowno = Integer.toString(it);
					saleInvoiceBVO.setCrowno(crowno);

					//无税金额
					saleInvoiceBVO.setNorigmny(cgag000003hvo1.getBalance_price());
					//无税单价
					saleInvoiceBVO.setNqtorigprice(cgag000003hvo1.getBalance_unit_price());
					//含税单价
					UFDouble ufDouble = new UFDouble();
					double rate = ufDouble.add(cgag000003hvo1.getTaxrate()).doubleValue();
					double balanceunitprice = ufDouble.add(cgag000003hvo1.getBalance_unit_price()).doubleValue();
					saleInvoiceBVO.setNqtorigtaxprice(new UFDouble(balanceunitprice*(1+rate)));
					//含税净价
					saleInvoiceBVO.setNqtorigtaxnetprc(new UFDouble(balanceunitprice*(1+rate)));
					//无税净价
					saleInvoiceBVO.setNqtorignetprice(cgag000003hvo1.getBalance_unit_price());
					//本币价税合计
					saleInvoiceBVO.setNtaxmny(cgag000003hvo1.getTaxamount());
					//主数量，
					saleInvoiceBVO.setNnum(new UFDouble(cgag000003hvo1.getDrymeasure()));
					//数量
					saleInvoiceBVO.setNastnum(new UFDouble(cgag000003hvo1.getDrymeasure()));
					//换算率
					saleInvoiceBVO.setVchangerate("1/1");
					//税率
					saleInvoiceBVO.setNtaxrate(new UFDouble(cgag000003hvo1.getTaxrate().doubleValue()*100));
					//税额
					saleInvoiceBVO.setNtax(cgag000003hvo1.getTaxprice());
					//税码-NC中是参照增值税税码税率档案bd_taxcode，默认编码CN01
					String ctaxcodeid = this.getTaxCodeByCn01("CN01");
					saleInvoiceBVO.setCtaxcodeid(ctaxcodeid);
					//扣税类别-NC中为枚举项，默认枚举值为1
					saleInvoiceBVO.setFtaxtypeflag(1);
					//价税合计
					saleInvoiceBVO.setNorigtaxmny(cgag000003hvo1.getTaxamount());
					saleInvoiceBVO.setStatus(VOStatus.NEW);
					//整单折扣
					saleInvoiceBVO.setNdiscountrate(new UFDouble(100));
					//单品折扣
					saleInvoiceBVO.setNitemdiscountrate(new UFDouble(100));
					//发票折扣
					saleInvoiceBVO.setNinvoicedisrate(new UFDouble(100));
					
					//采购合同号
					String bpoId = cgag000003hvo1.getBpoid();
					//采购合同行号
					String bpoLineId = cgag000003hvo1.getBpolineid();
					//执行单号
					String orderid = cgag000003hvo1.getOrderid();
					//执行单行号
					String orderlineid = cgag000003hvo1.getOrderlineid();
					
					//客户税号、电话、银行账号
					String customer = "";
					String tel = "";
					String bankid = "";
					String ywlc = "";
					String bwb = "";
					String shgj = "";
					String fhgj = "";
					String sjmy = "";
					String bsgj = "";
					String wl = "";
					String jsje = "";
					String zwsjj = "";
					String zbbwsjj = "";
					String bbjshj = "";
					String bbwsje = "";
					List<Map<String, Object>> saleInfoList = new ArrayList<Map<String, Object>>();
					List<Map<String, Object>> saleInfoList1 = this.getSaleInfo(bpoId, bpoLineId);
					List<Map<String, Object>> saleInfoList2 = this.getSaleInfo(orderid, orderlineid);
					if (saleInfoList1.size()>0) {
						saleInfoList = saleInfoList1;
					}else if(saleInfoList2.size()>0){
						saleInfoList = saleInfoList2;
					}
					else {
						mapData.get(keyString).get(i).setMsginfo("未找到销售订单");
//						ExceptionUtils.wrappBusinessException("未找到销售订单");
						continue;
					}
					
					if (saleInfoList.size()>0) {
						saleInvoiceBVO.setCmaterialvid(checkIsNull(saleInfoList.get(0).get("cmaterialvid")));
						saleInvoiceBVO.setCunitid(checkIsNull(saleInfoList.get(0).get("cunitid")));
						//单位
						saleInvoiceBVO.setCastunitid(checkIsNull(saleInfoList.get(0).get("castunitid")));
						//客户名称
						customer = checkIsNull(saleInfoList.get(0).get("ccustomerid"));
						saleInvoiceHVO.setCinvoicecustid(customer);
//						//业务流程
//						ywlc = checkIsNull(saleInfoList.get(0).get("cbiztypeid"));
//						saleInvoiceHVO.setCbiztypeid(ywlc);
//						//本位币
//						bwb = checkIsNull(saleInfoList.get(0).get("ccurrencyid"));
//						saleInvoiceHVO.setCcurrencyid(bwb);
//						//收货国家/地区
//						shgj = checkIsNull(saleInfoList.get(0).get("crececountryid"));
//						saleInvoiceHVO.setCrececountryid(shgj);
//						//发货国家/地区
//						fhgj = checkIsNull(saleInfoList.get(0).get("csendcountryid"));
//						saleInvoiceHVO.setCsendcountryid(fhgj);
//						//三角贸易
//						sjmy = checkIsNull(saleInfoList.get(0).get("btriatradeflag"));
//						saleInvoiceHVO.setBtriatradeflag(UFBoolean.valueOf(sjmy));
//						//报税国家
//						bsgj = checkIsNull(saleInfoList.get(0).get("ctaxcountryid"));
//						saleInvoiceHVO.setCtaxcountryid(bsgj);
						//物料
						wl = checkIsNull(saleInfoList.get(0).get("cmaterialid"));
						saleInvoiceBVO.setCmaterialid(wl);
						//计税金额
						jsje = checkIsNull(saleInfoList.get(0).get("ncaltaxmny"));
						saleInvoiceBVO.setNcaltaxmny(new UFDouble(jsje));
						//主无税净价
						zwsjj = checkIsNull(saleInfoList.get(0).get("norignetprice"));
						saleInvoiceBVO.setNorignetprice(new UFDouble(zwsjj));
						//主本币无税净价
						zbbwsjj = checkIsNull(saleInfoList.get(0).get("nnetprice"));
						saleInvoiceBVO.setNnetprice(new UFDouble(zbbwsjj));
						//本币价税合计
						bbjshj = checkIsNull(saleInfoList.get(0).get("ntaxmny"));
//						saleInvoiceBVO.setNtaxmny(new UFDouble(bbjshj));
						//本币无税金额
						bbwsje = checkIsNull(saleInfoList.get(0).get("nmny"));
						saleInvoiceBVO.setNmny(new UFDouble(bbwsje));
					}
					
					//电话
					//银行账号
//					bankid = checkIsNull(saleInfoList.get(0).get(""));
					//源头单据号、源头单据行号
					List<Map<String, Object>> vfirstInfoList = this.getVfirstInfo(bpoId, bpoLineId);
					if (vfirstInfoList.size()>0) {
						saleInvoiceBVO.setVfirstcode(vfirstInfoList.get(0).get("vfirstcode")+"");
						saleInvoiceBVO.setVfirstrowno(vfirstInfoList.get(0).get("vfirstrowno")+"");
					}
					
					//来源单据号、来源单据行号
					List<Map<String, Object>> vSrcInfoList = this.getVSrcInfo(bpoId, bpoLineId);
					if (vSrcInfoList.size()>0) {
						saleInvoiceBVO.setVfirstcode(vSrcInfoList.get(0).get("vsrccode")+"");
						saleInvoiceBVO.setVfirstrowno(vSrcInfoList.get(0).get("vsrcrowno")+"");
					}
					saleinvoiceList.add(saleInvoiceBVO);
				}
			}
			else {
				//发票号-系统自动生成
				saleInvoiceHVO.setVbillcode(getKeyValue());
				String gorg = "GLOBLE00000000000000";
				String pk_orgs="";
				String pk_group="";
				String pk_org_v="";
				try {
					pk_orgs = SysInitQuery.getParaString(gorg, "PGJSD002");
					pk_group = SysInitQuery.getParaString(gorg, "PGJSD001");
					pk_org_v = SysInitQuery.getParaString(gorg, "PGJSD003");
				} catch (BusinessException e1) {
					// TODO 自动生成的 catch 块
					e1.printStackTrace();
				}
				if ("".equals(pk_orgs)) {
					return "没有找到组织信息";
				}else {
					//组织-国贸攀枝花公司
					saleInvoiceHVO.setPk_org(pk_orgs);
				}
				if ("".equals(pk_group)) {
					return "没有找到集团信息";
				}else {
					//集团
					saleInvoiceHVO.setPk_group(pk_group);
				}
				if ("".equals(pk_org_v)) {
					return "没有找到组织版本信息";
				}else {
					//开票组织版本-国贸攀枝花公司
					saleInvoiceHVO.setPk_org_v(pk_org_v);
				}
				
				//发票类型编码-增值税发票
				saleInvoiceHVO.setVtrantypecode("32-01");
				//发票类型
				saleInvoiceHVO.setCtrantypeid("0001N6100000000023MO");
				//开票日期-接收单据日期
				saleInvoiceHVO.setDbilldate(new UFDate(voCgag000003hvo.getCreationtime()+""));
				//币种-CNY
				saleInvoiceHVO.setCorigcurrencyid("1002Z0100000000001K1");
				//购销类型-国内销售
				saleInvoiceHVO.setFbuysellflag(1);
				//折本汇率
				saleInvoiceHVO.setNexchangerate(new UFDouble(1));
				//对冲标记，NC中字段为枚举，枚举取值0=正常
				saleInvoiceHVO.setFopposeflag(0);
				//单据状态  默认1-自由态
				saleInvoiceHVO.setFstatusflag(1);
				//制单人
				saleInvoiceHVO.setBillmaker(AppContext.getInstance().getPkUser());
				//制单时间
				saleInvoiceHVO.setCreationtime(AppContext.getInstance().getServerTime());
				//价税合计-同一结算单含税金额合计
				
				//价税金额合计
				UFDouble taxAmount = UFDouble.ZERO_DBL;
				for(Cgag000003HVO tempvo : mapData.get(keyString)){
					taxAmount = taxAmount.add(tempvo.getTaxamount());
				}
				saleInvoiceHVO.setNtotalorigmny(new UFDouble(taxAmount));
				//客户名称
				saleInvoiceHVO.setCinvoicecustid(voCgag000003hvo.getCompanyname());
				saleInvoiceHVO.setStatus(VOStatus.NEW);
				for (int i = 0; i < mapData.get(keyString).size(); i++) {
					SaleInvoiceBVO saleInvoiceBVO = new SaleInvoiceBVO();
					Cgag000003HVO cgag000003hvo1 = mapData.get(keyString).get(i);
					saleInvoiceBVO.setVbdef9(cgag000003hvo1.getBpoid());
					saleInvoiceBVO.setVbdef10(cgag000003hvo1.getBpolineid());
					saleInvoiceBVO.setVbdef11(cgag000003hvo1.getBalanceid());
					saleInvoiceBVO.setVbdef12(cgag000003hvo1.getBalancelineid());
					saleInvoiceBVO.setVbdef13(cgag000003hvo1.getOrderid());
					saleInvoiceBVO.setVbdef14(cgag000003hvo1.getOrderlineid());
					saleInvoiceBVO.setVbdef8(cgag000003hvo1.getDrymeasure());
					saleInvoiceBVO.setVbdef7(cgag000003hvo1.getWetmeasure()+"");
					//设置行号
					Integer it = (i + 1) * 10;
					String crowno = Integer.toString(it);
					saleInvoiceBVO.setCrowno(crowno);

					//无税金额
					saleInvoiceBVO.setNorigmny(cgag000003hvo1.getBalance_price());
					//无税单价
					saleInvoiceBVO.setNqtorigprice(cgag000003hvo1.getBalance_unit_price());
					//含税单价
					UFDouble ufDouble = new UFDouble();
					double rate = ufDouble.add(cgag000003hvo1.getTaxrate()).doubleValue();
					double balanceunitprice = ufDouble.add(cgag000003hvo1.getBalance_unit_price()).doubleValue();
					saleInvoiceBVO.setNqtorigtaxprice(new UFDouble(balanceunitprice*(1+rate)));
					//含税净价
					saleInvoiceBVO.setNqtorigtaxnetprc(new UFDouble(balanceunitprice*(1+rate)));
					//无税净价
					saleInvoiceBVO.setNqtorignetprice(cgag000003hvo1.getBalance_unit_price());
					//本币价税合计
					saleInvoiceBVO.setNtaxmny(cgag000003hvo1.getTaxamount());
					//主数量，
					saleInvoiceBVO.setNnum(new UFDouble(cgag000003hvo1.getDrymeasure()));
					//数量
					saleInvoiceBVO.setNastnum(new UFDouble(cgag000003hvo1.getDrymeasure()));
					//换算率
					saleInvoiceBVO.setVchangerate("1/1");
					//税率
					saleInvoiceBVO.setNtaxrate(new UFDouble(cgag000003hvo1.getTaxrate().doubleValue()*100));
					//税额
					saleInvoiceBVO.setNtax(cgag000003hvo1.getTaxprice());
					//税码-NC中是参照增值税税码税率档案bd_taxcode，默认编码CN01
					String ctaxcodeid = this.getTaxCodeByCn01("CN01");
					saleInvoiceBVO.setCtaxcodeid(ctaxcodeid);
					//扣税类别-NC中为枚举项，默认枚举值为1
					saleInvoiceBVO.setFtaxtypeflag(1);
					//价税合计
					saleInvoiceBVO.setNorigtaxmny(cgag000003hvo1.getTaxamount());
					saleInvoiceBVO.setStatus(VOStatus.NEW);
					//整单折扣
					saleInvoiceBVO.setNdiscountrate(new UFDouble(100));
					//单品折扣
					saleInvoiceBVO.setNitemdiscountrate(new UFDouble(100));
					//发票折扣
					saleInvoiceBVO.setNinvoicedisrate(new UFDouble(100));
					
					//采购合同号
					String bpoId = cgag000003hvo1.getBpoid();
					//采购合同行号
					String bpoLineId = cgag000003hvo1.getBpolineid();
					//执行单号
					String orderid = cgag000003hvo1.getOrderid();
					//执行单行号
					String orderlineid = cgag000003hvo1.getOrderlineid();
					
					//客户税号、电话、银行账号
					String customer = "";
					String tel = "";
					String bankid = "";
					String ywlc = "";
					String bwb = "";
					String shgj = "";
					String fhgj = "";
					String sjmy = "";
					String bsgj = "";
					String wl = "";
					String jsje = "";
					String zwsjj = "";
					String zbbwsjj = "";
					String bbjshj = "";
					String bbwsje = "";
					List<Map<String, Object>> saleInfoList = new ArrayList<Map<String, Object>>();
					List<Map<String, Object>> saleInfoList1 = this.getSaleInfo(bpoId, bpoLineId);
					List<Map<String, Object>> saleInfoList2 = this.getSaleInfo(orderid, orderlineid);
					if (saleInfoList1.size()>0) {
						saleInfoList = saleInfoList1;
					}else if(saleInfoList2.size()>0){
						saleInfoList = saleInfoList2;
					}
					else {
						mapData.get(keyString).get(i).setMsginfo("未找到销售订单");
//						ExceptionUtils.wrappBusinessException("未找到销售订单");
						continue;
					}
					
					if (saleInfoList.size()>0) {
						saleInvoiceBVO.setCmaterialvid(checkIsNull(saleInfoList.get(0).get("cmaterialvid")));
						saleInvoiceBVO.setCunitid(checkIsNull(saleInfoList.get(0).get("cunitid")));
						//单位
						saleInvoiceBVO.setCastunitid(checkIsNull(saleInfoList.get(0).get("castunitid")));
						//客户名称
						customer = checkIsNull(saleInfoList.get(0).get("ccustomerid"));
						saleInvoiceHVO.setCinvoicecustid(customer);
						//业务流程
						ywlc = checkIsNull(saleInfoList.get(0).get("cbiztypeid"));
						saleInvoiceHVO.setCbiztypeid(ywlc);
						//本位币
						bwb = checkIsNull(saleInfoList.get(0).get("ccurrencyid"));
						saleInvoiceHVO.setCcurrencyid(bwb);
						//收货国家/地区
						shgj = checkIsNull(saleInfoList.get(0).get("crececountryid"));
						saleInvoiceHVO.setCrececountryid(shgj);
						//发货国家/地区
						fhgj = checkIsNull(saleInfoList.get(0).get("csendcountryid"));
						saleInvoiceHVO.setCsendcountryid(fhgj);
						//三角贸易
						sjmy = checkIsNull(saleInfoList.get(0).get("btriatradeflag"));
						saleInvoiceHVO.setBtriatradeflag(UFBoolean.valueOf(sjmy));
						//报税国家
						bsgj = checkIsNull(saleInfoList.get(0).get("ctaxcountryid"));
						saleInvoiceHVO.setCtaxcountryid(bsgj);
						//物料
						wl = checkIsNull(saleInfoList.get(0).get("cmaterialid"));
						saleInvoiceBVO.setCmaterialid(wl);
						//计税金额
						jsje = checkIsNull(saleInfoList.get(0).get("ncaltaxmny"));
						saleInvoiceBVO.setNcaltaxmny(new UFDouble(jsje));
						//主无税净价
						zwsjj = checkIsNull(saleInfoList.get(0).get("norignetprice"));
						saleInvoiceBVO.setNorignetprice(new UFDouble(zwsjj));
						//主本币无税净价
						zbbwsjj = checkIsNull(saleInfoList.get(0).get("nnetprice"));
						saleInvoiceBVO.setNnetprice(new UFDouble(zbbwsjj));
						//本币价税合计
						bbjshj = checkIsNull(saleInfoList.get(0).get("ntaxmny"));
//						saleInvoiceBVO.setNtaxmny(new UFDouble(bbjshj));
						//本币无税金额
						bbwsje = checkIsNull(saleInfoList.get(0).get("nmny"));
						saleInvoiceBVO.setNmny(new UFDouble(bbwsje));
					}
					
					//电话
					//银行账号
//					bankid = checkIsNull(saleInfoList.get(0).get(""));
					//源头单据号、源头单据行号
					List<Map<String, Object>> vfirstInfoList = this.getVfirstInfo(bpoId, bpoLineId);
					if (vfirstInfoList.size()>0) {
						saleInvoiceBVO.setVfirstcode(vfirstInfoList.get(0).get("vfirstcode")+"");
						saleInvoiceBVO.setVfirstrowno(vfirstInfoList.get(0).get("vfirstrowno")+"");
					}
					
					//来源单据号、来源单据行号
					List<Map<String, Object>> vSrcInfoList = this.getVSrcInfo(bpoId, bpoLineId);
					if (vSrcInfoList.size()>0) {
						saleInvoiceBVO.setVfirstcode(vSrcInfoList.get(0).get("vsrccode")+"");
						saleInvoiceBVO.setVfirstrowno(vSrcInfoList.get(0).get("vsrcrowno")+"");
					}
					saleinvoiceList.add(saleInvoiceBVO);
				}
			}
			
			
			
			SaleInvoiceVO saleInvoiceVO = new SaleInvoiceVO();
			saleInvoiceVO.setParent(saleInvoiceHVO);
			SaleInvoiceBVO []saleInvoiceBVOs = saleinvoiceList.toArray(new SaleInvoiceBVO[0]);
			saleInvoiceVO.setChildrenVO((CircularlyAccessibleValueObject[])saleInvoiceBVOs);
			
//			BaseDAO baseDAO = new BaseDAO();
			NCObject objs = NCObject.newInstance(saleInvoiceVO);
			InsertSaleInvoiceAction insertSaleInvoiceAction = new InsertSaleInvoiceAction();
			SaleInvoiceVO[] newvos = new SaleInvoiceVO[]{saleInvoiceVO};
			
//			insertSaleInvoiceAction.insert(newvos);
			try {
				insertSaleInvoiceAction.insert(newvos);
//				MDPersistenceService.lookupPersistenceService().saveBill(objs);

//				baseDAO.insertVO(saleInvoiceBVO);
				//回写中间表
				for (int i = 0; i < mapData.get(keyString).size(); i++) {
					this.writeBack(mapData.get(keyString).get(i));
				}
			} catch (Exception e) {
				// TODO 自动生成的 catch 块
				e.printStackTrace();
//				errorMsg = "中间表回写失败";
				errorMsg = e.toString();
			}
		}
		return errorMsg;
	}
	
	public void dsinsert(AggCgag000003HVO aggCgag000003HVO) {
		Cgag000003HVO cgag000003hvo = aggCgag000003HVO.getParentVO();
		SaleInvoiceHVO saleInvoiceHVO = new SaleInvoiceHVO();
		//发票号-系统自动生成
		saleInvoiceHVO.setVbillcode(getKeyValue());
		//集团
		saleInvoiceHVO.setPk_group("0001N610000000000IT0");
		//组织-国贸攀枝花公司
		saleInvoiceHVO.setPk_org("0001N610000000002JW4");
		//开票组织版本-国贸攀枝花公司
		saleInvoiceHVO.setPk_org_v("0001N610000000002JW3");
		//发票类型编码-增值税发票
		saleInvoiceHVO.setVtrantypecode("32-01");
		//发票类型
		saleInvoiceHVO.setCtrantypeid("0001N6100000000023MO");
		//开票日期-接收单据日期
		saleInvoiceHVO.setDbilldate(new UFDate(cgag000003hvo.getCreationtime()+""));
		//币种-CNY
		saleInvoiceHVO.setCorigcurrencyid("1002Z0100000000001K1");
		
		//对冲标记，NC中字段为枚举，枚举取值0=正常
		saleInvoiceHVO.setFopposeflag(0);
		//单据状态  默认1-自由态
		saleInvoiceHVO.setFstatusflag(1);
		//制单人
		saleInvoiceHVO.setBillmaker(AppContext.getInstance().getPkUser());
		//制单时间
		saleInvoiceHVO.setCreationtime(AppContext.getInstance().getServerTime());
		//价税合计-同一结算单含税金额合计
		//结算单单号
		String balanceId = cgag000003hvo.getBalanceid();
		List<Map<String, String>> cgag000003List = this.getbalanceIdList(balanceId);
		//价税金额合计
		double taxAmount = 0;
		if (cgag000003List!=null) {
			for (int i = 0; i < cgag000003List.size(); i++) {
				double jsje = Double.parseDouble(cgag000003List.get(i).get("jsje")) ;
				taxAmount += jsje;
			}
		}
		saleInvoiceHVO.setNtotalorigmny(new UFDouble(taxAmount));
		//客户名称
		saleInvoiceHVO.setCinvoicecustid(cgag000003hvo.getCompanyname());
		//源头单据号
		NCObject[] ncObjects={};
		List<Cgag000003HVO> cgagbovList = new ArrayList<Cgag000003HVO>();
		try {
			ncObjects = MDPersistenceService.lookupPersistenceQueryService().queryBillOfNCObjectByCond
					(Cgag000003HVO.class,"BALANCEID='"+balanceId+"'",false);
		} catch (MetaDataException e1) {
			// TODO 自动生成的 catch 块
			e1.printStackTrace();
		}
		
		if(ncObjects!=null){
			for(int i=0;i<ncObjects.length;i++){
				AggCgag000003HVO aggvo =new AggCgag000003HVO();
				aggvo=(AggCgag000003HVO)ncObjects[i].getContainmentObject();
				Cgag000003HVO cgag000003hvo2 = (Cgag000003HVO)aggvo.getParent();
				cgagbovList.add(cgag000003hvo2);
				}
		}
		
		List<SaleInvoiceBVO> saleinvoiceList = new ArrayList<SaleInvoiceBVO>();
		
		for (int i = 0; i < cgagbovList.size(); i++) {
			SaleInvoiceBVO saleInvoiceBVO = new SaleInvoiceBVO();
			Cgag000003HVO cgag000003hvo1 = cgagbovList.get(i);
			saleInvoiceBVO.setVbdef9(cgag000003hvo1.getBpoid());
			saleInvoiceBVO.setVbdef10(cgag000003hvo1.getBpolineid());
			saleInvoiceBVO.setVbdef11(cgag000003hvo1.getBalanceid());
			saleInvoiceBVO.setVbdef12(cgag000003hvo1.getBalancelineid());
			saleInvoiceBVO.setVbdef13(cgag000003hvo1.getOrderid());
			saleInvoiceBVO.setVbdef14(cgag000003hvo1.getOrderlineid());
			saleInvoiceBVO.setVbdef8(cgag000003hvo1.getDrymeasure());
			saleInvoiceBVO.setVbdef7(cgag000003hvo1.getWetmeasure()+"");
			//设置行号
			Integer it = (i + 1) * 10;
			String crowno = Integer.toString(it);
			saleInvoiceBVO.setCrowno(crowno);

			//无税金额
			saleInvoiceBVO.setNorigmny(cgag000003hvo1.getBalance_price());
			//无税单价
			saleInvoiceBVO.setNqtorigprice(cgag000003hvo1.getBalance_unit_price());
			//含税单价
			UFDouble ufDouble = new UFDouble();
			double rate = ufDouble.add(cgag000003hvo1.getTaxrate()).doubleValue();
			double balanceunitprice = ufDouble.add(cgag000003hvo1.getBalance_unit_price()).doubleValue();
			saleInvoiceBVO.setNqtorigtaxprice(new UFDouble(balanceunitprice*(1+rate)));
			//主数量，
			saleInvoiceBVO.setNnum(new UFDouble(cgag000003hvo1.getDrymeasure()));
			//税率
			saleInvoiceBVO.setNtaxrate(cgag000003hvo1.getTaxrate());
			//税额
			saleInvoiceBVO.setNtax(cgag000003hvo1.getTaxprice());
			//税码-NC中是参照增值税税码税率档案bd_taxcode，默认编码CN01
			String ctaxcodeid = this.getTaxCodeByCn01("CN01");
			saleInvoiceBVO.setCtaxcodeid(ctaxcodeid);
			//扣税类别-NC中为枚举项，默认枚举值为1
			saleInvoiceBVO.setFtaxtypeflag(1);
			//价税合计
			saleInvoiceBVO.setNorigtaxmny(cgag000003hvo1.getTaxamount());
			saleInvoiceBVO.setStatus(VOStatus.NEW);
			//采购合同号
			String bpoId = cgag000003hvo1.getBpoid();
			//采购合同行号
			String bpoLineId = cgag000003hvo1.getBpolineid();
			//客户税号、电话、银行账号
			String customer = "";
			String tel = "";
			String bankid = "";		
			List<Map<String, Object>> saleInfoList = this.getSaleInfo(bpoId, bpoLineId);
			if (saleInfoList.size()>0) {
				saleInvoiceBVO.setCmaterialvid(saleInfoList.get(0).get("cmaterialvid")+"");
				saleInvoiceBVO.setCunitid(saleInfoList.get(0).get("cunit")+"");
				
			}
			//客户名称
			customer = checkIsNull(saleInfoList.get(0).get("ccustomerid"));
			saleInvoiceHVO.setCinvoicecustid(customer);
			saleInvoiceHVO.setStatus(VOStatus.NEW);
			//电话
			//银行账号
			bankid = checkIsNull(saleInfoList.get(0).get(""));
			//源头单据号、源头单据行号
			List<Map<String, Object>> vfirstInfoList = this.getVfirstInfo(bpoId, bpoLineId);
			if (vfirstInfoList.size()>0) {
				saleInvoiceBVO.setVfirstcode(vfirstInfoList.get(0).get("vfirstcode")+"");
				saleInvoiceBVO.setVfirstrowno(vfirstInfoList.get(0).get("vfirstrowno")+"");
			}
			
			//来源单据号、来源单据行号
			List<Map<String, Object>> vSrcInfoList = this.getVSrcInfo(bpoId, bpoLineId);
			if (vSrcInfoList.size()>0) {
				saleInvoiceBVO.setVfirstcode(vSrcInfoList.get(0).get("vsrccode")+"");
				saleInvoiceBVO.setVfirstrowno(vSrcInfoList.get(0).get("vsrcrowno")+"");
			}
			saleinvoiceList.add(saleInvoiceBVO);
		}
		
		
		SaleInvoiceVO saleInvoiceVO = new SaleInvoiceVO();
		saleInvoiceVO.setParent(saleInvoiceHVO);
		SaleInvoiceBVO []saleInvoiceBVOs = saleinvoiceList.toArray(new SaleInvoiceBVO[0]);
		saleInvoiceVO.setChildrenVO((CircularlyAccessibleValueObject[])saleInvoiceBVOs);
		
//		BaseDAO baseDAO = new BaseDAO();
		NCObject objs = NCObject.newInstance(saleInvoiceVO);

		try {
			MDPersistenceService.lookupPersistenceService().saveBill(objs);

//			baseDAO.insertVO(saleInvoiceBVO);
			//回写中间表
			this.writeBack(aggCgag000003HVO);
		} catch (Exception e) {
			// TODO 自动生成的 catch 块
			e.printStackTrace();
		}
		
	}


		@SuppressWarnings("unchecked")
		@Override
		public List<Map<String, Object>> getSaleInfo(String bpoId,String bpoLineId) {
			// TODO 自动生成的方法存根
			BaseDAO baseDao = new BaseDAO();
			String querySql = "SELECT SAB.cmaterialvid,SAB.cunitid,SA.ccustomerid,bc.TAXPAYERID,bc.TEL1,BC.NAME,sab.CASTUNITID,SA.cbiztypeid,SAB.ccurrencyid,sab.crececountryid,sab.csendcountryid,sab.btriatradeflag,sab.ctaxcountryid,SAB.cmaterialid,sab.ncaltaxmny,sab.norignetprice,sab.nnetprice,SAB.ntaxmny,sab.nmny from so_saleorder_b sab,SO_SALEORDER sa,BD_CUSTOMER bc WHERE SAB.CSALEORDERID=SA.CSALEORDERID and SA.CCUSTOMERID=BC.PK_CUSTOMER and sab.dr=0 and vbdef2='"+bpoId+"' and vbdef3='"+bpoLineId+"'";
//			String querySql = "SELECT cmaterialvid,cunitid,csaleorderbid from so_saleorder_b where vbdef2='"+bpoId+"' and vbdef3='"+bpoLineId+"'";
			List<Map<String, Object>> list = null;
			try {
				list = (List<Map<String, Object>>)baseDao.executeQuery(querySql, new ResultSetProcessor() {
					
					@Override
					public List<Map<String, Object>> handleResultSet(ResultSet rs) throws SQLException {
						// TODO 自动生成的方法存根
						List<Map<String, Object>> list1 = new ArrayList<Map<String, Object>>();
						Map<String, Object> m = new HashMap<String, Object>();
						while (rs.next()) {
							//物料编码
							m.put("cmaterialvid", rs.getString(1));
							//计量单位
							m.put("cunitid", rs.getString(2));
							//客户编号
							m.put("ccustomerid", rs.getString(3));
							//客户税号
							m.put("taxpayerid", rs.getString(4));
							//客户电话
							m.put("tel1", rs.getString(5));
							//客户名称
							m.put("name", rs.getString(6));
							//单位
							m.put("castunitid", rs.getString(7));
							//业务流程
							m.put("cbiztypeid", rs.getString(8));
							//本位币
							m.put("ccurrencyid", rs.getString(9));
							//收货国家/地区
							m.put("crececountryid", rs.getString(10));
							//发货国家/地区
							m.put("csendcountryid", rs.getString(11));
							//三角贸易
							m.put("btriatradeflag", rs.getString(12));
							//报税国家
							m.put("ctaxcountryid", rs.getString(13));
							//物料
							m.put("cmaterialid", rs.getString(14));
							//计税金额
							m.put("ncaltaxmny", rs.getString(15));
							//主无税净价
							m.put("norignetprice", rs.getString(16));
							//主本币无税净价
							m.put("nnetprice", rs.getString(17));
							//本币价税合计
							m.put("ntaxmny", rs.getString(18));
							//本币无税金额
							m.put("nmny", rs.getString(19));
							list1.add(m);
							
							
						}
						return list1;
					}
				});
			} catch (DAOException e1) {
				// TODO 自动生成的 catch 块
				e1.printStackTrace();
			}
			return list;
		}

		@Override
		public boolean sendMsgAGCG000003(SaleInvoiceVO saleInvoiceVO) {
			// TODO 自动生成的方法存根
			//主表电文
			List<MsgAGCG000002> agcg000002s = new ArrayList<MsgAGCG000002>();
			//子表电文
			List<MsgAGCG000003> agcg000003s = new ArrayList<MsgAGCG000003>();
			//
			SaleInvoiceHVO saleInvoiceHVO = saleInvoiceVO.getParentVO();
			SaleInvoiceBVO []saleInvoiceBVOs = saleInvoiceVO.getChildrenVO();
			
			// 接口数据发送是否成功标识，true为都成功，false为至少一次失败
			boolean flag = true;
			
			//销售发票主表
			MsgAGCG000002 msgAGCG000002 = new MsgAGCG000002();
			msgAGCG000002.setInvoiceId(saleInvoiceHVO.getVbillcode());
			//发票代码
			msgAGCG000002.setInvoiceCode(saleInvoiceHVO.getVbillcode());
			if (saleInvoiceHVO.getVgoldtaxcode()==null) {
				msgAGCG000002.setInvoiceNum(saleInvoiceHVO.getVdef2());
			}else {
				msgAGCG000002.setInvoiceNum(saleInvoiceHVO.getVgoldtaxcode());
			}
			msgAGCG000002.setInvoiceDate(saleInvoiceHVO.getDbilldate()+"");
			//税率-取子表第一行
			UFDouble ufDouble = new UFDouble();
			double ntaxrate = ufDouble.add(checkDoubleIsNull(saleInvoiceBVOs[0].getNtaxrate())).doubleValue();
			msgAGCG000002.setTaxRate(BigDecimal.valueOf(ntaxrate));
			
	//		无税金额-取表体合计
			double norigmnySum = 0;
			for (int i = 0; i < saleInvoiceBVOs.length; i++) {
				SaleInvoiceBVO saleInvoiceBVO = saleInvoiceBVOs[i];
				double norigmny = ufDouble.add(saleInvoiceBVO.getNorigmny() == null?UFDouble.ZERO_DBL:saleInvoiceBVO.getNorigmny()).doubleValue();
				norigmnySum+=norigmny;
			}
			msgAGCG000002.setTotNetAmt(BigDecimal.valueOf(norigmnySum));
	//		价税合计
			double ntotalorigmny = ufDouble.add(checkDoubleIsNull(saleInvoiceHVO.getNtotalorigmny())).doubleValue();
			msgAGCG000002.setTotAmt(BigDecimal.valueOf(ntotalorigmny));
			//税额
			double ntaxSum = 0;
			for (int i = 0; i < saleInvoiceBVOs.length; i++) {
				SaleInvoiceBVO saleInvoiceBVO = saleInvoiceBVOs[i];
				double ntax = ufDouble.add(saleInvoiceBVO.getNtax()).doubleValue();
				ntaxSum+=ntax;
			}
			DecimalFormat df = new DecimalFormat("#.##");
			String ntaxString = df.format(ntaxSum);
			UFDouble ntaxDouble = new UFDouble(ntaxString);
			ntaxSum = ufDouble.add(ntaxDouble).doubleValue();
			msgAGCG000002.setTotTaxAmt(BigDecimal.valueOf(ntaxSum));
			//结算单单号
			msgAGCG000002.setBalanceId(saleInvoiceBVOs[0].getVbdef11());
			
			//采购合同号
			msgAGCG000002.setBpoId(saleInvoiceBVOs[0].getVbdef9());
			//执行单号
			msgAGCG000002.setOrderId(saleInvoiceBVOs[0].getVbdef13());
			agcg000002s.add(msgAGCG000002);
			MsgAGCG000002Head msgAGCG000002Head = new MsgAGCG000002Head();
			msgAGCG000002Head.setMsgBody(agcg000002s);
			RestTemplate rt = new RestTemplate();
			String headFlag = rt.postForObject(this.getRestURL("Agcg000002"), msgAGCG000002Head, String.class);
			
//			String headFlag = rt.postForObject("http://192.168.2.233:8084/mq/sendMsgAGCG000002", msgAGCG000002Head, String.class);
//			String headFlag = rt.postForObject("http://192.1.103.156:8084/conn-mq/mq/sendMsgAGCG000002", msgAGCG000002Head, String.class);
			//表体
			for (int i = 0; i < saleInvoiceBVOs.length; i++) {
				//销售发票子表
				MsgAGCG000003 msgAGCG000003 = new MsgAGCG000003();
				SaleInvoiceBVO saleInvoiceBVO = saleInvoiceBVOs[i];
				//发票号(取主表)
				msgAGCG000003.setInvoiceId(saleInvoiceHVO.getVbillcode());
				//发票票面号码(取主表)
				msgAGCG000003.setInvoiceNum(nullToString(saleInvoiceHVO.getVgoldtaxcode()));
				//发票明细号（明细信息主键）
				msgAGCG000003.setInvoiceLineId(saleInvoiceBVO.getCsaleinvoicebid());
				//发票代码
				msgAGCG000003.setInvoiceCode(saleInvoiceHVO.getVbillcode());
				//采购合同行号
				msgAGCG000003.setBpoLineId(saleInvoiceBVO.getVbdef10());
				//执行单行号
				msgAGCG000003.setOrderLineId(saleInvoiceBVO.getVbdef14());
				//结算单行号
				msgAGCG000003.setBalanceLineId(saleInvoiceBVO.getVbdef12());
				//物料编码
				String cmaterialvid = saleInvoiceBVO.getCmaterialvid();
				msgAGCG000003.setItemId(cmaterialvid);
				//物料名称
				String cmaterialvname = "";
				
				if (this.getCmaterialvInfoById(cmaterialvid)!=null&&this.getCmaterialvInfoById(cmaterialvid).size()>0) {
					cmaterialvname = this.getCmaterialvInfoById(cmaterialvid).get(0).get("name");
				}
				msgAGCG000003.setItemName(cmaterialvname);
				//计量单位
				String uom = "";
				if (this.getCmaterialvInfoById(cmaterialvid)!=null&&this.getCmaterialvInfoById(cmaterialvid).size()>0) {
					uom = this.getCmaterialvInfoById(cmaterialvid).get(0).get("uom");
				}
				msgAGCG000003.setUom(uom);
				//主数量
				double nnum = ufDouble.add(checkDoubleIsNull(saleInvoiceBVO.getNnum())).doubleValue();
				msgAGCG000003.setInvoicedQty(BigDecimal.valueOf(nnum));
				//未税单价
				double netPrice = ufDouble.add(checkDoubleIsNull(saleInvoiceBVO.getNqtorigprice())).doubleValue();
				msgAGCG000003.setNetPrice(BigDecimal.valueOf(netPrice));
				//含税单价
				double taxPrice = ufDouble.add(checkDoubleIsNull(saleInvoiceBVO.getNqtorigtaxprice())).doubleValue();
				msgAGCG000003.setTaxPrice(BigDecimal.valueOf(taxPrice));
				//税率
				double taxRate = ufDouble.add(checkDoubleIsNull(saleInvoiceBVO.getNtaxrate())).doubleValue();
				msgAGCG000003.setTaxRate(BigDecimal.valueOf(taxRate));
				//未税总金额（发票）
				double totNetAmt = ufDouble.add(checkDoubleIsNull(saleInvoiceBVO.getNorigmny())).doubleValue();
				msgAGCG000003.setTotNetAmt(BigDecimal.valueOf(totNetAmt));
				//含税总金额（发票）
				double totAmt = ufDouble.add(checkDoubleIsNull(saleInvoiceBVO.getNorigtaxmny())).doubleValue();
				msgAGCG000003.setTotAmt(BigDecimal.valueOf(totAmt));
				//总税额
				double totTaxAmt = ufDouble.add(checkDoubleIsNull(saleInvoiceBVO.getNtax())).doubleValue();
				msgAGCG000003.setTotTaxAmt(BigDecimal.valueOf(totTaxAmt));
				//结算单号
				msgAGCG000003.setBalanceId(saleInvoiceBVO.getVbdef11());
				//合同号
				msgAGCG000003.setBpoId(saleInvoiceBVO.getVbdef9());
				//执行单号
				msgAGCG000003.setOrderId(saleInvoiceBVO.getVbdef13());
				//开票票面日期
				msgAGCG000003.setInvoiceDate(checkIsNull(saleInvoiceHVO.getDbilldate()));
				agcg000003s.add(msgAGCG000003);
			}
			MsgAGCG000003Head msgAGCG000003Head = new MsgAGCG000003Head();
			msgAGCG000003Head.setMsgBody(agcg000003s);
			
			String bodyFlag = rt.postForObject(this.getRestURL("Agcg000003"), msgAGCG000003Head, String.class);
//			String bodyFlag = rt.postForObject("http://192.168.2.233:8084/mq/sendMsgAGCG000003", msgAGCG000003Head, String.class);
			
			if ("0".equals(headFlag)&&"0".equals(bodyFlag)) {// cs 0成功，1失败
				// 成功后
				agcg000002s.clear();
				agcg000003s.clear();
				//修改发送标识
				this.updateIsSend(saleInvoiceHVO);
			} else {
				flag = false;
			}
			 
			return flag;
		}
		
		public void updateIsSend(SaleInvoiceHVO saleInvoiceHVO){
			String csaleinvoiceid = saleInvoiceHVO.getCsaleinvoiceid();
			String sql = "update so_saleinvoice set vdef18='1' where csaleinvoiceid='"+csaleinvoiceid+"'";
			BaseDAO baseDAO = new BaseDAO();
			try {
				baseDAO.executeUpdate(sql);
			} catch (DAOException e) {
				// TODO 自动生成的 catch 块
				e.printStackTrace();
			}
		}

		@Override
		public void writeBack(AggCgag000003HVO aggCgag000003HVO) {
			// TODO 自动生成的方法存根
			Cgag000003HVO cgag000003hvo = aggCgag000003HVO.getParentVO();
			String pkid = cgag000003hvo.getPk_pgjdjjjsxx();
			String msginfo="";
			SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
			String currtime = df.format(new Date());
			BaseDAO baseDao = new BaseDAO();
			String updateSql = "update msg_cgag000003 set msgflag='1',msginfo='"+msginfo+"',MODIFIEDTIME='"+currtime +"' where PK_PGJDJJJSXX='"+pkid+"'";
			
//			cgag000003hvo.setMsgflag("1");
//			cgag000003hvo.setModifiedtime(new UFDateTime());
//			cgag000003hvo.setMsginfo("");
//			cgag000003hvo.setStatus(VOStatus.UPDATED);
			aggCgag000003HVO.setParent(cgag000003hvo);
			try {
				baseDao.executeUpdate(updateSql);
			} catch (DAOException e) {
				// TODO 自动生成的 catch 块
				e.printStackTrace();
			}
			
		}
		
		public void writeBack(Cgag000003HVO cgag000003hvo) {
			// TODO 自动生成的方法存根
			String pkid = cgag000003hvo.getPk_pgjdjjjsxx();
			String msginfo=cgag000003hvo.getMsginfo();
			SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
			String currtime = df.format(new Date());
			BaseDAO baseDao = new BaseDAO();
			String updateSql = "update msg_cgag000003 set msgflag='1',msginfo='"+msginfo+"',MODIFIEDTIME='"+currtime +"' where PK_PGJDJJJSXX='"+pkid+"'";
			
			try {
				baseDao.executeUpdate(updateSql);
			} catch (DAOException e) {
				// TODO 自动生成的 catch 块
				e.printStackTrace();
			}
			
		}

		@Override
		public void sendZjbInsert(SaleInvoiceVO saleInvoiceVO) {
			// TODO 自动生成的方法存根
			BaseDAO baseDAO = new BaseDAO();
			//转换后主表数据
			Agcg000002HVO agcg000002hvo = this.agcgZjbHVO(saleInvoiceVO);
			
			List<Agcg000003HVO> vos = this.agcgZjbBVO(saleInvoiceVO);
			try {
				//向中间表主表插入数据
				String idString = baseDAO.insertVO(agcg000002hvo);
				//向中间表子表插入数据
//				NCObject objs = NCObject.newInstance(vos.toArray(new Agcg000003HVO[0]));
//				MDPersistenceService.lookupPersistenceService().saveBill(objs);

				

				String []idsString = baseDAO.insertVOList(vos);
			}catch (DAOException e) {
				ExceptionUtils.wrappBusinessException("中间表插入失败!");
			}
			
		}
		/**
		 * 构建入库确认中间表表头数据vo
		 * 中间表
		 */
		public Agcg000002HVO agcgZjbHVO(SaleInvoiceVO saleInvoiceVO){
			SaleInvoiceHVO saleInvoiceHVO = saleInvoiceVO.getParentVO();
			SaleInvoiceBVO[] saleInvoiceBVOs = saleInvoiceVO.getChildrenVO();
			//销售发票主表
			Agcg000002HVO agcg000002hvo = new Agcg000002HVO();
			
			agcg000002hvo.setInvoiceid(saleInvoiceHVO.getVbillcode());
			agcg000002hvo.setInvoicenum(saleInvoiceHVO.getVgoldtaxcode());
			agcg000002hvo.setInvoicedate(saleInvoiceHVO.getDbilldate()+"");
			//税率-取子表第一行
			agcg000002hvo.setTaxrate(saleInvoiceBVOs[0].getNtaxrate());
			
	//		无税金额-取表体合计
			UFDouble ufDouble = new UFDouble();
			double norigmnySum = 0;
			for (int i = 0; i < saleInvoiceBVOs.length; i++) {
				SaleInvoiceBVO saleInvoiceBVO = saleInvoiceBVOs[i];
				double norigmny = ufDouble.add(saleInvoiceBVO.getNorigmny()).doubleValue();
				norigmnySum+=norigmny;
			}
			agcg000002hvo.setTotnetamt(new UFDouble(norigmnySum));
	//		价税合计
			agcg000002hvo.setTotamt(saleInvoiceHVO.getNtotalorigmny());
			//税额
			double ntaxSum = 0;
			for (int i = 0; i < saleInvoiceBVOs.length; i++) {
				SaleInvoiceBVO saleInvoiceBVO = saleInvoiceBVOs[i];
				double ntax = ufDouble.add(saleInvoiceBVO.getNtax()).doubleValue();
				ntaxSum+=ntax;
			}
			agcg000002hvo.setTottaxamt(new UFDouble(ntaxSum));
			//结算单单号
			agcg000002hvo.setBalanceid(saleInvoiceBVOs[0].getVbdef11());
			//发票代码
			agcg000002hvo.setInvoicecode(saleInvoiceHVO.getVbillcode());
			//采购合同号
			agcg000002hvo.setBpoid(saleInvoiceBVOs[0].getVbdef9());
			//成功标识
			agcg000002hvo.setAttributeValue("msgflag", "1");	
			//执行单号
			agcg000002hvo.setOrderid(saleInvoiceBVOs[0].getVbdef13());
			//集团
			agcg000002hvo.setPk_group(saleInvoiceHVO.getPk_group());
			//组织
			agcg000002hvo.setPk_org(saleInvoiceHVO.getPk_org());
			//组织版本
			agcg000002hvo.setPk_org_v(saleInvoiceHVO.getPk_org_v());
			//单据类型
			
			//制单人
			agcg000002hvo.setBillmaker(AppContext.getInstance().getPkUser());
			//单据日期
			agcg000002hvo.setDbilldate(new UFDate(new Date().getTime()));
			//创建人
			agcg000002hvo.setCreator(AppContext.getInstance().getPkUser());
			//创建时间
			agcg000002hvo.setCreationtime(new UFDate(new Date().getTime()));
			//审批人
			//审批时间
			//来源单据类型
			agcg000002hvo.setStatus(VOStatus.NEW);
			agcg000002hvo.setBillstatus(-1);
			agcg000002hvo.setMsgresource("销售发票");
			return agcg000002hvo;
		}
		
		/**
		 * 构建入库确认中间表表头数据vo
		 * 中间表
		 */
		public List<Agcg000003HVO> agcgZjbBVO(SaleInvoiceVO saleInvoiceVO){
			List<Agcg000003HVO> vos = new ArrayList<Agcg000003HVO>();
			SaleInvoiceHVO saleInvoiceHVO = saleInvoiceVO.getParentVO();
			SaleInvoiceBVO []saleInvoiceBVOs = saleInvoiceVO.getChildrenVO();
			
			for (int j = 0; j < saleInvoiceBVOs.length; j++) {
				Agcg000003HVO agcg000003hvo = new Agcg000003HVO();
				
				SaleInvoiceBVO saleInvoiceBVO = saleInvoiceBVOs[j];
				//发票号(取主表)
				agcg000003hvo.setInvoiceid(saleInvoiceHVO.getVbillcode());
				//发票票面号码(取主表)
				agcg000003hvo.setInvoicenum(saleInvoiceHVO.getVgoldtaxcode());
				//发票代码
				agcg000003hvo.setInvoicecode(saleInvoiceHVO.getVbillcode());
				//发票明细号（明细信息主键）
				agcg000003hvo.setInvoicelineid(saleInvoiceBVO.getCsaleinvoicebid());
				//采购合同行号
				agcg000003hvo.setBpolineid(saleInvoiceBVO.getVbdef10());
				//执行单行号
				agcg000003hvo.setOrderlineid(saleInvoiceBVO.getVbdef14());
				//结算单行号
				agcg000003hvo.setBalancelineid(saleInvoiceBVO.getVbdef12());
				//物料编码
				String cmaterialvid = saleInvoiceBVO.getCmaterialvid();
				agcg000003hvo.setItemid(cmaterialvid);
				//物料名称
				String cmaterialvname = "";
				
				if (this.getCmaterialvInfoById(cmaterialvid)!=null&&this.getCmaterialvInfoById(cmaterialvid).size()>0) {
					cmaterialvname = this.getCmaterialvInfoById(cmaterialvid).get(0).get("name");
				}
				agcg000003hvo.setItemname(cmaterialvname);
				//计量单位
				String uom = "";
				if (this.getCmaterialvInfoById(cmaterialvid)!=null&&this.getCmaterialvInfoById(cmaterialvid).size()>0) {
					uom = this.getCmaterialvInfoById(cmaterialvid).get(0).get("uom");
				}
				agcg000003hvo.setUom(uom);
				//物料名称
				//主数量
				agcg000003hvo.setInvoicedqty(saleInvoiceBVO.getNtaxrate());
				//未税单价
				agcg000003hvo.setNetprice(saleInvoiceBVO.getNorigprice());
				//含税单价
				agcg000003hvo.setTaxprice(saleInvoiceBVO.getNorigtaxprice());
				//税率
				agcg000003hvo.setTaxrate(saleInvoiceBVO.getNtaxrate());
				//未税总金额（发票）
				agcg000003hvo.setTotnetamt(saleInvoiceBVO.getNorigmny());
				//含税总金额（发票）
				agcg000003hvo.setTotamt(saleInvoiceBVO.getNorigtaxmny());
				//总税额
				agcg000003hvo.setTottaxamt(saleInvoiceBVO.getNtax());
				//结算单号
				agcg000003hvo.setBalanceid(saleInvoiceBVO.getVbdef11());
				//合同号
				agcg000003hvo.setBpoid(saleInvoiceBVO.getVbdef9());
				//执行单号
				agcg000003hvo.setOrderid(saleInvoiceBVO.getVbdef13());
				//开票票面日期
				agcg000003hvo.setInvoicedate(saleInvoiceHVO.getDbilldate()+"");
				//集团
				agcg000003hvo.setPk_group(saleInvoiceHVO.getPk_group());
				//组织
				agcg000003hvo.setPk_org(saleInvoiceHVO.getPk_org());
				//组织版本
				agcg000003hvo.setPk_org_v(saleInvoiceHVO.getPk_org_v());
				//单据类型
				
				//制单人
				agcg000003hvo.setBillmaker(AppContext.getInstance().getPkUser());
				//单据日期
				agcg000003hvo.setDbilldate(new UFDate(new Date().getTime()));
				//创建人
				agcg000003hvo.setCreator(AppContext.getInstance().getPkUser());
				//发送状态  1-发送成功
				agcg000003hvo.setMsgflag("1");
				//创建时间
				agcg000003hvo.setCreationtime(new UFDateTime());
				//审批人
				//审批时间
				//来源单据类型
				agcg000003hvo.setStatus(VOStatus.NEW);
				agcg000003hvo.setBillstatus(-1);
				agcg000003hvo.setMsgresource("销售发票明细");
				vos.add(agcg000003hvo);
			}
			
			return vos;
		}
		
		public String getRestURL(String telId){
			BaseDAO baseDao = new BaseDAO();
			String rsSql = "SELECT URL_PATH FROM RT_BASEURL WHERE PK_ID='"+ telId + "'";
			String retString = null;
			try {
				retString =(String) baseDao.executeQuery(rsSql, new ResultSetProcessor() {
					@Override
					public String handleResultSet(ResultSet rs) throws SQLException {
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
		//获取税码
		public String getTaxCodeByCn01(String code){
			BaseDAO baseDao = new BaseDAO();
			String rsSql = "select PK_TAXCODE from BD_TAXCODE where code='"+ code + "'";
			String retString = null;
			try {
				retString =(String) baseDao.executeQuery(rsSql, new ResultSetProcessor() {
					@Override
					public String handleResultSet(ResultSet rs) throws SQLException {
						String taxcode = null;
						while (rs.next()) {
							taxcode = rs.getString(1);
						}
						return taxcode;
					}
				});
			} catch (DAOException e) {
				e.printStackTrace();
			}
			return retString.trim();
		}
		
		//根据物料编码找物料信息
		@SuppressWarnings("unchecked")
		public List<Map<String, String>> getCmaterialvInfoById(String code){
			BaseDAO baseDao = new BaseDAO();
			String rsSql = "select bmv.name as name,BM.name as unit from bd_material_v bmv,bd_measdoc bm where BMV.pk_measdoc=BM.pk_measdoc and bmv.PK_SOURCE='"+ code + "'";
			List<Map<String, String>> list = null;
			try {
				list = (List<Map<String, String>>)baseDao.executeQuery(rsSql, new ResultSetProcessor() {
					
					@Override
					public List<Map<String, String>> handleResultSet(ResultSet rs) throws SQLException {
						// TODO 自动生成的方法存根
						List<Map<String, String>> list1 = new ArrayList<Map<String, String>>();
						Map<String, String> m = new HashMap<String, String>();
						while (rs.next()) {
							m.put("name", rs.getString(1));
							m.put("uom", rs.getString(2));
							list1.add(m);
						}
						return list1;
					}
				});
			} catch (DAOException e1) {
				// TODO 自动生成的 catch 块
				e1.printStackTrace();
			}
			return list;
		}
		
		@SuppressWarnings("unchecked")
		public List<Map<String, String>> getbalanceIdList(String balanceId){
			BaseDAO baseDao = new BaseDAO();
			String querySql = "select taxamount from msg_cgag000003 where BALANCEID= '"+balanceId+"'";
			List<Map<String, String>> list = null;
			try {
				list = (List<Map<String, String>>)baseDao.executeQuery(querySql, new ResultSetProcessor() {
					
					@Override
					public List<Map<String, String>> handleResultSet(ResultSet rs) throws SQLException {
						// TODO 自动生成的方法存根
						List<Map<String, String>> list1 = new ArrayList<Map<String, String>>();
						Map<String, String> m = new HashMap<String, String>();
						while (rs.next()) {
							m.put("jsje", rs.getString(1));
							list1.add(m);
						}
						return list1;
					}
				});
			} catch (DAOException e1) {
				// TODO 自动生成的 catch 块
				e1.printStackTrace();
			}
			return list;
		}
		public String getKeyValue() {
//			String previousTime = "";	
//			Integer ORDER = 0;
			String defString = "SI";
			String currentTime = new SimpleDateFormat("yyyyMMddHHmmss")
					.format(new Date());
			String keyValue = defString+currentTime;
			String ranString = String.valueOf(Math.ceil(Math.random()*500000+500000));
			keyValue+=ranString;
			if (keyValue.length()>18) {
				keyValue = keyValue.substring(0, 18);
			}

//			if (currentTime.equals(previousTime)) {
//				ORDER++;
//				StringBuffer orderStr = new StringBuffer("");
//				for (int i = 0; i < 6 - ORDER.toString().length(); i++) {
//					orderStr.append("0");
//				}
//				orderStr.append(ORDER.toString());
//				keyValue += orderStr;
//			} else {
//				previousTime = currentTime;
//				ORDER = 1;
//				StringBuffer orderStr = new StringBuffer("");
//				for (int i = 0; i < 6 - ORDER.toString().length(); i++) {
//					orderStr.append("0");
//				}
//				orderStr.append(ORDER.toString());
//				keyValue += orderStr;
//			}
			return keyValue;
		}
		
		//源头单据号、源头单据行号
		
		@SuppressWarnings("unchecked")
		public List<Map<String, Object>> getVfirstInfo(String bpoId,String bpoLineId) {
			// TODO 自动生成的方法存根
			BaseDAO baseDao = new BaseDAO();
			String querySql = "SELECT csaleorderbid,crowno from so_saleorder_b where vbdef2='"+bpoId+"' and vbdef3='"+bpoLineId+"'";
			List<Map<String, Object>> list = null;
			try {
				list = (List<Map<String, Object>>)baseDao.executeQuery(querySql, new ResultSetProcessor() {
					
					@Override
					public List<Map<String, Object>> handleResultSet(ResultSet rs) throws SQLException {
						// TODO 自动生成的方法存根
						List<Map<String, Object>> list1 = new ArrayList<Map<String, Object>>();
						Map<String, Object> m = new HashMap<String, Object>();
						while (rs.next()) {
							//销售订单号
							m.put("vfirstcode", rs.getString(1));
							//销售订单行号
							m.put("vfirstrowno", rs.getString(2));
							list1.add(m);
						}
						return list1;
					}
				});
			} catch (DAOException e1) {
				// TODO 自动生成的 catch 块
				e1.printStackTrace();
			}
			return list;
		}
		
		
		
		//来源单据号、来源单据行号
		@SuppressWarnings("unchecked")
		public List<Map<String, Object>> getVSrcInfo(String bpoId,String bpoLineId) {
			// TODO 自动生成的方法存根
			BaseDAO baseDao = new BaseDAO();
			String querySql = "SELECT cgeneralbid,crowno from ic_saleout_b where vbdef2='"+bpoId+"' and vbdef3='"+bpoLineId+"'";
			List<Map<String, Object>> list = null;
			try {
				list = (List<Map<String, Object>>)baseDao.executeQuery(querySql, new ResultSetProcessor() {
					
					@Override
					public List<Map<String, Object>> handleResultSet(ResultSet rs) throws SQLException {
						// TODO 自动生成的方法存根
						List<Map<String, Object>> list1 = new ArrayList<Map<String, Object>>();
						Map<String, Object> m = new HashMap<String, Object>();
						while (rs.next()) {
							//物料编码
							m.put("vsrccode", rs.getString(1));
							//计量单位
							m.put("vsrcrowno", rs.getString(2));
							
							list1.add(m);
						}
						return list1;
					}
				});
			} catch (DAOException e1) {
				// TODO 自动生成的 catch 块
				e1.printStackTrace();
			}
			return list;
		}
		
		public String checkIsNull(Object byCheckStr){
			if (byCheckStr==null) {
				return null;
			}else {
				return byCheckStr+"";
			}
		}
		public UFDouble checkDoubleIsNull(UFDouble checkDouble){
			if (checkDouble == null) {
				return UFDouble.ZERO_DBL;
			}else {
				return checkDouble;
			}
		}
		
		public String nullToString(Object object) {
			if (object == null) {
				return "";
			}else {
				return object+"";
			}
		}
		
		@SuppressWarnings("unchecked")
		public List<Map<String, Object>> isHaveVbillcode(String vbillcode) {
			// TODO 自动生成的方法存根
			BaseDAO baseDao = new BaseDAO();
			String querySql = "SELECT csaleinvoiceid FROM so_saleinvoice where vbillcode='"+vbillcode+"'";
			List<Map<String, Object>> list = null;
			try {
				list = (List<Map<String, Object>>)baseDao.executeQuery(querySql, new ResultSetProcessor() {
					
					@Override
					public List<Map<String, Object>> handleResultSet(ResultSet rs) throws SQLException {
						// TODO 自动生成的方法存根
						List<Map<String, Object>> list1 = new ArrayList<Map<String, Object>>();
						Map<String, Object> m = new HashMap<String, Object>();
						while (rs.next()) {
							m.put("csaleinvoiceid", rs.getString(1));
							list1.add(m);
						}
						return list1;
					}
				});
			} catch (DAOException e1) {
				// TODO 自动生成的 catch 块
				e1.printStackTrace();
			}
			return list;
		}
	
}
