package nc.ui.so.pub;

import nc.md.MDBaseQueryFacade;
import nc.md.model.IBean;
import nc.vo.pubapp.pattern.exception.ExceptionUtils;
import nc.vo.util.VisibleUtil;

public class ReturnBaseDocUtils {
  /**
   * 
   * 获得基本档案参照可见性
   * 
   * @param pk_group 集团pk
   * @param pk_org 组织pk
   * @param clazz VO class
   * @return 可见性
   */
  public static String getVisibleForRef(String pk_group, String pk_org,
      Class<?> clazz) {
    String visible = "";
    try {
      IBean ibean =
          MDBaseQueryFacade.getInstance().getBeanByFullClassName(
              clazz.getName());
      visible =
          VisibleUtil.getRefVisibleCondition(pk_group, pk_org, ibean.getID());
    }
    catch (Exception e) {
      ExceptionUtils.wrappException(e);
    }
    return visible;
  }
}
