package nc.bs.so.pub.rule;

import nc.bs.framework.common.NCLocator;
import nc.bs.ml.NCLangResOnserver;
import nc.impl.pubapp.pattern.rule.IRule;
import nc.itf.uap.pf.IPFWorkflowQry;
import nc.vo.pub.BusinessException;
import nc.vo.pubapp.AppContext;
import nc.vo.pubapp.pattern.exception.ExceptionUtils;
import nc.vo.pubapp.pattern.model.entity.bill.AbstractBill;
import nc.vo.so.pub.SOItemKey;
import nc.vo.so.pub.enumeration.BillStatus;

/**
 * @description
 *              销售订单、发票审批中支持修改，校验当前操作人是否是审批人
 * @scene
 *        销售订单、发票审批中支持修改 修改前规则检查当前操作人是否是审批人，只有审批人才可以修改
 * @param 无
 * 
 * @since 6.3
 * @version 2015-1-19 下午3:12:18
 * @author wangshu6
 */
public class CheckApproverRule implements IRule {

  @Override
  public void process(Object[] vos) {
    for (Object vo : vos) {
      Integer status =
          (Integer) ((AbstractBill) vo).getParentVO().getAttributeValue(
              SOItemKey.FSTATUSFLAG);
      // 只有当审批中才进行 当前操作人是否是审批人的校验
      if (BillStatus.AUDITING.getIntegerValue().equals(status)) {
        boolean isApprover = false;
        try {
          
          IPFWorkflowQry queryService = NCLocator.getInstance().lookup(IPFWorkflowQry.class);
          // 获取单据ID
          String billID = ((AbstractBill) vo).getPrimaryKey();
          // 获取交易类型编码
          String vtrantypecode =
              (String) ((AbstractBill) vo).getParentVO().getAttributeValue(
                  SOItemKey.VTRANTYPECODE);
          // 获取当前登录人
          String user = AppContext.getInstance().getPkUser();
          // 查询操作人是否是审批人
          isApprover = queryService.isCheckman(billID, vtrantypecode, user);
        }
        catch (BusinessException e) {
          ExceptionUtils.wrappException(e);
        }
        if (!isApprover) {
          ExceptionUtils.wrappBusinessException(NCLangResOnserver.getInstance().getStrByID("4006004_0", "04006004-0238")/*当前操作人不是审批人，审批中单据禁止修改！*/);
        }
      }
    }
  }

}
