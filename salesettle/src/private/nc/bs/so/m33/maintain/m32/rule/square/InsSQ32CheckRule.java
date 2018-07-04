package nc.bs.so.m33.maintain.m32.rule.square;

import nc.impl.pubapp.pattern.rule.IRule;
import nc.vo.pubapp.pattern.exception.ExceptionUtils;
import nc.vo.pubapp.pattern.pub.PubAppTool;
import nc.vo.so.m33.m32.entity.SquareInvBVO;
import nc.vo.so.m33.m32.entity.SquareInvVO;
import nc.vo.so.m33.pub.biz.vocheck.NumPriceMnyPubCheck;

/**
 * @description
 * 销售发票结算保存前校验：非空项检测、单价金额平衡性计算
 * @scene
 * 销售发票结算前
 * @param
 * 无
 */
public class InsSQ32CheckRule implements IRule<SquareInvVO> {

  @Override
  public void process(SquareInvVO[] vos) {
    // 非空项检测
    this.checkNullField(vos);
    // 单价金额平衡性计算
    new NumPriceMnyPubCheck<SquareInvVO>().checkData(vos);
  }

  private void checkNullField(SquareInvVO[] vos) {
    String errorMsg = null;
    for (SquareInvVO svo : vos) {
      for (SquareInvBVO bvo : svo.getChildrenVO()) {
        // 结算财务组织
        if (PubAppTool.isNull(bvo.getPk_org())
            || PubAppTool.isNull(svo.getParentVO().getPk_org())) {
          errorMsg =
              nc.vo.ml.NCLangRes4VoTransl.getNCLangRes().getStrByID(
                  "4006010_0", "04006010-0033")/*@res "生成销售发票待结算单的结算财务组织为空！"*/;
          break;
        }
        if (PubAppTool.isNull(bvo.getCcurrencyid())) {
          errorMsg =
              nc.vo.ml.NCLangRes4VoTransl.getNCLangRes().getStrByID(
                  "4006010_0", "04006010-0034")/*@res "生成销售发票待结算单的本币不能为空！"*/;
          break;
        }
        if (PubAppTool.isNull(bvo.getCorigcurrencyid())) {
          errorMsg =
              nc.vo.ml.NCLangRes4VoTransl.getNCLangRes().getStrByID(
                  "4006010_0", "04006010-0035")/*@res "生成销售发票待结算单的原币不能为空！"*/;
          break;
        }
      }
    }
    if (errorMsg != null) {
      ExceptionUtils.wrappBusinessException(errorMsg);
    }
  }

}
