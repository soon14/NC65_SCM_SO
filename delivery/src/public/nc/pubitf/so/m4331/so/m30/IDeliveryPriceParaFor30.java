package nc.pubitf.so.m4331.so.m30;

import nc.vo.pub.lang.UFDouble;

/**
 * 销售订单修订时，更改发货单的价格
 * 
 * @since 6.0
 * @version 2011-5-27 下午01:27:20
 * @author 祝会征
 */
public interface IDeliveryPriceParaFor30 {
  /**
   * 获得销售订单表体id
   * 
   * @return
   */
  String getHid();

  /**
   * 主单位本币无税净价
   * 
   * @return
   */
  UFDouble getNnetprice();

  /**
   * 主单位原币无税净价
   * 
   * @return
   */
  UFDouble getNorignetprice();

  /**
   * 主单位原币无税单价
   * 
   * @return
   */
  UFDouble getNorigprice();

  /**
   * 主单位原币含税净价
   * 
   * @return
   */
  UFDouble getNorigtaxnetprice();

  /**
   * 主单位原币含税单价
   * 
   * @return
   */
  UFDouble getNorigtaxprice();

  /**
   * 主单位本币无税单价
   * 
   * @return
   */
  UFDouble getNprice();

  /**
   * 报价单位本币无税净价
   * 
   * @return
   */
  UFDouble getNqtnetprice();

  /**
   * 报价单位原币无税净价
   * 
   * @return
   */
  UFDouble getNqtorignetprice();

  /**
   * 报价单位原币无税单价
   * 
   * @return
   */
  UFDouble getNqtorigprice();

  /**
   * 报价单位原币含税净价
   * 
   * @return
   */
  UFDouble getNqtorigtaxnetprc();

  /**
   * 报价单位原币含税单价
   * 
   * @return
   */
  UFDouble getNqtorigtaxprice();

  /**
   * 报价单位本币无税单价
   * 
   * @return
   */
  UFDouble getNqtprice();

  /**
   * 报价单位本币含税净价
   * 
   * @return
   */
  UFDouble getNqttaxnetprice();

  /**
   * 报价单位本币含税单价
   * 
   * @return
   */
  UFDouble getNqttaxprice();

  /**
   * 主单位本币含税净价
   * 
   * @return
   */
  UFDouble getNtaxnetprice();

  /**
   * 主单位本币含税单价
   * 
   * @return
   */
  UFDouble getNtaxprice();
  
  /**
   * 特征码
   * @return
   */
  String getCmffileid();
}
