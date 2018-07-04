package nc.ui.so.m38.billui.action.print;

import nc.ui.pubapp.uif2app.actions.MetaDataBasedPrintAction.
              IBeforePrintDataProcess;
import nc.ui.uif2.model.AbstractAppModel;
import nc.vo.so.m30.entity.SaleOrderVO;
import nc.vo.so.m38.entity.PreOrderVO;
import nc.vo.so.pub.precision.SoVoPrecionScale;

/**
 * 打印时处理打印精度
 * @since 6.0
 * @version 2010-11-4 下午07:40:24
 * @author 祝会征
 */
public class PreOrderPrintProcessor implements IBeforePrintDataProcess {

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
    // 转化为预订单
    //PreOrderVO[] vos = (PreOrderVO[]) datas;
	PreOrderVO[] vos = new PreOrderVO[datas.length];
    for (int i = 0; i < datas.length; i++) {
      vos[i] = (PreOrderVO) datas[i];
    }
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
