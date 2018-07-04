package nc.bs.so.m33.maintain.m32;

import nc.impl.pubapp.pattern.data.vo.VOUpdate;
import nc.vo.so.m33.m32.entity.SquareInvBVO;
import nc.vo.so.m33.m32.entity.SquareInvHVO;
import nc.vo.so.m33.m32.entity.SquareInvVO;
import nc.vo.so.m33.m32.entity.SquareInvVOUtils;

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
public class UpdateSquare32FieldsBP {

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
  public void updateFields(SquareInvVO[] sqvos, String[] hFlagKeys,
      String[] bFlagKeys) {

    if ((sqvos == null) || (sqvos.length <= 0)) {
      return;
    }

    if ((hFlagKeys != null) && (hFlagKeys.length > 0)) {
      VOUpdate<SquareInvHVO> bo = new VOUpdate<SquareInvHVO>();
      bo.update(SquareInvVOUtils.getInstance().getSquareInvHVO(sqvos),
          hFlagKeys);
    }

    if ((bFlagKeys != null) && (bFlagKeys.length > 0)) {
      VOUpdate<SquareInvBVO> bbo = new VOUpdate<SquareInvBVO>();
      bbo.update(SquareInvVOUtils.getInstance().getSquareInvBVO(sqvos),
          bFlagKeys);
    }

  }

}
