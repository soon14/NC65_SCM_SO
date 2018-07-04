package nc.vo.so.m32.util;

import nc.vo.pub.lang.UFBoolean;
import nc.vo.pub.lang.UFDate;
import nc.vo.pub.lang.UFDouble;
import nc.vo.pubapp.pattern.data.ValueUtils;
import nc.vo.pubapp.pattern.exception.ExceptionUtils;
import nc.vo.pubapp.pattern.pub.MathTool;
import nc.vo.so.m32.entity.SaleInvoiceBVO;
import nc.vo.so.m32.entity.SaleInvoiceHVO;
import nc.vo.so.m32.entity.SaleInvoiceVO;
import nc.vo.so.m32.enumeration.OpposeFlag;
import nc.vo.so.pub.enumeration.BillStatus;
import nc.vo.trade.checkrule.VOChecker;
import nc.vo.uif2.LoginContext;

/**
 * <p>
 * <b>本类主要完成以下功能：</b>
 * <ul>
 * <li>销售发票对冲数据转换工具类
 * </ul>
 * <p>
 * 
 * @version 本版本号 6.0
 * @since
 * @author fengjb
 * @time 2010-8-24 上午10:03:56
 */
public class OpposeConvertUtil {

  private static OpposeConvertUtil instance = new OpposeConvertUtil();

  /**
   * OpposeConvertUtil 的构造子
   */
  private OpposeConvertUtil() {
    // 缺省构造方法
  }

  /**
   * 方法功能描述：返回转换工具类实例。
   * <p>
   * <b>参数说明</b>
   * 
   * @return <p>
   * @author fengjb
   * @time 2010-8-24 上午10:03:40
   */
  public static OpposeConvertUtil getInstance() {
    return OpposeConvertUtil.instance;
  }

  /**
   * 方法功能描述：检查对冲发票合法性。
   * <p>
   * <b>参数说明</b>
   * 
   * @param voInvoice
   *          <p>
   * @author 冯加滨
   * @time 2010-4-28 上午10:32:30
   */
  public void checkOpposeVality(SaleInvoiceHVO voHead) {
    // 单据状态校验
    if (!BillStatus.AUDIT.equalsValue(voHead.getFstatusflag())) {
      ExceptionUtils
          .wrappBusinessException(nc.vo.ml.NCLangRes4VoTransl.getNCLangRes()
              .getStrByID("4006008_0", "04006008-0067")/*@res "审批通过的发票才允许对冲"*/);
    }
    // // 合并开票校验
    // if (voHead.getBsubunitflag().booleanValue()) {
    // ExceptionUtils
    // .wrappBusinessException(nc.vo.ml.NCLangRes4VoTransl.getNCLangRes()
    // .getStrByID("4006008_0", "04006008-0068")/*@res "已经费用冲抵的发票不允许对冲"*/);
    // }
    // 对冲标志校验
    if (!OpposeFlag.NORMAL.equalsValue(voHead.getFopposeflag())) {
      ExceptionUtils
          .wrappBusinessException(nc.vo.ml.NCLangRes4VoTransl.getNCLangRes()
              .getStrByID("4006008_0", "04006008-0069")/*@res "对冲标记为正常的发票才允许对冲"*/);
    }

  }

  public SaleInvoiceVO convertToOpposeVO(SaleInvoiceVO voInvoice,
      LoginContext logctx, UFDate busidate) {
    // 对冲发票合法性校验
    this.checkOpposeVality(voInvoice.getParentVO());

    SaleInvoiceHVO oppHead =
        this.convertToOpposeHVO(voInvoice.getParentVO(), logctx, busidate);

    SaleInvoiceBVO[] oppBodys =
        this.convertToOpposeBVO(voInvoice.getChildrenVO(), busidate);

    SaleInvoiceVO oppInvoice = new SaleInvoiceVO();
    oppInvoice.setParentVO(oppHead);
    oppInvoice.setChildrenVO(oppBodys);
    return oppInvoice;
  }

  /**
   * 方法功能描述：返回销售发票子表VO的对冲VO
   * <p>
   * <b>参数说明</b>
   * 
   * @param childrenVO
   * @return <p>
   * @author 冯加滨
   * @time 2010-4-28 上午11:44:19
   */
  private SaleInvoiceBVO[] convertToOpposeBVO(SaleInvoiceBVO[] childrenVO,
      UFDate busidate) {
    String[] clearKeys =
        new String[] {
          SaleInvoiceBVO.CSALEINVOICEBID, SaleInvoiceBVO.CSALEINVOICEID,
          SaleInvoiceBVO.NSHOULDOUTNUM, SaleInvoiceBVO.NTOTALOUTNUM,
          SaleInvoiceBVO.NTOTALINCOMENUM, SaleInvoiceBVO.NTOTALINCOMEMNY,
          SaleInvoiceBVO.NTOTALCOSTNUM, SaleInvoiceBVO.NTOTALPAYMNY
        };

    String[] oppKeys =
        new String[] {
          SaleInvoiceBVO.NNUM, SaleInvoiceBVO.NASTNUM,
          SaleInvoiceBVO.NQTUNITNUM, SaleInvoiceBVO.NTAX,
          SaleInvoiceBVO.NTAXMNY, SaleInvoiceBVO.NMNY,
          SaleInvoiceBVO.NCALTAXMNY, SaleInvoiceBVO.NDISCOUNT,
          SaleInvoiceBVO.NORIGTAXMNY, SaleInvoiceBVO.NORIGMNY,
          SaleInvoiceBVO.NORIGDISCOUNT, SaleInvoiceBVO.NGLOBALMNY,
          SaleInvoiceBVO.NGLOBALTAXMNY, SaleInvoiceBVO.NGROUPMNY,
          SaleInvoiceBVO.NGROUPTAXMNY, SaleInvoiceBVO.NORIGSUBMNY
        };
    int ilen = childrenVO.length;
    SaleInvoiceBVO[] oppbodys = new SaleInvoiceBVO[ilen];
    for (int i = 0; i < ilen; i++) {
      oppbodys[i] = (SaleInvoiceBVO) childrenVO[i].clone();
      // 设置默认值
      oppbodys[i].setDbilldate(busidate);
      oppbodys[i].setCopposesrcbid(oppbodys[i].getCsaleinvoicebid());
      // 清空字段值
      for (String key : clearKeys) {
        oppbodys[i].setAttributeValue(key, null);
      }
      // 取反字段值
      for (String key : oppKeys) {
        UFDouble value =
            ValueUtils.getUFDouble(oppbodys[i].getAttributeValue(key));
        UFDouble oppvalue = null;
        if (!VOChecker.isEmpty(value)) {
          oppvalue = MathTool.oppose(value);
        }
        oppbodys[i].setAttributeValue(key, oppvalue);
      }
    }
    return oppbodys;
  }

  /**
   * 方法功能描述：返回销售发票主表VO的对冲VO。
   * <p>
   * <b>参数说明</b>
   * 
   * @param parent
   * @return <p>
   * @author 冯加滨
   * @param logctx
   * @time 2010-4-28 上午10:55:32
   */
  private SaleInvoiceHVO convertToOpposeHVO(SaleInvoiceHVO voHead,
      LoginContext logctx, UFDate busidate) {
    SaleInvoiceHVO oppHead = (SaleInvoiceHVO) voHead.clone();
    // 设置对冲后与原发票不同的字段值
    oppHead.setBtogoldtaxflag(UFBoolean.FALSE);
    oppHead.setDbilldate(busidate);
    oppHead.setCreator(logctx.getPk_loginUser());
    oppHead.setBillmaker(logctx.getPk_loginUser());
    oppHead.setIprintcount(Integer.valueOf(0));
    oppHead.setFstatusflag((Integer) BillStatus.FREE.value());
    oppHead.setFopposeflag((Integer) OpposeFlag.GENERAL.value());
    oppHead.setCopposesrcid(voHead.getCsaleinvoiceid());
    oppHead.setVopposesrccode(voHead.getVbillcode());
    // 需要清空的字段
    String[] clearKeys =
        new String[] {
          SaleInvoiceHVO.VBILLCODE, SaleInvoiceHVO.CSALEINVOICEID,
          SaleInvoiceHVO.TGOLDTAXTIME, SaleInvoiceHVO.MODIFIER,
          SaleInvoiceHVO.MODIFIEDTIME, SaleInvoiceHVO.APPROVER,
          SaleInvoiceHVO.TAUDITTIME
        };
    for (String key : clearKeys) {
      oppHead.setAttributeValue(key, null);
    }

    // 需要取反的字段
    String[] oppKeys =
        new String[] {
          SaleInvoiceHVO.NTOTALASTNUM, SaleInvoiceHVO.NTOTALORIGMNY,
          SaleInvoiceHVO.NTOTALORIGSUBMNY
        };

    for (String key : oppKeys) {
      UFDouble value = ValueUtils.getUFDouble(voHead.getAttributeValue(key));
      if (null != value) {
        UFDouble oppValue = MathTool.oppose(value);
        oppHead.setAttributeValue(key, oppValue);
      }
    }
    return oppHead;
  }
}
