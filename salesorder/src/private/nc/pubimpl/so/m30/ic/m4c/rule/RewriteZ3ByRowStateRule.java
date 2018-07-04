package nc.pubimpl.so.m30.ic.m4c.rule;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import nc.bs.so.m30.state.row.RowCloseState;
import nc.bs.so.m30.state.rule.RowStateReWriteZ3Rule;
import nc.impl.pubapp.env.BSContext;
import nc.impl.pubapp.pattern.rule.IRule;
import nc.pubitf.so.m30.ic.m4c.Rewrite4CPara;
import nc.vo.so.m30.entity.SaleOrderViewVO;

/**
 * 
 * @description
 * 销售出库回写销售订单累计出库数量
 * @scene
 * 销售出库回写销售订单数量时，如果订单行关闭还要回写上游合同
 * @param
 * 无
 *
 * @since 6.5
 * @version 2015-10-19 下午1:52:19
 * @author zhangby5
 */
public class RewriteZ3ByRowStateRule implements IRule<SaleOrderViewVO> {
  @SuppressWarnings("unchecked")
  @Override
  public void process(SaleOrderViewVO[] vos) {
    Map<String, Rewrite4CPara> mParas =
        (Map<String, Rewrite4CPara>) BSContext.getInstance().getSession(
            Rewrite4CPara.class.getName());
    RowCloseState closeState = new RowCloseState();
    List<SaleOrderViewVO> closeList = new ArrayList<SaleOrderViewVO>();
    for (SaleOrderViewVO vo : vos) {
      if (closeState.isThisState(vo)) {
        closeList.add(vo);
      }
    }
    if (closeList.size() > 0) {
      RowStateReWriteZ3Rule rule = new RowStateReWriteZ3Rule(true, mParas);
      rule.process(closeList.toArray(new SaleOrderViewVO[closeList.size()]));
    }
  }

}
