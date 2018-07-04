package nc.vo.so.m32.rule;

import nc.itf.scmpub.reference.uap.bd.material.MaterialPubService;
import nc.vo.so.m32.entity.SaleInvoiceBVO;
import nc.vo.so.pub.keyvalue.IKeyValue;

public class UnitChangeRateRule {
  private IKeyValue keyValue;

  /**
   * UnitChangeRateHandler 的构造子
   * 
   * @param keyValue
   */
  public UnitChangeRateRule(IKeyValue keyValue) {
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
        this.calcChangeRate(index, SaleInvoiceBVO.CASTUNITID);
    this.keyValue
        .setBodyValue(index, SaleInvoiceBVO.VCHANGERATE, astChangeRate);
  }

  public void calcAstChangeRate(int[] indexs) {
    for (int index : indexs) {
      this.calcAstChangeRate(index);
    }

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
    String qtChangeRate = this.calcChangeRate(index, SaleInvoiceBVO.CQTUNITID);
    this.keyValue.setBodyValue(index, SaleInvoiceBVO.VQTUNITRATE, qtChangeRate);
  }

  public void calcQtChangeRate(int[] indexs) {
    for (int index : indexs) {
      this.calcAstChangeRate(index);
    }
  }

  /**
   * 方法功能描述：销售发票子表第index行，业务单位和主单位是否固定换算率。
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
    return this.isFixedRate(index, SaleInvoiceBVO.CASTUNITID);
  }

  /**
   * 方法功能描述：销售发票子表第index行，报价单位和主单位是否固定换算率。
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
    return this.isFixedRate(index, SaleInvoiceBVO.CQTUNITID);
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
        this.keyValue.getBodyStringValue(index, SaleInvoiceBVO.CMATERIALVID);
    String unit =
        this.keyValue.getBodyStringValue(index, SaleInvoiceBVO.CUNITID);
    String editunit = this.keyValue.getBodyStringValue(index, editunitkey);

    String changerate = null;
    changerate =
        MaterialPubService.queryMainMeasRateByMaterialAndMeasdoc(material,
            unit, editunit);

    return changerate;

  }

  /**
   * 方法功能描述：计算销售发票第index行，传入单位和主单位是否固定换算率。
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
        this.keyValue.getBodyStringValue(index, SaleInvoiceBVO.CMATERIALVID);
    String unit =
        this.keyValue.getBodyStringValue(index, SaleInvoiceBVO.CUNITID);
    String tounit = this.keyValue.getBodyStringValue(index, unitkey);

    boolean isfixed = false;
    isfixed =
        MaterialPubService.queryIsFixedRateByMaterialAndMeasdoc(material, unit,
            tounit);

    return isfixed;

  }
}
