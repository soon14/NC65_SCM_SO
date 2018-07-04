/**
 * 
 */
package nc.impl.so.m30;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import nc.impl.pubapp.pattern.database.DBTool;
import nc.itf.so.m30.ISaleOrgPubService;
import nc.vo.org.OrgVO;
import nc.vo.pub.BusinessException;

/**
 * @author gdsjw
 * 
 */
public class SaleOrgPubServiceImpl implements ISaleOrgPubService {

  @Override
  public String[] getOIDArray(int count) throws BusinessException {
    DBTool dao = new DBTool();
    String[] ids = dao.getOIDs(count);
    return ids;
  }

  // @Override
  // public CustsaleVO[] qeuryCustSaleVOsByOrgIDs(String[] orgIDs,
  // final String pk_customer) throws BusinessException {
  // if ((orgIDs == null) || (orgIDs.length == 0)) {
  // return null;
  // }
  // List<CustsaleVO> voList = null;
  // InSqlBatchCaller caller = new InSqlBatchCaller(orgIDs);
  // try {
  // voList = (List<CustsaleVO>) caller.execute(new IInSqlBatchCallBack() {
  // List<CustsaleVO> voList1 = new ArrayList<CustsaleVO>();
  //
  // @Override
  // public Object doWithInSql(String inSql) throws BusinessException,
  // SQLException {
  // String wherePart =
  // CustsaleVO.PK_CUSTOMER + " = '" + pk_customer + "' and "
  // + CustsaleVO.PK_ORG + " in " + inSql;
  // SuperVOQry query = new SuperVOQry(CustsaleVO.class);
  // CustsaleVO[] vos =
  // (CustsaleVO[]) query.getBatchVOByCondition(new String[] {
  // ICustConst.ATTR_CUST_SALE_ADDRESS
  // }, wherePart);
  // if ((vos != null) && (vos.length > 0)) {
  // this.voList1.addAll(Arrays.asList(vos));
  // }
  // return this.voList1;
  // }
  // });
  // }
  // catch (SQLException e) {
  // Logger.error(e.getMessage(), e);
  // throw new BusinessException("查询客户销售信息数据失败！");
  // }
  // return voList.toArray(new CustsaleVO[0]);
  // }

  @Override
  public Map<String, List<OrgVO>> queryDefaultOtherOrgIDsFromSaleOrgRel(
      String customerID, String saleorgID, String[] materialids)
      throws BusinessException {
    // TODO Auto-generated method stub
    return null;
  }

  @Override
  public Map<String, OrgVO> queryDefaultStockOrgIDFromSaleOrgRel(
      String saleorgID, String[] materialids) throws BusinessException {
    Map<String, OrgVO> defaultstockorgHM = new HashMap<String, OrgVO>();
    // IOrgUnitPubService orgservice =
    // NCLocator.getInstance().lookup(IOrgUnitPubService.class);
    //
    // // 同时销售组织又具有库存组织
    // StockOrgVO tmpstockorgvo =
    // (StockOrgVO) new BaseDAO().retrieveByPK(StockOrgVO.class, saleorgID);
    //
    // OrgVO orgvo = null;
    // if (tmpstockorgvo != null) {
    // String[] orgids = new String[] {
    // saleorgID
    // };
    // String[] fields = new String[] {
    // OrgVO.PK_ORG, OrgVO.CODE, OrgVO.NAME, OrgVO.PK_VID, OrgVO.PK_GROUP
    // };
    // OrgVO[] orgvos = orgservice.getOrgs(orgids, fields);
    // if ((orgvos != null) && (orgvos.length > 0)) {
    // orgvo = orgvos[0];
    // }
    // }
    // // 由物料ID得到产品线ID
    // IMaterialPubService materialservice =
    // NCLocator.getInstance().lookup(IMaterialPubService.class);
    // Map<String, MaterialVO> materialHM =
    // materialservice.queryMaterialBaseInfoByPks(materialids, new String[] {
    // "pk_material", "pk_prodline"
    // });
    // MaterialVO[] materialvos = materialHM.values().toArray(new
    // MaterialVO[0]);
    // if ((materialvos != null) && (materialvos.length > 0)) {
    // for (MaterialVO materialvo : materialvos) {
    // String defaultstockorgid = null;
    // String productlineid = materialvo.getPk_prodline();
    //
    // // 由销售组织ID、产品线ID得到所有符合条件的业务委托关系
    // String exeQuerySQL =
    // "select * from org_relation " + "where pk_relationtype = '"
    // + IOrgRelationTypeConst.SALESTOCKCONSIGN + "' "
    // + "and source = '" + saleorgID + "' " + "and (entity1 is null ";
    // if (productlineid != null) {
    // exeQuerySQL = exeQuerySQL + "or entity1 = '" + productlineid + "'";
    // }
    // exeQuerySQL = exeQuerySQL + ") ";
    // List<OrgRelationVO> list =
    // (List<OrgRelationVO>) new BaseDAO().executeQuery(exeQuerySQL,
    // new BeanListProcessor(OrgRelationVO.class));
    //
    // // 按最大匹配寻找默认发货组织
    // Iterator<OrgRelationVO> it = list.iterator();
    // while (it.hasNext()) {
    // OrgRelationVO relvo = it.next();
    // if (relvo.getIsdefault().booleanValue()) {
    // if (productlineid == null) {
    // defaultstockorgid = relvo.getTarget();
    // break;
    // }
    // else if (productlineid.equals(relvo.getEntity1())) {
    // defaultstockorgid = relvo.getTarget();
    // break;
    // }
    // else {
    // defaultstockorgid = relvo.getTarget();
    // }
    // }
    // }
    // // 如果还没找到默认发货库存组织，同时销售组织又具有库存组织属性，则取销售组织为默认发货库存组织
    // if (defaultstockorgid == null) {
    // if (orgvo != null) {
    // defaultstockorgHM
    // .put(saleorgID + materialvo.getPrimaryKey(), orgvo);
    // }
    // }
    // else {
    // String[] orgids = new String[] {
    // defaultstockorgid
    // };
    // String[] fields = new String[] {
    // OrgVO.PK_ORG, OrgVO.CODE, OrgVO.NAME, OrgVO.PK_VID, OrgVO.PK_GROUP
    // };
    // OrgVO[] orgvos = orgservice.getOrgs(orgids, fields);
    // OrgVO stockorgvo = orgvos[0];
    // defaultstockorgHM.put(saleorgID + materialvo.getPrimaryKey(),
    // stockorgvo);
    // }
    // }
    // }
    return defaultstockorgHM;
  }

  @Override
  public Map<String, OrgVO[]> queryStockOrgIDsFromSaleOrgRel(String saleorgID,
      String[] materialids) throws BusinessException {
    Map<String, OrgVO[]> stockorgHM = new HashMap<String, OrgVO[]>();
    // IOrgUnitPubService orgservice =
    // NCLocator.getInstance().lookup(IOrgUnitPubService.class);
    //
    // // 同时销售组织又具有库存组织
    // StockOrgVO tmpstockorgvo =
    // (StockOrgVO) new BaseDAO().retrieveByPK(StockOrgVO.class, saleorgID);
    //
    // OrgVO orgvo = null;
    // if (tmpstockorgvo != null) {
    // String[] orgids = new String[] {
    // saleorgID
    // };
    // String[] fields = new String[] {
    // OrgVO.PK_ORG, OrgVO.CODE, OrgVO.NAME, OrgVO.PK_VID, OrgVO.PK_GROUP
    // };
    // OrgVO[] orgvos = orgservice.getOrgs(orgids, fields);
    // if ((orgvos != null) && (orgvos.length > 0)) {
    // orgvo = orgvos[0];
    // }
    // }
    // // 由物料ID得到产品线ID
    // IMaterialPubService materialservice =
    // NCLocator.getInstance().lookup(IMaterialPubService.class);
    // Map<String, MaterialVO> materialHM =
    // materialservice.queryMaterialBaseInfoByPks(materialids, new String[] {
    // "pk_material", "pk_prodline"
    // });
    // MaterialVO[] materialvos = materialHM.values().toArray(new
    // MaterialVO[0]);
    // if ((materialvos != null) && (materialvos.length > 0)) {
    // for (MaterialVO materialvo : materialvos) {
    // Set<String> idsSet = new HashSet<String>();
    // String productlineid = materialvo.getPk_prodline();
    //
    // // 由销售组织ID、产品线ID得到所有符合条件的业务委托关系
    // String exeQuerySQL =
    // "select * from org_relation " + "where pk_relationtype = '"
    // + IOrgRelationTypeConst.SALESTOCKCONSIGN + "' "
    // + "and source = '" + saleorgID + "' " + "and (entity1 is null ";
    // if (productlineid != null) {
    // exeQuerySQL = exeQuerySQL + "or entity1 = '" + productlineid + "'";
    // }
    // exeQuerySQL = exeQuerySQL + ") ";
    // List<OrgRelationVO> list =
    // (List<OrgRelationVO>) new BaseDAO().executeQuery(exeQuerySQL,
    // new BeanListProcessor(OrgRelationVO.class));
    //
    // Iterator<OrgRelationVO> it = list.iterator();
    // while (it.hasNext()) {
    // OrgRelationVO relvo = it.next();
    // idsSet.add(relvo.getTarget());
    // }
    // if (orgvo != null) {
    // idsSet.add(saleorgID);
    // }
    // if (idsSet.size() > 0) {
    // String[] fields = new String[] {
    // OrgVO.PK_ORG, OrgVO.CODE, OrgVO.NAME, OrgVO.PK_VID, OrgVO.PK_GROUP
    // };
    // OrgVO[] orgvos =
    // orgservice.getOrgs(idsSet.toArray(new String[0]), fields);
    // stockorgHM.put(saleorgID + materialvo.getPrimaryKey(), orgvos);
    // }
    // }
    // }
    return stockorgHM;
  }

}
