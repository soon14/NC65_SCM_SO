package nc.impl.so.m30trantype.rule;

import nc.impl.pubapp.pattern.rule.ICompareRule;
import nc.impl.so.m30trantype.tool.CheckKeyEditableTool;
import nc.vo.so.m30.entity.SaleOrderHVO;
import nc.vo.so.m30trantype.entity.M30TranTypeVO;

/**
 * @description
 *  校验交易类型销售模式属性是否可修改
 * @scene
 *  交易类型修改保存前规则
 * @param
 *  无
 * @since 6.36
 * @version 2015-1-14 下午6:26:20
 * @author wangshu6
 */
public class CheckSaleModeEditableRule implements ICompareRule<M30TranTypeVO> {


  @Override
  public void process(M30TranTypeVO[] updateTransTypeVOs,
      M30TranTypeVO[] originVOs) {
    CheckKeyEditableTool<SaleOrderHVO, M30TranTypeVO> tool =
        new CheckKeyEditableTool<SaleOrderHVO, M30TranTypeVO>(
            SaleOrderHVO.class, M30TranTypeVO.class, M30TranTypeVO.FSALEMODE);
    tool.checkKeySuccessModify(updateTransTypeVOs, originVOs);
  }
}
