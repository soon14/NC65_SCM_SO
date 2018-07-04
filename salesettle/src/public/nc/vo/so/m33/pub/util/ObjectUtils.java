package nc.vo.so.m33.pub.util;

import nc.vo.pub.lang.UFDouble;

public class ObjectUtils {

  public static UFDouble convertNULLToZero(UFDouble ufd) {
    if (ufd == null) {
      return UFDouble.ZERO_DBL;
    }
    return ufd;
  }

}
