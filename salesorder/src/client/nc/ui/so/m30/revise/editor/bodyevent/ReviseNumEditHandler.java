package nc.ui.so.m30.revise.editor.bodyevent;

import nc.pubitf.so.m30.pub.M30TranTypeUtil;
import nc.ui.pub.bill.BillCardPanel;
import nc.ui.pubapp.uif2app.event.card.CardBodyAfterEditEvent;
import nc.ui.so.m30.pub.SaleOrderCalculator;
import nc.ui.so.m30.pub.SaleOrderFindPriceConfig;
import nc.ui.so.m30.revise.rule.CheckNumValidityRule;
import nc.ui.so.pub.findprice.FindSalePrice;
import nc.ui.so.pub.keyvalue.CardKeyValue;
import nc.vo.pub.lang.UFBoolean;
import nc.vo.pub.lang.UFDouble;
import nc.vo.pubapp.pattern.exception.ExceptionUtils;
import nc.vo.so.m30.entity.SaleOrderBVO;
import nc.vo.so.m30.entity.SaleOrderHVO;
import nc.vo.so.m30.rule.HeadTotalCalculateRule;
import nc.vo.so.m30.rule.PieceCalRule;
import nc.vo.so.m30.rule.WeightVolumeCalRule;
import nc.vo.so.m30trantype.entity.M30TranTypeVO;
import nc.vo.so.pub.keyvalue.IKeyValue;

/**
 * 销售订单修订主数量编辑事件
 * 
 * @since 6.0
 * @version 2011-12-28 上午10:09:25
 * @author fengjb
 */
public class ReviseNumEditHandler {
  /**
   * 销售订单修订主数量编辑后事件
   * 
   * @param e
   */
  public void afterEdit(CardBodyAfterEditEvent e) {

    BillCardPanel cardPanel = e.getBillCardPanel();
    IKeyValue keyValue = new CardKeyValue(cardPanel);
    int row = e.getRow();
    UFDouble old_num = (UFDouble) e.getOldValue();
    // 检查编辑后主数量是否合法
    CheckNumValidityRule rule = new CheckNumValidityRule();
    try {
      rule.check(cardPanel, keyValue, row);
    }
    catch (Exception ex) {
      // -- 不合法则恢复原数据
      keyValue.setBodyValue(row, SaleOrderBVO.NNUM, old_num);
      // 1.计算数量
      SaleOrderCalculator calcultor = new SaleOrderCalculator(cardPanel);
      calcultor.calculate(row, SaleOrderBVO.NNUM);
      ExceptionUtils.wrappException(ex);
    }

    // 1.计算数量
    SaleOrderCalculator calcultor = new SaleOrderCalculator(cardPanel);
    calcultor.calculate(row, SaleOrderBVO.NNUM);
    // 2.询价
    M30TranTypeVO m30transvo =
        M30TranTypeUtil.getInstance().queryTranTypeVO(
            keyValue.getHeadStringValue(SaleOrderHVO.CTRANTYPEID));
    UFBoolean reviseprice = m30transvo.getBreviseprice();
    if (null != reviseprice && reviseprice.booleanValue()) {
      SaleOrderFindPriceConfig config =
          new SaleOrderFindPriceConfig(cardPanel, m30transvo);
      FindSalePrice findPrice = new FindSalePrice(cardPanel, config);
      int[] rows = new int[] {
        row
      };
      findPrice.findPriceAfterEdit(rows, SaleOrderBVO.NQTUNITNUM);
    }

    // 3.计算单位体积重量、件数
    WeightVolumeCalRule calrule = new WeightVolumeCalRule(keyValue);
    calrule.calculateWeightVolume(row);

    PieceCalRule piececalrule = new PieceCalRule(keyValue);
    piececalrule.calcPiece(row);
    // 4.修订不匹配捆绑买赠
    // MatchBindLargessRule matchrule = new MatchBindLargessRule(cardPanel);
    // matchrule.matchBindLargess(rows);
    // 5.表头合计
    HeadTotalCalculateRule totalrule = new HeadTotalCalculateRule(keyValue);
    totalrule.calculateHeadTotal();
  }
}
