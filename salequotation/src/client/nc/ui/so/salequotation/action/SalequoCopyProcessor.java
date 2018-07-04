package nc.ui.so.salequotation.action;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import nc.bs.framework.common.NCLocator;
import nc.itf.price.priceform.IPriceFormService;
import nc.itf.scmpub.reference.uap.org.OrgUnitPubService;
import nc.ui.pubapp.AppUiContext;
import nc.ui.pubapp.uif2app.actions.intf.ICopyActionProcessor;
import nc.vo.pub.BusinessException;
import nc.vo.pub.VOStatus;
import nc.vo.pubapp.pattern.exception.ExceptionUtils;
import nc.vo.pubapp.pattern.pub.PubAppTool;
import nc.vo.so.salequotation.entity.AggSalequotationHVO;
import nc.vo.so.salequotation.entity.BillStatusEnum;
import nc.vo.so.salequotation.entity.SalequotationBVO;
import nc.vo.so.salequotation.entity.SalequotationHVO;
import nc.vo.uif2.LoginContext;

public class SalequoCopyProcessor implements
    ICopyActionProcessor<AggSalequotationHVO> {

  private IPriceFormService priceFormService;

  @Override
  public void processVOAfterCopy(AggSalequotationHVO billVO,
      LoginContext context) {
    SalequotationHVO hvo = billVO.getParentVO();

    // 设置新的销售组织
    String newOrgID = OrgUnitPubService.getNewVIDByOrgID(hvo.getPk_org());
    hvo.setPk_org_v(newOrgID);

    if (hvo != null) {
      hvo.setPrimaryKey(null);
      hvo.setFstatusflag(Integer.valueOf(BillStatusEnum.C_FREE));
      hvo.setVbillcode(null);
      hvo.setTs(null);
      hvo.setDr(null);
      hvo.setBillmaker(null);
      hvo.setDmakedate(null);
      hvo.setCreationtime(null);
      hvo.setCreator(null);
      hvo.setDbilldate(null);
      hvo.setDquotedate(AppUiContext.getInstance().getBusiDate());
      if (hvo.getDenddate().before(hvo.getDquotedate())) {
        hvo.setDenddate(null);
      }
      hvo.setModifier(null);
      hvo.setModifiedtime(null);
      hvo.setApprover(null);
      hvo.setDauditdate(null);
      hvo.setTaudittime(null);
    }

    SalequotationBVO[] bvos = billVO.getChildrenVO();
    if (bvos != null) {
      for (int i = 0; i < bvos.length; i++) {
        bvos[i].setPrimaryKey(null);
        bvos[i].setTs(null);
        bvos[i].setDr(null);
        bvos[i].setNordernum(null);
        bvos[i].setNcontractnum(null);
      }
    }

    // 价格组成重新生成id，以便于提供价格组成持久化保存
    String[] priceForms = this.getPriceForms(billVO);
    if (priceForms.length > 0) {
      Map<String, String> map = null;
      try {
        map = this.getPriceFormService().copyPriceForm(priceForms);
      }
      catch (BusinessException e) {
        ExceptionUtils.wrappException(e);
      }
      for (SalequotationBVO item : bvos) {
        String oldpriceform = item.getVpricedetail();
        if (!PubAppTool.isNull(oldpriceform)) {
          item.setVpricedetail(map.get(oldpriceform));
        }
      }
    }
  }

  private String[] getPriceForms(AggSalequotationHVO bill) {
    List<String> priceForms = new ArrayList<String>();
    SalequotationBVO[] bvos = bill.getChildrenVO();
    for (SalequotationBVO bvo : bvos) {
      if (bvo.getStatus() != VOStatus.DELETED && bvo.getVpricedetail() != null) {
        priceForms.add(bvo.getVpricedetail());
      }
    }
    return priceForms.toArray(new String[0]);
  }

  private IPriceFormService getPriceFormService() {
    if (this.priceFormService == null) {
      this.priceFormService =
          NCLocator.getInstance().lookup(IPriceFormService.class);
    }
    return this.priceFormService;
  }

}
