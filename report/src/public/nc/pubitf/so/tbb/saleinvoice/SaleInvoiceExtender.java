package nc.pubitf.so.tbb.saleinvoice;

import java.util.HashSet;
import java.util.Set;

import nc.itf.scmpub.tbb.ITbbSqlExtender;
import nc.vo.pubapp.pattern.pub.SqlBuilder;
import nc.vo.scmpub.fetchfunc.FuncSqlBuidler;
import nc.vo.scmpub.tbb.TbbSqlBuilder;
import nc.vo.so.m32.entity.SaleInvoiceBVO;

public class SaleInvoiceExtender implements ITbbSqlExtender {

  private static final String[] MNYKEYS = new String[] {
    SaleInvoiceBVO.NGLOBALTAXMNY, SaleInvoiceBVO.NGROUPTAXMNY,
    SaleInvoiceBVO.NTAXMNY, SaleInvoiceBVO.NORIGTAXMNY,
    SaleInvoiceBVO.NGLOBALMNY, SaleInvoiceBVO.NGROUPMNY, SaleInvoiceBVO.NMNY,
    SaleInvoiceBVO.NORIGMNY
  };

  private Set<String> setMnyKey;

  public SaleInvoiceExtender() {

    this.setMnyKey = new HashSet<String>();

    for (String mnykey : MNYKEYS) {
      this.setMnyKey.add(mnykey);
    }
  }

  @Override
  public boolean isExtendSumItemKey(String sumkey) {
    return this.setMnyKey.contains(sumkey);
  }

  @Override
  public String getExtendSumItemKeySql(String sumkey, String sumkeyalia,
      FuncSqlBuidler funcsqlbuilder) {
    String tablename =
        funcsqlbuilder.getTableAliasByMetaPath(SaleInvoiceBVO.MAINMETAPATH
            + SaleInvoiceBVO.BLARGESSFLAG);
    String condition =
        tablename + "." + SaleInvoiceBVO.BLARGESSFLAG + " = 'Y' ";
    SqlBuilder selsql = new SqlBuilder();
    selsql.appendCaseWhen(condition, "0", sumkeyalia);
    return selsql.toString();
  }

  @Override
  public String getExtendFromSql(FuncSqlBuidler funcsqlbuilder) {
    return null;
  }

  @Override
  public String getExtendWhereSql(FuncSqlBuidler funcsqlbuilder) {
    return null;
  }

  @Override
  public void appendOtherEntrySql(TbbSqlBuilder tbbsqlbuilder) {
  }

}
