package nc.bs.so.m33.biz.m4c.rule.outrush;

import java.util.HashMap;
import java.util.Map;

import nc.bs.ml.NCLangResOnserver;
import nc.impl.pubapp.pattern.rule.ICompareRule;
import nc.itf.so.m33.ref.ic.m4c.ICM4CServiceUtil;
import nc.vo.ic.m4c.entity.SaleOutViewVO;
import nc.vo.pub.BusinessException;
import nc.vo.pub.lang.UFBoolean;
import nc.vo.pub.lang.UFDouble;
import nc.vo.pubapp.pattern.exception.ExceptionUtils;
import nc.vo.pubapp.pattern.pub.PubAppTool;
import nc.vo.so.m33.enumeration.SquareType;
import nc.vo.so.m33.m4c.entity.SquareOutBVO;
import nc.vo.so.m33.m4c.entity.SquareOutVOUtils;
import nc.vo.so.m33.m4c.entity.SquareOutViewVO;
import nc.vo.so.pub.util.ArrayUtil;
import nc.vo.so.pub.votools.SoVoTools;
import nc.vo.trade.checkrule.VOChecker;

/**
 * @description
 * 销售出库单出库对冲检查销售出库待结算单是否可以参与出库对冲
 * @scene
 * 销售出库单出库对冲前
 * @param 
 * 无
 */
public class CheckOutRush4CRule implements ICompareRule<SquareOutViewVO> {

  @Override
  public void process(SquareOutViewVO[] bluevos, SquareOutViewVO[] redvos) {
    // 蓝字出库单
    if (VOChecker.isEmpty(bluevos) || bluevos.length > 1) {
      ExceptionUtils
          .wrappBusinessException(nc.vo.ml.NCLangRes4VoTransl.getNCLangRes()
              .getStrByID("4006010_0", "04006010-0022")/*@res "只能选择一行蓝字出库单"*/);
    }
    // 红字出库单
    if (VOChecker.isEmpty(redvos)) {
      ExceptionUtils
          .wrappBusinessException(nc.vo.ml.NCLangRes4VoTransl.getNCLangRes()
              .getStrByID("4006010_0", "04006010-0023")/*@res "没有红字出库单"*/);
    }

    // 获取销售出库待结算单对应的销售出库单
    Map<String, SaleOutViewVO> moutview =
        this.getSaleOutViewVO(bluevos, redvos);
    this.check(bluevos[0], redvos, moutview);
  }

  private void check(SquareOutViewVO bluevo, SquareOutViewVO[] redvos,
      Map<String, SaleOutViewVO> moutview) {
    // 自动应收结算、手工结算(应收、成本)不支持出库对冲
    this.checkSquareType(bluevo);

    for (SquareOutViewVO redvo : redvos) {
      // 自动应收结算、手工结算(应收、成本)不支持出库对冲
      this.checkSquareType(redvo);

      // 赠品和非赠品出库单行不能对冲
      this.checkLargess(bluevo, redvo);

      // 蓝字红字出库单相关属性需要一致
      this.checkEqual(bluevo, redvo, moutview);
    }
  }

  private void checkEqual(SquareOutViewVO bluevo, SquareOutViewVO redvo,
      Map<String, SaleOutViewVO> moutview) {
    // 物料、订单客户、开票客户、批次号、成本域相同;币种、原币和本币的无税净价、含税净价相同
    String bluemaroid = bluevo.getItem().getCmaterialid();
    String blueordcus = bluevo.getItem().getCordercustid();
    String blueinvcus = bluevo.getItem().getCinvoicecustid();
    String bluecurid = bluevo.getItem().getCorigcurrencyid();
    String bluelocalcurid = bluevo.getItem().getCcurrencyid();
    String bluecostorgid = bluevo.getItem().getCcostorgid();
    String bluebatchcode = bluevo.getItem().getVbatchcode();
    String redmaroid = redvo.getItem().getCmaterialid();
    String redordcus = redvo.getItem().getCordercustid();
    String redinvcus = redvo.getItem().getCinvoicecustid();
    String redcurid = redvo.getItem().getCorigcurrencyid();
    String redlocalcurid = redvo.getItem().getCcurrencyid();
    String redcostorgid = redvo.getItem().getCcostorgid();
    String redbatchcode = redvo.getItem().getVbatchcode();

    // 由于涉及签收改价，所以蓝字销售出库待结算单是原始的价格，所以必须用出库单实时的价格比较
    SaleOutViewVO blueview = moutview.get(bluevo.getItem().getCsquarebillbid());
    if (VOChecker.isEmpty(blueview)) {
      ExceptionUtils
          .wrappBusinessException(nc.vo.ml.NCLangRes4VoTransl.getNCLangRes()
              .getStrByID("4006010_0", "04006010-0024")/*@res "数据异常，未找到蓝字销售出库单！"*/);
    }
    UFDouble blueotaxprice = blueview.getItem().getNorigtaxnetprice();
    UFDouble blueltaxprice = blueview.getItem().getNtaxnetprice();
    UFDouble blueoprice = blueview.getItem().getNorignetprice();
    UFDouble bluelprice = blueview.getItem().getNnetprice();
    SaleOutViewVO redview = moutview.get(redvo.getItem().getCsquarebillbid());
    if (VOChecker.isEmpty(redview)) {
      ExceptionUtils
          .wrappBusinessException(nc.vo.ml.NCLangRes4VoTransl.getNCLangRes()
              .getStrByID("4006010_0", "04006010-0025")/*@res "数据异常，未找到红字销售出库单！"*/);
    }
    UFDouble redotaxprice = redview.getItem().getNorigtaxnetprice();
    UFDouble redltaxprice = redview.getItem().getNtaxnetprice();
    UFDouble redoprice = redview.getItem().getNorignetprice();
    UFDouble redlprice = redview.getItem().getNnetprice();

    this.checkEqualItem(bluemaroid, redmaroid, NCLangResOnserver.getInstance()
        .getStrByID("4006010_0", "04006010-0109")/*蓝字出库单和红字出库单物料*/);
    this.checkEqualItem(blueordcus, redordcus, NCLangResOnserver.getInstance()
        .getStrByID("4006010_0", "04006010-0110")/*蓝字出库单和红字出库单订单客户*/);
    this.checkEqualItem(blueinvcus, redinvcus, NCLangResOnserver.getInstance()
        .getStrByID("4006010_0", "04006010-0111")/*蓝字出库单和红字出库单开票客户*/);
    this.checkEqualItem(bluecurid, redcurid, NCLangResOnserver.getInstance()
        .getStrByID("4006010_0", "04006010-0112")/*蓝字出库单和红字出库单物料原币*/);
    this.checkEqualItem(bluelocalcurid, redlocalcurid, NCLangResOnserver
        .getInstance().getStrByID("4006010_0", "04006010-0113")/*蓝字出库单和红字出库单本币*/);
    this.checkEqualItem(bluecostorgid, redcostorgid, NCLangResOnserver
        .getInstance().getStrByID("4006010_0", "04006010-0114")/*蓝字出库单和红字出库单成本域*/);
    this.checkEqualItem(bluebatchcode, redbatchcode, NCLangResOnserver
        .getInstance().getStrByID("4006010_0", "04006010-0115")/*蓝字出库单和红字出库单批次号*/);
    this.checkEqualItem(blueotaxprice, redotaxprice, NCLangResOnserver
        .getInstance().getStrByID("4006010_0", "04006010-0116")/*蓝字出库单和红字出库单原币含税净价*/);
    this.checkEqualItem(blueltaxprice, redltaxprice, NCLangResOnserver
        .getInstance().getStrByID("4006010_0", "04006010-0117")/*蓝字出库单和红字出库单本币含税净价*/);
    this.checkEqualItem(blueoprice, redoprice, NCLangResOnserver.getInstance()
        .getStrByID("4006010_0", "04006010-0118")/*蓝字出库单和红字出库单原币无税净价*/);
    this.checkEqualItem(bluelprice, redlprice, NCLangResOnserver.getInstance()
        .getStrByID("4006010_0", "04006010-0119")/*蓝字出库单和红字出库单本币无税净价*/);
  }

  private void checkEqualItem(String blue, String red, String hit) {
    if (PubAppTool.isNull(blue) && !PubAppTool.isNull(red)
        || PubAppTool.isNull(red) && !PubAppTool.isNull(blue)
        || !PubAppTool.isNull(blue) && !PubAppTool.isNull(red)
        && !blue.equals(red)) {
      ExceptionUtils.wrappBusinessException(NCLangResOnserver.getInstance()
          .getStrByID("4006010_0", "04006010-0120", null, new String[] {
            hit
          })/*{0}必须相等*/);
    }
  }

  private void checkEqualItem(UFDouble blue, UFDouble red, String hit) {
    if (VOChecker.isEmpty(blue) && !VOChecker.isEmpty(red)
        || !VOChecker.isEmpty(blue) && VOChecker.isEmpty(red)
        || !VOChecker.isEmpty(blue) && !VOChecker.isEmpty(red)
        && blue.compareTo(red) != 0) {
      ExceptionUtils.wrappBusinessException(NCLangResOnserver.getInstance()
          .getStrByID("4006010_0", "04006010-0120", null, new String[] {
            hit
          })/*{0}必须相等*/);
    }
  }

  private void checkLargess(SquareOutViewVO bluevo, SquareOutViewVO redvo) {
    // 蓝字出库单行是否赠品
    UFBoolean ifBlueLar = bluevo.getItem().getBlargessflag();
    boolean bifBlueLar = false;
    if (ifBlueLar != null) {
      bifBlueLar = ifBlueLar.booleanValue();
    }

    // 红字出库单行是否赠品
    UFBoolean ifRedLar = redvo.getItem().getBlargessflag();
    boolean bifRedLar = false;
    if (ifRedLar != null) {
      bifRedLar = ifRedLar.booleanValue();
    }

    if (bifBlueLar && !bifRedLar || !bifBlueLar && bifRedLar) {
      ExceptionUtils
          .wrappBusinessException(nc.vo.ml.NCLangRes4VoTransl.getNCLangRes()
              .getStrByID("4006010_0", "04006010-0026")/*@res "赠品和非赠品出库单行不能对冲"*/);
    }

  }

  /**
   * 自动应收结算、手工应收结算不支持出库对冲
   * 
   * @param view
   */
  private void checkSquareType(SquareOutViewVO view) {
    boolean autoARFlag = view.getHead().getBautosquareincome().booleanValue();
    int artype = view.getItem().getFpreartype().intValue();
    if (autoARFlag && artype == SquareType.SQUARETYPE_AR.getIntValue()) {
      ExceptionUtils
          .wrappBusinessException(nc.vo.ml.NCLangRes4VoTransl.getNCLangRes()
              .getStrByID("4006010_0", "04006010-0027")/*@res "销售出库单自动应收结算不可以出库对冲！"*/);
    }
    if (!autoARFlag && artype == SquareType.SQUARETYPE_AR.getIntValue()) {
      ExceptionUtils
          .wrappBusinessException(nc.vo.ml.NCLangRes4VoTransl.getNCLangRes()
              .getStrByID("4006010_0", "04006010-0028")/*@res "销售出库单手工应收结算不可以出库对冲！"*/);
    }
  }

  /**
   * 获取销售出库待结算单对应的销售出库单
   * 
   * @param bluevos
   * @param redvos
   * @return <4cbid,SaleOutViewVO>
   */
  private Map<String, SaleOutViewVO> getSaleOutViewVO(
      SquareOutViewVO[] bluevos, SquareOutViewVO[] redvos) {
    SquareOutBVO[] bluebvos =
        SquareOutVOUtils.getInstance().getSquareOutBVO(bluevos);
    SquareOutBVO[] redbvos =
        SquareOutVOUtils.getInstance().getSquareOutBVO(redvos);
    String[] bluebids =
        SoVoTools.getVOsOnlyValues(bluebvos, SquareOutBVO.CSQUAREBILLBID);
    String[] redbids =
        SoVoTools.getVOsOnlyValues(redbvos, SquareOutBVO.CSQUAREBILLBID);
    String[] outbids = ArrayUtil.combinArrays(bluebids, redbids);
    Map<String, SaleOutViewVO> moutview = new HashMap<String, SaleOutViewVO>();
    SaleOutViewVO[] views = null;
    try {
      views = ICM4CServiceUtil.queryViewVOsByBids(outbids);
      for (SaleOutViewVO view : views) {
        moutview.put(view.getItem().getCgeneralbid(), view);
      }
    }
    catch (BusinessException e) {
      ExceptionUtils.wrappException(e);
    }
    return moutview;
  }

}
