package nc.pubitf.so.m33.uap;

import nc.bs.framework.common.InvocationInfoProxy;
import nc.bs.pub.pf.ISaveAfterMsgDrive;
import nc.itf.scmpub.reference.uap.pf.PFConfig;
import nc.itf.scmpub.reference.uap.pf.PfServiceScmUtil;
import nc.vo.pub.BusinessException;
import nc.vo.pub.pfflow01.BillbusinessVO;
import nc.vo.pub.pfflow04.MessagedriveVO;
import nc.vo.pubapp.AppContext;
import nc.vo.pubapp.pattern.exception.ExceptionUtils;
import nc.vo.scmpub.res.billaction.SOBillAction;
import nc.vo.scmpub.res.billtype.ICBillType;
import nc.vo.scmpub.res.billtype.SOBillType;
import nc.vo.trade.checkrule.VOChecker;

/**
 * <p>
 * <b>业务流程中销售发票消息驱动配置校验</b>
 * <p>
 * 
 * @version 本版本号
 * @since 上一版本号
 * @author zhangcheng
 * @time 2010-9-14 下午04:02:04
 */
public class SO32BizCheck implements ISaveAfterMsgDrive {

  private boolean asdjustIncome32;

  private boolean autoCost32;

  private boolean autoCost4C;

  private boolean autoEstimate4C;

  private boolean autoIncome32;

  private boolean autoIncome4C;

  private boolean autoResigeter4C;

  // 先票后货
  private boolean invFirst;

  // 业务流程中销售发票的交易类型，为空的话就取单据类型
  private String invoiceTransType;

  // 货票并行
  private boolean invOutParallel;

  private boolean manualCost4C;

  private boolean manualIncome4C;

  // 只有发票
  private boolean onlyInv;

  // 只有出库单
  private boolean onlyOut;

  // 先货后票
  private boolean outFirst;

  // 业务流程中销售出库单的交易类型，为空的话就取单据类型
  private String outTransType;

  @Override
  public void affectBusiForMsgDrive(MessagedriveVO mvo)
      throws BusinessException {
    // 销售发票审批动作、销售出库单签字配置消息驱动
    if (SOBillAction.SaleInvoiceApprove.getCode().equals(mvo.getSourceaction())
        || SOBillAction.SaleOutSIGN.getCode().equals(mvo.getSourceaction())) {

      // 初始化业务流程中销售出库单、销售发票的交易类型
      this.initOutInvoiceType(mvo);

      // 初始化先票后货、先货后票、票货并行标志
      this.initBizFlag(mvo);

      // 初始化销售发票、销售出库单审批的消息驱动
      this.initAction(mvo);

      try {
        // 业务校验
        this.check();
      }
      catch (Exception e) {
        ExceptionUtils.marsh(e);
      }

    }
  }

  private void check() {
    if (this.outFirst) {
      this.checkoutFirst();
    }
    else if (this.invFirst) {
      this.checkinvFirst();
    }
    else if (this.invOutParallel) {
      this.checkinvOutParallel();
    }
    else if (this.onlyInv) {
      this.checkonlyInv();
    }
    else if (this.onlyOut) {
      this.checkonlyOut();
    }
  }

  private void checkinvFirst() {
    String msg = null;
    if (this.autoEstimate4C) {
      msg =
          nc.vo.ml.NCLangRes4VoTransl.getNCLangRes().getStrByID("4006010_0",
              "04006010-0067")/*@res "先票后货业务销售出库单不能配置自动暂估应收!"*/;
    }
    else if (this.autoResigeter4C) {
      msg =
          nc.vo.ml.NCLangRes4VoTransl.getNCLangRes().getStrByID("4006010_0",
              "04006010-0068")/*@res "先票后货业务销售出库单不能配置自动计入发出商品!"*/;
    }

    if (!VOChecker.isEmpty(msg)) {
      ExceptionUtils.wrappBusinessException(msg);
    }

    this.checkonlyInv();
    this.checkonlyOut();
    this.checkpub();
  }

  private void checkinvOutParallel() {
    String msg = null;
    if (this.autoEstimate4C) {
      msg =
          nc.vo.ml.NCLangRes4VoTransl.getNCLangRes().getStrByID("4006010_0",
              "04006010-0069")/*@res "货票并行业务销售出库单不能配置自动暂估应收!"*/;
    }
    else if (this.autoResigeter4C) {
      msg =
          nc.vo.ml.NCLangRes4VoTransl.getNCLangRes().getStrByID("4006010_0",
              "04006010-0070")/*@res "货票并行业务销售出库单不能配置自动计入发出商品!"*/;
    }

    if (!VOChecker.isEmpty(msg)) {
      ExceptionUtils.wrappBusinessException(msg);
    }

    this.checkonlyInv();
    this.checkonlyOut();
    this.checkpub();
  }

  private void checkonlyInv() {
    String msg = null;
    if (this.autoIncome32 && this.asdjustIncome32) {
      msg =
          nc.vo.ml.NCLangRes4VoTransl.getNCLangRes().getStrByID("4006010_0",
              "04006010-0071")/*@res "销售发票不能同时配置自动应收结算和差额传应收!"*/;
    }
    if (!VOChecker.isEmpty(msg)) {
      ExceptionUtils.wrappBusinessException(msg);
    }
  }

  private void checkonlyOut() {
    String msg = null;
    if (this.autoCost4C && this.autoResigeter4C) {
      msg =
          nc.vo.ml.NCLangRes4VoTransl.getNCLangRes().getStrByID("4006010_0",
              "04006010-0072")/*@res "销售出库单不能同时配置自动成本结算和自动计入发出商品!"*/;
    }
    else if (this.autoIncome4C && this.autoEstimate4C) {
      msg =
          nc.vo.ml.NCLangRes4VoTransl.getNCLangRes().getStrByID("4006010_0",
              "04006010-0073")/*@res "销售出库单不能同时配置自动应收结算和自动暂估应收!"*/;
    }
    else if (this.autoIncome4C && this.manualIncome4C) {
      msg =
          nc.vo.ml.NCLangRes4VoTransl.getNCLangRes().getStrByID("4006010_0",
              "04006010-0074")/*@res "销售出库单不能同时配置自动应收结算和手工应收结算!"*/;
    }
    else if (this.autoResigeter4C && this.manualCost4C) {
      msg =
          nc.vo.ml.NCLangRes4VoTransl.getNCLangRes().getStrByID("4006010_0",
              "04006010-0075")/*@res "销售出库单不能同时配置自动计入发出商品和手工成本结算!"*/;
    }
    else if (this.manualCost4C && this.autoCost4C) {
      msg =
          nc.vo.ml.NCLangRes4VoTransl.getNCLangRes().getStrByID("4006010_0",
              "04006010-0076")/*@res "销售出库单不能同时配置自动成本结算和手工成本结算!"*/;
    }

    if (!VOChecker.isEmpty(msg)) {
      ExceptionUtils.wrappBusinessException(msg);
    }
  }

  private void checkoutFirst() {

    this.checkonlyInv();
    this.checkonlyOut();
    this.checkpub();
  }

  private void checkpub() {
    String msg = null;
    if (this.autoIncome4C && this.autoIncome32) {
      msg =
          nc.vo.ml.NCLangRes4VoTransl.getNCLangRes().getStrByID("4006010_0",
              "04006010-0077")/*@res "销售出库单和销售发票不能同时配置自动应收结算!"*/;
    }
    else if (this.autoCost4C && this.autoCost32) {
      msg =
          nc.vo.ml.NCLangRes4VoTransl.getNCLangRes().getStrByID("4006010_0",
              "04006010-0078")/*@res "销售出库单和销售发票不能同时配置自动成本结算!"*/;
    }
    else if (this.manualCost4C && this.autoCost32) {
      msg =
          nc.vo.ml.NCLangRes4VoTransl.getNCLangRes().getStrByID("4006010_0",
              "04006010-0079")/*@res "销售出库单为手工成本结算时和销售发票不能同时配置自动成本结算!"*/;
    }
    else if (this.manualIncome4C && this.autoIncome32) {
      msg =
          nc.vo.ml.NCLangRes4VoTransl.getNCLangRes().getStrByID("4006010_0",
              "04006010-0080")/*@res "销售出库单为手工应收结算时和销售发票不能同时配置自动应收结算!"*/;
    }
    else if (this.manualIncome4C && this.asdjustIncome32) {
      msg =
          nc.vo.ml.NCLangRes4VoTransl.getNCLangRes().getStrByID("4006010_0",
              "04006010-0140")/*@res "差额传应收不支持销售出库单手工收入结算!"*/;
    }

    if (!VOChecker.isEmpty(msg)) {
      ExceptionUtils.wrappBusinessException(msg);
    }
  }

  private void init32Action(MessagedriveVO mvo) {
    MessagedriveVO[] md32vos =
        PfServiceScmUtil.queryAllMsgdrvVOs(this.invoiceTransType,
            mvo.getPk_sourcebusinesstype(),
            SOBillAction.SaleInvoiceApprove.getCode());

    if (VOChecker.isEmpty(md32vos)) {
      return;
    }

    for (MessagedriveVO mdvo : md32vos) {
      String actiontype = mdvo.getActiontype();
      if (!VOChecker.isEmpty(actiontype)) {
        if (SOBillAction.SaleInvoiceSQUAREINCOME.getCode().equals(actiontype)) {
          this.autoIncome32 = true;
        }
        else if (SOBillAction.SaleInvoiceSQUARECOST.getCode()
            .equals(actiontype)) {
          this.autoCost32 = true;
        }
        else if (SOBillAction.SaleInvoiceADJUSTINCOME.getCode().equals(
            actiontype)) {
          this.asdjustIncome32 = true;
        }
      }
    }
  }

  private void init4CAction(MessagedriveVO mvo) {
    MessagedriveVO[] md4Cvos =
        PfServiceScmUtil.queryAllMsgdrvVOs(this.outTransType,
            mvo.getPk_sourcebusinesstype(), SOBillAction.SaleOutSIGN.getCode());

    if (VOChecker.isEmpty(md4Cvos)) {
      return;
    }

    for (MessagedriveVO mdvo : md4Cvos) {
      String actiontype = mdvo.getActiontype();
      if (!VOChecker.isEmpty(actiontype)) {
        if (SOBillAction.SaleOutAutoAR.getCode().equals(actiontype)) {
          this.autoIncome4C = true;
        }
        else if (SOBillAction.SaleOutAutoCost.getCode().equals(actiontype)) {
          this.autoCost4C = true;
        }
        else if (SOBillAction.SaleOutAutoEsti.getCode().equals(actiontype)) {
          this.autoEstimate4C = true;
        }
        else if (SOBillAction.SaleOutAutoRegister.getCode().equals(actiontype)) {
          this.autoResigeter4C = true;
        }
        else if (SOBillAction.SaleOutManualAR.getCode().equals(actiontype)) {
          this.manualIncome4C = true;
        }
        else if (SOBillAction.SaleOutManualCost.getCode().equals(actiontype)) {
          this.manualCost4C = true;
        }
      }
    }
  }

  private void initAction(MessagedriveVO mvo) {
    if (this.outFirst || this.invFirst || this.invOutParallel) {
      this.init32Action(mvo);
      this.init4CAction(mvo);
    }
    else if (this.onlyInv) {
      this.init32Action(mvo);
    }
    else if (this.onlyOut) {
      this.init4CAction(mvo);
    }
  }

  /**
   * 方法功能描述：初始化先票后货、先货后票、票货并行标志
   * <p>
   * <b>examples:</b>
   * <p>
   * 使用示例
   * <p>
   * <b>参数说明</b>
   * <p>
   * 
   * @author zhangcheng
   * @time 2010-9-14 下午04:46:36
   */
  private void initBizFlag(MessagedriveVO mvo) {
    
    String curBillType =
        PfServiceScmUtil.getBillTypeByTransType(mvo.getPk_sourcebilltype());
    // jilu for 恒安项目 20140904 业务流程配置了先票后货，出库单配置暂估应收，订单配置了推出库单，结果报错
    // 如果当前单据类型为30，则不需要判断是先票后货、现货后票还是货票同行，只有当前单据类型为32或者4c是判断才有用
    if (SOBillType.Order.getCode().equals(curBillType)) {
      return;
    }

    // 来源
    BillbusinessVO[] beforebinessVos =
        PfServiceScmUtil.querybillSource(InvocationInfoProxy.getInstance()
            .getGroupId(), mvo.getPk_sourcebilltype(), mvo
            .getPk_sourcebusinesstype(), true);

    for (BillbusinessVO bvo : beforebinessVos) {
      // 先货后票
      if (SOBillType.Invoice.getCode().equals(curBillType)
          && ICBillType.SaleOut.getCode().equals(bvo.getPk_billtype())) {
        this.outFirst = true;
        break;
      }
      // modify by zhangby5 合并项目补丁 NCdp205454502 begin
      // 先票后货
      if (ICBillType.SaleOut.getCode().equals(curBillType)
          && SOBillType.Invoice.getCode().equals(bvo.getPk_billtype())) {
        this.invFirst = true;
        break;
      }
    }

    // 目的
    BillbusinessVO[] afterbinessVos =
        PfServiceScmUtil.queryBillDest(mvo.getPk_sourcebilltype(),
            mvo.getPk_sourcebusinesstype());

    for (BillbusinessVO bvo : afterbinessVos) {
      // 先票后货
      if (SOBillType.Invoice.getCode().equals(curBillType)
          && ICBillType.SaleOut.getCode().equals(bvo.getPk_billtype())) {
        this.invFirst = true;
        break;
      }
      // 先货后票
      if (ICBillType.SaleOut.getCode().equals(curBillType)
          && SOBillType.Invoice.getCode().equals(bvo.getPk_billtype())) {
        this.outFirst = true;
        break;
      }
    }

    // 货票并行或者其他
    if (!this.outFirst && !this.invFirst) {
      BillbusinessVO[] bvos =
          PFConfig.findBillbusinessVOs(AppContext.getInstance().getPkGroup(),
              mvo.getPk_sourcebusinesstype());

      for (BillbusinessVO bvo : bvos) {
    	//begin-ncm-chengqiang5-解决业务流配置发货单推出库单报错的问题
    	  // 目的
          BillbusinessVO[] afterbinessVoss =
              PfServiceScmUtil.queryBillDest(bvo.getPk_billtype(),
                  bvo.getPk_businesstype());

          if (SOBillType.Invoice.getCode().equals(bvo.getPk_billtype())) {
              //如果发票下游没有目的单据，则判断只有发票
        	  if (afterbinessVoss.length == 0) {
            	  this.onlyInv = true;
              } else {
            	  for (BillbusinessVO abvo : afterbinessVoss) {
            		  //如果发票的下游是出库单，则判断为先票后货
            		  if (ICBillType.SaleOut.getCode().equals(abvo.getPk_billtype())) {
            			  this.invFirst = true;
            			  break;
            		  }
            	  }
              }
          } else if (ICBillType.SaleOut.getCode().equals(bvo.getPk_billtype())) {
        	  //如果出库单下游没有目的单据，则判断只有出库单
        	  if (afterbinessVoss.length == 0) {
        		  this.onlyOut = true;
        	  } else {
        		  for (BillbusinessVO abvo : afterbinessVoss) {
        			  //如果出库单的下游是发票，则判断为先货后票
        			  if (SOBillType.Invoice.getCode().equals(abvo.getPk_billtype())) {
        				  this.outFirst = true;
        				  break;
        			  }
        		  }
        	  }
          }//end if
          //end-ncm-chengqiang5-解决业务流配置发货单推出库单报错的问题
      }//end for 
      if (this.onlyInv && this.onlyOut) {
        this.invOutParallel = true;
        this.onlyInv = false;
        this.onlyOut = false;
      }
      //begin-ncm-chengqiang5
      //如果先票后货、先货后票成立，则只有发票、只有出库单不成立
      if ((this.onlyInv || this.onlyOut) && (this.invFirst || this.outFirst)) {
          this.onlyInv = false;
          this.onlyOut = false;
      }
      //end-ncm-chengqiang5
      // modify by zhangby5 合并项目补丁 NCdp205454502 end
    }

  }

  private void initOutInvoiceType(MessagedriveVO mvo) {
    BillbusinessVO[] bvos =
        PFConfig.findBillbusinessVOs(AppContext.getInstance().getPkGroup(),
            mvo.getPk_sourcebusinesstype());
    for (BillbusinessVO bvo : bvos) {
      if (SOBillType.Invoice.getCode().equals(bvo.getPk_billtype())) {
        this.invoiceTransType =
            bvo.getTranstype() == null ? bvo.getPk_billtype() : bvo
                .getTranstype();
      }
      else if (ICBillType.SaleOut.getCode().equals(bvo.getPk_billtype())) {
        this.outTransType =
            bvo.getTranstype() == null ? bvo.getPk_billtype() : bvo
                .getTranstype();
      }
    }
  }

}
