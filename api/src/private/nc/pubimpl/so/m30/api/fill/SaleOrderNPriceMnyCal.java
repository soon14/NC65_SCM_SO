package nc.pubimpl.so.m30.api.fill;

import nc.bs.framework.common.InvocationInfoProxy;
import nc.pubitf.uapbd.IMaterialPubService_C;
import nc.vo.pub.lang.UFBoolean;
import nc.vo.scmpub.fill.pricemny.AbstractNPMnyCalculator;
import nc.vo.scmpub.fill.pricemny.ICalculator;
import nc.vo.scmpub.fill.pricemny.IFindPrice;
import nc.vo.so.m30.entity.SaleOrderBVO;
import nc.vo.so.m30.entity.SaleOrderVO;
import nc.vo.so.pub.keyvalue.IKeyValue;
import nc.vo.so.pub.keyvalue.VOKeyValue;
import nc.vo.so.pub.rule.BodyValueRowRule;
import nc.vo.so.pub.rule.SOTaxInfoRule;
import nc.vo.so.pub.util.ArrayUtil;
import nc.vo.so.pub.util.SOSysParaInitUtil;

/**
 * @description
 * 销售订单数量、单价、金额计算
 * @scene
 *
 * @param
 *
 *
 * @since 6.5
 * @version 2015-12-10 上午10:07:25
 * @author 刘景
 * @param <E>
 */
public class SaleOrderNPriceMnyCal<E> extends
    AbstractNPMnyCalculator<SaleOrderVO> {

  public SaleOrderNPriceMnyCal(
      SaleOrderVO[] e) {
    super(e);
  }

  @Override
  public void findTaxInfo(SaleOrderVO[] vos) {
    for (SaleOrderVO salebillvo : vos) {
      IKeyValue keyValue = new VOKeyValue<SaleOrderVO>(salebillvo);
      BodyValueRowRule bodycouuitl = new BodyValueRowRule(keyValue);
      int[] sendstockrows =
          bodycouuitl.getValueNullRows(SaleOrderBVO.CSENDSTOCKORGVID);
      int[] finacerows =
          bodycouuitl.getValueNullRows(SaleOrderBVO.CSETTLEORGVID);
      int[] needchangerows = ArrayUtil.combinArrays(sendstockrows, finacerows);
      SOTaxInfoRule taxInfo = new SOTaxInfoRule(keyValue);
      taxInfo.setTaxInfoByBodyPos(needchangerows);
    }

  }

  @Override
  public IFindPrice getFindPrice() {
    return new SaleOrderFindPrice();
  }

  @Override
  public int getUnitType() {
    return IMaterialPubService_C.MATERIAL_CONVERT_SALE;
  }

  @Override
  public ICalculator getCalculator() {
    return new SaleOrderCalculator();
  }

  public boolean isTaxPrior() {
    String pk_group = InvocationInfoProxy.getInstance().getGroupId();
    UFBoolean istaxprior = SOSysParaInitUtil.getSO23(pk_group);
    if (istaxprior.booleanValue()) {
      return true;
    }
    return false;
  }

}
