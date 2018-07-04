package nc.itf.so.mreturnassign;

import java.util.Map;
import java.util.Set;

import nc.itf.pubapp.pub.smart.ISmartService;
import nc.vo.pub.BusinessException;
import nc.vo.so.m30.entity.SaleOrderVO;

public interface IReturnAssignMaintain extends ISmartService {
  Map<String, Set<String>> getAssignedPolicy(SaleOrderVO aggVO)
      throws BusinessException;
}
