/**
 * 
 */
package nc.ui.so.m30.billui.config;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

/**
 * @author gdsjw
 * 
 */
public class DefaultElementItemConfig implements IElementItemConfig {

  private Map<String, List<String>> elementitemmap =
      new HashMap<String, List<String>>();

  @Override
  public List<String> getCompriseItemKeys(String elementkey) {
    if (this.elementitemmap != null) {
      return this.elementitemmap.get(elementkey);
    }
    return null;
  }

  @Override
  public List<String> getParentElementKeys(String itemkey) {
    if (this.elementitemmap != null) {
      List<String> elementkeys = new ArrayList<String>();
      Iterator<String> it = this.elementitemmap.keySet().iterator();
      while (it.hasNext()) {
        String elementkey = it.next();
        List<String> itemkeys = this.elementitemmap.get(elementkey);
        if (itemkeys.contains(itemkey)) {
          elementkeys.add(elementkey);
        }
      }
      return elementkeys;
    }
    return null;
  }

  public Map<String, List<String>> getElementitemmap() {
    return this.elementitemmap;
  }

  public void setElementitemmap(Map<String, List<String>> elementitemmap) {
    this.elementitemmap = elementitemmap;
  }

}
