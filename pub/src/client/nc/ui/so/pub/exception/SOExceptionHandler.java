package nc.ui.so.pub.exception;

import java.util.HashMap;
import java.util.Map;

import nc.vo.credit.exception.CreditCheckException;
import nc.vo.credit.exception.OverPeriodDayCheckException;
import nc.vo.credit.exception.OverPeriodInnerDayCheckException;
import nc.vo.credit.exception.OverPeriodMoneyCheckException;
import nc.vo.pub.BusinessException;
import nc.vo.pubapp.pattern.exception.ExceptionUtils;
import nc.vo.pubapp.res.NCModule;
import nc.vo.scmpub.exp.AtpNotEnoughException;
import nc.vo.scmpub.res.BusinessCheck;
import nc.vo.so.m30.entity.SaleOrderBVO;
import nc.vo.so.m30.entity.SaleOrderHVO;
import nc.vo.so.m30.entity.SaleOrderVO;
import nc.vo.so.m30.entity.SaleOrderViewVO;
import nc.vo.so.m4331.entity.DeliveryBVO;
import nc.vo.so.m4331.entity.DeliveryHVO;
import nc.vo.so.m4331.entity.DeliveryVO;
import nc.vo.so.m4331.entity.DeliveryViewVO;
import nc.vo.so.pub.SOParameterVO;
import nc.vo.so.pub.exeception.OrderToleranceException;
import nc.vo.so.pub.exeception.PreOrderToleranceException;
import nc.vo.to.pub.exception.M5xDeliToleranceException;

import nc.pubitf.credit.accountcheck.IAccountCheckMessageService;
import nc.pubitf.credit.creditcheck.ICreditCheckMessageService;

import nc.desktop.ui.WorkbenchEnvironment;

import nc.ui.ml.NCLangRes;
import nc.ui.pub.beans.MessageDialog;
import nc.ui.pub.beans.UIDialog;
import nc.ui.pubapp.billref.push.BillContext;
import nc.ui.pubapp.billref.push.ExAppEventConst;
import nc.ui.pubapp.billref.push.IBillPush;
import nc.ui.pubapp.billref.push.PushBillEvent;
import nc.ui.pubapp.billref.push.RewriteInfo;
import nc.ui.pubapp.pub.locator.NCUILocator;
import nc.ui.pubapp.pub.task.ISingleBillService;
import nc.ui.pubapp.uif2app.DefaultExceptionHanler;
import nc.ui.uif2.IExceptionHandler;

public class SOExceptionHandler implements IExceptionHandler {
  private IBillPush billPush;

  private Map<String, Boolean> businessCheckMap =
      new HashMap<String, Boolean>();

  private ISingleBillService<Object> service;

  public IBillPush getBillPush() {
    return this.billPush;
  }

  private Object getDatasFor30(SaleOrderViewVO[] views) {
    SaleOrderVO[] vos = new SaleOrderVO[views.length];
    for (int i = 0; i < vos.length; i++) {
      vos[i] = new SaleOrderVO();
      // 视图VO主元数据为子表，界面冗余字段会向子表设值。需要将子表的值付给主表。
      SaleOrderHVO head = views[i].getHead();
      SaleOrderBVO body = views[i].getBody();
      head.setPk_org(body.getPk_org());
      head.setPk_group(body.getPk_group());
      head.setDbilldate(body.getDbilldate());
      vos[i].setParent(head);
      vos[i].setChildrenVO(new SaleOrderBVO[] {
        body
      });
    }
    SOParameterVO paravo = new SOParameterVO();
    paravo.setBusinessCheckMap(this.businessCheckMap);
    paravo.setVos(vos);
    return paravo;
  }

  private Object getDatasFor4331(DeliveryViewVO[] views) {
    DeliveryVO[] vos = new DeliveryVO[views.length];
    for (int i = 0; i < vos.length; i++) {
      vos[i] = new DeliveryVO();
      // 视图VO主元数据为子表，界面冗余字段会向子表设值。需要将子表的值付给主表。
      DeliveryHVO head = views[i].getHead();
      DeliveryBVO body = views[i].getItem();
      head.setPk_org(body.getPk_org());
      head.setDbilldate(body.getDbilldate());
      head.setPk_group(body.getPk_group());
      vos[i].setParent(head);
      vos[i].setChildrenVO(new DeliveryBVO[] {
        body
      });
    }
    SOParameterVO para = new SOParameterVO();
    para.setVos(vos);
    para.setBusinessCheckMap(this.businessCheckMap);
    return para;
  }

  private boolean getIsOn(Exception e) {
    boolean expr1 = e.getCause() instanceof AtpNotEnoughException;
    boolean expr2 = e.getCause() instanceof CreditCheckException;
    boolean expr3 = e.getCause() instanceof OverPeriodDayCheckException;
    boolean expr4 = e.getCause() instanceof OverPeriodInnerDayCheckException;
    boolean expr5 = e.getCause() instanceof OverPeriodMoneyCheckException;
    boolean expr6 = e.getCause() instanceof OrderToleranceException;
    boolean expr7 = e.getCause() instanceof M5xDeliToleranceException;
    boolean expr8 = e.getCause() instanceof PreOrderToleranceException;
    boolean expr =
        expr1 || expr2 || expr3 || expr4 || expr5 || expr6 || expr7 || expr8;
    if (expr) {
      return true;
    }
    return false;
  }

  public ISingleBillService<Object> getService() {
    return this.service;
  }

  private boolean handlerCheckException(Exception ex) {
    boolean expr1 = this.processATPCheck(ex);
    boolean expr2 = this.processCreditCheck(ex);
    boolean expr3 = this.processCreditOverPeriodDayCheck(ex);
    boolean expr4 = this.processCreditOverPeriodInnerDayCheck(ex);
    boolean expr5 = this.processCreditOverPeriodMoneyCheck(ex);
    boolean expr6 = this.processToleranceCheck(ex);
    boolean expr7 = this.processTranOrderCheck(ex);
    boolean expr8 = this.process38ToleranceCheck(ex);
    boolean expr =
        expr1 && expr2 && expr3 && expr4 && expr5 && expr6 && expr7 && expr8;
    return expr;
  }

  @Override
  public void handlerExeption(Exception ex) {
    boolean flag = this.getIsOn(ex);
    if (!flag) {
      this.handlerOtherException(ex);
      return;
    }
    boolean ison = this.getIsOn(ex);
    while (ison) {
      ison = false;
      boolean expr = this.handlerCheckException(ex);
      if (!expr) {
        return;
      }
      BillContext context = this.getBillPush().getBillContext();
      Object object = context.getBillTabPanel().getBodySelectVOs();
      Object datas = null;
      if (object instanceof SaleOrderViewVO[]) {
        datas = this.getDatasFor30((SaleOrderViewVO[]) object);
      }
      if (object instanceof DeliveryViewVO[]) {
        datas = this.getDatasFor4331((DeliveryViewVO[]) object);
      }
      try {
        datas = this.getService().operateBill(datas);
        context.getModel().deleteSelectData();
        PushBillEvent rewirteEv =
            new PushBillEvent(ExAppEventConst.BILL_PUSH_NUMREWRITE, this, null);

        RewriteInfo[] rewriteInfo =
            context.getTabBillInfo().getRewriteService()
                .getRewriterInfo((Object[]) datas);
        rewirteEv.addRewriteInfo(rewriteInfo);
        if (rewirteEv.shouldBeRewrite()) {
          context.getModel().fireEvent(rewirteEv);
        }
      }
      catch (Exception e) {
        ison = true;
      }
    }
  }

  private void handlerOtherException(Exception ex) {
    DefaultExceptionHanler handler =
        new DefaultExceptionHanler(this.billPush.getBillContext()
            .getBillTabPanel());
    handler.setContext(this.billPush.getBillContext().getTabBillInfo()
        .getLoginContext());
    handler.handlerExeption(ex);
  }

  private boolean process38ToleranceCheck(Exception e) {
    Throwable throwable = ExceptionUtils.unmarsh(e);
    boolean isResume = true;
    if (throwable instanceof PreOrderToleranceException) {
      int back =
          MessageDialog
              .showYesNoDlg(
                  WorkbenchEnvironment.getInstance().getWorkbench().getParent(),
                  NCLangRes.getInstance().getStrByID("4006004_0",
                      "04006004-0014")/*超预订单数量安排检查*/, throwable.getMessage());

      if (UIDialog.ID_YES == back) {
        isResume = true;
        this.businessCheckMap.put(
            BusinessCheck.PreOrderToleranceCheck.getCheckCode(), Boolean.FALSE);
      }
      else {
        isResume = false;
      }
    }
    return isResume;
  }

  private boolean processATPCheck(Exception e) {
    Throwable throwable = ExceptionUtils.unmarsh(e);
    boolean isResume = true;
    if (throwable instanceof AtpNotEnoughException) {
      int back = 0;
      back =
          MessageDialog
              .showYesNoDlg(
                  WorkbenchEnvironment.getInstance().getWorkbench().getParent(),
                  NCLangRes.getInstance().getStrByID("4006004_0",
                      "04006004-0015")/*可用量检查*/, throwable.getMessage());
      // 可用量不足继续
      if (UIDialog.ID_YES == back) {
        isResume = true;
        this.businessCheckMap.put(BusinessCheck.ATPCheck.getCheckCode(),
            Boolean.FALSE);
      }
      else {
        isResume = false;
      }
    }
    return isResume;
  }

  private boolean processCreditCheck(Exception e) {
    Throwable throwable = ExceptionUtils.unmarsh(e);
    boolean isResume = true;
    if (throwable instanceof CreditCheckException) {

      ICreditCheckMessageService cservice =
          NCUILocator.getInstance().lookup(ICreditCheckMessageService.class,
              NCModule.CREDIT);
      try {
        isResume =
            cservice.showMessage(WorkbenchEnvironment.getInstance()
                .getWorkbench().getParent(), (CreditCheckException) throwable);
        if (isResume) {
          this.businessCheckMap.put(BusinessCheck.CreditCheck.getCheckCode(),
              Boolean.FALSE);
        }
      }
      catch (BusinessException e1) {
        ExceptionUtils.wrappException(e1);
      }
    }
    return isResume;
  }

  /**
   * 超账期天数检查
   * 
   * @param resumeInfo
   * @return
   */
  private boolean processCreditOverPeriodDayCheck(Exception e) {
    Throwable throwable = ExceptionUtils.unmarsh(e);
    boolean isResume = true;
    if (throwable instanceof OverPeriodDayCheckException) {
      IAccountCheckMessageService cservice =
          NCUILocator.getInstance().lookup(IAccountCheckMessageService.class,
              NCModule.CREDIT);
      try {
        isResume =
            cservice.showMessage(WorkbenchEnvironment.getInstance()
                .getWorkbench().getParent(),
                ((OverPeriodDayCheckException) throwable).getHintMessage());
      }
      catch (BusinessException e1) {
        ExceptionUtils.wrappException(e1);
      }
      if (isResume) {
        String busiCheckKey =
            BusinessCheck.CreditOverPeriodDayCheck.getCheckCode();
        this.businessCheckMap.put(busiCheckKey, Boolean.FALSE);
      }
    }
    return isResume;
  }

  /**
   * 超内控账期天数检查
   * 
   * @param resumeInfo
   * @return
   */
  private boolean processCreditOverPeriodInnerDayCheck(Exception e) {
    Throwable throwable = ExceptionUtils.unmarsh(e);
    boolean isResume = true;
    if (throwable instanceof OverPeriodInnerDayCheckException) {
      IAccountCheckMessageService cservice =
          NCUILocator.getInstance().lookup(IAccountCheckMessageService.class,
              NCModule.CREDIT);
      try {
        isResume =
            cservice
                .showMessage(WorkbenchEnvironment.getInstance().getWorkbench()
                    .getParent(),
                    ((OverPeriodInnerDayCheckException) throwable)
                        .getHintMessage());
      }
      catch (BusinessException e1) {
        ExceptionUtils.wrappException(e1);
      }
      if (isResume) {
        String busiCheckKey =
            BusinessCheck.CreditOverPeriodInnerDayCheck.getCheckCode();
        this.businessCheckMap.put(busiCheckKey, Boolean.FALSE);
      }
    }
    return isResume;
  }

  /**
   * 超账期金额检查
   */
  private boolean processCreditOverPeriodMoneyCheck(Exception e) {
    Throwable throwable = ExceptionUtils.unmarsh(e);
    boolean isResume = true;
    if (throwable instanceof OverPeriodMoneyCheckException) {
      IAccountCheckMessageService cservice =
          NCUILocator.getInstance().lookup(IAccountCheckMessageService.class,
              NCModule.CREDIT);
      try {
        isResume =
            cservice.showMessage(WorkbenchEnvironment.getInstance()
                .getWorkbench().getParent(),
                ((OverPeriodMoneyCheckException) throwable).getHintMessage());
      }
      catch (BusinessException e1) {
        ExceptionUtils.wrappException(e1);
      }
      if (isResume) {
        String busiCheckKey =
            BusinessCheck.CreditOverPeriodMoneyCheck.getCheckCode();
        this.businessCheckMap.put(busiCheckKey, Boolean.FALSE);
      }
    }
    return isResume;
  }

  private boolean processToleranceCheck(Exception e) {
    Throwable throwable = ExceptionUtils.unmarsh(e);
    boolean isResume = true;
    if (throwable instanceof OrderToleranceException) {
      int back =
          MessageDialog
              .showYesNoDlg(WorkbenchEnvironment.getInstance().getWorkbench()
                  .getParent(), NCLangRes.getInstance().getStrByID("4006004_0",
                  "04006004-0222")/*发货单超订单发货检查*/, throwable.getMessage());

      if (UIDialog.ID_YES == back) {
        isResume = true;
        String busiCheckKey = BusinessCheck.OrderToleranceCheck.getCheckCode();
        this.businessCheckMap.put(busiCheckKey, Boolean.FALSE);
      }
      else {
        isResume = false;
      }
    }
    return isResume;
  }

  private boolean processTranOrderCheck(Exception e) {
    Throwable throwable = ExceptionUtils.unmarsh(e);
    boolean isResume = true;
    if (throwable instanceof M5xDeliToleranceException) {
      int back =
          MessageDialog
              .showYesNoDlg(
                  WorkbenchEnvironment.getInstance().getWorkbench().getParent(),
                  NCLangRes.getInstance().getStrByID("4006004_0",
                      "04006004-0017")/*发货单超调拨订单发货检查*/, throwable.getMessage());

      if (UIDialog.ID_YES == back) {
        isResume = true;
        String busiCheckKey =
            BusinessCheck.TransDeliToleranceCheck.getCheckCode();
        this.businessCheckMap.put(busiCheckKey, Boolean.FALSE);
      }
      else {
        isResume = false;
      }
    }
    return isResume;
  }

  public void setBillPush(IBillPush billPush) {
    this.billPush = billPush;
  }

  public void setService(ISingleBillService<Object> service) {
    this.service = service;
  }
}
