package nc.bs.so.m30.rule.credit;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import nc.bs.framework.common.NCLocator;
import nc.impl.pubapp.pattern.rule.IRule;
import nc.itf.scmpub.reference.uap.group.SysInitGroupQuery;
import nc.pubitf.credit.engrossmaintain.m30.IEngrossCreditManagerForM30;
import nc.vo.credit.engrossmaintain.pub.action.M30EngrossAction;
import nc.vo.credit.engrossmaintain.pub.para.M30CreditPara;
import nc.vo.pub.BusinessException;
import nc.vo.pubapp.pattern.exception.ExceptionUtils;
import nc.vo.scmpub.res.billtype.SOBillType;
import nc.vo.so.m30.entity.SaleOrderBVO;
import nc.vo.so.m30.entity.SaleOrderVO;

/**
 * @description
 * 信用占用提供给销售订单的整单动作前的信用检查
 * @scene
 * 销售订单审批、取消审批、新增、修改、删除保存前
 * @param 
 * billaction 销售管理动作Action
 *
 */
public class RenovateARByHidsBeginRule implements IRule<SaleOrderVO> {

  private M30EngrossAction billaction;

  public RenovateARByHidsBeginRule(M30EngrossAction billaction) {
    this.billaction = billaction;
  }

  @Override
  public void process(SaleOrderVO[] vos) {
    // 如果信用模块未启用，不处理
    if (!SysInitGroupQuery.isCREDITEnabled()) {
      return;
    }
    List<String> alhids = new ArrayList<String>();
    Set<String> hsSettleOrgs = new HashSet<String>();
    for (SaleOrderVO vo : vos) {
      alhids.add(vo.getParentVO().getCsaleorderid());
      for (SaleOrderBVO bvo : vo.getChildrenVO()) {
        hsSettleOrgs.add(bvo.getCsettleorgid());
      }
    }
    String[] headIDs = alhids.toArray(new String[0]);
    String[] settleOrgs = hsSettleOrgs.toArray(new String[0]);

    M30CreditPara para = new M30CreditPara();
    para.setHeadIDs(headIDs);
    para.setBilltype(SOBillType.Order.getCode());
    para.setBillaction(this.billaction);
    para.setPk_org(settleOrgs);

    IEngrossCreditManagerForM30 mange =
        NCLocator.getInstance().lookup(IEngrossCreditManagerForM30.class);
    try {
      mange.renovateARByHidsBegin(para);
    }
    catch (BusinessException e) {

      ExceptionUtils.wrappException(e);
    }
  }
}
