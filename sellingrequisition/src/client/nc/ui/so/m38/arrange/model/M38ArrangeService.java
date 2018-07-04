package nc.ui.so.m38.arrange.model;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;

import nc.bs.framework.common.NCLocator;
import nc.desktop.ui.WorkbenchEnvironment;
import nc.impl.pubapp.bill.convertor.BillToViewConvertor;
import nc.itf.pubapp.pub.exception.IResumeException;
import nc.itf.scmpub.reference.uap.bd.material.MaterialPubService;
import nc.itf.so.m38.arrange.IPreOrderArrange;
import nc.pubitf.credit.accountcheck.IAccountCheckMessageService;
import nc.pubitf.credit.creditcheck.ICreditCheckMessageService;
import nc.pubitf.so.m30.so.m38.IPushSave30For38Arrange;
import nc.pubitf.so.m38.so.m30.IPreOrderFor30;
import nc.ui.ml.NCLangRes;
import nc.ui.pub.beans.MessageDialog;
import nc.ui.pub.beans.UIDialog;
import nc.ui.pubapp.AppUiContext;
import nc.ui.pubapp.billref.push.IRewriteService;
import nc.ui.pubapp.billref.push.RewriteInfo;
import nc.ui.pubapp.pub.locator.NCUILocator;
import nc.ui.pubapp.pub.task.ISingleBillService;
import nc.ui.pubapp.uif2app.model.IQueryService;
import nc.vo.bd.material.MaterialVO;
import nc.vo.credit.exception.CreditCheckException;
import nc.vo.credit.exception.OverPeriodDayCheckException;
import nc.vo.credit.exception.OverPeriodInnerDayCheckException;
import nc.vo.credit.exception.OverPeriodMoneyCheckException;
import nc.vo.pub.BusinessException;
import nc.vo.pub.VOStatus;
import nc.vo.pub.lang.UFBoolean;
import nc.vo.pub.lang.UFDate;
import nc.vo.pub.lang.UFDouble;
import nc.vo.pubapp.pattern.exception.ExceptionUtils;
import nc.vo.pubapp.pattern.pub.MathTool;
import nc.vo.pubapp.pattern.pub.PubAppTool;
import nc.vo.pubapp.res.NCModule;
import nc.vo.scmpub.exp.AtpNotEnoughException;
import nc.vo.scmpub.res.BusinessCheck;
import nc.vo.scmpub.res.billtype.SOBillType;
import nc.vo.so.m30.entity.SaleOrderBVO;
import nc.vo.so.m30.entity.SaleOrderHVO;
import nc.vo.so.m30.entity.SaleOrderVO;
import nc.vo.so.m30.entity.SaleOrderViewVO;
import nc.vo.so.m38.entity.PreOrderBVO;
import nc.vo.so.m38.entity.PreOrderViewVO;
import nc.vo.so.pub.SOParameterVO;
import nc.vo.so.pub.exeception.PreOrderToleranceException;
import nc.vo.trade.checkrule.VOChecker;

public class M38ArrangeService implements IQueryService, IRewriteService,
    ISingleBillService<Object> {

  /**
   * 下游销售订单保存后调用：
   * <p>
   * 获得销售订单回写信息
   * </p>
   */
  @Override
  public RewriteInfo[] getRewriterInfo(Object[] bill) {
    if (VOChecker.isEmpty(bill)) {
      return null;
    }
    SaleOrderViewVO[] view = (SaleOrderViewVO[]) bill;
    int ilength = view.length;
    RewriteInfo[] rewinfos = new RewriteInfo[ilength];
    for (int i = 0; i < ilength; i++) {
      SaleOrderBVO body = view[i].getBody();
      rewinfos[i] = new RewriteInfo();
      rewinfos[i].setDestType(SOBillType.Order.getCode());
      rewinfos[i].setRewriteNum(body.getNnum());
      rewinfos[i].setSourceHeadId(body.getCsrcid());
      rewinfos[i].setSourceRowId(body.getCsrcbid());
      rewinfos[i].setSourceType(body.getVsrctype());

    }
    return rewinfos;
  }

  @Override
  public SaleOrderViewVO[] operateBill(Object obj) throws Exception {

    SOParameterVO paravo = this.getParavo(obj);

    List<SaleOrderViewVO> alclose = null;

    SaleOrderViewVO[] views = null;
    if (null != paravo) {

      alclose = (List<SaleOrderViewVO>) paravo.getUserObject();

      // 有待保存订单
      if (null != paravo.getVos() && paravo.getVos().length > 0) {
        boolean isContinue = true;

        while (isContinue) {
          // 保存单据
          try {
            SaleOrderVO[] rets = this.getService().pushSave30For38Arrange(paravo);
            BillToViewConvertor<SaleOrderVO, SaleOrderViewVO> convertor =
                new BillToViewConvertor<SaleOrderVO, SaleOrderViewVO>(
                    SaleOrderViewVO.class);
            views = convertor.convert(rets);
            isContinue = false;
          }
          catch (Exception exc) {
            Throwable ex = ExceptionUtils.unmarsh(exc);
            if (ex instanceof IResumeException) {
              if (this.isResume((IResumeException) ex, paravo)) {
                isContinue = true;
              }
              else {
                isContinue = false;
              }
            }
            else {
              ExceptionUtils.wrappException(exc);
            }
          }
        }
      }
    }
    if (null != alclose && alclose.size() > 0) {
      this.forceClosePreOrder(alclose);
      if (null != views) {
        for (SaleOrderViewVO view : views) {
          alclose.add(view);
        }
      }
      SaleOrderViewVO[] retviews = new SaleOrderViewVO[alclose.size()];
      alclose.toArray(retviews);
      return retviews;
    }
    return views;
  }

  private IPushSave30For38Arrange getService() {
    return NCLocator.getInstance().lookup(IPushSave30For38Arrange.class);
  }

  @Override
  public Object[] queryByWhereSql(String whereSql) throws Exception {

    PreOrderViewVO[] bills = null;
    IPreOrderArrange service =
        NCLocator.getInstance().lookup(IPreOrderArrange.class);
    try {
      bills = service.queryPreOrderViewVO(whereSql);
    }
    catch (Exception e) {

      ExceptionUtils.wrappException(e);
    }
    return bills;

  }

  /**
   * 下游销售订单保存后调用：
   * <p>
   * 回写前台预订单安排数量
   * </p>
   */
  @Override
  public void setRewriterNum(Object bill, UFDouble num, String bodyId) {
    PreOrderViewVO view = (PreOrderViewVO) bill;
    PreOrderBVO item = view.getItem();
    item.setNarrnum(MathTool.add(item.getNarrnum(), num));
  }

  private boolean checkChangeOrg(SaleOrderViewVO view) {
    String newpk_org = view.getBody().getPk_org();
    String srcpk_org = view.getBody().getSrcorgid();
    // 来源组织为空或者相同
    if (PubAppTool.isNull(srcpk_org)
        || PubAppTool.isEqual(newpk_org, srcpk_org)) {
      return true;
    }
    String marvid = view.getBody().getCmaterialvid();
    MaterialVO materialvo =
        MaterialPubService.queryMaterialBaseInfoByPk(marvid, new String[] {
          MaterialVO.PK_MATERIAL, MaterialVO.PK_ORG, MaterialVO.PK_GROUP
        });
    String pk_group = AppUiContext.getInstance().getPkGroup();
    if (newpk_org.equals(materialvo.getPk_org())
        || pk_group.equals(materialvo.getPk_org())) {
      return true;
    }
    return false;
  }

  private List<String> checkSaveBill(SaleOrderViewVO view) {

    List<String> errfield = new ArrayList<String>();

    String pk_org = view.getBody().getPk_org();
    if (PubAppTool.isNull(pk_org)) {
      errfield.add(NCLangRes.getInstance().getStrByID("4006012_0",
          "04006012-0075")/* 销售组织 */);
    }
    String trantypeid = view.getHead().getCtrantypeid();
    if (PubAppTool.isNull(trantypeid)) {
      errfield.add(NCLangRes.getInstance().getStrByID("4006012_0",
          "04006012-0076")/* 交易类型 */);
    }
    UFDate dbilldate = view.getBody().getDbilldate();
    if (null == dbilldate) {
      errfield.add(NCLangRes.getInstance().getStrByID("4006012_0",
          "04006012-0077")/* 单据日期 */);
    }
    String materialvid = view.getBody().getCmaterialvid();
    if (PubAppTool.isNull(materialvid)) {
      errfield.add(NCLangRes.getInstance().getStrByID("4006012_0",
          "04006012-0078")/* 物料编码 */);
    }
    String astunit = view.getBody().getCastunitid();
    if (PubAppTool.isNull(astunit)) {
      errfield.add(NCLangRes.getInstance().getStrByID("4006012_0",
          "04006012-0079")/* 单位 */);
    }
    UFDouble num = view.getBody().getNastnum();
    if (null == num) {
      errfield.add(NCLangRes.getInstance().getStrByID("4006012_0",
          "04006012-0080")/* 数量、 */);
    }
    UFDouble taxprice = view.getBody().getNqtorigtaxprice();
    if (null == taxprice) {
      errfield.add(NCLangRes.getInstance().getStrByID("4006012_0",
          "04006012-0081")/* 含税单价 */);
    }
    UFDouble taxnetprice = view.getBody().getNqtorigtaxnetprc();
    if (null == taxnetprice) {
      errfield.add(NCLangRes.getInstance().getStrByID("4006012_0",
          "04006012-0082")/* 含税净价 */);
    }

    UFDouble origmny = view.getBody().getNorigmny();
    if (null == origmny) {
      errfield.add(NCLangRes.getInstance().getStrByID("4006012_0",
          "04006012-0083")/* 无税金额 */);
    }
    UFDouble origtaxmny = view.getBody().getNorigtaxmny();
    if (null == origtaxmny) {
      errfield.add(NCLangRes.getInstance().getStrByID("4006012_0",
          "04006012-0084")/* 价税合计 */);
    }
    UFDate senddate = view.getBody().getDsenddate();
    if (null == senddate) {
      errfield.add(NCLangRes.getInstance().getStrByID("4006012_0",
          "04006012-0085")/* 计划发货日期 */);
    }
    UFDate receivedate = view.getBody().getDreceivedate();
    if (null == receivedate) {
      errfield.add(NCLangRes.getInstance().getStrByID("4006012_0",
          "04006012-0086")/* 要求收货日期 */);
    }
    String sendstockorg = view.getBody().getCsendstockorgvid();
    if (PubAppTool.isNull(sendstockorg)) {
      errfield.add(NCLangRes.getInstance().getStrByID("4006012_0",
          "04006012-0087")/* 发货库存组织 */);
    }
    String settleorg = view.getBody().getCsettleorgvid();
    if (PubAppTool.isNull(settleorg)) {
      errfield.add(NCLangRes.getInstance().getStrByID("4006012_0",
          "04006012-0088")/* 结算财务组织 */);
    }
    return errfield;
  }

  private void forceClosePreOrder(List<SaleOrderViewVO> alclose) {

    Set<String> setbids = new HashSet<String>();
    for (SaleOrderViewVO view : alclose) {
      setbids.add(view.getBody().getCsrcbid());
    }
    String[] bids = new String[setbids.size()];
    setbids.toArray(bids);

    IPreOrderFor30 service =
        NCLocator.getInstance().lookup(IPreOrderFor30.class);
    try {
      service.closeRowFor38Arrange(bids);
    }
    catch (BusinessException ex) {
      ExceptionUtils.wrappException(ex);
    }
  }

  private SOParameterVO getParavo(Object obj) {

    if (obj instanceof SOParameterVO) {
      return (SOParameterVO) obj;
    }

    SaleOrderViewVO[] views = (SaleOrderViewVO[]) obj;
    if (null == views || views.length == 0) {
      String msg =
          nc.vo.ml.NCLangRes4VoTransl.getNCLangRes().getStrByID("4006012_0",
              "04006012-0000")/* @res "请至少选择一行记录" */;
      ExceptionUtils.wrappBusinessException(msg);
      return null;
    }
    SOParameterVO paravo = new SOParameterVO();
    List<SaleOrderVO> listorder = new ArrayList<SaleOrderVO>();
    List<SaleOrderViewVO> alcloseview = new ArrayList<SaleOrderViewVO>();

    int i = 0;
    StringBuilder allerrmsg = new StringBuilder();
    for (SaleOrderViewVO view : views) {
      // 行索引
      i++;
      // 是否强制关闭
      UFBoolean forceclose = view.getBody().getBprerowcloseflag();
      // 订单数量
      UFDouble nordernum = view.getBody().getNnum();
      if (null != forceclose && forceclose.booleanValue()) {
        alcloseview.add(view);
        if (MathTool.isZero(nordernum)) {
          continue;
        }
      }
      // 合法性校验
      List<String> listerrfield = this.checkSaveBill(view);
      if (listerrfield.size() > 0) {
        allerrmsg.append(NCLangRes.getInstance().getStrByID("4006012_0",
            "04006012-0073", null, new String[] {
              String.valueOf(i)
            })/* 第{0}行下列字段不能为空(或0)： */);
        for (String field : listerrfield) {
          allerrmsg
              .append("[")
              .append(field)
              .append("]")
              .append(
                  NCLangRes.getInstance().getStrByID("4006012_0",
                      "04006012-0089")/* 、 */);
        }
        allerrmsg.deleteCharAt(allerrmsg.length() - 1);
        allerrmsg.append("\n");
        continue;
      }
      // 改变过组织，客户、物料分配关系校验
      if (!this.checkChangeOrg(view)) {
        allerrmsg.append(NCLangRes.getInstance().getStrByID("4006012_0",
            "04006012-0074", null, new String[] {
              String.valueOf(i)
            })/* 第{0}行物料在对应销售组织下不可见\n */);
        continue;
      }
      SaleOrderVO vo = new SaleOrderVO();
      // 视图VO主元数据为子表，界面冗余字段会向子表设值。需要将子表的值付给主表。
      SaleOrderHVO head = view.getHead();
      SaleOrderBVO body = view.getBody();
      head.setStatus(VOStatus.NEW);
      head.setPk_org(body.getPk_org());
      head.setPk_group(body.getPk_group());
      head.setDbilldate(body.getDbilldate());
      body.setStatus(VOStatus.NEW);
      vo.setParent(head);
      vo.setChildrenVO(new SaleOrderBVO[] {
        body
      });
      listorder.add(vo);
    }
    if (allerrmsg.length() > 0) {
      ExceptionUtils.wrappBusinessException(nc.vo.ml.NCLangRes4VoTransl
          .getNCLangRes().getStrByID("4006012_0", "04006012-0001")/* @res
                                                                   * "下列字段值不能为空：\n\r" */
          + allerrmsg.toString());
    }
    paravo.setUserObject(alcloseview);
    if (listorder.size() > 0) {
      SaleOrderVO[] ordervos = new SaleOrderVO[listorder.size()];
      listorder.toArray(ordervos);
      paravo.setVos(ordervos);
      paravo.setBusinessCheckMap(null);
    }
    return paravo;
  }

  private Set<String> getResumeExcType() {
    // 销售订单需要处理的前台异常类型
    Set<String> resumeExcType = new HashSet<String>();
    resumeExcType.add(BusinessCheck.ATPCheck.getCheckCode());
    resumeExcType.add(BusinessCheck.CreditCheck.getCheckCode());
    resumeExcType.add(BusinessCheck.CreditOverPeriodMoneyCheck.getCheckCode());
    resumeExcType.add(BusinessCheck.CreditOverPeriodDayCheck.getCheckCode());
    resumeExcType.add(BusinessCheck.CreditOverPeriodInnerDayCheck
        .getCheckCode());
    resumeExcType.add(BusinessCheck.PreOrderToleranceCheck.getCheckCode());
    resumeExcType.add(BusinessCheck.CtToleranceCheck.getCheckCode());

    return resumeExcType;
  }

  private boolean isResume(IResumeException resumeInfo, SOParameterVO paravo) {

    String exceptiontype = resumeInfo.getBusiExceptionType();
    if (PubAppTool.isNull(exceptiontype)
        || !this.getResumeExcType().contains(exceptiontype)) {
      ExceptionUtils.wrappException((Exception) resumeInfo);
    }

    // ATP检查
    boolean isATPResume = this.processATPCheck(resumeInfo, paravo);

    // 信用检查
    boolean isCCResume = this.processCreditCheck(resumeInfo, paravo);

    // 超账期金额检查
    boolean isCOPMCResume =
        this.processCreditOverPeriodMoneyCheck(resumeInfo, paravo);

    // 超账期天数检查
    boolean isCOPDCResume =
        this.processCreditOverPeriodDayCheck(resumeInfo, paravo);

    // 超内控账期天数检查
    boolean isCOPIDCResume =
        this.processCreditOverPeriodInnerDayCheck(resumeInfo, paravo);
    // 超预订单数量安排检查
    boolean is38ToleranceResume =
        this.process38ToleranceCheck(resumeInfo, paravo);

    return isCCResume && isCOPMCResume && isCOPDCResume && isCOPIDCResume
        && isATPResume && is38ToleranceResume;

  }

  private boolean process38ToleranceCheck(IResumeException resumeInfo,
      SOParameterVO paravo) {

    boolean isResume = true;
    int back = 0;
    if (BusinessCheck.PreOrderToleranceCheck.getCheckCode().equals(
        resumeInfo.getBusiExceptionType())) {
      back =
          MessageDialog
              .showYesNoDlg(
                  WorkbenchEnvironment.getInstance().getWorkbench().getParent(),
                  NCLangRes.getInstance().getStrByID("4006011_0",
                      "04006011-0243")/* 超预订单数量安排检查 */,
                  ((PreOrderToleranceException) resumeInfo).getMessage());

      if (UIDialog.ID_YES == back) {
        Map<String, Boolean> mapCheck = paravo.getBusinessCheckMap();
        mapCheck.put(BusinessCheck.PreOrderToleranceCheck.getCheckCode(),
            Boolean.FALSE);
      }
      else {
        isResume = false;
      }
    }
    return isResume;

  }

  private boolean processATPCheck(IResumeException resumeInfo,
      SOParameterVO paravo) {

    boolean isResume = true;
    int back = 0;
    if (BusinessCheck.ATPCheck.getCheckCode().equals(
        resumeInfo.getBusiExceptionType())) {
      back =
          MessageDialog
              .showYesNoDlg(
                  WorkbenchEnvironment.getInstance().getWorkbench().getParent(),
                  NCLangRes.getInstance().getStrByID("4006011_0",
                      "04006011-0244")/* 销售订单可用量检查 */,
                  ((AtpNotEnoughException) resumeInfo).getMessage());

      // 可用量不足继续
      if (UIDialog.ID_YES == back) {
        isResume = true;
        Map<String, Boolean> mapCheck = paravo.getBusinessCheckMap();
        mapCheck.put(BusinessCheck.ATPCheck.getCheckCode(), !isResume);
      }
      else {
        isResume = false;
      }

    }
    return isResume;
  }

  private boolean processCreditCheck(IResumeException resumeInfo,
      SOParameterVO paravo) {

    boolean isResume = true;
    // 信用检查
    if (BusinessCheck.CreditCheck.getCheckCode().equals(
        resumeInfo.getBusiExceptionType())) {
      ICreditCheckMessageService service =
          NCUILocator.getInstance().lookup(ICreditCheckMessageService.class,
              NCModule.CREDIT);
      try {
        isResume =
            service.showMessage(WorkbenchEnvironment.getInstance()
                .getWorkbench().getParent(), (CreditCheckException) resumeInfo);
      }
      catch (BusinessException e) {
        ExceptionUtils.wrappException(e);
      }

      // 继续动作时，不做信用检查
      Map<String, Boolean> mapCheck = paravo.getBusinessCheckMap();
      mapCheck.put(BusinessCheck.CreditCheck.getCheckCode(), !isResume);
    }
    return isResume;

  }

  private boolean processCreditOverPeriodDayCheck(IResumeException resumeInfo,
      SOParameterVO paravo) {

    boolean isResume = true;
    if (BusinessCheck.CreditOverPeriodDayCheck.getCheckCode().equals(
        resumeInfo.getBusiExceptionType())) {
      IAccountCheckMessageService service =
          NCUILocator.getInstance().lookup(IAccountCheckMessageService.class,
              NCModule.CREDIT);
      try {
        isResume =
            service.showMessage(WorkbenchEnvironment.getInstance()
                .getWorkbench().getParent(),
                ((OverPeriodDayCheckException) resumeInfo).getHintMessage());
      }
      catch (BusinessException e) {
        ExceptionUtils.wrappException(e);
      }

      // 继续动作时，不做检查
      Map<String, Boolean> mapCheck = paravo.getBusinessCheckMap();
      mapCheck.put(BusinessCheck.CreditOverPeriodDayCheck.getCheckCode(),
          !isResume);
    }
    return isResume;

  }

  private boolean processCreditOverPeriodInnerDayCheck(
      IResumeException resumeInfo, SOParameterVO paravo) {

    boolean isResume = true;
    if (BusinessCheck.CreditOverPeriodInnerDayCheck.getCheckCode().equals(
        resumeInfo.getBusiExceptionType())) {
      IAccountCheckMessageService service =
          NCUILocator.getInstance().lookup(IAccountCheckMessageService.class,
              NCModule.CREDIT);
      try {
        isResume =
            service.showMessage(WorkbenchEnvironment.getInstance()
                .getWorkbench().getParent(),
                ((OverPeriodInnerDayCheckException) resumeInfo)
                    .getHintMessage());
      }
      catch (BusinessException e) {
        ExceptionUtils.wrappException(e);
      }

      // 继续动作时，不做检查
      Map<String, Boolean> mapCheck = paravo.getBusinessCheckMap();
      mapCheck.put(BusinessCheck.CreditOverPeriodInnerDayCheck.getCheckCode(),
          !isResume);
    }
    return isResume;

  }

  private boolean processCreditOverPeriodMoneyCheck(
      IResumeException resumeInfo, SOParameterVO paravo) {

    boolean isResume = true;
    if (BusinessCheck.CreditOverPeriodMoneyCheck.getCheckCode().equals(
        resumeInfo.getBusiExceptionType())) {
      IAccountCheckMessageService service =
          NCUILocator.getInstance().lookup(IAccountCheckMessageService.class,
              NCModule.CREDIT);
      try {
        isResume =
            service.showMessage(WorkbenchEnvironment.getInstance()
                .getWorkbench().getParent(),
                ((OverPeriodMoneyCheckException) resumeInfo).getHintMessage());
      }
      catch (BusinessException e) {
        ExceptionUtils.wrappException(e);
      }

      // 继续动作时，不做检查
      Map<String, Boolean> mapCheck = paravo.getBusinessCheckMap();
      mapCheck.put(BusinessCheck.CreditOverPeriodMoneyCheck.getCheckCode(),
          !isResume);
    }
    return isResume;

  }
}
