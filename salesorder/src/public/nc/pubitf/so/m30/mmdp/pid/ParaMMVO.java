package nc.pubitf.so.m30.mmdp.pid;

import java.io.Serializable;
import java.util.List;

import nc.pubitf.so.m30.mmdp.pid.ParaVO.ParaVOKeyEnum;
import nc.vo.pub.lang.UFDate;

/**
 * 
 * @since 6.0
 * @version 2011-12-5 下午02:51:40
 * @author 么贵敬
 */
public class ParaMMVO implements Serializable {
  private static final long serialVersionUID = -2571961431582595775L;

  /**
   * 开始日期
   */
  private UFDate cbegindate;

  /**
   * 结束日期
   */
  private UFDate cenddate;

  private List<ParaVOKeyEnum> keys;

  /**
   *
   */
  private List<ParaVO> paras;

  public UFDate getCbegindate() {
    return this.cbegindate;
  }

  public UFDate getCenddate() {
    return this.cenddate;
  }

  public List<ParaVOKeyEnum> getKeys() {
    return this.keys;
  }

  public List<ParaVO> getParas() {
    return this.paras;
  }

  public void setCbegindate(UFDate cbegindate) {
    this.cbegindate = cbegindate;
  }

  public void setCenddate(UFDate cenddate) {
    this.cenddate = cenddate;
  }

  public void setKeys(List<ParaVOKeyEnum> keys) {
    this.keys = keys;
  }

  public void setParas(List<ParaVO> paras) {
    this.paras = paras;
  }

}
