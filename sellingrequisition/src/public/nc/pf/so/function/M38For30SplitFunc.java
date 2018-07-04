package nc.pf.so.function;

import java.util.List;

import nc.bs.framework.common.NCLocator;
import nc.pubitf.so.m30.split.ISaleOrderOrgRelaSplit;
import nc.vo.pub.AggregatedValueObject;
import nc.vo.pub.BusinessException;
import nc.vo.so.m38.entity.PreOrderVO;
/**
 * 预订单为销售订单提供的按照订单业务委托关系匹配后分单函数
 * 
 * @since 6.0
 * @version 2011-6-30 下午06:15:52
 * @author fengjb
 */
public class M38For30SplitFunc {

  public List<String> splitBySendStockOrg(AggregatedValueObject vo)
      throws BusinessException {
    PreOrderVO preordervo = (PreOrderVO) vo;
    M38For30SplitPara splitpara = new M38For30SplitPara(preordervo);

    ISaleOrderOrgRelaSplit splitservice =
        NCLocator.getInstance().lookup(ISaleOrderOrgRelaSplit.class);
    return splitservice.splitBySendStockOrg(splitpara);

  }

  public List<String> splitByTrafficOrg(AggregatedValueObject vo)
      throws BusinessException {
    PreOrderVO preordervo = (PreOrderVO) vo;
    M38For30SplitPara splitpara = new M38For30SplitPara(preordervo);

    ISaleOrderOrgRelaSplit splitservice =
        NCLocator.getInstance().lookup(ISaleOrderOrgRelaSplit.class);
    return splitservice.splitByTrafficOrg(splitpara);

  }

  public List<String> splitBySettleOrg(AggregatedValueObject vo)
      throws BusinessException {
    PreOrderVO preordervo = (PreOrderVO) vo;
    M38For30SplitPara splitpara = new M38For30SplitPara(preordervo);

    ISaleOrderOrgRelaSplit splitservice =
        NCLocator.getInstance().lookup(ISaleOrderOrgRelaSplit.class);
    return splitservice.splitBySettleOrg(splitpara);

  }

  public List<String> splitByArOrg(AggregatedValueObject vo)
      throws BusinessException {
    PreOrderVO preordervo = (PreOrderVO) vo;
    M38For30SplitPara splitpara = new M38For30SplitPara(preordervo);

    ISaleOrderOrgRelaSplit splitservice =
        NCLocator.getInstance().lookup(ISaleOrderOrgRelaSplit.class);
    return splitservice.splitByArOrg(splitpara);

  }
}
