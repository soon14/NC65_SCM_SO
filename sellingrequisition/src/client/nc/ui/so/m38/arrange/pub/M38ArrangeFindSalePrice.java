package nc.ui.so.m38.arrange.pub;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;
import java.util.Set;

import nc.bs.framework.common.NCLocator;
import nc.impl.pubapp.plugin.PluginExecutor;
import nc.itf.so.m30trantype.IM30TranTypeService;
import nc.itf.so.pub.findprice.ISOFindPrice;
import nc.ui.ml.NCLangRes;
import nc.ui.pubapp.ClientContext;
import nc.ui.so.pub.findprice.FindPricePluginExecDelegate;
import nc.vo.price.pub.entity.FindPriceParaVO;
import nc.vo.price.pub.entity.FindPriceResultVO;
import nc.vo.pub.BusinessException;
import nc.vo.pub.lang.UFBoolean;
import nc.vo.pub.lang.UFDate;
import nc.vo.pub.lang.UFDateTime;
import nc.vo.pub.lang.UFDouble;
import nc.vo.pubapp.pattern.exception.ExceptionUtils;
import nc.vo.pubapp.pattern.pub.MapList;
import nc.vo.pubapp.pattern.pub.PubAppTool;
import nc.vo.so.m30.entity.SaleOrderBVO;
import nc.vo.so.m30.entity.SaleOrderViewVO;
import nc.vo.so.m30.pub.SaleOrderViewCalculator;
import nc.vo.so.m30trantype.entity.M30TranTypeVO;
import nc.vo.so.pub.SOItemKey;
import nc.vo.so.pub.enumeration.AskPriceRule;
import nc.vo.so.pub.enumeration.LargessGetqtRule;
import nc.vo.so.pub.util.SOSysParaInitUtil;

public class M38ArrangeFindSalePrice {

  // 交易类型为空行
  private List<Integer> notranrows = new ArrayList<Integer>();

  // 交易类型不询价行
  private List<Integer> trannoaskrows = new ArrayList<Integer>();

  // 询价失败行
  private List<SaleOrderViewVO> failrows = new ArrayList<SaleOrderViewVO>();

  // 行索引
  private Map<SaleOrderViewVO, Integer> mapindex =
      new HashMap<SaleOrderViewVO, Integer>();

  // 询价成功行
  private Map<SaleOrderViewVO, FindPriceResultVO> hmSucess =
      new HashMap<SaleOrderViewVO, FindPriceResultVO>();

  private SaleOrderViewVO[] orderviews;

  private Map<String, M30TranTypeVO> mapTrantype;

  public M38ArrangeFindSalePrice(SaleOrderViewVO[] orderviews) {
    this.orderviews = orderviews;
  }

  private void clearFailRow() {

    String[] clearitems =
        new String[] {
        SOItemKey.NORIGTAXPRICE, SOItemKey.NORIGTAXNETPRICE,
        SOItemKey.NORIGPRICE, SOItemKey.NORIGNETPRICE, SOItemKey.NORIGTAX,
        SOItemKey.NORIGTAXMNY, SOItemKey.NORIGMNY, SOItemKey.NORIGDISCOUNT,

        SOItemKey.NTAXPRICE, SOItemKey.NTAXNETPRICE, SOItemKey.NPRICE,
        SOItemKey.NNETPRICE, SOItemKey.NTAX, SOItemKey.NTAXMNY,
        SOItemKey.NMNY, SOItemKey.NDISCOUNT,

        SOItemKey.NQTORIGTAXPRICE, SOItemKey.NQTORIGTAXNETPRC,
        SOItemKey.NQTORIGPRICE, SOItemKey.NQTORIGNETPRICE,

        SOItemKey.NQTTAXPRICE, SOItemKey.NQTTAXNETPRICE, SOItemKey.NQTPRICE,
        SOItemKey.NQTNETPRICE,

        SOItemKey.NASKQTORIGPRICE, SOItemKey.NASKQTORIGNETPRICE,
        SOItemKey.NASKQTORIGTAXPRC, SOItemKey.NASKQTORIGTXNTPRC,
        SOItemKey.NGLOBALMNY, SOItemKey.NGLOBALTAXMNY, SOItemKey.NGROUPMNY,
        SOItemKey.NGROUPTAXMNY, SOItemKey.CPRICEPOLICYID,
        SOItemKey.CPRICEITEMID, SOItemKey.CPRICEITEMTABLEID,
        SOItemKey.CPRICEFORMID, SOItemKey.NCALTAXMNY

    };
    for (SaleOrderViewVO view : this.failrows) {
      for (String key : clearitems) {
        view.setAttributeValue(key, null);
      }

    }
  }

  /**
   * 过滤需要询价行
   * 
   * @param rows
   * @return
   */
  private SaleOrderViewVO[] filterFindViews() {

    List<SaleOrderViewVO> alfindrow = new ArrayList<SaleOrderViewVO>();

    int i = 0;
    for (SaleOrderViewVO view : this.orderviews) {
      i++;
      this.mapindex.put(view, Integer.valueOf(i));
      // --交易类型空不询价
      String tranid = view.getHead().getCtrantypeid();
      if (PubAppTool.isNull(tranid)) {
        this.notranrows.add(Integer.valueOf(i));
        continue;
      }
      M30TranTypeVO tranvo = this.mapTrantype.get(tranid);
      Integer askrule = tranvo.getFaskqtrule();
      if (AskPriceRule.ASKPRICE_NO.equalsValue(askrule)) {
        this.trannoaskrows.add(Integer.valueOf(i));
        continue;
      }
      // --物料空不询价
      String marid = view.getBody().getCmaterialvid();
      if (PubAppTool.isNull(marid)) {
        continue;
      }
      // --根据参数赠品是否询价过滤
      UFBoolean blargess = view.getBody().getBlargessflag();

      Integer larask = tranvo.getFlargessgetqtrule();
      if (null != blargess && blargess.booleanValue()
          && !LargessGetqtRule.ASK_SALEQT.equalsValue(larask)) {
        continue;
      }
      alfindrow.add(view);
    }
    SaleOrderViewVO[] views = new SaleOrderViewVO[alfindrow.size()];
    alfindrow.toArray(views);
    return views;
  }

  /**
   * 
   * 
   * @param rows
   * @param editkey
   */
  public void forceFindPrice() {

    // 1.缓存交易类型VO
    this.cacheTranTypeVO();
    // 2.过滤出询价行
    SaleOrderViewVO[] askviews = this.filterFindViews();
    if (askviews.length > 0) {
      // 3.组织询价VOs
      Map<FindPriceParaVO, SaleOrderViewVO> mapparaview =
          new HashMap<FindPriceParaVO, SaleOrderViewVO>();
      MapList<String, FindPriceParaVO> maplistparaVOs =
          this.getFindPriceParaVOs(askviews, mapparaview);
      // 4.询价
      for (Entry<String, List<FindPriceParaVO>> entry : maplistparaVOs
          .entrySet()) {
        String pk_org = entry.getKey();
        List<FindPriceParaVO> listparavo = entry.getValue();
        FindPriceParaVO[] paraVOs = new FindPriceParaVO[listparavo.size()];
        listparavo.toArray(paraVOs);

        FindPriceResultVO[] resultVOs =
            this.getFindPriceResultVOs(paraVOs, pk_org);
        if (null == resultVOs || resultVOs.length == 0) {
          continue;
        }
        // 5.分离询价成功行与失败行
        this.splitFindResult(resultVOs, paraVOs, mapparaview);
      }
    }
    // 7.成功行：设值并单价金额计算
    this.setSucessResult();
    // 8.失败行：清空单价金额字段
    this.clearFailRow();
  }

  private void splitFindResult(FindPriceResultVO[] resultVOs,
      FindPriceParaVO[] paraVOs,
      Map<FindPriceParaVO, SaleOrderViewVO> mapparaview) {

    int i = 0;
    for (FindPriceResultVO result : resultVOs) {

      FindPriceParaVO paravo = paraVOs[i];
      SaleOrderViewVO view = mapparaview.get(paravo);

      if (null == result) {
        this.failrows.add(view);
      }
      else {
        this.hmSucess.put(view, result);
      }
      i++;
    }
  }

  private void cacheTranTypeVO() {

    this.mapTrantype = new HashMap<String, M30TranTypeVO>();
    Set<String> settranid = new HashSet<String>();
    for (SaleOrderViewVO view : this.orderviews) {
      String pk_org = view.getBody().getPk_org();
      if (PubAppTool.isNull(pk_org)) {
        continue;
      }
      String tranid = view.getHead().getCtrantypeid();
      if (PubAppTool.isNull(tranid)) {
        continue;
      }
      settranid.add(tranid);
    }

    if (settranid.size() > 0) {
      String[] tranids = new String[settranid.size()];
      settranid.toArray(tranids);

      IM30TranTypeService transrv =
          NCLocator.getInstance().lookup(IM30TranTypeService.class);
      M30TranTypeVO[] tranvos = null;
      try {
        tranvos = transrv.queryTranTypeVOs(tranids);
      }
      catch (BusinessException e) {
        ExceptionUtils.wrappException(e);
      }
      if (null != tranvos) {
        for (M30TranTypeVO tranvo : tranvos) {
          this.mapTrantype.put(tranvo.getCtrantypeid(), tranvo);
        }
      }
    }
  }

  /**
   * 方法功能描述：获得需要询价参数VOs
   * 
   * @param askrows
   * @author 刘志伟
   * @param mapparaview
   * @param mapindex
   * @time 2010-5-31 下午01:42:30
   */
  private MapList<String, FindPriceParaVO> getFindPriceParaVOs(
      SaleOrderViewVO[] askviews,
      Map<FindPriceParaVO, SaleOrderViewVO> mapparaview) {

    MapList<String, FindPriceParaVO> maplistpara =
        new MapList<String, FindPriceParaVO>();
    // 集团
    String pk_group = ClientContext.getInstance().getPk_group();

    for (SaleOrderViewVO view : askviews) {
      FindPriceParaVO paravo = new FindPriceParaVO();
      paravo.setPk_group(pk_group);
      // 销售组织
      String saleorg = view.getBody().getPk_org();
      paravo.setPk_org(saleorg);
      // 客户
      String customer = view.getHead().getCcustomerid();
      paravo.setPk_customer(customer);
      // 结算方式
      String balancetype = view.getHead().getCbalancetypeid();
      paravo.setPk_balatype(balancetype);
      // 渠道类型
      String channeltype = view.getHead().getCchanneltypeid();
      paravo.setPk_channeltype(channeltype);
      // 原币币种
      String currtype = view.getHead().getCorigcurrencyid();
      paravo.setPk_currtype(currtype);
      // 单据日期
      UFDate billdate = view.getHead().getDbilldate();
      UFDateTime datetime = null;
      if (null != billdate) {
        datetime = new UFDateTime(billdate.toString());
      }
      paravo.setTpricedate(datetime);

      UFDouble num = view.getBody().getNqtunitnum();
      paravo.setNnum(null == num ? UFDouble.ONE_DBL : num);

      String materialid = view.getBody().getCmaterialid();
      paravo.setPk_material(materialid);

      String unit = view.getBody().getCqtunitid();
      paravo.setPk_unit(unit);

      // 交易类型
      String trantypeid = view.getHead().getCtrantypeid();
      paravo.setVsaleorgtype(trantypeid);

      // 正常询价即询促销价
      M30TranTypeVO tranvo = this.mapTrantype.get(trantypeid);
      Integer askqtrule = tranvo.getFaskqtrule();
      UFBoolean ispromote = UFBoolean.FALSE;
      if (AskPriceRule.ASKPRICE_NORMAL.equalsValue(askqtrule)) {
        ispromote = UFBoolean.TRUE;
      }

      paravo.setIsFindPromotePrice(ispromote);
      // 运输方式
      String tranporttype = view.getHead().getCtransporttypeid();
      paravo.setPk_sendtype(tranporttype);

      // 质量等级
      String qualityleve = view.getBody().getCqualitylevelid();
      paravo.setPk_qualitylevel(qualityleve);
      // 收货地区
      String receivearea = view.getBody().getCreceiveareaid();
      paravo.setPk_areacl(receivearea);
      // 自由辅助属性
      String vfree1 = view.getBody().getVfree1();
      paravo.setVfree1(vfree1);
      String vfree2 = view.getBody().getVfree2();
      paravo.setVfree2(vfree2);
      String vfree3 = view.getBody().getVfree3();
      paravo.setVfree3(vfree3);
      String vfree4 = view.getBody().getVfree4();
      paravo.setVfree4(vfree4);
      String vfree5 = view.getBody().getVfree5();
      paravo.setVfree5(vfree5);
      String vfree6 = view.getBody().getVfree6();
      paravo.setVfree6(vfree6);
      String vfree7 = view.getBody().getVfree7();
      paravo.setVfree7(vfree7);
      String vfree8 = view.getBody().getVfree8();
      paravo.setVfree8(vfree8);
      String vfree9 = view.getBody().getVfree9();
      paravo.setVfree9(vfree9);
      String vfree10 = view.getBody().getVfree10();
      paravo.setVfree10(vfree10);
      
      mapparaview.put(paravo, view);
      maplistpara.put(saleorg, paravo);
    }

    return maplistpara;
  }

  private FindPriceResultVO[] getFindPriceResultVOs(FindPriceParaVO[] paraVOs,
      String saleOrg) {
    PluginExecutor<ISOFindPrice> executor =
        new PluginExecutor<ISOFindPrice>(ISOFindPrice.class);
    FindPricePluginExecDelegate delegate =
        new FindPricePluginExecDelegate(paraVOs, saleOrg);
    try {
      executor.exec(delegate);
    }
    catch (Exception e) {
      ExceptionUtils.wrappException(e);
    }
    return delegate.getFindPriceResultVOs();
  }

  private boolean getIsTax() {

    String pk_group = ClientContext.getInstance().getPk_group();
    UFBoolean so23 = SOSysParaInitUtil.getSO23(pk_group);
    if (null == so23) {
      return false;
    }

    return so23.booleanValue();
  }

  /**
   * 将询到的销售价格设置到卡片上,包括：
   * <ul>
   * <li>含税单价
   * <li>单品折扣
   * <li>定价策略
   * <li>价格项
   * <li>价目表
   * <li>价格组成
   * </ul>
   * 
   * @author 刘志伟
   * @time 2010-5-31 下午01:42:30
   */
  private void setSucessResult() {
    // 询价成功行设置并计算单价金额
    boolean istax = this.getIsTax();
    // 含税/无税单价
    String pricekey =
        istax ? SOItemKey.NQTORIGTAXPRICE : SOItemKey.NQTORIGPRICE;
    String askpricekey =
        istax ? SOItemKey.NASKQTORIGTAXPRC : SOItemKey.NASKQTORIGPRICE;

    // 含税/无税净价
    String netpricekey =
        istax ? SOItemKey.NQTORIGTAXNETPRC : SOItemKey.NQTORIGNETPRICE;
    String asknetpricekey =
        istax ? SOItemKey.NASKQTORIGTXNTPRC : SOItemKey.NASKQTORIGNETPRICE;

    for (Entry<SaleOrderViewVO, FindPriceResultVO> entry : this.hmSucess
        .entrySet()) {
      SaleOrderViewVO view = entry.getKey();
      FindPriceResultVO resultVO = entry.getValue();

      view.setAttributeValue(pricekey, resultVO.getPrice());
      view.setAttributeValue(netpricekey, resultVO.getNetPrice());

      // 询价原币含税/无税单价
      view.setAttributeValue(askpricekey, resultVO.getPrice());
      // 询价原币含税/无税净价
      view.setAttributeValue(asknetpricekey, resultVO.getNetPrice());

      // 单品折扣
      view.setAttributeValue(SOItemKey.NITEMDISCOUNTRATE,
          resultVO.getDiscount());

      // 定价策略
      view.setAttributeValue(SOItemKey.CPRICEPOLICYID,
          resultVO.getPricePolicy());

      // 价格项
      view.setAttributeValue(SOItemKey.CPRICEITEMID, resultVO.getPriceType());

      // 价目表
      view.setAttributeValue(SOItemKey.CPRICEITEMTABLEID, resultVO.getTariff());

      // 价格组成
      view.setAttributeValue(SOItemKey.CPRICEFORMID, resultVO.getPk_priceform());
    }
    Set<SaleOrderViewVO> setviews = this.hmSucess.keySet();
    SaleOrderViewVO[] sucessviews = new SaleOrderViewVO[setviews.size()];
    setviews.toArray(sucessviews);

    // 数量单价金额运算
    SaleOrderViewCalculator calcultor =
        new SaleOrderViewCalculator(sucessviews);
    calcultor.setChangePrice(UFBoolean.FALSE);
    calcultor.calculate(netpricekey);
  }

  /**
   * 设置询价失败行信息
   */
  public String getFailMsg() {

    if (this.notranrows.size() == 0 && this.trannoaskrows.size() == 0
        && this.failrows.size() == 0) {
      return null;

    }

    StringBuilder failMsg = new StringBuilder();
    if (this.notranrows.size() > 0) {
      StringBuilder failMsgRow = new StringBuilder();
      for (Integer row : this.notranrows) {
        failMsgRow.append(NCLangRes.getInstance().getStrByID("4006012_0",
            "04006012-0070", null, new String[] {
            Integer.toString(row)
        })/*[{0}]、*/);
      }
      failMsgRow.deleteCharAt(failMsgRow.length() - 1);
      failMsg.append(NCLangRes.getInstance().getStrByID("4006012_0",
          "04006012-0071", null, new String[] {
          failMsgRow.toString()
      })/*第 {0} 行交易类型为空，无法询价！\n\r*/);
    }
    if (this.trannoaskrows.size() > 0) {
      StringBuilder failMsgRow = new StringBuilder();
      for (Integer row : this.trannoaskrows) {
        failMsgRow.append(NCLangRes.getInstance().getStrByID("4006012_0",
            "04006012-0070", null, new String[] {
            Integer.toString(row)
        })/*[{0}]、*/);
      }
      failMsgRow.deleteCharAt(failMsgRow.length() - 1);
      // failMsg.append("第" + failMsgRow.toString() + "行交易类型询价规则为不询价！\n\r");
      failMsg.append(NCLangRes.getInstance().getStrByID("4006012_0",
          "04006012-0092", null, new String[] {
          failMsgRow.toString()
      })/*第 {0} 行交易类型为空，无法询价！\n\r*/);
    }
    if (this.failrows.size() > 0) {
      StringBuilder failMsgRow = new StringBuilder();
      for (SaleOrderViewVO view : this.failrows) {
        Integer failrow = this.mapindex.get(view);
        failMsgRow.append(NCLangRes.getInstance().getStrByID("4006012_0",
            "04006012-0070", null, new String[] {
            Integer.toBinaryString(failrow)
        })/*[{0}]、*/);
      }
      failMsgRow.deleteCharAt(failMsgRow.length() - 1);
      failMsg.append(NCLangRes.getInstance().getStrByID("4006012_0",
          "04006012-0072", null, new String[] {
          failMsgRow.toString()
      })/*第 {0} 行询价失败！*/);
    }
    return failMsg.toString();
  }

  public List<SaleOrderViewVO> getChangeView() {
    List<SaleOrderViewVO> arychgview = new ArrayList<SaleOrderViewVO>();
    if (this.failrows.size() > 0) {
      arychgview.addAll(this.failrows);
    }
    if (this.hmSucess.keySet().size() > 0) {
      arychgview.addAll(this.hmSucess.keySet());
    }
    return arychgview;
  }
}
