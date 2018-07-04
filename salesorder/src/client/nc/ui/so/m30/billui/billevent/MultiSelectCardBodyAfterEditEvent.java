/**
 * 
 */
package nc.ui.so.m30.billui.billevent;

import nc.ui.pubapp.uif2app.event.card.CardBodyAfterEditEvent;

/**
 * PUBAPP不支持多选事件，复写这个类提供多选的行信息
 * 主要用于事件派发后的变化事件传递
 * 
 * @author gdsjw
 *
 */
public class MultiSelectCardBodyAfterEditEvent extends CardBodyAfterEditEvent {

  private int[] editrows;

  public MultiSelectCardBodyAfterEditEvent(
      CardBodyAfterEditEvent cardbodyaftereditevent) {
    super(cardbodyaftereditevent.getBillCardPanel(), cardbodyaftereditevent
        .getTableCode(), cardbodyaftereditevent.getRow(),
        cardbodyaftereditevent.getKey(), cardbodyaftereditevent.getValue(),
        cardbodyaftereditevent.getOldValue());
    this.setContext(cardbodyaftereditevent.getContext());
    this.setSource(cardbodyaftereditevent.getSource());
    this.setContextObject(cardbodyaftereditevent.getContextObject());
    this.setEditrows(new int[] {
      cardbodyaftereditevent.getRow()
    });
  }

  public int[] getEditrows() {
    return this.editrows;
  }

  public void setEditrows(int[] editrows) {
    this.editrows = editrows;
  }

}
