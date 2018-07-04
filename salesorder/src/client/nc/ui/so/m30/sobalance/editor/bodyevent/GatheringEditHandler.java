package nc.ui.so.m30.sobalance.editor.bodyevent;

import nc.bs.framework.common.NCLocator;
import nc.pubitf.arap.gathering.IArapGatheringBillPubQueryService;
import nc.ui.bd.ref.AbstractRefModel;
import nc.ui.pub.beans.UIRefPane;
import nc.ui.pub.bill.BillCardPanel;
import nc.ui.pub.bill.BillItem;
import nc.ui.pubapp.uif2app.event.card.CardBodyAfterEditEvent;
import nc.ui.pubapp.uif2app.event.card.CardBodyBeforeEditEvent;
import nc.ui.so.m30.sobalance.ref.SOArapGathingBillRefModel;
import nc.ui.so.pub.keyvalue.CardKeyValue;
import nc.vo.arap.gathering.GatheringBillItemVO;
import nc.vo.pub.BusinessException;
import nc.vo.pub.lang.UFDouble;
import nc.vo.pubapp.pattern.exception.ExceptionUtils;
import nc.vo.pubapp.pattern.pub.MathTool;
import nc.vo.so.m30.sobalance.entity.SoBalanceBVO;
import nc.vo.so.m30.sobalance.entity.SoBalanceHVO;
import nc.vo.so.m30.sobalance.entity.SoBalanceVO;
import nc.vo.so.m30.sobalance.enumeration.SoBalanceType;
import nc.vo.so.m30.sobalance.util.GatherbillUtil;
import nc.vo.so.pub.keyvalue.IKeyValue;
import nc.vo.trade.checkrule.VOChecker;

public class GatheringEditHandler {

  public void beforeEdit(CardBodyBeforeEditEvent e) {
    int row = e.getRow();
    BillCardPanel cardPanel = e.getBillCardPanel();
    IKeyValue keyValue = new CardKeyValue(cardPanel);

    // 只有订单核销行或者表头金额合计不等于0可以编辑
    Integer fibaltype =
        keyValue.getBodyIntegerValue(row, SoBalanceBVO.FIBALTYPE);

    UFDouble totaltaxmny =
        keyValue.getHeadUFDoubleValue(SoBalanceHVO.NTOTALORIGTAXMNY);

    if (SoBalanceType.SOBALANCE_FINBAL.equalsValue(fibaltype)
        || MathTool.isZero(totaltaxmny)) {
      e.setReturnValue(Boolean.FALSE);
      return;
    }
    /* // 只有新增的行可以修改收款单行
     String csobalancebid =
         keyValue.getBodyStringValue(row, SoBalanceBVO.CSOBALANCEBID);
     if (!VOChecker.isEmpty(csobalancebid)) {
       e.setReturnValue(Boolean.FALSE);
       return;
     }*/
    // 收款单参照
    BillItem paybillrowItem = cardPanel.getBodyItem(SoBalanceBVO.VARBILLCODE);
    UIRefPane paybillrowItemRef = (UIRefPane) paybillrowItem.getComponent();
    AbstractRefModel refmodel = paybillrowItemRef.getRefModel();
    if (refmodel == null) {
      SOArapGathingBillRefModel newRefmodel =
          new SOArapGathingBillRefModel(keyValue);
      newRefmodel.setWherePart(newRefmodel.getWherePart());
      paybillrowItemRef.setRefModel(newRefmodel);
    }
  }

  public void afterEdit(CardBodyAfterEditEvent e) {
    int row = e.getRow();
    BillCardPanel cardPanel = e.getBillCardPanel();
    IKeyValue keyValue = new CardKeyValue(cardPanel);

    String csobalanceid =
        keyValue.getHeadStringValue(SoBalanceHVO.CSOBALANCEID);
    BillItem paybillrowItem = cardPanel.getBodyItem(SoBalanceBVO.VARBILLCODE);
    UIRefPane paybillrowItemRef = (UIRefPane) paybillrowItem.getComponent();
    String paybillrowid = paybillrowItemRef.getRefPK();
    // 为什么要清空？我不理解
    this.clearRow(keyValue, row);
    IArapGatheringBillPubQueryService arapservice =
        NCLocator.getInstance().lookup(IArapGatheringBillPubQueryService.class);
    if (VOChecker.isEmpty(paybillrowid)) {
      return;
    }
    try {
      GatheringBillItemVO[] gatheringbillitemvos =
          arapservice.queryGatheringBillItem(new String[] {
            paybillrowid
          });
      if (gatheringbillitemvos != null && gatheringbillitemvos.length > 0) {
        GatheringBillItemVO gatheringbillitemvo = gatheringbillitemvos[0];
        SoBalanceVO sobalancevo =
            (SoBalanceVO) cardPanel.getBillValueVO(SoBalanceVO.class.getName(),
                SoBalanceHVO.class.getName(), SoBalanceBVO.class.getName());
        SoBalanceBVO sobalancebodyvo =
            GatherbillUtil.getInstance()
                .createBalanceBVOByGatheringBillItemForManual(
                    gatheringbillitemvo, sobalancevo);
        if (sobalancebodyvo != null) {
          keyValue.setBodyValue(row, SoBalanceBVO.CPAYBILLID,
              sobalancebodyvo.getCpaybillid());
          keyValue.setBodyValue(row, SoBalanceBVO.CPAYBILLROWID, paybillrowid);
          keyValue.setBodyValue(row, SoBalanceBVO.CARORIGCURRENCYID,
              sobalancebodyvo.getCarorigcurrencyid());
          keyValue.setBodyValue(row, SoBalanceBVO.CPRODLINEID,
              sobalancebodyvo.getCprodlineid());
          keyValue.setBodyValue(row, SoBalanceBVO.CSOBALANCEID, csobalanceid);
          keyValue.setBodyValue(row, SoBalanceBVO.DARBALANCEDATE,
              sobalancebodyvo.getDarbalancedate());
          keyValue.setBodyValue(row, SoBalanceBVO.DARBILLDATE,
              sobalancebodyvo.getDarbilldate());
          keyValue.setBodyValue(row, SoBalanceBVO.FIBALTYPE,
              sobalancebodyvo.getFibaltype());
          keyValue.setBodyValue(row, SoBalanceBVO.NORIGTHISBALMNY,
              sobalancebodyvo.getNorigthisbalmny());
          keyValue.setBodyValue(row, SoBalanceBVO.VARBILLCODE,
              sobalancebodyvo.getVarbillcode());
          keyValue.setBodyValue(row, SoBalanceBVO.NORIGARMNY,
              sobalancebodyvo.getNorigarmny());
          keyValue.setBodyValue(row, SoBalanceBVO.BPRECEIVEFLAG,
              sobalancebodyvo.getBpreceiveflag());
        }
      }
      else {
        throw new BusinessException(nc.vo.ml.NCLangRes4VoTransl.getNCLangRes()
            .getStrByID("4006011_0", "04006011-0045")/*@res "查询不到收款单行记录。"*/);
      }
    }
    catch (BusinessException ex) {
      ExceptionUtils.wrappException(ex);
    }
  }

  private void clearRow(IKeyValue keyvalue, int row) {
    keyvalue.setBodyValue(row, SoBalanceBVO.CPAYBILLID, null);
    keyvalue.setBodyValue(row, SoBalanceBVO.CPAYBILLROWID, null);
    keyvalue.setBodyValue(row, SoBalanceBVO.CARORIGCURRENCYID, null);
    keyvalue.setBodyValue(row, SoBalanceBVO.CPRODLINEID, null);
    keyvalue.setBodyValue(row, SoBalanceBVO.DARBALANCEDATE, null);
    keyvalue.setBodyValue(row, SoBalanceBVO.DARBILLDATE, null);
    // keyvalue.setBodyValue(row, SoBalanceBVO.FIBALTYPE, null);
    keyvalue.setBodyValue(row, SoBalanceBVO.NORIGTHISBALMNY, null);
    keyvalue.setBodyValue(row, SoBalanceBVO.VARBILLCODE, null);
  }
}
