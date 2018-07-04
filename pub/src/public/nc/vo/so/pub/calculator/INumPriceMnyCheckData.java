package nc.vo.so.pub.calculator;

import nc.vo.pub.lang.UFBoolean;
import nc.vo.pubapp.calculator.data.IDataSetForCal;

public interface INumPriceMnyCheckData extends IDataSetForCal {

  /** 获得是否折扣类物料 */
  UFBoolean getBdiscountflag();

  /** 获得是否费用类物料 */
  UFBoolean getBlaborflag();

  /** 获得是否赠品 */
  UFBoolean getBlargessflag();

}
