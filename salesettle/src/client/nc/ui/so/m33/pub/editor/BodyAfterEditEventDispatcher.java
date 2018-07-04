package nc.ui.so.m33.pub.editor;

import nc.ui.pubapp.uif2app.event.IAppEventHandler;
import nc.ui.pubapp.uif2app.event.list.ListBodyAfterEditEvent;
import nc.ui.so.m33.pub.editor.headevent.after.PriceNumMnyHandler;
import nc.vo.so.m33.m4c.entity.SquareOutBVO;

public class BodyAfterEditEventDispatcher implements
    IAppEventHandler<ListBodyAfterEditEvent> {

  @Override
  public void handleAppEvent(ListBodyAfterEditEvent e) {
    if (SquareOutBVO.NTHISNUM.equals(e.getKey())) {
      new PriceNumMnyHandler().afterEdit(e);
    }
    else if (SquareOutBVO.NORIGTAXNETPRICE.equals(e.getKey())) {
      new PriceNumMnyHandler().afterEdit(e);
    }
    else if (SquareOutBVO.NORIGTAXPRICE.equals(e.getKey())) {
      new PriceNumMnyHandler().afterEdit(e);
    }
    else if (SquareOutBVO.NORIGNETPRICE.equals(e.getKey())) {
      new PriceNumMnyHandler().afterEdit(e);
    }
    else if (SquareOutBVO.NORIGPRICE.equals(e.getKey())) {
      new PriceNumMnyHandler().afterEdit(e);
    }
    else if (SquareOutBVO.NORIGTAXMNY.equals(e.getKey())) {
      new PriceNumMnyHandler().afterEdit(e);
    }
    else if (SquareOutBVO.NORIGMNY.equals(e.getKey())) {
      new PriceNumMnyHandler().afterEdit(e);
    }

  }

}
