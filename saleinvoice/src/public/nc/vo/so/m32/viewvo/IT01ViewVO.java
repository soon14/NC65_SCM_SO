package nc.vo.so.m32.viewvo;

import nc.vo.it.ctjsd.AggCtjsdHeadVO;
import nc.vo.it.ctjsd.CtjsdHeadVO;
import nc.vo.it.ctjsd.CtjsdbBodyVO;
import nc.vo.pubapp.pattern.model.entity.view.AbstractDataView;
import nc.vo.pubapp.pattern.model.meta.entity.view.DataViewMetaFactory;
import nc.vo.pubapp.pattern.model.meta.entity.view.IDataViewMeta;

class IT01ViewVO extends AbstractDataView {

	/**
	 * 
	 */
	private static final long serialVersionUID = 7928422618384995789L;

	@Override
	public IDataViewMeta getMetaData() {
		// TODO 自动生成的方法存根
		return DataViewMetaFactory.getInstance().getBillViewMeta(
				AggCtjsdHeadVO.class);
	}
	public AggCtjsdHeadVO changeToBill() {
		AggCtjsdHeadVO bill = new AggCtjsdHeadVO();
		bill.setParent(this.getHead());
		CtjsdbBodyVO[] items = new CtjsdbBodyVO[] { this.getItem() };
		bill.setChildrenVO(items);
		return bill;
	}
	
	//主表
	public CtjsdHeadVO getHead() {
		return (CtjsdHeadVO) this.getVO(CtjsdHeadVO.class);
	}

	public void setHead(CtjsdHeadVO head) {
		this.setVO(head);
	}
	
	//子表
	public void setItem(CtjsdbBodyVO item) {
		this.setVO(item);
	}
	
	public CtjsdbBodyVO getItem() {
		return (CtjsdbBodyVO) this.getVO(CtjsdbBodyVO.class);
	}
	

}
