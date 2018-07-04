package nc.ui.so.m30.billui.action;

import java.awt.event.ActionEvent;

import nc.ui.bd.feature.fselect.service.FeatureSelectService;
import nc.ui.ml.NCLangRes;
import nc.ui.scmpub.action.SCMActionInitializer;
import nc.ui.so.m30.billui.view.SaleOrderBillForm;
import nc.ui.so.m30.pub.SaleOrderCalculator;
import nc.ui.so.pub.keyvalue.CardKeyValue;
import nc.ui.so.pub.util.BodyItemEditCheckUtil;
import nc.ui.uif2.NCAction;
import nc.vo.bd.feature.ffile.entity.AggFFileVO;
import nc.vo.bd.feature.fselect.entity.FeatrueParamVO;
import nc.vo.bd.feature.fselect.entity.FeatrueParamVO.Ffiletype;
import nc.vo.bd.feature.fselect.entity.FeatrueParamVO.Srctype;
import nc.vo.pub.lang.UFBoolean;
import nc.vo.pub.lang.UFDouble;
import nc.vo.pubapp.AppContext;
import nc.vo.pubapp.pattern.exception.ExceptionUtils;
import nc.vo.pubapp.pattern.pub.MathTool;
import nc.vo.pubapp.pattern.pub.PubAppTool;
import nc.vo.scmpub.res.SCMActionCode;
import nc.vo.scmpub.res.billtype.SOBillType;
import nc.vo.so.m30.entity.SaleOrderBVO;
import nc.vo.so.m30.entity.SaleOrderHVO;
import nc.vo.so.m30.util.FeatureSelectUtil;
import nc.vo.so.m30.util.SaleOrderClientContext;
import nc.vo.so.m30trantype.entity.M30TranTypeVO;
import nc.vo.so.m30trantype.enumeration.SaleMode;
import nc.vo.so.pub.SOConstant;
import nc.vo.so.pub.keyvalue.IKeyValue;
import nc.vo.so.pub.rule.SOCalConditionRule;
import nc.vo.uif2.LoginContext;

/**
 * 销售订单特征选配按钮
 * 
 * @author zhangby5
 * @version 636
 * 
 */
public class SaleOrderFeatureSelectAction extends NCAction {

  /**
   * 
   */
  private static final long serialVersionUID = 1L;

  /** 订单卡控件 */
  private SaleOrderBillForm editor;

  public SaleOrderFeatureSelectAction() {
    super();
    SCMActionInitializer.initializeAction(this, SCMActionCode.SO_FEATURESELECT);
  }

  @Override
  public void doAction(ActionEvent e) throws Exception {
    this.editor.getBillCardPanel().stopEditing();
    IKeyValue keyValue = new CardKeyValue(this.getEditor().getBillCardPanel());
    int selectRow =
        this.getEditor().getBillCardPanel().getBillTable().getSelectedRow();
    this.checkBeforeFeatureSelect(keyValue,selectRow);
    
    boolean isTaxPrior = SOCalConditionRule.isTaxPrior();
    FeatureSelectService service = new FeatureSelectService();
    FeatrueParamVO paramvo =
        this.createFeatrueParamVO(selectRow, keyValue, isTaxPrior);
    LoginContext context = this.getEditor().getModel().getContext();
    AggFFileVO aggfilevo = service.featrueSelect(paramvo, context);
    if (aggfilevo == null) {
      return;
    }
    this.processReturnVO(keyValue, selectRow, aggfilevo, isTaxPrior);
    this.getEditor().getBillCardPanel().getBillModel()
        .loadLoadRelationItemValue();
  }

  private void checkBeforeFeatureSelect(IKeyValue keyValue, int selectRow) {
    String tranTypeCode =
        keyValue.getHeadStringValue(SaleOrderHVO.VTRANTYPECODE);
    String pk_group = AppContext.getInstance().getPkGroup();
    SaleOrderClientContext cache = this.editor.getM30ClientContext();
    M30TranTypeVO m30transvo = cache.getTransType(tranTypeCode, pk_group);
    if (m30transvo != null) {
      if (SaleMode.MODE_RETURN.equalsValue(m30transvo.getFsalemode())) {
        ExceptionUtils
            .wrappBusinessException(NCLangRes.getInstance().getStrByID(
                "4006011_0", "04006011-0522")/*交易类型的退换货模式为退货的不允许进行特征选配！*/);
      }
    }
    
    if (selectRow < 0) {
      ExceptionUtils.wrappBusinessException(NCLangRes.getInstance().getStrByID(
          "4006011_0", "04006011-0487")/*请选择要进行特征选配的行！*/);
    }

    if (FeatureSelectUtil.isOut(keyValue, selectRow)) {
      ExceptionUtils.wrappBusinessException(NCLangRes.getInstance().getStrByID(
          "4006011_0", "04006011-0488")/*当前单据行已经生成下游，无法进行特征选配*/);
    }
    
    if(this.isSaleOrderOffset(keyValue)){
      ExceptionUtils.wrappBusinessException(NCLangRes.getInstance().getStrByID(
          "4006011_0", "04006011-0510")/*做过费用冲抵的单据不允许进行此操作，请取消费用冲抵后再操作！*/);
    }
    
  }
  
  private boolean isSaleOrderOffset(IKeyValue keyValue) {
    UFBoolean offsetfalg =
        keyValue.getHeadUFBooleanValue(SaleOrderHVO.BOFFSETFLAG);
    return offsetfalg == null ? false : offsetfalg.booleanValue();
  }

  private void processReturnVO(IKeyValue keyValue, int selectRow,
      AggFFileVO aggfilevo, boolean isTaxPrior) {
    // 当订单价格为空且价格字段不能编辑时，说明是询价失败，此时不能再往界面上设值
    if (null == keyValue.getBodyUFDoubleValue(selectRow,
        SaleOrderBVO.NQTORIGTAXNETPRC)
        && !BodyItemEditCheckUtil.checkPriceEditable(getEditor(), selectRow)) {
      return;
    }
    // 将选配的特征码档案中的参照特征码赋值给表体的特征码
    keyValue.setBodyValue(selectRow, SaleOrderBVO.CMFFILEID, aggfilevo
        .getParentVO().getCffileid());
    UFDouble nfeatureprice = aggfilevo.getParentVO().getNfeatureprice();
    keyValue.setBodyValue(selectRow, SaleOrderBVO.NMFFILEPRICE,
        nfeatureprice == null ? UFDouble.ZERO_DBL : nfeatureprice);
    UFDouble totalprice = aggfilevo.getParentVO().getNtotalprice();
    if (MathTool.isZero(totalprice)) {
      totalprice =
          MathTool.add(aggfilevo.getParentVO().getNfeatureprice(), aggfilevo
              .getParentVO().getNbaseprice());
    }
    keyValue.setBodyValue(selectRow, isTaxPrior ? SaleOrderBVO.NQTORIGTAXNETPRC
        : SaleOrderBVO.NQTORIGNETPRICE, totalprice);
    keyValue.setBodyValue(selectRow, SOConstant.AGGFFILEVO, aggfilevo);
    SaleOrderCalculator calculator =
        new SaleOrderCalculator(getEditor().getBillCardPanel());
    calculator.calculate(selectRow, isTaxPrior ? SaleOrderBVO.NQTORIGTAXNETPRC
        : SaleOrderBVO.NQTORIGNETPRICE);
  }

  private FeatrueParamVO createFeatrueParamVO(int selectRow,
      IKeyValue keyValue, boolean isTaxPrior) {
    FeatrueParamVO paramvo = new FeatrueParamVO();
    paramvo.setSrctype(Srctype.SHOWPRICE);
    paramvo.setCanedit(Boolean.TRUE);
    paramvo.setCmaterialvid(keyValue.getBodyStringValue(selectRow,
        SaleOrderBVO.CMATERIALVID));
    paramvo.setCmaterialid(keyValue.getBodyStringValue(selectRow,
        SaleOrderBVO.CMATERIALID));

    String cmffileid =
        keyValue.getBodyStringValue(selectRow, SaleOrderBVO.CMFFILEID);
    paramvo.setFfileid(cmffileid);

    paramvo.setCvendorid(keyValue.getBodyStringValue(selectRow,
        SaleOrderBVO.CVENDORID));
    paramvo.setCprojectid(keyValue.getBodyStringValue(selectRow,
        SaleOrderBVO.CPROJECTID));
    paramvo.setCproductorid(keyValue.getBodyStringValue(selectRow,
        SaleOrderBVO.CPRODUCTORID));
    paramvo.setCcustomerid(keyValue
        .getHeadStringValue(SaleOrderHVO.CCUSTOMERID));

    paramvo.setVfree1(keyValue.getBodyStringValue(selectRow,
        SaleOrderBVO.VFREE1));
    paramvo.setVfree2(keyValue.getBodyStringValue(selectRow,
        SaleOrderBVO.VFREE2));
    paramvo.setVfree3(keyValue.getBodyStringValue(selectRow,
        SaleOrderBVO.VFREE3));
    paramvo.setVfree4(keyValue.getBodyStringValue(selectRow,
        SaleOrderBVO.VFREE4));
    paramvo.setVfree5(keyValue.getBodyStringValue(selectRow,
        SaleOrderBVO.VFREE5));
    paramvo.setVfree6(keyValue.getBodyStringValue(selectRow,
        SaleOrderBVO.VFREE6));
    paramvo.setVfree7(keyValue.getBodyStringValue(selectRow,
        SaleOrderBVO.VFREE7));
    paramvo.setVfree8(keyValue.getBodyStringValue(selectRow,
        SaleOrderBVO.VFREE8));
    paramvo.setVfree9(keyValue.getBodyStringValue(selectRow,
        SaleOrderBVO.VFREE9));
    paramvo.setVfree10(keyValue.getBodyStringValue(selectRow,
        SaleOrderBVO.VFREE10));

    UFDouble ntotalprice =
        keyValue.getBodyUFDoubleValue(selectRow,
            isTaxPrior ? SaleOrderBVO.NQTORIGTAXNETPRC
                : SaleOrderBVO.NQTORIGNETPRICE);
    UFDouble nfeatureprice =
        keyValue.getBodyUFDoubleValue(selectRow, SaleOrderBVO.NMFFILEPRICE);
    paramvo.setBaseprice(MathTool.sub(ntotalprice, nfeatureprice));

    paramvo.setPk_group(keyValue.getHeadStringValue(SaleOrderHVO.PK_GROUP));
    paramvo.setCorigcurrencyid(keyValue
        .getHeadStringValue(SaleOrderHVO.CORIGCURRENCYID));
    paramvo.setVsrcbid(keyValue.getBodyStringValue(selectRow,
        SaleOrderBVO.CSALEORDERBID));
    paramvo.setVsrcid(keyValue.getBodyStringValue(selectRow,
        SaleOrderBVO.CSALEORDERID));
    paramvo.setVsrccode(keyValue.getHeadStringValue(SaleOrderHVO.VBILLCODE));
    paramvo.setVsrcrowno(keyValue.getBodyStringValue(selectRow,
        SaleOrderBVO.CROWNO));
    paramvo.setVsrctype(SOBillType.Order.getCode());
    // 基础价格的编辑性
    paramvo.setBaseCanEdit(BodyItemEditCheckUtil.checkPriceEditable(
        getEditor(), selectRow));
    if (PubAppTool.isNull(cmffileid)) {
      paramvo.setfFiletype(Ffiletype.ISINSERT);
    }
    else {
      paramvo.setfFiletype(Ffiletype.ISUPDATE);
    }
    AggFFileVO aggffilevo =
        (AggFFileVO) keyValue.getBodyValue(selectRow, SOConstant.AGGFFILEVO);
    if (aggffilevo != null) {
      aggffilevo.getParentVO().setNbaseprice(paramvo.getBaseprice());
      aggffilevo.getParentVO().setNtotalprice(
          MathTool.add(paramvo.getBaseprice(), nfeatureprice));
    }
    paramvo.setSelectFFileVO(aggffilevo);
    return paramvo;
  }

  public SaleOrderBillForm getEditor() {
    return editor;
  }

  public void setEditor(SaleOrderBillForm editor) {
    this.editor = editor;
  }
  
}
