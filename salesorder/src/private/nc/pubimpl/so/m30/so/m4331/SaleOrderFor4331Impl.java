package nc.pubimpl.so.m30.so.m4331;

import java.util.HashMap;
import java.util.Map;

import nc.impl.pubapp.pattern.data.view.ViewQuery;
import nc.impl.pubapp.pattern.data.vo.VOQuery;
import nc.pubitf.so.m30.so.m4331.ISaleOrderFor4331;
import nc.vo.pub.BusinessException;
import nc.vo.pub.lang.UFBoolean;
import nc.vo.pub.lang.UFDouble;
import nc.vo.pubapp.pattern.pub.MathTool;
import nc.vo.so.m30.entity.SaleOrderBVO;
import nc.vo.so.m30.entity.SaleOrderViewVO;

public class SaleOrderFor4331Impl implements ISaleOrderFor4331 {

  @Override
  public Map<String, UFBoolean> queryIsReserved(String[] bids)
      throws BusinessException {
    // Map<销售订单bid, 是否预留>
    Map<String, UFBoolean> returnMap = new HashMap<String, UFBoolean>();
    if (bids == null || bids.length == 0) {
      return returnMap;
    }
    // 查询订单行预留数量
    VOQuery<SaleOrderBVO> bvoQuery =
        new VOQuery<SaleOrderBVO>(SaleOrderBVO.class, new String[] {
          SaleOrderBVO.CSALEORDERBID, SaleOrderBVO.NREQRSNUM
        });
    SaleOrderBVO[] bvos = bvoQuery.query(bids);
    // 组织返回map
    if (bvos != null && bvos.length > 0) {
      for (SaleOrderBVO bvo : bvos) {
        UFBoolean bReserved =
            (bvo.getNreqrsnum() == null || MathTool.equals(bvo.getNreqrsnum(),
                UFDouble.ZERO_DBL)) ? UFBoolean.FALSE : UFBoolean.TRUE;
        returnMap.put(bvo.getCsaleorderbid(), bReserved);
      }
    }
    return returnMap;
  }

  @Override
  public Object[] querySaleOrderViewVOsFor4331Arrange(String[] bids)
      throws BusinessException {
    SaleOrderViewVO[] ret = null;
    if (bids != null && bids.length > 0) {
      ret = new ViewQuery<SaleOrderViewVO>(SaleOrderViewVO.class).query(bids);
    }
    return ret;
  }
}
