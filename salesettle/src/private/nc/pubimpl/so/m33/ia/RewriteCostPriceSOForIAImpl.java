package nc.pubimpl.so.m33.ia;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;

import nc.bs.framework.common.NCLocator;
import nc.impl.pubapp.pattern.data.vo.VOQuery;
import nc.impl.pubapp.pattern.data.vo.VOUpdate;
import nc.impl.pubapp.pattern.database.TempTableDefine;
import nc.impl.pubapp.pattern.pub.LockOperator;
import nc.pubitf.ic.m4c.m33.IParameter4CCostPriceFor33;
import nc.pubitf.ic.m4c.m33.IRewrite4CCostPriceFor33;
import nc.pubitf.so.m33.ia.IRewriteCostPriceSOForIA;
import nc.vo.pub.BusinessException;
import nc.vo.pub.lang.UFDouble;
import nc.vo.pubapp.AppContext;
import nc.vo.pubapp.pattern.pub.MapList;
import nc.vo.pubapp.pattern.pub.MathTool;
import nc.vo.pubapp.pattern.pub.SqlBuilder;
import nc.vo.pubapp.scale.ScaleUtils;
import nc.vo.so.m33.m4c.entity.SquareOutDetailVO;
import nc.vo.so.m33.para.ReWrite4CParaForIA;

/**
 * 提供给存货核算回写销售出库单成本金额和成本单价的接口
 * 
 * @since 6.3
 * @version 2013-05-09 08:47:08
 * @author yixl
 */
public class RewriteCostPriceSOForIAImpl implements IRewriteCostPriceSOForIA {
  private static final int MaxInSize = 100;

  @Override
  public void set4CCostPrice(ReWrite4CParaForIA[] paras)
      throws BusinessException {
    Map<String, ReWrite4CParaForIA> bidAndParas = this.getBidsAndParas(paras);
    // 更新销售出库单结算明细单的成本金额和成本单价
    SquareOutDetailVO[] detailVOs = this.updateSquareOutDetail(bidAndParas);

    Set<String> csquarebillbids = new HashSet<String>();
    for (SquareOutDetailVO detailVO : detailVOs) {
      csquarebillbids.add(detailVO.getCsquarebillbid());
    }
    String[] billbids =
        csquarebillbids.toArray(new String[csquarebillbids.size()]);
    String pk_group = AppContext.getInstance().getPkGroup();

    SquareOutDetailVO[] allSquareDVOs =
        this.queryDetailVOByBillbids(billbids, pk_group);
    MapList<String, SquareOutDetailVO> squareDvoBy4Cbid =
        new MapList<String, SquareOutDetailVO>();
    for (SquareOutDetailVO allSquareDVO : allSquareDVOs) {
      squareDvoBy4Cbid.put(allSquareDVO.getCsquarebillbid(), allSquareDVO);
    }

    List<SquareOutDetailVO> sameBillbidDvos = null;
    ScaleUtils scaleutil = new ScaleUtils(pk_group);
    List<IParameter4CCostPriceFor33> arraylist =
        new ArrayList<IParameter4CCostPriceFor33>();
    for (String billbid : billbids) {
      sameBillbidDvos = squareDvoBy4Cbid.get(billbid);
      if (null != sameBillbidDvos && sameBillbidDvos.size() > 1) {
        UFDouble ncostMny = null;
        UFDouble nnum = null;
        for (SquareOutDetailVO outDetailVO : sameBillbidDvos) {
          if (null != outDetailVO.getNcostmny()) {
            ncostMny = MathTool.add(ncostMny, outDetailVO.getNcostmny());
            nnum = MathTool.add(nnum, outDetailVO.getNsquarenum());
          }
        }
        UFDouble ncostprice = null;
        if (null != ncostMny) {
          ncostprice = ncostMny.div(nnum);
        }
        final SquareOutDetailVO retDVO = sameBillbidDvos.get(0);
        final UFDouble retncostMny = ncostMny;
        if (null != ncostprice) {
          ncostprice = scaleutil.adjustPriceScale(ncostprice);
        }

        final UFDouble retncostprice = ncostprice;

        IParameter4CCostPriceFor33 para = new IParameter4CCostPriceFor33() {

          @Override
          public String getBid() {
            return retDVO.getCsquarebillbid();
          }

          @Override
          public String getHid() {
            return retDVO.getCsquarebillid();
          }

          @Override
          public UFDouble getNcostmny() {
            return retncostMny;
          }

          @Override
          public UFDouble getNcostprice() {
            return retncostprice;
          }
        };
        arraylist.add(para);
      }
      else if (null != sameBillbidDvos && sameBillbidDvos.size() == 1) {
        final SquareOutDetailVO retDVO = sameBillbidDvos.get(0);
        IParameter4CCostPriceFor33 para = new IParameter4CCostPriceFor33() {

          @Override
          public String getBid() {
            return retDVO.getCsquarebillbid();
          }

          @Override
          public String getHid() {
            return retDVO.getCsquarebillid();
          }

          @Override
          public UFDouble getNcostmny() {
            return retDVO.getNcostmny();
          }

          @Override
          public UFDouble getNcostprice() {
            return retDVO.getNcostprice();
          }
        };
        arraylist.add(para);
      }

    }

    IParameter4CCostPriceFor33[] icparas =
        arraylist.toArray(new IParameter4CCostPriceFor33[arraylist.size()]);
    IRewrite4CCostPriceFor33 svr =
        NCLocator.getInstance().lookup(IRewrite4CCostPriceFor33.class);
    svr.rewrite4CCostFor33(icparas);
  }

  private SquareOutDetailVO[] queryDetailVOByBillbids(String[] billbids,
      String pk_group) {
    SqlBuilder wherepart = new SqlBuilder();
    wherepart.append(" where ");
    if (billbids.length >= RewriteCostPriceSOForIAImpl.MaxInSize) {
      TempTableDefine ttd = new TempTableDefine();
      String table = ttd.get(billbids);
      wherepart.append(SquareOutDetailVO.CSQUAREBILLBID);
      wherepart.append(" in (select id1 from  ");
      wherepart.append(table);
      wherepart.append(" ) ");
    }
    else {
      wherepart.append(SquareOutDetailVO.CSQUAREBILLBID, billbids);
    }
    wherepart.append(" and ");
    wherepart.append(SquareOutDetailVO.PK_GROUP, pk_group);
    /*wherepart.append(" and ");
    wherepart.append(SquareOutDetailVO.NCOSTMNY + " is not null");*/

    String order = " order by " + SquareOutDetailVO.CSQUAREBILLBID + " desc";

    VOQuery<SquareOutDetailVO> query =
        new VOQuery<SquareOutDetailVO>(SquareOutDetailVO.class);

    SquareOutDetailVO[] allSquareDVOs =
        query.queryWithWhereKeyWord(wherepart.toString(), order);

    return allSquareDVOs;
  }

  /**
   * 更新销售出库结算单明细的成本金额和成本单价
   * 
   * @param bidAndParas
   * @return
   */
  private SquareOutDetailVO[] updateSquareOutDetail(
      Map<String, ReWrite4CParaForIA> bidAndParas) {
    Set<String> keys = bidAndParas.keySet();
    String[] bids = keys.toArray(new String[keys.size()]);
    LockOperator locker = new LockOperator();
    String message =
        nc.vo.ml.NCLangRes4VoTransl.getNCLangRes().getStrByID("4006010_0",
            "04006010-0150")/*@res "成本结算回写销售出库结算单明细，加锁失败！"*/;
    locker.lock(bids, message);
    VOQuery<SquareOutDetailVO> query =
        new VOQuery<SquareOutDetailVO>(SquareOutDetailVO.class);

    SquareOutDetailVO[] detailVOs = query.query(bids);
    ReWrite4CParaForIA paraforia = null;
    for (int i = 0; i < detailVOs.length; i++) {
      SquareOutDetailVO detailVO = detailVOs[i];
      paraforia = bidAndParas.get(detailVO.getCsalesquaredid());

      UFDouble costmny = paraforia.getNcostmny();
      UFDouble costprice = paraforia.getNcostprice();

      detailVO.setNcostmny(costmny);
      detailVO.setNcostprice(costprice);
    }

    // 更新销售出库单待结算单明细成本金额和成本单价
    VOUpdate<SquareOutDetailVO> update = new VOUpdate<SquareOutDetailVO>();
    String[] names = new String[] {
      SquareOutDetailVO.NCOSTMNY, SquareOutDetailVO.NCOSTPRICE
    };

    SquareOutDetailVO[] retVOs = update.update(detailVOs, names);

    return retVOs;
  }

  private Map<String, ReWrite4CParaForIA> getBidsAndParas(
      ReWrite4CParaForIA[] paras) {
    Map<String, ReWrite4CParaForIA> bidAndPara =
        new HashMap<String, ReWrite4CParaForIA>();
    for (ReWrite4CParaForIA para : paras) {
      bidAndPara.put(para.getCsrcbid(), para);
    }
    return bidAndPara;
  }
}
