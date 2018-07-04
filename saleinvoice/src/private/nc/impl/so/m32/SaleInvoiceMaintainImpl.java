package nc.impl.so.m32;

import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import nc.bs.dao.BaseDAO;
import nc.bs.dao.DAOException;
import nc.bs.framework.common.InvocationInfoProxy;
import nc.impl.pubapp.pattern.data.bill.BillQuery;
import nc.impl.pubapp.pattern.data.bill.tool.BillTransferTool;
import nc.impl.pubapp.pattern.data.view.SchemeViewQuery;
import nc.impl.pubapp.pattern.data.view.ViewQuery;
import nc.impl.pubapp.pattern.data.vo.VOQuery;
import nc.impl.pubapp.pattern.data.vo.VOUpdate;
import nc.impl.pubapp.pattern.database.DataAccessUtils;
import nc.impl.pubapp.pattern.database.IDExQueryBuilder;
import nc.impl.pubapp.pattern.database.TempTable;
import nc.impl.so.m32.action.DeleteSaleInvoiceAction;
import nc.impl.so.m32.action.InsertSaleInvoiceAction;
import nc.impl.so.m32.action.UpdateSaleInvoiceAction;
import nc.itf.scmpub.reference.uap.pf.PfServiceScmUtil;
import nc.itf.so.m32.ISaleInvoiceMaintain;
import nc.jdbc.framework.SQLParameter;
import nc.md.persist.framework.MDPersistenceService;
import nc.ui.querytemplate.querytree.IQueryScheme;
import nc.util.mmf.framework.base.MMCollectionUtil;
import nc.vo.bd.material.MaterialVO;
import nc.vo.bd.material.marbasclass.MarBasClassVO;
import nc.vo.et.fgjsdld.AggFgjsdldHeadVO;
import nc.vo.et.gcdljsd.AggGcdljsdHeadVO;
import nc.vo.it.ctjsd.AggCtjsdHeadVO;
import nc.vo.it.yljsd.AggYljsd;
import nc.vo.lm.jkyldlbzdzb1.AggJkyldlbzdzb1HeadVO;
import nc.vo.lm.yffyjsd.AggYffyjsdHeadVO;
import nc.vo.pub.BusinessException;
import nc.vo.pub.JavaType;
import nc.vo.pub.lang.UFDateTime;
import nc.vo.pubapp.pattern.data.IRowSet;
import nc.vo.pubapp.pattern.exception.ExceptionUtils;
import nc.vo.pubapp.pattern.pub.SqlBuilder;
import nc.vo.pubapp.query2.sql.process.QuerySchemeProcessor;
import nc.vo.pubapp.util.CombineViewToAggUtil;
import nc.vo.scmpub.goldtax.GoldTaxVO;
import nc.vo.scmpub.res.billtype.SOBillType;
import nc.vo.so.m32.entity.SaleInvoiceBVO;
import nc.vo.so.m32.entity.SaleInvoiceHVO;
import nc.vo.so.m32.entity.SaleInvoiceVO;
import nc.vo.so.m32.entity.SaleInvoiceViewVO;
import nc.vo.so.m32.util.RemoteFormSOUtil;
import nc.vo.so.pub.SOTable;
import nc.vo.so.pub.query.SOQuerySchemeUtils;

import org.apache.commons.lang.ArrayUtils;

/**
 * 
 * @since 6.0
 * @version 2011-6-13 下午05:51:15
 * @author 么贵敬
 */
public class SaleInvoiceMaintainImpl implements ISaleInvoiceMaintain {

  @Override
  public SaleInvoiceVO[] approveSaleInvoice(SaleInvoiceVO[] appvos)
      throws BusinessException {
    return null;
  }

  @Override
  public void deleteSaleInvoice(SaleInvoiceVO[] delvos)
      throws BusinessException {

    try {
      DeleteSaleInvoiceAction action = new DeleteSaleInvoiceAction();
      action.delete(delvos);
    }
    catch (Exception e) {
      ExceptionUtils.marsh(e);
    }

  }

  @Override
  public GoldTaxVO[] executeVOChangeTogtax(String[] hids)
      throws BusinessException {
    try {
      BillQuery<SaleInvoiceVO> srv =
          new BillQuery<SaleInvoiceVO>(SaleInvoiceVO.class);
      SaleInvoiceVO[] vos = srv.query(hids);

      GoldTaxVO[] goldtaxvos =
          PfServiceScmUtil.executeVOChange(SOBillType.Invoice.getCode(),
              "gtax", vos);
      return goldtaxvos;
    }
    catch (Exception ex) {
      ExceptionUtils.marsh(ex);
    }
    return new GoldTaxVO[0];
  }

  @Override
  public Map<String, String> getCmaterialids(String[] innercode)
      throws BusinessException {
    Map<String, String> rets = new HashMap<String, String>();
    SqlBuilder sql = new SqlBuilder();
    sql.append("select ");
    sql.append("b." + MarBasClassVO.INNERCODE);
    sql.append(",");
    sql.append("b." + MarBasClassVO.PK_MARBASCLASS);

    sql.append(" from ");
    sql.append(MarBasClassVO.getDefaultTableName());
    sql.append(" b");
    sql.append(" where ");
    sql.append(MarBasClassVO.INNERCODE, innercode);
    DataAccessUtils dataacc = new DataAccessUtils();
    try {
      IRowSet rowset = dataacc.query(sql.toString());
      while (rowset.next()) {
        rets.put(rowset.getString(0), rowset.getString(1));
      }
    }
    catch (Exception ex) {
      ExceptionUtils.marsh(ex);
    }
    return rets;
  }

  @Override
  public Map<String, String> getInnercodemaps(String[] cmaterialids)
      throws BusinessException {
    SqlBuilder sql = new SqlBuilder();
    sql.append("select ");

    sql.append("a." + MaterialVO.PK_MATERIAL);
    sql.append(",");
    sql.append("b." + MarBasClassVO.INNERCODE);

    sql.append(" from ");
    sql.append(MaterialVO.getDefaultTableName());
    sql.append(" a");
    sql.append(" inner join " + MarBasClassVO.getDefaultTableName() + " b");
    sql.append(" on a." + MaterialVO.PK_MARBASCLASS + "=b."
        + MarBasClassVO.PK_MARBASCLASS);
    sql.append(" where ");
    IDExQueryBuilder iq = new IDExQueryBuilder(SOTable.TMP_SO_ID1.getName());
    sql.append(iq.buildSQL(MaterialVO.PK_MATERIAL, cmaterialids));
    DataAccessUtils dataacc = new DataAccessUtils();
    Map<String, String> ret = new HashMap<String, String>();
    try {
      IRowSet rowset = dataacc.query(sql.toString());
      while (rowset.next()) {
        ret.put(rowset.getString(0), rowset.getString(1));
      }
    }
    catch (Exception ex) {
      ExceptionUtils.marsh(ex);
    }
    return ret;
  }

  @Override
  public SaleInvoiceVO[] insertSaleInvoice(SaleInvoiceVO[] newvos)
      throws BusinessException {

    SaleInvoiceVO[] retvos = null;
    try {
      InsertSaleInvoiceAction action = new InsertSaleInvoiceAction();
      retvos = action.insert(newvos);
    }
    catch (Exception ex) {
      ExceptionUtils.marsh(ex);
    }
    return retvos;

  }

  @Override
  public SaleInvoiceVO[] querySaleInvoice(String whereSql)
      throws BusinessException {
    try {
      DataAccessUtils utils = new DataAccessUtils();
      IRowSet rowset = utils.query(whereSql);
      if (rowset.size() == 0) {
        return new SaleInvoiceVO[0];
      }
      String[] ids = rowset.toOneDimensionStringArray();
      BillQuery<SaleInvoiceVO> query =
          new BillQuery<SaleInvoiceVO>(SaleInvoiceVO.class);
      return query.query(ids);
    }
    catch (Exception ex) {
      ExceptionUtils.marsh(ex);
    }
    return null;
  }

  @Override
  public SaleInvoiceVO[] querySaleInvoiceFor4C(IQueryScheme queryScheme)
      throws BusinessException {
    this.createSqlByQuerySchemeFor4C(queryScheme);
    // 拼接排序sql
    String ordersql = this.createOrderSql(queryScheme);
    try {
      SaleInvoiceVO[] queryVos =
          this.querySaleInvoiceVOForSource(queryScheme, ordersql);
      return queryVos;
    }
    catch (Exception ex) {
      ExceptionUtils.marsh(ex);
    }
    return null;
  }

  @Override
  public SaleInvoiceViewVO[] queryViewvo(String sql) throws BusinessException {
    DataAccessUtils utils = new DataAccessUtils();
    try {
      String[] bids = utils.query(sql).toOneDimensionStringArray();
      SaleInvoiceViewVO[] views =
          new ViewQuery<SaleInvoiceViewVO>(SaleInvoiceViewVO.class).query(bids);
      return views;
    }
    catch (Exception ex) {
      ExceptionUtils.marsh(ex);
    }
    return null;
  }

  @Override
  public SaleInvoiceVO[] unapproveSaleInvoice(SaleInvoiceVO[] unappvos)
      throws BusinessException {
    return null;
  }

  @Override
  public SaleInvoiceHVO[] updateGoldTaxCode(SaleInvoiceHVO[] updateHeads)
      throws BusinessException {

    Map<String, SaleInvoiceHVO> mapbillcode =
        new HashMap<String, SaleInvoiceHVO>();
    for (SaleInvoiceHVO hvo : updateHeads) {
      mapbillcode.put(hvo.getVbillcode(), hvo);
    }
    String[] names = new String[] {
      SaleInvoiceHVO.CSALEINVOICEID, SaleInvoiceHVO.VBILLCODE
    };
    VOQuery<SaleInvoiceHVO> querysrv =
        new VOQuery<SaleInvoiceHVO>(SaleInvoiceHVO.class, names);

    SqlBuilder sql = new SqlBuilder();
    sql.append(" and ");
    String[] billcodes = mapbillcode.keySet().toArray(new String[0]);
    sql.append(createTempTable(billcodes));
    sql.append(" and ");
    String pk_group = InvocationInfoProxy.getInstance().getGroupId();
    sql.append(SaleInvoiceHVO.PK_GROUP, pk_group);
    try {
      SaleInvoiceHVO[] voHeads = querysrv.query(sql.toString(), null);
      List<SaleInvoiceHVO> newupdateHeads = new ArrayList<SaleInvoiceHVO>();
      for (SaleInvoiceHVO hvo : voHeads) {
        String vbillcode = hvo.getVbillcode();
        if (mapbillcode.containsKey(vbillcode)) {
          String id = hvo.getCsaleinvoiceid();
          if (null == id) {
            mapbillcode.remove(vbillcode);
            continue;
          }
          mapbillcode.get(vbillcode).setCsaleinvoiceid(hvo.getCsaleinvoiceid());
          newupdateHeads.add(mapbillcode.get(vbillcode));
        }
      }
      if (newupdateHeads.size() == 0) {
        return new SaleInvoiceHVO[0];
      }
      /**
       * modify by wangzym
       * 原来是只更新金税票号，现在根据鞍钢国贸郭晨旭要求增加一些其他的数据，在这增加
       */
      names = new String[] {
        SaleInvoiceHVO.VGOLDTAXCODE
      };
      VOUpdate<SaleInvoiceHVO> updatesrv = new VOUpdate<SaleInvoiceHVO>();
      SaleInvoiceHVO[] updatehvos =
          newupdateHeads.toArray(new SaleInvoiceHVO[newupdateHeads.size()]);
      return updatesrv.update(updatehvos, names);
    }
    catch (Exception ex) {
      ExceptionUtils.marsh(ex);
    }
    return new SaleInvoiceHVO[0];
  }

  private String createTempTable(String[] codes) {
    if (codes == null) {
      return new String();
    }
    if (codes.length <= 100) {
      SqlBuilder sql = new SqlBuilder();
      sql.append("vbillcode", codes);
      return sql.toString();
    }
    else {
      List<List<Object>> datalist = new ArrayList<List<Object>>();
      for (String code : codes) {
        List<Object> codelist = new ArrayList<Object>();
        codelist.add(code);
        datalist.add(codelist);
      }
      TempTable bo = new TempTable();
      String table = bo.getTempTable("tmp_invcodes", new String[] {
        "code"
      }, new String[] {
        "varchar(40)"
      }, new JavaType[] {
        JavaType.String
      }, datalist);
      return " vbillcode in (select code from " + table + ")";
    }
  }

  @Override
  public SaleInvoiceVO[] updateSaleInvoice(SaleInvoiceVO[] updatevos)
      throws BusinessException {
    SaleInvoiceVO[] retvos = null;
    BillTransferTool<SaleInvoiceVO> transferTool =
        new BillTransferTool<SaleInvoiceVO>(updatevos);
    try {
      SaleInvoiceVO[] fullbills = transferTool.getClientFullInfoBill();
      SaleInvoiceVO[] originBill = transferTool.getOriginBills();
      UpdateSaleInvoiceAction action = new UpdateSaleInvoiceAction();
      retvos = action.update(fullbills, originBill);
    }
    catch (Exception ex) {
      ExceptionUtils.marsh(ex);
    }
    return retvos;

  }

  @Override
  public SaleInvoiceHVO[] updateWhenExportGoldTax(SaleInvoiceHVO[] voHeads)
      throws BusinessException {
    String[] names = new String[] {
      SaleInvoiceHVO.BTOGOLDTAXFLAG, SaleInvoiceHVO.TGOLDTAXTIME
    };
    try {
      VOUpdate<SaleInvoiceHVO> updatesrv = new VOUpdate<SaleInvoiceHVO>();
      return updatesrv.update(voHeads, names);
    }
    catch (Exception ex) {
      ExceptionUtils.marsh(ex);
    }
    return new SaleInvoiceHVO[0];
  }

  /**
   * 拼接排序sql 默认方法（单据号、行号排序）
   * 
   * @param queryScheme
   * @return
   */
  private String createOrderSql(IQueryScheme queryScheme) {
    // 根据单据号、行号排序
    SqlBuilder order = new SqlBuilder();
    QuerySchemeProcessor processor = new QuerySchemeProcessor(queryScheme);
    order.append(" order by ");
    String tableName =
        processor.getTableAliasOfAttribute(SaleInvoiceHVO.class,
            SaleInvoiceHVO.VBILLCODE);
    order.append(tableName);
    order.append(".");
    order.append(SaleInvoiceHVO.VBILLCODE);
    order.append(",");
    tableName =
        processor.getTableAliasOfAttribute(SaleInvoiceBVO.class,
            SaleInvoiceBVO.CROWNO);
    order.append(tableName);
    order.append(".");
    order.append(SaleInvoiceBVO.CROWNO);
    return order.toString();

  }

  private void createSqlByQuerySchemeFor4C(IQueryScheme queryScheme) {
    QuerySchemeProcessor qsp = new QuerySchemeProcessor(queryScheme);
    // 增加固定where条件
    new SOQuerySchemeUtils().appendFixedWhr(queryScheme, qsp);

    // 增加集团
    qsp.appendCurrentGroup();

    // 得到子表别名
    String subTable =
        qsp.getTableAliasOfAttribute(SaleInvoiceBVO.class,
            SaleInvoiceBVO.BDISCOUNTFLAG);

    IQueryScheme filterorder = null;

    filterorder =
        RemoteFormSOUtil.getOutEndSQL4Filter32(subTable,
            SaleInvoiceBVO.CFIRSTBID);

    if (null != filterorder) {
      qsp.appendFrom(filterorder.getTableJoinFromWhereSQL().getFrom());
      qsp.appendWhere(" and ");
      qsp.appendWhere(filterorder.getTableJoinFromWhereSQL().getWhere());
    }

  }

  private SaleInvoiceVO[] querySaleInvoiceVOForSource(IQueryScheme scheme,
      String ordersql) {
    SchemeViewQuery<SaleInvoiceViewVO> query =
        new SchemeViewQuery<SaleInvoiceViewVO>(SaleInvoiceViewVO.class);
    SaleInvoiceViewVO[] views = query.query(scheme, ordersql);

    if (ArrayUtils.isEmpty(views)) {
      return null;
    }

    SaleInvoiceVO[] queryVos =
        new CombineViewToAggUtil<SaleInvoiceVO>(SaleInvoiceVO.class,
            SaleInvoiceHVO.class, SaleInvoiceBVO.class).combineViewToAgg(views,
            SaleInvoiceHVO.CSALEINVOICEID);

    return queryVos;
  }

  @Override
  public SaleInvoiceVO[] querySaleInvoice(String[] hids)
      throws BusinessException {
    SaleInvoiceVO[] bills = null;
    BillQuery<SaleInvoiceVO> query =
        new BillQuery<SaleInvoiceVO>(SaleInvoiceVO.class);
    bills = query.query(hids);
    return bills;
  }

	@SuppressWarnings("unchecked")
	@Override
	public AggYljsd[] queryIT02ForM32(IQueryScheme queryScheme)
			throws BusinessException {
		
		List<AggYljsd> result = (List<AggYljsd>) MDPersistenceService.lookupPersistenceQueryService().queryBillOfVOByCond(
				AggYljsd.class, queryScheme.getTableJoinFromWhereSQL().getWhere(), true, false);
		if (MMCollectionUtil.isEmpty(result)) {
			return null;
		}
		return result.toArray(new AggYljsd[0]);
//		NCObject[] ncObjects;
//		AggYljsd[] aggvo = null;
//			try {
////				+" and statusdzxh in (0,1)"+" and fstatusflag='2'"+" and dr='0'"+" and is_next='N'"
//				ncObjects = MDPersistenceService.lookupPersistenceQueryService().queryBillOfNCObjectByCond//statusdzxh
//						(AggYljsd.class,queryScheme.getTableJoinFromWhereSQL().getWhere(),false);
//				if (ncObjects!=null) {
//					aggvo = new AggYljsd[ncObjects.length];
//					//转换为AggVo
//					for(int i = 0; i<ncObjects.length; i++){
//						aggvo[i] = (AggYljsd)ncObjects[i].getContainmentObject();
//					} 
//				}
//				
//			} catch (MetaDataException e) {
//				ExceptionUtils.wrappBusinessException("更新-查询失败!");
//			}
//		 
////		 bills = this.queryGMJCHeadVOForSource(queryScheme, ordersql);
//		 return aggvo;
	}
	
	@SuppressWarnings("unchecked")
	@Override
	public AggCtjsdHeadVO[] queryIT01ForM32(IQueryScheme queryScheme)
			throws BusinessException {
		
		List<AggCtjsdHeadVO> result = (List<AggCtjsdHeadVO>) MDPersistenceService.lookupPersistenceQueryService().queryBillOfVOByCond(
				AggCtjsdHeadVO.class, queryScheme.getTableJoinFromWhereSQL().getWhere(), true, false);
		if (MMCollectionUtil.isEmpty(result)) {
			return null;
		}
		return result.toArray(new AggCtjsdHeadVO[0]);
//		NCObject[] ncObjects;
//		AggCtjsdHeadVO[] aggvo = null;
//			try {
////				+" and statusdzxh in (0,1)"+" and fstatusflag='2'"+" and dr='0'"+" and is_next='N'"
//				ncObjects = MDPersistenceService.lookupPersistenceQueryService().queryBillOfNCObjectByCond//statusdzxh
//						(AggCtjsdHeadVO.class,queryScheme.getTableJoinFromWhereSQL().getWhere(),false);
//				if (ncObjects!=null) {
//					aggvo = new AggCtjsdHeadVO[ncObjects.length];
//					//转换为AggVo
//					for(int i = 0; i<ncObjects.length; i++){
//						aggvo[i] = (AggCtjsdHeadVO)ncObjects[i].getContainmentObject();
//					} 
//				}
//				
//			} catch (MetaDataException e) {
//				ExceptionUtils.wrappBusinessException("更新-查询失败!");
//			}
//		 
////		 bills = this.queryGMJCHeadVOForSource(queryScheme, ordersql);
//		 return aggvo;
	}
	
	@SuppressWarnings("unchecked")
	@Override
	public AggGcdljsdHeadVO[] queryET03ForM32(IQueryScheme queryScheme)
			throws BusinessException {
		
		List<AggGcdljsdHeadVO> result = (List<AggGcdljsdHeadVO>) MDPersistenceService.lookupPersistenceQueryService().queryBillOfVOByCond(
				AggGcdljsdHeadVO.class, queryScheme.getTableJoinFromWhereSQL().getWhere(), true, false);
		if (MMCollectionUtil.isEmpty(result)) {
			return null;
		}
		return result.toArray(new AggGcdljsdHeadVO[0]);
//		NCObject[] ncObjects;
//		AggGcdljsdHeadVO[] aggvo = null;
//			try {
////				+" and statusdzxh in (0,1)"+" and fstatusflag='2'"+" and dr='0'"+" and is_next='N'"
//				ncObjects = MDPersistenceService.lookupPersistenceQueryService().queryBillOfNCObjectByCond//statusdzxh
//						(AggGcdljsdHeadVO.class,queryScheme.getTableJoinFromWhereSQL().getWhere(),false);
//				if (ncObjects!=null) {
//					aggvo = new AggGcdljsdHeadVO[ncObjects.length];
//					//转换为AggVo
//					for(int i = 0; i<ncObjects.length; i++){
//						aggvo[i] = (AggGcdljsdHeadVO)ncObjects[i].getContainmentObject();
//					} 
//				}
//				
//			} catch (MetaDataException e) {
//				ExceptionUtils.wrappBusinessException("更新-查询失败!");
//			}
//		 
////		 bills = this.queryGMJCHeadVOForSource(queryScheme, ordersql);
//		 return aggvo;
	}
	
	@SuppressWarnings("unchecked")
	@Override
	public AggFgjsdldHeadVO[] queryET02ForM32(IQueryScheme queryScheme)
			throws BusinessException {
		
		List<AggFgjsdldHeadVO> result = (List<AggFgjsdldHeadVO>) MDPersistenceService.lookupPersistenceQueryService().queryBillOfVOByCond(
				AggFgjsdldHeadVO.class, queryScheme.getTableJoinFromWhereSQL().getWhere(), true, false);
		if (MMCollectionUtil.isEmpty(result)) {
			return null;
		}
		return result.toArray(new AggFgjsdldHeadVO[0]);
//		NCObject[] ncObjects;
//		AggFgjsdldHeadVO[] aggvo = null;
//			try {
////				+" and statusdzxh in (0,1)"+" and fstatusflag='2'"+" and dr='0'"+" and is_next='N'"
//				ncObjects = MDPersistenceService.lookupPersistenceQueryService().queryBillOfNCObjectByCond//statusdzxh
//						(AggFgjsdldHeadVO.class,queryScheme.getTableJoinFromWhereSQL().getWhere(),false);
//				if (ncObjects!=null) {
//					aggvo = new AggFgjsdldHeadVO[ncObjects.length];
//					//转换为AggVo
//					for(int i = 0; i<ncObjects.length; i++){
//						aggvo[i] = (AggFgjsdldHeadVO)ncObjects[i].getContainmentObject();
//					} 
//				}
//				
//			} catch (MetaDataException e) {
//				ExceptionUtils.wrappBusinessException("更新-查询失败!");
//			}
//		 
////		 bills = this.queryGMJCHeadVOForSource(queryScheme, ordersql);
//		 return aggvo;
	}
	
	@SuppressWarnings("unchecked")
	@Override
	public AggJkyldlbzdzb1HeadVO[] queryLM40ForM32(IQueryScheme queryScheme)
			throws BusinessException {
		
		/*List<AggJkyldlbzdzb1HeadVO> result = (List<AggJkyldlbzdzb1HeadVO>) MDPersistenceService.lookupPersistenceQueryService().queryBillOfVOByCond(
				AggJkyldlbzdzb1HeadVO.class, queryScheme.getTableJoinFromWhereSQL().getWhere() + " and lm_jkyldlbzdzb1.brec_pay = '2' ", true, false);*/
		List<AggJkyldlbzdzb1HeadVO> result = (List<AggJkyldlbzdzb1HeadVO>) MDPersistenceService.lookupPersistenceQueryService().queryBillOfVOByCond(
				AggJkyldlbzdzb1HeadVO.class, queryScheme.getTableJoinFromWhereSQL().getWhere() + "   ", true, false);
		if (MMCollectionUtil.isEmpty(result)) {
			return null;
		}
		return result.toArray(new AggJkyldlbzdzb1HeadVO[0]);
//		NCObject[] ncObjects;
//		AggJkyldlbzdzb1HeadVO[] aggvo = null;
//			try {
////				+" and statusdzxh in (0,1)"+" and fstatusflag='2'"+" and dr='0'"+" and is_next='N'"
//				ncObjects = MDPersistenceService.lookupPersistenceQueryService().queryBillOfNCObjectByCond//statusdzxh
//						(AggJkyldlbzdzb1HeadVO.class,queryScheme.getTableJoinFromWhereSQL().getWhere(),false);
//				if (ncObjects!=null) {
//					aggvo = new AggJkyldlbzdzb1HeadVO[ncObjects.length];
//					//转换为AggVo
//					for(int i = 0; i<ncObjects.length; i++){
//						aggvo[i] = (AggJkyldlbzdzb1HeadVO)ncObjects[i].getContainmentObject();
//					} 
//				}
//				
//			} catch (MetaDataException e) {
//				ExceptionUtils.wrappBusinessException("更新-查询失败!");
//			}
//		 
////		 bills = this.queryGMJCHeadVOForSource(queryScheme, ordersql);
//		 return aggvo;
	}

	@SuppressWarnings("unchecked")
	@Override
	public AggYffyjsdHeadVO[] queryLM21ForM32(IQueryScheme queryScheme) 
			throws BusinessException {
		
		List<AggYffyjsdHeadVO> result = (List<AggYffyjsdHeadVO>) MDPersistenceService.lookupPersistenceQueryService().queryBillOfVOByCond(
				AggYffyjsdHeadVO.class, queryScheme.getTableJoinFromWhereSQL().getWhere() + " and brec_pay = '2' and is_next = 'N' ", true, false);
		if (MMCollectionUtil.isEmpty(result)) {
			return null;
		}
		return result.toArray(new AggYffyjsdHeadVO[0]);
//		NCObject[] ncObjects;
//		AggYffyjsdHeadVO[] aggvo = null;
//			try {
////				+" and statusdzxh in (0,1)"+" and fstatusflag='2'"+" and dr='0'"+" and is_next='N'"
//				// modify by baigs 2017-9-5 22:41:54 begin
////				ncObjects = MDPersistenceService.lookupPersistenceQueryService().queryBillOfNCObjectByCond//statusdzxh
////						(AggYffyjsdHeadVO.class,queryScheme.getTableJoinFromWhereSQL().getWhere(),false);
//				ncObjects = MDPersistenceService.lookupPersistenceQueryService().queryBillOfNCObjectByCond//statusdzxh
//						(AggYffyjsdHeadVO.class,queryScheme.getTableJoinFromWhereSQL().getWhere() + " and brec_pay = '2' ",false);
//				// end
//				if (ncObjects!=null) {
//					aggvo = new AggYffyjsdHeadVO[ncObjects.length];
//					//转换为AggVo
//					for(int i = 0; i<ncObjects.length; i++){
//						aggvo[i] = (AggYffyjsdHeadVO)ncObjects[i].getContainmentObject();
//					} 
//				}
//				
//			} catch (MetaDataException e) {
//				ExceptionUtils.wrappBusinessException("更新-查询失败!");
//			}
//		 
////		 bills = this.queryGMJCHeadVOForSource(queryScheme, ordersql);
//		 return aggvo;
	}
	
	 /**
	   * 删除应收单中数据
	   * @param ebwsload
	   * @return
	   */
	  public void deteleysd(String pk) {
			
			BaseDAO baseDao = new BaseDAO();
			String sqlStr = "update ar_recbill set modifiedtime=?,dr=1 where pk_recbill=? ";
			SQLParameter sp = new SQLParameter();
			Date date = new Date(); 
			UFDateTime systime= new UFDateTime(date.getTime()); 
			sp.addParam(systime);
			sp.addParam(pk);
			try {
				baseDao.executeUpdate(sqlStr, sp);
			} catch (DAOException e) {
				ExceptionUtils.wrappBusinessException("传统更新-执行失败!");
			}
			
		}
	
	  /**
	   * 修改销售发票下游生成标识状态
	   * @param ebwsload
	   * @return
	   */
	  public void updatexsfp(String pk) {
			
			BaseDAO baseDao = new BaseDAO();
			String sqlStr = "update so_saleinvoice set modifiedtime=?,vdef8=0 where csaleinvoiceid=? ";
			SQLParameter sp = new SQLParameter();
			Date date = new Date(); 
			UFDateTime systime= new UFDateTime(date.getTime()); 
			sp.addParam(systime);
			sp.addParam(pk);
			try {
				baseDao.executeUpdate(sqlStr, sp);
			} catch (DAOException e) {
				ExceptionUtils.wrappBusinessException("传统更新-执行失败!");
			}
			
		}
}
