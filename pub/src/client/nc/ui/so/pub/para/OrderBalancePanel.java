package nc.ui.so.pub.para;

import java.util.HashSet;
import java.util.Iterator;
import java.util.LinkedHashMap;
import java.util.Map;
import java.util.Set;

import nc.vo.pub.BusinessException;
import nc.vo.pub.para.CheckParaVO;
import nc.vo.pub.para.SysInitVO;
import nc.vo.pubapp.pattern.pub.PubAppTool;
import nc.vo.so.pub.enumeration.OrderBalanceRule;
import nc.vo.so.pub.res.ParameterList;

import nc.ui.ml.NCLangRes;
import nc.ui.pubapp.panel.AbstractParaListToListPanel;

/**
 * 订单收款核销条件
 * 
 * @since 6.0
 * @version 2011-1-6 下午06:47:34
 * @author 祝会征
 */
public class OrderBalancePanel extends AbstractParaListToListPanel {

  private ParaListItemInfo[] leftItems;

  private Map<String, String> mapKeyName;

  private ParaListItemInfo[] rightItems;

  /**
   * 
   * @param pk_org
   * @throws BusinessException
   */
  public OrderBalancePanel(String pk_org) {
    super();
  }

  @Override
  public CheckParaVO check() {
    CheckParaVO paravo = new CheckParaVO();
    Object[] retArray = this.getListToListPanel().getRightData();
    AbstractParaListToListPanel.ParaListItemInfo[] array =
        new AbstractParaListToListPanel.ParaListItemInfo[retArray.length];
    int i = 0;
    for (Object obj : retArray) {
      array[i++] = (ParaListItemInfo) obj;
    }
    String listrightvalue = this.getSysInitVOValue(array);
    StringBuffer errMsg = new StringBuffer();
    if (PubAppTool.isNull(listrightvalue)) {
      errMsg.append(NCLangRes.getInstance().getStrByID("4006004_0",
          "04006004-0040")/*应收组织、币种、开票客户为必输项。*/);
    }
    else {
      Set<String> tempSet = new HashSet<String>();
      String[] paras = listrightvalue.split(",");
      for (String para : paras) {
        tempSet.add(para);
      }
      if (!tempSet.contains(OrderBalanceRule.CARORGID.getKey())) {
        errMsg.append(NCLangRes.getInstance().getStrByID("4006004_0",
            "04006004-0041")/*应收组织*/);
      }
      if (!tempSet.contains(OrderBalanceRule.CORIGCURRENCYID.getKey())) {
        if (errMsg.length() > 0) {
          errMsg.append(NCLangRes.getInstance().getStrByID("4006004_0",
              "04006004-0042")/*、*/);
        }
        errMsg.append(NCLangRes.getInstance().getStrByID("4006004_0",
            "04006004-0043")/*币种*/);
      }
      if (!tempSet.contains(OrderBalanceRule.CINVOICECUSTID.getKey())) {
        if (errMsg.length() > 0) {
          errMsg.append(NCLangRes.getInstance().getStrByID("4006004_0",
              "04006004-0042")/*、*/);
        }
        errMsg.append(NCLangRes.getInstance().getStrByID("4006004_0",
            "04006004-0044")/* 开票客户*/);
      }
      if (errMsg.length() > 0) {
        errMsg.append(NCLangRes.getInstance().getStrByID("4006004_0",
            "04006004-0045")/*为必输项。*/);
      }
    }
    if (errMsg.length() > 0) {
      paravo.setErrMsg(errMsg.toString());
      paravo.setLegal(false);
      return paravo;
    }
    return null;
  }

  @Override
  public String chkBeforeReturn(
      AbstractParaListToListPanel.ParaListItemInfo[] retArray) {
    return null;
  }

  public Map<String, String> getKeyNameRela() {
    if (null == this.mapKeyName) {
      this.mapKeyName = new LinkedHashMap<String, String>();
      for (OrderBalanceRule rule : OrderBalanceRule.values()) {
        this.mapKeyName.put(rule.getKey(), rule.getName());
      }
    }
    return this.mapKeyName;
  }

  @Override
  public String getParamValueCode() {
    return ParameterList.SO11.getCode();
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
