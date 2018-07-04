package nc.vo.so.pub.util;

import nc.itf.so.pub.para.IPriorityCode;

public class PriorityCodeGenUtil {

  public static String genPriorityCode(IPriorityCode[] pricodes) {
    StringBuilder code = new StringBuilder();
    for (IPriorityCode pricode : pricodes) {
      code.append(pricode.getPriorityCode());
    }
    return code.toString();
  }
}
