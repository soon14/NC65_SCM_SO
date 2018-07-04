package nc.vo.so.upgrade;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import nc.impl.pubapp.pattern.database.DataAccessUtils;
import nc.vo.pub.JavaType;
import nc.vo.pubapp.pattern.data.IRowSet;
import nc.vo.so.pub.enumeration.SOFInvoiceKey2Code;
import nc.vo.so.pub.res.ParameterList;

/**
 * @description
 *              SO28参数63对参数值解密和加密处理（减少字符串的长度），对于历史数据没有处理升级
 * @scene
 * 
 * @param
 * 
 * 
 * @since 6.5
 * @version 2015-11-12 下午7:03:26
 * @author 刘景
 */
public class SO28ParaUpgrade {

  private static Map<String, String> mapTransKeyToCode =
      new HashMap<String, String>();

  /**
   * 升级处理
   */
  public static void Upgrade() {

    // 1.查询历史参数数据
    String[][] sysinits = getSysinit();
    if (sysinits == null) {
      return;
    }
    // 2. 初始化参数值
    initTransKeyToCode();

    // 3.对历史数据进行编码
    Map<String, String> sysinitmap = getConverSysInit(sysinits);

    // 4.更新参数
    updateSysinit(sysinitmap);
  }

  private static void initTransKeyToCode() {
    for (SOFInvoiceKey2Code rule : SOFInvoiceKey2Code.values()) {
      mapTransKeyToCode.put(rule.getKey(), rule.getCode());
    }
  }

  private static Map<String, String> getConverSysInit(String[][] sysinits) {

    Map<String, String> sysinitmap = new HashMap<String, String>();
    for (String[] sysinit : sysinits) {
      if (sysinit[1] == null) {
        continue;
      }
      if (!sysinit[1].startsWith(ParameterList.DOLLER)) {
        sysinitmap.put(sysinit[0], getTransKeyToCode(sysinit[1]));
      }
    }
    return sysinitmap;

  }

  private static String[][] getSysinit() {
    DataAccessUtils accessutil = new DataAccessUtils();
    String sql =
        "select pk_sysinit,value from pub_sysinit where initcode='SO28_V' and dr=0";
    IRowSet rs = accessutil.query(sql);
    String[][] sysinits = rs.toTwoDimensionStringArray();
    return sysinits;
  }

  private static void updateSysinit(Map<String, String> sysinitmap) {
    if (sysinitmap.size() == 0) {
      return;
    }
    List<List<Object>> data = new ArrayList<List<Object>>();
    for (Map.Entry<String, String> entry : sysinitmap.entrySet()) {
      List<Object> obj = new ArrayList<Object>();
      obj.add(entry.getValue());
      obj.add(entry.getKey());
      data.add(obj);
    }
    String updatesql = "update pub_sysinit set value = ? where pk_sysinit=?";
    JavaType[] types = new JavaType[] {
      JavaType.String, JavaType.String
    };
    DataAccessUtils accessutil = new DataAccessUtils();
    accessutil.update(updatesql, types, data);

  }

  /**
   * 获取密码值对照Map
   * 
   * @return Map<String, String>
   */
  private static String getTransKeyToCode(String sysinitvalule) {
    String[] allrightValues = sysinitvalule.split(ParameterList.BIGSPLITKEY);
    StringBuffer newvalule = new StringBuffer();
    newvalule.append(ParameterList.DOLLER);
    for (String allrightValue : allrightValues) {
      String[] rightValues = allrightValue.split(ParameterList.SPLITKEY);
      for (String rightValue : rightValues) {
        String value = mapTransKeyToCode.get(rightValue);
        newvalule.append(value);
      }
      newvalule.append(ParameterList.BIGSPLITKEY);
    }

    int len = newvalule.length();
    if (len > 0) {
      newvalule.deleteCharAt(len - 1);
    }
    return newvalule.toString();
  }
}
