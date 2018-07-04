package nc.bs.so.m30.maintain.rule.insert;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import nc.impl.pubapp.pattern.rule.IRule;
import nc.vo.price.pplimitexe.SOUpdatePPLimitExePara;
import nc.vo.scmpub.res.billtype.OPCBillType;
import nc.vo.scmpub.res.billtype.SOBillType;
import nc.vo.so.m30.entity.SaleOrderBVO;
import nc.vo.so.m30.entity.SaleOrderVO;
import nc.vo.so.m30.util.PromoteLimitUtil;
import nc.vo.so.m30.util.RewriteProPriceUtil;

/**
 * 
 * @description
 * 销售订单新增保存后规则
 * @scene
 * 销售订单新增保存回写限量促销价格执行量
 * @param
 * 无
 *
 * @since 6.5
 * @version 2014-02-24 15:51:45
 * @author zhangyfr
 */
public class RewritePromotePriceInsertRule implements IRule<SaleOrderVO> {

  @Override
  public void process(SaleOrderVO[] vos) {
    // 回写限量促销执行量参数列表
    List<SOUpdatePPLimitExePara> paras =
        new ArrayList<SOUpdatePPLimitExePara>();
    // 用于暂存<来源表体行ID，回写参数>，方便B2B预定单查询接口返回值给回写参数赋值
    Map<String, SOUpdatePPLimitExePara> map =
        new HashMap<String, SOUpdatePPLimitExePara>();

    for (SaleOrderVO sovo : vos) {
      for (SaleOrderBVO bvo : sovo.getChildrenVO()) {
        if (bvo.getCpromotpriceid() == null) {
          continue;
        }
        SOUpdatePPLimitExePara para = new SOUpdatePPLimitExePara();
        para.setBilltypecode(SOBillType.Order.getCode());
        para.setCcustomerid(sovo.getParentVO().getCcustomerid());
        para.setCpromoetpriceid(bvo.getCpromotpriceid());
        para.setRowID(bvo.getCsaleorderbid());
        para.setRowNo(bvo.getCrowno());
        para.setNnum(bvo.getNqtunitnum());
        // 来源单据类型为电子订单'ECC1'
        if (OPCBillType.OPCORDER.getCode().equals(bvo.getVsrctype())) {
          para.setSrcbilltypecode(bvo.getVsrctype());
          String srcbic = bvo.getCsrcbid();
          map.put(srcbic, para);
        }
        else {
          paras.add(para);
        }
      }
    }

    if (map.size() > 0) {
      // 考虑到，订单来自上游订单中心-预订单，新增保存场景，回写参数
      RewriteProPriceUtil util = new RewriteProPriceUtil();
      paras = util.setSrcParas(map);
    }
    if (paras != null && paras.size() > 0) {
      PromoteLimitUtil.InsertPPLimit(paras
          .toArray(new SOUpdatePPLimitExePara[paras.size()]));
    }

  }
}
