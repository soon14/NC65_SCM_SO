package nc.bs.so.m33.biz.m4c.rule.manual;

import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;

import nc.impl.pubapp.pattern.data.view.ViewUpdate;
import nc.impl.pubapp.pattern.rule.IRule;
import nc.impl.so.pub.view.ViewRefresh;
import nc.itf.so.m33.ref.ic.m4c.ICM4CServiceUtil;
import nc.vo.pub.lang.UFDouble;
import nc.vo.pubapp.pattern.pub.MathTool;
import nc.vo.so.m33.m4c.entity.SquareOutBVO;
import nc.vo.so.m33.m4c.entity.SquareOutVOUtils;
import nc.vo.so.m33.m4c.entity.SquareOutViewVO;
import nc.vo.so.m33.pub.util.SquareCalculatorForVO;

/**
 * @description
 * 如果前台界面手工修改过含税净价，需要同步更新待结算单、销售出库单
 * @scene
 * 销售出库单手工结算，如果前台界面手工修改过含税净价
 * @param
 * 无
 * @since 6.0
 * @version 2011-8-23 下午01:19:49
 * @author zhangcheng
 */
public class ProcessEditTaxNetPriceRule implements IRule<SquareOutViewVO> {

  @Override
  public void process(SquareOutViewVO[] vos) {
    // 回写销售出库待结算单
    this.processSquareOut(vos);
  }

  private void processSaleOut(SquareOutViewVO[] vos) {
    Map<String, UFDouble> paraMap = new HashMap<String, UFDouble>();
    for (SquareOutViewVO view : vos) {
      paraMap.put(view.getItem().getCsquarebillbid(), view.getItem()
          .getNorigtaxnetprice());
    }
    ICM4CServiceUtil.renovatePrice(paraMap);
  }

  private void processSquareOut(SquareOutViewVO[] vos) {
    // 原销售出库代结算单
    Map<String, SquareOutViewVO> m_dbview =
        new ViewRefresh<SquareOutViewVO>().getViewsMapFromDB(vos);
    // 判断含税净价是否修改过
    Set<SquareOutViewVO> s_updateview = new HashSet<SquareOutViewVO>();
    for (SquareOutViewVO view : vos) {
      String bid = view.getItem().getCsalesquarebid();
      UFDouble newprice = view.getItem().getNorigtaxnetprice();
      SquareOutViewVO dbview = m_dbview.get(bid);
      UFDouble oldprice = dbview.getItem().getNorigtaxnetprice();
      if (!MathTool.equals(newprice, oldprice)) {
        dbview.getItem().setNorigtaxnetprice(newprice);
        dbview.getItem().setNthisnum(dbview.getItem().getNnum());
        s_updateview.add(dbview);
      }
    }

    int size = s_updateview.size();
    if (size > 0) {
      SquareOutViewVO[] updateviews =
          s_updateview.toArray(new SquareOutViewVO[size]);
      SquareOutBVO[] bvos =
          SquareOutVOUtils.getInstance().getSquareOutBVO(updateviews);
      new SquareCalculatorForVO()
          .calculate(bvos, SquareOutBVO.NORIGTAXNETPRICE);
      String[] updatefields =
          new String[] {
            SquareOutBVO.NORIGTAXNETPRICE,
            SquareOutBVO.NORIGTAXPRICE,
            SquareOutBVO.NORIGNETPRICE,
            SquareOutBVO.NORIGPRICE,
            SquareOutBVO.NORIGDISCOUNT,
            SquareOutBVO.NORIGMNY,
            SquareOutBVO.NORIGTAXMNY,
            // TODO 2012.02.16 fbinly v61删除原币税额字段
            // SquareOutBVO.NORIGTAX,
            SquareOutBVO.NTAXNETPRICE, SquareOutBVO.NTAXPRICE,
            SquareOutBVO.NNETPRICE, SquareOutBVO.NPRICE, SquareOutBVO.NTAXMNY,
            SquareOutBVO.NTAX, SquareOutBVO.NMNY, SquareOutBVO.NDISCOUNT,
            SquareOutBVO.NGROUPMNY, SquareOutBVO.NGROUPTAXMNY,
            SquareOutBVO.NGLOBALMNY, SquareOutBVO.NGLOBALTAXMNY,
            // 2012.02.16 fbinly v61新增字段
            SquareOutBVO.NCALTAXMNY
          };
      ViewUpdate<SquareOutViewVO> bo = new ViewUpdate<SquareOutViewVO>();
      bo.update(updateviews, SquareOutBVO.class, updatefields);

      // 回写销售出库单
      this.processSaleOut(updateviews);
    }

  }

}
