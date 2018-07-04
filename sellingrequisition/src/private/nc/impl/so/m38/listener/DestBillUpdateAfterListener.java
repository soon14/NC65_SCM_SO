package nc.impl.so.m38.listener;

import java.util.Map;

import nc.bs.businessevent.BusinessEvent;
import nc.bs.businessevent.IBusinessEvent;
import nc.bs.businessevent.IBusinessListener;
import nc.impl.so.m38.migrate.action.PreOrderDestBillUpdateAction;
import nc.vo.pub.BusinessException;

public class DestBillUpdateAfterListener implements IBusinessListener {

  @Override
  public void doAction(IBusinessEvent event) throws BusinessException {
    if (("1065".equals(event.getEventType()))){      
      Object object = ((BusinessEvent.BusinessUserObj)event.getUserObject()).getUserObj();
      Map<String, String> oldNewTrantypeIdMap = (Map<String, String>)object;
      // 更新预订单的下游单据将下游单据的来源单据类型以及来源交易类型更改为订单中心预订单的单据类型和交易类型
      PreOrderDestBillUpdateAction pdbuAction = new PreOrderDestBillUpdateAction();
      pdbuAction.update(oldNewTrantypeIdMap);
    }
  }
}
