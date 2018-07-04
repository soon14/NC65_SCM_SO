package nc.vo.so.pub.rule;

import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;

import nc.itf.scmpub.reference.uap.bd.customer.CustomerPubService;
import nc.vo.pubapp.pattern.pub.PubAppTool;
import nc.vo.so.pub.SOItemKey;
import nc.vo.so.pub.keyvalue.IKeyRela;
import nc.vo.so.pub.keyvalue.IKeyValue;
import nc.vo.so.pub.keyvalue.SOKeyRela;

/**
 * 收货地址公共规则
 * 
 * @since 6.3
 * @version 2013-05-21 20:03:33
 * @author liujingn
 */
public class RecCustDefAddrByHeadRecInfo {

  private IKeyRela keyRela;

  private IKeyValue keyValue;

  /**
   * 
   * @param keyValue
   */
  public RecCustDefAddrByHeadRecInfo(IKeyValue keyValue) {
    this.keyValue = keyValue;
    this.keyRela = new SOKeyRela();
  }

  /**
   * 
   * @param keyValue
   * @param keyRela
   */
  public RecCustDefAddrByHeadRecInfo(IKeyValue keyValue, IKeyRela keyRela) {
    this.keyValue = keyValue;
    this.keyRela = keyRela;
  }

  /**
   * 
   * @param rows
   */
  public void setCustDefaultAddress(int[] rows) {
    String receivecustomerid =
        this.keyValue.getHeadStringValue(SOItemKey.CRECEIVECUSTID);
    Set<String> setreceivecust = new HashSet<String>();

    if (!PubAppTool.isNull(receivecustomerid)) {
      setreceivecust.add(receivecustomerid);
    }

    Map<String, String> mapadd = new HashMap<String, String>();
    if (setreceivecust.size() > 0) {
      String[] receivecusts = new String[setreceivecust.size()];
      setreceivecust.toArray(receivecusts);
      String pk_org =
          this.keyValue.getHeadStringValue(this.keyRela.getPk_orgKey());
      // 默认收货地址
      String[] defadds =
          CustomerPubService.getDefaultAddresses(receivecusts, pk_org);
      if (null != defadds && defadds.length > 0) {
        Set<String> setadd = new HashSet<String>();
        for (int i = 0, iloop = receivecusts.length; i < iloop; i++) {
          mapadd.put(receivecusts[i], defadds[i]);
          if (!PubAppTool.isNull(defadds[i])) {
            setadd.add(defadds[i]);
          }
        }

      }
    }
    String defadd = mapadd.get(receivecustomerid);
    this.keyValue.setHeadValue(SOItemKey.CRECEIVEADDRID, defadd);
    for (int row : rows) {
      if (PubAppTool.isNull(receivecustomerid)) {
        continue;
      }

      this.keyValue.setBodyValue(row, this.keyRela.getCreceiveaddridKey(),
          defadd);

    }
  }
}
