package nc.vo.so.m30.util;

import nc.vo.pub.lang.UFDate;

public class AppTool {
  private static AppTool instance = new AppTool();

  private static final int LENGTH = 10;
  private AppTool() {
    //
  }

  public static AppTool getInstance() {
    return AppTool.instance;
  }

  public int compareDate(UFDate value1, UFDate value2) {
    int ret = 0;
    if ((value1 == null) || (value2 == null)) {
      throw new IllegalArgumentException(nc.vo.ml.NCLangRes4VoTransl.getNCLangRes().getStrByID("4006011_0","04006011-0230")/*@res "日期比较的参数不能为空！"*/);
    }
    UFDate d1 = UFDate.getDate(value1.toString().substring(0, AppTool.LENGTH));
    UFDate d2 = UFDate.getDate(value2.toString().substring(0, AppTool.LENGTH));
    if (d1.before(d2)) {
      ret = -1;
    }
    else if (d1.after(d2)) {
      ret = 1;
    }
    else {
      ret = 0;
    }
    return ret;
  }

}