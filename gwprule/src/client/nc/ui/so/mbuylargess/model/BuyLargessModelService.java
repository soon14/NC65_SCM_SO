package nc.ui.so.mbuylargess.model;

import nc.bs.framework.common.NCLocator;
import nc.itf.so.mbuylagress.IBuyLargessMaintain;
import nc.ui.pubapp.uif2app.query2.model.IQueryService;
import nc.ui.querytemplate.querytree.IQueryScheme;
import nc.ui.uif2.model.IAppModelService;
import nc.vo.pub.BusinessException;
import nc.vo.pubapp.pattern.exception.ExceptionUtils;
import nc.vo.pubapp.pattern.model.transfer.bill.ClientBillCombinServer;
import nc.vo.so.mbuylargess.entity.BuyLargessVO;
import nc.vo.uif2.LoginContext;

public class BuyLargessModelService implements IAppModelService, IQueryService {

  private static String beanName = IBuyLargessMaintain.class.getName();

  /**
   * 删除买赠记录
   */
  @Override
  public void delete(Object object) throws Exception {
    IBuyLargessMaintain bo =
        (IBuyLargessMaintain) NCLocator.getInstance().lookup(
            BuyLargessModelService.beanName);
    bo.deleteBuylargess((BuyLargessVO) object);
  }

  /**
   * 新增买赠记录
   */
  @Override
  public BuyLargessVO insert(Object object) throws Exception {
    BuyLargessVO clientbill = (BuyLargessVO) object;
    BuyLargessVO[] vo = new BuyLargessVO[] {
      clientbill
    };
    IBuyLargessMaintain bo =
        (IBuyLargessMaintain) NCLocator.getInstance().lookup(
            BuyLargessModelService.beanName);
    ClientBillCombinServer<BuyLargessVO> util =
        new ClientBillCombinServer<BuyLargessVO>();
    BuyLargessVO ret = bo.insertBuylargess((BuyLargessVO) object);
    BuyLargessVO[] retvo = new BuyLargessVO[] {
      ret
    };
    util.combine(vo, retvo);
    return clientbill;
  }

  @Override
  public Object[] queryByDataVisibilitySetting(LoginContext context)
      throws Exception {
    return null;
  }

  /**
   * 根据where条件查询买赠记录
   * 
   * @param whereSql
   * @return
   * @throws Exception
   */

  @Override
  public Object[] queryByQueryScheme(IQueryScheme queryScheme) throws Exception {
    BuyLargessVO[] retbills = null;
    IBuyLargessMaintain service =
        NCLocator.getInstance().lookup(IBuyLargessMaintain.class);
    try {
      retbills = service.queryBuylargess(queryScheme);
    }
    catch (BusinessException e) {
      ExceptionUtils.wrappException(e);
    }
    return retbills;
  }

  /**
   * 修改保存买赠记录
   */
  @Override
  public Object update(Object object) throws Exception {
    BuyLargessVO clientbill = (BuyLargessVO) object;
    BuyLargessVO[] vo = new BuyLargessVO[] {
      clientbill
    };
    IBuyLargessMaintain bo =
        (IBuyLargessMaintain) NCLocator.getInstance().lookup(
            BuyLargessModelService.beanName);
    ClientBillCombinServer<BuyLargessVO> util =
        new ClientBillCombinServer<BuyLargessVO>();
    BuyLargessVO ret = bo.updateBuylargess((BuyLargessVO) object);
    BuyLargessVO[] retvo = new BuyLargessVO[] {
      ret
    };
    util.combine(vo, retvo);
    return clientbill;
  }
}
