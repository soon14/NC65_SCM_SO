package nc.pubimpl.so.m33.ia;

import nc.impl.pubapp.pattern.data.vo.VOQuery;
import nc.impl.pubapp.pattern.page.IPage;
import nc.impl.pubapp.pattern.page.SecondaryPage;
import nc.impl.pubapp.pattern.page.db.IDDBPage;
import nc.pubitf.so.m33.ia.ISaleSettleQueryForIA;
import nc.vo.ic.general.define.MetaNameConst;
import nc.vo.ic.m4c.entity.SaleOutHeadVO;
import nc.vo.ic.pub.define.ICPubMetaNameConst;
import nc.vo.pub.BusinessException;
import nc.vo.pubapp.pattern.pub.SqlBuilder;
import nc.vo.scmpub.parameter.ia.QueryParaVO;
import nc.vo.so.m32.entity.SaleInvoiceHVO;
import nc.vo.so.pub.enumeration.BillStatus;

public class SaleSettleQueryForIAImpl implements ISaleSettleQueryForIA {
  /**
   * √ø“≥¥Û–°
   */
  private static final int fetchSize = 5000;

  @Override
  public SaleInvoiceHVO[] queryFreeSaleInvoiceHVO(QueryParaVO paraVO)
      throws BusinessException {

    if (paraVO == null) {
      return null;
    }

    String[] queryFileds =
        new String[] {
          SaleInvoiceHVO.VBILLCODE, SaleInvoiceHVO.DBILLDATE,
          SaleInvoiceHVO.CTRANTYPEID, SaleInvoiceHVO.CINVOICECUSTID,
          SaleInvoiceHVO.BILLMAKER
        };

    VOQuery<SaleInvoiceHVO> query =
        new VOQuery<SaleInvoiceHVO>(SaleInvoiceHVO.class, queryFileds);
    String condition = this.getInvoiceCondition(paraVO);
    SqlBuilder sql = new SqlBuilder();
    sql.append(" select csaleinvoiceid from so_saleinvoice ");
    sql.append(condition);
    IDDBPage ds = new IDDBPage(sql.toString());
    IPage<String> page = new SecondaryPage<String>(ds, fetchSize);
    if (page.hasNext()) {
      String[] cbillids = page.next();
      SaleInvoiceHVO[] vos = query.query(cbillids);
      return vos;
    }
    return new SaleInvoiceHVO[0];
  }

  private String getInvoiceCondition(QueryParaVO paraVO) {
    SqlBuilder sqlBuilder = new SqlBuilder();
    sqlBuilder.append(" where so_saleinvoice.dbilldate >= '");
    sqlBuilder.append(paraVO.getStartData());
    sqlBuilder.append("' and ");
    sqlBuilder.append("so_saleinvoice.dbilldate <= '");
    sqlBuilder.append(paraVO.getEndData());
    sqlBuilder.append("' and ");
    sqlBuilder.append("so_saleinvoice.pk_org", paraVO.getPk_financeorgs());
    sqlBuilder.append(" and ");
    sqlBuilder.appendNot("so_saleinvoice.fstatusflag", new int[] {
      BillStatus.AUDIT.getIntValue()
    });
    sqlBuilder.append(" and ");
    sqlBuilder.append("so_saleinvoice.dr", "0");
    sqlBuilder.append(" order by so_saleinvoice.dbilldate desc");
    return sqlBuilder.toString();
  }

  @Override
  public SaleOutHeadVO[] queryUnSquareSaleOutHeadVO(QueryParaVO paraVO)
      throws BusinessException {

    if (paraVO == null) {
      return null;
    }

    String sql = this.getSaleOutCondition(paraVO).toString();
    IDDBPage ds = new IDDBPage(sql);
    IPage<String> page = new SecondaryPage<String>(ds, fetchSize);
    if (page.hasNext()) {
      String[] cbillids = page.next();
      SaleOutHeadVO[] vos = this.querySaleOutHead(cbillids);
      return vos;
    }
    return new SaleOutHeadVO[0];
  }

  private SaleOutHeadVO[] querySaleOutHead(String[] cbillids) {
    String[] queryFileds =
        new String[] {
          ICPubMetaNameConst.VBILLCODE, ICPubMetaNameConst.DBILLDATE,
          ICPubMetaNameConst.CTRANTYPEID, MetaNameConst.CCUSTOMERID,
          ICPubMetaNameConst.CWHSMANAGERID, ICPubMetaNameConst.OPERATOR
        };

    VOQuery<SaleOutHeadVO> queryHead =
        new VOQuery<SaleOutHeadVO>(SaleOutHeadVO.class, queryFileds);
    String order = " and 1=1 order by dbilldate";
    SaleOutHeadVO[] heads = queryHead.query(cbillids, order);
    return heads;
  }

  private String getSaleOutCondition(QueryParaVO paraVO) {
    SqlBuilder sqlBuilder = new SqlBuilder();
    sqlBuilder
        .append("select distinct so_squareout.csquarebillid from so_squareout so_squareout ");
    sqlBuilder
        .append("inner join so_squareout_b so_squareout_b on so_squareout.csalesquareid = so_squareout_b.csalesquareid");
    sqlBuilder.append(" where ");
    sqlBuilder.append("so_squareout_b.dbizdate >= '");
    sqlBuilder.append(paraVO.getStartData());
    sqlBuilder.append("' and ");
    sqlBuilder.append("so_squareout_b.dbizdate <= '");
    sqlBuilder.append(paraVO.getEndData());
    sqlBuilder.append("' and ");
    sqlBuilder.append("so_squareout.pk_org", paraVO.getPk_financeorgs());
    sqlBuilder.append(" and ");
    sqlBuilder.append("so_squareout_b.bsquareiafinish", "N");
    sqlBuilder.append(" and ");
    sqlBuilder
        .append(" abs(isnull(so_squareout_b.nnum,0)) > abs(isnull(so_squareout_b.nrushnum,0))");
    sqlBuilder.append(" and ");
    sqlBuilder
        .append(" abs(isnull(so_squareout_b.nnum,0)) > abs(isnull(so_squareout_b.nsquareianum,0))");
    sqlBuilder.append(" and ");
    sqlBuilder
        .append(" abs(isnull(so_squareout_b.nnum,0)) > abs(isnull(so_squareout_b.nsquareregnum,0))");
    sqlBuilder.append(" and ");
    sqlBuilder.append("so_squareout_b.dr", "0");
    return sqlBuilder.toString();

  }
}
