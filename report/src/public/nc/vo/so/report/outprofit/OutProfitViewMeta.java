package nc.vo.so.report.outprofit;

import nc.vo.ic.m4c.entity.SaleOutBodyVO;
import nc.vo.ic.m4c.entity.SaleOutHeadVO;
import nc.vo.pub.JavaType;
import nc.vo.pubapp.pattern.model.meta.entity.view.DataViewMeta;
import nc.vo.pubapp.pattern.model.meta.entity.vo.Attribute;

/**
 * 销售出库毛利分析视图META
 * 
 * @since 6.3
 * @version 2012-7-27 上午8:53:59
 * @author 梁吉明
 */

public class OutProfitViewMeta extends DataViewMeta {

  /**
   * 销售出库单主实体属性字段名
   */
  public static final String[] SALEOUT_HKEYS = new String[] {
    OutProfitViewVO.CGENERALHID, OutProfitViewVO.CSALEORGOID,
    OutProfitViewVO.CDPTID, OutProfitViewVO.CBIZID,
    OutProfitViewVO.CCUSTOMERID, OutProfitViewVO.VBILLCODE,
    OutProfitViewVO.CTRANTYPEID, OutProfitViewVO.VDEF1, OutProfitViewVO.VDEF2,
    OutProfitViewVO.VDEF3, OutProfitViewVO.VDEF4, OutProfitViewVO.VDEF5,
    OutProfitViewVO.VDEF6, OutProfitViewVO.VDEF7, OutProfitViewVO.VDEF8,
    OutProfitViewVO.VDEF9, OutProfitViewVO.VDEF10, OutProfitViewVO.VDEF11,
    OutProfitViewVO.VDEF12, OutProfitViewVO.VDEF13, OutProfitViewVO.VDEF14,
    OutProfitViewVO.VDEF15, OutProfitViewVO.VDEF16, OutProfitViewVO.VDEF17,
    OutProfitViewVO.VDEF18, OutProfitViewVO.VDEF19, OutProfitViewVO.VDEF20
  };

  /**
   * 销售出库单子实体属性字段名
   */
  public static final String[] SALEOUT_BKEYS = {
    OutProfitViewVO.CGENERALBID, OutProfitViewVO.CMATERIALOID,
    OutProfitViewVO.CMATERIALVID, OutProfitViewVO.CUNITID,
    OutProfitViewVO.VBATCHCODE, OutProfitViewVO.CORIGCURRENCYID,
    OutProfitViewVO.NNUM, OutProfitViewVO.NQTORIGNETPRICE,
    OutProfitViewVO.PK_ORG, OutProfitViewVO.CBODYWAREHOUSEID,
    OutProfitViewVO.CFANACEORGOID, OutProfitViewVO.VBDEF1,
    OutProfitViewVO.VBDEF2, OutProfitViewVO.VBDEF3, OutProfitViewVO.VBDEF4,
    OutProfitViewVO.VBDEF5, OutProfitViewVO.VBDEF6, OutProfitViewVO.VBDEF7,
    OutProfitViewVO.VBDEF8, OutProfitViewVO.VBDEF9, OutProfitViewVO.VBDEF10,
    OutProfitViewVO.VBDEF11, OutProfitViewVO.VBDEF12, OutProfitViewVO.VBDEF13,
    OutProfitViewVO.VBDEF14, OutProfitViewVO.VBDEF15, OutProfitViewVO.VBDEF16,
    OutProfitViewVO.VBDEF17, OutProfitViewVO.VBDEF18, OutProfitViewVO.VBDEF19,
    OutProfitViewVO.VBDEF20, OutProfitViewVO.FLARGESS
  };

  /**
   * 扩展String字段
   * 临时表返回的String字段
   */
  public static final String[] EXTEND_STRKEYS = {

    OutProfitViewVO.PK_CUSTCLASS, OutProfitViewVO.PK_CUSTSALECLASS,
    OutProfitViewVO.PK_AREACL, OutProfitViewVO.PK_MARBASCLASS,
    OutProfitViewVO.PK_MARSALECLASS, OutProfitViewVO.CCHANNELTYPEID,
  };

  /**
   * 扩展UFDouble字段
   * 临时表返回的UFDouble字段
   */
  public static final String[] EXTEND_UFKEYS = {
    OutProfitViewVO.NMAINNUM, OutProfitViewVO.NSHOULDRECEIVNUM,
    OutProfitViewVO.NTOTALRECEIVMNY, OutProfitViewVO.NNOTAXMNY,
    OutProfitViewVO.NNOTAXPRICE, OutProfitViewVO.NCOSTNUM,
    OutProfitViewVO.NCOSTMNY, OutProfitViewVO.NTOTALCOSTMNY,
    OutProfitViewVO.NCOSTPRICE, OutProfitViewVO.NPROFITMNY,
    OutProfitViewVO.NPROFITRATE, OutProfitViewVO.NCOST,
    OutProfitViewVO.NOCOSTNUM

  };

  /**
   * 临时表返回的出库单字段
   */
  public static final String[] TMPTABLE_OUTKEYS = new String[] {
    OutProfitViewVO.CSALEORGOID, OutProfitViewVO.CDPTID,
    OutProfitViewVO.CBIZID, OutProfitViewVO.CCUSTOMERID,
    OutProfitViewVO.CMATERIALOID, OutProfitViewVO.CMATERIALVID,
    OutProfitViewVO.CUNITID, OutProfitViewVO.CTRANTYPEID,
    OutProfitViewVO.VBILLCODE, OutProfitViewVO.VBATCHCODE,
    OutProfitViewVO.CORIGCURRENCYID, OutProfitViewVO.NNUM
  };

  /**
   * 临时表返回的出库单的自定义字段
   */
  public static final String[] TMPTABLE_OUTVFKEYS = new String[] {
    OutProfitViewVO.VDEF1, OutProfitViewVO.VDEF2, OutProfitViewVO.VDEF3,
    OutProfitViewVO.VDEF4, OutProfitViewVO.VDEF5, OutProfitViewVO.VDEF6,
    OutProfitViewVO.VDEF7, OutProfitViewVO.VDEF8, OutProfitViewVO.VDEF9,
    OutProfitViewVO.VDEF10, OutProfitViewVO.VDEF11, OutProfitViewVO.VDEF12,
    OutProfitViewVO.VDEF13, OutProfitViewVO.VDEF14, OutProfitViewVO.VDEF15,
    OutProfitViewVO.VDEF16, OutProfitViewVO.VDEF17, OutProfitViewVO.VDEF18,
    OutProfitViewVO.VDEF19, OutProfitViewVO.VDEF20, OutProfitViewVO.VBDEF1,
    OutProfitViewVO.VBDEF2, OutProfitViewVO.VBDEF3, OutProfitViewVO.VBDEF4,
    OutProfitViewVO.VBDEF5, OutProfitViewVO.VBDEF6, OutProfitViewVO.VBDEF7,
    OutProfitViewVO.VBDEF8, OutProfitViewVO.VBDEF9, OutProfitViewVO.VBDEF10,
    OutProfitViewVO.VBDEF11, OutProfitViewVO.VBDEF12, OutProfitViewVO.VBDEF13,
    OutProfitViewVO.VBDEF14, OutProfitViewVO.VBDEF15, OutProfitViewVO.VBDEF16,
    OutProfitViewVO.VBDEF17, OutProfitViewVO.VBDEF18, OutProfitViewVO.VBDEF19,
    OutProfitViewVO.VBDEF20
  };

  /**
   *
   */
  public OutProfitViewMeta() {
    // 通过实体VO的Class添加实体元数据的指定属性到视图元数据中
    super.add(SaleOutHeadVO.class, OutProfitViewMeta.SALEOUT_HKEYS);
    this.add(SaleOutBodyVO.class, OutProfitViewMeta.SALEOUT_BKEYS);
    // 指定实体元数据之间的关联关系
    this.addRelation(SaleOutHeadVO.class, OutProfitViewVO.CGENERALHID,
        SaleOutBodyVO.class, OutProfitViewVO.CGENERALHID);
    this.addExtAttributes();
  }

  private void addExtAttributes() {
    for (String field : OutProfitViewMeta.EXTEND_STRKEYS) {
      this.addAttribute(field, JavaType.String);
    }
    for (String field : OutProfitViewMeta.EXTEND_UFKEYS) {
      this.addAttribute(field, JavaType.UFDouble);
    }
  }

  private void addAttribute(String itemkey, JavaType type) {
    Attribute attribute = new Attribute(itemkey, null, null);
    attribute.setJavaType(type);
    attribute.setCustom(false);
    attribute.setStatic(false);
    attribute.setPersistence(false);
    attribute.setSerializable(true);
    this.add(attribute);
  }

}
