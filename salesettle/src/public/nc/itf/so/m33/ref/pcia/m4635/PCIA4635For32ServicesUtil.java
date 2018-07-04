package nc.itf.so.m33.ref.pcia.m4635;

import nc.bs.framework.common.NCLocator;
import nc.itf.scmpub.reference.uap.group.SysInitGroupQuery;
import nc.pubitf.pcia.m4635.so.m32.IPCIA4635ForSO32Settle;
import nc.vo.pub.BusinessException;
import nc.vo.pubapp.bill.SplitBill;
import nc.vo.pubapp.pattern.exception.ExceptionUtils;
import nc.vo.so.m33.m32.entity.SquareInvBVO;
import nc.vo.so.m33.m32.entity.SquareInvVO;
import nc.vo.so.m33.m32.entity.SquareInvVOUtils;
import nc.vo.so.m33.m32.entity.SquareInvViewVO;
import nc.vo.so.m33.m4c.entity.SquareOutVO;
import nc.vo.so.m33.m4c.entity.SquareOutVOUtils;
import nc.vo.so.m33.m4c.entity.SquareOutViewVO;

/**
 * 利润中心存货销售成本结转单对销售发票审批和取消审批自动结算传存货提供的工具类
 * 
 * @author zhangby5
 * 
 */
public class PCIA4635For32ServicesUtil {

  private PCIA4635For32ServicesUtil() {
    super();
  }

  /**
   * 销售发票普通结算到利润中心存货
   * 
   * @param bills 销售发票
   */
  public static void insert4635ForSO32Settle(SquareInvVO[] bills) {
    if(!SysInitGroupQuery.isPCIAEnabled()){
      return ;
    }
    SquareInvVO[] snewvos = splitSquareInvVOs(bills);
    
    IPCIA4635ForSO32Settle bo =
        NCLocator.getInstance().lookup(IPCIA4635ForSO32Settle.class);
    try {
      
      SquareInvViewVO[] svvos =
          SquareInvVOUtils.getInstance().changeToSaleSquareViewVO(snewvos);
      SquareTOPCIARule<SquareInvViewVO> rule=new SquareTOPCIARule<SquareInvViewVO>(null, svvos);
      SquareInvViewVO[] newview= rule.geToPCIASquareVO(); 
      
      if(newview==null || newview.length==0){
        return ;
      }
      SquareInvVO[]  svos=  SquareInvVOUtils.getInstance().combineBill(newview);
      bo.insert4635ForSO32Settle(svos);
    }
    catch (BusinessException e) {
      ExceptionUtils.wrappException(e);
    }
  }
  
  /**
   * 分单处理
   * 
   * @param sqvos
   * @return
   */
  public static SquareInvVO[] splitSquareInvVOs(SquareInvVO[] sqvos) {
    // 按照IA的要求上游按照来源单据ID+库存组织+仓库进行分单
    SplitBill<SquareInvVO> spb = new SplitBill<SquareInvVO>();
    spb.appendKey(SquareInvBVO.CSRCID);
    spb.appendKey(SquareInvBVO.CSENDSTOCKORGID);
    spb.appendKey(SquareInvBVO.CSENDSTORDOCID);
    SquareInvVO[] snewvos = spb.split(sqvos);
    return snewvos;
  }

  /**
   * 销售发票取消结算
   * 
   * @param csrcids 来源单据销售结算ID
   * @param csrcbids 来源单据销售结算明细ID
   */
  public static void delete4635ForSO32UnSettle(String[] csrcids,
      String[] csrcbids) {
    if(!SysInitGroupQuery.isPCIAEnabled()){
      return ;
    }
    IPCIA4635ForSO32Settle bo =
        NCLocator.getInstance().lookup(IPCIA4635ForSO32Settle.class);
    try {
      bo.delete4635ForSO32UnSettle(csrcids, csrcbids);
    }
    catch (BusinessException e) {
      ExceptionUtils.wrappException(e);
    }
  }

}
