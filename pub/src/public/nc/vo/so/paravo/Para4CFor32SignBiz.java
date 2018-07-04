package nc.vo.so.paravo;

import nc.vo.pub.lang.UFBoolean;
import nc.vo.pubapp.pattern.exception.ExceptionUtils;
import nc.vo.pubapp.pattern.pub.PubAppTool;

/**
 * 根据业务流程确定签收、非签收
 *
 * @since 6.0
 * @version 2011-3-22 上午11:06:42
 * @author 么贵敬
 */
public class Para4CFor32SignBiz {
  // 业务流程
  private String cbizid;

  // 是否基于 签收开票
  private UFBoolean isSign;

  // 销售组织
  private String pk_org;

  public Para4CFor32SignBiz(String cbizid, String pk_org) {
    if (PubAppTool.isNull(cbizid) || PubAppTool.isNull(pk_org)) {
      ExceptionUtils.wrappBusinessException(nc.vo.ml.NCLangRes4VoTransl.getNCLangRes().getStrByID("4006004_0","04006004-0008")/*@res "缺少业务流程或者销售组织！"*/);
    }
    this.cbizid = cbizid;
    this.pk_org = pk_org;
  }

  public String getCbizid() {
    return this.cbizid;
  }

  public UFBoolean getIsSign() {
    return this.isSign;
  }

  public String getPk_org() {
    return this.pk_org;
  }

  public void setCbizid(String cbizid) {
    this.cbizid = cbizid;
  }

  public void setIsSign(UFBoolean isSign) {
    this.isSign = isSign;
  }

  public void setPk_org(String pk_org) {
    this.pk_org = pk_org;
  }
}