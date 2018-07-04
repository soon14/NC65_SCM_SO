package nc.ui.so.salequotation.service;

import java.util.Set;
import java.util.HashSet;
import nc.bs.uif2.validation.DefaultValidationService;
import nc.bs.uif2.validation.ValidationException;
import nc.ui.so.salequotation.view.SalequoBillForm;
import nc.vo.pubapp.pattern.exception.ExceptionUtils;
import nc.vo.so.salequotation.entity.AggSalequotationHVO;
import nc.vo.so.salequotation.entity.SalequotationBVO;

public class SalequoValidationService extends DefaultValidationService {
  private SalequoBillForm editor;

  public SalequoBillForm getEditor() {
    return this.editor;
  }

  public void setEditor(SalequoBillForm editor) {
    this.editor = editor;
  }

  @Override
  public void validate(Object obj) throws ValidationException {
    this.getEditor().validateValue();
    this.checkBody();
    super.validate(obj);
  }

  private void checkBody() {
    AggSalequotationHVO aggVO =
        (AggSalequotationHVO) this.getEditor().getValue();
    SalequotationBVO[] childrenVO = aggVO.getChildrenVO();
    if (childrenVO == null || childrenVO.length == 0) {
      ExceptionUtils.wrappBusinessException(nc.vo.ml.NCLangRes4VoTransl.getNCLangRes().getStrByID("4006009_0","04006009-0017")/*@res "表体行不能为空"*/);
    }
    
    //控制表体“物料+报价单位”相同时，不能列多行(通过报价单交易类型属性“同一货物可以否列多行”来控制)
    /*String[] material_unit = new String[childrenVO.length];
    Set<String> materunitset = new HashSet<String>();  
    for(int i = 0; i<childrenVO.length; i++) {
      String material = childrenVO[i].getPk_material_v();
      String cqunit = childrenVO[i].getCqtunitid();
      if(material == null||cqunit == null){
        material_unit[i] = "";
      }
      else{
        material_unit[i] = material + cqunit;
      }
    }

    for(int i = 0; i<material_unit.length; i++) {
      if(!material_unit[i].equals("")) {
        if(materunitset.size() == 0) {
          materunitset.add(material_unit[i]);
        }
        else {
          if(materunitset.contains(material_unit[i])) {
            ExceptionUtils.wrappBusinessException(nc.vo.ml.NCLangRes4VoTransl.getNCLangRes().getStrByID("4006009_0","04006009-0071")@res "同一“物料+报价单位”不允许列多行"); 
          }
          else {
            materunitset.add(material_unit[i]);
          }
        }
      }
    }*/
  }
  
}