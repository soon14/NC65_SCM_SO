package nc.ui.so.m32.billui.query;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;

import nc.vo.pub.ISuperVO;
import nc.vo.pub.SuperVO;
import nc.vo.pubapp.lazilyload.BillLazilyLoaderVO;
import nc.vo.pubapp.pattern.exception.ExceptionUtils;
import nc.vo.pubapp.pattern.model.entity.bill.IBill;
import nc.vo.so.m32.entity.SaleInvoiceHVO;
import nc.vo.so.m32.entity.SaleInvoiceVO;

import nc.itf.pubapp.pub.lazilyload.IBillLazilyLoaderService;

import nc.bs.framework.common.NCLocator;

import nc.ui.pubapp.uif2app.lazilyload.DefaultBillLazilyLoader;
import nc.ui.so.m32.billui.model.SaleInvoiceManageModel;

/**
 * 扩展懒加载查询。
 * 
 * @since 6.3
 * @version 2013-09-07 10:19:42
 * @author 刘景
 */
public class SaleInvoiceBillLazilyLoader extends DefaultBillLazilyLoader {

  private SaleInvoiceManageModel model;

  /**
   * 
   * @param model
   */
  public void setModel(SaleInvoiceManageModel model) {
    this.model = model;
  }

  /**
   * 
   * @return BillManageModel
   */
  public SaleInvoiceManageModel getModel() {
    return this.model;
  }

  @Override
  public void loadChildrenByClass(
      Map<IBill, List<Class<? extends ISuperVO>>> needLoadChildrenMap)
      throws Exception {
    try {
      IBillLazilyLoaderService service =
          NCLocator.getInstance().lookup(IBillLazilyLoaderService.class);

      Map<BillLazilyLoaderVO, List<Class<? extends ISuperVO>>> map =
          new HashMap<BillLazilyLoaderVO, List<Class<? extends ISuperVO>>>();
      for (Entry<IBill, List<Class<? extends ISuperVO>>> entry : needLoadChildrenMap
          .entrySet()) {
        String pk = entry.getKey().getParent().getPrimaryKey();
        String ts =
            entry.getKey().getParent().getAttributeValue(SaleInvoiceHVO.TS)
                .toString();
        Class<? extends IBill> billClass = entry.getKey().getClass();
        Class<SuperVO> parentClass =
            (Class<SuperVO>) entry.getKey().getParent().getClass();

        BillLazilyLoaderVO loaderVO = new BillLazilyLoaderVO();
        loaderVO.setPk(pk);
        loaderVO.setTs(ts);
        loaderVO.setBillClass(billClass);
        loaderVO.setParentClass(parentClass);

        map.put(loaderVO, entry.getValue());
      }

      Map<String, Map<Class<? extends ISuperVO>, SuperVO[]>> resultMap =
          service.queryChildrenByParentID(map);

      for (Entry<IBill, List<Class<? extends ISuperVO>>> entry : needLoadChildrenMap
          .entrySet()) {
        for (Entry<String, Map<Class<? extends ISuperVO>, SuperVO[]>> resultEntry : resultMap
            .entrySet()) {
          IBill bill = entry.getKey();
          if (bill.getPrimaryKey().equals(resultEntry.getKey())) {
            this.fillBill(bill, entry.getValue(), resultEntry.getValue());
          }
        }
      }
    }
    catch (Exception e) {
      ExceptionUtils.wrappException(e);
    }
  }

  private void fillBill(IBill bill,
      List<Class<? extends ISuperVO>> needLoadChildrenList,
      Map<Class<? extends ISuperVO>, SuperVO[]> resultMap) {
    for (Class<? extends ISuperVO> childrenClass : needLoadChildrenList) {
      SuperVO[] itemVOs = resultMap.get(childrenClass);
      bill.setChildren(childrenClass, itemVOs);
      // 处理汇总
      SaleInvoiceVO[] combinrevo =
          this.getModel().getCombinreVO(new SaleInvoiceVO[] {
            (SaleInvoiceVO) bill
          });
      bill.setChildren(childrenClass, combinrevo[0].getChildrenVO());
    }
  }

}
