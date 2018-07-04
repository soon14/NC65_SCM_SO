package nc.vo.so.m32.viewvo;

import nc.vo.et.fgjsdld.AggFgjsdldHeadVO;
import nc.vo.et.fgjsdld.FgjsdldBodyVO;
import nc.vo.et.fgjsdld.FgjsdldHeadVO;
import nc.vo.pubapp.pattern.model.entity.view.AbstractDataView;
import nc.vo.pubapp.pattern.model.meta.entity.view.DataViewMetaFactory;
import nc.vo.pubapp.pattern.model.meta.entity.view.IDataViewMeta;

class ET02ViewVO extends AbstractDataView {

	/**
	 * 
	 */
	private static final long serialVersionUID = 4050758996080486991L;

	@Override
	public IDataViewMeta getMetaData() {
		// TODO 自动生成的方法存根
		return DataViewMetaFactory.getInstance().getBillViewMeta(
				AggFgjsdldHeadVO.class);
	}
	public AggFgjsdldHeadVO changeToBill() {
		AggFgjsdldHeadVO bill = new AggFgjsdldHeadVO();
		bill.setParent(this.getHead());
		FgjsdldBodyVO[] items = new FgjsdldBodyVO[] { this.getItem() };
		bill.setChildrenVO(items);
		return bill;
	}
	
	//主表
	public FgjsdldHeadVO getHead() {
		return (FgjsdldHeadVO) this.getVO(FgjsdldHeadVO.class);
	}

	public void setHead(FgjsdldHeadVO head) {
		this.setVO(head);
	}
	
	//子表
	public void setItem(FgjsdldBodyVO item) {
		this.setVO(item);
	}
	
	public FgjsdldBodyVO getItem() {
		return (FgjsdldBodyVO) this.getVO(FgjsdldBodyVO.class);
	}
	

}
