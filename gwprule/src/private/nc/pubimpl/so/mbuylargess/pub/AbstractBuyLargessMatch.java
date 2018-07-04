package nc.pubimpl.so.mbuylargess.pub;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;
import java.util.Set;

import nc.bs.framework.common.NCLocator;
import nc.bs.pubapp.AppBsContext;
import nc.impl.pubapp.env.BSContext;
import nc.impl.pubapp.pattern.database.DataAccessUtils;
import nc.itf.scmpub.reference.uap.bd.customer.CustomerBaseClassPubService;
import nc.itf.scmpub.reference.uap.bd.customer.CustomerPubService;
import nc.itf.scmpub.reference.uap.bd.customer.CustomerSaleClassPubService;
import nc.itf.scmpub.reference.uap.bd.material.MaterialPubService;
import nc.itf.scmpub.reference.uap.bd.material.MaterialSaleClassPubSerive;
import nc.itf.scmpub.reference.uap.org.SaleOrgPubService;
import nc.pubitf.eaa.InnerCodeUtil;
import nc.pubitf.uapbd.IMaterialBaseClassPubService;
import nc.vo.bd.accessor.IBDData;
import nc.vo.bd.cust.CustomerVO;
import nc.vo.bd.cust.saleinfo.CustsaleVO;
import nc.vo.bd.material.MaterialVO;
import nc.vo.bd.material.marbasclass.MarBasClassVO;
import nc.vo.bd.material.sale.MaterialSaleVO;
import nc.vo.pub.BusinessException;
import nc.vo.pub.ValidationException;
import nc.vo.pubapp.pattern.data.IRowSet;
import nc.vo.pubapp.pattern.exception.ExceptionUtils;
import nc.vo.pubapp.pattern.model.entity.view.AbstractDataView;
import nc.vo.pubapp.pattern.pub.MapList;
import nc.vo.pubapp.pattern.pub.PubAppTool;
import nc.vo.pubapp.pattern.pub.SqlBuilder;
import nc.vo.scmpub.util.ArrayUtil;
import nc.vo.scmpub.util.ListUtil;
import nc.vo.so.mbuylargess.entity.BuyLargessHVO;
import nc.vo.so.mbuylargess.match.BuyLargessMatchPara;
import nc.vo.so.mbuylargess.match.BuyLargessMatchResult;
import nc.vo.so.pub.SOTable;
import nc.vo.so.pub.util.BaseSaleClassUtil;
import nc.vo.so.pub.util.ObjectUtil;

import org.apache.commons.lang.StringUtils;

/**
 * 买赠匹配处理过程抽象类
 * 
 * @since 6.1
 * @version 2012-10-30 18:26:27
 * @author 冯加彬
 * @param <E> 返回的结果中的视图
 */
public abstract class AbstractBuyLargessMatch<E extends AbstractDataView> {

  /**
   * 匹配买赠设置结果
   * 
   * @param matchparas
   * @return 买赠设置结果视图
   */
  public BuyLargessMatchResult[] matchBuyLargessResult(
      BuyLargessMatchPara[] matchparas) {

    BuyLargessMatchResult[] result =
        new BuyLargessMatchResult[matchparas.length];
    // 效率考虑查询买赠表是否有集团数据
    if (!this.checkGroupHaveData()) {
      return result;
    }
    // 1.检查匹配参数合法性
    this.checkMatchParas(matchparas);
    // 2.填充数据，给每个参数填充一个唯一标识
    this.fillIndex(matchparas);
    // 3.按照销售组织分组
    MapList<String, BuyLargessMatchPara> splitparas =
        this.splitPara(matchparas);
    for (Entry<String, List<BuyLargessMatchPara>> entry : splitparas.entrySet()) {
      String csaleorgid = entry.getKey();
      List<BuyLargessMatchPara> listpara = entry.getValue();
      // 4.扩展匹配参数
      BuyLargessMatchPara[] extendparas =
          this.extendParas(csaleorgid, listpara);
      // 5.空值转换
      this.changeNullValue(extendparas);
      // 6.创建临时表
      MatchParaTableCreate creater = new MatchParaTableCreate();
      String temptablename = SOTable.TMP_SO_LARMATCH.getName();
      temptablename = creater.createParaTempTable(temptablename, extendparas);
      // 7.查询
      E[] matchviews = this.queryMatchBuyLargess(temptablename);
      // 8.处理查询结果
      this.processMatchViews(result, matchparas, matchviews);
    }
    return result;
  }

  private boolean checkGroupHaveData() {
    String pk_group = AppBsContext.getInstance().getPkGroup();
    SqlBuilder sql = new SqlBuilder();
    sql.append("select so_buylargess.pk_buylargess from so_buylargess where ");
    sql.append("dr", 0);
    sql.append(" and ");
    sql.append(BuyLargessHVO.PK_GROUP, pk_group);
    DataAccessUtils utils = new DataAccessUtils();
    IRowSet rowset = utils.query(sql.toString());
    String[] cbillids = rowset.toOneDimensionStringArray();
    if (null != cbillids && cbillids.length > 0) {
      return true;
    }
    return false;
  }

  private MapList<String, BuyLargessMatchPara> splitPara(
      BuyLargessMatchPara[] matchparas) {
    MapList<String, BuyLargessMatchPara> splitparas =
        new MapList<String, BuyLargessMatchPara>();
    for (BuyLargessMatchPara para : matchparas) {
      splitparas.put(para.getCsaleorgid(), para);
    }
    return splitparas;
  }

  /**
   * 处理查询到的结果视图
   * 
   * @param result
   * @param matchparas
   * @param matchviews
   */
  protected abstract void processMatchViews(BuyLargessMatchResult[] result,
      BuyLargessMatchPara[] matchparas, E[] matchviews);

  /**
   * 
   * 
   * @param temptablename
   * @return 查询的结果视图VO
   */
  protected abstract E[] queryMatchBuyLargess(String temptablename);

  private void fillIndex(BuyLargessMatchPara[] matchparas) {
    int i = 0;
    for (BuyLargessMatchPara para : matchparas) {
      para.setParaindex(Integer.valueOf(i));
      i++;
    }
  }

  private BuyLargessMatchPara[] extendParas(String csaleorgid,
      List<BuyLargessMatchPara> listpara) {
    // 1.扩展销售组织
    this.extendSaleOrg(csaleorgid, listpara);
    // 2.扩展物料
    String pk_group = BSContext.getInstance().getGroupID();
    if (BaseSaleClassUtil.isMarBaseClass(pk_group)) {
      this.extendMarBaseClass(listpara);
    }
    else {
      this.extendMarSaleClass(csaleorgid, listpara);
    }
    // 3.扩展客户
    if (BaseSaleClassUtil.isCustBaseClass(pk_group)) {
      this.extendCustBaseClass(csaleorgid, listpara);
    }
    else {
      this.extendCustSaleClass(csaleorgid, listpara);
    }
    BuyLargessMatchPara[] extendparas =
        new BuyLargessMatchPara[listpara.size()];
    listpara.toArray(extendparas);

    return extendparas;
  }

  private void changeNullValue(BuyLargessMatchPara[] extendparas) {
    String[] nullitemkeys =
        new String[] {
          BuyLargessMatchPara.CMATERIALID, BuyLargessMatchPara.CMARBASECLID,
          BuyLargessMatchPara.CMARSALECLID, BuyLargessMatchPara.CCUSTOMERID,
          BuyLargessMatchPara.CCUSTCLID, BuyLargessMatchPara.CCUSTSALECLID
        };
    for (BuyLargessMatchPara para : extendparas) {
      for (String key : nullitemkeys) {
        String value = (String) para.getAttributeValue(key);
        if (PubAppTool.isNull(value)) {
          para.setAttributeValue(key, BuyLargessMatchPara.NULLVALUE);
        }
      }
    }
  }

  private void extendCustSaleClass(String pk_saleorg,
      List<BuyLargessMatchPara> extendpara) {

    Set<String> setCustid = new HashSet<String>();
    for (BuyLargessMatchPara para : extendpara) {
      setCustid.add(para.getCcustomerid());
    }
    String[] custkeys = new String[] {
      CustsaleVO.PK_CUSTSALECLASS
    };
    String[] custids = new String[setCustid.size()];
    setCustid.toArray(custids);

    Map<String, CustsaleVO> mapCustSale =
        CustomerPubService.getCustSaleVOByPks(custids, pk_saleorg, custkeys);

    Map<String, String[]> mapcustsalecl = new HashMap<String, String[]>();
    BuyLargessMatchPara[] orgparas = new BuyLargessMatchPara[extendpara.size()];
    extendpara.toArray(orgparas);

    for (BuyLargessMatchPara para : orgparas) {
      BuyLargessMatchPara nullclextend =
          (BuyLargessMatchPara) ObjectUtil.serializableClone(para);
      nullclextend.setCcustomerid(null);
      nullclextend.setCcustclid(null);
      extendpara.add(nullclextend);

      String custid = para.getCcustomerid();
      if (mapcustsalecl.containsKey(custid)) {
        String[] saleclids = mapcustsalecl.get(custid);
        for (String salecl : saleclids) {
          BuyLargessMatchPara basclextend =
              (BuyLargessMatchPara) ObjectUtil.serializableClone(para);
          basclextend.setCcustomerid(null);
          basclextend.setCcustsaleclid(salecl);
          extendpara.add(basclextend);
        }
      }
      else {
        CustsaleVO salevo = mapCustSale.get(custid);
        if (null == salevo) {
          mapcustsalecl.put(custid, new String[0]);
        }
        else {
          String pk_custsalecl = salevo.getPk_custsaleclass();
          String[] custclids =
              this.getFatherCustSaleClass(pk_saleorg, pk_custsalecl);
          for (String custcl : custclids) {
            BuyLargessMatchPara basclextend =
                (BuyLargessMatchPara) ObjectUtil.serializableClone(para);
            basclextend.setCcustomerid(null);
            basclextend.setCcustsaleclid(custcl);
            extendpara.add(basclextend);
          }
          mapcustsalecl.put(custid, custclids);
        }
      }

    }

  }

  private String[] getFatherCustSaleClass(String pk_saleorg,
      String pk_custsalecl) {
    List<IBDData> fatherdatas =
        CustomerSaleClassPubService.queryCustSaleClassFather(pk_saleorg,
            pk_custsalecl, true);
    String[] fathersalecls = new String[fatherdatas.size()];
    int i = 0;
    for (IBDData data : fatherdatas) {
      fathersalecls[i] = data.getPk();
      i++;
    }
    return fathersalecls;
  }

  private void extendCustBaseClass(String pk_saleorg,
      List<BuyLargessMatchPara> extendpara) {

    Set<String> setCustid = new HashSet<String>();
    for (BuyLargessMatchPara para : extendpara) {
      setCustid.add(para.getCcustomerid());
    }
    String[] custkeys = new String[] {
      CustomerVO.PK_CUSTCLASS
    };
    String[] custids = new String[setCustid.size()];
    setCustid.toArray(custids);

    Map<String, CustomerVO> mapCust =
        CustomerPubService.getCustomerVOByPks(custids, custkeys);

    Map<String, String[]> mapcustcl = new HashMap<String, String[]>();
    BuyLargessMatchPara[] orgparas = new BuyLargessMatchPara[extendpara.size()];
    extendpara.toArray(orgparas);

    for (BuyLargessMatchPara para : orgparas) {
      BuyLargessMatchPara nullclextend =
          (BuyLargessMatchPara) ObjectUtil.serializableClone(para);
      nullclextend.setCcustomerid(null);
      nullclextend.setCcustclid(null);
      extendpara.add(nullclextend);

      String custid = para.getCcustomerid();
      if (mapcustcl.containsKey(custid)) {
        String[] baseclids = mapcustcl.get(custid);
        for (String basecl : baseclids) {
          BuyLargessMatchPara basclextend =
              (BuyLargessMatchPara) ObjectUtil.serializableClone(para);
          basclextend.setCcustomerid(null);
          basclextend.setCcustclid(basecl);
          extendpara.add(basclextend);
        }
      }
      else {

        String pk_custcl = mapCust.get(custid).getPk_custclass();
        String[] custclids = this.getFatherCustBaseClass(pk_saleorg, pk_custcl);
        for (String custcl : custclids) {
          BuyLargessMatchPara basclextend =
              (BuyLargessMatchPara) ObjectUtil.serializableClone(para);
          basclextend.setCcustomerid(null);
          basclextend.setCcustclid(custcl);
          extendpara.add(basclextend);
        }
        mapcustcl.put(custid, custclids);
      }

    }

  }

  private String[] getFatherCustBaseClass(String pk_saleorg, String pk_custcl) {
    List<IBDData> fathercustcls =
        CustomerBaseClassPubService.queryCustClassFather(pk_saleorg, pk_custcl,
            true);
    String[] fathercls = new String[fathercustcls.size()];
    int i = 0;
    for (IBDData bddata : fathercustcls) {
      fathercls[i] = bddata.getPk();
      i++;
    }
    return fathercls;
  }

  private void extendMarSaleClass(String pk_saleorg,
      List<BuyLargessMatchPara> extendpara) {

    Set<String> setMarid = new HashSet<String>();
    for (BuyLargessMatchPara para : extendpara) {
      setMarid.add(para.getCmaterialid());
    }
    String[] marsalekeys = new String[] {
      MaterialSaleVO.PK_MARSALECLASS
    };
    String[] marids = new String[setMarid.size()];
    setMarid.toArray(marids);

    Map<String, MaterialSaleVO> mapMarSale =
        MaterialPubService.queryMaterialSaleInfoByPks(marids, pk_saleorg,
            marsalekeys);

    Map<String, String[]> mapsalecl = new HashMap<String, String[]>();
    BuyLargessMatchPara[] orgparas = new BuyLargessMatchPara[extendpara.size()];
    extendpara.toArray(orgparas);

    for (BuyLargessMatchPara para : orgparas) {
      String marid = para.getCmaterialid();
      MaterialSaleVO salevo = mapMarSale.get(marid);
      if (null == salevo) {
        mapsalecl.put(marid, new String[0]);
        continue;
      }
      String pk_marsaleclass = salevo.getPk_marsaleclass();
      if (mapsalecl.containsKey(pk_marsaleclass)) {
        String[] saleclids = mapsalecl.get(pk_marsaleclass);
        for (String salecl : saleclids) {
          BuyLargessMatchPara basclextend =
              (BuyLargessMatchPara) ObjectUtil.serializableClone(para);
          basclextend.setCmaterialid(null);
          basclextend.setCmarsaleclid(salecl);
          extendpara.add(basclextend);
        }
      }
      else {
          String[] saleclids =
              this.getFatherMarSaleClass(pk_saleorg, pk_marsaleclass);
          for (String salecl : saleclids) {
            BuyLargessMatchPara basclextend =
                (BuyLargessMatchPara) ObjectUtil.serializableClone(para);
            basclextend.setCmaterialid(null);
            basclextend.setCassunitid(null);
            basclextend.setCmarsaleclid(salecl);
            extendpara.add(basclextend);
          }
          mapsalecl.put(pk_marsaleclass, saleclids);
        }
      }
  }

  private String[] getFatherMarSaleClass(String pk_saleorg,
      String pk_marsaleclass) {
    List<IBDData> marsaleclassvos =
        MaterialSaleClassPubSerive.queryMaterialSaleClassFather(pk_saleorg,
            pk_marsaleclass, true);
    String[] fathersalecls = new String[marsaleclassvos.size()];
    int i = 0;
    for (IBDData bddata : marsaleclassvos) {
      fathersalecls[i] = bddata.getPk();
      i++;
    }
    return fathersalecls;
  }

  private void extendMarBaseClass(List<BuyLargessMatchPara> extendpara) {
    Set<String> setMarid = new HashSet<String>();
    for (BuyLargessMatchPara para : extendpara) {
      setMarid.add(para.getCmaterialid());
    }
    String[] markeys = new String[] {
      MaterialVO.PK_MATERIAL, MaterialVO.PK_MARBASCLASS
    };
    String[] marids = new String[setMarid.size()];
    setMarid.toArray(marids);

    MaterialVO[] materialvos =
        MaterialPubService.queryMaterialBaseInfoByPks(marids, markeys);
    Map<String, String> mapmarcl = new HashMap<String, String>();
    Set<String> setMarbasClass = new HashSet<>();
    for (MaterialVO marvo : materialvos) {
      mapmarcl.put(marvo.getPk_material(), marvo.getPk_marbasclass());
      setMarbasClass.add(marvo.getPk_marbasclass());
    }
    //性能优化：批量取出物料基本分类包含上级分类 add by zhangby5 for 636
    Map<String, String[]> mapbaseclids = this.getFatherMarBaseClass(setMarbasClass.toArray(new String[0]));

    BuyLargessMatchPara[] orgparas = new BuyLargessMatchPara[extendpara.size()];
    extendpara.toArray(orgparas);

    for (BuyLargessMatchPara para : orgparas) {
      String marid = para.getCmaterialid();
      String pk_marbasclass = mapmarcl.get(marid);
      String[] baseclids = mapbaseclids.get(pk_marbasclass);
      if(ArrayUtil.isEmpty(baseclids)){
    	  continue;
      }
      for (String basecl : baseclids) {
         BuyLargessMatchPara basclextend =
             (BuyLargessMatchPara) ObjectUtil.serializableClone(para);
         basclextend.setCmaterialid(null);
         basclextend.setCmarbaseclid(basecl);
         extendpara.add(basclextend);
      }
    }
  }

  /**
   * key:物料基本分类PK
   * value:当前物料基本分类PK和上级物料基本分类PK
   * @param marbasClasses
   * @return
   */
  private Map<String, String[]> getFatherMarBaseClass(String[] marbasClasses) {
	  Map<String, String[]> mapbaseclids = new HashMap<>();
	  if(marbasClasses.length==0){
		  return mapbaseclids;
	  }
	  MarBasClassVO[] aterialBaseClass = null;
	    try {
	      //调用批量接口
	      aterialBaseClass =
	          NCLocator.getInstance().lookup(IMaterialBaseClassPubService.class)
	              .queryMaterialBaseClassByPks(marbasClasses, true);
	    }
	    catch (BusinessException e) {
	      // 日志异常
	      ExceptionUtils.wrappException(e);
	    }
	    if(ArrayUtil.isEmpty(aterialBaseClass)){
	    	return mapbaseclids;
	    }
	    //根据物料基本分类的内部码来组合成包含父类的基本分类PK
	    mapbaseclids = this.constructBaseClass(aterialBaseClass,marbasClasses);
	    return mapbaseclids;
  }

  private Map<String, String[]> constructBaseClass(
		  MarBasClassVO[] aterialBaseClass, String[] marbasClasses) {
	  Map<String, String[]> mapbaseclids = new HashMap<>();
	  Map<String,String> baseClassInnerCodeMap = new HashMap<>();
	  Map<String,String> innerCodeBaseClassMap = new HashMap<>();
	  this.fillMap(aterialBaseClass,baseClassInnerCodeMap,innerCodeBaseClassMap);
	  for(String marbasClass : marbasClasses){
		  String innercode = baseClassInnerCodeMap.get(marbasClass);
		  mapbaseclids.put(marbasClass, this.getBaseClassByInnerCode(innerCodeBaseClassMap,innercode));
	  }
	  return mapbaseclids;
  }

  private String[] getBaseClassByInnerCode(Map<String, String> baseClassInnerCodeMap, String innercode) {
	  Set<String> parentCodes = new HashSet<String>();
	  String parentCode =
			  innercode.substring(0,
					  innercode.length() - InnerCodeUtil.INNERCODELENGTH);
	  while (StringUtils.isNotBlank(parentCode)) {
		  parentCodes.add(parentCode);
		  parentCode = parentCode.substring(0, parentCode.length() - InnerCodeUtil.INNERCODELENGTH);
	  }
	  List<String> baseclassids = new ArrayList<>();
	  baseclassids.add(baseClassInnerCodeMap.get(innercode));
	  for(String code : parentCodes){
		  baseclassids.add(baseClassInnerCodeMap.get(code));
	  }
	  return ListUtil.toArray(baseclassids);
  }

  private void fillMap(MarBasClassVO[] aterialBaseClass, 
			Map<String, String> baseClassInnerCodeMap, 
				Map<String, String> innerCodeBaseClassMap) {
	for(MarBasClassVO vo : aterialBaseClass){
		baseClassInnerCodeMap.put(vo.getPk_marbasclass(), vo.getInnercode());
		innerCodeBaseClassMap.put(vo.getInnercode(), vo.getPk_marbasclass());
	}
  }

  private List<BuyLargessMatchPara> extendSaleOrg(String csaleorgid,
      List<BuyLargessMatchPara> splitparas) {
    String pk_group = BSContext.getInstance().getGroupID();
    List<IBDData> fathersaleorgs =
        SaleOrgPubService.queryFatherSale(pk_group, csaleorgid, false);

    if (null == fathersaleorgs || fathersaleorgs.size() == 0) {
      return splitparas;
    }
    int orgsize = splitparas.size();
    for (IBDData bddata : fathersaleorgs) {
      String fatherorg = bddata.getPk();
      for (int i = 0; i < orgsize; i++) {
        BuyLargessMatchPara fatherpara =
            (BuyLargessMatchPara) ObjectUtil.serializableClone(splitparas
                .get(i));
        fatherpara.setCsaleorgid(null);
        fatherpara.setCfatherorgid(fatherorg);
        splitparas.add(fatherpara);
      }
    }
    return splitparas;
  }

  private void checkMatchParas(BuyLargessMatchPara[] matchparas) {

    for (BuyLargessMatchPara para : matchparas) {
      try {
        para.validate();
      }
      catch (ValidationException e) {
        ExceptionUtils.wrappException(e);
      }
    }
  }
}
