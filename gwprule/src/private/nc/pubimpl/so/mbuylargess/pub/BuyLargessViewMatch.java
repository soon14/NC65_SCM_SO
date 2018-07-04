package nc.pubimpl.so.mbuylargess.pub;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Set;

import nc.vo.pubapp.AppContext;
import nc.vo.pubapp.pattern.data.IRowSet;
import nc.vo.pubapp.pattern.pub.MapList;
import nc.vo.pubapp.pattern.pub.SqlBuilder;
import nc.vo.so.mbuylargess.match.BuyLargessMatchPara;
import nc.vo.so.mbuylargess.match.BuyLargessMatchResult;
import nc.vo.so.mbuylargess.view.BuyLargessShowViewVO;
import nc.vo.so.pub.util.BaseSaleClassUtil;

import nc.impl.pubapp.pattern.data.view.EfficientViewQuery;
import nc.impl.pubapp.pattern.database.DataAccessUtils;

/**
 * 匹配买赠设置，返回买赠政策展示视图的处理类
 * 
 * @since 6.1
 * @version 2012-10-30 19:13:22
 * @author 冯加彬
 */
public class BuyLargessViewMatch extends
    AbstractBuyLargessMatch<BuyLargessShowViewVO> {

  @Override
  protected void processMatchViews(BuyLargessMatchResult[] result,
      BuyLargessMatchPara[] matchparas, BuyLargessShowViewVO[] showviews) {

    if (null == showviews || showviews.length == 0) {
      return;
    }
    MapList<Integer, BuyLargessShowViewVO> mapView =
        new MapList<Integer, BuyLargessShowViewVO>();
    for (BuyLargessShowViewVO view : showviews) {
      Integer index = view.getParaindex();
      mapView.put(index, view);
    }

    int i = 0;
    for (BuyLargessMatchPara para : matchparas) {
      Integer index = para.getParaindex();
      if (mapView.containsKey(index)) {
        List<BuyLargessShowViewVO> matchvos = mapView.get(index);
        BuyLargessShowViewVO[] views =
            new BuyLargessShowViewVO[matchvos.size()];
        matchvos.toArray(views);
        result[i] = new BuyLargessMatchResult(views);
        i++;
      }
    }
  }

  @Override
  protected BuyLargessShowViewVO[] queryMatchBuyLargess(String temptablename) {
    // 返回查询SQL语句
    String querysql = this.getQuerySql(temptablename);
    DataAccessUtils bo = new DataAccessUtils();
    IRowSet rowset = bo.query(querysql);
    Map<String, Integer> pk_index = new HashMap<String, Integer>();
    while (rowset.next()) {
      int index = 0;
      Integer paraindex = rowset.getInteger(index++);
      String bid = rowset.getString(index);
      pk_index.put(bid, paraindex);
    }
    if (pk_index.size() == 0) {
      return null;
    }
    Set<String> setbid = pk_index.keySet();
    String[] bids = new String[setbid.size()];
    setbid.toArray(bids);

    EfficientViewQuery<BuyLargessShowViewVO> viewquery =
        new EfficientViewQuery<BuyLargessShowViewVO>(BuyLargessShowViewVO.class);
    BuyLargessShowViewVO[] showviews = viewquery.query(bids);

    for (BuyLargessShowViewVO view : showviews) {
      String pk_buylargess_b = view.getBody().getPk_buylargess_b();
      view.setParaindex(pk_index.get(pk_buylargess_b));
    }
    return showviews;
  }

  private String getQuerySql(String temptablename) {
    SqlBuilder sql = new SqlBuilder();
    sql.append("select " + temptablename + "." + BuyLargessMatchPara.PARAINDEX);
    sql.append(",so_buylargess_b.pk_buylargess_b ");
    sql.append(" from so_buylargess,so_buylargess_b, bd_material_v,"
        + temptablename);
    sql.append(" where");
    sql.append(" so_buylargess.pk_buylargess = so_buylargess_b.pk_buylargess ");
    sql.append(" and so_buylargess_b.pk_material = bd_material_v.pk_source ");
    sql.append(" and bd_material_v.enablestate = 2 ");
    // 买赠主表销售组织等于临时表销售组织或者等于临时表上级销售组织并且适用于下级
    sql.append(" and ((so_buylargess.pk_org = " + temptablename + "."
        + BuyLargessMatchPara.CSALEORGID);
    sql.append(" ) or ( so_buylargess.pk_org = " + temptablename + "."
        + BuyLargessMatchPara.CFATHERORGID);
    sql.append(" and so_buylargess.islow = 'Y' )) ");
    // 买赠主表物料等于临时表物料
    sql.append(" and  so_buylargess.cbuymarid = " + temptablename + "."
        + BuyLargessMatchPara.CMATERIALID);

    String pk_group = AppContext.getInstance().getPkGroup();
    // 买赠主表物料分类等于临时表物料分类
    if (BaseSaleClassUtil.isMarBaseClass(pk_group)) {
      sql.append(" and  so_buylargess.pk_marbasclass =" + temptablename + "."
          + BuyLargessMatchPara.CMARBASECLID);
    }
    else {
      sql.append(" and so_buylargess.pk_marsaleclass =" + temptablename + "."
          + BuyLargessMatchPara.CMARSALECLID);
    }
    // 买赠主表客户等临时表客户
    sql.append(" and so_buylargess.pk_customer =" + temptablename + "."
        + BuyLargessMatchPara.CCUSTOMERID);
    // 买赠主表客户分类等于临时表客户分类
    if (BaseSaleClassUtil.isCustBaseClass(pk_group)) {
      sql.append(" and so_buylargess.pk_custclass =" + temptablename + "."
          + BuyLargessMatchPara.CCUSTCLID);
    }
    else {
      sql.append(" and so_buylargess.pk_custsaleclass =" + temptablename + "."
          + BuyLargessMatchPara.CCUSTSALECLID);
    }
    // 买赠主表单位等于临时表单位
    sql.append(" and so_buylargess.cbuyunitid =" + temptablename + "."
        + BuyLargessMatchPara.CASSUNITID);
    // 买赠主表币种等于临时表币种
    sql.append(" and so_buylargess.pk_currinfo =" + temptablename + "."
        + BuyLargessMatchPara.CCURRTYPEID);

    sql.append(" and so_buylargess.pk_group", pk_group);
    // sql.append(" and so_buylargess_b.pk_group", pk_group);
    sql.append(" and so_buylargess_b.dbegdate <=" + temptablename + "."
        + BuyLargessMatchPara.DBILLDATE);
    sql.append(" and so_buylargess_b.denddate >=" + temptablename + "."
        + BuyLargessMatchPara.DBILLDATE);
    sql.append(" and so_buylargess.dr = 0 and so_buylargess_b.dr = 0 ");
    return sql.toString();
  }
}
