package nc.ui.so.m4331.billref.m4804;

import java.awt.Container;

import nc.ui.pub.pf.BillSourceVar;
import nc.ui.pubapp.billref.src.view.SourceRefDlg;

public class SourceRefDlgFor4804 extends SourceRefDlg {
  /**
     * 
     */
  private static final long serialVersionUID = -4385434936229999968L;

  public SourceRefDlgFor4804(Container parent, BillSourceVar bsVar) {
	  super(parent, bsVar, true);
  }

  /**
   * 父类方法重写
   * 
   * @see nc.ui.pubapp.billref.src.view.SourceRefDlg#getRefBillInfoBeanPath()
   */
  @Override
  public String getRefBillInfoBeanPath() {
    return "nc/ui/so/m4331/billref/m4804/M4804RefInfo.xml";
  }

}
