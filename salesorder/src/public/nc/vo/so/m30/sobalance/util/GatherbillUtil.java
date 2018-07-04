package nc.vo.so.m30.sobalance.util;

import java.util.HashSet;
import java.util.Set;

import nc.vo.arap.gathering.AggGatheringBillVO;
import nc.vo.arap.gathering.GatheringBillItemVO;
import nc.vo.arap.gathering.GatheringBillVO;
import nc.vo.ml.NCLangRes4VoTransl;
import nc.vo.pub.BusinessException;
import nc.vo.pub.lang.UFBoolean;
import nc.vo.pub.lang.UFDouble;
import nc.vo.pubapp.pattern.exception.ExceptionUtils;
import nc.vo.pubapp.pattern.pub.MathTool;
import nc.vo.so.m30.entity.SaleOrderBVO;
import nc.vo.so.m30.entity.SaleOrderHVO;
import nc.vo.so.m30.entity.SaleOrderVO;
import nc.vo.so.m30.pub.SaleOrderVOCalculator;
import nc.vo.so.m30.sobalance.entity.SoBalanceBVO;
import nc.vo.so.m30.sobalance.entity.SoBalanceHVO;
import nc.vo.so.m30.sobalance.entity.SoBalanceVO;
import nc.vo.so.m30.sobalance.entity.SoBalanceViewVO;
import nc.vo.so.m30.sobalance.enumeration.SoBalanceType;
import nc.vo.so.m30trantype.entity.M30TranTypeVO;
import nc.vo.so.pub.SOTable;
import nc.vo.so.pub.enumeration.BillStatus;
import nc.vo.so.pub.keyvalue.IKeyValue;
import nc.vo.so.pub.keyvalue.VOKeyValue;
import nc.vo.so.pub.rule.SOExchangeRateRule;
import nc.vo.so.pub.util.SOSysParaInitUtil;

import nc.itf.so.m30trantype.IM30TranTypeService;

import nc.bs.framework.common.NCLocator;
import nc.bs.ml.NCLangResOnserver;

import nc.impl.pubapp.pattern.database.IDExQueryBuilder;

/**
 * 
 * @author gdsjw
 */
public class GatherbillUtil {

  private static GatherbillUtil instance = new GatherbillUtil();

  private Set<String> paraSet;

  private GatherbillUtil() {
    // 私有构造子
  }

  public static GatherbillUtil getInstance() {
    return GatherbillUtil.instance;
  }

  public void checkBeforeGathering(SaleOrderVO ordvo) throws BusinessException {
    // 关闭不能收款
    SaleOrderHVO head = ordvo.getParentVO();
    Integer fstatus = head.getFstatusflag();
    if (fstatus != null
        && fstatus.intValue() == BillStatus.CLOSED.getIntValue()) {
      throw new BusinessException(nc.vo.ml.NCLangRes4VoTransl.getNCLangRes()
          .getStrByID("4006011_0", "04006011-0228")/* @res "订单已关闭，不允许进行订单收款。" */);
    }

    SaleOrderBVO[] bodyVOs = ordvo.getChildrenVO();
    if (bodyVOs.length <= 0) {
      return;
    }
    // 表体多个不同的应收组织不能收款
    String carorg = null;
    for (SaleOrderBVO bodyvo : bodyVOs) {
      // 去空行
      if (bodyvo.getCmaterialid() == null) {
        continue;
      }
      // 记录第一行财务组织
      if (carorg == null) {
        carorg = bodyvo.getCarorgid();
      }
      // 对比
      else if (!carorg.equals(bodyvo.getCarorgid())) {
        throw new BusinessException(nc.vo.ml.NCLangRes4VoTransl.getNCLangRes()
            .getStrByID("4006011_0", "04006011-0107")/* @res
                                                      * "销售订单表体存在多个应收组织，不允许订单收款。" */);
      }
    }

    // 红字不能收款
    UFDouble ntotalorigmny = head.getNtotalorigmny();
    if (MathTool.compareTo(ntotalorigmny, UFDouble.ZERO_DBL) < 0) {
      String pk_group = head.getPk_group();
      String vtrantypecode = head.getVtrantypecode();
      M30TranTypeVO trantype = null;
      IM30TranTypeService service =
          NCLocator.getInstance().lookup(IM30TranTypeService.class);
      trantype = service.queryTranType(pk_group, vtrantypecode);
      UFBoolean bredorderpay = trantype.getBredorderpay();
      if (bredorderpay == null || !bredorderpay.booleanValue()) {
        throw new BusinessException(nc.vo.ml.NCLangRes4VoTransl.getNCLangRes()
            .getStrByID("4006011_0", "04006011-0229")/* @res
                                                      * "销售订单不支持红字收款，不允许进行订单收款。" */);
      }
    }

    this.checkCanGathering(new SaleOrderHVO[] {
      head
    });
  }

  public void checkBeforeGathering(SaleOrderVO ordvo, UFDouble thisGatheringMny)
      throws BusinessException {
    // 符号不同不能收款
    UFDouble ntotalorigmny = ordvo.getParentVO().getNtotalorigmny();
    if (MathTool.isDiffSign(ntotalorigmny, thisGatheringMny)) {
      throw new BusinessException(NCLangRes4VoTransl.getNCLangRes().getStrByID(
          "4006011_0", "04006011-0424")/* @res "销售订单价税合计与收款单金额算术符号不一致！" */);
    }
    // 检查其他的
    this.checkBeforeGathering(ordvo);
  }

  /**
   * 已做过收款，检查能否再次收款
   */
  public void checkCanGathering(SaleOrderHVO[] heads) {
    /* 收款限额控制预收“打勾”时：订单表头订单收款限额=实际预收款金额时不能再次收款
     * 收款限额控制预收“不打勾”时：订单表头的价税合计 = 实际收款金额时不能再次收款 */
    StringBuilder errMsg = new StringBuilder();
    for (SaleOrderHVO head : heads) {
      boolean bpreceiveflag =
          head.getBpreceiveflag() == null ? false : head.getBpreceiveflag()
              .booleanValue();
      UFDouble npreceivequota = head.getNpreceivequota();
      UFDouble npreceivemny = head.getNpreceivemny();
      UFDouble nthisreceivemny = head.getNthisreceivemny();
      UFDouble ntotalorigmny = head.getNtotalorigmny();
      if (bpreceiveflag
          && MathTool.absCompareTo(npreceivequota, npreceivemny) == 0) {
//        errMsg.append(
//            NCLangResOnserver.getInstance().getStrByID("4006011_0",
//                "04006011-0283")/* 单据号： */).append(head.getVbillcode());
        errMsg.append(NCLangResOnserver.getInstance().getStrByID("4006011_0",
            "04006011-0425")/* 实际预收款金额已达到订单收款限额,不允许再收款！ */);
        errMsg.append("\n");
      }
      UFDouble npreceivemny_new = MathTool.add(npreceivemny, nthisreceivemny);
      if (bpreceiveflag
          && MathTool.absCompareTo(npreceivemny_new, npreceivequota) > 0) {
//        errMsg.append(
//            NCLangResOnserver.getInstance().getStrByID("4006011_0",
//                "04006011-0283")/* 单据号： */).append(head.getVbillcode());
        errMsg.append(NCLangResOnserver.getInstance().getStrByID("4006011_0",
            "04006011-0426")/* 收款限额控制预收,实际预收款金额不能大于订单收款限额！ */);
        errMsg.append("\n");
      }
      UFDouble nreceivedmny = head.getNreceivedmny();
      if (!bpreceiveflag
          && MathTool.absCompareTo(ntotalorigmny, nreceivedmny) == 0) {
//        errMsg.append(
//            NCLangResOnserver.getInstance().getStrByID("4006011_0",
//                "04006011-0283")/* 单据号： */).append(head.getVbillcode());
        errMsg.append(NCLangResOnserver.getInstance().getStrByID("4006011_0",
            "04006011-0427")/* 实际收款金额已达到订单价税合计,不允许再收款！ */);
        errMsg.append("\n");
      }
    }
    if (errMsg.length() > 0) {
      ExceptionUtils.wrappBusinessException(errMsg.toString());
    }
  }

  public void checkGatheringbillAndSaleorderConsistent(SaleOrderVO saleordervo,
      AggGatheringBillVO gatheringbillvo) throws BusinessException {
    // 不允许编辑的字段：开票客户、币种、应收组织
    String ccustomerid = saleordervo.getParentVO().getCcustomerid();
    String corigcurrencyid = saleordervo.getParentVO().getCorigcurrencyid();
    String cinvoicecustid = saleordervo.getParentVO().getCinvoicecustid();
    String carorgid = saleordervo.getChildrenVO()[0].getCarorgid();
    GatheringBillVO parent = (GatheringBillVO) gatheringbillvo.getParentVO();
    GatheringBillItemVO[] items =
        (GatheringBillItemVO[]) gatheringbillvo.getChildrenVO();
    if (!parent.getPk_org().equals(carorgid)) {
      throw new BusinessException(nc.vo.ml.NCLangRes4VoTransl.getNCLangRes()
          .getStrByID("4006011_0", "04006011-0196")/* @res "不允许修改应收组织。" */);
    }
    for (int i = 0; i < items.length; i++) {
      // TODO 订单客户是否允许修改待议，先按用例上的来
      if (!ccustomerid.equals(items[i].getOrdercubasdoc())) {
        throw new BusinessException(nc.vo.ml.NCLangRes4VoTransl.getNCLangRes()
            .getStrByID("4006011_0", "04006011-0193")/* @res "不允许修改订单客户。" */);
      }
      if (!corigcurrencyid.equals(items[i].getPk_currtype())) {
        throw new BusinessException(nc.vo.ml.NCLangRes4VoTransl.getNCLangRes()
            .getStrByID("4006011_0", "04006011-0195")/* @res "不允许修改币种。" */);
      }
      if (!cinvoicecustid.equals(items[i].getCustomer())) {
        throw new BusinessException(nc.vo.ml.NCLangRes4VoTransl.getNCLangRes()
            .getStrByID("4006011_0", "04006011-0194")/* @res "不允许修改开票客户。" */);
      }
    }

  }

  public void checkGatheringbillAndSoBalanceConsistent(
      SoBalanceViewVO[] sobalanceview, AggGatheringBillVO gatheringbillvo,
      String paybillrow) throws BusinessException {
    // 不允许编辑的字段：开票客户、币种、应收组织
    // 财务核销时建立的订单财务核销关系，收款单与订单上述字段可能不一致，这里可能需要根据关系类型进行区分判断
    GatheringBillVO parent = (GatheringBillVO) gatheringbillvo.getParentVO();
    GatheringBillItemVO[] items =
        (GatheringBillItemVO[]) gatheringbillvo.getChildrenVO();
    for (SoBalanceViewVO viewvo : sobalanceview) {
      SoBalanceHVO sobalancehvo = viewvo.getHead();
      // SoBalanceBVO sobalancebvo = viewvo.getBody();
      String ccustomerid = sobalancehvo.getCcustomerid();
      String cinvoicecustid = sobalancehvo.getCinvoicecustid();
      String corigcurrencyid = sobalancehvo.getCorigcurrencyid();
      String carorgid = sobalancehvo.getCarorgid();
      SoBalanceBVO sobalancebvo = viewvo.getBody();
      int fibaltype = sobalancebvo.getFibaltype().intValue();
      if (SoBalanceType.SOBALANCE_ORDERBAL.getIntValue() == fibaltype) {
        if (!parent.getPk_org().equals(carorgid)) {
          throw new BusinessException(nc.vo.ml.NCLangRes4VoTransl
              .getNCLangRes().getStrByID("4006011_0", "04006011-0196")/* @res
                                                                       * "不允许修改应收组织。" */);
        }
        for (int i = 0; i < items.length; i++) {
          if (!items[i].getPk_gatheritem().equals(paybillrow)) {
            continue;
          }
          if (!cinvoicecustid.equals(items[i].getCustomer())) {
            throw new BusinessException(nc.vo.ml.NCLangRes4VoTransl
                .getNCLangRes().getStrByID("4006011_0", "04006011-0194")/* @res
                                                                         * "不允许修改开票客户。" */);
          }
          if (!corigcurrencyid.equals(items[i].getPk_currtype())) {
            throw new BusinessException(nc.vo.ml.NCLangRes4VoTransl
                .getNCLangRes().getStrByID("4006011_0", "04006011-0195")/* @res
                                                                         * "不允许修改币种。" */);
          }
          if (!ccustomerid.equals(items[i].getOrdercubasdoc())) {
            throw new BusinessException(nc.vo.ml.NCLangRes4VoTransl
                .getNCLangRes().getStrByID("4006011_0", "04006011-0193")/* @res
                                                                         * "不允许修改订单客户。" */);
          }
        }
      }
    }
  }

  public void checkSoBalanceAndSaleorderConsistent(SoBalanceVO bill,
      SaleOrderVO saleordervo) {
    SoBalanceHVO sobalancehvo = bill.getParentVO();
    String cinvoicecustid = sobalancehvo.getCinvoicecustid();
    String corigcurrencyid = sobalancehvo.getCorigcurrencyid();
    UFDouble ntotalorigtaxmny = sobalancehvo.getNtotalorigtaxmny();

    if (!corigcurrencyid.equals(saleordervo.getParentVO().getCorigcurrencyid())) {
      ExceptionUtils
          .wrappBusinessException(nc.vo.ml.NCLangRes4VoTransl.getNCLangRes()
              .getStrByID("4006011_0", "04006011-0108")/* @res
                                                        * "订单收款核销关系与订单数据不一致，请刷新数据重做业务。" */);
    }
    if (!cinvoicecustid.equals(saleordervo.getParentVO().getCinvoicecustid())) {
      ExceptionUtils
          .wrappBusinessException(nc.vo.ml.NCLangRes4VoTransl.getNCLangRes()
              .getStrByID("4006011_0", "04006011-0108")/* @res
                                                        * "订单收款核销关系与订单数据不一致，请刷新数据重做业务。" */);
    }
    if (MathTool.compareTo(ntotalorigtaxmny, saleordervo.getParentVO()
        .getNtotalorigmny()) != 0) {
      ExceptionUtils
          .wrappBusinessException(nc.vo.ml.NCLangRes4VoTransl.getNCLangRes()
              .getStrByID("4006011_0", "04006011-0108")/* @res
                                                        * "订单收款核销关系与订单数据不一致，请刷新数据重做业务。" */);
    }

  }

  public SoBalanceBVO createBalanceBVOByGatheringBillItemForManual(
      GatheringBillItemVO gatheringbillitemvo, SoBalanceVO sobalancevo) {
    UFDouble ntotalorigtaxmny = sobalancevo.getParentVO().getNtotalorigtaxmny();
    UFDouble totalbodymny =
        SoBalanceUtil.getInstance().caculateSoBalanceTotalBodymnyForManual(
            sobalancevo);

    UFDouble diffmny = MathTool.sub(ntotalorigtaxmny, totalbodymny);
    if (MathTool.compareTo(ntotalorigtaxmny, UFDouble.ZERO_DBL) > 0) {
      if (MathTool.compareTo(diffmny, UFDouble.ZERO_DBL) <= 0) {
        diffmny = UFDouble.ZERO_DBL;
      }
    }
    else {
      if (MathTool.compareTo(ntotalorigtaxmny, UFDouble.ZERO_DBL) < 0) {
        if (MathTool.compareTo(diffmny, UFDouble.ZERO_DBL) >= 0) {
          diffmny = UFDouble.ZERO_DBL;
        }
      }
      else {
        diffmny = UFDouble.ZERO_DBL;
      }
    }
    SoBalanceBVO bodyvo = null;

    if (MathTool.absCompareTo(diffmny, UFDouble.ZERO_DBL) > 0) {
      bodyvo = new SoBalanceBVO();
      bodyvo.setCpaybillid(gatheringbillitemvo.getPk_gatherbill());
      bodyvo.setCpaybillrowid(gatheringbillitemvo.getPk_gatheritem());
      bodyvo.setVarbillcode(gatheringbillitemvo.getBillno());
      bodyvo.setDarbilldate(gatheringbillitemvo.getBilldate());
      bodyvo.setDarbalancedate(gatheringbillitemvo.getBilldate());
      bodyvo.setCarorigcurrencyid(gatheringbillitemvo.getPk_currtype());
      bodyvo.setCprodlineid(gatheringbillitemvo.getProductline());
      bodyvo.setNorigarmny(gatheringbillitemvo.getMoney_cr());
      // 收款单行"预占用原币余额"为专门为销售订单可建立核销关系而设立的：
      // 财务核销和订单核销都会影响,实际值 = 收款单原币金额 - 财务核销金额 - 订单核销金额
      if (MathTool
          .absCompareTo(diffmny, gatheringbillitemvo.getOccupationmny()) > 0) {
        bodyvo.setNorigthisbalmny(gatheringbillitemvo.getOccupationmny());
      }
      else {
        bodyvo.setNorigthisbalmny(diffmny);
      }
      bodyvo.setFibaltype(Integer.valueOf(SoBalanceType.SOBALANCE_ORDERBAL
          .getIntValue()));
      bodyvo.setBpreceiveflag(Integer.valueOf(1).equals(
          gatheringbillitemvo.getPrepay()) ? UFBoolean.TRUE : UFBoolean.FALSE);
    }

    return bodyvo;
  }

  /**
   * 获得当前默认收款的金额
   */
  public UFDouble getDefaultGatheringMny(SaleOrderVO ordvo) {

    /* 收款限额控制预收“打勾”时，默认收款金额=订单表头订单收款限额-实际预收款金额。
     * 收款限额控制预收“不打勾”时：
     * 订单收款限额为空或者等于0：默认收款金额=订单表头的价税合计-实际收款金额。
     * 订单收款限额不为空且不等于0：默认收款金额=订单表头的订单收款限额-实际收款金额。 */
    SaleOrderHVO head = ordvo.getParentVO();
    // 收款限额控制预收
    UFBoolean preceiveflag = head.getBpreceiveflag();
    // 订单收款限额
    UFDouble preceivequota = head.getNpreceivequota();
    // 实际预收款金额
    UFDouble preceivemny = head.getNpreceivemny();
    // 实际收款金额
    UFDouble receivedmny = head.getNreceivedmny();
    // 价税合计
    UFDouble totalorigmny = head.getNtotalorigmny();
    UFDouble ret = UFDouble.ZERO_DBL;
    if (preceiveflag != null && preceiveflag.booleanValue()) {
      ret = MathTool.sub(preceivequota, preceivemny);
    }
    else if (MathTool.compareTo(preceivequota, UFDouble.ZERO_DBL) == 0) {
      ret = MathTool.sub(totalorigmny, receivedmny);
    }
    else {
      // 实际收款金额已大于收款限额：那么默认值为0
      if (MathTool.compareTo(MathTool.abs(receivedmny),
          MathTool.abs(preceivequota)) > 0) {
        ret = UFDouble.ZERO_DBL;
      }
      else {
        ret = MathTool.sub(preceivequota, receivedmny);
      }
    }

    return ret;
  }

  public String getWherePartSqlCanVerify(AbstractGatheringKeyValue keyValue) {
    if (keyValue == null) {
      return "";
    }
    // keyValue.get
    // 初始化SO11参数
    this.initParaSO11(keyValue);
    StringBuilder sb = new StringBuilder();
    sb.append("and dr = 0");
    UFDouble money = keyValue.getMoney();
    if (MathTool.compareTo(money, UFDouble.ZERO_DBL) > 0) {
      sb.append(" and isnull(occupationmny,0) > 0");
    }
    else {
      sb.append(" and isnull(occupationmny,0) < 0");
    }
    // 固定条件——财务组织
    String carorgid = keyValue.getPk_org();
    if (carorgid == null) {
      sb.append(" and");
      sb.append(" pk_org = '~'");
    }
    else {
      sb.append(" and");
      sb.append(" pk_org = '");
      sb.append(carorgid);
      sb.append("'");
    }
    // 固定条件——币种
    String corigcurrencyid = keyValue.getPk_currtype();
    if (corigcurrencyid == null) {
      sb.append(" and");
      sb.append(" pk_currtype = '~'");
    }
    else {
      sb.append(" and");
      sb.append(" pk_currtype = '");
      sb.append(corigcurrencyid);
      sb.append("'");
    }
    // 固定条件——开票客户
    String cinvoicecustid = keyValue.getCustomer();
    if (this.paraSet.contains(SoBalanceHVO.CINVOICECUSTID)) {
      if (cinvoicecustid == null) {
        sb.append(" and");
        sb.append(" customer = '~'");
      }
      else {
        sb.append(" and");
        sb.append(" customer = '");
        sb.append(cinvoicecustid);
        sb.append("'");
      }
    }
    String pk_org = keyValue.getSo_org();
    if (this.paraSet.contains(SoBalanceHVO.PK_ORG)) {
      if (pk_org == null) {
        sb.append(" and");
        sb.append(" so_org = '~'");
      }
      else {
        sb.append(" and");
        sb.append(" so_org = '");
        sb.append(pk_org);
        sb.append("'");
      }
    }
    String vtrantypecode = keyValue.getSo_ordertype();
    if (this.paraSet.contains(SoBalanceHVO.VTRANTYPECODE)) {
      if (vtrantypecode == null) {
        sb.append(" and");
        sb.append(" so_ordertype = '~'");
      }
      else {
        sb.append(" and");
        sb.append(" so_ordertype = '");
        sb.append(vtrantypecode);
        sb.append("'");
      }
    }
    String csettleorgid = keyValue.getSett_org();
    if (this.paraSet.contains(SaleOrderBVO.CSETTLEORGID)) {
      if (csettleorgid == null) {
        sb.append(" and");
        sb.append(" sett_org = '~'");
      }
      else {
        sb.append(" and");
        sb.append(" sett_org = '");
        sb.append(csettleorgid);
        sb.append("'");
      }
    }
    String cdeptid = keyValue.getSo_deptid();
    if (this.paraSet.contains(SoBalanceHVO.CDEPTID)) {
      if (cdeptid == null) {
        sb.append(" and");
        sb.append(" so_deptid  = '~'");
      }
      else {
        sb.append(" and");
        sb.append(" so_deptid = '");
        sb.append(cdeptid);
        sb.append("'");
      }
    }
    String cemployeeid = keyValue.getSo_psndoc();
    if (this.paraSet.contains(SoBalanceHVO.CEMPLOYEEID)) {
      if (cemployeeid == null) {
        sb.append(" and");
        sb.append(" so_psndoc = '~'");
      }
      else {
        sb.append(" and");
        sb.append(" so_psndoc = '");
        sb.append(cemployeeid);
        sb.append("'");
      }
    }
    String cchanneltypeid = keyValue.getSo_transtype();
    if (this.paraSet.contains(SoBalanceHVO.CCHANNELTYPEID)) {
      if (cchanneltypeid == null) {
        sb.append(" and");
        sb.append(" so_transtype = '~'");
      }
      else {
        sb.append(" and");
        sb.append(" so_transtype = '");
        sb.append(cchanneltypeid);
        sb.append("'");
      }
    }
    String ccusttomerid = keyValue.getOrdercubasdoc();
    if (this.paraSet.contains(SoBalanceHVO.CCUSTOMERID)) {
      if (ccusttomerid == null) {
        sb.append(" and");
        sb.append(" ordercubasdoc = '~'");
      }
      else {
        sb.append(" and");
        sb.append(" ordercubasdoc = '");
        sb.append(ccusttomerid);
        sb.append("'");
      }
    }
    // 销售订单上有一行的产品线能匹配收款单产品线，那么整单都可以收款核销
    String[] cprodlineids = keyValue.getProductlines();
    if (this.paraSet.contains(SoBalanceBVO.CPRODLINEID)) {
      Set<String> notNullSet = new HashSet<String>();
      boolean isNull = false;
      for (String cprodlineid : cprodlineids) {
        if (cprodlineid == null) {
          isNull = true;
        }
        else {
          notNullSet.add(cprodlineid);
        }
      }
      sb.append("and (");
      StringBuilder sbProductline = new StringBuilder();
      if (notNullSet.size() > 0) {
        IDExQueryBuilder iq =
            new IDExQueryBuilder(SOTable.TMP_SO_ID1.getName());
        sbProductline.append(iq.buildSQL("productline",
            notNullSet.toArray(new String[notNullSet.size()])));
      }
      if (isNull) {
        if (sbProductline.length() > 0) {
          sbProductline.append(" or");
        }
        sbProductline.append(" productline = '~' ");
      }
      sb.append(sbProductline.toString());
      sb.append(")");
    }
    return sb.toString();
  }

  /**
   * VO对照前准备SaleOrderVO：订单收款只产生一行收款单行
   */
  public SaleOrderVO prepareOrderBeforeChangeData(SaleOrderVO ordvo) {
    return this.prepareOrderBeforeChangeData(ordvo, null);
  }

  /**
   * VO对照前准备SaleOrderVO：按照录入的收款金额，订单收款只产生一行收款单行
   */
  public SaleOrderVO prepareOrderByGatherMny(SaleOrderVO ordvo,
      UFDouble thisgathermny) {
    return this.prepareOrderBeforeChangeData(ordvo, thisgathermny);
  }

  private void initParaSO11(AbstractGatheringKeyValue keyValue) {
    this.paraSet = new HashSet<String>();
    String[] paras = null;

    String pk_saleorg = keyValue.getSo_org();
    if (pk_saleorg != null) {
      paras = SOSysParaInitUtil.getSO11(pk_saleorg);
    }

    if (paras != null && paras.length > 0) {
      for (String para : paras) {
        this.paraSet.add(para);
      }
    }
  }

  private SaleOrderVO prepareOrderBeforeChangeData(SaleOrderVO ordvo,
      UFDouble thisgathermny) {

    SaleOrderVO retVO = new SaleOrderVO();
    SaleOrderHVO newHead = (SaleOrderHVO) ordvo.getParentVO().clone();
    SaleOrderBVO newBody = (SaleOrderBVO) ordvo.getChildrenVO()[0].clone();
    /** 计算表体金额和并设置到一行收款单行 */

    // 原币无税金额
    UFDouble sumOrigmny = UFDouble.ZERO_DBL;
    // 原币价税合计
    UFDouble sumOrigtaxmny = UFDouble.ZERO_DBL;
    // 本币税额
    UFDouble sumtax = UFDouble.ZERO_DBL;
    // 本币价税合计
    UFDouble sumtaxmny = UFDouble.ZERO_DBL;
    // 本币无税金额
    UFDouble summny = UFDouble.ZERO_DBL;
    // 集团本币价税合计
    UFDouble groupsumtaxmny = UFDouble.ZERO_DBL;
    // 集团本币无税金额
    UFDouble groupsummny = UFDouble.ZERO_DBL;
    // 全局本币价税合计
    UFDouble globalsumtaxmny = UFDouble.ZERO_DBL;
    // 全局本币无税金额
    UFDouble globalsummny = UFDouble.ZERO_DBL;

    SaleOrderBVO[] bodys = ordvo.getChildrenVO();
    for (SaleOrderBVO body : bodys) {
      // 赠品不传应收
      if (body.getBlargessflag().booleanValue()) {
        continue;
      }
      // sumOrigtax = MathTool.add(sumOrigtax, body.getNorigtax());
      sumOrigmny = MathTool.add(sumOrigmny, body.getNorigmny());
      sumOrigtaxmny = MathTool.add(sumOrigtaxmny, body.getNorigtaxmny());
      sumtaxmny = MathTool.add(sumtaxmny, body.getNtaxmny());
      sumtax = MathTool.add(sumtax, body.getNtax());
      summny = MathTool.add(summny, body.getNmny());
      groupsumtaxmny = MathTool.add(groupsumtaxmny, body.getNgrouptaxmny());
      groupsummny = MathTool.add(groupsummny, body.getNgroupmny());
      globalsumtaxmny = MathTool.add(globalsumtaxmny, body.getNglobaltaxmny());
      globalsummny = MathTool.add(globalsummny, body.getNglobalmny());
    }

    // 原币
    newBody.setNorigtaxmny(sumOrigtaxmny);
    // newBody.setNorigtax(sumOrigtax);
    newBody.setNorigmny(sumOrigmny);

    // 本币
    newBody.setNtaxmny(sumtaxmny);
    newBody.setNtax(sumtax);
    newBody.setNmny(summny);

    // 集团、全局本币
    newBody.setNgrouptaxmny(groupsumtaxmny);
    newBody.setNgroupmny(groupsummny);
    newBody.setNglobaltaxmny(globalsumtaxmny);
    newBody.setNglobalmny(globalsummny);

    // 组装SaleOrderVO
    retVO.setParentVO(newHead);
    retVO.setChildrenVO(new SaleOrderBVO[] {
      newBody
    });

    // 取最新折本汇率
    UFDouble oldexchgrate = newBody.getNexchangerate();
    IKeyValue keyValue = new VOKeyValue<SaleOrderVO>(retVO);
    SOExchangeRateRule exraterule = new SOExchangeRateRule(keyValue);
    int[] rows = new int[] {
      0
    };
    exraterule.calcCurrentBodyExchangeRates(rows);
    UFDouble newexchgrate = retVO.getChildrenVO()[0].getNexchangerate();
    if (MathTool.isZero(newexchgrate)) {
      retVO.getChildrenVO()[0].setNexchangerate(oldexchgrate);
    }

    SaleOrderVOCalculator calculator = new SaleOrderVOCalculator(retVO);
    // 默认收款金额 = 可以收款价税合计 - 已收款
    UFDouble nsumtaxmny = newHead.getNtotalorigmny();
    if (null == thisgathermny) {
      thisgathermny = this.getDefaultGatheringMny(retVO);
    }
    retVO.getParentVO().setNtotalorigmny(thisgathermny);
    retVO.getChildrenVO()[0].setNorigtaxmny(thisgathermny);
    // 非第一次收款，则重新计算
    if (MathTool.compareTo(thisgathermny, nsumtaxmny) != 0) {
      calculator.calculate(rows, SaleOrderBVO.NORIGTAXMNY);
    }
    // 否则汇率不一致时取最新折本汇率计算
    else if (!MathTool.isZero(newexchgrate)
        && MathTool.compareTo(oldexchgrate, newexchgrate) != 0) {
      calculator.calculate(rows, SaleOrderBVO.NEXCHANGERATE);
    }

    return retVO;

  }
}
