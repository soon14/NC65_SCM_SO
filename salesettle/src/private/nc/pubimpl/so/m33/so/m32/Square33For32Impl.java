package nc.pubimpl.so.m33.so.m32;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import nc.vo.pub.BusinessException;
import nc.vo.pubapp.pattern.exception.ExceptionUtils;
import nc.vo.pubapp.pattern.log.TimeLog;
import nc.vo.pubapp.pattern.pub.MathTool;
import nc.vo.pubapp.pattern.pub.SqlBuilder;
import nc.vo.scmpub.res.billaction.SOBillAction;
import nc.vo.scmpub.res.billtype.SOBillType;
import nc.vo.so.m32.entity.SaleInvoiceVO;
import nc.vo.so.m33.enumeration.SquareType;
import nc.vo.so.m33.m32.entity.SquareInvBVO;
import nc.vo.so.m33.m32.entity.SquareInvDetailVO;
import nc.vo.so.m33.m32.entity.SquareInvVO;
import nc.vo.so.m33.m4c.entity.SquareOutVO;
import nc.vo.so.pub.SOTable;
import nc.vo.so.pub.util.AggVOUtil;
import nc.vo.so.pub.votools.SoVoTools;

import nc.itf.scmpub.reference.uap.pf.PfServiceScmUtil;

import nc.pubitf.so.m33.so.m32.ISquare33For32;

import nc.bs.so.m33.biz.m32.bp.cancelsquare.CancelSquareInvDetailBP;
import nc.bs.so.m33.biz.m32.bp.cancelsquare.cancelar.CancelARIncomeFor32BP;
import nc.bs.so.m33.biz.m32.bp.cancelsquare.cancelar.CancelARRushIncomeFor32BP;
import nc.bs.so.m33.biz.m32.bp.cancelsquare.cancelia.CancelIACostFor32BP;
import nc.bs.so.m33.biz.m32.bp.cancelsquare.cancelia.CancelIARegisterCreditFor32BP;
import nc.bs.so.m33.biz.m32.bp.check.SquareInvoiceCheckBP;
import nc.bs.so.m33.biz.pub.cancelsquare.AbstractCancelSquareDetail;
import nc.bs.so.m33.maintain.m32.DeleteSquare32BP;
import nc.bs.so.m33.maintain.m32.InsertSquare32BP;
import nc.bs.so.m33.maintain.m32.query.QuerySquare32VOBP;
import nc.bs.so.m33.plugin.ServicePlugInPoint;
import nc.bs.so.m33.pub.CheckSquareBiz;

import nc.impl.pubapp.env.BSContext;
import nc.impl.pubapp.pattern.data.bill.tool.BillConcurrentTool;
import nc.impl.pubapp.pattern.data.vo.VOQuery;
import nc.impl.pubapp.pattern.data.vo.tool.VOConcurrentTool;
import nc.impl.pubapp.pattern.database.IDExQueryBuilder;
import nc.impl.pubapp.pattern.rule.processer.AroundProcesser;

/**
 * 销售结算提供给销售发票服务接口实现类
 * 
 * @since 6.1
 * @version 2012-11-29 11:11:45
 * @author 冯加彬
 */
public class Square33For32Impl implements ISquare33For32 {

  @Override
  public void cancelSquareSrv(SaleInvoiceVO[] voInvoice)
      throws BusinessException {
    try {
      // 加锁
      BillConcurrentTool tool = new BillConcurrentTool();
      tool.lockBill(voInvoice);

      // 查询结算单数据
      SquareInvVO[] sqvos =
          new QuerySquare32VOBP().querySquareInvVOBy32ID(SoVoTools
              .getVOPKValues(voInvoice));
      if (sqvos == null || sqvos.length == 0) {
        return;
      }

      // 如果上游出库单部分暂估、发出商品，则发票不可取消结算
      new SquareInvoiceCheckBP().checkETREGForCancelSquare(sqvos);

      // 对发票待结算单加锁
      tool.lockBill(sqvos);

      // 取消结算单
      this.cancelSquareDetail(sqvos);

      // 取消待结算单
      new DeleteSquare32BP().delete(sqvos);

    }
    catch (Exception e) {
      ExceptionUtils.marsh(e);
    }
  }

  @Override
  public void pushSquareSrv(SaleInvoiceVO[] voInvoice) throws BusinessException {
    try {
      // 加锁
      BillConcurrentTool tool = new BillConcurrentTool();
      tool.lockBill(voInvoice);

      // 如果没有配置结算动作直接跳出
      TimeLog.logStart();

      boolean hasSquareBiz =
          new CheckSquareBiz().ifHasSquareAction(voInvoice[0].getParentVO()
              .getVtrantypecode(), voInvoice[0].getParentVO().getCbiztypeid(),
              SOBillAction.SaleInvoiceApprove.getCode());
      if (!hasSquareBiz) {
        return;
      }

      TimeLog.info(nc.vo.ml.NCLangRes4VoTransl.getNCLangRes().getStrByID(
          "4006010_0", "04006010-0063")/*@res "判断发票是否有结算动作"*/);

      // 32到33的VO对照
      TimeLog.logStart();
      SquareInvVO[] sqvos =
          PfServiceScmUtil.executeVOChange(SOBillType.Invoice.getCode(),
              SOBillType.SquareInvoice.getCode(), voInvoice);

      TimeLog.info(nc.vo.ml.NCLangRes4VoTransl.getNCLangRes().getStrByID(
          "4006010_0", "04006010-0064")/*@res "进行32到33的VO对照"*/);

      TimeLog.logStart();
      AroundProcesser<SquareInvVO> processer =
          new AroundProcesser<SquareInvVO>(ServicePlugInPoint.Push33For32);

      processer.before(sqvos);

      TimeLog.info(nc.vo.ml.NCLangRes4VoTransl.getNCLangRes().getStrByID(
          "4006010_0", "04006010-0017")/*@res "调用结算单保存BP前规则"*/);

      // 结算单保存
      TimeLog.logStart();
      new InsertSquare32BP().insert(sqvos);

      TimeLog.info(nc.vo.ml.NCLangRes4VoTransl.getNCLangRes().getStrByID(
          "4006010_0", "04006010-0018")/*@res "调用结算单新增保存BP"*/);

      TimeLog.logStart();
      processer.after(sqvos);

      TimeLog.info(nc.vo.ml.NCLangRes4VoTransl.getNCLangRes().getStrByID(
          "4006010_0", "04006010-0019")/*@res "调用结算单保存BP后规则"*/);

    }
    catch (Exception e) {
      ExceptionUtils.marsh(e);
    }

  }

  private void cancelSquareDetail(
      Map<SquareType, List<SquareInvDetailVO>> m_sqDetailVo) {
    // 取消回冲
    new CancelARRushIncomeFor32BP().cancelSquare(m_sqDetailVo
        .get(SquareType.SQUARETYPE_ARRUSH));

    // 取消确认
    new CancelARIncomeFor32BP().cancelSquare(m_sqDetailVo
        .get(SquareType.SQUARETYPE_AR));

    // 取消成本
    new CancelIACostFor32BP().cancelSquare(m_sqDetailVo
        .get(SquareType.SQUARETYPE_IA));

    // 取消发出商品
    new CancelIARegisterCreditFor32BP().cancelSquare(m_sqDetailVo
        .get(SquareType.SQUARETYPE_REG_CREDIT));
  }

  /**
   * 方法功能描述：取消结算单
   * <p>
   * <b>examples:</b>
   * <p>
   * 使用示例
   * <p>
   * <b>参数说明</b>
   * 
   * @param sqvos
   *          <p>
   * @author zhangcheng
   * @time 2010-8-18 下午03:05:27
   */
  private void cancelSquareDetail(SquareInvVO[] sqvos) {
    // 结算明细数据
    SquareInvDetailVO[] sqdvos =
        new QuerySquare32VOBP().querySquareInvDetailVOBySQHID(AggVOUtil
            .getDistinctItemFieldArray(sqvos, SquareInvDetailVO.CSALESQUAREID,
                String.class));

    if (sqdvos != null) {
      // 加锁
      new VOConcurrentTool().lock(sqdvos);

      // 设置本次取消结算数量,由于发票都是整单原则，故直接用带结算单数量取反
      for (SquareInvVO svo : sqvos) {
        for (SquareInvBVO bvo : svo.getChildrenVO()) {
          bvo.setNthisnum(MathTool.oppose(bvo.getNnum()));
          bvo.setNorigtaxmny(MathTool.oppose(bvo.getNorigtaxmny()));
        }
      }
      for (SquareInvDetailVO dvo : sqdvos) {
        dvo.setNsquarenum(MathTool.oppose(dvo.getNsquarenum()));
        dvo.setNorigtaxmny(MathTool.oppose(dvo.getNorigtaxmny()));
      }

      // 将销售出库待结算单缓存
      BSContext.getInstance().setSession(SquareInvVO.class.getName(), sqvos);

      // 结算单数据分类
      AbstractCancelSquareDetail<SquareInvDetailVO> caction =
          new CancelSquareInvDetailBP();
      Map<SquareType, List<SquareInvDetailVO>> m_sqDetailVo =
          caction.splitVOBySquareType(sqdvos, SquareInvDetailVO.FSQUARETYPE);
      // caction.cancelSquare(sqdvos, SquareInvDetailVO.FSQUARETYPE);

      // 取消结算
      this.cancelSquareDetail(m_sqDetailVo);

      // 释放缓存
      BSContext.getInstance().removeSession(SquareOutVO.class.getName());
    }
  }

  @Override
  public Map<String, String> queryInvSquareDetail(String[] invhids,
      String[] invbids) throws BusinessException {
    String[] keys = new String[] {
      SquareInvDetailVO.CSQUAREBILLBID, SquareInvDetailVO.CSALESQUAREDID
    };
    SqlBuilder wheresql = new SqlBuilder();
    wheresql.append(" and ");
    IDExQueryBuilder idqb = new IDExQueryBuilder(SOTable.TMP_SO_ID1.getName());
    String where = idqb.buildSQL(SquareInvDetailVO.CSQUAREBILLID, invhids);
    wheresql.append(where);

    idqb = new IDExQueryBuilder(SOTable.TMP_SO_ID2.getName());
    where = idqb.buildSQL(SquareInvDetailVO.CSQUAREBILLBID, invbids);
    wheresql.append("and  " + where);

    VOQuery<SquareInvDetailVO> qrysrv =
        new VOQuery<SquareInvDetailVO>(SquareInvDetailVO.class, keys);
    SquareInvDetailVO[] detailvos = qrysrv.query(wheresql.toString(), null);
    if (null == detailvos || detailvos.length == 0) {
      return new HashMap<String, String>();
    }
    Map<String, String> mapdet = new HashMap<String, String>();
    for (SquareInvDetailVO detvo : detailvos) {
      mapdet.put(detvo.getCsalesquaredid(), detvo.getCsquarebillbid());
    }
    return mapdet;

  }

}
