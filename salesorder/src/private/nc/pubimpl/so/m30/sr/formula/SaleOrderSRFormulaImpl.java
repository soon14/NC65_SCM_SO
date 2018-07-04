package nc.pubimpl.so.m30.sr.formula;

import nc.vo.pub.BusinessException;

import nc.pubitf.so.m30.sr.formula.SaleOrderFormulaSqlMap;
import nc.pubitf.sr.formula.ISRFormulaSqlMap;
import nc.pubitf.sr.formula.ISRFormulaSqlPara;
import nc.pubitf.sr.formula.so.saleorder.ISaleOrderSRFormula;

/**
 * 预置的订单返利取数函数接口实现类
 * 
 * @since 6.1
 * @version 2012-11-27 15:49:27
 * @author 冯加彬
 */
public class SaleOrderSRFormulaImpl implements ISaleOrderSRFormula {

  @Override
  public ISRFormulaSqlMap getNumFormulaSqlMap(ISRFormulaSqlPara para)
      throws BusinessException {
    SaleOrderFormulaSqlMap ordermap =
        new SaleOrderFormulaSqlMap("so_saleorder_b.nnum", para);
    return ordermap;
  }

  @Override
  public ISRFormulaSqlMap getMnyFormulaSqlMap(ISRFormulaSqlPara para)
      throws BusinessException {
    SaleOrderFormulaSqlMap ordermap =
        new SaleOrderFormulaSqlMap("so_saleorder_b.norigtaxmny", para);
    return ordermap;
  }
}
