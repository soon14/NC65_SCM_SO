package nc.bs.so.m33.biz.m4c.rule.push;

import nc.bs.so.m33.maintain.m4c.UpdateSquare4CFlagBP;
import nc.impl.pubapp.pattern.rule.IRule;
import nc.vo.so.m33.m4c.entity.SquareOutVO;

/**
 * @description
 * 销售出库单推式生成销售结算单时的业务规则，设置默认的结算标志
 * @scene
 * 销售出库单推式生成销售结算单时
 * @param 
 * 无
 * @version 本版本号
 * @since 上一版本号
 * @author zhangcheng
 * @time 2010-5-28 上午10:12:52
 */
public class Before4CPush33Rule implements IRule<SquareOutVO> {

  @Override
  public void process(SquareOutVO[] vos) {
    // 设置默认的结算标志
    this.setDefaultSquareFlag(vos);
  }

  /**
   * 设置默认的结算标志当销售出库单推式生成结算单的时候
   * 
   * @param vos
   */
  private void setDefaultSquareFlag(SquareOutVO[] vos) {
    // 销售出库单推式生成结算单的时候更新表体结算标志位
    new UpdateSquare4CFlagBP().updateSquareBFlagFor4CPush33(vos);
  }

}
