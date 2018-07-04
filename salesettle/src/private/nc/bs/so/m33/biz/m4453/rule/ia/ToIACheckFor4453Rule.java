package nc.bs.so.m33.biz.m4453.rule.ia;

import nc.impl.pubapp.pattern.rule.IRule;
import nc.vo.pubapp.pattern.exception.ExceptionUtils;
import nc.vo.pubapp.pattern.pub.PubAppTool;
import nc.vo.so.m33.m4453.entity.SquareWasBVO;
import nc.vo.so.m33.m4453.entity.SquareWasVO;

/**
 * @description
 * 销售出库单传成本、传发出商品前检查成本域
 * @scene
 * 销售出库单传成本、传发出商品前
 * @param
 * 无
 * 
 * @version 本版本号
 * @since 上一版本号
 * @author zhangcheng
 * @time 2010-5-28 上午10:19:52
 */
public class ToIACheckFor4453Rule implements IRule<SquareWasVO> {

  private void checkNullField(SquareWasVO[] vos) {

    String errorMsg = null;

    for (SquareWasVO svo : vos) {
      for (SquareWasBVO bvo : svo.getChildrenVO()) {



        // 结算财务组织
        if (PubAppTool.isNull(bvo.getPk_org())
            || PubAppTool.isNull(svo.getParentVO().getPk_org())) {
          errorMsg = nc.vo.ml.NCLangRes4VoTransl.getNCLangRes().getStrByID("4006010_0","04006010-0014")/*@res "生成待结算单的结算财务组织为空！"*/;
          break;
        }

        // 应收组织
        /*
         * if (PubAppTool.getInstance().isNull(bvo.getCarorgid())){ errorMsg =
         * "生成待结算单的应收组织为空！"; break; }
         */


        // 成本域
        if (PubAppTool.isNull(bvo.getCcostorgid())) {
          errorMsg = nc.vo.ml.NCLangRes4VoTransl.getNCLangRes().getStrByID("4006010_0","04006010-0016")/*@res "生成待结算单的成本域为空！"*/;
          break;
        }

      }
    }

    if (errorMsg != null) {

      ExceptionUtils.wrappBusinessException(errorMsg);
    }
  }

  @Override
  public void process(SquareWasVO[] vos) {

    // 非空项检测
    this.checkNullField(vos);

    // 单价金额平衡性计算

  }

}