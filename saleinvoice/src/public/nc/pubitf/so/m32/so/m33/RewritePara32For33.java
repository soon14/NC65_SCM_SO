package nc.pubitf.so.m32.so.m33;

import nc.vo.pub.lang.UFDouble;
import nc.vo.pubapp.pattern.exception.ExceptionUtils;
import nc.vo.pubapp.pattern.pub.PubAppTool;

public class RewritePara32For33 {

  /**
   * 销售发票bid
   */
  private String csaleinvoicebid;

  /**
   * 累计成本结算数量
   */
  private UFDouble ntotalcostnum;

  /**
   * 累计确认应收金额
   */
  private UFDouble ntotalincomemny;

  /**
   * 累计确认应收数量
   */
  private UFDouble ntotalincomenum;

  public RewritePara32For33(String csaleinvoicebid, UFDouble ntotalincomenum,
      UFDouble ntotalincomemny, UFDouble ntotalcostnum) {
    if (PubAppTool.isNull(csaleinvoicebid)) {
      ExceptionUtils
          .wrappBusinessException(nc.vo.ml.NCLangRes4VoTransl.getNCLangRes()
              .getStrByID("4006008_0", "04006008-0064")/*@res "销售发票表体行id不可为空。"*/);
    }
    this.csaleinvoicebid = csaleinvoicebid;

    this.ntotalincomenum = ntotalincomenum;
    this.ntotalincomemny = ntotalincomemny;
    this.ntotalcostnum = ntotalcostnum;
  }

  public String getCsaleinvoicebid() {
    return this.csaleinvoicebid;
  }

  public UFDouble getNtotalCostNum() {
    return this.ntotalcostnum;
  }

  public UFDouble getNtotalIncomeMny() {
    return this.ntotalincomemny;
  }

  public UFDouble getNtotalIncomeNum() {
    return this.ntotalincomenum;
  }

}
