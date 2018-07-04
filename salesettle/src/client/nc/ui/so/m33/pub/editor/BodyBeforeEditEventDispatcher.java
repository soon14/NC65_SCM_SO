package nc.ui.so.m33.pub.editor;

import nc.ui.pub.beans.UIRefPane;
import nc.ui.pubapp.uif2app.event.IAppEventHandler;
import nc.ui.pubapp.uif2app.event.list.ListBodyBeforeEditEvent;
import nc.ui.so.pub.keyvalue.ListKeyValue;
import nc.vo.so.m33.m4c.entity.SquareOutBVO;
import nc.vo.so.m33.m4c.entity.SquareOutHVO;
import nc.vo.so.pub.enumeration.ListTemplateType;
import nc.vo.so.pub.keyvalue.IKeyValue;

public class BodyBeforeEditEventDispatcher implements
    IAppEventHandler<ListBodyBeforeEditEvent> {

  @Override
  public void handleAppEvent(ListBodyBeforeEditEvent e) {
    e.setReturnValue(Boolean.TRUE);
    if (SquareOutBVO.CINVOICECUSTID.equals(e.getKey())) {
      UIRefPane refPanel =
          (UIRefPane) e.getBillListPanel().getBodyItem(e.getKey())
              .getComponent();
      if (refPanel.getRefModel() != null) {
        IKeyValue ikey =
            new ListKeyValue(e.getBillListPanel(), e.getRow(),
                ListTemplateType.SUB);
        String pk_org =
            ikey.getBodyStringValue(e.getRow(), SquareOutHVO.PK_ORG);
        refPanel.getRefModel().setPk_org(pk_org);
      }
    }
  }

}
