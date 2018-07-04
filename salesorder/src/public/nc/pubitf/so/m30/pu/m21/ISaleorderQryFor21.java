package nc.pubitf.so.m30.pu.m21;

import java.util.Map;

import nc.vo.pub.lang.UFDate;
import nc.vo.pub.lang.UFDouble;

/**
 * 查询已审核和关闭状态的销售订单上的物料数量
 * 
 * @since 6.0
 * @version 2010-12-15 上午09:01:05
 * @author 么贵敬
 */
public interface ISaleorderQryFor21 {

  /**
   * 查询已审核和关闭状态的销售订单上的物料数量
   * 
   * @param cmaterialid 物料ID
   * @param queryDate 查询开始日期
   * @param queryDay 时间短长度
   * @param pk_group 集团
   * @param pk_org 销售组织
   * @return 物料ID为KEY，数量为Value的Map
   */
  Map<String, UFDouble> getSaleOrderNumber(String[] cmaterialid,
      UFDate queryDate, Integer queryDay, String pk_group, String pk_org);
}
