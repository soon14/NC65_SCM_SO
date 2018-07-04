package nc.vo.so.iufo;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;

import nc.impl.pubapp.pattern.database.DataAccessUtils;
import nc.itf.scmpub.fetchfunc.IFuncParaValue;
import nc.itf.scmpub.fetchfunc.IFuncSelectItem;
import nc.vo.pub.ISuperVO;
import nc.vo.pub.lang.UFDouble;
import nc.vo.pubapp.pattern.data.IRowSet;
import nc.vo.pubapp.pattern.pub.MapList;
import nc.vo.scmpub.fetchfunc.FuncParaDescribe;
import nc.vo.scmpub.fetchfunc.FuncRegister;
import nc.vo.scmpub.fetchfunc.FuncResult;
import nc.vo.scmpub.fetchfunc.FuncSelectItem;
import nc.vo.scmpub.fetchfunc.FuncSqlBuidler;
import nc.vo.scmpub.fetchfunc.split.ParaSplitKey;
import nc.vo.scmpub.fetchfunc.split.ParaSpliter;
import nc.vo.so.pub.SOItemKey;

import org.apache.commons.lang.StringUtils;

public class UFOFuncExector {

  // private class SoFuncSqlBuidler extends FuncSqlBuidler {
  //
  // public SoFuncSqlBuidler(ParaSplitKey splitkey,
  // List<IFuncParaValue> listpara, IFuncParaDescribe desb,
  // Class<? extends ISuperVO> entityclass, IFuncSelectItem fetchitem,
  // IFuncSqlExtender sqlExtender) {
  // super(splitkey, listpara, desb, entityclass, fetchitem, sqlExtender);
  //
  // }
  //
  // @Override
  // public String getTableAliasByMetaPath(String metapath) {
  // if (metapath.contains("csaleinvoicebid.nmny")
  // || metapath.contains("csaleinvoicebid.ntaxmny")) {
  // return SOTable.SALEINVOICE_B.getName();
  // }
  // return super.getTableAliasByMetaPath(metapath);
  // }
  //
  // private void getTableColumnByMetaPath() {
  // }
  //
  // }

  /* private void setNullField(UFOFuncParaVO paravo) {
     if (PubAppTool.isNull(paravo.getCmaterialid())) {
       paravo.setCmaterialid("~");
     }
     if (PubAppTool.isNull(paravo.getCcustomerid())) {
       paravo.setCcustomerid("~");
     }
     if (PubAppTool.isNull(paravo.getPk_org())) {
       paravo.setPk_org("~");
     }
     if (PubAppTool.isNull(paravo.getPk_group())) {
       paravo.setPk_group("~");
     }
     if (PubAppTool.isNull(paravo.getCdeptid())) {
       paravo.setCdeptid("~");
     }
     if (PubAppTool.isNull(paravo.getCmarbasclid())) {
       paravo.setCmarbasclid("~");
     }
     if (PubAppTool.isNull(paravo.getCcustbasclid())) {
       paravo.setCcustbasclid("~");
     }
   }
  */
  public UFDouble[] execUFOFunc(FuncRegister register, UFOFuncParaVO[] paravos) {
    // 1.返回结果集
    UFDouble[] results = new UFDouble[paravos.length];
    // 2.转换数据
    IFuncParaValue[] paras = new UFOFuncParaValue[paravos.length];
    Map<IFuncParaValue, Integer> mapindex =
        new HashMap<IFuncParaValue, Integer>();
    int i = 0;
    for (UFOFuncParaVO paravo : paravos) {
      paras[i] = new UFOFuncParaValue(paravo);
      mapindex.put(paras[i], Integer.valueOf(i));
      i++;
    }
    // 3.组织函数描述信息 取数实体类、取数对象
    // 查询实体类
    Class<? extends ISuperVO> entityclass = register.getEntityClass();
    // 参数描述信息
    FuncParaDescribe desb = this.getParaDescribe(register);
    ParaSpliter spliter = new ParaSpliter();
    MapList<ParaSplitKey, IFuncParaValue> splitpara =
        spliter.splitParas(desb, paras);
    // 4 .对每个分组拼接SQL
    for (Entry<ParaSplitKey, List<IFuncParaValue>> splitentry : splitpara
        .entrySet()) {
      ParaSplitKey splitkey = splitentry.getKey();
      // 参数数组
      List<IFuncParaValue> listpara = splitentry.getValue();
      // 取数项目
      IFuncSelectItem selectitem = this.getSelectItem(register);
      // 组件取数函数SQL构造器
      FuncSqlBuidler sqlbuilder =
          new FuncSqlBuidler(splitkey, listpara, desb, entityclass, selectitem,
              null);
      List<FuncResult> listresult = this.getFuncResult(sqlbuilder);
      i = 0;
      for (IFuncParaValue para : listpara) {
        FuncResult funcres = listresult.get(i);
        int index = mapindex.get(para).intValue();
        if (null == funcres) {
          results[index] = UFDouble.ZERO_DBL;
        }
        else {
          String funcname = para.getParaValue(UFOFuncParaVO.FUNCNAME);
          String selkey = this.getSelectKey(funcname);
          UFDouble value = funcres.getUFDoubleValue(selkey);
          if (null == value) {
            results[index] = UFDouble.ZERO_DBL;
          }
          else {
            results[index] = value;
          }
        }
        i++;
      }
    }
    return results;
  }

  private String getSelectKey(String funcname) {
    if (funcname.equals(UFOFuncParaVO.FuncName[0])) {
      return SOItemKey.NNUM;
    }
    else if (funcname.equals(UFOFuncParaVO.FuncName[1])) {
      return SOItemKey.NMNY;
    }
    else if (funcname.equals(UFOFuncParaVO.FuncName[2])) {
      return SOItemKey.NTAXMNY;
    }
    return null;
  }

  private List<FuncResult> getFuncResult(FuncSqlBuidler sqlbuilder) {
    String querysql = sqlbuilder.getQuerySql();
    if (!StringUtils.isEmpty(querysql)) {
      querysql =
          querysql
              .replace(
                  "sum(so_saleinvoice_b.nmny)",
                  "sum(case when so_saleinvoice_b.blargessflag = 'Y' then 0 else so_saleinvoice_b.nmny end)");
      querysql =
          querysql
              .replace(
                  "sum(so_saleinvoice_b.ntaxmny)",
                  "sum(case when so_saleinvoice_b.blargessflag = 'Y' then 0 else so_saleinvoice_b.ntaxmny end)");

    }
    DataAccessUtils bo = new DataAccessUtils();
    IRowSet rowset = bo.query(querysql);
    List<FuncResult> listdata = sqlbuilder.getResult(rowset);

    return listdata;
  }

  private IFuncSelectItem getSelectItem(FuncRegister register) {
    FuncSelectItem selitem = new FuncSelectItem();
    selitem.setSumItemKey(register.getSelectSumKey());
    selitem.setFetchItemMetaPathMap(register.getSelectMetaPathMap());
    return selitem;
  }

  private FuncParaDescribe getParaDescribe(FuncRegister register) {
    FuncParaDescribe parades = new FuncParaDescribe();

    parades.setNormalKey(register.getNormalKey());
    parades.setLevelKey(register.getLevelKey());
    parades.setSpelcialKey(register.getSpecialKey());
    parades.setParaMetaPathMap(register.getParaMetaPathMap());
    parades.setSpecialOperatorMap(register.getParaOperatorMap());
    parades.setParaRedundancyMap(register.getParaReduncyMap());
    return parades;
  }

}
