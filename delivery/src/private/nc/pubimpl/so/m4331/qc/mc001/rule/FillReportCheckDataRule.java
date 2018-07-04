package nc.pubimpl.so.m4331.qc.mc001.rule;

import java.util.Map;

import nc.pubitf.so.m4331.qc.mc001.RewritePara4331ForC001;
import nc.vo.pub.lang.UFBoolean;
import nc.vo.pub.lang.UFDouble;
import nc.vo.pubapp.pattern.pub.MathTool;
import nc.vo.so.m4331.entity.DeliveryViewVO;

/**
 * 报检回写发货单，填充回写数据信息
 * 
 * @since 6.0
 * @version 2010-12-28 下午02:21:31
 * @author 祝会征
 */
public class FillReportCheckDataRule {

  /**
   * 报检单回写发货单 填充回写数据
   * 
   * @param index
   * @param views
   */
  public void fillReportData(Map<String, RewritePara4331ForC001> index,
      DeliveryViewVO[] views) {
    for (DeliveryViewVO view : views) {
      String bid = view.getItem().getCdeliverybid();
      RewritePara4331ForC001 para = index.get(bid);
      // 获得回写的报检数据
      UFDouble totalreportnum = para.getTotalreportnum();
      // 只有负数行才能报检，累计报检数量为负数
      totalreportnum = MathTool.oppose(totalreportnum);
      view.getItem().setNtotalreportnum(totalreportnum);
      view.getItem().setBcheckflag(para.getBcheckflag());
      // 是否已经质检过标记设置成N 只有质检过，才可以重新报检
      view.getItem().setBqualityflag(UFBoolean.FALSE);
      // 清空累计合格数量
      view.getItem().setNtotalelignum(null);
      // 清空累计不合格数量
      view.getItem().setNtotalunelignum(null);
    }
  }
}
