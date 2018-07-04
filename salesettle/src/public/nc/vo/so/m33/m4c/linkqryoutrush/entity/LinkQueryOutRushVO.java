package nc.vo.so.m33.m4c.linkqryoutrush.entity;

import java.io.Serializable;

public class LinkQueryOutRushVO implements Serializable {

  private static final long serialVersionUID = -4703256727568926921L;

  /**
   * 销售出库单物料VID
   */
  private String cmaterialvid;

  /**
   * 销售出库单行ID
   */
  private String outbid;

  /**
   * 销售出库单行号
   */
  private String outRowNo;

  /**
   * 
   * @return 销售出库单物料VID
   */
  public String getCmaterialvid() {
    return this.cmaterialvid;
  }

  /**
   * 
   * @return 销售出库单行ID
   */
  public String getOutbid() {
    return this.outbid;
  }

  /**
   * 
   * @return 销售出库单行号
   */
  public String getOutRowNo() {
    return this.outRowNo;
  }

  /**
   * 
   * @param cmaterialvid 销售出库单物料VID
   */
  public void setCmaterialvid(String cmaterialvid) {
    this.cmaterialvid = cmaterialvid;
  }

  /**
   * 
   * @param outbid 销售出库单行ID
   */
  public void setOutbid(String outbid) {
    this.outbid = outbid;
  }

  /**
   * 
   * @param outRowNo 销售出库单行号
   */
  public void setOutRowNo(String outRowNo) {
    this.outRowNo = outRowNo;
  }

}
