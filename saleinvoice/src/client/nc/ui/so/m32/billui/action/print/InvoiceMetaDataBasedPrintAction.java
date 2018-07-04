package nc.ui.so.m32.billui.action.print;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import nc.vo.pub.AggregatedValueObject;
import nc.vo.pub.BusinessException;
import nc.vo.pub.CircularlyAccessibleValueObject;
import nc.vo.pub.lang.UFDouble;
import nc.vo.pubapp.pattern.exception.ExceptionUtils;
import nc.vo.pubapp.pattern.pub.MathTool;
import nc.vo.pubapp.util.DefaultVOMerger;
import nc.vo.so.m32.entity.SaleInvoiceBVO;
import nc.vo.trade.checkrule.VOChecker;

import nc.ui.pub.print.IMetaDataDataSource;
import nc.ui.pubapp.uif2app.actions.MetaDataBasedPrintAction;
import nc.ui.pubapp.uif2app.model.BatchBillTableModel;
import nc.ui.pubapp.uif2app.model.BillManageModel;
import nc.ui.pubapp.uif2app.model.HierachicalDataAppModel;

/**
 * 销售发票支持欧盟发票打印
 * 
 * @since 6.0
 * @version 2012-4-9 上午09:02:06
 * @author 么贵敬
 */
public class InvoiceMetaDataBasedPrintAction extends MetaDataBasedPrintAction {

  private static final long serialVersionUID = 4377444581502271316L;

  /**
   * 打印数据源
   * 
   * @since 6.3
   * @version 2012-12-21 上午11:22:55
   * @author yaogj
   */
  public class SOMetaDataSource extends MetaDataSource {

    /**
     * 
     */
    private static final long serialVersionUID = -4024748128276598323L;

    private Map<String, Object> m_values = new HashMap<String, Object>();

    /**
     * 构造方法
     * 
     * @param printData
     */
    public SOMetaDataSource(Object[] printData) {
      this.setPrintData(printData);
      // super.printData = printData;

    }

    @Override
    public String[] getDependentItemExpressByExpress(String itemExpress) {

      if (!itemExpress.startsWith("ds_sum_")) {
        return null;
      }
      if (this.m_values.size() == 0) {
        this.initValues();
      }
      Object ret = this.m_values.get(itemExpress);
      if (ret instanceof Object[]) {
        Object[] ms = (Object[]) ret;
        String[] datas = new String[ms.length];
        for (int i = 0; i < datas.length; i++) {
          datas[i] = ms[i].toString();
        }
        return datas;
      }

      return new String[] {
        ret.toString()
      };

    }

    private void initValues() {
      Object[] obj = this.getPrintData();
      AggregatedValueObject vo = (AggregatedValueObject) obj[0];
      CircularlyAccessibleValueObject[] bvos = vo.getChildrenVO();
      DefaultVOMerger mergertool = new DefaultVOMerger();
      mergertool.setNumAttr(SaleInvoiceBVO.NNUM);

      mergertool.setSummingAttr(new String[] {
        SaleInvoiceBVO.NORIGMNY
      });
      mergertool.setGroupingAttr(new String[] {
        SaleInvoiceBVO.CTAXCODEID, SaleInvoiceBVO.NTAXRATE
      });
      CircularlyAccessibleValueObject[] mergebvos = null;
      try {
        mergebvos = mergertool.mergeByGroup(bvos);
      }
      catch (BusinessException e) {
        ExceptionUtils.wrappException(e);
      }
      if (null != mergebvos) {
        int length = mergebvos.length;
        String[] norigmny = new String[length];
        String[] ntax = new String[length];
        String[] ctaxcodeid = new String[length];
        String[] ntaxrate = new String[length];
        UFDouble totalMny = null;
        for (int i = 0; i < length; i++) {
          CircularlyAccessibleValueObject bvo = mergebvos[i];
          norigmny[i] =
              bvo.getAttributeValue(SaleInvoiceBVO.NORIGMNY).toString();
          ctaxcodeid[i] =
              (String) bvo.getAttributeValue(SaleInvoiceBVO.CTAXCODEID);
          ntaxrate[i] =
              bvo.getAttributeValue(SaleInvoiceBVO.NTAXRATE).toString();
          UFDouble mny =
              (UFDouble) bvo.getAttributeValue(SaleInvoiceBVO.NORIGMNY);
          UFDouble tax =
              (UFDouble) bvo.getAttributeValue(SaleInvoiceBVO.NTAXRATE);
          UFDouble taxMny = mny.multiply(tax.div(100.00), mny.getPower());
          ntax[i] = taxMny.toString();
          if (null == totalMny) {
            totalMny = mny;
            totalMny = MathTool.add(totalMny, taxMny);
          }
          else {
            totalMny = MathTool.add(totalMny, mny);
            totalMny = MathTool.add(totalMny, taxMny);
          }
        }
        // 取原币的无税金额放在打印模板上
        this.m_values.put("ds_sum_" + SaleInvoiceBVO.NCALTAXMNY, norigmny);
        this.m_values.put("ds_sum_" + SaleInvoiceBVO.NTAX, ntax);
        this.m_values.put("ds_sum_" + SaleInvoiceBVO.CTAXCODEID, ctaxcodeid);
        this.m_values.put("ds_sum_" + SaleInvoiceBVO.NTAXRATE, ntaxrate);
        this.m_values.put("ds_sum_totalmny", totalMny);
      }
    }

    @Override
    public String[] getItemValuesByExpress(String itemExpress) {
      if (!itemExpress.startsWith("ds_sum_")) {
        return null;
      }
      Object ret = this.m_values.get(itemExpress);
      if (ret instanceof Object[]) {
        Object[] ms = (Object[]) ret;
        String[] datas = new String[ms.length];
        for (int i = 0; i < datas.length; i++) {
          datas[i] = ms[i].toString();
        }
        return datas;
      }

      return new String[] {
        ret.toString()
      };

    }

  }

  @Override
  protected IMetaDataDataSource[] getDefaultMetaDataSource() {
    IMetaDataDataSource[] defaultDataSource = null;
    Object[] datas = null;
    boolean isMultiTask = true;
    if (this.getModel() instanceof BillManageModel) {
      if (this.isPrintAll()) {
        BillManageModel billmodel = (BillManageModel) this.getModel();
        List<Object> list = billmodel.getData();
        datas = new Object[list.size()];
        list.toArray(datas);
      }
      else {
        datas = ((BillManageModel) this.getModel()).getSelectedOperaDatas();
      }
      if (VOChecker.isEmpty(datas)) {
        datas = new Object[] {
          this.getModel().getSelectedData()
        };
      }

    }
    else if (this.getModel() instanceof HierachicalDataAppModel) {
      isMultiTask = false;
      if (this.isPrintAll()) {
        datas = ((HierachicalDataAppModel) this.getModel()).getAllDatas();
      }
      else {
        datas = ((HierachicalDataAppModel) this.getModel()).getSelectedDatas();
      }
    }
    else {
      isMultiTask = false;
      if (this.isPrintAll()) {
        datas =
            ((BatchBillTableModel) this.getModel()).getRows().toArray(
                new Object[0]);
      }
      else {
        datas = ((BatchBillTableModel) this.getModel()).getSelectedOperaDatas();
      }
    }
    if (this.getDataSplit() != null) {
      datas = this.getDataSplit().splitData(datas);
    }
    if (!VOChecker.isEmpty(datas)) {
      if (isMultiTask) {
        defaultDataSource = new SOMetaDataSource[datas.length];
        for (int i = 0; i < defaultDataSource.length; i++) {
          defaultDataSource[i] = new SOMetaDataSource(new Object[] {
            datas[i]
          });
        }
      }
      else {
        defaultDataSource = new SOMetaDataSource[] {
          new SOMetaDataSource(datas)
        };
      }
    }
    return defaultDataSource;
  }

}
