package nc.itf.so.m4331trantype;

import java.util.Map;

import nc.vo.pub.BusinessException;
import nc.vo.pub.lang.UFBoolean;
import nc.vo.so.m4331trantype.entity.M4331trantypeVO;

public interface IM4331TranTypeService {

  M4331trantypeVO queryTranType(String pk_group, String vtrantype)
      throws BusinessException;

  Map<String, UFBoolean> queryTranTypes(String pk_group, String[] vtrantypes)
      throws BusinessException;
}
