package nc.vo.so.m32.entity;

import java.io.Serializable;

import nc.vo.so.m30.entity.OffsetTempVO;


/**
 * 销售发票前台往后太传递的数据结构
 * 
 * @since 6.36
 * @version 2015-6-3 下午6:33:03
 * @author 刘景
 */
public class SaleinvoiceUserObject  implements Serializable{
  
  /**
   * 
   */
  private static final long serialVersionUID = -3350140407540789363L;


  /**
   * 冲抵产生的缓存
   */
  private  OffsetTempVO tempvo;
  

  /**
   * 单据是否从前台界面保存，而不是外部交换平台导入的（考虑到效率问题，前台界面保存的单据不进行效率检查）
   */
  private boolean isclientsave=false;
  
  
  public OffsetTempVO getTempvo() {
    return tempvo;
  }


  
  public void setTempvo(OffsetTempVO tempvo) {
    this.tempvo = tempvo;
  }

  
  /**
   * @return 获取单据是否从前台界面保存的
   */
  public boolean isIsclientsave() {
    return isclientsave;
  }

  
  /**
   * 设置单据是否从前台界面保存的
   * @param isclientsave
   */
  public void setIsclientsave(boolean isclientsave) {
    this.isclientsave = isclientsave;
  }
  

}
