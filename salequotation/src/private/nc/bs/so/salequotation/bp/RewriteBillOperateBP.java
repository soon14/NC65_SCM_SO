package nc.bs.so.salequotation.bp;

import java.util.Collection;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Map.Entry;
import java.util.Set;

import nc.bs.framework.common.InvocationInfoProxy;
import nc.impl.pubapp.pattern.data.view.ViewQuery;
import nc.impl.pubapp.pattern.data.vo.VOUpdate;
import nc.impl.so.m4310trantype.M4310TranTypeImpl;
import nc.vo.pub.BusinessException;
import nc.vo.pub.VOStatus;
import nc.vo.pubapp.pattern.exception.ExceptionUtils;
import nc.vo.pubapp.pattern.pub.PubAppTool;
import nc.vo.scmpub.util.CombineViewToAggUtil;
import nc.vo.so.m4310trantype.entity.M4310TranTypeVO;
import nc.vo.so.salequotation.entity.AggSalequotationHVO;
import nc.vo.so.salequotation.entity.BillStatusEnum;
import nc.vo.so.salequotation.entity.QuatationRewritePara;
import nc.vo.so.salequotation.entity.SalequoViewVO;
import nc.vo.so.salequotation.entity.SalequotationBVO;
import nc.vo.so.salequotation.entity.SalequotationHVO;

public class RewriteBillOperateBP {

  public void operateBill(QuatationRewritePara[] vos) {
    try {
      // <报价单表体id，参数>
      Map<String, QuatationRewritePara> para = this.wrapPara(vos);
      // 待处理的销售报价单view
      SalequoViewVO[] views = this.queryViewVO(para);
      // <报价单id，报价单>
      Map<String, AggSalequotationHVO> billMap = this.getBills(views);
      Set<Entry<String, AggSalequotationHVO>> entrySet = billMap.entrySet();

      Map<String, AggSalequotationHVO> closedBillMap =
          new HashMap<String, AggSalequotationHVO>();
      Map<String, AggSalequotationHVO> openedBillMap =
          new HashMap<String, AggSalequotationHVO>();
      // <交易类型>
      Set<String> setvtrantype = new HashSet<String>();
      // <交易类型,交易类型VO>
      Map<String, M4310TranTypeVO> mapvtrantypeVO =
          new HashMap<String, M4310TranTypeVO>();
      // 查询交易类型并缓存
      this.queryTrantype(entrySet, setvtrantype, mapvtrantypeVO);

      M4310TranTypeVO trantypeVO = null;
      for (Entry<String, AggSalequotationHVO> entry : entrySet) {
        Integer operateFlag;
        AggSalequotationHVO bill = entry.getValue();
        SalequotationBVO[] bvos = bill.getChildrenVO();
        for (SalequotationBVO bvo : bvos) {
          String bid = bvo.getPk_salequotation_b();
          operateFlag = para.get(bid).getOperateFlag();
          SalequotationHVO header = (SalequotationHVO) bill.getParent();
          String vtrantype = header.getVtrantype();
          trantypeVO = mapvtrantypeVO.get(vtrantype);
          String billPk = bill.getPrimaryKey();
          // 删除
          if (operateFlag.intValue() == VOStatus.DELETED) {
            if (!openedBillMap.containsKey(billPk)
                && header.getFstatusflag().intValue() == BillStatusEnum.C_CLOSE) {
              openedBillMap.put(billPk, bill);
            }
          }
          // 新增
          else if (operateFlag.intValue() == VOStatus.NEW) {
            boolean bautocloseflag =
                trantypeVO.getBautocloseflag().booleanValue();
            if (!bautocloseflag) {
              continue;
            }
            if (!closedBillMap.containsKey(billPk)
                && header.getFstatusflag().intValue() == BillStatusEnum.C_AUDIT) {
              closedBillMap.put(billPk, bill);
            }
          }
        }
        this.closeBill(closedBillMap.values());
        this.openBill(openedBillMap.values());
      }
    }
    catch (BusinessException e) {
      ExceptionUtils.wrappException(e);
    }
  }

  private void closeBill(Collection<AggSalequotationHVO> closedBill) {

    if (closedBill.size() == 0) {
      return;
    }
    AggSalequotationHVO[] closeAggVO =
        closedBill.toArray(new AggSalequotationHVO[0]);
    SalequotationHVO[] hvos = new SalequotationHVO[closeAggVO.length];
    for (int i = 0; i < hvos.length; i++) {
      hvos[i] = closeAggVO[i].getParentVO();
      hvos[i].setFstatusflag(BillStatusEnum.C_CLOSE);
      hvos[i].setStatus(VOStatus.UPDATED);
    }
    String[] names = new String[] {
      SalequotationHVO.FSTATUSFLAG
    };
    VOUpdate<SalequotationHVO> update = new VOUpdate<SalequotationHVO>();
    update.update(hvos, names);
  }

  private Map<String, AggSalequotationHVO> getBills(SalequoViewVO[] views) {

    AggSalequotationHVO[] queryVos =
        new CombineViewToAggUtil<AggSalequotationHVO>(
            AggSalequotationHVO.class, SalequotationHVO.class,
            SalequotationBVO.class).combineViewToAgg(views,
            SalequotationHVO.PK_SALEQUOTATION);
    Map<String, AggSalequotationHVO> map =
        new HashMap<String, AggSalequotationHVO>();
    for (AggSalequotationHVO aggvo : queryVos) {
      map.put(aggvo.getPrimaryKey(), aggvo);
    }
    return map;
  }

  private void openBill(Collection<AggSalequotationHVO> openedBill) {
    if (openedBill.size() == 0) {
      return;
    }
    SalequoOpenBP openBP = new SalequoOpenBP();
    try {
      openBP.open(openedBill.toArray(new AggSalequotationHVO[0]));
    }
    catch (Exception e) {
      ExceptionUtils.wrappException(e);
    }
  }

  /**
   * 根据集团和交易类型查询交易类型VO并缓存在集合中
   */
  private void queryTrantype(Set<Entry<String, AggSalequotationHVO>> entrySet,
      Set<String> setvtrantype, Map<String, M4310TranTypeVO> mapvtrantypeVO)
      throws BusinessException {
    String pk_group = InvocationInfoProxy.getInstance().getGroupId();
    for (Entry<String, AggSalequotationHVO> entry : entrySet) {
      AggSalequotationHVO bill = entry.getValue();
      SalequotationHVO header = (SalequotationHVO) bill.getParent();
      String vtrantype = header.getVtrantype();
      if (!PubAppTool.isNull(vtrantype)) {
        setvtrantype.add(vtrantype);
      }
    }
    String[] vtrantypes = new String[setvtrantype.size()];
    setvtrantype.toArray(vtrantypes);
    M4310TranTypeVO[] trantypeVOs =
        new M4310TranTypeImpl().queryTranType(pk_group, vtrantypes);
    for (M4310TranTypeVO m30tvo : trantypeVOs) {
      mapvtrantypeVO.put(m30tvo.getVtrantype(), m30tvo);
    }
  }

  private SalequoViewVO[] queryViewVO(Map<String, QuatationRewritePara> para) {
    String[] ids = para.keySet().toArray(new String[0]);
    ViewQuery<SalequoViewVO> query =
        new ViewQuery<SalequoViewVO>(SalequoViewVO.class);
    query.setSharedHead(true);
    SalequoViewVO[] views = query.query(ids);
    if (views.length != para.size()) {
      String message =
          nc.vo.ml.NCLangRes4VoTransl.getNCLangRes().getStrByID("4006009_0",
              "04006009-0018")/*@res "出现并发，请重新查询销售订单"*/;
      ExceptionUtils.wrappBusinessException(message);
    }
    return views;
  }

  private Map<String, QuatationRewritePara> wrapPara(QuatationRewritePara[] vos) {
    Map<String, QuatationRewritePara> map =
        new HashMap<String, QuatationRewritePara>();
    for (QuatationRewritePara vo : vos) {
      map.put(vo.getPk_salequobill_b(), vo);
    }
    return map;
  }
}
