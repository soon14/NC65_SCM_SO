package nc.ui.so.custmatrel.action;

import java.awt.event.ActionEvent;
import java.util.HashMap;
import java.util.Map;

import nc.bs.framework.common.NCLocator;
import nc.itf.so.custmatrel.ICustMatRelMaintain;
import nc.ui.pubapp.uif2app.actions.SaveAction;
import nc.ui.so.custmatrel.rule.BillDataValidateRule;
import nc.ui.so.custmatrel.view.CardForm;
import nc.ui.uif2.UIState;
import nc.ui.uif2.model.BillManageModel;
import nc.ui.uif2.model.HierachicalDataAppModel;
import nc.vo.pub.BusinessException;
import nc.vo.pub.VOStatus;
import nc.vo.pubapp.pattern.exception.ExceptionUtils;
import nc.vo.pubapp.pattern.model.transfer.bill.ClientBillCombinServer;
import nc.vo.pubapp.pattern.model.transfer.bill.ClientBillToServer;
import nc.vo.pubapp.pattern.pub.PubAppTool;
import nc.vo.so.custmatrel.entity.CustMatRelBVO;
import nc.vo.so.custmatrel.entity.CustMatRelVO;
import nc.vo.trade.checkrule.VOChecker;

/**
 * 客户物料关系保存
 * 
 * @since 6.3
 * @version 2013-05-23 14:38:16
 * @author liujingn
 */
public class CustMatRelSaveAction extends SaveAction {

  /**
   * 
   */
  private static final long serialVersionUID = 1097887736946789708L;

  private CardForm view;

  /**
   * 
   * @return CardForm
   */
  public CardForm getView() {
    return this.view;
  }

  /**
   * 
   * @param view
   */
  public void setView(CardForm view) {
    this.view = view;
  }

  @Override
  public void doAction(ActionEvent e) throws Exception {
    this.view.getBillCardPanel().stopEditing();

    if (VOChecker.isEmpty(this.getModel().getContext().getPk_org())) {
      ExceptionUtils
          .wrappBusinessException(nc.vo.ml.NCLangRes4VoTransl.getNCLangRes()
              .getStrByID("4006007_0", "04006007-0001")/*@res "必须选择主组织。"*/);
    }

	  int bodyRowCount = this.view.getBillCardPanel().getBodyPanel()
					.getTable().getRowCount();
	  if(bodyRowCount <= 0){
	    ExceptionUtils
      .wrappBusinessException(nc.vo.ml.NCLangRes4VoTransl.getNCLangRes()
          .getStrByID("4006007_0", "04006007-0038")/*@res "表体不可为空！"*/);
	  }
    Object value = this.getEditor().getValue();

    this.view.validateValue();
    this.validate(value);

    if (this.getModel().getUiState() == UIState.ADD) {
      this.insertBill(value);

      this.getModel().setUiState(UIState.NOT_EDIT);
      if (this.getModel() instanceof HierachicalDataAppModel) {
        ((HierachicalDataAppModel) this.getModel()).setSelectedData(value);
        // 以上两句顺序不可调换。
      }
    }
    else if (this.getModel().getUiState() == UIState.EDIT) {
      this.updateBill(value);

      this.getModel().setUiState(UIState.NOT_EDIT);
    }
	// 修改保存后表头“销售组织”字段不允许编辑
		view.getOrgPanel().setEnabled(false);
    this.showSuccessInfo();
  }

  private void insertBill(Object value) {
    CustMatRelVO bill =
        new ClientBillToServer<CustMatRelVO>()
            .constructInsert(new CustMatRelVO[] {
              (CustMatRelVO) value
            })[0];
    BillDataValidateRule checkRule = new BillDataValidateRule();
    checkRule.validate(bill);
    CustMatRelVO retvo = null;
    try {
      ICustMatRelMaintain service =
          NCLocator.getInstance().lookup(ICustMatRelMaintain.class);
      retvo = service.insert(bill);
      // 后台变化VO与前台合并
      ClientBillCombinServer<CustMatRelVO> util =
          new ClientBillCombinServer<CustMatRelVO>();
      util.combine(new CustMatRelVO[] {
        (CustMatRelVO) value
      }, new CustMatRelVO[] {
        retvo
      });
    }
    catch (BusinessException e) {
      ExceptionUtils.wrappException(e);
    }
    this.getModel().directlyAdd(value);
  }

  private void updateBill(Object value) {
    int index = ((BillManageModel) this.getModel()).findBusinessData(value);
    CustMatRelVO data = null;
    // 导入修改时，可能model里面数据是空的，需要取值缓卡片界面中的原始VO
    if (index == -1) {
      data = this.view.getCachecustmatervo();
    }
    else {
      data =
          (CustMatRelVO) ((BillManageModel) this.getModel()).getData().get(
              index);
    }

    // data为当前model缓存中的数据，value为当前界面上的数据
    CustMatRelVO bill =
        new ClientBillToServer<CustMatRelVO>().construct(new CustMatRelVO[] {
          data
        }, new CustMatRelVO[] {
          (CustMatRelVO) value
        })[0];
// 强制转换，获得value内的childrenVO
		CustMatRelVO custMatRelVO = (CustMatRelVO) value;
		CustMatRelBVO[] valueItems = custMatRelVO.getChildrenVO();
		CustMatRelBVO[] billItems = bill.getChildrenVO();
		// 比较value和bill里同bvo状态
		compareVOStatus(valueItems, billItems);
    BillDataValidateRule checkRule = new BillDataValidateRule();
    checkRule.validate(data);
    CustMatRelVO ret = null;
    ICustMatRelMaintain service =
        NCLocator.getInstance().lookup(ICustMatRelMaintain.class);
    try {
      ret = service.update(bill);
      // 后台变化VO与前台合并
      ClientBillCombinServer<CustMatRelVO> util =
          new ClientBillCombinServer<CustMatRelVO>();
      util.combine(new CustMatRelVO[] {
        (CustMatRelVO) value
      }, new CustMatRelVO[] {
        ret
      });
    }
    catch (BusinessException e) {
      ExceptionUtils.wrappException(e);
    }
    this.getModel().initModel(value);
    
  }
	/**
	 * 
	 * 比较Value和Bill中的同一个子实体比较两个子实体的状态
	 * @param valueItems
	 * @param billItems
	 */
  private void compareVOStatus(CustMatRelBVO[] valueItems,
      CustMatRelBVO[] billItems) {
    if ((billItems != null) && (billItems.length > 0)) {
    }
    // NCM_Begin_quyt_20150702_构建map<表体主键，表体状态>，比较两个表体状态
    Map<String, Integer> valueMap = new HashMap<String, Integer>();
    for (CustMatRelBVO valueItem : valueItems) {
      if (!PubAppTool.isNull(valueItem.getPk_custmatrel_b())) {
        valueMap.put(valueItem.getPk_custmatrel_b(),
            valueItem.getStatus());
      }
    }
    for (CustMatRelBVO billItem : billItems) {
      if (PubAppTool.isNull(billItem.getPk_custmatrel_b())) {
        continue;
      }
      if (!valueMap.containsKey(billItem.getPk_custmatrel_b())) {
        continue;
      }
      // 根据表体PK判断相同单据，若value表体VO状态为UNCHANGE，billVO状态为UPDATE则将billVO状态改为UNCHANGE
      if (valueMap.get(billItem.getPk_custmatrel_b()) == VOStatus.UNCHANGED
          && billItem.getStatus() == VOStatus.UPDATED) {
        billItem.setStatus(VOStatus.UNCHANGED);
      }
    }
    // NCM_End_quyt_20150702_构建map<表体主键，表体状态>，比较两个表体状态
  }


}
