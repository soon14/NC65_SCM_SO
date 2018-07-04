package nc.pubimpl.so.custmatrel.rule;

import nc.pubitf.so.custmatrel.CustMatRelParaVO;

/**
 * 给参数填充唯一标识
 * 
 * @since 6.0
 * @version 2011-4-14 下午02:56:35
 * @author 祝会征
 */
public class CustMatRelFillIndexRule {

  /**
   * 给参数填充唯一标识
   * 
   * @param paravos
   */
  public void fillIndex(CustMatRelParaVO[] paravos) {
    int i = 0;
    for (CustMatRelParaVO para : paravos) {
      para.setParaindex(Integer.valueOf(i));
      i++;
    }
  }
}
