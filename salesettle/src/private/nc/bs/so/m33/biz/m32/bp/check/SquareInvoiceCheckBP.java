package nc.bs.so.m33.biz.m32.bp.check;

import java.util.HashMap;
import java.util.Map;

import nc.bs.framework.common.NCLocator;
import nc.bs.ml.NCLangResOnserver;
import nc.pubitf.so.m33.self.pub.ISquare434CQuery;
import nc.vo.pub.lang.UFDouble;
import nc.vo.pubapp.pattern.exception.ExceptionUtils;
import nc.vo.pubapp.pattern.pub.MathTool;
import nc.vo.so.m33.enumeration.SquareType;
import nc.vo.so.m33.m32.entity.SquareInvBVO;
import nc.vo.so.m33.m32.entity.SquareInvVO;
import nc.vo.so.m33.m4c.entity.SquareOutViewVO;
import nc.vo.so.pub.util.AggVOUtil;
import nc.vo.trade.checkrule.VOChecker;

/**
 * 销售发票待结算单公共校验BP
 * @since 6.0
 * @version 2011-8-29 下午08:13:57
 * @author zc
 */
public class SquareInvoiceCheckBP {
  
  /**
   * 如果上游出库单部分暂估、发出商品，则发票不可取消结算
   * @param sqvos
   */
  public void checkETREGForCancelSquare(SquareInvVO[] sqvos){
    String ethit = NCLangResOnserver
    .getInstance().getStrByID("4006010_0", "04006010-0107", null,
        new String[] {})/*上游销售出库单做过暂估应收，则禁止对没有回冲应收的下游销售发票取消结算！*/;
    String reghit = NCLangResOnserver
    .getInstance().getStrByID("4006010_0", "04006010-0108", null,
        new String[] {})/*上游销售出库单做过发出商品，则禁止对没有发出商品的下游销售发票取消结算！*/;
    checkETREG(sqvos,ethit,reghit);
  }
  
  /**
   * 如果上游出库单部分暂估、发出商品，则发票不可生成对冲发票
   * @param sqvos
   */
  public void checkETREGForCreateRushInvoice(SquareInvVO[] sqvos){
   // String ethit = "上游销售出库单做过暂估应收，则禁止对没有回冲应收的下游销售发票生成对冲发票";
   // String reghit = "上游销售出库单做过发出商品，则禁止对没有发出商品的下游销售发票生成对冲发票";
    String ethit = NCLangResOnserver
    .getInstance().getStrByID("4006010_0", "04006010-0146", null,
        new String[] {})/*上游销售出库单做过暂估应收，则禁止对没有回冲应收的下游销售发票生成对冲发票*/;
    String reghit = NCLangResOnserver
    .getInstance().getStrByID("4006010_0", "04006010-0147", null,
        new String[] {})/*上游销售出库单做过发出商品，则禁止对没有发出商品的下游销售发票生成对冲发票*/;
    checkETREG(sqvos,ethit,reghit);
  }
  
  private void checkETREG(SquareInvVO[] sqvos,String ethit,String reghit){
    // 销售出库结算查询接口
    ISquare434CQuery square4cQry =
        NCLocator.getInstance().lookup(ISquare434CQuery.class);
    // 查询上游出库单暂估应收记录
    String[] outBids =
        AggVOUtil.getDistinctItemFieldArray(sqvos, SquareInvBVO.CSRCBID,
            String.class);
    SquareOutViewVO[] outETREGviews =
        square4cQry.queryETIncomeREGCostBidBy4CBID(outBids);
    if (!VOChecker.isEmpty(outETREGviews)) {

      // <上游出库单bid,出库结算单>
      Map<String, SquareOutViewVO> moutbiddvo =
          new HashMap<String, SquareOutViewVO>();
      for (SquareOutViewVO view : outETREGviews) {
        moutbiddvo.put(view.getItem().getCsquarebillbid(), view);
      }

      // 上游出库单有暂估应收
      for (SquareInvVO svo : sqvos) {
        for (SquareInvBVO bvo : svo.getChildrenVO()) {
          // 销售发票
          UFDouble nrushnum = bvo.getNarrushnum();
          UFDouble nregnum = bvo.getNsquareregnum();
          Integer iarsqtype = bvo.getFpreartype();
          Integer iiasqtype = bvo.getFpreiatype();

          // 上游出库单结算类型
          SquareOutViewVO outview = moutbiddvo.get(bvo.getCsrcbid());
          // 销售发票上游不是出库单跳出
          if (VOChecker.isEmpty(outview)) {
            continue;
          }
          Integer ioutarsqtype = outview.getItem().getFpreartype();
          Integer ioutiasqtype = outview.getItem().getFpreiatype();
          UFDouble noutetnum = outview.getItem().getNsquareestnum();
          UFDouble noutregnum = outview.getItem().getNsquareregnum();

          // 销售发票行没有回冲应收同时上游出库单有暂估应收
          if (SquareType.SQUARETYPE_ET.getIntValue() == ioutarsqtype.intValue()
              && !VOChecker.isEmpty(iarsqtype)
              && SquareType.SQUARETYPE_AR.getIntValue() == iarsqtype.intValue()
              && !MathTool.isZero(noutetnum) && MathTool.isZero(nrushnum)
              && moutbiddvo.containsKey(bvo.getCsrcbid())) {
            ExceptionUtils.wrappBusinessException(ethit);
          }
          // 销售发票行没有发出商品同时上游出库单有发出商品
          if (SquareType.SQUARETYPE_REG_DEBIT.getIntValue() == ioutiasqtype
              .intValue()
              && !VOChecker.isEmpty(iiasqtype)
              && SquareType.SQUARETYPE_IA.getIntValue() == iiasqtype.intValue()
              && !MathTool.isZero(noutregnum)
              && MathTool.isZero(nregnum)
              && moutbiddvo.containsKey(bvo.getCsrcbid())) {
            ExceptionUtils.wrappBusinessException(reghit);
          }
        }
      }
    }
  }
  
}
