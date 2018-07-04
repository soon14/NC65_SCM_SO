package nc.pubitf.so.tbb.saleorder;

import java.util.HashSet;
import java.util.Set;

import nc.itf.scmpub.tbb.ITbbSqlExtender;
import nc.vo.pubapp.pattern.pub.SqlBuilder;
import nc.vo.scmpub.fetchfunc.FuncSqlBuidler;
import nc.vo.scmpub.tbb.TbbSqlBuilder;
import nc.vo.so.m30.entity.SaleOrderBVO;

public class SaleOrderExtender implements ITbbSqlExtender {

  private static final String[] MNYKEYS = new String[] {
    SaleOrderBVO.NGLOBALTAXMNY, SaleOrderBVO.NGROUPTAXMNY,
    SaleOrderBVO.NTAXMNY, SaleOrderBVO.NORIGTAXMNY, SaleOrderBVO.NGLOBALMNY,
    SaleOrderBVO.NGROUPMNY, SaleOrderBVO.NMNY, SaleOrderBVO.NORIGMNY
  };

  private Set<String> setMnyKey;

  public SaleOrderExtender() {

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
        funcsqlbuilder.getTableAliasByMetaPath(SaleOrderBVO.METAPATH
            + SaleOrderBVO.BLARGESSFLAG);
    String condition = tablename + "." + SaleOrderBVO.BLARGESSFLAG + " = 'Y' ";
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
