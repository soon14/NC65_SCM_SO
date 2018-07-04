package nc.bs.so.m38.maintain.rule.delete;

import java.util.ArrayList;
import java.util.List;

import nc.impl.pubapp.pattern.rule.IRule;
import nc.itf.so.pub.ref.price.PriceServicesUtil;
import nc.vo.pub.BusinessException;
import nc.vo.pubapp.pattern.exception.ExceptionUtils;
import nc.vo.so.m38.entity.PreOrderBVO;
import nc.vo.so.m38.entity.PreOrderVO;

/**
 * @description
 * 预订单删除后删除价格组成信息规则
 * @scene
 * 销售预订单删除后
 * @param
 * 无
 * @since 6.0
 * @version 2011-8-22 下午02:25:22
 * @author 刘志伟
 */
public class DeletePriceFormRule implements IRule<PreOrderVO> {

  @Override
  public void process(PreOrderVO[] bills) {
    for (PreOrderVO bill : bills) {
      PreOrderBVO[] bodys = bill.getChildrenVO();
      List<String> idList = new ArrayList<String>();
      for (PreOrderBVO body : bodys) {
        String cpriceformid = body.getCpriceformid();
        if (cpriceformid != null) {
          idList.add(cpriceformid);
        }
      }
      if (idList.size() > 0) {
        try {
          PriceServicesUtil.deletePriceFormByBillPK(bill.getPrimaryKey());
        }
        catch (BusinessException e) {
          ExceptionUtils.wrappException(e);
        }
      }
    }
  }
}
