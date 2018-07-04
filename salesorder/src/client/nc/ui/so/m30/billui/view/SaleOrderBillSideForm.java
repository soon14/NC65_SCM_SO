package nc.ui.so.m30.billui.view;

import java.awt.Color;
import java.awt.Component;
import java.awt.GridLayout;

import nc.bs.framework.common.NCLocator;
import nc.itf.scmpub.reference.uap.group.SysInitGroupQuery;
import nc.pubitf.ic.atp.IAtpQuery;
import nc.ui.ml.NCLangRes;
import nc.ui.pub.beans.UILabel;
import nc.ui.pub.beans.UIPanel;
import nc.ui.pubapp.uif2app.event.IAppEventHandler;
import nc.ui.pubapp.uif2app.event.card.CardBodyRowChangedEvent;
import nc.ui.pubapp.uif2app.model.IAppModelEx;
import nc.ui.so.pub.keyvalue.CardKeyValue;
import nc.ui.uif2.components.IDisplayable;
import nc.ui.uif2.model.AbstractAppModel;
import nc.vo.pub.BusinessException;
import nc.vo.pub.lang.UFDate;
import nc.vo.pub.lang.UFDouble;
import nc.vo.pubapp.pattern.exception.ExceptionUtils;
import nc.vo.so.m30.entity.SaleOrderBVO;
import nc.vo.so.m30.entity.SaleOrderHVO;

public class SaleOrderBillSideForm extends UIPanel implements IDisplayable,
    IAppEventHandler<CardBodyRowChangedEvent> {

  /**
   * 
   */
  private static final long serialVersionUID = -8854417772068776092L;

  private String canNum;

  // 是否显示右边栏
  private boolean isShow = true;

  private UILabel m_canNumLabel;

  private UILabel m_onhandNumLabel;

  private UILabel m_preceiveBalanceMnyLabel;

  private AbstractAppModel model;

  private String onhandNum;

  private String preceiveBalanceMny;

  public SaleOrderBillSideForm() {
    super();
    this.initUI();
  }

  public AbstractAppModel getModel() {
    return this.model;
  }

  @Override
  public void handleAppEvent(CardBodyRowChangedEvent e) {
    CardKeyValue keyValue = new CardKeyValue(e.getBillCardPanel());
    this.preceiveBalanceMny =
        NCLangRes.getInstance().getStrByID("4006011_0", "04006011-0276", null,
            new String[] {
              keyValue.getHeadUFDoubleValue(SaleOrderHVO.NTOTALNUM).toString()
            })/*{0}(表头总数量)*/;
    this.onhandNum =
        NCLangRes.getInstance().getStrByID("4006011_0", "04006011-0277", null,
            new String[] {
              keyValue.getBodyStringValue(e.getRow(), SaleOrderBVO.NNUM)
            })/*{0}(表体主数量)*/;
    this.canNum =
        NCLangRes.getInstance().getStrByID("4006011_0", "04006011-0278", null,
            new String[] {
              keyValue.getBodyStringValue(e.getRow(), SaleOrderBVO.NASTNUM)
            })/*{0}(表体数量)*/;

    // 可用量
    String csendstockorgid =
        keyValue.getBodyStringValue(e.getRow(), SaleOrderBVO.CSENDSTOCKORGID);
    String cmaterialid =
        keyValue.getBodyStringValue(e.getRow(), SaleOrderBVO.CMATERIALID);
    UFDate dsenddate =
        keyValue.getBodyUFDateValue(e.getRow(), SaleOrderBVO.DSENDDATE);
    IAtpQuery atp = NCLocator.getInstance().lookup(IAtpQuery.class);
    UFDouble[] canNums = null;
    try {
      canNums = atp.queryAtp(new String[] {
        csendstockorgid
      }, new String[] {
        cmaterialid
      }, new UFDate[] {
        dsenddate
      });
    }
    catch (BusinessException e1) {

      ExceptionUtils.wrappException(e1);
    }
    Component[] cps = this.getComponents();
    for (Component cp : cps) {
      if ("preceiveBalanceMny".equals(cp.getName())) {
        UILabel label = (UILabel) cp;
        label.setText(this.preceiveBalanceMny);
      }
      else if ("onhandNum".equals(cp.getName())) {
        UILabel label = (UILabel) cp;
        label.setText(this.onhandNum);
      }
      else if ("canNum".equals(cp.getName())) {
        UILabel label = (UILabel) cp;
        if ((canNums != null) && (canNums.length > 0)) {
          label.setText(canNums[0].toString());
        }
        else {
          label.setText(this.canNum);
        }

      }
    }

    this.updateUI();
  }

  @Override
  public boolean isComponentDisplayable() {
    // 没有启用库存，右边栏不显示
    if (!SysInitGroupQuery.isICEnabled()) {
      return false;
    }
    return this.isShow;
  }

  public void setModel(AbstractAppModel model) {
    this.model = model;
    ((IAppModelEx) this.getModel()).addAppEventListener(
        CardBodyRowChangedEvent.class, this);
  }

  private Component getCanNumLabel() {
    if (this.m_onhandNumLabel == null) {
      this.m_onhandNumLabel = new UILabel();
      this.m_onhandNumLabel.setName("onhandNum");
    }
    return this.m_onhandNumLabel;
  }

  private Component getOnhandNumLabel() {
    if (this.m_canNumLabel == null) {
      this.m_canNumLabel = new UILabel();
      this.m_canNumLabel.setName("canNum");
    }
    return this.m_canNumLabel;
  }

  private Component getPreceiveBalanceMnyLabel() {
    if (this.m_preceiveBalanceMnyLabel == null) {
      this.m_preceiveBalanceMnyLabel = new UILabel();
      this.m_preceiveBalanceMnyLabel.setName("preceiveBalanceMny");
    }
    return this.m_preceiveBalanceMnyLabel;
  }

  private void initUI() {
    GridLayout gl = new GridLayout(0, 2);
    this.setLayout(gl);
    UILabel title1 =
        new UILabel(NCLangRes.getInstance().getStrByID("4006011_0",
            "04006011-0279")/*客户信用：*/);
    title1.setForeground(Color.BLUE);
    this.add(title1);
    this.add(new UILabel(""));

    this.add(new UILabel(NCLangRes.getInstance().getStrByID("4006011_0",
        "04006011-0265")/*信用额度*/));
    this.add(new UILabel("1000"));

    this.add(new UILabel(NCLangRes.getInstance().getStrByID("4006011_0",
        "04006011-0266")/*信用占用*/));
    this.add(new UILabel("800"));

    this.add(new UILabel(NCLangRes.getInstance().getStrByID("4006011_0",
        "04006011-0267")/*信用余额*/));
    this.add(new UILabel("200"));

    this.add(new UILabel("__________"));

    this.add(new UILabel(""));

    UILabel title2 =
        new UILabel(NCLangRes.getInstance().getStrByID("4006011_0",
            "04006011-0280")/*客户预收款：*/);
    title2.setForeground(Color.BLUE);
    this.add(title2);
    this.add(new UILabel(""));

    this.add(new UILabel(NCLangRes.getInstance().getStrByID("4006011_0",
        "04006011-0281")/*客户预收款余额*/));
    this.add(this.getPreceiveBalanceMnyLabel());

    this.add(new UILabel("__________"));

    this.add(new UILabel(""));

    UILabel title3 =
        new UILabel(NCLangRes.getInstance().getStrByID("4006011_0",
            "04006011-0282")/*存量：*/);
    title3.setForeground(Color.BLUE);
    this.add(title3);
    this.add(new UILabel(""));

    this.add(new UILabel(NCLangRes.getInstance().getStrByID("4006011_0",
        "04006011-0261")/*现存量*/));
    this.add(this.getOnhandNumLabel());

    this.add(new UILabel(NCLangRes.getInstance().getStrByID("4006011_0",
        "04006011-0262")/*可用量*/));
    this.add(this.getCanNumLabel());

  }

}
