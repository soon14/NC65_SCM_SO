package nc.bs.so.m33.biz.m4c.bp.square.ia;

import nc.itf.so.m33.ref.ia.mi5.IAI5For4CServicesUtil;
import nc.vo.ia.mi5.entity.I5BillVO;

/**
 * 出库对冲传发出商品贷方
 * 
 * @since 6.0
 * @version 2011-8-3 上午10:40:04
 * @author zhangcheng
 */
public class SquareIARegisterCreditFor4COutRushBP extends
    AbstractSquareIARegisterCreditFor4CBP {

  @Override
  protected void toIA(I5BillVO[] i5vos) {
    // 调用I5的销售出库单发出商品接口
    IAI5For4CServicesUtil.insertI5ForSO4CIntransitForOutrush(i5vos);
  }

}
