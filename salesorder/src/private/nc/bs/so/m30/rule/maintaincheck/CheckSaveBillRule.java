package nc.bs.so.m30.rule.maintaincheck;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;

import nc.bs.framework.common.NCLocator;
import nc.bs.ml.NCLangResOnserver;
import nc.bs.so.pub.rule.MaterielDistributeCheck;
import nc.bs.so.pub.rule.SOProfitCenterUtil;
import nc.bs.so.pub.rule.rowno.SORowNoUtil;
import nc.impl.pubapp.pattern.rule.IRule;
import nc.itf.ct.saledaily.ISaledailyMaintain;
import nc.itf.scmpub.reference.uap.pf.PfServiceScmUtil;
import nc.itf.so.m30trantype.IM30TranTypeService;
import nc.vo.ct.saledaily.entity.AggCtSaleVO;
import nc.vo.jcom.lang.StringUtil;
import nc.vo.ml.NCLangRes4VoTransl;
import nc.vo.pub.BusinessException;
import nc.vo.pub.VOStatus;
import nc.vo.pub.lang.UFBoolean;
import nc.vo.pub.lang.UFDate;
import nc.vo.pub.lang.UFDouble;
import nc.vo.pub.pfflow01.BillbusinessVO;
import nc.vo.pubapp.pattern.data.ValueUtils;
import nc.vo.pubapp.pattern.exception.ExceptionUtils;
import nc.vo.pubapp.pattern.pub.MathTool;
import nc.vo.pubapp.pattern.pub.PubAppTool;
import nc.vo.scmpub.res.billtype.ICBillType;
import nc.vo.scmpub.res.billtype.SOBillType;
import nc.vo.so.m30.entity.SaleOrderBVO;
import nc.vo.so.m30.entity.SaleOrderHVO;
import nc.vo.so.m30.entity.SaleOrderVO;
import nc.vo.so.m30.enumeration.Fretexchange;
import nc.vo.so.m30.enumeration.Largesstype;
import nc.vo.so.m30trantype.entity.M30TranTypeVO;
import nc.vo.so.m30trantype.enumeration.DirectType;
import nc.vo.so.m30trantype.enumeration.SaleMode;
import nc.vo.so.pub.keyvalue.IKeyValue;
import nc.vo.so.pub.keyvalue.VOKeyValue;
import nc.vo.so.pub.rule.SOBuysellTriaRule;
import nc.vo.so.pub.util.SOSysParaInitUtil;
import nc.vo.trade.checkrule.VOChecker;

/**
 * @description
 *              单据保存前检查规则 1、检查单据必输项非空 2、检查日期 3、检查退换货标志 4、检查销售模式 5、退换货关系检查
 * @scene
 *        销售订单新增、修改保存前
 * @param m_transType 销售订单交易类型<销售订单交易类型code,销售订单交易类型vo>
 *          tranTypeService 销售订单交易类型服务接口
 * @author gdsjw
 */
public class CheckSaveBillRule implements IRule<SaleOrderVO> {

  // 销售订单交易类型<销售订单交易类型code,销售订单交易类型vo>
  private Map<String, M30TranTypeVO> m_transType =
      new HashMap<String, M30TranTypeVO>();

  private IM30TranTypeService tranTypeService;

  public M30TranTypeVO getTransType(String ctrantypeid) {
    M30TranTypeVO tranType = this.m_transType.get(ctrantypeid);
    // 缓存中没有销售订单交易类型从后台查询
    if (VOChecker.isEmpty(tranType)) {
      try {
        tranType = this.getTranTypeService().queryTranTypeVO(ctrantypeid);
        m_transType.put(ctrantypeid, tranType);
      }
      catch (BusinessException e) {
        ExceptionUtils.wrappBusinessException(e.getMessage());
      }
    }
    return tranType;
  }

  @Override
  public void process(SaleOrderVO[] vos) {
    for (SaleOrderVO vo : vos) {
      // 这个是补全VO，校验时可能需要区分行状态
      this.checkNotNull(vo);
      this.checkDirectType(vo);
      this.checkFretexchange(vo);
      this.checkSaleMode(vo);
      this.checbodyJustNegative(vo);
      this.checkRowCountLimit(vo);
      // 校验物料和库存组织的分配关系
      this.checkMaterielDistribute(vo);
      this.checkMaterielMutil(vo);
      this.checkFreeCust(vo);
      this.checkOrderPay(vo);

      SORowNoUtil.checkRowNo(vo);
      SOProfitCenterUtil.checkProfitCenterValue(vo);
    }
    // 当鼠标选择物料后，没有离开焦点，直接选择币种。这个时候提示关联销售合同会出现延迟，因此在此做校验。
    checkCTCurrency(vos);
  }

  private void checkCTCurrency(SaleOrderVO[] vos) {
    Map<String, String> currencymap = new HashMap<String, String>();
    Set<String> ctmanageset = new HashSet<String>();
    for (SaleOrderVO vo : vos) {
      String corigcurrencyid = vo.getParentVO().getCorigcurrencyid();
      for (SaleOrderBVO bvo : vo.getChildrenVO()) {
        if (VOStatus.DELETED == bvo.getStatus()
            || VOStatus.UNCHANGED == bvo.getStatus()) {
          continue;
        }

        if (bvo.getCctmanageid() != null) {
          currencymap.put(bvo.getCctmanageid(), corigcurrencyid);
          ctmanageset.add(bvo.getCctmanageid());
        }
      }
    }
    if (ctmanageset.size() == 0) {
      return;
    }
    ISaledailyMaintain ct =
        NCLocator.getInstance().lookup(ISaledailyMaintain.class);
    String[] cthids = ctmanageset.toArray(new String[ctmanageset.size()]);
    try {
      AggCtSaleVO[] ctvos = ct.queryCtApVoByIds(cthids);
      Map<String, String> ctcurrencymap = new HashMap<String, String>();
      for (AggCtSaleVO ctvo : ctvos) {
        ctcurrencymap.put(ctvo.getParentVO().getPk_ct_sale(), ctvo
            .getParentVO().getCorigcurrencyid());
      }
      for (SaleOrderVO vo : vos) {
        for (SaleOrderBVO bvo : vo.getChildrenVO()) {
          if (VOStatus.DELETED == bvo.getStatus()
              || VOStatus.UNCHANGED == bvo.getStatus()) {
            continue;
          }
          String cthid = bvo.getCctmanageid();

          if (cthid == null) {
            continue;
          }
          String corigcurrencyid = currencymap.get(cthid);
          String ctcorigcurrencyid = ctcurrencymap.get(cthid);

          if (!PubAppTool.isEqual(corigcurrencyid, ctcorigcurrencyid)) {
            ExceptionUtils.wrappBusinessException(nc.vo.ml.NCLangRes4VoTransl
                .getNCLangRes().getStrByID("4006011_0", "04006011-0524")/*
                                                                         * 销售订单与销售合同币种不相同
                                                                         * ，请检查!
                                                                         */);
          }
        }
      }
    }
    catch (BusinessException e) {
      ExceptionUtils.wrappBusinessException(e.toString());
    }
  }

  private void checkBodyValidity(SaleOrderHVO head, SaleOrderBVO[] bodys) {
    // 是否流程配有发货单
    boolean isHaveDelivery = false;
    String vtrantypecode = head.getVtrantypecode();
    String cbiztypeid = head.getCbiztypeid();
    BillbusinessVO[] businessvos =
        PfServiceScmUtil.queryBillDest(vtrantypecode, cbiztypeid);
    if (businessvos != null && businessvos.length > 0) {
      for (int i = 0; i < businessvos.length; i++) {
        if (SOBillType.Delivery.getCode().equals(
            businessvos[i].getPk_billtype())) {
          isHaveDelivery = true;
        }
      }
    }
    StringBuilder errMsg = new StringBuilder();
    SaleOrderVO aggvo = new SaleOrderVO();
    aggvo.setParentVO(head);
    for (SaleOrderBVO bvo : bodys) {
      aggvo.setChildrenVO(new SaleOrderBVO[] {
        bvo
      });
      // 过滤删除行 注释掉unchanged
      if (VOStatus.DELETED == bvo.getStatus()
          || VOStatus.UNCHANGED == bvo.getStatus()) {
        continue;
      }
      // 不合法字段
      List<String> listValiField = new ArrayList<String>();

      String cmaterialid = bvo.getCmaterialvid();
      if (PubAppTool.isNull(cmaterialid)) {
        listValiField.add(NCLangResOnserver.getInstance().getStrByID(
            "4006011_0", "04006011-0307")/* 物料 */);
      }

      String castunitid = bvo.getCastunitid();
      if (PubAppTool.isNull(castunitid)) {
        listValiField.add(NCLangResOnserver.getInstance().getStrByID(
            "4006011_0", "04006011-0308")/* 单位 */);
      }

      UFDouble ndiscountrate = bvo.getNdiscountrate();
      if (null == ndiscountrate) {
        listValiField.add(NCLangResOnserver.getInstance().getStrByID(
            "4006011_0", "04006011-0309")/* 整单折扣 */);
      }
      UFDouble nitemdiscount = bvo.getNitemdiscountrate();
      if (null == nitemdiscount) {
        listValiField.add(NCLangResOnserver.getInstance().getStrByID(
            "4006011_0", "04006011-0310")/* 单品折扣 */);
      }
      UFDouble ntaxrate = bvo.getNtaxrate();
      if (null == ntaxrate) {
        listValiField.add(NCLangResOnserver.getInstance().getStrByID(
            "4006011_0", "04006011-0311")/* 税率 */);
      }

      if (null == bvo.getCtaxcodeid()) {
        listValiField.add(NCLangResOnserver.getInstance().getStrByID(
            "4006011_0", "04006011-0439")/* 税码 */);
      }

      if (null == bvo.getFtaxtypeflag()) {
        listValiField.add(NCLangResOnserver.getInstance().getStrByID(
            "4006011_0", "04006011-0440")/* 扣税类别 */);
      }

      if (null == bvo.getNcaltaxmny()) {
        listValiField.add(NCLangResOnserver.getInstance().getStrByID(
            "4006011_0", "04006011-0441")/* 计税金额 */);
      }

      if (null == bvo.getCrececountryid()) {
        listValiField.add(NCLangResOnserver.getInstance().getStrByID(
            "4006011_0", "04006011-0442")/* 收货国家/地区 */);
      }

      if (null == bvo.getCsendcountryid()) {
        listValiField.add(NCLangResOnserver.getInstance().getStrByID(
            "4006011_0", "04006011-0443")/* 发货国家/地区 */);
      }

      if (null == bvo.getCtaxcountryid()) {
        listValiField.add(NCLangResOnserver.getInstance().getStrByID(
            "4006011_0", "04006011-0444")/* 报税国家/地区 */);
      }

      if (null == bvo.getFbuysellflag()) {
        listValiField.add(NCLangResOnserver.getInstance().getStrByID(
            "4006011_0", "04006011-0445")/* 购销类型 */);
      }

      if (null == bvo.getBtriatradeflag()) {
        listValiField.add(NCLangResOnserver.getInstance().getStrByID(
            "4006011_0", "04006011-0446")/* 三角贸易 */);
      }

      UFDate dsenddate = bvo.getDsenddate();
      if (null == dsenddate) {
        listValiField.add(NCLangResOnserver.getInstance().getStrByID(
            "4006011_0", "04006011-0319")/* 计划发货日期 */);
      }
      UFDate dreceivedate = bvo.getDreceivedate();
      if (null == dreceivedate) {
        listValiField.add(NCLangResOnserver.getInstance().getStrByID(
            "4006011_0", "04006011-0320")/* 要求到货日期 */);
      }
      String creceivicustid = bvo.getCreceivecustid();
      if (PubAppTool.isNull(creceivicustid)) {
        listValiField.add(NCLangResOnserver.getInstance().getStrByID(
            "4006011_0", "04006011-0321")/* 收货客户 */);
      }
      String csendstockorgvid = bvo.getCsendstockorgid();
      if (PubAppTool.isNull(csendstockorgvid)) {
        listValiField.add(NCLangResOnserver.getInstance().getStrByID(
            "4006011_0", "04006011-0322")/* 发货库存组织 */);
      }
      // 配有发货单的流程物流组织不可空
      String ctrafficorgvid = bvo.getCtrafficorgvid();
      if (isHaveDelivery && PubAppTool.isNull(ctrafficorgvid)) {
        listValiField.add(NCLangResOnserver.getInstance().getStrByID(
            "4006011_0", "04006011-0323")/* 物流组织 */);
      }
      String csettleorgvid = bvo.getCsettleorgvid();
      if (PubAppTool.isNull(csettleorgvid)) {
        listValiField.add(NCLangResOnserver.getInstance().getStrByID(
            "4006011_0", "04006011-0324")/* 结算财务组织 */);
      }
      String carorgvid = bvo.getCarorgvid();
      if (PubAppTool.isNull(carorgvid)) {
        listValiField.add(NCLangResOnserver.getInstance().getStrByID(
            "4006011_0", "04006011-0429")/* 应收组织 */);
      }
      String ccurrencyid = bvo.getCcurrencyid();
      if (PubAppTool.isNull(ccurrencyid)) {
        listValiField.add(NCLangResOnserver.getInstance().getStrByID(
            "4006011_0", "04006011-0325")/* 本位币 */);
      }

      UFDouble nexchangerate = bvo.getNexchangerate();
      if (null == nexchangerate
          || nexchangerate.compareTo(UFDouble.ZERO_DBL) == 0) {
        listValiField.add(NCLangResOnserver.getInstance().getStrByID(
            "4006011_0", "04006011-0326")/* 折本汇率 */);
      }
      String ctrantypeid = head.getCtrantypeid();
      M30TranTypeVO trantypevo = this.getTransType(ctrantypeid);
      UFDouble naccprice = bvo.getNaccprice();
      if (null != trantypevo.getBlrgcashflag()
          && trantypevo.getBlrgcashflag().booleanValue()) {
        if (naccprice == null || MathTool.isZero(naccprice)) {
          listValiField.add(NCLangResOnserver.getInstance().getStrByID(
              "4006011_0", "04006011-0489")/* 主记账单价 */);
        }
      }
      IKeyValue keyValue = new VOKeyValue<SaleOrderVO>(aggvo);
      SOBuysellTriaRule buyselrule = new SOBuysellTriaRule(keyValue);
      boolean isInternational = buyselrule.isBuysellFlagOut(0);
      // 暂时先去掉跨国业务没有此关系
      if (!isInternational) {
        UFDouble ntaxmny = bvo.getNtaxmny();
        UFDouble naddtaxmny = MathTool.add(bvo.getNtax(), bvo.getNmny());
        if (!MathTool.equals(ntaxmny, naddtaxmny)) {
          listValiField.add(NCLangResOnserver.getInstance().getStrByID(
              "4006011_0",
              "04006011-0453",
              null,
              new String[] {
                ValueUtils.getString(ntaxmny),
                ValueUtils.getString(bvo.getNmny()),
                ValueUtils.getString(bvo.getNtax())
              })/* 本币价税合计({0})不等于本币无税金额({1})加税额({2}) */);
        }
      }
      if (listValiField.size() > 0) {
        String crowno = bvo.getCrowno();
        errMsg.append(NCLangResOnserver.getInstance().getStrByID("4006011_0",
            "04006011-0327", null, new String[] {
              crowno
            })/* 第[{0}]行： */);
        for (String field : listValiField) {
          errMsg
              .append("[")
              .append(field)
              .append("]")
              .append(
                  NCLangResOnserver.getInstance().getStrByID("4006011_0",
                      "04006011-0284")/* 、 */);
        }
        errMsg.deleteCharAt(errMsg.length() - 1);
        errMsg.append("\n");
      }
    }
    if (errMsg.length() > 0) {
      ExceptionUtils.wrappBusinessException(NCLangResOnserver.getInstance()
          .getStrByID("4006011_0", "04006011-0328", null, new String[] {
            errMsg.toString()
          })/* 下列字段值不能为空或为0:\n{0} */);
    }
  }

  private void checkDirectType(SaleOrderVO newbill) {
    SaleOrderHVO head = newbill.getParentVO();
    // 优化：add by zhangby5 从缓存中取交易类型的直运类型
    M30TranTypeVO trantypevo = this.getTransType(head.getCtrantypeid());
    Integer directType =
        trantypevo.getFdirecttype() == null ? DirectType.DIRECTTRAN_NO
            .getIntegerValue() : trantypevo.getFdirecttype();
    if (DirectType.DIRECTTRAN_TO.equalsValue(directType)) {
      for (SaleOrderBVO bvo : newbill.getChildrenVO()) {
        if (VOStatus.DELETED == bvo.getStatus()
            || VOStatus.UNCHANGED == bvo.getStatus()) {
          continue;
        }
        if (StringUtil.isEmpty(bvo.getCsendstordocid())) {
          ExceptionUtils.wrappBusinessException(nc.vo.ml.NCLangRes4VoTransl
              .getNCLangRes().getStrByID("4006011_0", "04006011-0090")/*
                                                                       * @res
                                                                       * "直运调拨类型，发货仓库不允许为空"
                                                                       */);
        }
      }
    }
  }

  private void checbodyJustNegative(SaleOrderVO bill) {
    // 赠品兑付的销售订单不允许录入红字订单行
    SaleOrderBVO[] saleorders = bill.getChildrenVO();
    for (SaleOrderBVO saleorder : saleorders) {
      if (MathTool.compareTo(saleorder.getNastnum(), UFDouble.ZERO_DBL) < 0
          && !PubAppTool.isNull(bill.getParentVO().getCarsubtypeid())) {
        ExceptionUtils.wrappBusinessException(NCLangResOnserver.getInstance()
            .getStrByID("4006011_0", "04006011-0517")/* 赠品兑付的销售订单不允许录入负数行！ */);
      }
    }
  }

  private void checkFreeCust(SaleOrderVO bill) {
    SaleOrderHVO header = bill.getParentVO();
    if (null != header.getBfreecustflag()
        && header.getBfreecustflag().booleanValue()) {
      if (PubAppTool.isNull(header.getCfreecustid())) {
        ExceptionUtils.wrappBusinessException(nc.vo.ml.NCLangRes4VoTransl
            .getNCLangRes().getStrByID("4006011_0", "04006011-0091")/*
                                                                     * @res
                                                                     * "销售订单客户有散户性质，散户必输!"
                                                                     */);
      }
    }
  }

  private void checkFretexchange(SaleOrderVO bill) {
    SaleOrderBVO[] items = bill.getChildrenVO();
    for (SaleOrderBVO item : items) {
      int vostatus = item.getStatus();
      if (vostatus == VOStatus.DELETED) {
        // 不检查删除的行
        continue;
      }
      Integer fretexchange = item.getFretexchange();
      UFDouble nnum = item.getNastnum();
      if (Fretexchange.COMMON.value().equals(fretexchange)) {
        if (null != nnum && nnum.compareTo(UFDouble.ZERO_DBL) < 0) {
          if (item.getBdiscountflag() != null
              && item.getBdiscountflag().booleanValue()) {
            // 普通订单的物料行属性为折扣时，明细行允许数量为负数
          }
          else {
            ExceptionUtils.wrappBusinessException(nc.vo.ml.NCLangRes4VoTransl
                .getNCLangRes().getStrByID("4006011_0", "04006011-0092")/*
                                                                         * @res
                                                                         * "非退货的订单行数量不允许为负数。"
                                                                         */);
          }
        }
      }
      else if (Fretexchange.WITHDRAW.value().equals(fretexchange)) {
        if (item.getBdiscountflag() != null
            && item.getBdiscountflag().booleanValue()) {
          // 退货订单的物料行属性为折扣时，明细行允许数量为正数 v636
          if (null != nnum && nnum.compareTo(UFDouble.ZERO_DBL) < 0) {
            ExceptionUtils.wrappBusinessException(NCLangResOnserver
                .getInstance().getStrByID("4006011_0", "04006011-0490")/*
                                                                        * 退货的订单行（
                                                                        * 折扣类物料
                                                                        * ）
                                                                        * 数量不允许为负数
                                                                        * 。
                                                                        */);
          }
        }
        else {
          if (null != nnum && nnum.compareTo(UFDouble.ZERO_DBL) > 0) {
            ExceptionUtils.wrappBusinessException(NCLangResOnserver
                .getInstance().getStrByID("4006011_0", "04006011-0491")/*
                                                                        * 退货的订单行（
                                                                        * 非折扣类物料
                                                                        * ）
                                                                        * 数量不允许为正数
                                                                        * 。
                                                                        */);
          }
        }
      }
      else if (Fretexchange.EXCHANGE.value().equals(fretexchange)) {
        if (null != nnum && nnum.compareTo(UFDouble.ZERO_DBL) < 0) {

          ExceptionUtils.wrappBusinessException(nc.vo.ml.NCLangRes4VoTransl
              .getNCLangRes().getStrByID("4006011_0", "04006011-0094")/*
                                                                       * @res
                                                                       * "换货的订单行数量不允许为负数。"
                                                                       */);
        }
      }
    }

  }

  private void checkHeadValidity(SaleOrderHVO header) {

    List<String> errField = new ArrayList<String>();

    String pk_org = header.getPk_org();
    if (PubAppTool.isNull(pk_org)) {
      errField.add(NCLangResOnserver.getInstance().getStrByID("4006011_0",
          "04006011-0329")/* 销售组织 */);
    }

    String ctrantypeid = header.getCtrantypeid();
    if (PubAppTool.isNull(ctrantypeid)) {
      errField.add(NCLangResOnserver.getInstance().getStrByID("4006011_0",
          "04006011-0330")/* 交易类型 */);
    }

    String cbiztypeid = header.getCbiztypeid();
    if (PubAppTool.isNull(cbiztypeid)) {
      errField.add(NCLangResOnserver.getInstance().getStrByID("4006011_0",
          "04006011-0331")/* 业务流程 */);
    }

    UFDate dbilldate = header.getDbilldate();
    if (null == dbilldate) {
      errField.add(NCLangResOnserver.getInstance().getStrByID("4006011_0",
          "04006011-0332")/* 单据日期 */);
    }
    String ccustomerid = header.getCcustomerid();
    if (PubAppTool.isNull(ccustomerid)) {
      errField.add(NCLangResOnserver.getInstance().getStrByID("4006011_0",
          "04006011-0333")/* 客户 */);
    }
    String cinvoicecustid = header.getCinvoicecustid();
    if (PubAppTool.isNull(cinvoicecustid)) {
      errField.add(NCLangResOnserver.getInstance().getStrByID("4006011_0",
          "04006011-0334")/* 开票客户 */);
    }
    String corigcurrencyid = header.getCorigcurrencyid();
    if (PubAppTool.isNull(corigcurrencyid)) {
      errField.add(NCLangResOnserver.getInstance().getStrByID("4006011_0",
          "04006011-0335")/* 币种 */);
    }
    String cdeptid = header.getCdeptid();
    if (PubAppTool.isNull(cdeptid)) {
      errField.add(NCLangResOnserver.getInstance().getStrByID("4006011_0",
          "04006011-0336")/* 销售部门 */);
    }

    if (errField.size() > 0) {
      StringBuilder errMsg =
          new StringBuilder(NCLangResOnserver.getInstance().getStrByID(
              "4006011_0", "04006011-0337")/* 下列属性的值不能为空： */);
      errMsg.append(NCLangResOnserver.getInstance().getStrByID("4006011_0",
          "04006011-0338")/* \n 表头 */);
      for (String field : errField) {
        errMsg
            .append("[")
            .append(field)
            .append("]")
            .append(
                NCLangResOnserver.getInstance().getStrByID("4006011_0",
                    "04006011-0284")/* 、 */);
      }
      errMsg.deleteCharAt(errMsg.length() - 1);

      ExceptionUtils.wrappBusinessException(errMsg.toString());
    }
  }

  private void checkMaterielDistribute(SaleOrderVO bill) {
    int len = bill.getChildrenVO().length;
    String[][] materIDStoreIDs = new String[len][2];
    int i = 0;
    for (SaleOrderBVO bvo : bill.getChildrenVO()) {
      materIDStoreIDs[i] = new String[2];
      materIDStoreIDs[i][0] = bvo.getCmaterialvid();
      materIDStoreIDs[i][1] = bvo.getCsendstockorgid();
      i++;
    }
    new MaterielDistributeCheck().check(materIDStoreIDs);
  }

  /**
   * 控制物料是否可重复
   * 
   * @param bill
   */
  private void checkMaterielMutil(SaleOrderVO bill) {
    SaleOrderHVO header = bill.getParentVO();
    M30TranTypeVO trantype = this.getTransType(header.getCtrantypeid());
    if (!trantype.getBmorerows().booleanValue()) {
      Set<String> sinvo = new HashSet<String>();
      Set<String> sinvv = new HashSet<String>();
      for (SaleOrderBVO bvo : bill.getChildrenVO()) {
        // 删除行
        if (VOStatus.DELETED == bvo.getStatus()) {
          continue;
        }
        // 赠品行
        if (null != bvo.getBlargessflag()
            && bvo.getBlargessflag().booleanValue()) {
          continue;
        }
        // 换货行不控制
        int fretexchage =
            bvo.getFretexchange() == null ? Fretexchange.COMMON.getIntValue()
                : bvo.getFretexchange().intValue();
        if (Fretexchange.EXCHANGE.getIntValue() == fretexchage) {
          continue;
        }
        // 分摊赠品行
        Integer larstflag = bvo.getFlargesstypeflag();
        if (Largesstype.APPORTIONLARGESS.equalsValue(larstflag)) {
          continue;
        }
        String materieloid = bvo.getCmaterialid();
        String materielvid = bvo.getCmaterialvid();
        if (sinvo.contains(materieloid) && sinvv.contains(materielvid)) {
          ExceptionUtils.wrappBusinessException(nc.vo.ml.NCLangRes4VoTransl
              .getNCLangRes().getStrByID("4006011_0", "04006011-0095")/*
                                                                       * @res
                                                                       * "订单类型控制同一货物不可列多行！"
                                                                       */);
        }
        else {
          sinvo.add(materieloid);
          sinvv.add(materielvid);
        }
      }
    }
  }

  private void checkNotNull(SaleOrderVO bill) {
    SaleOrderHVO head = bill.getParentVO();
    this.checkHeadValidity(head);

    SaleOrderBVO[] bodys = bill.getChildrenVO();
    if (null == bodys || this.getVORowCount(bill) == 0) {
      ExceptionUtils.wrappBusinessException(nc.vo.ml.NCLangRes4VoTransl
          .getNCLangRes().getStrByID("4006011_0", "04006011-0096")/*
                                                                   * @res
                                                                   * "订单表体不可为空。"
                                                                   */);
    }
    this.checkBodyValidity(head, bodys);
  }

  /**
   * 订单整单价税合计和订单实际收款金额必须同号
   * 
   * @param bill
   */
  private void checkOrderPay(SaleOrderVO bill) {
    UFDouble nsummny = bill.getParentVO().getNtotalorigmny();
    UFDouble npaymny = bill.getParentVO().getNreceivedmny();
    if (MathTool.isDiffSign(nsummny, npaymny)) {
      ExceptionUtils.wrappBusinessException(NCLangRes4VoTransl.getNCLangRes()
          .getStrByID("4006011_0", "04006011-0413")/*
                                                    * @res
                                                    * "订单整单价税合计和订单实际收款金额必须同号！"
                                                    */);
    }

  }

  private void checkRowCountLimit(SaleOrderVO vo) {
    Object pk_org = vo.getParentVO().getPk_org();

    int rowlimit = 0;

    rowlimit =
        SOSysParaInitUtil.getSO01(pk_org.toString()) == null ? 0
            : SOSysParaInitUtil.getSO01(pk_org.toString()).intValue();

    int rowCount = this.getVORowCount(vo);
    if (rowlimit > 0 && rowCount > rowlimit) {
      ExceptionUtils.wrappBusinessException(nc.vo.ml.NCLangRes4VoTransl
          .getNCLangRes().getStrByID("4006011_0", "04006011-0101")/*
                                                                   * @res
                                                                   * "超订单限制行数![参数SO01限制行数:"
                                                                   */
          + rowlimit + "]");
    }
  }

  // 获取VO中表体中非删除状态的行数 zhangby5 2014.7.29
  private int getVORowCount(SaleOrderVO vo) {

    int count = 0;
    for (SaleOrderBVO bvo : vo.getChildrenVO()) {

      if (bvo.getStatus() != VOStatus.DELETED) {
        count++;
      }
    }
    return count;
  }

  private void checkSaleMode(SaleOrderVO bill) {
    SaleOrderHVO header = bill.getParentVO();
    M30TranTypeVO trantype = this.getTransType(header.getCtrantypeid());
    if (trantype == null) {
      ExceptionUtils.wrappBusinessException(nc.vo.ml.NCLangRes4VoTransl
          .getNCLangRes().getStrByID("4006011_0", "04006011-0102")/*
                                                                   * @res
                                                                   * "查询订单类型出错。"
                                                                   */);
    }
    Integer fsalemode = trantype.getFsalemode();
    if (fsalemode == null) {
      fsalemode = (Integer) SaleMode.MODE_COMMON.value();
    }
    boolean isExistCommon = false;
    boolean isExistWithdraw = false;
    boolean isExistExchange = false;

    SaleOrderBVO[] items = bill.getChildrenVO();
    for (SaleOrderBVO item : items) {
      int vostatus = item.getStatus();
      if (vostatus == VOStatus.DELETED) {
        // 不检查删除的行
        continue;
      }
      Integer fretexchange = item.getFretexchange();
      // 折扣类物料数量为空但是价税合计为正(退换货)，要求可以保存通过，价税合计为负则不能通过
      if (Fretexchange.COMMON.value().equals(fretexchange)
          || null == fretexchange) {
        if (!MathTool.equals(item.getNnum(), new UFDouble(0))) {
          isExistCommon = true;
        }
        else {
          if (item.getBdiscountflag().booleanValue()
              && MathTool.lessThan(item.getNorigtaxmny(), new UFDouble(0))) {
            isExistCommon = true;
          }
        }
        if (item.getBdiscountflag().booleanValue()
            && MathTool.equals(item.getNnum(), new UFDouble(0))
            && MathTool.greaterThan(item.getNorigtaxmny(), new UFDouble(0))) {
          isExistWithdraw = true;
        }
      }
      else if (Fretexchange.WITHDRAW.value().equals(fretexchange)) {
        isExistWithdraw = true;
      }
      else if (Fretexchange.EXCHANGE.value().equals(fretexchange)) {
        isExistExchange = true;
      }

      /*  // 订单保存是通过录入的数据来确认退货、换货、普通(FretexchangeRule)，参照退货已经明确了是退货，所以不能根据录入的数据来判断销售模式。因此在这里强制校验下。
      // 来源于订单和出库单的订单为退货，所以不能为正数。
      if (SOBillType.Order.getCode().equals(item.getVsrctype())
          || ICBillType.SaleOut.getCode().equals(item.getVsrctype())) {
        boolean discountflag = item.getBdiscountflag().booleanValue();
        // 非折扣类物料退货应该为负数
        if (!discountflag
            && MathTool.greaterThan(item.getNorigtaxmny(), new UFDouble(0))) {
          ExceptionUtils.wrappBusinessException(nc.vo.ml.NCLangRes4VoTransl
              .getNCLangRes().getStrByID("4006011_0", "04006011-0104")
                                                                       * @res
                                                                       * "销售模式为退货的销售订单只允许退货。"
                                                                       );
        }
        // 折扣类物料退货应该为整数
        if (discountflag
            && MathTool.lessThan(item.getNorigtaxmny(), new UFDouble(0))) {
          ExceptionUtils.wrappBusinessException(nc.vo.ml.NCLangRes4VoTransl
              .getNCLangRes().getStrByID("4006011_0", "04006011-0104")
                                                                       * @res
                                                                       * "销售模式为退货的销售订单只允许退货。"
                                                                       );
        }
      }*/

      if (fsalemode.equals(SaleMode.MODE_COMMON.value())) {
        if (isExistWithdraw || isExistExchange) {
          ExceptionUtils.wrappBusinessException(NCLangResOnserver.getInstance()
              .getStrByID("4006011_0", "04006011-0492")/*
                                                        * 销售模式为普通的销售订单不允许退换货（
                                                        * 非折扣类物料行不可以为负数
                                                        * ，折扣类物料行不能为正数）
                                                        */);
        }
      }
      else if (fsalemode.equals(SaleMode.MODE_RETURN.value())) {
        if (isExistExchange || isExistCommon) {

          ExceptionUtils.wrappBusinessException(nc.vo.ml.NCLangRes4VoTransl
              .getNCLangRes().getStrByID("4006011_0", "04006011-0104")/*
                                                                       * @res
                                                                       * "销售模式为退货的销售订单只允许退货。"
                                                                       */);
        }
      }
      else if (fsalemode.equals(SaleMode.MODE_RETURNCHANGE.value())) {
        if (isExistCommon) {
          // 如果不是折扣类物料，价税合计小于0，则不满足 add by quyt 20150318
          ExceptionUtils.wrappBusinessException(NCLangResOnserver.getInstance()
              .getStrByID(
                  "4006011_0",
                  "04006011-0493",
                  null,
                  new String[] {
                    nc.vo.ml.NCLangRes4VoTransl.getNCLangRes().getStrByID(
                        "4006011_0", "04006011-0105")
                  })/* {0}（非折扣类物料行不可以为正数，折扣类物料行不能为负数） */);
        }
      }
      else if (fsalemode.equals(SaleMode.MODE_COMMONRETURN.value())) {
        if (isExistExchange) {

          ExceptionUtils.wrappBusinessException(nc.vo.ml.NCLangRes4VoTransl
              .getNCLangRes().getStrByID("4006011_0", "04006011-0106")/*
                                                                       * @res
                                                                       * "销售模式为普通+退货的销售订单不允许换货。"
                                                                       */);
        }
      }
    }
  }

  private IM30TranTypeService getTranTypeService() {
    if (this.tranTypeService == null) {
      this.tranTypeService =
          NCLocator.getInstance().lookup(IM30TranTypeService.class);
    }
    return this.tranTypeService;
  }

}
