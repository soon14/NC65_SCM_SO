package nc.vo.so.m32.viewvo;

import nc.vo.lm.jkyldlbzdzb1.AggJkyldlbzdzb1HeadVO;
import nc.vo.lm.jkyldlbzdzb1.Jkyldlbzdzb1HeadVO;
import nc.vo.lm.jkyldlbzdzb1.Jkyldlbzdzbb1BodyVO;
import nc.vo.pubapp.pattern.model.entity.view.AbstractDataView;
import nc.vo.pubapp.pattern.model.meta.entity.view.DataViewMetaFactory;
import nc.vo.pubapp.pattern.model.meta.entity.view.IDataViewMeta;

class LM40ViewVO extends AbstractDataView {

	/**
	 * 
	 */
	private static final long serialVersionUID = -6360697740496834980L;

	@Override
	public IDataViewMeta getMetaData() {
		// TODO 自动生成的方法存根
		return DataViewMetaFactory.getInstance().getBillViewMeta(
				AggJkyldlbzdzb1HeadVO.class);
	}
	public AggJkyldlbzdzb1HeadVO changeToBill() {
		AggJkyldlbzdzb1HeadVO bill = new AggJkyldlbzdzb1HeadVO();
		bill.setParent(this.getHead());
		Jkyldlbzdzbb1BodyVO[] items = new Jkyldlbzdzbb1BodyVO[] { this.getItem() };
		bill.setChildrenVO(items);
		return bill;
	}
	
	//主表
	public Jkyldlbzdzb1HeadVO getHead() {
		return (Jkyldlbzdzb1HeadVO) this.getVO(Jkyldlbzdzb1HeadVO.class);
	}

	public void setHead(Jkyldlbzdzb1HeadVO head) {
		this.setVO(head);
	}
	
	//子表
	public void setItem(Jkyldlbzdzbb1BodyVO item) {
		this.setVO(item);
	}
	
	public Jkyldlbzdzbb1BodyVO getItem() {
		return (Jkyldlbzdzbb1BodyVO) this.getVO(Jkyldlbzdzbb1BodyVO.class);
	}
	

}
