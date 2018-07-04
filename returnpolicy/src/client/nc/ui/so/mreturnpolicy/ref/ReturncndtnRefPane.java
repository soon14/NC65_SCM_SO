package nc.ui.so.mreturnpolicy.ref;

import nc.ui.ml.NCLangRes;
import nc.vo.uif2.LoginContext;

/**
 * 此处插入类型说明。 创建日期：(2004-3-9 15:36:00)
 * 
 * @author：左小军
 */
public class ReturncndtnRefPane extends nc.ui.pub.beans.UIRefPane implements
    IRefReturn {
  /**
   * 
   */
  private static final long serialVersionUID = 1L;

  /**
   * FomulaEditRefPane 构造子注解。
   */
  private LoginContext context;

  /**
   * FomulaEditRefPane 构造子注解。
   * 
   * @param p0
   *          boolean
   */
  public ReturncndtnRefPane(boolean p0) {
    super(p0);
    this.initialize();
  }

  /**
   * FomulaEditRefPane 构造子注解。
   * 
   * @param parent
   *          java.awt.Container
   */
  public ReturncndtnRefPane(java.awt.Container parent) {
    super(parent);
    this.initialize();
  }

  /**
   * FomulaEditRefPane 构造子注解。
   * 
   * @param p0
   *          java.awt.LayoutManager
   */
  public ReturncndtnRefPane(java.awt.LayoutManager p0) {
    super(p0);
  }

  /**
   * FomulaEditRefPane 构造子注解。
   * 
   * @param p0
   *          java.awt.LayoutManager
   * @param p1
   *          boolean
   */
  public ReturncndtnRefPane(java.awt.LayoutManager p0, boolean p1) {
    super(p0, p1);
    this.initialize();
  }

  public ReturncndtnRefPane(LoginContext context) {
    super();
    this.context = context;
    this.initialize();
  }

  /**
   * 此处插入方法说明。 创建日期：(2002-3-25 17:32:49)
   * 
   * @return java.lang.String
   */
  @Override
  public String getNameByCode(String codee) {
    return null;
  }

  /**
   * ?user> 功能： 参数： 返回： 例外： 日期：(2004-7-1 8:34:59) 修改日期，修改人，修改原因，注释标志：
   * 
   * @return java.lang.String
   */
  @Override
  public java.lang.String getRefReturnCode() {
    String sReturn = this.getRefModel().getRefCodeValue();
    sReturn = "judge(\"" + sReturn + "\")";
    return sReturn;
  }

  /**
   * ?user> 功能： 参数： 返回： 例外： 日期：(2004-7-1 8:34:59) 修改日期，修改人，修改原因，注释标志：
   * 
   * @return java.lang.String
   */
  @Override
  public java.lang.String getRefReturnName() {
    String sReturn = this.getRefModel().getRefCodeValue();
    sReturn = NCLangRes.getInstance().getStrByID("4006006_0", "04006006-0121", null, new String[]{sReturn})/*判断退货条件("{0}")*/;
    return sReturn;
  }

  /**
   * 此处插入方法说明。 创建日期：(2002-3-25 17:32:49)
   * 
   * @return java.lang.String
   */
  public String getRefString() {
    this.getRefName();
    String strRefCode = this.getRefCode();
    if (strRefCode == null) {
      return "";
    }
    return "\"" + strRefCode + "\"";
  }

  /**
   * ?user> 功能： 参数： 返回： 例外： 日期：(2004-7-1 9:07:11) 修改日期，修改人，修改原因，注释标志：
   * 
   * @return int
   */
  @Override
  public int showModal() {
    /*
     * onButtonClicked(); return getReturnButtonCode()
     */
    super.showModel();
    return this.getReturnButtonCode();
  }

  private void initialize() {
    String strPk_corp = this.context.getPk_group();
    ReturncndtnRefModel model = new ReturncndtnRefModel();
    model.setPk_group(strPk_corp);
    this.setRefModel(model);
    this.getUITextField().setEditable(false);
  }
}
