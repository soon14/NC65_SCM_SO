package nc.vo.so.report.paravo;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import nc.vo.so.m30.entity.SaleOrderBVO;
import nc.vo.so.report.profit.OrderPorfitViewMeta;

public class ProfitQryInfoParaVO implements Serializable {

  private static final long serialVersionUID = -169528012060968425L;

  // 统计依据数组
  private String[] groupcondtion;

  /**
   * 获取需要显示的字段数组
   * 
   * @return
   */
  public Set<String> getDisplayKeys() {
    Set<String> dispalyKeys = new HashSet<String>();
    String[] groupKeys = this.getGroupKeys();
    for (String key : groupKeys) {
      dispalyKeys.add(key);
    }
    dispalyKeys.add(SaleOrderBVO.CCURRENCYID);
    dispalyKeys.add(SaleOrderBVO.CUNITID);
    return dispalyKeys;
  }

  public Set<String> getDisplayKeys(Set<String>  groupset){
    this.groupcondtion=groupset.toArray(new String[0]);
    return getDisplayKeys();
  }
  
  public String[] getGroupcondtion() {
    return this.groupcondtion;
  }

  public String[] getGroupKeys() {
    int length = this.groupcondtion.length;
    // int size;
    List<String> groupKeys = new ArrayList<String>();
    for (int i = 0; i < length; i++) {
      String groupkey = this.groupcondtion[i];
      groupKeys.add(groupkey);
    }
    return groupKeys.toArray(new String[0]);
  }

  public String[] getHideKeys() {
    List<String> hideKeys = new ArrayList<String>();
    Set<String> displayKeys = this.getDisplayKeys();

    for (String key : OrderPorfitViewMeta.HNAMES) {
      if (!displayKeys.contains(key)) {
        hideKeys.add(key);
      }
    }
    for (String key : OrderPorfitViewMeta.BNAMES) {
      if (!displayKeys.contains(key)) {
        hideKeys.add(key);
      }
    }
    for (String key : OrderPorfitViewMeta.CUSTNAMES) {
      if (!displayKeys.contains(key)) {
        hideKeys.add(key);
      }
    }
    for (String key : OrderPorfitViewMeta.CUSTSALENAMES) {
      if (!displayKeys.contains(key)) {
        hideKeys.add(key);
      }
    }
    for (String key : OrderPorfitViewMeta.MATERIALNAMES) {
      if (!displayKeys.contains(key)) {
        hideKeys.add(key);
      }
    }
    for (String key : OrderPorfitViewMeta.MATERIALSALENAMES) {
      if (!displayKeys.contains(key)) {
        hideKeys.add(key);
      }
    }
    return hideKeys.toArray(new String[0]);

  }

  public String[] getTotalKeys() {
    // String[] totalKeys =
    // new String[] {
    // "nmainnum", "ntotalreceivnum", "ntotalreceivmny", "nmny", "nprice",
    // "ntotalcostnum", "ntotalsettlecostmny", "ntotalcostmny",
    // "ncostprice", "nprofitmny", "nprofitcate"
    // };
    Set<String> totallist = new HashSet<String>();
    for (String key : OrderPorfitViewMeta.DOUBLEFIELDS) {
      totallist.add(key);
    }
    for (String key : OrderPorfitViewMeta.DOUSELECT) {
      totallist.add(key);
    }
    String[] totalKeys = totallist.toArray(new String[totallist.size()]);
    return totalKeys;
  }

  public void setGroupcondtion(String groupenum) {

    String noncontent = groupenum.replace("(", "");
    noncontent = noncontent.replace(")", "");
    String nums = noncontent.replace("'", "");
    this.groupcondtion = nums.substring(0, nums.length()).split(",");

  }

}
