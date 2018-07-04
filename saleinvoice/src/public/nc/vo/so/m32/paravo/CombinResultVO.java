package nc.vo.so.m32.paravo;

import java.util.Map;

import nc.vo.pubapp.pattern.pub.MapList;
import nc.vo.so.m32.entity.SaleInvoiceBVO;
import nc.vo.so.m32.entity.SaleInvoiceVO;

public class CombinResultVO {

  // 缓存明细数据
  private CombinCacheVO cachevo;

  // 汇总后聚合VO
  private SaleInvoiceVO[] combinvos;

  public CombinResultVO(boolean combinflag) {
    this.cachevo = new CombinCacheVO(combinflag);
  }

  public boolean getBcombinflag() {
    return this.cachevo.getBcombinflag();
  }

  public CombinCacheVO getCacheVO() {
    return this.cachevo;
  }

  public MapList<String, SaleInvoiceBVO> getCombinRela() {
    return this.cachevo.getCombinRela();
  }

  public SaleInvoiceVO[] getCombinvos() {
    return this.combinvos;
  }

  public Map<String, String> getGroupKeys() {
    return this.cachevo.getMapGroupKeys();
  }

  public void setBcombinflag(boolean combinflag) {
    this.cachevo.setBcombinflag(combinflag);
  }

  public void setCachevo(CombinCacheVO cachevo) {
    this.cachevo = cachevo;
  }

  public void setCombinrela(MapList<String, SaleInvoiceBVO> combinrela) {
    this.cachevo.setCombinrela(combinrela);
  }

  public void setCombinvos(SaleInvoiceVO[] combinvos) {
    this.combinvos = combinvos;
  }

  public void setMapGroupKeys(Map<String, String> groupkeys) {
    this.cachevo.setMapGroupKeys(groupkeys);
  }

}
