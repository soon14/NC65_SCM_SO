package nc.pubimpl.so.ic.m4c;

import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;

import nc.bs.framework.common.NCLocator;
import nc.impl.pubapp.pattern.database.DataAccessUtils;
import nc.itf.org.IOrgConst;
import nc.itf.org.IOrgUnitQryService;
import nc.itf.org.orgmodel.IOrgRelationTypeConst;
import nc.pubitf.so.ic.m4c.ISaleFor4CParaQuery;
import nc.vo.bd.pub.IPubEnumConst;
import nc.vo.org.OrgVO;
import nc.vo.org.orgmodel.OrgRelationVO;
import nc.vo.org.util.OrgTypeManager;
import nc.vo.pub.BusinessException;
import nc.vo.pub.lang.UFBoolean;
import nc.vo.pubapp.pattern.data.IRowSet;
import nc.vo.pubapp.pattern.exception.ExceptionUtils;
import nc.vo.pubapp.pattern.pub.MapList;
import nc.vo.pubapp.pattern.pub.SqlBuilder;
import nc.vo.so.paravo.Para4CFor32SignBiz;
import nc.vo.so.pub.util.SOSysParaInitUtil;

public class SaleFor4CParaQueryImpl implements ISaleFor4CParaQuery {

  /**
   * 用途： 根据销售组织得到允许发货的库存组织ID[]； 逻辑：
   * 1、根据销售组织匹配销售业务委托关系，匹配上的库存组织允许发货；
   * 2、销售组织又具有库存组织属性，则销售组织作为发货库存组织允许发货； 说明：返回的ID不应该有重复
   * 
   * @param saleorgID
   * @param materialID
   * @return
   * @throws BusinessException
   */
  public Map<String, List<String>> getStockOrgIDSBySaleOrgID(String[] orgids) {

    MapList<String, String> saleorgmap = new MapList<String, String>();

    try {
      // 1、根据“销售组织"匹配销售业务委托关系，匹配上的库存组织允许发货；
      SqlBuilder sql = new SqlBuilder();
      sql.append("select distinct " + OrgRelationVO.TARGET + ","
          + OrgRelationVO.SOURCER + " from ");
      sql.append(OrgRelationVO.getDefaultTableName());
      sql.append(" where ");
      sql.append(OrgRelationVO.PK_RELATIONTYPE,
          IOrgRelationTypeConst.SALESTOCKCONSIGN);
      sql.append(" and ");
      sql.append(OrgRelationVO.SOURCER, orgids);
      sql.append(" and ");
      sql.append(OrgRelationVO.ENABLESTATE, IPubEnumConst.ENABLESTATE_ENABLE);
      DataAccessUtils dao = new DataAccessUtils();
      IRowSet rowset = dao.query(sql.toString());
      String[][] orgs = rowset.toTwoDimensionStringArray();
      for (String[] org : orgs) {
        // 销售组织对应的库存组织
        saleorgmap.put(org[1], org[0]);
      }

      // 2、销售组织又具有库存组织属性，则销售组织作为发货库存组织允许发货；
      IOrgUnitQryService service =
          NCLocator.getInstance().lookup(IOrgUnitQryService.class);
      OrgVO[] orgvos = service.getOrgs(orgids);
      for (OrgVO orgvo : orgvos) {
        if (orgvo != null
            && OrgTypeManager.getInstance().isTypeOf(orgvo,
                IOrgConst.STOCKORGTYPE)) {
          saleorgmap.put(orgvo.getPrimaryKey(), orgvo.getPrimaryKey());
        }
      }
    }
    catch (BusinessException e) {
      ExceptionUtils.wrappException(e);
    }
    return saleorgmap.toMap();
  }

  @Override
  public Map<String, UFBoolean> getSO20(String[] cfinaceorgids)
      throws BusinessException {
    Map<String, UFBoolean> ret = new HashMap<String, UFBoolean>();
    for (String cfinaceorgid : cfinaceorgids) {
      ret.put(cfinaceorgid, SOSysParaInitUtil.getSO20(cfinaceorgid));
    }
    return ret;
  }

  @Override
  public Para4CFor32SignBiz[] querySignNumBusitype(Para4CFor32SignBiz[] paras)
      throws BusinessException {
    String[] saleorgs = this.getSaleorgs(paras);
    // SOSysParaInitUtil util = new SOSysParaInitUtil();
    MapList<String, String> sysMap = SOSysParaInitUtil.getSO16(saleorgs);
    for (Para4CFor32SignBiz para : paras) {
      String pk_org = para.getPk_org();
      String cbizid = para.getCbizid();
      if (!sysMap.containsKey(pk_org)) {
        para.setIsSign(UFBoolean.FALSE);
        continue;
      }
      List<String> sysList = sysMap.get(pk_org);
      if (!sysList.contains(cbizid)) {
        para.setIsSign(UFBoolean.FALSE);
        continue;
      }
      para.setIsSign(UFBoolean.TRUE);
    }
    return paras;
  }

  private String[] getSaleorgs(Para4CFor32SignBiz[] paras) {
    Set<String> saleorgset = new HashSet<String>();
    for (Para4CFor32SignBiz para : paras) {
      String pk_org = para.getPk_org();
      if (null != pk_org) {
        saleorgset.add(pk_org);
      }
    }
    String[] pk_orgs = saleorgset.toArray(new String[saleorgset.size()]);
    return pk_orgs;
  }

}
