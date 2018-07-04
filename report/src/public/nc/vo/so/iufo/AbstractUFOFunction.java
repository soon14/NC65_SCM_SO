package nc.vo.so.iufo;

import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;

import com.ufsoft.iufo.util.parser.IFuncType;

import nc.vo.bd.accessor.IBDData;
import nc.vo.jcom.lang.StringUtil;
import nc.vo.pub.SuperVOUtil;
import nc.vo.pub.lang.UFDate;
import nc.vo.pub.lang.UFDouble;
import nc.vo.pubapp.pattern.pub.PubAppTool;
import nc.vo.scmpub.fetchfunc.FuncRegister;
import nc.vo.scmpub.util.TimeUtils;
import nc.vo.so.m32.entity.SaleInvoiceHVO;
import nc.vo.so.pub.SOItemKey;

import nc.itf.fi.iuforeport.IFuncNCBatch;
import nc.itf.fi.iuforeport.ResultVO;
import nc.itf.fi.iuforeport.UfoParseException;
import nc.itf.fi.iuforeport.UfoSimpleObject;
import nc.itf.scmpub.reference.uap.bd.customer.CustomerBaseClassPubService;
import nc.itf.scmpub.reference.uap.bd.material.MaterialBaseClassPubService;
import nc.itf.scmpub.reference.uap.org.DeptPubService;

import nc.bs.framework.common.InvocationInfoProxy;

public abstract class AbstractUFOFunction implements IFuncNCBatch {

  private static final long serialVersionUID = -3631291993535391210L;

  @Override
  public ResultVO[] callFunc(String[] strFuncNames, String[] strParams)
      throws Exception {
    UFOFuncParaVO[] paravos = this.constructParaVO(strFuncNames, strParams);
    this.changeCodeToID(paravos);
    this.fillLowerData(paravos);
    FuncRegister register = this.getFuncRegister();

    UFDouble[] funcresults = this.execUFOFunc(register, paravos);
    ResultVO[] resultvos = new ResultVO[paravos.length];
    int i = 0;
    for (String funname : strFuncNames) {
      resultvos[i] = new ResultVO();
      resultvos[i].setFuncName(funname);
      resultvos[i].setParams(strParams[i]);
      UFDouble value = funcresults[i];
      if (null == value) {
        value = UFDouble.ZERO_DBL;
      }
      resultvos[i].setValue(funcresults[i].toDouble());
      i++;
    }
    return resultvos;
  }

  protected abstract UFDouble[] execUFOFunc(FuncRegister register,
      UFOFuncParaVO[] paravos);

  private FuncRegister getFuncRegister() {
    FuncRegister register = new FuncRegister(SaleInvoiceHVO.class);
    // 添加普通字段
    register.addNormalPara(UFOFuncParaVO.PK_ORG, "pk_org");
    register.addNormalPara(UFOFuncParaVO.CCUSTOMERID, "cinvoicecustid");
    register.addNormalPara(UFOFuncParaVO.CMATERIALID,
        "csaleinvoicebid.cmaterialid");
    register.addNormalParaKey(UFOFuncParaVO.FUNCNAME);
    // 添加级次字段
    register.addLevelPara(UFOFuncParaVO.CCUSTBASCLID,
        "cinvoicecustid.pk_custclass");
    register.addLevelPara(UFOFuncParaVO.CMARBASCLID,
        "csaleinvoicebid.cmaterialid.pk_marbasclass");
    register.addLevelPara(UFOFuncParaVO.CDEPTID, "csaleinvoicebid.cdeptid");
    // 添加特殊字段
    register.addSpecialPara(UFOFuncParaVO.BEGINDATE, "dbilldate", ">=");
    register.addSpecialPara(UFOFuncParaVO.ENDDATE, "dbilldate", "<=");
    // 添加冗余字段
    register.addParaReduncyMap("dbilldate", "csaleinvoicebid.dbilldate");
    register.addParaReduncyMap("pk_org", "csaleinvoicebid.pk_org");
    // 选择字段
    register.addSelectSumItem(SOItemKey.NNUM, "csaleinvoicebid.nnum");
    register.addSelectSumItem(SOItemKey.NMNY, "csaleinvoicebid.nmny");
    register.addSelectSumItem(SOItemKey.NTAXMNY, "csaleinvoicebid.ntaxmny");

    return register;
  }

  private void fillLowerData(UFOFuncParaVO[] paravos) {
    Set<String> setCustBascl = new HashSet<String>();
    Set<String> setMarBascl = new HashSet<String>();
    Set<String> setDept = new HashSet<String>();
    for (UFOFuncParaVO para : paravos) {
      String pk_org = para.getPk_org();
      String custbascl = para.getCcustbasclid();
      if (!PubAppTool.isNull(custbascl)) {
        setCustBascl.add(pk_org + "|" + custbascl);
      }
      String marbascl = para.getCmarbasclid();
      if (!PubAppTool.isNull(marbascl)) {
        setMarBascl.add(pk_org + "|" + marbascl);
      }
      String dept = para.getCdeptid();
      if (!PubAppTool.isNull(dept)) {
        setDept.add(pk_org + "|" + dept);
      }
    }
    Map<String, String[]> mapcustclchild = this.getCustClassChild(setCustBascl);

    Map<String, String[]> mapmarbasclchild = this.getMarClassChild(setMarBascl);

    Map<String, String[]> mapdeptchild = this.getDeptChild(setDept);
    for (UFOFuncParaVO para : paravos) {
      String pk_org = para.getPk_org();
      String custbascl = para.getCcustbasclid();
      if (!PubAppTool.isNull(custbascl)) {
        String custclkey = pk_org + "|" + custbascl;
        if (mapcustclchild.containsKey(custclkey)) {
          para.addLowerArray(UFOFuncParaVO.CCUSTBASCLID,
              mapcustclchild.get(custclkey));
        }
      }
      String marbascl = para.getCmarbasclid();
      if (!PubAppTool.isNull(marbascl)) {
        String marclkey = pk_org + "|" + marbascl;
        if (mapmarbasclchild.containsKey(marclkey)) {
          para.addLowerArray(UFOFuncParaVO.CMARBASCLID,
              mapmarbasclchild.get(marclkey));
        }
      }
      String dept = para.getCdeptid();
      if (!PubAppTool.isNull(dept)) {
        String deptkey = pk_org + "|" + dept;
        if (mapdeptchild.containsKey(deptkey)) {
          para.addLowerArray(UFOFuncParaVO.CDEPTID, mapdeptchild.get(deptkey));
        }
      }
    }
  }

  private Map<String, String[]> getDeptChild(Set<String> setDept) {
    Map<String, String[]> mapchild = new HashMap<String, String[]>();
    if (setDept.size() == 0) {
      return mapchild;
    }
    for (String doc : setDept) {
      String[] orgdoc = doc.split("|");
      String pk_org = orgdoc[0];
      String basccl = orgdoc[1];
      List<IBDData> listchild =
          DeptPubService.queryChildDocs(pk_org, basccl, true);
      if (null != listchild && listchild.size() > 1) {
        String[] childcls = new String[listchild.size()];
        int i = 0;
        for (IBDData data : listchild) {
          childcls[i] = data.getPk();
          i++;
        }
        mapchild.put(doc, childcls);
      }
    }
    return mapchild;
  }

  private Map<String, String[]> getMarClassChild(Set<String> setMarBascl) {
    Map<String, String[]> mapchild = new HashMap<String, String[]>();
    if (setMarBascl.size() == 0) {
      return mapchild;
    }
    for (String doc : setMarBascl) {
      String[] orgdoc = doc.split("|");
      String pk_org = orgdoc[0];
      String basccl = orgdoc[1];
      List<IBDData> listchild =
          MaterialBaseClassPubService.queryMarBsaeClassChild(pk_org, basccl,
              true);
      if (null != listchild && listchild.size() > 1) {
        String[] childcls = new String[listchild.size()];
        int i = 0;
        for (IBDData data : listchild) {
          childcls[i] = data.getPk();
          i++;
        }
        mapchild.put(doc, childcls);
      }
    }
    return mapchild;
  }

  private Map<String, String[]> getCustClassChild(Set<String> setCustBascl) {
    Map<String, String[]> mapchild = new HashMap<String, String[]>();
    if (setCustBascl.size() == 0) {
      return mapchild;
    }
    for (String doc : setCustBascl) {
      String[] orgdoc = doc.split("|");
      String pk_org = orgdoc[0];
      String basccl = orgdoc[1];
      List<IBDData> listchild =
          CustomerBaseClassPubService.queryCustClassChild(pk_org, basccl, true);
      if (null != listchild && listchild.size() > 1) {
        String[] childcls = new String[listchild.size()];
        int i = 0;
        for (IBDData data : listchild) {
          childcls[i] = data.getPk();
          i++;
        }
        mapchild.put(doc, childcls);
      }
    }
    return mapchild;
  }

  private void changeCodeToID(UFOFuncParaVO[] paravos) {

    String[] formulas =
        new String[] {
          // 开票组织ID
          "pk_org->getColValue2(org_financeorg,pk_financeorg,code,orgcode,pk_group,pk_group)",
          // 客户ID
          "ccustomerid->getColValue2(bd_customer,pk_customer,code,customercode,pk_group,pk_group)",
          // 客户基本分类ID
          "ccustbasclid->getColValue2(bd_custclass,pk_custclass,code,custbasclcode,pk_group,pk_group)",
          // 物料ID
          "cmaterialid->getColValue2(bd_material_v,pk_source,code,materialcode,pk_group,pk_group)",
          // 物料基本分类ID
          "cmarbasclid->getColValue2(bd_marbasclass,pk_marbasclass,code,marbasclcode,pk_group,pk_group)",
          // 部门编号时按业务单元对应，不同业务单元编码可以重复
          "cdeptid->getColValue2(org_dept,pk_dept,code,deptcode,pk_org,getColValue2(org_salesorg,pk_salesorg,code,orgcode,pk_group,pk_group))"
          //end-ncm-shenjzh-报表取销售发票数据按部门取值问题 NCdp205433564
        };
    SuperVOUtil.execFormulaWithVOs(paravos, formulas);

  }

  private UFOFuncParaVO[] constructParaVO(String[] strFuncNames,
      String[] strParams) {
    UFOFuncParaVO[] paravos = new UFOFuncParaVO[strFuncNames.length];
    String pk_group = InvocationInfoProxy.getInstance().getGroupId();
    int i = 0;
    for (String funcname : strFuncNames) {
      paravos[i] = new UFOFuncParaVO();
      String[] paras = this.splitParam(strParams[i]);
      paravos[i].setFuncname(funcname);
      paravos[i].setPk_group(pk_group);
      if (paras.length > 0) {
        paravos[i].setOrgcode(paras[0]);
      }
      if (paras.length > 1) {
        paravos[i].setCustomercode(paras[1]);
      }
      if (paras.length > 2) {
        paravos[i].setCustbasclcode(paras[2]);
      }
      if (paras.length > 3) {
        paravos[i].setMaterialcode(paras[3]);
      }
      if (paras.length > 4) {
        paravos[i].setMarbasclcode(paras[4]);
      }
      if (paras.length > 5) {
        paravos[i].setDeptcode(paras[5]);
      }
      UFDate dbegindate = null;
      String begindate = null;
      if (paras.length > 6) {
        dbegindate = new UFDate(paras[6]);
        begindate = TimeUtils.getStartDate(dbegindate).toString();
      }
      paravos[i].setBegindate(begindate);
      UFDate denddate = null;
      String enddate = null;
      if (paras.length > 7) {
        denddate = new UFDate(paras[7]);
        enddate = TimeUtils.getEndDate(denddate).toString();
      }

      paravos[i].setEnddate(enddate);
      i++;
    }
    return paravos;
  }

  @Override
  public Object callFunc(String strFuncName, String strParam) throws Exception {
    String[] funcnames = new String[] {
      strFuncName
    };
    String[] params = new String[] {
      strParam
    };
    return this.callFunc(funcnames, params);
  }

  @Override
  public String[] checkFunc(String[] strFuncName, String[] strParam)
      throws UfoParseException, Exception {
    return null;
  }

  @Override
  public String checkFunc(String strFuncName, String strParam)
      throws UfoParseException, Exception {
    return null;
  }

  @Override
  public String doFuncRefer(String strFuncName) {
    return null;
  }

  @Override
  public boolean getDefaultParaFlag() {
    return false;
  }

  @Override
  public int getFuncCount(int iServPos) {
    return UFOFuncParaVO.FuncName.length;
  }

  @Override
  public String getFuncDesc(String strFuncName) {

    int i = 0;
    for (String funcname : UFOFuncParaVO.FuncName) {
      if (funcname.equals(strFuncName)) {
        return UFOFuncParaVO.FuncDesc[i];
      }
      i++;
    }
    return null;
  }

  @Override
  public String getFuncForm(String strFuncName) {

    int i = 0;
    for (String funcname : UFOFuncParaVO.FuncName) {
      if (funcname.equals(strFuncName)) {
        return UFOFuncParaVO.FuncForm[i];
      }
      i++;
    }
    return null;

  }

  @Override
  public UfoSimpleObject[] getFuncList(int iModulePos) {
    UfoSimpleObject[] funclist =
        new UfoSimpleObject[UFOFuncParaVO.FuncName.length];
    int i = 0;
    for (String funcname : UFOFuncParaVO.FuncName) {
      funclist[i] = new UfoSimpleObject();
      funclist[i].setID(10000 + i);
      funclist[i].setName(funcname);
      i++;
    }
    return funclist;
  }

  @Override
  public String getFuncName(int iModulePos, int iFuncPos, String[] strChName) {
    if (iFuncPos < UFOFuncParaVO.FuncName.length) {
      return UFOFuncParaVO.FuncName[iFuncPos];
    }
    return null;
  }

  @Override
  public byte getFuncType(String strFuncName) {
    return IFuncType.FLOAT;
  }

  @Override
  public int getModuleCount() {
    return 1;
  }

  @Override
  public String getModuleDesc(int iPos) {
    return UFOFuncParaVO.ModuleDesc;
  }

  @Override
  public UfoSimpleObject[] getModuleList() {
    UfoSimpleObject[] modulelist = new UfoSimpleObject[1];
    modulelist[0] = new UfoSimpleObject();
    modulelist[0].setID(0);
    modulelist[0].setName(UFOFuncParaVO.ModuleName);
    return modulelist;
  }

  @Override
  public int getVersion() {
    return 1;
  }

  @Override
  public boolean hasReferDlg(String strFuncName) {
    return false;
  }

  @Override
  public boolean isModuleFunc(String strFuncName) {
    for (String funcname : UFOFuncParaVO.FuncName) {
      if (funcname.equals(strFuncName)) {
        return true;
      }
    }
    return false;
  }

  @Override
  public String setCalEnv(String[] env) {
    return null;
  }

  @Override
  public boolean setDefaultPara() {
    return false;
  }

  /**
   * 预处理参数
   */
  private String[] splitParam(String sParam) {

    if (StringUtil.isEmptyWithTrim(sParam)) {
      return null;
    }
    String splitsparam = StringUtil.replaceAllString(sParam, "\r\n", ",");

    String[] paras = splitsparam.split(",");
    for (String para : paras) {
      if (StringUtil.isEmptyWithTrim(para)) {
        para = null;
      }
      else {
        para = para.trim();
      }
    }

    return paras;
  }

}
