package nc.pubimpl.so.rule;

import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;

import nc.pubitf.so.m30.ReturnAssignMatchVO;

/**
 * 匹配退货政策分配结果
 * 
 * @since 6.0
 * @version 2011-4-19 上午10:25:32
 * @author 祝会征
 */
public class MatchResultRule {
  public Map<Integer, String> match(ReturnAssignMatchVO[] results) {
    if ((null == results) || (results.length == 0)) {
      return new HashMap<Integer, String>();
    }
    return this.processMatch(results);
  }

  /**
   * 匹配查询结果
   * 
   * @param matchparas
   * @param matchvos
   */
  private Map<Integer, String> processMatch(ReturnAssignMatchVO[] results) {
    Map<Integer, String> map = new HashMap<Integer, String>();
    Set<Integer> tempSet = new HashSet<Integer>();
    for (ReturnAssignMatchVO vo : results) {
      Integer curindex = vo.getParaindex();
      if (tempSet.size() == 0 || !tempSet.contains(curindex)) {
        tempSet.add(curindex);
        map.put(curindex, vo.getPk_returnpolicy());
      }
    }
    return map;
  }
}
