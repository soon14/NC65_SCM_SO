package nc.vo.so.m30.entity;

import java.io.Serializable;
import java.util.Map;

import nc.vo.bd.feature.ffile.entity.AggFFileVO;
import nc.vo.pub.lang.UFDouble;
import nc.vo.so.m30.sobalance.entity.SoBalanceVO;
import nc.vo.so.m35.entity.ArsubViewVO;

public class SaleOrderUserObject implements Serializable {

  private static final long serialVersionUID = -3771119361183908707L;

  private OffsetTempVO offsetTempVO;

  private SoBalanceVO soBalanceVO;

  private ArsubViewVO[] arsubViews;

  private UFDouble thisGatheringMny;
  
  private Map<String, AggFFileVO> aggffilevomap;
  
  
  /**
   * 单据是否从前台界面保存，而不是外部交换平台导入的（考虑到效率问题，前台界面保存的单据不进行效率检查）
   */
  private boolean isclientsave=false;

  
  /**
   * @return 获取单据是否从前台界面保存的
   */
  public boolean isIsclientsave() {
    return isclientsave;
  }

  
  /**
   * 设置单据是否从前台界面保存的
   * @param isclientsave
   */
  public void setIsclientsave(boolean isclientsave) {
    this.isclientsave = isclientsave;
  }
  
  
  

  public OffsetTempVO getOffsetTempVO() {
    return this.offsetTempVO;
  }

  public void setOffsetTempVO(OffsetTempVO offsetTempVO) {
    this.offsetTempVO = offsetTempVO;
  }

  public SoBalanceVO getSoBalanceVO() {
    return this.soBalanceVO;
  }

  public void setSoBalanceVO(SoBalanceVO soBalanceVO) {
    this.soBalanceVO = soBalanceVO;
  }

  public ArsubViewVO[] getArsubViews() {
    return this.arsubViews;
  }

  public void setArsubViews(ArsubViewVO[] arsubViews) {
    this.arsubViews = arsubViews;
  }

  public UFDouble getThisGatheringMny() {
    return this.thisGatheringMny;
  }

  public void setThisGatheringMny(UFDouble thisGatheringMny) {
    this.thisGatheringMny = thisGatheringMny;
  }

  public Map<String, AggFFileVO> getAggffilevomap() {
	  return aggffilevomap;
  }

  public void setAggffilevomap(Map<String, AggFFileVO> aggffilevomap) {
	this.aggffilevomap = aggffilevomap;
  }

}
