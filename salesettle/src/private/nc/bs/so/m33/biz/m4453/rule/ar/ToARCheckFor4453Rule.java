package nc.bs.so.m33.biz.m4453.rule.ar;

import java.util.HashMap;
import java.util.Map;

import nc.bs.so.pub.rule.CustDistributeCheck;
import nc.impl.pubapp.pattern.rule.IRule;
import nc.vo.pubapp.pattern.exception.ExceptionUtils;
import nc.vo.pubapp.pattern.pub.PubAppTool;
import nc.vo.so.m33.m4453.entity.SquareWasBVO;
import nc.vo.so.m33.m4453.entity.SquareWasVO;

/**
 * @description
 * 销售途损应收结算前数据校验（非空项检测、检查客户是否分属于集团或者当前应收组织）
 * @scene
 * 销售途损应收结算、回冲应收结算前
 * @param
 * 无
 * 
 * @version 本版本号
 * @since 上一版本号
 * @author zhangcheng
 * @time 2010-5-28 上午10:17:23
 */
public class ToARCheckFor4453Rule implements IRule<SquareWasVO> {

  @Override
  public void process(SquareWasVO[] vos) {

    // 非空项检测
    this.checkNullField(vos);

    // 检查客户是否分属于集团或者当前应收组织
    this.checkCustDistribute(vos);
  }

  private void checkCustDistribute(SquareWasVO[] vos) {
    Map<String, String> map = new HashMap<String, String>();
    for (SquareWasVO svo : vos) {
      for (SquareWasBVO bvo : svo.getChildrenVO()) {
        map.put(bvo.getCinvoicecustid(), bvo.getCarorgid());
      }
    }
    new CustDistributeCheck().check(map);
  }

  private void checkNullField(SquareWasVO[] vos) {
    String errorMsg = null;
    for (SquareWasVO svo : vos) {
      for (SquareWasBVO bvo : svo.getChildrenVO()) {
        // 结算财务组织
        if (PubAppTool.isNull(bvo.getPk_org())
            || PubAppTool.isNull(svo.getParentVO().getPk_org())) {
          errorMsg =
              nc.vo.ml.NCLangRes4VoTransl.getNCLangRes().getStrByID(
                  "4006010_0", "04006010-0014")/*@res "生成待结算单的结算财务组织为空！"*/;
          break;
        }
        // 应收组织
        if (PubAppTool.isNull(bvo.getCarorgid())) {
          errorMsg =
              nc.vo.ml.NCLangRes4VoTransl.getNCLangRes().getStrByID(
                  "4006010_0", "04006010-0015")/*@res "生成待结算单的应收组织为空！"*/;
          break;
        }
      }
    }
    if (errorMsg != null) {
      ExceptionUtils.wrappBusinessException(errorMsg);
    }
  }

}
