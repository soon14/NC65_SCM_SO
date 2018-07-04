package nc.ui.so.m4331.arrange.listener;

import java.util.ArrayList;
import java.util.List;

import nc.funcnode.ui.FuncletInitData;
import nc.ui.pubapp.billref.push.BillContext;
import nc.ui.pubapp.billref.push.IBillPush;
import nc.ui.uif2.IFuncNodeInitDataListener;
import nc.vo.pubapp.pattern.exception.ExceptionUtils;
import nc.vo.so.m30.entity.SaleOrderViewVO;
import nc.vo.so.pub.enumeration.BillStatus;

/**
 * 销售订单点击按钮发货安排后，弹出发货安排界面初始数据监听
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

  /**
   * 父类方法重写
   * 
   */
  @Override
  public void initData(FuncletInitData data) {
    Object object = data.getInitData();
    if (object == null) {
      return;
    }
    SaleOrderViewVO[] views = (SaleOrderViewVO[]) object;
    // 按发货安排节点查询过滤Views
    views = this.filterViews(views);
    if (views.length < 1) {
      return;
    }
    try {
      this.getBillContext().getModel().initModel(views);
    }
    catch (Exception e) {
      // 按规范抛出异常(EJB边界请用marsh方法)
      ExceptionUtils.wrappException(e);
    }
  }

  @Override
  public void setBillContext(BillContext context1) {
    this.context = context1;
  }

  /**
   * 过滤可以发货安排的数据行
   * <ul>
   * <li>已审批
   * <li>物流组织非空
   * <li>未发货关闭
   * <li>...
   * </ul>
   * 
   * @version 6.0
   * @author 刘志伟
   * @time 2010-9-14 上午11:25:27
   */
  private SaleOrderViewVO[] filterViews(SaleOrderViewVO[] views) {
    List<SaleOrderViewVO> alViews = new ArrayList<SaleOrderViewVO>();
    for (SaleOrderViewVO view : views) {
      if (BillStatus.AUDIT.equalsValue(view.getHead().getFstatusflag())
          && view.getBody().getCtrafficorgid() != null
          && !view.getBody().getBbsendendflag().booleanValue()) {
        alViews.add(view);
      }
    }
    return alViews.toArray(new SaleOrderViewVO[0]);
  }

}
