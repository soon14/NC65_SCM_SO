package nc.impl.so.m30.msg;

import nc.impl.pubapp.pattern.data.bill.BillInsert;   
import nc.impl.pubapp.pattern.data.view.ViewQuery;
import nc.impl.pubapp.pattern.data.vo.VOUpdate;
import nc.itf.so.m30.msg.IMsgCgag000002;

import java.util.ArrayList;
import java.util.List;


import nc.bs.framework.common.InvocationInfoProxy;
import nc.bs.pub.pf.PfUtilTools;
import nc.md.data.access.NCObject;
import nc.md.model.MetaDataException;
import nc.md.persist.framework.MDPersistenceService;
import nc.vo.pp.m28.entity.PriceAuditItemVO;
import nc.vo.pp.m28.entity.PriceAuditVO;
import nc.vo.pp.m28.entity.PriceAuditViewVO;
import nc.vo.pub.IVOMeta;
import nc.vo.pub.lang.UFDouble;
import nc.vo.pubapp.AppContext;
import nc.vo.pubapp.bill.CombineBill;
import nc.vo.pubapp.pattern.exception.ExceptionUtils;
import nc.vo.pubapp.util.VORowNoUtils;
import nc.vo.so.m30.entity.SaleOrderBVO;
import nc.vo.so.m30.entity.SaleOrderVO;
import nc.vo.lm.pgjdcght.AggCgag000002HVO;
import nc.vo.lm.pgjdcght.Cgag000002HVO;

/**
 * @author wangzym
 * @version 2017年4月10日 下午9:19:13
 * 本次将价格审批单的vbdef1设计成需求号
 * 本次将价格审批单的vbdef2设计成需求行号
 * 而且vbdef1和vbdef没有流程的
 * 将pk_group设计为定值
 * 
 */
public class MsgCgag000002Impl implements IMsgCgag000002 {

	/*
	 * （非 Javadoc）
	 * 
	 * @see
	 * nc.itf.so.m30.msg.IMsgCgag000002#Cgag000002RequiresNew(nc.vo.lm.pgjdcght
	 * .Cgag000002HVO)
	 * 不需要传参数来没必要
	 */
	@Override
	public void Cgag000002RequiresNew(Cgag000002HVO cgag000002hvo) {
		// TODO 自动生成的方法存根
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
		/*
		 * VOQuery<Cgag000002HVO> query = new VOQuery<Cgag000002HVO>(
		 * Cgag000002HVO.class); SqlBuilder sb = new SqlBuilder();
		 * //sb.append("and dr<>'1'"); sb.append("and  msgflag='0'");
		 * Cgag000002HVO[] hvos = query.query(sb.toString(), null);
		 */
		Cgag000002HVO[] hvos = lhvo.toArray(new Cgag000002HVO[lhvo.size()]);
		/*
		 * List<Cgag000002HVO> hvos1 = new ArrayList<Cgag000002HVO>(); for (int
		 * i = 0; i < hvos.length; i++) {
		 * 
		 * if (!hvos1.contains(hvos[i])) { hvos1.add(hvos[i]); } }
		 * Cgag000002HVO[] hvo = hvos1.toArray(new Cgag000002HVO[hvos1.size()]);
		 */
		// 计算出来需要处理多少张推单，也就是多少个价格审批单的aggvo需要处理
		List<String> mrid = new ArrayList<String>();
		for (int i = 0; i < hvos.length; i++) {
			Cgag000002HVO cgag000002hvo2 = hvos[i];
			String mri = cgag000002hvo2.getMrid();
			if (!mrid.contains(mri)) {
				mrid.add(mri);
			}
		}
		// 查出对应的主表主键的集合
		List<String> bpks = queryPriceAuditVO(mrid);

		// 查出聚合vo
		PriceAuditVO[] agg = queryPriceAuditAggVO(bpks);
		// 对价格审批单进行更新值操作与中间表数据对应
		PriceAuditVO[] aggVOS = updatePriceAudit(agg, hvos);
		// 进行推单操作
		pushToM30(aggVOS,hvos);

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
		List<Cgag000002HVO> matchvo = new ArrayList<Cgag000002HVO>();

		for (PriceAuditVO priceAuditVO : agg) {

			// 假定自定义项1是需求号
			String mrid = priceAuditVO.getChildrenVO()[0].getVbdef1();
			for (int i = 0; i < hvos.length; i++) {
				if ((hvos[i].getMrid()).equals(mrid)) {
					matchvo.add(hvos[i]);
				}
			}
			Cgag000002HVO[] matchvos = matchvo
					.toArray(new Cgag000002HVO[matchvo.size()]);
			PriceAuditItemVO[] bvos = priceAuditVO.getChildrenVO();
			for (PriceAuditItemVO priceAuditItemVO : bvos) {
				// 假定自定义项2是行号
				String mrlineid = priceAuditItemVO.getVbdef2();
				for (int i = 0; i < matchvos.length; i++) {
					if (matchvos[i].getMrlineid().equals(mrlineid)) {
						// 数量
						UFDouble bpoamt = matchvos[i].getBpoamt();
						// 税率
						UFDouble taxrate = matchvos[i].getTaxrate();
						// 不含税单价
						UFDouble bpoprice = matchvos[i].getBpoprice();
						// 合同总金额不含税
						UFDouble bposum = matchvos[i].getBposum();
						// 给价格审批单设值
						priceAuditItemVO.setNnum(bpoamt);
						priceAuditItemVO.setNtaxrate(taxrate);
						priceAuditItemVO.setNorigprice(bpoprice);
						priceAuditItemVO.setPlan_priceb(bposum.toString());

					}

				}
			}
		}
		return agg;

	}

	public List<String> queryPriceAuditVO(List<String> list) {
		String[] mrid = list.toArray(new String[list.size()]);
		List<String> hpks = new ArrayList<String>();
		List<String> bpks = new ArrayList<String>();

		for (int i = 0; i < mrid.length; i++) {
			String mri = mrid[i];
			NCObject[] ncObjects = null;
			try {
				// 假定需求号 =vbdef1
				ncObjects = MDPersistenceService
						.lookupPersistenceQueryService()
						.queryBillOfNCObjectByCond(PriceAuditItemVO.class,
								"vbdef1 = '" + mri + "' and dr='0'", false);
			} catch (MetaDataException e) {
				// TODO 自动生成的 catch 块
				e.printStackTrace();
			}
			if (ncObjects != null) {

				PriceAuditItemVO bvo = (PriceAuditItemVO) ncObjects[0]
						.getContainmentObject();
				String hpk = bvo.getPk_priceaudit();
				if (!hpks.contains(hpk)) {
					hpks.add(hpk);
				}
				for (int j = 0; j < ncObjects.length; j++) {
					PriceAuditItemVO bvos = (PriceAuditItemVO) ncObjects[j]
							.getContainmentObject();
					String pk_PriceAudit_b = bvos.getPk_priceaudit_b();
					if (!bpks.contains(pk_PriceAudit_b)) {
						bpks.add(pk_PriceAudit_b);
					}
				}
			}
		}
		// view查询要使用子表主键才能一条一条查出，禁止使用主表主键
		return bpks;

	}

	// PriceAuditVO[] pavo = queryPriceAuditAggVO(hpks);

	public void pushToM30(PriceAuditVO[] aggVOS,Cgag000002HVO[] hvos) {
		//本次没有新接过来的数据
		if (aggVOS.length==0) {
			return;
		}
		// 设置当前环境的pk_group
		InvocationInfoProxy.getInstance().setGroupId("0001N610000000000IT0");
		for (int i = 0; i < aggVOS.length; i++) {
			PriceAuditVO priceAuditVO = aggVOS[i];
			//本次聚合vo对应的中间表中的数据，需要进行回写操作（成功或者失败标志及原因）
			List<Cgag000002HVO> lMatchvo=new ArrayList<Cgag000002HVO>();
			for (Cgag000002HVO cgag000002hvo : hvos) {
				PriceAuditItemVO[]  bvos=priceAuditVO.getChildrenVO();
				String mrid =bvos[0].getVbdef1();
				if (cgag000002hvo.getMrid().equals(mrid)) {
					lMatchvo.add(cgag000002hvo);
				}
			}

			try {
				SaleOrderVO saleordervo = (SaleOrderVO) PfUtilTools
						.runChangeData("28-Cxx-03", "30", priceAuditVO);
				NCObject objs = NCObject.newInstance(saleordervo);// AGGVO对象
				saleordervo.getParentVO().setTs(
						AppContext.getInstance().getServerTime());
				SaleOrderBVO[] bvos = saleordervo.getChildrenVO();
				for (SaleOrderBVO saleOrderBVO : bvos) {
					//给表体设置ts
					saleOrderBVO
							.setTs(AppContext.getInstance().getServerTime());
				}
				VORowNoUtils.setVOsRowNoByRule(new SaleOrderVO[] { saleordervo });
				BillInsert<SaleOrderVO> bo = new BillInsert<SaleOrderVO>();
				// 插入到数据库后的最新单据VO，至少时间戳是最新的
				SaleOrderVO[] vos = bo
						.insert(new SaleOrderVO[] { saleordervo });
				//插入到数据库后，要把保存成功的数据回写（没写）
				Cgag000002HVO[] matchvo=lMatchvo.toArray(new Cgag000002HVO[lMatchvo.size()]);
				for (Cgag000002HVO cgag000002hvo : matchvo) {
					//处理成功
					cgag000002hvo.setMsgflag("1");
				}
				VOUpdate<Cgag000002HVO> update=new VOUpdate<Cgag000002HVO>();
				update.update(matchvo);
				
			} catch (Exception e) {
				// TODO 自动生成的 catch 块
				// e.printStackTrace();
				// 回写中间表错误日志
				Cgag000002HVO[] matchvo=lMatchvo.toArray(new Cgag000002HVO[lMatchvo.size()]);
				for (Cgag000002HVO cgag000002hvo : matchvo) {
					//处理失败
					cgag000002hvo.setMsgflag("2");
					cgag000002hvo.setMsginfo(e.getMessage());
					
				}
				VOUpdate<Cgag000002HVO> update=new VOUpdate<Cgag000002HVO>();
				update.update(matchvo);
				e.getMessage();
				ExceptionUtils.wrappBusinessException("处理失败");
			}
		}

	}

	/**
	 * @param hpks
	 */
	private PriceAuditVO[] queryPriceAuditAggVO(List<String> hpks) {
		// TODO 自动生成的方法存根
		PriceAuditVO[] rets = new PriceAuditVO[hpks.size()];
		ViewQuery<PriceAuditViewVO> query = new ViewQuery<PriceAuditViewVO>(
				PriceAuditViewVO.class);
		PriceAuditViewVO[] views = query.query(hpks.toArray(new String[hpks
				.size()]));

		if (null != views && views.length > 0) {
			int len = views.length;
			PriceAuditVO[] bills = new PriceAuditVO[len];
			for (int i = 0; i < len; i++) {
				bills[i] = (views[i]).changeToBill();
			}
			CombineBill<PriceAuditVO> combine = new CombineBill<PriceAuditVO>();
			IVOMeta headMeta = bills[0].getMetaData().getParent();
			String headItemKey = headMeta.getPrimaryAttribute().getName();
			combine.appendKey(headItemKey);
			rets = combine.combine(bills);
		}
		return rets;

	}
	/**
	 * 生成单据号
	 */

}
