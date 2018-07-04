package nc.vo.so.report.profit;

import nc.vo.pub.lang.UFDouble;
import nc.vo.pubapp.pattern.model.entity.view.AbstractDataView;
import nc.vo.pubapp.pattern.model.meta.entity.view.DataViewMetaFactory;
import nc.vo.pubapp.pattern.model.meta.entity.view.IDataViewMeta;

public class OrderProfitViewVO extends AbstractDataView {

  // 成本单价
  public static final String NCOSTPRICE = "ncostprice";

  // 主数量
  public static final String NMAINNUM = "nmainnum";

  // 主本币无税净价
  public static final String NNETPRICE = "nnetprice";

  // 无税金额(报表展示的无税金额 不是订单上的)
  public static final String NNTAXMNY = "nntaxmny";

  // 无税单价
  public static final String NNTAXPRICE = "nntaxprice";

  /****/
  // 订单主数量
  public static final String NORDERNNUM = "nordernnum";

  // 主本币无税单价
  public static final String NPRICE = "nprice";

  // 毛利率
  public static final String NPROFITCATE = "nprofitcate";

  // 毛利
  public static final String NPROFITMNY = "nprofitmny";

  // 累计回冲数量
  public static final String NQUANTITY_DE = "nquantity_de";

  // 累计回冲金额
  public static final String NTAX_DE = "ntax_de";

  // 累计确认应收金额
  public static final String NTOTALARMNY = "ntotalarmny";

  // 累计确认应数量
  public static final String NTOTALARNUM = "ntotalarnum";

  // 成本金额
  public static final String NTOTALCOSTMNY = "ntotalcostmny";

  // 成本结算主数量
  public static final String NTOTALCOSTNUM = "ntotalcostnum";

  // 累计暂估应收金额
  public static final String NTOTALESTARMNY = "ntotalestarmny";

  // 累计暂估应收数量
  public static final String NTOTALESTARNUM = "ntotalestarnum";

  // 应收无税金额
  public static final String NTOTALRECEIVMNY = "ntotalreceivmny";

  // 应收主数量
  public static final String NTOTALRECEIVNUM = "ntotalreceivnum";

  // 成本结算成本金额
  public static final String NTOTALSETTLECOSTMNY = "ntotalsettlecostmny";

  //
  public static final String PK_AREACL = "pk_areacl";

  //
  public static final String PK_CUSTCLASS = "pk_custclass";

  //
  public static final String PK_CUSTSALECLASS = "pk_custsaleclass";

  //
  public static final String PK_MARBASCLASS = "pk_marbasclass";

  //
  public static final String PK_MARSALECLASS = "pk_marsaleclass";
  
  private static final long serialVersionUID = -6465999612927515679L;

  @Override
  public IDataViewMeta getMetaData() {
    IDataViewMeta viewMeta =
        DataViewMetaFactory.getInstance().getDataViewMeta(
            OrderPorfitViewMeta.class);
    // DataViewMeta viewMeta = new OrderPorfitViewMeta();
    return viewMeta;
  }

  public UFDouble getNcostprice() {
    return (UFDouble) this.getAttributeValue(OrderProfitViewVO.NCOSTPRICE);
  }

  public UFDouble getNmainnum() {
    return (UFDouble) this.getAttributeValue(OrderProfitViewVO.NMAINNUM);
  }

  public UFDouble getNnetprice() {
    return (UFDouble) this.getAttributeValue(OrderProfitViewVO.NNETPRICE);
  }

  public UFDouble getNntaxmny() {
    return (UFDouble) this.getAttributeValue(OrderProfitViewVO.NNTAXMNY);
  }

  public UFDouble getNntaxprice() {
    return (UFDouble) this.getAttributeValue(OrderProfitViewVO.NNTAXPRICE);
  }

  public UFDouble getNordernnum() {
    return (UFDouble) this.getAttributeValue(OrderProfitViewVO.NORDERNNUM);
  }

  public UFDouble getNprofitcate() {
    return (UFDouble) this.getAttributeValue(OrderProfitViewVO.NPROFITCATE);
  }

  public UFDouble getNprofitmny() {
    return (UFDouble) this.getAttributeValue(OrderProfitViewVO.NPROFITMNY);
  }

  // 查询时选择出的字段
  // public static final String[] SELECTKEYS = new String[] {
  // OrderProfitViewVO.PK_ORG, OrderProfitViewVO.CDEPTVID,
  // };

  // public void setNnum(UFDouble nnum) {
  // this.setAttributeValue(DetailLedgerViewVO.NNUM, nnum);
  // }
  //
  // public UFDouble getNnum() {
  // return (UFDouble) this.getAttributeValue(DetailLedgerViewVO.NNUM);
  // }

  public UFDouble getNquantityDe() {
    return (UFDouble) this.getAttributeValue(OrderProfitViewVO.NQUANTITY_DE);
  }

  public UFDouble getNtaxDe() {
    return (UFDouble) this.getAttributeValue(OrderProfitViewVO.NTAX_DE);
  }

  public UFDouble getNtotalarmny() {
    return (UFDouble) this.getAttributeValue(OrderProfitViewVO.NTOTALARMNY);
  }

  public UFDouble getNtotalarnum() {
    return (UFDouble) this.getAttributeValue(OrderProfitViewVO.NTOTALARNUM);
  }

  public UFDouble getNtotalcostmny() {
    return (UFDouble) this.getAttributeValue(OrderProfitViewVO.NTOTALCOSTMNY);
  }

  public UFDouble getNtotalcostnum() {
    return (UFDouble) this.getAttributeValue(OrderProfitViewVO.NTOTALCOSTNUM);
  }

  public UFDouble getNtotalestarmny() {
    return (UFDouble) this.getAttributeValue(OrderProfitViewVO.NTOTALESTARMNY);
  }

  public UFDouble getNtotalestarnum() {
    return (UFDouble) this.getAttributeValue(OrderProfitViewVO.NTOTALESTARNUM);
  }

  public UFDouble getNtotalreceivmny() {
    return (UFDouble) this.getAttributeValue(OrderProfitViewVO.NTOTALRECEIVMNY);
  }

  public UFDouble getNtotalreceivnum() {
    return (UFDouble) this.getAttributeValue(OrderProfitViewVO.NTOTALRECEIVNUM);
  }

  public UFDouble getNtotalsettlecostmny() {
    return (UFDouble) this
        .getAttributeValue(OrderProfitViewVO.NTOTALSETTLECOSTMNY);
  }

  public String getpk_areacl() {
    return (String) this.getAttributeValue(OrderProfitViewVO.PK_AREACL);
  }

  public String getPk_custclass() {
    return (String) this.getAttributeValue(OrderProfitViewVO.PK_CUSTCLASS);
  }

  public String getpk_custsaleclass() {
    return (String) this.getAttributeValue(OrderProfitViewVO.PK_CUSTSALECLASS);
  }

  public String getpk_marbasclass() {
    return (String) this.getAttributeValue(OrderProfitViewVO.PK_MARBASCLASS);
  }

  public String getpk_marsaleclass() {
    return (String) this.getAttributeValue(OrderProfitViewVO.PK_MARSALECLASS);
  }

  public void setNcostprice(UFDouble ncostprice) {
    this.setAttributeValue(OrderProfitViewVO.NCOSTPRICE, ncostprice);
  }

  public void setNmainnum(UFDouble nmainnum) {
    this.setAttributeValue(OrderProfitViewVO.NMAINNUM, nmainnum);
  }

  public void setNnetprice(UFDouble nnetprice) {
    this.setAttributeValue(OrderProfitViewVO.NNETPRICE, nnetprice);
  }

  public void setNntaxmny(UFDouble nntaxmny) {
    this.setAttributeValue(OrderProfitViewVO.NNTAXMNY, nntaxmny);
  }

  public void setNntaxprice(UFDouble nntaxprice) {
    this.setAttributeValue(OrderProfitViewVO.NNTAXPRICE, nntaxprice);
  }

  public void setNordernnum(UFDouble nordernnum) {
    this.setAttributeValue(OrderProfitViewVO.NORDERNNUM, nordernnum);
  }

  public void setNprofitcate(UFDouble nprofitcate) {
    this.setAttributeValue(OrderProfitViewVO.NPROFITCATE, nprofitcate);
  }

  public void setNprofitmny(UFDouble nprofitmny) {
    this.setAttributeValue(OrderProfitViewVO.NPROFITMNY, nprofitmny);
  }

  public void setNquantityDe(UFDouble nquantityde) {
    this.setAttributeValue(OrderProfitViewVO.NQUANTITY_DE, nquantityde);
  }

  public void setNtaxDe(UFDouble ntaxde) {
    this.setAttributeValue(OrderProfitViewVO.NTAX_DE, ntaxde);
  }

  public void setNtotalarmny(UFDouble ntotalarmny) {
    this.setAttributeValue(OrderProfitViewVO.NTOTALARMNY, ntotalarmny);
  }

  public void setNtotalarnum(UFDouble ntotalarnum) {
    this.setAttributeValue(OrderProfitViewVO.NTOTALARNUM, ntotalarnum);
  }

  public void setNtotalcostmny(UFDouble ntotalcostmny) {
    this.setAttributeValue(OrderProfitViewVO.NTOTALCOSTMNY, ntotalcostmny);
  }

  public void setNtotalcostnum(UFDouble ntotalcostnum) {
    this.setAttributeValue(OrderProfitViewVO.NTOTALCOSTNUM, ntotalcostnum);
  }

  public void setNtotalestarmny(UFDouble ntotalestarmny) {
    this.setAttributeValue(OrderProfitViewVO.NTOTALESTARMNY, ntotalestarmny);
  }

  public void setNtotalestarnum(UFDouble ntotalestarnum) {
    this.setAttributeValue(OrderProfitViewVO.NTOTALESTARNUM, ntotalestarnum);
  }

  public void setNtotalreceivmny(UFDouble ntotalreceivmny) {
    this.setAttributeValue(OrderProfitViewVO.NTOTALRECEIVMNY, ntotalreceivmny);
  }

  public void setNtotalreceivnum(UFDouble ntotalreceivnum) {
    this.setAttributeValue(OrderProfitViewVO.NTOTALRECEIVNUM, ntotalreceivnum);
  }

  public void setNtotalsettlecostmny(UFDouble ntotalsettlecostmny) {
    this.setAttributeValue(OrderProfitViewVO.NTOTALSETTLECOSTMNY,
        ntotalsettlecostmny);
  }

  public void setpk_areacl(String pk_areacl) {
    this.setAttributeValue(OrderProfitViewVO.PK_AREACL, pk_areacl);
  }

  public void setPk_custclass(String pk_custclass) {
    this.setAttributeValue(OrderProfitViewVO.PK_CUSTCLASS, pk_custclass);
  }

  public void setpk_custsaleclass(String pk_custsaleclass) {
    this.setAttributeValue(OrderProfitViewVO.PK_CUSTSALECLASS, pk_custsaleclass);
  }

  public void setpk_marbasclass(String pk_marbasclass) {
    this.setAttributeValue(OrderProfitViewVO.PK_MARBASCLASS, pk_marbasclass);
  }

  public void setpk_marsaleclass(String pk_marsaleclass) {
    this.setAttributeValue(OrderProfitViewVO.PK_MARSALECLASS, pk_marsaleclass);
  }

}
