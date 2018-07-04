package nc.pubimpl.so.rule;

import java.util.HashMap;
import java.util.Map;
import java.util.Map.Entry;
import java.util.Set;

import nc.bs.bank_cvp.compile.registry.Register;
import nc.bs.bank_cvp.formulainterface.RefCompilerBS;
import nc.bs.ml.NCLangResOnserver;
import nc.impl.pubapp.pattern.data.vo.VOQuery;
import nc.pubimpl.so.FunctionContex;
import nc.pubitf.so.m30.ReturnAssignMatchVO;
import nc.vo.bank_cvp.compile.datastruct.ArrayValue;
import nc.vo.pub.lang.UFBoolean;
import nc.vo.pubapp.pattern.exception.ExceptionUtils;
import nc.vo.so.mreturncondition.entity.ReturnConditionVO;
import nc.vo.so.mreturnpolicy.entity.ReturnPolicyVO;

/**
 * 匹配退货政策设置
 * 
 * @since 6.0
 * @version 2011-4-19 下午01:53:12
 * @author 祝会征
 */
public class MatchReturnPoliceRule {
  private Map<String, String> reMap = new HashMap<String, String>();

  public Map<String, String> matchReturnPolice(Map<Integer, String> map,
      ReturnAssignMatchVO[] matchvos, UFBoolean check) {
    if (null == map || map.size() == 0) {
      return this.reMap;
    }
    Map<ReturnAssignMatchVO, String> voMap =
        new HashMap<ReturnAssignMatchVO, String>();
    for (ReturnAssignMatchVO vo : matchvos) {
      Integer index = vo.getParaindex();
      if (map.containsKey(index)) {
        String pk_returnpolicy = map.get(index);
        voMap.put(vo, pk_returnpolicy);
      }
    }
    if (check.equals(UFBoolean.TRUE)) {
      this.processReturnPolice(voMap);
    }
    else {
      Set<Entry<ReturnAssignMatchVO, String>> entrys = voMap.entrySet();
      for (Entry<ReturnAssignMatchVO, String> entry : entrys) {
        String key = entry.getValue();
        ReturnAssignMatchVO matchvo = entry.getKey();
        this.reMap.put(matchvo.getCrowno(), key);
      }
    }

    return this.reMap;
  }

  /**
   * 检查退货政策表达式
   * 
   * @param vo
   * @param matchvo
   * @param bill
   */
  private boolean checkExpress(ReturnPolicyVO vo, ReturnAssignMatchVO matchvo) {
    FunctionContex context = new FunctionContex(matchvo);
    String expr = vo.getVexpresscode();
    try {
      Register.getInstance().getAllSMethods();
      String express = this.getExpress(expr, vo.getPk_org());
      ArrayValue v = RefCompilerBS.getExpressionResult(express, context);
      Object objTemp = v.getValue();
      if (objTemp instanceof Boolean) {
        Boolean bln = (Boolean) objTemp;
        if (bln.booleanValue()) {
          return true;
        }
        return false;
      }
      else if (objTemp instanceof String) {
        if (objTemp.equals("true")) {
          return true;
        }
        else if (objTemp.equals("false")) {
          return false;
        }
      }
    }
    catch (Exception e) {
      ExceptionUtils.wrappException(e);
    }
    return true;
  }

  private String getCndtnExpress(String sCndtncode, String pk_org) {
    if (sCndtncode == null || sCndtncode.trim().length() == 0) {
      return null;
    }
    String sWheres =
        " and  dr=0 and vconditioncode='" + sCndtncode + "' and pk_org ='"
            + pk_org + "'";
    VOQuery<ReturnConditionVO> query =
        new VOQuery<ReturnConditionVO>(ReturnConditionVO.class);
    ReturnConditionVO[] vos = query.query(sWheres, null);
    if (vos == null || vos.length == 0) {
      return null;
    }
    return vos[0].getVexpresscode();
  }

  private String getExpress(String sExpcode, String sPk_corp) {
    if (sExpcode == null) {
      return null;
    }
    StringBuffer sExpress = new StringBuffer();
    String stmp = null;
    int ifirst = 0;
    int inext = 0;
    int imid = 0;
    while (true) {
      ifirst = sExpcode.indexOf('(', inext);
      if (ifirst == -1) {
        if (inext != sExpcode.length()) {
          String tempStr = sExpress.toString();
          String tempExpr = tempStr.substring(inext, sExpcode.length());
          sExpress.append(tempExpr);
        }
        break;
      }
      imid = sExpcode.indexOf("judge", inext);
      stmp = sExpcode.substring(inext, imid);
      sExpress.append(stmp);
      inext = sExpcode.indexOf(')', ifirst);
      if (inext == -1) {
        ExceptionUtils
            .wrappBusinessException(nc.vo.ml.NCLangRes4VoTransl.getNCLangRes()
                .getStrByID("4006006_0", "04006006-0035")/*@res "公式错误。"*/);
      }
      stmp = sExpcode.substring(ifirst + 2, inext - 1);
      sExpress.append(this.getCndtnExpress(stmp, sPk_corp));
      inext++;
    }

    return sExpress.toString();
  }

  private void processReturnPolice(Map<ReturnAssignMatchVO, String> voMap) {

    String[] pks = new String[voMap.size()];
    int i = 0;
    for (Entry<ReturnAssignMatchVO, String> entry : voMap.entrySet()) {
      pks[i] = entry.getValue();
      i++;
    }
    // voMap.keySet().toArray(pks);
    VOQuery<ReturnPolicyVO> query =
        new VOQuery<ReturnPolicyVO>(ReturnPolicyVO.class);
    Map<String, ReturnPolicyVO> tempMap = new HashMap<String, ReturnPolicyVO>();
    ReturnPolicyVO[] vos = query.query(pks);
    for (ReturnPolicyVO vo : vos) {
      tempMap.put(vo.getPk_returnpolicy(), vo);
    }
    StringBuffer errMsg = new StringBuffer();
    Set<Entry<ReturnAssignMatchVO, String>> entrys = voMap.entrySet();
    for (Entry<ReturnAssignMatchVO, String> entry : entrys) {
      String key = entry.getValue();
      ReturnAssignMatchVO matchvo = entry.getKey();
      String row = matchvo.getCrowno();
      if (tempMap.size() == 0 || !tempMap.containsKey(key)) {
        errMsg.append(NCLangResOnserver.getInstance().getStrByID("4006006_0",
            "04006006-0129", null, new String[] {
              row
            })/*订单表体下列：{0}检查不通过，引用退货政策为空。\n*/);
        continue;
      }
      ReturnPolicyVO policyVO = tempMap.get(key);
      boolean isTrue = this.checkExpress(policyVO, matchvo);
      if (!isTrue) {
        errMsg.append(NCLangResOnserver.getInstance().getStrByID("4006006_0",
            "04006006-0130", null, new String[] {
              row, policyVO.getVpolicycode(), policyVO.getVpolicyname()
            })/*行号为:{0}的订单表体行检查不通过，引用退货政策编码为:{1}，退货名称为：{2}\n*/);
        continue;
      }
      this.reMap.put(matchvo.getCrowno(), key);
    }
    if (errMsg.length() > 0) {
      ExceptionUtils.wrappBusinessException(errMsg.toString());
    }
  }
}
