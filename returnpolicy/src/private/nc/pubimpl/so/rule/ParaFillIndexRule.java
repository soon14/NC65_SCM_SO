package nc.pubimpl.so.rule;

import nc.pubitf.so.m30.ReturnAssignMatchVO;

/**
 * 给参数填充唯一标识
 * 
 * @since 6.0
 * @version 2011-4-14 下午02:56:35
 * @author 祝会征
 */
public class ParaFillIndexRule {

  /**
   * 给参数填充唯一标识
   * 
   * @param matchparas
   */
  public void fillIndex(ReturnAssignMatchVO[] matchparas) {
    int i = 0;
    for (ReturnAssignMatchVO para : matchparas) {
      para.setParaindex(Integer.valueOf(i));
      i++;
    }
  }
}
