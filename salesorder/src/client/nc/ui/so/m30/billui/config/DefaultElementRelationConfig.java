/**
 * 
 */
package nc.ui.so.m30.billui.config;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * @author gdsjw
 */
public class DefaultElementRelationConfig implements IElementRelationConfig {

  private Map<String, List<String>> relationmap =
      new HashMap<String, List<String>>();

  @Override
  public List<String> getRelationByKey(String key) {
    if (this.relationmap != null) {
      return this.relationmap.get(key);
    }
    return null;
  }

  public Map<String, List<String>> getRelationmap() {
    return this.relationmap;
  }

  public void setRelationmap(Map<String, List<String>> relationmap) {
    this.relationmap = relationmap;
  }

}
