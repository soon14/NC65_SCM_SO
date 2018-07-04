package nc.ui.so.m30.arrange.listener;

import java.util.ArrayList;
import java.util.List;

import nc.funcnode.ui.FuncletInitData;
import nc.ui.pubapp.billref.push.BillContext;
import nc.ui.pubapp.billref.push.IBillPush;
import nc.ui.uif2.IFuncNodeInitDataListener;
import nc.vo.pubapp.pattern.exception.ExceptionUtils;
import nc.vo.so.m30.entity.SaleOrderBVO;
import nc.vo.so.m30.entity.SaleOrderVO;
import nc.vo.so.pub.enumeration.BillStatus;

/**
 * 补货/直运提供给销售订单推补货/直运的监听
 * <p>
 * <b>点击按钮补货/直运安排后，弹出补货/直运界面——初始数据监听</b>
 * </p>
 * 
 * @version 6.0
 * @author 刘志伟
 * @time 2010-9-14 下午01:49:04
 */

public class M30InitDataListener implements IFuncNodeInitDataListener,
    IBillPush {

  private BillContext context;

  @Override
  public BillContext getBillContext() {
    return this.context;
  }

  @Override
  public void initData(FuncletInitData data) {
    Object object = data.getInitData();
    if (object == null) {
      return;
    }
    Object[] newObjects = (Object[]) object;
    // 按补货安排节点查询过滤vo
    SaleOrderVO[] vos = this.filterVOs(newObjects);
    if (vos.length < 1) {
      return;
    }
    try {
      this.getBillContext().getModel().initModel(vos);
    }
    catch (Exception e) {

      // 按规范抛出异常(EJB边界请用marsh方法)
      ExceptionUtils.wrappException(e);
    }
  }

  @Override
  public void setBillContext(BillContext newContext) {
    this.context = newContext;
  }

  /**
   * 过滤可以补货安排的单据
   * <p>
   * <b>按补货/直运内置查询规则过滤,其他的交给补货/直运节点自身规则去做</b>
   * 
   * <ul>
   * <li>已审批
   * <li>未安排关闭
   * <li>...
   * </ul>
   * </p>
   * 
   * @version 6.0
   * @author 刘志伟
   * @time 2010-9-14 上午11:25:27
   */
  private SaleOrderVO[] filterVOs(Object[] objects) {
    List<SaleOrderVO> alViews = new ArrayList<SaleOrderVO>();
    for (Object object : objects) {
      SaleOrderVO vo = (SaleOrderVO) object;
      boolean isBZArrange = false;
      if (BillStatus.AUDIT.equalsValue(vo.getParentVO().getFstatusflag())) {
        SaleOrderBVO[] bodys = vo.getChildrenVO();
        for (SaleOrderBVO body : bodys) {
          if (!body.getBarrangedflag().booleanValue()) {
            isBZArrange = true;
            break;
          }
        }
        if (isBZArrange) {
          alViews.add(vo);
        }
      }
    }
    return alViews.toArray(new SaleOrderVO[0]);
  }

}
