package nc.ui.so.m32.billui.action;

import java.awt.event.ActionEvent;
import java.util.ArrayList;
import java.util.List;

import org.apache.commons.lang.StringUtils;

import nc.bs.framework.common.NCLocator;
import nc.bs.pub.pf.PfUtilTools;
import nc.desktop.ui.WorkbenchEnvironment;
import nc.funcnode.ui.FuncletInitData;
import nc.funcnode.ui.FuncletWindowLauncher;
import nc.itf.lm.IYffyjsdMaintain;
import nc.itf.org.IOrgConst;
import nc.itf.scmpub.reference.uap.group.SysInitGroupQuery;
import nc.itf.scmpub.reference.uap.org.OrgUnitPubService;
import nc.itf.scmpub.reference.uap.pf.PfServiceScmUtil;
import nc.itf.so.m32.ISaleInvoiceMaintain;
import nc.itf.uap.IUAPQueryBS;
import nc.jdbc.framework.processor.ColumnProcessor;
import nc.md.data.access.NCObject;
import nc.md.persist.framework.MDPersistenceService;
import nc.ui.dm.pub.parapanel.TransferViewProcessor;
import nc.ui.pubapp.uif2app.actions.AbstractReferenceAction;
import nc.ui.pubapp.uif2app.actions.pflow.ApproveStatus;
import nc.ui.pubapp.uif2app.view.ShowUpableBillForm;
import nc.ui.uif2.model.AbstractAppModel;
import nc.vo.arap.receivable.AggReceivableBillVO;
import nc.vo.ecpubapp.pattern.exception.ExceptionUtils;
import nc.vo.org.OrgVO;
import nc.vo.pp.m28.entity.PriceAuditHeaderVO;
import nc.vo.pub.AggregatedValueObject;
import nc.vo.pub.BusinessException;
import nc.vo.pub.CircularlyAccessibleValueObject;
import nc.vo.pubapp.pattern.tool.performance.DeepCloneTool;
import nc.vo.sm.funcreg.FuncRegisterVO;
import nc.vo.so.m32.entity.SaleInvoiceBVO;
import nc.vo.so.m32.entity.SaleInvoiceHVO;
import nc.vo.so.m32.entity.SaleInvoiceVO;
import nc.bs.pubapp.pf.util.ApproveFlowUtil;
import nc.ui.pubapp.uif2app.AppUiState;

public class NoSaleInvoiceSendysdAction extends AbstractReferenceAction {
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 2599372460525675314L;

	private ShowUpableBillForm editor;
	
	private AbstractAppModel model;

	//转单的transfer
	private TransferViewProcessor transferProcessor;
	
	public TransferViewProcessor getTransferProcessor() {
		return transferProcessor;
	}
	public void setTransferProcessor(TransferViewProcessor transferProcessor) {
		this.transferProcessor = transferProcessor;
	}
	public ShowUpableBillForm getEditor() {
		return editor;
	}
	public void setEditor(ShowUpableBillForm editor) {
		this.editor = editor;
	}
	
	public AbstractAppModel getModel() {
		return this.model;
	}

	public void setModel(AbstractAppModel model) {
	    this.model = model;
	    model.addAppEventListener(this);
	}
	
	@Override
	protected boolean isActionEnable() {
		// TODO 自动生成的方法存根
		Object selectedData = this.model.getSelectedData();
		int status = ApproveStatus.FREE;
		boolean isEnable =false;
		if (selectedData != null) {
			NCObject obj = NCObject.newInstance(selectedData);
			if (obj != null) {
				status = ApproveFlowUtil.getBillStatus(obj).intValue();
			}
			SaleInvoiceVO saleInvoiceVO = (SaleInvoiceVO) selectedData;
			String vdef8 = null==saleInvoiceVO.getParentVO().getAttributeValue("vdef8")?"":saleInvoiceVO.getParentVO().getAttributeValue("vdef8").toString();
			if("1".equals(vdef8)){
				isEnable = true;
//				return true;
			}
		}
		if(isEnable){
			// 审批中或审批通过时，发送按钮可用
			isEnable = this.model.getAppUiState() == AppUiState.NOT_EDIT
					&& selectedData != null
					&& (status == ApproveStatus.APPROVED );
		}
		return isEnable;
//		return true;
	}

	@Override
	public void doAction(ActionEvent e) throws Exception {
		// 不支持在列表下，选中多行记录需要用getModel().getSelectedOperaDatas()选中多个vo
		SaleInvoiceVO selectedVO = (SaleInvoiceVO) this.getModel().getSelectedData();
		if (null == selectedVO ||null==selectedVO.getChildren(SaleInvoiceBVO.class)) {
			return;
		}
		// 连续推单的情况下，界面无法刷新，需要从数据库中查询数据，进行推单,否则订单保存会并发
		ISaleInvoiceMaintain isaleInvoice = NCLocator.getInstance().lookup(ISaleInvoiceMaintain.class);
	    String id = (String) selectedVO.getParentVO().getAttributeValue("csaleinvoiceid");
	    String sql = "select billstatus from ar_recbill where pk_recbill = (select pk_recbill from ar_recitem where top_billid='"+id+"')";
		IUAPQueryBS uapbs = NCLocator.getInstance().lookup(IUAPQueryBS.class);
		Object qresult = uapbs.executeQuery(sql, new ColumnProcessor());
		if(qresult!=null&&"-1".equals(qresult.toString())){
			 String sqls = "select pk_recbill from ar_recitem where top_billid='"+id+"'";
			 IUAPQueryBS uapbss = NCLocator.getInstance().lookup(IUAPQueryBS.class);
			 Object qresults = uapbss.executeQuery(sqls, new ColumnProcessor());
			 isaleInvoice.deteleysd(qresults.toString());
			 isaleInvoice.updatexsfp(qresults.toString());
		}
		
	  }

	

}
