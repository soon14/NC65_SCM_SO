package nc.ui.so.pub.findprice;

import nc.vo.pub.AggregatedValueObject;


public interface IFindPriceConfig {
  /**
   * 询价成功是否允许修改单价
   * 
   * @return
   */
  boolean isModifyAskSucess();

  /**
   * 询价失败是否允许修改单价
   * 
   * @return
   */
  boolean isModifyAskFail();

  /**
   * 询价失败是否提示
   * 
   * @return
   */
  boolean isShowMsgAskFail();

  /**
   * 赠品是否询价
   * 
   * @return
   */
  boolean isLargessAskPrice();

  /**
   * 询价规则
   * 
   * @return
   */
  Integer getAskPriceRule();

  /**
   * 询价成功行后续处理
   * 
   * @param sucessrows
   */
  void processAskSucessRows(int[] sucessrows, String chgkey);

  /**
   * 询价失败行后续处理
   * 
   * @param failrows
   */
  void processAskFailRows(int[] failrows);
  
  /**
   * 返回整单VO数据
   * @return
   */
  AggregatedValueObject getBillVO();
  
  
  /**
   * 是否赠品兑付
   * 
   * @return
   */
  boolean isblrgcashflag();
  
  
  /**
   * 交易类型销售模式
   * 
   * @return
   */
  int getSalemode();

}
