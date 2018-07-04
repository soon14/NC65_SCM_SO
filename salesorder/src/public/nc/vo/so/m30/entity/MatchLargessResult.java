package nc.vo.so.m30.entity;

import java.io.Serializable;
import java.util.List;
import java.util.Map;


public class MatchLargessResult implements Serializable {

  /**
   * 
   */
  private static final long serialVersionUID = 4183438140195988497L;

  private SaleOrderBVO srcbodyvo;

  private List<SaleOrderBVO> resultvolist;

  private Map<String, String> errmsgmap;

  public SaleOrderBVO getSrcbodyvo() {
    return this.srcbodyvo;
  }

  public void setSrcbodyvo(SaleOrderBVO srcbodyvo) {
    this.srcbodyvo = srcbodyvo;
  }

  public List<SaleOrderBVO> getResultvolist() {
    return this.resultvolist;
  }

  public void setResultvolist(List<SaleOrderBVO> resultvolist) {
    this.resultvolist = resultvolist;
  }

  public Map<String, String> getErrmsgmap() {
    return this.errmsgmap;
  }

  public void setErrmsgmap(Map<String, String> errmsgmap) {
    this.errmsgmap = errmsgmap;
  }

}
