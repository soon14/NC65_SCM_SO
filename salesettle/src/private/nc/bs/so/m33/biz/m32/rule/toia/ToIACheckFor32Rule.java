package nc.bs.so.m33.biz.m32.rule.toia;

import nc.impl.pubapp.pattern.rule.IRule;
import nc.vo.pubapp.pattern.exception.ExceptionUtils;
import nc.vo.pubapp.pattern.pub.PubAppTool;
import nc.vo.so.m33.m32.entity.SquareInvBVO;
import nc.vo.so.m33.m32.entity.SquareInvVO;

/**
 * @description
 * 上游出库单做过发出商品，发票传发出商品结算 ,结算传成本前校验检查成本域规则
 * @scene
 * 上游出库单做过发出商品，发票传发出商品成本结算前
 * @param
 * 无
 *
 * @version 本版本号
 * @since 上一版本号
 * @author zhangcheng
 * @time 2010-5-28 上午10:19:52
 */
public class ToIACheckFor32Rule implements IRule<SquareInvVO> {

  private void checkNullField(SquareInvVO[] vos) {

    String errorMsg = null;

    for (SquareInvVO svo : vos) {
      for (SquareInvBVO bvo : svo.getChildrenVO()) {



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
  public void process(SquareInvVO[] vos) {

    // 非空项检测
    this.checkNullField(vos);

    // 单价金额平衡性计算

  }

}