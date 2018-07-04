package nc.pubimpl.so.deptmatrel.rule;

import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;

import nc.pubitf.so.deptmatrel.DeptMatRelParaVO;
import nc.vo.pub.lang.UFBoolean;

/**
 * 部门、业务员、物料之间关系定义：匹配部门、业务与物料关系最优结果
 * 
 * @since 6.0
 * @version 2011-4-19 上午10:25:32
 * @author 祝会征
 */
public class DeptMatRelMatchResultRule {
  /**
   * 匹配相应的结果
   * 
   * @param results
   * @param paravos
   * @return
   */
  public Map<Integer, DeptMatRelParaVO> match(DeptMatRelParaVO[] results) {
    if ((null == results) || (results.length == 0)) {
      return new HashMap<Integer, DeptMatRelParaVO>();
    }
    return this.processMatch(results);
  }

  /**
   * 匹配查询结果
   * 
   * @param matchparas
   * @param matchvos
   */
  private Map<Integer, DeptMatRelParaVO> processMatch(DeptMatRelParaVO[] results) {
    Map<Integer, DeptMatRelParaVO> map =
        new HashMap<Integer, DeptMatRelParaVO>();
    Set<Integer> tempSet = new HashSet<Integer>();
    for (DeptMatRelParaVO vo : results) {
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
