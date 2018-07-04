package nc.itf.so.multipleprofit;

import com.ufida.dataset.IContext;

import nc.vo.pub.BusinessException;

/**
 * 综合毛利分析查询接口
 * 
 * @since 6.3
 * @version 2012-10-18 14:13:10
 * @author zhangkai4
 */
public interface IMultipleProfitMaintain {

  /**
   * 生成查询“综合毛利分析”的sql语句
   * 
   * @param context 上下文
   * @return String 查询临时表的SQL语句
   * @throws BusinessException
   */
  public String queryMultipleProfit(IContext context) throws BusinessException;
}
