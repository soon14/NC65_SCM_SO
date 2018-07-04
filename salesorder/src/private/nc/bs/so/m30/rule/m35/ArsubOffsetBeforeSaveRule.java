package nc.bs.so.m30.rule.m35;

import nc.impl.pubapp.pattern.rule.IRule;
import nc.vo.pub.lang.UFBoolean;
import nc.vo.so.m30.entity.SaleOrderBVO;
import nc.vo.so.m30.entity.SaleOrderHVO;
import nc.vo.so.m30.entity.SaleOrderVO;

/**
 * 
 * @description
 * 销售订单保存时
 * @scene
 * 赠品兑付费用单冲抵保存前处理
 * @param
 * 无
 *
 * @since 6.35
 * @version 2013-11-28 下午03:51:53
 * @author dongli2
 */
public class ArsubOffsetBeforeSaveRule implements IRule<SaleOrderVO> {

  @Override
  public void process(SaleOrderVO[] vos) {
    for (SaleOrderVO bill : vos) {
      SaleOrderHVO header = bill.getParentVO();
      String arsubtypeid = header.getCarsubtypeid();
      if (null != arsubtypeid) {
        SaleOrderBVO[] bodys = bill.getChildrenVO();
        for (SaleOrderBVO bvo : bodys) {
          this.changeOrderbody(bvo);
        }
      }
    }
  }

  /**
   * 处理赠品和赠品兑付标记
   * 
   * @param thissub
   * @param rowindex
   */
  private void changeOrderbody(SaleOrderBVO bvo) {
    // 处理赠品和赠品兑付标志
    bvo.setBlargessflag(UFBoolean.TRUE);
    bvo.setBlrgcashflag(UFBoolean.TRUE);
  }

}
