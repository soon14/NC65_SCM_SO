package nc.ui.so.custmatrel.action;

import java.util.ArrayList;
import java.util.List;

import nc.vo.pub.ExtendedAggregatedValueObject;
import nc.vo.so.custmatrel.entity.CustMatRelHVO;

import nc.ui.pp.pub.excel.BillItemValue;
import nc.ui.pub.bill.BillItem;
import nc.ui.pub.bill.IBillItem;
import nc.ui.trade.excelimport.InputItem;
import nc.ui.trade.excelimport.Uif2BodyOnlyImportablePanel;

/**
 * 导出处理类
 * 
 * @since 6.3
 * @version 2013-05-17 13:57:37
 * @author liujingn
 */
public class CustMaterExptableEditor extends Uif2BodyOnlyImportablePanel {

  /**
   * 
   * @param title
   * @param funCode
   * @param configFilePath
   */

  public CustMaterExptableEditor(String title, String funCode,
      String configFilePath) {
    super(title, funCode, configFilePath);
  }

  /**
   *
   */
  public CustMaterExptableEditor() {
    super(null, null,
        "nc/ui/so/base/config/customermaterialrelation_config.xml");
  }

  @Override
  protected String getAddActionBeanName() {
    return null;
  }

  @Override
  protected String getAppModelBeanName() {
    return null;
  }

  @Override
  protected String getBillCardEditorBeanName() {
    return null;
  }

  @Override
  protected String getCancelActionBeanName() {
    return null;
  }

  @Override
  protected String getSaveActionBeanName() {
    return null;
  }

  @Override
  protected void setProcessedVO(ExtendedAggregatedValueObject eavo) {
    super.setProcessedVO(eavo);
  }

  @Override
  public List<InputItem> getInputItems() {
    List<InputItem> listitems = super.getInputItems();
    return this.getaddInputItem(listitems);
  }

  private List<InputItem> getaddInputItem(List<InputItem> items) {
    /* 2014-8-9 wangweir 处理导出界面多语 Begin*/
    BillItem orgItem =
        this.getBillcardPanelEditor().getBillCardPanel()
            .getHeadItem(CustMatRelHVO.PK_ORG);
    String tabName =
        this.getBillcardPanelEditor().getBillCardPanel().getBillData()
            .getTableName(IBillItem.HEAD, orgItem.getTableCode());
    orgItem.setTableName(tabName);
    InputItem orgitem = new BillItemValue(orgItem);
    /* 2014-8-9 wangweir End*/

    List<InputItem> newitems = new ArrayList<InputItem>();
    // 添加销售组织项
    newitems.add(orgitem);
    if (items == null || items.size() == 0) {
      return newitems;
    }
    for (InputItem item : items) {
      newitems.add(item);
    }
    return newitems;
  }
}
