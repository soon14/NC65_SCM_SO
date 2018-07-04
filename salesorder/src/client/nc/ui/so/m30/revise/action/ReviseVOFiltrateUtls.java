package nc.ui.so.m30.revise.action;

import java.util.ArrayList;
import java.util.List;

import nc.ui.ml.NCLangRes;
import nc.vo.pubapp.pattern.exception.ExceptionUtils;
import nc.vo.so.m30.revise.entity.SaleOrderHistoryVO;

/**
 * @description
 *              销售订单修订批量审批、取消审批、提交、收回前需要过滤不能操作的数据。比如刚从销售订单查询出来的单据，没有修订。<br>
 *              这样处理的原因：从销售订单查询出来的单据，修订主键与订单id一致。这样就导致审批报错。
 *              报错位置：(nc.bs.pub.pf.pfframe.PlatFormEntryImpl.processAction(
 *              PlatFormEntryImpl.java:117))
 * @scene
 * 
 * @param
 * 
 * 
 * @since 6.5
 * @version 2015-12-5 上午10:45:16
 * @author 刘景
 */
public class ReviseVOFiltrateUtls {

  /**
   * 提供给销售订单修订提交、收回、审批、取消审批Ation的processBefore方法使用
   * 
   * @param vos
   * @return
   */
  public static Object[] getIsPFlowActionBillVO(Object[] vos) {
    // 没有修订的订单不进行审批
    SaleOrderHistoryVO[] salevos = (SaleOrderHistoryVO[]) vos;
    if (salevos == null || salevos.length == 0) {
      return vos;
    }
    List<SaleOrderHistoryVO> salevolist = new ArrayList<>();
    for (SaleOrderHistoryVO salevo : salevos) {
      Integer iversion = salevo.getParentVO().getIversion();
      String saleorderid = salevo.getParentVO().getCsaleorderid();
      String orderhistoryid = salevo.getParentVO().getCorderhistoryid();

      // 修订的版本大于0，销售订单修订主键与订单主键不一致情况下才可以进行流程操作

      // 销售订单修订主键与销售订单主键一致时，一定是从销售订单查询出来的VO，所以需要修订后才能审批、取消审批、提交、收回
      // (nc.bs.pub.pf.pfframe.PlatFormEntryImpl.processAction(PlatFormEntryImpl.java:117))
      if ((iversion != null && iversion > 0)
          && !saleorderid.equals(orderhistoryid)) {
        salevolist.add(salevo);
      }
    }
    if (salevolist.size() == 0) {
      ExceptionUtils.wrappBusinessException(NCLangRes.getInstance().getStrByID(
          "4006011_0", "04006011-0533")/*过滤掉未修订的单据后，没有要操作的单据，请重新选择单据。*/);
    }
    return salevolist.toArray(new SaleOrderHistoryVO[salevolist.size()]);
  }

}
