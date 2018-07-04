package nc.pubimpl.so.m30.pu.m21.rule;

import java.util.ArrayList;
import java.util.List;

import nc.impl.pubapp.pattern.data.vo.VOQuery;
import nc.impl.pubapp.pattern.data.vo.VOUpdate;
import nc.impl.pubapp.pattern.pub.LockOperator;
import nc.vo.pub.BusinessException;
import nc.vo.pub.lang.UFBoolean;
import nc.vo.pubapp.pattern.exception.ExceptionUtils;
import nc.vo.pubapp.pattern.log.TimeLog;
import nc.vo.so.m30.entity.SaleOrderHVO;
import nc.vo.trade.checkrule.VOChecker;

/**
 * 协同销售订单生成采购订单，采购订单删除时更新销售订单
 *
 * @since 6.0
 * @version 2011-3-21 上午09:33:19
 * @author 祝会征
 */
public class Renovate30For21Rule {

  public void renovate30For21(String[] ids) throws BusinessException {
    try {
      List<String> idlist = new ArrayList<String>();
      for (String id : ids) {
        if (id != null) {
          idlist.add(id);
        }
      }
      this.renovate(idlist.toArray(new String[0]));
    }
    catch (Exception ex) {
      ExceptionUtils.marsh(ex);
    }
  }

  /**
   * 给销售订单加锁
   *
   * @param ids
   */
  private void lockBills(String[] ids) {
    LockOperator locker = new LockOperator();
    String message =
        nc.vo.ml.NCLangRes4VoTransl.getNCLangRes().getStrByID("4006011_0",
            "04006011-0183")/* @res "采购订单会写销售订单，给销售订单加锁失败。" */;
    locker.lock(ids, message);
  }

  /**
   * 根据来源销售订单id查询销售订单信息
   *
   * @param codeMap
   * @return
   */
  private SaleOrderHVO[] query(String[] ids) {

    this.lockBills(ids);
    VOQuery<SaleOrderHVO> query = new VOQuery<SaleOrderHVO>(SaleOrderHVO.class);
    SaleOrderHVO[] hvos = query.query(ids);
    if (VOChecker.isEmpty(hvos) || (hvos.length != ids.length)) {
      ExceptionUtils.wrappBusinessException(nc.vo.ml.NCLangRes4VoTransl
          .getNCLangRes().getStrByID("4006011_0", "04006011-0184")/*
                                                                   * @res
                                                                   * "找不到采购订单对应的销售订单，回写失败。"
                                                                   */);
    }
    for (SaleOrderHVO hvo : hvos) {
      hvo.setVcooppohcode(null);
      hvo.setBcooptopoflag(UFBoolean.FALSE);
    }
    return hvos;
  }

  private void renovate(String[] ids) {
    TimeLog.logStart();
    SaleOrderHVO[] hvos = this.query(ids);
    TimeLog.info("查询销售订单信息"); /* -=notranslate=- */
    TimeLog.logStart();
    String[] names = new String[] {
      SaleOrderHVO.BCOOPTOPOFLAG, SaleOrderHVO.VCOOPPOHCODE
    };
    VOUpdate<SaleOrderHVO> update = new VOUpdate<SaleOrderHVO>();
    update.update(hvos, names);
    TimeLog.info("更新数据库"); /* -=notranslate=- */
  }
}
