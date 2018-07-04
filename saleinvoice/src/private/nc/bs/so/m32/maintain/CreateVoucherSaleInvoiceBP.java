package nc.bs.so.m32.maintain;

import java.util.ArrayList;
import java.util.List;

import nc.vo.fip.service.FipMessageVO;
import nc.vo.fip.service.FipRelationInfoVO;
import nc.vo.pub.BusinessException;
import nc.vo.pubapp.pattern.exception.ExceptionUtils;
import nc.vo.pubapp.pattern.log.TimeLog;
import nc.vo.pubapp.res.NCModule;
import nc.vo.so.m32.entity.SaleInvoiceHVO;
import nc.vo.so.m32.entity.SaleInvoiceVO;

import nc.itf.scmpub.reference.fip.FipMessageService;
import nc.itf.scmpub.reference.uap.group.SysInitGroupQuery;

import nc.impl.pubapp.env.BSContext;

/**
 * 生成凭证动作
 * 
 * @since 6.0
 * @version 2011-3-28 上午11:53:20
 * @author 么贵敬
 */
public class CreateVoucherSaleInvoiceBP {

  /**
   * 创建凭证
   * 
   * @param bills 销售发票vo
   */
  public void createVoucher(SaleInvoiceVO[] bills) {
    // 如果没起会计平台
    if (!SysInitGroupQuery.isFIPEnabled()) {
      return;
    }
    // TODO 统一规范后再调整
    TimeLog.logStart();
    List<FipMessageVO> msgvos = this.createBillsForFIP(bills);
    TimeLog.info("创建生成实时凭证的单据VO"); /*-=notranslate=-*/

    TimeLog.logStart();
    try {
      this.sendMessage(msgvos);
    }
    catch (BusinessException e) {
      ExceptionUtils.wrappException(e);
    }
    TimeLog.info("向会计平台发送消息"); /*-=notranslate=-*/
  }

  private List<FipMessageVO> createBillsForFIP(SaleInvoiceVO[] bills) {
    List<FipMessageVO> msgvos = new ArrayList<FipMessageVO>();
    for (SaleInvoiceVO bill : bills) {
      SaleInvoiceHVO hvo = bill.getParentVO();
      // SaleInvoiceBVO[] bvos = bill.getChildrenVO();
      // for (SaleInvoiceBVO bvo : bvos) {
      FipMessageVO msgvo = new FipMessageVO();
      msgvo.setBillVO(bill);
      msgvo.setMessagetype(FipMessageVO.MESSAGETYPE_ADD);
      FipRelationInfoVO infovo = new FipRelationInfoVO();
      infovo.setPk_billtype(hvo.getVtrantypecode());
      infovo.setBusidate(BSContext.getInstance().getDate());
      infovo.setPk_operator(BSContext.getInstance().getUserID());
      infovo.setPk_group(BSContext.getInstance().getGroupID());
      infovo.setPk_org(hvo.getPk_org());
      infovo.setPk_system(NCModule.SO.getName().toUpperCase());
      infovo.setRelationID(hvo.getCsaleinvoiceid());
      // 交易类型
      infovo.setDefdoc1(hvo.getCtrantypeid());
      // 成本域
      // infovo.setDefdoc2(hvo.getPk_org());
      // 财务核算账簿
      // infovo.setDefdoc3(hvo.getPk_book());

      // 单据号
      infovo.setFreedef1(hvo.getVbillcode());
      // 备注
      // infovo.setFreedef2(hvo.getNtotalorigmny().toString());
      // 金额
      infovo.setFreedef3(hvo.getNtotalorigmny().toString());

      msgvo.setMessageinfo(infovo);
      msgvo.setBillVO(bill);
      msgvos.add(msgvo);
      // }
    }
    return msgvos;
  }

  /**
   * 向会计平台发送消息
   * 
   * @param billsmap
   * @throws BusinessException
   */
  private void sendMessage(List<FipMessageVO> msgvos) throws BusinessException {
    FipMessageVO[] msgvo = msgvos.toArray(new FipMessageVO[0]);
    try {
      FipMessageService.sendMessages(msgvo);
    }
    catch (Exception ex) {
      ExceptionUtils.marsh(ex);
    }

  }
}
