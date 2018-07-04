package nc.vo.so.report.profit;

import nc.vo.bd.cust.saleinfo.CustsaleVO;
import nc.vo.pub.ISuperVO;
import nc.vo.pub.IVOMeta;
import nc.vo.pub.JavaType;
import nc.vo.pubapp.pattern.model.meta.entity.view.DataViewMeta;
import nc.vo.pubapp.pattern.model.meta.entity.vo.Attribute;
import nc.vo.pubapp.pattern.pub.Constructor;
import nc.vo.so.m30.entity.SaleOrderBVO;
import nc.vo.so.m30.entity.SaleOrderHVO;

public class OrderPorfitViewMeta extends DataViewMeta {

  /**
   * 子实体属性字段名
   */
  public static final String[] BNAMES = {
    SaleOrderBVO.CSALEORDERID, SaleOrderBVO.CSALEORDERBID,
    SaleOrderBVO.CMATERIALVID, SaleOrderBVO.CMATERIALID, SaleOrderBVO.CUNITID,
    SaleOrderBVO.CSENDSTOCKORGVID, SaleOrderBVO.CSENDSTOCKORGID,
    SaleOrderBVO.CSENDSTORDOCID, SaleOrderBVO.CSETTLEORGID,
    SaleOrderBVO.CCURRENCYID, SaleOrderBVO.VBATCHCODE, SaleOrderBVO.VBDEF1,
    SaleOrderBVO.VBDEF2, SaleOrderBVO.VBDEF3, SaleOrderBVO.VBDEF4,
    SaleOrderBVO.VBDEF5, SaleOrderBVO.VBDEF6, SaleOrderBVO.VBDEF7,
    SaleOrderBVO.VBDEF8, SaleOrderBVO.VBDEF9, SaleOrderBVO.VBDEF10,
    SaleOrderBVO.VBDEF11, SaleOrderBVO.VBDEF12, SaleOrderBVO.VBDEF13,
    SaleOrderBVO.VBDEF14, SaleOrderBVO.VBDEF15, SaleOrderBVO.VBDEF16,
    SaleOrderBVO.VBDEF17, SaleOrderBVO.VBDEF18, SaleOrderBVO.VBDEF19,
    SaleOrderBVO.VBDEF20, SaleOrderBVO.BBCOSTSETTLEFLAG,
    SaleOrderBVO.BBARSETTLEFLAG
  };

  /**
   * 客户字段
   */
  public static final String[] CUSTNAMES = {
    OrderProfitViewVO.PK_CUSTCLASS, OrderProfitViewVO.PK_AREACL
  };

  /**
   * 客户销售分类
   */
  public static final String[] CUSTSALENAMES = {
    CustsaleVO.PK_CUSTSALECLASS
  };

  public static final String[] DOUBLEFIELDS = new String[] {
    OrderProfitViewVO.NMAINNUM, OrderProfitViewVO.NCOSTPRICE,
    OrderProfitViewVO.NNTAXMNY, OrderProfitViewVO.NNTAXPRICE,
    OrderProfitViewVO.NPROFITMNY, OrderProfitViewVO.NTOTALCOSTMNY,
    OrderProfitViewVO.NTOTALRECEIVNUM, OrderProfitViewVO.NTOTALRECEIVMNY,
    OrderProfitViewVO.NTOTALSETTLECOSTMNY, OrderProfitViewVO.NPROFITCATE
  };

  /**
   * 可以从订单表中取值的字段
   */
  public static final String[] DOUSELECT = new String[] {
    OrderProfitViewVO.NORDERNNUM, OrderProfitViewVO.NNETPRICE,
    OrderProfitViewVO.NTOTALESTARNUM, OrderProfitViewVO.NTOTALARNUM,
    OrderProfitViewVO.NTOTALCOSTNUM, OrderProfitViewVO.NTOTALESTARMNY,
    OrderProfitViewVO.NTOTALARMNY
  };

  /**
   * 主实体属性字段名
   */
  public static final String[] HNAMES = {
    SaleOrderHVO.PK_ORG, SaleOrderHVO.PK_ORG_V, SaleOrderHVO.CDEPTID,
    SaleOrderHVO.CDEPTVID, SaleOrderHVO.CEMPLOYEEID,
    SaleOrderHVO.CCHANNELTYPEID, SaleOrderHVO.CCUSTOMERID,
    SaleOrderHVO.CINVOICECUSTID, SaleOrderHVO.CTRANTYPEID,
    SaleOrderHVO.VBILLCODE, SaleOrderHVO.VDEF1, SaleOrderHVO.VDEF2,
    SaleOrderHVO.VDEF3, SaleOrderHVO.VDEF4, SaleOrderHVO.VDEF5,
    SaleOrderHVO.VDEF6, SaleOrderHVO.VDEF7, SaleOrderHVO.VDEF8,
    SaleOrderHVO.VDEF9, SaleOrderHVO.VDEF10, SaleOrderHVO.VDEF11,
    SaleOrderHVO.VDEF12, SaleOrderHVO.VDEF13, SaleOrderHVO.VDEF14,
    SaleOrderHVO.VDEF15, SaleOrderHVO.VDEF16, SaleOrderHVO.VDEF17,
    SaleOrderHVO.VDEF18, SaleOrderHVO.VDEF19, SaleOrderHVO.VDEF20
  };

  /**
   * 物料字段
   */
  public static final String[] MATERIALNAMES = {
    OrderProfitViewVO.PK_MARBASCLASS
  };

  /**
   * 物料销售分类
   */
  public static final String[] MATERIALSALENAMES = {
    OrderProfitViewVO.PK_MARSALECLASS
  };

  public OrderPorfitViewMeta() {
    super();
    this.addVOmeta(SaleOrderHVO.class, OrderPorfitViewMeta.HNAMES);
    this.addVOmeta(SaleOrderBVO.class, OrderPorfitViewMeta.BNAMES);

    // this.addVOmeta(MaterialVO.class, OrderPorfitViewMeta.materialNames);
    //
    // this.addVOmeta(MaterialSaleVO.class,
    // OrderPorfitViewMeta.materialsaleNames);
    //
    // this.addVOmeta(CustomerVO.class, OrderPorfitViewMeta.custNames);
    //
    // this.addVOmeta(CustomerVO.class, OrderPorfitViewMeta.custsaleNames);

    this.addExtAttributes();
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

  private void addExtAttributes() {
    for (String field : OrderPorfitViewMeta.MATERIALNAMES) {
      this.addAttribute(field, JavaType.String);
    }
    for (String field : OrderPorfitViewMeta.MATERIALSALENAMES) {
      this.addAttribute(field, JavaType.String);
    }
    for (String field : OrderPorfitViewMeta.CUSTNAMES) {
      this.addAttribute(field, JavaType.String);
    }
    for (String field : OrderPorfitViewMeta.CUSTSALENAMES) {
      this.addAttribute(field, JavaType.String);
    }

    for (String field : OrderPorfitViewMeta.DOUSELECT) {
      this.addAttribute(field, JavaType.UFDouble);
    }

    for (String field : OrderPorfitViewMeta.DOUBLEFIELDS) {
      this.addAttribute(field, JavaType.UFDouble);
    }
  }

  private void addVOmeta(Class<? extends ISuperVO> clazz, String[] hnames) {
    ISuperVO vo = Constructor.construct(clazz);
    IVOMeta meta = vo.getMetaData();
    for (String key : hnames) {
      if (null == meta) {
        this.addAttribute(key, JavaType.String);
      }
      else {
        Attribute vometa = (Attribute) meta.getAttribute(key);
        Attribute attribute =
            new Attribute(vometa.getName(), vometa.getColumn(), null);
        attribute.setSerializable(true);
        attribute.setJavaType(vometa.getJavaType());
        this.add(attribute);
      }
    }
  }
}
