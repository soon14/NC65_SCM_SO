package nc.pubimpl.so.mbuylargess.api;

import nc.bs.framework.common.InvocationInfoProxy;
import nc.bs.framework.common.NCLocator;
import nc.login.bs.INCUserQueryService;
import nc.pubimpl.so.pub.api.SOTestCase;
import nc.pubitf.org.IGroupPubService;
import nc.vo.pubapp.pattern.exception.ExceptionUtils;
import nc.vo.sm.UserVO;

import org.junit.Test;

/**
 * @description
 * 买赠设置查询服务测试用例
 * @scene
 *
 * @param
 *
 *
 * @since 6.5
 * @version 2015-11-10 下午10:54:27
 * @author 刘景
 */
public class GwpruleQueryAPITest extends SOTestCase {

  @Test
  public void test() {
    GwpruleQuery query = new GwpruleQuery();
    query.queryVOByIDs();
    query.queryViewVOByBIDs();
    query.queryVOByScheme();
    query.queryViewVOByScheme();
  }

  @Override
  protected String getPwd() {
    return super.getPwd();
  }

  @Override
  protected String getUser() {
    return super.getUser();
  }

  @Override
  protected void setUp() throws Exception {
    super.setUp();
    String dataSource = InvocationInfoProxy.getInstance().getUserDataSource();
    String userCode = InvocationInfoProxy.getInstance().getUserCode();
    try {
      UserVO userVO =
          NCLocator.getInstance().lookup(INCUserQueryService.class)
              .findUserVO(dataSource, userCode);
      if (userVO != null) {
        InvocationInfoProxy.getInstance().setGroupId(userVO.getPk_group());
        String groupNo =
            NCLocator.getInstance().lookup(IGroupPubService.class)
                .getGroupNoByPK(userVO.getPk_group());
        InvocationInfoProxy.getInstance().setGroupNumber(groupNo);
        InvocationInfoProxy.getInstance().setUserId(userVO.getPrimaryKey());
      }
    }
    catch (Exception e) {
      ExceptionUtils.wrappException(e);
    }
 
  }
}
