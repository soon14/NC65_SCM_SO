package nc.vo.so.pub.comparator;

import java.io.Serializable;
import java.util.Comparator;

import nc.vo.pub.CircularlyAccessibleValueObject;

/**
 * 行号排序
 * 
 * @since 6.3
 * @version 2013-12-20 16:11:18
 * @author 刘景
 */
public class BillNOandRowNoComparator implements
    Comparator<CircularlyAccessibleValueObject>, Serializable {

  private static final long serialVersionUID = 1L;

  private String rowNoKey = "vsrcrowno";

  private String billNoKey = "vsrccode";

  /**
   * 构造函数
   */
  public BillNOandRowNoComparator() {
    //
  }

  /**
   * 构造函数
   * 
   * @param rowNoKey
   * @param billNoKey
   */
  public BillNOandRowNoComparator(String rowNoKey, String billNoKey) {
    this.rowNoKey = rowNoKey;
    this.billNoKey = billNoKey;
  }

  @Override
  public int compare(CircularlyAccessibleValueObject o1,
      CircularlyAccessibleValueObject o2) {
    String billno1 = (String) o1.getAttributeValue(this.billNoKey) + "";
    String billno2 = (String) o2.getAttributeValue(this.billNoKey) + "";
    int returnValue = billno1.compareTo(billno2);
    if (returnValue == 0) {
      double value2 =
          o2.getAttributeValue(this.rowNoKey) == null ? 0 : Double
              .parseDouble((String) o2.getAttributeValue(this.rowNoKey));
      double value1 =
          o1.getAttributeValue(this.rowNoKey) == null ? 0 : Double
              .parseDouble((String) o1.getAttributeValue(this.rowNoKey));
      return value1 > value2 ? 1 : value1 == value2 ? 0 : -1;
    }
    return returnValue;
  }

}
