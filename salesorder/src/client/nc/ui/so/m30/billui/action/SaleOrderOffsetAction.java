package nc.ui.so.m30.billui.action;

import java.awt.event.ActionEvent;
import java.util.Map;

import nc.ui.ml.NCLangRes;
import nc.ui.pub.bill.BillCardPanel;
import nc.ui.pubapp.pub.locator.NCUILocator;
import nc.ui.scmpub.action.SCMActionInitializer;
import nc.ui.so.m30.billui.view.SaleOrderBillForm;
import nc.ui.so.m30.pub.CardEditSetter;
import nc.ui.so.m30.pub.SaleOrderCalculator;
import nc.ui.so.m35.service.so.m30.itf.IDlgArsubToM30Ctr;
import nc.ui.so.pub.keyvalue.CardKeyValue;
import nc.ui.uif2.AppEvent;
import nc.ui.uif2.NCAction;
import nc.ui.uif2.UIState;
import nc.ui.uif2.model.AbstractAppModel;
import nc.vo.pub.lang.UFBoolean;
import nc.vo.pub.lang.UFDouble;
import nc.vo.pubapp.AppContext;
import nc.vo.pubapp.pattern.exception.ExceptionUtils;
import nc.vo.pubapp.res.NCModule;
import nc.vo.scmpub.res.SCMActionCode;
import nc.vo.so.m30.entity.OffsetTempVO;
import nc.vo.so.m30.entity.SaleOrderBVO;
import nc.vo.so.m30.entity.SaleOrderHVO;
import nc.vo.so.m30.rule.HeadTotalCalculateRule;
import nc.vo.so.m30.util.OffsetItfVOUtil;
import nc.vo.so.m30.util.SaleOrderClientContext;
import nc.vo.so.m30trantype.entity.M30TranTypeVO;
import nc.vo.so.m35.entity.ArsubViewVO;
import nc.vo.so.m35.paravo.ItfParaVO;
import nc.vo.so.m35.paravo.OffsetParaVO;
import nc.vo.so.pub.keyvalue.IKeyValue;
import nc.vo.so.rule.OffsetUtil;
import nc.vo.so.util.OffsetDefaltSqlUtil;

/**
 * 销售订单费用冲抵的按钮响应类
 * 
 * @since 6.0
 * @version 2010-12-10 上午09:53:52
 * @author 么贵敬
 */
public class SaleOrderOffsetAction extends NCAction {

  /**
   * Version
   */
  private static final long serialVersionUID = 9162084216392130278L;

  /** 订单卡控件 */
  private SaleOrderBillForm editor;

  /** 订单管理应用模型 */
  private AbstractAppModel model;

  public SaleOrderOffsetAction() {
    super();
    SCMActionInitializer.initializeAction(this, SCMActionCode.SO_OFFSET);
  }

  @Override
  public void doAction(ActionEvent e) throws Exception {
    if (null != this.editor.getM30ClientContext()
        && this.editor.getM30ClientContext().getIsCashSale()) {
      ExceptionUtils.wrappBusinessException(nc.vo.ml.NCLangRes4VoTransl
          .getNCLangRes().getStrByID("4006011_0", "04006011-0009")
          /*@res "您已经做了现销处理不允许做费用冲抵！"*/);
    }
    BillCardPanel cardPanel = this.getEditor().getBillCardPanel();
    IKeyValue keyValue = new CardKeyValue(cardPanel);
    OffsetItfVOUtil voutil = new OffsetItfVOUtil(keyValue);

    // 检查是否有可冲抵的行
    Map<Integer, OffsetParaVO> itfvo = voutil.getinterfaceData();
    // 检查是否有可冲抵的行
    if (null == itfvo || itfvo.size() == 0) {
      ExceptionUtils.wrappBusinessException(NCLangRes.getInstance().getStrByID(
          "4006011_0", "04006011-0239", null, new String[] {})
          /*赠品、折扣和劳务行不做冲抵，过滤后没有可冲抵的行,请检查冲抵比例是否为0*/);
    }
    // 获取销售订单原先冲抵关系
    OffsetTempVO tempvo = this.getEditor().getTempvo();
    if (null == tempvo) {
      tempvo = new OffsetTempVO();
    }

    // 查询并加载显示销售费用单获得用户选定的销售费用单试图VO
    IDlgArsubToM30Ctr control =
        NCUILocator.getInstance().lookup(IDlgArsubToM30Ctr.class,
            NCModule.SO.getName());
    String billid = keyValue.getHeadStringValue(SaleOrderHVO.CSALEORDERID);
    OffsetDefaltSqlUtil sqlutil = new OffsetDefaltSqlUtil();
    String pk_group = keyValue.getHeadStringValue(SaleOrderHVO.PK_GROUP);
    String defaultwhere = sqlutil.getOrderDefaultSql(pk_group, itfvo);
    ArsubViewVO[] selectviewvos = null;
    ItfParaVO itfparavo = new ItfParaVO();
    itfparavo.setBillid(billid);
    itfparavo.setDefaultwhere(defaultwhere);
    itfparavo.setItfvomap(itfvo);
    itfparavo.setOffsettempvo(tempvo);
    try {
      control.queryAndLoadArsub(this.getEditor().getBillCardPanel(),
          this.model, itfparavo);
      selectviewvos = control.getSelectedVOs();
    }
    catch (Exception ex) {
      ExceptionUtils.marsh(ex);
    }
    Map<Integer, OffsetParaVO> newitfvo = voutil.getinterfaceData();
    OffsetUtil offsetutil = new OffsetUtil(pk_group, newitfvo);
    // 获得分配关系行索引为key冲抵金额为Value

    Map<Integer, UFDouble> dismap = offsetutil.manualoffsetArsub(selectviewvos);
    voutil.distributeMapToVO(dismap);
    if (null != dismap && dismap.size() > 0) {
      Integer[] changerows = dismap.keySet().toArray(new Integer[0]);
      this.doafter(changerows);
      // 得到新的冲抵关系将冲抵关系newhmrelation,set进缓存
      Map<String, UFDouble> hmrelation = tempvo.getHmArsubRelation();
      Map<String, UFDouble> newhmrelation =
          offsetutil.getNewRelation(hmrelation);
      tempvo.setHmArsubRelation(newhmrelation);
      this.getEditor().setTempvo(tempvo);

      // 为了更新取消费用冲抵按钮的可用状态
      AppEvent appevent =
          new AppEvent(
              "nc.ui.pubapp.uif2app.event.list.ListHeadRowChangedEvent");
      this.getModel().fireEvent(appevent);
    }
  }

  /**
   * 订单卡控件getter
   * 
   * @return 订单卡控件
   */
  public SaleOrderBillForm getEditor() {
    return this.editor;
  }

  /**
   * 订单应用模型getter
   * 
   * @return 应用模型
   */
  public AbstractAppModel getModel() {
    return this.model;
  }

  /**
   * 订单卡控件setter
   * 
   * @param editor 卡控件
   */
  public void setEditor(SaleOrderBillForm editor) {
    this.editor = editor;
  }

  /**
   * 订单卡控件setter
   * 
   * @param model 应有管理模型
   */
  public void setModel(AbstractAppModel model) {
    this.model = model;
    this.model.addAppEventListener(this);
  }

  @Override
  protected boolean isActionEnable() {
    UIState uistate = this.getModel().getUiState();

    if (UIState.ADD.equals(uistate) || UIState.EDIT.equals(uistate)) {
      CardKeyValue keyValue = new CardKeyValue(this.editor.getBillCardPanel());
      String tranTypeCode =
          keyValue.getHeadStringValue(SaleOrderHVO.VTRANTYPECODE);
      String pk_group = AppContext.getInstance().getPkGroup();
      SaleOrderClientContext cache = this.editor.getM30ClientContext();
      M30TranTypeVO m30transvo = cache.getTransType(tranTypeCode, pk_group);
      if (m30transvo != null) {
        if (m30transvo.getBlrgcashflag().booleanValue()) {
          return false;
        }
        else {
          return true;
        }
      }
      else {
        return false;
      }
    }
    else {
      return false;
    }
  }

  /**
   * 做一些分配结束后的业务规则（重算数量单价金额、计算表头价税合计、冲抵金额）
   * 
   * @param changerows
   */
  private void doafter(Integer[] changerows) {
    BillCardPanel cardPanel = this.getEditor().getBillCardPanel();
    IKeyValue keyValue = new CardKeyValue(cardPanel);

    // 重算数量单价金额开始
    int[] intchangerows = new int[changerows.length];
    for (int i = 0; i < changerows.length; i++) {
      intchangerows[i] = changerows[i].intValue();
    }

    String tranTypeCode =
        keyValue.getHeadStringValue(SaleOrderHVO.VTRANTYPECODE);
    SaleOrderClientContext ordercontex = this.getEditor().getM30ClientContext();
    String pk_group = AppContext.getInstance().getPkGroup();
    M30TranTypeVO trantypevo = ordercontex.getTransType(tranTypeCode, pk_group);
    SaleOrderCalculator calcutor = new SaleOrderCalculator(cardPanel);
    calcutor.setTranTypeVO(trantypevo);
    calcutor.calculate(intchangerows, SaleOrderBVO.NORIGTAXMNY);

    // 重算数量单价金额结束

    // 计算表头价税合计、冲抵金额
    HeadTotalCalculateRule totalrule = new HeadTotalCalculateRule(keyValue);
    totalrule.calculateHeadTotal();

    // 设置编辑性
    UFBoolean oldboffsetflag =
        keyValue.getHeadUFBooleanValue(SaleOrderHVO.BOFFSETFLAG);
    keyValue.setHeadValue(SaleOrderHVO.BOFFSETFLAG, UFBoolean.TRUE);
    if (!oldboffsetflag.booleanValue()) {
      CardEditSetter editset = new CardEditSetter(this.editor);
      editset.setEditEnable();
    }

  }

}
