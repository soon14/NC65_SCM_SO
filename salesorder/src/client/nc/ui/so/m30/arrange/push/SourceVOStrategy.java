package nc.ui.so.m30.arrange.push;

import java.util.ArrayList;
import java.util.List;

import nc.ui.pubapp.billref.push.ISourceVOStrategy;
import nc.vo.so.m30.entity.SaleOrderBVO;
import nc.vo.so.m30.entity.SaleOrderHVO;
import nc.vo.so.m30.entity.SaleOrderVO;

/**
 * 补货安排销售订单刷新区分策略
 * 
 * @since 6.0
 * @version 2011-8-9 上午10:21:40
 * @author 刘志伟
 */
public class SourceVOStrategy implements ISourceVOStrategy {

  /**
   * 需要更新单据：删除行过滤掉
   */
  @Override
  public Object[] getUpdateVO(Object[] sourceVOs) {
    List<SaleOrderVO> newbillList = new ArrayList<SaleOrderVO>();
    for (Object obj : sourceVOs) {
      SaleOrderVO bill = (SaleOrderVO) obj;
      SaleOrderBVO[] bodys = bill.getChildrenVO();
      List<SaleOrderBVO> newBodyList = new ArrayList<SaleOrderBVO>();
      for (SaleOrderBVO body : bodys) {
        newBodyList.add(body);
      }
      if (newBodyList.size() > 0) {
        SaleOrderBVO[] newBodys =
            newBodyList.toArray(new SaleOrderBVO[newBodyList.size()]);
        SaleOrderHVO newHead = (SaleOrderHVO) bill.getParentVO().clone();
        SaleOrderVO newBill = new SaleOrderVO();
        newBill.setParentVO(newHead);
        newBill.setChildrenVO(newBodys);
        newbillList.add(newBill);
      }
    }
    return newbillList.toArray(new SaleOrderVO[newbillList.size()]);
  }

  /**
   * 需要删除单据：所有行都不满足安排条件的单据
   * modify by zhangby5 平台删除数据时model更新数据有问题，更新后所有的数据都没有了，
   * 此处改为不删除model中的数据
   */
  @Override
  public Object[] getDeleteVO(Object[] sourceVOs) {
    return new SaleOrderVO[0];
  }

}
