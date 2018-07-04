package nc.ui.so.m30.billui.editor.bodyevent;

import java.util.ArrayList;
import java.util.List;

import nc.ui.pub.bill.BillCardPanel;
import nc.ui.pubapp.AppUiContext;
import nc.ui.pubapp.uif2app.event.card.CardBodyAfterEditEvent;
import nc.ui.so.m30.billui.rule.MatchBindLargessRule;
import nc.ui.so.m30.billui.view.SaleOrderBillForm;
import nc.ui.so.m30.pub.SaleOrderCalculator;
import nc.ui.so.m30.pub.SaleOrderFindPriceConfig;
import nc.ui.so.pub.findprice.FindSalePrice;
import nc.ui.so.pub.keyvalue.CardKeyValue;
import nc.ui.so.pub.util.BodyEditEventUtil;
import nc.vo.pub.lang.UFDouble;
import nc.vo.pubapp.pattern.pub.MathTool;
import nc.vo.so.m30.entity.SaleOrderBVO;
import nc.vo.so.m30.entity.SaleOrderHVO;
import nc.vo.so.m30.rule.HeadTotalCalculateRule;
import nc.vo.so.m30.rule.PieceCalRule;
import nc.vo.so.m30.rule.WeightVolumeCalRule;
import nc.vo.so.m30.util.SaleOrderClientContext;
import nc.vo.so.m30trantype.entity.M30TranTypeVO;
import nc.vo.so.pub.keyvalue.IKeyValue;

/**
 * 销售订单业务数量编辑事件
 * 
 * @since 6.0
 * @version 2011-6-9 下午03:27:32
 * @author fengjb
 */
public class AstNumEditHandler {

  public void afterEdit(CardBodyAfterEditEvent e, SaleOrderBillForm billform) {

    int[] rows = BodyEditEventUtil.getInstance().getAfterEditRow(e);

    BillCardPanel cardPanel = e.getBillCardPanel();
    IKeyValue keyValue = new CardKeyValue(cardPanel);
    String tranTypeCode =
        keyValue.getHeadStringValue(SaleOrderHVO.VTRANTYPECODE);
    String pk_group = AppUiContext.getInstance().getPkGroup();
    SaleOrderClientContext cache = billform.getM30ClientContext();
    M30TranTypeVO m30transvo = cache.getTransType(tranTypeCode, pk_group);

    UFDouble[] old_nums = new UFDouble[rows.length];
    for (int i = 0; i < rows.length; i++) {
      old_nums[i] = keyValue.getBodyUFDoubleValue(rows[i], SaleOrderBVO.NNUM);
    }

    // 1.计算数量
    SaleOrderCalculator calcultor = new SaleOrderCalculator(cardPanel);
    calcultor.setTranTypeVO(m30transvo);
    calcultor.calculate(rows, SaleOrderBVO.NASTNUM);

    List<Integer> listchgrow = new ArrayList<Integer>();
    for (int i = 0; i < rows.length; i++) {
      UFDouble new_num =
          keyValue.getBodyUFDoubleValue(rows[i], SaleOrderBVO.NNUM);
      if (!MathTool.equals(old_nums[i], new_num)) {
        listchgrow.add(rows[i]);
      }
    }
    // 主数量未变化
    int chgsize = listchgrow.size();
    if (chgsize == 0) {
      return;
    }

    int[] chgrows = new int[chgsize];
    for (int i = 0; i < chgsize; i++) {
      chgrows[i] = listchgrow.get(i);
    }
    // 2.询价
    SaleOrderFindPriceConfig config =
        new SaleOrderFindPriceConfig(cardPanel, m30transvo);
    FindSalePrice findPrice = new FindSalePrice(cardPanel, config);
    findPrice.findPriceAfterEdit(chgrows, SaleOrderBVO.NQTUNITNUM);

    // 3.计算单位体积重量、件数
    WeightVolumeCalRule calrule = new WeightVolumeCalRule(keyValue);
    calrule.calculateWeightVolume(chgrows);

    PieceCalRule piececalrule = new PieceCalRule(keyValue);
    piececalrule.calcPiece(chgrows);

    // 4.匹配捆绑买赠
    MatchBindLargessRule matchrule = new MatchBindLargessRule(cardPanel, m30transvo);
    matchrule.matchBindLargess(chgrows);

    // 5.表头合计
    HeadTotalCalculateRule totalrule = new HeadTotalCalculateRule(keyValue);
    totalrule.calculateHeadTotal();
  }
}
