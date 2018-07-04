package nc.pf.so.function;

import nc.impl.pubapp.pattern.data.bill.tool.BillTransferTool;
import nc.vo.pub.AggregatedValueObject;
import nc.vo.pub.lang.UFBoolean;
import nc.vo.pub.lang.UFDouble;
import nc.vo.so.m38.entity.PreOrderVO;
import nc.vo.so.pub.function.SOFunctionUtil;

public class PreOrderFunction {

  private BillTransferTool<PreOrderVO> transferTool;

  /**
   * 函数——预订单表体行的净价/询到净价的最大值
   * <p>
   * <b>支持预订单交易类型约束配置</b>
   * <p>
   * <b>配置方式,例如：</b>
   * <ul>
   * <li>1.
   * <li>2.
   * <li>3...
   * </ul>
   * <p>
   * <b>参数说明</b>
   * 
   * @param vo
   * @return
   *         <p>
   * @author 刘志伟
   * @time 2010-10-19 下午01:45:01
   */
  public UFDouble getMaxPriceRate(AggregatedValueObject vo) {
    PreOrderVO bill = this.getFullBill(vo);

    SOFunctionUtil util = new SOFunctionUtil();
    UFDouble dMaxPriceRate = util.getMaxPriceRate(bill);
    return dMaxPriceRate;
  }

  /**
   * 函数——预订单表体行的净价/询到净价的最小值
   * <p>
   * <b>支持预订单交易类型约束配置</b>
   * <p>
   * <b>配置方式,例如：</b>
   * <ul>
   * <li>1.
   * <li>2.
   * <li>3...
   * </ul>
   * <p>
   * <b>参数说明</b>
   * 
   * @param vo AggregatedValueObject
   * @return UFDouble
   *         <p>
   * @author 刘志伟
   * @time 2010-10-19 下午01:45:01
   */
  public UFDouble getMinPriceRate(AggregatedValueObject vo) {
    PreOrderVO bill = this.getFullBill(vo);

    SOFunctionUtil util = new SOFunctionUtil();
    UFDouble dMinPriceRate = util.getMinPriceRate(bill);
    return dMinPriceRate;
  }

  /**
   * 根据新增或修改获得补全VO
   * 
   * @param vo AggregatedValueObject
   * @return SaleOrderVO
   * @author 刘志伟
   * @time 2010-10-20 上午09:23:56
   */
  private PreOrderVO getFullBill(AggregatedValueObject vo) {
    // 新增
    PreOrderVO bill = (PreOrderVO) vo;
    // 修改
    String strOrderID = bill.getParentVO().getCpreorderid();
    if ((strOrderID != null) && (strOrderID.trim().length() > 0)) {
      bill = this.getClientInfoFullBill(bill);
    }
    return bill;
  }

  private PreOrderVO getClientInfoFullBill(PreOrderVO bill) {
    if (this.transferTool == null) {
      PreOrderVO[] bills = new PreOrderVO[] {
        bill
      };
      this.transferTool = new BillTransferTool<PreOrderVO>(bills);
    }
    return this.transferTool.getClientFullInfoBill()[0];
  }

  /**
   * 函数——销售净价不能小于物料档案的最低售价*客户档案的物料最低售价比例:
   * 逐行检查、任意行不满足即返回false
   * <p>
   * <b>支持预订单交易类型约束配置：</b>
   * <ul>
   * <li>1.
   * <li>2.
   * <li>3....
   * </ul>
   * <p>
   * <b>配置方式,例如：</b>
   * <ul>
   * <li>1.
   * <li>2.
   * <li>3...
   * </ul>
   * 
   * <p>
   * <b>参数说明</b>
   * 
   * @param vo
   * @return UFBoolean
   *         <p>
   * @author 刘志伟
   * @time 2010-10-20 上午09:23:56
   */
  public UFBoolean examSaleNetprice(AggregatedValueObject vo) {
    PreOrderVO bill = this.getFullBill(vo);

    SOFunctionUtil util = new SOFunctionUtil();
    return util.examSaleNetprice(bill);
  }

  /**
   * 函数——表体行整单折扣*单品折扣的最大值
   * <p>
   * <b>支持预订单交易类型约束配置：</b>
   * <ul>
   * <li>1.
   * <li>2.
   * <li>3....
   * </ul>
   * <p>
   * <b>配置方式,例如：</b>
   * <ul>
   * <li>1.
   * <li>2.
   * <li>3....
   * </ul>
   * 
   * <p>
   * <b>参数说明</b>
   * 
   * @param vo
   * @return UFDouble
   *         <p>
   * @author 刘志伟
   * @time 2010-10-23 下午12:49:41
   */
  public UFDouble getMaxDiscountRate(AggregatedValueObject vo) {
    PreOrderVO bill = this.getFullBill(vo);

    SOFunctionUtil util = new SOFunctionUtil();
    UFDouble maxDiscountRate = util.getMaxDiscountRate(bill);
    return maxDiscountRate;
  }

  /**
   * 函数——表体行整单折扣*单品折扣的最小值
   * <p>
   * <b>支持预订单交易类型约束配置：</b>
   * <ul>
   * <li>1.
   * <li>2.
   * <li>3....
   * </ul>
   * <p>
   * <b>配置方式,例如：</b>
   * <ul>
   * <li>1.
   * <li>2.
   * <li>3....
   * </ul>
   * 
   * <p>
   * <b>参数说明</b>
   * 
   * @param vo
   * @return UFDouble
   *         <p>
   * @author 刘志伟
   * @time 2010-10-23 下午12:49:41
   */
  public UFDouble getMinDiscountRate(AggregatedValueObject vo) {
    PreOrderVO bill = this.getFullBill(vo);

    SOFunctionUtil util = new SOFunctionUtil();
    UFDouble minDiscountRate = util.getMinDiscountRate(bill);
    return minDiscountRate;
  }
}
