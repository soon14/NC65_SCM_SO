package nc.bs.so.pub.rule;

import java.util.Map;
import java.util.Set;

import org.apache.commons.lang.ArrayUtils;

import nc.vo.bd.cust.CustomerVO;
import nc.vo.bd.cust.finance.CustFinanceVO;
import nc.vo.ml.NCLangRes4VoTransl;
import nc.vo.pubapp.AppContext;
import nc.vo.pubapp.pattern.exception.ExceptionUtils;
import nc.vo.pubapp.pattern.pub.MapList;

import nc.itf.org.IOrgConst;
import nc.itf.scmpub.reference.uap.bd.customer.CustomerPubService;

/**
 * 检查客户是否分属于集团或者当前应收组织
 * 
 * @since 6.0
 * @version 2011-8-15 下午01:54:55
 * @author zhangcheng
 */
public class CustDistributeCheck {

  /**
   * <客户ID,应收组织OID>
   * 
   * @param map
   */
  public void check(Map<String, String> map) {
    int size = map.size();
    String[] cusIDs = map.keySet().toArray(new String[size]);
    String[] fields = new String[] {
      CustomerVO.PK_CUSTOMER, CustomerVO.PK_ORG
    };
    MapList<String, String> notAssinOrgs = new MapList<String, String>();
    Map<String, CustomerVO> mpkcut =
        CustomerPubService.getCustomerVOByPks(cusIDs, fields);
    String pk_group = AppContext.getInstance().getPkGroup();
    for (String cusID : cusIDs) {
      CustomerVO cusvo = mpkcut.get(cusID);
      String pk_org = cusvo.getPk_org();
      String arorg = map.get(cusID);
      if (!pk_org.equals(IOrgConst.GLOBEORG) && !pk_org.equals(pk_group)
          && !pk_org.equals(arorg)) {
        notAssinOrgs.put(arorg, cusID);
      }
    }
    if (notAssinOrgs.size() > 0) {
      Set<String> fiorgids = notAssinOrgs.keySet();
      for (String fiorgid : fiorgids) {
        String[] customerids = notAssinOrgs.get(fiorgid).toArray(new String[0]);
        String[] names = new String[] {
          CustFinanceVO.PK_CUSTFINANCE
        };
        CustFinanceVO[] fivos =
            CustomerPubService.getCustFinanceVO(customerids, fiorgid, names);
        if (ArrayUtils.isEmpty(fivos)) {
          ExceptionUtils.wrappBusinessException(NCLangRes4VoTransl
              .getNCLangRes().getStrByID("4006004_0", "04006004-0223")/* 客户必须是全局、
                                                                       * 集团客户或者当前应收组织的客户
                                                                       * ！ */);
        }
      }

    }
  }

}
