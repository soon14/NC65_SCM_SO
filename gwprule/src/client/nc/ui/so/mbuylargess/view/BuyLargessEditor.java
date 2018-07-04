package nc.ui.so.mbuylargess.view;

import nc.itf.scmpub.reference.uap.org.OrgUnitPubService;
import nc.ui.pub.bill.BillCardPanel;
import nc.ui.pubapp.AppUiContext;
import nc.ui.pubapp.uif2app.view.ShowUpableBillForm;
import nc.ui.so.pub.keyvalue.CardKeyValue;
import nc.vo.pub.lang.UFDate;
import nc.vo.scmpub.util.TimeUtils;
import nc.vo.so.mbuylargess.entity.BuyLargessBVO;
import nc.vo.so.mbuylargess.entity.BuyLargessHVO;
import nc.vo.so.pub.SOConstant;
import nc.vo.so.pub.keyvalue.IKeyValue;
import nc.vo.so.pub.util.BaseSaleClassUtil;

public class BuyLargessEditor extends ShowUpableBillForm {
  private static final long serialVersionUID = 3980285733144504397L;

  /**
   * 父类方法重写
   * 
   * @see nc.ui.pubapp.uif2app.view.BillForm#initUI()
   */
  @Override
  public void initUI() {
    super.initUI();
    // 根据参数SO79:物料分类方式 SO80:客户分类方式 设置分类字段显示属性
    String pk_group = this.getModel().getContext().getPk_group();
    boolean isbas = BaseSaleClassUtil.isMarBaseClass(pk_group);
    this.getBillCardPanel().getHeadItem("pk_marbasclass").setShow(isbas);
    this.getBillCardPanel().getHeadItem("pk_marsaleclass").setShow(!isbas);
    isbas = BaseSaleClassUtil.isCustBaseClass(pk_group);
    this.getBillCardPanel().getHeadItem("pk_custclass").setShow(isbas);
    this.getBillCardPanel().getHeadItem("pk_custsaleclass").setShow(!isbas);
    this.getBillCardPanel().hideBodyTableCol("pk_marsaleclass");
    this.getBillCardPanel().hideBodyTableCol("pk_marbasclass");
    this.getBillCardPanel().hideBodyTableCol("pk_material.pk_marbasclass");
    this.getBillCardPanel().hideBodyTableCol("pk_material.pk_measdoc");
    // this.getBillCardPanel().getHeadItem("pk_material.pk_measdoc").setShow(
    // false);
    this.getBillCardPanel().setBillData(this.getBillCardPanel().getBillData());
  }

  /**
   * 父类方法重写 在编辑器进入新增状态后被调用。
   * 
   * @see nc.ui.uif2.editor.BillForm#setDefaultValue()
   */
  @Override
  protected void setDefaultValue() {

    BillCardPanel cardPanel = this.getBillCardPanel();
    IKeyValue keyValue = new CardKeyValue(cardPanel);

    String pk_group = AppUiContext.getInstance().getPkGroup();
    keyValue.setHeadValue(BuyLargessHVO.PK_GROUP, pk_group);

    String pk_org = this.getModel().getContext().getPk_org();
    keyValue.setHeadValue(BuyLargessHVO.PK_ORG, pk_org);
    cardPanel.getBillData().loadEditHeadRelation(BuyLargessHVO.PK_ORG);

    String defcurr = OrgUnitPubService.queryOrgCurrByPk(pk_org);
    keyValue.setHeadValue(BuyLargessHVO.PK_CURRINFO, defcurr);

    int len = keyValue.getBodyCount();
    UFDate busdate = AppUiContext.getInstance().getBusiDate();
    UFDate begindate = busdate.asLocalBegin();
    UFDate enddate = TimeUtils.getEndDate(SOConstant.SYSENDDATE);
    enddate = enddate.asLocalEnd();
    for (int i = 0; i < len; i++) {
      cardPanel.getBillModel().setValueAt(begindate, i, BuyLargessBVO.DBEGDATE);
      cardPanel.getBillModel().setValueAt(enddate, i, BuyLargessBVO.DENDDATE);
      cardPanel.getBillModel().setValueAt(Integer.valueOf(0), i,
          BuyLargessBVO.FTOPLIMITTYPE);
    }

  }
}
