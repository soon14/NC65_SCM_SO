package nc.ui.so.m32.billui.pub;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.HashSet;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;
import java.util.Set;

import nc.bs.framework.common.NCLocator;
import nc.itf.scmpub.IScmpubMaintain;
import nc.itf.scmpub.reference.uap.bd.material.MaterialBaseClassPubService;
import nc.itf.scmpub.reference.uap.bd.material.MaterialPubService;
import nc.itf.so.m32.ISaleInvoiceMaintain;
import nc.ui.pubapp.pub.scale.UIScaleUtils;
import nc.vo.pub.BusinessException;
import nc.vo.pub.CircularlyAccessibleValueObject;
import nc.vo.pub.VOStatus;
import nc.vo.pub.lang.UFBoolean;
import nc.vo.pub.lang.UFDouble;
import nc.vo.pubapp.AppContext;
import nc.vo.pubapp.pattern.exception.ExceptionUtils;
import nc.vo.pubapp.pattern.model.transfer.bill.ClientBillCombinServer;
import nc.vo.pubapp.pattern.pub.Constructor;
import nc.vo.pubapp.pattern.pub.MapList;
import nc.vo.pubapp.pattern.pub.MathTool;
import nc.vo.pubapp.pattern.pub.PubAppTool;
import nc.vo.pubapp.scale.ScaleUtils;
import nc.vo.so.m32.entity.SaleInvoiceBVO;
import nc.vo.so.m32.entity.SaleInvoiceHVO;
import nc.vo.so.m32.entity.SaleInvoiceVO;
import nc.vo.so.m32.paravo.CombinCacheVO;
import nc.vo.so.m32.paravo.CombinContext;
import nc.vo.so.m32.paravo.CombinResultVO;
import nc.vo.so.m32.util.SaleInvoiceVOCalculator;
import nc.vo.so.m32.util.SaleInvoiceVOMerger;
import nc.vo.so.pub.SOItemKey;
import nc.vo.so.pub.comparator.RowNoComparator;
import nc.vo.so.pub.enumeration.SOFInvoiceKey2Code;
import nc.vo.so.pub.res.ParameterList;
import nc.vo.so.pub.rule.SOCalConditionRule;
import nc.vo.so.pub.util.ListUtil;
import nc.vo.so.pub.util.SOSysParaInitUtil;

/**
 * 销售发票合并显示和合并编辑处理类
 * 
 * @since 6.3
 * @version 2012-12-21 上午10:56:29
 * @author yaogj
 */
public class SaleInvoiceCombin {

  /**
   * 解密map
   * code => key
   */
  private Map<String, String> mapTransCodeToKey;

  /**
   * 获取密码值对照Map
   * 
   * @return Map<String, String>
   */
  public Map<String, String> getTransCodeToKey() {
    if (null == this.mapTransCodeToKey) {
      this.mapTransCodeToKey = new LinkedHashMap<String, String>();
      for (SOFInvoiceKey2Code rule : SOFInvoiceKey2Code.values()) {
        this.mapTransCodeToKey.put(rule.getCode(), rule.getKey());
      }
    }
    return this.mapTransCodeToKey;
  }

  /**
   * 集团发票显示方式：汇总 时，对销售发票进行汇总
   * 
   * @param detailvos 明细vos
   * @param cachevo 缓存vo
   * @return 合并数据
   */
  public CombinResultVO combinSaleInvoices(SaleInvoiceVO[] detailvos,
      CombinCacheVO cachevo) {

    CombinResultVO combinpara = new CombinResultVO(true);
    combinpara.setCachevo(cachevo);
    if (null != detailvos) {
      this.combinDetails(detailvos, combinpara);
    }
    return combinpara;
  }

  /**
   * 从缓存中删除VOS
   * 的合并关系
   * 
   * @param vos
   * @param combinrela
   */
  public void deleteCombinRelation(SaleInvoiceVO[] vos,
      MapList<String, SaleInvoiceBVO> combinrela) {
    for (SaleInvoiceVO vo : vos) {
      SaleInvoiceBVO[] bvos = vo.getChildrenVO();
      for (SaleInvoiceBVO bvo : bvos) {
        String key = bvo.getCsaleinvoicebid();
        combinrela.remove(key);
      }
    }

  }

  /**
   * 合并编辑下参照增行与原来的行合并
   * 
   * @param detainvo 原来界面上的明细VO
   * @param newvos 参照增行古来的新VO
   * @param cachevo 缓存
   * @return 发票vo
   */
  public SaleInvoiceVO getCombinVOByRefAndLine(SaleInvoiceVO detainvo,
      SaleInvoiceVO[] newvos, CombinCacheVO cachevo) {
    // SaleInvoiceHVO hvo = oldvo.getParentVO();
    List<SaleInvoiceBVO> bvos = new ArrayList<SaleInvoiceBVO>();
    for (SaleInvoiceBVO bvo : detainvo.getChildrenVO()) {
      bvos.add(bvo);
    }
    for (SaleInvoiceVO newvo : newvos) {
      for (SaleInvoiceBVO bvo : newvo.getChildrenVO()) {
        bvos.add(bvo);
      }
    }
    detainvo.setChildrenVO(bvos.toArray(new SaleInvoiceBVO[bvos.size()]));
    this.deleteCombinRelation(new SaleInvoiceVO[] {
      detainvo
    }, cachevo.getCombinRela());

    CombinResultVO comvo = this.combinSaleInvoices(new SaleInvoiceVO[] {
      detainvo
    }, cachevo);
    return comvo.getCombinvos()[0];
  }

  /**
   * 审批弃审、删除等操作获取最新的UIVO
   * 
   * @param cachevo 缓存vo
   * @param oldcombinvo 旧的合并vo
   * @param olddetailvos 旧的明细vo
   * @param pretObj 明细vo
   * @return 发票vo
   */
  public SaleInvoiceVO[] getNewCombinUIVOS(CombinCacheVO cachevo,
      SaleInvoiceVO[] oldcombinvo, SaleInvoiceVO[] olddetailvos,
      Object[] pretObj) {
    SaleInvoiceVO[] retdetailvos = (SaleInvoiceVO[]) pretObj;

    new ClientBillCombinServer<SaleInvoiceVO>().combine(olddetailvos,
        retdetailvos);
    List<SaleInvoiceBVO> oldcombinbvos = new ArrayList<SaleInvoiceBVO>();
    List<SaleInvoiceBVO> newdetbvos = new ArrayList<SaleInvoiceBVO>();
    for (SaleInvoiceVO vo : oldcombinvo) {
      SaleInvoiceBVO[] bvos = vo.getChildrenVO();
      for (SaleInvoiceBVO bvo : bvos) {
        oldcombinbvos.add(bvo);
      }
    }
    for (SaleInvoiceVO vo : olddetailvos) {
      SaleInvoiceBVO[] bvos = vo.getChildrenVO();
      for (SaleInvoiceBVO bvo : bvos) {
        newdetbvos.add(bvo);
      }
    }

    MapList<String, SaleInvoiceBVO> cachebvomap = cachevo.getCombinRela();
    SaleInvoiceCombin combin = new SaleInvoiceCombin();
    // 更新合并关系
    combin.updateNoEditCombinRela(ListUtil.toArray(oldcombinbvos),
        ListUtil.toArray(newdetbvos), cachebvomap);

    SaleInvoiceVO[] newcombinvos = new SaleInvoiceVO[oldcombinvo.length];
    for (int i = 0; i < oldcombinvo.length; i++) {
      newcombinvos[i] = (SaleInvoiceVO) oldcombinvo[i].clone();
      newcombinvos[i].setParentVO(retdetailvos[i].getParentVO());
    }
    return newcombinvos;
  }

  /**
   * 还原明细数据（审批、弃审、提交、收回）
   * 
   * @param oldconbinvos
   * @param combinRela
   * @return 发票vo
   */
  public SaleInvoiceVO[] getOldDetailVOs(SaleInvoiceVO[] oldconbinvos,
      MapList<String, SaleInvoiceBVO> combinRela) {
    List<SaleInvoiceVO> volist = new ArrayList<SaleInvoiceVO>();
    for (SaleInvoiceVO vo : oldconbinvos) {
      SaleInvoiceHVO hvo = vo.getParentVO();
      SaleInvoiceBVO[] bvos = vo.getChildrenVO();
      List<SaleInvoiceBVO> oldbvolist = new ArrayList<SaleInvoiceBVO>();
      for (SaleInvoiceBVO bvo : bvos) {
        String bpk = bvo.getCsaleinvoicebid();
        List<SaleInvoiceBVO> bvoslist = combinRela.get(bpk);
        if (null == bvoslist) {
          continue;
        }
        oldbvolist.addAll(bvoslist);
      }
      SaleInvoiceVO oldvo = new SaleInvoiceVO();
      oldvo.setParentVO(hvo);
      oldvo.setChildrenVO(oldbvolist.toArray(new SaleInvoiceBVO[oldbvolist
          .size()]));
      volist.add(oldvo);
    }
    return volist.toArray(new SaleInvoiceVO[volist.size()]);
  }

  /**
   * 返回集团销售发票显示方式
   * 
   * @return 合并显示参数
   */
  public boolean getSO27() {
    String pk_group = AppContext.getInstance().getPkGroup();
    String so27 = null;

    so27 = SOSysParaInitUtil.getSO27(pk_group);

    if ("汇总".equals(so27)) { /* -=notranslate=- */
      return true;
    }
    return false;
  }

  /**
   * 对冲发票时补充表体ID
   * 用在对冲发票
   * 
   * @param detailvos
   */
  public void processVOBids(SaleInvoiceVO[] detailvos) {
    int blength = 0;
    for (SaleInvoiceVO vo : detailvos) {
      blength += vo.getChildrenVO().length;
    }

    IScmpubMaintain srv = NCLocator.getInstance().lookup(IScmpubMaintain.class);
    String[] bids;
    try {
      bids = srv.getIDs(blength);
    }
    catch (BusinessException ex) {
      ExceptionUtils.wrappException(ex);
      return;
    }
    int i = 0;
    for (SaleInvoiceVO vo : detailvos) {
      SaleInvoiceBVO[] bvos = vo.getChildrenVO();
      for (SaleInvoiceBVO bvo : bvos) {
        bvo.setCsaleinvoicebid(bids[i]);
        i++;
      }
    }
  }

  /**
   * 编辑状态还原明细VO
   * 
   * @param combinvo
   * @param combinrela
   * @return 发票vo
   */
  public SaleInvoiceVO splitEditSaleInvoice(SaleInvoiceVO combinvo,
      MapList<String, SaleInvoiceBVO> combinrela) {
    List<SaleInvoiceBVO> listdetail = new ArrayList<SaleInvoiceBVO>();
    boolean isnew = this.isNew(combinvo);
    String pk_org = combinvo.getParentVO().getPk_org();
    Map<String, String> mParas =
        SOSysParaInitUtil.queryBatchParaStringValues(new String[] {
          pk_org
        }, ParameterList.SO28.getCode() + ParameterList.SUFFIX);
    // String[] combinparary =
    // mParas.get(pk_org).split(ParameterList.BIGSPLITKEY);
    String[] combinparary = this.getGroupKeys(mParas.get(pk_org));
    SaleInvoiceVO detailvo = new SaleInvoiceVO();
    SaleInvoiceHVO headvo = (SaleInvoiceHVO) combinvo.getParentVO().clone();
    detailvo.setParentVO(headvo);
    // 表体有增行 bid是空的 这里进行补充
    String[] newids = this.getNewID(combinvo);
    int i = 0;
    for (SaleInvoiceBVO combvo : combinvo.getChildrenVO()) {
      // 新增行
      String bodypk = combvo.getPrimaryKey();
      if (PubAppTool.isNull(bodypk)) {
        if (PubAppTool.isNull(combvo.getCsrcid())) {
          String newid = newids[i];
          combvo.setCsaleinvoicebid(newid);
          listdetail.add(combvo);
          combinrela.put(newid, combvo);
          i++;
        }
        continue;
      }
      List<SaleInvoiceBVO> cachedetbvos = combinrela.get(bodypk);
      if (null == cachedetbvos || cachedetbvos.size() == 0) {
        if (!"isnull".equals(bodypk)) {
          listdetail.add(combvo);
        }

      }
      else {
        if (isnew) {
          listdetail.addAll(this.processNew(headvo, combvo, cachedetbvos,
              combinparary));
        }
        else {
          listdetail.addAll(this.processUpdate(headvo, combvo, cachedetbvos,
              combinparary));
        }
      }
    }
    SaleInvoiceBVO[] detailbvos = new SaleInvoiceBVO[listdetail.size()];
    listdetail.toArray(detailbvos);
    detailvo.setChildrenVO(detailbvos);
    return detailvo;
  }

  private String[] getNewID(SaleInvoiceVO combinvo) {
    String[] ids = null;
    int i = 0;
    for (SaleInvoiceBVO combvo : combinvo.getChildrenVO()) {
      // 新增行
      String bodypk = combvo.getPrimaryKey();
      String srcid = combvo.getCsrcid();
      if (PubAppTool.isNull(bodypk) && PubAppTool.isNull(srcid)) {
        i++;
      }
    }
    if (i > 0) {
      IScmpubMaintain srv =
          NCLocator.getInstance().lookup(IScmpubMaintain.class);
      try {
        ids = srv.getIDs(i);
      }
      catch (BusinessException ex) {
        ExceptionUtils.wrappException(ex);
      }
    }
    return ids;
  }

  /**
   * 非编辑态还原明细状态数据
   * 
   * @param combinvos
   * @param combinrela
   * @return 发票vo
   */
  public SaleInvoiceVO[] splitNoEditSaleInvoice(SaleInvoiceVO[] combinvos,
      MapList<String, SaleInvoiceBVO> combinrela) {
    List<SaleInvoiceVO> detailvos = new ArrayList<SaleInvoiceVO>();
    for (SaleInvoiceVO combinvo : combinvos) {
      SaleInvoiceVO detailvo = new SaleInvoiceVO();
      if (combinvo.getChildrenVO() == null
          || combinvo.getChildrenVO().length == 0) {
        detailvo.setParentVO((SaleInvoiceHVO) combinvo.getParentVO().clone());
        detailvo.setChildrenVO(null);
        detailvos.add(detailvo);
        continue;
      }
      List<SaleInvoiceBVO> listdetail = new ArrayList<SaleInvoiceBVO>();
      for (SaleInvoiceBVO combvo : combinvo.getChildrenVO()) {
        String key = combvo.getPrimaryKey();
        if (null == key || "isnull".equals(key)) {
          continue;
        }

        List<SaleInvoiceBVO> cachebvo = combinrela.get(key);
        if (null == cachebvo) {
          listdetail.add(combvo);
        }
        else {
          listdetail.addAll(cachebvo);
        }

      }
      SaleInvoiceHVO headvo = (SaleInvoiceHVO) combinvo.getParentVO().clone();
      detailvo.setParentVO(headvo);

      SaleInvoiceBVO[] bodyvos = new SaleInvoiceBVO[listdetail.size()];
      detailvo.setChildrenVO(listdetail.toArray(bodyvos));
      detailvos.add(detailvo);
    }
    return detailvos.toArray(new SaleInvoiceVO[detailvos.size()]);
  }

  /**
   * 删除动作后更新合并和明细的缓存关系
   * 
   * @param oldcombinbvos
   * @param combinRela
   */
  public void updateCombinRela(SaleInvoiceBVO[] oldcombinbvos,
      MapList<String, SaleInvoiceBVO> combinRela) {

    for (SaleInvoiceBVO oldbvo : oldcombinbvos) {
      combinRela.toMap().remove(oldbvo.getPrimaryKey());
    }
  }

  /**
   * 保存动作后更新合并和明细的缓存关系
   * 
   * @param oldcombinbvos
   * @param newbvos
   * @param combinRela
   * @param setDelbids
   */
  public void updateEditCombinRela(SaleInvoiceBVO[] oldcombinbvos,
      SaleInvoiceBVO[] newbvos, MapList<String, SaleInvoiceBVO> combinRela,
      Set<String> setDelbids) {
    Map<String, SaleInvoiceBVO> mapNew = new HashMap<String, SaleInvoiceBVO>();
    for (SaleInvoiceBVO bvo : newbvos) {
      mapNew.put(bvo.getPrimaryKey(), bvo);
    }
    Set<String> setOldKey = new HashSet<String>();
    for (SaleInvoiceBVO oldbvo : oldcombinbvos) {
      String key = oldbvo.getPrimaryKey();
      if (VOStatus.DELETED == oldbvo.getStatus()) {
        combinRela.toMap().remove(key);
      }
      else {
        List<SaleInvoiceBVO> oldbvos = combinRela.get(key);
        if (null == oldbvos) {
          continue;
        }
        combinRela.remove(key);
        for (int i = oldbvos.size() - 1; i >= 0; i--) {
          SaleInvoiceBVO bvo = oldbvos.get(i);
          String bvokey = bvo.getPrimaryKey();
          if (!setDelbids.contains(bvokey)) {
            SaleInvoiceBVO newbvo = mapNew.get(bvokey);
            if (null != newbvo) {
              combinRela.put(key, newbvo);
              setOldKey.add(bvokey);
            }
          }
        }
      }
    }
    for (SaleInvoiceBVO bvo : newbvos) {
      String key = bvo.getPrimaryKey();
      if (!setOldKey.contains(key)) {
        combinRela.put(key, bvo);
      }
    }
  }

  /**
   * 送审、审核、弃审等非编辑动作后更新合并和明细的缓存关系
   * 
   * @param oldcombinbvos
   * @param newdetbvos
   * @param combinRela
   */
  public void updateNoEditCombinRela(SaleInvoiceBVO[] oldcombinbvos,
      SaleInvoiceBVO[] newdetbvos, MapList<String, SaleInvoiceBVO> combinRela) {
    Map<String, SaleInvoiceBVO> mapNew = new HashMap<String, SaleInvoiceBVO>();
    for (SaleInvoiceBVO bvo : newdetbvos) {
      mapNew.put(bvo.getPrimaryKey(), bvo);
    }
    for (SaleInvoiceBVO oldbvo : oldcombinbvos) {
      String key = oldbvo.getPrimaryKey();
      List<SaleInvoiceBVO> oldbvos = combinRela.get(key);
      if (null == oldbvos) {
        continue;
      }
      for (int i = 0; i < oldbvos.size(); i++) {
        SaleInvoiceBVO bvo = oldbvos.get(i);
        oldbvos.set(i, mapNew.get(bvo.getPrimaryKey()));
      }
    }
  }

  /**
   * 
   * @return 得到价格字段
   */
  public static String getCalPriceKey() {
    if (SOCalConditionRule.isTaxPrior()) {
      return SOItemKey.NORIGTAXPRICE;
    }

    return SOItemKey.NORIGPRICE;
  }

  /**
   * 
   * @return 得到金额字段
   */
  public static String getCalMnyKey() {
    if (SOCalConditionRule.isTaxPrior()) {
      return SOItemKey.NORIGTAXMNY;
    }

    return SOItemKey.NORIGMNY;
  }

  private void checkNewPriceMnyChg(SaleInvoiceHVO headvo,
      SaleInvoiceBVO combvo, List<SaleInvoiceBVO> retdetial) {
    SaleInvoiceVO voInvoice = new SaleInvoiceVO();
    voInvoice.setParentVO(headvo);
    SaleInvoiceBVO[] bvos = new SaleInvoiceBVO[retdetial.size()];
    retdetial.toArray(bvos);
    voInvoice.setChildrenVO(bvos);
    SaleInvoiceVOCalculator calc = new SaleInvoiceVOCalculator(voInvoice);
    // 原币金额是否改变
    boolean isorigmnychange = this.isOrigMnyChange(combvo, retdetial);

    // 本币金额是否改变
    boolean ismnychange = this.isMnyChange(combvo, retdetial);
    if (!isorigmnychange) {
      // 原币没变 本币变了 认为 是折本汇率发生变化
      if (ismnychange) {
        calc.calculateAll(SaleInvoiceHVO.NEXCHANGERATE);
      }
      return;
    }
    String pricekey = SaleInvoiceCombin.getCalPriceKey();
    UFDouble oldprice = this.getOldPrice(retdetial);
    UFDouble nowprice = (UFDouble) combvo.getAttributeValue(pricekey);

    /*
     * int size = retdetial.size(); // 金额值改变，如果单价没有改变说明数量改变，只需倒挤最后一行金额 if
     * (isorigmnychange && MathTool.equals(oldprice, nowprice)) {
     * this.processCombinMargin(combvo, retdetial, size, calc); // 金额值改变，并且单价改变
     * 需要把所有明细的单价改变，重新计算金额，倒挤最后一行金额 } else if (isorigmnychange &&
     * !MathTool.equals(oldprice, nowprice)) { UFDouble ntaxrate =
     * combvo.getNtaxrate(); for (SaleInvoiceBVO bvo : retdetial) {
     * bvo.setNorigprice(nowprice); bvo.setNtaxrate(ntaxrate); }
     * calc.calculateAll(pricekey);
     */

    String mnykey = SaleInvoiceCombin.getCalMnyKey();
    UFDouble oldMny = this.getOldMny(retdetial);
    ScaleUtils scale = UIScaleUtils.getScaleUtils();
    String currid = headvo.getCcurrencyid();
    UFDouble nowMny = (UFDouble) combvo.getAttributeValue(mnykey);
    int size = retdetial.size();

    // 金额值改变，如果单价没有改变说明数量改变，只需倒挤最后一行金额
    if (isorigmnychange && MathTool.equals(oldprice, nowprice)) {
      this.processCombinMargin(combvo, retdetial, size, calc);
      // 金额值改变，并且单价改变 需要把所有明细的单价改变，重新计算金额，倒挤最后一行金额
    }
    else if (isorigmnychange && !MathTool.equals(oldprice, nowprice)) {
      UFDouble ntaxrate = combvo.getNtaxrate();
      // 用价税合计触发单价金额算法，按每行原来的价税合计比例分摊新的价税合计
      UFDouble nowTotalBodyMny = UFDouble.ZERO_DBL;
      SaleInvoiceBVO[] bvo =
          retdetial.toArray(new SaleInvoiceBVO[retdetial.size()]);
      for (int i = 0; i < bvo.length - 1; i++) {
        UFDouble bodyoldMny = (UFDouble) bvo[i].getAttributeValue(mnykey);
        UFDouble nowbodyMny =
            scale.adjustMnyScale(nowMny.multiply(bodyoldMny).div(oldMny),
                currid);
        bvo[i].setAttributeValue(mnykey, nowbodyMny);
        bvo[i].setNtaxrate(ntaxrate);
        nowTotalBodyMny = nowTotalBodyMny.add(nowbodyMny);
      }
      bvo[bvo.length - 1]
          .setAttributeValue(mnykey, nowMny.sub(nowTotalBodyMny));
      bvo[bvo.length - 1].setNtaxrate(ntaxrate);
      calc.calculateAll(mnykey);
      // 倒挤尾差
      this.processCombinMargin(combvo, retdetial, size, calc);
    }
  }

  /**
   * 旧的金额
   * 
   * @param retdetial
   * @return
   */
  private UFDouble getOldMny(List<SaleInvoiceBVO> retdetial) {
    String mnykey = SaleInvoiceCombin.getCalMnyKey();
    UFDouble oldtotalmny = UFDouble.ZERO_DBL;
    for (SaleInvoiceBVO bvo : retdetial) {
      oldtotalmny =
          MathTool.add(oldtotalmny, (UFDouble) bvo.getAttributeValue(mnykey));
    }
    return oldtotalmny;
  }

  /**
   * 校验数据是否变化
   * 
   * @param headvo
   * @param combvo
   * @param detailbvos
   * @param retdetail
   * @param isnew true是新增 false 为更新
   */
  private void checkNumChange(SaleInvoiceHVO headvo, SaleInvoiceBVO combvo,
      List<SaleInvoiceBVO> detailbvos, List<SaleInvoiceBVO> retdetail,
      boolean isnew, String[] combinparary) {
    SaleInvoiceVOCalculator calc = new SaleInvoiceVOCalculator();
    UFDouble oldtotalnum = UFDouble.ZERO_DBL;
    List<SaleInvoiceBVO> clonebvos = new ArrayList<SaleInvoiceBVO>();
    for (SaleInvoiceBVO bvo : detailbvos) {
      SaleInvoiceBVO clonebvo = (SaleInvoiceBVO) bvo.clone();
      for (String key : combinparary) {
        List<String> exceptMnyFileds = getExceptMnyFileds();
        // jilu for 633 修改价税合计时，会自动计算净价及发票折扣，此处再更具发票折扣反算净价，就错了
        if (!exceptMnyFileds.contains(key)) {
          // end
          // 有变化则重新赋值
          if (combvo.getAttributeValue(key) != null
              && !combvo.getAttributeValue(key).equals(
                  clonebvo.getAttributeValue(key))
              || clonebvo.getAttributeValue(key) != null
              && !clonebvo.getAttributeValue(key).equals(
                  combvo.getAttributeValue(key))) {
            clonebvo.setAttributeValue(key, combvo.getAttributeValue(key));
            clonebvo.setAttributeValue(SaleInvoiceBVO.VCHANGERATE,
                combvo.getAttributeValue(SaleInvoiceBVO.VCHANGERATE));
            SaleInvoiceVO invoice = new SaleInvoiceVO();
            invoice.setParentVO(headvo);
            invoice.setChildrenVO(new SaleInvoiceBVO[] {
              clonebvo
            });
            calc.setVoInvoice(invoice);
            calc.calculate(0, key);
          }
        }
      }
      if (null != combvo.getAttributeValue(SaleInvoiceBVO.VCHANGERATE)
          && !combvo.getAttributeValue(SaleInvoiceBVO.VCHANGERATE).equals(
              clonebvo.getAttributeValue(SaleInvoiceBVO.VCHANGERATE))) {
        clonebvo.setAttributeValue(SaleInvoiceBVO.VCHANGERATE,
            combvo.getAttributeValue(SaleInvoiceBVO.VCHANGERATE));
        SaleInvoiceVO invoice = new SaleInvoiceVO();
        invoice.setParentVO(headvo);
        invoice.setChildrenVO(new SaleInvoiceBVO[] {
          clonebvo
        });
        calc.setVoInvoice(invoice);
        calc.calculate(0, SaleInvoiceBVO.VCHANGERATE);
      }
      if (null != combvo.getAttributeValue(SaleInvoiceBVO.VQTUNITRATE)
          && !combvo.getAttributeValue(SaleInvoiceBVO.VQTUNITRATE).equals(
              clonebvo.getAttributeValue(SaleInvoiceBVO.VQTUNITRATE))) {
        clonebvo.setAttributeValue(SaleInvoiceBVO.VQTUNITRATE,
            combvo.getAttributeValue(SaleInvoiceBVO.VQTUNITRATE));
        SaleInvoiceVO invoice = new SaleInvoiceVO();
        invoice.setParentVO(headvo);
        invoice.setChildrenVO(new SaleInvoiceBVO[] {
          clonebvo
        });
        calc.setVoInvoice(invoice);
        calc.calculate(0, SaleInvoiceBVO.VQTUNITRATE);
      }

      clonebvos.add(clonebvo);
      oldtotalnum = MathTool.add(oldtotalnum, bvo.getNnum());
    }
    // 检查数量
    UFDouble nowtotalnum = combvo.getNnum();

    if (MathTool.isDiffSign(nowtotalnum, oldtotalnum)) {
      // ExceptionUtils.wrappBusinessException("不允许修改开票数量的符号");
      ExceptionUtils.wrappBusinessException(nc.vo.ml.NCLangRes4VoTransl
          .getNCLangRes().getStrByID("4006008_0", "04006008-0127")/*
                                                                   * @res
                                                                   * "不允许修改开票数量的符号"
                                                                   */);
    }

    if (MathTool.absCompareTo(nowtotalnum, oldtotalnum) > 0
        && !MathTool.isZero(oldtotalnum)) {
      ExceptionUtils.wrappBusinessException(nc.vo.ml.NCLangRes4VoTransl
          .getNCLangRes().getStrByID("4006008_0", "04006008-0010")/*
                                                                   * @res
                                                                   * "不允许编辑数量大于携带数量"
                                                                   */);
    }
    else if (!MathTool.isZero(oldtotalnum)) {
      UFDouble remainnum = nowtotalnum;

      for (SaleInvoiceBVO bvo : clonebvos) {
        if (MathTool.absCompareTo(remainnum, bvo.getNnum()) >= 0) {
          remainnum = MathTool.sub(remainnum, bvo.getNnum());
          retdetail.add(bvo);
        }
        else if (remainnum.compareTo(UFDouble.ZERO_DBL) == 0) {
          if (isnew) {
            break;
          }
          bvo.setStatus(VOStatus.DELETED);
          retdetail.add(bvo);
        }
        else if (MathTool.absCompareTo(remainnum, UFDouble.ZERO_DBL) > 0) {
          bvo.setNnum(remainnum);
          SaleInvoiceVO invoice = new SaleInvoiceVO();
          invoice.setParentVO(headvo);
          invoice.setChildrenVO(new SaleInvoiceBVO[] {
            bvo
          });
          calc.setVoInvoice(invoice);
          calc.calculate(0, SaleInvoiceBVO.NNUM);
          retdetail.add(bvo);
          break;
        }
      }
    }
    else {
      retdetail.addAll(clonebvos);
    }
  }

  /**
   * 当以下字段包含在内时，跳过方法。
   * 
   * 
   * */
  public List<String> getExceptMnyFileds() {
    List<String> exceptMnyFileds = new ArrayList<String>();
    exceptMnyFileds.add("CMARBASCALSSID");
    exceptMnyFileds.add("NINVOICEDISRATE");
    exceptMnyFileds.add("NORIGMNY");
    exceptMnyFileds.add("NMNY");
    exceptMnyFileds.add("NTAXMNY");
    exceptMnyFileds.add("NTAX");

    return exceptMnyFileds;
  }

  /**
   * 校验单据是否变化
   * 
   * @param headvo
   * @param combvo
   * @param retdetail
   */
  private void checkUpdatePriceMnyChg(SaleInvoiceHVO headvo,
      SaleInvoiceBVO combvo, List<SaleInvoiceBVO> retdetail) {
    // 去除删除的行
    List<SaleInvoiceBVO> bvolistnodel = new ArrayList<SaleInvoiceBVO>();
    for (SaleInvoiceBVO bvo : retdetail) {
      if (VOStatus.DELETED == bvo.getStatus()) {
        continue;
      }
      bvolistnodel.add(bvo);
    }
    this.checkNewPriceMnyChg(headvo, combvo, bvolistnodel);
  }

  /**
   * 表体行合并
   * 
   * @param detailvos
   * @param combinpara
   */
  private void combinDetails(SaleInvoiceVO[] detailvos,
      CombinResultVO combinpara) {
    SaleInvoiceVOMerger mergertool = new SaleInvoiceVOMerger();
    mergertool.setNumAttr(SaleInvoiceBVO.NNUM);
    Map<String, String> mparacombins = this.getCombinParas(detailvos);
    combinpara.setMapGroupKeys(mparacombins);
    String pk_org = null;

    MapList<String, SaleInvoiceBVO> cominrela = combinpara.getCombinRela();
    if (null == cominrela) {
      cominrela = new MapList<String, SaleInvoiceBVO>();
    }
    SaleInvoiceVO[] combinvo = new SaleInvoiceVO[detailvos.length];
    int i = 0;
    for (SaleInvoiceVO vo : detailvos) {
      if (vo.getChildrenVO() == null || vo.getChildrenVO().length == 0) {
        combinvo[i] = new SaleInvoiceVO();
        combinvo[i].setParentVO(vo.getParentVO());
        combinvo[i].setChildrenVO(null);
        i++;
        continue;
      }
      mergertool.setIshasclass(false);
      pk_org = vo.getParentVO().getPk_org();
      String combinparas = mparacombins.get(pk_org);
      String[] combinparary = combinparas.split(ParameterList.BIGSPLITKEY);
      String[] groupkeys = this.getGroupKeys(combinparas);
      if (groupkeys.length == 0) {
        combinvo[i] = new SaleInvoiceVO();
        combinvo[i].setParentVO(vo.getParentVO());
        combinvo[i].setChildrenVO(vo.getChildrenVO());
        i++;
        continue;
      }
      int classlevel = this.getClassLevel(combinparary[4]);
      List<SaleInvoiceBVO> megervolist = new ArrayList<SaleInvoiceBVO>();
      // 按物料级次进行汇总则复制表体vos
      this.getDoubleVOS(megervolist, vo, groupkeys, classlevel, detailvos);
      mergertool.setSummingAttr(this.getSumKeys(combinparas));
      mergertool.setProavgingAttr(this.getProAvgKeys(combinparas));
      String[] avergeattrs = this.getAvgKeys(combinparas);
      if (null != avergeattrs && avergeattrs.length > 0) {
        mergertool.setAveragingAttr(this.getAvgKeys(combinparas));
      }
      mergertool.setGroupingAttr(groupkeys);
      SaleInvoiceBVO[] bodys = vo.getChildrenVO();
      for (SaleInvoiceBVO bvo : bodys) {
        megervolist.add(bvo);
      }
      SaleInvoiceBVO[] mergebvos = null;
      try {
        mergebvos =
            (SaleInvoiceBVO[]) mergertool.mergeByGroup(megervolist
                .toArray(new SaleInvoiceBVO[0]));
      }
      catch (BusinessException e) {
        ExceptionUtils.wrappException(e);
      }
      if (null == mergebvos) {
        continue;
      }
      UFBoolean megerEqualDetail = UFBoolean.FALSE;
      if (mergebvos.length == bodys.length) {
        megerEqualDetail = UFBoolean.TRUE;
      }

      Map<CircularlyAccessibleValueObject, CircularlyAccessibleValueObject[]> mg =
          mergertool.m_hashMergeRelations;
      int row = 10;
      for (SaleInvoiceBVO mergebvo : mergebvos) {
        SaleInvoiceBVO[] detailbvos = (SaleInvoiceBVO[]) mg.get(mergebvo);
        // 设置行号
        if (megerEqualDetail.booleanValue()) {
          mergebvo.setCrowno(detailbvos[0].getCrowno());
        }
        else {
          mergebvo.setCrowno(String.valueOf(row));
          row = row + 10;
        }

        String bid = detailbvos[0].getPrimaryKey();
        if (null == bid || "isnull".equals(bid)) {
          continue;
        }
        mergebvo.setPrimaryKey(bid);
        for (SaleInvoiceBVO bvo : detailbvos) {
          SaleInvoiceBVO cachebvo = (SaleInvoiceBVO) bvo.clone();
          cominrela.put(bid, cachebvo);
        }

      }
      // 按行号重新排序
      if (megerEqualDetail.booleanValue()) {
        RowNoComparator comp = new RowNoComparator(SaleInvoiceBVO.CROWNO);
        Arrays.sort(mergebvos, comp);
      }

      combinvo[i] = new SaleInvoiceVO();
      combinvo[i].setParentVO(vo.getParentVO());
      combinvo[i].setChildrenVO(mergebvos);
      i++;
    }
    combinpara.setCombinvos(combinvo);
    combinpara.setCombinrela(cominrela);
  }

  /**
   * 平均值字段数据
   * 
   * @param paravalue
   * @return
   */
  private String[] getAvgKeys(String paravalue) {
    // jilu for 633 合并BUG 前进化工
    // 销售发票显示规则选择汇总，汇总规则按照基本分类汇总，求和项目选择表体自定义项目1-7，但是汇总时表体自定义项目汇总合并时没哟汇总显示
    // String keystring = paravalue.split(ParameterList.BIGSPLITKEY)[1];
    String keystring = paravalue.split(ParameterList.BIGSPLITKEY)[2];
    // end
    if (keystring.length() == 0) {
      return null;
    }
    return this.transCode2Key(keystring.split(ParameterList.SPLITKEY));
  }

  private int getClassLevel(String leveltext) {
    // String leveltext = listgroupkeys.get(listgroupkeys.size() - 1);
    int levelint = 0;
    if ("逐级汇总".equals(leveltext)) { /* -=notranslate=- */
      levelint = -1;
    }
    else if ("末级汇总".equals(leveltext)) { /* -=notranslate=- */
      levelint = 0;
    }
    else if ("一级汇总".equals(leveltext)) { /* -=notranslate=- */
      levelint = 1;
    }
    else if ("二级汇总".equals(leveltext)) { /* -=notranslate=- */
      levelint = 2;
    }
    else if ("三级汇总".equals(leveltext)) { /* -=notranslate=- */
      levelint = 3;
    }
    else if ("四级汇总".equals(leveltext)) { /* -=notranslate=- */
      levelint = 4;
    }
    else if ("五级汇总".equals(leveltext)) { /* -=notranslate=- */
      levelint = 5;
    }

    return levelint;
  }

  private String[] getCmaterialIDs(SaleInvoiceVO[] detailvos) {
    Set<String> cmaterialidset = new java.util.HashSet<String>();

    for (SaleInvoiceVO vo : detailvos) {
      SaleInvoiceBVO[] bvos = vo.getChildrenVO();
      for (SaleInvoiceBVO bvo : bvos) {
        cmaterialidset.add(bvo.getCmaterialid());
      }

    }
    String[] cmaterialids =
        cmaterialidset.toArray(new String[cmaterialidset.size()]);
    return cmaterialids;
  }

  /**
   * 根据组织获得分组合并条件
   * 
   * @param detailvos
   * @return
   */
  private Map<String, String> getCombinParas(SaleInvoiceVO[] detailvos) {
    Set<String> setOrgs = new java.util.HashSet<String>();
    for (SaleInvoiceVO vo : detailvos) {
      setOrgs.add(vo.getParentVO().getPk_org());
    }
    String[] orgs = new String[setOrgs.size()];
    orgs = setOrgs.toArray(orgs);
    Map<String, String> mParas = null;

    mParas =
        SOSysParaInitUtil.queryBatchParaStringValues(orgs,
            ParameterList.SO28.getCode() + ParameterList.SUFFIX);

    return mParas;
  }

  /**
   * 复制VO
   * 
   * @param megervolist
   * @param vo
   * @param listgroupkeys
   * @param classlevel
   * @param detailvos
   */
  private void getDoubleVOS(List<SaleInvoiceBVO> megervolist, SaleInvoiceVO vo,
      String[] listgroupkeys, int classlevel, SaleInvoiceVO[] detailvos) {
    boolean ishascmrbasclass = false;
    for (String str : listgroupkeys) {
      if (str.equals(SaleInvoiceBVO.CMARBASCALSSID)) {
        ishascmrbasclass = true;
        break;
      }
    }
    if (ishascmrbasclass) {
      SaleInvoiceBVO[] bvos = vo.getChildrenVO();
      String[] pks = this.getCmaterialIDs(detailvos);

      // 逐级汇总
      if (classlevel == -1) {
        Map<String, String> baseclassmaps =
            MaterialPubService.queryMaterialBaseClassPk(pks);
        ISaleInvoiceMaintain srv =
            NCLocator.getInstance().lookup(ISaleInvoiceMaintain.class);
        Map<String, String> innercodecmatermap = new HashMap<String, String>();
        try {
          innercodecmatermap = srv.getInnercodemaps(pks);
        }
        catch (BusinessException e1) {
          ExceptionUtils.wrappException(e1);
        }
        // 物料内码各个级次上的内码集合
        Set<String> innercodesets = new HashSet<String>();
        for (Entry<String, String> entry : innercodecmatermap.entrySet()) {
          String innercode = entry.getValue();
          // 物料内码4位为第一级，8位为第二级，以此内推。
          int rownum = innercode.length() / 4;
          UFDouble basenum = new UFDouble(4);
          for (int j = 0; j < rownum; j++) {
            UFDouble level = new UFDouble(j + 1);
            String newcode =
                innercode.substring(0, basenum.multiply(level).intValue());
            innercodesets.add(newcode);
          }
        }

        Map<String, String> newcmarbascalssidmaps =
            new HashMap<String, String>();
        try {
          newcmarbascalssidmaps =
              srv.getCmaterialids(innercodesets
                  .toArray(new String[innercodesets.size()]));
        }
        catch (BusinessException e1) {
          ExceptionUtils.wrappException(e1);
        }
        for (SaleInvoiceBVO bvo : bvos) {
          String cmaterid = bvo.getCmaterialid();
          bvo.setCmarbascalssid(baseclassmaps.get(cmaterid));

          String mergecode = innercodecmatermap.get(cmaterid);
          int rownum = mergecode.length() / 4;
          // 逐级汇总时，如果物料基本分类只有1级，不赋值表体行。否则会导致表体行数量翻倍
          if (rownum == 1) {
            continue;
          }
          SaleInvoiceBVO[] newmergebvos = this.getNewmergebvos(bvo, rownum);
          UFDouble basenum = new UFDouble(4);
          for (int j = 0; j < rownum - 1; j++) {
            UFDouble level = new UFDouble(j + 1);
            String newcode =
                mergecode.substring(0, basenum.multiply(level).intValue());
            String newcmarbascalssid = newcmarbascalssidmaps.get(newcode);
            newmergebvos[j].setCmarbascalssid(newcmarbascalssid);
            /**
             * newmergebvos[j].setPrimaryKey(NULL); 审批、弃审、提交、收回如果按物料分类逐级汇总会有问题
             * 出现主键为null，nc.vo.pubapp.pattern.model.transfer.bill.
             * ClientBillCombinServer.combineNoCloumnIndex(E clientBill,
             * ISuperVO[] childrenVO, IVOMeta voMeta) 会报不支持此业务
             */
            newmergebvos[j].setPrimaryKey("isnull");
            megervolist.add(newmergebvos[j]);
          }
        }
      }
      else {

        Map<String, String> baseclassmaps = null;
        if (classlevel == 0) {
          baseclassmaps = MaterialPubService.queryMaterialBaseClassPk(pks);
        }
        else {
          baseclassmaps =
              MaterialBaseClassPubService
                  .queryMarBasClassIDByClassLevelAndMaterialOIDs(classlevel,
                      pks);
        }

        for (SaleInvoiceBVO bvo : bvos) {
          String cmaterid = bvo.getCmaterialid();
          bvo.setCmarbascalssid(baseclassmaps.get(cmaterid));
        }
      }
    }
  }

  /**
   * 汇总依据字段
   * 
   * @param paravalue
   * @return
   */
  private String[] getGroupKeys(String paravalue) {
    String groupstring = paravalue.split(ParameterList.BIGSPLITKEY)[0];
    if (paravalue.startsWith(ParameterList.DOLLER)) {
      return this.transCode2Key(groupstring.substring(1, groupstring.length())
          .split(ParameterList.SPLITKEY));
    }
    return groupstring.split(ParameterList.SPLITKEY);

  }

  /**
   * 将code解密为key
   * 
   * @param split
   * @return res
   */
  private String[] transCode2Key(String[] split) {
    Map<String, String> code2Key = this.getTransCodeToKey();
    int length = split.length;
    String[] res = new String[length];
    for (int i = 0; i < length; i++) {
      res[i] = code2Key.get(split[i]);
    }
    return res;
  }

  /**
   * 复制vo
   * 
   * @param mergebvo 被复制的VO
   * @param rownum 复制的个数
   * @return
   */
  private SaleInvoiceBVO[] getNewmergebvos(SaleInvoiceBVO mergebvo, int rownum) {
    SaleInvoiceBVO[] bvos = Constructor.construct(SaleInvoiceBVO.class, rownum);
    String[] attrinames = mergebvo.getAttributeNames();
    for (SaleInvoiceBVO bvo : bvos) {
      for (String attriname : attrinames) {
        bvo.setAttributeValue(attriname, mergebvo.getAttributeValue(attriname));
      }
    }
    return bvos;
  }

  /**
   * 旧的单价
   * 
   * @param retdetial
   * @return
   */
  private UFDouble getOldPrice(List<SaleInvoiceBVO> retdetial) {
    // String pricekey = SaleInvoiceCombin.getCalPriceKey();
    String mnykey = SaleInvoiceCombin.getCalMnyKey();
    UFDouble oldprice = UFDouble.ZERO_DBL;
    UFDouble oldtotalmny = UFDouble.ZERO_DBL;
    UFDouble oldtotalnum = null;
    int power = 0;
    for (SaleInvoiceBVO bvo : retdetial) {
      if (bvo.getNorigprice() != null) {// 对于折扣行，允许单据为空
        power = bvo.getNorigprice().getPower();
      }
      oldtotalmny =
          MathTool.add(oldtotalmny, (UFDouble) bvo.getAttributeValue(mnykey));
      oldtotalnum = MathTool.add(oldtotalnum, bvo.getNnum());
      // oldprice = MathTool.add(oldprice, bvo.getNorigprice());
    }
    if (!MathTool.isZero(oldtotalnum)) {
      oldprice =
          oldtotalmny.div(oldtotalnum).setScale(power, UFDouble.ROUND_HALF_UP);
    }
    return oldprice;
  }

  /**
   * 加权平均字段数组
   * 
   * @param paravalue
   * @return
   */
  private String[] getProAvgKeys(String paravalue) {
    String keystring = paravalue.split(ParameterList.BIGSPLITKEY)[3];
    if (null != keystring && keystring.length() > 0) {
      List<String> proavgkeylist = new ArrayList<String>();
      for (String key : CombinContext.COMBIN_AVERAG) {
        proavgkeylist.add(key);
      }
      String[] keys = keystring.split(ParameterList.SPLITKEY);
      if (paravalue.startsWith(ParameterList.DOLLER)) {
        keys = this.transCode2Key(keys);
      }
      for (String key : keys) {
        proavgkeylist.add(key);
      }
      return proavgkeylist.toArray(new String[proavgkeylist.size()]);
    }

    return CombinContext.COMBIN_AVERAG;
  }

  /**
   * 求和字段数组
   * 
   * @param paravalue
   * @return
   */
  private String[] getSumKeys(String paravalue) {
    String keystring = paravalue.split(ParameterList.BIGSPLITKEY)[1];
    if (null != keystring && keystring.length() > 0) {
      List<String> sumkeylist = new ArrayList<String>();
      for (String key : CombinContext.COMBIN_SUMKEYS) {
        sumkeylist.add(key);
      }
      String[] keys = keystring.split(ParameterList.SPLITKEY);
      if (paravalue.startsWith(ParameterList.DOLLER)) {
        keys = this.transCode2Key(keys);
      }
      for (String key : keys) {
        sumkeylist.add(key);
      }
      return sumkeylist.toArray(new String[sumkeylist.size()]);
    }
    return CombinContext.COMBIN_SUMKEYS;
  }

  /**
   * 原币金额是否发生改变 并计算单价
   * 
   * @param combvo
   * @param retdetial
   * @param oldprice
   * @return
   */
  private boolean isOrigMnyChange(SaleInvoiceBVO combvo,
      List<SaleInvoiceBVO> retdetial) {

    UFDouble oldtotaltaxmny = UFDouble.ZERO_DBL;
    UFDouble oldtotalmny = UFDouble.ZERO_DBL;
    UFDouble oldtotalnum = UFDouble.ZERO_DBL;
    for (SaleInvoiceBVO bvo : retdetial) {
      oldtotaltaxmny = MathTool.add(oldtotaltaxmny, bvo.getNorigtaxmny());
      oldtotalmny = MathTool.add(oldtotalmny, bvo.getNorigmny());
      oldtotalnum = MathTool.add(oldtotalnum, bvo.getNnum());
    }
    UFDouble nowtotaltaxmny = combvo.getNorigtaxmny();
    UFDouble nowtotalmny = combvo.getNorigmny();
    if (oldtotaltaxmny.compareTo(nowtotaltaxmny) != 0
        || oldtotalmny.compareTo(nowtotalmny) != 0) {
      return true;
    }
    return false;
  }

  /**
   * 本币金额是否改变
   * 
   * @param combvo
   * @param retdetial
   * @return
   */
  private boolean isMnyChange(SaleInvoiceBVO combvo,
      List<SaleInvoiceBVO> retdetial) {

    UFDouble oldtotaltaxmny = UFDouble.ZERO_DBL;
    UFDouble oldtotalmny = UFDouble.ZERO_DBL;
    UFDouble oldtotalnum = UFDouble.ZERO_DBL;
    for (SaleInvoiceBVO bvo : retdetial) {
      oldtotaltaxmny = MathTool.add(oldtotaltaxmny, bvo.getNtaxmny());
      oldtotalmny = MathTool.add(oldtotalmny, bvo.getNmny());
      oldtotalnum = MathTool.add(oldtotalnum, bvo.getNnum());
    }
    UFDouble nowtotaltaxmny = combvo.getNtaxmny();
    UFDouble nowtotalmny = combvo.getNmny();
    if (oldtotaltaxmny.compareTo(nowtotaltaxmny) != 0
        || oldtotalmny.compareTo(nowtotalmny) != 0) {
      return true;
    }
    return false;
  }

  private boolean isNew(SaleInvoiceVO voInvoice) {
    if (null == voInvoice.getParentVO().getCsaleinvoiceid()) {
      return true;
    }
    return false;
  }

  /**
   * 处理合并后的尾差
   * 
   * @param combvo
   * @param retdetail
   * @param maxsize
   * @param calc
   */
  private void processCombinMargin(SaleInvoiceBVO combvo,
      List<SaleInvoiceBVO> retdetail, int maxsize, SaleInvoiceVOCalculator calc) {
    // 价税合计
    UFDouble nowtotalorigtaxmny = combvo.getNorigtaxmny();
    // 无税金额
    UFDouble nowtotalorigmny = combvo.getNorigmny();
    // 税额
    UFDouble nowtotaltax = combvo.getNtax();
    // 倒挤尾差
    UFDouble remianorigtaxmny = nowtotalorigtaxmny;
    UFDouble remianorigmny = nowtotalorigmny;
    for (int i = 0; i < maxsize - 1; i++) {
      SaleInvoiceBVO detailbvo = retdetail.get(i);
      remianorigtaxmny =
          MathTool.sub(remianorigtaxmny, detailbvo.getNorigtaxmny());
      remianorigmny = MathTool.sub(remianorigmny, detailbvo.getNorigmny());
      nowtotaltax = MathTool.sub(nowtotaltax, detailbvo.getNtax());
    }
    SaleInvoiceBVO maxsizebvo = retdetail.get(maxsize - 1);
    maxsizebvo.setNorigtaxmny(remianorigtaxmny);
    maxsizebvo.setNorigmny(remianorigmny);

    // maxsizebvo.setNorigtax(MathTool.sub(remianorigtaxmny, remianorigmny));
    calc.calculate(maxsize - 1, SaleInvoiceHVO.NEXCHANGERATE);
    maxsizebvo.setNtax(nowtotaltax);
    calc.calculate(maxsize - 1, SaleInvoiceBVO.NTAX);
  }

  private List<SaleInvoiceBVO> processNew(SaleInvoiceHVO headvo,
      SaleInvoiceBVO combvo, List<SaleInvoiceBVO> cachedetbvos,
      String[] combinparary) {
    // 过滤后明细
    List<SaleInvoiceBVO> retdetails = new ArrayList<SaleInvoiceBVO>();
    // 检查数量
    this.checkNumChange(headvo, combvo, cachedetbvos, retdetails, true,
        combinparary);

    // 检查单价金额
    this.checkNewPriceMnyChg(headvo, combvo, retdetails);

    return retdetails;
  }

  private List<SaleInvoiceBVO> processUpdate(SaleInvoiceHVO headvo,
      SaleInvoiceBVO combvo, List<SaleInvoiceBVO> cachedetbvos,
      String[] combinparary) {
    List<SaleInvoiceBVO> retdetail = new ArrayList<SaleInvoiceBVO>();
    if (VOStatus.DELETED == combvo.getStatus()) {
      for (SaleInvoiceBVO bvo : cachedetbvos) {
        bvo.setStatus(VOStatus.DELETED);
      }
    }
    else {
      // 修改保存时检查数量
      this.checkNumChange(headvo, combvo, cachedetbvos, retdetail, false,
          combinparary);
      // 修改保存是检查单价金额
      this.checkUpdatePriceMnyChg(headvo, combvo, retdetail);
    }
    return retdetail;
  }
}
