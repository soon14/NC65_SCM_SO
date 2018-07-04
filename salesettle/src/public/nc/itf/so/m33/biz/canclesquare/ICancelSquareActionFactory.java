package nc.itf.so.m33.biz.canclesquare;

import nc.vo.pub.SuperVO;
import nc.vo.so.m33.enumeration.SquareType;

/**
 * 销售结算抽象工厂
 * 
 * @author zhangcheng
 * 
 */
public interface ICancelSquareActionFactory<T extends SuperVO> {

  /**
   * @return 取消结算动作
   */
  ICancelSquareAction<T> createCancelSquareAction(SquareType type);

}
