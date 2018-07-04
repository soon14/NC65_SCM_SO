package nc.pubitf.so.m33.self.m4332;

import nc.vo.pub.lang.UFDouble;
import nc.vo.pubapp.pattern.exception.ExceptionUtils;
import nc.vo.pubapp.pattern.pub.PubAppTool;

/**
 * 销售发票结算单回写销售出库待结算单的回写参数类
 * 
 * @author zhangcheng
 * @since 6.0
 * @time 2010-01-28 下午13:49:07
 */
public class Rewrite434CPara {

  /** 销售出库待结算单表体id */
  private String csaleorderbid;

  /** 销售出库待结算单累计下游确认应收金额（变化量） */
  private UFDouble narmny;

  /** 销售出库待结算单累计下游确认应收数量（变化量） */
  private UFDouble narnum;

  public Rewrite434CPara(String csaleorderbidt, UFDouble narnumt) {
    if (PubAppTool.isNull(csaleorderbidt)) {
      ExceptionUtils
          .wrappBusinessException(nc.vo.ml.NCLangRes4VoTransl.getNCLangRes()
              .getStrByID("4006010_0", "04006010-0065")/*@res "要回写销售出库待结算单表体行的id不可为空。"*/);
    }
    this.csaleorderbid = csaleorderbidt;

    if (narnumt == null) {
      ExceptionUtils
          .wrappBusinessException(nc.vo.ml.NCLangRes4VoTransl.getNCLangRes()
              .getStrByID("4006010_0", "04006010-0066")/*@res "累计下游确认应收数量不可为空。"*/);
    }
    this.narnum = narnumt;
  }

  public String getCsaleorderbid() {
    return this.csaleorderbid;
  }

  public UFDouble getNarmny() {
    return this.narmny;
  }

  public UFDouble getNarnum() {
    return this.narnum;
  }

  public void setNarmny(UFDouble narmny) {
    this.narmny = narmny;
  }

  public void setNarnum(UFDouble narnum) {
    this.narnum = narnum;
  }

}
