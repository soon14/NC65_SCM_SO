package nc.vo.so.mbuylargess.view;

import nc.vo.pub.lang.UFDouble;
import nc.vo.pubapp.pattern.model.entity.view.AbstractDataView;
import nc.vo.pubapp.pattern.model.meta.entity.view.DataViewMetaFactory;
import nc.vo.pubapp.pattern.model.meta.entity.view.IDataViewMeta;
import nc.vo.so.mbuylargess.entity.BuyLargessBVO;
import nc.vo.so.mbuylargess.entity.BuyLargessHVO;

public class BuyLargessMatchViewVO extends AbstractDataView {

  private static final long serialVersionUID = 6763533108298227360L;

  @Override
  public IDataViewMeta getMetaData() {

    IDataViewMeta viewmeta =
        DataViewMetaFactory.getInstance().getDataViewMeta(
            BuyLargessMatchViewMeta.class);
    return viewmeta;
  }

  public void setPk_buylargess(String pk_buylargess) {
    this.setAttributeValue(BuyLargessHVO.PK_BUYLARGESS, pk_buylargess);
  }

  public String getPk_buylargess() {
    return (String) this.getAttributeValue(BuyLargessHVO.PK_BUYLARGESS);
  }

  public void setCpromottypeid(String cpromottypeid) {
    this.setAttributeValue(BuyLargessHVO.CPROMOTTYPEID, cpromottypeid);
  }

  public String getCpromottypeid() {
    return (String) this.getAttributeValue(BuyLargessHVO.CPROMOTTYPEID);
  }

  /**
   * 设置营销活动
   * 
   * @param cmarketactid
   */
  public void setCmarketactid(String cmarketactid) {
    this.setAttributeValue(BuyLargessHVO.CMARKETACTID, cmarketactid);
  }

  /**
   * 
   * @return 获取营销活动
   */
  public String getCmarketactid() {
    return (String) this.getAttributeValue(BuyLargessHVO.CMARKETACTID);
  }

  public void setNbuynum(UFDouble nbuynum) {
    this.setAttributeValue(BuyLargessHVO.NBUYNUM, nbuynum);
  }

  public UFDouble getNbuynum() {
    return (UFDouble) this.getAttributeValue(BuyLargessHVO.NBUYNUM);
  }

  public void setPk_material(String pk_material) {
    this.setAttributeValue(BuyLargessBVO.PK_MATERIAL, pk_material);
  }

  public String getPk_material() {
    return (String) this.getAttributeValue(BuyLargessBVO.PK_MATERIAL);
  }

  public void setPk_measdoc(String pk_measdoc) {
    this.setAttributeValue(BuyLargessBVO.PK_MEASDOC, pk_measdoc);
  }

  public String getPk_measdoc() {
    return (String) this.getAttributeValue(BuyLargessBVO.PK_MEASDOC);
  }

  public void setNnum(UFDouble nnum) {
    this.setAttributeValue(BuyLargessBVO.NNUM, nnum);
  }

  public UFDouble getNnum() {
    return (UFDouble) this.getAttributeValue(BuyLargessBVO.NNUM);
  }

  public void setNprice(UFDouble nprice) {
    this.setAttributeValue(BuyLargessBVO.NPRICE, nprice);
  }

  public UFDouble getNprice() {
    return (UFDouble) this.getAttributeValue(BuyLargessBVO.NPRICE);
  }

  public void setNmny(UFDouble nmny) {
    this.setAttributeValue(BuyLargessBVO.NMNY, nmny);
  }

  public UFDouble getNmny() {
    return (UFDouble) this.getAttributeValue(BuyLargessBVO.NMNY);
  }

  public void setFtoplimittype(Integer ftoplimittype) {
    this.setAttributeValue(BuyLargessBVO.FTOPLIMITTYPE, ftoplimittype);
  }

  public Integer getFtoplimittype() {
    return (Integer) this.getAttributeValue(BuyLargessBVO.FTOPLIMITTYPE);
  }

  public void setNtoplimitvalue(UFDouble ntoplimitvalue) {
    this.setAttributeValue(BuyLargessBVO.NTOPLIMITVALUE, ntoplimitvalue);
  }

  public UFDouble getNtoplimitvalue() {
    return (UFDouble) this.getAttributeValue(BuyLargessBVO.NTOPLIMITVALUE);
  }

  public void setParaindex(Integer paraindex) {
    this.setAttributeValue(BuyLargessMatchViewMeta.PARAINDEX, paraindex);
  }

  public Integer getParaindex() {
    return (Integer) this.getAttributeValue(BuyLargessMatchViewMeta.PARAINDEX);
  }
}
