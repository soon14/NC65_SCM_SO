package nc.pubimpl.so.mbuylargess.opc.mecc;

import java.util.ArrayList;
import java.util.List;

import org.apache.commons.lang.ArrayUtils;

import nc.vo.pub.BusinessException;
import nc.vo.pubapp.pattern.exception.ExceptionUtils;
import nc.vo.pubapp.pattern.pub.SqlBuilder;
import nc.vo.so.mbuylargess.entity.BuyLargessBVO;
import nc.vo.so.mbuylargess.entity.BuyLargessHVO;
import nc.vo.so.mbuylargess.entity.BuyLargessVO;
import nc.vo.so.pub.SOTable;

import nc.pubitf.so.mbuylagress.opc.mecc.IBuylargessForMecc;

import nc.bs.ml.NCLangResOnserver;

import nc.impl.pubapp.pattern.data.vo.VOQuery;
import nc.impl.pubapp.pattern.database.IDExQueryBuilder;

/**
 * 买赠设置查询接口实现
 * 
 * @since 6.3
 * @version 2012-10-23 下午02:42:56
 * @author 梁吉明
 */
public class BuylargessForMeccImpl implements IBuylargessForMecc {

  /**
   * 电子销售根据集团、销售组织查询买赠设置
   * 
   * @param pk_groups 集团ID数组
   * @param pk_orgs 销售组织ID数组
   * @return 买赠设置VO数组
   * @throws BusinessException
   * 
   */
  @Override
  public BuyLargessVO[] queryBuyLargessVO(String[] pk_groups, String[] pk_orgs)
      throws BusinessException {
    if (null == pk_groups || pk_groups.length == 0) {
      ExceptionUtils.wrappBusinessException(NCLangResOnserver.getInstance()
          .getStrByID("4006003_0", "04006003-0033")/*集团PK为空*/);
    }
    BuyLargessVO[] buyLargessVOs = null;
    try {
      // 查询主表信息
      BuyLargessHVO[] headvo = this.getHeadVO(pk_groups, pk_orgs);
      // 判断查询主表是否为空
      if (null == headvo || headvo.length == 0) {
        return new BuyLargessVO[0];
      }
      // 查询子表信息
      BuyLargessBVO[] bodyvo = this.getBodyVO(headvo);
      // 获得聚合VO
      buyLargessVOs = this.getVO(headvo, bodyvo);
    }
    catch (Exception e) {
      ExceptionUtils.marsh(e);
    }
    return buyLargessVOs;
  }

  private BuyLargessVO[] getVO(BuyLargessHVO[] headvo, BuyLargessBVO[] bodyvo) {
    BuyLargessVO[] buyLargessVOs;
    int hlength = headvo.length;
    buyLargessVOs = new BuyLargessVO[hlength];
    for (int i = 0; i < hlength; i++) {
      buyLargessVOs[i] = new BuyLargessVO();
      buyLargessVOs[i].setParentVO(headvo[i]);
      String pk_buylargess = headvo[i].getPk_buylargess();
      List<BuyLargessBVO> bodylist = new ArrayList<BuyLargessBVO>();
      int blength = bodyvo.length;
      for (int j = 0; j < blength; j++) {
        if (pk_buylargess.equals(bodyvo[j].getPk_buylargess())) {
          bodylist.add(bodyvo[j]);
        }
      }
      buyLargessVOs[i].setChildrenVO(bodylist
          .toArray(new BuyLargessBVO[bodylist.size()]));
    }
    return buyLargessVOs;
  }

  private BuyLargessBVO[] getBodyVO(BuyLargessHVO[] headvo) {
    // 获得主表所有主键
    List<String> pk_list = new ArrayList<String>();
    for (BuyLargessHVO hvo : headvo) {
      pk_list.add(hvo.getPk_buylargess());
    }
    String[] pk_buylargess = pk_list.toArray(new String[pk_list.size()]);
    SqlBuilder bodycondition = new SqlBuilder();
    bodycondition.append(" and ");
    IDExQueryBuilder iq = new IDExQueryBuilder(SOTable.TMP_SO_ID1.getName());
    bodycondition.append(iq
        .buildSQL(BuyLargessBVO.PK_BUYLARGESS, pk_buylargess));
    VOQuery<BuyLargessBVO> bvoQuery =
        new VOQuery<BuyLargessBVO>(BuyLargessBVO.class);
    BuyLargessBVO[] bodyvo = bvoQuery.query(bodycondition.toString(), null);
    // 判断查询子表是否为空
    if (null == bodyvo || bodyvo.length == 0) {
      return new BuyLargessBVO[0];
    }
    return bodyvo;
  }

  private BuyLargessHVO[] getHeadVO(String[] pk_groups, String[] pk_orgs) {
    SqlBuilder headcondition = new SqlBuilder();
    headcondition.append(" and ");
    IDExQueryBuilder iq = new IDExQueryBuilder(SOTable.TMP_SO_ID1.getName());
    headcondition.append(iq.buildSQL(BuyLargessHVO.PK_GROUP, pk_groups));
    if (!ArrayUtils.isEmpty(pk_orgs)) {
      headcondition.append(" and ");
      iq = new IDExQueryBuilder(SOTable.TMP_SO_ID2.getName());
      headcondition.append(iq.buildSQL(BuyLargessHVO.PK_ORG, pk_orgs));
    }
    VOQuery<BuyLargessHVO> hvoQuery =
        new VOQuery<BuyLargessHVO>(BuyLargessHVO.class);
    BuyLargessHVO[] headvo = hvoQuery.query(headcondition.toString(), null);
    return headvo;
  }
}
