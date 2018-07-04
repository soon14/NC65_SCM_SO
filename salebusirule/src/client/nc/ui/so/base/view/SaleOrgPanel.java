package nc.ui.so.base.view;

import java.awt.Color;

import javax.swing.BorderFactory;

import nc.funcnode.ui.AbstractFunclet;
import nc.ui.pubapp.uif2app.event.OrgChangedEvent;
import nc.ui.pubapp.uif2app.view.OrgPanel;
import nc.ui.pubapp.uif2app.view.util.OrgUtils;
import nc.vo.pubapp.pattern.exception.ExceptionUtils;

public class SaleOrgPanel extends OrgPanel {

  private static final long serialVersionUID = 8571481967175733082L;

  @Override
  public void initUI() {
    super.initUI();
    // Ìí¼Ó·Ö¸ôÏß
    this.setBorder(BorderFactory.createMatteBorder(0, 0, 1, 0, Color.gray));
  }

  @Override
  protected void initDefaultOrg() {
    try {
      this.setPkOrg(OrgUtils.getDefaultOrgPK(((AbstractFunclet) this.getModel()
            .getContext().getEntranceUI()).getFuncletContext()
            .getFuncRegisterVO().getOrgtypecode()));
    }
    catch (Exception e) {
      ExceptionUtils.wrappException(e);
    }

  }

  @Override
  protected void handleOrgChangedEvent(OrgChangedEvent e) {
    String pk_org = this.getRefPane().getRefPK();
    this.getModel().getContext().setPk_org(pk_org);
    this.getDataManager().initModel();
  }

}
