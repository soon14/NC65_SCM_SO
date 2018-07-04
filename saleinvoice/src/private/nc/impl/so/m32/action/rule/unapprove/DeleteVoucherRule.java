package nc.impl.so.m32.action.rule.unapprove;

import java.util.ArrayList;
import java.util.List;

import nc.impl.pubapp.env.BSContext;
import nc.impl.pubapp.pattern.rule.IRule;
import nc.itf.scmpub.reference.fip.FipMessageService;
import nc.itf.scmpub.reference.uap.group.SysInitGroupQuery;
import nc.itf.scmpub.reference.uap.pf.PfServiceScmUtil;
import nc.vo.fip.service.FipMessageVO;
import nc.vo.fip.service.FipRelationInfoVO;
import nc.vo.pub.pfflow04.MessagedriveVO;
import nc.vo.pubapp.pattern.log.TimeLog;
import nc.vo.pubapp.res.NCModule;
import nc.vo.scmpub.res.billaction.SOBillAction;
import nc.vo.scmpub.res.billtype.SOBillType;
import nc.vo.so.m32.entity.SaleInvoiceHVO;
import nc.vo.so.m32.entity.SaleInvoiceVO;

/**
 * @description
 * 销售发票弃审后删除销售发票的会计凭证
 * @scene
 * 销售发票弃审后
 * @param
 * 无
 * @since 6.0
 * @version 2011-3-29 上午08:55:40
 * @author 么贵敬
 */
public class DeleteVoucherRule implements IRule<SaleInvoiceVO> {

  @Override
  public void process(SaleInvoiceVO[] vos) {

    // 如果没起会计平台
    if (!SysInitGroupQuery.isFIPEnabled()) {
      return;
    }
    TimeLog.logStart();
    List<FipMessageVO> msgvos = this.createBillsForFIP(vos);
    TimeLog.info("创建生成实时凭证的单据VO"); /*-=notranslate=-*/

    TimeLog.logStart();
    this.sendMessage(msgvos);
    TimeLog.info("向会计平台发送消息"); /*-=notranslate=-*/
  }

  private List<FipMessageVO> createBillsForFIP(SaleInvoiceVO[] bills) {
    List<FipMessageVO> msgvos = new ArrayList<FipMessageVO>();
    for (SaleInvoiceVO bill : bills) {
      MessagedriveVO[] md4Cvos =
          PfServiceScmUtil.queryAllMsgdrvVOs(bill.getParentVO()
              .getVtrantypecode(), bill.getParentVO().getCbiztypeid(),
              SOBillAction.SaleInvoiceApprove.getCode());
      if (md4Cvos.length == 0) {
        md4Cvos =
            PfServiceScmUtil.queryAllMsgdrvVOs(SOBillType.Invoice.getCode(),
                bill.getParentVO().getCbiztypeid(),
                SOBillAction.SaleInvoiceApprove.getCode());
      }
      for (MessagedriveVO vo : md4Cvos) {
        if (SOBillAction.SaleInvoiceCreateVoucher.getCode().equals(
            vo.getActiontype())) {
          SaleInvoiceHVO hvo = bill.getParentVO();
          // SaleInvoiceBVO[] bvos = bill.getChildrenVO();
          // for (SaleInvoiceBVO bvo : bvos) {
          FipMessageVO msgvo = new FipMessageVO();
          msgvo.setBillVO(bill);
          msgvo.setMessagetype(FipMessageVO.MESSAGETYPE_DEL);
          FipRelationInfoVO infovo = new FipRelationInfoVO();
          infovo.setPk_billtype(hvo.getVtrantypecode());
          infovo.setBusidate(BSContext.getInstance().getDate());
          infovo.setPk_operator(BSContext.getInstance().getUserID());
          infovo.setPk_group(BSContext.getInstance().getGroupID());
          infovo.setPk_org(hvo.getPk_org());
          infovo.setPk_system(NCModule.SO.getName().toUpperCase());
          infovo.setRelationID(hvo.getCsaleinvoiceid());
          // 业务流程
          infovo.setDefdoc1(hvo.getCbiztypeid());
          // 成本域
          // infovo.setDefdoc2(hvo.getPk_org());
          // 财务核算账簿
          // infovo.setDefdoc3(hvo.getPk_book());

          // 单据号
          infovo.setFreedef1(hvo.getVbillcode());
          // 金额
          infovo.setFreedef2(hvo.getNtotalorigmny().toString());
          msgvo.setMessageinfo(infovo);
          msgvo.setBillVO(bill);
          msgvos.add(msgvo);
          // }
        }
      }
    }
    return msgvos;
  }

  /**
   * 向会计平台发送消息
   * 
   * @param billsmap
   */
  private void sendMessage(List<FipMessageVO> msgvos) {
    FipMessageVO[] msgvo = msgvos.toArray(new FipMessageVO[0]);
    FipMessageService.sendMessages(msgvo);
  }

}
