package nc.bs.so.m33.biz.m32.rule.toar;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import nc.bs.framework.common.NCLocator;
import nc.bs.so.m33.maintain.m4c.query.QuerySquare4CVOBP;
import nc.impl.pubapp.pattern.rule.IRule;
import nc.itf.so.m33.ref.ic.m4453.ICM4453ServiceUtil;
import nc.itf.so.m33.ref.ic.m4c.ICM4CServiceUtil;
import nc.pubitf.so.ic.m4c.ISaleFor4CParaQuery;
import nc.vo.ml.NCLangRes4VoTransl;
import nc.vo.pub.BusinessException;
import nc.vo.pub.lang.UFBoolean;
import nc.vo.pubapp.pattern.data.ValueUtils;
import nc.vo.pubapp.pattern.exception.ExceptionUtils;
import nc.vo.pubapp.pattern.pub.PubAppTool;
import nc.vo.so.m33.m32.entity.SquareInvBVO;
import nc.vo.so.m33.m32.entity.SquareInvVO;
import nc.vo.so.m33.m32.entity.SquareInvVOUtils;
import nc.vo.so.m33.m32.entity.SquareInvViewVO;
import nc.vo.so.m33.m4c.entity.SquareOutViewVO;
import nc.vo.so.paravo.Para4CFor32SignBiz;
import nc.vo.so.pub.util.AggVOUtil;
import nc.vo.so.pub.votools.SoVoTools;
import nc.vo.trade.checkrule.VOChecker;

/**
 * @description
 * 差额传应收流程业务前校验:
 * 1.销售发票上游的途损单和签收开票流程退回的销售出库单必须全部审批完毕
 * 2.销售发票作差额传应收，销售发票与出库单的应收组织必须一致
 * @scene
 * 差额传应收业务前校验
 * @param
 * 无
 * 
 * @since 6.0
 * @version 2011-9-30 上午10:51:17
 * @author zhangcheng
 */
public class CheckADIncomeDataRule implements IRule<SquareInvVO> {

  @Override
  public void process(SquareInvVO[] vos) {
    this.checkOutWasApproved(vos);
    this.checkOutInvAROrg(vos);
  }

  /**
   * 销售发票作差额传应收，销售发票与出库单的应收组织必须一致
   * 
   * @param vos
   */
  private void checkOutInvAROrg(SquareInvVO[] vos) {
    String[] outBids =
        AggVOUtil.getDistinctItemFieldArray(vos, SquareInvBVO.CSRCBID,
            String.class);
    // 查询销售出库单待结算单
    SquareOutViewVO[] outViews =
        new QuerySquare4CVOBP().querySquareOutViewVOBy4CBID(outBids);
    // <销售出库单bid,销售出库单行应收组织oid>
    Map<String, String> map = new HashMap<String, String>();
    for (SquareOutViewVO view : outViews) {
      map.put(view.getItem().getCsquarebillbid(), view.getItem().getCarorgid());
    }
    for (SquareInvVO invvo : vos) {
      for (SquareInvBVO bvo : invvo.getChildrenVO()) {
        String outbid = bvo.getCsrcbid();
        String invoicearoid = bvo.getCarorgid();
        String outaroid = map.get(outbid);
        // 排出销售发票没有上游出库单的场景，增加劳务折扣行 !PubAppTool.isNull(outaroid)
        if (!PubAppTool.isNull(outaroid)
            && !PubAppTool.isEqual(invoicearoid, outaroid)) {
          ExceptionUtils
              .wrappBusinessException(NCLangRes4VoTransl.getNCLangRes()
                  .getStrByID("4006010_0", "04006010-0148")/*@res "销售发票作差额传应收，销售发票与出库单的应收组织必须一致"*/);
        }
      }
    }

  }

  /**
   * 销售发票上游的途损单和签收开票流程退回的销售出库单必须全部审批完毕
   * 
   * @param vos
   */
  private void checkOutWasApproved(SquareInvVO[] vos) {
    String[] outBids =
        AggVOUtil.getDistinctItemFieldArray(vos, SquareInvBVO.CSRCBID,
            String.class);

    // 销售发票上游的销售出库单对应的下游途损单必须全部审批
    boolean bWasBillApprove =
        ICM4453ServiceUtil.checkWasBillIsApproveByOutBids(outBids);

    // 销售发票上游的销售出库单对应的下游签收退回的销售出库单必须全部签字
    boolean bRetOutApprove = true;
    SquareInvViewVO[] views =
        SquareInvVOUtils.getInstance().changeToSaleSquareViewVO(vos);
    Para4CFor32SignBiz[] paras = this.querySignNumBusitype(views);
    if (!VOChecker.isEmpty(paras)) {
      bRetOutApprove = this.processSignReturnOut(views, paras);
    }

    if (!bWasBillApprove || !bRetOutApprove) {
      ExceptionUtils
          .wrappBusinessException(nc.vo.ml.NCLangRes4VoTransl.getNCLangRes()
              .getStrByID("4006010_0", "04006010-0013")/*@res "差额传应收流程，销售发票上游的途损单和签收开票流程退回的销售出库单必须全部审批完毕！"*/);
    }
  }

  /**
   * 签收开票流程退回的销售出库单是否全部签字
   * 
   * @param views
   * @param paras
   * @return
   */
  private boolean processSignReturnOut(SquareInvViewVO[] views,
      Para4CFor32SignBiz[] paras) {
    boolean bRetOutApprove = true;
    Map<String, UFBoolean> mpara = new HashMap<String, UFBoolean>();
    for (Para4CFor32SignBiz para : paras) {
      mpara.put(para.getPk_org() + para.getCbizid(), para.getIsSign());
    }
    List<SquareInvViewVO> lSignView = new ArrayList<SquareInvViewVO>();
    for (SquareInvViewVO view : views) {
      String key =
          view.getItem().getCsaleorgid() + view.getHead().getCbiztypeid();
      UFBoolean isSignflag = ValueUtils.getUFBoolean(mpara.get(key));
      if (isSignflag.booleanValue()) {
        lSignView.add(view);
      }
    }

    // 有基于签收开票的流程
    if (lSignView.size() > 0) {
      SquareInvViewVO[] sviews =
          lSignView.toArray(new SquareInvViewVO[lSignView.size()]);
      String[] outBids =
          SoVoTools.getVOsOnlyValues(sviews, SquareInvBVO.CSRCBID);
      bRetOutApprove = ICM4CServiceUtil.checkSaleOutIsSignByOutBids(outBids);
    }

    return bRetOutApprove;
  }

  /**
   * 查询基于签收开票的流程
   * 
   * @param views
   * @return
   */
  private Para4CFor32SignBiz[] querySignNumBusitype(SquareInvViewVO[] views) {
    List<Para4CFor32SignBiz> lpara = new ArrayList<Para4CFor32SignBiz>();
    for (SquareInvViewVO svo : views) {
      String cbiztypeid = svo.getHead().getCbiztypeid();
      String csaleorgid = svo.getItem().getCsaleorgid();
      String cfirstbid = svo.getItem().getCfirstbid();
      // 无来源无销售组织行（自制的费用折扣行）
      if (null == cfirstbid && null == csaleorgid) {
        continue;
      }
      Para4CFor32SignBiz para = new Para4CFor32SignBiz(cbiztypeid, csaleorgid);
      lpara.add(para);
    }
    Para4CFor32SignBiz[] paras =
        lpara.toArray(new Para4CFor32SignBiz[lpara.size()]);
    ISaleFor4CParaQuery pqs =
        NCLocator.getInstance().lookup(ISaleFor4CParaQuery.class);
    Para4CFor32SignBiz[] rets = null;
    try {
      rets = pqs.querySignNumBusitype(paras);
    }
    catch (BusinessException e) {
      ExceptionUtils.wrappBusinessException(e.getMessage());
    }
    return rets;
  }

}
