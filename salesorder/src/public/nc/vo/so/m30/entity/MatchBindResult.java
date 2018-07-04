package nc.vo.so.m30.entity;

import java.io.Serializable;
import java.util.List;
import java.util.Map;

/**
 * 
 * @since 6.0
 * @version 2010-11-24 ионГ11:42:09
 * @author ку╫╗нд
 */
public class MatchBindResult implements Serializable {

  private static final long serialVersionUID = 3365247472267472543L;

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
