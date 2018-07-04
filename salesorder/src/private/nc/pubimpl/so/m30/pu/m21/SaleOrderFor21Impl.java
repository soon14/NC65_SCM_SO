package nc.pubimpl.so.m30.pu.m21;

import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;

import nc.bs.framework.common.NCLocator;
import nc.impl.pubapp.pattern.data.vo.VOQuery;
import nc.impl.pubapp.pattern.database.DataAccessUtils;
import nc.impl.pubapp.pattern.database.IDExQueryBuilder;
import nc.impl.so.m30.action.main.InsertSaleOrderAction;
import nc.itf.scmpub.reference.uap.pf.PfServiceScmUtil;
import nc.itf.so.m30.ref.scmpub.CoopVOChangeUtil;
import nc.itf.so.m30trantype.IM30TranTypeService;
import nc.pubitf.so.m30.pu.m21.ISaleOrderFor21;
import nc.vo.pu.m21.entity.OrderHeaderVO;
import nc.vo.pu.m21.entity.OrderItemVO;
import nc.vo.pu.m21.entity.OrderVO;
import nc.vo.pub.BusinessException;
import nc.vo.pub.lang.UFBoolean;
import nc.vo.pub.lang.UFDate;
import nc.vo.pub.lang.UFDouble;
import nc.vo.pubapp.pattern.data.IRowSet;
import nc.vo.pubapp.pattern.exception.ExceptionUtils;
import nc.vo.pubapp.pattern.pub.SqlBuilder;
import nc.vo.scmpub.res.billtype.POBillType;
import nc.vo.scmpub.res.billtype.SOBillType;
import nc.vo.so.m30.entity.SaleOrderBVO;
import nc.vo.so.m30.entity.SaleOrderHVO;
import nc.vo.so.m30.entity.SaleOrderVO;
import nc.vo.so.m30.pub.SaleOrderVOCalculator;
import nc.vo.so.m30.rule.FillNmffilePriceRule;
import nc.vo.so.pub.SOTable;
import nc.vo.so.pub.enumeration.BillStatus;
import nc.vo.so.pub.keyvalue.IKeyValue;
import nc.vo.so.pub.keyvalue.VOKeyValue;
import nc.vo.so.pub.rule.BodyValueRowRule;
import nc.vo.so.pub.rule.SOBuysellTriaRule;
import nc.vo.so.pub.rule.SOCalConditionRule;
import nc.vo.so.pub.rule.SOCountryInfoRule;
import nc.vo.so.pub.rule.SOCurrencyRule;
import nc.vo.so.pub.rule.SOExchangeRateRule;
import nc.vo.so.pub.rule.SOGlobalExchangeRate;
import nc.vo.so.pub.rule.SOGroupExchangeRate;
import nc.vo.so.pub.rule.SOTaxInfoRule;

public class SaleOrderFor21Impl implements ISaleOrderFor21 {

  @Override
  public Map<String, String> queryCoop21Bids(String[] bids)
      throws BusinessException {
    // Map<销售订单bid, 采购订单bid>
    Map<String, String> returnMap = new HashMap<String, String>();
    if (bids == null || bids.length == 0) {
      return returnMap;
    }
    Set<String> idSet = new HashSet<String>();
    for (int i = 0; i < bids.length; i++) {
      idSet.add(bids[i]);
    }

    // 查询订单BVO
    VOQuery<SaleOrderBVO> bodyQuery =
        new VOQuery<SaleOrderBVO>(SaleOrderBVO.class, new String[] {
          SaleOrderBVO.CSALEORDERID, SaleOrderBVO.CSALEORDERBID,
          SaleOrderBVO.VSRCTYPE, SaleOrderBVO.CSRCBID
        });
    SaleOrderBVO[] bodys =
        bodyQuery.query(idSet.toArray(new String[idSet.size()]));
    if (bodys != null && bodys.length > 0) {
      for (int i = 0; i < bodys.length; i++) {
        if (POBillType.Order.getCode().equals(bodys[i].getVsrctype())) {
          returnMap.put(bodys[i].getCsaleorderbid(), bodys[i].getCsrcbid());
        }
      }
    }
    return returnMap;
  }

  @Override
  public void push21To30(OrderVO[] srcBills) throws BusinessException {
    SaleOrderVO[] destBills =
        PfServiceScmUtil.executeVOChange(POBillType.Order.getCode(),
            SOBillType.Order.getCode(), srcBills);
    CoopVOChangeUtil coopUtil = new CoopVOChangeUtil();
    // 协同设置 && 客户档案默认值
    try {
      SaleOrderVO[] vos = coopUtil.processVO(srcBills, destBills);
      
      /*
       * 销售协同采购汇率问题 jilu for 633
       */
      Map<String, String> srcccurrencyidMap = new HashMap<>();
      this.fillSrcCurrencyidMap(srcccurrencyidMap,srcBills);
      for (SaleOrderVO billvo : vos) {
        IKeyValue keyValue = new VOKeyValue<SaleOrderVO>(billvo);
        // 发货库存组织、物流组织、财务组织填充
        BodyValueRowRule bodycouuitl = new BodyValueRowRule(keyValue);
        int[] finacerows =
            bodycouuitl.getValueNullRows(SaleOrderBVO.CSETTLEORGVID);
        // 组织本位币
        SOCurrencyRule currule = new SOCurrencyRule(keyValue);
        currule.setCurrency(finacerows);
        
        SaleOrderVOCalculator vocalcultor = new SaleOrderVOCalculator(billvo);
        // 折本汇率
        SOExchangeRateRule exrule = new SOExchangeRateRule(keyValue);
        // 获得需要计算折本汇率的标题行 by zhangby5
        int[] ratesRows = bodycouuitl.getCalRatesRows(srcccurrencyidMap);
        exrule.calcBodyExchangeRates(ratesRows);
        // 获得折本汇率之后 根据折本汇率进行单价金额等计算 by jilu
        vocalcultor.calculate(ratesRows, SaleOrderBVO.NEXCHANGERATE);

        // 集团、全局汇率计算
        SOGlobalExchangeRate globalraterule =
            new SOGlobalExchangeRate(keyValue);
        globalraterule.calcGlobalExchangeRate(finacerows);

        SOGroupExchangeRate groupraterule = new SOGroupExchangeRate(keyValue);
        groupraterule.calcGroupExchangeRate(finacerows);
        vocalcultor.calculate(finacerows, SaleOrderBVO.NPRICE);    
      }
      // end of add
      
      
      InsertSaleOrderAction action = new InsertSaleOrderAction();
      // 制单人为采购订单审批人
      for (OrderVO srcbill : srcBills) {
        String srcHid =
            (String) srcbill.getParentVO().getAttributeValue(
                OrderHeaderVO.PK_ORDER);
        String srcOperator =
            (String) srcbill.getParentVO().getAttributeValue(
                OrderHeaderVO.BILLMAKER);
        for (SaleOrderVO desbill : vos) {
          SaleOrderBVO[] bvos = desbill.getChildrenVO();
          for (SaleOrderBVO bvo : bvos) {
            if (srcHid.equals(bvo.getCsrcid())) {
              desbill.getParentVO().setBillmaker(srcOperator);
            }
          }
          IKeyValue keyValue = new VOKeyValue<SaleOrderVO>(desbill);
          BodyValueRowRule bodycouuitl = new BodyValueRowRule(keyValue);
          int[] rows = bodycouuitl.getMarNotNullRows();
          SOCountryInfoRule countryrule = new SOCountryInfoRule(keyValue);
          countryrule.setTaxCountry(rows);
          // 购销类型
          SOBuysellTriaRule buyflgrule = new SOBuysellTriaRule(keyValue);
          buyflgrule.setBuysellAndTriaFlag(rows);
          SaleOrderVOCalculator vocalcultor =
              new SaleOrderVOCalculator(desbill);
          int[] buychgrows = buyflgrule.getBuysellChgRow();
          vocalcultor
              .calculate(buychgrows, SOCalConditionRule.getCalPriceKey());
          // 询税
          SOTaxInfoRule taxInfo = new SOTaxInfoRule(keyValue);
          taxInfo.setTaxInfoByBodyPos(rows);
          int[] taxchgrows = taxInfo.getTaxChangeRows();
          vocalcultor.calculate(taxchgrows, SaleOrderBVO.NTAXRATE);
          // 设置特征价
          FillNmffilePriceRule nmffileRule = new FillNmffilePriceRule(keyValue);
          nmffileRule.setNmffilePrice();
        }
      }

      action.insert(vos);
    }
    catch (Exception e) {
      ExceptionUtils.marsh(e);
    }
  }

  private void fillSrcCurrencyidMap(Map<String, String> srcccurrencyidMap,
      OrderVO[] srcBills) {
    if(srcBills ==null || srcBills.length==0){
      return;
    }
    for(OrderVO bill : srcBills){
      OrderItemVO[] bvos = (OrderItemVO[]) bill.getChildrenVO();
      for(OrderItemVO bvo : bvos){
        String pk_order_b = bvo.getPk_order_b();
        srcccurrencyidMap.put(pk_order_b, bvo.getCcurrencyid());
      }
    }
  }

  @Override
  public Map<String, UFDouble> getSaleOrderNumber(String[] cmaterialid,
      UFDate queryDate, Integer queryDay, String pk_group, String pk_org) {
    String sql =
        this.getWhereSQL(cmaterialid, queryDate, queryDay, pk_group, pk_org);

    DataAccessUtils utils = new DataAccessUtils();
    IRowSet rowset = utils.query(sql);
    Map<String, UFDouble> map = new HashMap<String, UFDouble>();
    while (rowset.next()) {
      map.put(rowset.getString(1), rowset.getUFDouble(0));
    }
    return map;
  }

  private String getWhereSQL(String[] cmaterialid, UFDate queryDate,
      Integer queryDay, String pk_group, String pk_org) {
    UFDate startDate = queryDate.getDateBefore(queryDay.intValue());
    UFDate endDate = queryDate;
    SqlBuilder sql = new SqlBuilder();
    sql.append("select sum(isnull(so_saleorder_b.nnum,0)) as totalnum,"
        + "so_saleorder_b.cmaterialvid"
        + " from so_saleorder_b inner join so_saleorder on"
        + " so_saleorder.csaleorderid = so_saleorder_b.csaleorderid");
    sql.append(" where ");
    sql.append("so_saleorder.pk_org", pk_org);
    sql.append(" and ");
    sql.append("so_saleorder_b.pk_org", pk_org);
    sql.append(" and ");
    sql.append("so_saleorder.dbilldate", "<=", endDate.toString());
    sql.append(" and ");
    sql.append("so_saleorder.dbilldate", ">=", startDate.toString());
    sql.append(" and ");
    sql.append("so_saleorder_b.dbilldate", "<=", endDate.toString());
    sql.append(" and ");
    sql.append("so_saleorder_b.dbilldate", ">=", startDate.toString());
    sql.append(" and ");
    sql.startParentheses();
    sql.append("so_saleorder.fstatusflag", (Integer) BillStatus.AUDIT.value());
    sql.append(" or ");
    sql.append("so_saleorder.fstatusflag", (Integer) BillStatus.CLOSED.value());
    sql.endParentheses();
    sql.append(" and ");
    sql.append("so_saleorder.dr", 0);
    sql.append(" and ");
    sql.append("so_saleorder_b.dr", 0);
    sql.append(" and ");
    sql.append("so_saleorder.pk_group", pk_group);
    // 拼写INsql语句
    IDExQueryBuilder iq = new IDExQueryBuilder(SOTable.TMP_SO_ID1.getName());
    String insql = iq.buildSQL("so_saleorder_b.cmaterialid", cmaterialid);
    sql.append(" and ");
    sql.append(insql);
    sql.append(" group by so_saleorder_b.cmaterialvid");
    return sql.toString();
  }

  @Override
  public Map<String, UFBoolean> queryIsDirectPO(String[] ctrantypeids)
      throws BusinessException {
    // Map<交易类型code, 是否直运采购>
    Map<String, UFBoolean> returnMap = null;
    IM30TranTypeService service =
        NCLocator.getInstance().lookup(IM30TranTypeService.class);
    try {
      returnMap = service.queryIsDirectPO(ctrantypeids);
    }
    catch (BusinessException e) {
      ExceptionUtils.marsh(e);
    }
    return returnMap;
  }

  @Override
  public Map<String, UFBoolean> query30IsFromCoop(String[] ids)
      throws BusinessException {
    Set<String> idSet = new HashSet<String>();
    for (int i = 0; i < ids.length; i++) {
      idSet.add(ids[i]);
    }
    // Map<销售订单id, 是否采购订单协同>
    Map<String, UFBoolean> returnMap = new HashMap<String, UFBoolean>();
    // 查询订单BVO
    VOQuery<SaleOrderHVO> bodyQuery =
        new VOQuery<SaleOrderHVO>(SaleOrderHVO.class, new String[] {
          SaleOrderHVO.CSALEORDERID, SaleOrderHVO.BPOCOOPTOMEFLAG
        });
    SaleOrderHVO[] bodys =
        bodyQuery.query(idSet.toArray(new String[idSet.size()]));
    if (bodys != null && bodys.length > 0) {
      for (int i = 0; i < bodys.length; i++) {
        returnMap
            .put(bodys[i].getCsaleorderid(), bodys[i].getBpocooptomeflag());
      }
    }
    return returnMap;
  }
}
