package nc.ui.so.pub.actions;

import nc.ui.pubapp.uif2app.actions.FileDocManageAction;
import nc.ui.pubapp.uif2app.model.BillManageModel;

/**
 * 销售管理附件管理响应类
 * 
 * @since 6.0
 * @version 2011-2-24 下午02:35:02
 * @author 么贵敬
 */
public class SOManageDocumentAction extends FileDocManageAction {

  /** VersionUID */
  private static final long serialVersionUID = 5140670340573968728L;

  /**
   * 构造函数
   */
  public SOManageDocumentAction() {
    super();
  }

  @Override
  protected boolean isActionEnable() {
    BillManageModel somodel = (BillManageModel) super.getModel();

    return somodel.getSelectedData() != null
        && somodel.getSelectedOperaDatas() != null
        && somodel.getSelectedOperaDatas().length == 1;
  }

}
