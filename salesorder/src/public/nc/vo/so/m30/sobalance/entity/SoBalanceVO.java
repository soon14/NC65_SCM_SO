package nc.vo.so.m30.sobalance.entity;

import nc.vo.pub.lang.UFBoolean;
import nc.vo.pubapp.pattern.model.entity.bill.AbstractBill;
import nc.vo.pubapp.pattern.model.meta.entity.bill.BillMetaFactory;
import nc.vo.pubapp.pattern.model.meta.entity.bill.IBillMeta;

@nc.vo.annotation.AggVoInfo(parentVO = 
    "nc.vo.so.m30.sobalance.entity.SoBalanceHVO")
public class SoBalanceVO extends AbstractBill {

  /**
   *监听标志，当订单收款时，收款信息通过监听应收单而获取（GatheringAddAfterListenerAction），
   *此时通过listenerflag来标识SoBananceVO是否来自于应收，如果是是来自于应收，那么修改保存收款单
   *时就不用进行订单收款金额与价税合计之间的比较，如果订单收款时更改的是金额信息，而业务上是不允许修改
   *金额信息的，那么在应收这边会进行校验控制，而如果修改的不是金额信息，那么订单收款时是允许保存的。
   */
  private UFBoolean listenerflag;
  
  public static final String ENTITYNAME = "so.so_balance";

  private static final long serialVersionUID = 3914562600056273535L;

  @Override
  public IBillMeta getMetaData() {
    IBillMeta billMeta =
        BillMetaFactory.getInstance().getBillMeta(SoBalanceVO.ENTITYNAME);
    return billMeta;
  }

  @Override
  public SoBalanceHVO getParentVO() {
    return (SoBalanceHVO) super.getParentVO();
  }

  public void setParentVO(SoBalanceHVO headVO) {
    this.setParent(headVO);
  }

  @Override
  public SoBalanceBVO[] getChildrenVO() {
    return (SoBalanceBVO[]) super.getChildrenVO();
  }

  public void setChildrenVO(SoBalanceBVO[] bodyVO) {
    super.setChildrenVO(bodyVO);
  }
  
  public UFBoolean getListenerflag() {
    return listenerflag;
  }

  public void setListenerflag(UFBoolean listenerflag) {
    this.listenerflag = listenerflag;
  }

}
