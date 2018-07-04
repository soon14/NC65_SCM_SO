package nc.ui.so.mreturncondition.model;

import nc.ui.pubapp.uif2app.model.BatchBillTableModel;
import nc.vo.bd.meta.BatchOperateVO;
import nc.vo.so.mreturncondition.entity.ReturnConditionVO;
import nc.vo.uif2.LoginContext;

public class ReturnConditionModel extends BatchBillTableModel {

  private LoginContext context;

  private boolean orgSelected;

  @Override
  public LoginContext getContext() {
    return this.context;
  }

  public boolean isCorpSelected() {
    return this.orgSelected;
  }

  @Override
  public void save() throws Exception {
    BatchOperateVO vo = this.getCurrentSaveObject();
    Object[] vos = vo.getAddObjs();
    for (Object item : vos) {
      ReturnConditionVO rcvo = (ReturnConditionVO) item;
      rcvo.setPk_group(this.context.getPk_group());
    }
    super.save();
  }

  @Override
  public void setContext(LoginContext context) {
    this.context = context;
  }
}
