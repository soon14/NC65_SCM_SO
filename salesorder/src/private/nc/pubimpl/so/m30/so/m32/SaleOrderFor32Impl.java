package nc.pubimpl.so.m30.so.m32;

import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;

import nc.impl.pubapp.pattern.data.view.EfficientViewQuery;
import nc.pubitf.so.m30.so.m32.ISaleOrderFor32;
import nc.ui.querytemplate.querytree.FromWhereSQL;
import nc.ui.querytemplate.querytree.FromWhereSQLImpl;
import nc.ui.querytemplate.querytree.IQueryScheme;
import nc.ui.querytemplate.querytree.QueryScheme;
import nc.vo.pub.BusinessException;
import nc.vo.pubapp.pattern.pub.SqlBuilder;
import nc.vo.so.m30.entity.SaleOrderBVO;
import nc.vo.so.m30.entity.SaleOrderHVO;
import nc.vo.so.m30.entity.SaleOrderViewVO;
import nc.vo.so.m30.paravo.Info30For32Para;

public class SaleOrderFor32Impl implements ISaleOrderFor32 {

  @Override
  public IQueryScheme getOutEndSQL4Filter32(String invbodytable,
      String cfirstbid) throws BusinessException {
    if (invbodytable == null || invbodytable.trim().length() == 0
        || cfirstbid == null || cfirstbid.trim().length() == 0) {
      return null;
    }
    SqlBuilder fromSql = new SqlBuilder();
    fromSql.append(" inner join so_saleorder_b so_saleorder_b on ");
    fromSql.append(invbodytable + "." + cfirstbid
        + " = so_saleorder_b.csaleorderbid");
    SqlBuilder whereSql = new SqlBuilder();
    whereSql.append("isnull(so_saleorder_b.bboutendflag,'N') = 'N'");

    QueryScheme queryScheme = new QueryScheme();
    FromWhereSQL sql =
        new FromWhereSQLImpl(fromSql.toString(), whereSql.toString());
    queryScheme.putTableJoinFromWhereSQL(sql);
    return queryScheme;
  }

  @Override
  public Map<String, Info30For32Para> queryInfosByOrderBIDs(String[] bids)
      throws BusinessException {
    // Map<销售订单主表id, 散户id>
    Map<String, Info30For32Para> returnMap =
        new HashMap<String, Info30For32Para>();
    if (bids == null || bids.length == 0) {
      return returnMap;
    }
    Set<String> idSet = new HashSet<String>();
    for (int i = 0; i < bids.length; i++) {
      idSet.add(bids[i]);
    }

    EfficientViewQuery<SaleOrderViewVO> viewQuery =
        new EfficientViewQuery<SaleOrderViewVO>(SaleOrderViewVO.class,
            new String[] {
              SaleOrderBVO.CSALEORDERBID, SaleOrderHVO.BFREECUSTFLAG,
              SaleOrderHVO.CFREECUSTID, SaleOrderHVO.CPAYTERMID,
              SaleOrderHVO.CCUSTBANKID, SaleOrderHVO.CCUSTBANKACCID,
              SaleOrderHVO.CDEPTID, SaleOrderHVO.CDEPTVID,
              SaleOrderBVO.CCTMANAGEID, SaleOrderBVO.CCTMANAGEBID,
              SaleOrderHVO.CCHANNELTYPEID
            });

    SaleOrderViewVO[] viewvos =
        viewQuery.query(idSet.toArray(new String[idSet.size()]));

    if (viewvos != null && viewvos.length > 0) {
      for (int i = 0; i < viewvos.length; i++) {
        SaleOrderViewVO viewvo = viewvos[i];
        SaleOrderHVO hvo = viewvo.getHead();
        SaleOrderBVO bvo = viewvo.getBody();
        boolean bfreecustflag =
            hvo.getBfreecustflag() == null ? false : hvo.getBfreecustflag()
                .booleanValue();
        Info30For32Para info = new Info30For32Para();
        if (bfreecustflag) {
          info.setCfreecustid(hvo.getCfreecustid());
        }
        info.setCpaytermid(hvo.getCpaytermid());
        info.setCcustbankid(hvo.getCcustbankid());
        info.setCcustbankaccid(hvo.getCcustbankaccid());
        info.setCchanneltypeid(hvo.getCchanneltypeid());
        info.setCctmanageid(bvo.getCctmanageid());
        info.setCctmanagebid(bvo.getCctmanagebid());
        returnMap.put(bvo.getCsaleorderbid(), info);
      }
    }
    return returnMap;
  }
}
