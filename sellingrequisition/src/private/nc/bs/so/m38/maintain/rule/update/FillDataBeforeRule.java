package nc.bs.so.m38.maintain.rule.update;

import nc.bs.framework.common.InvocationInfoProxy;
import nc.bs.scmpub.app.flow.billcode.BillCodeInfoBuilder;
import nc.impl.pubapp.bill.billcode.BillCodeInfo;
import nc.impl.pubapp.bill.billcode.BillCodeUtils;
import nc.impl.pubapp.pattern.rule.ICompareRule;
import nc.vo.pub.lang.UFDate;
import nc.vo.pub.lang.UFDateTime;
import nc.vo.pubapp.util.VORowNoUtils;
import nc.vo.scmpub.res.billtype.SOBillType;
import nc.vo.so.m38.entity.PreOrderBVO;
import nc.vo.so.m38.entity.PreOrderHVO;
import nc.vo.so.m38.entity.PreOrderVO;

/**
 * @description
 * 预定单修改保存前填充数据
 * @scene
 * 销售预订单修改保存前
 * @param
 * 无
 * @author 刘志伟
 * @time 2010-01-25
 */
public class FillDataBeforeRule implements ICompareRule<PreOrderVO> {

  @Override
  public void process(PreOrderVO[] vos, PreOrderVO[] originVOs) {
    for (PreOrderVO vo : vos) {
      this.setHeadDefault(vo);
      this.setBodyDefault(vo);
    }
    this.setBillCode(vos, originVOs);
  }

  /**
   * 设置单据号
   */
  private void setBillCode(PreOrderVO[] vos, PreOrderVO[] originVOs) {
    BillCodeInfo info =
        BillCodeInfoBuilder.buildBillCodeInfo(SOBillType.PreOrder.getCode(), PreOrderHVO.VBILLCODE,
            PreOrderHVO.PK_GROUP, PreOrderHVO.PK_ORG, PreOrderHVO.VTRANTYPECODE);
    BillCodeUtils util = new BillCodeUtils(info);
    util.upadteBillCode(vos, originVOs);
  }

  private void setBodyDefault(PreOrderVO vo) {
    // 设置表体冗余销售组织、单据日期
    PreOrderHVO headvo = vo.getParentVO();
    String orgid = headvo.getPk_org();
    UFDate billdate = headvo.getDbilldate();
    PreOrderBVO[] bodyvos = vo.getChildrenVO();
    for (PreOrderBVO bodyvo : bodyvos) {
      bodyvo.setPk_org(orgid);
      bodyvo.setDbilldate(billdate);
    }

    // 为行号为空的行补充行号。
    VORowNoUtils.setVOsRowNoByRule(bodyvos, PreOrderBVO.CROWNO);
  }

  private void setHeadDefault(PreOrderVO vo) {
    PreOrderHVO head = vo.getParentVO();

    InvocationInfoProxy proxy = InvocationInfoProxy.getInstance();
    String userId = proxy.getUserId();
    UFDateTime dateTime = new UFDateTime(System.currentTimeMillis());

    // 修改人
    if (!userId.equals(head.getModifier())) {
      head.setModifier(userId);
    }
    // 修改时间
    if (!dateTime.equals(head.getModifiedtime())) {
      head.setModifiedtime(dateTime);
    }

  }

}
