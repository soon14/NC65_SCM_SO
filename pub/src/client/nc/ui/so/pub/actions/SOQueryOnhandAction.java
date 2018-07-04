package nc.ui.so.pub.actions;

import java.awt.event.ActionEvent;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;

import nc.bs.framework.common.NCLocator;
import nc.funcnode.ui.AbstractFunclet;
import nc.funcnode.ui.FuncletInitData;
import nc.impl.pubapp.pattern.database.DataAccessUtils;
import nc.itf.org.IOrgConst;
import nc.itf.org.IOrgUnitQryService;
import nc.itf.org.orgmodel.IOrgRelationTypeConst;
import nc.pubitf.so.ic.m4c.ISaleFor4CParaQuery;
import nc.ui.ic.onhand.OnhandDialog;
import nc.ui.ml.NCLangRes;
import nc.ui.pubapp.billref.push.BillContext;
import nc.ui.pubapp.billref.push.IBillPush;
import nc.ui.pubapp.billref.push.TabBillInfo;
import nc.ui.pubapp.uif2app.model.BillManageModel;
import nc.ui.pubapp.uif2app.view.ShowUpableBillForm;
import nc.ui.scmpub.action.SCMActionInitializer;
import nc.ui.uif2.NCAction;
import nc.vo.bd.pub.IPubEnumConst;
import nc.vo.org.OrgVO;
import nc.vo.org.orgmodel.OrgRelationVO;
import nc.vo.org.util.OrgTypeManager;
import nc.vo.pub.BusinessException;
import nc.vo.pub.CircularlyAccessibleValueObject;
import nc.vo.pubapp.pattern.data.IRowSet;
import nc.vo.pubapp.pattern.exception.ExceptionUtils;
import nc.vo.pubapp.pattern.model.entity.bill.AbstractBill;
import nc.vo.pubapp.pattern.model.entity.view.IDataView;
import nc.vo.pubapp.pattern.pub.MapList;
import nc.vo.pubapp.pattern.pub.SqlBuilder;
import nc.vo.scmpub.res.SCMActionCode;
import nc.vo.scmpub.util.ArrayUtil;
import nc.vo.scmpub.util.ListUtil;
import nc.vo.so.entry.SOOnhandDlgHeadVO;
import nc.vo.so.m4331.entity.DeliveryVO;
import nc.vo.so.m4331.entity.DeliveryViewVO;
import nc.vo.so.pub.SOItemKey;
import nc.vo.uif2.LoginContext;

/**
 * 存量查拣
 * 
 * @author zhangby5
 * @version 636
 * 
 */
public class SOQueryOnhandAction extends NCAction implements IBillPush {

  /**
   * 
   */
  private static final long serialVersionUID = -8127341892739389935L;

  private BillManageModel model;

  private ShowUpableBillForm card;

  // 推单界面 正常的单据界面无需注册该bean
  private TabBillInfo tabBillInfo;

  // 推单使用 存储有界面的值
  private BillContext billcontext;

  private static final String PATH = "nc/ui/so/pub/model/so_onhand.xml";
  
  private IFillOnhandDlgHeadVO fillhead;

  public SOQueryOnhandAction() {
    super();
    SCMActionInitializer.initializeAction(this, SCMActionCode.SCM_ONHANDINFO);
  }

  @Override
  public void doAction(ActionEvent e) throws Exception {
    LoginContext context =
        this.getModel() == null ? this.getTabBillInfo().getLoginContext()
            : this.getModel().getContext();
    OnhandDialog onHandDlg = new OnhandDialog(context.getEntranceUI(), true);
    Object[] bills = this.getSelectedBills();
    if (ArrayUtil.isEmpty(bills) || this.isBillsContainsNull(bills)) {
      ExceptionUtils.wrappBusinessException(NCLangRes.getInstance().getStrByID(
          "4006004_0", "04006004-0244")/* 表体数据不能为空 */);
    }
    IDataView[] datas = this.fillOnhandDlgData(bills);
    if (ArrayUtil.isEmpty(datas)) {
      ExceptionUtils.wrappBusinessException(NCLangRes.getInstance().getStrByID(
          "4006004_0", "04006004-0245")/* 请检查表体数据，物料不能为空 */);
    }
    FuncletInitData initData = new FuncletInitData();
    initData.setInitData(datas);
    AbstractFunclet funclet = (AbstractFunclet) context.getEntranceUI();
    onHandDlg.initUI(funclet.getFuncletContext().getFuncRegisterVO()
        .getFuncode(), PATH, initData, false);
    onHandDlg.showModal();
  }

  private boolean isBillsContainsNull(Object[] bills) {
    for (Object bill : bills) {
      if (bill == null) {
        return true;
      }
    }
    return false;
  }

  private Object[] getSelectedBills() {
    Object[] bills = null;
    if (this.getTabBillInfo() != null) {
      try {
        bills =
            this.getTabBillInfo().getBillTabPanel().getModel().getSelectDatas();
      }
      catch (Exception e) {
        ExceptionUtils.wrappBusinessException(e.getMessage());
      }
    }
    else {
      if (this.getCard().isShowing()) {
        bills = new Object[] {
          this.getCard().getValue()
        };
      }
      else {
        bills = this.getModel().getSelectedOperaDatas();
      }
    }
    return bills;
  }

  private IDataView[] fillOnhandDlgData(Object[] bills) {
    Set<String> orgset = new HashSet<>();
    for (Object bill : bills) {
      this.creatOrgSet(bill, orgset);
    }
    if (orgset.size() == 0) {
      ExceptionUtils.wrappBusinessException(NCLangRes.getInstance().getStrByID(
          "4006004_0", "04006004-0244")/* 表体数据不能为空 */);
    }
    ISaleFor4CParaQuery query =
        NCLocator.getInstance().lookup(ISaleFor4CParaQuery.class);
    // 有权限的组织
    Map<String, List<String>> orgmap = null;
    try {
      orgmap =
          query.getStockOrgIDSBySaleOrgID(orgset.toArray(new String[orgset
              .size()]));
    }
    catch (BusinessException e) {
      ExceptionUtils.wrappBusinessException(e.getMessage());
    }

    List<SOOnhandDlgHeadVO> onhandHVOs = new ArrayList<SOOnhandDlgHeadVO>();
    for (Object bill : bills) {
      List<SOOnhandDlgHeadVO> handHVOs = this.getOnhandDlgHeadVO(bill, orgmap);
      if (ListUtil.isEmpty(handHVOs)) {
        continue;
      }
      onhandHVOs.addAll(handHVOs);
    }
    return ListUtil.toArray(onhandHVOs);
  }

  /**
   * 用途： 根据销售组织得到允许发货的库存组织ID[]； 逻辑：
   * 1、根据销售组织匹配销售业务委托关系，匹配上的库存组织允许发货；
   * 2、销售组织又具有库存组织属性，则销售组织作为发货库存组织允许发货； 说明：返回的ID不应该有重复
   * 
   * @param saleorgID
   * @param materialID
   * @return
   * @throws BusinessException
   */
  private Map<String, List<String>> getStockOrgIDSBySaleOrgID(String[] orgids) {

    MapList<String, String> saleorgmap = new MapList<String, String>();

    try {
      // 1、根据“销售组织"匹配销售业务委托关系，匹配上的库存组织允许发货；
      SqlBuilder sql = new SqlBuilder();
      sql.append("select distinct " + OrgRelationVO.TARGET + ","
          + OrgRelationVO.SOURCER + " from ");
      sql.append(OrgRelationVO.getDefaultTableName());
      sql.append(" where ");
      sql.append(OrgRelationVO.PK_RELATIONTYPE,
          IOrgRelationTypeConst.SALESTOCKCONSIGN);
      sql.append(" and ");
      sql.append(OrgRelationVO.SOURCER, orgids);
      sql.append(" and ");
      sql.append(OrgRelationVO.ENABLESTATE, IPubEnumConst.ENABLESTATE_ENABLE);
      DataAccessUtils dao = new DataAccessUtils();
      IRowSet rowset = dao.query(sql.toString());
      String[][] orgs = rowset.toTwoDimensionStringArray();
      for (String[] org : orgs) {
        // 销售组织对应的库存组织
        saleorgmap.put(org[1], org[0]);
      }

      // 2、销售组织又具有库存组织属性，则销售组织作为发货库存组织允许发货；
      IOrgUnitQryService service =
          NCLocator.getInstance().lookup(IOrgUnitQryService.class);
      OrgVO[] orgvos = service.getOrgs(orgids);
      for (OrgVO orgvo : orgvos) {
        if (orgvo != null
            && OrgTypeManager.getInstance().isTypeOf(orgvo,
                IOrgConst.STOCKORGTYPE)) {
          saleorgmap.put(orgvo.getPrimaryKey(), orgvo.getPrimaryKey());
        }
      }
    }
    catch (BusinessException e) {
      ExceptionUtils.wrappException(e);
    }
    return saleorgmap.toMap();
  }

  private void creatOrgSet(Object bill, Set<String> orgset) {
    CircularlyAccessibleValueObject hvo = null;
    CircularlyAccessibleValueObject[] bvos = null;
    if (DeliveryViewVO.class.isInstance(bill)) {
      DeliveryViewVO viewVO = (DeliveryViewVO) bill;
      hvo = viewVO.getHead();
      bvos = new CircularlyAccessibleValueObject[] {
          viewVO.getItem()
        };
    }
    else if (AbstractBill.class.isInstance(bill)) {
      AbstractBill billVO = (AbstractBill) bill;
      hvo = billVO.getParentVO();
      bvos = billVO.getChildrenVO();
    }
    else {
      ExceptionUtils.wrappBusinessException(NCLangRes.getInstance().getStrByID(
          "4006004_0", "04006004-0248")/* 暂不支持该操作 */);
    }
    //发货单取表体销售组织、调拨订单取调出库存组织、销售订单取销售组织
    if (DeliveryViewVO.class.isInstance(bill)
        || DeliveryVO.class.isInstance(bill)) {
      for (CircularlyAccessibleValueObject bvo : bvos) {
        Object obj = bvo.getAttributeValue("csaleorgid");
        if (obj == null) {
          obj = bvo.getAttributeValue("pk_org");
        }
        orgset.add((String) obj);
      }
    }
    else {
      if (hvo != null) {
        Object obj = hvo.getAttributeValue(SOItemKey.PK_ORG);
        if (obj != null) {
          orgset.add((String) obj);
        }
      }
    }
  }

  private List<SOOnhandDlgHeadVO> getOnhandDlgHeadVO(Object bill,
      Map<String, List<String>> orgmap) {

    List<SOOnhandDlgHeadVO> onhandHVOs = new ArrayList<>();
    CircularlyAccessibleValueObject hvo = null;
    CircularlyAccessibleValueObject[] bvos = null;
    if (DeliveryViewVO.class.isInstance(bill)) {
      DeliveryViewVO viewVO = (DeliveryViewVO) bill;
      hvo = viewVO.getHead();
      bvos = new CircularlyAccessibleValueObject[] {
        viewVO.getItem()
      };
    }
    else if (AbstractBill.class.isInstance(bill)) {
      AbstractBill billVO = (AbstractBill) bill;
      hvo = billVO.getParentVO();
      bvos = billVO.getChildrenVO();
    }
    else {
      ExceptionUtils.wrappBusinessException(NCLangRes.getInstance().getStrByID(
          "4006004_0", "04006004-0248")/* 暂不支持该操作 */);
    }
    if (bvos == null) {
      return onhandHVOs;
    }
    for (CircularlyAccessibleValueObject bvo : bvos) {
      SOOnhandDlgHeadVO onhandHVO = null;
      if (this.getFillhead() != null) {
        onhandHVO = this.getFillhead().fillOnhandVO(hvo, bvo);
      }
      else {
        onhandHVO = new SOFillOnhandDlgHeadVO().fillOnhandVO(hvo, bvo);
      }
      if(onhandHVO == null){
        continue;
      }
      String org = (String) hvo.getAttributeValue(SOItemKey.PK_ORG);
      List<String> orglist = orgmap.get(org);
      //发货单取表体销售组织
      if (orglist == null) {
        org = (String) bvo.getAttributeValue("csaleorgid");
        orglist = orgmap.get(org);
      }
      if (orglist == null) {
        onhandHVO.setPk_orgs(null);
      }
      else {
        onhandHVO.setPk_orgs(orglist.toArray(new String[orglist.size()]));
      }
      onhandHVOs.add(onhandHVO);
    }
    return onhandHVOs;
  }

  public BillManageModel getModel() {
    return model;
  }

  public void setModel(BillManageModel model) {
    this.model = model;
  }

  public ShowUpableBillForm getCard() {
    return card;
  }

  public void setCard(ShowUpableBillForm card) {
    this.card = card;
  }

  public TabBillInfo getTabBillInfo() {
    return tabBillInfo;
  }

  public void setTabBillInfo(TabBillInfo tabBillInfo) {
    this.tabBillInfo = tabBillInfo;
  }

  public BillContext getBillContext() {
    return billcontext;
  }

  public void setBillContext(BillContext context) {
    this.billcontext = context;
    if (this.getTabBillInfo() == null) {
      this.setTabBillInfo(billcontext.getTabBillInfo());
    }
  }

  
  public IFillOnhandDlgHeadVO getFillhead() {
    return fillhead;
  }
  
  public void setFillhead(IFillOnhandDlgHeadVO fillhead) {
    this.fillhead = fillhead;
  }
  
}
