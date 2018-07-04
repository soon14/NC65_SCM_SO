package nc.ui.so.m32.billui.model;

import java.util.Arrays;

import nc.vo.pubapp.pattern.pub.MapList;
import nc.vo.so.m32.entity.SaleInvoiceBVO;
import nc.vo.so.m32.entity.SaleInvoiceVO;
import nc.vo.so.m32.paravo.CombinCacheVO;
import nc.vo.so.m32.paravo.CombinResultVO;
import nc.vo.so.pub.comparator.RowNoComparator;

import nc.ui.pubapp.uif2app.components.grand.util.ArrayUtil;
import nc.ui.pubapp.uif2app.model.BillManageModel;
import nc.ui.so.m32.billui.pub.SaleInvoiceCombin;

/**
 * 销售发票模型管理器
 * 
 * @since 6.3
 * @version 2013-09-05 10:27:13
 * @author 刘景
 */
public class SaleInvoiceManageModel extends BillManageModel {

  /**
   * 是否已处理汇总
   */
  private boolean isProcesscombin;

  /**
   * 用于销售发票汇总显示缓存
   */
  private CombinCacheVO combinvo;

  /**
   * 获取是否汇总标志
   * 
   * @return boolean
   */
  public boolean isProcesscombin() {
    return this.isProcesscombin;
  }

  /**
   * 设置是否汇总标志
   * 
   * @param isProcesscombin
   */
  public void setProcesscombin(boolean isProcesscombin) {
    this.isProcesscombin = isProcesscombin;
  }

  /**
   * 
   * @param cachevo
   */
  public void setCombinCacheVO(CombinCacheVO cachevo) {
    this.combinvo = cachevo;
  }

  /**
   * 获取缓存中的明细VO
   * 
   * @return CombinCacheVO
   */
  public CombinCacheVO getCombinCacheVO() {
    if (null == this.combinvo) {
      SaleInvoiceCombin combin = new SaleInvoiceCombin();
      boolean so27 = combin.getSO27();
      this.combinvo = new CombinCacheVO(so27);
    }
    return this.combinvo;
  }

  /**
   * 根据明细VO获取汇总VO
   * 
   * @param vos
   * @return SaleInvoiceVO
   */
  public SaleInvoiceVO[] getCombinreVO(SaleInvoiceVO[] vos) {
    if (vos == null || vos.length == 0) {
      return vos;
    }
    CombinResultVO combinres = null;
    CombinCacheVO cache = this.getCombinCacheVO();
    SaleInvoiceVO[] combinvos = null;
    if (cache.getBcombinflag()) {
      SaleInvoiceCombin combinutil = new SaleInvoiceCombin();
      combinres = combinutil.combinSaleInvoices(vos, cache);
      combinvos = combinres.getCombinvos();
      this.setCombinCacheVO(cache);
    }
    else {
      combinvos = vos;
    }
    return combinvos;
  }

  @Override
  public void initModel(Object data) {
    if (null == data) {
      super.initModel(data);
      return;
    }
    SaleInvoiceVO[] vos = null;
    if (data.getClass().isArray()) {
    	vos = ArrayUtil.toArray(SaleInvoiceVO.class, data);
    }
    else {
      vos = new SaleInvoiceVO[] {
        (SaleInvoiceVO) data
      };
    }
    SaleInvoiceVO[] combinrevos = null;
    if (!this.isProcesscombin) {
      this.getCombinCacheVO().setCombinrela(
          new MapList<String, SaleInvoiceBVO>());
      combinrevos = this.getCombinreVO(vos);
    }
    else {
      for (SaleInvoiceVO vo : vos) {
        if (vo.getChildrenVO() == null || vo.getChildrenVO().length == 0) {
          continue;
        }
        RowNoComparator comp = new RowNoComparator(SaleInvoiceBVO.CROWNO);
        Arrays.sort(vo.getChildrenVO(), comp);
      }
      combinrevos = vos;
      this.setProcesscombin(false);
    }
    super.initModel(combinrevos);
  }
}
