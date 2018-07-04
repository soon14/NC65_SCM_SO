package nc.bs.so.m33.biz.m32.rule.toar;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;

import org.apache.commons.lang.ArrayUtils;

import nc.vo.pub.lang.UFDouble;
import nc.vo.pubapp.pattern.data.ValueUtils;
import nc.vo.pubapp.pattern.pub.MathTool;
import nc.vo.pubapp.pattern.pub.PubAppTool;
import nc.vo.pubapp.scale.ScaleUtils;
import nc.vo.so.m33.enumeration.SquareType;
import nc.vo.so.m33.m32.entity.SquareInvBVO;
import nc.vo.so.m33.m32.entity.SquareInvVO;
import nc.vo.so.m33.m32.entity.SquareInvVOUtils;
import nc.vo.so.m33.m32.entity.SquareInvViewVO;
import nc.vo.so.m33.m4c.entity.SquareOutDetailVO;
import nc.vo.so.pub.calculator.PriceNumMnyCalculatorForVO;
import nc.vo.so.pub.util.AggVOUtil;
import nc.vo.so.pub.util.SOSysParaInitUtil;
import nc.vo.so.pub.util.SOVOChecker;

import nc.pubitf.so.m33.self.pub.ISquare434CQuery;
import nc.pubitf.so.m33.self.pub.ISquare4353Query;

import nc.bs.framework.common.NCLocator;
import nc.bs.pubapp.AppBsContext;
import nc.bs.so.m33.maintain.m32.query.SquareADQuery;
import nc.bs.so.m33.maintain.m4c.query.QuerySquare4CVOBP;

import nc.impl.pubapp.pattern.rule.IFilterRule;

/**
 * @description
 * 差额传应收准备数据（销售出库单必须自动应收结算，销售发票差额传应收）
 * 
 * 1.如果发票累计结算数量 != 出库数量-途损数量-签收开票退回出库单的数量：找到销售出库单传应收单的单价。
 * a)如果发票单价不等于销售出库单传应收单的单价，生成调差的应收单：
 * 存货等数据项原样传递；数量传0；单价传空；
 * 金额传 （发票单价 - 销售出库单传应收单的单价）* 发票数量；
 * b)如果发票单价等于销售出库单传应收单的单价，不生成调差应收单。
 * 
 * 2.发票累计结算数量 = 出库数量-途损数量-签收开票退回出库单的数量：
 * 本次调整应收金额=本次结算金额+∑（已结算金额-已调整应收金额）-出库确认应收金额。
 * 其中，已调整应收金额要包括：已经调差的金额+途损生成应收单金额+基于签收开票场景下退回生成应收单金额。
 * @scene
 * 差额传应收准备数据
 * @param
 * bTaxPrior 集团 基价含税、含税优先
 * mInvADMny 出库单下游发票已经调差金额  <上游出库单行id,[0]累计应收调差含税金额,[1]累计应收调差无税金额>
 * mnyKey 根据含税优先机制确定单价字段
 * mOutNum 下游签收开票退回出库单累计传应收数据  <上游出库单行id,[0]累计应收数量,[1]累计应收含税金额,[2]累计应收无税金额>
 * mWasNum 下游途损单累计传应收数据  <上游出库单行id,[0]累计应收数量,[1]累计应收含税金额,[2]累计应收无税金额>
 * @author zhangcheng
 * 
 */
public class AdjustIncomeFor32Rule implements IFilterRule<SquareInvVO> {

  private boolean bTaxPrior = true;

  /**
   * 出库单下游发票已经调差金额
   * <上游出库单行id,[0]累计应收调差含税金额,[1]累计应收调差无税金额>
   */
  private Map<String, UFDouble[]> mInvADMny;

  private String mnyKey;

  /**
   * 下游签收开票退回出库单累计传应收数据
   * <上游出库单行id,[0]累计应收数量,[1]累计应收含税金额,[2]累计应收无税金额>
   */
  private Map<String, UFDouble[]> mOutNum;

  /**
   * 下游途损单累计传应收数据
   * <上游出库单行id,[0]累计应收数量,[1]累计应收含税金额,[2]累计应收无税金额>
   */
  private Map<String, UFDouble[]> mWasNum;

  // 根据基价是否含税来确定是价税合计还是无税金额
  private String priceKey;

  public AdjustIncomeFor32Rule() {
    this.setKey();
  }

  @Override
  public SquareInvVO[] process(SquareInvVO[] vos) {
    // 初始化、处理已经调差的金额、下游途损单、签收退回的销售出库单累计传应收数据
    this.initADWasReturnOutData(vos);

    // 查询上游出库单应收结算数据<出库单行id,结算明细VO>
    Map<String, SquareOutDetailVO> m_dvo = this.queryUpARSquareOutVO(vos);

    // 查询同一行上游出库单下游发票累计结算信息（包括本次）<出库单行id,(下游发票累计结算数量[0],[1]累计结算金额)>
    Map<String, UFDouble[]> m4cbidnum =
        new SquareADQuery().queryTotalSquareNumBy4C(vos);

    // 发票累计结算数量与出库数量不相同-调差应收
    List<SquareInvViewVO> l_noEqual = new ArrayList<SquareInvViewVO>();
    // 发票累计结算数量与出库数量相同-调差应收
    List<SquareInvViewVO> l_Equal = new ArrayList<SquareInvViewVO>();
    // 发票累计结算数量与出库数量相同-调差应收(不需要联动计算的)
    List<SquareInvViewVO> l_EqualZeroMny = new ArrayList<SquareInvViewVO>();
    SquareInvViewVO[] sqviewvos =
        SquareInvVOUtils.getInstance().changeToSaleSquareViewVO(vos);
    for (SquareInvViewVO svo : sqviewvos) {
      // 销售发票上游销售出库单行id
      String srcoutbid = svo.getItem().getCsrcbid();
      // 销售发票上新增的无来源劳务折扣行
      if (PubAppTool.isNull(srcoutbid)) {
        l_noEqual.add(svo);
        continue;
      }
      // 上游出库单应收结算数据
      SquareOutDetailVO sqare4cvo = m_dvo.get(srcoutbid);
      // 销售发票非来源于销售出库单（参照销售订单服务费用行），直接传应收
      if (null == sqare4cvo) {
        l_noEqual.add(svo);
        continue;
      }
      UFDouble price32 =
          ValueUtils
              .getUFDouble(svo.getItem().getAttributeValue(this.priceKey));
      UFDouble price4C =
          ValueUtils.getUFDouble(sqare4cvo.getAttributeValue(this.priceKey));
      UFDouble[] invTotalNumMny = m4cbidnum.get(srcoutbid);
      // 出库单下游途损数量
      UFDouble wasNum =
          null == this.mWasNum || null == this.mWasNum.get(srcoutbid) ? UFDouble.ZERO_DBL
              : this.mWasNum.get(srcoutbid)[0].abs();
      // 出库单下游签收开票退回出库单的数量
      UFDouble returnNum =
          null == this.mOutNum || null == this.mOutNum.get(srcoutbid) ? UFDouble.ZERO_DBL
              : this.mOutNum.get(srcoutbid)[0].abs();
      // 出库数量-途损数量-签收开票退回出库单的数量
      UFDouble outwasreturnNum =
          MathTool.sub(sqare4cvo.getNsquarenum(),
              MathTool.add(wasNum, returnNum));

      // 发票累计结算数量 != 出库数量-途损数量-签收开票退回出库单的数量
      if (MathTool.compareTo(invTotalNumMny[0], outwasreturnNum) != 0) {
        // 金额传 （发票单价 - 销售出库单传应收单的单价）* 发票数量
        if (MathTool.compareTo(price32, price4C) != 0) {
          UFDouble mny = price32.sub(price4C).multiply(svo.getItem().getNnum());
          mny =
              ScaleUtils.getScaleUtilAtBS().adjustMnyScale(mny,
                  svo.getItem().getCorigcurrencyid());
          svo.getItem().setAttributeValue(this.mnyKey, mny);
          l_noEqual.add(svo);
        }
      }

      /**
       * 发票累计结算数量 = 出库数量-途损数量-签收开票退回出库单的数量:
       * 本次调整应收金额=本次结算金额-出库确认应收金额 + ∑已结算金额-∑已调整应收金额）
       * 已调整应收金额要包括：
       * 已经调差的金额+途损生成应收单金额+基于签收开票场景下退回生成应收单金额。
       **/
      else {
        // mny = 本次结算金额 + ∑已结算金额 - 出库确认应收金额
        // invTotalNumMny[1] = 本次结算金额 + ∑已结算金额
        UFDouble invTotalTaxMny = invTotalNumMny[1];
        UFDouble invTotalMny = invTotalNumMny[2];
        UFDouble taxmny = invTotalTaxMny.sub(sqare4cvo.getNorigtaxmny());
        UFDouble mny = invTotalMny.sub(sqare4cvo.getNorigmny());
        // 2013-07-微调税额时，无税金额或价税合计，只有某一种为0的情况
        if (!MathTool.equals(UFDouble.ZERO_DBL, taxmny)
            && MathTool.equals(UFDouble.ZERO_DBL, mny)
            || !MathTool.equals(UFDouble.ZERO_DBL, mny)
            && MathTool.equals(UFDouble.ZERO_DBL, taxmny)) {
          svo.getItem().setNorigtaxmny(taxmny);
          svo.getItem().setNorigmny(mny);
          svo.getItem().setNtaxmny(taxmny);
          svo.getItem().setNmny(mny);
          UFDouble tax =
              MathTool.equals(UFDouble.ZERO_DBL, taxmny) ? mny : taxmny;
          svo.getItem().setNtax(tax);
          l_EqualZeroMny.add(svo);
        }
        else {
          svo.getItem().setNorigtaxmny(taxmny);
          svo.getItem().setNorigmny(mny);
          l_Equal.add(svo);
        }
      }
    }

    // 发票累计结算数量与出库数量相同:处理已经调差的金额、途损和签收退回
    this.processEqual(l_Equal);

    // 发票累计结算数量与出库数量-途损-签收退回数量不相同
    this.processNoEqual(l_noEqual);
    // 发票累计结算数量与出库数量相同:处理已经调差的金额、途损和签收退回(调整税额，无税金额或价税合计为0的情况
    this.processEqualZeroMny(l_EqualZeroMny);
    // 合并一下
    l_Equal.addAll(l_EqualZeroMny);

    // 存货等数据项原样传递；数量传0；单价传空
    return this.setDataForAD(l_Equal, l_noEqual);

  }

  private void processEqualZeroMny(List<SquareInvViewVO> l_EqualZeroMny) {
    if (l_EqualZeroMny.size() == 0) {
      return;
    }
    SquareInvBVO[] bvos =
        SquareInvVOUtils.getInstance().getSquareInvBVO(
            l_EqualZeroMny.toArray(new SquareInvViewVO[l_EqualZeroMny.size()]));
    // 重新结算本币金额
    new PriceNumMnyCalculatorForVO().calculateByTax(bvos);
  }

  private void initADWasReturnOutData(SquareInvVO[] vos) {
    String[] outBids =
        AggVOUtil.getDistinctItemFieldArray(vos, SquareInvBVO.CSRCBID,
            String.class);
    // 查询出库单下游发票已经调差金额
    Map<String, UFDouble[]> mInvADMny1 =
        new SquareADQuery().queryTotalSquareADMnyBy4C(outBids);
    this.mInvADMny = mInvADMny1;

    // 查询下游途损单累计传应收数据
    ISquare4353Query qry53 =
        NCLocator.getInstance().lookup(ISquare4353Query.class);
    Map<String, UFDouble[]> mWasNum1 = qry53.queryARNumBy4CBID(outBids);
    this.mWasNum = mWasNum1;

    // 查询下游签收开票退回出库单累计传应收数据
    ISquare434CQuery qry4C =
        NCLocator.getInstance().lookup(ISquare434CQuery.class);
    Map<String, UFDouble[]> mOutNum1 = qry4C.queryARNumBy4CBID(outBids);
    this.mOutNum = mOutNum1;
  }

  /**
   * 方法功能描述：发票累计结算数量与出库数量相同
   * <p>
   * 本次调整应收金额=本次结算金额-出库确认应收金额 + ∑已结算金额-∑已调整应收金额。
   * <p>
   * 其中，已调整应收金额要包括：处理已经调差的金额+途损生成应收单金额+基于签收开票场景下退回生成应收单金额。
   * <p>
   * vo中的金额 = 本次结算金额-出库确认应收金额 + ∑已结算金额，此处只需要-∑已调整应收金额。 <b>examples:</b>
   * <p>
   * 使用示例
   * <p>
   * <b>参数说明</b>
   * 
   * @param l_Equal
   * @param m_4cdvo
   *          <p>
   * @author zhangcheng
   * @time 2010-9-7 下午07:24:44
   */
  private void processEqual(List<SquareInvViewVO> l_Equal) {
    if (l_Equal.size() > 0) {
      SquareInvViewVO[] svvos = l_Equal.toArray(new SquareInvViewVO[0]);
      if (!SOVOChecker.isEmpty(this.mInvADMny)
          || !SOVOChecker.isEmpty(this.mWasNum)
          || !SOVOChecker.isEmpty(this.mOutNum)) {
        for (SquareInvViewVO vvo : svvos) {
          String outBid = vvo.getItem().getCsrcbid();
          // admny = 途损生成应收单金额+基于签收开票场景下退回生成应收单金额。
          UFDouble adTaxMny = UFDouble.ZERO_DBL;
          UFDouble adMny = UFDouble.ZERO_DBL;
          if (!SOVOChecker.isEmpty(this.mInvADMny)) {
            UFDouble invADTaxMny = this.mInvADMny.get(outBid)[0];
            UFDouble invADMny = this.mInvADMny.get(outBid)[1];
            adTaxMny = adTaxMny.add(invADTaxMny);
            adMny = adMny.add(invADMny);
          }
          // 三行出库单 有可能只有其中的两行有途损
          if (!SOVOChecker.isEmpty(this.mWasNum)
              && this.mWasNum.containsKey(outBid)) {
            UFDouble wasTaxMny = this.mWasNum.get(outBid)[1];
            UFDouble wasMny = this.mWasNum.get(outBid)[2];
            adTaxMny = adTaxMny.add(wasTaxMny);
            adMny = adMny.add(wasMny);
          }
          if (!SOVOChecker.isEmpty(this.mOutNum)) {
            UFDouble outTaxMny = this.mOutNum.get(outBid)[1];
            UFDouble outMny = this.mOutNum.get(outBid)[2];
            adTaxMny = adTaxMny.add(outTaxMny);
            adMny = adMny.add(outMny);
          }
          vvo.getItem().setNorigtaxmny(
              MathTool.sub(vvo.getItem().getNorigtaxmny(), adTaxMny));
          vvo.getItem().setNorigmny(
              MathTool.sub(vvo.getItem().getNorigmny(), adMny));
        }
      } // end if
      // 带尾差处理的联动计算
      this.calculateWithMargin(svvos);

    }
  }

  /**
   * 带尾差处理的联动计算
   * 
   * @param svvos
   */
  private void calculateWithMargin(SquareInvViewVO[] svvos) {
    SquareInvBVO[] bvos = SquareInvVOUtils.getInstance().getSquareInvBVO(svvos);
    Map<String, UFDouble[]> sameCurBvo = new HashMap<String, UFDouble[]>();
    for (SquareInvBVO item : bvos) {
      UFDouble[] mny = new UFDouble[2];
      mny[0] = item.getNorigtaxmny();
      mny[1] = item.getNorigmny();
      sameCurBvo.put(item.getCsquarebillbid(), mny);
    }
    // 重新结算本币金额
    new PriceNumMnyCalculatorForVO().calculateLocal(bvos);
    // 原本币一致的情况，处理一下倒挤
    for (SquareInvBVO item : bvos) {
      if (item.getCorigcurrencyid() != null
          && item.getCorigcurrencyid().equals(item.getCcurrencyid())) {
        UFDouble[] mny = sameCurBvo.get(item.getCsquarebillbid());
        if (mny != null) {
          item.setNorigtaxmny(mny[0]);
          item.setNorigmny(mny[1]);
          item.setNtaxmny(mny[0]);
          item.setNmny(mny[1]);
          item.setNtax(MathTool.sub(mny[0], mny[1]));
        }
      }
    }
  }

  private void processNoEqual(List<SquareInvViewVO> l_noEqual) {
    if (l_noEqual.size() > 0) {
      SquareInvViewVO[] svvos = l_noEqual.toArray(new SquareInvViewVO[0]);
      SquareInvBVO[] bvos =
          SquareInvVOUtils.getInstance().getSquareInvBVO(svvos);
      // 金额重新计算
      new PriceNumMnyCalculatorForVO().calculate(bvos, this.mnyKey);
    }
  }

  /**
   * 方法功能描述：查询上游出库单应收结算数据
   * <p>
   * <出库单行id,结算明细VO>
   * <p>
   * <b>参数说明</b>
   * 
   * @param vos
   * @return <出库单行id,结算明细VO>
   *         <p>
   * @author zhangcheng
   * @time 2010-7-1 下午07:02:45
   */
  private Map<String, SquareOutDetailVO> queryUpARSquareOutVO(SquareInvVO[] vos) {
    // 销售发票来源表体id
    String[] icbids =
        AggVOUtil.getDistinctItemFieldArray(vos, SquareInvBVO.CSRCBID,
            String.class);
    SquareOutDetailVO[] sdvos =
        new QuerySquare4CVOBP().querySquareOutDetailVOBy4CBID(icbids);
    List<SquareOutDetailVO> listvo = new ArrayList<SquareOutDetailVO>();
    if (!ArrayUtils.isEmpty(sdvos)) {
      for (SquareOutDetailVO vo : sdvos) {
        if (SquareType.SQUARETYPE_AR.getIntegerValue().equals(
            vo.getFsquaretype())) {
          listvo.add(vo);
        }
      }
    }
    // <出库单行id,结算明细VO>
    Map<String, SquareOutDetailVO> m_dvo =
        new HashMap<String, SquareOutDetailVO>();
    if (listvo.size() > 0) {
      sdvos = listvo.toArray(new SquareOutDetailVO[listvo.size()]);
      for (SquareOutDetailVO dvo : sdvos) {
        m_dvo.put(dvo.getCsquarebillbid(), dvo);
      }
    }
    return m_dvo;
  }

  /**
   * 存货等数据项原样传递；数量传0；单价传空
   */
  private SquareInvVO[] setDataForAD(List<SquareInvViewVO> l_Equal,
      List<SquareInvViewVO> l_noEqual) {
    // 合并
    l_Equal.addAll(l_noEqual);
    SquareInvVO[] retvos =
        SquareInvVOUtils.getInstance().combineBill(
            l_Equal.toArray(new SquareInvViewVO[0]));

    if (SOVOChecker.isEmpty(retvos)) {
      return null;
    }

    List<SquareInvViewVO> list = new LinkedList<SquareInvViewVO>();
    SquareInvViewVO[] views =
        SquareInvVOUtils.getInstance().changeToSaleSquareViewVO(retvos);
    for (SquareInvViewVO view : views) {
      SquareInvBVO bvo = view.getItem();
      UFDouble norigtaxmny = bvo.getNorigtaxmny();
      UFDouble norigmny = bvo.getNorigmny();
      // 2013-07-20 调整税额时，存在一种金额为空的情况
      if (!(MathTool.isZero(norigtaxmny) && MathTool.isZero(norigmny))) {
        list.add(view);
      }
    }

    int size = list.size();
    if (size > 0) {
      SquareInvViewVO[] retviews = list.toArray(new SquareInvViewVO[size]);
      SquareInvVO[] rets = SquareInvVOUtils.getInstance().combineBill(retviews);
      for (SquareInvVO svo : rets) {
        for (SquareInvBVO bvo : svo.getChildrenVO()) {
          bvo.setDifftoarsquarenum(bvo.getNthisnum());
          bvo.setNthisnum(UFDouble.ZERO_DBL);
          bvo.setNorignetprice(null);
          bvo.setNorigprice(null);
          bvo.setNorigtaxnetprice(null);
          bvo.setNorigtaxprice(null);
          bvo.setNprice(null);
          bvo.setNnetprice(null);
          bvo.setNtaxprice(null);
          bvo.setNtaxnetprice(null);
        }
      }
      return rets;
    }
    return null;
  }

  /**
   * 方法功能描述：根据含税优先机制确定单价字段
   */
  private void setKey() {
    // 根据含税优先机制确定单价字段
    this.bTaxPrior =
        SOSysParaInitUtil.getSO23(AppBsContext.getInstance().getPkGroup())
            .booleanValue();
    this.priceKey =
        this.bTaxPrior ? SquareInvBVO.NORIGTAXNETPRICE
            : SquareInvBVO.NORIGNETPRICE;
    this.mnyKey =
        this.bTaxPrior ? SquareInvBVO.NORIGTAXMNY : SquareInvBVO.NORIGMNY;
  }

}
