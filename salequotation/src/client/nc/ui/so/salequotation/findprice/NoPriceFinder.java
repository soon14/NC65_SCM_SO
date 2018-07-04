package nc.ui.so.salequotation.findprice;

import nc.ui.pubapp.uif2app.event.card.CardBodyAfterEditEvent;
import nc.ui.pubapp.uif2app.event.card.CardHeadTailAfterEditEvent;

/**
 * 不取价
 * 
 * @author chenyyb
 * @since 6.0
 * @time 2010-8-2 上午10:44:51
 * 
 */
public class NoPriceFinder extends AbstractPriceFinder {

  @Override
  public void findPrice(CardBodyAfterEditEvent e, int rows) {
    // do nothing
  }

  @Override
  public void findPrice(CardHeadTailAfterEditEvent e) {
    // do nothing
  }

}
