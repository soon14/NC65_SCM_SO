package nc.impl.so.m30.closemanage;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;

import nc.bs.so.m30.rule.atp.SaleOrderVOATPAfterRule;
import nc.bs.so.m30.rule.atp.SaleOrderVOATPBeforeRule;
import nc.bs.so.m30.state.SaleOrderStateMachine;
import nc.bs.so.m30.state.bill.BillAuditState;
import nc.bs.so.m30.state.bill.BillCloseState;
import nc.bs.so.m30.state.bill.BillFreezeState;
import nc.bs.so.m30.state.bill.BillOpenState;
import nc.bs.so.m30.state.row.ArSettleCloseState;
import nc.bs.so.m30.state.row.ArSettleOpenState;
import nc.bs.so.m30.state.row.CostSettleCloseState;
import nc.bs.so.m30.state.row.CostSettleOpenState;
import nc.bs.so.m30.state.row.InvoiceCloseState;
import nc.bs.so.m30.state.row.InvoiceOpenState;
import nc.bs.so.m30.state.row.OutCloseState;
import nc.bs.so.m30.state.row.OutOpenState;
import nc.bs.so.m30.state.row.RowCloseState;
import nc.bs.so.m30.state.row.RowOpenState;
import nc.bs.so.m30.state.row.SendCloseState;
import nc.bs.so.m30.state.row.SendOpenState;
import nc.impl.pubapp.bill.convertor.BillToViewConvertor;
import nc.impl.pubapp.bill.convertor.ViewToBillConvertor;
import nc.impl.pubapp.bill.state.IState;
import nc.impl.pubapp.env.BSContext;
import nc.impl.pubapp.pattern.data.bill.tool.BillTransferTool;
import nc.impl.pubapp.pattern.data.view.ViewQuery;
import nc.impl.pubapp.pattern.data.view.ViewUpdate;
import nc.impl.pubapp.pattern.data.vo.VOUpdate;
import nc.impl.pubapp.pattern.data.vo.tool.VOConcurrentTool;
import nc.impl.pubapp.pattern.database.DataAccessUtils;
import nc.itf.scmpub.reference.uap.group.SysInitGroupQuery;
import nc.itf.so.m30.closemanage.ISaleOrderCloseManageMaintain;
import nc.itf.so.m30.ref.ic.m4453.ICm4453ServicesUtil;
import nc.itf.so.m30.ref.ic.m4c.ICm4CServicesUtil;
import nc.itf.so.m30.ref.so.m32.SOm32ServicesUtil;
import nc.itf.so.m30.ref.so.m33.SOm33ServicesUtil;
import nc.itf.so.m30.ref.so.m4331.SOm4331ServicesUtil;
import nc.itf.so.pub.ref.ic.m4c.SOATPprocess;
import nc.pubitf.ic.m4c.m30.ICSaleOutNumInfoVO;
import nc.ui.querytemplate.querytree.IQueryScheme;
import nc.vo.ml.NCLangRes4VoTransl;
import nc.vo.pub.BusinessException;
import nc.vo.pub.lang.UFBoolean;
import nc.vo.pub.lang.UFDouble;
import nc.vo.pubapp.pattern.data.IObjectConvert;
import nc.vo.pubapp.pattern.data.IRowSet;
import nc.vo.pubapp.pattern.data.ValueUtils;
import nc.vo.pubapp.pattern.exception.ExceptionUtils;
import nc.vo.pubapp.pattern.log.TimeLog;
import nc.vo.pubapp.pattern.pub.MathTool;
import nc.vo.pubapp.pattern.pub.SqlBuilder;
import nc.vo.pubapp.query2.sql.process.QueryCondition;
import nc.vo.pubapp.query2.sql.process.QuerySchemeProcessor;
import nc.vo.scmpub.res.BusinessCheck;
import nc.vo.scmpub.res.billtype.ICBillType;
import nc.vo.scmpub.res.billtype.SOBillType;
import nc.vo.so.m30.entity.SaleOrderBVO;
import nc.vo.so.m30.entity.SaleOrderHVO;
import nc.vo.so.m30.entity.SaleOrderVO;
import nc.vo.so.m30.entity.SaleOrderViewVO;
import nc.vo.so.pub.SOConstant;
import nc.vo.so.pub.SOParameterVO;
import nc.vo.so.pub.enumeration.BillStatus;
import nc.vo.so.pub.util.AggVOUtil;
import nc.vo.so.pub.util.ListUtil;
import nc.vo.so.pub.util.SOSysParaInitUtil;
import nc.vo.so.pub.util.ViewVOUtil;
import nc.vo.so.pub.util.biz.SOBusiUtil;
import nc.vo.so.pub.votools.SoVoTools;

public class SaleOrderCloseManageMaintainImpl implements
    ISaleOrderCloseManageMaintain {

  @Override
  public SaleOrderVO[] closeSaleOrder(SaleOrderVO[] bills)
      throws BusinessException {
    try {
      // 手工做结算关闭，检查订单下游出库单、销售发票、途损单是否已经审批
      String[] orderbids =
          AggVOUtil.getDistinctItemFieldArray(bills,
              SaleOrderBVO.CSALEORDERBID, String.class);
      String[] orderids =
          AggVOUtil.getDistinctItemFieldArray(bills, SaleOrderBVO.CSALEORDERID,
              String.class);
      this.checkOrderDownBillAllApprove(orderids, orderbids);

      // 更新关闭/打开原因
      this.updateCloseOpenReason(bills);

      // 更新状态
      BillCloseState state = new BillCloseState();
      // SaleOrderBusiLogUtil util = new SaleOrderBusiLogUtil();
      // util.addBillCloseBusiLog(bills);
      return this.setState(state, bills);
    }
    catch (Exception ex) {
      ExceptionUtils.marsh(ex);
    }
    return null;
  }

  @Override
  public SaleOrderViewVO[] closeSaleOrderInvoice(SaleOrderVO[] vos)
      throws BusinessException {
    try {
      IObjectConvert<SaleOrderVO, SaleOrderViewVO> convert =
          new BillToViewConvertor<SaleOrderVO, SaleOrderViewVO>(
              SaleOrderViewVO.class);
      SaleOrderViewVO[] views = convert.convert(vos);
      // 更新关闭/打开原因
      this.updateCloseOpenReason(views);

      InvoiceCloseState state = new InvoiceCloseState();
      // SaleOrderBusiLogUtil util = new SaleOrderBusiLogUtil();
      // util.addInvoiceCloseBusiLog(views);
      return this.setState(state, views);
    }
    catch (Exception ex) {
      ExceptionUtils.marsh(ex);
    }
    return null;
  }

  @Override
  public SaleOrderViewVO[] closeSaleOrderOut(SaleOrderVO[] vos)
      throws BusinessException {
    try {
      IObjectConvert<SaleOrderVO, SaleOrderViewVO> convert =
          new BillToViewConvertor<SaleOrderVO, SaleOrderViewVO>(
              SaleOrderViewVO.class);
      SaleOrderViewVO[] views = convert.convert(vos);
      // 更新关闭/打开原因
      this.updateCloseOpenReason(views);
      OutCloseState state = new OutCloseState();
      // SaleOrderBusiLogUtil util = new SaleOrderBusiLogUtil();
      // util.addOutCloseBusiLog(views);
      return this.setState(state, views);
    }
    catch (Exception ex) {
      ExceptionUtils.marsh(ex);
    }
    return null;
  }

  @Override
  public SaleOrderViewVO[] closeSaleOrderRow(SaleOrderViewVO[] views)
      throws BusinessException {
    try {
      // 手工做结算关闭，检查订单下游出库单、销售发票、途损单是否已经审批
      String[] orderbids =
          SoVoTools.getVOsOnlyValues(views, SaleOrderBVO.CSALEORDERBID);
      String[] orderids =
          ViewVOUtil.getDistinctFieldArray(views, SaleOrderBVO.class,
              SaleOrderBVO.CSALEORDERID, String.class);
      this.checkOrderDownBillAllApprove(orderids, orderbids);

      // 更新关闭/打开原因
      this.updateCloseOpenReason(views);

      RowCloseState state = new RowCloseState();
      // SaleOrderBusiLogUtil util = new SaleOrderBusiLogUtil();
      // util.addBillRowClose(views);
      return this.setState(state, views);
    }
    catch (Exception ex) {
      ExceptionUtils.marsh(ex);
    }
    return null;
  }

  @Override
  public SaleOrderViewVO[] closeSaleOrderSend(SaleOrderVO[] vos)
      throws BusinessException {
    try {
      IObjectConvert<SaleOrderVO, SaleOrderViewVO> convert =
          new BillToViewConvertor<SaleOrderVO, SaleOrderViewVO>(
              SaleOrderViewVO.class);
      SaleOrderViewVO[] views = convert.convert(vos);
      // 更新关闭/打开原因
      this.updateCloseOpenReason(views);

      SendCloseState state = new SendCloseState();
      // SaleOrderBusiLogUtil util = new SaleOrderBusiLogUtil();
      // util.addSendCloseBusiLog(views);
      return this.setState(state, views);
    }
    catch (Exception ex) {
      ExceptionUtils.marsh(ex);
    }
    return null;
  }

  @Override
  public SaleOrderViewVO[] closeSaleOrderSettle(SaleOrderVO[] vos)
      throws BusinessException {
    IObjectConvert<SaleOrderVO, SaleOrderViewVO> convert =
        new BillToViewConvertor<SaleOrderVO, SaleOrderViewVO>(
            SaleOrderViewVO.class);
    SaleOrderViewVO[] views = convert.convert(vos);
    // 手工做结算关闭，检查订单下游出库单、销售发票、途损单是否已经审批
    String[] orderbids =
        SoVoTools.getVOsOnlyValues(views, SaleOrderBVO.CSALEORDERBID);
    String[] orderids =
        ViewVOUtil.getDistinctFieldArray(views, SaleOrderBVO.class,
            SaleOrderBVO.CSALEORDERID, String.class);
    this.checkOrderDownBillAllApprove(orderids, orderbids);

    SaleOrderViewVO[] retViews = null;
    try {
      // 更新关闭/打开原因
      this.updateCloseOpenReason(views);

      // 前台MultiBillTaskRunner调用，传入views都为单行view
      if (!views[0].getBody().getBbcostsettleflag().booleanValue()) {
        CostSettleCloseState costState = new CostSettleCloseState();
        retViews = this.setState(costState, views);
      }
      if (!views[0].getBody().getBbarsettleflag().booleanValue()) {
        ArSettleCloseState arState = new ArSettleCloseState();
        if (null == retViews) {
          retViews = this.setState(arState, views);
        }
        else {
          retViews = this.setState(arState, retViews);
        }
      }

      // 手工结算关闭如果发票或者出库单参与结算则做相应的结算关闭
      retViews = this.processOutInvState(retViews);
    }
    catch (Exception ex) {
      ExceptionUtils.marsh(ex);
    }
    if (null == retViews) {
      return views;
    }
    // SaleOrderBusiLogUtil util = new SaleOrderBusiLogUtil();
    // util.addSetCloseBusiLog(views);
    return retViews;
  }

  @Override
  public SaleOrderVO[] freezeSaleOrder(SaleOrderVO[] billVOs)
      throws BusinessException {
    try {
      BillFreezeState state = new BillFreezeState();
      // SaleOrderBusiLogUtil util = new SaleOrderBusiLogUtil();
      // util.addFreezeBusiLog(billVOs);
      return this.setState(state, billVOs);
    }
    catch (Exception ex) {
      ExceptionUtils.marsh(ex);
    }
    return null;
  }

  @Override
  public SOParameterVO[] openSaleOrder(SaleOrderVO[] vos, boolean isAbandonATP)
      throws BusinessException {
    // // 是否放弃ATP检查
    // boolean isAbandonATP = false;
    // // 参数paraVOs中解析出bills
    // SaleOrderVO[] bills = new SaleOrderVO[paraVOs.length];
    // for (int i = 0; i < paraVOs.length; i++) {
    // bills[i] = (SaleOrderVO) paraVOs[i].getVo();
    // Object o =
    // paraVOs[i].getBusinessCheckMap().get(
    // BusinessCheck.ATPCheck.getCheckCode());
    // if (o != null && !((Boolean) o).booleanValue()) {
    // isAbandonATP = true;
    // }
    // }
    if (isAbandonATP) {
      // 不做ATP检查
      SOATPprocess.abandonATPCheck();
    }
    // 更新打开原因
    this.updateCloseOpenReason(vos);
    // 更新打开状态
    try {
      BillOpenState state = new BillOpenState();
      SaleOrderVO[] newbills = this.setState(state, vos);
      // BillOpenState打开不一定会开票打开，但整单打开要求开票打开
      InvoiceOpenState invoiceOpen = new InvoiceOpenState();
      IObjectConvert<SaleOrderVO, SaleOrderViewVO> convert =
          new BillToViewConvertor<SaleOrderVO, SaleOrderViewVO>(
              SaleOrderViewVO.class);
      SaleOrderViewVO[] newviews = convert.convert(newbills);
      List<SaleOrderViewVO> invoiceOpenlist = new ArrayList<SaleOrderViewVO>();
      for (SaleOrderViewVO viewvo : newviews) {
        if (invoiceOpen.isThisState(viewvo) || this.isLargessCanInvoice(viewvo)) {
          continue;
        }
        invoiceOpenlist.add(viewvo);
      }
      if (invoiceOpenlist.size() > 0) {
        invoiceOpen.setState(ListUtil.toArray(invoiceOpenlist));
      }
      // 组织返回vo
      SOParameterVO[] retVOs = new SOParameterVO[newbills.length];
      for (int i = 0; i < newbills.length; i++) {
        retVOs[i] = new SOParameterVO();
        retVOs[i].setVo(newbills[i]);
      }
      // SaleOrderBusiLogUtil util = new SaleOrderBusiLogUtil();
      // util.addBillOpenBusiLog(bills);
      return retVOs;
    }
    catch (Exception ex) {
      ExceptionUtils.marsh(ex);
    }
    return null;
  }
  
  /**
   * 赠品是否开票
   * 
   * @param view
   */
  private boolean isLargessCanInvoice(SaleOrderViewVO view) {
    SaleOrderBVO body = view.getBody();
    boolean larflag = ValueUtils.getBoolean(body.getBlargessflag());
    String settleorgid = body.getCsettleorgid();
    boolean bSO20 =
        SOSysParaInitUtil.getSO20(settleorgid) == null ? false
            : SOSysParaInitUtil.getSO20(settleorgid).booleanValue();
    return larflag && !bSO20;
  }

  @Override
  public SaleOrderViewVO[] openSaleOrderInvoice(SaleOrderVO[] vos)
      throws BusinessException {
    try {
      IObjectConvert<SaleOrderVO, SaleOrderViewVO> convert =
          new BillToViewConvertor<SaleOrderVO, SaleOrderViewVO>(
              SaleOrderViewVO.class);
      SaleOrderViewVO[] views = convert.convert(vos);
      // 是否可以手工打开
      this.checkIfOpen(SOBillType.Invoice.getCode(), views);

      // 更新关闭/打开原因
      this.updateCloseOpenReason(views);

      InvoiceOpenState state = new InvoiceOpenState();
      // SaleOrderBusiLogUtil util = new SaleOrderBusiLogUtil();
      // util.addInvoiceOpenBusiLog(views);
      return this.setState(state, views);
    }
    catch (Exception ex) {
      ExceptionUtils.marsh(ex);
    }
    return null;
  }

  @Override
  public SOParameterVO[] openSaleOrderOut(SaleOrderVO[] vos,
      boolean isAbandonATP) throws BusinessException {
    IObjectConvert<SaleOrderVO, SaleOrderViewVO> convert =
        new BillToViewConvertor<SaleOrderVO, SaleOrderViewVO>(
            SaleOrderViewVO.class);
    SaleOrderViewVO[] views = convert.convert(vos);

    try {
      // 更新关闭/打开原因
      this.updateCloseOpenReason(views);

      // SaleOrderBusiLogUtil util = new SaleOrderBusiLogUtil();
      // util.addOutOpenBusiLog(views);
      OutOpenState state = new OutOpenState();
      for (SaleOrderViewVO view : views) {
        if (!state.isManualOutOpen(view)) {
          ExceptionUtils
              .wrappBusinessException(NCLangRes4VoTransl.getNCLangRes()
                  .getStrByID("4006011_0", "04006011-0414")/*@res "折扣服务类物料，不应该允许手工做出库打开"*/);
        }
      }
      SaleOrderViewVO[] newbills = this.setState(state, views);
      // 组织返回vo
      SOParameterVO[] retVOs = new SOParameterVO[newbills.length];
      for (int i = 0; i < newbills.length; i++) {
        retVOs[i] = new SOParameterVO();
        retVOs[i].setView(newbills[i]);
      }
      return retVOs;
    }
    catch (Exception ex) {
      ExceptionUtils.marsh(ex);
    }
    return null;
  }

  @Override
  public SaleOrderViewVO[] openSaleOrderRow(SaleOrderViewVO[] views)
      throws BusinessException {
    try {
      // 更新关闭/打开原因
      this.updateCloseOpenReason(views);

      RowOpenState state = new RowOpenState();
      SaleOrderViewVO[] retviewvo = this.setState(state, views);
      InvoiceOpenState invoiceOpen = new InvoiceOpenState();
      List<SaleOrderViewVO> invoiceOpenlist = new ArrayList<SaleOrderViewVO>();
      for (SaleOrderViewVO viewvo : retviewvo) {
        if (invoiceOpen.isThisState(viewvo) || this.isLargessCanInvoice(viewvo)) {
          continue;
        }
        invoiceOpenlist.add(viewvo);
      }
      if (invoiceOpenlist.size() > 0) {
        invoiceOpen.setState(ListUtil.toArray(invoiceOpenlist));
      }
      return retviewvo;
    }
    catch (Exception ex) {
      ExceptionUtils.marsh(ex);
    }
    return null;
  }

  @Override
  public SaleOrderViewVO[] openSaleOrderSend(SaleOrderVO[] vos)
      throws BusinessException {
    try {
      IObjectConvert<SaleOrderVO, SaleOrderViewVO> convert =
          new BillToViewConvertor<SaleOrderVO, SaleOrderViewVO>(
              SaleOrderViewVO.class);
      SaleOrderViewVO[] views = convert.convert(vos);
      // 更新关闭/打开原因
      this.updateCloseOpenReason(views);

      SendOpenState state = new SendOpenState();
      // SaleOrderBusiLogUtil util = new SaleOrderBusiLogUtil();
      // util.addSendOpenBusiLog(views);
      for (SaleOrderViewVO view : views) {
        if (!state.isManualSendOpen(view)) {
          ExceptionUtils
              .wrappBusinessException(NCLangRes4VoTransl.getNCLangRes()
                  .getStrByID("4006011_0", "04006011-0415")/*@res "折扣服务类物料，不应该允许手工做发货打开"*/);
        }
      }
      return this.setState(state, views);
    }
    catch (Exception ex) {
      ExceptionUtils.marsh(ex);
    }
    return null;
  }

  @Override
  public SaleOrderViewVO[] openSaleOrderSettle(SaleOrderVO[] vos)
      throws BusinessException {
    SaleOrderViewVO[] retViews = null;
    IObjectConvert<SaleOrderVO, SaleOrderViewVO> convert =
        new BillToViewConvertor<SaleOrderVO, SaleOrderViewVO>(
            SaleOrderViewVO.class);
    SaleOrderViewVO[] views = convert.convert(vos);
    try {
      // 更新关闭/打开原因
      this.updateCloseOpenReason(views);

      // 前台MultiBillTaskRunner调用，传入views都为单行view
      if (views[0].getBody().getBbcostsettleflag().booleanValue()) {
        CostSettleOpenState costState = new CostSettleOpenState();
        retViews = this.setState(costState, views);
      }
      if (views[0].getBody().getBbarsettleflag().booleanValue()) {
        ArSettleOpenState arState = new ArSettleOpenState();
        if (null == retViews) {
          retViews = this.setState(arState, views);
        }
        else {
          retViews = this.setState(arState, retViews);
        }
      }
    }
    catch (Exception ex) {
      ExceptionUtils.marsh(ex);
    }
    if (null == retViews) {
      return views;
    }
    // SaleOrderBusiLogUtil util = new SaleOrderBusiLogUtil();
    // util.addSetOpenBusiLog(views);
    return retViews;
  }

  @Override
  public SaleOrderViewVO[] querySaleOrderViewVO(IQueryScheme queryScheme)
      throws BusinessException {
    // 后台拼接sql
    String sql = this.createSqlByQueryScheme(queryScheme);

    DataAccessUtils utils = new DataAccessUtils();
    utils.setMaxRows(SOConstant.CLOSEMANAGEMAXROWS);
    IRowSet set = utils.query(sql.toString());
    if (set.size() == 0) {
      return new SaleOrderViewVO[0];
    }
    String[] bids = set.toOneDimensionStringArray();
    ViewQuery<SaleOrderViewVO> query =
        new ViewQuery<SaleOrderViewVO>(SaleOrderViewVO.class);
    SaleOrderViewVO[] views = query.query(bids);
    Set<String> idset = new HashSet<String>();
    for (SaleOrderViewVO view : views) {
      idset.add(view.getHead().getCsaleorderid());
    }

    // 查询"发货审批主数量"
    Map<String, UFDouble> hs4331 = SOm4331ServicesUtil.queryAppNum(bids);

    // 查询"出库审批主数量"、"出库未签字主数量"
    Map<String, ICSaleOutNumInfoVO> hs4C =
        ICm4CServicesUtil.query4CNumInfoFor30CloseMng(bids);

    // 查询"发票审批主数量"
    Map<String, UFDouble> hs32 =
        SOm32ServicesUtil.getInvoiceApproveNum(
            idset.toArray(new String[idset.size()]), bids);

    // 查询"途损单未审核主数量"
    Map<String, UFDouble> hs4453 =
        ICm4453ServicesUtil.queryUnApproveNumByFirst(bids);

    // 查询"应收结算单据类型" ,"成本结算单据类型"
    String[] biztypeids = this.getAllBiztypeids(views);
    Map<String, String[]> hs33 =
        SOm33ServicesUtil.querySquareBillFor30SqEnd(bids, biztypeids);

    // 设置***主数量
    for (SaleOrderViewVO view : views) {
      Object bid = view.getAttributeValue(SaleOrderBVO.CSALEORDERBID);
      SaleOrderHVO head = view.getHead();
      SaleOrderBVO body = view.getBody();
      // --发货审批主数量
      body.setNsendauditnum(hs4331.get(bid));
      // --"出库审批主数量"、"出库未签字主数量"
      ICSaleOutNumInfoVO icVO = hs4C.get(bid);
      if (null != icVO) {
        body.setNoutauditnum(icVO.getNoutauditnum());
        body.setNoutnotauditnum(icVO.getNoutnotauditnum());
      }
      // --"发票审批主数量"
      body.setNinvoiceauditnum(hs32.get(bid));
      // --"途损单未审核主数量"
      body.setNlossnotauditnum(hs4453.get(bid));
      // --"未传确认应收主数量":
      String[] squareBillType = hs33.get(head.getCbiztypeid());
      if (null == squareBillType || null == squareBillType[0]) {
        body.setNnotarnum(MathTool.sub(body.getNnum(), body.getNtotalarnum()));
      }
      // --1.出库传应收,未传确认应收主数量 = 累计出库主数量 - 累计确认应收主数量
      else if (ICBillType.SaleOut.getCode().equals(squareBillType[0])) {
        body.setNnotarnum(MathTool.sub(body.getNtotaloutnum(),
            body.getNtotalarnum()));
      }
      // --2.发票传应收,未传确认应收主数量 = 累计开票主数量 - 累计确认应收主数量
      else if (SOBillType.Invoice.getCode().equals(squareBillType[0])) {
        body.setNnotarnum(MathTool.sub(body.getNtotalinvoicenum(),
            body.getNtotalarnum()));
      }
      // --"未传存货核算主数量":
      if (null == squareBillType || null == squareBillType[1]) {
        body.setNnotcostnum(MathTool.sub(body.getNnum(),
            body.getNtotalcostnum()));
      }
      // --1.出库传应收,未传确认应收主数量 = 累计出库主数量 - 累计传存货核算主数量
      else if (ICBillType.SaleOut.getCode().equals(squareBillType[1])) {
        body.setNnotcostnum(MathTool.sub(body.getNtotaloutnum(),
            body.getNtotalcostnum()));
      }
      // --2.发票传应收,未传确认应收主数量 = 累计开票主数量 - 累计传存货核算主数量
      else if (SOBillType.Invoice.getCode().equals(squareBillType[1])) {
        body.setNnotcostnum(MathTool.sub(body.getNtotalinvoicenum(),
            body.getNtotalcostnum()));
      }
    }
    return views;
  }

  @Override
  public SaleOrderVO[] unFreezeSaleOrder(SaleOrderVO[] billVOs,
      Map<String, Boolean> businessCheckMap) throws BusinessException {
    try {
      this.examATPCheck(businessCheckMap);
      boolean icEnable = SysInitGroupQuery.isICEnabled();
      if (icEnable) {
        // 可用量前
        new SaleOrderVOATPBeforeRule().process(billVOs);
      }

      BillAuditState state = new BillAuditState();
      SaleOrderVO[] rets = this.setState(state, billVOs);

      if (icEnable) {
        // 可用量后
        new SaleOrderVOATPAfterRule().process(billVOs);
      }
      // SaleOrderBusiLogUtil util = new SaleOrderBusiLogUtil();
      // util.addUnFreezeBusiLog(billVOs);
      return rets;
    }
    catch (Exception ex) {
      ExceptionUtils.marsh(ex);
    }
    return null;
  }

  private void checkIfOpen(String billType, SaleOrderViewVO[] views) {
    Set<String> bizSet = new HashSet<String>();
    Map<String, String> codeMap = new HashMap<String, String>();
    for (SaleOrderViewVO view : views) {
      String biz = view.getHead().getCbiztypeid();
      bizSet.add(biz);
      codeMap.put(biz, view.getHead().getVbillcode());
      if (this.isLargessCanInvoice(view)) {
        ExceptionUtils
            .wrappBusinessException(NCLangRes4VoTransl.getNCLangRes()
                .getStrByID("4006011_0", "04006011-0543")/*@res "赠品行物料且参数so20控制赠品行物料不开票则不能做相应手工打开"*/);
      }
    }
    if (bizSet.size() > 0) {
      String[] cbiztypeids = bizSet.toArray(new String[bizSet.size()]);
      Map<String, Set<String>> busiTypeMap =
          new SOBusiUtil().queryAllBillType(cbiztypeids);
      for (int i = 0; i < cbiztypeids.length; i++) {
        Set<String> typeSet = busiTypeMap.get(cbiztypeids[i]);
        if (!typeSet.contains(billType)) {
          ExceptionUtils
              .wrappBusinessException(NCLangRes4VoTransl.getNCLangRes()
                  .getStrByID("4006011_0", "04006011-0416")/*@res "业务流程中没有配置发货、出库、发票则不能做相应的手工关闭"*/);
        }
      }
    }
  }

  /**
   * 手工做结算关闭，检查订单下游出库单、销售发票、途损单是否已经全部审批
   * 
   * @param orderbids
   */
  private void checkOrderDownBillAllApprove(String[] orderids,
      String[] orderbids) {
    UFBoolean[] isAllApprove =
        SOm32ServicesUtil.isInvoiceAllApprove(orderids, orderbids);
    this.checkOrderDownBillAllApprove(orderbids, isAllApprove);
    // 库存模块启用才判断
    if (SysInitGroupQuery.isICEnabled()) {
      isAllApprove = ICm4CServicesUtil.queryIsAllSigned(orderbids);
      this.checkOrderDownBillAllApprove(orderbids, isAllApprove);
      isAllApprove = ICm4453ServicesUtil.queryWastageSigned(orderbids);
      this.checkOrderDownBillAllApprove(orderbids, isAllApprove);
    }

  }

  private void checkOrderDownBillAllApprove(String[] orderbids,
      UFBoolean[] isAllApprove) {
    int iloop = orderbids.length;
    for (int i = 0; i < iloop; i++) {
      if (null == isAllApprove[i] || !isAllApprove[i].booleanValue()) {
        ExceptionUtils
            .wrappBusinessException(NCLangRes4VoTransl.getNCLangRes()
                .getStrByID("4006011_0", "04006011-0417")/*@res "手工结算关闭，订单下游出库单、销售发票、途损单必须已经全部审批"*/);
      }
    }
  }

  private String createSqlByQueryScheme(IQueryScheme queryScheme) {
    QuerySchemeProcessor qrySchemeProcessor =
        new QuerySchemeProcessor(queryScheme);
    String mainTableAlias = qrySchemeProcessor.getMainTableAlias();
    String childTableAlias =
        qrySchemeProcessor.getTableAliasOfAttribute(SaleOrderBVO.METAPATH
            + SaleOrderBVO.PK_ORG);
    QueryCondition qryCon_bbillendflag =
        qrySchemeProcessor.getQueryCondition("bbillendflag");
    Object[] o = null;
    if (qryCon_bbillendflag != null) {
      o = qryCon_bbillendflag.getValues();
    }
    SqlBuilder sql = new SqlBuilder();
    sql.append("select distinct(" + childTableAlias + ".csaleorderbid) ");
    sql.append(qrySchemeProcessor.getFinalFromWhere());
    sql.append(" and " + mainTableAlias + ".pk_group", BSContext.getInstance()
        .getGroupID());
    if (o != null && o.length > 0 && UFBoolean.TRUE.compareTo(o[0]) == 0) {
      sql.append(" and so_saleorder.fstatusflag",
          BillStatus.CLOSED.getIntValue());
    }
    else if (o != null && o.length > 0 && UFBoolean.FALSE.compareTo(o[0]) == 0) {
      sql.append(" and so_saleorder.fstatusflag",
          BillStatus.AUDIT.getIntValue());
    }
    else {
      sql.append(" and (so_saleorder.fstatusflag = "
          + BillStatus.AUDIT.getIntValue() + " or so_saleorder.fstatusflag ="
          + BillStatus.CLOSED.getIntValue() + ")");
    }
    return sql.toString();
  }

  private void examATPCheck(Map<String, Boolean> businessCheckMap) {
    if (null != businessCheckMap
        && null != businessCheckMap.get(BusinessCheck.ATPCheck.getCheckCode())
        && !businessCheckMap.get(BusinessCheck.ATPCheck.getCheckCode())
            .booleanValue()) {
      // 不做ATP检查
      SOATPprocess.abandonATPCheck();
    }
  }

  private String[] getAllBiztypeids(SaleOrderViewVO[] views) {
    Set<String> bizSet = new HashSet<String>();
    for (SaleOrderViewVO view : views) {
      bizSet.add(view.getHead().getCbiztypeid());
    }
    return bizSet.toArray(new String[bizSet.size()]);
  }

  /**
   * 手工结算关闭如果发票或者出库单参与结算则做相应的结算关闭
   * 
   * @param views
   * @return
   */
  private SaleOrderViewVO[] processOutInvState(SaleOrderViewVO[] views) {
    Set<String> sbiz = new HashSet<String>();
    for (SaleOrderViewVO view : views) {
      sbiz.add(view.getHead().getCbiztypeid());
    }
    Map<String, String[]> mreturn =
        new SOBusiUtil().querySquareBusiBillType(sbiz.toArray(new String[sbiz
            .size()]));
    List<SaleOrderViewVO> loutclose = new ArrayList<SaleOrderViewVO>();
    List<SaleOrderViewVO> linvoiceclose = new ArrayList<SaleOrderViewVO>();
    for (SaleOrderViewVO view : views) {
      String biz = view.getHead().getCbiztypeid();
      String[] flags = mreturn.get(biz);
      UFBoolean outflag =
          ValueUtils.getUFBoolean(view.getBody().getBboutendflag());
      if ("Y".equals(flags[0]) && !outflag.booleanValue()) {
        loutclose.add(view);
      }
      UFBoolean invflag =
          ValueUtils.getUFBoolean(view.getBody().getBbinvoicendflag());
      if ("Y".equals(flags[1]) && !invflag.booleanValue()) {
        linvoiceclose.add(view);
      }
    }

    // 订单出库关闭
    int size = loutclose.size();
    if (size > 0) {
      OutCloseState state = new OutCloseState();
      SaleOrderViewVO[] outviews = loutclose.toArray(new SaleOrderViewVO[size]);
      this.setState(state, outviews);
    }

    // 订单开票关闭
    size = linvoiceclose.size();
    if (size > 0) {
      InvoiceCloseState state = new InvoiceCloseState();
      SaleOrderViewVO[] invoiceviews =
          linvoiceclose.toArray(new SaleOrderViewVO[size]);
      this.setState(state, invoiceviews);
    }

    return this.refreshView(views);
  }

  private SaleOrderViewVO[] refreshView(SaleOrderViewVO[] views) {
    String[] bids =
        SoVoTools.getVOsOnlyValues(views, SaleOrderBVO.CSALEORDERBID);
    SaleOrderViewVO[] rets =
        new ViewQuery<SaleOrderViewVO>(SaleOrderViewVO.class).query(bids);
    return rets;
  }

  /**
   * 通过SaleOrder状态机处理单据状态
   * 
   * @param state
   * @param views SaleOrderViewVO
   * @param act 单据动作
   * @time 2010-6-24 上午10:58:57
   */
  private SaleOrderViewVO[] setState(IState<SaleOrderViewVO> state,
      SaleOrderViewVO[] views) {
    // 加锁校验表体ts，因为前台循环每一行调用，校验hts会报并发（上一行影响到整单状态）
    int len = views.length;
    SaleOrderBVO[] bvos = new SaleOrderBVO[len];
    for (int i = 0; i < len; i++) {
      bvos[i] = views[i].getBody();
    }
    VOConcurrentTool tool = new VOConcurrentTool();
    tool.checkTSWithDB(bvos);

    SaleOrderViewVO[] originViews = this.refreshView(views);
    SaleOrderStateMachine bo = new SaleOrderStateMachine();
    bo.setState(state, originViews);

    /**
     * 在最后会统一刷新一下VO,在返回前台，由于手工关闭处理中用到状态机，
     * 而状态机中结算关闭的处理用到的接口，入口参数是id，且没有返回值，
     * 所以VO不能被刷新TS（历史原因）
     */
    return this.refreshView(originViews);
  }

  /**
   * 通过SaleOrder状态机处理单据状态
   * 
   * @param state
   * @param views SaleOrderViewVO
   * @param act 单据动作
   * @time 2010-6-24 上午10:58:57
   */
  private SaleOrderVO[] setState(IState<SaleOrderVO> state, SaleOrderVO[] vos) {
    TimeLog.logStart();
    BillTransferTool<SaleOrderVO> transferTool =
        new BillTransferTool<SaleOrderVO>(vos);
    SaleOrderVO[] billVOs = transferTool.getOriginBills();
    TimeLog.info("补全前台VO、加锁、检查时间戳");/*-=notranslate=-*/

    SaleOrderStateMachine bo = new SaleOrderStateMachine();
    bo.setState(state, billVOs);

    String[] bids =
        AggVOUtil.getDistinctItemFieldArray(vos, SaleOrderBVO.CSALEORDERBID,
            String.class);
    SaleOrderViewVO[] viewrets =
        new ViewQuery<SaleOrderViewVO>(SaleOrderViewVO.class).query(bids);
    ViewToBillConvertor<SaleOrderViewVO, SaleOrderVO> convert =
        new ViewToBillConvertor<SaleOrderViewVO, SaleOrderVO>(SaleOrderVO.class);
    return convert.convert(viewrets);
  }

  private void updateCloseOpenReason(SaleOrderViewVO[] views) {
    String[] names = new String[] {
      SaleOrderBVO.VCLOSEREASON
    };

    ViewUpdate<SaleOrderViewVO> bo = new ViewUpdate<SaleOrderViewVO>();
    bo.update(views, SaleOrderBVO.class, names);
  }

  private void updateCloseOpenReason(SaleOrderVO[] bills) {
    List<SaleOrderBVO> bodyList = new ArrayList<SaleOrderBVO>();
    for (SaleOrderVO bill : bills) {
      SaleOrderBVO[] bodys = bill.getChildrenVO();
      for (SaleOrderBVO body : bodys) {
        bodyList.add(body);
      }
    }
    String[] names = new String[] {
      SaleOrderBVO.VCLOSEREASON
    };
    SaleOrderBVO[] items = new SaleOrderBVO[bodyList.size()];
    items = bodyList.toArray(items);
    VOUpdate<SaleOrderBVO> bo = new VOUpdate<SaleOrderBVO>();
    bo.update(items, names);
  }

}
