package nc.vo.so.m4331.rule;

import java.util.HashSet;
import java.util.Set;

import nc.itf.pubapp.margin.IMarginDudgement;
import nc.vo.pubapp.margin.BillMarginContext;
import nc.vo.pubapp.margin.MarginContextFactory;
import nc.vo.pubapp.margin.MarginEntry;
import nc.vo.pubapp.pattern.exception.ExceptionUtils;
import nc.vo.scmpub.margin.SCMMuiltyWordMarginJudgement;
import nc.vo.scmpub.res.billtype.SOBillType;
import nc.vo.scmpub.res.billtype.TOBillType;
import nc.vo.so.m30.entity.SaleOrderBVO;
import nc.vo.so.m4331.entity.DeliveryBVO;
import nc.vo.so.m4331.entity.DeliveryVO;
import nc.vo.to.m5x.entity.BillItemVO;

/**
 * 发货单尾差处理规则
 * 
 * @since 6.0
 * @version 2011-4-29 下午03:03:04
 * @author 祝会征
 */
public class DeliveryMarginRule {

  private final String[] destMnyForOrder = new String[] {
    DeliveryBVO.NORIGMNY, DeliveryBVO.NORIGTAXMNY, DeliveryBVO.NORIGDISCOUNT,
    DeliveryBVO.NMNY, DeliveryBVO.NTAXMNY, DeliveryBVO.NTAX,
    DeliveryBVO.NDISCOUNT, DeliveryBVO.NGROUPMNY, DeliveryBVO.NGROUPTAXMNY,
    DeliveryBVO.NGLOBALMNY, DeliveryBVO.NGLOBALTAXMNY
  };

  private final String[] destMnyForTran = new String[] {
    DeliveryBVO.NORIGMNY, DeliveryBVO.NORIGTAXMNY,
    DeliveryBVO.NMNY, DeliveryBVO.NTAXMNY, DeliveryBVO.NTAX
  };

  private final String[] srcorderMNY = new String[] {
    SaleOrderBVO.NORIGMNY, SaleOrderBVO.NORIGTAXMNY,
    SaleOrderBVO.NORIGDISCOUNT, SaleOrderBVO.NMNY, SaleOrderBVO.NTAXMNY,
    SaleOrderBVO.NTAX, SaleOrderBVO.NDISCOUNT, SaleOrderBVO.NGROUPMNY,
    SaleOrderBVO.NGROUPTAXMNY, SaleOrderBVO.NGLOBALMNY,
    SaleOrderBVO.NGLOBALTAXMNY
  };

  private final String[] srctranMNY = new String[] {
    BillItemVO.NORIGMNY, BillItemVO.NORIGTAXMNY,
    BillItemVO.NMNY, BillItemVO.NTAXMNY, BillItemVO.NTAX
  };

  public void process(DeliveryVO[] vos) {
    try {
      if(vos==null){
        return;
      }
      Set<DeliveryVO> orderSet = new HashSet<DeliveryVO>();
      Set<DeliveryVO> tranSet = new HashSet<DeliveryVO>();
      for (DeliveryVO vo : vos) {
        if(vo.getChildrenVO()==null||vo.getChildrenVO().length==0){
          return;
        }
        String billtype = vo.getChildrenVO()[0].getVsrctype();
        if (SOBillType.Order.getCode().equals(billtype)) {
          orderSet.add(vo);
        }
        else {
          tranSet.add(vo);
        }
      }
      if (orderSet.size() > 0) {
    	  IMarginDudgement margindudgement =
              new SCMMuiltyWordMarginJudgement(new String[]{SaleOrderBVO.NORIGNETPRICE, SaleOrderBVO.NORIGTAXNETPRICE},
                  new String[]{DeliveryBVO.NORIGNETPRICE, DeliveryBVO.NORIGTAXNETPRICE});
        DeliveryVO[] newvos = new DeliveryVO[orderSet.size()];
        newvos = orderSet.toArray(newvos);
        BillMarginContext context =
            MarginContextFactory.getInstance().produceMarginContext(newvos,
                SOBillType.Order.getCode(), SaleOrderBVO.NNUM,
                this.srcorderMNY, SOBillType.Delivery.getCode(),
                DeliveryBVO.NNUM, this.destMnyForOrder, DeliveryBVO.CSRCBID,
                DeliveryBVO.CSRCID, margindudgement);
        MarginEntry.getInstance().process(context);
      }
      if (tranSet.size() > 0) {
	    IMarginDudgement margindudgement =
            new SCMMuiltyWordMarginJudgement(new String[]{BillItemVO.NORIGNETPRICE, BillItemVO.NORIGTAXNETPRICE},
                new String[]{DeliveryBVO.NORIGNETPRICE, DeliveryBVO.NORIGTAXNETPRICE});
        DeliveryVO[] newvos = new DeliveryVO[tranSet.size()];
        newvos = tranSet.toArray(newvos);
        BillMarginContext context =
            MarginContextFactory.getInstance().produceMarginContext(newvos,
                TOBillType.TransOrder.getCode(), BillItemVO.NNUM,
                this.srctranMNY, SOBillType.Delivery.getCode(),
                DeliveryBVO.NNUM, this.destMnyForTran, DeliveryBVO.CSRCBID,
                DeliveryBVO.CSRCID, margindudgement);
        MarginEntry.getInstance().process(context);
      }
    }
    catch (Exception e) {
      ExceptionUtils.wrappException(e);
    }
  }
}
