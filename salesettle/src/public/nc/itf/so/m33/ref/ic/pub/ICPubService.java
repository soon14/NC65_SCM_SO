package nc.itf.so.m33.ref.ic.pub;

import nc.bs.framework.common.NCLocator;
import nc.pubitf.ic.transtype.IInOutTransTypeQueryService;
import nc.vo.ic.transtype.TransTypeExtendVO;
import nc.vo.pub.BusinessException;

public class ICPubService {

  private ICPubService() {
    super();
  }
  
  /**
   * 方法功能描述：取销售出库单交易类型
   * <p>
   * <b>examples:</b>
   * <p>
   * 使用示例
   * <p>
   * <b>参数说明</b>
   * 
   * @param transTypeCode -- 销售出库单交易类型
   * @return
   * @throws BusinessException
   *           <p>
   * @author zhangcheng
   * @time 2010-8-11 上午09:21:43
   */
  public static TransTypeExtendVO[] queryTransType(String[] transTypeCode)
      throws BusinessException {
    IInOutTransTypeQueryService ioutSvr =
        NCLocator.getInstance().lookup(IInOutTransTypeQueryService.class);
    TransTypeExtendVO[] ttEvos = ioutSvr.query(transTypeCode);
    return ttEvos;
  }

}
