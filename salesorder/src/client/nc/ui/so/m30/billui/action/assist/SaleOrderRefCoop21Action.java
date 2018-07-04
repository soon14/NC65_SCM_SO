package nc.ui.so.m30.billui.action.assist;

import java.awt.event.ActionEvent;

import nc.itf.scmpub.reference.uap.group.SysInitGroupQuery;
import nc.itf.so.m30.ref.scmpub.CoopVOChangeUtil;
import nc.ui.ml.NCLangRes;
import nc.ui.pub.bill.BillItem;
import nc.ui.pub.pf.PfUtilClient;
import nc.ui.pubapp.uif2app.view.BillForm;
import nc.ui.scmpub.action.ReferenceAction;
import nc.ui.uif2.UIState;
import nc.ui.uif2.model.AbstractAppModel;
import nc.vo.pu.m21.entity.OrderVO;
import nc.vo.pubapp.pattern.data.ValueUtils;
import nc.vo.pubapp.pattern.exception.ExceptionUtils;
import nc.vo.scmpub.res.billtype.SOBillType;
import nc.vo.so.m30.entity.SaleOrderBVO;
import nc.vo.so.m30.entity.SaleOrderHVO;
import nc.vo.so.m30.entity.SaleOrderVO;
import nc.vo.so.pub.keyvalue.IKeyValue;
import nc.vo.so.pub.keyvalue.VOKeyValue;
import nc.vo.so.pub.rule.BodyValueRowRule;
import nc.vo.so.pub.rule.SOBuysellTriaRule;
import nc.vo.so.pub.rule.SOCountryInfoRule;
import nc.vo.so.pub.rule.SOTaxInfoRule;

public class SaleOrderRefCoop21Action extends ReferenceAction {

  /**
   * 
   */
  private static final long serialVersionUID = 318973906744522014L;

  private BillForm editor;

  private AbstractAppModel model;

  @Override
  public void doAction(ActionEvent e) throws Exception {
	  
	if (!SysInitGroupQuery.isPOEnabled()) {
	         ExceptionUtils.wrappBusinessException(NCLangRes.getInstance().getStrByID(
		          "4006011_0", "04006011-0521")/*请启用采购模块！*/);
    }
	  
    PfUtilClient.childButtonClicked(this.getSourceBillType(), this.getModel()
        .getContext().getPk_group(), this.getModel().getContext()
        .getPk_loginUser(), SOBillType.Order.getCode(), this.getModel()
        .getContext().getEntranceUI(), null, null);
    if (PfUtilClient.isCloseOK()) {
      CoopVOChangeUtil coopUtil = new CoopVOChangeUtil();
      OrderVO[] srcVOs = (OrderVO[]) PfUtilClient.getRetOldVos();
      SaleOrderVO[] destVOs = (SaleOrderVO[]) PfUtilClient.getRetVos();
      // 协同设置 && 客户档案默认值
      if (null == destVOs || destVOs.length == 0) {
        return;
      }
      SaleOrderVO[] vos = coopUtil.processVO(srcVOs, destVOs);
      // 重取购销类型 三角贸易 税码
      for (SaleOrderVO ordervo : vos) {

        IKeyValue keyValue = new VOKeyValue<SaleOrderVO>(ordervo);
        BodyValueRowRule bodycouuitl = new BodyValueRowRule(keyValue);
        int[] rows = bodycouuitl.getMarNotNullRows();
        SOCountryInfoRule countryrule = new SOCountryInfoRule(keyValue);
        countryrule.setTaxCountry(rows);
        // 购销类型
        SOBuysellTriaRule buyflgrule = new SOBuysellTriaRule(keyValue);
        buyflgrule.setBuysellAndTriaFlag(rows);
        // 询税
        SOTaxInfoRule taxInfo = new SOTaxInfoRule(keyValue);
        taxInfo.setOnlyTaxCodeByBodyPos(rows);
      }
      // 显示到转单界面上
      this.getTransferBillViewProcessor().processBillTransfer(vos);
      this.setEditeEnable();
    }
  }

  public BillForm getEditor() {
    return this.editor;
  }

  public AbstractAppModel getModel() {
    return this.model;
  }

  public void setEditor(BillForm editor) {
    this.editor = editor;
  }

  public void setModel(AbstractAppModel model) {
    this.model = model;
    model.addAppEventListener(this);
  }

  @Override
  public void setSourceBillName(String sourceBillName) {
    super.setSourceBillName(sourceBillName);
  }

  @Override
  protected boolean isActionEnable() {
    return this.model.getUiState() == UIState.NOT_EDIT;
  }

  private void setEditeEnable() {
    Object obj =
        this.editor.getBillCardPanel()
            .getHeadItem(SaleOrderHVO.BPOCOOPTOMEFLAG).getValueObject();
    boolean iscoop = false;
    if (null != obj) {
      iscoop = ValueUtils.getBoolean(obj);
    }
    BillItem item =
        this.editor.getBillCardPanel().getBodyItem(SaleOrderBVO.CSETTLEORGVID);
    if (iscoop) {
      item.setEdit(false);
      return;
    }
    item.setEdit(true);
  }
}
