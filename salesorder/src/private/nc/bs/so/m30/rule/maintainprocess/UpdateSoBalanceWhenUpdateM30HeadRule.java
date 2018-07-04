package nc.bs.so.m30.rule.maintainprocess;

import java.util.HashMap;
import java.util.Map;

import nc.bs.framework.common.NCLocator;
import nc.impl.pubapp.pattern.data.vo.VOUpdate;
import nc.impl.pubapp.pattern.rule.IRule;
import nc.itf.so.m30.sobalance.ISOBalanceQuery;
import nc.vo.pub.BusinessException;
import nc.vo.pub.lang.UFDouble;
import nc.vo.pubapp.pattern.exception.ExceptionUtils;
import nc.vo.pubapp.pattern.pub.MathTool;
import nc.vo.so.m30.entity.SaleOrderHVO;
import nc.vo.so.m30.entity.SaleOrderVO;
import nc.vo.so.m30.sobalance.entity.SoBalanceHVO;
import nc.vo.so.m30.sobalance.entity.SoBalanceVO;
import nc.vo.so.pub.util.AggVOUtil;
import nc.vo.so.pub.util.SOVOChecker;

/**
 * @description
 * 销售订单修改保存前，如果已经有收款核销关系，用订单总金额更新订单核销表头订单总金额
 * @scene
 * 销售订单修改保存前，如果已经有收款核销关系
 * @param 
 * 无
 */
public class UpdateSoBalanceWhenUpdateM30HeadRule implements IRule<SaleOrderVO>{

  @Override
  public void process(SaleOrderVO[] vos) {
    Map<String,SaleOrderVO> map = new HashMap<String,SaleOrderVO>();
    for (SaleOrderVO svo : vos){
      UFDouble nreceivedmny = svo.getParentVO().getNreceivedmny();
      if (!SOVOChecker.isEmpty(nreceivedmny)&& !MathTool.isZero(nreceivedmny))
      map.put(svo.getParentVO().getCsaleorderid(), svo);
    }
    
    if (map.size()>0){
      String[] csaleorderids = AggVOUtil.getDistinctHeadFieldArray(vos, SaleOrderHVO.CSALEORDERID, String.class);
      ISOBalanceQuery queryservice =
        NCLocator.getInstance().lookup(ISOBalanceQuery.class);
      SoBalanceVO[] balanvos = null;
      try {
        balanvos = queryservice.querySoBalanceVOBySaleOrderIDs(csaleorderids);
      }
      catch (BusinessException e) {
        ExceptionUtils.wrappException(e);
      }
      if (SOVOChecker.isEmpty(balanvos)){
        return;
      }
      
      SoBalanceHVO[] hvos = new SoBalanceHVO[balanvos.length];
      int i=0;
      for (SoBalanceVO svo: balanvos){
        SaleOrderHVO orderHvo = map.get(svo.getParentVO().getCsaleorderid()).getParentVO();
        svo.getParentVO().setNtotalorigtaxmny(orderHvo.getNtotalorigmny());
        hvos[i] = svo.getParentVO();
        i++;
      }
      new VOUpdate<SoBalanceHVO>().update(hvos, new String[]{SoBalanceHVO.NTOTALORIGTAXMNY});
    }
   
 }
    

}
