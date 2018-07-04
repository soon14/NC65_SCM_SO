package nc.vo.so.billinformation;

import nc.vo.pubapp.pattern.model.entity.bill.AbstractBill;
import nc.vo.pubapp.pattern.model.meta.entity.bill.BillMetaFactory;
import nc.vo.pubapp.pattern.model.meta.entity.bill.IBillMeta;

@nc.vo.annotation.AggVoInfo(parentVO = "nc.vo.so.billinformation.BillInforMationVO")

public class AggBillInforMationVO extends AbstractBill {
	
	  @Override
	  public IBillMeta getMetaData() {
	  	IBillMeta billMeta =BillMetaFactory.getInstance().getBillMeta(AggBillInforMationVOMeta.class);
	  	return billMeta;
	  }
	    
	  @Override
	  public BillInforMationVO getParentVO(){
	  	return (BillInforMationVO)this.getParent();
	  }
	  
}