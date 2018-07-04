package nc.ui.so.custmatrel.action;

import nc.funcnode.ui.action.MenuAction;
import nc.ui.ml.NCLangRes;

/**
 * 导入导出菜单
 * 
 * @since 6.3
 * @version 2013-05-16 09:19:11
 * @author liujingn
 */
public class ExportMenuAction extends MenuAction {

  private static final long serialVersionUID = -7831751651064668284L;

  /**
   * 菜单名称
   */
  public ExportMenuAction() {
    super();
    this.setCode("exportMenu");
    this.setName(NCLangRes.getInstance().getStrByID("4006007_0",
        "04006007-0034"/* @res "导入导出" */
    ));
  }

  @Override
  public boolean isEnabled() {
    return true;
  }
}
