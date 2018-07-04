package nc.ui.so.m32.billui.action;

import java.awt.event.ActionEvent;

import nc.bs.framework.common.NCLocator;
import nc.bs.pubapp.pf.util.ApproveFlowUtil;
import nc.md.data.access.NCObject;
import nc.pubitf.so.saleinvoice.api.ISaleinvoiceReceive;
import nc.ui.pubapp.uif2app.AppUiState;
import nc.ui.pubapp.uif2app.actions.AbstractReferenceAction;
import nc.ui.pubapp.uif2app.actions.pflow.ApproveStatus;
import nc.ui.uif2.ShowStatusBarMsgUtil;
import nc.ui.uif2.model.AbstractAppModel;
import nc.vo.ecpubapp.pattern.exception.ExceptionUtils;
import nc.vo.so.m32.entity.SaleInvoiceHVO;
import nc.vo.so.m32.entity.SaleInvoiceVO;

public class SaleInvoiceSendAction extends AbstractReferenceAction {
	
	private AbstractAppModel model;
	
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
		if (selectedData != null) {
			NCObject obj = NCObject.newInstance(selectedData);
			if (obj != null) {
				status = ApproveFlowUtil.getBillStatus(obj).intValue();
			}
		}

		// 审批中或审批通过时，发送按钮可用
		boolean isEnable = this.model.getAppUiState() == AppUiState.NOT_EDIT
				&& selectedData != null
				&& (status == ApproveStatus.APPROVED || status == ApproveStatus.NOPASS);

		return isEnable;
	}

	@Override
	public void doAction(ActionEvent e) throws Exception {
		// TODO 自动生成的方法存根
		//向接口发送数据
	    ISaleinvoiceReceive is = NCLocator.getInstance().lookup(
	    		ISaleinvoiceReceive.class);
	    Object obj =  getModel().getSelectedData();//获取单选
	    SaleInvoiceVO saleInvoiceVO = new SaleInvoiceVO();
	    saleInvoiceVO = (SaleInvoiceVO)NCObject.newInstance(obj).getContainmentObject();
	    SaleInvoiceHVO saleInvoiceHVO = saleInvoiceVO.getParentVO();
	  //是否发送过
	    String isSend = saleInvoiceHVO.getVdef18();
	    if ("1".equals(isSend)) {
	    	ExceptionUtils.wrappBusinessException("该条数据已经发送过");
		}
	    String vgoldtaxcode = saleInvoiceHVO.getVgoldtaxcode();
	    if (vgoldtaxcode==null&&saleInvoiceHVO.getVdef2()==null) {
	    	ExceptionUtils.wrappBusinessException("发票号为空");
		}
	    //向接口发送数据
	    boolean b = is.sendMsgAGCG000003(saleInvoiceVO);
	    
	    // 接口数据发送成功后写入中间表
	    if (b) {
	    	ShowStatusBarMsgUtil.showStatusBarMsg("数据发送成功!",model.getContext());

			is.sendZjbInsert(saleInvoiceVO);
		}else {
			try {
				nc.vo.pubapp.pattern.exception.ExceptionUtils.marsh(null);
			} catch (Exception ex) {
				// TODO 自动生成的 catch 块
				ExceptionUtils.wrappBusinessException("数据发送不成功");
			}
		}
	}

}
