package nc.pubitf.so.m30.so.m32;

import java.util.Map;

import nc.ui.querytemplate.querytree.IQueryScheme;
import nc.vo.pub.BusinessException;
import nc.vo.so.m30.paravo.Info30For32Para;

/**
 * 销售订单提供给销售发票接口服务
 * 
 * @since 6.0
 * @version 2011-5-4 上午10:03:29
 * @author 刘志伟
 */
public interface ISaleOrderFor32 {

  /**
   * 根据32附表名称和32附表源头表体id名称构造过滤掉30已出库关闭关联SQL片段
   * 
   * @param invbodytable 出库单附表ID
   * @param cfirstbid 源头附表ID
   * @return IQueryScheme sql片段
   * @throws BusinessException
   */
  IQueryScheme getOutEndSQL4Filter32(String invbodytable, String cfirstbid)
      throws BusinessException;

  /**
   * 根据销售订单主表id查询散户id
   * <p>不是散户则散户id为null</p>
   * 
   * @param ids 销售订单主表id[]
   * @return Map<String, Info30For32Para> Map<销售订单表头id, 散户id、收款协议>
   * @throws BusinessException
   */
  Map<String, Info30For32Para> queryInfosByOrderBIDs(String[] bids)
      throws BusinessException;
}
