package nc.pubimpl.so.tranmatrel.rule;

import nc.pubitf.so.tanmatrel.TranMatRelParaVO;

/**
 * 给参数填充唯一标识
 * 
 * @since 6.0
 * @version 2011-4-14 下午02:56:35
 * @author 祝会征
 */
public class TranMatRelFillIndexRule {

  /**
   * 给参数填充唯一标识
   * 
   * @param paravos
   */
  public void fillIndex(TranMatRelParaVO[] paravos) {
    int i = 0;
    for (TranMatRelParaVO para : paravos) {
      para.setParaindex(Integer.valueOf(i));
      i++;
    }
  }
}
