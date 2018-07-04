package nc.bs.so.m30.rule.rewrite.price;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import nc.impl.pubapp.pattern.rule.IRule;
import nc.vo.price.pplimitexe.SOUpdatePPLimitExePara;
import nc.vo.pub.lang.UFDouble;
import nc.vo.scmpub.res.billtype.OPCBillType;
import nc.vo.scmpub.res.billtype.SOBillType;
import nc.vo.so.m30.entity.SaleOrderBVO;
import nc.vo.so.m30.entity.SaleOrderViewVO;
import nc.vo.so.m30.util.PromoteLimitUtil;
import nc.vo.so.m30.util.RewriteProPriceUtil;

/**
 * 
 * @description
 * 销售订单发货打开
 * @scene
 * 发货打开回写限量促销价格执行量
 * @param
 * 无
 *
 * @since 6.5
 * @version 2014-02-26 14:50:41
 * @author zhangyfr
 */
public class RewriteProPirceWhenSendOpen implements IRule<SaleOrderViewVO> {

  @Override
  public void process(SaleOrderViewVO[] vos) {

    List<SOUpdatePPLimitExePara> paras =
        new ArrayList<SOUpdatePPLimitExePara>();
    // 用于暂存<来源表体行ID，回写参数>，方便B2B预定单查询接口返回值给回写参数赋值
    Map<String, SOUpdatePPLimitExePara> map =
        new HashMap<String, SOUpdatePPLimitExePara>();
    for (SaleOrderViewVO vo : vos) {
      SaleOrderBVO bvo = vo.getBody();
      // 累计发货数量为空直接返回
      UFDouble ntotalsendnum = bvo.getNtotalsendnum();
      if (null == ntotalsendnum) {
        return;
      }
      // 累计发货数量不为空，占用报价数量，释放累计发货数量
      SOUpdatePPLimitExePara para = new SOUpdatePPLimitExePara();
      para.setBilltypecode(SOBillType.Order.getCode());
      para.setCcustomerid(vo.getHead().getCcustomerid());
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

    if (map.size() > 0) {
      RewriteProPriceUtil util = new RewriteProPriceUtil();
      paras = util.setSrcParas(map);

    }
    PromoteLimitUtil.updateExecutedNumByOpenOrClose(paras
        .toArray(new SOUpdatePPLimitExePara[paras.size()]));

  }
}
