package nc.ui.so.m4331.billui.editor.orgevent;

import nc.ui.pub.bill.BillCardPanel;
import nc.ui.pub.bill.BillItem;
import nc.ui.pubapp.uif2app.event.IAppEventHandler;
import nc.ui.pubapp.uif2app.event.OrgChangedEvent;
import nc.ui.pubapp.uif2app.view.util.BillPanelUtils;
import nc.ui.so.m4331.billui.util.DeliveryPrecision;
import nc.ui.uif2.UIState;
import nc.ui.uif2.editor.BillForm;
import nc.vo.pub.lang.UFDate;
import nc.vo.pubapp.AppContext;
import nc.vo.pubapp.pattern.pub.PubAppTool;
import nc.vo.so.m4331.entity.DeliveryBVO;
import nc.vo.so.m4331.entity.DeliveryHVO;
import nc.vo.uif2.LoginContext;

public class OrgEditHandler implements IAppEventHandler<OrgChangedEvent> {

  private BillCardPanel billCardPanel;

  private BillForm billfrom;

  private LoginContext context;

  public OrgEditHandler(BillForm bill, LoginContext context) {
    this.billfrom = bill;
    this.billCardPanel = bill.getBillCardPanel();
    this.context = context;
  }

  @Override
  public void handleAppEvent(OrgChangedEvent e) {
    // 设置界面精度
    DeliveryPrecision.getInstance().setCardPrecision(
        this.context.getPk_group(), this.billCardPanel);
    // 设置参照约束条件
    BillPanelUtils.setOrgForAllRef(this.billfrom.getBillCardPanel(),
        this.billfrom.getModel().getContext());
    ;
    // 新增时切换主组织处理
    if (this.billfrom.getModel().getUiState().equals(UIState.ADD)) {
      // 清空单据面板
      this.clearPanelValue();

      if (PubAppTool.isNull(e.getNewPkOrg())) {
        return;
      }
      // 设置默认值
      this.setDefValue(e);
    }

  }

  private void clearPanelValue() {
    // 清空表头字段值
    BillItem[] headItems = this.billCardPanel.getHeadItems();
    for (BillItem item : headItems) {
      if (item.getDefaultValue() != null) {
        item.setValue(item.getDefaultValue());
      }
      else {
        item.setValue(null);
      }
    }
    // 清空表体字段值
    BillItem[] bodyItems = this.billCardPanel.getBodyItems();
    int len = this.billCardPanel.getRowCount();
    for (BillItem item : bodyItems) {
      if (item.getKey().equals(DeliveryBVO.CROWNO)) {
        continue;
      }
      for (int i = 0; i < len; i++) {
        if (item.getDefaultValue() != null) {
          this.billCardPanel.setBodyValueAt(item.getDefaultValue(), i,
              item.getKey());
        }
        else {
          this.billCardPanel.setBodyValueAt(null, i, item.getKey());
        }
      }
    }

    BillItem[] tailItems = this.billCardPanel.getTailItems();
    for (BillItem item : tailItems) {
      if (item.getDefaultValue() != null) {
        item.setValue(item.getDefaultValue());
      }
      else {
        item.setValue(null);
      }
    }
  }

  private void setDefValue(OrgChangedEvent e) {

    int irowcount = this.billCardPanel.getRowCount();
    UFDate dbusidate = AppContext.getInstance().getBusiDate();
    // 设置表头开票组织
    this.billCardPanel.setHeadItem(DeliveryHVO.PK_ORG, e.getNewPkOrg());
    // this.billCardPanel.getBillData().loadEditHeadRelation(DeliveryHVO.PK_ORG);
    // 集团
    this.billCardPanel.setHeadItem(DeliveryHVO.PK_GROUP,
        this.context.getPk_group());
    // 单据日期
    this.billCardPanel.setHeadItem(DeliveryHVO.DBILLDATE, dbusidate);
    // 表体开票组织、单据日期
    for (int i = 0; i < irowcount; i++) {
      this.billCardPanel.setBodyValueAt(e.getNewPkOrg(), i, DeliveryBVO.PK_ORG);
      this.billCardPanel.setBodyValueAt(dbusidate, i, DeliveryBVO.DBILLDATE);
    }
  }

}
