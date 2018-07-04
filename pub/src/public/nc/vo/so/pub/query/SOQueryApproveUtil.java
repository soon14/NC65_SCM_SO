package nc.vo.so.pub.query;

import nc.ui.querytemplate.querytree.IQueryScheme;
import nc.vo.pub.AggregatedValueObject;
import nc.vo.pub.lang.UFBoolean;
import nc.vo.pubapp.AppContext;
import nc.vo.pubapp.pattern.data.ValueUtils;
import nc.vo.pubapp.pflow.PfServiceUtil;
import nc.vo.pubapp.query2.sql.process.QueryCondition;
import nc.vo.pubapp.query2.sql.process.QuerySchemeProcessor;

import org.apache.commons.lang.ArrayUtils;

/**
 * 销售查询过滤"待审批"工具类
 * 
 * @since 6.0
 * @version 2011-7-19 上午09:49:41
 * @author 刘志伟
 */
public class SOQueryApproveUtil {

  /**
   * 待审批(查询模板配置"待审批"字段名称)
   */
  public static final String BISAPPROVING = "bisapproving";

  /**
   * 从给定的业务VO数组中过滤出给定用户的待审批的业务VO
   * 
   * @param queryScheme 查询方案
   * @param vos 需要过滤的聚合VO数组
   * @param billType 单据类型
   * @return 过滤后的聚合VO数组
   */
  public static <T extends AggregatedValueObject> T[] filterForApprove(
      IQueryScheme queryScheme, T[] vos, String billType, String trantypekey) {
    if (ArrayUtils.isEmpty(vos)) {
      return null;
    }

    QuerySchemeProcessor qrySchemeProcessor =
        new QuerySchemeProcessor(queryScheme);
    QueryCondition condition =
        qrySchemeProcessor.getQueryCondition(SOQueryApproveUtil.BISAPPROVING);

    if (null == condition) {
      return vos;
    }

    Object[] values = condition.getValues();
    if (ValueUtils.getUFBoolean(values[0]).booleanValue()) {
      String userId = AppContext.getInstance().getPkUser();
      return PfServiceUtil.filterForApprove(vos, billType, userId, trantypekey);
    }

    return vos;
  }
}
