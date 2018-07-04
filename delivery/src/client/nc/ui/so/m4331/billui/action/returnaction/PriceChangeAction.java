package nc.ui.so.m4331.billui.action.returnaction;

import java.awt.event.ActionEvent;
import java.util.Map;

import nc.desktop.ui.WorkbenchEnvironment;
import nc.itf.scmpub.reference.uap.bd.material.MaterialPubService;
import nc.ui.pub.beans.UITable;
import nc.ui.pub.bill.BillCardPanel;
import nc.ui.pub.bill.BillModel;
import nc.ui.scmpub.action.SCMActionInitializer;
import nc.ui.so.m4331.billui.action.returnaction.dlg.ChgPriceDialog;
import nc.ui.so.m4331.billui.model.DeliveryManageModel;
import nc.ui.so.m4331.billui.view.DeliveryEditor;
import nc.ui.so.m4331.billui.view.DeliveryListView;
import nc.ui.uif2.NCAction;
import nc.ui.uif2.UIState;
import nc.vo.bd.material.stock.MaterialStockVO;
import nc.vo.pub.lang.UFBoolean;
import nc.vo.pub.lang.UFDouble;
import nc.vo.pubapp.pattern.exception.ExceptionUtils;
import nc.vo.pubapp.pattern.pub.MathTool;
import nc.vo.scmpub.res.SCMActionCode;
import nc.vo.so.m4331.entity.DeliveryBVO;
import nc.vo.so.m4331.entity.DeliveryVO;
import nc.vo.so.pub.enumeration.BillStatus;

public class PriceChangeAction extends NCAction {

  /**
     *
     */
  private static final long serialVersionUID = -4490586267001887381L;

  private BillCardPanel billcard;

  private final String body = DeliveryBVO.class.getName();

  private DeliveryEditor editor;
  
  private DeliveryListView listview;

  private DeliveryManageModel model;

  public PriceChangeAction() {
    super();
    this.initializeAction();
  }

  @Override
  public void doAction(ActionEvent e) throws Exception {
    this.changePrice();
  }

  public DeliveryManageModel getModel() {
    return this.model;
  }

  public void setEditor(DeliveryEditor editor) {
    this.editor = editor;
  }

  public void setModel(DeliveryManageModel model) {
    this.model = model;
    model.addAppEventListener(this);
  }

  @Override
  protected boolean isActionEnable() {
    DeliveryVO vo = (DeliveryVO) this.getModel().getSelectedData();
    if (this.getModel().getUiState() != UIState.NOT_EDIT || null == vo) {
      return false;
    }
    Integer billstatus = vo.getParentVO().getFstatusflag();
    if (!BillStatus.AUDIT.equalsValue(billstatus)) {
      return false;
    }
    UFDouble num = UFDouble.ZERO_DBL;
    int[] rows = null;
    if (this.editor.isComponentVisible()) {
      rows = this.editor.getBillCardPanel().getBillTable().getSelectedRows();
      if (null == rows || rows.length != 1) {
        return false;
      }
      DeliveryBVO bvo =
          (DeliveryBVO) this.editor.getBillCardPanel().getBillModel()
              .getBodyValueRowVO(rows[0], DeliveryBVO.class.getName());
      num = bvo.getNnum();
    }
    else {
      UITable table = this.listview.getBillListPanel().getBodyTable();
      rows = table.getSelectedRows();
      if (null == rows || rows.length != 1) {
        return false;
      }
      DeliveryBVO bvo =
          (DeliveryBVO) this.listview.getBillListPanel().getBodyBillModel()
              .getBodyValueRowVO(rows[0], DeliveryBVO.class.getName());
      num = bvo.getNnum();
    }
    if (MathTool.compareTo(num, UFDouble.ZERO_DBL) >= 0) {
      return false;
    }
    return true;
  }

  private void changePrice() {
    DeliveryBVO bvo = this.getBodyVO();
    if (bvo == null) {
      ExceptionUtils.wrappBusinessException(nc.vo.ml.NCLangRes4VoTransl.getNCLangRes().getStrByID("4006002_0","04006002-0004")/*@res "请选中表体行。"*/);
    }
    this.checkIsChange(bvo);
    
    ChgPriceDialog dlg =
        new ChgPriceDialog(WorkbenchEnvironment.getInstance().getWorkbench()
            .getParent(), true);
    dlg.loadData(bvo);
    dlg.setResizable(true);
    dlg.showModal();
  }
  
  private DeliveryBVO getBodyVO() {
    boolean flag = this.editor.isComponentVisible();
    DeliveryBVO bvo = null;
    if (!flag) {
      UITable table = this.listview.getBillListPanel().getBodyTable();
      int row = table.getSelectedRow();
      BillModel billmodel = this.listview.getBillListPanel().getBodyBillModel();
      bvo =
            (DeliveryBVO) billmodel.getBodyValueRowVO(row,
                DeliveryBVO.class.getName());
    }
    else {
      int row =
          this.editor.getBillCardPanel().getBillTable().getSelectedRow();
      bvo =
            (DeliveryBVO) this.editor.getBillCardPanel().getBillModel()
                .getBodyValueRowVO(row, DeliveryBVO.class.getName());
    }
    return bvo;
  }

  private void checkIsChange(DeliveryBVO bvo) {
    UFBoolean bcheckflag = bvo.getBcheckflag();
    UFBoolean bqualityflag = bvo.getBqualityflag();
    boolean isretinstobychk = this.getIsretinstobychk(bvo);
    if (isretinstobychk) {
      ExceptionUtils.wrappBusinessException(nc.vo.ml.NCLangRes4VoTransl.getNCLangRes().getStrByID("4006002_0","04006002-0011")/*@res "物料为退货免检，不能做价格改判。"*/);
    }
    if (null == bcheckflag || !bcheckflag.booleanValue()) {
      ExceptionUtils.wrappBusinessException(nc.vo.ml.NCLangRes4VoTransl.getNCLangRes().getStrByID("4006002_0","04006002-0012")/*@res "选中表体行没有报检，不能做价格改判。"*/);
    }
    if (null == bqualityflag || !bqualityflag.booleanValue()) {
      ExceptionUtils.wrappBusinessException(nc.vo.ml.NCLangRes4VoTransl.getNCLangRes().getStrByID("4006002_0","04006002-0013")/*@res "选中表体行没有质检结束，不能做价格改判。"*/);
    }
  }

  private BillCardPanel getBillCardPanel() {
    if (this.billcard == null) {
      this.billcard = this.editor.getBillCardPanel();
    }
    return this.billcard;
  }

  private boolean getIsretinstobychk(DeliveryBVO bvo) {
    String pk_material = bvo.getCmaterialvid();
    String pk_sendorg = bvo.getCsendstockorgid();
    if (null == pk_sendorg || "".equals(pk_sendorg)) {
      ExceptionUtils.wrappBusinessException(nc.vo.ml.NCLangRes4VoTransl.getNCLangRes().getStrByID("4006002_0","04006002-0014")/*@res "发货库存组织为空，不能进行价格改判。"*/);
    }
    // 查询发货库存组织下的物料的免检属性 为N的可以报检
    Map<String, MaterialStockVO> materialMap =
        MaterialPubService.queryMaterialStockInfo(new String[] {
          pk_material
        }, pk_sendorg, new String[] {
          MaterialStockVO.ISRETFREEOFCHK, MaterialStockVO.PK_MATERIAL
        });
    UFBoolean flag = materialMap.get(pk_material).getIsretfreeofchk();
    if (null == flag || !flag.booleanValue()) {
      return false;
    }
    return true;
  }

  private void initializeAction() {
    SCMActionInitializer.initializeAction(this, SCMActionCode.SO_PRICECHANGE);

  }

  
  public DeliveryListView getListview() {
    return listview;
  }

  
  public void setListview(DeliveryListView listview) {
    this.listview = listview;
  }
}