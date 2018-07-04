package nc.ui.so.m30.revise.lazilyload;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;

import nc.bs.framework.common.NCLocator;
import nc.itf.pubapp.pub.lazilyload.IBillLazilyLoaderService;
import nc.ui.pubapp.uif2app.lazilyload.DefaultBillLazilyLoader;
import nc.vo.pub.ISuperVO;
import nc.vo.pub.SuperVO;
import nc.vo.pubapp.lazilyload.BillLazilyLoaderVO;
import nc.vo.pubapp.pattern.exception.ExceptionUtils;
import nc.vo.pubapp.pattern.model.entity.bill.IBill;
import nc.vo.so.m30.entity.SaleOrderBVO;
import nc.vo.so.m30.entity.SaleOrderHVO;
import nc.vo.so.m30.entity.SaleOrderVO;
import nc.vo.so.m30.revise.entity.SaleOrderHistoryBVO;
import nc.vo.so.m30.revise.entity.SaleOrderHistoryHVO;
import nc.vo.so.m30.util.Transfer30and30RVOTool;

public class M30ReviseDefaultBillLazilyLoader extends DefaultBillLazilyLoader {

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
            entry.getKey().getParent().getAttributeValue("ts").toString();
        Class<? extends IBill> billClass = entry.getKey().getClass();
        Class<SuperVO> parentClass =
            (Class<SuperVO>) entry.getKey().getParent().getClass();

        BillLazilyLoaderVO loaderVO = new BillLazilyLoaderVO();
        if (pk.equals(entry.getKey().getParent()
            .getAttributeValue(SaleOrderHVO.CSALEORDERID))) {
          loaderVO.setPk(entry.getKey().getParent()
              .getAttributeValue(SaleOrderHVO.CSALEORDERID).toString());
          loaderVO.setBillClass(SaleOrderVO.class);
          SaleOrderVO aggVO = new SaleOrderVO();
          aggVO.setParentVO(new SaleOrderHVO());
          aggVO.setChildrenVO(new SaleOrderBVO[] {
            new SaleOrderBVO()
          });
          Class<SuperVO> parentClass_SaleOrderHVO =
              (Class<SuperVO>) aggVO.getParent().getClass();
          Class<SuperVO> parentClass_SaleOrderBVO =
              (Class<SuperVO>) aggVO.getChildren(SaleOrderBVO.class)[0]
                  .getClass();
          loaderVO.setParentClass(parentClass_SaleOrderHVO);

          entry.getValue().set(0, parentClass_SaleOrderBVO);
        }
        else {
          loaderVO.setPk(pk);
          loaderVO.setBillClass(billClass);
          loaderVO.setParentClass(parentClass);
        }
        loaderVO.setTs(ts);

        map.put(loaderVO, entry.getValue());
      }

      Map<String, Map<Class<? extends ISuperVO>, SuperVO[]>> resultMap =
          service.queryChildrenByParentID(map);

      for (Entry<IBill, List<Class<? extends ISuperVO>>> entry : needLoadChildrenMap
          .entrySet()) {
        for (Entry<String, Map<Class<? extends ISuperVO>, SuperVO[]>> resultEntry : resultMap
            .entrySet()) {
          IBill bill = entry.getKey();
          // add by wangshu6 for 销售订单修订懒加载
          // 如果销售订单修订主键和销售订单主键一样（还没有修订版本），则查询销售订单表，进行懒加载
          if (!isRevised(bill)) {
            String csaleorderid =
                bill.getParent().getAttributeValue(SaleOrderHistoryHVO.CSALEORDERID)
                    .toString();
            if (csaleorderid.equals(resultEntry.getKey())) {
              this.transHistoryBVOBySaleorderBVOForLazilyLoader(entry, resultEntry);
            }
          }
          // end
          else {
            if (bill.getPrimaryKey().equals(resultEntry.getKey())) {
              fillBill(bill, entry.getValue(), resultEntry.getValue());
            }
          }
        }
      }
    }
    catch (Exception e) {
      ExceptionUtils.wrappException(e);
    }
  }

  /**
   * 判断HistoryVO是否做过修订
   * 
   * @param entry
   * @param resultEntry
   * @return
   */
  private boolean isRevised(IBill bill) {
    String csaleorderid =
        bill.getParent().getAttributeValue(SaleOrderHVO.CSALEORDERID)
            .toString();
    if (bill.getPrimaryKey().equals(csaleorderid)) {
      return false;
    }
    else {
      return true;
    }
  }

  /**
   * 当销售订单没有进行过修订时，懒加载展现的子实体是销售订单子实体体现的
   * 
   * @param entry 需要进行懒加载单据
   * @param resultEntry 销售订单集合
   */
  private void transHistoryBVOBySaleorderBVOForLazilyLoader(
      Entry<IBill, List<Class<? extends ISuperVO>>> entry,
      Entry<String, Map<Class<? extends ISuperVO>, SuperVO[]>> resultEntry) {

    IBill bill = entry.getKey();
    for (Class<? extends ISuperVO> childrenClass : entry.getValue()) {
      // 1.获得销售订单子实体
      SuperVO[] itemVOs = resultEntry.getValue().get(childrenClass);
      // 2.转换成销售订单修订bvo
      Transfer30and30RVOTool trans = new Transfer30and30RVOTool();
      SaleOrderHistoryBVO[] orderhistorybvos =
          trans.transferVOS(itemVOs, SaleOrderHistoryBVO.class);
      // 3.将销售订单修订bvo放到单据表体vo
      bill.setChildren(childrenClass, orderhistorybvos);
    }
  }

  private void fillBill(IBill bill,
      List<Class<? extends ISuperVO>> needLoadChildrenList,
      Map<Class<? extends ISuperVO>, SuperVO[]> resultMap) {
    for (Class<? extends ISuperVO> childrenClass : needLoadChildrenList) {
      SuperVO[] itemVOs = resultMap.get(childrenClass);
      bill.setChildren(childrenClass, itemVOs);
    }
  }

}
