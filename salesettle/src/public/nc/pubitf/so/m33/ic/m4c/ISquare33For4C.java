package nc.pubitf.so.m33.ic.m4c;

import java.util.Map;

import nc.vo.ic.m4c.entity.SaleOutVO;
import nc.vo.pub.BusinessException;

/**
 * 销售结算提供给销售出库单服务：
 * 
 * 1.销售出库单推式生成销售结算单
 * 
 * 2.销售出库单取消结算
 * 
 * @author zhangcheng
 * 
 */
public interface ISquare33For4C {

  /**
   * 销售出库单取消签字时调用取消结算
   * 
   * @param saleOutVOs
   * @throws BusinessException
   */
  void cancelSquareSrv(SaleOutVO[] saleOutVOs) throws BusinessException;

  /**
   * 销售出库单签字时调用自动推式生成销售结算单
   * 
   * @param saleOutVOs
   * @throws BusinessException
   */
  void pushSquareSrv(SaleOutVO[] saleOutVOs) throws BusinessException;

  /**
   * 方法功能描述：出库对冲
   * <p>
   * <b>examples:</b>
   * <p>
   * 使用示例
   * <p>
   * <b>参数说明</b>
   * 
   * @param blueOutVO -- 蓝字出库单
   * @param redOutVOs -- 红字出库单数组
   * @throws BusinessException
   *           <p>
   * @author zhangcheng
   * @time 2010-7-20 下午07:45:44
   */
  void squareOutRush(SaleOutVO blueOutVO, SaleOutVO[] redOutVOs)
      throws BusinessException;

  /**
   * 结算单为销售出库单提供的查询出库单行结算单明细服务
   * 
   * @param outhids 出库单表头ID数组
   * @param outbids 出库单表体ID数组
   * @return Map<String,String> Key:结算单明细行ID Value:对应出库单表体ID
   * @throws BusinessException
   * 
   * @author 梁吉明
   * @time 2012-9-20 下午07:45:44
   */
  Map<String, String> queryOutSquareDetail(String[] outhids, String[] outbids)
      throws BusinessException;
}
