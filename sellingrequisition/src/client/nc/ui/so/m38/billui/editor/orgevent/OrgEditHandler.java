package nc.ui.so.m38.billui.editor.orgevent;

import nc.itf.scmpub.reference.uap.rbac.UserManageQuery;
import nc.ui.pub.bill.BillCardPanel;
import nc.ui.pub.bill.BillItem;
import nc.ui.pubapp.AppUiContext;
import nc.ui.pubapp.uif2app.event.IAppEventHandler;
import nc.ui.pubapp.uif2app.event.OrgChangedEvent;
import nc.ui.pubapp.uif2app.view.BillForm;
import nc.ui.pubapp.uif2app.view.util.BillPanelUtils;
import nc.ui.so.m38.billui.pub.PreOrderPrecision;
import nc.ui.so.pub.keyvalue.CardKeyValue;
import nc.ui.uif2.UIState;
import nc.vo.pub.lang.UFDate;
import nc.vo.pub.lang.UFDouble;
import nc.vo.so.m38.entity.PreOrderBVO;
import nc.vo.so.m38.entity.PreOrderHVO;
import nc.vo.uif2.LoginContext;

public class OrgEditHandler implements IAppEventHandler<OrgChangedEvent> {

  private static final int DEFDABATEDATE = 3;

  private BillCardPanel billCardPanel;

  private BillForm billfrom;

  private LoginContext context;

  private CardKeyValue keyValue;

  public OrgEditHandler(BillForm bill, LoginContext context) {
    this.billfrom = bill;
    this.billCardPanel = bill.getBillCardPanel();
    this.context = context;
  }

  @Override
  public void handleAppEvent(OrgChangedEvent e) {
    this.keyValue = new CardKeyValue(this.billCardPanel);
    if (this.billfrom.isEditable()) {
      // 在编辑状态下，主组织切换时，清空界面数据，自动表体增行，并设置行号，songhy
      this.billfrom.addNew();
    }

    // 设置界面精度
    PreOrderPrecision.getInstance().setCardPrecision(
        this.context.getPk_group(), this.billCardPanel);

    // 打开节点直接查询old组织为空会跳进来，判断新增的
    if (this.billfrom.getModel().getUiState().equals(UIState.ADD)) {

      // 清空单据面板
      this.clearPanel();

      if (e.getNewPkOrg() == null) {
        return;
      }

      // 设置默认值
      this.setDefValue(e);

      // 根据操作员带出业务员
      this.setDefaultPsnValue();
    }
    BillPanelUtils.setOrgForAllRef(this.billfrom.getBillCardPanel(),
        this.billfrom.getModel().getContext());
  }

  private void clearPanel() {
    BillItem[] headItems = this.billCardPanel.getHeadItems();
    for (BillItem item : headItems) {
      if (item.getDefaultValue() != null) {
        item.setValue(item.getDefaultValue());
      }
      else {
        item.setValue(null);
      }
    }

    BillItem[] bodyItems = this.billCardPanel.getBodyItems();
    int irowcount = this.billCardPanel.getRowCount();
    for (BillItem item : bodyItems) {
      if (item.getKey().equals(PreOrderBVO.CROWNO)) {
        continue;
      }
      for (int i = 0; i < irowcount; i++) {
        if (item.getDefaultValue() != null) {
          this.keyValue.setBodyValue(i, item.getKey(), item.getDefaultValue());
        }
        else {
          this.keyValue.setBodyValue(i, item.getKey(), null);
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

  private void setDefaultPsnValue() {
    // 模板上如果设置了业务员的默认值，则不进行下面的处理
    if (this.keyValue.getHeadStringValue(PreOrderHVO.CEMPLOYEEID) != null) {
      return;
    }
    // TODO 获得业务员信息
    String pk_psndoc =
        UserManageQuery.queryPsndocByUserid(this.context.getPk_loginUser());
    this.keyValue.setHeadValue(PreOrderHVO.CEMPLOYEEID, pk_psndoc);
  }

  private void setDefValue(OrgChangedEvent e) {
    int irowcount = this.billCardPanel.getRowCount();
    // -- 表头 ----------
    // 销售组织
    String pk_org = e.getNewPkOrg();
    this.keyValue.setHeadValue(PreOrderHVO.PK_ORG, pk_org);
    this.billCardPanel.getBillData().loadEditHeadRelation(PreOrderHVO.PK_ORG);
    // 集团
    String pk_group = AppUiContext.getInstance().getPkGroup();
    this.keyValue.setHeadValue(PreOrderHVO.PK_GROUP, pk_group);
    // 单据日期
    UFDate busidate = AppUiContext.getInstance().getBusiDate();
    this.keyValue.setHeadValue(PreOrderHVO.DBILLDATE, busidate);
    // 失效日期
    this.keyValue.setHeadValue(PreOrderHVO.DABATEDATE, busidate.getDateAfter(DEFDABATEDATE).asLocalEnd());
    
    // 发货、收货日期
    UFDate localend = busidate.asLocalEnd();
    // -- 表体 ----------
    for (int i = 0; i < irowcount; i++) {
      // 集团
      this.keyValue.setBodyValue(i, PreOrderBVO.PK_GROUP, pk_group);
      // 销售组织
      this.keyValue.setBodyValue(i, PreOrderBVO.PK_ORG, pk_org);
      // 单据日期
      this.keyValue.setBodyValue(i, PreOrderBVO.DBILLDATE, busidate);
      // 单品折扣
      this.keyValue.setBodyValue(i, PreOrderBVO.NITEMDISCOUNTRATE,
          new UFDouble(100));
      // 计划发货日期、要求收货日期
      this.keyValue.setBodyValue(i, PreOrderBVO.DSENDDATE, localend);
      this.keyValue.setBodyValue(i, PreOrderBVO.DRECEIVEDATE, localend);
    }
  }
}
