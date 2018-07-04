package nc.ui.so.m30.revise.rule;

import java.util.ArrayList;
import java.util.List;

import nc.vo.so.m30.entity.SaleOrderBVO;

/**
 * 销售订单修订时复制粘贴规则：要清空一些信息
 * 
 * @since 6.33
 * @version 2014-9-9 下午6:11:26
 * @author 张斌阳
 */
public class RevisePasteLineRule {

  private static final String[] CLEARITEMS = new String[] {
    // TS、表体ID
    SaleOrderBVO.TS,
    SaleOrderBVO.CSALEORDERBID,

    SaleOrderBVO.FROWSTATUS,
    SaleOrderBVO.VBREVISEREASON,
    // 标志
    SaleOrderBVO.BBOUTENDFLAG,
    SaleOrderBVO.BBSENDENDFLAG,
    SaleOrderBVO.BBINVOICENDFLAG,
    SaleOrderBVO.BBCOSTSETTLEFLAG,
    SaleOrderBVO.BBARSETTLEFLAG,
    SaleOrderBVO.BARRANGEDFLAG,
    SaleOrderBVO.CARRANGEPERSONID,
    SaleOrderBVO.TLASTARRANGETIME,
    SaleOrderBVO.FRETEXCHANGE,
    SaleOrderBVO.CEXCHANGESRCRETID,
    // 下游累计数量
    SaleOrderBVO.NTOTALSENDNUM, SaleOrderBVO.NTOTALINVOICENUM,
    SaleOrderBVO.NTOTALOUTNUM, SaleOrderBVO.NTOTALNOTOUTNUM,
    SaleOrderBVO.NTOTALSIGNNUM, SaleOrderBVO.NTRANSLOSSNUM,
    SaleOrderBVO.NTOTALRUSHNUM, SaleOrderBVO.NTOTALESTARNUM,
    SaleOrderBVO.NTOTALARNUM, SaleOrderBVO.NTOTALCOSTNUM,
    SaleOrderBVO.NTOTALESTARMNY, SaleOrderBVO.NTOTALARMNY,
    SaleOrderBVO.NTOTALPAYMNY, SaleOrderBVO.NTOTALPLONUM,
    SaleOrderBVO.NARRANGESCORNUM, SaleOrderBVO.NARRANGEPOAPPNUM,
    SaleOrderBVO.NARRANGETOORNUM, SaleOrderBVO.NARRANGETOAPPNUM,
    SaleOrderBVO.NARRANGEMONUM, SaleOrderBVO.NARRANGEPONUM,
    SaleOrderBVO.NTOTALRETURNNUM, SaleOrderBVO.NTOTALTRADENUM,
    SaleOrderBVO.NREQRSNUM,

    SaleOrderBVO.BBINDFLAG, SaleOrderBVO.CBINDSRCID,
    SaleOrderBVO.CLARGESSSRCID, SaleOrderBVO.VCLOSEREASON
  };

  private static final String[] OFFSETCLEARITEMS = new String[] {
    // 数量
    SaleOrderBVO.NNUM,
    SaleOrderBVO.NASTNUM,
    SaleOrderBVO.NQTUNITNUM,
    // 原币单价
    SaleOrderBVO.NQTORIGTAXPRICE,
    SaleOrderBVO.NQTORIGPRICE,
    SaleOrderBVO.NQTORIGTAXNETPRC,
    SaleOrderBVO.NQTORIGNETPRICE,
    // 主原币单价
    SaleOrderBVO.NORIGPRICE,
    SaleOrderBVO.NORIGTAXPRICE,
    SaleOrderBVO.NORIGNETPRICE,
    SaleOrderBVO.NORIGTAXNETPRICE,
    // 金额
    SaleOrderBVO.NORIGMNY,
    SaleOrderBVO.NORIGTAXMNY,
    SaleOrderBVO.NORIGDISCOUNT,
    SaleOrderBVO.NCALTAXMNY,
    // 本币单价
    SaleOrderBVO.NQTTAXNETPRICE, SaleOrderBVO.NQTNETPRICE,
    SaleOrderBVO.NQTTAXPRICE,
    SaleOrderBVO.NQTPRICE,
    // 主本币单价
    SaleOrderBVO.NPRICE, SaleOrderBVO.NTAXPRICE, SaleOrderBVO.NNETPRICE,
    SaleOrderBVO.NTAXNETPRICE,
    // 本币金额
    SaleOrderBVO.NTAX, SaleOrderBVO.NMNY, SaleOrderBVO.NTAXMNY,
    SaleOrderBVO.NDISCOUNT,
    // 集团金额
    SaleOrderBVO.NGROUPMNY, SaleOrderBVO.NGROUPTAXMNY,
    // 全局金额
    SaleOrderBVO.NGLOBALMNY, SaleOrderBVO.NGLOBALTAXMNY,
    // 冲抵金额
    SaleOrderBVO.NORIGSUBMNY, SaleOrderBVO.NBFORIGSUBMNY
  };

  public List<String> getClearItems() {
    List<String> list = new ArrayList<String>();
    for (String key : RevisePasteLineRule.CLEARITEMS) {
      list.add(key);
    }
    return list;
  }

  public List<String> getClearItemsWhenOffSet() {
    List<String> list = new ArrayList<String>();
    for (String key : RevisePasteLineRule.OFFSETCLEARITEMS) {
      list.add(key);
    }
    return list;
  }

}
