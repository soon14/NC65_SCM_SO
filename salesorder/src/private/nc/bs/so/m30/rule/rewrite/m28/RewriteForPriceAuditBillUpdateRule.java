/**
 * 
 */
package nc.bs.so.m30.rule.rewrite.m28;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;

import nc.impl.pubapp.pattern.data.vo.VOQuery;
import nc.impl.pubapp.pattern.data.vo.VOUpdate;
import nc.impl.pubapp.pattern.rule.ICompareRule;
import nc.vo.arap.pub.UFDoubleTool;
import nc.vo.so.m30.entity.SaleOrderVO;
import nc.vo.so.m30.entity.SaleOrderBVO;
import nc.vo.pp.m28.entity.PriceAuditItemVO;
import nc.vo.pub.lang.UFDouble;
import nc.vo.pubapp.pattern.exception.ExceptionUtils;
import nc.vo.pubapp.util.VOSortUtils.VOComparator;

/**
 * @author wangzym
 * @version 2017年5月5日 下午6:09:09
 */
public class RewriteForPriceAuditBillUpdateRule implements
		ICompareRule<SaleOrderVO> {

	public RewriteForPriceAuditBillUpdateRule() {
		// TODO 自动生成的构造函数存根
	}

	// 删除的来源主键集合
	// private List<String> delIndex = new ArrayList<String>();
	private HashMap<String, UFDouble> delIndex = new HashMap<String, UFDouble>();
	private HashMap<String, UFDouble> numMoreIndex = new HashMap<String, UFDouble>();
	private HashMap<String, UFDouble> numLessIndex = new HashMap<String, UFDouble>();

	/*
	 * （非 Javadoc）
	 * 
	 * @see nc.impl.pubapp.pattern.rule.ICompareRule#process(java.lang.Object[],
	 * java.lang.Object[])
	 */
	@Override
	public void process(SaleOrderVO[] vos, SaleOrderVO[] originVOs) {
		// TODO 只针对表体
		SaleOrderBVO[] bvos = (SaleOrderBVO[]) vos[0].getChildrenVO();
		for (int i = 0; i < bvos.length; i++) {
			SaleOrderBVO SaleOrderBVO = bvos[i];
			if ((SaleOrderBVO.getAttributeValue("csrcid")) == null
					|| !"28".equals(SaleOrderBVO.getAttributeValue("vsrctype"))) {
				// 不需要回写没有取到上游的来源单据或者来源不是价格审批单
				return;
			}
		}

		SaleOrderVO vo = vos[0];
		SaleOrderVO originVO = originVOs[0];
		compareAgg(vo, originVO);
		// delIndex去重

		reWriteDel(delIndex);
		reWriteForLessNum(numLessIndex);
		rewriteForMoreNum(numMoreIndex);

	}

	/**
	 * @param numMoreIndex2
	 */
	private void rewriteForMoreNum(HashMap<String, UFDouble> numMoreIndex2) {
		// TODO 自动生成的方法存根
		for (Entry<String, UFDouble> map : numMoreIndex2.entrySet()) {
			String pk = map.getKey();
			String newpk = pk.substring(0, 20);
			UFDouble value = map.getValue();
			VOQuery<PriceAuditItemVO> query = new VOQuery<PriceAuditItemVO>(
					PriceAuditItemVO.class);
			PriceAuditItemVO[] bvo = query.query(new String[] { newpk });
			UFDouble has = (UFDouble) bvo[0].getAttributeValue("hasnordastnum");
			UFDouble sum = (UFDouble) bvo[0].getAttributeValue("nordastnum");
			UFDouble num = sum;
			if (has == null) {
			} else {
				num = sum.sub(has);

			}
			// UFDouble num = sum.sub(has);
			int i = num.compareTo(map.getValue());
			if (i >= 0) {
				// 没问题还能做
				UFDouble newHas = UFDoubleTool.sum(has, value); // has.add(value.intValue());
				PriceAuditItemVO vo = new PriceAuditItemVO();
				vo.setPrimaryKey(newpk);
				vo.setAttributeValue("hasnordastnum", newHas);
				// 回写上游直接更新
				VOUpdate<PriceAuditItemVO> update = new VOUpdate<PriceAuditItemVO>();
				update.update(new PriceAuditItemVO[] { vo },
						new String[] { "hasnordastnum" });

			} else {
				// 出错了
				String pk1 = map.getKey();
				String rowNum = pk1.substring(21);
				String message = "第" + rowNum + "行数量已超出上游可用量";
				ExceptionUtils.wrappBusinessException(message);
			}
		}

	}

	/**
	 * @param numLessIndex2
	 */
	private void reWriteForLessNum(HashMap<String, UFDouble> numLessIndex2) {
		// 因为是减少了所以上游已参照量应该减少，不需要校验
		for (Entry<String, UFDouble> map : numLessIndex2.entrySet()) {
			String pk = map.getKey();
			String newpk = pk.substring(0, 20);
			UFDouble value = map.getValue();
			VOQuery<PriceAuditItemVO> query = new VOQuery<PriceAuditItemVO>(
					PriceAuditItemVO.class);
			PriceAuditItemVO[] bvo = query.query(new String[] { newpk });
			UFDouble has = (UFDouble) bvo[0].getAttributeValue("hasnordastnum");
			UFDouble sum = (UFDouble) bvo[0].getAttributeValue("nordastnum");

			// 因为是减少了所以上游已参照量应该减少，不需要校验
			UFDouble newHas = has.sub(value.intValue());
			PriceAuditItemVO vo = new PriceAuditItemVO();
			vo.setPrimaryKey(newpk);
			vo.setAttributeValue("hasnordastnum", newHas);
			// 回写上游直接更新
			VOUpdate<PriceAuditItemVO> update = new VOUpdate<PriceAuditItemVO>();
			update.update(new PriceAuditItemVO[] { vo },
					new String[] { "hasnordastnum" });
		}
		// TODO 自动生成的方法存根

	}

	/**
	 * @param delIndex2
	 */
	private void reWriteDel(HashMap<String, UFDouble> delIndex2) {
		// TODO 自动生成的方法存根
		// 没有删除行的操作
		if (delIndex2.size() == 0) {
			return;
		} else {

			List<PriceAuditItemVO> bvosL = new ArrayList<PriceAuditItemVO>();

			for (Map.Entry<String, UFDouble> entry : delIndex2.entrySet()) {
				VOQuery<PriceAuditItemVO> query = new VOQuery<PriceAuditItemVO>(
						PriceAuditItemVO.class);
				PriceAuditItemVO[] bvo = query.query(new String[] { entry
						.getKey() });
				UFDouble oldNum = (UFDouble) bvo[0]
						.getAttributeValue("hasnordastnum");
				UFDouble newNum = oldNum.sub(entry.getValue());
				PriceAuditItemVO priceaudititemvo = new PriceAuditItemVO();
				priceaudititemvo.setPrimaryKey(entry.getKey());
				// 释放上游数量
				priceaudititemvo.setAttributeValue("hasnordastnum", newNum);
				bvosL.add(priceaudititemvo);
			}

			VOUpdate<PriceAuditItemVO> update = new VOUpdate<PriceAuditItemVO>();
			String[] names = new String[] { "hasnordastnum" };
			update.update(bvosL.toArray(new PriceAuditItemVO[bvosL.size()]),
					names);
		}

	}

	/**
	 * @param vo
	 * @param originVO
	 */
	private void compareAgg(nc.vo.so.m30.entity.SaleOrderVO vo,
			SaleOrderVO originVO) {
		// TODO 自动生成的方法存根
		// 比较两个vo的表体长度是否一致
		if (vo.getChildrenVO().length == originVO.getChildrenVO().length) {
			// 如果一致则直接比较
			SaleOrderBVO[] bvos = (SaleOrderBVO[]) vo.getChildrenVO();
			SaleOrderBVO[] oriBvos = (SaleOrderBVO[]) originVO.getChildrenVO();
			HashMap<String, Integer> res = new HashMap<String, Integer>();
			for (int i = 0; i < bvos.length; i++) {
				SaleOrderBVO SaleOrderBVO = bvos[i];
				SaleOrderBVO oriSaleOrderBVO = oriBvos[i];
				this.caculateNum(SaleOrderBVO, oriSaleOrderBVO);
			}

		}// 两个字段不一样长说明有删行
		else {
			SaleOrderBVO[] bvos = (SaleOrderBVO[]) vo.getChildrenVO();
			// 数据库里边的数据多有删行
			List<String> temp = new ArrayList<String>();
			// 被删除的行，将值赋成未被参照过
			for (SaleOrderBVO SaleOrderBVO : bvos) {
				temp.add(SaleOrderBVO.getPrimaryKey());
			}

			ArrayList<SaleOrderBVO> oriBvosL = new ArrayList<SaleOrderBVO>();
			SaleOrderBVO[] oriBvos = (SaleOrderBVO[]) originVO.getChildrenVO();
			for (SaleOrderBVO SaleOrderBVO : oriBvos) {
				oriBvosL.add(SaleOrderBVO);
			}
			ArrayList<SaleOrderBVO> oriBvosLC = (ArrayList<SaleOrderBVO>) oriBvosL
					.clone();
			for (int i = 0; i < oriBvosL.size(); i++) {
				SaleOrderBVO SaleOrderBVO = oriBvosL.get(i);
				if (temp.contains(SaleOrderBVO.getPrimaryKey())) {
					continue;
				} else {
					delIndex.put(((String) SaleOrderBVO
							.getAttributeValue("csrcbid")),
							(UFDouble) SaleOrderBVO
									.getAttributeValue("nastnum"));
					oriBvosLC.remove(SaleOrderBVO);

				}

			}
			SaleOrderBVO[] newbvos = oriBvosLC
					.toArray(new SaleOrderBVO[oriBvosLC.size()]);
			for (int i = 0; i < newbvos.length; i++) {
				SaleOrderBVO saleOrderBVO = newbvos[i];
				this.caculateNum(bvos[i], saleOrderBVO);

			}

		}

	}

	/**
	 * @param SaleOrderBVO
	 * @param oriSaleOrderBVO
	 */
	private void caculateNum(SaleOrderBVO SaleOrderBVO,
			SaleOrderBVO oriSaleOrderBVO) {
		// TODO 自动生成的方法存根
		// 新增对数量的判断
		VOComparator<SaleOrderBVO> compare = new VOComparator<SaleOrderBVO>(
				new String[] { "nastnum" });
		int res1 = compare.compare(SaleOrderBVO, oriSaleOrderBVO);
		if (res1 == 0) {
			// 等于0不回写
		} else if (res1 == 1) {
			// 当前的数量多
			String rowNum = (String) SaleOrderBVO.getAttributeValue("crowno");
			String pk = (String) SaleOrderBVO.getAttributeValue("csrcbid");
			UFDouble num = ((UFDouble) SaleOrderBVO
					.getAttributeValue("nastnum"))
					.sub((UFDouble) oriSaleOrderBVO
							.getAttributeValue("nastnum"));
			pk = pk + "_" + rowNum;
			numMoreIndex.put(pk, num);

		} else if (res1 == -1) {
			// 当前修改的数量减少了
			String pk = (String) SaleOrderBVO.getAttributeValue("csrcbid");
			String rowNum = (String) SaleOrderBVO.getAttributeValue("crowno");
			UFDouble num = ((UFDouble) oriSaleOrderBVO
					.getAttributeValue("nastnum")).sub((UFDouble) SaleOrderBVO
					.getAttributeValue("nastnum"));
			pk = pk + "_" + rowNum;
			numLessIndex.put(pk, num);
		}
	}

}
