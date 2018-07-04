package nc.pubimpl.iufo;

import nc.vo.pub.BusinessException;
import nc.vo.pub.lang.UFDouble;
import nc.vo.pubapp.pattern.exception.ExceptionUtils;
import nc.vo.scmpub.fetchfunc.FuncRegister;
import nc.vo.so.iufo.AbstractUFOFunction;
import nc.vo.so.iufo.UFOFuncParaVO;

import nc.pubitf.so.iufo.ISOToUFOFunc;

import nc.bs.framework.common.NCLocator;

/**
 * 
 * 
 * @since 6.1
 * @version 2013-03-19 13:43:18
 * @author liangjm
 */
public class SaleUFOFunction extends AbstractUFOFunction {

  private static final long serialVersionUID = 8381488086886093962L;

  @Override
  protected UFDouble[] execUFOFunc(FuncRegister register,
      UFOFuncParaVO[] paravos) {
    ISOToUFOFunc srv = NCLocator.getInstance().lookup(ISOToUFOFunc.class);
    UFDouble[] results = null;
    try {
      results = srv.execUFOFuncQuery(register, paravos);
    }
    catch (BusinessException e) {
      ExceptionUtils.wrappException(e);
    }
    return results;
  }

  @Override
  public String getFuncDisName(String arg0) {
    return null;
  }

}
