package nc.vo.so.pub.bp;

import java.io.Serializable;

import nc.vo.pub.BusinessException;
import nc.vo.pub.pfflow04.MessagedriveVO;

public class SOSaleBusinessPara implements IARBusinessPara, Serializable {

  /**
   * 
   */
  private static final long serialVersionUID = -231959508422741092L;

  @Override
  public void checkMsgDrv(MessagedriveVO[] message) throws BusinessException {
    // TODO
  }

  @Override
  public String getDefaultDestAction() {
    return "SAVE";
  }

  @Override
  public String getDefaultDestBill() {
    return "D2";
  }

}
