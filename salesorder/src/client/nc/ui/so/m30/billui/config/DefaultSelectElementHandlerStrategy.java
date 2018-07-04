/**
 * 
 */
package nc.ui.so.m30.billui.config;


/**
 * @author gdsjw
 * 
 */
public class DefaultSelectElementHandlerStrategy implements
    ISelectElementHandlerStrategy {

  // @Override
  // public IElementHandler getElementHandlerOfItemkey(IBillConfig billconfig,
  // String key) {
  // IElementHandler itemhandler =
  // billconfig.getElementEventHandlerConfig().getHandlerByKey(key);
  // if (itemhandler != null) {
  // return itemhandler;
  // }
  // List<String> elementkeys =
  // billconfig.getElementItemConfig().getParentElementKeys(key);
  // if ((elementkeys != null) && (elementkeys.size() > 0)) {
  // Iterator<String> it = elementkeys.iterator();
  // while (it.hasNext()) {
  // String elementkey = it.next();
  // itemhandler =
  // billconfig.getElementEventHandlerConfig().getHandlerByKey(
  // elementkey);
  // if (itemhandler != null) {
  // break;
  // }
  // }
  // }
  // return itemhandler;
  // }
  //
  // @Override
  // public List<IElementHandler> getRelationElementHandlerOfItemkey(
  // IBillConfig billconfig, String key) {
  // List<IElementHandler> itemhandlers = new ArrayList<IElementHandler>();
  //
  // List<String> relkeys =
  // billconfig.getElementRelationConfig().getRelationByKey(key);
  // if ((relkeys == null) || (relkeys.size() == 0)) {
  // List<String> elementkeys =
  // billconfig.getElementItemConfig().getParentElementKeys(key);
  // if ((elementkeys != null) && (elementkeys.size() > 0)) {
  // Iterator<String> it = elementkeys.iterator();
  // while (it.hasNext()) {
  // String elementkey = it.next();
  // relkeys =
  // billconfig.getElementRelationConfig()
  // .getRelationByKey(elementkey);
  // if ((relkeys != null) && (relkeys.size() > 0)) {
  // break;
  // }
  // }
  // }
  // }
  //
  // if ((relkeys != null) && (relkeys.size() > 0)) {
  // for (String relkey : relkeys) {
  // IElementHandler itemhandler =
  // this.getElementHandlerOfItemkey(billconfig, relkey);
  // if (itemhandler != null) {
  // itemhandlers.add(itemhandler);
  // }
  // }
  // }
  //
  // if (itemhandlers.size() == 0) {
  // itemhandlers = null;
  // }
  // return itemhandlers;
  // }
}
