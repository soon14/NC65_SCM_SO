package nc.pubitf.so.m30.it.m5801;

import nc.vo.pub.lang.UFDouble;
import nc.vo.pubapp.pattern.exception.ExceptionUtils;
import nc.vo.pubapp.pattern.pub.PubAppTool;

/**
 * 进口合同回写上游单据参数
 * 
 * @since JCK 6.31
 * @version 2014-03-19 14:35:01
 * @author zhangyfr
 */
public class Rewrite5801Para {

  /** 销售订单表体id */
  private String csaleorderbid;

  /** 进口合同数量（变化量） */
  private UFDouble nchangenum;

  /**
   * Rewrite5801Para 构造子
   * 
   * @param csaleorderbid 销售订单表体id
   * @param nchangenum 进口合同数量（变化量）
   */
  public Rewrite5801Para(String csaleorderbid, UFDouble nchangenum) {
    if (PubAppTool.isNull(csaleorderbid)) {
      ExceptionUtils
          .wrappBusinessException(nc.vo.ml.NCLangRes4VoTransl.getNCLangRes()
              .getStrByID("4006011_0", "04006011-0481")/*@res "要回写销售订单表体行的id不可为空。"*/);
    }
    this.csaleorderbid = csaleorderbid;
    if (null == nchangenum) {
      ExceptionUtils
          .wrappBusinessException(nc.vo.ml.NCLangRes4VoTransl.getNCLangRes()
              .getStrByID("4006011_0", "04006011-0482")/*@res "进口合同数量不可为空。"*/);
    }
    this.nchangenum = nchangenum;
  }

  /**
   * 获得要回写销售订单表体行的id
   * 
   * @return 要回写销售订单表体行的id
   */
  public String getCsaleorderbid() {
    return this.csaleorderbid;
  }

  /**
   * 获得进口合同数量
   * 
   * @return 进口合同数量
   */
  public UFDouble getNchangenum() {
    return this.nchangenum;
  }

}
