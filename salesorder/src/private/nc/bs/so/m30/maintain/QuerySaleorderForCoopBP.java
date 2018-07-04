package nc.bs.so.m30.maintain;

import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;

import nc.bs.framework.common.NCLocator;
import nc.impl.pubapp.pattern.data.bill.BillQuery;
import nc.impl.pubapp.pattern.database.DataAccessUtils;
import nc.itf.scmf.coop.ICoopService;
import nc.pubitf.so.m30.pub.M30TranTypeUtil;
import nc.vo.pub.BusinessException;
import nc.vo.pubapp.pattern.exception.ExceptionUtils;
import nc.vo.scmf.coop.entity.CoopHeaderVO;
import nc.vo.scmpub.res.billtype.POBillType;
import nc.vo.scmpub.res.billtype.SOBillType;
import nc.vo.so.m30.entity.SaleOrderBVO;
import nc.vo.so.m30.entity.SaleOrderVO;

public class QuerySaleorderForCoopBP {
  public SaleOrderVO[] querySaleorderForCoop(String sql, String pk_puorg) {
    DataAccessUtils utils = new DataAccessUtils();
    String[] ids = utils.query(sql).toOneDimensionStringArray();
    BillQuery<SaleOrderVO> query =
        new BillQuery<SaleOrderVO>(SaleOrderVO.class);
    SaleOrderVO[] bills = query.query(ids);
    SaleOrderVO[] vos = this.filerVO(bills, pk_puorg);
    return vos;
  }

  /*
   * 根据协同设置过滤查询出的vo,并且过滤出订单交易类型是直运类型的vo
   */
  private SaleOrderVO[] filerVO(SaleOrderVO[] billvos, String pk_puorg) {
    if (null == billvos) {
      return billvos;
    }
    Map<String, SaleOrderVO> tempMap = new HashMap<String, SaleOrderVO>();
    for (SaleOrderVO bill : billvos) {
      tempMap.put(bill.getParentVO().getCsaleorderid(), bill);
    }
    // 根据结算财务组织过滤
    SaleOrderVO[] newbills = this.filterBySetOrg(billvos);
    if (null == newbills || newbills.length == 0) {
      return null;
    }
    String[] transtypes =
        M30TranTypeUtil.getInstance().queryDirectTypeAllBillTypeCode();
    String[] hids = this.getMatchOrderHids(newbills, pk_puorg);
    if ((null == hids) || (hids.length == 0)) {
      return null;
    }
    Map<String, SaleOrderVO> orderMap = this.getSaleorderMap(newbills);
    Set<SaleOrderVO> set = new HashSet<SaleOrderVO>();
    for (String hid : hids) {
      SaleOrderVO vo = orderMap.get(hid);
      if (null == vo) {
        continue;
      }
      for (String transtype : transtypes) {
        if (!transtype.equals(vo.getParentVO().getVtrantypecode())) {
          set.add(vo);
        }
      }
    }
    if (set.size() == 0) {
      return new SaleOrderVO[0];
    }
    SaleOrderVO[] vos = new SaleOrderVO[set.size()];
    return set.toArray(vos);
  }

  private SaleOrderVO[] filterBySetOrg(SaleOrderVO[] vos) {
    Map<String, SaleOrderVO> tempMap = new HashMap<String, SaleOrderVO>();
    for (SaleOrderVO vo : vos) {
      tempMap.put(vo.getParentVO().getCsaleorderid(), vo);
    }
    for (SaleOrderVO vo : vos) {
      Set<String> tempSet = new HashSet<String>();
      SaleOrderBVO[] bvos = vo.getChildrenVO();
      for (SaleOrderBVO bvo : bvos) {
        if (0 == tempSet.size()) {
          tempSet.add(bvo.getCsettleorgvid());
        }
        if ((tempSet.size() > 0) && !tempSet.contains(bvo.getCsettleorgvid())) {
          tempMap.remove(vo.getParentVO().getCsaleorderid());
          tempSet.clear();
          break;
        }
      }
    }
    SaleOrderVO[] newbills = new SaleOrderVO[tempMap.size()];
    return tempMap.values().toArray(newbills);
  }

  /*
   * 根据销售订单vo，匹配协同设置，返回能匹配上的订单主键
   */
  private String[] getMatchOrderHids(SaleOrderVO[] billvos, String pk_puorg) {
    Map<String, CoopHeaderVO> coopMap = new HashMap<String, CoopHeaderVO>();
    for (SaleOrderVO vo : billvos) {
      String pk_financeorg_src = vo.getChildrenVO()[0].getCsettleorgid();
      CoopHeaderVO headvo = new CoopHeaderVO();
      headvo.setCustomerPK(vo.getParentVO().getCcustomerid());
      headvo.setPk_financeorg_src(pk_financeorg_src);
      headvo.setPk_org_src(vo.getParentVO().getPk_org());
      headvo.setVtrantypecode_src(vo.getParentVO().getVtrantypecode());
      headvo.setPk_group(vo.getParentVO().getPk_group());
      headvo.setPk_org_dest(pk_puorg);

      headvo.setVbilltype_src(SOBillType.Order.getCode());
      headvo.setVbilltype_dest(POBillType.Order.getCode());
      coopMap.put(vo.getPrimaryKey(), headvo);
    }
    ICoopService service = NCLocator.getInstance().lookup(ICoopService.class);
    try {
      String[] hids =
          service.gethasCoopRelationShipSrcPks(POBillType.Order.getCode(),
              coopMap);
      return hids;
    }
    catch (BusinessException e) {
      ExceptionUtils.wrappException(e);
    }
    return null;
  }

  private Map<String, SaleOrderVO> getSaleorderMap(SaleOrderVO[] vos) {
    Map<String, SaleOrderVO> orderMap = new HashMap<String, SaleOrderVO>();
    for (SaleOrderVO vo : vos) {
      orderMap.put(vo.getPrimaryKey(), vo);
    }
    return orderMap;
  }

}
