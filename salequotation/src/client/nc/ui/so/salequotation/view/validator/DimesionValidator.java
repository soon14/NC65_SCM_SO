package nc.ui.so.salequotation.view.validator;

import nc.bs.uif2.validation.ValidationFailure;
import nc.bs.uif2.validation.Validator;
import nc.ui.so.salequotation.view.SalequoBillForm;
import nc.vo.so.salequotation.entity.AggSalequotationHVO;
import nc.vo.so.salequotation.entity.SalequotationBVO;

public class DimesionValidator implements Validator {

  private SalequoBillForm editor;

  public SalequoBillForm getEditor() {
    return this.editor;
  }

  public void setEditor(SalequoBillForm editor) {
    this.editor = editor;
  }

  @Override
  public ValidationFailure validate(Object obj) {
    return this.checkDimensionUnique();
  }

  private ValidationFailure checkDimensionUnique() {
    ValidationFailure failure = null;
    AggSalequotationHVO aggVO =
        (AggSalequotationHVO) this.getEditor().getValue();
    SalequotationBVO[] childrenVO = aggVO.getChildrenVO();
    if (childrenVO == null || childrenVO.length == 0) {
      return null;
    }
    return failure;
  }
}
