package nc.bs.so.pub.rule;

import nc.impl.pubapp.pattern.rule.IRule;
import nc.vo.pub.ISuperVO;
import nc.vo.pubapp.AppContext;
import nc.vo.pubapp.pattern.model.entity.bill.IBill;
import nc.vo.pubapp.pattern.pub.PubAppTool;
import nc.vo.so.pub.SOItemKey;

/**
 * 
 * @description
 * 新增单据时补充单据表尾信息，目前是制单人、制单日期
 * @scene
 * 新增单据保存前
 * @param 
 * 无
 * 
 * @since 6.0
 * @version 2011-8-11
 * @author 步晓慧
 */
public class FillBillTailInfoRuleForIns<E extends IBill> implements IRule<E> {

  @Override
  public void process(E[] vos) {
    for (IBill vo : vos) {
      this.setBillMakeInfo(vo);
    }
  }

  private void setBillMakeInfo(IBill vo) {
    ISuperVO head = vo.getParent();
    AppContext proxy = AppContext.getInstance();
    String billmaker = (String) head.getAttributeValue(SOItemKey.BILLMAKER);
    if (PubAppTool.isNull(billmaker)) {
      head.setAttributeValue(SOItemKey.BILLMAKER, proxy.getPkUser());
    }
    Object makedate = head.getAttributeValue(SOItemKey.DMAKEDATE);
    if (null == makedate) {
      head.setAttributeValue(SOItemKey.DMAKEDATE, proxy.getBusiDate());
    }
  }

}
