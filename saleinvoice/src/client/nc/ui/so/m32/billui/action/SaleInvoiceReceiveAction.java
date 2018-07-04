package nc.ui.so.m32.billui.action;

import java.awt.event.ActionEvent;

import nc.bs.framework.common.NCLocator;
import nc.pubitf.so.saleinvoice.api.ISaleinvoiceReceive;
import nc.ui.pubapp.uif2app.actions.AbstractReferenceAction;
import nc.ui.uif2.ShowStatusBarMsgUtil;
import nc.ui.uif2.model.AbstractAppModel;
import nc.vo.ecpubapp.pattern.exception.ExceptionUtils;

public class SaleInvoiceReceiveAction extends AbstractReferenceAction {

//	@Override
//	protected boolean isActionEnable() {
//		// TODO 自动生成的方法存根
//		return true;
//	}
	private AbstractAppModel model;
	
	public AbstractAppModel getModel() {
		return this.model;
	}

	public void setModel(AbstractAppModel model) {
	    this.model = model;
	    model.addAppEventListener(this);
	}

	@Override
	public void doAction(ActionEvent e) throws Exception {
		// TODO 自动生成的方法存根
		ISaleinvoiceReceive is = NCLocator.getInstance().lookup(
	    		ISaleinvoiceReceive.class);
		 //向接口发送数据
	    String b = is.receiveMsgCGAG000003();
	    
	    // 接口数据发送成功后写入中间表
	    if ("".equals(b)) {
	    	ShowStatusBarMsgUtil.showStatusBarMsg("数据接收成功!",model.getContext());

		}else {
			try {
				nc.vo.pubapp.pattern.exception.ExceptionUtils.marsh(null);
			} catch (Exception ex) {
				// TODO 自动生成的 catch 块
				ExceptionUtils.wrappBusinessException(b);
			}
		}

	}

}
