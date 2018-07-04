package nc.pubimpl.so.m30.so.m32.rule;

import java.util.HashMap;
import java.util.Map;

import nc.bs.framework.common.NCLocator;
import nc.impl.pubapp.env.BSContext;
import nc.impl.pubapp.pattern.rule.IRule;
import nc.pubitf.so.ic.m4c.ISaleFor4CParaQuery;
import nc.pubitf.so.m30.so.m32.Rewrite32Para;
import nc.vo.ml.NCLangRes4VoTransl;
import nc.vo.pub.BusinessException;
import nc.vo.pub.lang.UFBoolean;
import nc.vo.pub.lang.UFDouble;
import nc.vo.pubapp.pattern.data.ValueUtils;
import nc.vo.pubapp.pattern.exception.ExceptionUtils;
import nc.vo.pubapp.pattern.pub.MathTool;
import nc.vo.so.m30.entity.SaleOrderBVO;
import nc.vo.so.m30.entity.SaleOrderHVO;
import nc.vo.so.m30.entity.SaleOrderViewVO;
import nc.vo.so.paravo.Para4CFor32SignBiz;
import nc.vo.so.pub.util.SOVOChecker;

/**
 * 
 * @description
 * 销售发票回写销售订单累计开票数量前校验：
 * @scene
 * 基于签收开票,订单累计签收数量必须大于等于订单累计开票数量
 * @param
 * 无
 *
 * @since 6.5
 * @version 2015-10-19 下午3:19:41
 * @author zhangby5
 */
public class RewriteWastageToleranceCheck implements IRule<SaleOrderViewVO> {

  private Map<String, Rewrite32Para> index;

  @SuppressWarnings("unchecked")
  @Override
  public void process(SaleOrderViewVO[] vos) {

    // 1.初始回写参数
    this.index =
        (Map<String, Rewrite32Para>) BSContext.getInstance().getSession(
            Rewrite32Para.class.getName());
    // 基于签收开票,订单累计签收数量必须大于等于订单累计开票数量
    this.checkSignBiz(vos);
  }

  private void checkSignBiz(SaleOrderViewVO[] vos) {

    // 初始化基于签收开票的业务流程
    Para4CFor32SignBiz[] paras = new Para4CFor32SignBiz[vos.length];
    int i = 0;
    for (SaleOrderViewVO svo : vos) {
      SaleOrderHVO head = svo.getHead();
      String cbiztypeid = head.getCbiztypeid();
      String csaleorgid = head.getPk_org();
      paras[i] = new Para4CFor32SignBiz(cbiztypeid, csaleorgid);
      i++;
    }
    ISaleFor4CParaQuery pqs =
        NCLocator.getInstance().lookup(ISaleFor4CParaQuery.class);
    Para4CFor32SignBiz[] rets = new Para4CFor32SignBiz[0];
    try {
      rets = pqs.querySignNumBusitype(paras);
    }
    catch (BusinessException e) {
      ExceptionUtils.wrappException(e);
    }
    Map<String, UFBoolean> mpara = new HashMap<String, UFBoolean>();
    if (!SOVOChecker.isEmpty(rets)) {
      for (Para4CFor32SignBiz para : rets) {
        mpara.put(para.getPk_org() + para.getCbizid(), para.getIsSign());
      }
    }

    // 校验
    if (!SOVOChecker.isEmpty(mpara)) {
      for (SaleOrderViewVO vo : vos) {
        SaleOrderBVO body = vo.getBody();
        SaleOrderHVO head = vo.getHead();
        Rewrite32Para para = this.index.get(body.getCsaleorderbid());
        UFDouble ntotalsignnum = body.getNtotalsignnum();
        UFDouble ntotalinvoicenum = body.getNtotalinvoicenum();
        ntotalinvoicenum = MathTool.add(ntotalinvoicenum, para.getNchangenum());
        String cbiztypeid = head.getCbiztypeid();
        String csaleorgid = head.getPk_org();
        boolean isSign =
            ValueUtils.getBoolean(mpara.get(csaleorgid + cbiztypeid));
        if (isSign && MathTool.lessThan(ntotalsignnum, ntotalinvoicenum)) {
          ExceptionUtils
              .wrappBusinessException(NCLangRes4VoTransl.getNCLangRes()
                  .getStrByID("4006011_0", "04006011-0419")/*@res "基于签收开票,订单累计签收数量必须大于等于订单累计开票数量"*/);
        }
      }
    }

  }

}
