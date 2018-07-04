package nc.ui.so.pub.para;

import java.util.Map;

import nc.bs.framework.common.NCLocator;
import nc.itf.so.pub.para.IBasedOnSignNumQuery;
import nc.ui.pubapp.panel.AbstractParaListToListPanel;
import nc.vo.pub.BusinessException;
import nc.vo.pub.para.SysInitVO;
import nc.vo.pubapp.pattern.exception.ExceptionUtils;
import nc.vo.pubapp.pattern.pub.PubAppTool;
import nc.vo.so.pub.res.ParameterList;

/**
 * 基于签收流程开发
 * 
 * @since 6.0
 * @version 2011-1-6 下午06:47:34
 * @author 祝会征
 */
public class SignNumBusiPanel extends AbstractParaListToListPanel {

  private ParaListItemInfo[] leftItems;

  private Map<String, String> mapKeyName;

  private ParaListItemInfo[] rightItems;

  /**
   * 
   * @param pk_org
   * @throws BusinessException
   */
  public SignNumBusiPanel(String pk_org) {
    super();
  }

  @Override
  public String chkBeforeReturn(
      AbstractParaListToListPanel.ParaListItemInfo[] retArray) {
    return null;
  }

  @Override
  public String getParamValueCode() {
    return ParameterList.SO16.getCode();
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
    String[] busitypes = new String[this.getKeyNameRela().size()];
    this.getKeyNameRela().keySet().toArray(busitypes);
    i = 0;
    for (String key : busitypes) {
      this.leftItems[i] = new ParaListItemInfo();
      this.leftItems[i].setText(this.getKeyNameRela().get(key));
      this.leftItems[i].setValue(key);
      i++;
    }
  }

  private Map<String, String> getKeyNameRela() {
    if (null == this.mapKeyName) {
      IBasedOnSignNumQuery service =
          NCLocator.getInstance().lookup(IBasedOnSignNumQuery.class);
      try {
        this.mapKeyName = service.queryBusitypes();
      }
      catch (BusinessException e) {
        ExceptionUtils.wrappException(e);
      }
    }
    return this.mapKeyName;
  }
}
