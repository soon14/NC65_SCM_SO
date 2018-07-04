package nc.pubitf.so.m30.so.m33;

/**
 * 结算发票查询销售订单view信息返回结果VO
 * 
 * @since 6.0
 * @version 2011-1-28 下午04:15:48
 * @author 刘志伟
 */
public class For33SquareInvVO {

  // 销售订单附表主键
  private String csaleorderbid;

  // 销售合同订单号
  private String vctcode;

  public String getCsaleorderbid() {
    return this.csaleorderbid;
  }

  public void setCsaleorderbid(String csaleorderbid) {
    this.csaleorderbid = csaleorderbid;
  }

  public String getVctcode() {
    return this.vctcode;
  }

  public void setVctcode(String vctcode) {
    this.vctcode = vctcode;
  }

}
