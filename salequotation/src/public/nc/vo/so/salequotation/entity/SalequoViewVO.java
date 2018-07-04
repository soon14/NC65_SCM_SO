package nc.vo.so.salequotation.entity;

import nc.vo.pubapp.pattern.model.entity.view.AbstractDataView;
import nc.vo.pubapp.pattern.model.meta.entity.view.DataViewMetaFactory;
import nc.vo.pubapp.pattern.model.meta.entity.view.IDataViewMeta;
import nc.vo.pubapp.pattern.model.meta.entity.vo.PseudoColumnAttribute;

public class SalequoViewVO extends AbstractDataView {

  /**
   * 
   */
  private static final long serialVersionUID = 4581823863247419635L;

  public AggSalequotationHVO changeTOBill() {
    AggSalequotationHVO aggVO = new AggSalequotationHVO();
    aggVO.setParent(this.getVO(SalequotationHVO.class));
    SalequotationBVO bvo =
        (SalequotationBVO) this.getVO(SalequotationBVO.class);
    bvo.setAttributeValue(PseudoColumnAttribute.PSEUDOCOLUMN,
        Integer.valueOf(0));
    aggVO.setChildren(SalequotationBVO.class, new SalequotationBVO[] {
      bvo
    });
    return aggVO;
  }

  @Override
  public IDataViewMeta getMetaData() {
    return DataViewMetaFactory.getInstance().getBillViewMeta(
        AggSalequotationHVO.class);
  }
}
