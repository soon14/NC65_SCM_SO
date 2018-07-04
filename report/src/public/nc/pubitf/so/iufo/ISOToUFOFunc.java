package nc.pubitf.so.iufo;

import nc.vo.pub.BusinessException;
import nc.vo.pub.lang.UFDouble;
import nc.vo.scmpub.fetchfunc.FuncRegister;
import nc.vo.so.iufo.UFOFuncParaVO;

public interface ISOToUFOFunc {

  UFDouble[] execUFOFuncQuery(FuncRegister register, UFOFuncParaVO[] paravos)
      throws BusinessException;
}
