package nc.pubimpl.so.m33.ic.m4c;

import java.util.Map;

import nc.bs.framework.common.NCLocator;
import nc.pubitf.so.m33.ic.m4c.ICheckCostRegionFor4C;
import nc.pubitf.so.m33.self.pub.ISquare4332Query;
import nc.vo.pub.BusinessException;
import nc.vo.pubapp.pattern.exception.ExceptionUtils;
import nc.vo.so.m33.m32.entity.SquareInvViewVO;

public class CheckCRegionFor4CImpl implements ICheckCostRegionFor4C {

  @Override
  public void checkCostRegion(Map<String, String> srcbidCostregid)
      throws BusinessException {

    // 参数为空，说明出库单不是来源于发票
    if ((srcbidCostregid == null) || (srcbidCostregid.size() == 0)) {
      return;
    }

    // 销售发票结算查询接口
    ISquare4332Query square4cQry =
        NCLocator.getInstance().lookup(ISquare4332Query.class);
    String[] bidValues = srcbidCostregid.keySet().toArray(new String[0]);
    SquareInvViewVO[] svvos =
        square4cQry.queryCostSquareInvVOBy32BID(bidValues);

    // 销售发票没有参数成本结算
    if ((svvos == null) || (svvos.length == 0)) {
      return;
    }

    for (SquareInvViewVO vviewvo : svvos) {
      String bid = vviewvo.getItem().getCsalesquarebid();
      String costRegoin = vviewvo.getItem().getCcostorgid();
      if (!costRegoin.equals(srcbidCostregid.get(bid))) {

        ExceptionUtils.wrappBusinessException(nc.vo.ml.NCLangRes4VoTransl
            .getNCLangRes().getStrByID("4006010_0", "04006010-0057")
        /*@res "销售出库单表体结算成本域与上游销售发票成本结算生成的成本域不一致！"*/);
      }
    }

  }

}
