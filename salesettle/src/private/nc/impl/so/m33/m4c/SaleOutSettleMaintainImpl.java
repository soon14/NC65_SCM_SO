package nc.impl.so.m33.m4c;

import java.util.ArrayList;
import java.util.List;

import nc.bs.so.m33.biz.m4c.action.manual.SaleOutManualRushAction;
import nc.bs.so.m33.biz.m4c.action.manual.SaleOutManualSquareAction;
import nc.bs.so.m33.maintain.m4c.query.QuerySquare4CVOBP;
import nc.bs.so.m33.maintain.m4c.query.QuerySquare4CVOBizBP;
import nc.impl.pubapp.pattern.data.view.tool.ViewConcurrentTool;
import nc.itf.so.m33.maintain.m4c.ISaleOutSettleMaintain;
import nc.ui.querytemplate.querytree.IQueryScheme;
import nc.vo.pub.BusinessException;
import nc.vo.pub.lang.UFDouble;
import nc.vo.pubapp.pattern.exception.ExceptionUtils;
import nc.vo.pubapp.pattern.model.entity.view.IDataView;
import nc.vo.pubapp.pattern.pub.MathTool;
import nc.vo.so.m33.m4c.entity.SquareOutBVO;
import nc.vo.so.m33.m4c.entity.SquareOutHVO;
import nc.vo.so.m33.m4c.entity.SquareOutVO;
import nc.vo.so.m33.m4c.entity.SquareOutVOUtils;
import nc.vo.so.m33.m4c.entity.SquareOutViewVO;
import nc.vo.so.pub.util.AggVOUtil;
import nc.vo.so.pub.util.ArrayUtil;

public class SaleOutSettleMaintainImpl implements ISaleOutSettleMaintain {

  @Override
  public void manualOutRush(SquareOutViewVO[] bluevos, SquareOutViewVO[] redvos)
      throws BusinessException {
    try {
      this.setHGroupByBGroup(bluevos);
      this.setHGroupByBGroup(redvos);
      SquareOutViewVO[] view = ArrayUtil.combinArrays(bluevos, redvos);
      this.concurrentCheck(view);
      new SaleOutManualRushAction().manualOutRush(bluevos, redvos);
    }
    catch (Exception ex) {
      ExceptionUtils.marsh(ex);
    }

  }

  @Override
  public SquareOutViewVO[] manualSquare(SquareOutViewVO[] vos)
      throws BusinessException {
    try {
      this.setHGroupByBGroup(vos);
      this.concurrentCheck(vos);
      return new SaleOutManualSquareAction().manualSquare(vos);
    }
    catch (Exception ex) {
      ExceptionUtils.marsh(ex);
    }
    return null;
  }

  @Override
  public void manualUnOutRush(SquareOutViewVO[] vos) throws BusinessException {
    try {
      this.setHGroupByBGroup(vos);
      this.concurrentCheck(vos);
      new SaleOutManualRushAction().manualUnOutRush(vos);
    }
    catch (Exception ex) {
      ExceptionUtils.marsh(ex);
    }
  }

  @Override
  public SquareOutViewVO[] manualUnSquare(SquareOutViewVO[] vos)
      throws BusinessException {
    try {
      this.setHGroupByBGroup(vos);
      this.concurrentCheck(vos);
      new SaleOutManualSquareAction().manualUnSquare(vos);
      SquareOutViewVO[] retview = this.getReturnVOFormanualUnSquare(vos);
      return retview;
    }
    catch (Exception ex) {
      ExceptionUtils.marsh(ex);
    }
    return null;
  }

  @Override
  public SquareOutViewVO[] querySquareOutFor4CManualSquare(
      IQueryScheme queryScheme) throws BusinessException {
    try {
      return new QuerySquare4CVOBizBP().querySquareOutFor4CSquare(queryScheme);
    }
    catch (Exception ex) {
      ExceptionUtils.marsh(ex);
    }
    return null;
  }

  /**
   * 并发控制
   * 
   * @param views 需要并发控制的视图vo
   */
  private void concurrentCheck(IDataView[] views) {
    if (views == null || views.length == 0) {
      return;
    }
    ViewConcurrentTool tool = new ViewConcurrentTool();
    tool.lock(views);
    tool.checkTSWithDB(views);
  }

  /**
   * 手工取消结算后设置返回值
   * 
   * @param vos
   * @return
   */
  private SquareOutViewVO[] getReturnVOFormanualUnSquare(SquareOutViewVO[] vos) {
    // 待结算单
    SquareOutVO[] sqvos = SquareOutVOUtils.getInstance().combineBill(vos);
    String[] bids =
        AggVOUtil.getDistinctItemFieldArray(sqvos, SquareOutBVO.CSALESQUAREBID,
            String.class);
    // 查询相应销售出库待结算单
    SquareOutViewVO[] qryview =
        new QuerySquare4CVOBP().querySquareOutViewVOByBID(bids);
    List<SquareOutViewVO> retList = new ArrayList<SquareOutViewVO>();
    for (SquareOutViewVO view : qryview) {
      UFDouble nsquaremanualnum = view.getItem().getNsquarearnum();
      if (MathTool.isZero(nsquaremanualnum)) {
        nsquaremanualnum = view.getItem().getNsquareianum();
      }
      if (MathTool.greaterThan(view.getItem().getNnum(), nsquaremanualnum)) {
        retList.add(view);
      }
    }

    SquareOutViewVO[] retsvos = null;
    if (retList.size() > 0) {
      // 设置返回值
      retsvos = retList.toArray(new SquareOutViewVO[retList.size()]);
      int i = 0;
      for (SquareOutViewVO view : qryview) {
        SquareOutHVO hvo = new SquareOutHVO();
        hvo.setCsalesquareid(view.getHead().getCsalesquareid());
        hvo.setTs(view.getHead().getTs());
        SquareOutBVO bvo = new SquareOutBVO();
        bvo.setCsalesquareid(view.getHead().getCsalesquareid());
        bvo.setCsalesquarebid(view.getItem().getCsalesquarebid());
        bvo.setNsquarearnum(view.getItem().getNsquarearnum());
        bvo.setNsquareianum(view.getItem().getNsquareianum());
        bvo.setTs(view.getItem().getTs());
        retsvos[i] = new SquareOutViewVO();
        retsvos[i].setHead(hvo);
        retsvos[i].setItem(bvo);
      }
    }

    return retsvos;
  }

  /**
   * 用表体Group设置表头Group，因为前台拉平界面只存表体Group
   * 
   * @param vos
   */
  private void setHGroupByBGroup(SquareOutViewVO[] vos) {
    for (SquareOutViewVO view : vos) {
      view.getHead().setPk_group(view.getItem().getPk_group());
    }
  }

}
