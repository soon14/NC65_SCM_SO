package nc.vo.so.m30.entity;

import java.io.Serializable;
import java.util.Map;

import nc.vo.pub.lang.UFDouble;

/**
 * 界面缓存的冲抵对象
 * 
 * @since 6.0
 * @version 2010-12-10 下午03:23:27
 * @author 么贵敬
 */
public class OffsetTempVO implements Serializable {
  /**
   * VersionUID
   */
  private static final long serialVersionUID = 4461020823338021748L;

  /** 冲抵关系， 销售费用单子表ID为key，冲抵金额为value */
  private Map<String, UFDouble> hmArsubRelation;

  /** 是否做取消冲抵 */
  private boolean isCancelOffset;

  /**
   * 冲抵关系
   * 
   * @return 冲抵关系
   */
  public Map<String, UFDouble> getHmArsubRelation() {
    return this.hmArsubRelation;
  }

  /**
   * 是否取消冲抵
   * 
   * @return 是否取消冲抵
   */
  public boolean getIsCancelOffset() {
    return this.isCancelOffset;
  }

  /**
   * 冲抵关系
   * 
   * @param hmArsubRelation 冲抵关系
   */
  public void setHmArsubRelation(Map<String, UFDouble> hmArsubRelation) {
    this.hmArsubRelation = hmArsubRelation;
  }

  /**
   * 是否取消冲抵
   * 
   * @param isCancelOffset 是否取消冲抵
   */
  public void setIsCancelOffset(boolean isCancelOffset) {
    this.isCancelOffset = isCancelOffset;
  }

}
