package nc.bs.so.salequotation.rule;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;

import nc.bs.ml.NCLangResOnserver;
import nc.impl.pubapp.pattern.data.vo.VOQuery;
import nc.impl.pubapp.pattern.database.IDQueryBuilder;
import nc.impl.pubapp.pattern.rule.IRule;
import nc.vo.pub.VOStatus;
import nc.vo.pub.lang.UFBoolean;
import nc.vo.pub.lang.UFDouble;
import nc.vo.pubapp.pattern.exception.ExceptionUtils;
import nc.vo.pubapp.pattern.pub.MathTool;
import nc.vo.pubapp.pattern.pub.PubAppTool;
import nc.vo.scmpub.util.VOFieldLengthChecker;
import nc.vo.so.m4310trantype.entity.M4310TranTypeVO;
import nc.vo.so.pub.util.SOMathUtil;
import nc.vo.so.pub.util.SOSysParaInitUtil;
import nc.vo.so.salequotation.entity.AggSalequotationHVO;
import nc.vo.so.salequotation.entity.SalequotationBVO;
import nc.vo.so.salequotation.entity.SalequotationHVO;

/**
 * @description 销售报价单保存前数据检查
 * @scene 销售报价单保存前
 * @param m_transType
 *            销售报价单交易类型<销售报价单交易类型code,销售报价单交易类型vo> tranTypeService 销售报价单交易类型服务接口
 */
public class SavedDataCheckRule implements IRule<AggSalequotationHVO> {

	// 销售报价单交易类型<销售报价单交易类型code,销售报价单交易类型vo>
	Map<String, M4310TranTypeVO> m_transType = new HashMap<String, M4310TranTypeVO>();

	public void initTransType(AggSalequotationHVO[] aggvos) {
		Set<String> ctrantypeIds = new HashSet<String>();
		for (AggSalequotationHVO vo : aggvos) {
			ctrantypeIds.add(vo.getParentVO().getCtrantypeid());
		}
		M4310TranTypeVO[] trantypevos = null;
		VOQuery<M4310TranTypeVO> query = new VOQuery<M4310TranTypeVO>(
				M4310TranTypeVO.class);
		IDQueryBuilder idb = new IDQueryBuilder();
		String cond = idb.buildSQL(M4310TranTypeVO.CTRANTYPEID,
				ctrantypeIds.toArray(new String[0]));
		trantypevos = query.query(" and " + cond, null);
		for (M4310TranTypeVO vo : trantypevos) {
			m_transType.put(vo.getCtrantypeid(), vo);
		}
	}

	@Override
	public void process(AggSalequotationHVO[] vos) {
		initTransType(vos);
		this.checkSavedData(vos);
	}

	private void checkMainPrice(SalequotationBVO bvo) {
		if (bvo.getNorigprice() != null
				&& bvo.getNorigprice().doubleValue() < 0) {
			ExceptionUtils
					.wrappBusinessException(nc.vo.ml.NCLangRes4VoTransl
							.getNCLangRes().getStrByID("4006009_0",
									"04006009-0028")/* @res "主无税单价不能为空且不能小于零!" */);
		}
		if (bvo.getNorigtaxprice() != null
				&& bvo.getNorigtaxprice().doubleValue() < 0) {
			ExceptionUtils
					.wrappBusinessException(nc.vo.ml.NCLangRes4VoTransl
							.getNCLangRes().getStrByID("4006009_0",
									"04006009-0029")/* @res "主含税单价不能为空且不能小于零!" */);
		}
		if (bvo.getNorignetprice() != null
				&& bvo.getNorignetprice().doubleValue() < 0) {
			ExceptionUtils
					.wrappBusinessException(nc.vo.ml.NCLangRes4VoTransl
							.getNCLangRes().getStrByID("4006009_0",
									"04006009-0030")/* @res "主无税净价不能为空且不能小于零!" */);
		}
		if (bvo.getNorigtaxnetprice() != null
				&& bvo.getNorigtaxnetprice().doubleValue() < 0) {
			ExceptionUtils
					.wrappBusinessException(nc.vo.ml.NCLangRes4VoTransl
							.getNCLangRes().getStrByID("4006009_0",
									"04006009-0031")/* @res "主含税净价不能为空且不能小于零!" */);
		}

		UFDouble price = bvo.getNorigtaxprice();
		if (MathTool.compareTo(price, UFDouble.ZERO_DBL) <= 0) {
			ExceptionUtils
					.wrappBusinessException(nc.vo.ml.NCLangRes4VoTransl
							.getNCLangRes().getStrByID("4006009_0",
									"04006009-0032")/* @res "价格不能为空且不能小于等于0" */);
		}
		price = bvo.getNqtorigtaxprice();
		if (MathTool.compareTo(price, UFDouble.ZERO_DBL) <= 0) {
			ExceptionUtils
					.wrappBusinessException(nc.vo.ml.NCLangRes4VoTransl
							.getNCLangRes().getStrByID("4006009_0",
									"04006009-0032")/* @res "价格不能为空且不能小于等于0" */);
		}
		if (bvo.getNorigmny() == null) {
			ExceptionUtils
					.wrappBusinessException(nc.vo.ml.NCLangRes4VoTransl
							.getNCLangRes().getStrByID("4006009_0",
									"04006009-0074")/* @res "无税金额不能为空。" */);
		}
		if (bvo.getNorigtaxmny() == null) {
			ExceptionUtils
					.wrappBusinessException(nc.vo.ml.NCLangRes4VoTransl
							.getNCLangRes().getStrByID("4006009_0",
									"04006009-0075")/* @res "价税合计不能为空。" */);
		}

	}	

	private void checkNumber(SalequotationBVO bvo) {
		if (SOMathUtil.isZero(bvo.getNnum()) || bvo.getNnum().doubleValue() < 0) {
			ExceptionUtils
					.wrappBusinessException(nc.vo.ml.NCLangRes4VoTransl
							.getNCLangRes().getStrByID("4006009_0",
									"04006009-0034")/* @res "主数量不能为空且不能小于零!" */);
		}
		if (SOMathUtil.isZero(bvo.getNassistnum())
				|| bvo.getNassistnum().doubleValue() < 0) {
			ExceptionUtils
					.wrappBusinessException(nc.vo.ml.NCLangRes4VoTransl
							.getNCLangRes().getStrByID("4006009_0",
									"04006009-0035")/* @res "数量不能为空且不能小于零!" */);
		}
	}


	private void checkRowCountLimit(AggSalequotationHVO vo) {
		Object pk_org = vo.getParentVO().getPk_org();
		int rowlimit = 0;

		rowlimit = SOSysParaInitUtil.getSO29(pk_org.toString()) == null ? 0
				: SOSysParaInitUtil.getSO29(pk_org.toString()).intValue();

		int rowCount = vo.getChildrenVO().length;
		if (rowlimit > 0 && rowCount > rowlimit) {
			ExceptionUtils
					.wrappBusinessException(NCLangResOnserver
							.getInstance()
							.getStrByID("4006009_0", "04006009-0067", null,
									new String[] { Integer.toString(rowlimit) })/*
																				 * 超报价单限制行数
																				 * !
																				 * [
																				 * 参数SO29限制行数
																				 * :
																				 * {
																				 * 0
																				 * }
																				 * ]
																				 */);
		}
	}

	private void checkSavedData(AggSalequotationHVO aggVO) {
		VOFieldLengthChecker.checkVOFieldsLength(aggVO);
		if (aggVO.getChildrenVO() != null && aggVO.getChildrenVO().length != 0) {
			SalequotationBVO[] bvos = aggVO.getChildrenVO();
			for (int i = 0; i < bvos.length; i++) {
				this.validateNonNegative(bvos[i]);
			}
		}
	}

	private void checkSavedData(AggSalequotationHVO[] vos) {
		for (AggSalequotationHVO vo : vos) {
			this.checkSavedData(vo);
			// this.checkMaterielMutil(vo);
			this.checkRowCountLimit(vo);
			this.checkMaterielMutil(vo);
		}
	}

	/**
	 * 控制物料是否可重复(liylr 2015-04-22)
	 * 
	 * @param bill
	 */
	private void checkMaterielMutil(AggSalequotationHVO bill) {

		SalequotationHVO header = bill.getParentVO();
		UFBoolean bmorerows = m_transType.get(header.getCtrantypeid())
				.getBmorerows();
		if (bmorerows != null && bmorerows.booleanValue()) {
			return;
		}
		Set<String> sinvo = new HashSet<String>();
		Set<String> sinvv = new HashSet<String>();
		for (SalequotationBVO bvo : bill.getChildrenVO()) {
			// 删除行
			if (VOStatus.DELETED == bvo.getStatus()) {
				continue;
			}
			// 赠品行
			if (null != bvo.getBlargessflag()
					&& bvo.getBlargessflag().booleanValue()) {
				continue;
			}
			String materieloid = bvo.getPk_material();
			String materielvid = bvo.getPk_material_v();
			if (sinvo.contains(materieloid) && sinvv.contains(materielvid)) {
				ExceptionUtils
						.wrappBusinessException(nc.vo.ml.NCLangRes4VoTransl
								.getNCLangRes().getStrByID("4006009_0",
										"04006009-0087")/*
														 * @res
														 * "报价单类型控制同一货物不可列多行！"
														 */);
			} else {
				sinvo.add(materieloid);
				sinvv.add(materielvid);
			}
		}

	}

	private void validateNonNegative(SalequotationBVO bvo) {
		if (bvo != null) {
			this.checkBodyValidity(bvo);
			this.checkNumber(bvo);
			UFBoolean blargess = bvo.getBlargessflag();
			if (!blargess.booleanValue()) {
				// this.checkPrice(bvo);
				this.checkMainPrice(bvo);
			}
			if (bvo.getNtaxrate() != null
					&& bvo.getNtaxrate().doubleValue() < 0) {
				ExceptionUtils
						.wrappBusinessException(nc.vo.ml.NCLangRes4VoTransl
								.getNCLangRes().getStrByID("4006009_0",
										"04006009-0036")/* @res "税率不能为负数!" */);
			}
			if (bvo.getNitemdiscountrate() != null
					&& bvo.getNitemdiscountrate().doubleValue() < 0) {
				ExceptionUtils
						.wrappBusinessException(nc.vo.ml.NCLangRes4VoTransl
								.getNCLangRes().getStrByID("4006009_0",
										"04006009-0037")/* @res "单品折扣不能为负数!" */);
			}
			// if (bvo.getNchangerate() != null
			// && bvo.getNchangerate().doubleValue() < 0) {
			// ExceptionUtils
			// .wrappBusinessException("换算率不能为负数!");
			// }
			if (bvo.getNqtnum() != null && bvo.getNqtnum().doubleValue() < 0) {
				ExceptionUtils
						.wrappBusinessException(nc.vo.ml.NCLangRes4VoTransl
								.getNCLangRes().getStrByID("4006009_0",
										"04006009-0038")/* @res "报价换算率不能为负数!" */);
			}
		}
	}

	private void checkBodyValidity(SalequotationBVO bvo) {
		// 过滤删除行 注释掉unchanged
		if (VOStatus.DELETED == bvo.getStatus()
				|| VOStatus.UNCHANGED == bvo.getStatus()) {
			return;
		}
		// 不合法字段
		List<String> listValiField = new ArrayList<String>();

		String cmaterialid = bvo.getPk_material_v();
		if (PubAppTool.isNull(cmaterialid)) {
			listValiField.add(NCLangResOnserver.getInstance().getStrByID(
					"4006011_0", "04006011-0307")/* 物料 */);
		}

		String castunitid = bvo.getCastunitid();
		if (PubAppTool.isNull(castunitid)) {
			listValiField.add(NCLangResOnserver.getInstance().getStrByID(
					"4006011_0", "04006011-0308")/* 单位 */);
		}

		UFDouble ndiscountrate = (UFDouble) bvo
				.getAttributeValue(SalequotationBVO.NDISCOUNTRATE);
		if (null == ndiscountrate) {
			listValiField.add(NCLangResOnserver.getInstance().getStrByID(
					"4006011_0", "04006011-0309")/* 整单折扣 */);
		}
		UFDouble nitemdiscount = bvo.getNitemdiscountrate();
		if (null == nitemdiscount) {
			listValiField.add(NCLangResOnserver.getInstance().getStrByID(
					"4006011_0", "04006011-0310")/* 单品折扣 */);
		}
		UFDouble ntaxrate = bvo.getNtaxrate();
		if (null == ntaxrate) {
			listValiField.add(NCLangResOnserver.getInstance().getStrByID(
					"4006011_0", "04006011-0311")/* 税率 */);
		}

		if (null == bvo.getCtaxcodeid()) {
			listValiField.add(NCLangResOnserver.getInstance().getStrByID(
					"4006011_0", "04006011-0439")/* 税码 */);
		}

		if (null == bvo.getFtaxtypeflag()) {
			listValiField.add(NCLangResOnserver.getInstance().getStrByID(
					"4006011_0", "04006011-0440")/* 扣税类别 */);
		}

		if (null == bvo.getCrececountryid()) {
			listValiField.add(NCLangResOnserver.getInstance().getStrByID(
					"4006011_0", "04006011-0442")/* 收货国家/地区 */);
		}

		if (null == bvo.getCsendcountryid()) {
			listValiField.add(NCLangResOnserver.getInstance().getStrByID(
					"4006011_0", "04006011-0443")/* 发货国家/地区 */);
		}

		if (null == bvo.getCtaxcountryid()) {
			listValiField.add(NCLangResOnserver.getInstance().getStrByID(
					"4006011_0", "04006011-0444")/* 报税国家/地区 */);
		}

		if (null == bvo.getFbuysellflag()) {
			listValiField.add(NCLangResOnserver.getInstance().getStrByID(
					"4006011_0", "04006011-0445")/* 购销类型 */);
		}

		if (null == bvo.getBtriatradeflag()) {
			listValiField.add(NCLangResOnserver.getInstance().getStrByID(
					"4006011_0", "04006011-0446")/* 三角贸易 */);
		}
		StringBuilder errMsg = new StringBuilder();
		if (listValiField.size() > 0) {
			String crowno = bvo.getCrowno();
			errMsg.append(NCLangResOnserver.getInstance()
					.getStrByID("4006011_0", "04006011-0327", null,
							new String[] { crowno })/* 第[{0}]行： */);
			for (String field : listValiField) {
				errMsg.append("[")
						.append(field)
						.append("]")
						.append(NCLangResOnserver.getInstance().getStrByID(
								"4006011_0", "04006011-0284")/* 、 */);
			}
			errMsg.deleteCharAt(errMsg.length() - 1);
			errMsg.append("\n");
		}
		if (errMsg.length() > 0) {
			ExceptionUtils.wrappBusinessException(NCLangResOnserver
					.getInstance().getStrByID("4006011_0", "04006011-0328",
							null, new String[] { errMsg.toString() })/*
																	 * 下列字段值不能为空或为0:
																	 * \n{0}
																	 */);
		}
	}
}
