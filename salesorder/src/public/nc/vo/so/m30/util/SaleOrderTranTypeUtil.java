package nc.vo.so.m30.util;

import java.util.Map;

import nc.bs.framework.common.NCLocator;
import nc.itf.so.m30trantype.IM30TranTypeService;
import nc.vo.pub.BusinessException;
import nc.vo.pubapp.pattern.exception.ExceptionUtils;
import nc.vo.pubapp.pattern.pub.PubAppTool;
import nc.vo.so.m30trantype.entity.M30TranTypeVO;
import nc.vo.so.m30trantype.enumeration.DirectType;

public class SaleOrderTranTypeUtil {
  /**
   * 是否直运类型判定
   * 
   * @param trantypeid
   * @return
   */
  public boolean isDirectTran(String trantypeid) {
    if (PubAppTool.isNull(trantypeid)) {
      return false;
    }
    M30TranTypeVO typevo = this.queryTranTypeVO(trantypeid);
    if (DirectType.DIRECTTRAN_PO.equalsValue(typevo.getFdirecttype())
        || DirectType.DIRECTTRAN_TO.equalsValue(typevo.getFdirecttype())) {
      return true;
    }
    return false;
  }

  public int getDirectType(String ctrantypeid) {
    int flag = 0;
    IM30TranTypeService service =
        NCLocator.getInstance().lookup(IM30TranTypeService.class);
    try {
      Map<String, Integer> directMap = service.queryDirectType(new String[] {
        ctrantypeid
      });
      flag =
          directMap.get(ctrantypeid) == null ? flag : directMap
              .get(ctrantypeid).intValue();
    }
    catch (BusinessException e) {
      ExceptionUtils.wrappException(e);
    }
    return flag;
  }

  public boolean isDirectTypeChg(String oldtrantypeid, String newtrantypeid) {

    if (PubAppTool.isNull(oldtrantypeid) || PubAppTool.isNull(newtrantypeid)) {
      return true;
    }

    M30TranTypeVO oldtypevo = this.queryTranTypeVO(oldtrantypeid);
    M30TranTypeVO newtypevo = this.queryTranTypeVO(newtrantypeid);

    return !(oldtypevo.getFdirecttype().equals(newtypevo.getFdirecttype()));
  }

  private M30TranTypeVO queryTranTypeVO(String trantypeid) {
    M30TranTypeVO returnVos = null;
    try {
      IM30TranTypeService service =
          NCLocator.getInstance().lookup(IM30TranTypeService.class);
      returnVos = service.queryTranTypeVO(trantypeid);
    }
    catch (Exception e) {
      ExceptionUtils.wrappException(e);
    }
    return returnVos;
  }

}
