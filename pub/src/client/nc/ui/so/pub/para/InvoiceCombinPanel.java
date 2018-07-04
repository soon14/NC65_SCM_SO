package nc.ui.so.pub.para;

import java.awt.Component;
import java.awt.FlowLayout;
import java.util.Iterator;
import java.util.LinkedHashMap;
import java.util.Map;

import nc.bs.para.ComplicatedParaContext;
import nc.ui.ml.NCLangRes;
import nc.ui.pub.beans.UIComboBox;
import nc.ui.pub.beans.UIPanel;
import nc.ui.pubapp.panel.AbstractParaListToListPanel;
import nc.ui.trade.component.ListToListPanel;
import nc.vo.pub.BusinessException;
import nc.vo.pub.para.SysInitVO;
import nc.vo.pubapp.pattern.pub.PubAppTool;
import nc.vo.so.m32.entity.SaleInvoiceBVO;
import nc.vo.so.pub.enumeration.InvoiceCombinRule;
import nc.vo.so.pub.res.ParameterList;

public class InvoiceCombinPanel extends AbstractParaListToListPanel {

  private ParaListItemInfo[] leftItems;

  private Map<String, String> mapKeyName;

  private UIPanel radioButtonPanel;

  private ParaListItemInfo[] rightItems;

  /**
   * 
   * @param pk_org
   * @throws BusinessException
   */
  public InvoiceCombinPanel(String pk_org) {
    super();
  }

  public Map<String, String> getKeyNameRela() {
    if (null == this.mapKeyName) {
      this.mapKeyName = new LinkedHashMap<String, String>();
      for (InvoiceCombinRule rule : InvoiceCombinRule.values()) {
        this.mapKeyName.put(rule.getKey(), rule.getName());
      }
    }
    return this.mapKeyName;
  }

  @Override
  public UIPanel getPanel(ComplicatedParaContext context) {
    ListToListPanel listtopanel = (ListToListPanel) super.getPanel(context);
    listtopanel.setExtensionPanel(this.getRadioButtonPanel());
    return listtopanel;
  }

  @Override
  public String getParamValueCode() {
    return ParameterList.SO28.getCode();
  }

  @Override
  public SysInitVO[] getSysInitVOs() {
    SysInitVO[] retvo = super.getSysInitVOs();
    Component[] coms = this.getRadioButtonPanel().getComponents();
    for (Component com : coms) {
      if (com instanceof UIComboBox) {
        String value = retvo[0].getValue();
        if (value.indexOf(SaleInvoiceBVO.CMARBASCALSSID) != -1
            || value.indexOf(SaleInvoiceBVO.CMATERIALID) != -1
            || value.indexOf(SaleInvoiceBVO.CMATERIALVID) != -1) {
          if (value.indexOf(SaleInvoiceBVO.CUNITID) == -1) {
            value += SaleInvoiceBVO.CUNITID + ",";
          }
          if (value.indexOf(SaleInvoiceBVO.CASTUNITID) == -1) {
            value += SaleInvoiceBVO.CASTUNITID + ",";
          }
          if (value.indexOf(SaleInvoiceBVO.CQTUNITID) == -1) {
            value += SaleInvoiceBVO.CQTUNITID + ",";
          }
        }
        if (value.indexOf(SaleInvoiceBVO.NTAXRATE) == -1) {
          value += SaleInvoiceBVO.NTAXRATE + ",";
        }
        if (value.indexOf(SaleInvoiceBVO.NDISCOUNTRATE) == -1) {
          value += SaleInvoiceBVO.NDISCOUNTRATE + ",";
        }
        if (value.indexOf(SaleInvoiceBVO.NINVOICEDISRATE) == -1) {
          value += SaleInvoiceBVO.NINVOICEDISRATE + ",";
        }
        if (value.indexOf(SaleInvoiceBVO.NITEMDISCOUNTRATE) == -1) {
          value += SaleInvoiceBVO.NITEMDISCOUNTRATE + ",";
        }
        retvo[0].setValue(value
            + ((UIComboBox) com).getSelectdItemValue().toString() + ",");
      }
    }
    return retvo;
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

  protected UIPanel getRadioButtonPanel() {
    if (this.radioButtonPanel == null) {
      this.radioButtonPanel = new UIPanel();
      this.radioButtonPanel.setLayout(new FlowLayout());
      UIComboBox combo = new UIComboBox();
      String[] arNames = new String[7];
      arNames[0] =
          NCLangRes.getInstance().getStrByID("4006004_0", "04006004-0026")/*逐级汇总*/;
      arNames[1] =
          NCLangRes.getInstance().getStrByID("4006004_0", "04006004-0027")/*末级汇总*/;
      arNames[2] =
          NCLangRes.getInstance().getStrByID("4006004_0", "04006004-0028")/*一级汇总*/;
      arNames[3] =
          NCLangRes.getInstance().getStrByID("4006004_0", "04006004-0029")/*二级汇总*/;
      arNames[4] =
          NCLangRes.getInstance().getStrByID("4006004_0", "04006004-0030")/*三级汇总*/;
      arNames[5] =
          NCLangRes.getInstance().getStrByID("4006004_0", "04006004-0031")/*四级汇总*/;
      arNames[6] =
          NCLangRes.getInstance().getStrByID("4006004_0", "04006004-0032")/*五级汇总*/;
      combo.addItems(arNames);
      combo.setName("cmaterialbaseclass");
      this.radioButtonPanel.add(combo);
    }
    return this.radioButtonPanel;
  }

  private void buildInitData(SysInitVO initVO) {

    String[] initValues = new String[0];
    String value = initVO.getValue();

    // value = value.substring(0, value.lastIndexOf(","));
    // value = value.substring(0, value.lastIndexOf(",") + 1);
    if (!PubAppTool.isNull(value)) {
      initValues = value.split(ParameterList.SPLITKEY);
    }
    this.rightItems = new ParaListItemInfo[initValues.length - 1];
    for (int i = 0; i < initValues.length - 1; i++) {
      String key = initValues[i];
      this.rightItems[i] = new ParaListItemInfo();
      String name = this.getKeyNameRela().get(key);
      this.rightItems[i].setText(name);
      this.rightItems[i].setValue(key);
      // 删除已被选中的值
      this.getKeyNameRela().remove(key);
    }

    Component[] coms = this.getRadioButtonPanel().getComponents();
    for (Component com : coms) {
      if (com instanceof UIComboBox) {
        String levelvalue = initValues[initValues.length - 1];
        ((UIComboBox) com).setSelectedItem(levelvalue);
      }
    }

    int i = 0;
    // for (String key : initValues) {
    // this.rightItems[i] = new ParaListItemInfo();
    // String name = this.getKeyNameRela().get(key);
    // this.rightItems[i].setText(name);
    // this.rightItems[i].setValue(key);
    // // 删除已被选中的值
    // this.getKeyNameRela().remove(key);
    // i++;
    // }

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
