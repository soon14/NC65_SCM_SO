package nc.bs.so.m38.maintain.rule.insert;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;

import nc.bs.framework.common.NCLocator;
import nc.bs.ml.NCLangResOnserver;
import nc.bs.so.pub.rule.MaterielDistributeCheck;
import nc.bs.uif2.validation.ValidationException;
import nc.impl.pubapp.pattern.rule.IRule;
import nc.itf.so.m38trantype.IM38TranTypeService;
import nc.vo.pub.BusinessException;
import nc.vo.pub.VOStatus;
import nc.vo.pub.lang.UFDouble;
import nc.vo.pubapp.pattern.exception.ExceptionUtils;
import nc.vo.pubapp.pattern.pub.MathTool;
import nc.vo.pubapp.util.VORowNoUtils;
import nc.vo.so.m38.entity.PreOrderBVO;
import nc.vo.so.m38.entity.PreOrderHVO;
import nc.vo.so.m38.entity.PreOrderVO;
import nc.vo.so.m38trantype.entity.M38TranTypeVO;
import nc.vo.so.pub.util.SOSysParaInitUtil;
import nc.vo.trade.checkrule.VOChecker;

/**
 * @description
 * 预定单保存前数据合法性检查
 * @scene
 * 销售预订单新增、修改保存前
 * @param
 * m_transType 销售订单交易类型<销售订单交易类型ID,销售订单交易类型vo>
 * tranTypeService 预订单交易类型服务接口 
 */
public class CheckBillBeforeRule implements IRule<PreOrderVO> {

  // 销售订单交易类型<销售订单交易类型ID,销售订单交易类型vo>
  private final Map<String, M38TranTypeVO> m_transType =
      new HashMap<String, M38TranTypeVO>();

  private IM38TranTypeService tranTypeService;

  public M38TranTypeVO getTransType(String ctrantypeid) {
    M38TranTypeVO tranType = this.m_transType.get(ctrantypeid);
    // 缓存中没有销售订单交易类型从后台查询
    if (VOChecker.isEmpty(tranType)) {
      try {
        tranType = this.getTranTypeService().queryTranTypeVO(ctrantypeid);
        this.setTransType(ctrantypeid, tranType);
      }
      catch (BusinessException e) {
        ExceptionUtils.wrappBusinessException(e.getMessage());
      }
    }
    return tranType;
  }

  @Override
  public void process(PreOrderVO[] vos) {
    for (PreOrderVO vo : vos) {
      this.checkPrice(vo);
      this.checkHeadNotNull(vo);
      this.checkBodyNotNull(vo);
      this.checkRowNo(vo);
      this.checkRowCountLimit(vo);
      this.checkMaterielMutil(vo);
      this.checkMaterielDistribute(vo);
    }
  }

  private void checkBodyNotNull(PreOrderVO vo) {
    // 检查表体非空
    PreOrderBVO[] bvos = vo.getChildrenVO();
    List<String> errField = new ArrayList<String>();
    for (PreOrderBVO bvo : bvos) {
      // 过滤删除行
      if (bvo.getStatus() == VOStatus.DELETED) {
        continue;
      }
      if (VOChecker.isEmpty(bvo.getCmaterialid())) {
        errField.add(NCLangResOnserver.getInstance().getStrByID("4006012_0",
            "04006012-0041")/* [物料] */);
      }
      if (VOChecker.isEmpty(bvo.getCastunitid())) {
        errField.add(NCLangResOnserver.getInstance().getStrByID("4006012_0",
            "04006012-0042")/* [单位] */);
      }
      if (VOChecker.isEmpty(bvo.getNastnum())
          || MathTool.equals(UFDouble.ZERO_DBL, bvo.getNastnum())) {
        errField.add(NCLangResOnserver.getInstance().getStrByID("4006012_0",
            "04006012-0043")/* [数量] */);
      }
      if (VOChecker.isEmpty(bvo.getNnum())
          || MathTool.equals(UFDouble.ZERO_DBL, bvo.getNnum())) {
        errField.add(NCLangResOnserver.getInstance().getStrByID("4006012_0",
            "04006012-0044")/* [主数量] */);
      }
      if (VOChecker.isEmpty(bvo.getDreceivedate())) {
        errField.add(NCLangResOnserver.getInstance().getStrByID("4006012_0",
            "04006012-0086")/* [要求收货日期] */);
      }
      if (VOChecker.isEmpty(bvo.getCtaxcodeid())) {
        errField.add(NCLangResOnserver.getInstance().getStrByID("4006012_0",
            "04006012-0093")/* [税码] */);
      }

      if (VOChecker.isEmpty(bvo.getFtaxtypeflag())) {
        errField.add(NCLangResOnserver.getInstance().getStrByID("4006012_0",
            "04006012-0094")/* [扣税类别] */);
      }

      if (VOChecker.isEmpty(bvo.getCrececountryid())) {
        errField.add(NCLangResOnserver.getInstance().getStrByID("4006012_0",
            "04006012-0096")/* [收货国家/地区] */);
      }

      if (VOChecker.isEmpty(bvo.getCsendcountryid())) {
        errField.add(NCLangResOnserver.getInstance().getStrByID("4006012_0",
            "04006012-0097")/* [发货国家/地区] */);
      }

      if (VOChecker.isEmpty(bvo.getCtaxcountryid())) {
        errField.add(NCLangResOnserver.getInstance().getStrByID("4006012_0",
            "04006012-0098")/* [报税国家/地区] */);

      }

      if (VOChecker.isEmpty(bvo.getFbuysellflag())) {
        errField.add(NCLangResOnserver.getInstance().getStrByID("4006012_0",
            "04006012-0099")/* [购销类型] */);
      }

      if (VOChecker.isEmpty(bvo.getBtriatradeflag())) {
        errField.add(NCLangResOnserver.getInstance().getStrByID("4006012_0",
            "04006012-0100")/* [三角贸易] */);
      }
      if (errField.size() > 0) {
        StringBuilder errMsg =
            new StringBuilder(NCLangResOnserver.getInstance().getStrByID(
                "4006012_0", "04006012-0045", null, new String[] {
                  bvo.getCrowno()
                })/* 表体第[{0}]行下列字段不能为空(或0)： */);
        errMsg.append(errField.get(0));
        for (int i = 1; i < errField.size(); i++) {
          errMsg.append(
              NCLangResOnserver.getInstance().getStrByID("4006012_0",
                  "04006012-0046")/* 、 */).append(errField.get(i));
        }
        ExceptionUtils.wrappBusinessException(errMsg.toString());
      }
    }
  }

  /**
   * 校验单据上日期合法性
   * 
   * @param vo
   */

  private void checkHeadNotNull(PreOrderVO vo) {
    // 检查表头非空
    PreOrderHVO hvo = vo.getParentVO();
    List<String> errField = new ArrayList<String>();
    if (VOChecker.isEmpty(hvo.getPk_org())) {
      errField.add(NCLangResOnserver.getInstance().getStrByID("4006012_0",
          "04006012-0047")/* [销售组织] */);
    }
    if (VOChecker.isEmpty(hvo.getDbilldate())) {
      errField.add(NCLangResOnserver.getInstance().getStrByID("4006012_0",
          "04006012-0048")/* [订货日期 ] */);
    }
    if (VOChecker.isEmpty(hvo.getDabatedate())) {
      errField.add(NCLangResOnserver.getInstance().getStrByID("4006012_0",
          "04006012-0049")/* [失效日期] */);
    }
    if (VOChecker.isEmpty(hvo.getVtrantypecode())) {
      errField.add(NCLangResOnserver.getInstance().getStrByID("4006012_0",
          "04006012-0050")/* [交易类型] */);
    }
    if (VOChecker.isEmpty(hvo.getCcustomerid())) {
      errField.add(NCLangResOnserver.getInstance().getStrByID("4006012_0",
          "04006012-0051")/* [客户] */);
    }
    if (VOChecker.isEmpty(hvo.getCorigcurrencyid())) {
      errField.add(NCLangResOnserver.getInstance().getStrByID("4006012_0",
          "04006012-000003")/* 币种 */);
    }
    /**
     * 预订单应允许币种、价税合计、单价、金额等空。依赖模板控制。默认模板可空。
     * v6.0 jdm cheney zc
     */
    /* if (VOChecker.isEmpty(hvo.getCorigcurrencyid())) {
     * errField.add("币种");
     * }
     * if (VOChecker.isEmpty(hvo.getNheadsummny())) {
     * errField.add("价税合计");
     * } */
    if (errField.size() > 0) {
      StringBuilder errMsg =
          new StringBuilder(NCLangResOnserver.getInstance().getStrByID(
              "4006012_0", "04006012-0052")/* 表头下列字段不能为空： */);
      errMsg.append(errField.get(0));
      for (int i = 1; i < errField.size(); i++) {
        errMsg.append(
            NCLangResOnserver.getInstance().getStrByID("4006012_0",
                "04006012-0046")/* 、 */).append(errField.get(i));
      }
      ExceptionUtils.wrappBusinessException(errMsg.toString());
    }
  }

  private void checkMaterielDistribute(PreOrderVO bill) {
    int len = bill.getChildrenVO().length;
    String[][] materIDStoreIDs = new String[len][2];
    int i = 0;
    for (PreOrderBVO bvo : bill.getChildrenVO()) {
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
  private void checkMaterielMutil(PreOrderVO bill) {
    PreOrderHVO header = bill.getParentVO();
    String ctrantypeid = header.getCtrantypeid();
    M38TranTypeVO trantype = this.getTransType(ctrantypeid);
    if (VOChecker.isEmpty(trantype)) {
      return;
    }
    if (!trantype.getBmorerows().booleanValue()) {
      Set<String> sinvo = new HashSet<String>();
      Set<String> sinvv = new HashSet<String>();
      for (PreOrderBVO bvo : bill.getChildrenVO()) {
        if (VOStatus.DELETED == bvo.getStatus()) {
          continue;
        }
        if (null != bvo.getBlargessflag()
            && bvo.getBlargessflag().booleanValue()) {
          continue;
        }
        String materieloid = bvo.getCmaterialid();
        String materielvid = bvo.getCmaterialvid();
        if (sinvo.contains(materieloid) && sinvv.contains(materielvid)) {
          ExceptionUtils
              .wrappBusinessException(nc.vo.ml.NCLangRes4VoTransl
                  .getNCLangRes().getStrByID("4006012_0", "04006012-0021")/* @res
                                                                           * "预订单类型控制同一货物不可列多行！" */);
        }
        else {
          sinvo.add(materieloid);
          sinvv.add(materielvid);
        }
      }
    }
  }

  private void checkPrice(PreOrderVO vo) {
    PreOrderBVO[] bvos = vo.getChildrenVO();
    UFDouble price = null;
    for (PreOrderBVO bvo : bvos) {
      price = bvo.getNorigtaxprice();
      if (MathTool.lessThan(price, UFDouble.ZERO_DBL)) {
        ExceptionUtils
            .wrappBusinessException(nc.vo.ml.NCLangRes4VoTransl.getNCLangRes()
                .getStrByID("4006012_0", "04006012-0022")/* @res "预订单价格不能小于0" */);
      }
      price = bvo.getNqtorigtaxprice();
      if (MathTool.lessThan(price, UFDouble.ZERO_DBL)) {
        ExceptionUtils
            .wrappBusinessException(nc.vo.ml.NCLangRes4VoTransl.getNCLangRes()
                .getStrByID("4006012_0", "04006012-0022")/* @res "预订单价格不能小于0" */);
      }
    }
  }

  /**
   * SO01限行检查
   */
  private void checkRowCountLimit(PreOrderVO vo) {

    String pk_org = vo.getParentVO().getPk_org();

    int rowlimit = 0;

    Integer so01 = SOSysParaInitUtil.getSO01(pk_org);
    if (null != so01) {
      rowlimit = so01.intValue();
    }

    int rowCount = vo.getChildrenVO().length;
    if (rowlimit > 0 && rowCount > rowlimit) {
      ExceptionUtils.wrappBusinessException(nc.vo.ml.NCLangRes4VoTransl
          .getNCLangRes().getStrByID("4006012_0", "04006012-0023")/* @res
                                                                   * "超订单限制行数[参数SO01限制行数:" */
          + rowlimit + "]");
    }
  }

  private void checkRowNo(PreOrderVO vo) {
    try {
      VORowNoUtils.validateRowNo(vo.getChildrenVO(), PreOrderBVO.CROWNO);
    }
    catch (ValidationException e) {
      ExceptionUtils.wrappException(e);
    }
  }

  private IM38TranTypeService getTranTypeService() {
    if (this.tranTypeService == null) {
      this.tranTypeService =
          NCLocator.getInstance().lookup(IM38TranTypeService.class);
    }
    return this.tranTypeService;
  }

  private void setTransType(String ctrantypeid, M38TranTypeVO mvo) {
    this.m_transType.put(ctrantypeid, mvo);
  }

}
