package nc.pubimpl.so.pub.api;

import nc.pubitf.so.pub.api.ISOQueryAPI;
import nc.ui.querytemplate.querytree.IQueryScheme;
import nc.vo.pub.BusinessException;
import nc.vo.pubapp.pattern.exception.ExceptionUtils;

/**
 * @description
 * 销售管理查询api抽象类
 * @scene
 *
 * @param
 *
 *
 * @since 6.5
 * @version 2015-11-9 上午9:40:01
 * @author 刘景
 */
public abstract class SOQueryAPI {

  public abstract ISOQueryAPI getService();

  public abstract String[] getQueryFields();

  public abstract String[] getBids();

  public abstract String[] getIds();

  public abstract String[] getSourceBIDs();

  public abstract IQueryScheme getQueryscheme();

  public void queryViewVOByBIDs() {
    try {
      getService().queryViewVOByBIDs(getBids());

      getService().queryViewVOByBIDs(getBids(), getQueryFields());
    }
    catch (BusinessException e) {
      ExceptionUtils.wrappException(e);
    }
  }

  public void queryVOByIDs() {

    try {

      getService().queryVOByIDs(getIds());

      getService().queryVOByIDs(getIds(), getQueryFields());
    }
    catch (BusinessException e) {
      ExceptionUtils.wrappException(e);
    }

  }

  public void queryViewVOByScheme() {

    try {

      getService().queryViewVOByScheme(getQueryscheme());

      getService().queryViewVOByScheme(getQueryscheme(), getQueryFields());
    }
    catch (BusinessException e) {
      ExceptionUtils.wrappException(e);
    }

  }

  public void queryVOByScheme() {

    try {

      getService().queryVOByScheme(getQueryscheme());

      getService().queryVOByScheme(getQueryscheme(), getQueryFields());
    }
    catch (BusinessException e) {
      ExceptionUtils.wrappException(e);
    }

  }

  public void queryViewVOBySourceBIDs() {

    try {

      getService().queryViewVOBySourceBIDs(getSourceBIDs());

      getService().queryViewVOBySourceBIDs(getSourceBIDs(), getQueryFields());
    }
    catch (BusinessException e) {
      ExceptionUtils.wrappException(e);
    }

  }

}
