package nc.ui.so.m30.billui.view.sideform;

import java.awt.BorderLayout;
import java.awt.Component;
import java.awt.Dimension;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.GridLayout;
import java.awt.Insets;
import java.awt.LayoutManager;
import java.util.Map;

import javax.swing.BorderFactory;
import javax.swing.border.Border;

import nc.vo.pub.BusinessException;
import nc.vo.pub.lang.UFDouble;
import nc.vo.pubapp.AppContext;
import nc.vo.pubapp.pattern.exception.ExceptionUtils;
import nc.vo.pubapp.scale.ScaleUtils;
import nc.vo.so.m30.entity.SaleOrderHVO;
import nc.vo.so.m30.sobalance.util.AbstractGatheringKeyValue;
import nc.vo.so.m30.sobalance.util.GatherbillUtil;
import nc.vo.so.m30.sobalance.util.GatheringKeyValueForSaleOrder;
import nc.vo.so.m30.util.OffsetItfVOUtil;
import nc.vo.so.m35.paravo.OffsetParaVO;
import nc.vo.so.pub.keyvalue.IKeyValue;
import nc.vo.so.pub.util.SOMilSymbolUtil;
import nc.itf.so.m30.ref.arap.mf2.ARmF2ServicesUtil;
import nc.pubitf.so.m35.so.m30.IArsubToSaleorder;
import nc.bs.framework.common.NCLocator;
import nc.ui.ml.NCLangRes;
import nc.ui.pub.beans.UILabel;
import nc.ui.pub.beans.UIPanel;
import nc.ui.queryarea.component.QueryAreaHyperlinkButton;
import nc.ui.so.m30.billui.view.SaleOrderBillForm;
import nc.ui.so.pub.keyvalue.CardKeyValue;
import nc.ui.uif2.IExceptionHandler;
import nc.ui.uif2.components.widget.IBesideWidgetlet;
import nc.ui.uif2.components.widget.WidgetInfo;

public class PaySideForm extends UIPanel implements IBesideWidgetlet {

  private static final String CUSTPAYVALUE = "CustPayValue";

  private static final String OFFSETVALUE = "OffsetValue";

  private static final long serialVersionUID = 8802096116443456484L;

  private IExceptionHandler exceptionHandler;

  // 预收款余额
  private UIPanel custPayValuePanel;

  private UILabel custPayValueLabel;

  // 费用预额
  private UIPanel offsetValuePanel;

  private UILabel offsetValueLabel;

  // 订单卡片
  private SaleOrderBillForm billForm;

  // 用于resetUI刷新时重新填充初始的layout
  private LayoutManager defaultLayout;

  public PaySideForm() {
    super();
    this.initUI();
    this.defaultLayout = this.getLayout();
  }

  public void display() {
    this.removeAll();
    this.notifyUpdateData();
  }

  public void notifyUpdateData() {
    this.setLayout(new BorderLayout());
    this.initBorder();

    // 设置显示值
    Component[] parent = this.getComponents();
    UIPanel uipanel = (UIPanel) parent[0];
    Component[] cps = uipanel.getComponents();

    UFDouble custPayValue = this.getCanVerifyMny();
    UFDouble offsetValue = this.getCanOffsetMny();
    CardKeyValue keyValue = new CardKeyValue(this.billForm.getBillCardPanel());
    String corigcurrencyid =
        keyValue.getHeadStringValue(SaleOrderHVO.CORIGCURRENCYID);
    for (Component cp : cps) {
      if ("custPayValuePanel".equals(cp.getName())) {
        UILabel label = (UILabel) this.getCustPayValueLabel();
        label.setText(this.getFormatData(corigcurrencyid, custPayValue));
      }
      else if ("offsetValuePanel".equals(cp.getName())) {
        UILabel label = (UILabel) this.getOffsetValueLabel();
        label.setText(this.getFormatData(corigcurrencyid, offsetValue));
      }
    }
    this.updateUI();

  }

  private String getFormatData(String corigcurrencyid, UFDouble creditValue) {
    UFDouble newcreditValue = new UFDouble();
    ScaleUtils scaleutil =
        new ScaleUtils(AppContext.getInstance().getPkGroup());
    newcreditValue = scaleutil.adjustMnyScale(creditValue, corigcurrencyid);
    // 数据格式处理
    return SOMilSymbolUtil.setNumFormat(newcreditValue.toString());
  }

  private UFDouble getCanOffsetMny() {
    UFDouble ret = UFDouble.ZERO_DBL;
    CardKeyValue keyValue = new CardKeyValue(this.billForm.getBillCardPanel());
    String pk_group = keyValue.getHeadStringValue(SaleOrderHVO.PK_GROUP);
    OffsetItfVOUtil util = new OffsetItfVOUtil(keyValue);
    Map<Integer, OffsetParaVO> offsetparamap = util.getinterfaceData();

    // 没有可冲抵的表体行
    if (offsetparamap.size() == 0) {
      return ret;
    }
    IArsubToSaleorder service =
        NCLocator.getInstance().lookup(IArsubToSaleorder.class);
    try {
      ret = service.getCanOffsetMny(pk_group, offsetparamap);
    }
    catch (BusinessException e) {
      ExceptionUtils.wrappException(e);
    }
    return ret;
  }

  private UFDouble getCanVerifyMny() {
    UFDouble ret = null;
    IKeyValue keyValue = new CardKeyValue(this.billForm.getBillCardPanel());
    AbstractGatheringKeyValue gatheringKeyValue =
        new GatheringKeyValueForSaleOrder(keyValue);
    String wherePartSql =
        GatherbillUtil.getInstance()
            .getWherePartSqlCanVerify(gatheringKeyValue);
    /**
     * 增加对于合同号的限制
     * 目的为查询该合同号、币种、组织、客户下的数值
     * @author 王梓懿
     * @time 2017-11-15
     */
    String wherePartContainContract=addContractLimit(wherePartSql,keyValue.getHeadStringValue("vbillcode"));
    try {
      ret =
          ARmF2ServicesUtil.queryGatheringBillMoneyItemCanVerify(wherePartContainContract);
    }
    catch (BusinessException e) {
      ExceptionUtils.wrappException(e);
    }
    return ret == null ? UFDouble.ZERO_DBL : ret;
  }

  /** 
 * @Title: addContractLimit 
 * @Description: TODO
 * @param wherePartSql
 * @param string 
 * @return
 * @return: String
 */
private String addContractLimit(String wherePartSql, String contractNo) {
	// TODO 自动生成的方法存根
	
	return wherePartSql+" and contractno='"+contractNo+"'";
}

private void initBorder() {
    UIPanel panel = new UIPanel();
    panel.setLayout(new GridLayout(2, 1));

    // 设置label中内容的布局
    GridBagConstraints constraints = new GridBagConstraints();
    constraints.weightx = 100;
    constraints.weighty = 100;
    constraints.gridx = 0;
    constraints.gridy = 2;
    constraints.anchor = GridBagConstraints.EAST;
    // 初始化边框对象并为每个panel设置边框
    Border etched = BorderFactory.createEtchedBorder();
    Border custPayValueTitled =
        BorderFactory.createTitledBorder(etched, NCLangRes.getInstance()
            .getStrByID("4006011_0", "04006011-0271")/*预收款余额*/);
    this.getCustPayValuePanel().add(this.getCustPayValueLabel(), constraints);
    this.getCustPayValuePanel().setBorder(custPayValueTitled);

    Border offsetValueTitled =
        BorderFactory.createTitledBorder(etched, NCLangRes.getInstance()
            .getStrByID("4006011_0", "04006011-0272")/*费用余额*/);
    this.getOffsetValuePanel().add(this.getOffsetValueLabel(), constraints);
    this.getOffsetValuePanel().setBorder(offsetValueTitled);

    panel.add(this.getCustPayValuePanel());
    panel.add(this.getOffsetValuePanel());
    this.add(panel);
  }

  public UIPanel getCustPayValuePanel() {
    if (this.custPayValuePanel == null) {
      this.custPayValuePanel = new UIPanel(new GridBagLayout());
      this.custPayValuePanel.setName("custPayValuePanel");
    }
    return this.custPayValuePanel;
  }

  public UIPanel getOffsetValuePanel() {
    if (this.offsetValuePanel == null) {
      this.offsetValuePanel = new UIPanel(new GridBagLayout());
      this.offsetValuePanel.setName("offsetValuePanel");
    }
    return this.offsetValuePanel;
  }

  private Component getCustPayValueLabel() {
    if (this.custPayValueLabel == null) {
      this.custPayValueLabel = new UILabel();
      this.custPayValueLabel.setName(PaySideForm.CUSTPAYVALUE);
    }
    return this.custPayValueLabel;
  }

  private Component getOffsetValueLabel() {
    if (this.offsetValueLabel == null) {
      this.offsetValueLabel = new UILabel();
      this.offsetValueLabel.setName(PaySideForm.OFFSETVALUE);
    }
    return this.offsetValueLabel;
  }

  private void initUI() {
    this.setPreferredSize(new Dimension(80, 80));
    this.add(new QueryAreaHyperlinkButton(new PaySideFormAction(this,
        this.exceptionHandler)));
    this.updateUI();
  }

  public void resetUI() {
    this.removeAll();
    this.setLayout(this.defaultLayout);
    this.initUI();
  }

  @Override
  public WidgetInfo getWidgetInfo() {
    WidgetInfo info = new WidgetInfo();
    info.setTitle(NCLangRes.getInstance().getStrByID("4006011_0",
        "04006011-0273")/*客户预收款*/);
    return info;
  }

  @Override
  public Component getContentComponent() {
    return this;
  }

  @Override
  public Insets getComponentInsets() {
    return null;
  }

  @Override
  public void setWidgetState(int state) {
    //
  }

  public SaleOrderBillForm getBillForm() {
    return this.billForm;
  }

  public void setBillForm(SaleOrderBillForm billForm) {
    this.billForm = billForm;
  }

  public IExceptionHandler getExceptionHandler() {
    return this.exceptionHandler;
  }

  public void setExceptionHandler(IExceptionHandler exceptionHandler) {
    this.exceptionHandler = exceptionHandler;
  }
}
