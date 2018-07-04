package nc.pf.so.function;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;

import nc.bs.framework.common.InvocationInfoProxy;
import nc.bs.framework.common.NCLocator;
import nc.impl.pubapp.pattern.data.vo.VOQuery;
import nc.itf.scmpub.reference.uap.bd.customer.CustomerPubService;
import nc.itf.scmpub.reference.uap.rbac.UserManageQuery;
import nc.itf.so.m30trantype.IM30TranTypeService;
import nc.pf.so.function.para.ParaForCustMat;
import nc.pf.so.function.para.ParaForDeptMat;
import nc.pf.so.function.para.ParaForReturn;
import nc.pf.so.function.para.ParaForTranMat;
import nc.pubitf.so.custmatrel.CustMatRelParaVO;
import nc.pubitf.so.custmatrel.ICustMatRelFor30;
import nc.pubitf.so.deptmatrel.DeptMatRelParaVO;
import nc.pubitf.so.deptmatrel.IDeptMatRelFor30;
import nc.pubitf.so.m30.IReturnAssignMatch;
import nc.pubitf.so.m30.ReturnAssignMatchVO;
import nc.pubitf.so.tanmatrel.ITranMatRelFor30;
import nc.pubitf.so.tanmatrel.TranMatRelParaVO;
import nc.pubitf.uapbd.ICustomerPubService_C;
import nc.pubitf.uapbd.IMaterialPubService_C;
import nc.vo.bd.cust.saleinfo.CustsaleVO;
import nc.vo.bd.material.sale.MaterialSaleVO;
import nc.vo.pub.AggregatedValueObject;
import nc.vo.pub.BusinessException;
import nc.vo.pub.lang.UFBoolean;
import nc.vo.pub.lang.UFDate;
import nc.vo.pub.lang.UFDouble;
import nc.vo.pubapp.AppContext;
import nc.vo.pubapp.pattern.exception.ExceptionUtils;
import nc.vo.pubapp.pattern.pub.MathTool;
import nc.vo.scmpub.res.billtype.SOBillType;
import nc.vo.so.m30.entity.SaleOrderBVO;
import nc.vo.so.m30.entity.SaleOrderHVO;
import nc.vo.so.m30.entity.SaleOrderVO;
import nc.vo.so.m30trantype.entity.M30TranTypeVO;
import nc.vo.so.m30trantype.enumeration.SaleMode;
import nc.vo.so.pub.enumeration.BillStatus;
import nc.vo.so.pub.function.SOFunctionUtil;
import nc.vo.so.pub.util.SOSysParaInitUtil;
import nc.vo.so.pub.votools.SoVoTools;

/**
 * 销售订单函数
 * 
 * @since 6.0
 * @author 刘志伟
 * @time 2010-10-18 上午10:21:03
 */
public class SaleOrderFunction {

  private SaleOrderFuncTransferTool<SaleOrderVO> transferTool;

  /**
   * 检查客户与物料的关系
   * 
   * @param vo
   * @return
   * @throws BusinessException
   */
  public UFBoolean checkCustMatRel(AggregatedValueObject vo)
      throws BusinessException {
    try {
      SaleOrderVO bill = this.getFullBill(vo);
      ParaForCustMat paraforcustmat = new ParaForCustMat();
      CustMatRelParaVO[] paravos = paraforcustmat.getParavos(bill);
      ICustMatRelFor30 service =
          NCLocator.getInstance().lookup(ICustMatRelFor30.class);
      service.checkCustMatRel(paravos);
    }
    catch (Exception e) {
      ExceptionUtils.marsh(e);
    }
    return UFBoolean.TRUE;
  }

  /**
   * 检查部门与物料关系
   * 
   * @param vo
   * @return
   * @throws BusinessException
   */
  public UFBoolean checkDeptMatRel(AggregatedValueObject vo)
      throws BusinessException {
    try {
      SaleOrderVO bill = this.getFullBill(vo);
      ParaForDeptMat parafordeptmat = new ParaForDeptMat();
      DeptMatRelParaVO[] paravos = parafordeptmat.getParavos(bill);
      IDeptMatRelFor30 service =
          NCLocator.getInstance().lookup(IDeptMatRelFor30.class);
      service.checkDeptMatRel(paravos);
      return UFBoolean.TRUE;
    }
    catch (Exception e) {
      ExceptionUtils.marsh(e);
    }
    return UFBoolean.TRUE;
  }

  public UFBoolean checkOrgAndCust(AggregatedValueObject vo) {
    SaleOrderVO bill = this.getFullBill(vo);
    String pk_org = bill.getParentVO().getPk_org();
    String pk_customer = bill.getParentVO().getCcustomerid();
    CustsaleVO custsalevo =
        CustomerPubService.getCustSaleVOByPk(pk_customer, pk_org, new String[] {
            CustsaleVO.PK_CUSTOMER
        });
    if (custsalevo == null) {
      ExceptionUtils
      .wrappBusinessException(nc.vo.ml.NCLangRes4VoTransl.getNCLangRes()
          .getStrByID("4006011_0", "04006011-0206")/*@res "客户没有分配到该销售组织。"*/);
    }
    return UFBoolean.TRUE;
  }

  /**
   * 检查交易类型与物料关系
   * 
   * @param vo
   * @return
   * @throws BusinessException
   */
  public UFBoolean checkTranMatRel(AggregatedValueObject vo)
      throws BusinessException {
    try {
      SaleOrderVO bill = this.getFullBill(vo);
      ParaForTranMat parafortranmat = new ParaForTranMat();
      TranMatRelParaVO[] paravos = parafortranmat.getParavos(bill);
      ITranMatRelFor30 service =
          NCLocator.getInstance().lookup(ITranMatRelFor30.class);
      service.checkTranMatRel(paravos);
      return UFBoolean.TRUE;
    }
    catch (Exception e) {
      ExceptionUtils.marsh(e);
    }
    return UFBoolean.TRUE;

  }

  /**
   * 函数——订单现收款金额不能小于订单收款限额
   * <p>
   * <b>支持销售订单约束配置：</b>
   * <ul>
   * <li>1.动作前约束配置：审批(APPOVE)
   * </ul>
   * <p>
   * <b>配置方式,例如：</b>
   * <ul>
   * <li>1.订单现收款金额不能小于订单收款限额 = Y
   * </ul>
   * 
   * @param vo
   * @return UFBoolean
   * @author 刘志伟
   * @time 2010-10-27 下午04:38:02
   */
  public UFBoolean examOrigBalMnyNotLessThanPreceiveMny(AggregatedValueObject vo) {
    SaleOrderVO bill = this.getFullBill(vo);
    SaleOrderHVO head = bill.getParentVO();
    // 订单实际收款金额
    UFDouble receivedmny = head.getNreceivedmny();
    // 订单收款限额
    UFDouble preceivequota = head.getNpreceivequota();
    // 订单收款金额不能小于(>=)订单收款限额
    if (MathTool.compareTo(receivedmny, preceivequota) < 0) {
      return UFBoolean.FALSE;
    }
    return UFBoolean.TRUE;
  }

  /**
   * 检查主记账单价不能小于物料档案的最低售价*客户档案的物料最低售价比例”
   * 
   * @param vo 聚合VO
   * @return UFBoolean
   */
  public UFBoolean checkAccountPrice(AggregatedValueObject vo) {
    SaleOrderVO bill = this.getFullBill(vo);
    SaleOrderHVO head = bill.getParentVO();
    SaleOrderBVO[] salebvos = bill.getChildrenVO();
    Set<String> materidset = new HashSet<String>();
    for (SaleOrderBVO salebvo : salebvos) {
      materidset.add(salebvo.getCmaterialvid());
    }
    String[] materids = materidset.toArray(new String[0]);
    Map<String, UFDouble> materpricemap =
        this.getMinpriceMultiplyCustratio(materids, head.getCcustomerid(),
            head.getPk_org());
    for (SaleOrderBVO salebvo : salebvos) {
      UFDouble naccprice = salebvo.getNaccprice();
      UFDouble materprice = materpricemap.get(salebvo.getCmaterialvid());
      if (MathTool.compareTo(naccprice, materprice) < 0) {
        return UFBoolean.TRUE;
      }
    }
    return UFBoolean.FALSE;
  }

  private Map<String, UFDouble> getMinpriceMultiplyCustratio(String[] materids,
      String custid, String pk_org) {
    Map<String, UFDouble> retmap = new HashMap<String, UFDouble>();
    IMaterialPubService_C materservice =
        NCLocator.getInstance().lookup(IMaterialPubService_C.class);
    Map<String, MaterialSaleVO> matervomap = null;
    CustsaleVO[] custvos = null;
    try {
      matervomap =
          materservice.queryMaterialSaleInfoByPks(materids, pk_org,
              new String[] {
              MaterialSaleVO.MINPRICE
          });
      ICustomerPubService_C custService =
          NCLocator.getInstance().lookup(ICustomerPubService_C.class);
      custvos = custService.getCustSaleVO(new String[] {
          custid
      }, pk_org, new String[] {
          CustsaleVO.STOCKPRICERATIO
      });
    }
    catch (BusinessException ex) {
      ExceptionUtils.wrappBusinessException(ex.toString());
    }
    if (matervomap == null) {
      return retmap;
    }
    for (String materid : matervomap.keySet()) {
      MaterialSaleVO msalevo = matervomap.get(materid);
      UFDouble minprice = msalevo.getMinprice();
      if (null != custvos && null != custvos[0]) {
        Integer stockpriceratio = custvos[0].getStockpriceratio();
        MathTool.nvl(minprice).multiply(stockpriceratio.intValue() / 100);
        retmap.put(materid, minprice);
      }
      else {
        retmap.put(materid, minprice);
      }
    }
    return retmap;
  }

  /**
   * 函数——操作员对应业务员与客户专管业务员是否为同一人
   * <p>
   * <b>支持销售订单约束配置：</b>
   * <ul>
   * <li>1.动作前约束配置：写入(STORE)——新增
   * <li>2.动作前约束配置：写入(STORE)——修改
   * </ul>
   * <p>
   * <b>配置方式,例如：</b>
   * <ul>
   * <li>1.操作员对应业务员与客户专管业务员是否为同一人 = Y
   * </ul>
   * 
   * @param vo
   * @return UFBoolean
   * @author 刘志伟
   * @time 2010-10-23 下午12:49:41
   */
  public UFBoolean examRespPerson(AggregatedValueObject vo) {
    SaleOrderVO bill = this.getFullBill(vo);
    SaleOrderHVO head = bill.getParentVO();
    String operator = InvocationInfoProxy.getInstance().getUserId();
    String psndoc = UserManageQuery.queryPsndocByUserid(operator);
    if (psndoc != null) {
      CustsaleVO custsaleVO =
          CustomerPubService.getCustSaleVOByPk(head.getCcustomerid(),
              head.getPk_org(), new String[] {
            CustsaleVO.RESPPERSON
          });
      if (custsaleVO != null && custsaleVO.getRespperson() != null
          && psndoc.trim().equals(custsaleVO.getRespperson().trim())) {
        return UFBoolean.TRUE;
      }
    }
    return UFBoolean.FALSE;
  }

  /**
   * 函数——退货含税(无税)净价不能大于正向销售的含税(无税)净价
   * 2012-07-22和陈恩宇沟通：比对单据无意义，应该根据 含税\无税优先参数比对含税净价或无税净价
   * <p>
   * <b>支持销售订单约束配置：</b>
   * <ul>
   * <li>1.动作前约束配置：写入(STORE)——新增
   * <li>2.动作前约束配置：写入(STORE)——修改
   * </ul>
   * <p>
   * <b>配置方式,例如：</b>
   * <ul>
   * <li>1.退货含税(无税)净价不能大于正向销售的含税(无税)净价 = Y
   * </ul>
   * 
   * @param vo
   * @return UFBoolean
   * @author 刘志伟
   * @time 2010-10-23 下午02:37:57
   */
  public UFBoolean examReturnPriceOverOrder(AggregatedValueObject vo) {
    SaleOrderVO bill = this.getFullBill(vo);
    SaleOrderHVO head = bill.getParentVO();
    UFBoolean so23 =
        SOSysParaInitUtil.getSO23(AppContext.getInstance().getPkGroup());

    // --1.检查销售模式为退货、退换货、普通+退货、普通+退换货的订单
    M30TranTypeVO trantypeVO = null;
    try {
      IM30TranTypeService service =
          NCLocator.getInstance().lookup(IM30TranTypeService.class);
      trantypeVO = service.queryTranTypeVO(head.getCtrantypeid());
    }
    catch (Exception e) {
      ExceptionUtils.wrappBusinessException(e.getMessage());
    }
    if (trantypeVO != null) {
      if (SaleMode.MODE_COMMON.equalsValue(trantypeVO.getFsalemode())) {
        return UFBoolean.TRUE;
      }
    }
    // --2.查询源头订单表体ID
    String[] strBodyIDs = this.getFirstbid(bill);
    // --3.查询销售订单price(含税净价)
    Map<String, UFDouble> hmOrderPrice =
        this.getOrderPriceByID(strBodyIDs, so23);
    // --4.检查比较
    if (hmOrderPrice != null && hmOrderPrice.size() > 0) {
      SaleOrderBVO[] bodys = bill.getChildrenVO();
      for (SaleOrderBVO body : bodys) {
        UFDouble orderPrice = hmOrderPrice.get(body.getCfirstbid());
        UFDouble returnPrice =
            so23.booleanValue() ? body.getNqtorigtaxnetprc() : body
                .getNqtorignetprice();
            if (returnPrice != null
                && MathTool.compareTo(returnPrice, orderPrice) > 0) {
              return UFBoolean.FALSE;
            }
      }
    }
    return UFBoolean.TRUE;
  }

  /**
   * 函数——销售净价不能小于物料档案的最低售价*客户档案的物料最低售价比例:
   * 逐行检查、任意行不满足即返回false
   * <p>
   * <b>支持销售订单约束配置：</b>
   * <ul>
   * <li>1.动作前约束配置：写入(STORE)——新增
   * <li>2.动作前约束配置：写入(STORE)——修改
   * </ul>
   * <p>
   * <b>配置方式,例如：</b>
   * <ul>
   * <li>1.销售净价不能小于物料档案的最低售价*客户档案的物料最低售价比例 = Y
   * </ul>
   * 
   * @param vo
   * @return UFBoolean
   * @author 刘志伟
   * @time 2010-10-20 上午09:23:56
   */
  public UFBoolean examSaleNetprice(AggregatedValueObject vo) {
    SaleOrderVO bill = this.getFullBill(vo);

    SOFunctionUtil util = new SOFunctionUtil();
    return util.examSaleNetprice(bill);
  }

  /**
   * 函数——销售订单表头合计金额（原币含税）：支持原退货申请单的单据函数
   * <p>
   * <b>支持销售订单约束配置：</b>
   * <ul>
   * <li>1.动作前约束配置：写入(STORE)——新增
   * <li>2.动作前约束配置：写入(STORE)——修改
   * </ul>
   * <p>
   * <b>配置方式,例如：</b>
   * <ul>
   * <li>1.销售订单表头合计金额（原币含税） < 100
   * <li>2.销售订单表头合计金额（原币含税） >= 100
   * </ul>
   * 
   * @param vo
   * @return UFDouble
   * @author 刘志伟
   * @time 2010-10-18 上午10:21:03
   */
  public UFDouble getHeadSumMny(AggregatedValueObject vo) {
    SaleOrderVO bill = this.getFullBill(vo);
    UFDouble ntotalorigmny = bill.getParentVO().getNtotalorigmny();
    if (ntotalorigmny == null) {
      return UFDouble.ZERO_DBL;
    }
    return ntotalorigmny;
  }

  /**
   * 函数——订单表体行发货日期与订单日期的最大差值
   * <p>
   * <b>支持销售订单约束配置：</b>
   * <ul>
   * <li>1.动作前约束配置：写入(STORE)——新增
   * <li>2.动作前约束配置：写入(STORE)——修改
   * </ul>
   * <p>
   * <b>配置方式,例如：</b>
   * <ul>
   * <li>1.订单表体行发货日期与订单日期的最大差值 < 100
   * <li>2.订单表体行发货日期与订单日期的最大差值 >= 100
   * </ul>
   * 
   * @param vo
   * @return UFDouble
   * @author 刘志伟
   * @time 2010-10-23 下午02:37:57
   */
  public int getMaxDate(AggregatedValueObject vo) {
    SaleOrderVO bill = this.getFullBill(vo);
    UFDate billDate = bill.getParentVO().getDbilldate();
    SaleOrderBVO[] bodys = bill.getChildrenVO();
    int ret = 0;
    for (SaleOrderBVO body : bodys) {
      int date =
          UFDate.getDaysBetween(new UFDate(billDate.toStdString()), new UFDate(
              body.getDsenddate().toStdString()));
      if (date > ret) {
        ret = date;
      }
    }
    return ret;
  }

  /**
   * 函数——表体行整单折扣*单品折扣的最大值
   * <p>
   * <b>支持销售订单约束配置：</b>
   * <ul>
   * <li>1.动作前约束配置：写入(STORE)——新增
   * <li>2.动作前约束配置：写入(STORE)——修改
   * </ul>
   * <p>
   * <b>配置方式,例如：</b>
   * <ul>
   * <li>1.表体行整单折扣*单品折扣的最大值 < 0.5
   * <li>2.表体行整单折扣*单品折扣的最大值 >= 0.5
   * </ul>
   * 
   * @param vo
   * @return UFDouble
   * @author 刘志伟
   * @time 2010-10-23 下午12:49:41
   */
  public UFDouble getMaxDiscountRate(AggregatedValueObject vo) {
    SaleOrderVO bill = this.getFullBill(vo);
    SOFunctionUtil util = new SOFunctionUtil();
    UFDouble maxDiscountRate = util.getMaxDiscountRate(bill);
    return maxDiscountRate;
  }

  /**
   * 函数——订单表体行的净价/询到净价的最大值
   * <p>此函数主要为了询价后改价：控制改价的范围
   * <p>
   * <b>支持销售订单约束配置：</b>
   * <ul>
   * <li>1.动作前约束配置：写入(STORE)——新增
   * <li>2.动作前约束配置：写入(STORE)——修改
   * </ul>
   * <p>
   * <b>配置方式,例如：</b>
   * <ul>
   * <li>1.订单表体行的净价/询到净价的最大值 < 100
   * <li>2.订单表体行的净价/询到净价的最大值 >= 100
   * </ul>
   * 
   * @param vo
   * @return
   * @author 刘志伟
   * @time 2010-10-19 下午01:45:01
   */
  public UFDouble getMaxPriceRate(AggregatedValueObject vo) {
    SaleOrderVO bill = this.getFullBill(vo);
    SOFunctionUtil util = new SOFunctionUtil();
    UFDouble dMaxPriceRate = util.getMaxPriceRate(bill);
    return dMaxPriceRate;
  }

  /**
   * 函数——订单表体行发货日期与订单日期的最小差值
   * <p>
   * <b>支持销售订单约束配置：</b>
   * <ul>
   * <li>1.动作前约束配置：写入(STORE)——新增
   * <li>2.动作前约束配置：写入(STORE)——修改
   * </ul>
   * <p>
   * <b>配置方式,例如：</b>
   * <ul>
   * <li>1.订单表体行发货日期与订单日期的最小差值 < 100
   * <li>2.订单表体行发货日期与订单日期的最小差值 >= 100
   * </ul>
   * 
   * @param vo
   * @return UFDouble
   * @author 刘志伟
   * @time 2010-10-23 下午02:37:57
   */
  public int getMinDate(AggregatedValueObject vo) {
    SaleOrderVO bill = this.getFullBill(vo);
    UFDate billDate = bill.getParentVO().getDbilldate();
    SaleOrderBVO[] bodys = bill.getChildrenVO();
    int ret = 1000000;
    for (SaleOrderBVO body : bodys) {
      int date =
          UFDate.getDaysBetween(new UFDate(billDate.toStdString()), new UFDate(
              body.getDsenddate().toStdString()));
      if (date < ret) {
        ret = date;
      }
    }
    return ret;
  }

  /**
   * 函数——表体行整单折扣*单品折扣的最小值
   * <p>
   * <b>支持销售订单约束配置：</b>
   * <ul>
   * <li>1.动作前约束配置：写入(STORE)——新增
   * <li>2.动作前约束配置：写入(STORE)——修改
   * </ul>
   * <p>
   * <b>配置方式,例如：</b>
   * <ul>
   * <li>1.表体行整单折扣*单品折扣的最小值 < 0.5
   * <li>2.表体行整单折扣*单品折扣的最小值 >= 0.5
   * </ul>
   * 
   * @param vo
   * @return UFDouble
   * @author 刘志伟
   * @time 2010-10-23 下午12:49:41
   */
  public UFDouble getMinDiscountRate(AggregatedValueObject vo) {
    SaleOrderVO bill = this.getFullBill(vo);
    SOFunctionUtil util = new SOFunctionUtil();
    UFDouble minDiscountRate = util.getMinDiscountRate(bill);
    return minDiscountRate;
  }

  /**
   * 函数——订单表体行的净价/询到净价的最小值
   * <p>
   * <b>支持销售订单约束配置：</b>
   * <ul>
   * <li>1.动作前约束配置：写入(STORE)——新增
   * <li>2.动作前约束配置：写入(STORE)——修改
   * </ul>
   * <p>
   * <b>配置方式,例如：</b>
   * <ul>
   * <li>1.订单表体行的净价/询到净价的最小值 < 100
   * <li>2.订单表体行的净价/询到净价的最小值 >= 100
   * </ul>
   * 
   * @param vo
   * @return UFDouble
   * @author 刘志伟
   * @time 2010-10-19 下午01:45:01
   */
  public UFDouble getMinPriceRate(AggregatedValueObject vo) {
    SaleOrderVO bill = this.getFullBill(vo);
    SOFunctionUtil util = new SOFunctionUtil();
    UFDouble dMinPriceRate = util.getMinPriceRate(bill);
    return dMinPriceRate;
  }

  /**
   * 函数——订单表体行未出库合计主数量
   * <p>
   * <b>支持销售订单约束配置：</b>
   * <ul>
   * <li>1.动作前约束配置：写入(STORE)——新增
   * <li>2.动作前约束配置：写入(STORE)——修改
   * <li>3.动作前约束配置：修订()
   * </ul>
   * <p>
   * <b>配置方式,例如：</b>
   * <ul>
   * <li>1.订单表体行未出库合计主数量 > 100
   * <li>2.订单表体行未出库合计主数量 <= 100
   * </ul>
   * 
   * @param vo
   * @return UFDouble
   * @author 刘志伟
   * @time 2010-10-20 上午09:23:56
   */
  public UFDouble getNotNtotaloutnum(AggregatedValueObject vo) {
    return this.getNotExecuteTotalNnumByKey(vo, SaleOrderBVO.NTOTALOUTNUM);
  }

  /**
   * 函数——订单表体行未发货合计主数量
   * <p>
   * <b>支持销售订单约束配置：</b>
   * <ul>
   * <li>1.动作前约束配置：写入(STORE)——新增
   * <li>2.动作前约束配置：写入(STORE)——修改
   * <li>3.动作前约束配置：修订()
   * </ul>
   * <p>
   * <b>配置方式,例如：</b>
   * <ul>
   * <li>1.订单表体行未发货合计主数量 > 100
   * <li>2.订单表体行未发货合计主数量 <= 100
   * <li>3...
   * </ul>
   * 
   * <p>
   * <b>参数说明</b>
   * 
   * @param vo
   * @return UFDouble
   *         <p>
   * @author 刘志伟
   * @time 2010-10-20 上午09:23:56
   */
  public UFDouble getNotNtotalsendnum(AggregatedValueObject vo) {
    return this.getNotExecuteTotalNnumByKey(vo, SaleOrderBVO.NTOTALSENDNUM);
  }

  /**
   * 函数——订单收现款金额(销售订单表头)
   * <p>
   * <b>支持销售订单约束配置：</b>
   * <ul>
   * <li>1.动作前约束配置：写入(STORE)——新增
   * <li>2.动作前约束配置：写入(STORE)——修改
   * <li>3.动作前约束配置：审批(APPOVE)
   * </ul>
   * <p>
   * <b>配置方式,例如：</b>
   * <ul>
   * <li>1.订单收现款金额 > 100
   * </ul>
   * 
   * @param vo
   * @return UFDouble
   * @author 刘志伟
   * @time 2010-10-27 下午04:38:02
   */
  public UFDouble getOrigBalMny(AggregatedValueObject vo) {
    SaleOrderVO bill = (SaleOrderVO) vo;
    UFDouble nreceivedmny = bill.getParentVO().getNreceivedmny();
    if (nreceivedmny != null) {
      return bill.getParentVO().getNreceivedmny();
    }
    return UFDouble.ZERO_DBL;
  }

  /**
   * 销售退货政策检查
   * 
   * @param vo
   * @return
   * @throws BusinessException
   */
  public UFBoolean judge(AggregatedValueObject vo) throws BusinessException {
    try {
      SaleOrderVO bill = this.getFullBill(vo);
      ParaForReturn paraforreturn = new ParaForReturn();
      ReturnAssignMatchVO[] paravos = paraforreturn.getParavos(bill);
      IReturnAssignMatch service =
          NCLocator.getInstance().lookup(IReturnAssignMatch.class);
      Map<String, String> map = service.matchReturnAssign(paravos);
      if (map.size() > 0) {
        // // SaleOrderVO oldbill = (SaleOrderVO) vo;
        // SaleOrderBVO[] bvos = bill.getChildrenVO();
        // for (SaleOrderBVO bvo : bvos) {
        // int state = bvo.getStatus();
        // String row = bvo.getCrowno();
        // if (state == VOStatus.NEW) {
        // bvo.setCretpolicyid(map.get(row));
        // bvo.setStatus(VOStatus.NEW);
        // continue;
        // }
        // bvo.setCretpolicyid(map.get(row));
        // bvo.setStatus(VOStatus.UPDATED);
        // }
        // VOUpdate<SaleOrderBVO> bo = new VOUpdate<SaleOrderBVO>();
        // String[] names = new String[] {
        // SaleOrderBVO.CRETPOLICYID
        // };
        // bvos= bo.update(bvos, names);
      }

      return UFBoolean.TRUE;
    }
    catch (Exception e) {
      ExceptionUtils.marsh(e);
    }
    return UFBoolean.TRUE;
  }

  private SaleOrderVO getClientInfoFullBill(SaleOrderVO bill) {
    if (this.transferTool == null) {
      SaleOrderVO[] bills = new SaleOrderVO[] {
          bill
      };
      this.transferTool =
          new SaleOrderFuncTransferTool<SaleOrderVO>(bills, UFBoolean.TRUE);
    }
    return this.transferTool.getClientFullInfoBill()[0];
  }

  /**
   * 获得源头销售订单表体ID
   * 
   * @param vo AggregatedValueObject
   * @return SaleOrderVO
   * @author 刘志伟
   * @time 2010-10-25 上午09:55:15
   */
  private String[] getFirstbid(SaleOrderVO bill) {
    List<String> alFirstbids = null;
    SaleOrderBVO[] bodys = bill.getChildrenVO();
    for (SaleOrderBVO body : bodys) {
      if (SOBillType.Order.getCode().equals(body.getVfirsttype())
          && body.getCfirstbid() != null
          && body.getCfirstbid().trim().length() > 0) {
        if (alFirstbids == null) {
          alFirstbids = new ArrayList<String>();
        }
        alFirstbids.add(body.getCfirstbid());
      }
    }
    if (alFirstbids != null && alFirstbids.size() > 0) {
      return alFirstbids.toArray(new String[0]);
    }
    return null;
  }

  /**
   * 根据新增或修改获得补全VO
   * 
   * @param vo AggregatedValueObject
   * @return SaleOrderVO
   * @author 刘志伟
   * @time 2010-10-20 上午09:23:56
   */
  private SaleOrderVO getFullBill(AggregatedValueObject vo) {
    // 新增
    SaleOrderVO bill = (SaleOrderVO) vo;
    // 修改
    String strOrderID = bill.getParentVO().getCsaleorderid();
    if (strOrderID != null && strOrderID.trim().length() > 0) {
      bill = this.getClientInfoFullBill(bill);
    }
    return bill;
  }

  /**
   * 获得表体行未执行key合计主数量：合计主数量 - 合计key数量
   * 
   * @param vo AggregatedValueObject
   * @param key 累计执行*数量
   * @return UFDouble
   * @author 刘志伟
   * @time 2010-10-20 上午09:23:56
   */
  private UFDouble getNotExecuteTotalNnumByKey(AggregatedValueObject vo,
      String key) {
    UFDouble retTotalnum = UFDouble.ZERO_DBL;
    SaleOrderVO bill = (SaleOrderVO) vo;
    // 补全VO
    SaleOrderVO clientBill = this.getClientInfoFullBill(bill);
    // 修订
    if (BillStatus.AUDIT.equalsValue(clientBill.getParentVO().getFstatusflag())) {
      retTotalnum =
          SoVoTools.getTotalMnySub(clientBill.getChildrenVO(),
              SaleOrderBVO.NNUM, clientBill.getChildrenVO(), key);
    }
    return retTotalnum;
  }

  /**
   * 根据表体IDs获得源头销售订单表体含税净价(原币含税净价)
   * 
   * @param vo AggregatedValueObject
   * @return SaleOrderVO
   * @author 刘志伟
   * @param so23
   * @time 2010-10-25 上午09:55:15
   */
  private Map<String, UFDouble> getOrderPriceByID(String[] strBodyIDs,
      UFBoolean so23) {
    Map<String, UFDouble> retMap = null;
    if (strBodyIDs == null) {
      return retMap;
    }
    VOQuery<SaleOrderBVO> voQuery =
        new VOQuery<SaleOrderBVO>(SaleOrderBVO.class, new String[] {
				SaleOrderBVO.CSALEORDERBID, SaleOrderBVO.NQTORIGTAXNETPRC, 
					SaleOrderBVO.NQTORIGNETPRICE});
    SaleOrderBVO[] bodys = voQuery.query(strBodyIDs);
    if (bodys != null && bodys.length > 0) {
      for (SaleOrderBVO body : bodys) {
        if (retMap == null) {
          retMap = new HashMap<String, UFDouble>();
        }
        retMap.put(
            body.getCsaleorderbid(),
            so23.booleanValue() ? body.getNqtorigtaxnetprc() : body
                .getNqtorignetprice());
      }
    }
    return retMap;
  }
}
