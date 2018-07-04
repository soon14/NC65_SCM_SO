package nc.ui.so.m4331.billui.editor.bodyevent;

import nc.ui.pubapp.uif2app.event.IAppEventHandler;
import nc.ui.pubapp.uif2app.event.card.CardBodyAfterEditEvent;
import nc.ui.so.m4331.billui.pub.calculator.DeliveryCardCalculator;
import nc.ui.so.m4331.billui.view.DeliveryEditor;
import nc.vo.so.m4331.entity.DeliveryBVO;

public class BodyAfterEditHandler implements
IAppEventHandler<CardBodyAfterEditEvent> {
  private DeliveryEditor editor;
  @Override
  public void handleAppEvent(CardBodyAfterEditEvent e) {
    String editKey = e.getKey();
    // 物料
    if (DeliveryBVO.CMATERIALVID.equals(editKey)) {
      MateriaEditHandler handler = new MateriaEditHandler();
      handler.afterEdit(e);
    }
    // 重量、体积、件数
    else if (DeliveryBVO.NWEIGHT.equals(editKey)
        || DeliveryBVO.NVOLUME.equals(editKey)
        || DeliveryBVO.NPIECE.equals(editKey)) {
      HeadTotalCalEditHandler handler = new HeadTotalCalEditHandler();
      handler.afterEdit(e);
    }
    // 单位
    else if (DeliveryBVO.CASTUNITID.equals(editKey)) {
      AstUnitEditHandler handler = new AstUnitEditHandler();
      handler.afterEdit(e);
      editKey = DeliveryBVO.VCHANGERATE;
    }
    // 发货库存组织
    else if (DeliveryBVO.CSENDSTOCKORGVID.equals(editKey)) {
      SendStockOrgHandler handler = new SendStockOrgHandler();
      handler.afterEdit(e);
    }
    // 收货库存组织
    else if (DeliveryBVO.CINSTOCKORGVID.equals(editKey)) {
      InstockHandler handler = new InstockHandler();
      handler.afterEdit(e);
    }
    // 收货地址
    else if (DeliveryBVO.CRECEIVEADDRID.equals(editKey)) {
      ReceAddrEditHandler handler = new ReceAddrEditHandler();
      handler.afterEdit(e);
    }
    // 收货客户
    else if (DeliveryBVO.CRECEIVECUSTID.equals(editKey)) {
      ReceCustEditHandler handler = new ReceCustEditHandler();
      handler.afterEdit(e);
    }
    // 批次号
    else if (DeliveryBVO.VBATCHCODE.equals(editKey)) {
      BatchCodeEditHandler handler = new BatchCodeEditHandler();
      handler.setEditor(this.getEditor());
      handler.afterEdit(e);
    }// 收货仓库
    else if (DeliveryBVO.CINSTORDOCID.equals(editKey)) {
      CinstordocEditHandler handler = new CinstordocEditHandler();
      handler.afterEdit(e);
    }// 发货仓库
    else if (DeliveryBVO.CSENDSTORDOCID.equals(editKey)) {
      CsendstordocEditHandler handler = new CsendstordocEditHandler();
      handler.afterEdit(e);
    }// 发货利润中心
    else if (DeliveryBVO.CSPROFITCENTERVID.equals(editKey)) {
      CsprofitcenterEditHandler handler = new CsprofitcenterEditHandler();
      handler.afterEdit(e);
    }// 收货利润中心 
    else if (DeliveryBVO.CRPROFITCENTERVID.equals(editKey)) {
      CrprofitcenterEditHandler handler = new CrprofitcenterEditHandler();
      handler.afterEdit(e);
    }
    
    DeliveryCardCalculator calculator =
        new DeliveryCardCalculator(e.getBillCardPanel());
    int[] rows = new int[] {
        e.getRow()
    };
    calculator.calculate(rows, editKey);
  }
  
  public DeliveryEditor getEditor() {
    return this.editor;
  }

  public void setEditor(DeliveryEditor editor) {
    this.editor = editor;
  }
}
