package nc.pubitf.so.mbuylargess.pub;

import nc.vo.pub.lang.UFDate;
import nc.vo.pub.lang.UFDouble;

/**
 * 买赠政策匹配参数接口
 * 
 * @since 6.1
 * @version 2012-10-30 17:57:32
 * @author 冯加彬
 */
public interface IBuyLargessMatchPara {

  /**
   * 返回销售组织
   * 
   * @return saleorgid
   */
  String getCsaleorgid();

  /**
   * 返回物料OID
   * 
   * @return marterialid
   */
  String getCmarterialid();

  /**
   * 返回业务单位ID
   * 
   * @return astunitid
   */
  String getCastunitid();

  /**
   * 返回订单客户
   * 
   * @return customerid
   */
  String getCcustomerid();

  /**
   * 返回币种
   * 
   * @return origcurrencyid
   */
  String getCorigcurrencyid();

  /**
   * 返回单据日期
   * 
   * @return billdate
   */
  UFDate getDbilldate();

  /**
   * 返回数量
   * 
   * @return astnum
   */
  UFDouble getNastnum();
}
