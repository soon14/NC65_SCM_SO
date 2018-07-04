package nc.pubimpl.so.m33.self.m4332;

import java.util.HashMap;
import java.util.Map;

import nc.bs.so.m33.maintain.m4c.query.QuerySquare4CVOBP;
import nc.impl.pubapp.pattern.data.view.ViewUpdate;
import nc.impl.pubapp.pattern.pub.LockOperator;
import nc.pubitf.so.m33.self.m4332.IRewrite434CFor4332;
import nc.pubitf.so.m33.self.m4332.Rewrite434CPara;
import nc.vo.pub.BusinessException;
import nc.vo.pub.lang.UFDouble;
import nc.vo.pubapp.pattern.data.ValueUtils;
import nc.vo.pubapp.pattern.exception.ExceptionUtils;
import nc.vo.pubapp.pattern.log.TimeLog;
import nc.vo.pubapp.pattern.pub.MathTool;
import nc.vo.so.m33.m4c.entity.SquareOutBVO;
import nc.vo.so.m33.m4c.entity.SquareOutViewVO;
import nc.vo.trade.checkrule.VOChecker;

public class Rewrite434CFor4332Impl implements IRewrite434CFor4332 {

  @Override
  public void rewrite434CDownARNumMnyFor4332(Rewrite434CPara[] paras)
      throws BusinessException {
    try {
      this.rewrite(paras, new String[] {
        SquareOutBVO.NDOWNARNUM, SquareOutBVO.NDOWNARMNY
      });
    }
    catch (RuntimeException ex) {
      ExceptionUtils.marsh(ex);
    }
  }

  @Override
  public void rewrite434CDownIANumFor4332(Rewrite434CPara[] paras)
      throws BusinessException {
    try {
      this.rewrite(paras, new String[] {
        SquareOutBVO.NDOWNIANUM
      });
    }
    catch (RuntimeException ex) {

      ExceptionUtils.marsh(ex);
    }
  }

  private String[] lockBills(Map<String, Rewrite434CPara> index) {
    int size = index.size();
    String[] bids = new String[size];
    bids = index.keySet().toArray(bids);
    LockOperator locker = new LockOperator();
    String message =
        nc.vo.ml.NCLangRes4VoTransl.getNCLangRes().getStrByID("4006010_0",
            "04006010-0059")/*@res "销售发票结算回写销售出库单累计下游确认应收数量，锁销售出库待结算单表体失败"*/;
    locker.lock(bids, message);
    return bids;
  }

  private Map<String, Rewrite434CPara> prepareParams(Rewrite434CPara[] paras) {
    Map<String, Rewrite434CPara> index = new HashMap<String, Rewrite434CPara>();
    for (Rewrite434CPara para : paras) {
      index.put(para.getCsaleorderbid(), para);
    }
    return index;
  }

  private SquareOutViewVO[] query(Map<String, Rewrite434CPara> index) {
    String[] ids = this.lockBills(index);
    SquareOutViewVO[] views =
        new QuerySquare4CVOBP().querySquareOutViewVOBy4CBID(ids);
    if (VOChecker.isEmpty(views)) {
      String message =
          nc.vo.ml.NCLangRes4VoTransl.getNCLangRes().getStrByID("4006010_0",
              "04006010-0060")/*@res "数据错误：销售出库单签字没有生成销售出库待结算单！"*/;

      ExceptionUtils.wrappBusinessException(message);
    }
    if (views.length != index.size()) {
      String message =
          nc.vo.ml.NCLangRes4VoTransl.getNCLangRes().getStrByID("4006010_0",
              "04006010-0061")/*@res "出现并发，请重新查询销售发票"*/;

      ExceptionUtils.wrappBusinessException(message);
    }
    return views;
  }

  private void rewrite(Rewrite434CPara[] paras, String[] keys) {
    TimeLog.logStart();
    Map<String, Rewrite434CPara> index = this.prepareParams(paras);
    TimeLog.info("并处理参数"); /*-=notranslate=-*/

    TimeLog.logStart();
    SquareOutViewVO[] views = this.query(index);
    TimeLog.info("查询销售出库待结算单表体"); /*-=notranslate=-*/

    this.setUpdateNum(views, index, keys);

    TimeLog.logStart();
    ViewUpdate<SquareOutViewVO> bo = new ViewUpdate<SquareOutViewVO>();
    bo.update(views, SquareOutBVO.class, keys);
    TimeLog.info("更新数据库"); /*-=notranslate=-*/
  }

  private void setUpdateNum(SquareOutViewVO[] vos,
      Map<String, Rewrite434CPara> index, String[] keys) {
    UFDouble ntotaloldvalue;
    SquareOutBVO body = null;
    for (SquareOutViewVO vo : vos) {
      body = vo.getItem();
      Rewrite434CPara para = index.get(body.getCsquarebillbid());
      for (String key : keys) {
        ntotaloldvalue = ValueUtils.getUFDouble(body.getAttributeValue(key));
        UFDouble nnewvalue = null;
        if (SquareOutBVO.NDOWNARNUM.equals(key)
            || SquareOutBVO.NDOWNIANUM.equals(key)) {
          nnewvalue = para.getNarnum();
        }
        else {
          nnewvalue = para.getNarmny();
        }
        body.setAttributeValue(key, MathTool.add(ntotaloldvalue, nnewvalue));
      }
    }
  }

}
