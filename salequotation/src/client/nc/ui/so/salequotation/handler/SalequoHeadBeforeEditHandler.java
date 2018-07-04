package nc.ui.so.salequotation.handler;

import nc.ui.pub.beans.UIRefPane;
import nc.ui.pub.bill.BillItem;
import nc.ui.pubapp.uif2app.event.IAppEventHandler;
import nc.ui.pubapp.uif2app.event.card.CardHeadTailBeforeEditEvent;
import nc.ui.scmpub.ref.FilterCustomerRefUtils;
import nc.ui.scmpub.ref.FilterDeptRefUtils;
import nc.ui.scmpub.ref.FilterPsndocRefUtils;
import nc.ui.scmpub.ref.FilterTransTypeRefUtils;
import nc.vo.price.pub.context.PriceContext;
import nc.vo.pub.lang.UFBoolean;
import nc.vo.pubapp.pattern.exception.ExceptionUtils;
import nc.vo.pubapp.pattern.pub.PubAppTool;
import nc.vo.so.salequotation.entity.SalequotationHVO;

public class SalequoHeadBeforeEditHandler implements
    IAppEventHandler<CardHeadTailBeforeEditEvent> {

  @Override
  public void handleAppEvent(CardHeadTailBeforeEditEvent e) {
    boolean retFlag = true;
    // 报价单类型
    if (SalequotationHVO.CTRANTYPEID.equals(e.getKey())) {
      this.filterTranType(e);
      retFlag = true;
    }
    if (SalequotationHVO.PK_DEPT_V.equals(e.getKey())) {
      this.filterDept(e);
      retFlag = true;
    }
    if (SalequotationHVO.CEMPLOYEEID.equals(e.getKey())) {
      this.filterEmployee(e);
      retFlag = true;
    }
    if(SalequotationHVO.PK_CUSTOMER.equals(e.getKey())){
      custEdit(e);
    }
    e.setReturnValue(Boolean.valueOf(retFlag));
  }
  
  private void custEdit(CardHeadTailBeforeEditEvent e) {
    // 订单客户
    BillItem customeritem =
        e.getBillCardPanel().getHeadTailItem(SalequotationHVO.PK_CUSTOMER);
    FilterCustomerRefUtils filterutils =
        new FilterCustomerRefUtils(customeritem);
    filterutils.filterRefByFrozenFlag(UFBoolean.FALSE);

  }

  private void filterDept(CardHeadTailBeforeEditEvent e) {
    String pk_org = e.getContext().getPk_org();
    if (PubAppTool.isNull(pk_org)) {
      e.setReturnValue(Boolean.FALSE);
      String message = nc.vo.ml.NCLangRes4VoTransl.getNCLangRes().getStrByID("4006009_0","04006009-0016")/*@res "请先录入销售组织"*/;
      e.getBillCardPanel().transferFocus();
      ExceptionUtils.wrappBusinessException(message);
    }
    BillItem item =
        e.getBillCardPanel().getHeadItem(SalequotationHVO.PK_DEPT_V);
    FilterDeptRefUtils filter = FilterDeptRefUtils
        .createFilterDeptRefUtilsOfSO((UIRefPane) item.getComponent());
        filter.filterItemRefByOrg(pk_org);
  }

  private void filterEmployee(CardHeadTailBeforeEditEvent e) {
    String pk_org = e.getContext().getPk_org();
    if (PubAppTool.isNull(pk_org)) {
      e.setReturnValue(Boolean.FALSE);
      String message = nc.vo.ml.NCLangRes4VoTransl.getNCLangRes().getStrByID("4006009_0","04006009-0016")/*@res "请先录入销售组织"*/;
      e.getBillCardPanel().transferFocus();
      ExceptionUtils.wrappBusinessException(message);
    }

    BillItem item =
        e.getBillCardPanel().getHeadItem(SalequotationHVO.CEMPLOYEEID);
    FilterPsndocRefUtils filter = FilterPsndocRefUtils
        .createFilterPsndocRefUtilsOfSO((UIRefPane) item.getComponent());
        filter.filterItemRefByOrg(pk_org);
        
    // 光标自动定位到部门
    String pk_dept =
        (String) e.getBillCardPanel().getHeadItem(SalequotationHVO.PK_DEPT)
            .getValueObject();
    filter.fixFocusByPK(pk_dept);
  }

  private void filterTranType(CardHeadTailBeforeEditEvent e) {
    BillItem trantypeItem = e.getBillCardPanel().getHeadItem(e.getKey());
    FilterTransTypeRefUtils transTypeFilter =
        new FilterTransTypeRefUtils(trantypeItem, e.getContext().getPk_org());
    transTypeFilter.filterTranType(new String[] {
      PriceContext.SALEQUOBILLTYPE
    });
  }
}