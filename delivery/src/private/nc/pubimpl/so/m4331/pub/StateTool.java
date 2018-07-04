package nc.pubimpl.so.m4331.pub;

import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;

import nc.itf.scmpub.reference.uap.bd.material.MaterialPubService;
import nc.vo.bd.material.MaterialVO;
import nc.vo.pub.lang.UFBoolean;
import nc.vo.pub.lang.UFDouble;
import nc.vo.pubapp.pattern.data.ValueUtils;
import nc.vo.pubapp.pattern.exception.ExceptionUtils;
import nc.vo.pubapp.pattern.pub.MathTool;
import nc.vo.so.m4331.entity.DeliveryBVO;
import nc.vo.so.m4331.entity.DeliveryCheckVO;
import nc.vo.so.m4331.entity.DeliveryViewVO;
import nc.vo.so.pub.SOItemKey;
import nc.vo.so.pub.util.ViewVOUtil;

/**
 * 根据单传来的信息 获得该单据的状态
 * 
 * @since 6.0
 * @version 2011-2-24 下午03:40:53
 * @author 祝会征
 */
public class StateTool {
  // 是否一次出库
  private UFBoolean boneceflag;

  // 主键
  private String id;

  // 单据主数量
  private UFDouble num;

  private Object obj;

  // 发货单记录的已出库数量
  private UFDouble oldOutNum;

  // 单据原来的状态
  private UFBoolean oldState;

  private final UFDouble percent = new UFDouble(0.01);

  // 物料
  private String pk_material;

  // 下游回写的实际出库数量变化值
  private UFDouble reOutNum;

  /*
   * 缓存单据号和该单据对应的变化后的状态
   */
  private Map<String, UFBoolean> stateMap;

  /*
   *  缓存单据id和要回写来源单据的变化值
   */
  private Map<String, UFDouble> valueMap;

  private RewriteValueUtil valueUtil;

  private RewriteVOUtil voUtil;

  private Map<String, UFBoolean> typeInfoMap = new HashMap<String, UFBoolean>();

  public StateTool(RewriteVOUtil voutil, RewriteValueUtil valueutil) {
    this.valueUtil = valueutil;
    this.voUtil = voutil;
  }

  /**
   * 获得单据的状态
   * 
   * @return
   */
  public Map<String, UFBoolean> getState(Object[] vopara, Object[] viewpara) {
    if (null == this.stateMap) {
      this.stateMap = new HashMap<String, UFBoolean>();
      this.valueMap = new HashMap<String, UFDouble>();
      if (null != vopara && vopara instanceof DeliveryCheckVO[]) {
        this.initQualityState((DeliveryCheckVO[]) vopara);
      }
      if (null != viewpara && viewpara instanceof DeliveryViewVO[]) {
        this.initDeliveryState((DeliveryViewVO[]) viewpara);
      }
    }
    return this.stateMap;
  }

  public Map<String, UFDouble> getValueForRewriteSrc(Object[] vopara,
      Object[] viewpara) {
    if (null != this.valueMap) {
      return this.valueMap;
    }
    this.getState(vopara, viewpara);
    return this.valueMap;
  }

  /*
   * 获得满足关闭条件的下限值
   */
  private UFDouble getLowerNum(Map<String, MaterialVO> materialMap) {
    MaterialVO materialvo = materialMap.get(this.pk_material);
    UFDouble lowlimit = materialvo.getOutcloselowerlimit();
    lowlimit = lowlimit.multiply(this.percent);
    lowlimit = MathTool.sub(UFDouble.ONE_DBL, lowlimit);
    // 关闭下限
    UFDouble lowerNum = this.num.multiply(lowlimit);
    return lowerNum;
  }

  /*
   * 单据类型为非一次出库关闭 获得单据变化后的状态 
   * @return
   */
  private UFBoolean getStateForNoOnece(Map<String, MaterialVO> materialMap) {
    UFBoolean state = null;
    UFDouble totalOutNum = MathTool.add(this.oldOutNum, this.reOutNum);
    if (totalOutNum.compareTo(UFDouble.ZERO_DBL) == 0) {
      state = UFBoolean.FALSE;
    }
    else {
      UFDouble lowerNum = this.getLowerNum(materialMap);
      boolean expr1 = this.oldState.booleanValue();
      boolean expr2 = totalOutNum.abs().compareTo(lowerNum.abs()) >= 0;
      boolean expr3 = this.oldOutNum.abs().compareTo(lowerNum.abs()) < 0;
      if (!expr1 && expr2) {
        // 原来的状态为打开，更新累计出库数量后大于或等于关闭下限
        state = UFBoolean.TRUE;
      }
      else if (!expr1 && !expr2) {
        // 原来的状态为打开，更新累计出库数量后小于关闭下限
        state = UFBoolean.FALSE;
      }
      else if (expr3) {
        // 原来的状态为关闭状态 且原出累计库数量小于关闭下限（为手动关闭，只能手动打开）
        state = UFBoolean.TRUE;
      }
      else if (!expr3 && expr2) {
        /*
         * 原来状态为关闭状态且原出累计库数量不小于关闭下限
         * 并且变化后的累计出库数量大于或者等于关闭下限
         */
        state = UFBoolean.TRUE;
      }
      else {
        /*
         * 原来状态为关闭状态且原出累计库数量不小于关闭下限
         * 并且变化后的累计出库数量小于关闭下限
         */
        state = UFBoolean.FALSE;
      }
    }
    this.updateQualityInfo(totalOutNum, state);
    return state;
  }

  /*
   * 单据类型为一次出库关闭，获得单据变化后的状态 
   * @return
   */
  private UFBoolean getStateForOnece() {
    UFBoolean state = null;
    UFDouble totalOutNum = MathTool.add(this.oldOutNum, this.reOutNum);
    if (totalOutNum.compareTo(UFDouble.ZERO_DBL) == 0) {
      state = UFBoolean.FALSE;
    }
    else {
      state = UFBoolean.TRUE;
    }
    this.updateQualityInfo(totalOutNum, state);
    return state;
  }

  private UFDouble getUFDoubleValue(Object object) {
    UFDouble value = ValueUtils.getUFDouble(object);
    if (null == value) {
      return UFDouble.ZERO_DBL;
    }
    return value;
  }

  /**
   * 当状态改变时，获得回写来源单据的数据
   * 
   * @param state
   * @return
   */
  private UFDouble getValueStateChange(UFBoolean state) {
    if (!state.booleanValue()) {
      // 原来单据是关闭 现在状态是打开
      return MathTool.sub(this.num, this.oldOutNum);
    }
    // 原来的状态是打开 现在状态是关闭
    UFDouble totaloutnum = MathTool.add(this.oldOutNum, this.reOutNum);
    return MathTool.sub(totaloutnum, this.num);
  }

  public Map<String, MaterialVO> getMaterials(String[] pk_materials) {
    try {
      String[] str = new String[2];
      // 物料出库关闭下限
      str[0] = MaterialVO.OUTCLOSELOWERLIMIT;
      // 物料关闭容差
      str[1] = MaterialVO.OUTTOLERANCE;
      Map<String, MaterialVO> map =
          MaterialPubService.queryMaterialBaseInfo(pk_materials, str);
      return map;
    }
    catch (Exception e) {
      ExceptionUtils.wrappBusinessException(e.getMessage());
    }
    return null;
  }

  /**
   * 初始化发货单的信息
   * 
   * @param views
   */
  private void initDeliveryState(DeliveryViewVO[] views) {
    String[] cmaterialvids =
        ViewVOUtil.getDistinctFieldArray(views, DeliveryBVO.class,
            SOItemKey.CMATERIALVID, String.class);
    Map<String, MaterialVO> materialMap = this.getMaterials(cmaterialvids);
    for (DeliveryViewVO view : views) {
      if (this.stateMap.containsKey(view.getItem().getCdeliverybid())) {
        continue;
      }
      this.initViewData(view);
      String bid = view.getItem().getCdeliverybid();
      if (!this.boneceflag.booleanValue()) {
        // 单据类型非一次性出库关闭
        UFBoolean state = this.getStateForNoOnece(materialMap);
        this.stateMap.put(bid, state);
        continue;
      }
      // 单据类型为一次出库关闭
      UFBoolean state = this.getStateForOnece();
      this.stateMap.put(bid, state);
    }
  }

  /**
   * 初始化质检信息的的状态
   * 
   * @param vos
   */
  private void initQualityState(DeliveryCheckVO[] vos) {
    Set<String> cmaterialvids = new HashSet<String>();
    for (DeliveryCheckVO vo : vos) {
      cmaterialvids.add(vo.getCmaterialvid());
    }

    Map<String, MaterialVO> materialMap =
        this.getMaterials(cmaterialvids.toArray(new String[0]));
    for (DeliveryCheckVO vo : vos) {
      if (this.stateMap.containsKey(vo.getCdeliverycid())) {
        continue;
      }
      this.initVOData(vo);
      String cid = vo.getCdeliverycid();
      if (!this.boneceflag.booleanValue()) {
        // 单据类型为一次出库关闭
        UFBoolean state = this.getStateForNoOnece(materialMap);
        this.stateMap.put(cid, state);
        continue;
      }
      // 单据类型非一次性出库关闭
      UFBoolean state = this.getStateForOnece();
      this.stateMap.put(cid, state);
    }
  }

  private void initValueMapForSrc(UFBoolean newState) {
    boolean expr1 = this.oldState.booleanValue();
    boolean expr2 = newState.booleanValue();
    if (!expr1 && !expr2) {
      // 原来状态是打开 更新后状态无变化 回写来源单据为0
      this.valueMap.put(this.id, UFDouble.ZERO_DBL);
    }
    else if (!expr1 && expr2 || expr1 && !expr2) {
      // 原来状态是打开，更新后状态为关闭状态
      UFDouble value = this.getValueStateChange(newState);
      this.valueMap.put(this.id, value);
    }
    else if (expr1 && expr2) {
      this.valueMap.put(this.id, this.reOutNum);
    }
  }

  /*
   * 根据发货单视图vo初始化相应数据 
   * @param view
   */
  private void initViewData(DeliveryViewVO view) {
    // 将来源信息处理为行
    this.valueUtil = new RewriteValueUtil();
    this.obj = view;
    this.id = view.getItem().getCdeliverybid();
    this.num = this.getUFDoubleValue(view.getItem().getNnum());
    this.oldOutNum = this.getUFDoubleValue(view.getItem().getNtotaloutnum());
    this.oldState = view.getItem().getBoutendflag();
    this.pk_material = view.getItem().getCmaterialvid();
    String srcBilltype = view.getItem().getVsrctype();
    UFDouble rewriteNum = this.valueUtil.getRewriteNum(this.id, srcBilltype);
    this.reOutNum = this.getUFDoubleValue(rewriteNum);
    String billtype = view.getHead().getVtrantypecode();
    if (!this.typeInfoMap.containsKey(billtype)) {
      this.typeInfoMap.put(billtype, this.valueUtil
          .getBilltypeInfo(this.voUtil).get(billtype));

    }
    this.boneceflag = this.typeInfoMap.get(billtype);
    // 设置累计应发未出库数量
    this.setNoOutNumForView(view, srcBilltype);
  }

  /*
   * 根据质检信息vo 初始化相应数据 
   * @param vo
   */
  private void initVOData(DeliveryCheckVO vo) {
    this.obj = vo;
    this.id = vo.getCdeliverycid();
    this.num = this.getUFDoubleValue(vo.getNnum());
    this.oldOutNum = this.getUFDoubleValue(vo.getNtotaloutnum());
    this.oldState = vo.getBoutendflag();
    this.pk_material = vo.getCmaterialvid();
    DeliveryViewVO view =
        this.voUtil.getRewriteViewVOOnCheck().get(vo.getCdeliverybid());
    String srcBilltype = view.getItem().getVsrctype();
    // 实际出库数量的变化量
    UFDouble outNum = this.valueUtil.getRewriteNum(this.id, srcBilltype);
    this.reOutNum = this.getUFDoubleValue(outNum);
    String billtype = view.getHead().getVtrantypecode();
    this.boneceflag = this.valueUtil.getBilltypeInfo(this.voUtil).get(billtype);
    this.setNoOutNumForVo(view, srcBilltype, vo);
  }

  /**
   * 设置发货单子表累计应发未出库数量。
   * 
   * @param view
   * @param srcBilltype
   */
  private void setNoOutNumForView(DeliveryViewVO view, String srcBilltype) {
    UFDouble totalNoOutNum = view.getItem().getNtotalnotoutnum();
    UFDouble noOutNum = this.valueUtil.getRewriteNoNum(this.id, srcBilltype);
    totalNoOutNum = this.getUFDoubleValue(totalNoOutNum);
    noOutNum = this.getUFDoubleValue(noOutNum);
    UFDouble value = MathTool.add(totalNoOutNum, noOutNum);
    view.getItem().setNtotalnotoutnum(value);
  }

  /**
   * 设置质检信息的累计应发未出库主数量
   * 
   * @param view
   * @param srcBilltype
   * @param vo
   */
  private void setNoOutNumForVo(DeliveryViewVO view, String srcBilltype,
      DeliveryCheckVO vo) {
    UFDouble oldNoOut = vo.getNtotalnotoutnum();
    UFDouble reNoOut = this.valueUtil.getRewriteNoNum(this.id, srcBilltype);
    UFDouble totalNoOut = view.getItem().getNtotalnotoutnum();
    oldNoOut = this.getUFDoubleValue(oldNoOut);
    reNoOut = this.getUFDoubleValue(reNoOut);
    totalNoOut = this.getUFDoubleValue(totalNoOut);
    UFDouble value = MathTool.add(reNoOut, oldNoOut);
    vo.setNtotalnotoutnum(value);
    value = MathTool.add(totalNoOut, reNoOut);
    view.getItem().setNtotalnotoutnum(value);
  }

  /*
   * 更新累计出库数量和出库状态
   */
  private void updateQualityInfo(UFDouble totaloutnum, UFBoolean state) {
    UFDouble value = totaloutnum;
    if (totaloutnum.compareTo(UFDouble.ZERO_DBL) != 0) {
      value = totaloutnum;
    }
    if (this.obj instanceof DeliveryCheckVO) {
      DeliveryCheckVO vo = (DeliveryCheckVO) this.obj;
      vo.setNtotaloutnum(value);
      vo.setBoutendflag(state);
    }
    if (this.obj instanceof DeliveryViewVO) {
      this.initValueMapForSrc(state);
      DeliveryViewVO view = (DeliveryViewVO) this.obj;
      view.getItem().setNtotaloutnum(value);
      view.getItem().setBoutendflag(state);
    }
  }
}
