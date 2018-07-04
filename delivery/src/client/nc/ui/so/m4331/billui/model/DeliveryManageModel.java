package nc.ui.so.m4331.billui.model;

import java.util.Map;

import nc.bs.framework.common.NCLocator;
import nc.itf.so.pub.para.IWeightAndVolMaintain;
import nc.ui.pubapp.uif2app.model.BillManageModel;
import nc.vo.pub.BusinessException;
import nc.vo.pubapp.AppContext;
import nc.vo.pubapp.pattern.exception.ExceptionUtils;

public class DeliveryManageModel extends BillManageModel {
  private String srcType;

  public String getSourceType() {
    return this.srcType;
  }
  
  private Map<String, String> cacheWeightAndVolName;

  public Map<String, String> getWeightAndVolName() {
	  
	if(cacheWeightAndVolName!=null){
		return cacheWeightAndVolName;
	}
	  
    IWeightAndVolMaintain service =
        NCLocator.getInstance().lookup(IWeightAndVolMaintain.class);
    String pk_group = AppContext.getInstance().getPkGroup();
    try {
      Map<String, String> map = service.getWeightAndVolName(pk_group);
      this.setWeightAndVolName(map);
    }
    catch (BusinessException e) {
      ExceptionUtils.wrappException(e);
    }
    return cacheWeightAndVolName;
  }

  public void setSourceType(String srctype) {
    this.srcType = srctype;
  }
  
  public void setWeightAndVolName(Map<String, String> map){
	  this.cacheWeightAndVolName = map;
  }
}
