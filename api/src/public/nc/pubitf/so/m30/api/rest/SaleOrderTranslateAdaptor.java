package nc.pubitf.so.m30.api.rest;

import nc.vo.pubapp.pattern.model.entity.bill.IBill;
import nc.vo.scmpub.util.translate.BillTranslator;
import nc.vo.scmpub.util.translate.MDTranslateParamProvider;
import nc.vo.so.m30.entity.SaleOrderVO;

/**
 * @description
 *
 * @scene
 * 
 * @param
 * 
 *
 * @since 6.5
 * @version 2015年11月14日 下午3:09:45
 * @author wangweir
 */

public class SaleOrderTranslateAdaptor {

  public SaleOrderVO[] doTranslate(SaleOrderVO[] bills) {
    MDTranslateParamProvider<IBill> provider = new MDTranslateParamProvider<>();

    BillTranslator billTanslator = new BillTranslator();
    billTanslator.translateCodeToId(bills, provider);
    return bills;
  }
}
