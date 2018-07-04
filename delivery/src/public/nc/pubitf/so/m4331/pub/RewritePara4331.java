package nc.pubitf.so.m4331.pub;

import nc.vo.pubapp.pattern.exception.ExceptionUtils;
import nc.vo.pubapp.pattern.pub.PubAppTool;

/**
 * <p>
 * <b>本类主要完成以下功能：</b>
 * <li>当销售订单或者调拨订单发货关闭，删除下游自由态的发货单，关闭下游审核状态的发货单
 *
 * @author 祝会征
 * @time 2010-3-23 下午06:36:26
 */
public class RewritePara4331 {

  /** 销售订单或者调拨订单表体id */
  private String csrcbid;

  public RewritePara4331(String csrcbid) {
    if (PubAppTool.isNull(csrcbid)) {
      ExceptionUtils.wrappBusinessException(nc.vo.ml.NCLangRes4VoTransl.getNCLangRes().getStrByID("4006002_0","04006002-0104")/*@res "来源单据表体id不能为空！"*/);
    }
    this.csrcbid = csrcbid;
  }

  public String getCsrcbid() {
    return this.csrcbid;
  }
}