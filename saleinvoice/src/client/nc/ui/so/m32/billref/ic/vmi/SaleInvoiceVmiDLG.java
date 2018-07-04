package nc.ui.so.m32.billref.ic.vmi;

import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.List;

import javax.swing.JPanel;
import javax.swing.WindowConstants;

import nc.vo.pub.BusinessException;
import nc.vo.pubapp.AppContext;
import nc.vo.pubapp.pattern.exception.ExceptionUtils;
import nc.vo.pubapp.pattern.pub.PubAppTool;
import nc.vo.pubapp.pattern.pub.SqlBuilder;
import nc.vo.pubapp.query2.sql.process.QuerySchemeProcessor;
import nc.vo.so.m32.entity.SaleInvoiceHVO;
import nc.vo.so.m32.entity.SaleInvoiceViewVO;
import nc.vo.so.pub.enumeration.BillStatus;

import nc.pubitf.so.m32.ic.m4c.ISaleInvoiceToVmi;

import nc.bs.framework.common.NCLocator;

import nc.desktop.ui.WorkbenchEnvironment;

import nc.ui.ml.NCLangRes;
import nc.ui.pub.beans.MessageDialog;
import nc.ui.pub.beans.UIButton;
import nc.ui.pub.beans.UIDialog;
import nc.ui.pub.beans.UIPanel;
import nc.ui.pub.bill.BillListPanel;
import nc.ui.pub.bill.IBillItem;
import nc.ui.pubapp.uif2app.view.CompositeBillListDataPrepare;
import nc.ui.pubapp.uif2app.view.material.assistant.MarAsstPreparator;
import nc.ui.querytemplate.querytree.IQueryScheme;
import nc.ui.so.m32.billui.pub.SaleInvoicePrecision;
import nc.ui.uif2.editor.QueryTemplateContainer;
import nc.ui.uif2.editor.UserdefQueryParam;
import nc.ui.uif2.editor.UserdefitemContainerListPreparator;
import nc.ui.uif2.userdefitem.QueryParam;
import nc.ui.uif2.userdefitem.UserDefItemContainer;

public class SaleInvoiceVmiDLG extends UIDialog {

  class ActionHandler implements ActionListener {

    @Override
    public void actionPerformed(ActionEvent e) {
      if (e.getSource() == SaleInvoiceVmiDLG.this.getbtnQuery()) {
        SaleInvoiceVmiDLG.this.doQueryAction();
      }
      else if (e.getSource() == SaleInvoiceVmiDLG.this.getbtnOK()) {
        SaleInvoiceVmiDLG.this.doOkAction();
      }
      else if (e.getSource() == SaleInvoiceVmiDLG.this.getbtnCancel()) {
        SaleInvoiceVmiDLG.this.doCancelAction();
      }
    }
  }

  private static final int DEFAULTHIGH = 700;

  private static final int DEFAULTWITH = 900;

  private static final int DENOMINATOR = 8;

  private static final int MOLECULE = 7;

  /**
   *
   */
  private static final long serialVersionUID = 2006914098648533017L;

  // 按钮响应事件
  private ActionHandler actionHandler = new ActionHandler();

  // 取消按钮
  private UIButton btnCancel;

  // 确定按钮
  private UIButton btnOK;

  // 查询按钮
  private UIButton btnQuery;

  private UIPanel btnUIPanel;

  private InvoiceToVmiQueryDlgContainer dlgcontainer;

  private BillListPanel invoicePanel;

  private SaleInvoiceViewVO[] selectedVOs;

  /** 查询模板容器 */
  private QueryTemplateContainer templateContainer;

  // private QueryConditionDLG queryDlg;

  private JPanel uiContentPane;

  private CompositeBillListDataPrepare userlistdata;

  public SaleInvoiceVmiDLG(InvoiceToVmiQueryDlgContainer dlgcontainer) {

    super(WorkbenchEnvironment.getInstance().getWorkbench());

    this.dlgcontainer = dlgcontainer;

    this.initialize();
  }

  public SaleInvoiceViewVO[] getSelectedVOs() {
    return this.selectedVOs;
  }

  public QueryTemplateContainer getTemplateContainer() {
    return this.templateContainer;
  }

  public CompositeBillListDataPrepare getUserlistdata() {
    if (null == this.userlistdata) {
      UserDefItemContainer container = this.getUserDefContainer();
      // 列表自定义项
      UserdefitemContainerListPreparator userdefitem =
          this.getUserDefItem(container);

      MarAsstPreparator marAsstPreparator =
          this.getMarAsstPrepatator(container);

      this.userlistdata = new CompositeBillListDataPrepare();
      this.userlistdata.addBillListDataPrepares(userdefitem);
      this.userlistdata.addBillListDataPrepares(marAsstPreparator);
    }

    return this.userlistdata;
  }

  public void setTemplateContainer(QueryTemplateContainer templateContainer) {
    this.templateContainer = templateContainer;
  }

  public void setUserlistdata(CompositeBillListDataPrepare userlistdata) {
    this.userlistdata = userlistdata;
  }

  /**
   * 方法功能描述：关闭事件处理。
   * <p>
   * <b>参数说明</b>
   * <p>
   * 
   * @author fengjb
   * @time 2010-6-9 上午10:11:02
   */
  void doCancelAction() {
    this.closeCancel();
  }

  /**
   * 方法功能描述：确定事件。
   * <p>
   * <b>参数说明</b>
   * 
   * @param e
   *          <p>
   * @author fengjb
   * @time 2010-6-9 上午10:09:41
   */
  void doOkAction() {
    SaleInvoiceViewVO[] viewvos =
        (SaleInvoiceViewVO[]) this.getInvoicePanel().getBodyBillModel()
            .getBodySelectedVOs(SaleInvoiceViewVO.class.getName());
    if (viewvos == null || viewvos.length == 0) {
      MessageDialog
          .showErrorDlg(
              this,
              nc.vo.ml.NCLangRes4VoTransl.getNCLangRes().getStrByID(
                  "4006008_0", "04006008-0000")/*@res "错误"*/,
              nc.vo.ml.NCLangRes4VoTransl.getNCLangRes().getStrByID(
                  "4006008_0", "04006008-0001")/*@res "请选择单据！"*/);
      return;
    }

    this.setSelectedVO(viewvos);
    this.closeOK();
  }

  /**
   * 方法功能描述：查询事件处理。
   * <p>
   * <b>参数说明</b>
   * 
   * @param e
   *          <p>
   * @author fengjb
   * @time 2010-6-9 上午09:55:13
   */
  void doQueryAction() {

    if (UIDialog.ID_OK == this.dlgcontainer.getQryCondDLGDelegator()
        .showModal()) {
      this.queryAndLoadInvoice();
    }
  }

  /**
   * 返回 btnClose 特性值。
   */
  UIButton getbtnCancel() {
    if (null == this.btnCancel) {
      this.btnCancel = new UIButton();
      this.btnCancel.setName("btnCancel");
      this.btnCancel.setText(NCLangRes.getInstance().getStrByID("4006008_0",
          "04006008-0070")/*取消*/);
    }
    return this.btnCancel;
  }

  /**
   * 返回 btnOK 特性值。
   */
  UIButton getbtnOK() {
    if (null == this.btnOK) {
      this.btnOK = new UIButton();
      this.btnOK.setName("btnOK");
      this.btnOK.setText(NCLangRes.getInstance().getStrByID("4006008_0",
          "04006008-0071")/*确 定*/);
    }
    return this.btnOK;
  }

  /**
   * 返回 btnQuery 特性值。
   */
  UIButton getbtnQuery() {
    if (null == this.btnQuery) {

      this.btnQuery = new UIButton();
      this.btnQuery.setName("btnQuery");
      this.btnQuery.setText(NCLangRes.getInstance().getStrByID("4006008_0",
          "04006008-0072")/*查 询*/);
    }
    return this.btnQuery;
  }

  /**
   * 方法功能描述：添加按钮监听。
   * <p>
   * <b>参数说明</b>
   * <p>
   * 
   * @author fengjb
   * @time 2010-6-9 上午10:15:31
   */
  private void addActionListener() {
    this.getbtnQuery().addActionListener(this.actionHandler);
    this.getbtnOK().addActionListener(this.actionHandler);
    this.getbtnCancel().addActionListener(this.actionHandler);
  }

  /**
   * 方法功能描述：返回 btnUIPanel特性值。
   * <p>
   * <b>参数说明</b>
   * 
   * @return <p>
   * @author fengjb
   * @time 2010-6-9 上午10:14:51
   */
  private UIPanel getBtnUIPanel() {
    if (this.btnUIPanel == null) {
      this.btnUIPanel = new nc.ui.pub.beans.UIPanel();
      this.btnUIPanel.setName("BtnUIPanel");
      this.btnUIPanel.add(this.getbtnQuery(), this.getbtnQuery().getName());
      this.btnUIPanel.add(this.getbtnOK(), this.getbtnOK().getName());
      this.btnUIPanel.add(this.getbtnCancel(), this.getbtnCancel().getName());
      this.btnUIPanel.setLayout(new FlowLayout(FlowLayout.RIGHT));
    }
    return this.btnUIPanel;
  }

  private String getDefaultWhereSql(String headtableName, String bodytablename) {

    SqlBuilder defsql = new SqlBuilder();

    defsql.append(headtableName + "." + SaleInvoiceHVO.FSTATUSFLAG,
        (Integer) BillStatus.AUDIT.value());
    defsql.append(" and ");
    defsql.append(headtableName + "." + SaleInvoiceHVO.PK_GROUP, AppContext
        .getInstance().getPkGroup());
    defsql.append("and ");
    defsql.appendIDIsNotNull(bodytablename + ".cvmivenderid");
    defsql.append("and ");
    defsql.appendIDIsNull(bodytablename + ".csumid");

    // defsql.append("and ");
    // defsql.appendIDIsNotNull(bodytablename + ".cvendorid");

    return defsql.toString();
  }

  /**
   * 方法功能描述：返回销售发票列表Panel。
   * <p>
   * <b>参数说明</b>
   * 
   * @return <p>
   * @author fengjb
   * @time 2010-6-9 上午10:14:06
   */
  private BillListPanel getInvoicePanel() {

    if (null == this.invoicePanel) {
      this.invoicePanel = new BillListPanel();
      this.invoicePanel.setName("SaleInvoicePanel");
      this.invoicePanel.setToolTipText(NCLangRes.getInstance().getStrByID(
          "4006008_0", "04006008-0073")/*请选择要进行消耗汇总的发票*/);
      String operator = AppContext.getInstance().getPkUser();
      String pk_group = AppContext.getInstance().getPkGroup();
      this.invoicePanel.loadTemplet("40060501", null, operator, pk_group,
          "32toVMI", this.getUserlistdata());

      this.invoicePanel.getHeadTable().setSelectionMode(
          javax.swing.ListSelectionModel.MULTIPLE_INTERVAL_SELECTION);
      this.invoicePanel.setMultiSelect(true);

      this.invoicePanel.setEnabled(false);
    }
    return this.invoicePanel;
  }

  private MarAsstPreparator getMarAsstPrepatator(UserDefItemContainer container) {
    MarAsstPreparator marAsstPreparator = new MarAsstPreparator();
    marAsstPreparator.setContainer(container);
    marAsstPreparator.setMaterialField("cmaterialid");
    marAsstPreparator.setPrefix("vfree");
    marAsstPreparator.setProductorField("cproductorid");
    marAsstPreparator.setProjectField("cprojectid");
    marAsstPreparator.setSupplierField("cvendorid");
    return marAsstPreparator;
  }

  /**
   * 方法功能描述：返回 UIDialogContentPane 特性值
   * <p>
   * <b>参数说明</b>
   * 
   * @return <p>
   * @author fengjb
   * @time 2010-6-9 上午10:14:35
   */
  private javax.swing.JPanel getUIContentPane() {
    if (null == this.uiContentPane) {

      this.uiContentPane = new JPanel();
      this.uiContentPane.setName("UIDialogContentPane");
      this.uiContentPane.setLayout(new BorderLayout());

      this.getUIContentPane().add(this.getInvoicePanel(), "Center");
      this.getUIContentPane().add(this.getBtnUIPanel(), "South");

    }
    return this.uiContentPane;
  }

  /**
   * 合并查询多个用户自定义属性（自定义项）规则的工具类
   * 
   * @return
   */
  private UserDefItemContainer getUserDefContainer() {
    UserDefItemContainer container = new UserDefItemContainer();
    container.setContext(this.dlgcontainer.getContext());
    List<QueryParam> params = new ArrayList<QueryParam>();
    QueryParam param = new QueryParam();
    param.setMdfullname("so.saleinvoice");
    params.add(param);
    param = new QueryParam();
    param.setMdfullname("so.saleinvoice_b");
    params.add(param);
    param = new QueryParam();
    param.setRulecode("materialassistant");
    params.add(param);
    container.setParams(params);
    return container;
  }

  private UserdefitemContainerListPreparator getUserDefItem(
      UserDefItemContainer container) {
    UserdefitemContainerListPreparator userdefitem =
        new UserdefitemContainerListPreparator();
    userdefitem.setContainer(container);
    List<UserdefQueryParam> params = new ArrayList<UserdefQueryParam>();
    UserdefQueryParam param = new UserdefQueryParam();
    param.setMdfullname("so.saleinvoice");
    param.setPos(IBillItem.BODY);
    param.setPrefix("vdef");
    param.setTabcode("saleinvoice");
    params.add(param);
    param = new UserdefQueryParam();
    param.setMdfullname("so.saleinvoice_b");
    param.setPos(IBillItem.BODY);
    param.setPrefix("vbdef");
    param.setTabcode("saleinvoice");
    params.add(param);
    userdefitem.setParams(params);
    return userdefitem;
  }

  /**
   * 初始化类。
   */
  private void initialize() {

    this.setName("SaleInvoiceVmiDLG");
    this.setDefaultCloseOperation(WindowConstants.DISPOSE_ON_CLOSE);
    Dimension dd = Toolkit.getDefaultToolkit().getScreenSize();
    int w = SaleInvoiceVmiDLG.DEFAULTWITH;
    int h = SaleInvoiceVmiDLG.DEFAULTHIGH;
    if (dd != null) {
      w =
          (int) dd.getWidth() * SaleInvoiceVmiDLG.MOLECULE
              / SaleInvoiceVmiDLG.DENOMINATOR;
      h =
          (int) dd.getHeight() * SaleInvoiceVmiDLG.MOLECULE
              / SaleInvoiceVmiDLG.DENOMINATOR;
    }
    w =
        w > SaleInvoiceVmiDLG.DEFAULTWITH || w <= 0 ? SaleInvoiceVmiDLG.DEFAULTWITH
            : w;
    h =
        h > SaleInvoiceVmiDLG.DEFAULTHIGH || h <= 0 ? SaleInvoiceVmiDLG.DEFAULTHIGH
            : h;
    this.setSize(w, h);
    this.setResizable(true);
    this.setTitle(NCLangRes.getInstance().getStrByID("4006008_0",
        "04006008-0074")/*销售发票消耗汇总*/);
    this.setContentPane(this.getUIContentPane());
    // 添加监听
    this.addActionListener();

    // 初始化数据
    this.queryAndLoadInvoice();

  }

  /**
   * 方法功能描述：查询并加载显示销售发票。
   * <p>
   * <b>参数说明</b>
   * <p>
   * 
   * @author fengjb
   * @time 2010-8-17 下午08:33:45
   */
  @SuppressWarnings("restriction")
  private void queryAndLoadInvoice() {
    try {
      this.dlgcontainer.getCondProcessor().fillVmiSumGenerateParam(
          this.dlgcontainer.getQryCondDLGDelegator());
    }
    catch (BusinessException e1) {
      ExceptionUtils.wrappException(e1);
    }
    IQueryScheme queryscheme =
        this.dlgcontainer.getQryCondDLGDelegator().getQueryScheme();
    QuerySchemeProcessor qsp = new QuerySchemeProcessor(queryscheme);
    String btableName =
        qsp.getTableAliasOfAttribute("csaleinvoicebid.csaleorgid");
    String headTableName = qsp.getMainTableAlias();
    if (PubAppTool.isNull(btableName)) {
      btableName = "so_saleinvoice_b";

      qsp.appendFrom(" inner join so_saleinvoice_b so_saleinvoice_b");
      qsp.appendFrom(" on");
      String equalsql =
          headTableName + ".csaleinvoiceid=" + btableName + ".csaleinvoiceid";
      qsp.appendFrom(equalsql);

    }
    String formwhere = qsp.getFinalFromWhere();
    SqlBuilder sql = new SqlBuilder();
    sql.append("select  " + btableName + ".csaleinvoicebid");
    sql.append(formwhere + " and "
        + this.getDefaultWhereSql(headTableName, btableName));

    List<String> trantypes = null;
    try {
      this.dlgcontainer.getCondProcessor().fillVmiSumRule(
          this.dlgcontainer.getQryCondDLGDelegator().getLogicalConds());
      trantypes = this.dlgcontainer.getCondProcessor().getTranTypesFor32();
    }
    catch (BusinessException e1) {
      ExceptionUtils.wrappException(e1);
    }
    if (null != trantypes && trantypes.size() > 0) {
      sql.append(" and ");
      sql.append(headTableName + "." + SaleInvoiceHVO.CTRANTYPEID,
          trantypes.toArray(new String[trantypes.size()]));
    }

    SaleInvoiceViewVO[] bills = null;
    ISaleInvoiceToVmi querysrv =
        NCLocator.getInstance().lookup(ISaleInvoiceToVmi.class);
    try {
      bills = querysrv.queryConsumeInvoice(sql.toString());
    }
    catch (Exception e) {
      ExceptionUtils.wrappException(e);
    }
    // 设置界面精度
    String pk_group = AppContext.getInstance().getPkGroup();
    SaleInvoicePrecision.getInstance().setModelPrecision(pk_group,
        this.getInvoicePanel().getBillListData().getBodyBillModel());
    // 加载数据
    this.getInvoicePanel().getBillListData().setBodyValueVO(bills);

    // 执行显示公式
    this.getInvoicePanel().getBillListData().getBodyBillModel()
        .loadLoadRelationItemValue();
    this.getInvoicePanel().getBillListData().getBodyBillModel()
        .execLoadFormula();

  }

  /**
   * 方法功能描述：设置选中的冲应收单VO。
   * <p>
   * <b>参数说明</b>
   * 
   * @param selectedVOs
   *          <p>
   * @author fengjb
   * @time 2010-6-9 上午10:13:16
   */
  private void setSelectedVO(SaleInvoiceViewVO[] selVOs) {
    this.selectedVOs = selVOs;
  }
}
