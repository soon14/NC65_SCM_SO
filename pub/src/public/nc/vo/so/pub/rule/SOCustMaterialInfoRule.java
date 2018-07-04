package nc.vo.so.pub.rule;

import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Map.Entry;
import java.util.Set;

import nc.vo.pub.BusinessException;
import nc.vo.pubapp.pattern.exception.ExceptionUtils;
import nc.vo.pubapp.pattern.pub.PubAppTool;
import nc.vo.so.pub.SOItemKey;
import nc.vo.so.pub.keyvalue.IKeyValue;
import nc.vo.so.salequotation.entity.SalequotationBVO;
import nc.vo.so.salequotation.entity.SalequotationHVO;
import nc.vo.uapbd.custmaterial.CustMaterialVO;
import nc.vo.uapbd.custmaterial.CustMaterialView;

import nc.pubitf.uapbd.ICustMaterialPubService;

import nc.bs.framework.common.NCLocator;

/**
 * 销售订单、销售报价单客户物料码设置信息规则
 * 
 * @since 6.3
 * @version 2012-12-11 18:33:32
 * @author liangjm
 */
public class SOCustMaterialInfoRule {

  private IKeyValue keyValue;

  /**
   * 构造方法
   * 
   * @param keyValue
   */

  public SOCustMaterialInfoRule(IKeyValue keyValue) {
    this.keyValue = keyValue;
  }

  /**
   * 销售订单根据客户和物料设置客户物料码
   * 
   * @param rows
   */
  public void setCustMaterial(int... rows) {
    String pk_org = this.keyValue.getHeadStringValue(SOItemKey.PK_ORG);
    String customer = this.keyValue.getHeadStringValue(SOItemKey.CCUSTOMERID);
    CustMaterialView[] custMaterialViews = this.getCustMaterialViews(rows);
    Map<CustMaterialView, CustMaterialVO> custmaterialmap =
        this.queryCustMaterials(pk_org, customer, custMaterialViews);
    this.setNewCustMaterial(custmaterialmap, rows);
  }

  /**
   * 销售报价单根据客户和物料设置客户物料码
   * (因为销售报价单的VO字段命名与SOItemKey不符，所以拿出来单独处理)
   * 
   * @param rows
   */
  public void set4310CustMaterial(int... rows) {

    String pk_org = this.keyValue.getHeadStringValue(SOItemKey.PK_ORG);
    String customer =
        this.keyValue.getHeadStringValue(SalequotationHVO.PK_CUSTOMER);
    CustMaterialView[] custMaterialViews = this.get4310CustMaterialViews(rows);
    Map<CustMaterialView, CustMaterialVO> custmaterialmap =
        this.queryCustMaterials(pk_org, customer, custMaterialViews);

    this.setNew4310CustMaterial(custmaterialmap, rows);

  }

  private void setNew4310CustMaterial(
      Map<CustMaterialView, CustMaterialVO> custmaterialmap, int... rows) {
    Map<String, CustMaterialVO> custmaterial =
        new HashMap<String, CustMaterialVO>();
    if (null != custmaterialmap && custmaterialmap.size() != 0) {
      for (Entry<CustMaterialView, CustMaterialVO> entry : custmaterialmap
          .entrySet()) {
        custmaterial.put(entry.getKey().toString(), entry.getValue());
      }
    }
    for (int row : rows) {
      CustMaterialView custmarview = this.get4310CustMaterialView(row);
      if (0 != custmaterial.size()
          && custmaterial.containsKey(custmarview.toString())) {
        this.keyValue.setBodyValue(row, SOItemKey.CCUSTMATERIALID, custmaterial
            .get(custmarview.toString()).getPk_custmaterial());
      }
      else {
        this.keyValue.setBodyValue(row, SOItemKey.CCUSTMATERIALID, null);
        this.keyValue.setBodyValue(row, SOItemKey.CCUSTMATERIALID + ".name",
            null);
      }
    }
  }

  private CustMaterialView[] get4310CustMaterialViews(int... rows) {
    Set<CustMaterialView> custMaterialViewset = new HashSet<CustMaterialView>();
    for (int row : rows) {
      CustMaterialView custmarview = this.get4310CustMaterialView(row);
      custMaterialViewset.add(custmarview);
    }
    CustMaterialView[] custMaterialViews =
        custMaterialViewset.toArray(new CustMaterialView[custMaterialViewset
            .size()]);
    return custMaterialViews;
  }

  private CustMaterialView get4310CustMaterialView(int row) {
    String material =
        this.keyValue.getBodyStringValue(row, SalequotationBVO.PK_MATERIAL_V);
    String cproductorid =
        this.keyValue.getBodyStringValue(row, SalequotationBVO.PK_PRODUCTOR);
    String cvendorid =
        this.keyValue.getBodyStringValue(row, SalequotationBVO.PK_SUPPLIER);
    String vfree1 = this.keyValue.getBodyStringValue(row, SOItemKey.VFREE1);
    String vfree2 = this.keyValue.getBodyStringValue(row, SOItemKey.VFREE2);
    String vfree3 = this.keyValue.getBodyStringValue(row, SOItemKey.VFREE3);
    String vfree4 = this.keyValue.getBodyStringValue(row, SOItemKey.VFREE4);
    String vfree5 = this.keyValue.getBodyStringValue(row, SOItemKey.VFREE5);
    String vfree6 = this.keyValue.getBodyStringValue(row, SOItemKey.VFREE6);
    String vfree7 = this.keyValue.getBodyStringValue(row, SOItemKey.VFREE7);
    String vfree8 = this.keyValue.getBodyStringValue(row, SOItemKey.VFREE8);
    String vfree9 = this.keyValue.getBodyStringValue(row, SOItemKey.VFREE9);
    String vfree10 = this.keyValue.getBodyStringValue(row, SOItemKey.VFREE10);
    CustMaterialView custmarview = new CustMaterialView();
    custmarview.setPk_material(material);
    custmarview.setVfree3(cvendorid);
    custmarview.setVfree4(cproductorid);
    custmarview.setVfree6(vfree1);
    custmarview.setVfree7(vfree2);
    custmarview.setVfree8(vfree3);
    custmarview.setVfree9(vfree4);
    custmarview.setVfree10(vfree5);
    custmarview.setVfree11(vfree6);
    custmarview.setVfree12(vfree7);
    custmarview.setVfree13(vfree8);
    custmarview.setVfree14(vfree9);
    custmarview.setVfree15(vfree10);
    return custmarview;

  }

  private void setNewCustMaterial(
      Map<CustMaterialView, CustMaterialVO> custmaterialmap, int... rows) {
    Map<String, CustMaterialVO> custmaterial =
        new HashMap<String, CustMaterialVO>();
    if (null != custmaterialmap && custmaterialmap.size() != 0) {
      for (Entry<CustMaterialView, CustMaterialVO> entry : custmaterialmap
          .entrySet()) {
        custmaterial.put(entry.getKey().toString(), entry.getValue());
      }
    }
    for (int row : rows) {
      CustMaterialView custmarview = this.getCustMaterialView(row);
      if (0 != custmaterial.size()
          && custmaterial.containsKey(custmarview.toString())) {
        this.keyValue.setBodyValue(row, SOItemKey.CCUSTMATERIALID, custmaterial
            .get(custmarview.toString()).getPk_custmaterial());
      }
      else {
        this.keyValue.setBodyValue(row, SOItemKey.CCUSTMATERIALID, null);
        this.keyValue.setBodyValue(row, SOItemKey.CCUSTMATERIALID + ".name",
            null);
      }
    }
  }

  private CustMaterialView[] getCustMaterialViews(int... rows) {
    Set<CustMaterialView> custMaterialViewset = new HashSet<CustMaterialView>();
    for (int row : rows) {
      CustMaterialView custmarview = this.getCustMaterialView(row);
      custMaterialViewset.add(custmarview);
    }
    CustMaterialView[] custMaterialViews =
        custMaterialViewset.toArray(new CustMaterialView[custMaterialViewset
            .size()]);
    return custMaterialViews;
  }

  private CustMaterialView getCustMaterialView(int row) {
    String material =
        this.keyValue.getBodyStringValue(row, SOItemKey.CMATERIALVID);
    String cproductorid =
        this.keyValue.getBodyStringValue(row, SOItemKey.CPRODUCTORID);
    String cvendorid =
        this.keyValue.getBodyStringValue(row, SOItemKey.CVENDORID);
    String vfree1 = this.keyValue.getBodyStringValue(row, SOItemKey.VFREE1);
    String vfree2 = this.keyValue.getBodyStringValue(row, SOItemKey.VFREE2);
    String vfree3 = this.keyValue.getBodyStringValue(row, SOItemKey.VFREE3);
    String vfree4 = this.keyValue.getBodyStringValue(row, SOItemKey.VFREE4);
    String vfree5 = this.keyValue.getBodyStringValue(row, SOItemKey.VFREE5);
    String vfree6 = this.keyValue.getBodyStringValue(row, SOItemKey.VFREE6);
    String vfree7 = this.keyValue.getBodyStringValue(row, SOItemKey.VFREE7);
    String vfree8 = this.keyValue.getBodyStringValue(row, SOItemKey.VFREE8);
    String vfree9 = this.keyValue.getBodyStringValue(row, SOItemKey.VFREE9);
    String vfree10 = this.keyValue.getBodyStringValue(row, SOItemKey.VFREE10);
    CustMaterialView custmarview = new CustMaterialView();
    custmarview.setPk_material(material);
    custmarview.setVfree3(cvendorid);
    custmarview.setVfree4(cproductorid);
    custmarview.setVfree6(vfree1);
    custmarview.setVfree7(vfree2);
    custmarview.setVfree8(vfree3);
    custmarview.setVfree9(vfree4);
    custmarview.setVfree10(vfree5);
    custmarview.setVfree11(vfree6);
    custmarview.setVfree12(vfree7);
    custmarview.setVfree13(vfree8);
    custmarview.setVfree14(vfree9);
    custmarview.setVfree15(vfree10);
    return custmarview;
  }

  private Map<CustMaterialView, CustMaterialVO> queryCustMaterials(
      String pk_org, String customer, CustMaterialView[] custMaterialViews) {
    ICustMaterialPubService custmarsrv =
        NCLocator.getInstance().lookup(ICustMaterialPubService.class);
    Map<CustMaterialView, CustMaterialVO> custmaterialmap = null;
    try {
      custmaterialmap =
          custmarsrv.queryCustMaterials(pk_org, customer, custMaterialViews);
    }
    catch (BusinessException e) {
      ExceptionUtils.wrappException(e);
    }
    return custmaterialmap;
  }

  /**
   * 
   * 
   * @param rows
   */
  public void setMaterials(int... rows) {
    String[] pk_custmaterials = this.getPk_custmaterials(rows);
    Map<String, CustMaterialVO> custmaterialmap =
        this.queryMaterials(pk_custmaterials);
    this.setNewMaterials(custmaterialmap, rows);

  }

  private void setNewMaterials(Map<String, CustMaterialVO> custmaterialmap,
      int... rows) {
    for (int row : rows) {
      String pk_custmaterial =
          this.keyValue.getBodyStringValue(row, SOItemKey.CCUSTMATERIALID);
      if (null != custmaterialmap && 0 != custmaterialmap.size()
          && custmaterialmap.containsKey(pk_custmaterial)) {
        CustMaterialVO custmarvo = custmaterialmap.get(pk_custmaterial);
        this.keyValue.setBodyValue(row, SOItemKey.CVENDORID,
            custmarvo.getVfree3());
        this.keyValue.setBodyValue(row, SOItemKey.CPRODUCTORID,
            custmarvo.getVfree4());
        // 自由辅助属性1
        this.keyValue
            .setBodyValue(row, SOItemKey.VFREE1, custmarvo.getVfree6());
        // 自由辅助属性2
        this.keyValue
            .setBodyValue(row, SOItemKey.VFREE2, custmarvo.getVfree7());
        // 自由辅助属性3
        this.keyValue
            .setBodyValue(row, SOItemKey.VFREE3, custmarvo.getVfree8());
        // 自由辅助属性4
        this.keyValue
            .setBodyValue(row, SOItemKey.VFREE4, custmarvo.getVfree9());
        // 自由辅助属性5
        this.keyValue.setBodyValue(row, SOItemKey.VFREE5,
            custmarvo.getVfree10());
        // 自由辅助属性6
        this.keyValue.setBodyValue(row, SOItemKey.VFREE6,
            custmarvo.getVfree11());
        // 自由辅助属性7
        this.keyValue.setBodyValue(row, SOItemKey.VFREE7,
            custmarvo.getVfree12());
        // 自由辅助属性8
        this.keyValue.setBodyValue(row, SOItemKey.VFREE8,
            custmarvo.getVfree13());
        // 自由辅助属性9
        this.keyValue.setBodyValue(row, SOItemKey.VFREE9,
            custmarvo.getVfree14());
        // 自由辅助属性10
        this.keyValue.setBodyValue(row, SOItemKey.VFREE10,
            custmarvo.getVfree15());
      }
    }
  }

  private Map<String, CustMaterialVO> queryMaterials(String[] pk_custmaterials) {
    ICustMaterialPubService custmarsrv =
        NCLocator.getInstance().lookup(ICustMaterialPubService.class);
    Map<String, CustMaterialVO> custmaterialmap = null;
    try {
      custmaterialmap = custmarsrv.queryVOsByID(pk_custmaterials);
    }
    catch (BusinessException ex) {
      ExceptionUtils.wrappException(ex);
    }
    return custmaterialmap;
  }

  private String[] getPk_custmaterials(int... rows) {
    Set<String> custmaterialset = new HashSet<String>();
    for (int row : rows) {
      String custmaterial =
          this.keyValue.getBodyStringValue(row, SOItemKey.CCUSTMATERIALID);
      if (!PubAppTool.isNull(custmaterial)) {
        custmaterialset.add(custmaterial);
      }
    }
    String[] pk_custmaterials =
        custmaterialset.toArray(new String[custmaterialset.size()]);
    return pk_custmaterials;
  }

  /**
   * 
   * 
   * @param rows
   */
  public void set4310Materials(int... rows) {
    String[] pk_custmaterials = this.getPk_custmaterials(rows);
    Map<String, CustMaterialVO> custmaterialmap =
        this.queryMaterials(pk_custmaterials);
    this.set4331NewMaterials(custmaterialmap, rows);
  }

  private void set4331NewMaterials(Map<String, CustMaterialVO> custmaterialmap,
      int... rows) {
    for (int row : rows) {
      String pk_custmaterial =
          this.keyValue.getBodyStringValue(row, SOItemKey.CCUSTMATERIALID);
      if (0 != custmaterialmap.size()
          && custmaterialmap.containsKey(pk_custmaterial)) {
        CustMaterialVO custmarvo = custmaterialmap.get(pk_custmaterial);
        this.keyValue.setBodyValue(row, SalequotationBVO.PK_SUPPLIER,
            custmarvo.getVfree3());
        this.keyValue.setBodyValue(row, SalequotationBVO.PK_PRODUCTOR,
            custmarvo.getVfree4());
        // 自由辅助属性1
        this.keyValue
            .setBodyValue(row, SOItemKey.VFREE1, custmarvo.getVfree6());
        // 自由辅助属性2
        this.keyValue
            .setBodyValue(row, SOItemKey.VFREE2, custmarvo.getVfree7());
        // 自由辅助属性3
        this.keyValue
            .setBodyValue(row, SOItemKey.VFREE3, custmarvo.getVfree8());
        // 自由辅助属性4
        this.keyValue
            .setBodyValue(row, SOItemKey.VFREE4, custmarvo.getVfree9());
        // 自由辅助属性5
        this.keyValue.setBodyValue(row, SOItemKey.VFREE5,
            custmarvo.getVfree10());
        // 自由辅助属性6
        this.keyValue.setBodyValue(row, SOItemKey.VFREE6,
            custmarvo.getVfree11());
        // 自由辅助属性7
        this.keyValue.setBodyValue(row, SOItemKey.VFREE7,
            custmarvo.getVfree12());
        // 自由辅助属性8
        this.keyValue.setBodyValue(row, SOItemKey.VFREE8,
            custmarvo.getVfree13());
        // 自由辅助属性9
        this.keyValue.setBodyValue(row, SOItemKey.VFREE9,
            custmarvo.getVfree14());
        // 自由辅助属性10
        this.keyValue.setBodyValue(row, SOItemKey.VFREE10,
            custmarvo.getVfree15());
      }
    }
  }
}
