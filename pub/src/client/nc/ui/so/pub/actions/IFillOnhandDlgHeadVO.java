package nc.ui.so.pub.actions;

import nc.vo.pub.CircularlyAccessibleValueObject;
import nc.vo.so.entry.SOOnhandDlgHeadVO;

/**
 * 
 * @description
 *              补全存量查拣表头数据接口
 * @scene
 *        存量查拣补全数据
 * @param 无
 * 
 * @since 6.5
 * @version 2015-11-19 下午7:21:42
 * @author zhangby5
 */
public interface IFillOnhandDlgHeadVO {

  /**
   * 补全存量查拣表头数据
   * 
   * @return 存量查拣表头VO
   */
  SOOnhandDlgHeadVO fillOnhandVO(CircularlyAccessibleValueObject hvo,
      CircularlyAccessibleValueObject bvo);
}
