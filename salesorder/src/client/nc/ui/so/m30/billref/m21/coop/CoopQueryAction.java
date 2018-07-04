package nc.ui.so.m30.billref.m21.coop;

import java.awt.event.ActionEvent;

import nc.vo.pub.AggregatedValueObject;
import nc.vo.pub.query.ConditionVO;
import nc.vo.pubapp.pattern.exception.ExceptionUtils;

import nc.ui.pub.beans.UIDialog;
import nc.ui.pubapp.billref.src.action.QueryAction;
import nc.ui.pubapp.uif2app.query2.QueryConditionDLGDelegator;
import nc.ui.querytemplate.querytree.IQueryScheme;
import nc.ui.so.m30.billui.model.QueryServiceForCoop;

public class CoopQueryAction extends QueryAction {
  private static final long serialVersionUID = -7687826074344837322L;

  @Override
  public void doAction(ActionEvent e) throws Exception {
    CoopBillReferQuery referQuery =
        (CoopBillReferQuery) this.getRefContext().getRefDialog().getQueyDlg();
    QueryConditionDLGDelegator queryCondition = referQuery.getQryDLGDelegator();
    if (!this.isInitQuery()) {
      if (queryCondition.showModal() != UIDialog.ID_OK) {
        return;
      }
    }
    try {
      IQueryScheme qs = queryCondition.getQueryScheme();
      ConditionVO[] conditionvos = queryCondition.getLogicalConds();
      String pk_puorg = null;
      if (null != conditionvos && conditionvos.length > 0) {
        pk_puorg = conditionvos[0].getValue();
      }
      if (this.getRefContext().getRefInfo().getQueryService() != null) {
        QueryServiceForCoop refqueryService =
            (QueryServiceForCoop) this.getRefContext().getRefInfo()
                .getQueryService();
        AggregatedValueObject[] billvos =
            (AggregatedValueObject[]) refqueryService.queryByQueryScheme(qs,
                pk_puorg);
        this.getRefBillModel().setBillVOs(billvos);
        return;
      }
      this.getRefBillModel().setQueryScheme(queryCondition.getQueryScheme());
    }
    catch (Exception ex) {
      ExceptionUtils.marsh(ex);
    }
    finally {
      this.setInitQuery(false);
    }
  }

  /*
   * 根据协同设置过滤查询出的vo,并且过滤出订单交易类型是直运类型的vo
   
  private SaleOrderVO[] filerVO(SaleOrderVO[] billvos, String pk_puorg) {
    if (null == billvos || null == pk_puorg) {
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
    String pk_group =
        this.getRefContext().getRefInfo().getBillSrcVar().getPk_group();
    String[] transtypes = this.getTranTypeCodes(pk_group);
    String[] hids = this.getMatchOrderHids(newbills, pk_puorg);
    if (null == hids || hids.length == 0) {
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
        if (tempSet.size() > 0 && !tempSet.contains(bvo.getCsettleorgvid())) {
          tempMap.remove(vo.getParentVO().getCsaleorderid());
          tempSet.clear();
          break;
        }
      }
    }
    SaleOrderVO[] newbills = new SaleOrderVO[tempMap.size()];
    return tempMap.values().toArray(newbills);
  }
  */
  /*
   * 根据销售订单vo，匹配协同设置，返回能匹配上的订单主键
   
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
  */
  /*
   * 获得是直运的所有订单交易类型
   
  private String[] getTranTypeCodes(String pk_group) {
    try {
      return NCLocator.getInstance().lookup(IM30TranTypeService.class)
          .queryDirectTypeAllBillTypeCode(pk_group);
    }
    catch (BusinessException e) {
      ExceptionUtils.wrappException(e);
    }
    return null;
  }*/
}
