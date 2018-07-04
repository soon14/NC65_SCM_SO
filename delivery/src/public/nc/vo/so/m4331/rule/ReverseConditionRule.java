package nc.vo.so.m4331.rule;

import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;

import nc.bs.framework.common.NCLocator;
import nc.itf.scmpub.reference.uap.bd.material.MaterialPubService;
import nc.pubitf.ic.reserve.ReserveQueryServer;
import nc.vo.bd.material.stock.MaterialStockVO;
import nc.vo.ml.NCLangRes4VoTransl;
import nc.vo.pub.BusinessException;
import nc.vo.pub.lang.UFBoolean;
import nc.vo.pub.lang.UFDouble;
import nc.vo.pubapp.pattern.exception.ExceptionUtils;
import nc.vo.pubapp.pattern.pub.MathTool;
import nc.vo.scmpub.res.billtype.SOBillType;
import nc.vo.scmpub.res.billtype.TOBillType;
import nc.vo.so.m4331.entity.DeliveryBVO;

/**
 * 预留条件判断
 * 
 * @since 6.0
 * @version 2011-5-14 下午01:58:05
 * @author 祝会征
 */
public class ReverseConditionRule {
	// 缓存可以预留的发货单
	private Map<String, DeliveryBVO> bvoMap;

	private DeliveryBVO[] bvos;

	private StringBuffer errMsg;

	// 缓存物料是否可也预留标识
	private Map<String, UFBoolean> matMap;

	private Map<String, UFBoolean> srcMap;

	public ReverseConditionRule(DeliveryBVO[] vos) {
		if (null == vos) {
			ExceptionUtils.wrappBusinessException(NCLangRes4VoTransl
					.getNCLangRes().getStrByID("4006002_0", "04006002-0163")
			/* 请选中表体行进行预留。 */);
		}
		this.bvos = vos;
		this.matMap = new HashMap<String, UFBoolean>();
		this.bvoMap = new HashMap<String, DeliveryBVO>();
		this.srcMap = new HashMap<String, UFBoolean>();
		for (DeliveryBVO bvo : this.bvos) {
			this.bvoMap.put(bvo.getCdeliverybid(), bvo);
		}
		this.initMatMap();
		this.initSrcMap();
	}

	/**
	 * 判断是否可以做预留
	 * 
	 * @param bvos
	 * @return
	 */
	public void checkReverse() {
		this.errMsg = new StringBuffer();
		for (DeliveryBVO bvo : this.bvos) {
			this.checkRowClose(bvo);
			this.checkNum(bvo);
			// 检查是否可预留物料 q q
			this.checkIsReserveMaterial(bvo);
			this.checkIsReserveSrc(bvo);
		}
		if (this.errMsg.length() > 0) {
			ExceptionUtils.wrappBusinessException(this.errMsg.toString());
		}
	}

	/*
	 * 检查物料是否是预留物料
	 * 
	 * @param vo
	 * 
	 * @throws BusinessException
	 */
	private void checkIsReserveMaterial(DeliveryBVO bvo) {
		String bid = bvo.getCdeliverybid();
		if (!this.bvoMap.containsKey(bid)) {
			return;
		}
		String pk_material = bvo.getCmaterialid();
		UFBoolean remain = this.matMap.get(pk_material);
		if (!remain.booleanValue()) {
			this.errMsg.append(NCLangRes4VoTransl.getNCLangRes().getStrByID(
					"4006002_0", "04006002-0164", null,
					new String[] { bvo.getCrowno() })/*
													 * 发货单行：{0}的物料不是可预留物料 ，不能预留。
													 */);
			this.errMsg.append("\n");
			this.bvoMap.remove(bvo.getCdeliverybid());
		}
	}

	private void checkIsReserveSrc(DeliveryBVO bvo) {
		String srcbid = bvo.getCsrcbid();
		UFBoolean isReverse = this.srcMap.get(srcbid);
		if (isReverse.booleanValue()) {
			this.bvoMap.remove(bvo.getCdeliverybid());
			this.errMsg.append(NCLangRes4VoTransl.getNCLangRes().getStrByID(
					"4006002_0", "04006002-0165", null,
					new String[] { bvo.getCrowno() })/*
													 * 发货单行：{0}的来源单据已经做过预留
													 * ，不能预留。
													 */);
			this.errMsg.append("\n");
		}
	}

	private void checkNum(DeliveryBVO bvo) {
		String bid = bvo.getCdeliverybid();
		if (!this.bvoMap.containsKey(bid)) {
			return;
		}
		UFDouble nnum = bvo.getNnum();
		UFDouble outNum = bvo.getNtotaloutnum();
		UFDouble reqNum = bvo.getNreqrsnum();
		UFDouble value = MathTool.add(outNum, reqNum);
		if (MathTool.compareTo(nnum, value) < 0) {
			this.errMsg.append(NCLangRes4VoTransl.getNCLangRes().getStrByID(
					"4006002_0", "04006002-0166", null,
					new String[] { bvo.getCrowno() })/*
													 * 发货单行：{0}发货数量小于累计出库数量与预留数量之和
													 * ，不能预留。
													 */);
			this.errMsg.append("\n");
			this.bvoMap.remove(bvo.getCdeliverybid());
		}
	}

	/**
	 * 检查行是否关闭
	 * 
	 * @param bvos
	 */
	private void checkRowClose(DeliveryBVO bvo) {
		if (!this.bvoMap.containsKey(bvo.getCdeliverybid())) {
			return;
		}
		UFBoolean flag = bvo.getBoutendflag();
		if (null == flag || !flag.booleanValue()) {
			return;
		}
		this.bvoMap.remove(bvo.getCdeliverybid());
		this.errMsg.append(NCLangRes4VoTransl.getNCLangRes().getStrByID(
				"4006002_0", "04006002-0167", null,
				new String[] { bvo.getCrowno() })/* 发货单行{0}已经行关闭，不能做预留 */);
		this.errMsg.append("\n");
	}

	private void initMatMap() {
		// 发货库存组织，同一张发货单发货单的发货库存组织是一样的
		String csendstockorgid = null;
		// 缓存发货单表体物料id
		Set<String> materialSet = new HashSet<String>();
		for (DeliveryBVO bvo : this.bvos) {
			String material = bvo.getCmaterialid();
			if (null == csendstockorgid || "".equals(csendstockorgid)) {
				csendstockorgid = bvo.getCsendstockorgid();
			}
			materialSet.add(material);
		}
		String[] pk_materials = new String[materialSet.size()];
		materialSet.toArray(pk_materials);
		// 根据物料id 发货库存组织查询物料信息
		Map<String, MaterialStockVO> stockMap = MaterialPubService
				.queryMaterialStockInfo(pk_materials, csendstockorgid,
						new String[] { MaterialStockVO.REMAIN,
								MaterialStockVO.PK_MATERIAL });
		if (null == stockMap || stockMap.size() == 0) {
			ExceptionUtils
					.wrappBusinessException(NCLangRes4VoTransl.getNCLangRes()
							.getStrByID("4006002_0", "04006002-0159")/*
																	 * 表体行的物料没有分配到对应的发货库存组织
																	 * 。
																	 */);
			return;
		}
		for (DeliveryBVO bvo : this.bvos) {
			String pk_material = bvo.getCmaterialid();
			MaterialStockVO stockvo = stockMap.get(pk_material);
			UFBoolean remain = stockvo.getRemain();
			if (null == remain || !remain.booleanValue()) {
				this.matMap.put(pk_material, UFBoolean.FALSE);
				continue;
			}
			this.matMap.put(pk_material, UFBoolean.TRUE);
		}
	}

	/*
	 * 检查来源单据是否做过预留 并且返回没有上游单据没有做过预留的vo
	 */
	private void initSrcMap() {
		String srctype = this.bvos[0].getVsrctype();
		if (SOBillType.Order.getCode().equals(srctype)
				|| TOBillType.TransOrder.getCode().equals(srctype)) {
			this.initOrderReverse(srctype);
		}
	}

	/*
	 * 检查来源单据是否做过预留
	 * 
	 * @param views
	 * 
	 * @return
	 */
	private void initOrderReverse(String srctype) {
		// 缓存来源表体id
		Set<String> idSet = new HashSet<String>();
		for (DeliveryBVO bvo : this.bvos) {
			idSet.add(bvo.getCsrcbid());
		}
		if (idSet.size() != 0) {
			String[] ids = new String[idSet.size()];
			ReserveQueryServer service = NCLocator.getInstance().lookup(
					ReserveQueryServer.class);
			try {
				this.srcMap = service.hasSrcBillReserved(srctype,
						idSet.toArray(ids));
			} catch (BusinessException e) {
				ExceptionUtils.wrappException(e);
			}
		}
	}

}
