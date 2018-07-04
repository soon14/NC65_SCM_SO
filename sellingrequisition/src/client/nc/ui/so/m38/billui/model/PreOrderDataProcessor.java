package nc.ui.so.m38.billui.model;

import nc.funcnode.ui.FuncletInitData;
import nc.ui.pub.bill.BillCardPanel;
import nc.ui.pubapp.ClientContext;
import nc.ui.pubapp.uif2app.model.DefaultFuncNodeInitDataListener.IInitDataProcessor;
import nc.ui.so.m38.billui.pub.PreOrderPrecision;
import nc.ui.so.m38.billui.view.PreOrderEditor;
import nc.ui.uif2.UIState;
import nc.ui.uif2.model.AbstractAppModel;
import nc.vo.so.m38.entity.PreOrderVO;

/**
 * 预订单打开节点监听
 * 
 * @since 6.0
 * @version 2011-1-5 上午10:10:31
 * @author 么贵敬
 */
public class PreOrderDataProcessor implements IInitDataProcessor {

  /** 预订单卡片 */
  private PreOrderEditor editor;

  /** 管理模型 */
  private AbstractAppModel model;

  /**
   * 卡片 getter
   * 
   * @return 卡片
   */
  public PreOrderEditor getEditor() {
    return this.editor;
  }

  /**
   * 管理模型
   * 
   * @return 管理模型
   */
  public AbstractAppModel getModel() {
    return this.model;
  }

  @Override
  public void process(FuncletInitData data) {
    if (101 == data.getInitType()) {
      PreOrderVO[] preorders = (PreOrderVO[]) data.getInitData();
      // 设置精度
      PreOrderPrecision precision = new PreOrderPrecision();
      String pk_group = ClientContext.getInstance().getPk_group();
      BillCardPanel cardpanel = this.getEditor().getBillCardPanel();
      precision.setCardPrecision(pk_group, cardpanel);
      // 加载数据
      this.getModel().initModel(preorders);
      this.getModel().setUiState(UIState.NOT_EDIT);
    }
  }

  /**
   * 卡片
   * 
   * @param editor 卡片
   */
  public void setEditor(PreOrderEditor editor) {
    this.editor = editor;
  }

  /**
   * 管理模型
   * 
   * @param model 管理模型
   */
  public void setModel(AbstractAppModel model) {
    this.model = model;
  }

}
