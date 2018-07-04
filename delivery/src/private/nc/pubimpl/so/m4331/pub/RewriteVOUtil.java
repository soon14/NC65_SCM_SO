package nc.pubimpl.so.m4331.pub;

import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Map.Entry;
import java.util.Set;

import nc.bs.ml.NCLangResOnserver;
import nc.impl.pubapp.pattern.data.view.ViewQuery;
import nc.impl.pubapp.pattern.data.vo.VOQuery;
import nc.impl.pubapp.pattern.pub.LockOperator;
import nc.vo.pub.lang.UFBoolean;
import nc.vo.pubapp.pattern.pub.PubAppTool;
import nc.vo.pubapp.pattern.pub.SqlBuilder;
import nc.vo.so.m4331.entity.DeliveryCheckVO;
import nc.vo.so.m4331.entity.DeliveryViewVO;

public class RewriteVOUtil {

  // 缓存viewvo 包含下游回写的表体view和根据质检信息查询出来的view
  private Map<String, DeliveryViewVO> allviewMap;

  // 下游回写发货单的所有id
  private String[] bids;
  
  //下游回写质检行的所有id
  private String[] checkids;

  // 质检信息所对应的发货单表体id
  private String[] bidsByCheckVO;

  // 缓存viewVO 用于回写发货单表体
  private Map<String, DeliveryViewVO> viewMapOnBackWard;

  // 缓存viewVo 根据质检表信息获得回写发货单子表的信息
  private Map<String, DeliveryViewVO> viewMapOnCheck;

  // 缓存 用于回写发货单质检表信息
  private Map<String, DeliveryCheckVO> voMapOnBackWard;

  private Map<String, UFBoolean> voStateMap;

  public RewriteVOUtil(String[] bids1,String[] checkids) {
    this.bids = bids1;
    this.checkids=checkids;
    this.init();
  }
  public RewriteVOUtil(String[] bids1) {
    this.bids = bids1;
    this.init();
  }

  /**
   * 获得所有要回写的发货单表体信息
   * 1.下游回写的发货单表体信息
   * 2.质检信息回写发货单表体的信息
   * 
   * @return
   */
  public DeliveryViewVO[] getAllRewriteViewVO() {
    if (this.allviewMap.size() == 0) {
      return null;
    }
    DeliveryViewVO[] views = new DeliveryViewVO[this.allviewMap.size()];
    return this.allviewMap.values().toArray(views);
  }

  /*
   * 根据发货单表体id查询质检信息的出库状态是否相同（不包含下游回写回来的质检信息）
   * @return
   */
  public Map<String, UFBoolean> getOtherVOState() {
    this.voStateMap = new HashMap<String, UFBoolean>();
    SqlBuilder where = new SqlBuilder();
    where.append(" and ");
    where.append(DeliveryCheckVO.CDELIVERYBID, this.bidsByCheckVO);
    VOQuery<DeliveryCheckVO> query =
        new VOQuery<DeliveryCheckVO>(DeliveryCheckVO.class);
    DeliveryCheckVO[] vos = query.query(where.toString(), null);
   // Set<String> cidTempSet = new HashSet<String>();
//    String[] cids = this.getRewritCheckIDS();
//    for (String cid : cids) {
//      cidTempSet.add(cid);
//    }
    for (DeliveryCheckVO vo : vos) {
     // String cid = vo.getCdeliverycid();
//      if (cidTempSet.contains(cid)) {
//        continue;
//      }
      String bid = vo.getCdeliverybid();
      if ((this.voStateMap.size() > 0) && this.voStateMap.containsKey(bid)) {
        continue;
      }
      UFBoolean state = vo.getBoutendflag();
      for (DeliveryCheckVO newvo : vos) {
        if (!bid.equals(newvo.getCdeliverybid())) {
          continue;
        }
        UFBoolean state1 = newvo.getBoutendflag();
        if (!PubAppTool.isEqual(state, state1)) {
          this.voStateMap.put(bid, UFBoolean.FALSE);
          break;
        }
      }
      this.voStateMap.put(bid, state);
    }
    return this.voStateMap;
  }

  /**
   * 获得下游单据要回写的质检表的信息
   * 
   * @return
   */
  public DeliveryCheckVO[] getRewriteCheckVO() {
    if (this.voMapOnBackWard.size() == 0) {
      return null;
    }
    DeliveryCheckVO[] vos = new DeliveryCheckVO[this.voMapOnBackWard.size()];
    return this.voMapOnBackWard.values().toArray(vos);
  }

  /**
   * 获得下有单据要回写发货单子表的数据信息
   * 
   * @return
   */
  public DeliveryViewVO[] getRewriteViewVO() {
    if (this.viewMapOnBackWard.size() == 0) {
      return null;
    }
    DeliveryViewVO[] views = new DeliveryViewVO[this.viewMapOnBackWard.size()];
    return this.viewMapOnBackWard.values().toArray(views);
  }

  /**
   * 下游回写质检单据后，质检单据需要回写的发货单表体信息
   * 
   * @return
   */
  public Map<String, DeliveryViewVO> getRewriteViewVOOnCheck() {
    return this.viewMapOnCheck;
  }

  /*
   * 获得需要回写质检表的id 
   * @return
   */
  private String[] getRewritCheckIDS() {
//    // 如果回写发货单子表的数据信息的size==回写的表体id的个数，则只回写发货单表体id
//    if (this.viewMapOnBackWard.size() == this.bids.length) {
//      return null;
//    }
//    // 如果回写发货单子表的数据信息为空，则下游回写的id都为质检表的id
//    if (this.viewMapOnBackWard.size() == 0) {
//      return this.bids;
//    }
    if(this.checkids==null || this.checkids.length==0){
      return null;
    }
    Set<String> idSet = new HashSet<String>();
    for (String bid : this.checkids) {
     // if (!this.viewMapOnBackWard.containsKey(bid)) {
        idSet.add(bid);
     // }
    }
    String[] checkIds = new String[idSet.size()];
    return idSet.toArray(checkIds);
  }

  private void init() {
    this.lockBills();
    this.initViewMapOnBackWard();
    this.initVoMapOnBackWard();
    this.initViewMapOnCheck();
    this.initAllViewMap();
  }

  /*
   * 初始化所有要回写的发货单子表信息 
   */
  private void initAllViewMap() {
    this.allviewMap = new HashMap<String, DeliveryViewVO>();
    if (this.viewMapOnCheck.size() > 0) {
      Set<Entry<String, DeliveryViewVO>> entrys =
          this.viewMapOnCheck.entrySet();
      for (Entry<String, DeliveryViewVO> entry : entrys) {
        this.allviewMap.put(entry.getKey(), entry.getValue());
      }
    }
    else if (this.viewMapOnBackWard.size() > 0) {
      Set<Entry<String, DeliveryViewVO>> entrys =
          this.viewMapOnBackWard.entrySet();
      for (Entry<String, DeliveryViewVO> entry : entrys) {
        this.allviewMap.put(entry.getKey(), entry.getValue());
      }
    }
  }

  /*
   * 初始化下游回写发货单表体的信息
   */
  private void initViewMapOnBackWard() {
    DeliveryViewVO[] views = this.queryViews(this.bids);
    this.viewMapOnBackWard = new HashMap<String, DeliveryViewVO>();
    if (null == views) {
      return;
    }
    for (DeliveryViewVO view : views) {
      this.viewMapOnBackWard.put(view.getItem().getCdeliverybid(), view);
    }
  }

  /*
   * 根据质检vo查询质检信息所对应的发货单表体行
   */
  private void initViewMapOnCheck() {
    this.viewMapOnCheck = new HashMap<String, DeliveryViewVO>();
    this.bidsByCheckVO = null;
    if (this.voMapOnBackWard.size() == 0) {
      return;
    }
    // 根据质检表信息获得
    Set<String> bidSet = new HashSet<String>();
    Set<Entry<String, DeliveryCheckVO>> entrys =
        this.voMapOnBackWard.entrySet();
    for (Entry<String, DeliveryCheckVO> entry : entrys) {
      DeliveryCheckVO vo = entry.getValue();
      bidSet.add(vo.getCdeliverybid());
    }
    this.bidsByCheckVO = new String[bidSet.size()];
    this.bidsByCheckVO = bidSet.toArray(this.bidsByCheckVO);
    DeliveryViewVO[] views = this.queryViews(this.bidsByCheckVO);
    for (DeliveryViewVO view : views) {
      this.viewMapOnCheck.put(view.getItem().getCdeliverybid(), view);
    }
  }

  /*
   * 初始化回写的发货单质检信息表的数据信息
   */
  private void initVoMapOnBackWard() {
    this.voMapOnBackWard = new HashMap<String, DeliveryCheckVO>();
    String[] ids = this.getRewritCheckIDS();
    if (null == ids ||ids.length==0) {
      return;
    }
    VOQuery<DeliveryCheckVO> query =
        new VOQuery<DeliveryCheckVO>(DeliveryCheckVO.class);
    DeliveryCheckVO[] vos = query.query(ids);
    for (DeliveryCheckVO vo : vos) {
      this.voMapOnBackWard.put(vo.getCdeliverycid(), vo);
    }
  }

  /*
   * 加锁
   */
  private void lockBills() {
    LockOperator locker = new LockOperator();
    String message = NCLangResOnserver.getInstance().getStrByID("4006002_0", "04006002-0084")/*回写发货单加锁失败。*/;
    locker.lock(this.bids, message);
    if(this.checkids!=null && this.checkids.length>0){
      locker.lock(this.checkids, message);
    }
  }

  /*
   * 根据发货单表体id，得到发货单的视图vo
   */
  private DeliveryViewVO[] queryViews(String[] bids1) {
    ViewQuery<DeliveryViewVO> query =
        new ViewQuery<DeliveryViewVO>(DeliveryViewVO.class);
    // 根据id查询viewvo
    return query.query(bids1);
  }
}
