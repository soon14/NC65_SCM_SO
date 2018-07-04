package nc.bs.so.m33.maintain.m4c.rule.square;

import nc.impl.pubapp.pattern.rule.IRule;
import nc.vo.pubapp.pattern.exception.ExceptionUtils;
import nc.vo.pubapp.pattern.pub.PubAppTool;
import nc.vo.so.m33.m4c.entity.SquareOutBVO;
import nc.vo.so.m33.m4c.entity.SquareOutVO;
import nc.vo.so.m33.pub.biz.vocheck.NumPriceMnyPubCheck;

/**
 * @description
 * 销售结算单保存校验规则
 * @scene
 * 销售结算单保存前
 * @param 
 * 无
 */
public class InsSQ4CCheckRule implements IRule<SquareOutVO> {

  @Override
  public void process(SquareOutVO[] vos) {

    // 非空项检测
    this.checkNullField(vos);

    // 单价金额平衡性计算
    new NumPriceMnyPubCheck<SquareOutVO>().checkData(vos);
  }

  private void checkNullField(SquareOutVO[] vos) {
    String errorMsg = null;
    for (SquareOutVO svo : vos) {
      for (SquareOutBVO bvo : svo.getChildrenVO()) {
        // 结算财务组织
        if (PubAppTool.isNull(bvo.getPk_org())
            || PubAppTool.isNull(svo.getParentVO().getPk_org())) {
          errorMsg =
              nc.vo.ml.NCLangRes4VoTransl.getNCLangRes().getStrByID(
                  "4006010_0", "04006010-0042")/*@res "生成销售出库待结算单的结算财务组织为空！"*/;
          break;
        }
        if (PubAppTool.isNull(bvo.getCcostorgid())) {
          errorMsg =
              nc.vo.ml.NCLangRes4VoTransl.getNCLangRes().getStrByID(
                  "4006010_0", "04006010-0043")/*@res "生成销售出库待结算单的成本域不能为空！"*/;
          break;
        }
        if (PubAppTool.isNull(bvo.getCcurrencyid())) {
          errorMsg =
              nc.vo.ml.NCLangRes4VoTransl.getNCLangRes().getStrByID(
                  "4006010_0", "04006010-0044")/*@res "生成销售出库待结算单的本币不能为空！"*/;
          break;
        }
        if (PubAppTool.isNull(bvo.getCorigcurrencyid())) {
          errorMsg =
              nc.vo.ml.NCLangRes4VoTransl.getNCLangRes().getStrByID(
                  "4006010_0", "04006010-0045")/*@res "生成销售出库待结算单的原币不能为空！"*/;
          break;
        }
        if (PubAppTool.isNull(bvo.getCorigcurrencyid())) {
          errorMsg =
              nc.vo.ml.NCLangRes4VoTransl.getNCLangRes().getStrByID(
                  "4006010_0", "04006010-0045")/*@res "生成销售出库待结算单的原币不能为空！"*/;
          break;
        }
      }
    }

    if (errorMsg != null) {
      ExceptionUtils.wrappBusinessException(errorMsg);
    }
  }

}
