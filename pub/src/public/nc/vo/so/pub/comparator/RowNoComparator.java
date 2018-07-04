package nc.vo.so.pub.comparator;

import java.io.Serializable;
import java.util.Comparator;

import nc.vo.pub.CircularlyAccessibleValueObject;

public class RowNoComparator implements
    Comparator<CircularlyAccessibleValueObject>, Serializable {

  private static final long serialVersionUID = 1L;

  private String rowNoKey = "vsrcrowno";

  public RowNoComparator() {
    //
  }

  public RowNoComparator(String rowNoKey) {
    this.rowNoKey = rowNoKey;
  }

  @Override
  public int compare(CircularlyAccessibleValueObject o1,
      CircularlyAccessibleValueObject o2) {
    double value1 =
        o1.getAttributeValue(this.rowNoKey) == null ? 0 : Double
            .parseDouble((String) o1.getAttributeValue(this.rowNoKey));
    double value2 =
        o2.getAttributeValue(this.rowNoKey) == null ? 0 : Double
            .parseDouble((String) o2.getAttributeValue(this.rowNoKey));
    return value1 > value2 ? 1 : value1 == value2 ? 0 : -1;
  }

}
