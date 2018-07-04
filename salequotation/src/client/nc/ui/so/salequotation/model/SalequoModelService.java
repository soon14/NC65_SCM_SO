package nc.ui.so.salequotation.model;

import nc.bs.framework.common.NCLocator;
import nc.itf.so.m4310trantype.IM4310TranTypeService;
import nc.itf.so.salequotation.ISalequotationQry;
import nc.ui.pubapp.uif2app.model.IAggVoLazilyLoader;
import nc.vo.pub.BusinessException;
import nc.vo.pub.CircularlyAccessibleValueObject;
import nc.vo.so.m4310trantype.entity.M4310TranTypeVO;

public class SalequoModelService implements IAggVoLazilyLoader {

  private ISalequotationQry iSalequotationQry;

  private IM4310TranTypeService m4310TranTypeService;

  public M4310TranTypeVO[] queryAllTranType(String pkGroup, String pkBilltype)
      throws BusinessException {
    return this.getM4310TranTypeService().queryAllTranType(pkGroup, pkBilltype);
  }

  @Override
  public CircularlyAccessibleValueObject[] queryChildrenByParentPk(
      String parentPk) throws Exception {
    return this.getISalequotationQry().queryBodyByHeadPk(parentPk);
  }

  public M4310TranTypeVO queryTranType(String pk_group, String pk_billtypecode)
      throws BusinessException {
    return this.getM4310TranTypeService().queryTranType(pk_group,
        pk_billtypecode);
  }

  private ISalequotationQry getISalequotationQry() {
    if (this.iSalequotationQry == null) {
      this.iSalequotationQry =
          NCLocator.getInstance().lookup(ISalequotationQry.class);
    }
    return this.iSalequotationQry;
  }

  private IM4310TranTypeService getM4310TranTypeService() {
    if (this.m4310TranTypeService == null) {
      this.m4310TranTypeService =
          NCLocator.getInstance().lookup(IM4310TranTypeService.class);
    }
    return this.m4310TranTypeService;
  }
}
