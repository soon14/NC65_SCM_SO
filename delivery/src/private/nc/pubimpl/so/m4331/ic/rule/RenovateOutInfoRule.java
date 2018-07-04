package nc.pubimpl.so.m4331.ic.rule;

import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Map.Entry;
import java.util.Set;

import nc.bs.so.m4331.assist.state.DeliveryStateMachine;
import nc.bs.so.m4331.assist.state.row.RowCloseState;
import nc.bs.so.m4331.assist.state.row.RowOpenState;
import nc.impl.pubapp.pattern.data.vo.VOUpdate;
import nc.pubimpl.so.m4331.pub.RewriteSrcUtil;
import nc.pubimpl.so.m4331.pub.RewriteVOUtil;
import nc.pubimpl.so.m4331.pub.RewriteValueUtil;
import nc.pubimpl.so.m4331.pub.StateTool;
import nc.vo.pub.lang.UFBoolean;
import nc.vo.pub.lang.UFDouble;
import nc.vo.pubapp.pattern.pub.MathTool;
import nc.vo.so.m4331.entity.DeliveryCheckVO;
import nc.vo.so.m4331.entity.DeliveryViewVO;

/**
 * 更新发货单出库信息 出库状态和累计出库数量
 * 
 * @since 6.0
 * @version 2011-2-18 上午09:32:48
 * @author 祝会征
 */
public class RenovateOutInfoRule {
  // 缓存要关闭的发货单
  private Set<DeliveryViewVO> closeSet;

  // 缓存要打开的发货单
  private Set<DeliveryViewVO> openSet;

  // 缓存发货单回写来源单据的变化量
  private Map<String, UFDouble> reSrcValueMap;

  // 缓存下游回写发货单后的出库状态
  private Map<String, UFBoolean> stateMap;

  // 缓存下游回写发货单的变化量
  private Map<String, UFDouble> valueMap;

  // 数据信息工具类
  private RewriteValueUtil valueUtil;

  // 回写vo工具类
  private RewriteVOUtil voutil;

  public RenovateOutInfoRule(RewriteVOUtil util,RewriteValueUtil valueutil) {
    this.voutil = util;
    if(valueutil==null){
      this.valueUtil = new RewriteValueUtil();
    }
    else{
      this.valueUtil = valueutil;
    }
    this.initMap();
  }

  public void renovateState() {
    // 更新质检相应信息
    this.manageDeliverycheckInfo();
    // 更新发货单表体相应信息
    this.manageDeliveryInfo();
    // 回写来源单据
    this.rewriteSrc();
    // 更新到数据库
    //this.updateToDB();
    // 更新质检信息到数据库
    this.updateQualityInfoToDB();
  }

  /**
   * 根据质检信息获得质检信息所对应的发货单表体的出库状态
   * 
   * @param entry
   * @param view
   * @return
   */
  private UFBoolean getViewStateByQualityInfo(Entry<String, UFBoolean> entry,
      DeliveryViewVO view) {
    String bid = entry.getKey();
    UFBoolean state = entry.getValue();
    UFBoolean bcloseflag = UFBoolean.FALSE;
    // 只要有打开的质检信息 则质检信息对应的发货单表体比为打开
    if (!state.booleanValue()
        || !this.voutil.getOtherVOState().get(bid).booleanValue()) {
      bcloseflag = UFBoolean.FALSE;
    }
    else if (!this.voutil.getOtherVOState().containsKey(bid)) {
      // 回写质检信息的个数和根据发货单表体id查询出来的质检信息个数是一致
      bcloseflag = state;
    }
    else {
      bcloseflag = UFBoolean.TRUE;
    }
    this.setReNumByQualityInfo(view, bcloseflag);
    return bcloseflag;
  }

  /*
   * 初始化初始化map缓存
   */
  private void initMap() {
    // 初始化质检信息id所对应的变化量
    this.initQualityValueMap();
    StateTool tool = new StateTool(this.voutil, this.valueUtil);
    DeliveryCheckVO[] vos = this.voutil.getRewriteCheckVO();
    DeliveryViewVO[] views = this.voutil.getRewriteViewVO();
    this.stateMap = tool.getState(vos, views);
    this.reSrcValueMap = tool.getValueForRewriteSrc(vos, views);
    this.openSet = new HashSet<DeliveryViewVO>();
    this.closeSet = new HashSet<DeliveryViewVO>();
  }

  /*
   * 初始化质检信息id所对应的变化量
   */
  private void initQualityValueMap() {
    DeliveryCheckVO[] vos = this.voutil.getRewriteCheckVO();
    if (null == vos) {
      return;
    }
    this.valueMap = new HashMap<String, UFDouble>();
    for (DeliveryCheckVO vo : vos) {
      String bid = vo.getCdeliverybid();
      if ((this.valueMap.size() > 0) && this.valueMap.containsKey(bid)) {
        continue;
      }
      DeliveryViewVO view = this.voutil.getRewriteViewVOOnCheck().get(bid);
      String srctype = view.getItem().getVsrctype();
      UFDouble totalRenum = new UFDouble(0.0);
      for (DeliveryCheckVO newvo : vos) {
        String newbid = newvo.getCdeliverybid();
        if (newbid.equals(bid)) {
          String newcid = newvo.getCdeliverycid();
          UFDouble newReNum = this.valueUtil.getRewriteNum(newcid, srctype);
          totalRenum = MathTool.add(totalRenum, newReNum);
        }
      }
      this.valueMap.put(bid, totalRenum);
    }
  }

  /*
   * 处理发货单质检信息
   * 更新出库状态和更新累计出库数量 
   */
  private void manageDeliverycheckInfo() {
    if (null == this.voutil.getRewriteCheckVO()) {
      return;
    }
    // 更新出库状态
    this.updateQualityInfo();
  }

  private void manageDeliveryInfo() {
    DeliveryViewVO[] views = this.voutil.getRewriteViewVO();
    if (null == views) {
      return;
    }
    this.updateViewsInfo();
  }

  /**
   * 回写来源单据
   */
  private void rewriteSrc() {
    RewriteSrcUtil rewrite = new RewriteSrcUtil(this.voutil);
    rewrite.rewriteSrc(this.reSrcValueMap);
  }

  /*
   * 更具质检信息 设置回写来源单据的变化量
   * @param view 
   * @param state 质检信息对应的发货单表体最新的出库状态
   */
  private void setReNumByQualityInfo(DeliveryViewVO view, UFBoolean state) {
    String bid = view.getItem().getCdeliverybid();
    UFDouble num = view.getItem().getNnum();
    UFDouble totalReNum = this.valueMap.get(bid);
    UFDouble oldTotalOutNum = view.getItem().getNtotaloutnum();
    UFDouble newTotalOutNum = MathTool.add(oldTotalOutNum, totalReNum);
    UFBoolean oldState = view.getItem().getBoutendflag();
    boolean expr1 = oldState.booleanValue();
    boolean expr2 = state.booleanValue();
    UFDouble reValueForSrc = UFDouble.ZERO_DBL;
    if (!expr1 && !expr2) {
      // 改变前状态是打开，改变后状态为打开 则不需要回写来源单据
      reValueForSrc = UFDouble.ZERO_DBL;
    }
    else if (!expr1 && expr2) {
      // 改变状态是打开,改变后状态是关闭
      reValueForSrc = MathTool.sub(newTotalOutNum, num);
    }
    else if (expr1 && !expr2) {
      // 改变前是关闭 改变后为打开
      reValueForSrc = MathTool.sub(num, oldTotalOutNum);
    }
    else if (expr1 && expr2) {
      reValueForSrc = totalReNum;
    }
    // 设置发货单子表累计出库数量
    view.getItem().setNtotaloutnum(newTotalOutNum);
    this.reSrcValueMap.put(bid, reValueForSrc);
  }

  private void updateDeliveryInfoToDB() {
    DeliveryStateMachine bo = new DeliveryStateMachine();
    if (this.openSet.size() > 0) {
      DeliveryViewVO[] openviews = new DeliveryViewVO[this.openSet.size()];
      openviews = this.openSet.toArray(openviews);
      bo.setState(new RowOpenState(), openviews);
    }
    if (this.closeSet.size() > 0) {
      DeliveryViewVO[] closeviews = new DeliveryViewVO[this.closeSet.size()];
      closeviews = this.closeSet.toArray(closeviews);
      bo.setState(new RowCloseState(), closeviews);
    }
  }

  /*
   * 更新质检信息 
   */
  private void updateQualityInfo() {
    // 缓存质检信息状态更新 所对应的发货单表体的出库状态
    Map<String, UFBoolean> outStateMap = new HashMap<String, UFBoolean>();
    // 默认相同发货单表体id对应的质检信息要更新的状态相同
    boolean isSame = true;
    DeliveryCheckVO[] vos = this.voutil.getRewriteCheckVO();
    for (DeliveryCheckVO vo : vos) {
      String bid = vo.getCdeliverybid();
      if ((outStateMap.size() > 0) && outStateMap.containsKey(bid)) {
        continue;
      }
      UFBoolean state = this.stateMap.get(bid);
      for (DeliveryCheckVO newvo : vos) {
        String newcid = newvo.getCdeliverycid();
        String newbid = newvo.getCdeliverybid();
        if (newcid.equals(vo.getCdeliverycid()) || !newbid.equals(bid)) {
          continue;
        }
        UFBoolean state1 = this.stateMap.get(newbid);
        // 相同表体id的质检单据出库状态和其他的不同则为false
        if (!state1.equals(state)) {
          outStateMap.put(bid, UFBoolean.FALSE);
          isSame = false;
        }
      }
      if (!isSame) {
        // 相同表体id的质检单据出库状态不同，则发货单表体必为打开状态
        outStateMap.put(bid, UFBoolean.FALSE);
        continue;
      }
      outStateMap.put(bid, state);
    }
    this.updateViewByQuality(outStateMap);
  }

  private void updateQualityInfoToDB() {
    DeliveryCheckVO[] vos = this.voutil.getRewriteCheckVO();
    if (null == vos) {
      return;
    }
    String[] names =
        new String[] {
          DeliveryCheckVO.NTOTALOUTNUM, DeliveryCheckVO.BOUTENDFLAG,
          DeliveryCheckVO.NTOTALNOTOUTNUM
        };
    VOUpdate<DeliveryCheckVO> bo = new VOUpdate<DeliveryCheckVO>();
    bo.update(vos, names);
  }

  public void updateToDB() {
    // 更新质检信息到数据库
   // this.updateQualityInfoToDB();
    // 更新发货单信息到数据库
    this.updateDeliveryInfoToDB();
  }

  /*
   * 根据质检信息更新所对应的发货单表体累计出库数量和出库状态
   * @param value
   * @param i
   * @param bclose
   * @param bopen
   */
  private void updateViewByQuality(Map<String, UFBoolean> outStateMap) {
    Map<String, DeliveryViewVO> viewMap = this.voutil.getRewriteViewVOOnCheck();
    Set<Entry<String, UFBoolean>> entrys = outStateMap.entrySet();
    for (Entry<String, UFBoolean> entry : entrys) {
      String bid = entry.getKey();
      DeliveryViewVO view = viewMap.get(bid);
    //  this.getViewStateByQualityInfo(entry, view);
      UFBoolean bcloseflag = this.getViewStateByQualityInfo(entry, view);
      if (!bcloseflag.booleanValue()) {
        view.getItem().setBoutendflag(UFBoolean.FALSE);
        this.openSet.add(view);
        continue;
      }
      view.getItem().setBoutendflag(UFBoolean.TRUE);
      this.closeSet.add(view);
    }
  }

  private void updateViewsInfo() {
    DeliveryViewVO[] views = this.voutil.getRewriteViewVO();
    for (DeliveryViewVO view : views) {
      UFBoolean state = this.stateMap.get(view.getItem().getCdeliverybid());
      if (!state.booleanValue()) {
        this.openSet.add(view);
        continue;
      }
      this.closeSet.add(view);
    }
  }
}
