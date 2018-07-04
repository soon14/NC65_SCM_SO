package nc.ui.so.report.tbb.m30;

import java.util.List;

import nc.bs.framework.common.NCLocator;
import nc.funcnode.ui.FuncletInitData;
import nc.pubitf.so.tbb.detail.ISOTbbDetail;
import nc.ui.pub.bill.BillCardPanel;
import nc.ui.pubapp.uif2app.model.BillManageModel;
import nc.ui.pubapp.uif2app.model.DefaultFuncNodeInitDataListener.IInitDataProcessor;
import nc.ui.pubapp.uif2app.view.ShowUpableBillForm;
import nc.vo.ml.NCLangRes4VoTransl;
import nc.vo.pub.BusinessException;
import nc.vo.pub.CircularlyAccessibleValueObject;
import nc.vo.pubapp.pattern.exception.ExceptionUtils;
import nc.vo.so.m30.entity.SaleOrderViewVO;
import nc.vo.tb.obj.NtbParamVO;

public class M30InitDataProcessor implements IInitDataProcessor {
  private ShowUpableBillForm billForm;

  private BillManageModel model;

  public ShowUpableBillForm getBillForm() {
    return this.billForm;
  }

  public BillManageModel getModel() {
    return this.model;
  }

  @SuppressWarnings("unchecked")
  @Override
  public void process(FuncletInitData data) {
    
     List<NtbParamVO> list = (List<NtbParamVO>) data.getInitData();
     if (null == list) {
     ExceptionUtils.wrappBusinessException(NCLangRes4VoTransl.getNCLangRes()
     .getStrByID("4006005_0", "04006005-0001")/*@res "请选中一个单元格进行联查。"*/);
     return;
     }
     if (list.size() != 1) {
     ExceptionUtils.wrappBusinessException(NCLangRes4VoTransl.getNCLangRes()
     .getStrByID("4006005_0", "04006005-0001")/*@res "请选中一个单元格进行联查。"*/);
     }
     NtbParamVO vo = list.get(0);
     ISOTbbDetail service =
     NCLocator.getInstance().lookup(ISOTbbDetail.class);
     try {
     SaleOrderViewVO[] views = service.getSaleorderDetail(vo);
     this.setVOInCard(views);
     }
     catch (BusinessException e) {
     ExceptionUtils.wrappException(e);
     }
  }

  public void setBillForm(ShowUpableBillForm cardView) {
    this.billForm = cardView;
  }

  public void setModel(BillManageModel model) {
    this.model = model;
  }

  public void setVOInCard(CircularlyAccessibleValueObject[] selectedData) {
    BillCardPanel cardPanel = this.billForm.getBillCardPanel();
    cardPanel.getBillData().setBodyValueVO(selectedData);
    // 设置选中第一行
    cardPanel.getBillTable().getSelectionModel().setSelectionInterval(0, 0);
    cardPanel.getBillModel().loadLoadRelationItemValue();
    cardPanel.getBillModel().execLoadFormula();
  }

}
