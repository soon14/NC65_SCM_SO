package nc.ui.so.m30.billui.rule;

import java.util.ArrayList;
import java.util.List;

import nc.bs.framework.common.NCLocator;
import nc.itf.so.m30.ISaleOrgPubService;
import nc.vo.pub.BusinessException;
import nc.vo.pubapp.pattern.exception.ExceptionUtils;
import nc.vo.pubapp.pattern.pub.PubAppTool;
import nc.vo.so.m30.entity.SaleOrderBVO;
import nc.vo.so.pub.keyvalue.IKeyValue;

/**
 * 
 */
public class FillBodyIDRule {

  private IKeyValue keyValue;

  public FillBodyIDRule(
      IKeyValue keyValue) {
    this.keyValue = keyValue;
  }

  public void fillBodyId(int[] rows) {

    List<Integer> needidlist = new ArrayList<Integer>();
    for (int row : rows) {
      String id =
          this.keyValue.getBodyStringValue(row, SaleOrderBVO.CSALEORDERBID);
      if (PubAppTool.isNull(id)) {
        needidlist.add(Integer.valueOf(row));
      }
    }
    if (needidlist.size() > 0) {
      ISaleOrgPubService service =
          NCLocator.getInstance().lookup(ISaleOrgPubService.class);
      try {
        String[] ids = service.getOIDArray(needidlist.size());
        for (int i = 0; i < needidlist.size(); i++) {
          this.keyValue.setBodyValue(needidlist.get(i).intValue(),
              SaleOrderBVO.CSALEORDERBID, ids[i]);
        }
      }
      catch (BusinessException e) {
        ExceptionUtils.wrappException(e);
      }
    }
  }

}
