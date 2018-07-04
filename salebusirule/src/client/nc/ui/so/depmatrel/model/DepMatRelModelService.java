package nc.ui.so.depmatrel.model;

import nc.bs.framework.common.NCLocator;
import nc.itf.so.depmatrel.IDepMatRelMaintain;
import nc.ui.pubapp.uif2app.model.IQueryService;
import nc.ui.uif2.model.IAppModelService;
import nc.vo.pub.BusinessException;
import nc.vo.pubapp.pattern.exception.ExceptionUtils;
import nc.vo.so.depmatrel.entity.DepMatRelVO;
import nc.vo.uif2.LoginContext;

public class DepMatRelModelService implements IAppModelService, IQueryService {

  /**
   * 删除
   */
  @Override
  public void delete(Object object) throws Exception {
    IDepMatRelMaintain service =
        NCLocator.getInstance().lookup(IDepMatRelMaintain.class);
    try {
      service.delete((DepMatRelVO) object);
    }
    catch (BusinessException e) {
      ExceptionUtils.wrappException(e);
    }
  }

  /**
   * 新增
   */
  @Override
  public DepMatRelVO insert(Object object) throws Exception {
    IDepMatRelMaintain service =
        NCLocator.getInstance().lookup(IDepMatRelMaintain.class);
    DepMatRelVO retvo = null;
    try {
      retvo = service.insert((DepMatRelVO) object);
    }
    catch (BusinessException e) {
      ExceptionUtils.wrappException(e);
    }
    return retvo;
  }

  /**
   * 修改保存
   */
  @Override
  public DepMatRelVO update(Object object) throws Exception {
    IDepMatRelMaintain service =
        NCLocator.getInstance().lookup(IDepMatRelMaintain.class);
    DepMatRelVO retvo = null;
    try {
      retvo = service.update((DepMatRelVO) object);
    }
    catch (BusinessException e) {
      ExceptionUtils.wrappException(e);
    }
    return retvo;
  }

  @Override
  public DepMatRelVO[] queryByDataVisibilitySetting(LoginContext context)
      throws Exception {
    DepMatRelVO[] rets = null;
    IDepMatRelMaintain service =
        NCLocator.getInstance().lookup(IDepMatRelMaintain.class);
    try {
      DepMatRelVO vo = service.queryByOrg(context.getPk_org());
      if (vo != null) {
        rets = new DepMatRelVO[] {
          vo
        };
      }
    }
    catch (BusinessException e) {
      ExceptionUtils.wrappException(e);
    }
    return rets;
  }

  /**
   * 根据where条件查询
   * 
   * @param whereSql
   * @return
   * @throws Exception
   */
  @Override
  public DepMatRelVO[] queryByWhereSql(String whereSql) throws Exception {
    DepMatRelVO[] rets = null;
    return rets;
  }

}
