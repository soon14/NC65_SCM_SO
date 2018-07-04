package nc.vo.so.m32.util;

import java.util.ArrayList;
import java.util.List;

import nc.vo.pub.lang.UFDate;
import nc.vo.pub.lang.UFDouble;
import nc.vo.pubapp.pattern.pub.MathTool;
import nc.vo.so.m32.entity.SaleInvoiceHVO;
import nc.vo.so.m32.entity.SaleInvoiceVO;
import nc.vo.so.pub.util.SOCurrencyUtil;
import nc.vo.so.pub.util.SOPubParaUtil;
import nc.vo.trade.checkrule.VOChecker;

/**
 * 销售发票汇率计算（基于VO）
 * 
 * @since 6.0
 * @version 2011-5-21 上午10:55:42
 * @author 么贵敬
 */
public class SaleInvoiceRateUtil {

  /**
   * 折本汇率改变的发票vo
   */
  private List<SaleInvoiceVO> ratechangevos = new ArrayList<SaleInvoiceVO>();

  /**
   * 全局本位币汇率改变的发票vo
   */
  private List<SaleInvoiceVO> globalexchgratevos =
      new ArrayList<SaleInvoiceVO>();

  /**
   * 集团本位币汇率改变的发票vo
   */
  private List<SaleInvoiceVO> groupchgratevos = new ArrayList<SaleInvoiceVO>();

  /**
   * 
   * @return 全局本位币汇率改变的发票vo
   */
  public List<SaleInvoiceVO> getGlobalexchgratevos() {
    return this.globalexchgratevos;
  }

  /**
   * 设置全局本位币汇率改变的发票vo
   * 
   * @param globalexchgratevos
   */
  public void setGlobalexchgratevos(List<SaleInvoiceVO> globalexchgratevos) {
    this.globalexchgratevos = globalexchgratevos;
  }

  /**
   * 
   * @return 集团本位币汇率改变的发票vo
   */
  public List<SaleInvoiceVO> getGroupchgratevos() {
    return this.groupchgratevos;
  }

  /**
   * 设置集团本位币汇率改变的发票vo
   * 
   * @param groupchgratevos
   */
  public void setGroupchgratevos(List<SaleInvoiceVO> groupchgratevos) {
    this.groupchgratevos = groupchgratevos;
  }

  /**
   * 
   * @return 折本汇率改变的发票vo
   */
  public List<SaleInvoiceVO> getRatechangevos() {
    return this.ratechangevos;
  }

  /**
   * 设置折本汇率改变的发票vo
   * 
   * @param ratechangevos
   */
  public void setRatechangevos(List<SaleInvoiceVO> ratechangevos) {
    this.ratechangevos = ratechangevos;
  }

  public void setBuyRate(SaleInvoiceVO[] vos) {
    this.setGlobalLocalCurrencyBuyRate(vos);
    this.setGroupLocalCurrencyBuyRate(vos);
    this.setCurrencyBuyRate(vos);
  }

  /**
   * 全局汇率基于组织本位币计算
   * 
   * @return
   */
  private boolean isCurToGlobalMoney() {
    return SOPubParaUtil.getInstance().isGlobalLocalCurrencyEnable()
        && !SOPubParaUtil.getInstance().isOrigCurToGlobalMoney();
  }

  /**
   * 集团汇率基于组织本位币计算
   * 
   * @return
   */
  private boolean isCurToGroupMoney(SaleInvoiceVO[] vos) {
    String pk_group = vos[0].getParentVO().getPk_group();
    return SOPubParaUtil.getInstance().isGroupLocalCurrencyEnable(pk_group)
        && !SOPubParaUtil.getInstance().isOrigCurToGroupMoney(pk_group);
  }

  /**
   * 全局汇率基于组织原币计算
   * 
   * @return
   */
  private boolean isOrigCurToGlobalMoney() {
    return SOPubParaUtil.getInstance().isGlobalLocalCurrencyEnable()
        && SOPubParaUtil.getInstance().isOrigCurToGlobalMoney();
  }

  /**
   * 集团汇率基于原币计算
   * 
   * @return
   */
  private boolean isOrigCurToGroupMoney(SaleInvoiceVO[] vos) {
    String pk_group = vos[0].getParentVO().getPk_group();
    return SOPubParaUtil.getInstance().isGroupLocalCurrencyEnable(pk_group)
        && SOPubParaUtil.getInstance().isOrigCurToGroupMoney(pk_group);
  }

  private void setCurrencyBuyRate(SaleInvoiceVO[] vos) {
    for (SaleInvoiceVO vo : vos) {

      SaleInvoiceHVO hvo = vo.getParentVO();
      String pk_org = hvo.getPk_org();
      // 单据日期
      UFDate billdate = hvo.getDbilldate();
      // 原币币种
      String orgcurrency = hvo.getCorigcurrencyid();
      // 本位币
      String currency = hvo.getCcurrencyid();
      UFDouble oldnexchangerate = null;
      if (!VOChecker.isEmpty(orgcurrency) && !VOChecker.isEmpty(currency)) {
        UFDouble changestrate =
            SOCurrencyUtil.getInCurrencyRateByOrg(pk_org, orgcurrency,
                currency, billdate);
        oldnexchangerate = hvo.getNexchangerate();
        if (!MathTool.equals(oldnexchangerate, changestrate)) {
          this.ratechangevos.add(vo);
        }
        hvo.setNexchangerate(changestrate);

      }
    }
  }

  /**
   * 全局汇率基于本币计算
   * 
   * @param vos
   */
  private void setGlobalCurRate(SaleInvoiceVO[] vos) {
    for (SaleInvoiceVO vo : vos) {
      // 单据日期
      UFDate billdate = vo.getParentVO().getDbilldate();
      // 币种
      String currency = vo.getParentVO().getCcurrencyid();
      UFDouble newrate =
          SOCurrencyUtil.getGlobalLocalCurrencyBuyRate(currency, billdate);

      UFDouble oldNglobalexchgrate = vo.getParentVO().getNglobalexchgrate();
      if (!MathTool.equals(oldNglobalexchgrate, newrate)) {
        this.globalexchgratevos.add(vo);
      }
      vo.getParentVO().setNglobalexchgrate(newrate);
    }
  }

  /**
   * 全局本位币汇率
   * 
   * @param vos
   * @return
   */
  private void setGlobalLocalCurrencyBuyRate(SaleInvoiceVO[] vos) {
    if (this.isCurToGlobalMoney()) {
      this.setGlobalCurRate(vos);
    }
    if (this.isOrigCurToGlobalMoney()) {
      this.setGlobalOrigCurRate(vos);
    }
  }

  /**
   * 全局汇率基于原币计算
   * 
   * @param vos
   */
  private void setGlobalOrigCurRate(SaleInvoiceVO[] vos) {
    for (SaleInvoiceVO vo : vos) {
      // 单据日期
      UFDate billdate = vo.getParentVO().getDbilldate();
      // 原币币种
      String orgcurrency = vo.getParentVO().getCorigcurrencyid();

      UFDouble newrate =
          SOCurrencyUtil.getGlobalLocalCurrencyBuyRate(orgcurrency, billdate);

      UFDouble oldNglobalexchgrate = vo.getParentVO().getNglobalexchgrate();
      if (!MathTool.equals(oldNglobalexchgrate, newrate)) {
        this.globalexchgratevos.add(vo);
      }
      vo.getParentVO().setNglobalexchgrate(newrate);
    }

  }

  /**
   * 集团汇率基于本币计算
   * 
   * @param vos
   */
  private void setGroupCurRate(SaleInvoiceVO[] vos) {
    for (SaleInvoiceVO vo : vos) {
      // 单据日期
      UFDate billdate = vo.getParentVO().getDbilldate();
      // 币种
      String currency = vo.getParentVO().getCcurrencyid();

      UFDouble newrate =
          SOCurrencyUtil.getGroupLocalCurrencyBuyRate(currency, billdate);

      UFDouble oldNgroupexchgrate = vo.getParentVO().getNgroupexchgrate();
      if (!MathTool.equals(oldNgroupexchgrate, newrate)) {
        this.groupchgratevos.add(vo);
      }
      vo.getParentVO().setNgroupexchgrate(newrate);
    }
  }

  /**
   * 集团汇率
   * 
   * @param vos
   * @return
   */
  private void setGroupLocalCurrencyBuyRate(SaleInvoiceVO[] vos) {

    if (this.isCurToGroupMoney(vos)) {
      this.setGroupCurRate(vos);
    }
    if (this.isOrigCurToGroupMoney(vos)) {
      this.setGroupOrigCurRate(vos);
    }
  }

  /**
   * 集团汇率基于原币计算
   * 
   * @param vos
   */
  private void setGroupOrigCurRate(SaleInvoiceVO[] vos) {
    for (SaleInvoiceVO vo : vos) {
      // 单据日期
      UFDate billdate = vo.getParentVO().getDbilldate();
      // 原币币种
      String orgcurrency = vo.getParentVO().getCorigcurrencyid();

      UFDouble newrate =
          SOCurrencyUtil.getGroupLocalCurrencyBuyRate(orgcurrency, billdate);

      UFDouble oldNgroupexchgrate = vo.getParentVO().getNgroupexchgrate();

      if (!MathTool.equals(oldNgroupexchgrate, newrate)) {
        this.groupchgratevos.add(vo);
      }
      vo.getParentVO().setNgroupexchgrate(newrate);
    }

  }
}
