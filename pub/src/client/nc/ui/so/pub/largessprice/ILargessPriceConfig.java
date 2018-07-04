package nc.ui.so.pub.largessprice;

import nc.ui.so.pub.findprice.IFindPriceConfig;

public interface ILargessPriceConfig {
  /**
   * 取价方式
   * 
   * @return
   */
  Integer getLargessPriceMode();

  /**
   * 询价配置规则
   * 
   * @return
   */
  IFindPriceConfig getFindPriceConfig();

  /**
   * 赠品取价成功行后续处理
   * 
   * @param sucessrows
   */
  void processAfterGetPrice(int[] rows, String chgkey);
}
