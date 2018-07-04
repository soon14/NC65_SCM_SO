package nc.bs.so.m30.rule.rewrite.m35;

import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;

import nc.bs.framework.common.NCLocator;
import nc.bs.so.m30.maintain.util.SOStateUtil;
import nc.impl.pubapp.pattern.rule.IRule;
import nc.pubitf.so.m35.so.m30.IArsubToSaleorder;
import nc.vo.pub.BusinessException;
import nc.vo.pub.lang.UFDouble;
import nc.vo.pubapp.pattern.exception.ExceptionUtils;
import nc.vo.pubapp.pattern.pub.MathTool;
import nc.vo.pubapp.pattern.pub.PubAppTool;
import nc.vo.pubapp.scale.ScaleUtils;
import nc.vo.scmpub.res.billtype.ICBillType;
import nc.vo.scmpub.res.billtype.SOBillType;
import nc.vo.so.m30.entity.SaleOrderBVO;
import nc.vo.so.m30.entity.SaleOrderHVO;
import nc.vo.so.m30.entity.SaleOrderViewVO;
import nc.vo.so.m35.entity.ArsubInterfaceVO;
import nc.vo.so.m35.paravo.OffsetParaVO;
import nc.vo.so.pub.util.SOMathUtil;
import nc.vo.so.pub.util.biz.SOBusiUtil;

/**
 * @description
 *              销售订单出库关闭后处理冲抵关系
 * @scene
 *        销售订单出库关闭后
 * @param 无
 * @since 6.0
 * @version 2011-9-13 上午09:54:15
 * @author 么贵敬
 */
public class Rewrite35WhenOutClose implements IRule<SaleOrderViewVO> {

  private UFDouble getDismny(SaleOrderBVO bvo, String corigcurrencyid) {
    UFDouble ntotaloutnum = bvo.getNtotaloutnum();
    UFDouble norigsubmny = bvo.getNorigsubmny();
    // begin 0544000849ISVE1510210914_广东景兴卫生用品有限公司_2015-10-23
    /*
     * 防止空指针异常，防止销售出库保存失败
     */
    if (norigsubmny == null) {
      norigsubmny = UFDouble.ZERO_DBL;
    }
    // end 0544000849ISVE1510210914_广东景兴卫生用品有限公司_2015-10-23

    if (SOMathUtil.isZero(bvo.getNtotaloutnum())
        || SOMathUtil.isZero(bvo.getNnum())) {
      return MathTool.oppose(norigsubmny);
    }
    ScaleUtils scaleutil = ScaleUtils.getScaleUtilAtBS();
    UFDouble disnum = MathTool.sub(ntotaloutnum, bvo.getNnum());
    UFDouble disnmny = disnum.div(bvo.getNnum()).multiply(norigsubmny);

    return scaleutil.adjustMnyScale(disnmny, corigcurrencyid);
  }

  /**
   * 赠品兑付使用
   * 
   * @param bvo
   * @param corigcurrencyid
   * @return
   */
  private UFDouble getDismnyForArsub(SaleOrderBVO bvo, String corigcurrencyid) {
    UFDouble ntotaloutnum = bvo.getNtotaloutnum();
    UFDouble norigtaxmny = bvo.getNorigtaxmny();
    if (SOMathUtil.isZero(bvo.getNtotaloutnum())
        || SOMathUtil.isZero(bvo.getNnum())) {
      return MathTool.oppose(norigtaxmny);
    }
    ScaleUtils scaleutil = ScaleUtils.getScaleUtilAtBS();
    UFDouble disnum = MathTool.sub(ntotaloutnum, bvo.getNnum());
    UFDouble disnmny = disnum.div(bvo.getNnum()).multiply(norigtaxmny);

    return scaleutil.adjustMnyScale(disnmny, corigcurrencyid);
  }

  private OffsetParaVO getParavo(SaleOrderViewVO vo) {
    OffsetParaVO paravo = new OffsetParaVO();
    SaleOrderHVO hvo = vo.getHead();
    SaleOrderBVO bvo = vo.getBody();
    paravo.setCorigcurrencyid(hvo.getCorigcurrencyid());
    paravo.setInvoicecusts(hvo.getCinvoicecustid());
    paravo.setOrdercusts(hvo.getCcustomerid());
    paravo.setOrdertrantype(hvo.getCtrantypeid());
    paravo.setProdline(bvo.getCprodlineid());
    paravo.setSaleorg(bvo.getPk_org());
    paravo.setSettleorg(bvo.getCsettleorgid());
    paravo.setArsubtype(hvo.getCarsubtypeid());
    return paravo;
  }

  /**
   * 出库关闭时是否需要处理对出库的影响
   * 
   * @param vo 试图VO
   * @param billTypeMap
   * @return
   */
  private boolean isNeedProcess(SaleOrderViewVO vo,
      Map<String, Set<String>> billTypeMap) {
    SaleOrderHVO hvo = vo.getHead();
    SaleOrderBVO bvo = vo.getBody();
    // 没有做过冲抵
    if (SOStateUtil.isNotOffsetAndlrgcash(vo)) {
      return false;
    }
    // 出库数量等于订单主数量
    if (MathTool.compareTo(bvo.getNnum(), bvo.getNtotaloutnum()) == 0) {
      return false;
    }
    String cbiztypeid = hvo.getCbiztypeid();
    SOBusiUtil busiUtil = new SOBusiUtil();
    Set<String> typeSet = new HashSet<String>();
    if (billTypeMap.containsKey(cbiztypeid)) {
      typeSet = billTypeMap.get(cbiztypeid);
    }
    else {
      typeSet = busiUtil.queryAllBillType(cbiztypeid);
      billTypeMap.put(cbiztypeid, typeSet);
    }
    // 不走出库的流程
    if (!typeSet.contains(ICBillType.SaleOut.getCode())) {
      return false;
    }
    return true;
  }

  @Override
  public void process(SaleOrderViewVO[] vos) {
    Map<String, ArsubInterfaceVO> itfvos =
        new HashMap<String, ArsubInterfaceVO>();
    Map<String, OffsetParaVO> offsetparavos =
        new HashMap<String, OffsetParaVO>();
    Map<String, UFDouble> offsetmnymap = new HashMap<String, UFDouble>();

    Map<String, ArsubInterfaceVO> arsubitfvos =
        new HashMap<String, ArsubInterfaceVO>();
    Map<String, OffsetParaVO> arsuboffsetparavos =
        new HashMap<String, OffsetParaVO>();
    Map<String, UFDouble> arsuboffsetmnymap = new HashMap<String, UFDouble>();

    Map<String, Set<String>> billTypeMap = new HashMap<String, Set<String>>();

    for (SaleOrderViewVO vo : vos) {
      SaleOrderHVO hvo = vo.getHead();
      SaleOrderBVO bvo = vo.getBody();
      String bid = bvo.getCsaleorderbid();
      String arsubtypeid = hvo.getCarsubtypeid();

      ArsubInterfaceVO itfvo = new ArsubInterfaceVO();
      itfvo.setCsalebillid(bvo.getCsaleorderid());
      itfvo.setVbillcode(hvo.getVbillcode());
      itfvo.setVbilltype(SOBillType.Order.getCode());

      if (!PubAppTool.isNull(arsubtypeid)) {

        // 出库关闭时是否需要处理冲抵
        boolean isneeddo = this.isNeedProcess(vo, billTypeMap);
        if (!isneeddo) {
          continue;
        }
        // 需要调整的冲抵金额
        UFDouble dismny = this.getDismnyForArsub(bvo, hvo.getCorigcurrencyid());
        if (MathTool.isZero(dismny)) {
          continue;
        }
        arsubitfvos.put(bid, itfvo);
        arsuboffsetmnymap.put(bid, dismny);
        OffsetParaVO paravo = this.getParavo(vo);
        arsuboffsetparavos.put(bid, paravo);
      }
      else {
        // 出库关闭时是否需要处理冲抵
        boolean isneeddo = this.isNeedProcess(vo, billTypeMap);
        if (!isneeddo) {
          continue;
        }
        // 需要调整的冲抵金额
        UFDouble dismny = this.getDismny(bvo, hvo.getCorigcurrencyid());
        if (MathTool.isZero(dismny)) {
          continue;
        }
        itfvos.put(bid, itfvo);
        offsetmnymap.put(bid, dismny);
        OffsetParaVO paravo = this.getParavo(vo);
        offsetparavos.put(bid, paravo);
      }
    }
    if (itfvos.size() == 0 && arsubitfvos.size() == 0) {
      return;
    }
    if (itfvos.size() != 0) {
      IArsubToSaleorder srv =
          NCLocator.getInstance().lookup(IArsubToSaleorder.class);
      try {
        srv.processOffsetWithOutClose(itfvos, offsetparavos, offsetmnymap);
      }
      catch (BusinessException e) {
        ExceptionUtils.wrappException(e);
      }
    }
    if (arsubitfvos.size() != 0) {
      IArsubToSaleorder srv =
          NCLocator.getInstance().lookup(IArsubToSaleorder.class);
      try {
        srv.processOffsetWithOutCloseForArsub(arsubitfvos, arsuboffsetparavos,
            arsuboffsetmnymap);
      }
      catch (BusinessException e) {
        ExceptionUtils.wrappException(e);
      }
    }
  }
}
