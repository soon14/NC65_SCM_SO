package nc.bs.so.m33.maintain.m4453;

import nc.impl.pubapp.pattern.data.vo.VOUpdate;
import nc.vo.so.m33.m4453.entity.SquareWasBVO;
import nc.vo.so.m33.m4453.entity.SquareWasHVO;
import nc.vo.so.m33.m4453.entity.SquareWasVO;
import nc.vo.so.m33.m4453.entity.SquareWasVOUtils;

/**
 * <p>
 * <b>本类主要完成以下功能：</b>
 * 
 * <ul>
 * <li>更新销售结算单表头或者表体指定字段
 * <li>
 * <li>...
 * </ul>
 * 
 * <p>
 * <b>变更历史（可选）：</b>
 * <p>
 * XXX版本增加XXX的支持。
 * <p>
 * <p>
 * 
 * @version 本版本号
 * @since 上一版本号
 * @author zhangcheng
 * @time 2010-5-28 上午10:37:12
 */
public class UpdateSquare4453FieldsBP {

  /**
   * 方法功能描述：更新结算单表头表体指定字段
   * <p>
   * <b>examples:</b>
   * <p>
   * 使用示例
   * <p>
   * <b>参数说明</b>
   * 
   * @param sqvos
   * @param hFlagKeys
   * @param bFlagKeys
   *          <p>
   * @author zhangcheng
   * @time 2010-5-28 上午10:42:16
   */
  public void updateFields(SquareWasVO[] sqvos, String[] hFlagKeys,
      String[] bFlagKeys) {

    if ((sqvos == null) || (sqvos.length <= 0)) {
      return;
    }

    if ((hFlagKeys != null) && (hFlagKeys.length > 0)) {
      VOUpdate<SquareWasHVO> bo = new VOUpdate<SquareWasHVO>();
      bo.update(SquareWasVOUtils.getInstance().getSquareWasHVO(sqvos),
          hFlagKeys);
    }

    if ((bFlagKeys != null) && (bFlagKeys.length > 0)) {
      VOUpdate<SquareWasBVO> bbo = new VOUpdate<SquareWasBVO>();
      bbo.update(SquareWasVOUtils.getInstance().getSquareWasBVO(sqvos),
          bFlagKeys);
    }

  }

}
