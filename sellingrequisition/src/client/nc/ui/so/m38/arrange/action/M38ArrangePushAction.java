package nc.ui.so.m38.arrange.action;

import java.awt.event.ActionEvent;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.TreeSet;

import nc.vo.price.pub.entity.FindPriceParaVO;
import nc.vo.price.pub.entity.FindPriceResultVO;
import nc.vo.pub.BusinessException;
import nc.vo.pub.lang.UFDateTime;
import nc.vo.pub.lang.UFDouble;
import nc.vo.pubapp.pattern.exception.ExceptionUtils;
import nc.vo.pubapp.pattern.pub.PubAppTool;
import nc.vo.scmpub.util.CombineViewToAggUtil;
import nc.vo.so.m30.entity.SaleOrderVO;
import nc.vo.so.m30.entity.SaleOrderViewVO;
import nc.vo.so.m38.entity.PreOrderBVO;
import nc.vo.so.m38.entity.PreOrderHVO;
import nc.vo.so.m38.entity.PreOrderVO;
import nc.vo.so.m38.entity.PreOrderViewVO;
import nc.vo.so.pub.enumeration.AskPriceRule;

import nc.itf.so.m30trantype.IM30TranTypeService;
import nc.itf.so.pub.ref.price.PriceServicesUtil;
import nc.itf.uap.pf.IPfExchangeService;
import nc.itf.uap.pf.busiflow.PfButtonClickContext;

import nc.bs.framework.common.NCLocator;

import nc.impl.pubapp.bill.convertor.BillToViewConvertor;

import nc.ui.ml.NCLangRes;
import nc.ui.pubapp.billref.push.BillPushConst;
import nc.ui.pubapp.billref.push.PushAction;
import nc.ui.pubapp.billref.push.SingleBillModel;
import nc.ui.pubapp.billref.src.view.RefListPanel;
import nc.ui.uif2.ShowStatusBarMsgUtil;

public class M38ArrangePushAction extends PushAction {

  /**
   * 
   */
  private static final long serialVersionUID = 8882857569626839971L;

  private M38ArrangePushBeforeVOChange pushBeforeVOChange;

  @Override
  public M38ArrangePushBeforeVOChange getPushBeforeVOChange() {
    return this.pushBeforeVOChange;
  }

  public void setPushBeforeVOChange(
      M38ArrangePushBeforeVOChange pushBeforeVOChange) {
    this.pushBeforeVOChange = pushBeforeVOChange;
  }

  @Override
  public void doAction(ActionEvent e) throws Exception {

    PreOrderViewVO[] srcViews =
        (PreOrderViewVO[]) this.getBillContext().getModel().getSelectDatas();
    if (null == srcViews || srcViews.length == 0) {
      return;
    }
    M38ArrangePushBeforeVOChange beforchg = this.getPushBeforeVOChange();
    RefListPanel destPanel =
        this.getBillContext().getMultiModel(BillPushConst.DEST)
            .getModelByBillType(this.getDestBillType()).getBillInfo()
            .getBillTabPanel().getListPanel();

    if (null == beforchg) {
      beforchg = new M38ArrangePushBeforeVOChange();
    }
    PreOrderViewVO[] newSrcViews =
        (PreOrderViewVO[]) beforchg.beforeVOChange(srcViews, destPanel);

    List<Integer> listnoaryrow = beforchg.getNoArrangeRowList();
    // 1.过滤不合法数据
    if (newSrcViews.length == 0) {
      this.showNoArrangeMessage(listnoaryrow);
      return;
    }
    // 2.VO对照
    SaleOrderViewVO[] destViews = this.changeDestViews(newSrcViews);

    // 3.有销售订单交易类型的询价
    SaleOrderViewVO[] retDestViews = this.findSalePrice(destViews);
    // 4.加载数据
    SingleBillModel billModel =
        this.getBillContext().getMultiModel(BillPushConst.DEST)
            .getModelByBillType(this.getDestBillType());
    // 行号处理
    SaleOrderViewVO[] scrviews = (SaleOrderViewVO[]) billModel.getAllBillVOs();
    this.setRowNo(scrviews, retDestViews);

    billModel.addData(retDestViews);
    this.showNoArrangeMessage(listnoaryrow);
  }

  private void setRowNo(SaleOrderViewVO[] scrviews, SaleOrderViewVO[] newViews) {
    if (scrviews == null) {
      int startrow = 10;
      for (SaleOrderViewVO newView : newViews) {
        newView.getBody().setCrowno(String.valueOf(startrow));
        startrow = startrow + 10;
      }
      return;
    }
    TreeSet<Integer> setrowno = new TreeSet<Integer>();
    for (SaleOrderViewVO scrview : scrviews) {
      Integer rowno = Integer.valueOf(scrview.getBody().getCrowno());
      setrowno.add(rowno);
    }
    int startrow = setrowno.last().intValue() + 10;
    for (SaleOrderViewVO newView : newViews) {
      newView.getBody().setCrowno(String.valueOf(startrow));
      startrow = startrow + 10;
    }
  }

  // ----------------------------询价处理----------------------------
  public SaleOrderViewVO[] findSalePrice(SaleOrderViewVO[] views)
      throws BusinessException {
    // 1.过滤出需要询价views
    SaleOrderViewVO[] findViews = this.filterFindViews(views);
    if (null == findViews || findViews.length == 0) {
      return views;
    }
    // 2.组织询价VOs
    FindPriceParaVO[] paraVOs = this.getFindPriceParaVOs(findViews);

    // 3.询价
    FindPriceResultVO[] retVOs =
        PriceServicesUtil.findPrice(paraVOs, views[0].getHead().getPk_org());

    // 4.设置询价结果
    this.setFindPriceResult(findViews, retVOs);

    // 5.组装没有询价和询价的views
    return this.combineViews(views, findViews);
  }

  @Override
  public void intActionUI() {
    super.intActionUI();
  }

  private SaleOrderViewVO[] changeDestViews(PreOrderViewVO[] srcViews)
      throws Exception {

    CombineViewToAggUtil<PreOrderVO> viewtoaggcv =
        new CombineViewToAggUtil<PreOrderVO>(PreOrderVO.class,
            PreOrderHVO.class, PreOrderBVO.class);

    PreOrderVO[] bills =
        viewtoaggcv.combineViewToAgg(srcViews, PreOrderHVO.CPREORDERID);

    IPfExchangeService exchangeService =
        NCLocator.getInstance().lookup(IPfExchangeService.class);
    SaleOrderVO[] destVos =
        (SaleOrderVO[]) exchangeService.runChangeDataAryNeedClassify(this
            .getBillContext().getTabBillInfo().getBillType(),
            this.getDestBillType(), bills, null,
            PfButtonClickContext.ClassifyByItfdef);
    BillToViewConvertor<SaleOrderVO, SaleOrderViewVO> convertor =
        new BillToViewConvertor<SaleOrderVO, SaleOrderViewVO>(
            SaleOrderViewVO.class);
    return convertor.convert(destVos);
  }

  private SaleOrderViewVO[] combineViews(SaleOrderViewVO[] views,
      SaleOrderViewVO[] findViews) {
    Map<String, SaleOrderViewVO> findViewsMap =
        new HashMap<String, SaleOrderViewVO>();
    for (SaleOrderViewVO view : findViews) {
      findViewsMap.put(view.getBody().getCsrcbid(), view);
    }
    SaleOrderViewVO[] retViews = new SaleOrderViewVO[views.length];
    for (int i = 0; i < views.length; i++) {
      if (findViewsMap.containsKey(views[i].getBody().getCsrcbid())) {
        retViews[i] = findViewsMap.get(views[i].getBody().getCsrcbid());
      }
      else {
        retViews[i] = views[i];
      }
    }
    return retViews;
  }

  private SaleOrderViewVO[] filterFindViews(SaleOrderViewVO[] views) {

    List<SaleOrderViewVO> findViewsList = new ArrayList<SaleOrderViewVO>();
    // 批量查询询价规则
    IM30TranTypeService m30service =
        NCLocator.getInstance().lookup(IM30TranTypeService.class);
    Set<String> settran = new HashSet<String>();

    for (SaleOrderViewVO view : views) {
      String ctranstypeid = view.getHead().getCtrantypeid();
      if (PubAppTool.isNull(ctranstypeid)) {
        continue;
      }
      UFDouble nqttaxnetprice = view.getBody().getNqtorigtaxnetprc();
      if (null == nqttaxnetprice) {
        settran.add(ctranstypeid);
      }
    }
    if (settran.size() == 0) {
      return null;
    }
    Map<String, Integer> faskqtrules = null;
    try {
      String[] ctranstypeids = new String[settran.size()];
      settran.toArray(ctranstypeids);
      faskqtrules = m30service.queryAskPriceRule(ctranstypeids);
    }
    catch (BusinessException e) {
      ExceptionUtils.wrappException(e);
    }

    // 获取要询价的行
    for (SaleOrderViewVO view : views) {
      String ctranstypeid = view.getHead().getCtrantypeid();
      if (PubAppTool.isNull(ctranstypeid)) {
        continue;
      }
      UFDouble nqttaxnetprice = view.getBody().getNqtorigtaxnetprc();
      if (null != nqttaxnetprice) {
        continue;
      }

      Integer faskqtrule = faskqtrules.get(ctranstypeid);
      if (AskPriceRule.ASKPRICE_NORMAL.equalsValue(faskqtrule)
          || AskPriceRule.ASKPRICE_UNSPROMOTION.equalsValue(faskqtrule)) {
        findViewsList.add(view);
      }
    }
    SaleOrderViewVO[] findviews = new SaleOrderViewVO[findViewsList.size()];
    findViewsList.toArray(findviews);
    return findviews;
  }

  private FindPriceParaVO[] getFindPriceParaVOs(SaleOrderViewVO[] findViews) {
    FindPriceParaVO[] paraVOs = new FindPriceParaVO[findViews.length];
    for (int i = 0; i < findViews.length; i++) {
      paraVOs[i] = new FindPriceParaVO();
      paraVOs[i].setPk_group(findViews[i].getHead().getPk_group());
      paraVOs[i].setPk_org(findViews[i].getHead().getPk_org());
      paraVOs[i].setPk_customer(findViews[i].getHead().getCcustomerid());
      paraVOs[i].setPk_balatype(findViews[i].getHead().getCbalancetypeid());
      paraVOs[i].setPk_channeltype(findViews[i].getHead().getCchanneltypeid());
      paraVOs[i].setPk_currtype(findViews[i].getHead().getCorigcurrencyid());
      paraVOs[i].setTpricedate(new UFDateTime(findViews[i].getHead()
          .getDbilldate().toString()));
      paraVOs[i].setNnum(findViews[i].getBody().getNnum());
      paraVOs[i].setPk_material(findViews[i].getBody().getCmaterialid());
      paraVOs[i].setPk_unit(findViews[i].getBody().getCqtunitid());
    }
    return paraVOs;
  }

  private void setFindPriceResult(SaleOrderViewVO[] findViews,
      FindPriceResultVO[] retVOs) {
    for (int i = 0; i < findViews.length; i++) {
      if (retVOs[i] != null) {
        // 含税单价
        if (retVOs[i].getPrice() != null) {
          findViews[i].getBody().setNqtorigtaxprice(retVOs[i].getPrice());
        }
        // 单品折扣
        if (retVOs[i].getDiscount() != null) {
          findViews[i].getBody().setNitemdiscountrate(retVOs[i].getDiscount());
        }
        // 定价策略
        if (retVOs[i].getPricePolicy() != null) {
          findViews[i].getBody().setCpricepolicyid(retVOs[i].getPricePolicy());
        }
        // 价格项
        if (retVOs[i].getPriceType() != null) {
          findViews[i].getBody().setCpriceitemid(retVOs[i].getPriceType());
        }
        // 价目表
        if (retVOs[i].getTariff() != null) {
          findViews[i].getBody().setCpriceitemtableid(retVOs[i].getTariff());
        }
        // 价格组成
        if (retVOs[i].getPk_priceform() != null) {
          findViews[i].getBody().setCpriceformid(retVOs[i].getPk_priceform());
        }
      }
    }
  }

  private void showNoArrangeMessage(List<Integer> listnoaryrow) {
    if (listnoaryrow.size() == 0) {
      return;
    }
    StringBuilder errmsg = new StringBuilder();
    errmsg.append(NCLangRes.getInstance().getStrByID("4006012_0",
        "04006012-0068")/*选中的下列预订单行已经关闭或可安排数量为0，不能安排生成订单：*/);
    for (Integer row : listnoaryrow) {
      errmsg.append(NCLangRes.getInstance().getStrByID("4006012_0",
          "04006012-0069", null, new String[] {
            String.valueOf(row.intValue())
          })/*第[{0}]行、*/);
    }
    errmsg.deleteCharAt(errmsg.length() - 1);
    ShowStatusBarMsgUtil.showStatusBarMsg(errmsg.toString(), this
        .getBillContext().getTabBillInfo().getLoginContext());
  }
}
