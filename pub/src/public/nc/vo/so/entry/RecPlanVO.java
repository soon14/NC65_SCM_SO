package nc.vo.so.entry;

import nc.vo.pub.IVOMeta;
import nc.vo.pub.lang.UFBoolean;
import nc.vo.pub.lang.UFDate;
import nc.vo.pub.lang.UFDateTime;
import nc.vo.pub.lang.UFDouble;
import nc.vo.pubapp.pattern.model.meta.entity.vo.VOMetaFactory;
import nc.vo.pubapp.res.NCModule;
import nc.vo.scmpub.payterm.pay.AbstractPayPlanVO;
import nc.vo.scmpub.payterm.recv.AbstractRecvPlanVO;
import nc.vo.so.m30.entity.SaleOrderHVO;

public class RecPlanVO extends AbstractRecvPlanVO {

  /** 预收标记 */
  public static final String BPREFLAG = "bpreflag";

  /** 收款协议号 */
  public static final String CPAYTERMID = "cpaytermid";

  /** 账期起算日 */
  public static final String DBEGINDATE = "dbegindate";

  /** 账期到期日 */
  public static final String DENDDATE = "denddate";

  /** 内控到期日期 */
  public static final String DINSIDEENDDATE = "dinsideenddate";

  /** dr */
  public static final String DR = "dr";

  /** 起效依据 */
  public static final String FEFFDATETYPE = "feffdatetype";

  /** 收款期 */
  public static final String IACCOUNTTERMNO = "iaccounttermno";

  /** 账期天数 */
  public static final String IITERMDAYS = "iitermdays";

  /** 金额 */
  public static final String NORIGMNY = "norigmny";

  /** 收款比例 */
  public static final String NRATE = "nrate";

  /** 总收款金额 */
  public static final String NTOTALORIGMNY = "ntotalorigmny";

  /**
   * 原币
   */
  public static final String CORIGCURRENCYID = "corigcurrencyid";

  /** ts */
  public static final String TS = "ts";

  public static final String VBILLCODE = "vbillcode";

  private static final long serialVersionUID = 4684054072960817194L;

  /** 预收标记 getter 方法 */
  public UFBoolean getBpreflag() {
    return (UFBoolean) this.getAttributeValue(RecPlanVO.BPREFLAG);
  }

  /** 账期起算日 getter 方法 */
  public UFDate getDbegindate() {
    return (UFDate) this.getAttributeValue(RecPlanVO.DBEGINDATE);
  }

  /** 账期到期日 getter 方法 */
  @Override
  public UFDate getDenddate() {
    return (UFDate) this.getAttributeValue(RecPlanVO.DENDDATE);
  }

  /** 内控到期日期 getter 方法 */
  public UFDate getDinsideenddate() {
    return (UFDate) this.getAttributeValue(RecPlanVO.DINSIDEENDDATE);
  }

  /** dr getter 方法 */
  public Integer getDr() {
    return (Integer) this.getAttributeValue(RecPlanVO.DR);
  }

  /** 起效依据 getter 方法 */
  public Integer getFeffdatetype() {
    return (Integer) this.getAttributeValue(RecPlanVO.FEFFDATETYPE);
  }

  /** 收款期 getter 方法 */
  public Integer getIaccounttermno() {
    return (Integer) this.getAttributeValue(RecPlanVO.IACCOUNTTERMNO);
  }

  /** 账期天数 getter 方法 */
  public Integer getIitermdays() {
    return (Integer) this.getAttributeValue(RecPlanVO.IITERMDAYS);
  }

  @Override
  public IVOMeta getMetaData() {
    IVOMeta meta =
        VOMetaFactory.getInstance().getVOMeta(
            NCModule.SO.getName().toLowerCase() + "." + "recvplan");
    return meta;
  }

  /** 金额 getter 方法 */
  public UFDouble getNorigmny() {
    return (UFDouble) this.getAttributeValue(RecPlanVO.NORIGMNY);
  }

  /** 收款比例 getter 方法 */
  public UFDouble getNrate() {
    return (UFDouble) this.getAttributeValue(RecPlanVO.NRATE);
  }

  /** 总收款金额 getter 方法 */
  public UFDouble getNtotalorigmny() {
    return (UFDouble) this.getAttributeValue(RecPlanVO.NTOTALORIGMNY);
  }

  /** 收款协议号 */
  public String getCpaytermid() {
    return (String) this.getAttributeValue(RecPlanVO.CPAYTERMID);
  }

  /** ts getter 方法 */
  public UFDateTime getTs() {
    return (UFDateTime) this.getAttributeValue(RecPlanVO.TS);
  }

  public String getVbillcode() {
    return (String) this.getAttributeValue(SaleOrderHVO.VBILLCODE);
  }

  /** 预收标记 setter 方法 */
  @Override
  public void setBpreflag(UFBoolean bpreflag) {
    this.setAttributeValue(RecPlanVO.BPREFLAG, bpreflag);
  }

  /** 行号 setter 方法 */
  @Override
  public void setCrowno(String crowno) {
    this.setAttributeValue(AbstractPayPlanVO.CROWNO, crowno);
  }

  /** 账期起算日 setter 方法 */
  @Override
  public void setDbegindate(UFDate dbegindate) {
    this.setAttributeValue(RecPlanVO.DBEGINDATE, dbegindate);
  }

  /** 账期到期日 setter 方法 */
  @Override
  public void setDenddate(UFDate denddate) {
    this.setAttributeValue(RecPlanVO.DENDDATE, denddate);
  }

  /** 内控到期日期 setter 方法 */
  @Override
  public void setDinsideenddate(UFDate dinsideenddate) {
    this.setAttributeValue(RecPlanVO.DINSIDEENDDATE, dinsideenddate);
  }

  /** dr setter 方法 */
  public void setDr(Integer dr) {
    this.setAttributeValue(RecPlanVO.DR, dr);
  }

  /** 起效依据 setter 方法 */
  @Override
  public void setFeffdatetype(String feffdatetype) {
    this.setAttributeValue(RecPlanVO.FEFFDATETYPE, feffdatetype);
  }

  /** 收款期 setter 方法 */
  @Override
  public void setIaccounttermno(Integer iaccounttermno) {
    this.setAttributeValue(RecPlanVO.IACCOUNTTERMNO, iaccounttermno);
  }

  /** 账期天数 setter 方法 */
  @Override
  public void setIitermdays(Integer iitermdays) {
    this.setAttributeValue(RecPlanVO.IITERMDAYS, iitermdays);
  }

  /** 金额 setter 方法 */
  @Override
  public void setNorigmny(UFDouble norigmny) {
    this.setAttributeValue(RecPlanVO.NORIGMNY, norigmny);
  }

  /** 收款比例 setter 方法 */
  @Override
  public void setNrate(UFDouble nrate) {
    this.setAttributeValue(RecPlanVO.NRATE, nrate);
  }

  /** 总收款金额 setter 方法 */
  @Override
  public void setNtotalorigmny(UFDouble ntotalorigmny) {
    this.setAttributeValue(RecPlanVO.NTOTALORIGMNY, ntotalorigmny);
  }

  /** 收款协议号 */
  @Override
  public void setPk_payterm(String pk_paytem) {
    this.setAttributeValue(RecPlanVO.CPAYTERMID, pk_paytem);
  }

  @Override
  public void setPk_paytermch(String pk_paymentch) {
    // TODO
  }

  /** ts setter 方法 */
  public void setTs(UFDateTime ts) {
    this.setAttributeValue(RecPlanVO.TS, ts);
  }

  /** 单据号 **/
  @Override
  public void setVbillcode(String vbillcode) {
    this.setAttributeValue(SaleOrderHVO.VBILLCODE, vbillcode);
  }

  /**
   * 获取原币
   * 
   * @return 原币
   */
  public String getCorigcurrencyid() {
    return (String) this.getAttributeValue(SaleOrderHVO.CORIGCURRENCYID);
  }

  /**
   * 设置原币
   * 
   * @param corigcurrencyid 原币
   */
  @Override
  public void setCorigcurrencyid(String corigcurrencyid) {
    this.setAttributeValue(SaleOrderHVO.CORIGCURRENCYID, corigcurrencyid);
  }
}
