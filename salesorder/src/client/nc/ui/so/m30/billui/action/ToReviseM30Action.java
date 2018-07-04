package nc.ui.so.m30.billui.action;

import java.awt.Dimension;
import java.awt.event.ActionEvent;

import nc.vo.ftpub.res.FTActionCode;
import nc.vo.it.m5801.enumeration.BillStatus;
import nc.vo.pub.BusinessException;
import nc.vo.pub.pf.BillStatusEnum;
import nc.vo.pubapp.pattern.exception.ExceptionUtils;
import nc.vo.pubapp.pattern.pub.MathTool;
import nc.vo.sm.funcreg.FuncRegisterVO;
import nc.vo.so.m30.entity.SaleOrderHVO;
import nc.vo.so.m30.entity.SaleOrderVO;
import nc.vo.so.m30.revise.entity.SaleOrderHistoryVO;
import nc.vo.so.m30.util.Transfer30and30RVOTool;
import nc.vo.so.pub.util.StringUtil;
import nc.funcnode.ui.FuncletInitData;
import nc.funcnode.ui.FuncletWindowLauncher;
import nc.itf.so.m30.revise.ISaleOrderReviseMaintainApp;
import nc.itf.uap.bbd.func.IFuncRegisterQueryService;
import nc.bs.framework.common.NCLocator;
import nc.ui.ftpub.action.FTActionInitializer;
import nc.ui.so.m30.billui.view.SaleOrderBillForm;
import nc.ui.uif2.NCAction;
import nc.ui.uif2.UIState;
import nc.ui.uif2.model.AbstractAppModel;

/**
 * 转到修订
 * 
 * @since 6.5
 * @version 2018-03-29 下午15:47:55
 * @author 王梓懿
 */
public class ToReviseM30Action extends NCAction {

	/**
	 * 销售订单修订节点
	 */
	public static final String CTREVISE_FUNCODE = "40060302";

	private AbstractAppModel model;

	private SaleOrderBillForm editor;

	/**
	 * 构造子
	 */
	public ToReviseM30Action() {
		this.setBtnName("修订审批");
		this.setCode("reviseapprove");
	}

	@Override
	public void doAction(ActionEvent e) throws Exception {
		try {
			SaleOrderVO ctVO = (SaleOrderVO) this.model.getSelectedData();
			// 1.准备VO实现方法
			ISaleOrderReviseMaintainApp service = NCLocator.getInstance()
					.lookup(ISaleOrderReviseMaintainApp.class);
			SaleOrderHistoryVO[] destVO = service
					.queryM30ReviseApp(new String[] { ctVO.getParentVO()
							.getPrimaryKey() });

			FuncletInitData initData = new FuncletInitData();
			initData.setInitType(0);
			initData.setInitData(destVO[0]);
			// 修订节点
			IFuncRegisterQueryService funQuery = NCLocator.getInstance()
					.lookup(IFuncRegisterQueryService.class);
			FuncRegisterVO funvo = funQuery
					.queryFunctionByCode(ToReviseM30Action.CTREVISE_FUNCODE);
			// 打开修订节点
			Dimension size = null;
			FuncletWindowLauncher.openFuncNodeDialog(this.editor, funvo,
					initData, null, true, false, size, true);
		} catch (BusinessException be) {
			ExceptionUtils.wrappException(be);
		}
	}

	/**
	 * 设置billForm
	 * 
	 * @return ContractEditor
	 */
	public SaleOrderBillForm getEditor() {
		return this.editor;
	}

	/**
	 * 获得model
	 * 
	 * @return BillManageModel
	 */
	public AbstractAppModel getModel() {
		return this.model;
	}

	/**
	 * 获得billForm
	 * 
	 * @param editor
	 */
	public void setEditor(SaleOrderBillForm editor) {
		this.editor = editor;
	}

	/**
	 * 设置model
	 * 
	 * @param model
	 */
	public void setModel(AbstractAppModel model) {
		this.model = model;
		model.addAppEventListener(this);
	}

	@Override
	protected boolean isActionEnable() {
		boolean iseidtable = (this.model.getUiState() == UIState.NOT_EDIT)
				&& (this.model.getSelectedData() != null);
		if (iseidtable) {
			SaleOrderVO vo = (SaleOrderVO) this.getModel().getSelectedData();
			// 审批态的可以修改
			// modify by wangshu6 for 销售订单修订支持审批流 当修订未提交时 单据状态为自由、审批中，可以再次修订
			// Integer fstatusflag = vo.getParentVO().getFstatusflag();
			// if (!BillStatus.AUDIT.equalsValue(fstatusflag)) {
			// return false;
			// }
			// add by wangshu6 已经做过费用冲抵或赠品兑付的销售订单，禁止修订。2015-01-05
			if (!StringUtil
					.isEmptyTrimSpace(vo.getParentVO().getCarsubtypeid())) {
				iseidtable = false;
			} else if (!MathTool.isZero(vo.getParentVO().getNtotalorigsubmny())) {
				iseidtable = false;
			}
			if (vo.getParentVO().getFstatusflag() == 2
					|| vo.getParentVO().getFstatusflag() == 3) {
				// 根据修订历史的查询条件查出来的审批通过（2）和冻结的能用，其他的不可以
				iseidtable = true;
			} else {
				iseidtable = false;
			}
			// end
		}
		return iseidtable;
	}

	/**
	 * 
	 * @param fstatusflag
	 * @return b
	 */
	protected boolean tryMakeFlow(Integer fstatusflag) {
		return fstatusflag == null
				|| BillStatusEnum.APPROVED.equalsValue(fstatusflag);
	}
}
