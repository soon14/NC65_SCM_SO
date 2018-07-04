package nc.bs.so.m38.maintain.rule.insert;

import java.util.ArrayList;
import java.util.List;

import nc.bs.framework.common.NCLocator;
import nc.impl.pubapp.pattern.rule.IRule;
import nc.itf.price.priceform.IPriceFormService;
import nc.vo.pub.BusinessException;
import nc.vo.pub.VOStatus;
import nc.vo.pubapp.pattern.exception.ExceptionUtils;
import nc.vo.pubapp.pattern.pub.PubAppTool;
import nc.vo.so.m38.entity.PreOrderBVO;
import nc.vo.so.m38.entity.PreOrderVO;

/**
 * @description
 * 预定单保存后回写价格组成
 * @scene
 * 销售预订单新增、修改保存后回写销售价格
 * @param
 * 无
 */
public class RewritePriceFormRule implements IRule<PreOrderVO> {

  @Override
  public void process(PreOrderVO[] vos) {
    for (PreOrderVO bill : vos) {
      this.reWritePriceForm(bill);
    }
  }

  private void reWritePriceForm(PreOrderVO bill) {

    PreOrderBVO[] bodys = bill.getChildrenVO();
    List<String> alpriceform = new ArrayList<String>();
    for (PreOrderBVO body : bodys) {
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
