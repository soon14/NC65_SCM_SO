package nc.pubimpl.so.m30.so.m4310;

import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;

import nc.vo.pub.BusinessException;
import nc.vo.pub.lang.UFBoolean;
import nc.vo.pubapp.pattern.pub.SqlBuilder;
import nc.vo.so.m30.entity.SaleOrderBVO;
import nc.vo.so.pub.SOTable;

import nc.pubitf.so.m30.so.m4310.IQuery30For4310;

import nc.impl.pubapp.pattern.data.vo.VOQuery;
import nc.impl.pubapp.pattern.database.IDExQueryBuilder;

/**
 * 为报价单提供查询销售订单服务
 * 
 * @since 6.1
 * @version 2013-06-04 11:19:54
 * @author yixl
 */
public class Query30For4310Impl implements IQuery30For4310 {

  @Override
  public Map<String, UFBoolean> isExistNextOrder(String[] quotationhids)
      throws BusinessException {
    SqlBuilder wheresql = new SqlBuilder();
    wheresql.append(" and ");
    IDExQueryBuilder iq = new IDExQueryBuilder(SOTable.TMP_SO_ID1.getName());
    wheresql.append(iq.buildSQL(SaleOrderBVO.CSRCID, quotationhids));

    String[] selkey = new String[] {
      SaleOrderBVO.CSRCID
    };

    VOQuery<SaleOrderBVO> querysrv =
        new VOQuery<SaleOrderBVO>(SaleOrderBVO.class, selkey);

    SaleOrderBVO[] retinvoicebvos = querysrv.query(wheresql.toString(), null);
    Set<String> setexistid = new HashSet<String>();
    for (SaleOrderBVO bvo : retinvoicebvos) {
      setexistid.add(bvo.getCsrcid());
    }
    Map<String, UFBoolean> mapisexist = new HashMap<String, UFBoolean>();
    for (String hid : quotationhids) {
      if (setexistid.contains(hid)) {
        mapisexist.put(hid, UFBoolean.TRUE);
      }
      else {
        mapisexist.put(hid, UFBoolean.FALSE);
      }
    }
    return mapisexist;
  }

}
