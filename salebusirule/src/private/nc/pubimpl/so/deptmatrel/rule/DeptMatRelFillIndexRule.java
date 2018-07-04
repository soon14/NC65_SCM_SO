package nc.pubimpl.so.deptmatrel.rule;

import nc.pubitf.so.deptmatrel.DeptMatRelParaVO;

/**
 * 部门、业务员、物料质检关系定义给参数填充唯一标识
 * 
 * @since 6.0
 * @version 2011-4-14 下午02:56:35
 * @author 祝会征
 */
public class DeptMatRelFillIndexRule {

  /**
   * 给参数填充唯一标识
   * 
   * @param paravos
   */
  public void fillIndex(DeptMatRelParaVO[] paravos) {
    int i = 0;
    for (DeptMatRelParaVO para : paravos) {
      para.setParaindex(Integer.valueOf(i));
      i++;
    }
  }
}
