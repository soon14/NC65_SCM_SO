package nc.bs.so.m30.rule.maintaincheck;

import java.util.ArrayList;
import java.util.List;

import nc.bs.ml.NCLangResOnserver;
import nc.impl.pubapp.pattern.rule.IRule;
import nc.vo.pub.lang.UFDouble;
import nc.vo.pubapp.pattern.exception.ExceptionUtils;
import nc.vo.pubapp.pattern.pub.MathTool;
import nc.vo.so.m30.entity.SaleOrderBVO;
import nc.vo.so.m30.entity.SaleOrderHVO;
import nc.vo.so.m30.entity.SaleOrderVO;

/**
 * @description
 * 数量单价金额后台判空、0、正负号校验
 * @scene
 * 销售订单新增、修改保存前
 * @param 
 * 无
 * 
 * @since 6.3
 * @version 2013-03-12 14:00:12
 * @author 王天文
 */
public class CheckNumPriceMnyRule implements IRule<SaleOrderVO> {

  @Override
  public void process(SaleOrderVO[] vos) {
    for (SaleOrderVO vo : vos) {
      // 如果此销售订单为赠品兑付的订单，则整单即为赠品，将表头价税合计值赋给赠品价税合计，之后设置价税合计为零
      SaleOrderHVO hvo = vo.getParentVO();
      if (hvo.getCarsubtypeid() != null) {
        UFDouble lrg = hvo.getNlrgtotalorigmny();
        hvo.setNlrgtotalorigmny(hvo.getNtotalorigmny().add(lrg));
        hvo.setNtotalorigmny(UFDouble.ZERO_DBL);

      }
      SaleOrderBVO[] bvos = vo.getChildrenVO();
      for (SaleOrderBVO bvo : bvos) {
        // 数量单价金额判空或0
        this.checkZeroNull(bvo);
        // 数量单价金额正负号校验
        this.checkValid(bvo);
      }
    }
  }

  private void checkZeroNull(SaleOrderBVO bvo) {
    // 不合法字段
    List<String> listValiField = new ArrayList<String>();
    StringBuilder errMsg = new StringBuilder();
    // 赠品标志位
    boolean larflag = false;
    if (null != bvo.getBlargessflag()) {
      larflag = bvo.getBlargessflag().booleanValue();
    }
    // 折扣标志位
    boolean disflag = false;
    if (null != bvo.getBdiscountflag()) {
      disflag = bvo.getBdiscountflag().booleanValue();
    }
    UFDouble astnnum = bvo.getNastnum();
    UFDouble nnum = bvo.getNnum();
    UFDouble nqtnnum = bvo.getNqtunitnum();
    // 赠品、折扣物料需单独判定
    if (larflag || disflag) {
      /**
       * 折扣：数量可以为空或者0，金额不能为空或者0
       * 赠品：数量、金额可以为0，不能为空
       */
      if (!disflag) {
        if (null == astnnum) {
          listValiField.add(NCLangResOnserver.getInstance().getStrByID(
              "4006011_0", "04006011-0312")/* 数量 */);
        }
        if (nqtnnum == null) {
          listValiField.add(NCLangResOnserver.getInstance().getStrByID(
              "4006011_0", "04006011-0477")/* 报价数量 */);
        }
        if (nnum == null) {
          listValiField.add(NCLangResOnserver.getInstance().getStrByID(
              "4006011_0", "04006011-0476")/* 主数量 */);
        }

      }

      // ---begin---项目问题：屏蔽掉表体金额不为0校验 -----modified :王天文 2013-03-12
      UFDouble norigmny = bvo.getNorigmny();
      if (null == norigmny || !larflag
          && norigmny.compareTo(UFDouble.ZERO_DBL) == 0) {
        listValiField.add(NCLangResOnserver.getInstance().getStrByID(
            "4006011_0", "04006011-0313")/* 无税金额 */);
      }
      UFDouble norigtaxmny = bvo.getNorigtaxmny();
      if (null == norigtaxmny || !larflag
          && norigtaxmny.compareTo(UFDouble.ZERO_DBL) == 0) {
        listValiField.add(NCLangResOnserver.getInstance().getStrByID(
            "4006011_0", "04006011-0314")/* 价税合计 */);
      }
    }
    // 劳务、普通物料
    else {
      /**
       * 非劳务、非折扣、非赠品：数量、单价、金额不能为空或者0
       * 劳务：数量、单价、金额不能为空或者0
       */
      if (MathTool.isZero(astnnum)) {
        listValiField.add(NCLangResOnserver.getInstance().getStrByID(
            "4006011_0", "04006011-0312")/* 数量 */);
      }
      if (MathTool.isZero(nqtnnum)) {
        listValiField.add(NCLangResOnserver.getInstance().getStrByID(
            "4006011_0", "04006011-0477")/* 报价数量 */);
      }
      if (MathTool.isZero(nnum)) {
        listValiField.add(NCLangResOnserver.getInstance().getStrByID(
            "4006011_0", "04006011-0476")/* 主数量 */);
      }
      UFDouble nqtorigtaxprice = bvo.getNqtorigtaxprice();
      if (null == nqtorigtaxprice
          || nqtorigtaxprice.compareTo(UFDouble.ZERO_DBL) == 0) {
        listValiField.add(NCLangResOnserver.getInstance().getStrByID(
            "4006011_0", "04006011-0315")/* 含税单价 */);
      }
      UFDouble nqtorigprice = bvo.getNqtorigprice();
      if (null == nqtorigprice
          || nqtorigprice.compareTo(UFDouble.ZERO_DBL) == 0) {
        listValiField.add(NCLangResOnserver.getInstance().getStrByID(
            "4006011_0", "04006011-0316")/* 无税单价 */);
      }
      UFDouble nqtorigtaxnetprice = bvo.getNqtorigtaxnetprc();
      if (null == nqtorigtaxnetprice
          || nqtorigtaxnetprice.compareTo(UFDouble.ZERO_DBL) == 0) {
        listValiField.add(NCLangResOnserver.getInstance().getStrByID(
            "4006011_0", "04006011-0317")/* 含税净价 */);
      }
      UFDouble nqtorignetprice = bvo.getNqtorignetprice();
      if (null == nqtorignetprice
          || nqtorignetprice.compareTo(UFDouble.ZERO_DBL) == 0) {
        listValiField.add(NCLangResOnserver.getInstance().getStrByID(
            "4006011_0", "04006011-0318")/* 无税净价 */);
      }
      UFDouble norignetprice = bvo.getNorignetprice();
      if (null == norignetprice
          || norignetprice.compareTo(UFDouble.ZERO_DBL) == 0) {
        listValiField.add(NCLangResOnserver.getInstance().getStrByID(
            "4006011_0", "04006011-0451")/* 主无税净价 */);
      }
      UFDouble nnetprice = bvo.getNnetprice();
      if (null == nnetprice || nnetprice.compareTo(UFDouble.ZERO_DBL) == 0) {
        listValiField.add(NCLangResOnserver.getInstance().getStrByID(
            "4006011_0", "04006011-0452")/* 主本币无税净价 */);
      }
      UFDouble norigmny = bvo.getNorigmny();
      if (null == norigmny || norigmny.compareTo(UFDouble.ZERO_DBL) == 0) {
        listValiField.add(NCLangResOnserver.getInstance().getStrByID(
            "4006011_0", "04006011-0313")/* 无税金额 */);
      }
      UFDouble norigtaxmny = bvo.getNorigtaxmny();
      if (null == norigtaxmny || norigtaxmny.compareTo(UFDouble.ZERO_DBL) == 0) {
        listValiField.add(NCLangResOnserver.getInstance().getStrByID(
            "4006011_0", "04006011-0314")/* 价税合计 */);
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
    if (errMsg.length() > 0) {
      ExceptionUtils.wrappBusinessException(NCLangResOnserver.getInstance()
          .getStrByID("4006011_0", "04006011-0328", null, new String[] {
              errMsg.toString()
          })/* 下列字段值不能为空或为0:\n{0} */);
    }
  }

  private void checkValid(SaleOrderBVO item) {
    UFDouble price = item.getNorigtaxprice();
    if (MathTool.lessThan(price, UFDouble.ZERO_DBL)) {
      ExceptionUtils
      .wrappBusinessException(nc.vo.ml.NCLangRes4VoTransl.getNCLangRes()
          .getStrByID("4006011_0", "04006011-0097")/* @res "单价不能小于0！" */);
    }
    price = item.getNqtorigtaxprice();
    if (MathTool.lessThan(price, UFDouble.ZERO_DBL)) {
      ExceptionUtils
      .wrappBusinessException(nc.vo.ml.NCLangRes4VoTransl.getNCLangRes()
          .getStrByID("4006011_0", "04006011-0097")/* @res "单价不能小于0！" */);
    }
    
    price = item.getNqtorigtaxnetprc();
    if (MathTool.lessThan(price, UFDouble.ZERO_DBL)) {
      ExceptionUtils
      .wrappBusinessException(nc.vo.ml.NCLangRes4VoTransl.getNCLangRes()
          .getStrByID("4006011_0", "04006011-0523")/* @res "净价不能小于0！" */);
    }
    price = item.getNorigtaxnetprice();
    if (MathTool.lessThan(price, UFDouble.ZERO_DBL)) {
      ExceptionUtils
      .wrappBusinessException(nc.vo.ml.NCLangRes4VoTransl.getNCLangRes()
          .getStrByID("4006011_0", "04006011-0523")/* @res "净价不能小于0！" */);
    }
    
    UFDouble exchangerate = item.getNexchangerate();
    if (MathTool.compareTo(exchangerate, UFDouble.ZERO_DBL) <= 0) {
      ExceptionUtils
      .wrappBusinessException(nc.vo.ml.NCLangRes4VoTransl.getNCLangRes()
          .getStrByID("4006011_0", "04006011-0098")/* @res "组织折本汇率不能小于等于0！" */);
    }
    
    // 本币金额校验（赠品除外）
    if (item.getBlargessflag() == null
        || !item.getBlargessflag().booleanValue()) {
      UFDouble ntaxmny = item.getNtaxmny();
      UFDouble norgtaxmny = item.getNorigtaxmny();
      if (MathTool.isDiffSign(ntaxmny, norgtaxmny)) {
        ExceptionUtils
        .wrappBusinessException(nc.vo.ml.NCLangRes4VoTransl.getNCLangRes()
            .getStrByID("4006011_0", "04006011-0099")/* @res
             * "本币金额和原币金额符号相反！" */);
      }
      else {
        if (MathTool.isZero(ntaxmny)) {
          ExceptionUtils
          .wrappBusinessException(nc.vo.ml.NCLangRes4VoTransl
              .getNCLangRes().getStrByID("4006011_0", "04006011-0100")/* @res
               * "本币价税合计不能等于0！" */);
        }
      }
    }
  }
}
