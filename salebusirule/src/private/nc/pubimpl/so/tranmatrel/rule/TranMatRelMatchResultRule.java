package nc.pubimpl.so.tranmatrel.rule;

import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;

import nc.pubitf.so.tanmatrel.TranMatRelParaVO;
import nc.vo.pub.lang.UFBoolean;

/**
 * 单据类型与物料：匹配单据类型与物料关系最优结果
 * 
 * @since 6.0
 * @version 2011-4-19 上午10:25:32
 * @author 祝会征
 */
public class TranMatRelMatchResultRule {
  /**
   * 匹配相应的结果
   * 
   * @param results
   * @param paravos
   * @return
   */
  public Map<Integer, TranMatRelParaVO> match(TranMatRelParaVO[] results) {
    if ((null == results) || (results.length == 0)) {
      return new HashMap<Integer, TranMatRelParaVO>();
    }
    return this.processMatch(results);
  }

  /**
   * 匹配查询结果
   * 
   * @param matchparas
   * @param matchvos
   */
  private Map<Integer, TranMatRelParaVO> processMatch(TranMatRelParaVO[] results) {
    Map<Integer, TranMatRelParaVO> map =
        new HashMap<Integer, TranMatRelParaVO>();
    Set<Integer> tempSet = new HashSet<Integer>();
    for (TranMatRelParaVO vo : results) {
      Integer curindex = vo.getParaindex();
      if (tempSet.size() == 0 || !tempSet.contains(curindex)) {
        tempSet.add(curindex);
        map.put(curindex, vo);
      }
      else if(tempSet.contains(curindex) && (map.get(curindex).getCprioritycode().equals(vo.getCprioritycode()))){
        //当一行记录匹配到两个关系的时候 优先选择不包含的关系 例如A物料类禁止销售  但A下的a类是不包含 优先匹配不包含关系
        if(vo.getExclude()==UFBoolean.TRUE){
          tempSet.add(curindex);
          map.put(curindex, vo);
        }
      }
    }
    return map;
  }
}
