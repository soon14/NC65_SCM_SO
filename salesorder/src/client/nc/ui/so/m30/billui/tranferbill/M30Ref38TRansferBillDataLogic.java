package nc.ui.so.m30.billui.tranferbill;

import nc.ui.pub.bill.BillCardPanel;
import nc.ui.pubapp.billref.dest.DefaultBillDataLogic;
import nc.ui.so.m30.billui.rule.AssociateConstractRule;
import nc.ui.so.m30.billui.rule.MatchLargessRule;
import nc.ui.so.m30.billui.view.SaleOrderBillForm;
import nc.ui.so.pub.keyvalue.CardKeyValue;
import nc.vo.pubapp.AppContext;
import nc.vo.so.m30.entity.SaleOrderBVO;
import nc.vo.so.m30.entity.SaleOrderHVO;
import nc.vo.so.m30.rule.HeadTotalCalculateRule;
import nc.vo.so.m30.util.SaleOrderClientContext;
import nc.vo.so.m30trantype.entity.M30TranTypeVO;
import nc.vo.so.pub.keyvalue.IKeyValue;
import nc.vo.so.pub.rule.BodyValueRowRule;
import nc.vo.so.pub.rule.SOProfitCenterValueRule;

/**
 * 销售订单参照预订单转单后数据处理类
 * 
 * @since 6.0
 * @version 2011-7-4 上午11:48:31
 * @author fengjb
 */
public class M30Ref38TRansferBillDataLogic extends DefaultBillDataLogic {

  @Override
  public void doTransferAddLogic(Object selectedData) {

    // 把数据设置在界面上
    super.doTransferAddLogic(selectedData);

    // 基于界面卡片填充值运算
    SaleOrderBillForm billform = (SaleOrderBillForm) this.getBillForm();
    BillCardPanel cardPanel = billform.getBillCardPanel();
    IKeyValue keyValue = new CardKeyValue(cardPanel);

    BodyValueRowRule bodycouuitl = new BodyValueRowRule(keyValue);
    int[] rows = bodycouuitl.getMarNotNullRows();
    // 1.V63 冯加滨、陈恩宇、吴玲 销售订单复制需要关联合同
    String tranTypeCode =
        keyValue.getHeadStringValue(SaleOrderHVO.VTRANTYPECODE);
    String pk_group = AppContext.getInstance().getPkGroup();
    SaleOrderClientContext cache = billform.getM30ClientContext();
    M30TranTypeVO m30transvo = cache.getTransType(tranTypeCode, pk_group);
    AssociateConstractRule ctrule =
        new AssociateConstractRule(cardPanel, m30transvo);
    ctrule.associateCT(rows);
    // 2.买赠匹配
    MatchLargessRule matchlarrule = new MatchLargessRule(cardPanel, m30transvo);
    matchlarrule.matchLargess(rows);

    // 3.表头合计
    HeadTotalCalculateRule totalrule = new HeadTotalCalculateRule(keyValue);
    totalrule.calculateHeadTotal();

  }

}
