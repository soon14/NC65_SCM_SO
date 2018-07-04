package nc.itf.so.m30.revise;

import java.util.List;
import java.util.Map;

import nc.vo.pub.BusinessException;
import nc.vo.pub.ISuperVO;
import nc.vo.pub.SuperVO;
import nc.vo.pubapp.lazilyload.BillLazilyLoaderVO;

public interface IM30ReviseBillLazilyLoaderService {

  // Map<Class<? extends ISuperVO>, SuperVO[]> queryChildrenByParentID(
  // String parentID, Class<? extends IBill> parentClz,
  // List<Class<? extends ISuperVO>> childClz) throws BusinessException;

  Map<String, Map<Class<? extends ISuperVO>, SuperVO[]>> queryChildrenByParentID(
      Map<BillLazilyLoaderVO, List<Class<? extends ISuperVO>>> map)
      throws BusinessException;

}
