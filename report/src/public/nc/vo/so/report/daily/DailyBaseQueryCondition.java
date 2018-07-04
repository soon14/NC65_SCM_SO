package nc.vo.so.report.daily;

import java.util.ArrayList;
import java.util.List;

import nc.bs.scmpub.report.ReportQueryCondition;
import nc.itf.iufo.freereport.extend.IAreaCondition;
import nc.pub.smart.model.SmartModel;
import nc.pub.smart.model.preferences.Parameter;

public class DailyBaseQueryCondition extends ReportQueryCondition {

  private static final long serialVersionUID = 1L;

  private List<Parameter> querycondtion;

  public DailyBaseQueryCondition(boolean isContinue) {
    super(isContinue);
    // TODO Auto-generated constructor stub
  }

  @Override
  public IAreaCondition getAreaConditions(String areaName, SmartModel smartModel) {
    List<Parameter> paramlist = new ArrayList<Parameter>();
    if (null == this.m_repParam) {
      if (null != this.querycondtion) {
        // Set<String> keys = this.querycondtion.keySet();
        // for (String key : keys) {
        // Parameter para = new Parameter();
        // if (key.equals("111")) {
        // para.setOperator("=");
        // }
        // para.setCode(key);
        // para.setName(key);
        // para.setDataType(Integer.valueOf(DATATYPE.DT_STRING));
        // para.setValue(this.querycondtion.get(key));
        // paramlist.add(para);
        // }
        paramlist = this.querycondtion;
      }
    }
    Parameter[] params = paramlist.toArray(new Parameter[paramlist.size()]);
    if (null == super.getDescriptors()) {
      return new com.ufida.report.anareport.base.BaseAreaCondition(null, params);
    }

    return new com.ufida.report.anareport.base.BaseAreaCondition(
        super.getDescriptors(), params);

  }

  public List<Parameter> getQuerycondtion() {
    return this.querycondtion;
  }

  public void setQuerycondtion(List<Parameter> querycondtion) {
    this.querycondtion = querycondtion;
  }

}
