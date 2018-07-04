package nc.bs.so.m4331.quality.rule.update;

import nc.impl.pubapp.pattern.rule.IRule;
import nc.vo.pub.lang.UFDouble;
import nc.vo.pubapp.pattern.exception.ExceptionUtils;
import nc.vo.so.m4331.entity.DeliveryCheckVO;

/**
 * @description
 * 销售发货单质检信息修改保存前校验能否删除
 * @scene
 * 销售发货单质检信息修改保存前
 * @param
 * 无
 */
public class CheckUpdateNullRule implements IRule<DeliveryCheckVO> {

  @Override
  public void process(DeliveryCheckVO[] vos) {
    for (DeliveryCheckVO vo : vos) {
      UFDouble nqtorignetprice = vo.getNqtorignetprice();
      if (null == nqtorignetprice
          || nqtorignetprice.compareTo(UFDouble.ZERO_DBL) == 0) {
        ExceptionUtils.wrappBusinessException(nc.vo.ml.NCLangRes4VoTransl.getNCLangRes().getStrByID("4006002_0","04006002-0058")/*@res "无税净价不能为空或者为0。"*/);
      }
      UFDouble taxnetprice = vo.getNqtorigtaxnetprc();
      if (null == taxnetprice || taxnetprice.compareTo(UFDouble.ZERO_DBL) == 0) {
        ExceptionUtils.wrappBusinessException(nc.vo.ml.NCLangRes4VoTransl.getNCLangRes().getStrByID("4006002_0","04006002-0059")/*@res "含税净价不能为空或者为0。"*/);
      }
    }
  }
}