package nc.ui.so.tranmatrel.model;

import nc.bs.framework.common.NCLocator;
import nc.itf.so.tranmatrel.ITranMatRelMaintain;
import nc.ui.pubapp.uif2app.model.IQueryService;
import nc.ui.uif2.model.IAppModelService;
import nc.vo.pub.BusinessException;
import nc.vo.pubapp.pattern.exception.ExceptionUtils;
import nc.vo.so.tranmatrel.entity.TranMatRelVO;
import nc.vo.uif2.LoginContext;

public class TranMatRelModelService implements IAppModelService, IQueryService {

  /**
   * 删除
   */
  @Override
  public void delete(Object object) throws Exception {
    ITranMatRelMaintain service =
        NCLocator.getInstance().lookup(ITranMatRelMaintain.class);
    try {
      service.delete((TranMatRelVO) object);
    }
    catch (BusinessException e) {
      ExceptionUtils.wrappException(e);
    }
  }

  /**
   * 新增
   */
  @Override
  public TranMatRelVO insert(Object object) throws Exception {
    ITranMatRelMaintain service =
        NCLocator.getInstance().lookup(ITranMatRelMaintain.class);
    TranMatRelVO retvo = null;
    try {
      retvo = service.insert((TranMatRelVO) object);
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
  public TranMatRelVO update(Object object) throws Exception {
    ITranMatRelMaintain service =
        NCLocator.getInstance().lookup(ITranMatRelMaintain.class);
    TranMatRelVO retvo = null;
    try {
      retvo = service.update((TranMatRelVO) object);
    }
    catch (BusinessException e) {
      ExceptionUtils.wrappException(e);
    }
    return retvo;
  }

  @Override
  public TranMatRelVO[] queryByDataVisibilitySetting(LoginContext context)
      throws Exception {
    TranMatRelVO[] rets = null;
    ITranMatRelMaintain service =
        NCLocator.getInstance().lookup(ITranMatRelMaintain.class);
    try {
      TranMatRelVO vo = service.queryByOrg(context.getPk_org());
      if (vo != null) {
        rets = new TranMatRelVO[] {
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
  public TranMatRelVO[] queryByWhereSql(String whereSql) throws Exception {
    TranMatRelVO[] rets = null;
    return rets;
  }

}
