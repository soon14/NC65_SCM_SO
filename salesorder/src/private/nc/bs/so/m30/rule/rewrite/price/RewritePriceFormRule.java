package nc.bs.so.m30.rule.rewrite.price;

import java.util.ArrayList;
import java.util.List;

import nc.bs.framework.common.NCLocator;
import nc.impl.pubapp.pattern.rule.IRule;
import nc.itf.price.priceform.IPriceFormService;
import nc.vo.pub.BusinessException;
import nc.vo.pub.VOStatus;
import nc.vo.pubapp.pattern.exception.ExceptionUtils;
import nc.vo.pubapp.pattern.pub.PubAppTool;
import nc.vo.so.m30.entity.SaleOrderBVO;
import nc.vo.so.m30.entity.SaleOrderVO;

/**
 * @description
 * 保存价格组成
 * @scene 
 * 销售订单新增、修改保存后
 * @param 
 * 无
 */
public class RewritePriceFormRule implements IRule<SaleOrderVO> {

  @Override
  public void process(SaleOrderVO[] vos) {
    for (SaleOrderVO bill : vos) {
      this.reWritePriceForm(bill);
    }
  }

  private void reWritePriceForm(SaleOrderVO bill) {

    SaleOrderBVO[] bodys = bill.getChildrenVO();
    List<String> alpriceform = new ArrayList<String>();
    for (SaleOrderBVO body : bodys) {
      if (VOStatus.DELETED == body.getStatus()
          || VOStatus.UNCHANGED == body.getStatus()) {
        continue;
      }
      String priceform = body.getCpriceformid();
      if (!PubAppTool.isNull(priceform)) {
        alpriceform.add(priceform);
      }
    }
    if (alpriceform.size() > 0) {
      String[] priceforms = new String[alpriceform.size()];
      alpriceform.toArray(priceforms);
      String hid = bill.getParentVO().getPrimaryKey();
      IPriceFormService rewritesrv =
          NCLocator.getInstance().lookup(IPriceFormService.class);
      try {
        rewritesrv.savePriceForm(hid, priceforms, true);
      }
      catch (BusinessException e) {
        ExceptionUtils.wrappException(e);
      }
    }

  }
}
