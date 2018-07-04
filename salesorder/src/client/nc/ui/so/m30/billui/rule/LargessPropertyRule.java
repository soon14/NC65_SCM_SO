package nc.ui.so.m30.billui.rule;

import java.util.ArrayList;
import java.util.List;

import nc.bs.framework.common.NCLocator;
import nc.itf.so.m30trantype.IM30TranTypeService;
import nc.ui.pub.bill.BillCardPanel;
import nc.ui.so.m30.billui.largessapportion.ApportionForPriceChange;
import nc.ui.so.m30.billui.largessapportion.ApportionForPriceUnchange;
import nc.ui.so.m30.billui.largessapportion.ApportionForSameMaterial;
import nc.ui.so.m30.billui.largessapportion.ApportionGroupByLargess;
import nc.ui.so.m30.billui.largessapportion.ApportionGroupByOne;
import nc.ui.so.m30.billui.largessapportion.ApportionGroupBySameMaterial;
import nc.ui.so.m30.billui.largessapportion.IApportionAction;
import nc.ui.so.m30.billui.largessapportion.IApportionGroup;
import nc.ui.so.m30.pub.SaleOrderCalculator;
import nc.ui.so.pub.keyvalue.CardKeyValue;
import nc.vo.pub.lang.UFBoolean;
import nc.vo.pub.lang.UFDouble;
import nc.vo.pubapp.AppContext;
import nc.vo.pubapp.pattern.exception.ExceptionUtils;
import nc.vo.pubapp.pattern.pub.PubAppTool;
import nc.vo.so.m30.entity.SaleOrderBVO;
import nc.vo.so.m30.entity.SaleOrderHVO;
import nc.vo.so.m30.enumeration.Fretexchange;
import nc.vo.so.m30.enumeration.Largesstype;
import nc.vo.so.m30.rule.HeadTotalCalculateRule;
import nc.vo.so.m30trantype.entity.M30TranTypeVO;
import nc.vo.so.m30trantype.enumeration.LargessDisType;
import nc.vo.so.pub.keyvalue.IKeyValue;
import nc.vo.so.pub.rule.BodyValueRowRule;
import nc.vo.so.pub.util.SOSysParaInitUtil;

public class LargessPropertyRule {

  private BillCardPanel cardPanel;

  private IKeyValue keyValue;

  private M30TranTypeVO trantypevo;

  public LargessPropertyRule(BillCardPanel cardPanel) {
    this.cardPanel = cardPanel;
    this.keyValue = new CardKeyValue(cardPanel);
  }

  public void processLargessApportion() {
    // 检查合法性
    this.checkLargessApportion();
    // 获得需要分摊的行
    List<Integer> listneedrow = this.getNeedApprotRows();

    // 分摊分组策略
    IApportionGroup appgroupstrategy = this.getApportionGroupStrategy();
    List<List<Integer>> appgroups =
        appgroupstrategy.getApportionGroupRows(this.keyValue, listneedrow);
    if (null == appgroups || appgroups.size() == 0) {
      ExceptionUtils
          .wrappBusinessException(nc.vo.ml.NCLangRes4VoTransl.getNCLangRes()
              .getStrByID("4006011_0", "04006011-0033")/*@res "按照交易类型赠品价格分摊方式，没有可进行分摊的行。"*/);
    }
    // 分摊操作策略
    IApportionAction appactionstrategy = this.getApportionActionStrategy();
    List<Integer> listallapprow = new ArrayList<Integer>();
    boolean istaxprior = this.isTaxPrior();
    for (List<Integer> rowlist : appgroups) {
      appactionstrategy.apportion(this.cardPanel, rowlist, istaxprior);
      listallapprow.addAll(rowlist);
    }
    // 数量单价金额运算
    int[] allapprows = new int[listallapprow.size()];
    int i = 0;
    for (Integer row : listallapprow) {
      allapprows[i] = row.intValue();
      i++;
    }
    SaleOrderCalculator calcutor = new SaleOrderCalculator(this.cardPanel);
    String editkey = SaleOrderBVO.NORIGMNY;
    if (istaxprior) {
      editkey = SaleOrderBVO.NORIGTAXMNY;
    }
    calcutor.setTranTypeVO(this.getTranTypeVO());
    calcutor.calculate(allapprows, editkey);
    // 表头合计
    HeadTotalCalculateRule totalrule =
        new HeadTotalCalculateRule(this.keyValue);
    totalrule.calculateHeadTotal();
  }

  public void undoLargessApportion() {
    // 检查合法性
    this.checkUnLargessApportion();
    // 获得需要取消分摊的行
    List<Integer> listneedrow = this.getNeedUnApportionRows();
    // 取消赠品价格分摊
    this.undoLargessApportion(listneedrow);
    // 数量单价金额运算
    int[] rows = new int[listneedrow.size()];
    int i = 0;
    for (Integer row : listneedrow) {
      rows[i] = row.intValue();
      i++;
    }
    boolean istaxprior = this.isTaxPrior();
    String mnyitemkey = SaleOrderBVO.NORIGMNY;
    if (istaxprior) {
      mnyitemkey = SaleOrderBVO.NORIGTAXMNY;
    }
    SaleOrderCalculator calcultor = new SaleOrderCalculator(this.cardPanel);
    calcultor.calculate(rows, mnyitemkey);
    // 合计
    HeadTotalCalculateRule totalrule =
        new HeadTotalCalculateRule(this.keyValue);
    totalrule.calculateHeadTotal();
  }

  private void checkLargessApportion() {

    if (this.isSaleOrderApportion()) {
      ExceptionUtils
          .wrappBusinessException(nc.vo.ml.NCLangRes4VoTransl.getNCLangRes()
              .getStrByID("4006011_0", "04006011-0034")/*@res "本订单已进行过赠品价格分摊，不允许再次分摊！"*/);
    }
    String ctrantypeid =
        this.keyValue.getHeadStringValue(SaleOrderHVO.CTRANTYPEID);
    if (PubAppTool.isNull(ctrantypeid)) {
      ExceptionUtils
          .wrappBusinessException(nc.vo.ml.NCLangRes4VoTransl.getNCLangRes()
              .getStrByID("4006011_0", "04006011-0035")/*@res "请先选择交易类型，然后再进行赠品价格分摊。"*/);
    }
    M30TranTypeVO trantypevo = this.getTranTypeVO();
    if (LargessDisType.NODISPART.equalsValue(trantypevo.getFlargessdistype())) {
      ExceptionUtils
          .wrappBusinessException(nc.vo.ml.NCLangRes4VoTransl.getNCLangRes()
              .getStrByID("4006011_0", "04006011-0036")/*@res "交易类型设置为不进行赠品价格分摊！"*/);
    }
    String origcur =
        this.keyValue.getHeadStringValue(SaleOrderHVO.CORIGCURRENCYID);
    if (PubAppTool.isNull(origcur)) {
      ExceptionUtils
          .wrappBusinessException(nc.vo.ml.NCLangRes4VoTransl.getNCLangRes()
              .getStrByID("4006011_0", "04006011-0037")/*@res "请先选择币种，然后再进行赠品价格分摊。"*/);
    }
    BodyValueRowRule rowrule = new BodyValueRowRule(this.keyValue);
    int[] rows = rowrule.getLargessRows();
    if (null == rows || rows.length == 0) {
      ExceptionUtils
          .wrappBusinessException(nc.vo.ml.NCLangRes4VoTransl.getNCLangRes()
              .getStrByID("4006011_0", "04006011-0038")/*@res "本订单没有赠品行，无法进行价格分摊！"*/);
    }
  }

  private void checkUnLargessApportion() {

    if (!this.isSaleOrderApportion()) {
      ExceptionUtils
          .wrappBusinessException(nc.vo.ml.NCLangRes4VoTransl.getNCLangRes()
              .getStrByID("4006011_0", "04006011-0039")/*@res "本订单未进行过赠品价格分摊，不能取消分摊！"*/);
    }
    String ctrantypeid =
        this.keyValue.getHeadStringValue(SaleOrderHVO.CTRANTYPEID);
    if (PubAppTool.isNull(ctrantypeid)) {
      ExceptionUtils
          .wrappBusinessException(nc.vo.ml.NCLangRes4VoTransl.getNCLangRes()
              .getStrByID("4006011_0", "04006011-0040")/*@res "请先选择交易类型，然后再取消赠品价格分摊。"*/);
    }
  }

  private IApportionAction getApportionActionStrategy() {

    M30TranTypeVO trantypevo = this.getTranTypeVO();

    Integer largessdistype = trantypevo.getFlargessdistype();
    UFBoolean ispricechg = trantypevo.getBlargesspriceno();

    IApportionAction apportionstrategy = null;
    // 同物料分摊
    if (LargessDisType.DISPARTBYINV.equalsValue(largessdistype)) {
      apportionstrategy = new ApportionForSameMaterial();
    }
    // 赠品价格是否改变
    else if (null != ispricechg && ispricechg.booleanValue()) {
      apportionstrategy = new ApportionForPriceUnchange();
    }
    // 赠品价格不变
    else {
      apportionstrategy = new ApportionForPriceChange();
    }
    return apportionstrategy;

  }

  /**
   * 返回分摊分组策略
   * 
   * @param largessdistype
   * @param largesspricenotchange
   * @return
   */
  private IApportionGroup getApportionGroupStrategy() {

    M30TranTypeVO trantypevo = this.getTranTypeVO();

    Integer largessdistype = trantypevo.getFlargessdistype();
    IApportionGroup apportionstrategy = null;
    // 同物料分摊
    if (LargessDisType.DISPARTBYINV.equalsValue(largessdistype)) {
      apportionstrategy = new ApportionGroupBySameMaterial();
    }
    // 买赠分摊
    else if (LargessDisType.DISPARTBYLARGESS.equalsValue(largessdistype)) {
      apportionstrategy = new ApportionGroupByLargess();
    }
    // 整单分摊
    else if (LargessDisType.DISPARTONE.equalsValue(largessdistype)) {
      apportionstrategy = new ApportionGroupByOne();
    }
    return apportionstrategy;
  }

  private List<Integer> getNeedApprotRows() {
    List<Integer> listneedrow = new ArrayList<Integer>();
    int bodycount = this.keyValue.getBodyCount();
    for (int i = 0; i < bodycount; i++) {
      // 物料为空行不分摊
      String cmarterialvid =
          this.keyValue.getBodyStringValue(i, SaleOrderBVO.CMATERIALVID);
      if (PubAppTool.isNull(cmarterialvid)) {
        continue;
      }
      // 数量为空或者红字不分摊
      UFDouble nnum = this.keyValue.getBodyUFDoubleValue(i, SaleOrderBVO.NNUM);
      if (null == nnum || nnum.compareTo(UFDouble.ZERO_DBL) <= 0) {
        continue;
      }
      // 退换货行不分摊
      Integer retexchange =
          this.keyValue.getBodyIntegerValue(i, SaleOrderBVO.FRETEXCHANGE);
      if (Fretexchange.EXCHANGE.equalsValue(retexchange)
          || Fretexchange.WITHDRAW.equalsValue(retexchange)) {
        continue;
      }
      listneedrow.add(Integer.valueOf(i));
    }
    return listneedrow;
  }

  private List<Integer> getNeedUnApportionRows() {

    List<Integer> listneedrow = new ArrayList<Integer>();
    int bodycount = this.keyValue.getBodyCount();
    for (int i = 0; i < bodycount; i++) {
      // 赠品分摊标志
      Integer largessflag =
          this.keyValue.getBodyIntegerValue(i, SaleOrderBVO.FLARGESSTYPEFLAG);

      if (Largesstype.APPORTIONMATERIAL.equalsValue(largessflag)
          || Largesstype.APPORTIONLARGESS.equalsValue(largessflag)) {
        listneedrow.add(Integer.valueOf(i));
      }
    }
    return listneedrow;

  }

  private M30TranTypeVO getTranTypeVO() {
    if (null == this.trantypevo) {

      try {
        IM30TranTypeService service =
            NCLocator.getInstance().lookup(IM30TranTypeService.class);
        String trantypeid =
            this.keyValue.getHeadStringValue(SaleOrderHVO.CTRANTYPEID);
        this.trantypevo = service.queryTranTypeVO(trantypeid);
      }
      catch (Exception e) {
        ExceptionUtils.wrappException(e);
      }
    }
    return this.trantypevo;
  }

  /**
   * 根据表体行判断订单是否进行了赠品价格分摊
   * 
   * @param saleordervo
   * @return
   */
  private boolean isSaleOrderApportion() {
    int bodycount = this.keyValue.getBodyCount();
    for (int i = 0; i < bodycount; i++) {
      Integer largesstypeflag =
          this.keyValue.getBodyIntegerValue(i, SaleOrderBVO.FLARGESSTYPEFLAG);

      if (Largesstype.APPORTIONMATERIAL.equalsValue(largesstypeflag)
          || Largesstype.APPORTIONLARGESS.equalsValue(largesstypeflag)) {
        return true;
      }
    }
    return false;
  }

  private boolean isTaxPrior() {

    UFBoolean so23 = null;

    String pk_group = AppContext.getInstance().getPkGroup();
    so23 = SOSysParaInitUtil.getSO23(pk_group);

    if (null == so23) {
      return false;
    }
    return so23.booleanValue();
  }

  private void undoLargessApportion(List<Integer> listneedrow) {

    for (Integer row : listneedrow) {
      int irow = row.intValue();

      Integer largessflag =
          this.keyValue
              .getBodyIntegerValue(irow, SaleOrderBVO.FLARGESSTYPEFLAG);
      if (Largesstype.APPORTIONLARGESS.equalsValue(largessflag)) {
        this.keyValue.setBodyValue(irow, SaleOrderBVO.BLARGESSFLAG,
            UFBoolean.TRUE);
      }
      else {
        this.keyValue.setBodyValue(irow, SaleOrderBVO.BLARGESSFLAG,
            UFBoolean.FALSE);
      }

      this.keyValue.setBodyValue(irow, SaleOrderBVO.FLARGESSTYPEFLAG,
          Largesstype.NOAPPORTION);

      // 原始价税合计
      UFDouble oldorigtaxmny =
          this.keyValue.getBodyUFDoubleValue(irow, SaleOrderBVO.NLARGESSTAXMNY);
      this.keyValue.setBodyValue(irow, SaleOrderBVO.NORIGTAXMNY, oldorigtaxmny);
      this.keyValue.setBodyValue(irow, SaleOrderBVO.NLARGESSTAXMNY, null);
      // 原始无税金额
      UFDouble oldorigmny =
          this.keyValue.getBodyUFDoubleValue(irow, SaleOrderBVO.NLARGESSMNY);
      this.keyValue.setBodyValue(irow, SaleOrderBVO.NORIGMNY, oldorigmny);
      this.keyValue.setBodyValue(irow, SaleOrderBVO.NLARGESSMNY, null);
    }

  }
}
