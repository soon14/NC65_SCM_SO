package nc.bs.so.pub;

import nc.impl.pubapp.pattern.data.bill.IAttributeOrderConvert;

/**
 * 
 * @description
 *              销售管理查询表体按照行号排序
 * @scene
 *        查询单据
 * @param 无
 * 
 * @since 6.5
 * @version 2015-11-16 下午2:42:21
 * @author zhangby5
 */
public class SORowNoOrderConvert implements IAttributeOrderConvert {

  @Override
  public String convert(String sqlname) {
    return " cast(" + sqlname + " as float) ";
  }
}
