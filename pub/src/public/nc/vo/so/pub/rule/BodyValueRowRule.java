package nc.vo.so.pub.rule;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import nc.vo.pub.lang.UFBoolean;
import nc.vo.pubapp.pattern.pub.PubAppTool;
import nc.vo.so.pub.SOItemKey;
import nc.vo.so.pub.keyvalue.IKeyValue;
import nc.vo.so.salequotation.entity.SalequotationBVO;

/**
 * 
 * @since 6.1
 * @version 2012-12-20 16:36:37
 * @author liangjm
 */
public class BodyValueRowRule {

  private IKeyValue keyValue;

  /**
   * 
   * 
   * @param keyValue
   */
  public BodyValueRowRule(IKeyValue keyValue) {
    this.keyValue = keyValue;
  }

  /**
   * 返回物料非空行
   * 
   * @return r
   */
  public int[] getMarNotNullRows() {
    List<Integer> alnotnullrow = new ArrayList<Integer>();
    int bodycount = this.keyValue.getBodyCount();
    for (int i = 0; i < bodycount; i++) {
      String marterialvid =
          this.keyValue.getBodyStringValue(i, SOItemKey.CMATERIALVID);
      if (PubAppTool.isNull(marterialvid)) {
        continue;
      }
      alnotnullrow.add(Integer.valueOf(i));
    }
    int notnullsize = alnotnullrow.size();
    int[] rows = new int[notnullsize];
    for (int i = 0; i < notnullsize; i++) {
      rows[i] = alnotnullrow.get(i).intValue();
    }
    return rows;
  }
  
  
  /**
   * 返回表体字段非空行
   * 
   * @return r  fields
   */
  public int[] getFieldNotNullRows(String field) {
    List<Integer> alnotnullrow = new ArrayList<Integer>();
    int bodycount = this.keyValue.getBodyCount();
    for (int i = 0; i < bodycount; i++) {
      String value =
          this.keyValue.getBodyStringValue(i, field);
      if (PubAppTool.isNull(value)) {
        continue;
      }
      alnotnullrow.add(Integer.valueOf(i));
    }
    int notnullsize = alnotnullrow.size();
    int[] rows = new int[notnullsize];
    for (int i = 0; i < notnullsize; i++) {
      rows[i] = alnotnullrow.get(i).intValue();
    }
    return rows;
  }
  

  /**
   * 销售报价单返回物料非空行
   * 
   * @return r
   */
  public int[] get4310MarNotNullRows() {
    List<Integer> alnotnullrow = new ArrayList<Integer>();
    int bodycount = this.keyValue.getBodyCount();
    for (int i = 0; i < bodycount; i++) {
      String marterialvid =
          this.keyValue.getBodyStringValue(i, SalequotationBVO.PK_MATERIAL_V);
      if (PubAppTool.isNull(marterialvid)) {
        continue;
      }
      alnotnullrow.add(Integer.valueOf(i));
    }
    int notnullsize = alnotnullrow.size();
    int[] rows = new int[notnullsize];
    for (int i = 0; i < notnullsize; i++) {
      rows[i] = alnotnullrow.get(i).intValue();
    }
    return rows;
  }

  /**
   * 返回物料不为空且字段key值为空行
   * 
   * @param bodykey
   * @return gg
   */
  public int[] getValueNullRows(String bodykey) {

    List<Integer> alvaluenullrow = new ArrayList<Integer>();
    int bodycount = this.keyValue.getBodyCount();
    for (int i = 0; i < bodycount; i++) {
      String marterialvid =
          this.keyValue.getBodyStringValue(i, SOItemKey.CMATERIALVID);
      if (PubAppTool.isNull(marterialvid)) {
        continue;
      }
      String value = this.keyValue.getBodyStringValue(i, bodykey);
      if (PubAppTool.isNull(value)) {
        alvaluenullrow.add(Integer.valueOf(i));
      }
    }
    int notnullsize = alvaluenullrow.size();
    int[] rows = new int[notnullsize];
    for (int i = 0; i < notnullsize; i++) {
      rows[i] = alvaluenullrow.get(i).intValue();
    }
    return rows;
  }

  /**
   * 
   * 
   * @return ff
   */
  public int[] getLargessRows() {

    List<Integer> alvaluenullrow = new ArrayList<Integer>();
    int bodycount = this.keyValue.getBodyCount();
    for (int i = 0; i < bodycount; i++) {
      String marterialvid =
          this.keyValue.getBodyStringValue(i, SOItemKey.CMATERIALVID);
      if (PubAppTool.isNull(marterialvid)) {
        continue;
      }
      UFBoolean larflag =
          this.keyValue.getBodyUFBooleanValue(i, SOItemKey.BLARGESSFLAG);
      if (null != larflag && larflag.booleanValue()) {
        alvaluenullrow.add(Integer.valueOf(i));
      }
    }
    int notnullsize = alvaluenullrow.size();
    int[] rows = new int[notnullsize];
    for (int i = 0; i < notnullsize; i++) {
      rows[i] = alvaluenullrow.get(i).intValue();
    }
    return rows;

  }

  /*
   * 获取需要计算的行，当物料id为空， 不需要计算，---XXXX 本币位和原币币种一样 也不需要计算，XXXX--
   * 逻辑错误，采购本位币和订单本位币不同需要计算 65集成测试问题：NCdp205451657
   * by zhangby5 
   */
  public int[] getCalRatesRows(Map<String, String> srcccurrencyidMap) {
    List<Integer> alnotnullrow = new ArrayList<Integer>();
    int bodycount = this.keyValue.getBodyCount();
    for (int i = 0; i < bodycount; i++) {
      String marterialvid =
          this.keyValue.getBodyStringValue(i, SOItemKey.CMATERIALVID);
      if (PubAppTool.isNull(marterialvid)) {
        continue;
      }
      String csrcbid = 
          this.keyValue.getBodyStringValue(i, SOItemKey.CSRCBID);
      String srccurrencyid = srcccurrencyidMap.get(csrcbid);
      String ccurrencyid =
          this.keyValue.getBodyStringValue(i, SOItemKey.CCURRENCYID);
      if(PubAppTool.isEqual(ccurrencyid, srccurrencyid)){
        continue;
      }
      alnotnullrow.add(Integer.valueOf(i));
    }
    int notnullsize = alnotnullrow.size();
    int[] rows = new int[notnullsize];
    for (int i = 0; i < notnullsize; i++) {
      rows[i] = alnotnullrow.get(i).intValue();
    }
    return rows;
  }
}
