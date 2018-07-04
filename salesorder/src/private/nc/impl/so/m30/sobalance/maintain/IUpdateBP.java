package nc.impl.so.m30.sobalance.maintain;

import nc.vo.so.m30.sobalance.entity.SoBalanceVO;

public interface IUpdateBP {

  SoBalanceVO[] update(SoBalanceVO[] bills, SoBalanceVO[] originBills);

}
