package nc.ui.so.pub.util;

import nc.ui.so.m30.billui.view.SaleOrderBillForm;
import nc.ui.so.m30.pub.SaleOrderFindPriceConfig;
import nc.ui.so.pub.keyvalue.CardKeyValue;
import nc.vo.pubapp.AppContext;
import nc.vo.pubapp.pattern.pub.MathTool;
import nc.vo.so.m30.entity.SaleOrderBVO;
import nc.vo.so.m30.entity.SaleOrderHVO;
import nc.vo.so.m30.util.SaleOrderClientContext;
import nc.vo.so.m30trantype.entity.M30TranTypeVO;
import nc.vo.so.pub.enumeration.AskPriceRule;
import nc.vo.so.pub.keyvalue.IKeyValue;

/**
 * 销售订单判断表体编辑性的工具类
 * @author zhangby5
 *
 */
public class BodyItemEditCheckUtil {
  
  /**
   * 判断销售订单卡片界面价格能否编辑
   * @param billform 
   * @param selectRow 当前行
   * @return
   */
  public static boolean checkPriceEditable(SaleOrderBillForm billform, int selectRow){
    IKeyValue keyValue = new CardKeyValue(billform.getBillCardPanel());
    String tranTypeCode =
        keyValue.getHeadStringValue(SaleOrderHVO.VTRANTYPECODE);
    String pk_group = AppContext.getInstance().getPkGroup();
    SaleOrderClientContext cache = billform.getM30ClientContext();
    M30TranTypeVO m30transvo = cache.getTransType(tranTypeCode, pk_group);
    SaleOrderFindPriceConfig config =
        new SaleOrderFindPriceConfig(billform.getBillCardPanel(),
            m30transvo);
    // 2.询价策略,判定是否询价
    Integer askrule = config.getAskPriceRule();
    if (AskPriceRule.ASKPRICE_NO.equalsValue(askrule)) {
      return true;
    }
    // 3.当edit=价格时，判断是否可编辑
    Boolean bmodifyaskedqt = config.isModifyAskSucess();
    Boolean bmodifyunaskedqt = config.isModifyAskFail();

    // 4.取出当前含税净价的值，判断是否询价成功
    // --当前项为空，应该是询价失败
    if (MathTool.isZero(keyValue.getBodyUFDoubleValue(selectRow, SaleOrderBVO.NQTORIGTAXNETPRC))) {
      // 询价失败是否可修改
      if (bmodifyunaskedqt) {
        return true;
      }
    }else {
      // 询价成功是否可改
      if (bmodifyaskedqt || bmodifyunaskedqt) {
        return true;
      }
    }
    return false;
    
  }

}
