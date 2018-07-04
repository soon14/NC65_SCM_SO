package nc.vo.so.m33.pub.biz.vocheck;

import nc.vo.ml.NCLangRes4VoTransl;
import nc.vo.pub.CircularlyAccessibleValueObject;
import nc.vo.pub.lang.UFDouble;
import nc.vo.pubapp.calculator.data.IRelationForItems;
import nc.vo.pubapp.calculator.data.RelationItemForCal;
import nc.vo.pubapp.pattern.data.ValueUtils;
import nc.vo.pubapp.pattern.exception.ExceptionUtils;
import nc.vo.pubapp.pattern.model.entity.bill.AbstractBill;
import nc.vo.pubapp.pattern.pub.MathTool;
import nc.vo.so.pub.calculator.INumPriceMnyCheckData;
import nc.vo.so.pub.calculator.SOVODataSetForCal;
import nc.vo.trade.checkrule.VOChecker;

public class NumPriceMnyPubCheck<T extends AbstractBill> {

  public void checkData(T[] vos) {
    SOVODataSetForCal data = null;
    IRelationForItems item = new RelationItemForCal();
    for (T vo : vos) {
      for (CircularlyAccessibleValueObject bvo : vo.getChildrenVO()) {
        data = new SOVODataSetForCal(vo.getParentVO(), bvo, item);
        this.check(data);
      }
    }
  }

  private void check(INumPriceMnyCheckData checkData) {
    // 赠品  12
    boolean blargessflag = false;
    
    
    if (ValueUtils.getBoolean(checkData.getBlargessflag())) {
      blargessflag = true;
      // 对于界面是折扣物料，同时又勾选了“赠品”的特殊处理一下（虽然这样做意义不大，谁会有这样的业务呢）
      if (ValueUtils.getBoolean(checkData.getBdiscountflag())) {
        this.checkDiscount(checkData);
      }
      else {
        this.checkLargess(checkData);
      }
    }
    // 费用类存货
    boolean blaborflag = false;
    if (ValueUtils.getBoolean(checkData.getBlaborflag())) {
      blaborflag = true;
      this.checkLabor(checkData);
    }
    // 折扣类存货
    boolean bdiscountflag = false;
    if (ValueUtils.getBoolean(checkData.getBdiscountflag())) {
      bdiscountflag = true;
      this.checkDiscount(checkData);
    }
    // 普通存货
    if (!blargessflag && !blaborflag && !bdiscountflag) {
      this.checkNormal(checkData);
    }
    // 校验单价金额数量之间的关系
    // 跨国业务不存在此关系暂时去掉校验
    // this.checkNumPriceMny(checkData);
  }

  private void checkDiscount(INumPriceMnyCheckData checkData) {
    String msg =
        NCLangRes4VoTransl.getNCLangRes().getStrByID("4006010_0",
            "04006010-0121")/* 折扣类存货 */;
    if(!ValueUtils.getBoolean(checkData.getBlargessflag())){
      this.checkMny(checkData, msg);
    }
  }

  private void checkLabor(INumPriceMnyCheckData checkData) {
    String msg =
        NCLangRes4VoTransl.getNCLangRes().getStrByID("4006010_0",
            "04006010-0122")/* 费用类存货 */;
    this.checkNumNull(checkData, msg);
    if(!ValueUtils.getBoolean(checkData.getBlargessflag())){
        this.checkMny(checkData, msg);
    }

  }

  private void checkLargess(INumPriceMnyCheckData checkData) {
    String msg =
        NCLangRes4VoTransl.getNCLangRes().getStrByID("4006010_0",
            "04006010-0123")/* 赠品类存货 */;
    this.checkNum(checkData, msg);
    this.checkPriceNull(checkData, msg);
    this.checkMnyNull(checkData, msg);
  }

  private void checkMny(INumPriceMnyCheckData checkData, String msg) {
    if (MathTool.isZero(checkData.getNorigtaxmny())) {
      ExceptionUtils.wrappBusinessException(NCLangRes4VoTransl.getNCLangRes()
          .getStrByID("4006010_0", "04006010-0124", null, new String[] {
            msg
          })/* 结算数据中：{0}原币价税合计为空或者0 */);
    }
    else if (MathTool.isZero(checkData.getNorigmny())) {
      ExceptionUtils.wrappBusinessException(NCLangRes4VoTransl.getNCLangRes()
          .getStrByID("4006010_0", "04006010-0125", null, new String[] {
            msg
          })/* 结算数据中：{0}原币无税金额为空或者0 */);
    }
    // 原币税额字段不存在了 暂时先去掉
    // else if (VOChecker.isEmpty(checkData.getNorigtax())) {
    // ExceptionUtils.wrappBusinessException(NCLangRes4VoTransl.getNCLangRes()
    // .getStrByID("4006010_0", "04006010-0126", null, new String[] {
    // msg
    // })/*结算数据中：{0}原币税额为空*/);
    // }
  }

  private void checkMnyNull(INumPriceMnyCheckData checkData, String msg) {
    if (VOChecker.isEmpty(checkData.getNorigtaxmny())) {
      ExceptionUtils.wrappBusinessException(NCLangRes4VoTransl.getNCLangRes()
          .getStrByID("4006010_0", "04006010-0127", null, new String[] {
            msg
          })/* 结算数据中：{0}原币价税合计为空 */);
    }
    else if (VOChecker.isEmpty(checkData.getNorigmny())) {
      ExceptionUtils.wrappBusinessException(NCLangRes4VoTransl.getNCLangRes()
          .getStrByID("4006010_0", "04006010-0128", null, new String[] {
            msg
          })/* 结算数据中：{0}原币无税金额为空 */);
    }
    // 原币税额字段不存在了 暂时先去掉
    // else if (VOChecker.isEmpty(checkData.getNorigtax())) {
    // ExceptionUtils.wrappBusinessException(NCLangRes4VoTransl.getNCLangRes()
    // .getStrByID("4006010_0", "04006010-0126", null, new String[] {
    // msg
    // })/*结算数据中：{0}原币税额为空*/);
    // }
  }

  private void checkNormal(INumPriceMnyCheckData checkData) {
    String msg =
        NCLangRes4VoTransl.getNCLangRes().getStrByID("4006010_0",
            "04006010-0129")/* 普通存货 */;
    this.checkNum(checkData, msg);
    this.checkPrice(checkData, msg);
    this.checkMny(checkData, msg);
  }

  private void checkNum(INumPriceMnyCheckData checkData, String msg) {
    if (MathTool.isZero(checkData.getNnum())) {
      ExceptionUtils.wrappBusinessException(NCLangRes4VoTransl.getNCLangRes()
          .getStrByID("4006010_0", "04006010-0130", null, new String[] {
            msg
          })/* 结算数据中：{0}主数量为空或者0 */);
    }
  }

  private void checkNumNull(INumPriceMnyCheckData checkData, String msg) {
    if (VOChecker.isEmpty(checkData.getNnum())) {
      ExceptionUtils.wrappBusinessException(NCLangRes4VoTransl.getNCLangRes()
          .getStrByID("4006010_0", "04006010-0131", null, new String[] {
            msg
          })/* 结算数据中：{0}主数量为空 */);
    }
  }

  private void checkNumPriceMny(INumPriceMnyCheckData checkData) {
    // 本币价税合计 = 本币无税金额 + 本币税额
    UFDouble temp = MathTool.add(checkData.getNmny(), checkData.getNtax());
    if (!MathTool.equals(temp, checkData.getNtaxmny())) {
      ExceptionUtils
          .wrappBusinessException(nc.vo.ml.NCLangRes4VoTransl.getNCLangRes()
              .getStrByID("4006010_0", "04006010-0081")/* @res
                                                        * "结算数据中：主单位本币价税合计 != 主单位本币无税金额 + 税额" */);
    }
  }

  private void checkPrice(INumPriceMnyCheckData checkData, String msg) {
    if (MathTool.isZero(checkData.getNorigtaxnetprice())) {
      ExceptionUtils.wrappBusinessException(NCLangRes4VoTransl.getNCLangRes()
          .getStrByID("4006010_0", "04006010-0132", null, new String[] {
            msg
          })/* 结算数据中：{0}主单位原币含税净价为空或者0 */);
    }
    else if (MathTool.isZero(checkData.getNorignetprice())) {
      ExceptionUtils.wrappBusinessException(NCLangRes4VoTransl.getNCLangRes()
          .getStrByID("4006010_0", "04006010-0133", null, new String[] {
            msg
          })/* 结算数据中：{0}主单位原币无税净价为空或者0 */);
    }
    else if (MathTool.isZero(checkData.getNorigtaxprice())) {
      ExceptionUtils.wrappBusinessException(NCLangRes4VoTransl.getNCLangRes()
          .getStrByID("4006010_0", "04006010-0134", null, new String[] {
            msg
          })/* 结算数据中：{0}主单位原币含税单价为空或者0 */);
    }
    else if (MathTool.isZero(checkData.getNorigprice())) {
      ExceptionUtils.wrappBusinessException(NCLangRes4VoTransl.getNCLangRes()
          .getStrByID("4006010_0", "04006010-0135", null, new String[] {
            msg
          })/* 结算数据中：{0}主单位原币无税单价为空或者0 */);
    }
  }

  private void checkPriceNull(INumPriceMnyCheckData checkData, String msg) {
    if (VOChecker.isEmpty(checkData.getNorigtaxnetprice())) {
      ExceptionUtils.wrappBusinessException(NCLangRes4VoTransl.getNCLangRes()
          .getStrByID("4006010_0", "04006010-0136", null, new String[] {
            msg
          })/* 结算数据中：{0}主单位原币含税净价为空 */);
    }
    else if (VOChecker.isEmpty(checkData.getNorignetprice())) {
      ExceptionUtils.wrappBusinessException(NCLangRes4VoTransl.getNCLangRes()
          .getStrByID("4006010_0", "04006010-0137", null, new String[] {
            msg
          })/* 结算数据中：{0}主单位原币无税净价为空 */);
    }
    else if (VOChecker.isEmpty(checkData.getNorigtaxprice())) {
      ExceptionUtils.wrappBusinessException(NCLangRes4VoTransl.getNCLangRes()
          .getStrByID("4006010_0", "04006010-0138", null, new String[] {
            msg
          })/* 结算数据中：{0}主单位原币含税单价为空 */);
    }
    else if (VOChecker.isEmpty(checkData.getNorigprice())) {
      ExceptionUtils.wrappBusinessException(NCLangRes4VoTransl.getNCLangRes()
          .getStrByID("4006010_0", "04006010-0139", null, new String[] {
            msg
          })/* 结算数据中：{0}主单位原币无税单价为空 */);
    }
  }

}
