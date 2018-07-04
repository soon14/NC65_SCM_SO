package nc.vo.so.m32.entity;

import nc.vo.lm.hyfjsd.AggSeasettHVO;
import nc.vo.lm.hyfjsd.SeasettHVO;
import nc.vo.lm.hyfjsd.SeasettbBVO;
import nc.vo.pubapp.pattern.model.entity.view.AbstractDataView;
import nc.vo.pubapp.pattern.model.meta.entity.view.DataViewMetaFactory;
import nc.vo.pubapp.pattern.model.meta.entity.view.IDataViewMeta;

public class UpHyfjsdViewVO extends AbstractDataView {


	/**
	 * 
	 */
	private static final long serialVersionUID = -3485044069470622099L;

	public AggSeasettHVO changeToBill() {
		AggSeasettHVO bill = new AggSeasettHVO();
		bill.setParent(this.getHead());
		SeasettbBVO[] items = new SeasettbBVO[] { this.getItem() };
		bill.setChildrenVO(items);
//		DetailInspectVO[] detailInspectVOitems = new DetailInspectVO[] { this.getDetailInspectVOItem() };
//		bill.setChildrenVO(detailInspectVOitems);
//		DetailPTVO[] detailPTVOitems = new DetailPTVO[] { this.getDetailPTVOItem() };
//		bill.setChildrenVO(detailPTVOitems);
//		DetailDOCVO[] detailDOCVOitems = new DetailDOCVO[] { this.getDetailDOCVOItem() };
//		bill.setChildrenVO(detailDOCVOitems);
		return bill;
	}
	
	//主表agg
	@Override
	public IDataViewMeta getMetaData() {
		return DataViewMetaFactory.getInstance().getBillViewMeta(
				AggSeasettHVO.class);
	}
	
	//主表
	public SeasettHVO getHead() {
		return (SeasettHVO) this.getVO(SeasettHVO.class);
	}

	public void setHead(SeasettHVO head) {
		this.setVO(head);
	}
	
	//子表
	public void setItem(SeasettbBVO item) {
		this.setVO(item);
	}
	
	public SeasettbBVO getItem() {
		return (SeasettbBVO) this.getVO(SeasettbBVO.class);
	}
	
	
}