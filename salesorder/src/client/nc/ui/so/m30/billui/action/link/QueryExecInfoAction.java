package nc.ui.so.m30.billui.action.link;

import java.awt.event.ActionEvent;
import java.util.HashSet;
import java.util.Set;

import nc.bs.framework.common.NCLocator;
import nc.desktop.ui.WorkbenchEnvironment;
import nc.itf.so.m30.self.ISaleOrderMaintain;
import nc.ui.pubapp.uif2app.AppUiState;
import nc.ui.pubapp.uif2app.model.BillManageModel;
import nc.ui.scmpub.action.SCMActionInitializer;
import nc.ui.so.m30.billui.view.SaleOrderBillForm;
import nc.ui.so.pub.execinfo.ExecInfoReportDialog;
import nc.ui.uif2.NCAction;
import nc.vo.pub.BusinessException;
import nc.vo.pub.lang.UFDouble;
import nc.vo.pubapp.pattern.exception.ExceptionUtils;
import nc.vo.pubapp.pattern.pub.MathTool;
import nc.vo.scmpub.res.SCMActionCode;
import nc.vo.so.m30.entity.SaleOrderBVO;
import nc.vo.so.m30.entity.SaleOrderVO;
import nc.vo.so.pub.execinfo.ExecInfoReportVO;

/**
 * 销售订单执行情况查询
 * 
 * @since 6.0
 * @version 2010-12-8 下午04:50:40
 * @author 祝会征
 */
public class QueryExecInfoAction extends NCAction {
	private static final long serialVersionUID = -4490586267001887381L;

	private SaleOrderBillForm editor;

	private BillManageModel model;

	public QueryExecInfoAction() {
		super();
		SCMActionInitializer.initializeAction(this,
				SCMActionCode.SO_ORDERQUERYEXEC);
	}

	@Override
	public void doAction(ActionEvent e) throws Exception {
		if (this.model.getSelectedOperaRows().length > 1) {
			ExceptionUtils
					.wrappBusinessException(nc.vo.ml.NCLangRes4VoTransl
							.getNCLangRes().getStrByID("4006011_0",
									"04006011-0002")/* @res "请选择一行数据。" */);
		}
		ExecInfoReportVO[] reportVOs = this.getReportVOs();
		ExecInfoReportDialog dialog = new ExecInfoReportDialog(
				WorkbenchEnvironment.getInstance().getWorkbench().getParent(), true);
		dialog.orderExecReport(reportVOs);
		dialog.showModal();
	}

	public SaleOrderBillForm getEditor() {
		return this.editor;
	}

	public BillManageModel getModel() {
		return this.model;
	}

	public void setEditor(SaleOrderBillForm editor) {
		this.editor = editor;
	}

	public void setModel(BillManageModel model) {
		this.model = model;
		model.addAppEventListener(this);
	}

	@Override
	protected boolean isActionEnable() {
		AppUiState uistate = this.model.getAppUiState();
		// 新增或者转单新增按钮应该不可用
		if (uistate.equals(AppUiState.ADD)
				|| uistate.equals(AppUiState.TRANSFERBILL_ADD)) {
			return false;
		}
		boolean isEnable = this.model.getSelectedData() != null;

		return isEnable;
	}

	/*
	 * 根据id查询最新的销售订单信息
	 */
	private SaleOrderVO[] getNewvoInfosById() throws BusinessException {
		SaleOrderVO vo = (SaleOrderVO) this.editor.getModel().getSelectedData();
		String csaleorderhid = vo.getParentVO().getCsaleorderid();
		ISaleOrderMaintain service = NCLocator.getInstance().lookup(
				ISaleOrderMaintain.class);
		return service.querySaleorder(new String[] { csaleorderhid });
	}

	/*
	 * 销售订单vo转换成执情况vo
	 */
	private ExecInfoReportVO[] getReportVOs() {
		try {
			SaleOrderVO[] vos = this.getNewvoInfosById();
			 if (vos==null || vos.length==0) {
		      ExceptionUtils
		          .wrappBusinessException(nc.vo.ml.NCLangRes4VoTransl
		              .getNCLangRes().getStrByID("4006011_0",
		                  "04006011-0529")/* @res "销售订单已被删除，无法联查执行情况。" */);
		    }
			
			
			// SaleOrderHVO hvo = vos[0].getParentVO();
			SaleOrderBVO[] bvos = vos[0].getChildrenVO();
			Set<String> bidset = new HashSet<String>();
			for (SaleOrderBVO bvo : bvos) {
				bidset.add(bvo.getCsaleorderbid());
			}

			ExecInfoReportVO[] reptvos = new ExecInfoReportVO[bvos.length];
			for (int i = 0; i < bvos.length; i++) {
				SaleOrderBVO bvo = bvos[i];
				reptvos[i] = new ExecInfoReportVO();
				// 物料
				reptvos[i].setCmaterialid(bvo.getCmaterialvid());
				// 主单位
				reptvos[i].setCunitid(bvo.getCunitid());
				// 数量
				UFDouble num = this.getUFDoubleValue(bvo.getNnum());
				reptvos[i].setNnum(num);
				// 已开票数量
				UFDouble invoicenum = this.getUFDoubleValue(bvo
						.getNtotalinvoicenum());
				reptvos[i].setNtotalinvoicenum(invoicenum);
				// 已出库数量
				UFDouble outnum = this.getUFDoubleValue(bvo.getNtotaloutnum());
				reptvos[i].setNtotaloutnum(outnum);
				// 已发货数量
				UFDouble sendnum = this
						.getUFDoubleValue(bvo.getNtotalsendnum());
				reptvos[i].setNtotalsendnum(sendnum);

				// 应发数量
				UFDouble shouldsendnum = this.getUFDoubleValue(bvos[i]
						.getNtotalnotoutnum());
				reptvos[i].setShouldsendnum(shouldsendnum);
				// 已收款核销金额
				reptvos[i].setTotalpaymoney(this.getUFDoubleValue(bvo
						.getNtotalpaymny()));
				// 未收款核销金额
				reptvos[i].setNeedpaymoney(MathTool.sub(bvo.getNorigtaxmny(),
						bvo.getNtotalpaymny()));
				reptvos[i].setCcurrencyid(bvo.getCcurrencyid());
			}
			return reptvos;
		} catch (BusinessException e) {
			ExceptionUtils.wrappException(e);
		}
		return null;
	}

	private UFDouble getUFDoubleValue(UFDouble value) {
		if (value == null) {
			return UFDouble.ZERO_DBL;
		}
		return value;
	}
}
