package nc.ui.so.pub.para;

import java.util.Iterator;
import java.util.LinkedHashMap;
import java.util.Map;

import nc.ui.pubapp.panel.AbstractParaListToListPanel;
import nc.vo.pub.BusinessException;
import nc.vo.pub.para.SysInitVO;
import nc.vo.pubapp.pattern.pub.PubAppTool;
import nc.vo.so.pub.enumeration.SaleOrderSplitRule;
import nc.vo.so.pub.res.ParameterList;

/**
 * 销售订单分单打印
 * 
 * @since 6.0
 * @version 2011-1-6 下午06:47:34
 * @author 祝会征
 */
public class SaleOrderSplitSysPanel extends AbstractParaListToListPanel {

  private ParaListItemInfo[] leftItems;

  private Map<String, String> mapKeyName;

  private ParaListItemInfo[] rightItems;

  /**
   * 
   * @param pk_org
   * @throws BusinessException
   */
  public SaleOrderSplitSysPanel(String pk_org) {
    super();
  }

  public Map<String, String> getKeyNameRela() {
    if (null == this.mapKeyName) {
      this.mapKeyName = new LinkedHashMap<String, String>();
      for (SaleOrderSplitRule rule : SaleOrderSplitRule.values()) {
        this.mapKeyName.put(rule.getKey(), rule.getName());
      }
    }
    return this.mapKeyName;
  }

  @Override
  public String getParamValueCode() {
    return ParameterList.SO18.getCode();
  }

  @Override
  public String chkBeforeReturn(
      AbstractParaListToListPanel.ParaListItemInfo[] retArray) {
    return null;
  }

  @Override
  public ParaListItemInfo[] initLeftItems(SysInitVO initVO) {
    if (null == this.leftItems) {
      this.buildInitData(initVO);
    }
    return this.leftItems;
  }

  @Override
  public ParaListItemInfo[] initRightItems(SysInitVO initVO) {
    if (null == this.rightItems) {
      this.buildInitData(initVO);
    }

    return this.rightItems;
  }

  private void buildInitData(SysInitVO initVO) {
    String[] initValues = new String[0];
    String value = initVO.getValue();
    if (!PubAppTool.isNull(value)) {
      initValues = value.split(ParameterList.SPLITKEY);
    }
    this.rightItems = new ParaListItemInfo[initValues.length];
    int i = 0;
    for (String key : initValues) {
      this.rightItems[i] = new ParaListItemInfo();
      String name = this.getKeyNameRela().get(key);
      this.rightItems[i].setText(name);
      this.rightItems[i].setValue(key);
      // 删除已被选中的值
      this.getKeyNameRela().remove(key);

      i++;
    }
    this.leftItems = new ParaListItemInfo[this.getKeyNameRela().size()];
    Iterator<String> iterator = this.getKeyNameRela().keySet().iterator();
    i = 0;
    while (iterator.hasNext()) {
      this.leftItems[i] = new ParaListItemInfo();
      String key = iterator.next();
      this.leftItems[i].setText(this.getKeyNameRela().get(key));
      this.leftItems[i].setValue(key);
      i++;
    }
  }
}
