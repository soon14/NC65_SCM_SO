/**
 * 
 */
package nc.ui.so.m30.billui.config;

import java.util.List;

/**
 * @author gdsjw
 *
 */
public interface IElementItemConfig {

  List<String> getCompriseItemKeys(String elementkey);

  List<String> getParentElementKeys(String itemkey);

}
