package nc.ui.so.pub.listener;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import nc.ui.pub.bill.BillTotalListener;
import nc.vo.pub.lang.UFBoolean;
import nc.vo.pub.lang.UFDouble;
import nc.vo.pubapp.pattern.pub.MathTool;
import nc.vo.so.pub.SOItemKey;
import nc.vo.so.pub.keyvalue.IKeyValue;
import nc.vo.so.salequotation.entity.SalequotationBVO;

public class SOBillTotalListener implements BillTotalListener {

  private IKeyValue keyValue;

  // 默认的公共的如果是赠品也需要合计的字段：主数量、数量、报价单位数量、重量、体积、件数，其他字段则不需要合计进去，比如金额等
  private String[] defaultIncludeSumNumKeys = new String[] {
    SOItemKey.NNUM, SOItemKey.NASTNUM, SOItemKey.NQTUNITNUM, SOItemKey.NWEIGHT,
    SOItemKey.NVOLUME, SOItemKey.NPIECE,SalequotationBVO.NASSISTNUM
  };

  /**
   * 存储的如果是赠品则不需要合计的字段
   */
  private List<String> includeSumNumKeys;

  public SOBillTotalListener(IKeyValue keyValue) {
    this.keyValue = keyValue;
    includeSumNumKeys = new ArrayList<String>();
    // 把默认的字段加进去
    includeSumNumKeys.addAll(Arrays.asList(defaultIncludeSumNumKeys));
  }

  /**
   * 向如果是赠品也需要合计的字段中添加字段
   * 
   * @param key
   */
  public void addIncludeSumNumKey(String key) {
    if (!includeSumNumKeys.contains(key)) {
      includeSumNumKeys.add(key);
    }
  }

  /**
   * 向如果是赠品也需要合计的字段中添加字段
   * 
   * @param key
   */
  public void addIncludeSumNumKey(String[] keys) {
    if (keys == null || keys.length == 0) {
      return;
    }
    includeSumNumKeys.addAll(Arrays.asList(keys));
  }

  /**
   * 从如果是赠品也需要合计的字段中删除字段
   * 
   * @param key
   */
  public void removeIncludeSumNumKey(String key) {
    if (includeSumNumKeys.contains(key)) {
      includeSumNumKeys.remove(key);
    }
  }

  public List<String> getIncludeSumNumKeys() {
    return includeSumNumKeys;
  }

  @Override
  public UFDouble calcurateTotal(String key) {

    UFDouble total = UFDouble.ZERO_DBL;
    int bodycount = this.keyValue.getBodyCount();
    for (int i = 0; i < bodycount; i++) {

      UFDouble ufvalue = this.keyValue.getBodyUFDoubleValue(i, key);
      UFBoolean larflag =
          this.keyValue.getBodyUFBooleanValue(i, SOItemKey.BLARGESSFLAG);
      // 例如：数量包含赠品行的
      if (getIncludeSumNumKeys().contains(key)) {
        total = MathTool.add(total, ufvalue);
      }
      else if (null == larflag || !larflag.booleanValue()) {
        total = MathTool.add(total, ufvalue);
      }
    }
    return total;
  }

}
