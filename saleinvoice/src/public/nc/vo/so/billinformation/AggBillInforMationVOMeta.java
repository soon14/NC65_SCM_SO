package nc.vo.so.billinformation;

import nc.vo.pubapp.pattern.model.meta.entity.bill.AbstractBillMeta;

public class AggBillInforMationVOMeta extends AbstractBillMeta{
	
	public AggBillInforMationVOMeta(){
		this.init();
	}
	
	private void init() {
		this.setParent(nc.vo.so.billinformation.BillInforMationVO.class);
	}
}