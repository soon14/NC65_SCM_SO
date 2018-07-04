package nc.pubimpl.so.m30.sr.formula;

import nc.vo.pub.BusinessException;

import nc.pubitf.so.m30.sr.formula.SOBalanceFormulaSqlMap;
import nc.pubitf.sr.formula.ISRFormulaSqlMap;
import nc.pubitf.sr.formula.ISRFormulaSqlPara;
import nc.pubitf.sr.formula.so.sobalance.ISOBalanceSRFormula;

/**
 * 
 * 
 * @since 6.1
 * @version 2012-11-27 17:34:20
 * @author ·ë¼Ó±ò
 */
public class SOBalanceSRFormulaImpl implements ISOBalanceSRFormula {

  @Override
  public ISRFormulaSqlMap getMnyFormulaSqlMap(ISRFormulaSqlPara para)
      throws BusinessException {
    SOBalanceFormulaSqlMap sqlmap = new SOBalanceFormulaSqlMap(para);
    return sqlmap;
  }

}
