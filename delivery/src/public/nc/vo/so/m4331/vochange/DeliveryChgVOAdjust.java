package nc.vo.so.m4331.vochange;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import nc.bs.framework.common.NCLocator;
import nc.impl.pubapp.bd.userdef.UserDefCheckUtils;
import nc.pubitf.so.m30.pub.ISaleOrderForPub;
import nc.vo.pf.change.ChangeVOAdjustContext;
import nc.vo.pf.change.IChangeVOAdjust;
import nc.vo.pub.AggregatedValueObject;
import nc.vo.pub.BusinessException;
import nc.vo.pub.CircularlyAccessibleValueObject;
import nc.vo.pub.lang.UFBoolean;
import nc.vo.pubapp.pattern.exception.ExceptionUtils;
import nc.vo.so.m30.entity.SaleOrderBVO;
import nc.vo.so.m30.entity.SaleOrderVO;
import nc.vo.so.m30.entity.SaleOrderViewVO;
import nc.vo.so.m4331.entity.DeliveryBVO;
import nc.vo.so.m4331.entity.DeliveryHVO;
import nc.vo.so.m4331.entity.DeliveryVO;
import nc.vo.so.m4331.rule.FillDeFaultDataRule;
import nc.vo.so.pub.SOConstant;
import nc.vo.so.pub.comparator.BillNOandRowNoComparator;
import nc.vo.to.m5x.entity.BillItemVO;
import nc.vo.to.m5x.entity.BillVO;

public class DeliveryChgVOAdjust implements IChangeVOAdjust {

  @Override
  public AggregatedValueObject adjustAfterChange(AggregatedValueObject srcVO,
      AggregatedValueObject destVO, ChangeVOAdjustContext adjustContext)
      throws BusinessException {
    AggregatedValueObject[] srcVOs = new AggregatedValueObject[] {
      srcVO
    };

    AggregatedValueObject[] destVOs = new AggregatedValueObject[] {
      destVO
    };
    this.batchAdjustAfterChange(srcVOs, destVOs, adjustContext);
    return destVO;
  }

  @Override
  public AggregatedValueObject adjustBeforeChange(AggregatedValueObject srcVO,
      ChangeVOAdjustContext adjustContext) throws BusinessException {
    return null;
  }

  @Override
  public AggregatedValueObject[] batchAdjustAfterChange(
      AggregatedValueObject[] srcVOs, AggregatedValueObject[] destVOs,
      ChangeVOAdjustContext adjustContext) throws BusinessException {

    // modify by jilu for EHP1合盘到633 20140703
    // 推单的时候上游销售订单劳务折扣行 审批会做发货关闭，所以下游要去掉相应的行
    Map<String, UFBoolean> upmap = this.getUpbboutendflagMap(srcVOs);
    DeliveryVO[] vos = (DeliveryVO[]) destVOs;
    destVOs = this.fiterDestVOs(upmap, destVOs);
    // 根据来源单据号和行号排序，保证与上游单据的行顺序
    for (DeliveryVO vo : vos) {
      BillNOandRowNoComparator c = new BillNOandRowNoComparator();
      Arrays.sort(vo.getChildrenVO(), c);
    }
    FillDeFaultDataRule util = new FillDeFaultDataRule();
    util.setDefaultData(srcVOs, destVOs);
   // this.checkVOS(vos);
    return destVOs;
  }

  @Override
  public AggregatedValueObject[] batchAdjustBeforeChange(
      AggregatedValueObject[] srcVOs, ChangeVOAdjustContext adjustContext)
      throws BusinessException {
    return null;
  }

  /**
   * 过滤下游单据
   * 如果上游订单发货关闭 （销售订单劳务折扣行审批会做发货关闭）就去掉下游相应的行
   * 
   * @param upmap
   * @param destVOs
   */
  private AggregatedValueObject[] fiterDestVOs(Map<String, UFBoolean> upmap,
      AggregatedValueObject[] destVOs) {
    for (AggregatedValueObject destVO : destVOs) {
      List<DeliveryBVO> bvolist = new ArrayList<DeliveryBVO>();
      CircularlyAccessibleValueObject[] bvos = destVO.getChildrenVO();
      for (CircularlyAccessibleValueObject bvo : bvos) {
        String srcid = (String) bvo.getAttributeValue(DeliveryBVO.CSRCBID);
        UFBoolean bbsendendflag = upmap.get(srcid);
        if (null == bbsendendflag || !bbsendendflag.booleanValue()) {
          bvolist.add((DeliveryBVO) bvo);
        }
      }
      destVO.setChildrenVO(bvolist.toArray(new DeliveryBVO[bvolist.size()]));
    }
    boolean allNull = true;
    for (AggregatedValueObject destVO : destVOs) {
      if (destVO.getChildrenVO().length > 0 ) {
        allNull = false;
        break;
      }
    }
    if (allNull) {
      return null;
    }else{
      return destVOs;
    }
  }

  @SuppressWarnings("rawtypes")
  private void checkVOS(DeliveryVO[] destVOs) {
    if (destVOs == null) {
      return;
    }
    String[] ruleCodes = new String[] {
      "4331_H", "4331_B"
    };
    String[] prefixs = new String[] {
      SOConstant.VDEF, SOConstant.VBDEF
    };
    Class[] voClasses = new Class[] {
      DeliveryHVO.class, DeliveryBVO.class
    };
    UserDefCheckUtils.check(destVOs, ruleCodes, prefixs, voClasses);
  }

  /**
   * 获取上游订单的发货关闭状态
   * 
   * @param srcVOs
   * @return
   */
  private Map<String, UFBoolean> getUpbboutendflagMap(
      AggregatedValueObject[] srcVOs) {
    Map<String, UFBoolean> upmap = new HashMap<String, UFBoolean>();
    List<String> sobids = new ArrayList<String>();
    for (AggregatedValueObject srcVO : srcVOs) {
      if (srcVO instanceof SaleOrderVO) {
        CircularlyAccessibleValueObject[] bvos = srcVO.getChildrenVO();
        for (CircularlyAccessibleValueObject bvo : bvos) {
          sobids
              .add((String) bvo.getAttributeValue(SaleOrderBVO.CSALEORDERBID));
        }
      }
      // 来源调拨订单情况
      else if (srcVO instanceof BillVO) {
        CircularlyAccessibleValueObject[] bvos = srcVO.getChildrenVO();
        for (CircularlyAccessibleValueObject bvo : bvos) {
          upmap.put((String) bvo.getAttributeValue(BillItemVO.CBILL_BID),
              (UFBoolean) bvo.getAttributeValue(BillItemVO.BSENDENDFLAG));
        }
      }
    }
    if (sobids.size() > 0) {
      ISaleOrderForPub squaresrv =
          NCLocator.getInstance().lookup(ISaleOrderForPub.class);
      try {
        SaleOrderViewVO[] viewvos =
            squaresrv.querySaleOrderViewVOs(
                sobids.toArray(new String[sobids.size()]), new String[] {
                  SaleOrderBVO.CSALEORDERBID, SaleOrderBVO.BBSENDENDFLAG
                });

        for (SaleOrderViewVO viewvo : viewvos) {
          upmap.put(viewvo.getBody().getCsaleorderbid(), viewvo.getBody()
              .getBbsendendflag());
        }

      }
      catch (BusinessException e) {
        ExceptionUtils.wrappException(e);
      }
    }

    return upmap;
  }

}
