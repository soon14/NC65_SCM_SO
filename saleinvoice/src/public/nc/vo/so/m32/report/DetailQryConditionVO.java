package nc.vo.so.m32.report;

import nc.bs.scmpub.report.ReportQueryCondition;
import nc.itf.iufo.freereport.extend.IAreaCondition;
import nc.pub.smart.model.SmartModel;

/**
 * ReportQueryCondition
 * 
 * @since 6.0
 * @version 2011-7-27 ÉÏÎç10:09:38
 * @author Ã´¹ó¾´
 */
public class DetailQryConditionVO extends ReportQueryCondition {

  private static final long serialVersionUID = 6863317785204312108L;

  private DetailQryConditionShowVO showvo;

  public DetailQryConditionVO(boolean isContinue) {
    super(isContinue);
  }

  @Override
  public Object clone() {
    Object ret = super.clone();
    DetailQryConditionVO con = (DetailQryConditionVO) ret;
    con.setQryconditionshowvo(this.showvo);
    return con;
  }

  @Override
  public IAreaCondition getAreaConditions(String areaName, SmartModel smartModel) {
    if ("invoiceexcehead".equals(areaName)) {
      return new com.ufida.report.anareport.base.BaseAreaCondition(null, null);
    }
    return super.getAreaConditions(areaName, smartModel);

  }

  public DetailQryConditionShowVO getQryconditionshowvo() {
    return this.showvo;
  }

  public void setQryconditionshowvo(DetailQryConditionShowVO conshowvo) {
    this.showvo = conshowvo;
  }
}
