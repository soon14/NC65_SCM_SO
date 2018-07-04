package nc.ui.so.m38.arrange.action;

import java.awt.event.ActionEvent;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import nc.ui.pubapp.billref.push.BillContext;
import nc.ui.pubapp.billref.push.IBillPush;
import nc.ui.scmpub.action.SCMActionInitializer;
import nc.ui.so.m38.arrange.pub.M38ArrangeFindSalePrice;
import nc.ui.uif2.NCAction;
import nc.vo.pubapp.pattern.exception.ExceptionUtils;
import nc.vo.pubapp.pattern.pub.PubAppTool;
import nc.vo.scmpub.res.SCMActionCode;
import nc.vo.so.m30.entity.SaleOrderViewVO;

public class M38ArrangeAskqtAction extends NCAction implements IBillPush {

  /**
   *
   */
  private static final long serialVersionUID = 2949067070568269327L;

  private BillContext context;

  public M38ArrangeAskqtAction() {
    super();
    SCMActionInitializer.initializeAction(this, SCMActionCode.SO_ASKPRICE);
  }

  @Override
  public void doAction(ActionEvent e) throws Exception {

    SaleOrderViewVO[] selviews =
        (SaleOrderViewVO[]) this.getBillContext().getBillTabPanel()
            .getBodySelectVOs();
    String[] selids = this.getBillContext().getModel().getSelectedBillID();
    if (null == selviews || selviews.length == 0) {
      ExceptionUtils
          .wrappBusinessException(nc.vo.ml.NCLangRes4VoTransl.getNCLangRes()
              .getStrByID("4006012_0", "04006012-0000")/*@res "请至少选择一行记录"*/);
    }
    Map<SaleOrderViewVO, String> mapindex =
        new HashMap<SaleOrderViewVO, String>();
    int i = 0;
    for (SaleOrderViewVO view : selviews) {
      mapindex.put(view, selids[i]);
      i++;
    }

    M38ArrangeFindSalePrice findprice = new M38ArrangeFindSalePrice(selviews);
    findprice.forceFindPrice();
    // 更新改变记录
    List<SaleOrderViewVO> listchgview = findprice.getChangeView();
    if (listchgview.size() > 0) {
      SaleOrderViewVO[] chgviews = new SaleOrderViewVO[listchgview.size()];
      listchgview.toArray(chgviews);
      String[] chgids = new String[listchgview.size()];
      i = 0;
      for (SaleOrderViewVO view : chgviews) {
        chgids[i] = mapindex.get(view);
        i++;
      }
      this.getBillContext().getModel().updateVOs(chgids, chgviews);
    }
    // 失败行提示信息
    String failmsg = findprice.getFailMsg();
    if (!PubAppTool.isNull(failmsg)) {
      ExceptionUtils.wrappBusinessException(failmsg);
    }
  }

  public BillContext getBillContext() {
    return this.context;
  }

  public void setBillContext(BillContext billContext) {
    this.context = billContext;
  }
}
