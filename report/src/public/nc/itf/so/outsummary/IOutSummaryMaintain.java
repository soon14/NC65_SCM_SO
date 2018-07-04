package nc.itf.so.outsummary;

import nc.vo.pub.BusinessException;

import com.ufida.dataset.IContext;

/**
 * 销售出库执行汇总接口
 * 
 * @since 6.3
 * @version 2012-10-18 下午01:14:50
 * @author 梁吉明
 */
public interface IOutSummaryMaintain {
  /**
   * 
   * @param context 上下文
   * @return String 查询临时表的SQL语句
   * @throws BusinessException
   */
  public String queryOutSummary(IContext context) throws BusinessException;
}
