package nc.bs.so.m30.rule.rewrite.me;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import nc.bs.framework.common.NCLocator;
import nc.bs.so.m30.maintain.util.SOStateUtil;
import nc.impl.pubapp.env.BSContext;
import nc.impl.pubapp.pattern.rule.IRule;
import nc.pubitf.me.m35meext.so.IArsubRewriteForSaleOrder;
import nc.pubitf.me.m35meext.so.IRewriteData;
import nc.pubitf.so.m30.ic.m4c.Rewrite4CPara;
import nc.vo.pub.BusinessException;
import nc.vo.pub.lang.UFDouble;
import nc.vo.pubapp.pattern.exception.ExceptionUtils;
import nc.vo.so.m30.entity.SaleOrderBVO;
import nc.vo.so.m30.entity.SaleOrderViewVO;

/**
 * 
 * @description
 * 销售订单出库打开时
 * @scene
 * 销售订单出库打开时回写客户费用单
 * @param
 * 无
 *
 * @since 6.3
 * @version 2014-06-30 15:31:35
 * @author 刘景
 */
public class RewriteME35WhenOutOpen implements IRule<SaleOrderViewVO> {

  @Override
  public void process(SaleOrderViewVO[] vos) {
    IArsubRewriteForSaleOrder rewrite =
        NCLocator.getInstance().lookup(IArsubRewriteForSaleOrder.class);
    
    Map<String, Rewrite4CPara> mParas =
            (Map<String, Rewrite4CPara>) BSContext.getInstance().getSession(
                Rewrite4CPara.class.getName());
    
    List<IRewriteData> datas = new ArrayList<IRewriteData>();
    for (SaleOrderViewVO vo : vos) {
    	// 没有做过冲抵或者赠品兑付
        if(SOStateUtil.isNotOffsetAndlrgcash(vo)){
      	  continue;
        }
        datas.add(this.getRewriteData(vo.getBody(),mParas));
    }
    try {
      if (datas.size() > 0) {
        rewrite.rewriteSubByOutOpen(datas.toArray(new IRewriteData[0]));
      }
    }
    catch (BusinessException ex) {
      ExceptionUtils.wrappBusinessException(ex.toString());
    }
  }

  private IRewriteData getRewriteData(SaleOrderBVO bvo, Map<String, Rewrite4CPara> mParas) {
    RewriteData data = new RewriteData();
    data.setSaleorderid(bvo.getCsaleorderid());
    data.setSaleorderbid(bvo.getCsaleorderbid());
    data.setFinanceorg(bvo.getCsettleorgid());
    data.setCcurrencyid(bvo.getCcurrencyid());
    data.setNorigsubmny(bvo.getNorigsubmny());
    if (null != bvo.getBlrgcashflag() && bvo.getBlrgcashflag().booleanValue()) {
      data.setNorigsubmny(bvo.getNorigtaxmny());
    }
    data.setNnum(bvo.getNnum());
    data.setNtotalsignnum(bvo.getNtotalsignnum());
    data.setNtranslossnum(bvo.getNtranslossnum());
    data.setNtotalsendnum(bvo.getNtotaloutnum());
    data.setIsoutclosed(bvo.getBboutendflag().booleanValue());
    data.setIslrgcash(bvo.getBlrgcashflag().booleanValue());
    if(null!=mParas){
      data.setNchangenum(mParas.get(bvo.getCsaleorderbid())
              .getNchangenum());
    }else{
      data.setNchangenum(UFDouble.ZERO_DBL);
    }
    return data;
  }

}
