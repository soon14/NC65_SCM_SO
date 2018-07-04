/**
 *
 */
package nc.ui.so.m30.billui.action;

import java.awt.event.ActionEvent;
import java.util.HashMap;
import java.util.Map;

import nc.bs.framework.common.NCLocator;
import nc.desktop.ui.WorkbenchEnvironment;
import nc.itf.pubapp.pub.exception.IResumeException;
import nc.itf.so.m30.ISaleOrgPubService;
import nc.ui.ml.NCLangRes;
import nc.ui.pub.beans.MessageDialog;
import nc.ui.pubapp.uif2app.actions.RefreshSingleAction;
import nc.ui.pubapp.uif2app.actions.pflow.SaveScriptAction;
import nc.ui.scmpub.util.ResumeExceptionUIProcessUtils;
import nc.ui.so.m30.billui.view.SaleOrderBillForm;
import nc.ui.so.pub.keyvalue.CardKeyValue;
import nc.ui.uif2.ShowStatusBarMsgUtil;
import nc.ui.uif2.UIState;
import nc.vo.bd.feature.ffile.entity.AggFFileVO;
import nc.vo.pub.lang.UFBoolean;
import nc.vo.pub.lang.UFDouble;
import nc.vo.pubapp.AppContext;
import nc.vo.pubapp.pattern.exception.ExceptionUtils;
import nc.vo.pubapp.pattern.pub.MathTool;
import nc.vo.pubapp.pattern.pub.PubAppTool;
import nc.vo.pubapp.pflow.PfUserObject;
import nc.vo.so.m30.entity.OffsetTempVO;
import nc.vo.so.m30.entity.SaleOrderBVO;
import nc.vo.so.m30.entity.SaleOrderHVO;
import nc.vo.so.m30.entity.SaleOrderUserObject;
import nc.vo.so.m30.entity.SaleOrderVO;
import nc.vo.so.m30.sobalance.entity.SoBalanceVO;
import nc.vo.so.m30.util.FeatureSelectUtil;
import nc.vo.so.m30.util.SaleOrderClientContext;
import nc.vo.so.m30trantype.entity.M30TranTypeVO;
import nc.vo.so.pub.SOConstant;
import nc.vo.so.pub.enumeration.BillStatus;

/**
 * 销售订单保存逻辑
 * 
 * @since 6.0
 * @version 2011-12-28 上午10:26:42
 * @author fengjb
 */
public class SaleOrderSaveAction extends SaveScriptAction {

	private static final long serialVersionUID = -3977967248003982108L;

	private RefreshSingleAction refreshAction;

	/**
	 * 构造方法
	 */
	public SaleOrderSaveAction() {
		super();
	}

	@Override
	public void doAction(ActionEvent e) throws Exception {
		SaleOrderVO saleorder = (SaleOrderVO) this.editor.getValue();
		SaleOrderBillForm billform = (SaleOrderBillForm) this.editor;
		CardKeyValue keyValue = new CardKeyValue(billform.getBillCardPanel());
		this.checkOverPurchase(saleorder);

		if (this.getModel().getUiState() == UIState.EDIT) {
			int index = this.getModel().findBusinessData(saleorder);
			if (index == -1) {
				ExceptionUtils
						.wrappBusinessException(nc.vo.ml.NCLangRes4VoTransl
								.getNCLangRes().getStrByID("4006011_0",
										"04006011-0019")/*
														 * @res
														 * "修改保存时，获取前台差异VO出错。"
														 */);
			}
			// 订单收款限额不能小于实际预收款金额
			this.checkGathering(saleorder);
			// 审批不同过状态重置为自由态
			this.reSetBillStatusForNoPass(saleorder);
		}
		// 前台增加表头ID，防止网络出问题时重复保存单据的情况
		if (this.getModel().getUiState() == UIState.ADD) {
			String hID = keyValue.getHeadStringValue(SaleOrderHVO.CSALEORDERID);
			if (PubAppTool.isNull(hID)) {
				ISaleOrgPubService service = NCLocator.getInstance().lookup(
						ISaleOrgPubService.class);
				String[] ids = service.getOIDArray(1);
				keyValue.setHeadValue(SaleOrderHVO.CSALEORDERID, ids[0]);
			}
		}
		// 赠品兑付类型不能为空
		this.checkCarsubtypeid(saleorder, keyValue);
		super.doAction(e);
		this.doAfterAction();
	}

	/**
	 * @param saleorder
	 *            保存前校验
	 */
	private void checkOverPurchase(SaleOrderVO saleorder) {
		// TODO 自动生成的方法存根
		// TODO 检查表体的超采和少采给出提示
		// 提示信息
		String msg = "第";
		SaleOrderBVO[] bvos = saleorder.getChildrenVO();
		for (SaleOrderBVO saleOrderBVO : bvos) {
			String rowNo = saleOrderBVO.getCrowno();
			UFDouble nastNum = saleOrderBVO.getNastnum();
			if(saleOrderBVO.getAttributeValue("plan_num")==null){
				ExceptionUtils.wrappBusinessException("表体物料信息页签 【计划数量】不能为空");
			}
			Integer planNum = (int)saleOrderBVO.getAttributeValue("plan_num");
			int nastnum = nastNum.intValue();
			if (planNum!=null&&nastnum != planNum.intValue()) {
				msg += "[" + rowNo + "] ";
			}
		}
		if(!msg.equals("第")){
			
		msg += "行 存在超采或少采！";
		MessageDialog.showWarningDlg(WorkbenchEnvironment.getInstance()
	              .getWorkbench().getParent(), "提示", msg);
		}
	}

	/**
	 * 保存后事件处理
	 */
	private void doAfterAction() {
		// 界面用mix("本次收款金额"||"价税合计")更新"实际收款金额",并清空"本次收款金额"
		SaleOrderBillForm billform = (SaleOrderBillForm) this.editor;
		CardKeyValue keyValue = new CardKeyValue(billform.getBillCardPanel());
		UFDouble thisreceivemny = keyValue
				.getHeadUFDoubleValue(SaleOrderHVO.NTHISRECEIVEMNY);
		UFDouble receivedmny = keyValue
				.getHeadUFDoubleValue(SaleOrderHVO.NRECEIVEDMNY);
		UFDouble totalorigmny = keyValue
				.getHeadUFDoubleValue(SaleOrderHVO.NTOTALORIGMNY);

		UFDouble receivedmny_new = MathTool.add(thisreceivemny, receivedmny);
		// 本次收款金额
		if (MathTool.absCompareTo(receivedmny_new, totalorigmny) > 0) {
			receivedmny_new = totalorigmny;
		}

		keyValue.setHeadValue(SaleOrderHVO.NRECEIVEDMNY, receivedmny_new);
		keyValue.setHeadValue(SaleOrderHVO.NTHISRECEIVEMNY, null);
		FeatureSelectUtil.clearAllRowValue(keyValue, SOConstant.AGGFFILEVO);
	}

	@Override
	public void doBeforAction() {
		super.doBeforAction();
		SaleOrderBillForm billform = (SaleOrderBillForm) this.editor;
		if (null != billform) {
			CardKeyValue keyValue = new CardKeyValue(
					billform.getBillCardPanel());
			PfUserObject pfUserObj = this.getFlowContext().getUserObj();
			pfUserObj = pfUserObj == null ? new PfUserObject() : pfUserObj;
			SaleOrderUserObject userobj = (SaleOrderUserObject) (pfUserObj
					.getUserObject() == null ? new SaleOrderUserObject()
					: pfUserObj.getUserObject());
			// 费用冲抵
			OffsetTempVO tempvo = billform.getTempvo();
			userobj.setOffsetTempVO(tempvo);
			// 订单核销
			SoBalanceVO sobalancevo = billform.getSobalancevo();
			userobj.setSoBalanceVO(sobalancevo);
			// 本次收款金额
			UFDouble thisGatheringMny = billform.getThisGatheringMny();
			userobj.setThisGatheringMny(thisGatheringMny);
			Map<String, AggFFileVO> aggffilevomap = FeatureSelectUtil
					.getAllRowAggFFileVO(keyValue);
			if (aggffilevomap.size() > 0) {
				userobj.setAggffilevomap(aggffilevomap);
			}
			userobj.setIsclientsave(true);
			pfUserObj.setUserObject(userobj);
			this.getFlowContext().setUserObj(pfUserObj);
		}
	}

	private void checkGathering(SaleOrderVO saleorder) {
		SaleOrderHVO hvo = saleorder.getParentVO();
		if (null == hvo) {
			return;
		}
		// 收款限额控制预收
		UFBoolean bpreceiveflag = hvo.getBpreceiveflag();
		// 订单收款限额
		UFDouble npreceivequota = hvo.getNpreceivequota();
		// 实际预收款金额
		UFDouble npreceivemny = hvo.getNpreceivemny();

		if (bpreceiveflag.booleanValue()
				&& MathTool.compareTo(npreceivequota, npreceivemny) < 0) {
			StringBuilder errMsg = new StringBuilder();
			errMsg.append(nc.vo.ml.NCLangRes4VoTransl.getNCLangRes()
					.getStrByID("4006011_0", "04006011-0283")/* 单据号： */);
			errMsg.append(hvo.getVbillcode());
			errMsg.append(nc.vo.ml.NCLangRes4VoTransl.getNCLangRes()
					.getStrByID("4006011_0", "04006011-0468")/*
															 * 订单收款限额小于实际预收款金额,
															 * 请重新调整价税合计！
															 */);
			ExceptionUtils.wrappBusinessException(errMsg.toString());
		}
	}

	private void checkCarsubtypeid(SaleOrderVO saleorder, CardKeyValue keyValue) {
		SaleOrderHVO hvo = saleorder.getParentVO();
		if (null == hvo) {
			return;
		}
		SaleOrderBillForm billform = (SaleOrderBillForm) this.editor;
		String tranTypeCode = keyValue
				.getHeadStringValue(SaleOrderHVO.VTRANTYPECODE);
		String pk_group = AppContext.getInstance().getPkGroup();
		SaleOrderClientContext cache = billform.getM30ClientContext();
		M30TranTypeVO m30transvo = cache.getTransType(tranTypeCode, pk_group);
		if (m30transvo != null) {
			if (m30transvo.getBlrgcashflag().booleanValue()) {
				// 赠品兑付类型
				String carsubtypeid = hvo.getCarsubtypeid();
				if (PubAppTool.isNull(carsubtypeid)) {
					ExceptionUtils.wrappBusinessException(NCLangRes
							.getInstance().getStrByID("4006011_0",
									"04006011-0506")/* 赠品兑付类型不允许为空。 */);
				}
			}
		}
	}

	/**
	 * 
	 * @return RefreshSingleAction
	 */
	public RefreshSingleAction getreFreshAction() {
		return this.refreshAction;
	}

	/**
	 * 
	 * @param refreshAction1
	 */
	public void setRefreshAction(RefreshSingleAction refreshAction1) {
		this.refreshAction = refreshAction1;
	}

	@Override
	protected boolean isResume(IResumeException resumeInfo) {
		return ResumeExceptionUIProcessUtils.isResume(resumeInfo,
				getFlowContext());
	}

	@Override
	protected Object[] processBefore(Object[] vos) {
		for (Object vo : vos) {
			SaleOrderVO saleordervo = (SaleOrderVO) vo;
			SaleOrderBVO[] bvos = saleordervo.getChildrenVO();
			if (bvos == null || bvos.length == 0) {

				ExceptionUtils
						.wrappBusinessException(nc.vo.ml.NCLangRes4VoTransl
								.getNCLangRes().getStrByID("4006011_0",
										"04006011-0020")/* @res "表体数据为空，不允许保存。" */);
			}
		}
		return vos;
	}

	private void reSetBillStatusForNoPass(SaleOrderVO vo) {
		if (vo.getParentVO().getFstatusflag().intValue() == BillStatus.NOPASS
				.getIntValue()) {
			vo.getParentVO().setFstatusflag(BillStatus.FREE.getIntegerValue());
			this.editor.setValue(vo);
		}
	}

}
