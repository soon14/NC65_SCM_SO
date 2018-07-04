package nc.itf.so.m30.ref.credit;

import nc.bs.framework.common.NCLocator;
import nc.pubitf.credit.billcreditquery.ICreditQuery;
import nc.vo.credit.billcreditquery.entity.CreditInfoVO;
import nc.vo.credit.billcreditquery.para.QueryParaForBill;
import nc.vo.pub.BusinessException;
import nc.vo.pubapp.pattern.exception.ExceptionUtils;

/**
 * 信用所有服务工具类
 * 
 * @since 6.0
 * @version 2011-5-4 上午11:43:39
 * @author 刘志伟
 */
public class CreditServicesUtil {

  public static CreditInfoVO creditQueryForSoSideHead(String csettleorg,
      String customerid, String ctranstypeid) throws BusinessException {
    ICreditQuery service = NCLocator.getInstance().lookup(ICreditQuery.class);

    CreditInfoVO[] rets = null;
    QueryParaForBill para = new QueryParaForBill();
    para.setCfinanceorgid(csettleorg);
    para.setCcustomerid(customerid);
    para.setCtrantypeid(ctranstypeid);
    // QueryParaForBill[] vos = new QueryParaForBill[1];
    try {
      rets = service.queryCreditForBill(new QueryParaForBill[] {
        para
      }, null);
      return rets[0];
    }
    catch (BusinessException e) {
      ExceptionUtils.marsh(e);
    }
    return null;
  }
}
