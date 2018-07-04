package nc.pubimpl.so.m33.so.m32;

import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;
import java.util.Set;

import nc.bs.arap.util.BillTermUtils;
import nc.bs.framework.common.NCLocator;
import nc.impl.pubapp.pattern.data.vo.VOQuery;
import nc.impl.pubapp.pattern.database.DataAccessUtils;
import nc.impl.pubapp.pattern.database.IDExQueryBuilder;
import nc.impl.pubapp.pattern.database.IDQueryBuilder;
import nc.itf.scmpub.reference.uap.group.SysInitGroupQuery;
import nc.pubimpl.so.m33.arap.ar.QueryAccountDateFromM33Action;
import nc.pubitf.arap.pub.IArapTermDateQueryService;
import nc.pubitf.ct.saledaily.so.IZ3QueryForOrder;
import nc.pubitf.so.m33.arap.AccountDateType;
import nc.vo.arap.termitem.ArapTermDateVO;
import nc.vo.bd.income.IncomeChVO;
import nc.vo.bd.payment.IPaymentUtil;
import nc.vo.ct.saledaily.entity.CtSalePayTermVO;
import nc.vo.pub.BusinessException;
import nc.vo.pub.lang.UFDate;
import nc.vo.pubapp.AppContext;
import nc.vo.pubapp.pattern.data.IRowSet;
import nc.vo.pubapp.pattern.pub.PubAppTool;
import nc.vo.scmpub.res.billtype.ICBillType;
import nc.vo.scmpub.res.billtype.SOBillType;
import nc.vo.so.m30.entity.SaleOrderBVO;
import nc.vo.so.m32.entity.SaleInvoiceBVO;
import nc.vo.so.m33.m32.entity.SquareInvDetailVO;
import nc.vo.so.m33.m4c.entity.SquareOutBVO;
import nc.vo.so.m33.m4c.entity.SquareOutDetailVO;
import nc.vo.so.pub.SOTable;
import nc.vo.so.pub.util.ListUtil;

/**
 * 销售结算传应收时点，将表体物料按照收款协议拆分并传给应收单
 * 
 * @author quyt
 * 
 */
public class ArapTermDateQueryServiceImpl implements IArapTermDateQueryService {

  @Override
  public Map<String, ArapTermDateVO[]> queryTermDataVOs(String top_billtype,
      Map<String, String> toppaymentMap) throws BusinessException {
    if (toppaymentMap == null || toppaymentMap.size() == 0
        || top_billtype == null) {
      return Collections.emptyMap();
    }
    if (!SOBillType.Invoice.getCode().equals(top_billtype)
        && !ICBillType.SaleOut.getCode().equals(top_billtype)) {
      return Collections.emptyMap();
    }
    // 销售待结算明细id
    Set<String> sqdetalid = new HashSet<String>();
    // 收款协议id
    Set<String> incomeId = new HashSet<String>();
    for (Entry<String, String> entry : toppaymentMap.entrySet()) {
      String key = entry.getKey();
      String value = entry.getValue();
      if (!PubAppTool.isNull(key)&& !PubAppTool.isNull(value)) {
        sqdetalid.add(key);
        incomeId.add(value);
      }
    }
    if (sqdetalid.size() == 0 || incomeId.size() == 0) {
      return Collections.emptyMap();
    }
    String[] sqdetalids = sqdetalid.toArray(new String[sqdetalid.size()]);

    Map<String, String> squarebidMap = new HashMap<String, String>();
    // 通过销售出库、发票待结算明细获取销售合同pk
    Map<String, String> ctsaleMap =
        getCtsaleMap(top_billtype, sqdetalids, squarebidMap);
    String[] cctmanageids = getcctmanageids(ctsaleMap);
    // 根据合同主键获取合同收款协议vo
    Map<String, CtSalePayTermVO[]> ctvoMap = null;
    if ((SysInitGroupQuery.isCTEnabled())) {
      ctvoMap =
          NCLocator.getInstance().lookup(IZ3QueryForOrder.class)
              .queryIsShowPayterm(cctmanageids);
    }
    // 1.收款协议来源于展开销售合同
    Map<String, ArapTermDateVO[]> ctdaremap = null;
    if (ctvoMap != null && ctvoMap.size() > 0) {
      Map<String, UFDate[]> retMap =
          getperiodDate(sqdetalids, squarebidMap, ctsaleMap, ctvoMap,
              top_billtype);
      // 如果调合同接口返回的map为不为空则表示该明细行来源是展开收款协议的，将使用retMap
      ctdaremap =
          getArapTermDateVOMap(squarebidMap, ctsaleMap, ctvoMap, retMap);
    }
    // 2.收款协议来源于收款协议档案定义
    Map<String, ArapTermDateVO[]> arapdatemap =
        new HashMap<String, ArapTermDateVO[]>();
    Map<String, UFDate[]> incomeMap = new HashMap<String, UFDate[]>();
    if (incomeId != null && incomeId.size() > 0) {
      incomeMap =
          getperiodDate(sqdetalid, incomeId, toppaymentMap, top_billtype);
      arapdatemap =
          getArapTermDateVOByIncomeVOMap(ctdaremap, incomeMap, incomeId,
              toppaymentMap);
    }

    // 3.合并来源展开销售合同和来源于基础数据的收款协议VO
    if (ctdaremap != null) {
      for (Map.Entry<String, ArapTermDateVO[]> map : ctdaremap.entrySet()) {
        if (map.getValue().length > 0) {
          arapdatemap.put(map.getKey(), map.getValue());
        }
      }
    }
    return arapdatemap;

  }

  @Override
  public boolean isSupportBilltype(String top_billtype) {
    if (SOBillType.Invoice.getCode().equals(top_billtype)
         ||ICBillType.SaleOut.getCode().equals(top_billtype)) {
      return true;
    }else{
      return false;
    }
  }

  /**
   * 判断传入的表体主键数组来确定返回的map
   * 
   * @param sqibidList
   *          销售发票表体数组
   * @param sqobidList
   *          销售出库单表体数组
   * @param ctsaleMap
   *          发票或出库单和合同主键的map
   * @return
   */
  private Map<String, String> getCtsaleMap(String top_billtype,
      String[] sqdetalids, Map<String, String> squarebidMap) {
    List<String> sqinvibidList = new ArrayList<String>();
    List<String> sqoutbidList = new ArrayList<String>();
    // 如果来源单据类型为销售发票
    if (SOBillType.Invoice.getCode().equals(top_billtype)) {
      VOQuery<SquareInvDetailVO> voQuery =
          new VOQuery<SquareInvDetailVO>(SquareInvDetailVO.class);
      SquareInvDetailVO[] sdVOs = voQuery.query(sqdetalids);
      for (SquareInvDetailVO sdVO : sdVOs) {
        sqinvibidList.add(sdVO.getCsquarebillbid());
        squarebidMap.put(sdVO.getCsalesquaredid(), sdVO.getCsquarebillbid());
      }
    }
    // 如果来源单据类型为销售出库单
    else if (top_billtype != null
        && ICBillType.SaleOut.getCode().equals(top_billtype)) {
      VOQuery<SquareOutDetailVO> voQuery =
          new VOQuery<SquareOutDetailVO>(SquareOutDetailVO.class);
      SquareOutDetailVO[] sdVOs = voQuery.query(sqdetalids);
      for (SquareOutDetailVO sdVO : sdVOs) {
        sqoutbidList.add(sdVO.getCsquarebillbid());
        squarebidMap.put(sdVO.getCsalesquaredid(), sdVO.getCsquarebillbid());
      }
    }
    Map<String, String> saleCtidMap = new HashMap<String, String>();
    if (!ListUtil.isEmpty(sqinvibidList)) {
      saleCtidMap = getPrimaryKeyBySaleInv(sqinvibidList);
    }
    else if (!ListUtil.isEmpty(sqoutbidList)) {
      saleCtidMap = getPrimaryKeyBySaleOut(sqoutbidList);
    }

    return saleCtidMap;
  }

  /**
   * 根据传入的单据收款协议主键，返回包含上有订单明细和起算日的map
   * 
   * @param keys 结算明细id
   * @param values 基础数据收款协议pk
   * @param toppaymentMap <结算明细id，基础数据收款协议pk>
   * @param top_billtype 来源单据类型
   * @return
   */
  private Map<String, UFDate[]> getperiodDate(Set<String> keys,
      Set<String> values, Map<String, String> toppaymentMap, String top_billtype) {
    Map<String, List<AccountDateType>> hashMap =
        new HashMap<String, List<AccountDateType>>();
    Map<String, UFDate[]> incomeMap = new HashMap<String, UFDate[]>();
    if (values != null && values.size() > 0) {
      Map<String, IncomeChVO[]> ctsaleMap = getIncomeBill(values);
      for (String key : keys) {
        String pk_payterm = toppaymentMap.get(key);
        // 获取起算日期主键
        IncomeChVO[] incomechVOs = ctsaleMap.get(pk_payterm);
        List<AccountDateType> list = new ArrayList<AccountDateType>();
        for (IncomeChVO incomechVO : incomechVOs) {
          String pk_incomeperiod = incomechVO.getPk_incomeperiod();
          list.add(getAccountDateType(pk_incomeperiod));
        }
        hashMap.put(key, list);
      }
      QueryAccountDateFromM33Action action =
          new QueryAccountDateFromM33Action();
      incomeMap = action.queryAccountDate(hashMap, top_billtype);
    }
    return incomeMap;
  }

  /**
   * 根据收款协议主键获取收款协议vo
   * 
   * @param pk_payterms
   * @return
   * 
   */
  private Map<String, IncomeChVO[]> getIncomeBill(Set<String> keys) {
    String[] pk_payterms = keys.toArray(new String[keys.size()]);
    StringBuilder whereSql = new StringBuilder();
    whereSql.append(" and ");
    IDExQueryBuilder iq = new IDExQueryBuilder(SOTable.TMP_SO_ID1.getName());
    whereSql.append(iq.buildSQL(IncomeChVO.PK_PAYMENT, pk_payterms));
    
    VOQuery<IncomeChVO> voQuery = new VOQuery<IncomeChVO>(IncomeChVO.class);
    IncomeChVO[] incomechVOs = voQuery.query(whereSql.toString(), null);
    
    Map<String, IncomeChVO[]> hashMap = new HashMap<String, IncomeChVO[]>();
    for(IncomeChVO incomechVO:incomechVOs){
      hashMap.put(incomechVO.getPk_payment(), incomechVOs);
    }
    return hashMap;
  }

  /**
   * 根据传入的收款协议vo和hashmap来返回财务需要的收款协议vo
   * 
   * @param csbidMap
   * @param ctsaleMap
   * @param ctvoMap
   * @param retMap
   * @return
   */
  private Map<String, ArapTermDateVO[]> getArapTermDateVOMap(
      Map<String, String> csbidMap, Map<String, String> ctsaleMap,
      Map<String, CtSalePayTermVO[]> ctvoMap, Map<String, UFDate[]> retMap) {
    Map<String, ArapTermDateVO[]> map = new HashMap<String, ArapTermDateVO[]>();
    if (retMap != null) {
      for (Map.Entry<String, UFDate[]> ret : retMap.entrySet()) {
        Set<ArapTermDateVO> arapTermDateVOs = new HashSet<ArapTermDateVO>();
        // 获取明细行pk
        String detialPrimaryKey = ret.getKey();
        // 获取明细行对应的说款协议上起算日数组
        UFDate[] paydates = ret.getValue();
        UFDate expiredate = new UFDate();
        int i = 0;
        for (UFDate paydate : paydates) {
          if (paydate == null) {
            // 根据明细行pk获取
            CtSalePayTermVO[] ctSalePayTermVOs =
                ctvoMap.get(ctsaleMap.get(csbidMap.get(detialPrimaryKey)));
            // 给每一个收款协议vo赋值，转成财务需要的vo
            // 判断起算日期类型
            // 如果到期日不是系统预置的
            // 各个收款期对应的实际到期日、计划到期日、单据生成的业务日期
            if (ctSalePayTermVOs[i].getDrealenddate() != null) {
              // 实际到期日
              expiredate = ctSalePayTermVOs[i].getDrealenddate();
            }
            else if (ctSalePayTermVOs[i].getDplaneffectdate() != null) {
              // 计划到期日
              expiredate = ctSalePayTermVOs[i].getDplanenddate();
            }
            else {
              // 单据生成业务日期(当前业务日期)
              expiredate = AppContext.getInstance().getBusiDate();
            }
            // 将到期日、起算日、收款协议vo赋值给ArapTermDateVO
            // 将销售合同vo转换为incomechvo（基础数据收款协议）
            ArapTermDateVO arapTermDateVO = new ArapTermDateVO();
            IncomeChVO incomevo = setATDVOToCSTVO(ctSalePayTermVOs[i]);
            arapTermDateVO.setPaydate(paydate);
            arapTermDateVO.setExpiredate(expiredate);
            arapTermDateVO.setIncomevo(incomevo);
            arapTermDateVOs.add(arapTermDateVO);
          }
          else if (paydates.length > 0) {
            CtSalePayTermVO[] ctSalePayTermVOs =
                ctvoMap.get(ctsaleMap.get(csbidMap.get(detialPrimaryKey)));
            // 给每一个收款协议vo赋值，转成财务需要的vo
            // 如果账期起算日为系统日期(起算日期不是系统预置的)，合同日期、出库日期、开票日期等，按如下优先规则取值：实际到期日、根据起算日计算的到期日
            ArapTermDateVO arapTermDateVO = new ArapTermDateVO();
            if (ctSalePayTermVOs[i].getDrealenddate() != null) {
              // 实际到期日
              expiredate = ctSalePayTermVOs[i].getDrealenddate();
              arapTermDateVO.setExpiredate(expiredate);
              // 将到期日、起算日、收款协议vo赋值给ArapTermDateVO
              // 将销售合同vo转换为incomechvo（基础数据收款协议）
              IncomeChVO incomevo = setATDVOToCSTVO(ctSalePayTermVOs[i]);
              arapTermDateVO.setPaydate(paydate);
              arapTermDateVO.setIncomevo(incomevo);
            }
            else {
              // 将到期日、起算日、收款协议vo赋值给ArapTermDateVO
              // 将销售合同vo转换为incomechvo（基础数据收款协议）
              IncomeChVO incomevo = setATDVOToCSTVO(ctSalePayTermVOs[i]);
              arapTermDateVO.setPaydate(paydate);
              arapTermDateVO.setIncomevo(incomevo);
              // 调财务接口，传过去含有起算日期的收款协议vo返回到期日
              // 根据起算日计算的到期日
              expiredate =
                  BillTermUtils.getExpiredateByTermDateVO(arapTermDateVO);
              arapTermDateVO.setExpiredate(expiredate);
            }
            arapTermDateVOs.add(arapTermDateVO);
          }
          i++;
        }
        map.put(detialPrimaryKey,
            arapTermDateVOs.toArray(new ArapTermDateVO[arapTermDateVOs.size()]));
      }
    }
    return map;
  }

  /**
   * 根据传入的收款协议vo和hashmap来返回财务需要的收款协议vo
   * 
   * @param ctdaremap 全部的<结算明细行id, 收款协议vo数组>
   * @param incomeMap <结算明细行id，起算日(具体日期)>
   * @param incomeId 基础数据收款协议pk
   * @param toppaymentMap
   * @return
   */
  private Map<String, ArapTermDateVO[]> getArapTermDateVOByIncomeVOMap(
      Map<String, ArapTermDateVO[]> ctdaremap, Map<String, UFDate[]> incomeMap,
      Set<String> incomeId, Map<String, String> toppaymentMap) {
    Map<String, ArapTermDateVO[]> newarapmap =
        new HashMap<String, ArapTermDateVO[]>();
    if (ctdaremap == null || ctdaremap.size() == 0) {
      return null;
    }
    // 根据收款协议PK获取基础数据收款协议vo
    Map<String, IncomeChVO[]> incomechmap = this.getIncomeBill(incomeId);

    for (Map.Entry<String, ArapTermDateVO[]> ctdare : ctdaremap.entrySet()) {
      // 结算明细行id
      String detailid = ctdare.getKey();
      // 获取明细行对应的收款协议上起算日数组
      UFDate[] paydates = incomeMap.get(detailid);
      // 获取基础数据收款协议vo数组
      IncomeChVO[] incomechVOs = incomechmap.get(toppaymentMap.get(detailid));
      Set<ArapTermDateVO> arapTermDateVOs = new HashSet<ArapTermDateVO>();
      int i = 0;
      for (UFDate paydate : paydates) {
        ArapTermDateVO arapTermDateVO = new ArapTermDateVO();
        // 由于是基础数据的收款协议，这里的到期日取得是当前业务日期
        UFDate expiredate = AppContext.getInstance().getBusiDate();
        if (paydate != null) {
          // 将到期日、起算日、收款协议vo赋值给ArapTermDateVO
          // 将销售合同vo转换为incomechvo（基础数据收款协议）
          arapTermDateVO.setPaydate(paydate);
          arapTermDateVO.setIncomevo(incomechVOs[i]);
          // 调财务接口，传过去含有起算日期的收款协议vo返回到期日
          expiredate = BillTermUtils.getExpiredateByTermDateVO(arapTermDateVO);
        }
        else {
          // 将到期日、起算日、收款协议vo赋值给ArapTermDateVO
          // 将销售合同vo转换为incomechvo（基础数据收款协议）
          arapTermDateVO.setPaydate(paydate);
          arapTermDateVO.setIncomevo(incomechVOs[i]);
        }
        arapTermDateVO.setExpiredate(expiredate);
        arapTermDateVOs.add(arapTermDateVO);
        i++;
      }
      newarapmap.put(detailid, arapTermDateVOs.toArray(new ArapTermDateVO[0]));
    }
    return newarapmap;
  }

  /**
   * 获取起算日
   * 
   * @param keys
   *          结算明细行id
   * @param squareMap
   *          存放明细实体和来源单据子实体的map
   * @param invAndOutMap
   *          存放发票,出库表体主键和合同主键
   * @param ctMap
   *          存放合同主键和收款协议vo
   * @param incomeperiodMap
   *          存放收款时点的name和pk
   * @param top_billtype
   *          上游单据类型
   * @return map
   */
  private Map<String, UFDate[]> getperiodDate(String[] keys,
      Map<String, String> squareMap, Map<String, String> invAndOutMap,
      Map<String, CtSalePayTermVO[]> ctMap, String top_billtype) {
    Map<String, List<AccountDateType>> hashMap =
        new HashMap<String, List<AccountDateType>>();
    for (String key : keys) {
      if (ctMap == null || ctMap.size() == 0) {
        continue;
      }
      if (invAndOutMap == null || invAndOutMap.size() == 0) {
        continue;
      }
      if (squareMap == null || squareMap.size() == 0) {
        continue;
      }
      // 根据明细行pk获取
      CtSalePayTermVO[] ctSalePayTermVOs =
          ctMap.get(invAndOutMap.get(squareMap.get(key)));

      List<AccountDateType> list = new ArrayList<AccountDateType>();
      if (ctSalePayTermVOs != null) {
        for (CtSalePayTermVO ctSalePayTermVO : ctSalePayTermVOs) {
          // 获取起算日期主键
          String pk_incomeperiod = ctSalePayTermVO.getPk_incomeperiod();
          list.add(getAccountDateType(pk_incomeperiod));
        }
      }
      hashMap.put(key, list);
    }
    QueryAccountDateFromM33Action action = new QueryAccountDateFromM33Action();
    Map<String, UFDate[]> ret = new HashMap<String, UFDate[]>();
    if (hashMap != null) {
      ret = action.queryAccountDate(hashMap, top_billtype);
    }
    return ret;
  }

  /**
   * 将获取到的收款期pk转化成枚举类型
   * 
   * @param pk_incomeperiod
   * @return
   */
  private AccountDateType getAccountDateType(String pk_incomeperiod) {
    AccountDateType datetype = null;
    // 出库日期
    if (IPaymentUtil.OUT_STORE_DATE.equals(pk_incomeperiod)) {
      datetype = AccountDateType.OUT_STORE_DATE;
    }
    // 出库签字日期
    if (IPaymentUtil.OUTSTORE_SIGNATURE_DATE.equals(pk_incomeperiod)) {
      datetype = AccountDateType.OUTSTORE_SIGNATURE_DATE;
    }
    // 销售合同生效日期
    if (IPaymentUtil.SALE_CONTRACT_EFFECTIVE_DATE.equals(pk_incomeperiod)) {
      datetype = AccountDateType.SALE_CONTRACT_EFFECTIVE_DATE;
    }
    // 销售发票审核日期
    if (IPaymentUtil.SALE_INVOICE_APPROVE_DATE.equals(pk_incomeperiod)) {
      datetype = AccountDateType.SALE_INVOICE_APPROVE_DATE;
    }
    // 销售开票日期
    if (IPaymentUtil.SALE_MAKE_BILL_DATE.equals(pk_incomeperiod)) {
      datetype = AccountDateType.SALE_MAKE_BILL_DATE;
    }
    // 销售订单日期
    if (IPaymentUtil.SALE_ORDER_DATE.equals(pk_incomeperiod)) {
      datetype = AccountDateType.SALE_ORDER_DATE;
    }
    return datetype;
  }

  /**
   * 将销售合同vo转换为incomevo（基础数据收款协议）
   * 
   * @param arapTermDateVO
   * @param cstVO
   *          合同收款协议vo
   */
  private IncomeChVO setATDVOToCSTVO(CtSalePayTermVO cstVO) {
    IncomeChVO ichVO = new IncomeChVO();
    ichVO.setAccrate(cstVO.getAccrate());
    ichVO.setCheckdata(cstVO.getCheckdata());
    ichVO.setEffectaddmonth(cstVO.getEffectaddmonth());
    ichVO.setEffectdateadddate(cstVO.getEffectdateadddate());
    ichVO.setEffectmonth(cstVO.getEffectmonth());
    ichVO.setIsdeposit(cstVO.getIsdeposit());
    ichVO.setPaymentday(cstVO.getPaymentday());
    ichVO.setPk_balatype(cstVO.getPk_balatype());
    ichVO.setPk_incomeperiod(cstVO.getPk_incomeperiod());
    ichVO.setPk_rate(cstVO.getPk_rate());
    ichVO.setPrepayment(cstVO.getPrepayment());
    ichVO.setPrimaryKey(cstVO.getPrimaryKey());
    ichVO.setShoworder(cstVO.getShoworder());
    ichVO.setStatus(cstVO.getStatus());
    return ichVO;
  }

  /**
   * 从map中取到的合同主键，存到String[]内
   * 
   * @param ctsaleMap
   * @return
   */
  private String[] getcctmanageids(Map<String, String> ctsaleMap) {
    Iterator<Entry<String, String>> it = ctsaleMap.entrySet().iterator();
    Set<String> cctmanageids = new HashSet<String>();
    // 取Map中合同主键pk存入数组内
    while (it.hasNext()) {
      String str = it.next().getValue();
      if (str != null) {
        cctmanageids.add(str);
      }
    }
    return cctmanageids.toArray(new String[cctmanageids.size()]);
  }

  /**
   * 根据销售发票查询来源来源合同主键
   * 
   * @param sqvos
   * @return
   */
  private Map<String, String> getPrimaryKeyBySaleInv(List<String> csbidList) {

    IDQueryBuilder builder = new IDQueryBuilder();
    String inSQL =
        builder.buildSQL(SaleInvoiceBVO.CSALEINVOICEBID,
            ListUtil.toArray(csbidList));
    String querySql =
        " select csaleinvoicebid, cctmanageid from so_saleinvoice_b where dr = 0 and "
            + inSQL;
    DataAccessUtils utils = new DataAccessUtils();
    IRowSet results = utils.query(querySql.toString());
    Map<String, String> hashMap = new HashMap<String, String>();
    for (String[] res : results.toTwoDimensionStringArray()) {
      hashMap.put(res[0], res[1]);
    }
    return hashMap;
  }

  /**
   * 根据销售出库查询来源合同主键
   * 
   * @param sqvos
   * @return
   */
  private Map<String, String> getPrimaryKeyBySaleOut(List<String> csbidList) {

    IDQueryBuilder builder = new IDQueryBuilder();
    String inSQL =
        builder.buildSQL(SquareOutBVO.CSQUAREBILLBID,
            ListUtil.toArray(csbidList));
    // 联查销售出库结算单和销售订单，在出库结算单中取源头单据子表，销售订单子表主键，查询出合同主键和销售出库单表体主键，并构建map
    String querySql =
        " select a.csquarebillbid, b.cctmanageid from so_squareout_b a left join so_saleorder_b b on a.cfirstbid = b.csaleorderbid where a.dr = 0 and b.dr = 0 and "
            + inSQL;
    DataAccessUtils utils = new DataAccessUtils();
    IRowSet results = utils.query(querySql.toString());
    Map<String, String> hashMap = new HashMap<String, String>();
    for (String[] res : results.toTwoDimensionStringArray()) {
      hashMap.put(res[0], res[1]);
    }
    return hashMap;
  }

}
