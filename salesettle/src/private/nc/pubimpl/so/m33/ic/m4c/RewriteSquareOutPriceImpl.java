package nc.pubimpl.so.m33.ic.m4c;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import nc.bs.so.m33.maintain.m4c.query.QuerySquare4CVOBP;
import nc.impl.pubapp.pattern.data.view.ViewUpdate;
import nc.pubitf.so.m33.ic.m4c.IRewriteSquareOutPrice;
import nc.pubitf.so.m33.ic.m4c.RewritePara33For4C;
import nc.vo.pub.BusinessException;
import nc.vo.pubapp.pattern.exception.ExceptionUtils;
import nc.vo.pubapp.pattern.pub.MathTool;
import nc.vo.so.m33.m4c.entity.SquareOutBVO;
import nc.vo.so.m33.m4c.entity.SquareOutVOUtils;
import nc.vo.so.m33.m4c.entity.SquareOutViewVO;
import nc.vo.so.m33.pub.util.SquareCalculatorForVO;
import nc.vo.so.pub.util.SOVOChecker;

public class RewriteSquareOutPriceImpl implements IRewriteSquareOutPrice {

  @Override
  public void rewriteSquareOutPrice(RewritePara33For4C[] paras)
      throws BusinessException {
    // 判断参数数据是否完整
    this.checkPara(paras);

    // 查询销售出库待结算单
    Map<String, RewritePara33For4C> moutbid =
        new HashMap<String, RewritePara33For4C>();
    for (RewritePara33For4C para : paras) {
      moutbid.put(para.getCoutbid(), para);
    }
    String[] outbids = moutbid.keySet().toArray(new String[moutbid.size()]);
    SquareOutViewVO[] views =
        new QuerySquare4CVOBP().querySquareOutViewVOBy4CBID(outbids);

    // 回写价格、重新计算、持久化
    if (!SOVOChecker.isEmpty(views)) {
      List<SquareOutViewVO> calViews = new ArrayList<SquareOutViewVO>();
      for (SquareOutViewVO view : views) {
        SquareOutBVO bvo = view.getItem();
        String outbid = bvo.getCsquarebillbid();
        RewritePara33For4C para = moutbid.get(outbid);
        // 只更新变化的数据
        if (!MathTool.equals(bvo.getNorigtaxnetprice(),
            para.getNorigtaxnetprice())
            || !MathTool
                .equals(bvo.getNorigtaxprice(), para.getNorigtaxprice())
            || !MathTool
                .equals(bvo.getNorignetprice(), para.getNorignetprice())
            || !MathTool.equals(bvo.getNorigprice(), para.getNorigprice())
            || !MathTool.equals(bvo.getNtaxnetprice(), para.getNtaxnetprice())
            || !MathTool.equals(bvo.getNtaxprice(), para.getNtaxprice())
            || !MathTool.equals(bvo.getNnetprice(), para.getNnetprice())
            || !MathTool.equals(bvo.getNprice(), para.getNprice())) {
          calViews.add(view);
          bvo.setNthisnum(bvo.getNnum());
          bvo.setNorigtaxnetprice(para.getNorigtaxnetprice());
          bvo.setNorigtaxprice(para.getNorigtaxprice());
          bvo.setNorignetprice(para.getNorignetprice());
          bvo.setNorigprice(para.getNorigprice());
          bvo.setNtaxnetprice(para.getNtaxnetprice());
          bvo.setNtaxprice(para.getNtaxprice());
          bvo.setNnetprice(para.getNnetprice());
          bvo.setNprice(para.getNprice());
        }
      }
      // 没有变化不进行联动计算和更新
      if (calViews.size() == 0) {
        return;
      }
      SquareOutViewVO[] updateViews =
          calViews.toArray(new SquareOutViewVO[calViews.size()]);
      SquareOutBVO[] bvos =
          SquareOutVOUtils.getInstance().getSquareOutBVO(updateViews);
      new SquareCalculatorForVO().calculate(bvos, SquareOutBVO.NTHISNUM);
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
      bo.update(updateViews, SquareOutBVO.class, updatefields);
    }

  }

  /**
   * 参数中4个原币价格、4个本币价格必须全部填写
   * 
   * @param paras
   */
  private void checkPara(RewritePara33For4C[] paras) {
    for (RewritePara33For4C para : paras) {
      if (SOVOChecker.isEmpty(para.getCoutbid())) {
        ExceptionUtils.wrappBusinessException("para.getCoutbid() is null");/* -=
                                                                            * notranslate
                                                                            * =- */
      }
      if (SOVOChecker.isEmpty(para.getNorigtaxnetprice())) {
        ExceptionUtils
            .wrappBusinessException("para.getNorigtaxnetprice() is null");/* -=
                                                                           * notranslate
                                                                           * =- */
      }
      if (SOVOChecker.isEmpty(para.getNorigtaxprice())) {
        ExceptionUtils
            .wrappBusinessException("para.getNorigtaxprice() is null");/* -=
                                                                        * notranslate
                                                                        * =- */
      }
      if (SOVOChecker.isEmpty(para.getNorignetprice())) {
        ExceptionUtils
            .wrappBusinessException("para.getNorignetprice() is null");/* -=
                                                                        * notranslate
                                                                        * =- */
      }
      if (SOVOChecker.isEmpty(para.getNorigprice())) {
        ExceptionUtils.wrappBusinessException("para.getNorigprice() is null");/* -=
                                                                               * notranslate
                                                                               * =
                                                                               * - */
      }
      if (SOVOChecker.isEmpty(para.getNtaxnetprice())) {
        ExceptionUtils.wrappBusinessException("para.getNtaxnetprice() is null");/* -=
                                                                                 * notranslate
                                                                                 * =
                                                                                 * - */
      }
      if (SOVOChecker.isEmpty(para.getNtaxprice())) {
        ExceptionUtils.wrappBusinessException("para.getNtaxprice() is null");/* -=
                                                                              * notranslate
                                                                              * =
                                                                              * - */
      }
      if (SOVOChecker.isEmpty(para.getNnetprice())) {
        ExceptionUtils.wrappBusinessException("para.getNnetprice() is null");/* -=
                                                                              * notranslate
                                                                              * =
                                                                              * - */
      }
      if (SOVOChecker.isEmpty(para.getNprice())) {
        ExceptionUtils.wrappBusinessException("para.getNprice() is null");/* -=
                                                                           * notranslate
                                                                           * =- */
      }
    }
  }

}
