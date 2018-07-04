package nc.bs.so.m33.biz.m4c.bp.square.ia;

import nc.itf.so.m33.ref.ia.mi5.IAI5For4CServicesUtil;
import nc.vo.ia.mi5.entity.I5BillVO;

/**
 * 结算关闭传发出商品贷方
 * 
 * @since 6.0
 * @version 2011-8-3 上午10:39:48
 * @author zhangcheng
 */
public class SquareIARegisterCreditFor4COrderEndBP extends
    AbstractSquareIARegisterCreditFor4CBP {

  @Override
  protected void toIA(I5BillVO[] i5vos) {
    // 调用I5的销售出库单发出商品接口
    IAI5For4CServicesUtil.insertI5ForSOSquareEnd(i5vos);
  }

}
