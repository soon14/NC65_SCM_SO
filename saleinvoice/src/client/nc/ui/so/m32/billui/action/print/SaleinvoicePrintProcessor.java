package nc.ui.so.m32.billui.action.print;

import nc.ui.pubapp.pub.power.PowerCheckUtils;
import nc.ui.uif2.model.AbstractAppModel;
import nc.vo.price.adjustprice.entity.AdjustPriceHVO;
import nc.vo.pubapp.pub.power.PowerActionEnum;
import nc.vo.scmpub.res.billtype.SOBillType;
import nc.vo.so.m32.entity.SaleInvoiceVO;
import nc.vo.so.pub.precision.SoVoPrecionScale;

/**
 * 打印时处理打印精度
 * 
 * @since 6.0
 * @version 2010-11-4 下午07:40:24
 * @author 祝会征
 */
public class SaleinvoicePrintProcessor
    implements
    nc.ui.pubapp.uif2app.actions.MetaDataBasedPrintAction.IBeforePrintDataProcess {

  private AbstractAppModel model;

  /**
   * @return model
   */
  public AbstractAppModel getModel() {
    return this.model;
  }

  /**
   * 父类方法重写
   * 
   */
  @Override
  public Object[] processData(Object[] datas) {
    // 转化为销售发票vo
    SaleInvoiceVO[] vos = new SaleInvoiceVO[datas.length];
    for (int i = 0; i < datas.length; i++) {
      vos[i] = (SaleInvoiceVO) datas[i];
    }

    // 权限校验
    PowerCheckUtils.checkHasPermission(vos, SOBillType.Invoice.getCode(),
        PowerActionEnum.PRINT.getActioncode(), AdjustPriceHVO.VBILLCODE);

    // vos = (SaleInvoiceVO[]) datas;
    // 精度处理
    SoVoPrecionScale handler =
        new SoVoPrecionScale(this.getModel().getContext().getPk_group(), vos);
    handler.setScale();
    return vos;

  }

  /**
   * @param model
   *          要设置的 model
   */
  public void setModel(AbstractAppModel model) {
    this.model = model;
  }

}
