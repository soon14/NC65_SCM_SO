package nc.impl.so.m30.revise.action.rule;

import java.util.ArrayList;
import java.util.List;

import nc.impl.pubapp.pattern.rule.IRule;
import nc.vo.ml.NCLangRes4VoTransl;
import nc.vo.pub.VOStatus;
import nc.vo.pub.lang.UFDouble;
import nc.vo.pubapp.pattern.exception.ExceptionUtils;
import nc.vo.pubapp.pattern.pub.MathTool;
import nc.vo.so.m30.entity.SaleOrderBVO;
import nc.vo.so.m30.entity.SaleOrderVO;
import nc.vo.so.pub.SOConstant;
import nc.vo.so.pub.util.SOSysParaInitUtil;

/**
 * @description
 * 销售订单修订保存前校验费用冲抵金额
 * @scene
 * 销售订单修订保存前
 * @param
 * 无
 */
public class OffsetCheckRule implements IRule<SaleOrderVO> {

  @Override
  public void process(SaleOrderVO[] saleordervos) {

    for (SaleOrderVO bill : saleordervos) {
      this.checkOffSetRate(bill);
    }
  }

  private void checkOffSetRate(SaleOrderVO bill) {
    // 主组织
    String pk_org = bill.getParentVO().getPk_org();
    UFDouble so15 = SOSysParaInitUtil.getSO15(pk_org);
    if (MathTool.isZero(so15)) {
      return;
    }
    List<String> listerrrow = new ArrayList<String>();
    SaleOrderBVO[] bodys = bill.getChildrenVO();
    for (SaleOrderBVO bvo : bodys) {
      if (VOStatus.DELETED == bvo.getStatus()
          || VOStatus.UNCHANGED == bvo.getStatus()) {
        continue;
      }
      UFDouble submny = bvo.getNorigsubmny();
      if (MathTool.isZero(submny)) {
        continue;
      }
      UFDouble bfsubmny = MathTool.add(submny, bvo.getNorigtaxmny());

      UFDouble cansubmny = bfsubmny.multiply(so15.div(SOConstant.ONEHUNDRED));
      cansubmny = cansubmny.setScale(submny.getPower(), UFDouble.ROUND_HALF_UP);
      if (MathTool.compareTo(cansubmny, submny) < 0) {
        listerrrow.add(bvo.getCrowno());
      }
    }
    if (listerrrow.size() > 0) {
      StringBuilder strrow = new StringBuilder();
      for (String row : listerrrow) {
        strrow.append("[" + row + "],");
      }
      strrow.deleteCharAt(strrow.length() - 1);
      String errorstr = NCLangRes4VoTransl.getNCLangRes().getStrByID("4006011_0",
          "04006011-0447", null, new String[] {
            strrow.toString()
          });/* 下列行[{0}]价税合计修订值过小，不能保证冲抵前金额*冲抵比率大于累计冲抵金额。 */
      ExceptionUtils.wrappBusinessException(errorstr);
    }
  }
}
