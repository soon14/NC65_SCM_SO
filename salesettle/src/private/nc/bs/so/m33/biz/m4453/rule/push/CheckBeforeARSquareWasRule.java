package nc.bs.so.m33.biz.m4453.rule.push;

import java.util.Map;

import nc.bs.so.m33.pub.QuerySaleOrderEndInfoBP;
import nc.impl.pubapp.pattern.rule.IRule;
import nc.vo.pub.lang.UFBoolean;
import nc.vo.pubapp.pattern.exception.ExceptionUtils;
import nc.vo.so.m33.m4453.entity.SquareWasBVO;
import nc.vo.so.m33.m4453.entity.SquareWasVO;
import nc.vo.so.pub.util.AggVOUtil;
import nc.vo.so.pub.util.SOVOChecker;

/**
 * @description
 * 途损单应收结算时判断是否源头订单行应收结算关闭
 * @scene
 * 途损单应收结算前
 * @param
 * 无
 * @since 6.0
 * @version 2011-9-21 上午09:56:43
 * @author zhangcheng
 */
public class CheckBeforeARSquareWasRule implements IRule<SquareWasVO> {

  @Override
  public void process(SquareWasVO[] vos) {
    // 上游销售订单已经结算关闭，则不允许途损单审批
    String[] ordbids =
        AggVOUtil.getDistinctItemFieldArray(vos, SquareWasBVO.CFIRSTBID,
            String.class);

    // 途损单源头非销售订单
    if (SOVOChecker.isEmpty(ordbids)) {
      return;
    }

    Map<String, UFBoolean[]> map =
        new QuerySaleOrderEndInfoBP().querySaleOrderEndInfo(ordbids);

    if (SOVOChecker.isEmpty(map)) {
      return;
    }

    for (SquareWasVO wasvo : vos) {
      for (SquareWasBVO bvo : wasvo.getChildrenVO()) {
        String ordbid = bvo.getCfirstbid();
        UFBoolean[] flag = map.get(ordbid);
        // 赠品不传应收，不应该判断
        if (UFBoolean.FALSE.equals(bvo.getBlargessflag())
            && flag[0].booleanValue()) {
          ExceptionUtils
              .wrappBusinessException(nc.vo.ml.NCLangRes4VoTransl
                  .getNCLangRes().getStrByID("4006010_0", "04006010-0145")/* @res
                                                                           * "上游销售订单已经结算关闭，则不允许途损单审批!" */);

        }
      }
    }
  }

}
