package nc.impl.so.m30.msg;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map.Entry;

import org.mozilla.javascript.edu.emory.mathcs.backport.java.util.Arrays;

import nc.bs.framework.common.InvocationInfoProxy;
import nc.bs.pub.pa.PreAlertObject;
import nc.bs.pub.pa.PreAlertReturnType;
import nc.bs.pub.pf.PfUtilTools;
import nc.bs.pub.taskcenter.BgWorkingContext;
import nc.bs.pub.taskcenter.IBackgroundWorkPlugin;
import nc.bs.pubapp.pub.rule.CreateBillCodeRule;
import nc.bs.trade.business.HYPubBO;
import nc.impl.pubapp.pattern.data.bill.BillInsert;
import nc.impl.pubapp.pattern.data.view.ViewQuery;
import nc.impl.pubapp.pattern.data.vo.VOQuery;
import nc.impl.pubapp.pattern.data.vo.VOUpdate;
import nc.impl.pubapp.pattern.database.DataAccessUtils;
import nc.md.data.access.NCObject;
import nc.md.model.MetaDataException;
import nc.md.persist.framework.MDPersistenceService;
import nc.uif.pub.exception.UifException;
import nc.vo.bd.cust.CustomerVO;
import nc.vo.lm.pgjdcght.AggCgag000002HVO;
import nc.vo.lm.pgjdcght.Cgag000002HVO;
import nc.vo.org.OrgVO;
import nc.vo.pp.m28.entity.PriceAuditItemVO;
import nc.vo.pp.m28.entity.PriceAuditVO;
import nc.vo.pp.m28.entity.PriceAuditViewVO;
import nc.vo.pub.BusinessException;
import nc.vo.pub.IVOMeta;
import nc.vo.pub.SuperVO;
import nc.vo.pub.lang.UFDate;
import nc.vo.pub.lang.UFDouble;
import nc.vo.pubapp.AppContext;
import nc.vo.pubapp.bill.CombineBill;
import nc.vo.pubapp.pattern.data.IRowSet;
import nc.vo.pubapp.pattern.exception.ExceptionUtils;
import nc.vo.pubapp.util.VORowNoUtils;
import nc.vo.so.m30.entity.SaleOrderBVO;
import nc.vo.so.m30.entity.SaleOrderVO;

/**
 * @author wangzym
 * @version 2017年4月14日 下午2:21:31 销售订单的定时任务 定时去中间表取数并且回写内容 本次将价格审批单的mrid设计成需求号
 *          本次将价格审批单的mrlineid设计成需求行号 将pk_group设计为定值
 * 
 */
public class SaleOrderTaskPlugIn implements IBackgroundWorkPlugin {

	public List<Cgag000002HVO> matchvo = new ArrayList<Cgag000002HVO>();
	private String returnMsg = "";

	/**
	 * 
	 */
	public SaleOrderTaskPlugIn() {
		// TODO 自动生成的构造函数存根
	}

	/*
	 * （非 Javadoc）
	 * 
	 * @see
	 * nc.bs.pub.taskcenter.IBackgroundWorkPlugin#executeTask(nc.bs.pub.taskcenter
	 * .BgWorkingContext)
	 */
	@Override
	public PreAlertObject executeTask(BgWorkingContext paramBgWorkingContext)
			throws BusinessException {
		// TODO 自动生成的方法存根
		PreAlertObject preobj = new PreAlertObject();
		preobj.setReturnType(PreAlertReturnType.RETURNMESSAGE);

		// 业务处理
		List<Cgag000002HVO> lhvo = new ArrayList<Cgag000002HVO>();
		try {
			NCObject[] ncObjects = MDPersistenceService
					.lookupPersistenceQueryService().queryBillOfNCObjectByCond(
							Cgag000002HVO.class, "msgflag='0'", false);
			for (NCObject ncObject : ncObjects) {
				AggCgag000002HVO aggvo = (AggCgag000002HVO) ncObject
						.getContainmentObject();
				Cgag000002HVO hvo = aggvo.getParentVO();
				lhvo.add(hvo);
			}
		} catch (MetaDataException e) {
			// TODO 自动生成的 catch 块
			e.printStackTrace();
		}

		Cgag000002HVO[] hvos = lhvo.toArray(new Cgag000002HVO[lhvo.size()]);

		// 根据合同号和数量判断是否本次合同号完全传过来了(字段为合同号 bpoid)
		HashMap<String, Integer> reCompare = new HashMap<String, Integer>();
		HashMap<String, HashMap<String, List<String>>> use = new HashMap<String, HashMap<String, List<String>>>();
		HashMap<String, Integer> compare = new HashMap<String, Integer>();
		HashMap<String, Integer> differ = new HashMap<String, Integer>();
		for (int i = 0; i < hvos.length; i++) {
			Cgag000002HVO cgag000002hvo2 = hvos[i];
			String bpoid = cgag000002hvo2.getBpoid();
			/*
			 * String mrid1 = cgag000002hvo2.getMrid(); String mrlineid =
			 * cgag000002hvo2.getMrlineid(); List<String> mrline=new
			 * ArrayList<String>();
			 */
			int num = cgag000002hvo2.getHdef2() == null ? 0 : Integer
					.parseInt(cgag000002hvo2.getHdef2());
			if (!reCompare.containsKey(bpoid)) {
				reCompare.put(bpoid, num);

			}

		}
		// 获取传过来的数据是否
		for (Entry<String, Integer> entry : reCompare.entrySet()) {
			String key = entry.getKey();
			int sum = 0;
			for (Cgag000002HVO cgag000002hvo : hvos) {
				while (cgag000002hvo.getBpoid() == null) {
					returnMsg += cgag000002hvo.getPrimaryKey()
							+ "合同号为空,请确认数据后再执行";
					break;
				}
				if (cgag000002hvo.getBpoid().equals(key)) {
					sum++;
				}
				compare.put(key, sum);
			}
		}
		// 传过来的数据和数据库中给的值作对比；
		for (Entry<String, Integer> entry : reCompare.entrySet()) {
			String key = entry.getKey();
			int db = entry.getValue();
			for (Entry<String, Integer> entry1 : compare.entrySet()) {
				String keyNew = entry1.getKey();
				if (key.equals(keyNew)) {
					int num = entry1.getValue();
					if (db == num) {
						continue;
					} else {
						differ.put(keyNew, 1);
					}
				}
			}

		}
		// 可用的本次子表
		for (Entry<String, Integer> entry : differ.entrySet()) {
			String key = entry.getKey();
			reCompare.remove(key);

		}

		// 取出来可用的值
		List<String> hths = new ArrayList<String>();

		for (Entry<String, Integer> entry : reCompare.entrySet()) {
			String key = entry.getKey();
			hths.add(key);
		}
		// 可进行推单操作的合同号
		/**
		 * 如果你有幸维护，恭喜你，我也不知道我怎么写的。出问题别找我。
		 */
		String[] PK = hths.toArray(new String[hths.size()]);
		for (int i = 0; i < PK.length; i++) {
			String string = PK[i];// use
			HashMap<String, List<String>> temp = new HashMap<String, List<String>>();
			String mrid1 = null;
			for (int j = 0; j < hvos.length; j++) {
				List<String> temp1 = new ArrayList<String>();
				Cgag000002HVO cgag000002hvo2 = hvos[j];
				String bpoid = cgag000002hvo2.getBpoid();
				mrid1 = cgag000002hvo2.getMrid();
				if (cgag000002hvo2.getBpoid() != string) {
					continue;
				}
				if (reCompare.containsKey(bpoid)) {
					// 对应合同号下的需求号和需求行号列表
					for (int k = 0; k < hvos.length; k++) {
						Cgag000002HVO cgag000002hvo3 = hvos[k];
						if (cgag000002hvo3.getMrid() == mrid1) {
							String mrlineid = cgag000002hvo3.getMrlineid();

							temp1.add(mrlineid);
						}
					}

					temp.put(mrid1, temp1);
				}

			}
			use.put(string, temp);

		}
		/**
		 * 2017-05-31 修改到这里
		 */

		// 查出聚合vo
		PriceAuditVO[] agg = this.getNeesPushAgg(use);

		// 对价格审批单进行更新值操作与中间表数据对应
		PriceAuditVO[] aggVOS = updatePriceAudit(agg, hvos);
		// 进行推单操作

		pushToM30(aggVOS, matchvo.toArray(new Cgag000002HVO[matchvo.size()]));
		preobj.setReturnObj(returnMsg);

		return preobj;
	}

	/**
	 * @param use
	 * @return
	 * @throws BusinessException
	 */
	private PriceAuditVO[] getNeesPushAgg(
			HashMap<String, HashMap<String, List<String>>> use)
			throws BusinessException {
		// TODO 自动生成的方法存根
		// 根据合同号计划号行号，找出对应的请购单行
		int num = 0;
		List<PriceAuditVO> sum = new ArrayList<PriceAuditVO>();
		HashMap<Integer, List<PriceAuditVO>> map = new HashMap<Integer, List<PriceAuditVO>>();
		for (Entry<String, HashMap<String, List<String>>> entry : use
				.entrySet()) {
			num += 1;
			List<PriceAuditVO> sumTemp = new ArrayList<PriceAuditVO>();
			/**
			 * key为合同号，一个合同号代表着一次推单（2017-06-06根据商国强和张越超修改，一个合同号代表一个价格审批单，
			 * 也就是现在根据合同号查出来多个价审单要合成一个，一次循环为一个大的合成的价格审批单）
			 */
			HashMap<String, List<String>> mrid1 = entry.getValue();
			String mrid11 = null;
			String mrlineid = "";
			for (Entry<String, List<String>> entry1 : mrid1.entrySet()) {
				mrlineid = "";
				mrid11 = entry1.getKey();
				List<String> list = entry1.getValue();
				for (Iterator<String> iterator = list.iterator(); iterator
						.hasNext();) {
					String string = "'" + (String) iterator.next() + "',";
					mrlineid += string;
				}
				mrlineid = mrlineid.substring(0, mrlineid.length() - 1);
				// 这能不能管用
				String sql = "select purp_priceaudit_b.pk_priceaudit_b from purp_priceaudit_b where  border='Y' and purp_priceaudit_b.mrid='"
						+ mrid11
						+ "'and purp_priceaudit_b.mrlineid in ("
						+ mrlineid + ")";
				PriceAuditVO[] rets = null;
				try {
					DataAccessUtils utils = new DataAccessUtils();
					IRowSet set = utils.query(sql);
					if (set.size() == 0) {
						continue;
						// sum.add(new PriceAuditVO());
					} else {
						String[] ids = set.toOneDimensionStringArray();
						ViewQuery<PriceAuditViewVO> query = new ViewQuery<PriceAuditViewVO>(
								PriceAuditViewVO.class);
						PriceAuditViewVO[] views = query.query(ids);
						if (null != views && views.length > 0) {
							int len = views.length;
							PriceAuditVO[] bills = new PriceAuditVO[len];
							for (int i = 0; i < len; i++) {
								bills[i] = views[i].changeToBill();
							}
							CombineBill<PriceAuditVO> combine = new CombineBill<PriceAuditVO>();
							IVOMeta headMeta = bills[0].getMetaData()
									.getParent();
							String headItemKey = headMeta.getPrimaryAttribute()
									.getName();
							combine.appendKey(headItemKey);
							rets = combine.combine(bills);
							sum.add(rets[0]);
							sumTemp.add(rets[0]);
						}
					}
				} catch (Exception e) {
					ExceptionUtils.marsh(e);
				}
			}
			map.put(num, sumTemp);

		}
		// map的数量，指的就是需要形成价审单的数量
		List<PriceAuditVO> ret = new ArrayList<PriceAuditVO>();
		for (Entry<Integer, List<PriceAuditVO>> entry2 : map.entrySet()) {
			List<PriceAuditItemVO> item = new ArrayList<PriceAuditItemVO>();
			List<PriceAuditVO> li = entry2.getValue();
			PriceAuditVO[] vo = li.toArray(new PriceAuditVO[li.size()]);
			PriceAuditVO agg = new PriceAuditVO();
			for (int i = 0; i < vo.length; i++) {
				PriceAuditVO priceAuditVO = vo[i];
				agg.setParentVO(vo[0].getParentVO());
				PriceAuditItemVO[] child = priceAuditVO.getChildrenVO();
				item.addAll(Arrays.asList(child));

			}
			agg.setChildrenVO(item.toArray(new PriceAuditItemVO[item.size()]));
			ret.add(agg);
		}

		return ret.size() == 0 ? new PriceAuditVO[] { new PriceAuditVO() }
				: ret.toArray(new PriceAuditVO[ret.size()]);
		/*
		 * return sum.size() == 0 ? new PriceAuditVO[] { new PriceAuditVO() } :
		 * sum.toArray(new PriceAuditVO[sum.size()]);
		 */
	}

	/**
	 * @param hvos
	 * @param agg
	 * @return
	 * 
	 */
	private PriceAuditVO[] updatePriceAudit(PriceAuditVO[] agg,
			Cgag000002HVO[] hvos) {
		// TODO 自动生成的方法存根
		// 一个aggvo对应多个hvos，根据行号取得
		// 查出来的1个agg对应的Cgag000002HVO

		for (PriceAuditVO priceAuditVO : agg) {

			if (priceAuditVO.getParent() == null) {
				continue;
			}

			PriceAuditItemVO[] bvos = priceAuditVO.getChildrenVO();
			for (PriceAuditItemVO priceAuditItemVO : bvos) {
				// 假定自定义项2是行号
				String mrlineid = (String) priceAuditItemVO
						.getAttributeValue("mrlineid");
				String mrid = (String) priceAuditItemVO
						.getAttributeValue("mrid");
				VOQuery<Cgag000002HVO> query = new VOQuery<Cgag000002HVO>(
						Cgag000002HVO.class);
				Cgag000002HVO[] dbVO = query.query("and mrid='" + mrid
						+ "' and mrlineid='" + mrlineid + "'", null);
				// 数量
				UFDouble bpoamt = dbVO[0].getBpoamt();
				// 税率
				UFDouble taxrate = dbVO[0].getTaxrate();
				// 不含税单价
				UFDouble bpoprice = dbVO[0].getBpoprice();
				// 合同总金额不含税
				UFDouble bposum = dbVO[0].getBposum();
				// 给价格审批单设值
				priceAuditItemVO.setNnum(bpoamt);
				priceAuditItemVO.setNtaxrate(taxrate);
				priceAuditItemVO.setNorigprice(bpoprice);
				if (bposum == null) {
					priceAuditItemVO.setPlan_priceb(null);
				} else {
					priceAuditItemVO.setPlan_priceb(bposum.toString());

				}

			}
		}
		return agg;

	}

	/**
	 * 已查出的价格审批单（已经筛选好有订货标志的）进行推单
	 * 
	 * @param aggVOS
	 * @param hvos
	 */
	public void pushToM30(PriceAuditVO[] aggVOS, Cgag000002HVO[] hvos) {
		// 本次没有新接过来的数据
		if (aggVOS.length == 0) {
			return;
		}
		// 设置当前环境的pk_group
		InvocationInfoProxy.getInstance().setGroupId("0001N610000000000IT0");
		for (int i = 0; i < aggVOS.length; i++) {
			PriceAuditVO priceAuditVO = aggVOS[i];
			if (priceAuditVO.getParent() == null) {
				continue;
			}
			// 本次聚合vo对应的中间表中的数据，需要进行回写操作（成功或者失败标志及原因）
			List<Cgag000002HVO> dbl = new ArrayList<Cgag000002HVO>();

			try {
				// 执行单据转换规则
				SaleOrderVO saleordervo = (SaleOrderVO) PfUtilTools
						.runChangeData("28-Cxx-05", "30-Cxx-05", priceAuditVO);
				saleordervo.getParentVO().setTs(
						AppContext.getInstance().getServerTime());
				// 根据商国强2017-06-22日要求则需要修改销售订单为普通
				// golble 普通的pk 1002Z83000000000FC60
				saleordervo.getParentVO()
						.setCtrantypeid("0001N6100000000023ME");
				;
				// 获取客户id给表体赋值
				String customerid = saleordervo.getParentVO().getCcustomerid();
				String pk_org = saleordervo.getParentVO().getPk_org();
				// 生成单据号
				CreateBillCodeRule rule = new CreateBillCodeRule();
				rule.setCbilltype("30");
				rule.setGroupItem("pk_group");
				rule.setCodeItem("vbillcode");
				rule.setOrgItem("pk_org");
				rule.process(new SaleOrderVO[] { saleordervo });

				SaleOrderBVO[] bvos = saleordervo.getChildrenVO();
				// 对应的数据库vo（存档为了回写）
				for (SaleOrderBVO saleOrderBVO : bvos) {
					// 给表体设置ts

					saleOrderBVO
							.setTs(AppContext.getInstance().getServerTime());
					// 根据商国强2017-06-26

					// 设置客户
					saleOrderBVO.setCcustmaterialid(customerid);
					//设置pk_org
					saleOrderBVO.setPk_org(pk_org);
					/*****2017-05-26*****/
					String mrid = (String) saleOrderBVO
							.getAttributeValue("mrid");
					String mrlineid = (String) saleOrderBVO
							.getAttributeValue("mrlineid");
					// 查询对应的数据库来源数据
					VOQuery<Cgag000002HVO> query = new VOQuery<Cgag000002HVO>(
							Cgag000002HVO.class);
					Cgag000002HVO[] dbVO = query.query("and mrid='" + mrid
							+ "' and mrlineid='" + mrlineid + "'", null);
					dbl.add(dbVO[0]);
					// 查出对应的值出来了，然后尽情的赋值吧
					// 采购合同号----表体自定义2

					String bpoid = dbVO[0].getBpoid();
					// 采购合同行号----表体自定义3
					String bpolineid = dbVO[0].getBpolineid();
					// 数量----数量
					UFDouble bpoamt = dbVO[0].getBpoamt();
					// 税率--------税率
					UFDouble taxrate = dbVO[0].getTaxrate();
					// 不含税单价---无税单价
					UFDouble bpoprice = dbVO[0].getBpoprice();
					// 合同总金额不含税----无税金额
					UFDouble bposum = dbVO[0].getBposum();
					// 交付起始日期----计划发货日期
					UFDate deliverystartdate = getUFdate(dbVO[0]
							.getDeliverystartdate());
					// 交付截止日期-----要求收货日期
					UFDate deliverystopdate = getUFdate(dbVO[0]
							.getDeliverystopdate());
					// 公司名称-------客户和开票客户--------表头
					String companyname = dbVO[0].getCompanyname();
					// 合同签订时间------单据日期------表头
					UFDate signedtime = getUFdate(dbVO[0].getSignedtime());
					UFDouble percent = new UFDouble(100);
					// 含税单价（简单计算）
					UFDouble hsdj = bpoprice.add(bpoprice.multiply(taxrate
							.div(percent)));
					// 价税合计（含税单价*数量）
					UFDouble jshj = hsdj.multiply(bpoamt);
					// 税额
					UFDouble se = jshj.sub(bposum);
					/****************************** 赋值 **************************************/
					// 2017-06-16新增一些数据字段赋值
					String custmer=getCompanyPK(companyname);
					saleOrderBVO.setVbdef2(bpoid);
					saleOrderBVO.setVbdef3(bpolineid);
					saleOrderBVO.setNastnum(bpoamt);
					saleOrderBVO.setNtaxrate(taxrate);
					// 无税单价
					saleOrderBVO.setNqtorigprice(bpoprice);
					// 含税单价
					saleOrderBVO.setNqtorigtaxprice(hsdj);
					// 无税金额（无税单价*数量）
					saleOrderBVO.setNorigmny(bposum);
					// 含税金额（含税单价*数量）
					saleOrderBVO.setNorigtaxmny(jshj);
					saleOrderBVO.setNtax(se);

					saleOrderBVO.setDsenddate(deliverystartdate);
					saleOrderBVO.setDreceivedate(deliverystopdate);
					
					//2017-07-06 增加收货客户和结算财务组织的赋值
					//结算财务组织
					saleOrderBVO.setCsettleorgid(getOrg());
					//收货客户
					saleOrderBVO.setCreceivecustid(custmer);
					saleordervo.getParentVO().setDbilldate(signedtime);
					saleordervo.getParentVO().setCinvoicecustid(
							getCompanyPK(custmer));
					saleordervo.getParentVO().setCcustomerid(
							getCompanyPK(custmer));

				}

				VORowNoUtils
						.setVOsRowNoByRule(new SaleOrderVO[] { saleordervo });
				BillInsert<SaleOrderVO> bo = new BillInsert<SaleOrderVO>();
				// 插入到数据库后的最新单据VO，至少时间戳是最新的
				SaleOrderVO[] vos = bo
						.insert(new SaleOrderVO[] { saleordervo });
				// 插入到数据库后，要把保存成功的数据回写
				Cgag000002HVO[] matchvo = dbl.toArray(new Cgag000002HVO[dbl
						.size()]);
				for (Cgag000002HVO cgag000002hvo : matchvo) {
					// 处理成功
					cgag000002hvo.setMsgflag("1");
				}
				VOUpdate<Cgag000002HVO> update = new VOUpdate<Cgag000002HVO>();
				update.update(matchvo);

			} catch (Exception e) {
				// TODO 自动生成的 catch 块
				// e.printStackTrace();
				// 回写中间表错误日志
				Cgag000002HVO[] matchvo = dbl.toArray(new Cgag000002HVO[dbl
						.size()]);
				VOUpdate<Cgag000002HVO> update = new VOUpdate<Cgag000002HVO>();
				for (Cgag000002HVO cgag000002hvo : matchvo) {
					// 处理失败
					cgag000002hvo.setMsgflag("2");
					cgag000002hvo.setMsginfo("请检查数据是否正确");

					update.update(new Cgag000002HVO[] { cgag000002hvo });
					cgag000002hvo.getPrimaryKey();

				}

			}
		}

	}

	private UFDate getUFdate(String date) throws ParseException {
		SimpleDateFormat format = new SimpleDateFormat("yyyyMMdd");
		if ("".equals(date) || date == null || " ".equals(date)) {
			return new UFDate();
		}
		Date newdate = format.parse(date);
		return UFDate.getDate(newdate);
	}

	// 根据商国强2017-06-13 所说，把传名称改为传编码了，对应客户
	private String getCompanyPK(String name) {
		List<String> list = new ArrayList<String>();
		try {
			// 查找客商档案的def2为基地对应的编码
			NCObject[] ncObjects = MDPersistenceService
					.lookupPersistenceQueryService().queryBillOfNCObjectByCond(
							CustomerVO.class, "def2='" + name + "'", false);
			if (ncObjects == null) {
				returnMsg += name + "没有对应的客户.";
				return "未有客户对应";
			}
			for (NCObject ncObject : ncObjects) {
				CustomerVO hvo = (CustomerVO) ncObject.getContainmentObject();
				String pk = hvo.getPrimaryKey();
				list.add(pk);
			}
		} catch (MetaDataException e) {
			// TODO 自动生成的 catch 块
			e.printStackTrace();
		}
		if (list.size() == 0) {
			return null;
		}
		return list.get(0);
	}
	public String getOrg(){
		HYPubBO bo=new HYPubBO();
		nc.vo.org.OrgVO[] vos = null;
		try {
			vos=(OrgVO[]) bo.queryByCondition(nc.vo.org.OrgVO.class, " name like '鞍钢国贸攀枝花有限公司'");
	
		} catch (UifException e) {
			// TODO 自动生成的 catch 块
			e.printStackTrace();
		}
		return vos.length==0?null:vos[0].getPrimaryKey();
		
	}

}
