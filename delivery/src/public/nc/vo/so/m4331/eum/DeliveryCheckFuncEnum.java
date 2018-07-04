package nc.vo.so.m4331.eum;

/**
 * 
 * @since 6.0
 * @version 2011-2-25 下午01:48:35
 * @author 祝会征
 */
public enum DeliveryCheckFuncEnum {

  // 发货单保存，物料批次检查
  DeliverySave("examBatchInv");
  private String func;

  private DeliveryCheckFuncEnum(String func1) {
    this.func = func1;
  }

  public String getValue() {
    return this.func;
  }

}
