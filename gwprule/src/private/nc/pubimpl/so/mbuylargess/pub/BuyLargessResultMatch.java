package nc.pubimpl.so.mbuylargess.pub;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;

import nc.vo.pub.lang.UFDouble;
import nc.vo.pubapp.pattern.data.IRowSet;
import nc.vo.pubapp.pattern.pub.MapList;
import nc.vo.pubapp.pattern.pub.SqlBuilder;
import nc.vo.so.mbuylargess.entity.Toplimittype;
import nc.vo.so.mbuylargess.match.BuyLargessMatchPara;
import nc.vo.so.mbuylargess.match.BuyLargessMatchResult;
import nc.vo.so.mbuylargess.view.BuyLargessMatchViewVO;
import nc.vo.so.pub.util.BaseSaleClassUtil;

import nc.itf.scmpub.reference.uap.bd.measuredoc.MeasureDocService;

import nc.impl.pubapp.env.BSContext;
import nc.impl.pubapp.pattern.database.DataAccessUtils;

/**
 * 匹配买赠设置，返回匹配结果的处理类
 * 
 * @since 6.1
 * @version 2012-10-30 19:13:22
 * @author 冯加彬
 */
public class BuyLargessResultMatch extends
    AbstractBuyLargessMatch<BuyLargessMatchViewVO> {

  @Override
  protected void processMatchViews(BuyLargessMatchResult[] result,
      BuyLargessMatchPara[] matchparas, BuyLargessMatchViewVO[] matchviews) {
    if (null == matchviews || matchviews.length == 0) {
      return;
    }
    MapList<Integer, BuyLargessMatchViewVO> mapView =
        new MapList<Integer, BuyLargessMatchViewVO>();
    String prebuyid = matchviews[0].getPk_buylargess();
    Integer preindex = matchviews[0].getParaindex();
    for (BuyLargessMatchViewVO view : matchviews) {
      Integer curindex = view.getParaindex();
      String curbuyid = view.getPk_buylargess();
      // 如果唯一标识 和 买赠主表ID相同，说明是属于同一个记录行的
      if (preindex.compareTo(curindex) == 0 && prebuyid.equals(curbuyid)) {
        mapView.put(curindex, view);
      }
      // 如果买赠主表ID不相同，说明不是最优的，不增加
      else if (preindex.compareTo(curindex) == 0 && !prebuyid.equals(curbuyid)) {
        continue;
      }
      // 如果唯一标识不等，说明切换到下一个匹配项了，增加
      else {
        mapView.put(curindex, view);
        prebuyid = curbuyid;
        preindex = curindex;
      }
    }

    for (BuyLargessMatchPara para : matchparas) {
      Integer index = para.getParaindex();
      if (mapView.containsKey(index)) {
        List<BuyLargessMatchViewVO> matchvos = mapView.get(index);
        BuyLargessMatchViewVO[] views =
            this.calcMatchData(matchvos, para.getNbillnum());
        int resultindex = index.intValue();
        result[resultindex] = new BuyLargessMatchResult(views);
      }
    }
  }

  private BuyLargessMatchViewVO[] calcMatchData(
      List<BuyLargessMatchViewVO> matchvos, UFDouble billnum) {
    BuyLargessMatchViewVO[] results =
        new BuyLargessMatchViewVO[matchvos.size()];
    matchvos.toArray(results);
    Map<String, Integer> mappower = this.getMeasDocPower(results);
    for (BuyLargessMatchViewVO view : results) {
      String measdoc = view.getPk_measdoc();
      int numpower = mappower.get(measdoc).intValue();
      // 赠品数量 = 单据数量/购买数量
      UFDouble everylarnum =
          billnum.div(view.getNbuynum()).setScale(0, UFDouble.ROUND_DOWN);
      UFDouble larnum =
          everylarnum.multiply(view.getNnum()).setScale(numpower,
              UFDouble.ROUND_DOWN);

      // 如果赠品上限为数量
      if (Toplimittype.LIMIT_NUM.equalsValue(view.getFtoplimittype())) {
        if (larnum.compareTo(view.getNtoplimitvalue()) > 0) {
          view.setNnum(view.getNtoplimitvalue());
        }
        else {
          view.setNnum(larnum);
        }
        if (null != view.getNprice()) {
          view.setNmny(view.getNnum().multiply(view.getNprice()));
        }
      }
      else if (Toplimittype.LIMIT_MNY.equalsValue(view.getFtoplimittype())) {
        UFDouble mny = larnum.multiply(view.getNprice());
        if (mny.compareTo(view.getNtoplimitvalue()) > 0) {

          UFDouble num =
              view.getNtoplimitvalue().div(view.getNprice())
                  .setScale(numpower, UFDouble.ROUND_DOWN);
          view.setNnum(num);
          view.setNmny(view.getNnum().multiply(view.getNprice()));
        }
        else {
          view.setNnum(larnum);
          view.setNmny(view.getNnum().multiply(view.getNprice()));
        }
      }
      else {
        view.setNnum(larnum);
        if (null != view.getNprice()) {
          view.setNmny(view.getNnum().multiply(view.getNprice()));
        }
      }
    }
    return results;
  }

  private Map<String, Integer> getMeasDocPower(BuyLargessMatchViewVO[] views) {
    Map<String, Integer> mappower = new HashMap<String, Integer>();

    Set<String> setmeasdoc = new HashSet<String>();
    for (BuyLargessMatchViewVO view : views) {
      setmeasdoc.add(view.getPk_measdoc());
    }
    String[] measdocs = new String[setmeasdoc.size()];
    setmeasdoc.toArray(measdocs);
    Integer[] powers = MeasureDocService.getMeasPrecision(measdocs);
    int i = 0;
    for (String meas : measdocs) {
      mappower.put(meas, powers[i]);
      i++;
    }
    return mappower;
  }

  @Override
  protected BuyLargessMatchViewVO[] queryMatchBuyLargess(String temptablename) {
    String querysql = this.getQuerySql(temptablename);
    DataAccessUtils bo = new DataAccessUtils();
    IRowSet rowset = bo.query(querysql);
    List<BuyLargessMatchViewVO> listview =
        new ArrayList<BuyLargessMatchViewVO>();
    while (rowset.next()) {
      int index = 0;
      BuyLargessMatchViewVO view = new BuyLargessMatchViewVO();
      view.setParaindex(rowset.getInteger(index++));
      view.setPk_buylargess(rowset.getString(index++));
      view.setNbuynum(rowset.getUFDouble(index++));
      view.setCpromottypeid(rowset.getString(index++));
      // 设置营销活动
      view.setCmarketactid(rowset.getString(index++));
      view.setPk_material(rowset.getString(index++));
      view.setPk_measdoc(rowset.getString(index++));
      view.setNnum(rowset.getUFDouble(index++));
      view.setNprice(rowset.getUFDouble(index++));
      view.setNmny(rowset.getUFDouble(index++));
      view.setFtoplimittype(rowset.getInteger(index++));

      view.setNtoplimitvalue(rowset.getUFDouble(index));

      listview.add(view);
    }
    BuyLargessMatchViewVO[] retviews =
        new BuyLargessMatchViewVO[listview.size()];
    listview.toArray(retviews);
    return retviews;
  }

  private String getQuerySql(String temptablename) {
    SqlBuilder sql = new SqlBuilder();
    sql.append("select " + temptablename + "." + BuyLargessMatchPara.PARAINDEX);
    sql.append(",so_buylargess.pk_buylargess,so_buylargess.nbuynum,so_buylargess.cpromottypeid");
    sql.append(",so_buylargess.cmarketactid");
    sql.append(",so_buylargess_b.pk_material,so_buylargess_b.pk_measdoc,");
    sql.append("so_buylargess_b.nnum,so_buylargess_b.nprice,so_buylargess_b.nmny");
    sql.append(",so_buylargess_b.ftoplimittype,so_buylargess_b.ntoplimitvalue");
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

    String pk_group = BSContext.getInstance().getGroupID();
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
    // 买赠主表数量小于等于临时表数量
    sql.append(" and so_buylargess.nbuynum <=" + temptablename + "."
        + BuyLargessMatchPara.NBILLNUM);
    sql.append(" and so_buylargess.pk_group", pk_group);
    sql.append(" and so_buylargess_b.dbegdate <=" + temptablename + "."
        + BuyLargessMatchPara.DBILLDATE);
    sql.append(" and so_buylargess_b.denddate >=" + temptablename + "."
        + BuyLargessMatchPara.DBILLDATE);
    sql.append(" and so_buylargess.dr = 0 and so_buylargess_b.dr = 0 ");

    sql.append(" order by " + BuyLargessMatchPara.PARAINDEX
        + ", so_buylargess.cprioritycode desc ");
    return sql.toString();
  }
}
