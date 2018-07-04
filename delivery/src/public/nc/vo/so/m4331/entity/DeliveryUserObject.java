package nc.vo.so.m4331.entity;

import java.io.Serializable;


/**
 * 发货单前台给后台传递参数的数据结构
 * 
 * @since 6.36
 * @version 2015-6-3 下午4:06:51
 * @author 刘景
 */
public class DeliveryUserObject  implements Serializable{
  

  /**
   * 
   */
  private static final long serialVersionUID = 6975292130693340847L;
  /**
   * 发货单是否从前台界面保存，而不是外部交换平台导入的（考虑到效率问题，前台界面保存的单据不进行效率检查）
   */
  private boolean isclientsave=false;

  
  /**
   * @return 获取发货单是否从前台界面保存的
   */
  public boolean isIsclientsave() {
    return isclientsave;
  }

  
  /**
   * 设置发货单是否从前台界面保存的
   * @param isclientsave
   */
  public void setIsclientsave(boolean isclientsave) {
    this.isclientsave = isclientsave;
  }

}
