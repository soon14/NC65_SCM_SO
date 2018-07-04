package nc.ui.so.m32.billui.action;

import java.awt.event.ActionEvent;

import nc.vo.pub.AggregatedValueObject;
import nc.vo.pub.SuperVO;
import nc.vo.pubapp.pattern.model.entity.bill.AbstractBill;
import nc.vo.so.m32.entity.SaleInvoiceBVO;
import nc.vo.so.m32.entity.SaleInvoiceVO;
import nc.vo.so.m32.paravo.CombinCacheVO;
import nc.vo.so.m32.paravo.CombinResultVO;

import nc.itf.pubapp.pub.smart.IBillQueryService;

import nc.md.persist.framework.MDPersistenceService;

import nc.bs.framework.common.NCLocator;
import nc.bs.logging.Logger;

import nc.ui.pubapp.uif2app.actions.RefreshSingleAction;
import nc.ui.pubapp.uif2app.query2.model.IModelDataManager;
import nc.ui.so.m32.billui.model.SaleInvoiceManageModel;
import nc.ui.so.m32.billui.pub.SaleInvoiceCombin;

/**
 * 销售发票卡片刷新 ----为了适用于汇总显示
 * 
 * @since 6.0
 * @version 2011-8-12 下午04:22:55
 * @author 么贵敬
 */
public class InvoiceRefreshSingleAction extends RefreshSingleAction {

  private static final long serialVersionUID = 1L;

  private IModelDataManager dataManager;

  @Override
  public void doAction(ActionEvent e) throws Exception {

    Object obj = this.model.getSelectedData();

    if (obj != null) {
      if (obj instanceof SuperVO) {
        SuperVO oldVO = (SuperVO) obj;
        SuperVO newVO =
            MDPersistenceService.lookupPersistenceQueryService()
                .queryBillOfVOByPK(oldVO.getClass(), oldVO.getPrimaryKey(),
                    false);
        this.model.directlyUpdate(newVO);
      }
      else if (obj instanceof AbstractBill) {

        AbstractBill oldVO = (AbstractBill) obj;
        String pk = oldVO.getParentVO().getPrimaryKey();
        IBillQueryService billQuery =
            NCLocator.getInstance().lookup(IBillQueryService.class);
        AggregatedValueObject newVO =
            billQuery.querySingleBillByPk(oldVO.getClass(), pk);

        SaleInvoiceManageModel invoicemodel =
            (SaleInvoiceManageModel) this.getModel();
        CombinCacheVO cachevo = invoicemodel.getCombinCacheVO();
        if (cachevo.getBcombinflag()) {
          newVO = this.getCombinNewVO(newVO, cachevo);
        }
        this.model.directlyUpdate(newVO);

      }
      else {
        Logger.debug("目前只支持SuperVO结构的数据"); /*-=notranslate=-*/
      }
    }
  }

  /**
   * 
   * @return 数据管理模型
   */
  public IModelDataManager getDataManager() {
    return this.dataManager;
  }

  /**
   * 
   * @param dataManager 数据管理模型
   */
  public void setDataManager(IModelDataManager dataManager) {
    this.dataManager = dataManager;
  }

  private AggregatedValueObject getCombinNewVO(AggregatedValueObject newVO,
      CombinCacheVO cachevo) {
    SaleInvoiceVO[] invoicevo = new SaleInvoiceVO[] {
      (SaleInvoiceVO) newVO
    };

    SaleInvoiceBVO[] bvos = invoicevo[0].getChildrenVO();
    for (SaleInvoiceBVO bvo : bvos) {
      cachevo.getCombinRela().remove(bvo.getCsaleinvoicebid());
    }

    SaleInvoiceCombin combinutil = new SaleInvoiceCombin();
    CombinResultVO combinres =
        combinutil.combinSaleInvoices(invoicevo, cachevo);

    return combinres.getCombinvos()[0];
  }

}
