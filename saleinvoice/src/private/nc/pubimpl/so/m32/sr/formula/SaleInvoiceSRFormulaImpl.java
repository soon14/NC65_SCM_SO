package nc.pubimpl.so.m32.sr.formula;

import nc.vo.pub.BusinessException;

import nc.pubitf.so.m32.sr.formula.SaleInvoiceFormulaSqlMap;
import nc.pubitf.sr.formula.ISRFormulaSqlMap;
import nc.pubitf.sr.formula.ISRFormulaSqlPara;
import nc.pubitf.sr.formula.so.saleinvoice.ISaleInvoiceSRFormula;

/**
 * 销售发票返利取数函数接口实现类
 * 
 * @since 6.1
 * @version 2012-11-27 16:23:24
 * @author 冯加彬
 */
public class SaleInvoiceSRFormulaImpl implements ISaleInvoiceSRFormula {

  @Override
  public ISRFormulaSqlMap getMnyFormulaSqlMap(ISRFormulaSqlPara para)
      throws BusinessException {
    SaleInvoiceFormulaSqlMap sqlmap =
        new SaleInvoiceFormulaSqlMap("so_saleinvoice_b.norigtaxmny", para);
    return sqlmap;
  }

  @Override
  public ISRFormulaSqlMap getNumFormulaSqlMap(ISRFormulaSqlPara para)
      throws BusinessException {
    SaleInvoiceFormulaSqlMap sqlmap =
        new SaleInvoiceFormulaSqlMap("so_saleinvoice_b.nnum", para);
    return sqlmap;
  }

}
