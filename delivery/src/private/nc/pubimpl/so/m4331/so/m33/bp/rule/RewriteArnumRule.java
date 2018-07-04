package nc.pubimpl.so.m4331.so.m33.bp.rule;

import java.util.Map;

import nc.impl.pubapp.env.BSContext;
import nc.impl.pubapp.pattern.rule.IRule;
import nc.pubitf.so.m4331.so.m33.RewriteArnumPara;
import nc.vo.pub.lang.UFDouble;
import nc.vo.pubapp.pattern.pub.MathTool;
import nc.vo.so.m4331.entity.DeliveryViewVO;

/**
 * 
 * @description
 * 回写发货单累计确认应收数量
 * @scene
 * 回写发货单累计确认应收数量时设置累计确认应收数量
 * @param
 * 无
 *
 * @since 6.5
 * @version 2015-10-19 下午2:12:13
 * @author zhangby5
 */
public class RewriteArnumRule implements IRule<DeliveryViewVO> {

  @Override
  public void process(DeliveryViewVO[] views) {
    @SuppressWarnings("unchecked")
    Map<String, RewriteArnumPara> index =
        (Map<String, RewriteArnumPara>) BSContext.getInstance().getSession(
            RewriteArnumPara.class.getName());
    for (DeliveryViewVO view : views) {
      String cdeliverybid = view.getItem().getCdeliverybid();
      UFDouble arnum = index.get(cdeliverybid).getArnum();
      UFDouble totalArnum = view.getItem().getNtotalarnum();
      totalArnum = MathTool.add(arnum, totalArnum);
      view.getItem().setNtotalarnum(totalArnum);
    }
  }
}
