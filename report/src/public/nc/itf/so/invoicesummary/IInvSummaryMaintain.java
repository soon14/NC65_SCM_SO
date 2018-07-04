package nc.itf.so.invoicesummary;

import nc.vo.pub.BusinessException;

import com.ufida.dataset.IContext;

/**
 * 销售发票执行汇总接口
 * 
 * @since 6.3
 * @version 2012-9-23 上午10:16:40
 * @author 梁吉明
 */
public interface IInvSummaryMaintain {
  /**
   * 
   * @param context 上下文
   * @return String 查询临时表的SQL语句
   * @throws BusinessException
   */
  public String queryInvSummary(IContext context) throws BusinessException;
}
