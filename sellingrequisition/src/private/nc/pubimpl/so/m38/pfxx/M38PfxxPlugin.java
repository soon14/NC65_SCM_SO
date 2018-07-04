package nc.pubimpl.so.m38.pfxx;

import nc.bs.framework.common.NCLocator;
import nc.impl.pubapp.pattern.data.bill.BillQuery;
import nc.impl.pubapp.pattern.data.bill.tool.BillConcurrentTool;
import nc.itf.so.m38.IPreOrderMaintain;
import nc.pubimpl.so.pfxx.AbstractSOPfxxPlugin;
import nc.vo.pub.AggregatedValueObject;
import nc.vo.pub.BusinessException;
import nc.vo.pubapp.pattern.exception.ExceptionUtils;
import nc.vo.so.m38.entity.PreOrderVO;

public class M38PfxxPlugin extends AbstractSOPfxxPlugin {

  @Override
  protected AggregatedValueObject insert(AggregatedValueObject vo) {
    PreOrderVO ret = null;
    IPreOrderMaintain maintainservice =
        NCLocator.getInstance().lookup(IPreOrderMaintain.class);
    PreOrderVO insertvo = (PreOrderVO) vo;
    try {
      ret = maintainservice.insertPreOrder(insertvo);
    }
    catch (BusinessException e) {
      ExceptionUtils.wrappException(e);
    }
    return ret;
  }

  @Override
  protected AggregatedValueObject update(AggregatedValueObject vo, String vopk) {

    PreOrderVO[] rets = null;
    IPreOrderMaintain maintainservice =
        NCLocator.getInstance().lookup(IPreOrderMaintain.class);
    PreOrderVO[] updatevo = new PreOrderVO[] {
      (PreOrderVO) vo
    };
    BillQuery<PreOrderVO> billquery =
        new BillQuery<PreOrderVO>(PreOrderVO.class);
    PreOrderVO[] originBill = billquery.query(new String[] {
      vopk
    });
    BillConcurrentTool tool = new BillConcurrentTool();
    tool.lockBill(originBill);
    try {
      rets = maintainservice.updatePreOrder(updatevo, originBill);
    }
    catch (BusinessException e) {
      ExceptionUtils.wrappException(e);
    }
    return null == rets ? null : rets[0];
  }

}
