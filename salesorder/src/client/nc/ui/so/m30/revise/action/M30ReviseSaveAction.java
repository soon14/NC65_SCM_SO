package nc.ui.so.m30.revise.action;

import java.awt.event.ActionEvent;
import java.util.Map;

import nc.itf.pubapp.pub.exception.IResumeException;
import nc.ui.pub.bill.fixblob.ReQuery2FixBlob;
import nc.ui.pubapp.pub.common.context.PFlowContext;
import nc.ui.pubapp.uif2app.AppUiState;
import nc.ui.pubapp.uif2app.actions.pflow.SaveScriptAction;
import nc.ui.scmpub.util.ResumeExceptionUIProcessUtils;
import nc.ui.so.m30.billui.view.SaleOrderBillForm;
import nc.ui.so.pub.keyvalue.CardKeyValue;
import nc.ui.uif2.UIState;
import nc.vo.bd.feature.ffile.entity.AggFFileVO;
import nc.vo.pf.change.PfUtilBaseTools;
import nc.vo.pub.lang.UFBoolean;
import nc.vo.pubapp.pattern.exception.ExceptionUtils;
import nc.vo.pubapp.pflow.PfUserObject;
import nc.vo.so.m30.entity.SaleOrderUserObject;
import nc.vo.so.m30.entity.SaleOrderVO;
import nc.vo.so.m30.revise.entity.SaleOrderHistoryBVO;
import nc.vo.so.m30.revise.entity.SaleOrderHistoryVO;
import nc.vo.so.m30.util.FeatureSelectUtil;
import nc.vo.so.pub.SOConstant;

/**
 * 销售订单修订保存按钮
 * 
 * @version 6.0
 * @author 刘志伟
 * @time 2010-8-9 下午08:27:54
 */
@SuppressWarnings("restriction")
public class M30ReviseSaveAction extends SaveScriptAction {

	/**
   *
   */
	private static final long serialVersionUID = 4625976481568821608L;

	@Override
	public void doAction(ActionEvent e) throws Exception {
		setLightBillUsed(false);
		super.doAction(e);
		SaleOrderBillForm billform = (SaleOrderBillForm) this.editor;
		CardKeyValue keyValue = new CardKeyValue(billform.getBillCardPanel());
		FeatureSelectUtil.clearAllRowValue(keyValue, SOConstant.AGGFFILEVO);
		// 王梓懿 2018-05-30 完成后刷新界面
		ReQuery2FixBlob.reFreshDate(model);
	}

	@Override
	public void doBeforAction() {
		super.doBeforAction();
		SaleOrderHistoryVO vos = (SaleOrderHistoryVO) this.editor.getValue();
		this.checkBodyData(vos);
		SaleOrderBillForm billform = (SaleOrderBillForm) this.editor;
		CardKeyValue keyValue = new CardKeyValue(billform.getBillCardPanel());
		PfUserObject pfUserObj = this.getFlowContext().getUserObj();
		pfUserObj = pfUserObj == null ? new PfUserObject() : pfUserObj;
		SaleOrderUserObject userobj = (SaleOrderUserObject) (pfUserObj
				.getUserObject() == null ? new SaleOrderUserObject()
				: pfUserObj.getUserObject());
		Map<String, AggFFileVO> aggffilevomap = FeatureSelectUtil
				.getAllRowAggFFileVO(keyValue);
		if (aggffilevomap.size() > 0) {
			userobj.setAggffilevomap(aggffilevomap);
		}
		pfUserObj.setUserObject(userobj);
		this.getFlowContext().setUserObj(pfUserObj);

	}

	// add by wangshu6 for 销售订单修订支持审批流 修订保存
	protected void fillUpContext(PFlowContext context) {
		super.fillUpContext(context);
		// 不要进行加锁，后台自己进行了加锁和校验
		context.getEParam().put(PfUtilBaseTools.PARAM_NO_LOCK, UFBoolean.TRUE);
	}

	private void checkBodyData(SaleOrderHistoryVO vo) {
		SaleOrderHistoryBVO[] bvos = vo.getChildrenVO();
		if (bvos == null || bvos.length == 0) {
			ExceptionUtils
					.wrappBusinessException(nc.vo.ml.NCLangRes4VoTransl
							.getNCLangRes().getStrByID("4006011_0",
									"04006011-0020")/* @res "表体数据为空，不允许保存。" */);
		}
	}

	@Override
	protected void processReturnObj(Object[] pretObj) throws Exception {
		// 把单据设为新增态
		this.model.setAppUiState(AppUiState.ADD);
		super.processReturnObj(pretObj);
	}

	// end

	@Override
	protected boolean isResume(IResumeException resumeInfo) {
		return ResumeExceptionUIProcessUtils.isResume(resumeInfo,
				getFlowContext());
	}

	/**
	 * @Description:由于增加了<b>修改保存</b> <p>
	 *                               原生保存和保存提交只能在修订的时候使用
	 *                               </p>
	 *                               <b>修改要用专用的修改保存</b>
	 * @author 王梓懿
	 */
	@Override
	protected boolean isActionEnable() {
		// TODO 自动生成的方法存根
		boolean iseidtable = (this.model.getUiState() == UIState.EDIT)
				&& (this.model.getSelectedData() != null);
		if (iseidtable) {

			SaleOrderHistoryVO vo = (SaleOrderHistoryVO) this.editor.getValue();
			Object value = vo.getParentVO().getAttributeValue("agdef1");
			// 为修改做falg
			if (value != null && "修改".equals(value)) {
				return false;
			}
		}
		return super.isActionEnable();
	}
}
