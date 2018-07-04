package nc.bs.so.m30.rule.m35;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;

import nc.bs.framework.common.NCLocator;
import nc.bs.ml.NCLangResOnserver;
import nc.impl.pubapp.pattern.data.vo.VOQuery;
import nc.impl.pubapp.pattern.data.vo.tool.VOConcurrentTool;
import nc.impl.pubapp.pattern.rule.IRule;
import nc.itf.scmpub.reference.uap.group.SysInitGroupQuery;
import nc.pubitf.so.m35.so.m30.IArsubToSaleorder;
import nc.vo.pub.BusinessException;
import nc.vo.pub.lang.UFDouble;
import nc.vo.pubapp.pattern.exception.ExceptionUtils;
import nc.vo.pubapp.pattern.pub.MathTool;
import nc.vo.pubapp.pattern.pub.SqlBuilder;
import nc.vo.pubapp.scale.ScaleUtils;
import nc.vo.so.m30.entity.OffsetTempVO;
import nc.vo.so.m30.entity.SaleOrderHVO;
import nc.vo.so.m30.entity.SaleOrderVO;
import nc.vo.so.m30.util.SaleOrderVOUtil;
import nc.vo.so.m35.entity.ArsubBVO;
import nc.vo.so.m35.entity.ArsubHVO;
import nc.vo.so.m35.entity.ArsubInterfaceVO;
import nc.vo.so.m35.entity.ArsubViewVO;
import nc.vo.so.m35.paravo.OffsetParaVO;
import nc.vo.so.m35trantype.entity.M35TranTypeVO;

/**
 * 
 * @description
 * 销售订单保存时
 * @scene
 * 销售订单保存时赠品兑付费用单冲抵保存后处理
 * @param
 * allcanoffsetmny 费用单可冲抵金额
 * alloffsettaxmny 订单参与冲抵金额
 * hmrelation 本次冲抵的冲抵关系
 * @since 6.35
 * @version 2013-11-28 下午03:51:53
 * @author dongli2
 */
public class ArsubOffsetAfterSaveRule implements IRule<SaleOrderVO> {

  /** 费用单可冲抵金额 */
  private UFDouble allcanoffsetmny = UFDouble.ZERO_DBL;

  /** 订单参与冲抵金额 */
  private UFDouble alloffsettaxmny = UFDouble.ZERO_DBL;

  /** 本次冲抵的冲抵关系 */
  private Map<String, UFDouble> hmrelation = new HashMap<String, UFDouble>();

  @Override
  public void process(SaleOrderVO[] vos) {
    List<OffsetTempVO> tempvos = new ArrayList<OffsetTempVO>();
    SaleOrderVOUtil voutil = new SaleOrderVOUtil();
    List<SaleOrderVO> arordervo = new ArrayList<SaleOrderVO>();
    boolean isarsub = false;
    for (SaleOrderVO bill : vos) {
      SaleOrderHVO header = bill.getParentVO();
      String arsubtypeid = header.getCarsubtypeid();
      if (null != arsubtypeid) {
    	if (!SysInitGroupQuery.isMeEnabled()) {
  			ExceptionUtils
  					.wrappBusinessException(NCLangResOnserver.getInstance()
  							.getStrByID("4006011_0", "04006011-0519")/*请启用营销费用模块！*/);
    	}
        // 1.过滤表体行
        Map<Integer, OffsetParaVO> newitfvo = this.getinterfaceData(bill);
        // 2.检查是否有可冲抵的行
        if (null == newitfvo || newitfvo.size() == 0) {
          ExceptionUtils.wrappBusinessException(NCLangResOnserver.getInstance().getStrByID("4006011_0", "04006011-0499")/*价税合计为0的行不能做赠品兑付！*/);
        }
        ArsubViewVO[] viewoffsets = null;
        try {
          viewoffsets = this.queryArsubViewVO(bill, newitfvo);
        }
        catch (BusinessException e) {
          ExceptionUtils.wrappBusinessException(NCLangResOnserver.getInstance().getStrByID("4006011_0", "04006011-0500")/*查询客户费用单出错*/);
        }
        if (null != viewoffsets && viewoffsets.length > 0) {
          // 3.冲抵
          ArsubViewVO[] views = this.filterArsubViewVO(viewoffsets, newitfvo);
          if (null != views
              && views.length > 0
              && !MathTool.greaterThan(this.alloffsettaxmny,
                  this.allcanoffsetmny)) {
            this.offsetArsubSave(bill, newitfvo, views);
          }
          else {
        	  String errorMsg =
                      NCLangResOnserver.getInstance().getStrByID("4006011_0", "04006011-0501")/*客户费用余额不足，当前订单赠品兑付金额为：(%s)，匹配到的客户费用单总可冲抵金额为：(%s)，请调整订单赠品兑付金额。*/;
            // add by wangshu6 for 636 提示信息精度处理 20150413
            ScaleUtils scale = ScaleUtils.getScaleUtilAtBS();
            String currid = header.getCorigcurrencyid();
                this.alloffsettaxmny = scale.adjustMnyScale(this.alloffsettaxmny, currid);
                this.allcanoffsetmny = scale.adjustMnyScale(this.allcanoffsetmny, currid);
            errorMsg =
                String.format(errorMsg, this.alloffsettaxmny,
                    this.allcanoffsetmny);
            ExceptionUtils.wrappBusinessException(errorMsg);
          }

          // 3.设置冲抵关系缓存
          OffsetTempVO tempvo = new OffsetTempVO();
          tempvo.setHmArsubRelation(this.hmrelation);
          tempvo.setIsCancelOffset(false);
          tempvos.add(tempvo);
          arordervo.add(bill);

          isarsub = true;
        }
        else {
          ExceptionUtils.wrappBusinessException(NCLangResOnserver.getInstance().getStrByID("4006011_0", "04006011-0502")/*没有匹配到客户费用单，请检查*/);
        }
      }
    }
    if (isarsub) {
      // 4.回写费用单冲抵关系
      SaleOrderVO[] forwirteoder =
          arordervo.toArray(new SaleOrderVO[arordervo.size()]);
      OffsetTempVO[] forwritetemp =
          tempvos.toArray(new OffsetTempVO[tempvos.size()]);
      ArsubInterfaceVO[] arsubvo =
          voutil.changeToArsubInterfaceVO(forwirteoder);
      IArsubToSaleorder service =
          NCLocator.getInstance().lookup(IArsubToSaleorder.class);
      try {
        service.writeArsubRelationForArsub(arsubvo, forwritetemp);
      }
      catch (BusinessException e) {
        ExceptionUtils.wrappBusinessException(e.getMessage());
      }
    }
  }

  /**
   * 订单保存时赠品兑付冲抵
   * 
   * @param bill
   * @param newitfvo
   * @param totalCanSubMny
   * @param viewoffsets
   */
  private void offsetArsubSave(SaleOrderVO bill,
      Map<Integer, OffsetParaVO> newitfvo, ArsubViewVO[] offsetviews) {
    // 1.循环OffsetParaVO进行冲抵
    for (Entry<Integer, OffsetParaVO> para : newitfvo.entrySet()) {
      OffsetParaVO paravo = para.getValue();
      // 红字费用单金额
      UFDouble realviewtotalmny = UFDouble.ZERO_DBL;
      // 2.循环ArsubViewVO进行冲抵分配
      for (ArsubViewVO arviewvo : offsetviews) {
        UFDouble remainmny = UFDouble.ZERO_DBL;
        UFDouble submny = UFDouble.ZERO_DBL;
        // 2.1 初始化ArsubViewVO冲抵金额
        UFDouble viewtotalmny = arviewvo.getItem().getNorigarsubmny();
        UFDouble ordersubmny = arviewvo.getItem().getNordersubmny();
        UFDouble invoicesubmny = arviewvo.getItem().getNinvoicesubmny();
        UFDouble redarsubmny = arviewvo.getItem().getNredarsubmny();
        UFDouble lrgcashmny = arviewvo.getItem().getNlrgcashmny();
        UFDouble arviewsub =
            this.hmrelation.get(arviewvo.getItem().getCarsubbid());

        submny =
            MathTool.add(
                MathTool.add(MathTool.add(ordersubmny, arviewsub),
                    MathTool.add(invoicesubmny, redarsubmny)), lrgcashmny);
        remainmny = MathTool.sub(viewtotalmny, submny);
        // 2.2 初始化OffsetParaVO冲抵金额
        UFDouble orderremainmny =
            MathTool.sub(paravo.getOrigtaxmny(), paravo.getOrigsubmny());
        UFDouble thisordersub = UFDouble.ZERO_DBL;

        // 2.3红字ArsubViewVO处理
        if (MathTool.isDiffSign(orderremainmny, remainmny)) {
          // 负数反正 add by zhangby5 未累加红字金额
          realviewtotalmny = MathTool.add(realviewtotalmny,MathTool.oppose(remainmny));
          // 计入冲抵关系,当做已处理完
          thisordersub = remainmny;
        }
        else {
          // 若红字金额未处理完
          if (!realviewtotalmny.equals(UFDouble.ZERO_DBL)) {
            // 2.4冲抵红字金额
            if (MathTool.greaterThan(remainmny, realviewtotalmny)) {
              remainmny = MathTool.sub(remainmny, realviewtotalmny);
              thisordersub =
                  this.normalOffsetCalculate(paravo, remainmny,
                      orderremainmny);
              // 冲抵红字部分也计入冲抵关系
              thisordersub = MathTool.add(thisordersub, realviewtotalmny);
              realviewtotalmny = UFDouble.ZERO_DBL;
            }
            else if (MathTool.equals(remainmny, realviewtotalmny)) {
              thisordersub = realviewtotalmny;
              realviewtotalmny = UFDouble.ZERO_DBL;
            }
            else if (MathTool.lessThan(remainmny, realviewtotalmny)) {
              thisordersub = remainmny;
              realviewtotalmny = MathTool.sub(realviewtotalmny, remainmny);
            }
          }
          else {
            // 2.5 订单行冲抵(已无红字金额或无红字费用单)
            thisordersub =
                this.normalOffsetCalculate(paravo, remainmny,
                    orderremainmny);
            if (thisordersub.compareTo(UFDouble.ZERO_DBL) == 0) {
              continue;
            }
          }
        }
        remainmny =
 MathTool.add(thisordersub, arviewsub);
        // 2.4 记录冲抵关系
        this.hmrelation.put(arviewvo.getItem().getCarsubbid(), remainmny);
        orderremainmny = MathTool.sub(orderremainmny, thisordersub);
        if (orderremainmny.compareTo(UFDouble.ZERO_DBL) == 0) {
          break;
        }
      }
      this.changeOrderbody(para.getKey(), bill, newitfvo);
    }
  }

  /**
   * 冲抵过程计算
   * 
   * @param paravo
   * @param orderarscale
   * @param remainmny
   * @param orderremainmny
   * @return
   */
  private UFDouble normalOffsetCalculate(OffsetParaVO paravo,
      UFDouble remainmny, UFDouble orderremainmny) {
    UFDouble ordersub;
    UFDouble thisordersub;
    if (MathTool.greaterThan(orderremainmny, remainmny)) {
      thisordersub = remainmny;
      ordersub = paravo.getOrigsubmny();
      ordersub =
 MathTool.add(ordersub, thisordersub);
      paravo.setOrigsubmny(ordersub);
    }
    else {
      thisordersub =
 orderremainmny;
      ordersub = paravo.getOrigsubmny();
      ordersub =
 MathTool.add(ordersub, thisordersub);
      paravo.setOrigsubmny(ordersub);
    }
    return thisordersub;
  }

  /**
   * 处理赠品和赠品兑付标记
   * 
   * @param thissub
   * @param rowindex
   */
  private void changeOrderbody(Integer rowindex, SaleOrderVO bill,
      Map<Integer, OffsetParaVO> newitfvo) {
    OffsetParaVO bvo = newitfvo.get(rowindex);
    UFDouble origtaxmny = bvo.getOrigtaxmny();
    UFDouble origsubmny = bvo.getOrigsubmny();
    if (!origtaxmny.equals(origsubmny)) {
      ExceptionUtils.wrappBusinessException(NCLangResOnserver.getInstance().getStrByID("4006011_0", "04006011-0503")/*费用冲抵失败！请检查！*/);
    }
  }

  /**
   * 收集销售费用单使用的接口数据用于拼接默认sql和按照规则进行分配
   * 
   * @return 接口vo
   */
  private Map<Integer, OffsetParaVO> getinterfaceData(SaleOrderVO bill) {
    Map<Integer, OffsetParaVO> offsetvomap =
        new HashMap<Integer, OffsetParaVO>();
    for (int i = 0; i < bill.getChildrenVO().length; i++) {
      // 价税合计为空或0的不冲抵
      UFDouble origtaxmny = bill.getChildrenVO()[i].getNorigtaxmny();
      if (null == origtaxmny || origtaxmny.compareTo(UFDouble.ZERO_DBL) == 0) {
        continue;
      }
      String settleorg = bill.getChildrenVO()[i].getCsettleorgid();

      // 设置接口VO子实体
      OffsetParaVO bvo = this.getBVO(i, settleorg, bill);
      offsetvomap.put(Integer.valueOf(i), bvo);
      // 累计订单价税合计
      this.alloffsettaxmny =
          MathTool.add(this.alloffsettaxmny, bvo.getOrigtaxmny());
    }
    return offsetvomap;
  }

  /**
   * 收集订单表体数据
   * 
   * @param i 行索引
   * @param settleorg 财务组织
   * @return
   */
  private OffsetParaVO getBVO(int i, String settleorg, SaleOrderVO bill) {
    OffsetParaVO vo = new OffsetParaVO();
    vo.setSaleorg(bill.getParentVO().getPk_org());
    vo.setPk_group(bill.getParentVO().getPk_group());
    vo.setInvoicecusts(bill.getParentVO().getCinvoicecustid());
    vo.setCorigcurrencyid(bill.getParentVO().getCorigcurrencyid());
    vo.setOrdercusts(bill.getParentVO().getCcustomerid());
    vo.setOrdertrantype(bill.getParentVO().getCtrantypeid());
    vo.setOrigsubmny(bill.getChildrenVO()[i].getNorigsubmny());
    vo.setOrigtaxmny(bill.getChildrenVO()[i].getNorigtaxmny());
    vo.setProdline(bill.getChildrenVO()[i].getCprodlineid());
    vo.setSaleorg(bill.getParentVO().getPk_org());
    vo.setSettleorg(settleorg);
    return vo;
  }

  /**
   * 根据销售订单查询费用单视图VO
   * 
   * @return ArsubViewVO
   */
  private ArsubViewVO[] queryArsubViewVO(SaleOrderVO bill,
      Map<Integer, OffsetParaVO> newitfvo) throws BusinessException {
    List<String> settleorgs = new ArrayList<String>();
    String ordercust = bill.getParentVO().getCcustomerid();
    String invcust = bill.getParentVO().getCinvoicecustid();
    String corigcurrencyid = bill.getParentVO().getCorigcurrencyid();
    String carsubtypeid = bill.getParentVO().getCarsubtypeid();
    String csaleorgid = bill.getParentVO().getPk_org();
    M35TranTypeVO m35TranTypeVo = this.queryTranTypeVO(carsubtypeid);

    boolean isByInvoiceCust = m35TranTypeVo.getBsubinvcustflag().booleanValue();
    boolean isBySaleOrg = m35TranTypeVo.getBsubsaleorgflag().booleanValue();

    for (Entry<Integer, OffsetParaVO> entry : newitfvo.entrySet()) {
      OffsetParaVO vo = entry.getValue();
      settleorgs.add(vo.getSettleorg());
    }
    String last = null;
    for (int i = 0; i < settleorgs.size(); i++) {
      if (last == null) {
        last = settleorgs.get(i);
      }
      else {
        if (!last.equals(settleorgs.get(i))) {
          ExceptionUtils.wrappBusinessException(NCLangResOnserver.getInstance().getStrByID("4006011_0", "04006011-0504")/*赠品兑付的销售订单表体行结算财务组织必须一致.*/);
        }
      }
    }

    SqlBuilder sql =
        this.querySqlBuilder(settleorgs.get(0), ordercust, invcust,
            corigcurrencyid, carsubtypeid, isByInvoiceCust, isBySaleOrg,
            csaleorgid);

    // 查询销售费用单
    ArsubViewVO[] bills = null;
    IArsubToSaleorder queryarsrv =
        NCLocator.getInstance().lookup(IArsubToSaleorder.class);
    try {
      bills = queryarsrv.queryOffsetArSub(sql.toString());
    }
    catch (Exception e) {
      ExceptionUtils.wrappException(e);
    }
    return bills;
  }

  public M35TranTypeVO queryTranTypeVO(String ctrantypeid)
      throws BusinessException {
    SqlBuilder sql = new SqlBuilder();
    sql.append(" and ");
    sql.append(M35TranTypeVO.CTRANTYPEID, ctrantypeid);
    VOQuery<M35TranTypeVO> srv =
        new VOQuery<M35TranTypeVO>(M35TranTypeVO.class);
    M35TranTypeVO[] vos = srv.query(sql.toString(), null);
    return vos[0];
  }

  /**
   * 构建查询脚本
   * 
   * @param settleorgs
   * @param ordercust
   * @param invcust
   * @param corigcurrencyid
   * @param carsubtypeid
   * @param isByInvoiceCust
   * @param isBySaleOrg
   * @return SqlBuilder
   */
  private SqlBuilder querySqlBuilder(String settleorg, String ordercust,
      String invcust, String corigcurrencyid, String carsubtypeid,
      boolean isByInvoiceCust, boolean isBySaleOrg, String csaleorgid) {
    SqlBuilder sql = new SqlBuilder();
    sql.append(" select so_arsub_b.carsubbid from so_arsub ");
    sql.append(" inner join so_arsub_b so_arsub_b");
    sql.append(" on so_arsub.carsubid=so_arsub_b.carsubid");
    sql.append(" where ");
    sql.append(" so_arsub." + ArsubHVO.PK_ORG, settleorg);
    sql.append(" and ");
    if (isBySaleOrg) {
      sql.append(" so_arsub." + ArsubHVO.CSALEORGID, csaleorgid);
      sql.append(" and ");
    }
    sql.append(" so_arsub." + ArsubHVO.CORIGCURRENCYID, corigcurrencyid);
    sql.append(" and ");
    sql.append(" so_arsub." + ArsubHVO.CTRANTYPEID, carsubtypeid);
    sql.append(" and ");
    if (isByInvoiceCust) {
      sql.append(" so_arsub." + ArsubHVO.CINVOICECUSTID, invcust);
    }
    else {
      sql.append(" so_arsub." + ArsubHVO.CORDERCUSTID, ordercust);
    }
    sql.append(" and so_arsub." + ArsubHVO.FSTATUSFLAG, 2);
    sql.append(" and so_arsub.dr = 0 ");
    sql.append(" order ");
    sql.append(" by so_arsub." + ArsubHVO.DBILLDATE);
    sql.append(" , so_arsub_b." + ArsubBVO.CARSUBBID);
    return sql;
  }

  /**
   * 费用单筛选
   * 
   * @param vos
   * @param hmtran
   * @param newitfvo
   * @return newViewVOS
   */
  private ArsubViewVO[] filterArsubViewVO(ArsubViewVO[] vos,
      Map<Integer, OffsetParaVO> newitfvo) {
    List<ArsubViewVO> newViewVOS = new ArrayList<ArsubViewVO>();
    List<ArsubViewVO> newRedViewVOS = new ArrayList<ArsubViewVO>();
    List<String> bidslist = new ArrayList<String>();
    for (ArsubViewVO vo : vos) {
      boolean isNeed = false;
      String arsubprodline = vo.getItem().getCprodlineid();
      String trantype = vo.getItem().getCordertrantypeid();
      String arsubpk_org = vo.getItem().getPk_org();
      for (Integer row : newitfvo.keySet()) {
        OffsetParaVO bvo = newitfvo.get(row);
        // 销售费用单产品线不为空，并且和销售订单表体不同，不冲抵
        String prodline = bvo.getProdline();
        if (null != arsubprodline && !arsubprodline.equals(prodline)) {
          continue;
        }
        String ordertran = bvo.getOrdertrantype();
        if (null != trantype && !trantype.equals(ordertran)) {
          continue;
        }
        String settleorg = bvo.getSettleorg();
        if (!arsubpk_org.equals(settleorg)) {
          continue;
        }
        isNeed = true;
      }
      // 至少符合一个订单行的View才是需要的费用单
      if (isNeed) {
        bidslist.add(vo.getItem().getCarsubbid());
        // 累计可冲抵费用金额
        UFDouble viewtotalmny = vo.getItem().getNorigarsubmny();
        if (MathTool.compareTo(viewtotalmny, UFDouble.ZERO_DBL) < 0) {
          newRedViewVOS.add(vo);
        }
        else {
          newViewVOS.add(vo);
        }
        UFDouble ordersubmny = vo.getItem().getNordersubmny();
        UFDouble invoicesubmny = vo.getItem().getNinvoicesubmny();
        UFDouble redarsubmny = vo.getItem().getNredarsubmny();
        UFDouble lrgcashmny = vo.getItem().getNlrgcashmny();
        UFDouble viewusedmny =
            MathTool.add(
                MathTool.add(ordersubmny,
                    MathTool.add(invoicesubmny, redarsubmny)), lrgcashmny);
        UFDouble viewmny = MathTool.sub(viewtotalmny, viewusedmny);
        this.allcanoffsetmny = MathTool.add(this.allcanoffsetmny, viewmny);
      }
    }
    String[] bids = new String[bidslist.size()];
    // 加锁
    VOConcurrentTool tool = new VOConcurrentTool();
    tool.lock(ArsubBVO.class, bidslist.toArray(bids));

    // 蓝字费用单放红字后面
    newRedViewVOS.addAll(newViewVOS);
    ArsubViewVO[] resultArsubVO = new ArsubViewVO[newRedViewVOS.size()];
    return newRedViewVOS.toArray(resultArsubVO);
  }
}
