package nc.pubimpl.so.m30.api;

import nc.bs.framework.common.InvocationInfoProxy;
import nc.bs.framework.common.NCLocator;
import nc.bs.scmpub.AbstractSCMTestCase;
import nc.login.bs.INCUserQueryService;
import nc.pubitf.org.IGroupPubService;
import nc.pubitf.so.m30.api.ISaleOrderMaintainAPI;
import nc.vo.pub.BusinessException;
import nc.vo.pub.lang.UFDouble;
import nc.vo.pubapp.pattern.exception.ExceptionUtils;
import nc.vo.sm.UserVO;
import nc.vo.so.m30.entity.SaleOrderBVO;
import nc.vo.so.m30.entity.SaleOrderHVO;
import nc.vo.so.m30.entity.SaleOrderVO;

import org.junit.Test;

/**
 * @description
 * 销售订单持久化接口测试用例1
 * @scene
 *
 * @param
 *
 *
 * @since 6.5
 * @version 2015-11-3 下午3:01:11
 * @author 刘景
 */
public class SaleOrderMaintainAPITest extends AbstractSCMTestCase {

  @Test
  public void testInsert() {
    ISaleOrderMaintainAPI service =
        NCLocator.getInstance().lookup(ISaleOrderMaintainAPI.class);
    // 浮动换算率场景
    SaleOrderVO vo = new SaleOrderVO();
    SaleOrderHVO hvo = new SaleOrderHVO();
    SaleOrderBVO bvo = new SaleOrderBVO();
    hvo.setPk_org("0001DD1000000000O6MM");
    hvo.setCdeptid("1001DD1000000000096Q");
    hvo.setCtrantypeid("0001DD10000000002UW7");
    hvo.setCcustomerid("1001DD1000000000091X");
    // hvo.setCorigcurrencyid("1002Z0100000000001K1");
    bvo.setCmaterialvid("1001DD10000000001UE0");
    bvo.setVbatchcode("A00123");
    // bvo.setNnum(new UFDouble(14));
    bvo.setNqtunitnum(new UFDouble(15));
    // bvo.setNastnum(new UFDouble(14));
    bvo.setNqtorigtaxnetprc(new UFDouble(3.14));
    bvo.setNorigtaxmny(new UFDouble(60));
    vo.setParent(hvo);
    vo.setChildrenVO(new SaleOrderBVO[] {
      bvo
    });

    // 固定换算率场景
    SaleOrderVO vo1 = new SaleOrderVO();
    SaleOrderHVO hvo1 = new SaleOrderHVO();
    SaleOrderBVO bvo1 = new SaleOrderBVO();
    hvo1.setPk_org("0001DD1000000000TLOG");
    hvo1.setCdeptid("1002DD10000000000U8D");
    hvo1.setVtrantypecode("30-01");
    hvo1.setCcustomerid("1002DD100000000005TK");
    hvo1.setCorigcurrencyid("1002Z0100000000001K1");

    bvo1.setCmaterialvid("1002DD100000000005QN");
    bvo1.setNqtunitnum(new UFDouble(15));
    // bvo1.setNorigtaxnetprice(new UFDouble(3.14));
     bvo1.setNorigtaxmny(new UFDouble(60));
    vo1.setParent(hvo1);
    vo1.setChildrenVO(new SaleOrderBVO[] {
      bvo1
    });

    try {
      service.insertBills(new SaleOrderVO[] {
        vo, vo1
      });
    }
    catch (BusinessException e) {
      ExceptionUtils.wrappException(e);
    }
  }

  @Test
  public void testDeleteBillsByID() {
    String[] ids = new String[] {
      "1001Z810000000026KUR"
    };
    try {
      ISaleOrderMaintainAPI service =
          NCLocator.getInstance().lookup(ISaleOrderMaintainAPI.class);
      service.deleteBillsByID(ids);
    }
    catch (BusinessException e) {
      ExceptionUtils.wrappException(e);
    }
  }

  @Override
  protected String getPwd() {
    return "123456a";
  }

  @Override
  protected String getUser() {
    return "wt2";
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
