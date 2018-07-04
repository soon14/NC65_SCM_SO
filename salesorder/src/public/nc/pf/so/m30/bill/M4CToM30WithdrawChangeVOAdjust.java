/**
 * $文件说明$
 * 
 * @author gdsjw
 * @version
 * @see
 * @since
 * @time 2010-6-2 上午10:08:04
 */
package nc.pf.so.m30.bill;

import nc.bs.framework.common.InvocationInfoProxy;
import nc.bs.framework.common.NCLocator;
import nc.itf.scmpub.reference.uap.pf.PfBillItfDefUtil;
import nc.itf.scmpub.reference.uap.pf.TransTypeMapping;
import nc.itf.uap.pf.IPFConfig;
import nc.vo.pf.change.ChangeVOAdjustContext;
import nc.vo.pf.change.IChangeVOAdjust;
import nc.vo.pub.AggregatedValueObject;
import nc.vo.pub.BusinessException;
import nc.vo.pub.lang.UFDouble;
import nc.vo.scmpub.res.billtype.SOBillType;
import nc.vo.so.m30.entity.SaleOrderBVO;
import nc.vo.so.m30.entity.SaleOrderHVO;
import nc.vo.so.m30.entity.SaleOrderVO;
import nc.vo.so.m30.enumeration.Fretexchange;
import nc.vo.so.pub.enumeration.BillStatus;

/**
 * <p>
 * <b>本类主要完成以下功能：</b>
 * 
 * <ul>
 * <li>功能条目1
 * <li>功能条目2
 * <li>...
 * </ul>
 * 
 * <p>
 * <b>变更历史（可选）：</b>
 * <p>
 * XXX版本增加XXX的支持。
 * <p>
 * <p>
 * 
 * @version 本版本号
 * @since 上一版本号
 * @author gdsjw
 * @time 2010-6-2 上午10:08:04
 */
public class M4CToM30WithdrawChangeVOAdjust implements IChangeVOAdjust {

  private static final UFDouble ONEHUND = new UFDouble(100);

  @Override
  public AggregatedValueObject adjustAfterChange(AggregatedValueObject srcVO,
      AggregatedValueObject destVO, ChangeVOAdjustContext adjustContext)
      throws BusinessException {
    return this.batchAdjustAfterChange(new AggregatedValueObject[] {
      srcVO
    }, new AggregatedValueObject[] {
      destVO
    }, adjustContext)[0];
  }

  @Override
  public AggregatedValueObject adjustBeforeChange(AggregatedValueObject srcVO,
      ChangeVOAdjustContext adjustContext) throws BusinessException {
    return this.batchAdjustBeforeChange(new AggregatedValueObject[] {
      srcVO
    }, adjustContext)[0];
  }

  @Override
  public AggregatedValueObject[] batchAdjustAfterChange(
      AggregatedValueObject[] srcVOs, AggregatedValueObject[] destVOs,
      ChangeVOAdjustContext adjustContext) throws BusinessException {
    for (AggregatedValueObject aggvo : destVOs) {
      SaleOrderVO salebillvo = (SaleOrderVO) aggvo;
      SaleOrderHVO headvo = salebillvo.getParentVO();
      SaleOrderBVO[] bodyvos = salebillvo.getChildrenVO();
      String pk_group = headvo.getPk_group();
      String csaleorgid = headvo.getPk_org();
      InvocationInfoProxy proxy = InvocationInfoProxy.getInstance();
      String userId = proxy.getUserId();
      // 根据来源订单类型确定退货订单类型
      String srcTranType = bodyvos[0].getVsrctrantype();
      String srcBillType = bodyvos[0].getVsrctype();
      TransTypeMapping mapping = new TransTypeMapping();
      mapping.setSrcBillType(srcBillType);
      mapping.setSrcTransType(srcTranType);
      mapping.setDestBillType(SOBillType.Order.getCode());
      PfBillItfDefUtil.queryTransTypeMapping(pk_group, mapping);
      String destTransType = mapping.getDestTransType();
      headvo.setVtrantypecode(destTransType);
      // 业务流程
      String cbiztypeid =
          this.retBusitypeCanStart(SOBillType.Order.getCode(), destTransType,
              csaleorgid, userId);
      headvo.setCbiztypeid(cbiztypeid);
      // 单据状态
      headvo.setFstatusflag(BillStatus.FREE.getIntegerValue());
      //
      headvo.setNdiscountrate(M4CToM30WithdrawChangeVOAdjust.ONEHUND);
      for (SaleOrderBVO bodyvo : bodyvos) {
        // 退换货标记
        bodyvo.setFretexchange((Integer) Fretexchange.WITHDRAW.value());
        //
        bodyvo.setNdiscountrate(M4CToM30WithdrawChangeVOAdjust.ONEHUND);
        bodyvo.setNitemdiscountrate(M4CToM30WithdrawChangeVOAdjust.ONEHUND);
      }
    }
    return destVOs;
  }

  @Override
  public AggregatedValueObject[] batchAdjustBeforeChange(
      AggregatedValueObject[] srcVOs, ChangeVOAdjustContext adjustContext)
      throws BusinessException {
    return srcVOs;
  }

  private String retBusitypeCanStart(String billtype, String transtype,
      String pk_org, String userId) throws BusinessException {

    return NCLocator.getInstance().lookup(IPFConfig.class)
        .retBusitypeCanStart(billtype, transtype, pk_org, userId);
  }

}
