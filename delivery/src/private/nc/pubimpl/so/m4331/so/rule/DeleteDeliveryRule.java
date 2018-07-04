package nc.pubimpl.so.m4331.so.rule;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import nc.bs.framework.common.NCLocator;
import nc.impl.pubapp.pattern.data.bill.tool.BillTransferTool;
import nc.impl.pubapp.pattern.database.DataAccessUtils;
import nc.impl.pubapp.pattern.database.IDExQueryBuilder;
import nc.itf.scmpub.reference.uap.pf.PfServiceScmUtil;
import nc.itf.uap.pf.IplatFormEntry;
import nc.ui.pub.bill.BillStatus;
import nc.vo.pub.BusinessException;
import nc.vo.pub.VOStatus;
import nc.vo.pubapp.pattern.data.IRowSet;
import nc.vo.pubapp.pattern.exception.ExceptionUtils;
import nc.vo.pubapp.pattern.pub.SqlBuilder;
import nc.vo.scmpub.res.billtype.SOBillType;
import nc.vo.so.m4331.entity.DeliveryBVO;
import nc.vo.so.m4331.entity.DeliveryVO;
import nc.vo.so.pub.SOTable;

/**
 * 删除发货单业务规则
 * 
 * @since 6.1
 * @version 2012-11-29 11:01:11
 * @author 冯加彬
 */
public class DeleteDeliveryRule {

  /**
   * 
   * 设置要删除发货单行VO状态
   * 
   * @param vos
   */
  public void deleteDelivery(DeliveryVO[] vos) {
    try {
      List<DeliveryVO> freeList = new ArrayList<DeliveryVO>();
      for (DeliveryVO vo : vos) {
        if (vo.getParentVO().getFstatusflag().intValue() == BillStatus.FREE) {
          freeList.add(vo);
        }
      }
      if (freeList.size() > 0) {
        DeliveryVO[] freevos = new DeliveryVO[freeList.size()];
        freevos = freeList.toArray(freevos);
        // 设置表体vo状态
        for (DeliveryVO vo : freevos) {
          for (DeliveryBVO bvo : vo.getChildrenVO()) {
            bvo.setStatus(VOStatus.DELETED);
          }
        }
        this.deleteVoForProcessOrderOutEnd(freevos);
      }

    }
    catch (BusinessException e) {
      ExceptionUtils.wrappBusinessException(e.getMessage());
    }
  }

  private void deleteVoForProcessOrderOutEnd(DeliveryVO[] freevos)
      throws BusinessException {
    /** 1.过滤出整单删除和非整单删除的vo************************** */
    String[] hids = new String[freevos.length];
    for (int i = 0; i < freevos.length; i++) {
      hids[i] = freevos[i].getParentVO().getPrimaryKey();
    }
    // 查询表头id对应的表体行id的个数
    Map<String, Integer> map = this.getRowCount(hids);
    // 过滤:整单删除的vo、非整单删除的vo
    List<DeliveryVO> alldelvolist = new ArrayList<DeliveryVO>();
    List<DeliveryVO> delvolist = new ArrayList<DeliveryVO>();
    for (DeliveryVO vo : freevos) {
      // 整单删除
      if (vo.getChildrenVO().length == map.get(vo.getParentVO().getPrimaryKey())
          .intValue()) {
        alldelvolist.add(vo);
        // 非整单删除
      }
      else {
        delvolist.add(vo);
        /** 1.过滤出整单删除和非整单删除的vo************************** */
      }
    }

    /** 2.删除（整单）或保存（非整单）************************** */
    // 删除（整单）
    if (alldelvolist.size() > 0) {
      DeliveryVO[] alldelvos = new DeliveryVO[alldelvolist.size()];
      alldelvos = alldelvolist.toArray(alldelvos);
      for (DeliveryVO delvo : alldelvos) {
        delvo.getParentVO().setStatus(VOStatus.DELETED);
      }
      // 调用发货单删除脚本
      PfServiceScmUtil.processBatch("DELETE", SOBillType.Delivery.getCode(),
          alldelvos, null, null);
    }
    // 保存（非整单）
    if (delvolist.size() > 0) {
      DeliveryVO[] delvos = new DeliveryVO[delvolist.size()];
      delvos = delvolist.toArray(delvos);
      BillTransferTool<DeliveryVO> transferTool =
          new BillTransferTool<DeliveryVO>(delvos);
      DeliveryVO[] clientbills = transferTool.getClientFullInfoBill();
      for (DeliveryVO vo : clientbills) {
        NCLocator.getInstance().lookup(IplatFormEntry.class).processAction(
            "WRITE", SOBillType.Delivery.getCode(), null, vo, null,
            new HashMap());
      }
    }
  }

  private Map<String, Integer> getRowCount(String[] hids) {
    SqlBuilder sql = new SqlBuilder();
    sql.append("select cdeliveryid ,count(cdeliverybid) from "
        + "so_delivery_b where dr =0 and ");
    IDExQueryBuilder iq = new IDExQueryBuilder(SOTable.TMP_SO_ID1.getName());
    sql.append(iq.buildSQL(DeliveryBVO.CDELIVERYID, hids));
    sql.append(" group by cdeliveryid");
    DataAccessUtils utils = new DataAccessUtils();
    IRowSet rowset = utils.query(sql.toString());

    if (rowset.size() == 0) {
      return null;
    }
    Map<String, Integer> map = new HashMap<String, Integer>();
    while (rowset.next()) {
      String id = rowset.getString(0);
      Integer count = rowset.getInteger(1);
      map.put(id, count);
    }
    return map;
  }
}
