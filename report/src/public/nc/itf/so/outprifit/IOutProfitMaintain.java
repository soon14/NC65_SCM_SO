package nc.itf.so.outprifit;

import com.ufida.dataset.IContext;

import nc.vo.pub.BusinessException;

/**
 * 销售出库毛利分析接口
 * 
 * @since 6.3
 * @version 2012-08-28 09:57:56
 * @author 梁吉明
 */
public interface IOutProfitMaintain {

  /**
   * 
   * @param context 上下文
   * @return String 查询临时表的SQL语句
   * @throws BusinessException
   */
  public String queryOutPrifit(IContext context) throws BusinessException;
}
