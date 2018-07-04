package nc.vo.so.pub.rule;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;

import nc.itf.scmpub.reference.uap.bd.customer.CustomerPubService;
import nc.itf.scmpub.reference.uap.bd.vat.BuySellFlagEnum;
import nc.itf.scmpub.reference.uap.bd.vat.VATBDService;
import nc.itf.scmpub.reference.uap.bd.vat.VATInfoByTaxcodeQueryVO;
import nc.itf.scmpub.reference.uap.bd.vat.VATInfoQueryVO;
import nc.itf.scmpub.reference.uap.bd.vat.VATInfoVO;
import nc.itf.scmpub.reference.uap.bd.vat.ZeroTaxCodeEnum;
import nc.itf.scmpub.reference.uap.org.OrgUnitPubService;
import nc.vo.pub.lang.UFBoolean;
import nc.vo.pub.lang.UFDate;
import nc.vo.pub.lang.UFDouble;
import nc.vo.pubapp.pattern.pub.PubAppTool;
import nc.vo.so.pub.keyvalue.IKeyRela;
import nc.vo.so.pub.keyvalue.IKeyValue;
import nc.vo.so.pub.keyvalue.SOKeyRela;
import nc.vo.so.pub.util.ArrayUtil;
import nc.vo.uap.taxcode.TaxType;

import org.apache.commons.lang.StringUtils;

/**
 * 销售管理报价单、预订单、订单、发票、发货单单据取税信息规则
 * 
 * @since 6.0
 * @version 2012-2-5 下午02:16:18
 * @author 刘景
 */
public class SOTaxInfoRule {

  private IKeyRela keyRela;

  private IKeyValue keyValue;

  /**
   * 税率或者计税类别改变行
   */
  private List<Integer> listchgrow;

  public SOTaxInfoRule(IKeyValue keyValue) {
    this.keyValue = keyValue;
    this.keyRela = new SOKeyRela();
  }

  public SOTaxInfoRule(IKeyValue keyValue, IKeyRela keyRela) {
    this.keyValue = keyValue;
    this.keyRela = keyRela;
  }

  /**
   * 在设置税率信息后获取税率或者计税类别改变行，
   * 如果是税码改变后不用调用此方法，认为所有行都改变
   * 
   * @return
   */
  public int[] getTaxChangeRows() {
    if (null == this.listchgrow || this.listchgrow.size() == 0) {
      return new int[0];
    }
    int[] chgrows = new int[this.listchgrow.size()];
    int i = 0;
    for (Integer chgrow : this.listchgrow) {
      chgrows[i] = chgrow;
      i++;
    }
    return chgrows;
  }
  
  /**
   * 设置 发货单VAT税相关信息（开票客户在表体）
   * 
   * @param rows
   */
  public void setDeliveryTaxInfo(int[] rows) {
    // 需要设置税码的行
    int[] zerorows = this.getDeliveryZeroTaxCodeRows(rows);
    // 所有行都是同公司
    VATInfoVO vo = this.initZeroTaxCode();
    VATInfoVO[] zerotaxs = new VATInfoVO[zerorows.length];
    for (int i = 0; i < zerorows.length; i++) {
      zerotaxs[i] = vo;
    }
    this.setTaxInfo(zerotaxs, zerorows, false);
    if (zerorows.length != rows.length) {
      int[] nozerorows = ArrayUtil.subArrays(rows, zerorows);
      VATInfoQueryVO[] qryvos = this.getVATInfoQueryVOByBodyPos(nozerorows);
      VATInfoVO[] vatinfos = VATBDService.queryCustVATInfo(qryvos);
      // 设置VAT税信息
      this.setTaxInfo(vatinfos, nozerorows, false);
    }
  }

  /**
   * 设置报价单、预订单、订单等取税参数在表体的单据VAT税相关信息
   * 
   * @param rows
   */
  public void setTaxInfoByBodyPos(int[] rows) {
    // 需要设置0税码的行
    int[] zerorows = this.getZeroTaxCodeRows(rows);
    // 所有行都是同公司
    VATInfoVO vo = this.initZeroTaxCode();
    VATInfoVO[] zerotaxs = new VATInfoVO[zerorows.length];
    for (int i = 0; i < zerorows.length; i++) {
      zerotaxs[i] = vo;
    }
    this.setTaxInfo(zerotaxs, zerorows, false);
    if (zerorows.length != rows.length) {
      int[] nozerorows = ArrayUtil.subArrays(rows, zerorows);
      VATInfoQueryVO[] qryvos = this.getVATInfoQueryVOByBodyPos(nozerorows);
      VATInfoVO[] vatinfos = VATBDService.queryCustVATInfo(qryvos);
      // 设置VAT税信息
      this.setTaxInfo(vatinfos, nozerorows, false);
    }
  }

  /**
   * 设置报价单、预订单、订单等取税参数在表体的单据VAT税相关信息
   * 
   * @param rows
   */
  public void setOnlyTaxCodeByBodyPos(int[] rows) {
    // 需要设置0税码的行
    int[] zerorows = this.getZeroTaxCodeRows(rows);
    // 所有行都是同公司
    VATInfoVO vo = this.initZeroTaxCode();
    VATInfoVO[] zerotaxs = new VATInfoVO[zerorows.length];
    for (int i = 0; i < zerorows.length; i++) {
      zerotaxs[i] = vo;
    }
    this.setTaxInfo(zerotaxs, zerorows, true);
    if (zerorows.length != rows.length) {
      int[] nozerorows = ArrayUtil.subArrays(rows, zerorows);
      VATInfoQueryVO[] qryvos = this.getVATInfoQueryVOByBodyPos(nozerorows);
      VATInfoVO[] vatinfos = VATBDService.queryCustVATInfo(qryvos);
      // 设置VAT税信息
      this.setTaxInfo(vatinfos, nozerorows, true);
    }
  }

  /**
   * 设置发票等取税参数在表头的单据VAT税相关信息
   * 
   * @param rows
   */
  public void setTaxInfoByHeadPos(int[] rows) {
    // 是否同公司
    boolean iscomoncorp = this.isZeroTaxCodeByHead();
    if (iscomoncorp) {
      VATInfoVO vo = this.initZeroTaxCode();
      VATInfoVO[] vatinfos = new VATInfoVO[rows.length];
      for (int i = 0; i < rows.length; i++) {
        vatinfos[i] = vo;
      }
      this.setTaxInfo(vatinfos, rows, false);
    }
    else {
      VATInfoQueryVO[] qryvos = this.getVATInfoQueryVOByHeadPos(rows);
      VATInfoVO[] vatinfos = VATBDService.queryCustVATInfo(qryvos);
      // 设置VAT税信息
      this.setTaxInfo(vatinfos, rows, false);
    }
  }

  private VATInfoVO initZeroTaxCode() {
    String ZeroTaxCode = ZeroTaxCodeEnum.ZEROTAXCODE.getCode();
    VATInfoVO zerotax =
        new VATInfoVO(ZeroTaxCode, Integer.valueOf(TaxType.TAXABLE_PLUS
            .toIntValue()), UFDouble.ZERO_DBL, UFDouble.ZERO_DBL);
    return zerotax;

  }

  /**
   * 财务组织在表头（销售发票）
   * 
   * @return
   */
  private boolean isZeroTaxCodeByHead() {

    // 购销类型
    SOBuysellTriaRule buyrule = new SOBuysellTriaRule(this.keyValue);
    if (buyrule.isHeadBuysellFlagOut()) {
      return true;
    }
    // 开票客户
    String invcustid =
        this.keyValue.getHeadStringValue(this.keyRela.getCinvoicecustidKey());
    String pk_finorg =
        this.keyValue.getHeadStringValue(this.keyRela.getCsettleorgidKey());
    Map<String, String> custmap =
        CustomerPubService.getCustomerFinorgs(new String[] {
          invcustid
        });
    String custorg = custmap.get(invcustid);
    if (pk_finorg.equals(custorg)) {
      return true;
    }
    if (null == custorg) {
      return false;
    }
    String[] finorgs = new String[] {
      pk_finorg, custorg
    };
    Map<String, String> orgcorpmap = OrgUnitPubService.getOrgCorp(finorgs);
    String finorgcorg = orgcorpmap.get(pk_finorg);
    if (finorgcorg.equals(orgcorpmap.get(custorg))) {
      return true;
    }
    return false;
  }
  
  /**
   * 
   * 返回发货单需要设置税码的行(开票客户在表体)
   * 
   * @return 发货单需要设置税码的行
   */
  private int[] getDeliveryZeroTaxCodeRows(int[] rows) {
    Set<Integer> retrows = new HashSet<Integer>();
    Set<String> custids = new HashSet<String>();
    Set<String> finorgs = new HashSet<String>();
    for (int row : rows) {
      // 开票客户
      String invcustid =
          this.keyValue.getBodyStringValue(row,
              this.keyRela.getCinvoicecustidKey());
      if (!PubAppTool.isNull(invcustid)) {
        custids.add(invcustid);
      }
    }
    Map<String, String> custmap =
        CustomerPubService.getCustomerFinorgs(custids
            .toArray(new String[custids.size()]));
    for (int row : rows) {
      String pk_finorg =
          this.keyValue.getBodyStringValue(row,
              this.keyRela.getCsettleorgidKey());
      finorgs.add(pk_finorg);
      String invcustid =
          this.keyValue.getBodyStringValue(row,
              this.keyRela.getCinvoicecustidKey());
      // 开票客户所属财务组织与表体财务组织一样（同公司）
      if (pk_finorg != null && pk_finorg.equals(custmap.get(invcustid))) {
        retrows.add(Integer.valueOf(row));
      }
    }
    Map<String, String> orgcorpmap =
        OrgUnitPubService
            .getOrgCorp(finorgs.toArray(new String[finorgs.size()]));
    for (int row : rows) {
      String pk_finorg =
          this.keyValue.getBodyStringValue(row,
              this.keyRela.getCsettleorgidKey());

      String invcustid =
          this.keyValue.getBodyStringValue(row,
              this.keyRela.getCinvoicecustidKey());
      // 结算财务组织所在公司
      String finorgcorg = orgcorpmap.get(pk_finorg);
      // 开票客户所在组织结算财务
      String custorg = custmap.get(invcustid);

      // 购销类型
      SOBuysellTriaRule buyrule = new SOBuysellTriaRule(this.keyValue);

      // 开票客户所属结算财务组织与结算财务组织所在公司相同
      if (finorgcorg.equals(custorg) && !buyrule.isBuysellFlagOut(row)) {
        retrows.add(Integer.valueOf(row));
      }
    }
    int[] ret = new int[retrows.size()];
    int i = 0;
    for (Integer row : retrows) {
      ret[i] = row.intValue();
      i++;
    }
    return ret;
  }

  /**
   * 
   * 返回需要设置0税码的行
   * 预订单、报价单、订单
   * 
   * @return
   */
  private int[] getZeroTaxCodeRows(int[] rows) {
    // 开票客户
    String invcustid =
        this.keyValue.getHeadStringValue(this.keyRela.getCinvoicecustidKey());
    if (StringUtils.isEmpty(invcustid)) {
      return new int[0];
    }
    Map<String, String> custmap =
        CustomerPubService.getCustomerFinorgs(new String[] {
          invcustid
        });
    String custorg = custmap.get(invcustid);
    if (null == custorg) {
      return new int[0];
    }
    Set<String> finorgs = new HashSet<String>();

    for (int row : rows) {
      String pk_finorg =
          this.keyValue.getBodyStringValue(row,
              this.keyRela.getCsettleorgidKey());
      if (null != pk_finorg) {
        finorgs.add(pk_finorg);
      }
    }
    // finorgs长度为0说明 表体财务组织都是空的,则都按跨公司处理
    if (finorgs.size() == 0) {
      return new int[0];
    }
    finorgs.add(custorg);
    // finorgs长度为1说明 开票客户所属财务组织与表体财务组织一样（同公司）
    if (finorgs.size() == 1) {
      return rows;
    }
    List<Integer> zerorow = new ArrayList<Integer>();
    Map<String, String> orgcorpmap =
        OrgUnitPubService
            .getOrgCorp(finorgs.toArray(new String[finorgs.size()]));
    for (int row : rows) {
      String pk_finorg =
          this.keyValue.getBodyStringValue(row,
              this.keyRela.getCsettleorgidKey());
      String finorgcorg = orgcorpmap.get(pk_finorg);
      // 购销类型
      SOBuysellTriaRule buyrule = new SOBuysellTriaRule(this.keyValue);

      if (finorgcorg.equals(orgcorpmap.get(custorg))
          && !buyrule.isBuysellFlagOut(row)) {
        zerorow.add(Integer.valueOf(row));
      }
    }
    int[] rets = new int[zerorow.size()];
    for (int i = 0; i < zerorow.size(); i++) {
      rets[i] = zerorow.get(i).intValue();
    }

    return rets;
  }

  /**
   * 根据税码、业务日期设置税率、扣税类别
   * 
   * @param rows
   */
  public void setTaxTypeAndRate(int[] rows) {

    UFDate dbilldate =
        this.keyValue.getHeadUFDateValue(this.keyRela.getDbilldateKey());
    Map<String, VATInfoByTaxcodeQueryVO> mapqryvo =
        new HashMap<String, VATInfoByTaxcodeQueryVO>();
    for (int row : rows) {
      String taxcode =
          this.keyValue
              .getBodyStringValue(row, this.keyRela.getCtaxcodeidKey());
      if (PubAppTool.isNull(taxcode) || mapqryvo.containsKey(taxcode)) {
        continue;
      }
      VATInfoByTaxcodeQueryVO queryvo =
          new VATInfoByTaxcodeQueryVO(taxcode, dbilldate);
      mapqryvo.put(taxcode, queryvo);
    }

    Map<String, VATInfoVO> mapvatinfo = new HashMap<String, VATInfoVO>();
    if (mapqryvo.size() > 0) {
      VATInfoByTaxcodeQueryVO[] queryvos =
          new VATInfoByTaxcodeQueryVO[mapqryvo.values().size()];
      mapqryvo.values().toArray(queryvos);
      VATInfoVO[] vatinfos = VATBDService.queryVATInfo(queryvos);

      for (VATInfoVO info : vatinfos) {
        if (null != info) {
          mapvatinfo.put(info.getCtaxcodeid(), info);
        }
      }
    }

    for (int row : rows) {
      String taxcode =
          this.keyValue
              .getBodyStringValue(row, this.keyRela.getCtaxcodeidKey());

      this.keyValue.setBodyValue(row, this.keyRela.getFtaxtypeflagKey(), null);
      this.keyValue.setBodyValue(row, this.keyRela.getNtaxrateKey(), null);

      if (!PubAppTool.isNull(taxcode)) {
        VATInfoVO vatinfo = mapvatinfo.get(taxcode);
        if (null != vatinfo) {
          this.keyValue.setBodyValue(row, this.keyRela.getFtaxtypeflagKey(),
              vatinfo.getFtaxtypeflag());
          this.keyValue.setBodyValue(row, this.keyRela.getNtaxrateKey(),
              vatinfo.getNtaxrate());
        }
      }
    }
  }

  private VATInfoQueryVO[] getVATInfoQueryVOByBodyPos(int[] rows) {

    VATInfoQueryVO[] qryvos = new VATInfoQueryVO[rows.length];
    String invcustid =
        this.keyValue.getHeadStringValue(this.keyRela.getCinvoicecustidKey());
    UFDate dbizdate =
        this.keyValue.getHeadUFDateValue(this.keyRela.getDbilldateKey());
    int i = 0;
    for (int row : rows) {
      // 报税国
      String ctaxcountryid =
          this.keyValue.getBodyStringValue(row,
              this.keyRela.getCtaxcountryidKey());
      // 购销类型
      Integer buysellfalg =
          this.keyValue.getBodyIntegerValue(row,
              this.keyRela.getFbuysellflagKey());
      BuySellFlagEnum fbuysellfalg = BuySellFlagEnum.valueOf(buysellfalg);
      // 三角贸易
      UFBoolean btriatradeflag =
          this.keyValue.getBodyUFBooleanValue(row,
              this.keyRela.getBtriatradeflagKey());
      // 发货国
      String csendcountryid =
          this.keyValue.getBodyStringValue(row,
              this.keyRela.getCsendcountryidKey());
      // 收货国
      String crececountryid =
          this.keyValue.getBodyStringValue(row,
              this.keyRela.getCrececountryidKey());
      // 物料
      String cmaterialvid =
          this.keyValue.getBodyStringValue(row,
              this.keyRela.getCmaterialvidKey());
      qryvos[i] =
          new VATInfoQueryVO(ctaxcountryid, fbuysellfalg, btriatradeflag,
              csendcountryid, crececountryid, invcustid, cmaterialvid, dbizdate);
      i++;
    }
    return qryvos;
  }

  private VATInfoQueryVO[] getVATInfoQueryVOByHeadPos(int[] rows) {
    VATInfoQueryVO[] qryvos = new VATInfoQueryVO[rows.length];
    // 开票客户
    String invcustid =
        this.keyValue.getHeadStringValue(this.keyRela.getCinvoicecustidKey());
    // 业务日期
    UFDate dbizdate =
        this.keyValue.getHeadUFDateValue(this.keyRela.getDbilldateKey());
    // 报税国
    String ctaxcountryid =
        this.keyValue.getHeadStringValue(this.keyRela.getCtaxcountryidKey());
    // 购销类型
    Integer buysellfalg =
        this.keyValue.getHeadIntegerValue(this.keyRela.getFbuysellflagKey());
    BuySellFlagEnum fbuysellfalg = BuySellFlagEnum.valueOf(buysellfalg);
    // 三角贸易
    UFBoolean btriatradeflag =
        this.keyValue
            .getHeadUFBooleanValue(this.keyRela.getBtriatradeflagKey());
    // 发货国
    String csendcountryid =
        this.keyValue.getHeadStringValue(this.keyRela.getCsendcountryidKey());
    // 收货国
    String crececountryid =
        this.keyValue.getHeadStringValue(this.keyRela.getCrececountryidKey());

    int i = 0;
    for (int row : rows) {
      // 物料
      String cmaterialvid =
          this.keyValue.getBodyStringValue(row,
              this.keyRela.getCmaterialvidKey());
      // 发货单开票客户在表体
      if (PubAppTool.isNull(invcustid)) {
        invcustid =
            this.keyValue.getBodyStringValue(row,
                this.keyRela.getCinvoicecustidKey());
      }
      qryvos[i] =
          new VATInfoQueryVO(ctaxcountryid, fbuysellfalg, btriatradeflag,
              csendcountryid, crececountryid, invcustid, cmaterialvid, dbizdate);
      i++;
    }
    return qryvos;
  }

  private void setTaxInfo(VATInfoVO[] vatinfos, int[] rows, boolean isonlycode) {
    int i = 0;
    this.listchgrow = new ArrayList<Integer>();
    for (int row : rows) {

      // 新税码信息
      String newtaxcode = null;
      Integer newtaxtype = null;
      UFDouble newtaxrate = null;
      if (null != vatinfos[i]) {
        newtaxcode = vatinfos[i].getCtaxcodeid();
        newtaxtype = vatinfos[i].getFtaxtypeflag();
        newtaxrate = vatinfos[i].getNtaxrate();
      }
      if (isonlycode) {
        this.keyValue.setBodyValue(row, this.keyRela.getCtaxcodeidKey(),
            newtaxcode);
      }
      else {
        // 原始税码
        String oldtaxcode =
            this.keyValue.getBodyStringValue(row,
                this.keyRela.getCtaxcodeidKey());
        // 原始税率
        UFDouble oldtaxrate =
            this.keyValue.getBodyUFDoubleValue(row,
                this.keyRela.getNtaxrateKey());
        // 原始计税类型
        Integer oldtaxtype =
            this.keyValue.getBodyIntegerValue(row,
                this.keyRela.getFtaxtypeflagKey());

        // 税率为空或税码变化
        if (null == oldtaxrate || !PubAppTool.isEqual(oldtaxcode, newtaxcode)) {
          this.keyValue.setBodyValue(row, this.keyRela.getCtaxcodeidKey(),
              newtaxcode);
          this.keyValue.setBodyValue(row, this.keyRela.getFtaxtypeflagKey(),
              newtaxtype);
          this.keyValue.setBodyValue(row, this.keyRela.getNtaxrateKey(),
              newtaxrate);
          this.listchgrow.add(row);
        }// 只是计税类别发生变化
        else if (!PubAppTool.isEqual(oldtaxtype, newtaxtype)) {
          this.keyValue.setBodyValue(row, this.keyRela.getFtaxtypeflagKey(),
              newtaxtype);
          this.listchgrow.add(row);
        }
      }
      i++;
    }
  }
}
