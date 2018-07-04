package nc.ui.so.mreturnpolicy.ref;

import nc.vo.uif2.LoginContext;

import nc.ui.ml.NCLangRes;

/**
 * 此处插入类型说明。 创建日期：(2004-3-11 9:54:53)
 * 
 * @author：左小军
 */
public class FomulaModel extends nc.ui.pub.formulaset.FormulaEditorModel {

  private ReturncndtnRefPane m_RefPane;

  /**
   * FomulaModel 构造子注解。
   */
  public FomulaModel(LoginContext context) {
    super();
    this.initModel(context);
  }

  /**
   * 此处插入方法说明。 创建日期：(2004-3-11 11:15:15)
   * 
   * @return nc.ui.so.so141.ReturncndtnRefPane
   */
  public ReturncndtnRefPane getRefPane(LoginContext context) {
    if (this.m_RefPane == null) {
      this.m_RefPane = new ReturncndtnRefPane(context);
    }
    return this.m_RefPane;
  }

  /**
   * 此处插入方法说明。 创建日期：(2004-3-11 11:15:15)
   * 
   * @param newRefPane
   *          nc.ui.so.so141.ReturncndtnRefPane
   */
  public void setRefPane(ReturncndtnRefPane newRefPane) {
    this.m_RefPane = newRefPane;
  }

  /**
   * 此处插入方法说明。 创建日期：(2002-3-26 16:03:41)
   */
  private void initModel(LoginContext context) {
    this.setBusinessFunc(new Object[][] {
      {
        "judge()",
        this.getRefPane(context),
        NCLangRes.getInstance().getStrByID("4006006_0", "04006006-0110")/*退货条件参照*/,
        NCLangRes.getInstance().getStrByID("4006006_0", "04006006-0111")
      /*判断退货条件*/
      }
    });
    this.setInputControl(false);
  }
}
