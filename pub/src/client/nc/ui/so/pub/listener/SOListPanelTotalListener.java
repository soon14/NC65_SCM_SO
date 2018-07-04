package nc.ui.so.pub.listener;

import nc.ui.so.pub.keyvalue.ListKeyValue;
import nc.ui.so.pub.keyvalue.ListPanelValueUtils;
import nc.vo.so.pub.keyvalue.IKeyValue;

/**
 * billlistpanel 合计行处理类
 * 
 * @since 6.0
 * @version 2011-9-7 下午02:25:32
 * @author 么贵敬
 */
public class SOListPanelTotalListener extends SOBillTotalListener {

  public SOListPanelTotalListener(IKeyValue keyValue) {
    super(keyValue);
  }

  public SOListPanelTotalListener(ListPanelValueUtils listutils) {
    super(new ListKeyValue(listutils.getListPanel(), listutils.getType()));
  }

}
