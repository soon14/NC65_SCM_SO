package nc.ui.so.m32.billui.action;

import java.awt.event.ActionEvent;
import java.util.List;

import nc.bs.framework.common.NCLocator;
import nc.itf.uap.IUAPQueryBS;
import nc.jdbc.framework.processor.ArrayListProcessor;
import nc.md.model.MetaDataException;
import nc.md.persist.framework.MDPersistenceService;
import nc.pubitf.arap.receivable.IArapReceivableBillPubService;
import nc.ui.pubapp.uif2app.view.ShowUpableBillForm;
import nc.ui.trade.business.HYPubBO_Client;
import nc.ui.uif2.NCAction;
import nc.ui.uif2.model.AbstractAppModel;
import nc.ui.pubapp.uif2app.model.BillManageModel;
import nc.vo.arap.receivable.ReceivableBillVO;
import nc.vo.pub.BusinessException;
import nc.vo.pub.VOStatus;
import nc.vo.pubapp.pattern.exception.ExceptionUtils;
import nc.vo.so.m32.entity.SaleInvoiceHVO;
import nc.vo.so.m32.entity.SaleInvoiceVO;

public class CancleSaleInvoiceToRecbill extends NCAction{
	/**
	 * 
	 */
	private static final long serialVersionUID = 5839581378058383198L;

	private ShowUpableBillForm editor;
	
	private AbstractAppModel model;
	public ShowUpableBillForm getEditor() {
		return editor;
	}
	public void setEditor(ShowUpableBillForm editor) {
		this.editor = editor;
	}
	public AbstractAppModel getModel() {
		return model;
	}
	public void setModel(AbstractAppModel model) {
		this.model = model;
		this.model.addAppEventListener(this);
	}
	
	public CancleSaleInvoiceToRecbill (){
		this.setBtnName("取消传应收");
		this.setCode("cancleRecbill");
	}
	@Override
	public void doAction(ActionEvent e) throws Exception {
		// TODO 自动生成的方法存根
		Object obj = this.getModel().getSelectedData();
		SaleInvoiceVO agg = (SaleInvoiceVO ) obj;
		SaleInvoiceHVO head = (SaleInvoiceHVO) agg.getParent();
		String hpk = head.getPrimaryKey();
		String Pk_recbill = queryPk_recbill(hpk);
		int has = queryWheatherApprove(Pk_recbill);
		if (has == 1) {
			ExceptionUtils.wrappBusinessException("生成的应收单已经审批，请取消审批再进行操作");
		} else {
			updateDrWithRecBill(hpk);
			HYPubBO_Client hy=new HYPubBO_Client();
			head.setVdef8("0");
			head.setStatus(VOStatus.UPDATED);
			head.setDr(0);
			hy.update(head);
			this.model.directlyUpdate(agg);
		}
	}
	
	/**
	 * @param pk_recbill
	 */
	private void updateDrWithRecBill(String hpk) {
		IArapReceivableBillPubService service=NCLocator.getInstance().lookup(IArapReceivableBillPubService.class);
		try {
			service.deleteBillBySourcePK(new String []{hpk});
		} catch (BusinessException e) {
			// TODO 自动生成的 catch 块
			e.printStackTrace();
		}

	}

	/**
	 * @param hpk
	 * @return
	 */
	private String queryPk_recbill(String hpk) {
		// TODO 自动生成的方法存根
		
		
		Object[]ReceivableBillItemVO=null;
			String sql="select pk_recbill from ar_recitem where top_billid	='"+hpk+"' and dr=0";
			
			try {
				ReceivableBillItemVO=	this.queryRecbillPK(sql);
			} catch (BusinessException e) {
				e.printStackTrace();
			}
		
		if (ReceivableBillItemVO==null||ReceivableBillItemVO[0]==null) {
			ExceptionUtils.wrappBusinessException("没有生成应收单，或应收单已经取消！");
		}
		return (String) ReceivableBillItemVO[0];
	}

	/**
	 * @param hpk
	 * @return
	 */
	private int queryWheatherApprove(String hpk) {

		// 查询主表的单据状态
		Object[]ReceivableBillItemVO=null;
		String sql="select billstatus from ar_recbill where pk_recbill	='"+hpk+"' and dr=0";
		
		try {
			ReceivableBillItemVO=	this.queryRecbillPK(sql);
		} catch (BusinessException e) {
			e.printStackTrace();
		}
		if (ReceivableBillItemVO==null||ReceivableBillItemVO[0]==null) {
			ExceptionUtils.wrappBusinessException("没有查询出对应应收单，检查该应收单是否被删除");

		}
		return (int) ReceivableBillItemVO[0];

	}
    @Override
	protected boolean isActionEnable() {
    	//多选则按钮不可用
		Object[] selectedRows = ((BillManageModel)this.model).getSelectedOperaDatas();
		if(selectedRows != null && selectedRows.length > 1){
			return false;
		}
    	
		SaleInvoiceVO bill = (SaleInvoiceVO)this.getModel().getSelectedData();
		if(bill == null){
			return false;
		}
		
	    // 审批通过状态 按钮可用
        Integer status = bill.getParentVO().getFstatusflag();
        if(status != 2){
        	return false;
        }

        /*
        //已推应收单标记 空值或0值 都是未推应收 则 取消推应收 按钮不可用
		String vdef8 = bill.getParentVO().getVdef8();
		if(vdef8 == null || "0".equals(vdef8)){
			return false;
		}
		*/
		
        String pk_bill = bill.getPrimaryKey();
    	Object[] ReceivableBillItemVO = null;
		String sql="select pk_recbill from ar_recitem where top_billid	='"+pk_bill+"' and dr=0";
		
		try {
			ReceivableBillItemVO=	this.queryRecbillPK(sql);
		} catch (BusinessException e) {
			e.printStackTrace();
		}
        if(ReceivableBillItemVO == null){
        	return false;
        }
	     
		
        return true;
	}
	// 通过sql语句 获取结果集
	@SuppressWarnings("unused")
	private Object[] queryRecbillPK(String sql) throws BusinessException {
		IUAPQueryBS uapQuery = (IUAPQueryBS) NCLocator.getInstance().lookup(nc.itf.uap.IUAPQueryBS.class);
		List<?> userList = (List<?>) uapQuery.executeQuery(sql, new ArrayListProcessor());
		if(userList==null||userList.isEmpty()){
		return null;	
		}
		Object[] obj = (Object[]) userList.get(userList.size()-1);
		return obj;
	}
}
