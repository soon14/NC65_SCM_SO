package nc.pubimpl.so.m30.opc.b2b;

import java.util.List;
import java.util.Map;

import nc.vo.pub.BusinessException;
import nc.vo.pub.lang.UFDouble;
import nc.vo.pubapp.calculator.HslParseUtil;
import nc.vo.pubapp.pattern.exception.ExceptionUtils;
import nc.vo.pubapp.pattern.pub.MapList;
import nc.vo.pubapp.pattern.pub.SqlBuilder;
import nc.vo.so.m30.entity.SaleOrderViewVO;

import nc.pubitf.so.m30.opc.b2b.ISaleOrderQueryForB2B;
import nc.pubitf.so.m30.opc.b2b.SaleOrderForB2bResult;

import nc.impl.pubapp.pattern.data.view.EfficientViewQuery;
import nc.impl.pubapp.pattern.database.IDQueryBuilder;

/**
 * 销售订单为B2B预订单提供查询实现类
 * 
 * @since 6.5
 * @version 2014-04-02 15:05:34
 * @author zhangyfr
 */
public class SaleOrderQueryForB2BImpl implements ISaleOrderQueryForB2B {

  @Override
  public Map<String, List<SaleOrderForB2bResult>> query(String[] ids)
      throws BusinessException {
    MapList<String, SaleOrderForB2bResult> mapList =
        new MapList<String, SaleOrderForB2bResult>();
    try {
      // 获得viewVO
      EfficientViewQuery<SaleOrderViewVO> viewQuery =
          new EfficientViewQuery<SaleOrderViewVO>(SaleOrderViewVO.class);
      SqlBuilder sql = new SqlBuilder();
      IDQueryBuilder query = new IDQueryBuilder();
      String where = query.buildAnotherSQL("so_saleorder_b.csrcbid", ids);
      sql.append(" and " + where);
      SaleOrderViewVO[] saleOrderViewVOs = viewQuery.query(sql.toString());
      if (null != saleOrderViewVOs) {
        for (SaleOrderViewVO viewVo : saleOrderViewVOs) {
          SaleOrderForB2bResult result = new SaleOrderForB2bResult();
          result.setCcustomerid(viewVo.getHead().getCcustomerid());
          result.setBboutendflag(viewVo.getBody().getBboutendflag());
          result.setBbsendendflag(viewVo.getBody().getBbsendendflag());
          result.setCpromotpriceid(viewVo.getBody().getCpromotpriceid());
          result.setNqtunitnum(viewVo.getBody().getNqtunitnum());
          // 获得换算率
          String qtunitrate = viewVo.getBody().getVqtunitrate();
          // 精度
          int scale = viewVo.getBody().getNqtunitnum().getPower();
          // 累计出库数量-->累计出库报价数量
          UFDouble ntotalsendnum = viewVo.getBody().getNtotalsendnum();
          if (null != ntotalsendnum) {
            UFDouble nqtunittotalsendnum =
                HslParseUtil.hslDivUFDouble(qtunitrate, ntotalsendnum);
            nqtunittotalsendnum =
                nqtunittotalsendnum.setScale(scale, UFDouble.ROUND_HALF_UP);
            result.setNtotalsendnum(nqtunittotalsendnum);
          }
          else {
            result.setNtotalsendnum(ntotalsendnum);
          }
          // 累计出库数量-->累计出库报价数量
          UFDouble ntotaloutnum = viewVo.getBody().getNtotaloutnum();
          if (null != ntotaloutnum) {
            UFDouble nqtunittotaloutnum =
                HslParseUtil.hslDivUFDouble(qtunitrate, ntotaloutnum);
            nqtunittotaloutnum =
                nqtunittotaloutnum.setScale(scale, UFDouble.ROUND_HALF_UP);
            result.setNtotaloutnum(nqtunittotaloutnum);
          }
          else {
            result.setNtotaloutnum(ntotaloutnum);
          }
          result.setCsrcbid(viewVo.getBody().getCsrcbid());
          String srcbid = viewVo.getBody().getCsrcbid();
          mapList.put(srcbid, result);
        }
      }
    }
    catch (Exception e) {
      ExceptionUtils.marsh(e);
    }

    return mapList.toMap();
  }
}
