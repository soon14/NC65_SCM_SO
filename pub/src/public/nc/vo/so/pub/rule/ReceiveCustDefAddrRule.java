package nc.vo.so.pub.rule;

import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;

import nc.itf.scmpub.reference.uap.bd.addrdoc.AddrdocPubService;
import nc.itf.scmpub.reference.uap.bd.customer.CustomerPubService;
import nc.vo.pubapp.pattern.pub.PubAppTool;
import nc.vo.so.m30.entity.SaleOrderBVO;
import nc.vo.so.m30.entity.SaleOrderHVO;
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
public class ReceiveCustDefAddrRule {

  private IKeyRela keyRela;

  private IKeyValue keyValue;

  /**
   * 
   * @param keyValue
   */
  public ReceiveCustDefAddrRule(IKeyValue keyValue) {
    this.keyValue = keyValue;
    this.keyRela = new SOKeyRela();
  }

  /**
   * 
   * @param keyValue
   * @param keyRela
   */
  public ReceiveCustDefAddrRule(IKeyValue keyValue, IKeyRela keyRela) {
    this.keyValue = keyValue;
    this.keyRela = keyRela;
  }

  /**
   * 
   * @param rows
   */
  public void setCustDefaultAddress(int[] rows) {

    Set<String> setreceivecust = new HashSet<String>();
    for (int row : rows) {
      String receivecustomerid =
          this.keyValue.getBodyStringValue(row,
              this.keyRela.getCreceivecustidKey());
      if (!PubAppTool.isNull(receivecustomerid)) {
        setreceivecust.add(receivecustomerid);
      }
    }
    Map<String, String> mapadd = new HashMap<String, String>();
    Map<String, String> maparea = new HashMap<String, String>();
    Map<String, String> mapaddoc = new HashMap<String, String>();
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
        // 取默认收货地区
        defadds = new String[setadd.size()];
        setadd.toArray(defadds);
        String[] defareapks =
            CustomerPubService.getAreaPksByConsignAddress(defadds);
        for (int i = 0, iloop = defadds.length; i < iloop; i++) {
          maparea.put(defadds[i], defareapks[i]);
        }
        // 取默认收货地点
        mapaddoc = AddrdocPubService.getAddressDocPksByConsignAddress(defadds);
      }
    }
    String headreccust =
        this.keyValue.getHeadStringValue(SOItemKey.CRECEIVECUSTID);
    String headrecadd =
        this.keyValue.getHeadStringValue(SOItemKey.CRECEIVEADDRID);
    for (int row : rows) {
      String receivecustomerid =
          this.keyValue.getBodyStringValue(row, SOItemKey.CRECEIVECUSTID);
      if (PubAppTool.isNull(receivecustomerid)) {
        continue;
      }
      if (null != headreccust && headreccust.equals(receivecustomerid)) {
        if (PubAppTool.isNull(headrecadd)) {
          headrecadd = mapadd.get(receivecustomerid);
        }
        this.keyValue.setBodyValue(row, this.keyRela.getCreceiveaddridKey(),
            headrecadd);

        this.keyValue.setBodyValue(row, this.keyRela.getCreceiveareaidKey(),
            maparea.get(headrecadd));

        this.keyValue.setBodyValue(row, this.keyRela.getCreceiveadddocidKey(),
            mapaddoc.get(headrecadd));

      }
      else {
        String defadd = mapadd.get(receivecustomerid);
        this.keyValue.setBodyValue(row, this.keyRela.getCreceiveaddridKey(),
            defadd);

        this.keyValue.setBodyValue(row, this.keyRela.getCreceiveareaidKey(),
            maparea.get(defadd));

        this.keyValue.setBodyValue(row, this.keyRela.getCreceiveadddocidKey(),
            mapaddoc.get(defadd));
      }

    }
  }

  /**
   * 
   * @param rows
   */
  public void setCustDefaultAddress2(int[] rows) {

    Set<String> setreceivecust = new HashSet<String>();
    String cust = this.keyValue.getHeadStringValue(SOItemKey.CRECEIVECUSTID);
    setreceivecust.add(cust);
    for (int row : rows) {
      String receivecustomerid =
          this.keyValue.getBodyStringValue(row,
              this.keyRela.getCreceivecustidKey());
      if (!PubAppTool.isNull(receivecustomerid)) {
        setreceivecust.add(receivecustomerid);
      }
    }
    Map<String, String> mapadd = new HashMap<String, String>();
    Map<String, String> maparea = new HashMap<String, String>();
    Map<String, String> mapaddoc = new HashMap<String, String>();
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
        // 取默认收货地区
        defadds = new String[setadd.size()];
        setadd.toArray(defadds);
        String[] defareapks =
            CustomerPubService.getAreaPksByConsignAddress(defadds);
        for (int i = 0, iloop = defadds.length; i < iloop; i++) {
          maparea.put(defadds[i], defareapks[i]);
        }
        // 取默认收货地点
        mapaddoc = AddrdocPubService.getAddressDocPksByConsignAddress(defadds);
      }
    }
    // 给表头收货地址赋值
    cust = this.keyValue.getHeadStringValue(SOItemKey.CRECEIVECUSTID);
    String headdefadd = mapadd.get(cust);
    this.keyValue.setHeadValue(SOItemKey.CRECEIVEADDRID, headdefadd);
    // 给表体收货地址赋值
    for (int row : rows) {
      String receivecustomerid =
          this.keyValue.getBodyStringValue(row, SOItemKey.CRECEIVECUSTID);
      if (PubAppTool.isNull(receivecustomerid)) {
        continue;
      }
      String defadd = mapadd.get(receivecustomerid);
      this.keyValue.setBodyValue(row, this.keyRela.getCreceiveaddridKey(),
          defadd);

      this.keyValue.setBodyValue(row, this.keyRela.getCreceiveareaidKey(),
          maparea.get(defadd));

      this.keyValue.setBodyValue(row, this.keyRela.getCreceiveadddocidKey(),
          mapaddoc.get(defadd));

    }
  }
  
  /**
   * @param rows
   */
  public void setCustAddDocByAddr(int[] rows) {
    String pk_address = this.keyValue.getHeadStringValue(SaleOrderHVO.CHRECEIVEADDID);
    
    //地区
    String area = null;
    //地点
    String location = null;
    if (!PubAppTool.isNull(pk_address)) {
      //根据收货地址获取收货地区
      String[] defareapks = CustomerPubService.getAreaPksByConsignAddress(new String[]{pk_address});
      if(defareapks != null && defareapks.length > 0){
        area = defareapks[0];
      }

      //根据收货地址获取收货地点
      Map<String, String> mapaddoc = AddrdocPubService.getAddressDocPksByConsignAddress(new String[]{pk_address});
      location = mapaddoc.get(pk_address);
    }

    for (int row : rows) {
      this.keyValue.setBodyValue(row, SaleOrderBVO.CRECEIVEADDRID, pk_address);
      this.keyValue.setBodyValue(row, this.keyRela.getCreceiveareaidKey(), area);
      this.keyValue.setBodyValue(row, this.keyRela.getCreceiveadddocidKey(), location);
    }
  }

}
