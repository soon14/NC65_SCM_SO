package nc.pubitf.so.deptmatrel;

import nc.vo.pub.BusinessException;
import nc.vo.pub.lang.UFBoolean;

/**
 * 部门与物料关系业务函数检查
 * 
 * @since 6.0
 * @version 2011-4-19 下午08:27:47
 * @author 祝会征
 */
public interface IDeptMatRelFor30 {
  /**
   * 部门与物料关系业务函数检查
   * 
   * @param paravos
   * @return
   * @throws BusinessException
   */
  UFBoolean checkDeptMatRel(DeptMatRelParaVO[] paravos)
      throws BusinessException;
}
