package nc.ui.so.salequotation.handler;

import java.util.Map;

import nc.ui.pubapp.uif2app.event.IAppEventHandler;
import nc.ui.pubapp.uif2app.event.card.CardHeadTailAfterEditEvent;
import nc.ui.so.salequotation.model.SalequoModel;
import nc.ui.so.salequotation.model.SalequoModelService;
import nc.vo.pub.BusinessException;
import nc.vo.pubapp.pattern.exception.ExceptionUtils;
import nc.vo.so.m4310trantype.entity.M4310TranTypeVO;
import nc.vo.so.salequotation.entity.SalequotationHVO;

public class SalequoTranTypeEditHandler implements
    IAppEventHandler<CardHeadTailAfterEditEvent> {

  private SalequoModel model;

  private SalequoModelService service;

  public SalequoModel getModel() {
    return this.model;
  }

  public SalequoModelService getService() {
    return this.service;
  }

  @Override
  public void handleAppEvent(CardHeadTailAfterEditEvent e) {
    // 报价单类型
    if (SalequotationHVO.VTRANTYPE.equals(e.getKey())) {
      if (e.getValue() == null) {
        // fengjb 2012.03.05 UE提示规范
        ExceptionUtils
            .wrappBusinessException(nc.vo.ml.NCLangRes4VoTransl.getNCLangRes()
                .getStrByID("4006009_0", "04006009-0004")/*@res "报价单类型不能为空"*/);
        e.getBillCardPanel().getHeadItem(SalequotationHVO.VTRANTYPE)
            .setValue(e.getOldValue());
        return;
      }
      Map<String, M4310TranTypeVO> m4310TranTypeBuffer =
          this.getModel().getM4310TranTypeBuffer();
      if (!m4310TranTypeBuffer.containsKey(e.getValue())) {
        try {
          M4310TranTypeVO m4310TranTypeVO =
              this.getService().queryTranType(e.getContext().getPk_group(),
                  (String) e.getValue());
          m4310TranTypeBuffer.put((String) e.getValue(), m4310TranTypeVO);
        }
        catch (BusinessException e1) {
          ExceptionUtils.wrappException(e1);
        }
      }
    }
  }

  public void setModel(SalequoModel model) {
    this.model = model;
  }

  public void setService(SalequoModelService service) {
    this.service = service;
  }

}
