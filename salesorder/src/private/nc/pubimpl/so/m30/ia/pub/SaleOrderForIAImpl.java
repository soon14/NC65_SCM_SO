package nc.pubimpl.so.m30.ia.pub;

import java.util.Map;

import nc.bs.framework.common.NCLocator;
import nc.itf.so.m30trantype.IM30TranTypeService;
import nc.pubitf.so.m30.ia.pub.ISaleOrderForIA;
import nc.vo.pub.BusinessException;
import nc.vo.pub.lang.UFBoolean;
import nc.vo.pubapp.pattern.exception.ExceptionUtils;

public class SaleOrderForIAImpl implements ISaleOrderForIA {

  @Override
  public Map<String, UFBoolean> queryIsDirectPO(String[] ctrantypeids)
      throws BusinessException {
    // Map<交易类型code, 是否直运采购>
    Map<String, UFBoolean> returnMap = null;
    IM30TranTypeService service =
        NCLocator.getInstance().lookup(IM30TranTypeService.class);
    try {
      returnMap = service.queryIsDirectPO(ctrantypeids);
    }
    catch (BusinessException e) {
      ExceptionUtils.marsh(e);
    }
    return returnMap;
  }
}
