package nc.ui.so.salequotation.rule;

import nc.itf.scmpub.reference.uap.bd.material.MaterialPubService;
import nc.vo.so.pub.keyvalue.IKeyValue;
import nc.vo.so.salequotation.entity.SalequotationBVO;

public class SaleQuotationUnitChangeRateRule {

  private IKeyValue keyValue;

  /**
   * UnitChangeRateHandler 的构造子
   * 
   * @param keyValue
   */
  public SaleQuotationUnitChangeRateRule(IKeyValue keyValue) {
    this.keyValue = keyValue;
  }

  /**
   * 方法功能描述：计算业务单位和主单位之间换算率。
   * <p>
   * <b>参数说明</b>
   * 
   * @param index
   *          <p>
   * @author 冯加滨
   * @time 2010-4-26 上午10:31:34
   */
  public void calcAstChangeRate(int index) {
    String astChangeRate =
        this.calcChangeRate(index, SalequotationBVO.CASTUNITID);
    this.keyValue.setBodyValue(index, SalequotationBVO.NCHANGERATE,
        astChangeRate);
  }

  /**
   * 方法功能描述：计算报价单位和主单位之间换算率。
   * <p>
   * <b>参数说明</b>
   * 
   * @param index
   *          <p>
   * @author 冯加滨
   * @time 2010-4-26 下午01:22:59
   */
  public void calcQtChangeRate(int index) {
    String qtChangeRate =
        this.calcChangeRate(index, SalequotationBVO.CQTUNITID);
    this.keyValue.setBodyValue(index, SalequotationBVO.NQTCHANGERATE,
        qtChangeRate);
  }

  /**
   * 方法功能描述：子表第index行，业务单位和主单位是否固定换算率。
   * <p>
   * <b>参数说明</b>
   * 
   * @param index
   * @param editunitkey
   * @return <p>
   * @author 冯加滨
   * @time 2010-4-26 下午05:53:19
   */
  public boolean isAstFixedRate(int index) {
    return this.isFixedRate(index, SalequotationBVO.CASTUNITID);
  }

  /**
   * 方法功能描述：子表第index行，报价单位和主单位是否固定换算率。
   * <p>
   * <b>参数说明</b>
   * 
   * @param index
   * @param editunitkey
   * @return <p>
   * @author 冯加滨
   * @time 2010-4-26 下午05:53:51
   */
  public boolean isQtFixedRate(int index) {
    return this.isFixedRate(index, SalequotationBVO.CQTUNITID);
  }

  /**
   * 方法功能描述：计算传入编辑单位和主单位之间换算率。
   * <p>
   * <b>参数说明</b>
   * 
   * @param index
   * @param editunitkey
   * @return <p>
   * @author 冯加滨
   * @time 2010-4-26 下午01:23:21
   */
  private String calcChangeRate(int index, String editunitkey) {

    String material =
        this.keyValue.getBodyStringValue(index, SalequotationBVO.PK_MATERIAL_V);
    String unit =
        this.keyValue.getBodyStringValue(index, SalequotationBVO.PK_UNIT);
    String editunit = this.keyValue.getBodyStringValue(index, editunitkey);

    String changerate = null;

    changerate =
        MaterialPubService.queryMainMeasRateByMaterialAndMeasdoc(material,
            unit, editunit);

    return changerate;

  }

  /**
   * 方法功能描述：计算第index行，传入单位和主单位是否固定换算率。
   * <p>
   * <b>参数说明</b>
   * 
   * @param index
   * @param unitkey
   * @return <p>
   * @author 冯加滨
   * @time 2010-4-26 下午05:58:01
   */
  private boolean isFixedRate(int index, String unitkey) {

    String material =
        this.keyValue.getBodyStringValue(index, SalequotationBVO.PK_MATERIAL_V);
    String unit =
        this.keyValue.getBodyStringValue(index, SalequotationBVO.PK_UNIT);
    String tounit = this.keyValue.getBodyStringValue(index, unitkey);

    boolean isfixed = false;
    isfixed =
        MaterialPubService.queryIsFixedRateByMaterialAndMeasdoc(material, unit,
            tounit);

    return isfixed;

  }

}
