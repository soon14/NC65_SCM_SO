package nc.vo.so.m33.pub.util;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import nc.impl.pubapp.env.BSContext;
import nc.itf.scmpub.reference.uap.para.SysParaInitQuery;
import nc.vo.pub.lang.UFBoolean;
import nc.vo.so.pub.res.ParameterList;
import nc.vo.so.pub.util.SOSysParaInitUtil;

public class ParaUtils {

  private ParaUtils() {
    super();
  }

  /**
   * 含税是否优先
   * 
   * @param pk_group
   */
  public static UFBoolean getBTaxPrior(String pk_group) {
    UFBoolean so26 = null;
    so26 = SysParaInitQuery.getParaBoolean(pk_group, "SO26");
    return so26 == null ? UFBoolean.FALSE : so26;
  }

  public static Map<String, List<String>> getifBaseSignInvoiceBiz(
      String[] csaleorgid) {
    Map<String, List<String>> map = null;

    map =
        SOSysParaInitUtil.queryBatchParaValues(csaleorgid,
            ParameterList.SO16.getCode());
    map = new HashMap<String, List<String>>();

    return map;
  }

  public static String getPK_Group() {
    BSContext proxy = BSContext.getInstance();
    return proxy.getGroupID();
  }

  public static String getPK_User() {
    BSContext proxy = BSContext.getInstance();
    return proxy.getUserID();
  }

}
