package nc.pubimpl.so.m32.so.report;

import nc.impl.pubapp.pattern.data.vo.VOQuery;
import nc.impl.pubapp.pattern.database.IDQueryBuilder;
import nc.pubitf.so.m32.so.report.IM32ForOutSum;
import nc.vo.pub.BusinessException;
import nc.vo.pubapp.pattern.pub.MapList;
import nc.vo.pubapp.pattern.pub.SqlBuilder;
import nc.vo.scmpub.res.billtype.ICBillType;
import nc.vo.so.m32.entity.SaleInvoiceBVO;

/**
 * 
 * @since
 * @version 2012-09-03 10:45:00
 * @author Áº¼ªÃ÷
 */
public class M32ForOutSumImpl implements IM32ForOutSum {

  @Override
  public MapList<String, SaleInvoiceBVO> queryInvoiceFromOut(String[] outhids,
      String[] outbids) throws BusinessException {
    MapList<String, SaleInvoiceBVO> mapinvoicebvo =
        new MapList<String, SaleInvoiceBVO>();
    SqlBuilder wheresql = new SqlBuilder();
    wheresql.append(" and ");
    wheresql.append(SaleInvoiceBVO.VSRCTYPE, ICBillType.SaleOut.getCode());
    wheresql.append(" and ");
    IDQueryBuilder idbuilder = new IDQueryBuilder();
    wheresql.append(idbuilder.buildSQL(SaleInvoiceBVO.CSRCID, outhids));
    wheresql.append(" and ");
    wheresql.append(idbuilder.buildAnotherSQL(SaleInvoiceBVO.CSRCBID, outbids));

    String[] selkey =
        new String[] {
          SaleInvoiceBVO.CSRCBID, SaleInvoiceBVO.CSALEINVOICEID,
          SaleInvoiceBVO.CSALEINVOICEBID, SaleInvoiceBVO.NORIGTAXMNY
        };
    VOQuery<SaleInvoiceBVO> voqry =
        new VOQuery<SaleInvoiceBVO>(SaleInvoiceBVO.class, selkey);
    SaleInvoiceBVO[] bvos = voqry.query(wheresql.toString(), null);
    if (null == bvos || bvos.length == 0) {
      return mapinvoicebvo;
    }
    for (SaleInvoiceBVO bvo : bvos) {
      mapinvoicebvo.put(bvo.getCsrcbid(), bvo);
    }
    return mapinvoicebvo;
  }
}
