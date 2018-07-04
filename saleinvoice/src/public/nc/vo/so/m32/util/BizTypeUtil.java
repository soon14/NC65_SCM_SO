package nc.vo.so.m32.util;

import nc.itf.scmpub.reference.uap.pf.PfServiceScmUtil;
import nc.vo.pub.pfflow04.MessagedriveVO;
import nc.vo.scmpub.res.billaction.SOBillAction;

/**
 * <p>
 * <b>本类主要完成以下功能：</b>
 * <ul>
 * <li>功能条目
 * </ul>
 * <p>
 * 
 * @version 本版本号 6.0
 * @since
 * @author fengjb
 * @time 2010-8-9 上午11:21:57
 */
public class BizTypeUtil {

  private static BizTypeUtil instance = new BizTypeUtil();

  /**
   * BizTypeUtil 的构造子
   */
  private BizTypeUtil() {
    // 私有化构造子
  }

  public static BizTypeUtil getInstance() {
    return BizTypeUtil.instance;
  }

  /**
   * 方法功能描述：业务流程上，销售发票是否配置差额传应收。
   * <p>
   * <b>参数说明</b>
   * 
   * @param bizType
   * @return <p>
   * @author fengjb
   * @time 2010-8-5 下午02:44:41
   */
  public boolean isAdjustIncome(String bizType, String vtrantypecode) {

    MessagedriveVO[] driveVOs =
        PfServiceScmUtil.queryAllMsgdrvVOs(vtrantypecode, bizType,
            SOBillAction.SaleInvoiceApprove.getCode());

    // 没有配置驱动动作
    if (null == driveVOs || driveVOs.length == 0) {
      return false;
    }
    boolean isAdjustInc = false;
    for (MessagedriveVO drive : driveVOs) {
      // 配置了差额传应收
      if (SOBillAction.SaleInvoiceADJUSTINCOME.getCode().equals(
          drive.getActiontype())) {
        isAdjustInc = true;
        break;
      }
    }
    return isAdjustInc;
  }
}
