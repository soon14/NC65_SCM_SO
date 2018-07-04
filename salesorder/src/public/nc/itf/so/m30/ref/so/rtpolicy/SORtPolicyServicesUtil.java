package nc.itf.so.m30.ref.so.rtpolicy;

import java.util.Map;

import nc.bs.framework.common.NCLocator;
import nc.pf.so.function.para.ParaForReturn;
import nc.pubitf.so.m30.IReturnAssignMatch;
import nc.pubitf.so.m30.ReturnAssignMatchVO;
import nc.vo.pub.BusinessException;
import nc.vo.pub.VOStatus;
import nc.vo.pubapp.pattern.exception.ExceptionUtils;
import nc.vo.so.m30.entity.SaleOrderBVO;
import nc.vo.so.m30.entity.SaleOrderVO;

public class SORtPolicyServicesUtil {
  /**
   * 补充退货政策
   * 
   * @param bill
   */
  public static void fillupReturnPolicy(SaleOrderVO bill) {
    ParaForReturn paraforreturn = new ParaForReturn();
    ReturnAssignMatchVO[] paravos = paraforreturn.getParavos(bill);
    IReturnAssignMatch service =
        NCLocator.getInstance().lookup(IReturnAssignMatch.class);
    try {
      Map<String, String> map = service.matchReturnPolicy(paravos);
      if (map.size() > 0) {
        SaleOrderBVO[] bvos = bill.getChildrenVO();
        for (SaleOrderBVO bvo : bvos) {
          int state = bvo.getStatus();
          // 行已经删除不用补充政策 不然会导致 行无法删除
          if (VOStatus.DELETED == state) {
            continue;
          }
          String row = bvo.getCrowno();
          if (state == VOStatus.NEW) {
            bvo.setCretpolicyid(map.get(row));
            bvo.setStatus(VOStatus.NEW);
            continue;
          }
          bvo.setCretpolicyid(map.get(row));
          bvo.setStatus(VOStatus.UPDATED);
        }
      }
    }
    catch (BusinessException e) {
      ExceptionUtils.wrappException(e);
    }

  }

}
