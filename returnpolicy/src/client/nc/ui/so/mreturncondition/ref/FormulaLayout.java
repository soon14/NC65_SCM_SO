package nc.ui.so.mreturncondition.ref;

/**
 * 此处插入类型说明。 创建日期：(2002-11-27 18:50:37)
 * 
 * @author：张春明
 */
import java.awt.Component;
import java.awt.Container;

public class FormulaLayout implements java.awt.LayoutManager {
  /**
   * FormulaLayout 构造子注解。
   */
  public FormulaLayout() {
    super();
  }

  /**
   * Adds the specified component with the specified name to the layout.
   * 
   * @param name
   *          the component name
   * @param comp
   *          the component to be added
   */
  @Override
  public void addLayoutComponent(String name, java.awt.Component comp) {
    // TODO
  }

  /**
   * Lays out the container in the specified panel.
   * 
   * @param parent
   *          the component which needs to be laid out
   */
  @Override
  public void layoutContainer(Container parent) {
    final int iLeft = 5;
    final int iRight = 5;
    final int iTop = 5;
    final int iBottom = 10;
    final int iHei = parent.getSize().height - iTop - iBottom;
    final int iWid = parent.getSize().width - iLeft - iRight;
    final int iVgap = 3;
    final int iTitleHei = 26;
    final int iOperHei = 77;
    final int iBnHei = 25;
    final int iEditorHei =
        (iHei - iTitleHei - iOperHei - iBnHei - 5 * iVgap) / 2;
    if (iEditorHei <= 10) {
      return;
    }
    int iNum = parent.getComponentCount();
    if (iNum <= 0) {
      return;
    }
    Component cp = null;
    for (int i = 0; i < iNum; i++) {
      cp = parent.getComponent(i);
      if (cp.getName().trim().equalsIgnoreCase("PaneTitle")) {
        cp.setBounds(iLeft, iTop, iWid, iTitleHei);
      }
      else if (cp.getName().trim().equalsIgnoreCase("PaneEdit")) {
        cp.setBounds(iLeft, iTop + iTitleHei + iVgap, iWid, iEditorHei);
      }
      else if (cp.getName().trim().equalsIgnoreCase("PaneOper")) {
        cp.setBounds(iLeft, iTop + iTitleHei + iEditorHei + iVgap + iVgap,
            iWid, iOperHei);
      }
      else if (cp.getName().trim().equalsIgnoreCase("PaneExplain")) {
        cp.setBounds(iLeft, iTop + iTitleHei + iEditorHei + iVgap * 3
            + iOperHei, iWid, iEditorHei);
      }
      else if (cp.getName().trim().equalsIgnoreCase("PaneBnShow")) {
        cp.setBounds(iLeft, iTop + iTitleHei + iOperHei + iEditorHei * 2
            + iVgap * 4, iWid, iBnHei);
      }
      else if (cp.getName().trim().equalsIgnoreCase("PaneShow")) {
        cp.setBounds(iLeft, iTop + iTitleHei + iEditorHei * 2 + iBnHei
            + iOperHei + iVgap * 5, iWid, iEditorHei);
      }
    }
  }

  /**
   * Calculates the minimum size dimensions for the specified panel given the
   * components in the specified parent container.
   * 
   * @param parent
   *          the component to be laid out
   * @see #preferredLayoutSize
   */
  @Override
  public java.awt.Dimension minimumLayoutSize(java.awt.Container parent) {
    return new java.awt.Dimension(613, 344);
  }

  /**
   * Calculates the preferred size dimensions for the specified panel given the
   * components in the specified parent container.
   * 
   * @param parent
   *          the component to be laid out
   * @see #minimumLayoutSize
   */
  @Override
  public java.awt.Dimension preferredLayoutSize(java.awt.Container parent) {
    return new java.awt.Dimension(613, 344);
  }

  /**
   * Removes the specified component from the layout.
   * 
   * @param comp
   *          the component to be removed
   */
  @Override
  public void removeLayoutComponent(java.awt.Component comp) {
    // TODO
  }
}
