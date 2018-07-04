package nc.pubimpl.so.m30.pu.m20;

import java.util.HashMap;
import java.util.Map;

import nc.bs.framework.common.NCLocator;
import nc.itf.so.m30trantype.IM30TranTypeService;
import nc.pubitf.so.m30.pu.m20.ISaleOrderFor20;
import nc.vo.pub.BusinessException;
import nc.vo.pub.lang.UFBoolean;
import nc.vo.pubapp.pattern.exception.ExceptionUtils;
import nc.vo.so.m30trantype.entity.M30TranTypeVO;
import nc.vo.so.m30trantype.enumeration.DirectType;

public class SaleOrderFor20Impl implements ISaleOrderFor20 {

  @Override
  public Map<String, UFBoolean> queryIsDirectPOType(String pk_group,
      String[] vtrantypecodes) throws BusinessException {
    // Map<交易类型code, 是否直运采购>
    Map<String, UFBoolean> returnMap = new HashMap<String, UFBoolean>();
    if (pk_group == null || vtrantypecodes == null
        || vtrantypecodes.length == 0) {
      return returnMap;
    }
    M30TranTypeVO[] tranTypeVOs = null;
    IM30TranTypeService service =
        NCLocator.getInstance().lookup(IM30TranTypeService.class);
    try {
      tranTypeVOs = service.queryTranTypeVOs(pk_group, vtrantypecodes);
    }
    catch (BusinessException e) {
      ExceptionUtils.marsh(e);
    }
    if (tranTypeVOs != null) {
      for (M30TranTypeVO vo : tranTypeVOs) {
        if (DirectType.DIRECTTRAN_PO.equalsValue(vo.getFdirecttype())) {
          returnMap.put(vo.getVtrantypecode(), UFBoolean.TRUE);
        }
        else {
          returnMap.put(vo.getVtrantypecode(), UFBoolean.FALSE);
        }
      }
    }
    return returnMap;
  }

}
