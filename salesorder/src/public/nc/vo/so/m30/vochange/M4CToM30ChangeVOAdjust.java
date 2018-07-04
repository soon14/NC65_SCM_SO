package nc.vo.so.m30.vochange;

import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;

import nc.bs.framework.common.NCLocator;
import nc.pubitf.so.m30.pub.ISaleOrderForPub;
import nc.vo.pub.BusinessException;
import nc.vo.pub.lang.UFBoolean;
import nc.vo.pubapp.pattern.exception.ExceptionUtils;
import nc.vo.pubapp.pattern.pub.PubAppTool;
import nc.vo.scmpub.res.billtype.ICBillType;
import nc.vo.so.m30.entity.SaleOrderBVO;
import nc.vo.so.m30.entity.SaleOrderHVO;
import nc.vo.so.m30.entity.SaleOrderVO;
import nc.vo.so.m30.entity.SaleOrderViewVO;
import nc.vo.so.m30.enumeration.Fretexchange;
import nc.vo.so.m30.pub.SaleOrderVOCalculator;
import nc.vo.so.m30.rule.DirectStoreRule;
import nc.vo.so.pub.keyvalue.IKeyValue;
import nc.vo.so.pub.keyvalue.VOKeyValue;
import nc.vo.so.pub.rule.BodyValueRowRule;

public class M4CToM30ChangeVOAdjust extends AbstractSaleOrderChangeVOAdjust {

  @Override
  protected String getSrcBillTypeCode() {
    return ICBillType.SaleOut.getCode();
  }

  @Override
  protected void fillRefAddValue(SaleOrderVO[] vos) {
    super.fillRefAddValue(vos);

    Map<String, String> mapchanneltype = this.getChannelType(vos);

    for (SaleOrderVO billvo : vos) {

      IKeyValue keyValue = new VOKeyValue<SaleOrderVO>(billvo);
      String channeltypeid = null;
      // 1.设置退换货标志
      int bodycount = keyValue.getBodyCount();
      for (int i = 0; i < bodycount; i++) {
        keyValue.setBodyValue(i, SaleOrderBVO.FRETEXCHANGE,
            Fretexchange.WITHDRAW.getIntegerValue());

        String csaleorderid =
            keyValue.getBodyStringValue(i, SaleOrderBVO.CFIRSTBID);
        if (mapchanneltype.containsKey(csaleorderid)) {
          channeltypeid = mapchanneltype.get(csaleorderid);
        }
      }
      BodyValueRowRule bodycouuitl = new BodyValueRowRule(keyValue);
      int[] rows = bodycouuitl.getMarNotNullRows();
      // 2.直运仓
      DirectStoreRule dirstorerule = new DirectStoreRule(keyValue);
      dirstorerule.setDirectStore(rows);

      // 3. 渠道类型
      keyValue.setHeadValue(SaleOrderHVO.CCHANNELTYPEID, channeltypeid);
    }
  }

  private Map<String, String> getChannelType(SaleOrderVO[] vos) {
    Set<String> sethids = new HashSet<String>();
    for (SaleOrderVO billvo : vos) {
      SaleOrderBVO[] salebvos = billvo.getChildrenVO();
      for (SaleOrderBVO salebvo : salebvos) {
        String salebid = salebvo.getCfirstbid();
        if (!PubAppTool.isNull(salebid)) {
          sethids.add(salebid);
        }
      }
    }
    ISaleOrderForPub squaresrv =
        NCLocator.getInstance().lookup(ISaleOrderForPub.class);
    Map<String, String> mapchanneltype = new HashMap<String, String>();
    SaleOrderViewVO[] ordervos = null;
    try {
      ordervos =
          squaresrv.querySaleOrderViewVOs(
              sethids.toArray(new String[sethids.size()]), new String[] {
                SaleOrderBVO.CSALEORDERBID, SaleOrderHVO.CCHANNELTYPEID
              });
    }
    catch (BusinessException ex) {
      ExceptionUtils.wrappException(ex);
    }
    if (ordervos == null) {
      return mapchanneltype;
    }
    for (SaleOrderViewVO orderhvo : ordervos) {
      mapchanneltype.put(orderhvo.getBody().getCsaleorderbid(), orderhvo
          .getHead().getCchanneltypeid());
    }
    return mapchanneltype;
  }

  @Override
  protected void processNumNoChangeOrder(SaleOrderVO[] nonumchgvos) {

    for (SaleOrderVO ordervo : nonumchgvos) {
      SaleOrderVOCalculator calcultor = new SaleOrderVOCalculator(ordervo);
      int ilength = ordervo.getChildrenVO().length;
      int[] rows = new int[ilength];
      for (int i = 0; i < ilength; i++) {
        rows[i] = i;
      }
      calcultor.setForceFixUnitRate(UFBoolean.TRUE);
      calcultor.calculateDiscountmny(rows, SaleOrderBVO.NORIGTAXMNY);
    }
    super.processNumNoChangeOrder(nonumchgvos);
  }

  @Override
  protected boolean isDownSymbolMinus() {
    return true;
  }

}
