package nc.vo.so.pub.rule;

import java.util.Map;

import nc.itf.scmpub.reference.uap.bd.customer.CustomerPubService;
import nc.itf.scmpub.reference.uap.org.DeptPubService;
import nc.itf.scmpub.reference.uap.org.OrgUnitPubService;
import nc.vo.bd.cust.saleinfo.CustsaleVO;
import nc.vo.pub.lang.UFDouble;
import nc.vo.pubapp.pattern.pub.PubAppTool;
import nc.vo.so.m38.entity.PreOrderBVO;
import nc.vo.so.pub.SOItemKey;
import nc.vo.so.pub.keyvalue.IKeyValue;

/**
 * 客户相关默认值设置
 * 
 * @since 6.0
 * @version 2012-3-12 上午11:20:51
 * @author 么贵敬
 */
public class SOCustRelaDefValueRule {

  private boolean isdisratechg;

  private IKeyValue keyValue;

  public SOCustRelaDefValueRule(IKeyValue keyValue) {
    this.keyValue = keyValue;
  }

  public boolean isDiscountRateChg() {
    return this.isdisratechg;
  }

  public void setCustRelaDefValue() {

    String old_origcur =
        this.keyValue.getHeadStringValue(SOItemKey.CORIGCURRENCYID);
    UFDouble old_discountrate =
        this.keyValue.getHeadUFDoubleValue(SOItemKey.NDISCOUNTRATE);
    // 1.根据客户档案设置默认值
    String[] fieldNames =
        new String[] {
        // 专管部门、专管业务员、开票客户、运输方式
        CustsaleVO.RESPDEPT, CustsaleVO.RESPPERSON,
        CustsaleVO.BILLINGCUST,
        CustsaleVO.SHIPPINGTYPE,
        // 默认交易币种、默认收付款协议、整单折扣、渠道类型
        CustsaleVO.CURRENCYDEFAULT, CustsaleVO.PAYTERMDEFAULT,
        CustsaleVO.DISCOUNTRATE, CustsaleVO.CHANNEL, CustsaleVO.PK_TRADETERM,
        CustsaleVO.ISSUECUST
    };
    CustsaleVO retVO = this.getCustSaleVO(fieldNames);
    // 设置默认值
    // 没有专管业务员不清空，登陆用户默认业务员，问题ID：NCdp205106387
    String cemployeeid = retVO.getRespperson();
    if (!PubAppTool.isNull(cemployeeid)) {
      this.keyValue.setHeadValue(SOItemKey.CEMPLOYEEID, cemployeeid);
    }
    this.keyValue.setHeadValue(SOItemKey.CCHANNELTYPEID, retVO.getChannel());
    this.keyValue.setHeadValue(SOItemKey.CTRADEWORDID, retVO.getPk_tradeterm());
    this.keyValue.setHeadValue(SOItemKey.CTRANSPORTTYPEID,
        retVO.getShippingtype());
    this.keyValue.setHeadValue(SOItemKey.CPAYTERMID, retVO.getPaytermdefault());

    // 2.客户没有专管部门
    String deptid = retVO.getRespdept();
    if (!PubAppTool.isNull(deptid)) {
      this.keyValue.setHeadValue(SOItemKey.CDEPTID, deptid);
      String[] pk_depts = new String[] {
          deptid
      };
      Map<String, String> mapvids =
          DeptPubService.getLastVIDSByDeptIDS(pk_depts);
      this.keyValue.setHeadValue(SOItemKey.CDEPTVID, mapvids.get(deptid));
    }
    // 3.客户没有默认开票客户，取客户本身
    String invcus = retVO.getBillingcust();
    if (PubAppTool.isNull(invcus)) {
      invcus = this.keyValue.getHeadStringValue(SOItemKey.CCUSTOMERID);
    }
    this.keyValue.setHeadValue(SOItemKey.CINVOICECUSTID, invcus);

    // 4.客户没有默认币种，取销售组织本位币
    String origcurr = retVO.getCurrencydefault();
    if (PubAppTool.isNull(origcurr)) {
      origcurr = this.getOrgCurr();
    }
    this.keyValue.setHeadValue(SOItemKey.CORIGCURRENCYID, origcurr);
    if (PubAppTool.isNull(old_origcur) || !old_origcur.equals(origcurr)) {
      // 更新表体币种
      BodyUpdateByHead updaterule = new BodyUpdateByHead(this.keyValue);
      updaterule.updateAllBodyValueByHead(SOItemKey.CORIGCURRENCYID,
          PreOrderBVO.CORIGCURRENCYID);
    }

    // 5.整单折扣优先级：1)客户档案默认 2)原单值 3)默认100
    UFDouble discountrate = retVO.getDiscountrate();
    if (null == discountrate) {
      if (null != old_discountrate) {
        discountrate = old_discountrate;
      }
      else {
        discountrate = new UFDouble(100);
      }
    }
    this.keyValue.setHeadValue(SOItemKey.NDISCOUNTRATE, discountrate);
    if (null == old_discountrate
        || discountrate.compareTo(old_discountrate) != 0) {
      // 更新表体整单折扣
      BodyUpdateByHead updaterule = new BodyUpdateByHead(this.keyValue);
      updaterule.updateAllBodyValueByHead(PreOrderBVO.NDISCOUNTRATE,
          SOItemKey.NDISCOUNTRATE);
      this.isdisratechg = true;
    }
    // 6.清空原先散户字段值
    this.keyValue.setHeadValue(SOItemKey.CFREECUSTID, null);

    // 7.设置表体收货客户
    BodyValueRowRule countutil = new BodyValueRowRule(this.keyValue);
    int[] rows = countutil.getMarNotNullRows();
    String rececust = retVO.getIssuecust();
    if (PubAppTool.isNull(rececust)) {
      rececust = this.keyValue.getHeadStringValue(SOItemKey.CCUSTOMERID);
    }
    for (int row : rows) {
      this.keyValue.setBodyValue(row, SOItemKey.CRECEIVECUSTID, rececust);
    }

    // 7.2 V635新增，表头收货客户
    this.keyValue.setHeadValue(SOItemKey.CRECEIVECUSTID, rececust);

  }

  /**
   * 设置客户默认交易币种
   */
  public void setCustRelaCurrency() {
    // 根据客户档案设置默认值
    String[] fieldNames = new String[] {
        // 默认交易币种
        CustsaleVO.CURRENCYDEFAULT
    };
    CustsaleVO retVO = this.getCustSaleVO(fieldNames);
    // 客户没有默认币种，取销售组织本位币
    String origcurr = retVO.getCurrencydefault();
    if (PubAppTool.isNull(origcurr)) {
      origcurr = this.getOrgCurr();
    }
    this.keyValue.setHeadValue(SOItemKey.CORIGCURRENCYID, origcurr);
  }

  /**
   * 设置客户默认收货客户
   */
  public void setRelaReceiveCust(int[] rows) {
    // 根据客户档案设置默认值
    String[] fieldNames = new String[] {
        // 收货客户
        CustsaleVO.ISSUECUST
    };
    CustsaleVO retVO = this.getCustSaleVO(fieldNames);

    String recust = this.keyValue.getHeadStringValue(SOItemKey.CRECEIVECUSTID);
    if (PubAppTool.isNull(recust)) {
      recust = retVO.getIssuecust();
    }

    if (PubAppTool.isNull(recust)) {
      recust = this.keyValue.getHeadStringValue(SOItemKey.CCUSTOMERID);
    }
    for (int row : rows) {
      this.keyValue.setBodyValue(row, SOItemKey.CRECEIVECUSTID, recust);
    }
  }

  public void setCustRelaInvoiceCust() {

    // 根据客户档案设置默认值
    String[] fieldNames = new String[] {
        // 开票客户
        CustsaleVO.BILLINGCUST
    };
    CustsaleVO retVO = this.getCustSaleVO(fieldNames);
    // 客户没有默认开票客户，取客户本身
    String invcus = retVO.getBillingcust();
    if (PubAppTool.isNull(invcus)) {
      invcus = this.keyValue.getHeadStringValue(SOItemKey.CCUSTOMERID);
    }
    this.keyValue.setHeadValue(SOItemKey.CINVOICECUSTID, invcus);
  }

  private CustsaleVO getCustSaleVO(String[] fieldNames) {

    String pk_org = this.keyValue.getHeadStringValue(SOItemKey.PK_ORG);
    String customer = this.keyValue.getHeadStringValue(SOItemKey.CCUSTOMERID);
    if (PubAppTool.isNull(customer)) {
      return new CustsaleVO();
    }
    Map<String, CustsaleVO> mret =
        CustomerPubService.getCustSaleVOByPks(new String[] {
            customer
        }, pk_org, fieldNames);

    if (null == mret || mret.size() == 0) {
      return new CustsaleVO();
    }
    return mret.get(customer);
  }

  private String getOrgCurr() {
    String pk_org = this.keyValue.getHeadStringValue(SOItemKey.PK_ORG);
    Map<String, String> orgCurrMap = null;

    orgCurrMap = OrgUnitPubService.queryOrgCurrByPk(new String[] {
        pk_org
    });

    if (null != orgCurrMap) {
      return orgCurrMap.get(pk_org);
    }
    return null;
  }
}
