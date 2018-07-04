package nc.pubitf.so.tbb.detail;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import nc.itf.scmpub.fetchfunc.IFuncParaValue;
import nc.itf.scmpub.tbb.ITbbDateParaMetaPath;
import nc.vo.pub.ISuperVO;
import nc.vo.scmpub.fetchfunc.FuncParaDescribe;
import nc.vo.scmpub.fetchfunc.split.ParaSplitKey;
import nc.vo.scmpub.tbb.TbbParaValue;
import nc.vo.scmpub.tbb.TbbRegister;
import nc.vo.tb.obj.NtbParamVO;

public class SODetailDataProvider {
  // TODO 冯加滨 2012.03.14
  /**
   * 预算取数函数取数值获取
   * 
   * @param tbbregister
   * @param ntbparamvos
   */
  public String getExecDatas(TbbRegister tbbregister, NtbParamVO ntbparamvos) {

    // 3.转换数据
    IFuncParaValue para = new TbbParaValue(ntbparamvos);

    // 5.对每种单据类型组织函数描述信息 取数实体类、取数对象
    return this.getBillTbbExecData(tbbregister, para);
  }

  private String getBillTbbExecData(TbbRegister register, IFuncParaValue para) {
    // 查询实体类
    Class<? extends ISuperVO> entityclass = register.getEntityClass();
    // 1.使用参数描述信息对传入参数分组

    FuncParaDescribe desb = this.getParaDescribe(register);
    // 2.对每个分组拼接SQL

    ParaSplitKey splitkey = new ParaSplitKey(desb, para);

    // 参数描述信息
    this.setParaMetaPath(splitkey, desb, register);
    List<IFuncParaValue> listpara = new ArrayList<IFuncParaValue>();
    listpara.add(para);
    // 组件取数函数SQL构造器
    TbbSqlBuilder sqlbuilder =
        new TbbSqlBuilder(splitkey, listpara, desb, entityclass);
    return sqlbuilder.getQuerySql();
  }

  private FuncParaDescribe getParaDescribe(TbbRegister register) {
    FuncParaDescribe parades = new FuncParaDescribe();
    parades.setNormalKey(register.getNormalKey());
    parades.setLevelKey(register.getLevelKey());
    parades.setSpelcialKey(register.getSpecialKey());
    parades.setSpecialOperatorMap(register.getParaOperatorMap());
    parades.setParaRedundancyMap(register.getParaReduncyMap());
    return parades;
  }

  private void setParaMetaPath(ParaSplitKey splitkey, FuncParaDescribe desb,
      TbbRegister register) {
    ITbbDateParaMetaPath datemetapath = register.getIDateMetaPath();
    if (null != datemetapath) {
      String datetype = splitkey.getSpecialParaValue(TbbParaValue.DATETYPE);
      register.addParaMetaPathMap(TbbParaValue.BEGINDATE,
          datemetapath.getDateMetaPath(datetype));
      register.addParaMetaPathMap(TbbParaValue.ENDDATE,
          datemetapath.getDateMetaPath(datetype));
    }
    Map<String, String> mapparametapath = register.getParaMetaPathMap();
    desb.setParaMetaPathMap(mapparametapath);
  }

}
