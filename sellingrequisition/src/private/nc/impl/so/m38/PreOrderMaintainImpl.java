package nc.impl.so.m38;

import nc.impl.pubapp.env.BSContext;
import nc.impl.pubapp.pattern.data.bill.BillLazyQuery;
import nc.impl.pubapp.pattern.data.view.SchemeViewQuery;
import nc.impl.so.m38.action.DeletePreOrderAction;
import nc.impl.so.m38.action.InsertPreOrderAction;
import nc.impl.so.m38.action.InvalidatePreorderAction;
import nc.impl.so.m38.action.QueryPreOrderAction;
import nc.impl.so.m38.action.UpdatePreOrderAction;
import nc.itf.so.m38.IPreOrderMaintain;
import nc.ui.querytemplate.querytree.IQueryScheme;
import nc.vo.pub.BusinessException;
import nc.vo.pub.lang.UFBoolean;
import nc.vo.pub.lang.UFDate;
import nc.vo.pubapp.AppContext;
import nc.vo.pubapp.pattern.exception.ExceptionUtils;
import nc.vo.pubapp.pattern.pub.SqlBuilder;
import nc.vo.pubapp.query2.sql.process.QuerySchemeProcessor;
import nc.vo.pubapp.util.CombineViewToAggUtil;
import nc.vo.scmpub.res.billtype.SOBillType;
import nc.vo.so.m38.entity.PreOrderBVO;
import nc.vo.so.m38.entity.PreOrderHVO;
import nc.vo.so.m38.entity.PreOrderVO;
import nc.vo.so.m38.entity.PreOrderViewVO;
import nc.vo.so.pub.enumeration.BillStatus;

import org.apache.commons.lang.ArrayUtils;

public class PreOrderMaintainImpl implements IPreOrderMaintain {

  @Override
  public void deletePreOrder(PreOrderVO[] bills) throws BusinessException {
    try {
      DeletePreOrderAction action = new DeletePreOrderAction();
      action.delete(bills);
    }
    catch (Exception ex) {
      ExceptionUtils.marsh(ex);
    }
  }

  @Override
  public PreOrderVO insertPreOrder(PreOrderVO bill) throws BusinessException {
    PreOrderVO[] ret = null;
    PreOrderVO[] bills = new PreOrderVO[] {
      bill
    };
    try {
      InsertPreOrderAction action = new InsertPreOrderAction();
      ret = action.insert(bills);
    }
    catch (Exception ex) {
      ExceptionUtils.marsh(ex);
    }
    return null == ret ? null : ret[0];
  }

  @Override
  public PreOrderVO[] invalidationPreorder(PreOrderVO[] vos)
      throws BusinessException {
    InvalidatePreorderAction action = new InvalidatePreorderAction();
    return action.invalidatePreorder(vos);
  }

  @Override
  public PreOrderVO[] queryPreOrder(IQueryScheme queryScheme)
      throws BusinessException {
    PreOrderVO[] bills = null;
    try {
      QuerySchemeProcessor processor = new QuerySchemeProcessor(queryScheme);
      String headTableName = processor.getMainTableAlias();
      processor.appendCurrentGroup();
      processor.appendFuncPermissionOrgSql();
      // 排序
      SqlBuilder order = new SqlBuilder();
      order.append("order by ");
      order.append(headTableName);
      order.append(".vbillcode");
      // 改用懒加载
      BillLazyQuery<PreOrderVO> qry =
          new BillLazyQuery<PreOrderVO>(PreOrderVO.class);
      bills = qry.query(queryScheme, order.toString());
    }
    catch (Exception ex) {
      ExceptionUtils.marsh(ex);
    }
    return bills;

  }

  @Override
  public PreOrderVO[] queryPreOrder(String sql) throws BusinessException {
    QueryPreOrderAction bo = new QueryPreOrderAction();
    return bo.queryPreOrder(sql);
  }

  @Override
  public PreOrderVO[] queryPreOrderFor30(IQueryScheme queryScheme)
      throws BusinessException {
    QuerySchemeProcessor processor = new QuerySchemeProcessor(queryScheme);

    String maintablename = processor.getMainTableAlias();
    SqlBuilder sqlbuild = new SqlBuilder();
    // 拼接whereSql必要条件
    sqlbuild.append(" and ");
    String pk_group = BSContext.getInstance().getGroupID();
    sqlbuild.append(maintablename + ".pk_group", pk_group);
    sqlbuild.append(" and ");
    sqlbuild.append(maintablename + ".fstatusflag",
        BillStatus.AUDIT.getIntValue());
    // 过滤掉审核通过状态，但失效日期小于当前业务日期的订单
    UFDate busiDate = AppContext.getInstance().getBusiDate();
    sqlbuild.append(" and ");
    sqlbuild.append(maintablename + ".dabatedate" + " > '" + busiDate + "'");

    String chidtable =
        processor.getTableAliasOfAttribute("so_preorder_b.blineclose");
    // 表体关闭状态
    sqlbuild.append(" and ");
    sqlbuild.append(chidtable + "." + PreOrderBVO.BLINECLOSE, UFBoolean.FALSE);
    // 表体安排值
    sqlbuild.append(" and ");
    sqlbuild.append(" abs( " + chidtable + ".nnum ) > abs( isnull(" + chidtable
        + ".narrnum,0)) ");

    processor.appendWhere(sqlbuild.toString());
    processor.appendRefTrantypeWhere(SOBillType.PreOrder.getCode(),
        SOBillType.Order.getCode(), PreOrderHVO.VTRANTYPECODE);

    // 拼接排序sql
    String ordersql = this.createOrderSql(queryScheme);

    SchemeViewQuery<PreOrderViewVO> query =
        new SchemeViewQuery<PreOrderViewVO>(PreOrderViewVO.class);
    PreOrderViewVO[] views = query.query(queryScheme, ordersql);
    if (ArrayUtils.isEmpty(views)) {
      return null;
    }
    for (PreOrderViewVO view : views) {
      PreOrderHVO headvo = view.getHead();
      PreOrderBVO bodyvo = view.getItem();
      headvo.setPk_group(bodyvo.getPk_group());
      headvo.setPk_org(bodyvo.getPk_org());
      headvo.setDbilldate(bodyvo.getDbilldate());
      headvo.setCorigcurrencyid(bodyvo.getCorigcurrencyid());
    }
    PreOrderVO[] queryVos =
        new CombineViewToAggUtil<PreOrderVO>(PreOrderVO.class,
            PreOrderHVO.class, PreOrderBVO.class).combineViewToAgg(views,
            PreOrderHVO.CPREORDERID);
    return queryVos;
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
        processor.getTableAliasOfAttribute(PreOrderHVO.class,
            PreOrderHVO.VBILLCODE);
    order.append(tableName);
    order.append(".");
    order.append(PreOrderHVO.VBILLCODE);
    order.append(",");
    tableName =
        processor.getTableAliasOfAttribute(PreOrderBVO.class,
            PreOrderBVO.CROWNO);
    order.append(tableName);
    order.append(".");
    order.append(PreOrderBVO.CROWNO);
    return order.toString();

  }

  @Override
  public PreOrderVO[] updatePreOrder(PreOrderVO[] bills, PreOrderVO[] originBill)
      throws BusinessException {
    PreOrderVO[] ret = null;
    try {

      UpdatePreOrderAction action = new UpdatePreOrderAction();
      ret = action.update(bills, originBill);
    }
    catch (Exception ex) {
      ExceptionUtils.marsh(ex);
    }
    return ret;
  }

}
