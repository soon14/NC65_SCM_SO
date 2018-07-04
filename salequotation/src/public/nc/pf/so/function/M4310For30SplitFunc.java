package nc.pf.so.function;

import java.util.List;

import nc.bs.framework.common.NCLocator;
import nc.pubitf.so.m30.split.ISaleOrderOrgRelaSplit;
import nc.vo.pub.AggregatedValueObject;
import nc.vo.pub.BusinessException;
import nc.vo.so.salequotation.entity.AggSalequotationHVO;

public class M4310For30SplitFunc {

  public List<String> splitBySendStockOrg(AggregatedValueObject vo)
      throws BusinessException {
    AggSalequotationHVO salequotavo = (AggSalequotationHVO) vo;
    M4310For30SplitPara splitpara = new M4310For30SplitPara(salequotavo);

    ISaleOrderOrgRelaSplit splitservice =
        NCLocator.getInstance().lookup(ISaleOrderOrgRelaSplit.class);
    return splitservice.splitBySendStockOrg(splitpara);

  }

  public List<String> splitByTrafficOrg(AggregatedValueObject vo)
      throws BusinessException {
    AggSalequotationHVO salequotavo = (AggSalequotationHVO) vo;
    M4310For30SplitPara splitpara = new M4310For30SplitPara(salequotavo);

    ISaleOrderOrgRelaSplit splitservice =
        NCLocator.getInstance().lookup(ISaleOrderOrgRelaSplit.class);
    return splitservice.splitByTrafficOrg(splitpara);

  }

  public List<String> splitBySettleOrg(AggregatedValueObject vo)
      throws BusinessException {
    AggSalequotationHVO salequotavo = (AggSalequotationHVO) vo;
    M4310For30SplitPara splitpara = new M4310For30SplitPara(salequotavo);

    ISaleOrderOrgRelaSplit splitservice =
        NCLocator.getInstance().lookup(ISaleOrderOrgRelaSplit.class);
    return splitservice.splitBySettleOrg(splitpara);

  }

  public List<String> splitByArOrg(AggregatedValueObject vo)
      throws BusinessException {
    AggSalequotationHVO salequotavo = (AggSalequotationHVO) vo;
    M4310For30SplitPara splitpara = new M4310For30SplitPara(salequotavo);

    ISaleOrderOrgRelaSplit splitservice =
        NCLocator.getInstance().lookup(ISaleOrderOrgRelaSplit.class);
    return splitservice.splitByArOrg(splitpara);

  }

}
