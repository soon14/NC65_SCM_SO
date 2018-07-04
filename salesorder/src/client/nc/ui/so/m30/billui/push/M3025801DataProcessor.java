package nc.ui.so.m30.billui.push;

import nc.funcnode.ui.FuncletInitData;
import nc.ui.pubapp.billref.dest.TransferViewProcessor;
import nc.ui.pubapp.uif2app.model.DefaultFuncNodeInitDataListener.IInitDataProcessor;
import nc.ui.pubapp.uif2app.view.BillForm;
import nc.ui.uif2.UIState;
import nc.vo.it.m5801.entity.ContractVO;
import nc.vo.so.m30.entity.SaleOrderVO;

public class M3025801DataProcessor implements IInitDataProcessor {
	private TransferViewProcessor transferProcessor;

	  public TransferViewProcessor getTransferProcessor() {
	    return this.transferProcessor;
	  }
	  	
	@Override
	public void process(FuncletInitData data) {
		
		SaleOrderVO[] aggVOs=(SaleOrderVO[]) data.getInitData();
		
		//…Ë÷√model◊¥Ã¨
		this.getTransferProcessor().getBillForm().getModel().setUiState(UIState.ADD);
		BillForm editor = this.getTransferProcessor().getBillForm();
	    editor.getModel().initModel(null);
	    this.getTransferProcessor().processBillTransfer(aggVOs);
		
	}
	public void setTransferProcessor(TransferViewProcessor transferProcessor) {
	    this.transferProcessor = transferProcessor;
	  }
}
