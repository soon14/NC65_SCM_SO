package nc.vo.so.m30.api.rest;

import java.io.Serializable;

/**
 * @description
 * 销售订单对外查询API参数
 * 
 * @scene
 *
 * @param
 *
 *
 * @since 6.5
 * @version 2015-11-13 下午3:28:25
 * @author 刘景
 */
public class SaleOrderQueryBean implements Serializable {

  /**
   * 
   */
  private static final long serialVersionUID = -3272849931175957833L;

  /**
   *单据号
   */
  private String billcode;

  /**
   * 获取单据号
   * 
   * @return 单据号
   */
  public String getBillcode() {
    return billcode;
  }

  /**
   * 设置单据号
   * 
   * @param billcode 单据号
   */
  public void setBillcode(String billcode) {
    this.billcode = billcode;
  }

}
