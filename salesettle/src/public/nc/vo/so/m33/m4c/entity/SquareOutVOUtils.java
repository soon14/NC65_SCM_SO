package nc.vo.so.m33.m4c.entity;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;
import java.util.Set;

import nc.vo.pub.lang.UFBoolean;
import nc.vo.pub.lang.UFDouble;
import nc.vo.pubapp.bill.CombineBill;
import nc.vo.pubapp.pattern.model.tool.BillComposite;
import nc.vo.pubapp.pattern.pub.ListToArrayTool;
import nc.vo.pubapp.pattern.pub.MathTool;
import nc.vo.pubapp.pattern.tool.performance.DeepCloneTool;
import nc.vo.so.m33.enumeration.SquareType;
import nc.vo.trade.checkrule.VOChecker;

import nc.md.model.impl.MDEnum;

import nc.impl.pubapp.pattern.database.DBTool;

public class SquareOutVOUtils {

  private static SquareOutVOUtils squtil = new SquareOutVOUtils();

  private SquareOutVOUtils() {
    super();
  }

  public static SquareOutVOUtils getInstance() {
    return SquareOutVOUtils.squtil;
  }

  /**
   * 方法功能描述：根据视图vo和对应明细vo构造已结算视图vo 一个视图vo对应1到多个明细vo，所以
   * 将视图vo根据对应的视图的个数克隆，并赋上相应的已经结算数量值
   * 
   * @param bvos
   * @param sdvos
   * @author zhangcheng
   */
  public SquareOutViewVO[] buildSquareOutdVO(SquareOutViewVO[] vos,
      SquareOutDetailVO[] sdvos) {
    // <bid,bid对应的SquareOutDetailVO>
    Map<String, List<SquareOutDetailVO>> mbid_dvos =
        new HashMap<String, List<SquareOutDetailVO>>();
    List<SquareOutDetailVO> listdvo = null;
    for (SquareOutDetailVO dvo : sdvos) {
      listdvo = mbid_dvos.get(dvo.getCsalesquarebid());
      if (listdvo == null) {
        listdvo = new ArrayList<SquareOutDetailVO>();
        mbid_dvos.put(dvo.getCsalesquarebid(), listdvo);
      }
      listdvo.add(dvo);
    }

    List<SquareOutViewVO> retList = new ArrayList<SquareOutViewVO>();
    String bid = null;
    int sdvoLen = 1;
    SquareOutViewVO tempvo = null;
    for (SquareOutViewVO svo : vos) {
      bid = svo.getItem().getPrimaryKey();
      listdvo = mbid_dvos.get(bid);
      if (VOChecker.isEmpty(listdvo)) {
        continue;
      }
      sdvoLen = listdvo.size();

      // 结算原始数量
      UFDouble num = svo.getItem().getNnum();
      // 本次结算数量
      UFDouble nsquarenum = listdvo.get(0).getNsquarenum();
      // 多次结算(结算明细记录大于1，并且当前结算数量<结算原始数量)
      if (sdvoLen > 1 && MathTool.absCompareTo(nsquarenum, num) < 0) {
        Set<String> sProcesseid = new HashSet<String>();
        for (int i = 0; i < sdvoLen; i++) {
          SquareOutDetailVO dvo = listdvo.get(i);
          if (i == 0) {
            tempvo = svo;
          }
          else if (sProcesseid.contains(dvo.getProcesseid())) {
            continue;
          }
          else {
            tempvo = (SquareOutViewVO) svo.clone();
          }
          sProcesseid.add(dvo.getProcesseid());
          this.setSquareDataByDetailVO(tempvo, dvo);
          retList.add(tempvo);
        }
      }
      // 一次结算
      else {
        this.setSquareDataByDetailVO(svo, listdvo.get(0));
        retList.add(svo);
      }
    }

    SquareOutViewVO[] retVOs = retList.toArray(new SquareOutViewVO[0]);

    return retVOs;
  }

  /**
   * 将传确认应收结算单VO转化为传确认应收结算明细VO
   * 
   * @param sqvos
   * @return
   */
  public SquareOutDetailVO[] changeSQVOtoSQDVOForAR(SquareOutVO[] sqvos) {
    // 设置结算类型:确认应收
    SquareOutDetailVO[] dvos =
        this.changeSQVOtoSQDVOByFlag(sqvos, SquareType.SQUARETYPE_AR);
    // 设置是否自动结算
    this.setBautosquareflagByAR(sqvos, dvos);
    return dvos;
  }

  public SquareOutDetailVO[] changeSQVOtoSQDVOForARRUSH(
      SquareOutViewVO[] blueviews, SquareOutViewVO[] redviews) {
    return this.changeSQVOtoSQDVOForOutRushByFlag(blueviews, redviews,
        SquareType.SQUARETYPE_ARRUSH);
  }

  /**
   * 将回冲结算单VO转化为传回冲结算明细VO
   * 
   * @param sqvos
   * @return
   */
  public SquareOutDetailVO[] changeSQVOtoSQDVOForARRUSH(SquareOutVO[] sqvos) {
    // 设置结算类型:成本结算
    SquareOutDetailVO[] dvos =
        this.changeSQVOtoSQDVOByFlag(sqvos, SquareType.SQUARETYPE_ARRUSH);
    // 设置是否自动结算
    this.setBautosquareflagByAR(sqvos, dvos);
    return dvos;
  }

  /**
   * 将传暂估结算单VO转化为传暂估应收结算明细VO
   * 
   * @param sqvos
   * @return
   */
  public SquareOutDetailVO[] changeSQVOtoSQDVOForET(SquareOutVO[] sqvos) {
    // 设置结算类型:暂估应收
    SquareOutDetailVO[] dvos =
        this.changeSQVOtoSQDVOByFlag(sqvos, SquareType.SQUARETYPE_ET);
    // 设置是否自动结算
    this.setBautosquareflagByAR(sqvos, dvos);
    return dvos;
  }

  /**
   * 将传成本结算单VO转化为传成本应收结算明细VO
   * 
   * @param sqvos
   * @return
   */
  public SquareOutDetailVO[] changeSQVOtoSQDVOForIA(SquareOutVO[] sqvos) {
    // 设置结算类型:成本结算
    SquareOutDetailVO[] dvos =
        this.changeSQVOtoSQDVOByFlag(sqvos, SquareType.SQUARETYPE_IA);
    // 设置是否自动结算
    this.setBautosquareflagByIA(sqvos, dvos);
    return dvos;
  }

  /**
   * 将出库对冲结算单VO转化为传出库对冲结算明细VO 虽然仅有一行蓝字待对冲出库单，但是蓝字结算明细和红字结算明细一一对应
   * 
   * @param sqvos
   * @return
   */
  public SquareOutDetailVO[] changeSQVOtoSQDVOForOUTRUSH(
      SquareOutViewVO[] blueviews, SquareOutViewVO[] redviews) {
    return this.changeSQVOtoSQDVOForOutRushByFlag(blueviews, redviews,
        SquareType.SQUARETYPE_OUTRUSH);
  }

  /**
   * 将传发出商品结算单VO转化为传发出商品结算明细VO
   * 
   * @param sqvos
   * @return
   */
  public SquareOutDetailVO[] changeSQVOtoSQDVOForREG(SquareOutVO[] sqvos) {
    // 设置结算类型:发出商品
    SquareOutDetailVO[] dvos =
        this.changeSQVOtoSQDVOByFlag(sqvos, SquareType.SQUARETYPE_REG_DEBIT);
    // 设置是否自动结算
    this.setBautosquareflagByIA(sqvos, dvos);
    return dvos;
  }

  /**
   * 将出库对冲传发出商品结算单VO转化为传发出商品结算明细VO
   * 
   * @param sqvos
   * @return
   */
  public SquareOutDetailVO[] changeSQVOtoSQDVOForREGCredit(SquareOutVO[] sqvos) {
    // 设置结算类型:发出商品
    SquareOutDetailVO[] dvos =
        this.changeSQVOtoSQDVOByFlag(sqvos, SquareType.SQUARETYPE_REG_CREDIT);
    // 设置是否自动结算
    this.setBautosquareflagByIA(sqvos, dvos);
    return dvos;
  }

  /**
   * 将聚合VO数组转化为视图VO数组
   * 
   * @param sqvos
   * @return
   */
  public SquareOutViewVO[] changeToSaleSquareViewVO(SquareOutVO[] sqvos) {
    List<SquareOutViewVO> list = new ArrayList<SquareOutViewVO>();
    for (SquareOutVO svo : sqvos) {
      list.addAll(Arrays.asList(svo.changeToSquareOutViewVO()));
    }
    return new ListToArrayTool<SquareOutViewVO>().convertToArray(list);
  }

  public SquareOutVO[] combineBill(SquareOutViewVO[] view) {
    int len = view.length;
    SquareOutVO[] bills = new SquareOutVO[len];
    for (int i = 0; i < len; i++) {
      bills[i] = view[i].changeToSquareOutVO();
    }
    CombineBill<SquareOutVO> cb = new CombineBill<SquareOutVO>();
    cb.appendKey(SquareOutHVO.CSALESQUAREID);
    return cb.combine(bills);
  }

  /**
   * 将重复的销售出库单表体vo合并，累加本次结算数量、价税合计 仅用于销售出库单回写累计结算信息的场景，因为有多次结算，取消结算
   * 的时候同一表体行的结算信息汇总为一行
   * 
   * @param sqvos
   */
  public SquareOutVO[] combineBVO(SquareOutVO[] sqvos) {
    SquareOutViewVO[] views = this.changeToSaleSquareViewVO(sqvos);
    Map<String, SquareOutViewVO> mview = new HashMap<String, SquareOutViewVO>();
    for (SquareOutViewVO view : views) {
      String bid = view.getItem().getCsalesquarebid();
      SquareOutViewVO smview = mview.get(bid);
      if (VOChecker.isEmpty(smview)) {
        SquareOutViewVO newview = new SquareOutViewVO();
        // 克隆是为了不改变传入参数sqvos，因为后续sqvos继续传递
        newview.setHead((SquareOutHVO) view.getHead().clone());
        newview.setItem((SquareOutBVO) view.getItem().clone());
        mview.put(bid, newview);
      }
      else {
        // 本次累计结算数量
        UFDouble nthisnum =
            MathTool.add(smview.getItem().getNthisnum(), view.getItem()
                .getNthisnum());
        smview.getItem().setNthisnum(nthisnum);

        // 本次累计结算原币价税合计
        UFDouble norigtaxmny =
            MathTool.add(smview.getItem().getNorigtaxmny(), view.getItem()
                .getNorigtaxmny());
        smview.getItem().setNorigtaxmny(norigtaxmny);
      }
    }

    views = mview.values().toArray(new SquareOutViewVO[mview.size()]);
    return this.combineBill(views);

  }

  public SquareOutVO[] composite(SquareOutHVO[] hvos, SquareOutBVO[] bvos) {
    BillComposite<SquareOutVO> bc =
        new BillComposite<SquareOutVO>(SquareOutVO.class);
    SquareOutVO svo = new SquareOutVO();
    bc.append(svo.getMetaData().getParent(), hvos);
    bc.append(svo.getMetaData().getVOMeta(SquareOutBVO.class), bvos);
    return bc.composite();
  }

  public SquareOutViewVO[] deepClone(SquareOutViewVO[] views) {
    DeepCloneTool dct = new DeepCloneTool();
    SquareOutViewVO[] retview = (SquareOutViewVO[]) dct.deepClone(views);
    for (SquareOutViewVO view : retview) {
      view.getHead().setCsalesquareid(view.getItem().getCsalesquareid());
    }
    return retview;
  }

  /**
   * 将表体的结算单据id、主组织填充到表头
   * 
   * @param vos
   */
  public void fill4CIDPkOrgToHead(SquareOutViewVO[] vos) {
    for (SquareOutViewVO svo : vos) {
      this.set4CIDPkOrgToHead(svo);
    }
  }

  /**
   * 1.将表体的结算单据id、主组织填充到表头 2.设置处理编号
   * 
   * @param vos
   */
  public void fillDataForManualSquare(SquareOutViewVO[] vos) {
    DBTool dao = new DBTool();
    String[] processeid = dao.getOIDs(1);
    for (SquareOutViewVO svo : vos) {
      // 1.将表体的结算单据id填充到表头
      this.set4CIDPkOrgToHead(svo);
      // 2.设置处理编号
      svo.getItem().setProcesseid(processeid[0]);
    }
  }

  /**
   * 将明细ID赋给SquareVO
   * 
   * @param sqvos
   * @return
   */
  public void fillDidToSquareVO(SquareOutVO[] sqvos, SquareOutDetailVO[] dvos) {
    Map<String, SquareOutDetailVO> map =
        new HashMap<String, SquareOutDetailVO>();
    for (SquareOutDetailVO dvo : dvos) {
      map.put(dvo.getCsquareoutbvoid(), dvo);
    }
    for (SquareOutVO svo : sqvos) {
      for (SquareOutBVO bvo : svo.getChildrenVO()) {
        bvo.setCsalesquaredid(map.get(String.valueOf(bvo.getCsquareoutbvoid()))
            .getCsalesquaredid());
      }
    }
  }

  /**
   * 方法功能描述：过滤可以传存货的结算vo
   * <p>
   * <b>examples:</b>
   * <p>
   * 使用示例
   * <p>
   * <b>参数说明</b>
   * 
   * @param vos
   * @return <p>
   * @author zhangcheng
   * @time 2010-8-10 下午02:55:33
   */
  public SquareOutVO[] filterCostableVO(SquareOutVO[] vos) {
    SquareOutViewVO[] sviewvos = this.changeToSaleSquareViewVO(vos);
    List<SquareOutViewVO> list = new ArrayList<SquareOutViewVO>();
    for (SquareOutViewVO view : sviewvos) {
      if (view.getItem().getBcost() == null
          || view.getItem().getBcost().booleanValue()) {
        list.add(view);
      }
    }
    if (list.size() == 0) {
      return null;
    }
    SquareOutViewVO[] retviewVOs = list.toArray(new SquareOutViewVO[0]);
    SquareOutVO[] retvos = this.combineBill(retviewVOs);
    return retvos;
  }

  /**
   * 过滤可以传应收的vo
   * 
   * @param vos
   * @return
   */
  public SquareOutVO[] filterIncomeableVO(SquareOutVO[] vos) {
    SquareOutViewVO[] sviewvos = this.changeToSaleSquareViewVO(vos);
    List<SquareOutViewVO> list = new ArrayList<SquareOutViewVO>();
    for (SquareOutViewVO view : sviewvos) {
      if (view.getItem().getBincome() == null
          || view.getItem().getBincome().booleanValue()) {
        list.add(view);
      }
    }
    if (list.size() == 0) {
      return null;
    }
    SquareOutViewVO[] retviewVOs = list.toArray(new SquareOutViewVO[0]);
    SquareOutVO[] retvos = this.combineBill(retviewVOs);
    return retvos;
  }

  public SquareOutViewVO[] filterSignReturnOut(SquareOutViewVO[] views) {
    List<SquareOutViewVO> list = new ArrayList<SquareOutViewVO>();
    for (SquareOutViewVO view : views) {
      if (view.getHead().getBreturnoutstock().booleanValue()) {
        list.add(view);
      }
    }
    SquareOutViewVO[] retview = null;
    if (list.size() > 0) {
      retview = list.toArray(new SquareOutViewVO[list.size()]);
    }
    return retview;
  }

  public SquareOutBVO[] getSquareOutBVO(SquareOutViewVO[] sqvos) {
    List<SquareOutBVO> blist = new ArrayList<SquareOutBVO>();
    for (SquareOutViewVO vo : sqvos) {
      blist.add(vo.getItem());
    }
    return blist.toArray(new SquareOutBVO[blist.size()]);
  }

  public SquareOutBVO[] getSquareOutBVO(SquareOutVO[] sqvos) {
    List<SquareOutBVO> blist = new ArrayList<SquareOutBVO>();
    for (SquareOutVO vo : sqvos) {
      for (SquareOutBVO bvo : vo.getChildrenVO()) {
        blist.add(bvo);
      }
    }
    return blist.toArray(new SquareOutBVO[0]);
  }

  public SquareOutHVO[] getSquareOutHVO(SquareOutViewVO[] sqvos) {
    Set<SquareOutHVO> hset = new HashSet<SquareOutHVO>();
    SquareOutHVO hvo = null;
    for (SquareOutViewVO vo : sqvos) {
      hvo = vo.getHead();
      if (!hset.contains(hvo)) {
        hset.add(vo.getHead());
      }
    }
    return hset.toArray(new SquareOutHVO[hset.size()]);
  }

  public SquareOutHVO[] getSquareOutHVO(SquareOutVO[] sqvos) {
    SquareOutHVO[] hvos = new SquareOutHVO[sqvos.length];
    int len = sqvos.length;
    for (int i = 0; i < len; i++) {
      hvos[i] = (SquareOutHVO) sqvos[i].getParent();
    }
    return hvos;
  }

  /**
   * 用srcvos中的TS赋值给targetvos
   * 
   * @param srcvos
   * @param targetvos
   */
  public void setNewTS(SquareOutVO[] srcvos, SquareOutVO[] targetvos) {
    SquareOutViewVO[] srcviews = this.changeToSaleSquareViewVO(srcvos);
    SquareOutViewVO[] targetviews = this.changeToSaleSquareViewVO(targetvos);
    this.setNewTS(srcviews, targetviews);
  }

  /**
   * 签收退回销售出库单暂估应收数量是签收退回销售出库单可用数量和上游销售出库单暂估应收数量的最小值
   * 
   * @param view 待暂估应收签收退回销售出库结算单
   * @param outSquareNum 上游销售出库单暂估应收数量
   */
  public void setNthisETRushNumUseMinNum(SquareOutViewVO view, UFDouble netnum) {
    UFDouble nwasnum =
        MathTool.sub(view.getItem().getNnum(), view.getItem().getNarrushnum());
    UFDouble nthisnum = nwasnum;
    if (MathTool.greaterThan(nwasnum.abs(), netnum.abs())) {
      nthisnum = MathTool.oppose(netnum);
    }
    view.getItem().setNnum(nthisnum);
    view.getItem().setNthisnum(nthisnum);
  }

  /**
   * 方法功能描述：手工取消结算前设置本次结算数量，后续的回写方法使用
   * 因为后续方法是基于SquareWasVO，此处设置SquareWasVO的Nthisnum,
   * 
   * @param sqdvos
   * @param sqvos
   *          <p>
   * @author zhangcheng
   * @time 2010-7-1 下午06:07:39
   */
  public SquareOutVO[] setNthisnumForCancelSquare(SquareOutDetailVO[] sqdvos,
      SquareOutVO[] sqvos) {
    // <bid,SquareOutViewVO>,将相同的表体行vo合并
    Map<String, SquareOutViewVO> mview = new HashMap<String, SquareOutViewVO>();
    SquareOutViewVO[] views = this.changeToSaleSquareViewVO(sqvos);
    for (SquareOutViewVO view : views) {
      mview.put(view.getItem().getCsalesquarebid(), view);
    }

    // <BID,Map<processid,SquareOutDetailVO>>
    Map<String, Map<String, SquareOutDetailVO>> mbidprovo =
        new HashMap<String, Map<String, SquareOutDetailVO>>();
    for (SquareOutDetailVO dvo : sqdvos) {
      String bid = dvo.getCsalesquarebid();
      String processid = dvo.getProcesseid();
      dvo.setNsquarenum(MathTool.oppose(dvo.getNsquarenum()));
      dvo.setNorigtaxmny(MathTool.oppose(dvo.getNorigtaxmny()));
      Map<String, SquareOutDetailVO> mpdvo = mbidprovo.get(bid);
      if (VOChecker.isEmpty(mpdvo)) {
        mpdvo = new HashMap<String, SquareOutDetailVO>();
      }
      mpdvo.put(processid, dvo);
      mbidprovo.put(bid, mpdvo);
    }

    for (Entry<String, Map<String, SquareOutDetailVO>> entry : mbidprovo
        .entrySet()) {
      String bid = entry.getKey();
      // 带设置数据vo
      SquareOutViewVO view = mview.get(bid);
      view.getItem().setNthisnum(UFDouble.ZERO_DBL);
      view.getItem().setNorigtaxmny(UFDouble.ZERO_DBL);
      Map<String, SquareOutDetailVO> mdvo = entry.getValue();
      for (Entry<String, SquareOutDetailVO> edvo : mdvo.entrySet()) {
        SquareOutDetailVO dvo = edvo.getValue();
        UFDouble oldNthisnum = view.getItem().getNthisnum();
        UFDouble oldNorigtaxmny = view.getItem().getNorigtaxmny();
        UFDouble nthisnum = MathTool.add(oldNthisnum, dvo.getNsquarenum());
        UFDouble norigtaxmny =
            MathTool.add(oldNorigtaxmny, dvo.getNorigtaxmny());
        view.getItem().setNthisnum(nthisnum);
        view.getItem().setNorigtaxmny(norigtaxmny);
      }
    }

    SquareOutViewVO[] rets =
        mview.values().toArray(new SquareOutViewVO[mview.size()]);
    return this.combineBill(rets);

  }

  /**
   * 签收退回销售出库发出商品数量是签收退回销售出库单可用数量和上游销售出库单发出商品数量的最小值
   * 
   * @param view 待发出商品签收退回销售出库结算单
   * @param outSquareNum 上游销售出库单发出商品数量
   */
  public void setNthisREGNumUseMinNum(SquareOutViewVO view, UFDouble nregnum) {
    UFDouble nwasnum =
        MathTool.sub(view.getItem().getNnum(), view.getItem()
            .getNsquareregnum());
    UFDouble nthisnum = nwasnum;
    if (MathTool.greaterThan(nwasnum.abs(), nregnum.abs())) {
      nthisnum = MathTool.oppose(nregnum);
    }
    view.getItem().setNnum(nthisnum);
    view.getItem().setNthisnum(nthisnum);
  }

  /**
   * 前台手工结算查询调用
   * 根据累计应收、累计成本结算数量设置累计结算数量（手工结算界面显示字段）
   * 
   * @param vos
   */
  public void setNtotalsquarenum(SquareOutViewVO[] vos) {
    for (SquareOutViewVO view : vos) {
      UFDouble usquareianum = view.getItem().getNsquareianum();
      UFDouble usquarearnum = view.getItem().getNsquarearnum();
      boolean manualar = !view.getHead().getBautosquareincome().booleanValue();
      boolean manualia = !view.getHead().getBautosquarecost().booleanValue();
      Integer iaKey = view.getItem().getFpreiatype();
      Integer arKey = view.getItem().getFpreartype();

      // 用累计成本或应收数量设置已结算数量
      manualar =
          manualar
              && SquareType.SQUARETYPE_AR.getIntValue() == arKey.intValue();
      manualia =
          manualia
              && SquareType.SQUARETYPE_IA.getIntValue() == iaKey.intValue();
      if (manualar && manualia) {
        UFDouble maxsquarenum = usquareianum;

        if (MathTool.absCompareTo(maxsquarenum, usquarearnum) < 0) {
          maxsquarenum = usquarearnum;
        }
        view.getItem().setNtotalsquarenum(maxsquarenum);
      }
      else if (manualia
          && SquareType.SQUARETYPE_IA.getIntValue() == iaKey.intValue()) {
        view.getItem().setNtotalsquarenum(usquareianum);
      }
      else if (manualar
          && SquareType.SQUARETYPE_AR.getIntValue() == arKey.intValue()) {
        view.getItem().setNtotalsquarenum(usquarearnum);
      }
    }
  }

  /**
   * 将vo设置上出库对冲标志
   * 
   * @param vos
   */
  public void setOutRushFlag(SquareOutViewVO[] vos) {
    DBTool dao = new DBTool();
    String[] processeid = dao.getOIDs(1);
    for (SquareOutViewVO vo : vos) {
      vo.getItem().setBoutrushflag(UFBoolean.TRUE);
      vo.getItem().setProcesseid(processeid[0]);
    }
  }

  public void setProcessid(SquareOutViewVO[] vos) {
    DBTool dao = new DBTool();
    String[] processeid = dao.getOIDs(1);
    for (SquareOutViewVO vo : vos) {
      vo.getItem().setProcesseid(processeid[0]);
    }
  }

  public Map<String, List<SquareOutVO>> splitBillByTransType(SquareOutVO[] svos) {
    if (svos == null) {
      return null;
    }
    Map<String, List<SquareOutVO>> map =
        new HashMap<String, List<SquareOutVO>>();
    String key = null;
    List<SquareOutVO> list = null;
    for (SquareOutVO sdvo : svos) {
      key = sdvo.getParentVO().getVtrantypecode();
      list = map.get(key);
      if (list == null) {
        list = new ArrayList<SquareOutVO>();
        map.put(key, list);
      }
      list.add(sdvo);
    }
    return map;
  }

  /**
   * 按照结算类型分组SquareOutDetailVO
   * 
   * @param sqdvos
   * @return <收入,List<SquareOutDetailVO>> <成本,List<SquareOutDetailVO>>
   *         <回冲,List<SquareOutDetailVO>> <暂估,List<SquareOutDetailVO>>
   *         <发出商品,List<SquareOutDetailVO>>
   */
  public Map<SquareType, List<SquareOutDetailVO>> splitDetailVOBySquareType(
      SquareOutDetailVO[] sqdvos) {
    if (sqdvos == null) {
      return null;
    }
    Map<SquareType, List<SquareOutDetailVO>> map =
        new HashMap<SquareType, List<SquareOutDetailVO>>();
    SquareType key = null;
    List<SquareOutDetailVO> list = null;
    for (SquareOutDetailVO sdvo : sqdvos) {
      key = MDEnum.valueOf(SquareType.class, sdvo.getFsquaretype());
      list = map.get(key);
      if (list == null) {
        list = new ArrayList<SquareOutDetailVO>();
        map.put(key, list);
      }
      list.add(sdvo);
    }
    return map;
  }

  /**
   * 方法功能描述：按照结算类型分组SquareOutViewVO
   * <p>
   * <b>examples:</b>
   * <p>
   * 使用示例
   * <p>
   * <b>参数说明</b>
   * 
   * @param sqdvos
   * @return <p>
   * @author zhangcheng
   * @time 2010-6-10 下午06:45:35
   */
  public Map<SquareType, List<SquareOutViewVO>> splitViewVOBySquareTypeForManual(
      SquareOutViewVO[] sqdvos) {
    if (sqdvos == null) {
      return null;
    }
    Map<SquareType, List<SquareOutViewVO>> map =
        new HashMap<SquareType, List<SquareOutViewVO>>();
    List<SquareOutViewVO> list = null;
    for (SquareOutViewVO sdvo : sqdvos) {
      boolean manualar = !sdvo.getHead().getBautosquareincome().booleanValue();
      boolean manualia = !sdvo.getHead().getBautosquarecost().booleanValue();
      boolean btoar = sdvo.getItem().getBincome().booleanValue();
      boolean btoia = sdvo.getItem().getBcost().booleanValue();
      SquareType iaKey =
          MDEnum.valueOf(SquareType.class, sdvo.getItem().getFpreiatype());
      SquareType arKey =
          MDEnum.valueOf(SquareType.class, sdvo.getItem().getFpreartype());
      if (manualia && iaKey != null && btoia) {
        list = map.get(iaKey);
        if (list == null) {
          list = new ArrayList<SquareOutViewVO>();
          map.put(iaKey, list);
        }
        list.add(sdvo);
      }
      if (manualar && arKey != null && btoar) {
        list = map.get(arKey);
        if (list == null) {
          list = new ArrayList<SquareOutViewVO>();
          map.put(arKey, list);
        }
        list.add(sdvo);
      }
    }
    return map;
  }

  public SquareOutViewVO[] subByBID(SquareOutViewVO[] vos1,
      SquareOutViewVO[] vos2) {
    SquareOutViewVO[] leftvos = new SquareOutViewVO[vos1.length - vos2.length];
    Set<String> bids = new HashSet<String>();
    for (SquareOutViewVO view : vos2) {
      bids.add(view.getItem().getCsalesquarebid());
    }
    int i = 0;
    for (SquareOutViewVO view : vos1) {
      if (!bids.contains(view.getItem().getCsalesquarebid())) {
        leftvos[i++] = view;
      }
    }
    return leftvos;
  }

  public SquareOutViewVO[] subByDID(SquareOutViewVO[] vos1,
      SquareOutViewVO[] vos2) {
    SquareOutViewVO[] leftvos = new SquareOutViewVO[vos1.length - vos2.length];
    Set<String> dids = new HashSet<String>();
    for (SquareOutViewVO view : vos2) {
      dids.add(view.getItem().getCsalesquaredid());
    }
    int i = 0;
    for (SquareOutViewVO view : vos1) {
      if (!dids.contains(view.getItem().getCsalesquaredid())) {
        leftvos[i++] = view;
      }
    }
    return leftvos;
  }

  /**
   * 将结算单VO转化为结算明细VO
   * 
   * @param sqvos
   * @return
   */
  private SquareOutDetailVO[] changeSQVOtoSQDVO(SquareOutVO[] sqvos) {
    List<SquareOutDetailVO> list = new ArrayList<SquareOutDetailVO>();
    SquareOutDetailVO tempdvo = null;
    int i = 1;
    for (SquareOutVO svo : sqvos) {
      for (SquareOutBVO sbvo : svo.getChildrenVO()) {
        tempdvo = new SquareOutDetailVO();

        /**
         * 销售出库单待结算单voID(程序中临时用，元数据上没有)
         * 待结算vo与明细vo的对应关系，因为可能待结算vo拆成1行拆成多行SquareOutDetailVO
         * 所以无法用行id对应
         */
        String id = String.valueOf(i);
        sbvo.setCsquareoutbvoid(id);

        this.setBVOtoDetailVO(tempdvo, sbvo);

        list.add(tempdvo);
        i++;
      }
    }
    return new ListToArrayTool<SquareOutDetailVO>().convertToArray(list);
  }

  private SquareOutDetailVO[] changeSQVOtoSQDVOByFlag(SquareOutVO[] sqvos,
      SquareType type) {
    SquareOutDetailVO[] sqdvos = this.changeSQVOtoSQDVO(sqvos);
    // 设置结算类型
    for (SquareOutDetailVO sqdvo : sqdvos) {
      sqdvo.setFsquaretype((Integer) type.value());
    }
    return sqdvos;
  }

  private SquareOutDetailVO[] changeSQVOtoSQDVOForOutRushByFlag(
      SquareOutViewVO[] blueviews, SquareOutViewVO[] redviews, SquareType type) {

    List<SquareOutDetailVO> list = new ArrayList<SquareOutDetailVO>();
    SquareOutDetailVO tempdvo = null;
    /**
     * 销售出库单待结算单voID(程序中临时用，元数据上没有)
     * 待结算vo与明细vo的对应关系，因为可能待结算vo拆成1行拆成多行SquareOutDetailVO
     * 所以无法用行id对应
     */
    int i = 1;
    String red = "red";
    String blue = "blue";
    // 存在多个蓝字对多个红字的情况，因此需要判定是否多个蓝字
    int index = 0;
    for (SquareOutViewVO redview : redviews) {
      SquareOutBVO bluebvo = null;
      if (blueviews.length > index) {
        bluebvo = blueviews[index].getItem();
      }
      else {
        bluebvo = blueviews[0].getItem();
      }
      // 红字明细
      String redid = red + String.valueOf(i);
      redview.getItem().setCsquareoutbvoid(redid);
      tempdvo = new SquareOutDetailVO();
      this.setBVOtoDetailVO(tempdvo, redview.getItem());
      tempdvo.setFsquaretype(type.getIntegerValue());
      tempdvo.setCrushgeneralbid(bluebvo.getCsquarebillbid());
      tempdvo.setVrushbillcode(blueviews[0].getHead().getVbillcode());
      list.add(tempdvo);
      // 蓝字明细
      String blueid = blue + String.valueOf(i);
      bluebvo.setCsquareoutbvoid(blueid);
      tempdvo = new SquareOutDetailVO();
      this.setBVOtoDetailVO(tempdvo, bluebvo);
      UFDouble nrednum = UFDouble.ZERO_DBL.sub(redview.getItem().getNthisnum());
      tempdvo.setNsquarenum(nrednum);
      tempdvo.setFsquaretype(type.getIntegerValue());
      tempdvo.setCrushgeneralbid(redview.getItem().getCsquarebillbid());
      tempdvo.setVrushbillcode(redview.getHead().getVbillcode());
      list.add(tempdvo);
      i++;
      index++;
    }
    return list.toArray(new SquareOutDetailVO[0]);
  }

  private void set4CIDPkOrgToHead(SquareOutViewVO svo) {
    svo.getHead().setCsquarebillid(svo.getItem().getCsquarebillid());
    svo.getHead().setPk_org(svo.getItem().getPk_org());
  }

  private void setBautosquareflagByAR(SquareOutVO[] sqvos,
      SquareOutDetailVO[] dvos) {
    Map<String, SquareOutVO> map = new HashMap<String, SquareOutVO>();
    for (SquareOutVO vo : sqvos) {
      map.put(vo.getParentVO().getCsalesquareid(), vo);
    }
    SquareOutHVO hvo = null;
    for (SquareOutDetailVO dvo : dvos) {
      hvo = map.get(dvo.getCsalesquareid()).getParentVO();
      dvo.setBautosquareflag(hvo.getBautosquareincome());
    }
  }

  private void setBautosquareflagByIA(SquareOutVO[] sqvos,
      SquareOutDetailVO[] dvos) {
    Map<String, SquareOutVO> map = new HashMap<String, SquareOutVO>();
    for (SquareOutVO vo : sqvos) {
      map.put(vo.getParentVO().getCsalesquareid(), vo);
    }
    SquareOutHVO hvo = null;
    for (SquareOutDetailVO dvo : dvos) {
      hvo = map.get(dvo.getCsalesquareid()).getParentVO();
      dvo.setBautosquareflag(hvo.getBautosquarecost());
    }
  }

  /**
   * 用结算单表头表体VO设置明细表VO
   * 
   * @param dvo
   * @param hvo
   * @param bvo
   */
  private void setBVOtoDetailVO(SquareOutDetailVO dvo, SquareOutBVO bvo) {

    /**
     * 销售出库单待结算单voID(程序中临时用，元数据上没有)
     * 待结算vo与明细vo的对应关系，因为可能待结算vo拆成1行拆成多行SquareOutDetailVO
     * 所以无法用行id对应
     */
    dvo.setCsquareoutbvoid(bvo.getCsquareoutbvoid());

    dvo.setCsalesquareid(bvo.getCsalesquareid());
    dvo.setCsalesquarebid(bvo.getCsalesquarebid());
    dvo.setCsalesquaredid(bvo.getCsalesquaredid());
    dvo.setCsquarebillid(bvo.getCsquarebillid());
    dvo.setCsquarebillbid(bvo.getCsquarebillbid());
    dvo.setNsquarenum(bvo.getNthisnum());
    dvo.setNnum(bvo.getNnum());

    dvo.setCorigcurrencyid(bvo.getCorigcurrencyid());
    dvo.setCcurrencyid(bvo.getCcurrencyid());
    dvo.setCunitid(bvo.getCunitid());
    dvo.setCastunitid(bvo.getCastunitid());
    dvo.setVchangerate(bvo.getVchangerate());
    dvo.setNexchangerate(bvo.getNexchangerate());
    dvo.setNglobalexchgrate(bvo.getNglobalexchgrate());
    dvo.setNgroupexchgrate(bvo.getNgroupexchgrate());
    dvo.setNtaxrate(bvo.getNtaxrate());
    dvo.setNitemdiscountrate(bvo.getNitemdiscountrate());

    dvo.setNorigtaxnetprice(bvo.getNorigtaxnetprice());
    dvo.setNorignetprice(bvo.getNorignetprice());
    dvo.setNorigtaxprice(bvo.getNorigtaxprice());
    dvo.setNorigprice(bvo.getNorigprice());
    dvo.setNorigtaxmny(bvo.getNorigtaxmny());
    dvo.setNorigmny(bvo.getNorigmny());

    dvo.setNtaxmny(bvo.getNtaxmny());
    // modify by zhangby5 增加本币相关信息
    dvo.setNtax(bvo.getNtax());
    dvo.setNtaxnetprice(bvo.getNtaxnetprice());
    dvo.setNtaxprice(bvo.getNtaxprice());

    dvo.setProcesseid(bvo.getProcesseid());
    dvo.setPk_org(bvo.getPk_org());
    dvo.setPk_group(bvo.getPk_group());
    dvo.setDbilldate(bvo.getDbizdate());
    dvo.setBoutrushflag(bvo.getBoutrushflag());
    dvo.setCrushoutbatchid(bvo.getProcesseid());
    // V61新增字段
    dvo.setCtaxcodeid(bvo.getCtaxcodeid());
    dvo.setFtaxtypeflag(bvo.getFtaxtypeflag());
    dvo.setNcaltaxmny(bvo.getNcaltaxmny());
  }

  private void setNewTS(SquareOutViewVO[] srcviews,
      SquareOutViewVO[] targetviews) {
    // <bid,SquareOutViewVO>
    Map<String, SquareOutViewVO> map = new HashMap<String, SquareOutViewVO>();
    for (SquareOutViewVO view : srcviews) {
      map.put(view.getItem().getCsalesquarebid(), view);
    }
    for (SquareOutViewVO view : targetviews) {
      String bid = view.getItem().getCsalesquarebid();
      SquareOutViewVO srcview = map.get(bid);
      view.getHead().setTs(srcview.getHead().getTs());
      view.getItem().setTs(srcview.getItem().getTs());
    }
  }

  /**
   * 方法功能描述：将结算数量、结算批次号、对冲批次号、结算明细表id、以及各种金额
   */
  private void setSquareDataByDetailVO(SquareOutViewVO vo, SquareOutDetailVO dvo) {

    vo.getItem().setCsalesquaredid(dvo.getCsalesquaredid());
    vo.getItem().setProcesseid(dvo.getProcesseid());
    vo.getItem().setCrushoutbatchid(dvo.getCrushoutbatchid());
    // 单价数量金额
    vo.getItem().setNthisnum(dvo.getNsquarenum());

    vo.getItem().setNorignetprice(dvo.getNorignetprice());
    vo.getItem().setNorigprice(dvo.getNorigprice());
    vo.getItem().setNorigtaxnetprice(dvo.getNorigtaxnetprice());
    vo.getItem().setNorigtaxprice(dvo.getNorigtaxprice());
    vo.getItem().setNorigdiscount(dvo.getNorigdiscount());
    vo.getItem().setNorigmny(dvo.getNorigmny());
    vo.getItem().setNorigtaxmny(dvo.getNorigtaxmny());

    vo.getItem().setNitemdiscountrate(dvo.getNitemdiscountrate());

    vo.getItem().setNnetprice(dvo.getNnetprice());
    vo.getItem().setNprice(dvo.getNprice());
    vo.getItem().setNtaxprice(dvo.getNtaxprice());
    vo.getItem().setNtaxnetprice(dvo.getNtaxnetprice());
    vo.getItem().setNtaxmny(dvo.getNtaxmny());
    vo.getItem().setNmny(dvo.getNmny());
    vo.getItem().setNtax(dvo.getNtax());
    vo.getItem().setNdiscount(dvo.getNdiscount());

    vo.getItem().setNglobalmny(dvo.getNglobalmny());
    vo.getItem().setNglobaltaxmny(dvo.getNglobaltaxmny());
    vo.getItem().setNgroupmny(dvo.getNgroupmny());
    vo.getItem().setNgrouptaxmny(dvo.getNgrouptaxmny());

    // V61新增字段
    vo.getItem().setCtaxcodeid(dvo.getCtaxcodeid());
    vo.getItem().setFtaxtypeflag(dvo.getFtaxtypeflag());
    vo.getItem().setNcaltaxmny(dvo.getNcaltaxmny());
  }

}
