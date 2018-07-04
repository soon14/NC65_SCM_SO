package nc.itf.so.m38trantype;

import java.util.ArrayList;
import java.util.List;

import nc.bs.framework.aop.Wrapper;
import nc.pubimpl.bdlayer.cache.CacheVOQuery;
import nc.vo.pub.BusinessException;
import nc.vo.pubapp.pattern.exception.ExceptionUtils;
import nc.vo.so.m38trantype.entity.M38TranTypeVO;

public class M38TranTypeServiceImpl implements IM38TranTypeService, Wrapper {

  @Override
  public void setWrapping(Object wrapping) {
    return;
  }

  @Override
  public M38TranTypeVO queryTranTypeVO(String ctrantypeid)
      throws BusinessException {
    M38TranTypeVO[] retVOs = this.queryTranTypeVOs(new String[] {
      ctrantypeid
    });
    if (retVOs != null && retVOs.length > 0) {
      return retVOs[0];
    }
    return null;
  }

  @Override
  public M38TranTypeVO[] queryTranTypeVOs(String[] ctrantypeids)
      throws BusinessException {
    M38TranTypeVO[] retVOs = null;
    try {
      String[] selectFds = this.getTranTypeSelectFds();
      String[] whrFields = {
        M38TranTypeVO.CTRANTYPEID
      };
      int length = ctrantypeids.length;
      String[][] whrFdValues = new String[length][];
      for (int i = 0; i < length; i++) {
        whrFdValues[i] = new String[1];
        whrFdValues[i][0] = ctrantypeids[i];
      }
      // »º´ævo²éÑ¯
      CacheVOQuery<M38TranTypeVO> cacheVOQuery =
          new CacheVOQuery<M38TranTypeVO>(M38TranTypeVO.class, selectFds);
      retVOs = cacheVOQuery.query(whrFields, whrFdValues);
    }
    catch (Exception ex) {
      ExceptionUtils.marsh(ex);
    }
    return retVOs;
  }

  private String[] getTranTypeSelectFds() {
    // ²éÑ¯ËùÓÐµÄ×Ö¶Î(ÅÅ³ýdr¡¢ts)
    List<String> attNames = new ArrayList<String>();
    String[] allAtts = new M38TranTypeVO().getAttributeNames();
    for (String att : allAtts) {
      // ÅÅ³ýdrÎ±ÁÐ
      if ("dr".equals(att)) {
        continue;
      }
      if ("PSEUDOCOLUMN".equalsIgnoreCase(att)) {
        continue;
      }
      attNames.add(att);
    }
    return attNames.toArray(new String[attNames.size()]);
  }
}
