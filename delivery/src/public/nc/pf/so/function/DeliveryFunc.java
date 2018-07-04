package nc.pf.so.function;

import nc.impl.pubapp.pattern.data.bill.tool.BillTransferTool;
import nc.vo.pub.AggregatedValueObject;
import nc.vo.pub.lang.UFBoolean;
import nc.vo.so.m4331.entity.DeliveryVO;

/**
 * 
 * <p>
 * 发货单检查函数
 * <li>批次管理物料批次必输
 * 
 * @author 祝会征
 * @time 2010-10-22 上午10:42:38
 */
public class DeliveryFunc {
  /**
   * 
   * 方法功能描述：检查物料是否是批次管理
   * 
   * @author 祝会征
   * @time 2010-10-22 上午10:43:20
   */
  public UFBoolean examBatchInv(AggregatedValueObject aggVO) {
    DeliveryVO newvo = this.getFullBill(aggVO);
    return new FlowDeliveryFuncImpl().examBatchInv(newvo);
  }

  private DeliveryVO getClientInfoFullBill(DeliveryVO bill) {
    DeliveryVO[] bills = new DeliveryVO[] {
      bill
    };
    BillTransferTool<DeliveryVO> transferTool =
        new BillTransferTool<DeliveryVO>(bills);
    return transferTool.getClientFullInfoBill()[0];
  }

  private DeliveryVO getFullBill(AggregatedValueObject vo) {
    // 新增
    DeliveryVO bill = (DeliveryVO) vo;
    // 修改
    String id = bill.getParentVO().getCdeliveryid();
    if ((id != null) && (id.trim().length() > 0)) {
      bill = this.getClientInfoFullBill(bill);
    }
    return bill;
  }
}
