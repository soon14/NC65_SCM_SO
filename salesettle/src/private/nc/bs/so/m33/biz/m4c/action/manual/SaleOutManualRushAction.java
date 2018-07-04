package nc.bs.so.m33.biz.m4c.action.manual;

import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;

import nc.bs.so.m33.biz.m4c.action.outrush.CancelOutRushFor4CAction;
import nc.bs.so.m33.biz.m4c.action.outrush.OutRushFor4CAction;
import nc.bs.so.m33.maintain.m4c.query.QuerySquare4CVOBizBP;
import nc.vo.pub.lang.UFDouble;
import nc.vo.pubapp.pattern.exception.ExceptionUtils;
import nc.vo.pubapp.pattern.pub.MathTool;
import nc.vo.so.m33.m4c.entity.SquareOutBVO;
import nc.vo.so.m33.m4c.entity.SquareOutDetailVO;
import nc.vo.so.m33.m4c.entity.SquareOutVOUtils;
import nc.vo.so.m33.m4c.entity.SquareOutViewVO;

public class SaleOutManualRushAction {

  public void manualOutRush(SquareOutViewVO[] bluevos, SquareOutViewVO[] redvos) {

    // 补充数据
    SquareOutVOUtils.getInstance().fill4CIDPkOrgToHead(bluevos);
    SquareOutVOUtils.getInstance().fill4CIDPkOrgToHead(redvos);

    // 出库对冲
    new OutRushFor4CAction().execOutRush(bluevos, redvos);

  }

  public void manualUnOutRush(SquareOutViewVO[] vos) {
    // 补充数据
    SquareOutVOUtils.getInstance().fill4CIDPkOrgToHead(vos);

    // 是否可以取消出库对冲
    checkETREGForCancelOutRush(vos);
    
    // 处理蓝字出库单取消出库对冲
    if (vos[0].getItem().getNnum().compareTo(UFDouble.ZERO_DBL) > 0) {
      new CancelOutRushFor4CAction().cancelBlueOutRush(vos);
    }
    // 处理红字出库单取消出库对冲
    else {
      new CancelOutRushFor4CAction().cancelRedOutRush(vos);
    }
  }
  
  /**
   * 如果出库单部分暂估、发出商品，则禁止取消出库对冲
   * @param vos
   */
  public void checkETREGForCancelOutRush(SquareOutViewVO[] vos){
    List<String> list = new LinkedList<String>();
    for(SquareOutViewVO view : vos){
      list.add(view.getItem().getCsalesquarebid());
    }
    String[] bids = list.toArray(new String[list.size()]);
    // 查询传发出商品贷方的销售出库结算单
    SquareOutDetailVO[] sdvos = new QuerySquare4CVOBizBP().queryREGCreditSquareOutDetailVOBySQBID(bids);
    Map<String,SquareOutDetailVO> mapreg = new HashMap<String,SquareOutDetailVO>();
    for(SquareOutDetailVO dvo : sdvos){
      mapreg.put(dvo.getCsalesquarebid(), dvo);
    }
    
    for (SquareOutViewVO view : vos){
      SquareOutBVO bvo = view.getItem();
      UFDouble nestnum = bvo.getNsquareestnum();
      UFDouble nrushnum = bvo.getNarrushnum();
      UFDouble nregnum = bvo.getNsquareregnum();
      // 出库单已经暂估应收，没有传过回冲应收
      if (!MathTool.isZero(nestnum) && MathTool.isZero(nrushnum)){
       // ExceptionUtils.wrappBusinessException("上游销售出库单做过暂估应收，则禁止对没有回冲应收的销售出库单取消出库对冲");
        ExceptionUtils.wrappBusinessException(nc.vo.ml.NCLangRes4VoTransl.getNCLangRes().getStrByID("4006010_0","04006010-0143")/*@res "上游销售出库单做过暂估应收，则禁止对没有回冲应收的销售出库单取消出库对冲"*/);
      }
      String sqbid = view.getItem().getCsalesquarebid();
      if (!MathTool.isZero(nregnum) && mapreg.get(sqbid)==null){
       // ExceptionUtils.wrappBusinessException("上游销售出库单做过发出商品借方，则禁止对没有发出商品贷方的销售出库单取消出库对冲");
        ExceptionUtils.wrappBusinessException(nc.vo.ml.NCLangRes4VoTransl.getNCLangRes().getStrByID("4006010_0","04006010-0144")/*@res "上游销售出库单做过发出商品借方，则禁止对没有发出商品贷方的销售出库单取消出库对冲"*/);     
      }
    }
  }
}
