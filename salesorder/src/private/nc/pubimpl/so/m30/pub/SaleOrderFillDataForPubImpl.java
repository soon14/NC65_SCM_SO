package nc.pubimpl.so.m30.pub;

import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;

import nc.vo.pub.BusinessException;
import nc.vo.pub.lang.UFDate;
import nc.vo.pub.lang.UFDouble;
import nc.vo.pubapp.AppContext;
import nc.vo.pubapp.calculator.HslParseUtil;
import nc.vo.pubapp.pattern.pub.MathTool;
import nc.vo.pubapp.pattern.pub.PubAppTool;
import nc.vo.scmpub.res.billtype.SOBillType;
import nc.vo.so.m30.entity.SaleOrderBVO;
import nc.vo.so.m30.entity.SaleOrderHVO;
import nc.vo.so.m30.entity.SaleOrderVO;
import nc.vo.so.m30.pub.SaleOrderVOCalculator;
import nc.vo.so.m30.rule.DirectStoreRule;
import nc.vo.so.m30.rule.PayTermRule;
import nc.vo.so.pub.SOConstant;
import nc.vo.so.pub.enumeration.BillStatus;
import nc.vo.so.pub.keyvalue.IKeyValue;
import nc.vo.so.pub.keyvalue.VOKeyValue;
import nc.vo.so.pub.rule.BodyValueRowRule;
import nc.vo.so.pub.rule.SOBuysellTriaRule;
import nc.vo.so.pub.rule.SOCountryInfoRule;
import nc.vo.so.pub.rule.SOCurrencyRule;
import nc.vo.so.pub.rule.SOCustRelaDefValueRule;
import nc.vo.so.pub.rule.SOExchangeRateRule;
import nc.vo.so.pub.rule.SOGlobalExchangeRate;
import nc.vo.so.pub.rule.SOGroupExchangeRate;
import nc.vo.so.pub.rule.SOTaxInfoRule;
import nc.vo.so.pub.rule.SOUnitChangeRateRule;
import nc.vo.so.pub.rule.SaleOrgRelationRule;
import nc.vo.so.pub.util.AggVOUtil;
import nc.vo.so.pub.util.ArrayUtil;

import nc.itf.scmpub.reference.uap.bd.psn.PsndocPubService;
import nc.itf.scmpub.reference.uap.org.DeptPubService;
import nc.itf.scmpub.reference.uap.pf.PfServiceScmUtil;
import nc.itf.scmpub.reference.uap.rbac.UserManageQuery;

import nc.pubitf.so.m30.pub.ISaleOrderFillDataForPub;

import nc.bs.pubapp.AppBsContext;

/**
 * 销售订单针对外模块生成销售订单时提供的补全数据接口实现
 * 
 * @since 6.3
 * @version 2013-11-20 09:11:32
 * @author liujingn
 */
public class SaleOrderFillDataForPubImpl implements ISaleOrderFillDataForPub {

  @Override
  public SaleOrderVO[] getFillSaleorderVO(SaleOrderVO[] ordervos)
      throws BusinessException {

    SaleOrderVO[] oldordervos = ordervos;
    Map<String, String> mapbiztype = new HashMap<String, String>();

    // 人员、部门
    this.setEmplyDept(ordervos);

    for (SaleOrderVO salebillvo : ordervos) {
      IKeyValue keyValue = new VOKeyValue<SaleOrderVO>(salebillvo);

      // 匹配业务流程
      this.setBusitype(mapbiztype, keyValue);

      // 填充强制单据默认值
      this.setForceDefValue(keyValue);

      // 设置收款协议信息
      PayTermRule payTermRule = new PayTermRule(keyValue);
      payTermRule.setPayTermInfo();

      if (salebillvo.getChildrenVO() == null
          || salebillvo.getChildrenVO().length == 0) {
        continue;
      }

      // 发货库存组织、物流组织、财务组织
      BodyValueRowRule bodycouuitl = new BodyValueRowRule(keyValue);

      SaleOrgRelationRule orgrelrule = new SaleOrgRelationRule(keyValue);
      int[] sendstockrows =
          bodycouuitl.getValueNullRows(SaleOrderBVO.CSENDSTOCKORGVID);
      orgrelrule.setSendStockOrg(sendstockrows);

      int[] trafficrows =
          bodycouuitl.getValueNullRows(SaleOrderBVO.CTRAFFICORGVID);
      orgrelrule.setTrafficOrg(trafficrows);

      int[] finacerows =
          bodycouuitl.getValueNullRows(SaleOrderBVO.CSETTLEORGVID);
      orgrelrule.setFinanceOrg(finacerows);

      // 组织本位币
      SOCurrencyRule currule = new SOCurrencyRule(keyValue);
      currule.setCurrency(finacerows);
      // 折本汇率
      SOExchangeRateRule exrule = new SOExchangeRateRule(keyValue);
      exrule.calcBodyExchangeRates(finacerows);

      // 收货客户
      int[] custisnullrows =
          bodycouuitl.getValueNullRows(SaleOrderBVO.CRECEIVECUSTID);
      SOCustRelaDefValueRule custrefrule = new SOCustRelaDefValueRule(keyValue);
      custrefrule.setRelaReceiveCust(custisnullrows);

      // 国家
      int[] needchangerows = ArrayUtil.combinArrays(sendstockrows, finacerows);
      SOCountryInfoRule countryrule = new SOCountryInfoRule(keyValue);
      countryrule.setCountryInfo(needchangerows);
      // 购销类型
      SOBuysellTriaRule buyflgrule = new SOBuysellTriaRule(keyValue);
      buyflgrule.setBuysellAndTriaFlag(needchangerows);

      // 询税
      SOTaxInfoRule taxInfo = new SOTaxInfoRule(keyValue);
      taxInfo.setTaxInfoByBodyPos(needchangerows);

      int[] rows = bodycouuitl.getMarNotNullRows();
      // 计算换算率
      SOUnitChangeRateRule unitrate = new SOUnitChangeRateRule(keyValue);
      //性能优化：批量处理 add by zhangby5
      unitrate.calcAstAndQtChangeRate(rows);
      // 计算主数量
      for (int row : rows) {
        UFDouble nnum = keyValue.getBodyUFDoubleValue(row, SaleOrderBVO.NNUM);

        UFDouble nassistnum =
            keyValue.getBodyUFDoubleValue(row, SaleOrderBVO.NASTNUM);

        String nchangerate =
            keyValue.getBodyStringValue(row, SaleOrderBVO.VCHANGERATE);

        if (MathTool.isZero(nnum)) {
          nnum = HslParseUtil.hslMultiplyUFDouble(nchangerate, nassistnum);
          keyValue.setBodyValue(row, SaleOrderBVO.NNUM, nnum);
        }
      }

      // 直运仓
      if (!PubAppTool.isNull(salebillvo.getParentVO().getCtrantypeid())) {
        DirectStoreRule dirstorerule = new DirectStoreRule(keyValue);
        dirstorerule.setDirectStore(rows);
      }

      // 集团、全局汇率计算
      SOGlobalExchangeRate globalraterule = new SOGlobalExchangeRate(keyValue);
      globalraterule.calcGlobalExchangeRate(rows);

      SOGroupExchangeRate groupraterule = new SOGroupExchangeRate(keyValue);
      groupraterule.calcGroupExchangeRate(rows);
    }

    // 因为此接口供给多个模块，每个模块所补数据不同，可能会导致覆盖上游单据数据，所以在此做合并处理。
    return (SaleOrderVO[]) AggVOUtil.combinBillVO(oldordervos, ordervos);
  }

  @Override
  public void calSaleOrderNumpriceMny(SaleOrderVO[] ordervos, String editkey)
      throws BusinessException {

    for (SaleOrderVO salebillvo : ordervos) {
      IKeyValue keyValue = new VOKeyValue<SaleOrderVO>(salebillvo);

      SaleOrderVOCalculator vocalcultor = new SaleOrderVOCalculator(salebillvo);

      BodyValueRowRule bodycouuitl = new BodyValueRowRule(keyValue);

      vocalcultor.calculate(bodycouuitl.getMarNotNullRows(), editkey);
    }
  }

  private void setEmplyDept(SaleOrderVO[] ordervos) {
    Set<String> setemploy = new HashSet<String>();

    for (SaleOrderVO salebillvo : ordervos) {
      IKeyValue keyValue = new VOKeyValue<SaleOrderVO>(salebillvo);
      String cemployeeid =
          keyValue.getHeadStringValue(SaleOrderHVO.CEMPLOYEEID);
      if (PubAppTool.isNull(cemployeeid)) {
        cemployeeid =
            UserManageQuery.queryPsndocByUserid(AppContext.getInstance()
                .getPkUser());
      }
      keyValue.setHeadValue(SaleOrderHVO.CEMPLOYEEID, cemployeeid);
      setemploy.add(cemployeeid);
    }
    if (setemploy.size() == 0) {
      return;
    }
    Map<String, List<String>> deptoldid =
        PsndocPubService.queryDeptIDByPsndocIDs(setemploy
            .toArray(new String[setemploy.size()]));
    Set<String> depold = new HashSet<String>();
    for (SaleOrderVO salebillvo : ordervos) {
      IKeyValue keyValue = new VOKeyValue<SaleOrderVO>(salebillvo);
      String cemployeeid =
          keyValue.getHeadStringValue(SaleOrderHVO.CEMPLOYEEID);
      String tmpeolddep = deptoldid.get(cemployeeid).get(0);
      keyValue.setHeadValue(SaleOrderHVO.CDEPTID, tmpeolddep);
      depold.add(tmpeolddep);
    }
    Map<String, String> depvid =
        DeptPubService.getLastVIDSByDeptIDS(depold.toArray(new String[depold
            .size()]));
    for (SaleOrderVO salebillvo : ordervos) {
      IKeyValue keyValue = new VOKeyValue<SaleOrderVO>(salebillvo);
      String cdeptid = keyValue.getHeadStringValue(SaleOrderHVO.CDEPTID);
      keyValue.setHeadValue(SaleOrderHVO.CDEPTVID, depvid.get(cdeptid));
    }
  }

  private void setBusitype(Map<String, String> mapbiztype, IKeyValue keyValue) {
    // 匹配业务流程
    String trantypecode =
        keyValue.getHeadStringValue(SaleOrderHVO.VTRANTYPECODE);
    if (PubAppTool.isNull(trantypecode)) {
      return;
    }
    String pk_org = keyValue.getHeadStringValue(SaleOrderHVO.PK_ORG);
    String bizkey = pk_org + trantypecode;
    if (mapbiztype.containsKey(bizkey)) {
      keyValue.setHeadValue(SaleOrderHVO.CBIZTYPEID, mapbiztype.get(bizkey));
    }
    else {
      String userId = AppContext.getInstance().getPkUser();
      String newbiztype =
          PfServiceScmUtil.getBusitype(SOBillType.Order.getCode(),
              trantypecode, pk_org, userId);
      keyValue.setHeadValue(SaleOrderHVO.CBIZTYPEID, newbiztype);
      mapbiztype.put(bizkey, newbiztype);
    }
  }

  /**
   * 设置转单后强制的数据默认值
   */
  private void setForceDefValue(IKeyValue keyValue) {

    // 单据状态
    keyValue.setHeadValue(SaleOrderHVO.FSTATUSFLAG,
        BillStatus.FREE.getIntegerValue());
    // 整单折扣
    UFDouble discountrate =
        keyValue.getHeadUFDoubleValue(SaleOrderHVO.NDISCOUNTRATE);
    if (null == discountrate) {
      discountrate = SOConstant.ONEHUNDRED;
      keyValue.setHeadValue(SaleOrderHVO.NDISCOUNTRATE, discountrate);
    }

    // 开票客户填充
    String invoicecust =
        keyValue.getHeadStringValue(SaleOrderHVO.CINVOICECUSTID);
    if (PubAppTool.isNull(invoicecust)) {
      SOCustRelaDefValueRule custrelarule =
          new SOCustRelaDefValueRule(keyValue);
      custrelarule.setCustRelaInvoiceCust();
    }

    // 单据日期
    UFDate busdate = AppBsContext.getInstance().getBusiDate();
    keyValue.setHeadValue(SaleOrderHVO.DBILLDATE, busdate);

    UFDate enddate = busdate.asLocalEnd();
    int bodycount = keyValue.getBodyCount();
    for (int i = 0; i < bodycount; i++) {
      keyValue.setBodyValue(i, SaleOrderBVO.DBILLDATE, busdate);
      // 计划发货日期
      UFDate senddate = keyValue.getBodyUFDateValue(i, SaleOrderBVO.DSENDDATE);
      if (null == senddate || senddate.before(busdate)) {
        keyValue.setBodyValue(i, SaleOrderBVO.DSENDDATE, enddate);
      }

      // 要求到货日期
      UFDate receivedate =
          keyValue.getBodyUFDateValue(i, SaleOrderBVO.DRECEIVEDATE);
      if (null == receivedate || receivedate.before(busdate)) {
        keyValue.setBodyValue(i, SaleOrderBVO.DRECEIVEDATE, enddate);
      }

      // 整单折扣
      UFDouble disrate =
          keyValue.getBodyUFDoubleValue(i, SaleOrderBVO.NDISCOUNTRATE);
      if (null == disrate) {
        keyValue.setBodyValue(i, SaleOrderHVO.NDISCOUNTRATE, discountrate);
      }
      // 单品折扣
      UFDouble itemdisrate =
          keyValue.getBodyUFDoubleValue(i, SaleOrderBVO.NITEMDISCOUNTRATE);
      if (null == itemdisrate) {
        keyValue.setBodyValue(i, SaleOrderBVO.NITEMDISCOUNTRATE,
            SOConstant.ONEHUNDRED);
      }
      // 行状态
      keyValue.setBodyValue(i, SaleOrderBVO.FROWSTATUS,
          BillStatus.FREE.getIntegerValue());
    }
  }

}
