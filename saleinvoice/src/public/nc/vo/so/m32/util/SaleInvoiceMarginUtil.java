package nc.vo.so.m32.util;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import nc.vo.ic.m4c.entity.SaleOutBodyVO;
import nc.vo.pub.lang.UFBoolean;
import nc.vo.pub.lang.UFDouble;
import nc.vo.pubapp.margin.BillMarginContext;
import nc.vo.pubapp.margin.MarginContextFactory;
import nc.vo.pubapp.margin.MarginEntry;
import nc.vo.pubapp.pattern.exception.ExceptionUtils;
import nc.vo.pubapp.pattern.pub.MathTool;
import nc.vo.scmpub.margin.SCMMuiltyWordMarginJudgement;
import nc.vo.scmpub.res.billtype.ICBillType;
import nc.vo.scmpub.res.billtype.SOBillType;
import nc.vo.so.m30.entity.SaleOrderBVO;
import nc.vo.so.m32.entity.SaleInvoiceBVO;
import nc.vo.so.m32.entity.SaleInvoiceHVO;
import nc.vo.so.m32.entity.SaleInvoiceVO;
import nc.vo.so.pub.util.ListUtil;

import nc.itf.pubapp.margin.IMarginDudgement;

/**
 * 销售发票尾差处理工具类
 * 
 * @since 6.0
 * @version 2012-3-27 下午01:35:42
 * @author 么贵敬
 */
public class SaleInvoiceMarginUtil {

  // 来源于订单
  private final String[] destMNYformOrder = new String[] {
    SaleInvoiceBVO.NORIGMNY, SaleInvoiceBVO.NORIGTAXMNY,
    SaleInvoiceBVO.NORIGDISCOUNT, SaleInvoiceBVO.NCALTAXMNY,
    SaleInvoiceBVO.NTAX
  };

  // 来源于出库单
  private final String[] destMNYformOut = new String[] {
    SaleInvoiceBVO.NORIGMNY, SaleInvoiceBVO.NORIGTAXMNY,
    SaleInvoiceBVO.NCALTAXMNY, SaleInvoiceBVO.NTAX
  };

  private final String[] ordersrcMNY = new String[] {
    SaleOrderBVO.NORIGMNY, SaleOrderBVO.NORIGTAXMNY,
    SaleOrderBVO.NORIGDISCOUNT, SaleOrderBVO.NCALTAXMNY, SaleOrderBVO.NTAX
  };

  private final String[] saleoutsrcMNY = new String[] {
    SaleOutBodyVO.NORIGMNY, SaleOutBodyVO.NORIGTAXMNY, SaleOrderBVO.NCALTAXMNY,
    SaleOutBodyVO.NTAX
  };

  // 原本币一致对照的字段
  // 来源于订单
  private final String[] destMNYformOrdercursame = new String[] {
    SaleInvoiceBVO.NORIGMNY, SaleInvoiceBVO.NORIGTAXMNY,
    SaleInvoiceBVO.NORIGDISCOUNT, SaleInvoiceBVO.NCALTAXMNY,
    SaleInvoiceBVO.NTAX, SaleInvoiceBVO.NMNY, SaleInvoiceBVO.NTAXMNY,
    SaleInvoiceBVO.NDISCOUNT, SaleInvoiceBVO.NGROUPMNY,
    SaleInvoiceBVO.NGROUPTAXMNY, SaleInvoiceBVO.NGLOBALMNY,
    SaleInvoiceBVO.NGLOBALTAXMNY
  };

  // 来源于出库单
  private final String[] destMNYformOutcursame = new String[] {
    SaleInvoiceBVO.NORIGMNY, SaleInvoiceBVO.NORIGTAXMNY,
    SaleInvoiceBVO.NCALTAXMNY, SaleInvoiceBVO.NTAX, SaleInvoiceBVO.NMNY,
    SaleInvoiceBVO.NTAXMNY, SaleInvoiceBVO.NGROUPMNY,
    SaleInvoiceBVO.NGROUPTAXMNY, SaleInvoiceBVO.NGLOBALMNY,
    SaleInvoiceBVO.NGLOBALTAXMNY
  };

  private final String[] ordersrcMNYcursame = new String[] {
    SaleOrderBVO.NORIGMNY, SaleOrderBVO.NORIGTAXMNY,
    SaleOrderBVO.NORIGDISCOUNT, SaleOrderBVO.NCALTAXMNY, SaleOrderBVO.NTAX,
    SaleOrderBVO.NMNY, SaleOrderBVO.NTAXMNY, SaleOrderBVO.NDISCOUNT,
    SaleOrderBVO.NGROUPMNY, SaleOrderBVO.NGROUPTAXMNY, SaleOrderBVO.NGLOBALMNY,
    SaleOrderBVO.NGLOBALTAXMNY
  };

  private final String[] saleoutsrcMNYcursame = new String[] {
    SaleOutBodyVO.NORIGMNY, SaleOutBodyVO.NORIGTAXMNY, SaleOrderBVO.NCALTAXMNY,
    SaleOutBodyVO.NTAX, SaleOutBodyVO.NMNY, SaleOutBodyVO.NTAXMNY,
    SaleOutBodyVO.NGROUPMNY, SaleOutBodyVO.NGROUPTAXMNY,
    SaleOutBodyVO.NGLOBALMNY, SaleOutBodyVO.NGLOBALTAXMNY
  };

  /**
   * 尾差处理
   * 
   * @param vos
   */
  public void processMargin(SaleInvoiceVO[] vos,
      Map<String, UFDouble> srcnummaps) {
    List<SaleInvoiceVO> srcordercurnosame = new ArrayList<SaleInvoiceVO>();
    List<SaleInvoiceVO> srcsaleoutcurnosame = new ArrayList<SaleInvoiceVO>();

    List<SaleInvoiceVO> srcordercursame = new ArrayList<SaleInvoiceVO>();
    List<SaleInvoiceVO> srcsaleoutcursame = new ArrayList<SaleInvoiceVO>();
    List<SaleInvoiceVO> allsrcsaleout = new ArrayList<SaleInvoiceVO>();

    // 准备数据
    this.prepareData(vos, srcnummaps, srcordercurnosame, srcsaleoutcurnosame,
        srcordercursame, srcsaleoutcursame, allsrcsaleout);

    // 处理来源于订单尾差
    this.processOrderMargin(srcordercurnosame, srcordercursame);
    // 处理来源于出库单尾差
    this.processSaleOutMargin(srcsaleoutcurnosame, srcsaleoutcursame);

    // 来源于出库单的时候由于上游没有出库单所以要计算折扣额
    if (allsrcsaleout.size() > 0) {
      SaleInvoiceVOUtil voutil = new SaleInvoiceVOUtil();
      SaleInvoiceVO[] allsrcsaleoutvos = ListUtil.toArray(allsrcsaleout);
      // 拿价税合计计算（只有来源于出库的时候需要 因为出库单没有折扣额）
      this.reCalculatorDiscountmnyByKey(allsrcsaleoutvos,
          SaleInvoiceBVO.NORIGTAXMNY);
      // 从补充出库单没有的数据
      voutil.makeupInfo(allsrcsaleoutvos);
    }

  }

  /**
   * 处理来源与出库单的尾差
   * 
   * @param srcsaleoutcurnosame 原本币不一致的发票
   * @param srcsaleoutcursame 原本币一直的发票
   */
  private void processSaleOutMargin(List<SaleInvoiceVO> srcsaleoutcurnosame,
      List<SaleInvoiceVO> srcsaleoutcursame) {
    SaleInvoiceVOCalculator voculator = new SaleInvoiceVOCalculator();
    // 设置 转单时为固定换算率
    voculator.setForcefixunitrate(UFBoolean.TRUE);
    for (SaleInvoiceVO calvo : srcsaleoutcurnosame) {
      voculator.setVoInvoice(calvo);
      voculator.calculateAll(SaleInvoiceBVO.NNUM);
    }
    for (SaleInvoiceVO calvo : srcsaleoutcursame) {
      voculator.setVoInvoice(calvo);
      voculator.calculateAll(SaleInvoiceBVO.NNUM);
    }
    IMarginDudgement margindudgement =
        new SCMMuiltyWordMarginJudgement(new String[] {
          SaleOutBodyVO.NORIGNETPRICE, SaleOutBodyVO.NORIGTAXNETPRICE
        }, new String[] {
          SaleInvoiceBVO.NORIGNETPRICE, SaleInvoiceBVO.NORIGTAXNETPRICE
        });
    try {
      // 原本币不一致，处理完尾差用折本汇率触发算法
      if (srcsaleoutcurnosame.size() > 0) {
        SaleInvoiceVO[] outvos = ListUtil.toArray(srcsaleoutcurnosame);
        BillMarginContext context =
            MarginContextFactory.getInstance().produceMarginContext(outvos,
                ICBillType.SaleOut.getCode(), SaleOrderBVO.NNUM,
                this.saleoutsrcMNY, SOBillType.Invoice.getCode(),
                SaleInvoiceBVO.NNUM, this.destMNYformOut,
                SaleInvoiceBVO.CSRCBID, SaleInvoiceBVO.CSRCID, margindudgement);
        MarginEntry.getInstance().process(context);
        this.reCalculatorByNexchangerate(ListUtil.toArray(srcsaleoutcurnosame),
            SaleInvoiceHVO.NEXCHANGERATE);
      }
      // 原本币一致
      if (srcsaleoutcursame.size() > 0) {
        SaleInvoiceVO[] outvos = ListUtil.toArray(srcsaleoutcursame);
        BillMarginContext context =
            MarginContextFactory.getInstance().produceMarginContext(outvos,
                ICBillType.SaleOut.getCode(), SaleOrderBVO.NNUM,
                this.saleoutsrcMNYcursame, SOBillType.Invoice.getCode(),
                SaleInvoiceBVO.NNUM, this.destMNYformOutcursame,
                SaleInvoiceBVO.CSRCBID, SaleInvoiceBVO.CSRCID, margindudgement);
        MarginEntry.getInstance().process(context);
      }
    }
    catch (Exception e) {
      ExceptionUtils.wrappException(e);
    }
  }

  /**
   * 处理来源与订单的微微差
   * 
   * @param srcordercursame 原本币一直的发票
   * @param srcsaleoutcrusame 原本币不一致的发票
   */
  private void processOrderMargin(List<SaleInvoiceVO> srcordercurnosame,
      List<SaleInvoiceVO> srcordercursame) {
    SaleInvoiceVOCalculator voculator = new SaleInvoiceVOCalculator();
    // 设置 转单时为固定换算率
    voculator.setForcefixunitrate(UFBoolean.TRUE);
    for (SaleInvoiceVO calvo : srcordercursame) {
      voculator.setVoInvoice(calvo);
      voculator.calculateAll(SaleInvoiceBVO.NNUM);
    }
    for (SaleInvoiceVO calvo : srcordercurnosame) {
      voculator.setVoInvoice(calvo);
      voculator.calculateAll(SaleInvoiceBVO.NNUM);
    }
    IMarginDudgement margindudgement =
        new SCMMuiltyWordMarginJudgement(new String[] {
          SaleOrderBVO.NORIGNETPRICE, SaleOrderBVO.NORIGTAXNETPRICE
        }, new String[] {
          SaleInvoiceBVO.NORIGNETPRICE, SaleInvoiceBVO.NORIGTAXNETPRICE
        });
    try {
      if (srcordercurnosame.size() > 0) {
        SaleInvoiceVO[] ordervos = ListUtil.toArray(srcordercurnosame);
        BillMarginContext context =
            MarginContextFactory.getInstance().produceMarginContext(ordervos,
                SOBillType.Order.getCode(), SaleOrderBVO.NNUM,
                this.ordersrcMNY, SOBillType.Invoice.getCode(),
                SaleInvoiceBVO.NNUM, this.destMNYformOrder,
                SaleInvoiceBVO.CSRCBID, SaleInvoiceBVO.CSRCID, margindudgement);
        MarginEntry.getInstance().process(context);
        this.reCalculatorByNexchangerate(ListUtil.toArray(srcordercurnosame),
            SaleInvoiceHVO.NEXCHANGERATE);
      }
      if (srcordercursame.size() > 0) {
        SaleInvoiceVO[] ordervos = ListUtil.toArray(srcordercursame);
        BillMarginContext context =
            MarginContextFactory.getInstance().produceMarginContext(ordervos,
                SOBillType.Order.getCode(), SaleOrderBVO.NNUM,
                this.ordersrcMNYcursame, SOBillType.Invoice.getCode(),
                SaleInvoiceBVO.NNUM, this.destMNYformOrdercursame,
                SaleInvoiceBVO.CSRCBID, SaleInvoiceBVO.CSRCID, margindudgement);
        MarginEntry.getInstance().process(context);
      }
    }
    catch (Exception e) {
      ExceptionUtils.wrappException(e);
    }
  }

  /**
   * 处理数据
   * 
   * @param vos 原始VOS
   * @param srcnummaps 来源数量
   * @param destvo1 第一次拉单的VOS
   * @param srcorder 来源订单并且不是第一次拉单的VOS
   * @param srcsaleout 来源出库单的并且不是第一次拉单vos
   * @param allsrcsaleout 所有来源于出库单的数据
   */
  private void prepareData(SaleInvoiceVO[] vos,
      Map<String, UFDouble> srcnummaps, List<SaleInvoiceVO> srcordercurnosame,
      List<SaleInvoiceVO> srcsaleoutcurnosame,
      List<SaleInvoiceVO> srcordercursame,
      List<SaleInvoiceVO> srcsaleoutcursame, List<SaleInvoiceVO> allsrcsaleout) {
    List<SaleInvoiceVO> destvo1 = new ArrayList<SaleInvoiceVO>();
    UFDouble voicenum = null;
    UFDouble srcnum = null;
    for (SaleInvoiceVO vo : vos) {
      SaleInvoiceHVO hvo = vo.getParentVO();
      SaleInvoiceBVO[] bvos = vo.getChildrenVO();
      // 第一次拉单的表体
      List<SaleInvoiceBVO> onebvos = new ArrayList<SaleInvoiceBVO>();
      if (SOBillType.Order.getCode().equals(bvos[0].getVsrctype())) {
        List<SaleInvoiceBVO> bsrcorder = new ArrayList<SaleInvoiceBVO>();
        for (SaleInvoiceBVO bvo : bvos) {
          voicenum = bvo.getNnum();
          srcnum = srcnummaps.get(bvo.getCsrcbid());
          if (MathTool.compareTo(voicenum, srcnum) == 0
              || MathTool.compareTo(voicenum, UFDouble.ZERO_DBL) == 0) {
            onebvos.add(bvo);
            continue;
          }

          bsrcorder.add(bvo);
        }
        if (bsrcorder.size() > 0) {
          SaleInvoiceVO newvo = new SaleInvoiceVO();
          newvo.setParentVO(hvo);
          newvo.setChildrenVO(ListUtil.toArray(bsrcorder));

          if (hvo.getCcurrencyid().equals(hvo.getCorigcurrencyid())) {
            // 原本币一致
            srcordercursame.add(newvo);
          }
          else {
            // 原本币不一致
            srcordercurnosame.add(newvo);
          }

        }
      }
      else if (ICBillType.SaleOut.getCode().equals(bvos[0].getVsrctype())) {
        allsrcsaleout.add(vo);
        List<SaleInvoiceBVO> bsrcsaleout = new ArrayList<SaleInvoiceBVO>();
        for (SaleInvoiceBVO bvo : bvos) {
          voicenum = bvo.getNnum();
          srcnum = srcnummaps.get(bvo.getCsrcbid());
          if (MathTool.compareTo(voicenum, srcnum) == 0
              || MathTool.compareTo(voicenum, UFDouble.ZERO_DBL) == 0) {
            onebvos.add(bvo);
            continue;

          }
          bsrcsaleout.add(bvo);
        }
        if (bsrcsaleout.size() > 0) {
          SaleInvoiceVO newvo = new SaleInvoiceVO();
          newvo.setParentVO(hvo);
          newvo.setChildrenVO(ListUtil.toArray(bsrcsaleout));
          if (hvo.getCcurrencyid().equals(hvo.getCorigcurrencyid())) {
            // 原本币一致
            srcsaleoutcursame.add(newvo);
          }
          else {
            // 原本币不一致
            srcsaleoutcurnosame.add(newvo);
          }
        }
      }

      if (onebvos.size() > 0) {
        SaleInvoiceVO newvo = new SaleInvoiceVO();
        newvo.setParentVO(hvo);
        newvo.setChildrenVO(ListUtil.toArray(onebvos));
        destvo1.add(newvo);

      }

    }

    if (destvo1.size() > 0) {
      SaleInvoiceRateUtil rateutil = new SaleInvoiceRateUtil();
      rateutil.setBuyRate(destvo1.toArray(new SaleInvoiceVO[destvo1.size()]));

      // 集团本位币汇率改变的发票vo
      List<SaleInvoiceVO> groupchgratevos = rateutil.getGroupchgratevos();
      if (groupchgratevos != null && groupchgratevos.size() > 0) {
        this.reCalculatorByNexchangerate(
            groupchgratevos.toArray(new SaleInvoiceVO[groupchgratevos.size()]),
            SaleInvoiceHVO.NGROUPEXCHGRATE);
      }

      // 全局本位币汇率改变的发票vo
      List<SaleInvoiceVO> globalexchgratevos = rateutil.getGlobalexchgratevos();
      if (globalexchgratevos != null && globalexchgratevos.size() > 0) {
        this.reCalculatorByNexchangerate(globalexchgratevos
            .toArray(new SaleInvoiceVO[globalexchgratevos.size()]),
            SaleInvoiceHVO.NGLOBALEXCHGRATE);
      }

      // 折本汇率改变的发票VO
      List<SaleInvoiceVO> ratechangevos = rateutil.getRatechangevos();
      if (ratechangevos != null && ratechangevos.size() > 0) {
        this.reCalculatorByNexchangerate(
            ratechangevos.toArray(new SaleInvoiceVO[ratechangevos.size()]),
            SaleInvoiceHVO.NEXCHANGERATE);
      }
    }

  }

  /**
   * 计算折扣额
   * 
   * @param vos
   * @param key
   */
  private void reCalculatorDiscountmnyByKey(SaleInvoiceVO[] vos, String key) {
    SaleInvoiceVOCalculator voculator = new SaleInvoiceVOCalculator();
    for (SaleInvoiceVO vo : vos) {
      SaleInvoiceBVO[] bvos = vo.getChildrenVO();
      List<Integer> integerlist = new ArrayList<Integer>();

      for (int i = 0; i < bvos.length; i++) {
        if (null == bvos[i].getNnum()
            || bvos[i].getNnum().compareTo(UFDouble.ZERO_DBL) == 0) {
          continue;
        }
        integerlist.add(Integer.valueOf(i));
      }

      voculator.setVoInvoice(vo);
      voculator.setForcefixunitrate(UFBoolean.TRUE);
      voculator.calculateDiscountmny(ListUtil.toArray(integerlist), key);
    }
  }

  /**
   * 重取汇率计算单价金额(以折本汇率触发价格算法)
   * 有隐含的bug（如果处理了尾差但是折本汇率又没变，注：第一次拉单折本汇率变了）
   * 
   * @param vos
   */
  private void reCalculatorByNexchangerate(SaleInvoiceVO[] vos, String editKey) {

    SaleInvoiceVOCalculator voculator = new SaleInvoiceVOCalculator();

    for (SaleInvoiceVO vo : vos) {

      SaleInvoiceBVO[] bvos = vo.getChildrenVO();
      Integer[] intchangerows = new Integer[bvos.length];
      for (int i = 0; i < bvos.length; i++) {
        intchangerows[i] = Integer.valueOf(i);
      }

      voculator.setVoInvoice(vo);
      voculator.calculate(intchangerows, editKey);
    }
  }

}
