package nc.bs.so.m32.maintain.rule.insert;

import java.util.ArrayList;
import java.util.List;

import nc.bs.ml.NCLangResOnserver;
import nc.bs.so.pub.rule.rowno.SORowNoUtil;
import nc.impl.pubapp.pattern.rule.IRule;
import nc.vo.pub.VOStatus;
import nc.vo.pub.lang.UFBoolean;
import nc.vo.pub.lang.UFDouble;
import nc.vo.pubapp.pattern.data.ValueUtils;
import nc.vo.pubapp.pattern.exception.ExceptionUtils;
import nc.vo.pubapp.pattern.pub.MathTool;
import nc.vo.so.m32.entity.SaleInvoiceBVO;
import nc.vo.so.m32.entity.SaleInvoiceHVO;
import nc.vo.so.m32.entity.SaleInvoiceVO;
import nc.vo.so.pub.keyvalue.IKeyValue;
import nc.vo.so.pub.keyvalue.VOKeyValue;
import nc.vo.so.pub.rule.SOBuysellTriaRule;
import nc.vo.so.pub.util.SOSysParaInitUtil;
import nc.vo.trade.checkrule.VOChecker;

/**
 * @description
 * 销售发票保存前数据合法性校验
 * @scene
 * 销售发票新增、修改保存前
 * @param
 * 无
 * @since 6.0
 * @version 2011-12-12 上午11:03:38
 * @author 么贵敬
 */
public class CheckBillValityRule implements IRule<SaleInvoiceVO> {

  @Override
  public void process(SaleInvoiceVO[] invoices) {

    for (SaleInvoiceVO invoicevo : invoices) {
      // 表头合法性校验
      this.checkHeadValidity(invoicevo.getParentVO());
      // 表体数据合法性校验
      this.checkBodyValidity(invoicevo);
      // // VO字段长度合法性校验
      // this.checkFieldLengthValidity(invoicevo);
      // 表体行数
      this.checkRowCountLimit(invoicevo);
      // 校验行号
      SORowNoUtil.checkRowNo(invoicevo);
    }
  }

  /**
   * 方法功能描述：新增保存时校验表体合法性。
   * <p>
   * <b>参数说明</b>
   * 
   * @param childrenVO
   *          <p>
   * @author 冯加滨
   * @time 2010-1-21 下午03:18:00
   */
  private void checkBodyValidity(SaleInvoiceVO vo) {
    IKeyValue keyValue = new VOKeyValue<SaleInvoiceVO>(vo);
    SOBuysellTriaRule buyselrule = new SOBuysellTriaRule(keyValue);
    boolean isInternational = buyselrule.isHeadBuysellFlagOut();
    SaleInvoiceBVO[] childrenVOs = vo.getChildrenVO();
    // 修改处 zhangby5 2014.7.29 销售发票表体行不能为零
    if (null == childrenVOs || this.getVORowCount(vo) == 0) {
      ExceptionUtils
          .wrappBusinessException(nc.vo.ml.NCLangRes4VoTransl.getNCLangRes()
              .getStrByID("4006008_0", "04006008-0138")/* @res "表体不可为空。" */);
      return;
    }
    NCLangResOnserver resservice = NCLangResOnserver.getInstance();
    List<String> errField = new ArrayList<String>();
    for (SaleInvoiceBVO bvo : childrenVOs) {
      // 过滤删除行
      if (bvo.getStatus() == VOStatus.DELETED) {
        continue;
      }
      if (VOChecker.isEmpty(bvo.getCmaterialid())) {
        errField
            .add(resservice.getStrByID("4006008_0", "04006008-0086")/* 物料 */);
      }
      if (VOChecker.isEmpty(bvo.getCastunitid())) {
        errField
            .add(resservice.getStrByID("4006008_0", "04006008-0087")/* 单位 */);
      }

      if (null == bvo.getCtaxcodeid()) {
        errField.add(NCLangResOnserver.getInstance().getStrByID("4006008_0",
            "04006008-0130")/* 税码 */);
      }

      if (null == bvo.getFtaxtypeflag()) {
        errField.add(NCLangResOnserver.getInstance().getStrByID("4006008_0",
            "04006008-0131")/* 扣税类别 */);
      }

      if (null == bvo.getNcaltaxmny()) {
        errField.add(NCLangResOnserver.getInstance().getStrByID("4006008_0",
            "04006008-0132")/* 计税金额 */);
      }

      UFBoolean disflag =
          bvo.getBdiscountflag() == null ? UFBoolean.FALSE : bvo
              .getBdiscountflag();
      UFBoolean freeflag =
          bvo.getBlaborflag() == null ? UFBoolean.FALSE : bvo.getBlaborflag();
      // 赠品
      UFBoolean largessflag =
          bvo.getBlargessflag() == null ? UFBoolean.FALSE : bvo
              .getBlargessflag();
      if ((disflag.booleanValue() || freeflag.booleanValue())) {
        if (freeflag.booleanValue() && null == bvo.getNnum()) {
          errField
              .add(resservice.getStrByID("4006008_0", "04006008-0088")/* 数量 */);
        }

        // begin-基础供应链-liujingn-NCdp205575534-2016/1/20-专项
        /**
         * 服务类折扣物料为赠品时价税合计应该可以为零
         */
        if (MathTool.isZero(bvo.getNorigtaxmny())
            && !largessflag.booleanValue()) {
          errField
              .add(resservice.getStrByID("4006008_0", "04006008-0089")/* 价税合计 */);
        }
        if (MathTool.isZero(bvo.getNtaxmny()) && !largessflag.booleanValue()) {
          errField
              .add(resservice.getStrByID("4006008_0", "04006008-0091")/* 本币价税合计 */);
        }
        // end-基础供应链-liujingn-NCdp205575534-2016/1/20-专项

      }
      else if (largessflag.booleanValue()) {
        if (MathTool.isZero(bvo.getNnum())) {
          errField
              .add(resservice.getStrByID("4006008_0", "04006008-0088")/* 数量 */);
        }

      }
      else {
        if (MathTool.isZero(bvo.getNqtorigtaxprice())) {
          errField
              .add(resservice.getStrByID("4006008_0", "04006008-0090")/* 含税单价 */);
        }
        if (MathTool.isZero(bvo.getNorignetprice())) {
          errField.add(NCLangResOnserver.getInstance().getStrByID("4006008_0",
              "04006008-0118")/* 主无税净价 */);
        }
        if (MathTool.isZero(bvo.getNnetprice())) {
          errField.add(NCLangResOnserver.getInstance().getStrByID("4006008_0",
              "04006008-0139")/* 主本币无税净价 */);
        }
        if (MathTool.isZero(bvo.getNorigtaxmny())) {
          errField
              .add(resservice.getStrByID("4006008_0", "04006008-0089")/* 价税合计 */);
        }

        if (MathTool.isZero(bvo.getNtaxmny())) {
          errField
              .add(resservice.getStrByID("4006008_0", "04006008-0091")/* 本币价税合计 */);
        }
      }

      if (errField.size() > 0) {
        StringBuilder errMsg =
            new StringBuilder(resservice.getStrByID("4006008_0",
                "04006008-0092", null, new String[] {
                  bvo.getCrowno()
                })/* 销售发票第[{0}]行下列字段不能为空或者0： */);

        for (String field : errField) {
          errMsg.append(NCLangResOnserver.getInstance().getStrByID("4006008_0",
              "04006008-0149", null, new String[] {
                field
              })/* [{0}]、 */);

        }
        errMsg.deleteCharAt(errMsg.length() - 1);
        ExceptionUtils.wrappBusinessException(errMsg.toString());
      }

//       暂时先去掉跨国业务没有此关系
      if (!isInternational) {
        UFDouble ntaxmny = bvo.getNtaxmny();
        UFDouble naddtaxmny = MathTool.add(bvo.getNtax(), bvo.getNmny());
        if (!MathTool.equals(ntaxmny, naddtaxmny)) {
          ExceptionUtils.wrappBusinessException(NCLangResOnserver.getInstance()
              .getStrByID(
                  "4006008_0",
                  "04006008-0140",
                  null,
                  new String[] {
                    bvo.getCrowno(), ValueUtils.getString(ntaxmny),
                    ValueUtils.getString(bvo.getNmny()),
                    ValueUtils.getString(bvo.getNtax())
                  })/* 销售发票下列行{0}本币价税合计({1})不等于本币无税金额({2})加税额{3} */);
        }
      }

    }
  }

  // /**
  // * 方法功能描述：新增时校验VO字段长度。
  // * <p>
  // * <b>参数说明</b>
  // *
  // * @param invoicevo
  // * <p>
  // * @author 冯加滨
  // * @time 2010-3-17 下午02:22:16
  // */
  // private void checkFieldLengthValidity(SaleInvoiceVO invoicevo) {
  // // 调用公共长度校验工具类
  // VOFieldLengthChecker.checkVOFieldsLength(invoicevo);
  // }

  /**
   * 方法功能描述：新增保存时校验表头合法性。
   * <p>
   * <b>参数说明</b>
   * 
   * @param parent
   *          <p>
   * @author 冯加滨
   * @time 2010-1-21 下午02:52:13
   */
  private void checkHeadValidity(SaleInvoiceHVO head) {
    List<String> errField = new ArrayList<String>();

    if (VOChecker.isEmpty(head.getPk_org())) {
      errField.add(NCLangResOnserver.getInstance().getStrByID("4006008_0",
          "04006008-0093")/* 开票组织 */);
    }
    if (VOChecker.isEmpty(head.getDbilldate())) {
      errField.add(NCLangResOnserver.getInstance().getStrByID("4006008_0",
          "04006008-0094")/* 开票日期 */);
    }
    if (VOChecker.isEmpty(head.getCtrantypeid())) {
      errField.add(NCLangResOnserver.getInstance().getStrByID("4006008_0",
          "04006008-0095")/* 发票类型 */);
    }
    if (VOChecker.isEmpty(head.getCinvoicecustid())) {
      errField.add(NCLangResOnserver.getInstance().getStrByID("4006008_0",
          "04006008-0096")/* 开票客户 */);
    }
    if (VOChecker.isEmpty(head.getCbiztypeid())) {
      errField.add(NCLangResOnserver.getInstance().getStrByID("4006008_0",
          "04006008-0097")/* 业务流程 */);
    }
    if (VOChecker.isEmpty(head.getCorigcurrencyid())) {
      errField.add(NCLangResOnserver.getInstance().getStrByID("4006008_0",
          "04006008-0098")/* 币种 */);
    }
    if (VOChecker.isEmpty(head.getNexchangerate())
        || head.getNexchangerate().compareTo(UFDouble.ZERO_DBL) <= 0) {
      errField.add(NCLangResOnserver.getInstance().getStrByID("4006008_0",
          "04006008-0099")/* 折本汇率 */);
    }

    if (null == head.getCrececountryid()) {
      errField.add(NCLangResOnserver.getInstance().getStrByID("4006008_0",
          "04006008-0133")/* 收货国家/地区 */);
    }

    if (null == head.getCsendcountryid()) {
      errField.add(NCLangResOnserver.getInstance().getStrByID("4006008_0",
          "04006008-0134")/* 发货国家/地区 */);
    }

    if (null == head.getCtaxcountryid()) {
      errField.add(NCLangResOnserver.getInstance().getStrByID("4006008_0",
          "04006008-0135")/* 报税国家/地区 */);
    }

    if (null == head.getFbuysellflag()) {
      errField.add(NCLangResOnserver.getInstance().getStrByID("4006008_0",
          "04006008-0136")/* 购销类型 */);
    }

    if (null == head.getBtriatradeflag()) {
      errField.add(NCLangResOnserver.getInstance().getStrByID("4006008_0",
          "04006008-0137")/* 三角贸易 */);
    }

    if (errField.size() > 0) {
      StringBuilder errMsg =
          new StringBuilder(NCLangResOnserver.getInstance().getStrByID(
              "4006008_0", "04006008-0100")/* 下列属性的值不能为空或0： */);
      errMsg.append(NCLangResOnserver.getInstance().getStrByID("4006008_0",
          "04006008-0101")/* \n 表头 */);
      for (String field : errField) {
        errMsg.append("[").append(field).append("]").append(",");
      }
      errMsg.deleteCharAt(errMsg.length() - 1);

      ExceptionUtils.wrappBusinessException(errMsg.toString());
    }
    // if (VOChecker.isEmpty(head.getNexchangerate())
    // || head.getNexchangerate().compareTo(UFDouble.ZERO_DBL) <= 0) {
    // ExceptionUtils.wrappBusinessException("销售发票折本汇率不能小于等于0。");
    // }
  }

  private void checkRowCountLimit(SaleInvoiceVO vo) {
    Object pk_org = vo.getParentVO().getPk_org();
    int rowlimit = 0;

    rowlimit =
        SOSysParaInitUtil.getSO17(pk_org.toString()) == null ? 0
            : SOSysParaInitUtil.getSO17(pk_org.toString()).intValue();

    int rowCount = this.getVORowCount(vo);
    if (rowlimit > 0 && rowCount > rowlimit) {
      ExceptionUtils.wrappBusinessException(NCLangResOnserver.getInstance()
          .getStrByID("4006008_0", "04006008-0102", null, new String[] {
            Integer.toString(rowlimit)
          })/* 超发票限制行数![参数SO17限制行数:{0}] */);
    }
  }

  // 获取VO中表体中非删除状态的行数 zhangby5 2014.7.29
  private int getVORowCount(SaleInvoiceVO vo) {

    int count = 0;
    for (SaleInvoiceBVO bvo : vo.getChildrenVO()) {

      if (bvo.getStatus() != VOStatus.DELETED) {
        count++;
      }
    }
    return count;
  }
}
