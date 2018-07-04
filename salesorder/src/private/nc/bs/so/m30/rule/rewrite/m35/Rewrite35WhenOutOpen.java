package nc.bs.so.m30.rule.rewrite.m35;

import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;

import nc.bs.framework.common.NCLocator;
import nc.bs.so.m30.maintain.util.SOStateUtil;
import nc.impl.pubapp.env.BSContext;
import nc.impl.pubapp.pattern.rule.IRule;
import nc.pubitf.so.m30.ic.m4c.Rewrite4CPara;
import nc.pubitf.so.m35.so.m30.IArsubToSaleorder;
import nc.vo.pub.BusinessException;
import nc.vo.pub.lang.UFDouble;
import nc.vo.pubapp.pattern.exception.ExceptionUtils;
import nc.vo.pubapp.pattern.pub.MathTool;
import nc.vo.pubapp.scale.ScaleUtils;
import nc.vo.scmpub.res.billtype.ICBillType;
import nc.vo.scmpub.res.billtype.SOBillType;
import nc.vo.so.m30.entity.SaleOrderBVO;
import nc.vo.so.m30.entity.SaleOrderHVO;
import nc.vo.so.m30.entity.SaleOrderViewVO;
import nc.vo.so.m35.entity.ArsubInterfaceVO;
import nc.vo.so.m35.paravo.OffsetParaVO;
import nc.vo.so.pub.util.biz.SOBusiUtil;

/**
 * @description
 * 销售订单出库打开时处理冲抵
 * @scene
 * 销售订单出库打开后
 * @param
 * 无
 * @since 6.0
 * @version 2011-9-13 上午09:54:15
 * @author 么贵敬
 */
public class Rewrite35WhenOutOpen implements IRule<SaleOrderViewVO> {

  private UFDouble getDismny(SaleOrderBVO bvo, String corigcurrencyid,
      Map<String, Rewrite4CPara> mParas) {
    UFDouble ntotaloutnum = bvo.getNtotaloutnum();
    UFDouble norigsubmny = bvo.getNorigsubmny();
    // if (SOMathUtil.isZero(bvo.getNtotaloutnum())
    // || SOMathUtil.isZero(bvo.getNnum())) {
    // return norigsubmny;
    // }
    ScaleUtils scaleutil = ScaleUtils.getScaleUtilAtBS();
    UFDouble disnum = UFDouble.ZERO_DBL;
    if (null != mParas) {
      // 自动打开减少冲抵金额
      UFDouble oldntotaloutnum =
          MathTool.sub(ntotaloutnum, mParas.get(bvo.getCsaleorderbid())
              .getNchangenum());
      disnum = MathTool.sub(bvo.getNnum(), oldntotaloutnum);
    }
    else {
      // 手工打开增加冲抵金额
      disnum = MathTool.sub(bvo.getNnum(), ntotaloutnum);
    }
    UFDouble disnmny =
        disnum.div(bvo.getNnum()).multiply(norigsubmny);

    return scaleutil.adjustMnyScale(disnmny,
        corigcurrencyid);
  }

  /**
   * 赠品兑付流程使用
   * 
   * @param bvo
   * @param corigcurrencyid
   * @param mParas
   * @return
   */
  private UFDouble getDismnyForArsub(SaleOrderBVO bvo, String corigcurrencyid,
      Map<String, Rewrite4CPara> mParas) {
    UFDouble ntotaloutnum = bvo.getNtotaloutnum();
    UFDouble norigtaxmny = bvo.getNorigtaxmny();
    
    ScaleUtils scaleutil = ScaleUtils.getScaleUtilAtBS();
    UFDouble disnum = UFDouble.ZERO_DBL;
    if (null != mParas) {
      // 自动打开减少冲抵金额
      UFDouble oldntotaloutnum =
          MathTool.sub(ntotaloutnum, mParas.get(bvo.getCsaleorderbid())
              .getNchangenum());
      disnum = MathTool.sub(bvo.getNnum(), oldntotaloutnum);
    }
    else {
      // 手工打开增加冲抵金额
      disnum = MathTool.sub(bvo.getNnum(), ntotaloutnum);
    }
    UFDouble disnmny =
        disnum.div(bvo.getNnum()).multiply(norigtaxmny);

    return scaleutil.adjustMnyScale(disnmny,
        corigcurrencyid);
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
   * 是否需要处理出库关闭对费用冲抵的影响
   * 
   * @param vo
   * @return
   */
  private boolean isNeedProcess(SaleOrderViewVO vo,
      Map<String, Set<String>> billTypeMap) {
    SaleOrderHVO hvo = vo.getHead();
    SaleOrderBVO bvo = vo.getBody();
    // 没有做过冲抵
    if(SOStateUtil.isNotOffsetAndlrgcash(vo)){
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

  @SuppressWarnings("unchecked")
  @Override
  public void process(SaleOrderViewVO[] vos) {

    Map<String, Rewrite4CPara> mParas =
        (Map<String, Rewrite4CPara>) BSContext.getInstance().getSession(
            Rewrite4CPara.class.getName());

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

      if (arsubtypeid != null) {
        boolean isneeddo = this.isNeedProcess(vo, billTypeMap);
        if (!isneeddo) {
          continue;
        }
        // 需要调整的冲抵金额
        UFDouble dismny =
            this.getDismnyForArsub(bvo, hvo.getCorigcurrencyid(), mParas);
        if (MathTool.isZero(dismny)) {
          continue;
        }
        arsubitfvos.put(bid, itfvo);
        arsuboffsetmnymap.put(bid, dismny);
        OffsetParaVO paravo = this.getParavo(vo);
        arsuboffsetparavos.put(bid, paravo);
      }
      else {
        boolean isneeddo = this.isNeedProcess(vo, billTypeMap);
        if (!isneeddo) {
          continue;
        }
        // 需要调整的冲抵金额
        UFDouble dismny = this.getDismny(bvo, hvo.getCorigcurrencyid(), mParas);
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
