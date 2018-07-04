package nc.bs.so.m4331.assist.state;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import nc.bs.framework.common.NCLocator;
import nc.itf.so.m4331trantype.IM4331TranTypeService;
import nc.vo.pub.BusinessException;
import nc.vo.pub.lang.UFBoolean;
import nc.vo.pub.lang.UFDouble;
import nc.vo.pubapp.pattern.exception.ExceptionUtils;
import nc.vo.pubapp.pattern.pub.MathTool;
import nc.vo.so.m4331.entity.DeliveryBVO;
import nc.vo.so.m4331.entity.DeliveryVO;
import nc.vo.so.m4331.entity.DeliveryViewVO;
import nc.vo.so.pub.enumeration.BillStatus;

/**
 * 发货单状态跃迁判断工具
 * 
 * @author 祝会征
 * @since 6.0
 * @time 2010-04-08 上午09:27:07
 */
public class StateCalculateUtil {

  /**
   * 方法功能描述：获得发货单单据类型的属性是否一次出库关闭
   * 
   * @author 祝会征
   * @time 2010-9-29 上午11:43:57
   */
  public Map<String, UFBoolean> getIsClosedMap(DeliveryViewVO[] views) {
    String pk_group = views[0].getHead().getPk_group();
    List<String> list = new ArrayList<String>();
    for (DeliveryViewVO view : views) {
      String billtype = view.getHead().getVtrantypecode();
      if ((list.size() == 0) || !list.contains(billtype)) {
        list.add(billtype);
      }
    }
    String[] billtypes = new String[list.size()];
    billtypes = list.toArray(billtypes);
    IM4331TranTypeService service =
        NCLocator.getInstance().lookup(IM4331TranTypeService.class);
    Map<String, UFBoolean> map;
    try {
      map = service.queryTranTypes(pk_group, billtypes);
      return map;
    }
    catch (BusinessException e) {
      ExceptionUtils.wrappBusinessException(e.getMessage());
    }
    return null;
  }

  /**
   * 方法功能描述：根据行状态判断是否可以整单关闭。 <li>所有行关闭，则整单关闭(表头转关闭状态) ：return true <li>
   * 有一行打开，则整单打开(表头转审批状态) ：return false
   * 
   * @param vo
   *          待计算的单据VO
   * @return 是否自动关闭
   * @author 祝会征
   * @time 2010-04-09 下午02:43:18
   */
  public boolean isAutoBillCloseByRowState(DeliveryVO vo) {
    DeliveryBVO[] items = vo.getChildrenVO();
    for (DeliveryBVO item : items) {
      boolean blineclose = item.getBoutendflag().booleanValue();
      if (!blineclose) {
        return false;
      }
    }
    return true;
  }

  /**
   * 方法功能描述：判断是否能够自动出库关闭
   * 
   * @author 祝会征
   * @time 2010-6-17 下午03:04:03
   */
  public UFBoolean isAutoOutCloseByNumber(DeliveryViewVO vo) {
    DeliveryBVO body = vo.getItem();
    UFDouble nnum = body.getNnum();
    UFDouble ntotaloutnum = body.getNtotaloutnum();
    if (MathTool.compareTo(nnum, ntotaloutnum) <= 0) {
      return UFBoolean.TRUE;
    }
    return UFBoolean.FALSE;
  }

  /**
   * 方法功能描述：根据单据状态判断是否可以自动行关闭。 <b>参数说明</b>
   * 
   * @param views
   *          待计算的单据views
   * @return 是否自动关闭
   * @author 祝会征
   * @time 2010-04-09 下午02:43:18
   */
  public boolean isAutoRowsCloseByBillState(DeliveryViewVO views) {
    return BillStatus.CLOSED.equalsValue(views.getHead().getFstatusflag());
  }

  /**
   * 方法功能描述：根据单据状态判断是否可以自动行打开。
   * 
   * @param views
   *          待计算的单据views
   * @return 是否自动关闭
   * @author 祝会征
   * @time 2010-04-09 下午02:43:18
   */
  public boolean isAutoRowsOpenByBillState(DeliveryViewVO views) {
    return BillStatus.AUDIT.equalsValue(views.getHead().getFstatusflag());
  }
}
