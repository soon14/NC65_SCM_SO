package nc.ui.so.m30.billui.view.sideform;

import java.awt.Component;
import java.awt.Dimension;
import java.awt.Insets;
import java.util.Map;

import nc.desktop.ui.WorkbenchEnvironment;
import nc.itf.scmpub.reference.uap.bd.accesor.MeasAccessor;
import nc.itf.scmpub.reference.uap.group.SysInitGroupQuery;
import nc.itf.so.m30.ref.ic.atp.ICAtpServicesUtil;
import nc.ui.ml.NCLangRes;
import nc.ui.pub.beans.UIPanel;
import nc.ui.queryarea.component.QueryAreaHyperlinkButton;
import nc.ui.so.m30.billui.view.SaleOrderBillForm;
import nc.ui.so.pub.keyvalue.CardKeyValue;
import nc.ui.uif2.IExceptionHandler;
import nc.ui.uif2.components.widget.IBesideWidgetlet;
import nc.ui.uif2.components.widget.WidgetInfo;
import nc.vo.bd.accessor.IBDData;
import nc.vo.ic.atp.entity.AtpVO;
import nc.vo.ic.atp.pub.AtpQryParamVO;
import nc.vo.pub.lang.MultiLangText;
import nc.vo.pubapp.pattern.exception.ExceptionUtils;
import nc.vo.pubapp.pattern.pub.MathTool;
import nc.vo.so.m30.entity.SaleOrderBVO;
import nc.vo.so.m30.entity.SaleOrderHVO;

import org.apache.commons.lang.StringUtils;

import ChartDirector.BarLayer;
import ChartDirector.ChartViewer;
import ChartDirector.XYChart;

public class ATPSideForm extends UIPanel implements IBesideWidgetlet {

  private static final long serialVersionUID = 2741893959666008896L;

  private IExceptionHandler exceptionHandler;

  // 订单卡片
  private SaleOrderBillForm billForm;

  public ATPSideForm() {
    super();
    this.initUI();
  }

  private void initUI() {
    this.setPreferredSize(new Dimension(150, 150));
    this.add(new QueryAreaHyperlinkButton(new ATPSideFormAction(this,
        this.exceptionHandler)));
    this.updateUI();
  }

  @Override
  public WidgetInfo getWidgetInfo() {
    WidgetInfo info = new WidgetInfo();
    info.setTitle(NCLangRes.getInstance().getStrByID("4006011_0",
        "04006011-0260")/*存量*/);
    return info;
  }

  public void display() {
    if (!SysInitGroupQuery.isICEnabled()) {
      ExceptionUtils
      .wrappBusinessException(nc.vo.ml.NCLangRes4VoTransl.getNCLangRes()
          .getStrByID("4006011_0", "04006011-0438")/*请先启用库存模块！*/);
    }
    int selectedRow =
        this.billForm.getBillCardPanel().getBillTable().getSelectedRow();
    if (selectedRow < 0) {
      String errorMsg =
          nc.vo.ml.NCLangRes4VoTransl.getNCLangRes().getStrByID("4006011_0",
              "04006011-0041")/*@res"请选一个订单行！"*/;
      this.exceptionHandler.handlerExeption(new Exception(errorMsg));
      return;
    }
    this.removeAll();
    this.notifyUpdateData(selectedRow);
  }

  private void notifyUpdateData(int row) {
    CardKeyValue keyValue = new CardKeyValue(this.billForm.getBillCardPanel());
    AtpQryParamVO atpQueryVO = this.creatAtpQueryPara(row, keyValue);

    // 取得现存量和可用量
    if (StringUtils.isEmpty(atpQueryVO.getCmaterialoid())
        || StringUtils.isEmpty(atpQueryVO.getPk_org())) {
      this.createAmountBar("", new double[] {
          0, 0
      });
      return;
    }
    Map<String, AtpVO> atpMap =
        ICAtpServicesUtil.queryAtpAndOnhandNum(new AtpQryParamVO[] {
          atpQueryVO
        });

    AtpVO atpVO = atpMap.get(atpQueryVO.getCmaterialoid());

    double natpnum = MathTool.nvl(atpVO.getNatpnum()).doubleValue();
    double nonhandnum = MathTool.nvl(atpVO.getNonhandnum()).doubleValue();
    double[] data = {
        nonhandnum, natpnum
    };

    String cunitid = keyValue.getBodyStringValue(row, SaleOrderBVO.CUNITID);
    IBDData meadata = MeasAccessor.getDocByPk(cunitid);
    MultiLangText cunittext = meadata.getName();
    String cunitname = cunittext.getText(cunittext.getCurrLangIndex());

    this.createAmountBar(cunitname, data);
  }

  private AtpQryParamVO creatAtpQueryPara(int row, CardKeyValue keyValue) {
    AtpQryParamVO atpQueryVO = new AtpQryParamVO();
    atpQueryVO.setCasscustid(keyValue
        .getHeadStringValue(SaleOrderHVO.CCUSTOMERID));
    atpQueryVO.setCmaterialoid(keyValue.getBodyStringValue(row,
        SaleOrderBVO.CMATERIALID));
    atpQueryVO.setCproductorid(keyValue.getBodyStringValue(row,
        SaleOrderBVO.CPRODUCTORID));
    atpQueryVO.setCprojectid(keyValue.getBodyStringValue(row,
        SaleOrderBVO.CPROJECTID));
    atpQueryVO.setCvendorid(keyValue.getBodyStringValue(row,
        SaleOrderBVO.CVENDORID));
    atpQueryVO.setCwarehouseid(keyValue.getBodyStringValue(row,
        SaleOrderBVO.CSENDSTORDOCID));
    atpQueryVO.setPk_group(keyValue.getHeadStringValue(SaleOrderHVO.PK_GROUP));
    atpQueryVO.setPk_org(keyValue.getBodyStringValue(row,
        SaleOrderBVO.CSENDSTOCKORGID));
    atpQueryVO.setVbatchcode(keyValue.getBodyStringValue(row,
        SaleOrderBVO.VBATCHCODE));
    atpQueryVO.setCcffileid(keyValue.getBodyStringValue(row,
        SaleOrderBVO.CMFFILEID));
    atpQueryVO.setDplandate(keyValue.getBodyUFDateValue(row,
        SaleOrderBVO.DSENDDATE));
    atpQueryVO.setVfree1(keyValue.getBodyStringValue(row, SaleOrderBVO.VFREE1));
    atpQueryVO.setVfree2(keyValue.getBodyStringValue(row, SaleOrderBVO.VFREE2));
    atpQueryVO.setVfree3(keyValue.getBodyStringValue(row, SaleOrderBVO.VFREE3));
    atpQueryVO.setVfree4(keyValue.getBodyStringValue(row, SaleOrderBVO.VFREE4));
    atpQueryVO.setVfree5(keyValue.getBodyStringValue(row, SaleOrderBVO.VFREE5));
    atpQueryVO.setVfree6(keyValue.getBodyStringValue(row, SaleOrderBVO.VFREE6));
    atpQueryVO.setVfree7(keyValue.getBodyStringValue(row, SaleOrderBVO.VFREE7));
    atpQueryVO.setVfree8(keyValue.getBodyStringValue(row, SaleOrderBVO.VFREE8));
    atpQueryVO.setVfree9(keyValue.getBodyStringValue(row, SaleOrderBVO.VFREE9));
    atpQueryVO.setVfree10(keyValue
        .getBodyStringValue(row, SaleOrderBVO.VFREE10));
    return atpQueryVO;
  }

  private void createAmountBar(String cunitname, double[] data) {
    XYChart chart = new XYChart(250, 200);
    BarLayer layer = chart.addBarLayer(data);
    // add by zhangyf for 埃克森美孚，现存量图表中把数量显示在图表上，而不是每次鼠标放上去才显示
    layer.set3D(10);
    layer.addDataSet(data, 0XFF0000);
    layer.setAggregateLabelStyle();
    // end
    chart.setPlotArea(110, 40, 115, 80);
    chart.xAxis().setLabels(
        new String[] {
            NCLangRes.getInstance().getStrByID("4006011_0", "04006011-0261")
            + "(" + cunitname + ")"/*现存量*/,
            NCLangRes.getInstance().getStrByID("4006011_0", "04006011-0262")
            + "(" + cunitname + ")"/*可用量*/
        });
    chart.yAxis().setTitle(cunitname);
    chart.setDefaultFonts(WorkbenchEnvironment.getClientApplet().getFont()
        .getFontName(), WorkbenchEnvironment.getClientApplet().getFont()
        .getFontName(), "");

    ChartViewer viewer = new ChartViewer();
    viewer.setImage(chart.makeImage());
    viewer.setImageMap(chart.getHTMLImageMap("clickable", "",
        "title='{xLabel}: {value} '"));

    this.add(viewer);
    this.updateUI();
  }

  public void resetUI() {
    this.removeAll();
    this.initUI();
  }

  @Override
  public Component getContentComponent() {
    return this;
  }

  @Override
  public Insets getComponentInsets() {
    return null;
  }

  @Override
  public void setWidgetState(int state) {
    //
  }

  public SaleOrderBillForm getBillForm() {
    return this.billForm;
  }

  public void setBillForm(SaleOrderBillForm billForm) {
    this.billForm = billForm;
  }

  public IExceptionHandler getExceptionHandler() {
    return this.exceptionHandler;
  }

  public void setExceptionHandler(IExceptionHandler exceptionHandler) {
    this.exceptionHandler = exceptionHandler;
  }
}
