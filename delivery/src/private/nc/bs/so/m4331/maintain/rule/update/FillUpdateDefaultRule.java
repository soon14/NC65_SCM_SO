package nc.bs.so.m4331.maintain.rule.update;

import nc.bs.scmpub.app.flow.billcode.BillCodeInfoBuilder;
import nc.impl.pubapp.bill.billcode.BillCodeInfo;
import nc.impl.pubapp.bill.billcode.BillCodeUtils;
import nc.impl.pubapp.pattern.rule.ICompareRule;
import nc.vo.pub.VOStatus;
import nc.vo.pub.lang.UFDate;
import nc.vo.pub.lang.UFDouble;
import nc.vo.scmpub.res.billtype.SOBillType;
import nc.vo.so.m4331.entity.DeliveryBVO;
import nc.vo.so.m4331.entity.DeliveryHVO;
import nc.vo.so.m4331.entity.DeliveryVO;
import nc.vo.so.pub.enumeration.BillStatus;
import nc.vo.trade.checkrule.VOChecker;

/**
 * @description
 * 销售发货单修改保存前填充默认值
 * @scene
 * 销售发货单修改保存前
 * @param
 * 无
 */
public class FillUpdateDefaultRule implements ICompareRule<DeliveryVO> {

  @Override
  public void process(DeliveryVO[] vos, DeliveryVO[] originVOs) {

    for (DeliveryVO deliveryVO : vos) {
      // 填充修改保存时默认值
      this.setHeadDefault(deliveryVO);
      this.setBodyDefault(deliveryVO);
    }
    // 填充单据号
    this.setBillCode(vos, originVOs);

  }

  private void setBillCode(DeliveryVO[] vos, DeliveryVO[] originVOs) {
    BillCodeInfo info =
        BillCodeInfoBuilder.buildBillCodeInfo(SOBillType.Delivery.getCode(), DeliveryHVO.VBILLCODE,
            DeliveryHVO.PK_GROUP, DeliveryHVO.PK_ORG, DeliveryHVO.VTRANTYPECODE);
    BillCodeUtils util = new BillCodeUtils(info);

    util.upadteBillCode(vos, originVOs);

  }

  /**
   * 方法功能描述：修改保存前表体默认值填充。
   * 
   * @param deliveryVO
   * @author 祝会征
   * @time 2010-1-21 下午06:59:37
   */
  private void setBodyDefault(DeliveryVO deliveryVO) {
    if (VOChecker.isEmpty(deliveryVO.getChildrenVO())) {
      return;
    }

    // 使用表头组织、单据日期填充表体
    String org = deliveryVO.getParentVO().getPk_org();
    String pk_group = deliveryVO.getParentVO().getPk_group();
    UFDate billdate = deliveryVO.getParentVO().getDbilldate();
    deliveryVO.getParentVO().setApprover(null);
    deliveryVO.getParentVO().setTaudittime(null);
    deliveryVO.getParentVO().setFstatusflag(BillStatus.FREE.getIntegerValue());
    for (DeliveryBVO bvo : deliveryVO.getChildrenVO()) {
      if (bvo.getStatus() == VOStatus.NEW) {
        bvo.setPk_org(org);
        bvo.setDbilldate(billdate);
        bvo.setPk_group(pk_group);
      }
    }
  }

  private void setHeadDefault(DeliveryVO deliveryVO) {
    UFDouble totalnum = new UFDouble(0.0);
    UFDouble totalweight = new UFDouble(0.0);
    UFDouble totalvolume = new UFDouble(0.0);
    UFDouble totalpiece = new UFDouble(0.0);
    DeliveryBVO[] bvo = deliveryVO.getChildrenVO();
    for (DeliveryBVO vo : bvo) {
      if(vo.getStatus()==VOStatus.DELETED){
        continue;
      }
      if (vo.getNastnum() != null) {
        totalnum = totalnum.add(vo.getNastnum());
      }
      if (vo.getNweight() != null) {
        totalweight = totalweight.add(vo.getNweight());
      }
      if (vo.getNvolume() != null) {
        totalvolume = totalvolume.add(vo.getNvolume());
      }
      if (vo.getNpiece() != null) {
        totalpiece = totalpiece.add(vo.getNpiece());
      }
    }
    deliveryVO.getParentVO().setNtotalastnum(totalnum);
    deliveryVO.getParentVO().setNtotalpiece(totalpiece);
    deliveryVO.getParentVO().setNtotalvolume(totalvolume);
    deliveryVO.getParentVO().setNtotalweight(totalweight);
  }
}
