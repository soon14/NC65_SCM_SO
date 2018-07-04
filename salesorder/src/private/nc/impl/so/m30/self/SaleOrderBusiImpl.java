package nc.impl.so.m30.self;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;
import java.util.Set;

import nc.itf.scmpub.reference.uap.bd.material.MaterialPubService;
import nc.itf.scmpub.reference.uap.bd.material.MaterialSalePubService;
import nc.itf.scmpub.reference.uap.pf.PfBillItfDefUtil;
import nc.itf.scmpub.reference.uap.pf.PfServiceScmUtil;
import nc.itf.scmpub.reference.uap.pf.TransTypeMapping;
import nc.itf.so.m30.self.ISaleOrderBusi;
import nc.vo.bd.material.MaterialVO;
import nc.vo.bd.material.sale.MaterialBindleVO;
import nc.vo.pub.AggregatedValueObject;
import nc.vo.pub.BusinessException;
import nc.vo.pub.lang.UFDate;
import nc.vo.pubapp.pattern.pub.MapList;
import nc.vo.scmpub.res.billtype.ARAPBillType;
import nc.vo.scmpub.res.billtype.SOBillType;
import nc.vo.so.m30.bind.OrderBindMatchPara;
import nc.vo.so.m30.bind.OrderBindMatchResult;
import nc.vo.so.m30.entity.SaleOrderVO;
import nc.vo.so.m30.sobalance.util.GatherbillUtil;

public class SaleOrderBusiImpl implements ISaleOrderBusi {

  @Override
  public OrderBindMatchResult[] matchBind(OrderBindMatchPara[] bindparas) {

    OrderBindMatchResult[] matchresult =
        new OrderBindMatchResult[bindparas.length];
    // 1.填充序列号
    int i = 0;
    for (OrderBindMatchPara para : bindparas) {
      para.setParaindex(Integer.valueOf(i));
      i++;
    }
    // 2.按销售组织分组
    MapList<String, OrderBindMatchPara> splitpara =
        new MapList<String, OrderBindMatchPara>();
    for (OrderBindMatchPara para : bindparas) {
      splitpara.put(para.getCsaleorgid(), para);
    }
    // 3.每组匹配捆绑件
    for (Entry<String, List<OrderBindMatchPara>> entry : splitpara.entrySet()) {
      String csaleorgid = entry.getKey();
      List<OrderBindMatchPara> paras = entry.getValue();
      this.queryMaterialBind(csaleorgid, paras, matchresult);

    }
    return matchresult;
  }

  private void queryMaterialBind(String csaleorgid,
      List<OrderBindMatchPara> paras, OrderBindMatchResult[] matchresult) {
    Set<String> setmarid = new HashSet<String>();
    for (OrderBindMatchPara para : paras) {
      setmarid.add(para.getCmarterialvid());
    }
    Map<String, List<MaterialBindleVO>> materialbindmap =
        MaterialSalePubService.queryMaterialBindles(csaleorgid, setmarid);

    for (OrderBindMatchPara para : paras) {
      int index = para.getParaindex().intValue();
      matchresult[index] = new OrderBindMatchResult();

      String cmarterialvid = para.getCmarterialvid();
      if (materialbindmap.containsKey(cmarterialvid)) {
        List<MaterialBindleVO> bindvo = materialbindmap.get(cmarterialvid);
        MaterialBindleVO[] bindlevos = this.filterMatchBindleVO(para, bindvo);
        matchresult[index].setMatchBindleVOs(bindlevos);
      }
    }
  }

  private MaterialBindleVO[] filterMatchBindleVO(OrderBindMatchPara para,
      List<MaterialBindleVO> bindvos) {
    MaterialBindleVO[] bindbvos = null;
    List<MaterialBindleVO> listbvos = new ArrayList<MaterialBindleVO>();

    Map<String, Integer> marenable = this.getMarEnableStateMap(bindvos);

    UFDate dbilldate = para.getDbilldate();
    for (MaterialBindleVO bindvo : bindvos) {
      // 非启用态
      Integer state = marenable.get(bindvo.getPk_bindle());
      if (null == state || state.intValue() != 2) {
        continue;
      }
      // 单据日期早于捆绑件日期
      if (null == bindvo.getEnddate() || dbilldate.before(bindvo.getEnddate())) {
        listbvos.add(bindvo);
      }
    }
    if (listbvos.size() > 0) {
      bindbvos = new MaterialBindleVO[listbvos.size()];
      listbvos.toArray(bindbvos);
    }
    return bindbvos;
  }

  private Map<String, Integer> getMarEnableStateMap(
      List<MaterialBindleVO> bindvos) {
    Set<String> marid = new HashSet<String>();
    for (MaterialBindleVO bindvo : bindvos) {
      marid.add(bindvo.getPk_bindle());
    }
    String[] marvids = new String[marid.size()];
    marid.toArray(marvids);

    String[] fields = new String[] {
      MaterialVO.PK_SOURCE, MaterialVO.ENABLESTATE
    };
    MaterialVO[] marvos =
        MaterialPubService.queryMaterialBaseInfoByPks(marvids, fields);

    Map<String, Integer> mapenable = new HashMap<String, Integer>();
    for (MaterialVO marvo : marvos) {
      mapenable.put(marvo.getPk_source(), marvo.getEnablestate());
    }
    return mapenable;
  }

  @Override
  public AggregatedValueObject[] prepareOrderGathering(SaleOrderVO bill)
      throws BusinessException {
    // 检查能否收款
    GatherbillUtil.getInstance().checkBeforeGathering(bill);
    // 准备待VO交换的订单VO
    SaleOrderVO newOrdvo =
        GatherbillUtil.getInstance().prepareOrderBeforeChangeData(bill);

    /*
     * V56逻辑 过滤赠品行 处理预收标志 订单收款不计税 订单预收款已完成不能收款 订单已收款完成不能收款
     */
    String srcTransTypeCode = bill.getParentVO().getVtrantypecode();
    String srcBillType = SOBillType.Order.getCode();
    TransTypeMapping mapping = new TransTypeMapping();
    mapping.setSrcBillType(srcBillType);
    mapping.setSrcTransTypeCode(srcTransTypeCode);
    mapping.setDestBillType(ARAPBillType.GatheringOrder.getCode());
    mapping =
        PfBillItfDefUtil.queryTransTypeMapping(
            bill.getParentVO().getPk_group(), mapping);
    String destTransTypeCode = mapping.getDestTransTypeCode();
    if (destTransTypeCode == null) {
      destTransTypeCode = "D2";
    }
    AggregatedValueObject[] destVOs =
        PfServiceScmUtil.executeVOChange(srcTransTypeCode, destTransTypeCode,
            new AggregatedValueObject[] {
              newOrdvo
            });

    return destVOs;
  }
}
