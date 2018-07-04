package nc.vo.so.m30.vochange;

import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;

import nc.bs.framework.common.InvocationInfoProxy;
import nc.bs.framework.common.NCLocator;
import nc.bs.pubapp.AppBsContext;
import nc.impl.pubapp.bd.userdef.UserDefCheckUtils;
import nc.impl.pubapp.env.BSContext;
import nc.itf.pubapp.margin.IMarginDudgement;
import nc.itf.scmpub.reference.uap.bd.customer.CustomerPubService;
import nc.itf.scmpub.reference.uap.org.OrgUnitPubService;
import nc.itf.uap.pf.IPFConfig;
import nc.vo.bd.cust.CustomerVO;
import nc.vo.pf.change.ChangeVOAdjustContext;
import nc.vo.pf.change.IChangeVOAdjust;
import nc.vo.pub.AggregatedValueObject;
import nc.vo.pub.BusinessException;
import nc.vo.pub.lang.UFBoolean;
import nc.vo.pub.lang.UFDate;
import nc.vo.pub.lang.UFDouble;
import nc.vo.pubapp.margin.BillMarginContext;
import nc.vo.pubapp.margin.MarginContextFactory;
import nc.vo.pubapp.margin.MarginEntry;
import nc.vo.pubapp.margin.SingleWordMarginJudgement;
import nc.vo.pubapp.pattern.exception.ExceptionUtils;
import nc.vo.pubapp.pattern.pub.MathTool;
import nc.vo.pubapp.pattern.pub.PubAppTool;
import nc.vo.scmpub.res.billtype.SOBillType;
import nc.vo.so.m30.entity.SaleOrderBVO;
import nc.vo.so.m30.entity.SaleOrderHVO;
import nc.vo.so.m30.entity.SaleOrderVO;
import nc.vo.so.m30.pub.SaleOrderVOCalculator;
import nc.vo.so.m30.rule.PieceCalRule;
import nc.vo.so.m30.rule.WeightVolumeCalRule;
import nc.vo.so.pub.SOConstant;
import nc.vo.so.pub.SOItemKey;
import nc.vo.so.pub.enumeration.BillStatus;
import nc.vo.so.pub.keyvalue.IKeyValue;
import nc.vo.so.pub.keyvalue.VOKeyValue;
import nc.vo.so.pub.rule.SOSrcNumCompareRule;
import nc.vo.so.pub.util.SOSysParaInitUtil;

/**
 * 销售订单参照来源单据后续处理类，主要进行默认数据填充、尾差处理
 * 
 * @since 6.0
 * @version 2012-02-22 上午09:55:02
 * @author 刘景
 */
public abstract class AbstractSaleOrderChangeVOAdjust implements
    IChangeVOAdjust {

  private static final String[] ADJUSTMNYKEY = new String[] {
    SOItemKey.NORIGMNY, SOItemKey.NORIGTAXMNY, SOItemKey.NTAX
  };

  private static final String[] OPPOSEKEY = new String[] {
    SaleOrderBVO.NASTNUM, SaleOrderBVO.NQTUNITNUM, SaleOrderBVO.NORIGMNY,
    SaleOrderBVO.NORIGTAXMNY, SaleOrderBVO.NORIGDISCOUNT, SaleOrderBVO.NMNY,
    SaleOrderBVO.NTAXMNY, SaleOrderBVO.NTAX, SaleOrderBVO.NDISCOUNT,
    SaleOrderBVO.NCALTAXMNY, SaleOrderBVO.NBFORIGSUBMNY,
    SaleOrderBVO.NGLOBALMNY, SaleOrderBVO.NGLOBALTAXMNY,
    SaleOrderBVO.NGROUPMNY, SaleOrderBVO.NGROUPTAXMNY
  };

  @Override
  public AggregatedValueObject adjustAfterChange(AggregatedValueObject srcVO,
      AggregatedValueObject destVO, ChangeVOAdjustContext adjustContext)
      throws BusinessException {
    AggregatedValueObject[] srcVOs = new AggregatedValueObject[] {
      srcVO
    };
    AggregatedValueObject[] destVOs = new AggregatedValueObject[] {
      destVO
    };
    AggregatedValueObject[] bills =
        this.batchAdjustAfterChange(srcVOs, destVOs, adjustContext);
    return bills[0];
  }

  @Override
  public AggregatedValueObject adjustBeforeChange(AggregatedValueObject srcVO,
      ChangeVOAdjustContext adjustContext) throws BusinessException {
    return null;
  }

  @Override
  public AggregatedValueObject[] batchAdjustAfterChange(
      AggregatedValueObject[] srcVOs, AggregatedValueObject[] destVOs,
      ChangeVOAdjustContext adjustContext) throws BusinessException {
    SaleOrderVO[] destordervos = (SaleOrderVO[]) destVOs;
    // 校验自定义项
    this.checkVOs(destordervos);
    // 设置pk_org_v
    this.setPkOrgVValues(destordervos);

    // 填充值
    this.fillRefAddValue(destordervos);
    // 计算重量体积件数
    this.calWeightVolumePiece(destordervos);

    // 对比订单主数量和上游单据主数量，判断主数量是否变化
    SOSrcNumCompareRule<SaleOrderVO, SaleOrderBVO> numcomrule =
        new SOSrcNumCompareRule<SaleOrderVO, SaleOrderBVO>(SaleOrderVO.class,
            SaleOrderBVO.class);
    numcomrule.compareSrcNum(srcVOs, destVOs);

    List<SaleOrderVO> numchg = numcomrule.getNumChgVOList();
    // 数量改变的单据
    if (numchg.size() > 0) {
      SaleOrderVO[] chgordervos = new SaleOrderVO[numchg.size()];
      numchg.toArray(chgordervos);
      // 单价金额算法——主数量入口
      this.calculate(chgordervos, SaleOrderBVO.NNUM);
      // 尾差处理
      this.processMargin(chgordervos);
    }
    // 数量未改变的单据
    List<SaleOrderVO> nonumchg = numcomrule.getNumNoChgVOList();
    if (nonumchg.size() > 0) {
      SaleOrderVO[] nonumchgvos = new SaleOrderVO[nonumchg.size()];
      nonumchg.toArray(nonumchgvos);
      this.processNumNoChangeOrder(nonumchgvos);
    }
    return destordervos;
  }

  /**
   * 设置pk_org_v
   * 
   * @param destordervos
   */
  private void setPkOrgVValues(SaleOrderVO[] destordervos) {
    // pk_org集合
    Set<String> orgidset = new HashSet<String>();

    for (SaleOrderVO vo : destordervos) {
      SaleOrderHVO head = vo.getParentVO();
      orgidset.add(head.getPk_org());
    }
    Map<String, String> orgmapmap =
        OrgUnitPubService.getNewVIDSByOrgIDS(orgidset
            .toArray(new String[orgidset.size()]));
    // 设置pk_org_v
    for (SaleOrderVO vo : destordervos) {
      SaleOrderHVO head = vo.getParentVO();
      String orgid = head.getPk_org();
      head.setPk_org_v(orgmapmap.get(orgid));
    }
  }

  protected void processNumNoChangeOrder(SaleOrderVO[] nonumchgvos) {
    // 如果上下游单据异号，需要对其他数量和金额字段取反
    if (this.isDownSymbolMinus()) {
      for (SaleOrderVO nochgvo : nonumchgvos) {
        for (SaleOrderBVO body : nochgvo.getChildrenVO()) {
          for (String oppkey : OPPOSEKEY) {
            UFDouble oldvalue = (UFDouble) body.getAttributeValue(oppkey);
            body.setAttributeValue(oppkey, MathTool.oppose(oldvalue));
          }
        }
      }
    }
  }

  private void calWeightVolumePiece(SaleOrderVO[] destordervos) {
    // 计算数量相关
    for (SaleOrderVO salebillvo : destordervos) {
      IKeyValue vokeyValue = new VOKeyValue<SaleOrderVO>(salebillvo);
      int bodycount = vokeyValue.getBodyCount();
      int[] rows = new int[bodycount];
      for (int i = 0; i < bodycount; i++) {
        rows[i] = i;
      }
      // 计算重量体积
      WeightVolumeCalRule weivolcalrule = new WeightVolumeCalRule(vokeyValue);
      weivolcalrule.calculateWeightVolume(rows);
      // 计算件数
      PieceCalRule piecerule = new PieceCalRule(vokeyValue);
      piecerule.calcPiece(rows);
    }
  }

  @Override
  public AggregatedValueObject[] batchAdjustBeforeChange(
      AggregatedValueObject[] srcVOs, ChangeVOAdjustContext adjustContext)
      throws BusinessException {
    return null;
  }

  /**
   * 
   * 单价金额触发单价金额算法
   * 
   * @param vos 销售订单聚合VO数组
   * @param editkey 触发的字段名
   * 
   */
  private void calculate(SaleOrderVO[] vos, String editkey) {
    for (SaleOrderVO ordervo : vos) {
      SaleOrderVOCalculator calcultor = new SaleOrderVOCalculator(ordervo);
      int ilength = ordervo.getChildrenVO().length;
      int[] rows = new int[ilength];
      for (int i = 0; i < ilength; i++) {
        rows[i] = i;
      }
      calcultor.setForceFixUnitRate(UFBoolean.TRUE);
      String currency = ordervo.getParentVO().getCorigcurrencyid();
      if (PubAppTool.isNull(currency)) {
        calcultor.calculateOnlyNum(rows, editkey);
      }
      else {
        calcultor.calculate(rows, editkey);
      }
    }
  }

  /**
   * 填充新增单据的值,可复写
   * 
   * @param vos
   */
  protected void fillRefAddValue(SaleOrderVO[] vos) {

    // 缓存销售组织、业务流程
    Map<String, String> mapbiztype = new HashMap<String, String>();

    for (SaleOrderVO salebillvo : vos) {
      IKeyValue vokeyValue = new VOKeyValue<SaleOrderVO>(salebillvo);
      String pk_org = vokeyValue.getHeadStringValue(SaleOrderHVO.PK_ORG);
      String trantypecode =
          vokeyValue.getHeadStringValue(SaleOrderHVO.VTRANTYPECODE);

      String bizkey = pk_org + trantypecode;
      if (mapbiztype.containsKey(bizkey)) {
        vokeyValue
            .setHeadValue(SaleOrderHVO.CBIZTYPEID, mapbiztype.get(bizkey));
      }
      else {
        String newbiztype = this.getBusitypeCanStart(pk_org, trantypecode);
        vokeyValue.setHeadValue(SaleOrderHVO.CBIZTYPEID, newbiztype);
        mapbiztype.put(bizkey, newbiztype);
      }
      // 填充强制单据默认值
      this.setForceDefValue(vokeyValue);
    }
    this.setFreeCustFlag(vos);
  }

  /**
   * 设置业务流程
   */
  private String getBusitypeCanStart(String csaleorgid, String destTransType) {

    String userId = BSContext.getInstance().getUserID();

    String cbiztypeid = null;
    try {

      IPFConfig pfsrv = NCLocator.getInstance().lookup(IPFConfig.class);
      cbiztypeid =
          pfsrv.retBusitypeCanStart(SOBillType.Order.getCode(), destTransType,
              csaleorgid, userId);

    }
    catch (BusinessException e) {
      ExceptionUtils.wrappException(e);
    }
    return cbiztypeid;
  }

  /**
   * 设置转单后强制的数据默认值
   */
  private void setForceDefValue(IKeyValue keyValue) {

    // 单据状态
    keyValue.setHeadValue(SaleOrderHVO.FSTATUSFLAG,
        BillStatus.FREE.getIntegerValue());
    // 整单折扣
    UFDouble discountrate =
        keyValue.getHeadUFDoubleValue(SaleOrderHVO.NDISCOUNTRATE);
    if (null == discountrate) {
      discountrate = SOConstant.ONEHUNDRED;
      keyValue.setHeadValue(SaleOrderHVO.NDISCOUNTRATE, discountrate);
    }

    // 单据日期
    UFDate busdate = AppBsContext.getInstance().getBusiDate();
    keyValue.setHeadValue(SaleOrderHVO.DBILLDATE, busdate);

    UFDate enddate = busdate.asLocalEnd();
    int bodycount = keyValue.getBodyCount();
    for (int i = 0; i < bodycount; i++) {
      keyValue.setBodyValue(i, SaleOrderBVO.DBILLDATE, busdate);
      // 计划发货日期
      UFDate senddate = keyValue.getBodyUFDateValue(i, SaleOrderBVO.DSENDDATE);
      if (null == senddate || senddate.before(busdate)) {
        keyValue.setBodyValue(i, SaleOrderBVO.DSENDDATE, enddate);
      }

      // 要求到货日期
      UFDate receivedate =
          keyValue.getBodyUFDateValue(i, SaleOrderBVO.DRECEIVEDATE);
      if (null == receivedate || receivedate.before(busdate)) {
        keyValue.setBodyValue(i, SaleOrderBVO.DRECEIVEDATE, enddate);
      }

      // 整单折扣
      UFDouble disrate =
          keyValue.getBodyUFDoubleValue(i, SaleOrderBVO.NDISCOUNTRATE);
      if (null == disrate) {
        keyValue.setBodyValue(i, SaleOrderHVO.NDISCOUNTRATE, discountrate);
      }
      // 单品折扣
      UFDouble itemdisrate =
          keyValue.getBodyUFDoubleValue(i, SaleOrderBVO.NITEMDISCOUNTRATE);
      if (null == itemdisrate) {
        keyValue.setBodyValue(i, SaleOrderBVO.NITEMDISCOUNTRATE,
            SOConstant.ONEHUNDRED);
      }
      // 行状态
      keyValue.setBodyValue(i, SaleOrderBVO.FROWSTATUS,
          BillStatus.FREE.getIntegerValue());
    }

  }

  /**
   * 转单后散户标志批量设置
   */
  private void setFreeCustFlag(SaleOrderVO[] vos) {
    // 客户、散户标志集合
    Map<String, UFBoolean> mapcustfreeflag = new HashMap<String, UFBoolean>();
    // 客户集合
    Set<String> setcustomer = new HashSet<String>();

    for (SaleOrderVO salevo : vos) {
      String customerid = salevo.getParentVO().getCcustomerid();
      if (PubAppTool.isNull(customerid)) {
        salevo.getParentVO().setBfreecustflag(UFBoolean.FALSE);
      }
      else {
        setcustomer.add(customerid);
      }
    }
    if (setcustomer.size() == 0) {
      return;
    }

    String[] fieldNames = new String[] {
      CustomerVO.ISFREECUST
    };
    String[] customerids = new String[setcustomer.size()];
    setcustomer.toArray(customerids);

    CustomerVO[] custVO =
        CustomerPubService.getCustomerVO(customerids, fieldNames);
    for (CustomerVO cstvo : custVO) {
      mapcustfreeflag.put(cstvo.getPk_customer(), cstvo.getIsfreecust());
    }
    for (SaleOrderVO salevo : vos) {
      String customerid = salevo.getParentVO().getCcustomerid();
      if (PubAppTool.isNull(customerid)) {
        continue;
      }
      salevo.getParentVO().setBfreecustflag(mapcustfreeflag.get(customerid));
    }
  }

  /**
   * 如果是含税优先，就传含税净价字段，否则，就传无税净价字段
   */
  private IMarginDudgement getMarginDudgement() {
    String pk_group = InvocationInfoProxy.getInstance().getGroupId();
    UFBoolean istaxprior = SOSysParaInitUtil.getSO23(pk_group);
    if (istaxprior.booleanValue()) {
      return new SingleWordMarginJudgement(getSrcBillOrigTaxNetPriceKey(),
          SaleOrderBVO.NORIGTAXNETPRICE);
    }
    return new SingleWordMarginJudgement(getSrcBillOrigNetPriceKey(),
        SaleOrderBVO.NORIGNETPRICE);
  }

  protected String[] getSrcBillMnyKey() {
    return AbstractSaleOrderChangeVOAdjust.ADJUSTMNYKEY;
  }

  protected String getSrcBillOrigTaxNetPriceKey() {
    return SOItemKey.NORIGTAXNETPRICE;
  }

  protected String getSrcBillOrigNetPriceKey() {
    return SOItemKey.NORIGNETPRICE;
  }

  /**
   * 来源单据数量字段
   * 
   * @return
   */
  protected String getSrcBillNumKey() {
    return SOItemKey.NNUM;
  }

  /**
   * 来源单据类型
   * 
   */
  protected abstract String getSrcBillTypeCode();

  /**
   * 尾差处理时是否需要取反
   * 
   * @return
   */
  protected boolean isDownSymbolMinus() {
    return false;
  }

  @SuppressWarnings("rawtypes")
  private void checkVOs(SaleOrderVO[] destVOs) {

    String[] ruleCodes = new String[] {
      "so.so_saleorder", "so.so_saleorder_b"
    };
    String[] prefixs = new String[] {
      SOConstant.VDEF, SOConstant.VBDEF
    };
    Class[] voClasses = new Class[] {
      SaleOrderHVO.class, SaleOrderBVO.class
    };
    UserDefCheckUtils.check(destVOs, ruleCodes, prefixs, voClasses);
  }

  protected boolean isProcessMargin() {
    return true;
  }

  /**
   * 尾差处理逻辑
   * 
   * @param vos
   */
  private void processMargin(SaleOrderVO[] vos) {

    if (!isProcessMargin()) {
      return;
    }

    try {
      IMarginDudgement margindudgement = this.getMarginDudgement();
      BillMarginContext context =
          MarginContextFactory.getInstance().produceMarginContext(vos,
              this.getSrcBillTypeCode(), this.getSrcBillNumKey(),
              this.getSrcBillMnyKey(), SOBillType.Order.getCode(),
              SaleOrderBVO.NNUM, AbstractSaleOrderChangeVOAdjust.ADJUSTMNYKEY,
              SaleOrderBVO.CSRCBID, SaleOrderBVO.CSRCID, margindudgement);
      MarginEntry marginentry = MarginEntry.getInstance();
      marginentry.setDownSymbolMinus(this.isDownSymbolMinus());
      marginentry.process(context);
    }
    catch (Exception e) {
      ExceptionUtils.wrappException(e);
    }
    // 单价金额算法——折本汇率入口计算本币金额、集团金额、全局金额
    this.calculate(vos, SaleOrderBVO.NEXCHANGERATE);
  }
}
