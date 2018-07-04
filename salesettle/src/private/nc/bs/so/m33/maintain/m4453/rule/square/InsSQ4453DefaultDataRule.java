package nc.bs.so.m33.maintain.m4453.rule.square;

import java.util.HashMap;
import java.util.Map;

import nc.vo.ic.m4c.entity.SaleOutBodyVO;
import nc.vo.pub.BusinessException;
import nc.vo.pubapp.pattern.exception.ExceptionUtils;
import nc.vo.pubapp.pattern.pub.MathTool;
import nc.vo.scmpub.res.billtype.SOBillType;
import nc.vo.so.m33.m4453.entity.SquareWasBVO;
import nc.vo.so.m33.m4453.entity.SquareWasHVO;
import nc.vo.so.m33.m4453.entity.SquareWasVO;
import nc.vo.so.m33.m4453.entity.SquareWasVOUtils;
import nc.vo.so.m33.m4c.entity.SquareOutViewVO;
import nc.vo.so.m33.pub.util.SquareCalculatorForVO;
import nc.vo.so.pub.util.AggVOUtil;
import nc.vo.trade.checkrule.VOChecker;

import nc.pubitf.ic.m4c.m32.IQuery4CFor32;
import nc.pubitf.so.m33.self.pub.ISquare434CQuery;

import nc.bs.framework.common.NCLocator;
import nc.bs.ml.NCLangResOnserver;

import nc.impl.pubapp.pattern.rule.IRule;

/**
 * @description
 * 推式生成途损待结算单保存前默认值填充规则
 * @scene
 * 推式生成途损待结算单保存前
 * @param
 * 无
 */
public class InsSQ4453DefaultDataRule implements IRule<SquareWasVO> {

  @Override
  public void process(SquareWasVO[] vos) {

    // 从销售出库待结算单上补充数据
    this.fillDataFromSquareOut(vos);

    // 重新计算单价金额
    SquareWasBVO[] bvos = SquareWasVOUtils.getInstance().getSquareWasBVO(vos);
    new SquareCalculatorForVO().calculate(bvos, SquareWasBVO.NTHISNUM);
  }

  /**
   * 从订单上补充数据：
   * 结算财务组织
   * 结算财务组织版本
   * 结算方式
   * 渠道类型
   * 散户
   * 并且设置是否可以传应收、成本标志
   * 
   * @param vos
   */
  private void fillDataFromSquareOut(SquareWasVO[] vos) {
    try {
      // 上游出库单行id
      String[] outBids =
          AggVOUtil.getDistinctItemFieldArray(vos, SquareWasBVO.CSRCBID,
              String.class);

      // 销售出库结算查询接口,查询销售出库待结算单
      Map<String, SquareOutViewVO> moutidvo =
          this.querySquareOutViewVO(outBids);

      // 从销售出库单上取原币单价、原币净价、组织折本汇率、集团折本汇率、全局折本汇率
      IQuery4CFor32 qry4C = NCLocator.getInstance().lookup(IQuery4CFor32.class);
      Map<String, SaleOutBodyVO> m4C = qry4C.queryBodys(outBids);

      // 途损待结算单赋值
      SquareOutViewVO soutvo = null;
      for (SquareWasVO swvo : vos) {
        for (SquareWasBVO bvo : swvo.getChildrenVO()) {
          String outbid = bvo.getCsrcbid();
          soutvo = moutidvo.get(outbid);

          if (soutvo == null) {
            if (!SOBillType.Order.isEqual(bvo.getVfirsttype())) {
              ExceptionUtils
                  .wrappBusinessException(NCLangResOnserver.getInstance()
                      .getStrByID("4006010_0", "04006010-0152")/*"审批失败：源头单据为出口合同，不支持途损流程！"*/);
            }
          }
          /**
           * 销售出库待结算单上设置相关值
           * 所以可以直接从销售出库待结算单上取结算财务组织，
           * 途损单上的结算财务组织是索赔客户承运商的，不能对照过来
           */
          String pk_org = soutvo.getHead().getPk_org();
          String pk_org_v = soutvo.getHead().getPk_org_v();
          SquareWasHVO hvo = swvo.getParentVO();
          hvo.setPk_org(pk_org);
          hvo.setPk_org_v(pk_org_v);
          bvo.setPk_org(pk_org);
          this.setSquareWasBVOBySquareOutViewVO(bvo, soutvo);

          // 从出库单上设置价格、业务日期
          SaleOutBodyVO outbvo = m4C.get(outbid);
          this.setSquareWasBVOBySaleOutBodyVO(bvo, outbvo);

          // 设置结算数量
          bvo.setNnum(MathTool.oppose(bvo.getNnum()));
          bvo.setNastnum(MathTool.oppose(bvo.getNastnum()));
          bvo.setNthisnum(MathTool.oppose(bvo.getNthisnum()));
        }
      }

    } // end try
    catch (BusinessException e) {
      ExceptionUtils.wrappBusinessException(e.getMessage());
    }
  }

  private Map<String, SquareOutViewVO> querySquareOutViewVO(String[] outBids) {
    ISquare434CQuery square4cQry =
        NCLocator.getInstance().lookup(ISquare434CQuery.class);
    SquareOutViewVO[] sovvos = square4cQry.querySquareOutViewVOBy4CBID(outBids);
    // <销售出库单行id,SquareOutViewVO>
    Map<String, SquareOutViewVO> moutidvo =
        new HashMap<String, SquareOutViewVO>();
    for (SquareOutViewVO svvo : sovvos) {
      moutidvo.put(svvo.getItem().getCsquarebillbid(), svvo);
    }

    return moutidvo;
  }

  private void setSquareType(SquareWasBVO bvo, SquareOutViewVO soutvo) {
    // 如果上游出库单暂估应收，途损单回冲应收
    Integer artype =
        SquareWasVOUtils.getInstance().getARSquareType(
            soutvo.getItem().getFpreartype());
    Integer iatype =
        SquareWasVOUtils.getInstance().getIASquareType(
            soutvo.getItem().getFpreiatype());
    if (!VOChecker.isEmpty(artype)) {
      bvo.setFpreartype(artype);
    }
    if (!VOChecker.isEmpty(iatype)) {
      bvo.setFpreiatype(iatype);
    }
  }

  private void setSquareWasBVOBySaleOutBodyVO(SquareWasBVO bvo,
      SaleOutBodyVO outbvo) {
    bvo.setDbizdate(outbvo.getDbizdate());
    bvo.setNorigdiscount(outbvo.getNitemdiscountrate());
    bvo.setNorigtaxprice(outbvo.getNorigtaxprice());
    bvo.setNorigtaxnetprice(outbvo.getNorigtaxnetprice());
    bvo.setNorigprice(outbvo.getNorigprice());
    bvo.setNorignetprice(outbvo.getNorignetprice());
    bvo.setNexchangerate(outbvo.getNchangestdrate());
    bvo.setNgroupexchgrate(outbvo.getNgroupexchgrate());
    bvo.setNglobalexchgrate(outbvo.getNglobalexchgrate());
  }

  private void setSquareWasBVOBySquareOutViewVO(SquareWasBVO bvo,
      SquareOutViewVO soutvo) {
    // 设置结算类型
    this.setSquareType(bvo, soutvo);

    bvo.setCarorgid(soutvo.getItem().getCarorgid());
    bvo.setCarorgvid(soutvo.getItem().getCarorgvid());
    bvo.setCprofitcenterid(soutvo.getItem().getCprofitcenterid());
    bvo.setCprofitcentervid(soutvo.getItem().getCprofitcentervid());
    bvo.setCcostorgid(soutvo.getItem().getCcostorgid());
    bvo.setCordercustid(soutvo.getItem().getCordercustid());
    bvo.setCinvoicecustid(soutvo.getItem().getCinvoicecustid());
    bvo.setCfreecustid(soutvo.getItem().getCfreecustid());
    bvo.setCpaytermid(soutvo.getItem().getCpaytermid());
    bvo.setCchanneltypeid(soutvo.getItem().getCchanneltypeid());
    bvo.setCdeptid(soutvo.getItem().getCdeptid());
    bvo.setCdeptvid(soutvo.getItem().getCdeptvid());
    bvo.setCsaleorgid(soutvo.getItem().getCsaleorgid());
    bvo.setCsaleorgvid(soutvo.getItem().getCsaleorgvid());
    bvo.setCemployeeid(soutvo.getItem().getCemployeeid());
    bvo.setCprolineid(soutvo.getItem().getCprolineid());
    bvo.setCcustbankaccid(soutvo.getItem().getCcustbankaccid());

    // 设置表体是否可以传应收、成本标志
    bvo.setBcost(soutvo.getItem().getBcost());
    bvo.setBincome(soutvo.getItem().getBincome());
  }

}
