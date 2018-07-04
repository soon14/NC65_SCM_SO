package nc.pubimpl.iufo;

import nc.pubitf.so.iufo.ISOToUFOFunc;
import nc.vo.pub.BusinessException;
import nc.vo.pub.lang.UFDouble;
import nc.vo.scmpub.fetchfunc.FuncRegister;
import nc.vo.so.iufo.UFOFuncExector;
import nc.vo.so.iufo.UFOFuncParaVO;

public class SOToUFOFuncImpl implements ISOToUFOFunc {

  @Override
  public UFDouble[] execUFOFuncQuery(FuncRegister register,
      UFOFuncParaVO[] paravos) throws BusinessException {
    UFOFuncExector exector = new UFOFuncExector();
    UFDouble[] funcresults = exector.execUFOFunc(register, paravos);
    return funcresults;
  }

}
