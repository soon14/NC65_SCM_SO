package nc.ui.so.m30.arrange.push;

import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;

import nc.bs.framework.common.NCLocator;
import nc.pubitf.so.m30arrange.IM30ArrangeForPub;
import nc.ui.pubapp.billref.push.IPushSourceVOUpdate;
import nc.vo.mmpac.dmo.entity.DmoVO;
import nc.vo.pu.m20.entity.PraybillItemVO;
import nc.vo.pu.m20.entity.PraybillVO;
import nc.vo.pu.m21.entity.OrderItemVO;
import nc.vo.pu.m21.entity.OrderVO;
import nc.vo.pub.BusinessException;
import nc.vo.pubapp.pattern.exception.ExceptionUtils;
import nc.vo.sc.m61.entity.SCOrderItemVO;
import nc.vo.sc.m61.entity.SCOrderVO;
import nc.vo.to.m5a.entity.TransInBodyVO;
import nc.vo.to.m5a.entity.TransInVO;
import nc.vo.to.m5x.entity.BillItemVO;
import nc.vo.to.m5x.entity.BillVO;

/**
 * 补货/安排界面刷新——查询最新的销售订单VO
 * 
 * @since 6.0
 * @version 2011-8-9 上午11:04:58
 * @author 刘志伟
 */
public class PushSourceVOUpdate implements IPushSourceVOUpdate {

  @Override
  public Map<String, Object[]> querySourceVOByDestVO(Object[] destVOs) {
    Map<String, Object[]> retMap = new HashMap<String, Object[]>();
    if (destVOs == null || destVOs.length == 0) {
      return retMap;
    }
    Set<String> srcIDSet = new HashSet<String>();
    for (Object obj : destVOs) {
      if (obj instanceof PraybillVO) {
        PraybillVO bill = (PraybillVO) obj;
        PraybillItemVO[] bodys = bill.getBVO();
        for (PraybillItemVO body : bodys) {
          srcIDSet.add(body.getCsourcebid());
        }
      }
      else if (obj instanceof OrderVO) {
        OrderVO bill = (OrderVO) obj;
        OrderItemVO[] bodys = bill.getBVO();
        for (OrderItemVO body : bodys) {
          srcIDSet.add(body.getCsourcebid());
        }
      }
      else if (obj instanceof TransInVO) {
        TransInVO bill = (TransInVO) obj;
        TransInBodyVO[] bodys = bill.getChildrenVO();
        for (TransInBodyVO body : bodys) {
          srcIDSet.add(body.getCsrcbid());
        }
      }
      else if (obj instanceof BillVO) {
        BillVO bill = (BillVO) obj;
        BillItemVO[] bodys = bill.getChildrenVO();
        for (BillItemVO body : bodys) {
          srcIDSet.add(body.getCsrcbid());
        }
      }
      else if (obj instanceof SCOrderVO) {
        SCOrderVO bill = (SCOrderVO) obj;
        SCOrderItemVO[] bodys = bill.getChildrenVO();
        for (SCOrderItemVO body : bodys) {
          srcIDSet.add(body.getCsrcbid());
        }
      }
      else if (obj instanceof DmoVO) {
        DmoVO bill = (DmoVO) obj;
        srcIDSet.add(bill.getVsrcbid());
      }
    }
    if (srcIDSet.size() > 0) {
      IM30ArrangeForPub service =
          NCLocator.getInstance().lookup(IM30ArrangeForPub.class);
      try {
        retMap =
            service.querySaleOrderVOs(srcIDSet.toArray(new String[srcIDSet
                .size()]));
      }
      catch (BusinessException e) {
        ExceptionUtils.wrappException(e);
      }
    }
    return retMap;
  }

}
