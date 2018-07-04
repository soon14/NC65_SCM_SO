package nc.impl.so.m4331.action.quality;

import java.util.HashSet;
import java.util.Set;

import nc.bs.so.m4331.quality.DeliverycheckOpenBP;
import nc.impl.pubapp.pattern.data.vo.VOQuery;
import nc.vo.pub.lang.UFBoolean;
import nc.vo.pubapp.pattern.log.TimeLog;
import nc.vo.pubapp.pattern.pub.SqlBuilder;
import nc.vo.so.m4331.entity.DeliveryBVO;
import nc.vo.so.m4331.entity.DeliveryCheckVO;
import nc.vo.so.m4331.entity.DeliveryVO;

/**
 * 打开质检信息
 * 
 * @since 6.0
 * @version 2011-3-1 下午04:47:18
 * @author 祝会征
 */
public class DeliverycheckOpenAction {
  public void openQualityInfo(DeliveryVO[] bills) {
    TimeLog.logStart();
    TimeLog.info("调用删除BP前执行业务规则"); /*-=notranslate=-*/
    TimeLog.logStart();
    DeliveryCheckVO[] vos = this.getQulityInfos(bills);
    DeliverycheckOpenBP open = new DeliverycheckOpenBP();
    open.open(vos);
    TimeLog.info("调用删除BP，进行删除"); /*-=notranslate=-*/
    TimeLog.logStart();
    TimeLog.info("调用删除BP后执行业务规则"); /*-=notranslate=-*/
  }

  private String[] getIds(DeliveryVO[] bills) {
    if (null == bills) {
      return null;
    }
    Set<String> idSet = new HashSet<String>();
    for (DeliveryVO bill : bills) {
      DeliveryBVO[] bvos = bill.getChildrenVO();
      for (DeliveryBVO bvo : bvos) {
        UFBoolean bqualityflag = bvo.getBqualityflag();
        if (!bqualityflag.booleanValue()) {
          continue;
        }
        idSet.add(bvo.getCdeliverybid());
      }
    }
    if (idSet.size() == 0) {
      return null;
    }
    String[] ids = new String[idSet.size()];
    return idSet.toArray(ids);
  }

  private DeliveryCheckVO[] getQulityInfos(DeliveryVO[] bills) {
    String[] ids = this.getIds(bills);
    if (null == ids) {
      return null;
    }
    SqlBuilder sqlwhere = new SqlBuilder();
    sqlwhere.append(" and ");
    sqlwhere.append(DeliveryCheckVO.CDELIVERYBID, ids);
    sqlwhere.append(" and ");
    sqlwhere.append(DeliveryCheckVO.BOUTENDFLAG, UFBoolean.TRUE);
    VOQuery<DeliveryCheckVO> query =
        new VOQuery<DeliveryCheckVO>(DeliveryCheckVO.class);
    DeliveryCheckVO[] vos = query.query(sqlwhere.toString(), null);
    return vos;
  }
}
