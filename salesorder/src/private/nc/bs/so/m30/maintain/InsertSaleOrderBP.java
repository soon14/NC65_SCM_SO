/**
 * 
 */
package nc.bs.so.m30.maintain;

import nc.bs.scmpub.rule.CrossRuleValidateRule;
import nc.bs.so.m30.maintain.rule.insert.RewriteBillInsertRule;
import nc.bs.so.m30.maintain.rule.insert.RewritePromotePriceInsertRule;
import nc.bs.so.m30.plugin.BP30PlugInPoint;
import nc.bs.so.m30.rule.atp.SaleOrderVOATPAfterRule;
import nc.bs.so.m30.rule.atp.SaleOrderVOATPBeforeRule;
import nc.bs.so.m30.rule.billcode.AddNewBillCodeRule;
import nc.bs.so.m30.rule.billcode.CheckUniqueBillCodeRule;
import nc.bs.so.m30.rule.businessinfo.SaveTransferMsgRule;
import nc.bs.so.m30.rule.credit.RenovateARByHidsBeginRule;
import nc.bs.so.m30.rule.credit.RenovateARByHidsEndRule;
import nc.bs.so.m30.rule.feature.FeatureSelectSaveRule;
import nc.bs.so.m30.rule.m35.ArsubOffsetBeforeSaveRule;
import nc.bs.so.m30.rule.m35.LrgCashMarCheckRule;
import nc.bs.so.m30.rule.maintaincheck.CheckDateRule;
import nc.bs.so.m30.rule.maintaincheck.CheckLrgTotalMoney;
import nc.bs.so.m30.rule.maintaincheck.CheckNumPriceMnyRule;
import nc.bs.so.m30.rule.maintaincheck.CheckSaveBillRule;
import nc.bs.so.m30.rule.maintaincheck.CheckSettleOrgRepeat;
import nc.bs.so.m30.rule.maintainprocess.FillupFretexchangeRule;
import nc.bs.so.m30.rule.maintainprocess.FillupRedundanceDataRule;
import nc.bs.so.m30.rule.maintainprocess.InsertSoBalanceWhenAddNewRule;
import nc.bs.so.m30.rule.maintainprocess.ThisGatheringRule;
import nc.bs.so.m30.rule.reserve.AutoReserveRule;
import nc.bs.so.m30.rule.rewrite.LS41.RewriteForLS41BillRule;
import nc.bs.so.m30.rule.rewrite.m28.RewriteForPriceAuditBillRule;
import nc.bs.so.m30.rule.rewrite.m5805.RewriteForM5805InsertRule;
import nc.bs.so.m30.rule.rewrite.m5805.RewriteForM5805UpdateRule;
import nc.bs.so.m30.rule.rewrite.price.RewritePriceFormRule;
import nc.bs.so.pub.rule.FillBillTailInfoRuleForIns;
import nc.impl.pubapp.bd.userdef.UserDefSaveRule;
import nc.impl.pubapp.pattern.data.bill.BillInsert;
import nc.impl.pubapp.pattern.rule.ICompareRule;
import nc.impl.pubapp.pattern.rule.IRule;
import nc.impl.pubapp.pattern.rule.processer.AroundProcesser;
import nc.itf.scmpub.reference.uap.group.SysInitGroupQuery;
import nc.vo.credit.engrossmaintain.pub.action.M30EngrossAction;
import nc.vo.pubapp.pattern.log.TimeLog;
import nc.vo.pubapp.util.SetAddAuditInfoRule;
import nc.vo.scmpub.rule.SaleOrgEnableCheckRule;
import nc.vo.so.m30.entity.SaleOrderBVO;
import nc.vo.so.m30.entity.SaleOrderHVO;
import nc.vo.so.m30.entity.SaleOrderVO;

/**
 * 新增保存BP
 * 
 * @author gdsjw
 */
public class InsertSaleOrderBP {

	public SaleOrderVO[] insert(SaleOrderVO[] bills) {
		AroundProcesser<SaleOrderVO> processer = new AroundProcesser<SaleOrderVO>(
				BP30PlugInPoint.InsertBP);

		// 注入点
		TimeLog.logStart();
		this.addBeforeRule(processer);
		processer.before(bills);

		TimeLog.info("调用新增保存前BP插入点"); /* -=notranslate=- */

		TimeLog.logStart();
		BillInsert<SaleOrderVO> bo = new BillInsert<SaleOrderVO>();
		SaleOrderVO[] vos = bo.insert(bills);

		TimeLog.info("保存单据到数据库"); /* -=notranslate=- */

		// 注入点
		TimeLog.logStart();
		this.addAfterRule(processer);
		processer.after(vos);

		TimeLog.info("调用新增保存后BP插入点"); /* -=notranslate=- */

		return vos;
	}

	private void addAfterRule(AroundProcesser<SaleOrderVO> processer) {
		IRule<SaleOrderVO> rule = null;

		// 收款核销关系
		rule = new InsertSoBalanceWhenAddNewRule();
		processer.addAfterRule(rule);

		// 信用
		rule = new RenovateARByHidsEndRule(M30EngrossAction.M30Insert);
		processer.addAfterRule(rule);

		// 本次收款金额规则
		rule = new ThisGatheringRule();
		processer.addAfterRule(rule);

		// 检查单据号是否重复
		rule = new CheckUniqueBillCodeRule();
		processer.addAfterRule(rule);
		// // 回写采购订单---移到回写规则里面
		// rule = new Rewrite21InsertRule();
		// processer.addAfterRule(rule);

		// 回写来源和源头单据（回写报价单、合同、预订单、电子销售、销售订单、出库单、库存借出）
		rule = new RewriteBillInsertRule();
		processer.addAfterRule(rule);

		// 调用内部交易信息
		rule = new SaveTransferMsgRule();
		processer.addAfterRule(rule);

		// 保存价格组成
		rule = new RewritePriceFormRule();
		processer.addAfterRule(rule);

		boolean icEnable = SysInitGroupQuery.isICEnabled();
		if (icEnable) {
			// 可用量
			rule = new SaleOrderVOATPAfterRule();
			processer.addAfterRule(rule);
			// 调用自动预留
			rule = new AutoReserveRule();
			processer.addAfterRule(rule);

		}

		// 回写促销价格 jilu for 恒安限量促销
		if (SysInitGroupQuery.isPRICEEnabled()) {
			rule = new RewritePromotePriceInsertRule();
			processer.addAfterRule(rule);
		}
		// end

		/**
		 * 回写价格审批单 wangzym 2017-06-07
		 */
		rule = new RewriteForPriceAuditBillRule();
		processer.addAfterRule(rule);
		/**
		 * 回写历史代理协议 2017-08-31 王梓懿
		 */
		rule = new RewriteForLS41BillRule();
		processer.addAfterRule(rule);
		
		//add by zhangjjs 2018-3-16
		//回写进口明细单据子表累计已生成销售订单行数量 vbdef14
		rule = new RewriteForM5805InsertRule();
		processer.addAfterRule(rule);
	}

	private void addBeforeRule(AroundProcesser<SaleOrderVO> processer) {

		// 销售组织停用检查
		IRule<SaleOrderVO> rule = new SaleOrgEnableCheckRule<SaleOrderVO>();
		processer.addBeforeRule(rule);

		// 补全数据规则
		rule = new FillupRedundanceDataRule();
		processer.addBeforeRule(rule);

		// 补全退换货标记
		rule = new FillupFretexchangeRule();
		processer.addBeforeRule(rule);

		// 获取单据号规则
		rule = new AddNewBillCodeRule();
		processer.addBeforeRule(rule);

		// 数量单价金额检查规则
		rule = new CheckNumPriceMnyRule();
		processer.addBeforeRule(rule);
		// 检查表体财务组织是否一致
		rule = new CheckSettleOrgRepeat();
		processer.addBeforeRule(rule);
		// 日期检查规则
		/**wangzym 2017-06-20
		 * 根据鞍钢项目组宋国强要求，去掉关于日期的检查规则
		 * 
		 */
		/*rule = new CheckDateRule();
		processer.addBeforeRule(rule);*/
		// 订单检查规则
		rule = new CheckSaveBillRule();
		processer.addBeforeRule(rule);

		// 填充制单
		rule = new FillBillTailInfoRuleForIns<SaleOrderVO>();
		processer.addBeforeRule(rule);

		// 填充审计信息:创建人、创建时间、最后修改人、最后修改时间
		rule = new SetAddAuditInfoRule<SaleOrderVO>();
		processer.addBeforeRule(rule);

		// 信用
		rule = new RenovateARByHidsBeginRule(M30EngrossAction.M30Insert);
		processer.addBeforeRule(rule);

		boolean icEnable = SysInitGroupQuery.isICEnabled();
		if (icEnable) {
			// 可用量
			rule = new SaleOrderVOATPBeforeRule();
			processer.addBeforeRule(rule);
		}

		// 销售订单保存时物料赠品兑付范围检查
		rule = new LrgCashMarCheckRule();
		processer.addBeforeRule(rule);

		// 销售订单保存时赠品兑付费用单冲抵保存前处理
		rule = new ArsubOffsetBeforeSaveRule();
		processer.addBeforeRule(rule);

		// 检查表头和表体赠品价税合计是否一致
		rule = new CheckLrgTotalMoney();
		processer.addBeforeRule(rule);

		// 销售订单特征码选配保存
		rule = new FeatureSelectSaveRule();
		processer.addBeforeRule(rule);

		// 校验表头表体录入的自定义项是否启用
		rule = new UserDefSaveRule<SaleOrderVO>(new Class[] {
				SaleOrderHVO.class, SaleOrderBVO.class });
		processer.addBeforeRule(rule);

		// 交叉校验规则
		rule = new CrossRuleValidateRule<SaleOrderVO>();
		processer.addBeforeRule(rule);
	}

}
