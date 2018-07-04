package nc.bs.so.m32.maintain.rule.insert;

import java.util.HashMap;
import java.util.Map;

import nc.bs.framework.common.NCLocator;
import nc.impl.pubapp.pattern.rule.IRule;
import nc.pubitf.so.m35.so.m32.IArsubToSaleInvoice;
import nc.vo.pub.BusinessException;
import nc.vo.pub.lang.UFDouble;
import nc.vo.pubapp.pattern.exception.ExceptionUtils;
import nc.vo.scmpub.res.billtype.SOBillType;
import nc.vo.so.m32.entity.SaleInvoiceBVO;
import nc.vo.so.m32.entity.SaleInvoiceHVO;
import nc.vo.so.m32.entity.SaleInvoiceVO;
import nc.vo.so.m32.enumeration.OpposeFlag;
import nc.vo.so.m35.entity.ArsubInterfaceVO;
import nc.vo.so.m35.paravo.OffsetParaVO;
import nc.vo.so.pub.util.SOMathUtil;

/**
 * @description
 * 销售发票保存后生成对冲发票时反冲抵
 * @scene
 * 销售发票新增保存后
 * @param
 * 无
 * @since 6.0
 * @version 2012-4-18 上午08:17:14
 * @author 么贵敬
 */
public class OppOffsetRule implements IRule<SaleInvoiceVO> {

  @Override
  public void process(SaleInvoiceVO[] vos) {
    Map<String, ArsubInterfaceVO> itfvos =
        new HashMap<String, ArsubInterfaceVO>();

    Map<String, UFDouble> offsetmnymap = new HashMap<String, UFDouble>();
    Map<String, OffsetParaVO> offsetparavos =
        new HashMap<String, OffsetParaVO>();
    this.prepareParas(vos, itfvos, offsetparavos, offsetmnymap);

    if (itfvos.size() == 0) {
      return;
    }
    IArsubToSaleInvoice srv =
        NCLocator.getInstance().lookup(IArsubToSaleInvoice.class);
    try {
      srv.processOffsetWithOpp(itfvos, offsetparavos, offsetmnymap);
    }
    catch (BusinessException e) {
      ExceptionUtils.wrappException(e);
    }
  }

  private void prepareParas(SaleInvoiceVO[] vos,
      Map<String, ArsubInterfaceVO> itfvos,
      Map<String, OffsetParaVO> offsetparavos,
      Map<String, UFDouble> offsetmnymap) {

    for (SaleInvoiceVO vo : vos) {

      SaleInvoiceHVO hvo = vo.getParentVO();
      SaleInvoiceBVO[] bvos = vo.getChildrenVO();
      if (!OpposeFlag.GENERAL.equalsValue(hvo.getFopposeflag())) {
        continue;
      }
      // 没有做过冲抵
      if (null == hvo.getBsubunitflag()
          || !hvo.getBsubunitflag().booleanValue()) {
        continue;
      }
      for (SaleInvoiceBVO bvo : bvos) {
        // 冲抵金额为0
        if (SOMathUtil.isZero(bvo.getNorigsubmny())) {
          continue;
        }
        String bid = bvo.getCsaleinvoicebid();
        offsetmnymap.put(bid, bvo.getNorigsubmny());

        ArsubInterfaceVO itfvo = new ArsubInterfaceVO();
        itfvo.setCsalebillid(bvo.getCsaleinvoiceid());
        itfvo.setVbillcode(hvo.getVbillcode());
        itfvo.setVbilltype(SOBillType.Invoice.getCode());
        itfvos.put(bid, itfvo);

        OffsetParaVO paravo = new OffsetParaVO();
        paravo.setCorigcurrencyid(hvo.getCorigcurrencyid());
        paravo.setInvoicecusts(hvo.getCinvoicecustid());
        paravo.setOrdercusts(bvo.getCordercustid());
        paravo.setOrdertrantype(bvo.getVfirsttrantype());
        paravo.setProdline(bvo.getCprodlineid());
        paravo.setSaleorg(bvo.getPk_org());
        paravo.setSettleorg(bvo.getPk_org());
        offsetparavos.put(bvo.getCsaleinvoicebid(), paravo);
      }
    }
  }

}
