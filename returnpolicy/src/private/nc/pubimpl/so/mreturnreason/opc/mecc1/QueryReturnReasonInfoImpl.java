package nc.pubimpl.so.mreturnreason.opc.mecc1;

import nc.vo.pub.BusinessException;
import nc.vo.pubapp.AppContext;
import nc.vo.pubapp.pattern.exception.ExceptionUtils;
import nc.vo.pubapp.pattern.pub.SqlBuilder;
import nc.vo.so.mreturnreason.entity.ReturnReasonVO;
import nc.vo.so.pub.SOTable;

import nc.pubitf.so.mreturnreason.opc.mecc1.IQueryReturnReasonInfo;
import nc.pubitf.so.mreturnreason.opc.mecc1.ReturnReasonInfoVO;

import nc.impl.pubapp.pattern.data.vo.VOQuery;
import nc.impl.pubapp.pattern.database.IDExQueryBuilder;

/**
 * 退货原因设置
 * 
 * @since 6.0
 * @version 2011-12-28 下午04:20:05
 * @author 刘景
 * 
 */
public class QueryReturnReasonInfoImpl implements IQueryReturnReasonInfo {

  @Override
  public ReturnReasonInfoVO[] queryGroupReturnReason(String[] pk_group)
      throws BusinessException {
    ReturnReasonInfoVO[] rrivo = null;
    if (null == pk_group || pk_group.length == 0) {
      ExceptionUtils.wrappBusinessException("集团PK为空");/*-=notranslate=-*/
    }
    try {
      // 要查询的实体属性名(集团、退货原因id、退货原因编码、退货原因名称)
      String[] entityNames =
          new String[] {
            ReturnReasonVO.PK_GROUP, ReturnReasonVO.PK_RETURNREASON,
            ReturnReasonVO.VREASONCODE, ReturnReasonVO.VREASONNAME
          };
      VOQuery<ReturnReasonVO> query =
          new VOQuery<ReturnReasonVO>(ReturnReasonVO.class, entityNames);
      // 构造查询条件
      SqlBuilder sWheres = new SqlBuilder();
      sWheres.append("and ");
      IDExQueryBuilder iq = new IDExQueryBuilder(SOTable.TMP_SO_ID1.getName());
      sWheres.append(iq.buildSQL(ReturnReasonVO.PK_GROUP, pk_group));
      sWheres.append(" and ");
      // 仅查集团级节点的数据
      sWheres.append(iq.buildSQL(ReturnReasonVO.PK_ORG, pk_group));
      ReturnReasonVO[] rrvo = query.query(sWheres.toString(), null);
      if (null == rrvo || rrvo.length == 0) {
        return new ReturnReasonInfoVO[0];
      }
      int len = rrvo.length;
      rrivo = new ReturnReasonInfoVO[len];
      // 把原对象数组赋值新对象数组
      for (int i = 0; i < len; i++) {
        rrivo[i] = new ReturnReasonInfoVO();
        // 退货原因id
        rrivo[i].setPk_returnreason(rrvo[i].getPk_returnreason());
        // 退货原因编码
        rrivo[i].setVreasoncode(rrvo[i].getVreasoncode());
        // 退货原因名称
        rrivo[i].setVreasonname(rrvo[i].getVreasonname());
        // 集团
        rrivo[i].setPk_group(rrvo[i].getPk_group());
      }

    }
    catch (Exception e) {
      ExceptionUtils.marsh(e);
    }
    return rrivo;
  }

  /**
   * 根据电子销售输入的销售组织，销售管理模块查询该销售组织可见的退货原因档案
   * 
   * @return 退货原因信息ReturnReasonVO
   * @throws BusinessException
   * @author 梁吉明
   */
  @Override
  public ReturnReasonVO[] queryReturnReasonByPk_orgs(String[] pk_orgs)
      throws BusinessException {
    if (null == pk_orgs || pk_orgs.length == 0) {
      return null;
    }
    ReturnReasonVO[] returnReasonVOs = null;
    try {
      // 构造查询条件
      SqlBuilder conditions = new SqlBuilder();
      String pk_group = AppContext.getInstance().getPkGroup();
      conditions.append(" and ");
      conditions.append(ReturnReasonVO.PK_GROUP, pk_group);
      conditions.append(" and ");
      IDExQueryBuilder iq = new IDExQueryBuilder(SOTable.TMP_SO_ID1.getName());
      conditions.append(iq.buildSQL(ReturnReasonVO.PK_ORG, pk_orgs));
      // 获得退货原因信息VO
      VOQuery<ReturnReasonVO> voQuery =
          new VOQuery<ReturnReasonVO>(ReturnReasonVO.class);
      returnReasonVOs = voQuery.query(conditions.toString(), null);

      if (null == returnReasonVOs || returnReasonVOs.length == 0) {
        return new ReturnReasonVO[0];
      }
    }
    catch (Exception e) {
      ExceptionUtils.marsh(e);
    }
    return returnReasonVOs;
  }
}
