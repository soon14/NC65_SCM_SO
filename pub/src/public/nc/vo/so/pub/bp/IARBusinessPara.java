package nc.vo.so.pub.bp;

import nc.vo.pub.BusinessException;
import nc.vo.pub.pfflow04.MessagedriveVO;

public interface IARBusinessPara {

  void checkMsgDrv(MessagedriveVO[] message) throws BusinessException;

  String getDefaultDestAction();

  String getDefaultDestBill();
}
