package nc.ui.so.mbuylargess.editor.bodyevent;

import nc.ui.pub.bill.BillCardPanel;
import nc.ui.pubapp.AppUiContext;
import nc.ui.pubapp.pub.scale.UIScaleUtils;
import nc.ui.pubapp.uif2app.event.IAppEventHandler;
import nc.ui.pubapp.uif2app.event.card.CardBodyAfterEditEvent;
import nc.ui.uif2.editor.BillForm;
import nc.vo.pub.lang.UFDate;
import nc.vo.pub.lang.UFDouble;
import nc.vo.pubapp.pattern.data.ValueUtils;
import nc.vo.pubapp.pattern.pub.MathTool;
import nc.vo.pubapp.scale.ScaleUtils;
import nc.vo.scmpub.util.TimeUtils;
import nc.vo.so.mbuylargess.entity.BuyLargessBVO;
import nc.vo.so.pub.SOConstant;

public class BodyAfterEditHandler implements
    IAppEventHandler<CardBodyAfterEditEvent> {
  // 上限类型：不控制
  private static final int NOTOPLIMIT = 2;

  private BillCardPanel billCardPanel;

  private UFDouble nmy;

  private UFDouble num;

  private UFDouble price;

  public BodyAfterEditHandler(BillForm editor) {
    this.billCardPanel = editor.getBillCardPanel();
  }

  @Override
  public void handleAppEvent(CardBodyAfterEditEvent e) {
    if (e.getKey().equals(BuyLargessBVO.FTOPLIMITTYPE)) {
      this.setToplimitType(e);
    }
    else if (e.getKey().equals(BuyLargessBVO.NNUM)) {
      this.editNum(e);
    }
    else if (e.getKey().equals(BuyLargessBVO.NPRICE)) {
      this.editPrice(e);
    }
    else if (e.getKey().equals(BuyLargessBVO.NMNY)) {
      this.editNmy(e);
    }
    else if (e.getKey().equals(BuyLargessBVO.PK_MATERIAL)) {
      this.setMaterial(e);
    }
    this.setDefauleVaule();
  }

  private void editNmy(CardBodyAfterEditEvent e) {
    this.initValue(e);
    int row = e.getRow();
    ScaleUtils scale = UIScaleUtils.getScaleUtils();
    String currid =
        (String) this.billCardPanel.getHeadItem("pk_currinfo")
            .getValueObject();
    if (this.nmy != null) {
      if ((this.num != null)
          && (MathTool.compareTo(this.num, UFDouble.ZERO_DBL) != 0)) {
      
        this.price = this.nmy.div(this.num);
        this.price = scale.adjustSoPuPriceScale(this.price,currid);
        this.billCardPanel.setBodyValueAt(this.price, row, "nprice");
      }
      else if ((this.price != null)
          && (MathTool.compareTo(this.price, UFDouble.ZERO_DBL) != 0)) {
        String unitid =
            (String) this.billCardPanel.getBodyValueAt(row, "pk_measdoc");
        this.num = this.nmy.div(this.price);
        this.num = scale.adjustNumScale(this.num, unitid);
        this.billCardPanel.setBodyValueAt(this.num, row, "nnum");
      }
    }
    else {
      if (this.price != null) {
        this.price = scale.adjustSoPuPriceScale(UFDouble.ZERO_DBL,currid);
        this.billCardPanel.setBodyValueAt(this.price, row, "nprice");
      }
    }

  }

  private void editNum(CardBodyAfterEditEvent e) {
    this.initValue(e);
    int row = e.getRow();
    ScaleUtils scale = UIScaleUtils.getScaleUtils();
    String currid =
        (String) this.billCardPanel.getHeadItem("pk_currinfo")
            .getValueObject();
    if (this.num != null) {
      if (this.price != null) {
    
        this.nmy = this.num.multiply(this.price);
        this.nmy = scale.adjustMnyScale(this.nmy, currid);
        this.billCardPanel.setBodyValueAt(this.nmy, row, "nmny");
      }
      else {
        if ((this.nmy != null)
            && (MathTool.compareTo(this.num, UFDouble.ZERO_DBL) != 0)) {
          this.price = this.nmy.div(this.num);
          this.price = scale.adjustSoPuPriceScale(this.price,currid);
          this.billCardPanel.setBodyValueAt(this.price, row, "nprice");
        }
        else {
          if ((MathTool.compareTo(this.num, UFDouble.ZERO_DBL) == 0)
              && (this.nmy != null)) {
            this.nmy = scale.adjustMnyScale(UFDouble.ZERO_DBL, currid);
            this.billCardPanel.setBodyValueAt(this.nmy, row, "nmny");
          }
        }
      }
    }
    else {
      this.billCardPanel.setBodyValueAt(null, row, "nmny");
    }
  }

  private void editPrice(CardBodyAfterEditEvent e) {
    this.initValue(e);
    int row = e.getRow();
    ScaleUtils scale = UIScaleUtils.getScaleUtils();
    if (this.price != null) {
      if (this.num != null) {
        String currid =
            (String) this.billCardPanel.getHeadItem("pk_currinfo")
                .getValueObject();
        this.nmy = this.num.multiply(this.price);
        this.nmy = scale.adjustMnyScale(this.nmy, currid);
        this.billCardPanel.setBodyValueAt(this.nmy, row, "nmny");
      }
      else {
        if ((this.nmy != null)
            && (MathTool.compareTo(this.price, UFDouble.ZERO_DBL) != 0)) {
          String unitid =
              (String) this.billCardPanel.getBodyValueAt(row, "pk_measdoc");
          this.num = this.nmy.div(this.price);
          this.num = scale.adjustNumScale(this.num, unitid);
          this.billCardPanel.setBodyValueAt(this.num, row, "nnum");
        }
        else {
          if ((MathTool.compareTo(this.price, UFDouble.ZERO_DBL) == 0)
              && (this.nmy != null)) {
            String currid =
                (String) this.billCardPanel.getHeadItem("pk_currinfo")
                    .getValueObject();
            this.nmy = scale.adjustMnyScale(UFDouble.ZERO_DBL, currid);
            this.billCardPanel.setBodyValueAt(this.nmy, row, "nmny");
          }
        }
      }
    }
    else {
      this.billCardPanel.setBodyValueAt(null, row, "nmny");
    }
  }

  private void initValue(CardBodyAfterEditEvent e) {
    int row = e.getRow();
    Object num1 = this.billCardPanel.getBillModel().getValueAt(row, "nnum");
    Object nprice = this.billCardPanel.getBillModel().getValueAt(row, "nprice");
    Object nmny = this.billCardPanel.getBillModel().getValueAt(row, "nmny");
    if (num1 != null) {
      UFDouble number = ValueUtils.getUFDouble(num1);
      this.num = number;
    }
    if (nprice != null) {
      UFDouble price1 = ValueUtils.getUFDouble(nprice);
      this.price = price1;
    }
    if (nmny != null) {
      UFDouble mny = ValueUtils.getUFDouble(nmny);
      this.nmy = mny;
    }
  }

  private void setDefauleVaule() {
    this.nmy = null;
    this.num = null;
    this.price = null;
  }

  /**
   * 编辑物料要处理的数据项
   */
  private void setMaterial(CardBodyAfterEditEvent e) {
    int row = e.getRow();
    UFDate busdate = AppUiContext.getInstance().getBusiDate();
    UFDate begindate = busdate.asLocalBegin();
    UFDate enddate = TimeUtils.getEndDate(SOConstant.SYSENDDATE);
    enddate = enddate.asLocalEnd();
    this.billCardPanel.setBodyValueAt(begindate, row, BuyLargessBVO.DBEGDATE);
    this.billCardPanel.setBodyValueAt(enddate, row, BuyLargessBVO.DENDDATE);
    this.billCardPanel.setBodyValueAt(Integer.valueOf(0), row,
        BuyLargessBVO.FTOPLIMITTYPE);
  }

  private void setToplimitType(CardBodyAfterEditEvent e) {
    // 获得用户选择的上限类型
    int col =
        this.billCardPanel.getBillModel().getBodyColByKey("ftoplimittype");
    Object objtoplimit =
        this.billCardPanel.getBillModel().getValueAt(e.getRow(), col);
    Object convervalue =
        this.billCardPanel.getBodyItem("ftoplimittype").converType(objtoplimit);
    int ftoplimittype = Integer.parseInt(convervalue.toString());

    this.billCardPanel.setBodyValueAt(null, e.getRow(), "ntoplimitvalue");
    if (BodyAfterEditHandler.NOTOPLIMIT == ftoplimittype) {
      this.billCardPanel.setCellEditable(e.getRow(), "ntoplimitvalue", false);
    }
    else {
      this.billCardPanel.setCellEditable(e.getRow(), "ntoplimitvalue", true);
    }
  }
}
