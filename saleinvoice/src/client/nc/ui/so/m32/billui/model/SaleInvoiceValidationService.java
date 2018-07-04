package nc.ui.so.m32.billui.model;

import nc.bs.uif2.validation.ValidationException;
import nc.ui.pubapp.uif2app.view.BillForm;
import nc.ui.so.pub.model.SOValidationService;
import nc.vo.pubapp.pattern.exception.ExceptionUtils;
import nc.vo.so.m32.paravo.CombinCacheVO;

/**
 * 销售发票非空校验服务(处理合并编辑的情况)
 * 
 * @since 6.0
 * @version 2011-8-15 上午09:34:28
 * @author 么贵敬
 */
public class SaleInvoiceValidationService extends SOValidationService {

  private SaleInvoiceManageModel model;

  public SaleInvoiceManageModel getModel() {
    return this.model;
  }

  public void setModel(SaleInvoiceManageModel model) {
    this.model = model;
  }

  @Override
  public void validate(Object arg0) throws ValidationException {
    try {
      if (super.getEditor() != null && super.getEditor() instanceof BillForm) {
        CombinCacheVO cachevo = this.getModel().getCombinCacheVO();
        if (!cachevo.getBcombinflag()) {
          ((BillForm) super.getEditor()).validateValue();
        }
      }
    }
    catch (Exception e) {

      ExceptionUtils.wrappException(e);
    }
  }

}
