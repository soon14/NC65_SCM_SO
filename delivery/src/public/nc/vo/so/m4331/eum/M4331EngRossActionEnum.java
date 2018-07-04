package nc.vo.so.m4331.eum;

/**
 * 
 * @since 6.0
 * @version 2011-2-25 下午01:49:13
 * @author 祝会征
 */
public enum M4331EngRossActionEnum {

  // 发货单保存动作
  M4331Write("WRITE");
  private String actionvalue;

  private M4331EngRossActionEnum(String actionvalue1) {
    this.actionvalue = actionvalue1;
  }

  public String getValue() {
    return this.actionvalue;
  }
}
