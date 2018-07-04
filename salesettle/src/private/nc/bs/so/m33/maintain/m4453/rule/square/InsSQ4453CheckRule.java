package nc.bs.so.m33.maintain.m4453.rule.square;

import nc.bs.ml.NCLangResOnserver;
import nc.impl.pubapp.pattern.rule.IRule;
import nc.vo.pubapp.pattern.exception.ExceptionUtils;
import nc.vo.pubapp.pattern.pub.PubAppTool;
import nc.vo.so.m33.m4453.entity.SquareWasBVO;
import nc.vo.so.m33.m4453.entity.SquareWasVO;
import nc.vo.so.m33.pub.biz.vocheck.NumPriceMnyPubCheck;

/**
 * @description
 * 推式生成途损待结算单保存前校验规则
 * @scene
 * 推式生成途损待结算单保存前
 * @param
 * 无
 */
public class InsSQ4453CheckRule implements IRule<SquareWasVO> {

  @Override
  public void process(SquareWasVO[] vos) {

    // 非空项检测
    this.checkNullField(vos);

    // 单价金额平衡性计算
    new NumPriceMnyPubCheck<SquareWasVO>().checkData(vos);
  }

  private void checkNullField(SquareWasVO[] vos) {
    String errorMsg = null;
    for (SquareWasVO svo : vos) {
      for (SquareWasBVO bvo : svo.getChildrenVO()) {
        // 结算财务组织
        if (PubAppTool.isNull(bvo.getPk_org())
            || PubAppTool.isNull(svo.getParentVO().getPk_org())) {
          errorMsg =
              NCLangResOnserver.getInstance().getStrByID("4006010_0",
                  "04006010-0097")/*生成途损待结算单的结算财务组织为空！*/;
          break;
        }
        if (PubAppTool.isNull(bvo.getCcostorgid())) {
          errorMsg =
              NCLangResOnserver.getInstance().getStrByID("4006010_0",
                  "04006010-0098")/*生成途损待结算单的成本域不能为空！*/;
          break;
        }
        if (PubAppTool.isNull(bvo.getCcurrencyid())) {
          errorMsg =
              NCLangResOnserver.getInstance().getStrByID("4006010_0",
                  "04006010-0099")/*生成途损待结算单的本币不能为空！*/;
          break;
        }
        if (PubAppTool.isNull(bvo.getCorigcurrencyid())) {
          errorMsg =
              NCLangResOnserver.getInstance().getStrByID("4006010_0",
                  "04006010-0100")/*生成途损待结算单的原币不能为空！*/;
          break;
        }
        if (PubAppTool.isNull(bvo.getCorigcurrencyid())) {
          errorMsg =
              NCLangResOnserver.getInstance().getStrByID("4006010_0",
                  "04006010-0100")/*生成途损待结算单的原币不能为空！*/;
          break;
        }
      }
    }

    if (errorMsg != null) {
      ExceptionUtils.wrappBusinessException(errorMsg);
    }
  }

}
