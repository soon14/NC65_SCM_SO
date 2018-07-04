package nc.pubimpl.so.pub.api;

import nc.bs.framework.common.InvocationInfoProxy;
import nc.bs.framework.common.NCLocator;
import nc.bs.scmpub.AbstractSCMTestCase;
import nc.login.bs.INCUserQueryService;
import nc.pubitf.org.IGroupPubService;
import nc.vo.pubapp.pattern.exception.ExceptionUtils;
import nc.vo.sm.UserVO;

/**
 * @description
 *  销售管理测试用例数据库：20.10.130.119/ZPXMT_JY1102 sa/sa
 * @scene
 *
 * @param
 *
 *
 * @since 6.5
 * @version 2015-11-10 下午11:19:46
 * @author 刘景
 */
public class SOTestCase extends AbstractSCMTestCase {

  @Override
  protected String getPwd() {
    return "123456a";
  }

  @Override
  protected String getUser() {
    return "zz1";
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
