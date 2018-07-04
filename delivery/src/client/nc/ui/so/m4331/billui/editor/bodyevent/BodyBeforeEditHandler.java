package nc.ui.so.m4331.billui.editor.bodyevent;

import nc.ui.pubapp.uif2app.event.IAppEventHandler;
import nc.ui.pubapp.uif2app.event.card.CardBodyBeforeEditEvent;
import nc.ui.so.m4331.billui.view.DeliveryEditor;
import nc.vo.so.m4331.entity.DeliveryBVO;

public class BodyBeforeEditHandler implements
    IAppEventHandler<CardBodyBeforeEditEvent> {

  private DeliveryEditor editor;

  @Override
  public void handleAppEvent(CardBodyBeforeEditEvent e) {

    String editkey = e.getKey();
    // 物料
    if (DeliveryBVO.CMATERIALVID.equals(editkey)) {
      MateriaEditHandler handler = new MateriaEditHandler();
      // modify by jilu for EHP1合盘到633 20140703
      handler.setEditor(this.editor);
      handler.beforeEdit(e);
    }
    // 发货库存组织
    else if (DeliveryBVO.CSENDSTOCKORGVID.equals(editkey)) {
      SendStockOrgHandler handler = new SendStockOrgHandler();
      handler.beforeEdit(e);
    }
    // 收货库存组织
    else if (DeliveryBVO.CINSTOCKORGID.equals(editkey)) {
      InstockHandler handler = new InstockHandler();
      handler.beforeEdit(e);
    }
    // 发货仓库
    else if (DeliveryBVO.CSENDSTORDOCID.equals(editkey)) {
      SendStordocHandler handler = new SendStordocHandler();
      handler.beforeEdit(e);
    }
    // 收货仓库
    else if (DeliveryBVO.CINSTORDOCID.equals(editkey)) {
      InstordocHandler handler = new InstordocHandler();
      handler.beforeEdit(e);
    }
    // 批次号
    else if (DeliveryBVO.VBATCHCODE.equals(editkey)) {
      BatchCodeEditHandler handler = new BatchCodeEditHandler();
      handler.setEditor(this.editor);
      handler.beforeEdit(e);
    }
    // 货位
    else if (DeliveryBVO.CSPACEID.equals(editkey)) {
      SpaceEditHandler handler = new SpaceEditHandler();
      handler.beforeEdit(e);
    }
    // 司机
    else if (DeliveryBVO.CCHAUFFEURID.equals(editkey)) {
      ChauffeurEditHandler handler = new ChauffeurEditHandler();
      handler.beforeEdit(e);
    }
    // 押运员
    else if (DeliveryBVO.CSUPERCARGOID.equals(editkey)) {
      SupercargoEditHandler handler = new SupercargoEditHandler();
      handler.beforeEdit(e);
    }
    // 车辆
    else if (DeliveryBVO.CVEHICLEID.equals(editkey)) {
      VehicleEditHandler handler = new VehicleEditHandler();
      handler.beforeEdit(e);
    }
    // 车型
    else if (DeliveryBVO.CVEHICLETYPEID.equals(editkey)) {
      VehicleTypeEditHandler handler = new VehicleTypeEditHandler();
      handler.beforeEdit(e);
    }
    // 承运商
    else if (DeliveryBVO.CTRANSCUSTID.equals(editkey)) {
      TransCustEditHandler handler = new TransCustEditHandler();
      handler.beforeEdit(e);
    }
    // 收货联系人
    else if (DeliveryBVO.CRECEIVEPERSONID.equals(editkey)) {
      ReceivePersonidEditHandler handler = new ReceivePersonidEditHandler();
      handler.beforeEdit(e);
    }
    // 单位
    else if (DeliveryBVO.CASTUNITID.equals(editkey)) {
      AstUnitEditHandler handler = new AstUnitEditHandler();
      handler.beforeEdit(e);
    }
    // 发货联系人
    else if (DeliveryBVO.CSENDPERSONID.equals(editkey)) {
      SendpersonidEditHandler handler = new SendpersonidEditHandler();
      handler.beforeEdit(e);
    }
    // 原产地区
    else if (DeliveryBVO.CORIGAREAID.equals(editkey)) {
      OrigAreaEditHandler handler = new OrigAreaEditHandler();
      handler.beforeEdit(e);
    }
    else if(DeliveryBVO.CDEPTVID.equals(editkey)){
      DeptEditHandler handler=new DeptEditHandler();
      handler.beforeEdit(e);
    }
    else if(DeliveryBVO.CEMPLOYEEID.equals(editkey)){
      EmployeeEditHandler handler = new EmployeeEditHandler();
      handler.beforeEmployeeEdit(e);
    }
    else if(DeliveryBVO.CORDERCUSTID.equals(editkey)){
      OrderCustEditHandler handler=new OrderCustEditHandler();
      handler.beforeEdit(e);
    }
  }

  public DeliveryEditor getEditor() {
    return this.editor;
  }

  public void setEditor(DeliveryEditor editor) {
    this.editor = editor;
  }
}
