package nc.bs.so.m30.rule.approve;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import nc.bs.so.m30.revise.UpdateSaleOrderBP;
import nc.bs.so.m30.revise.rule.ReviseApproveStateRule;
import nc.impl.pubapp.pattern.data.bill.BillQuery;
import nc.impl.pubapp.pattern.data.bill.tool.BillTransferTool;
import nc.impl.pubapp.pattern.database.DataAccessUtils;
import nc.impl.pubapp.pattern.rule.IRule;
import nc.vo.pub.BusinessException;
import nc.vo.pub.VOStatus;
import nc.vo.pub.lang.UFBoolean;
import nc.vo.pub.pf.BillStatusEnum;
import nc.vo.pubapp.pattern.data.IRowSet;
import nc.vo.pubapp.pattern.exception.ExceptionUtils;
import nc.vo.pubapp.pattern.pub.SqlBuilder;
import nc.vo.scmpub.util.ArrayUtil;
import nc.vo.so.m30.entity.SaleOrderBVO;
import nc.vo.so.m30.entity.SaleOrderHVO;
import nc.vo.so.m30.entity.SaleOrderVO;
import nc.vo.so.m30.revise.entity.SaleOrderHistoryBVO;
import nc.vo.so.m30.revise.entity.SaleOrderHistoryHVO;
import nc.vo.so.m30.revise.entity.SaleOrderHistoryVO;
import nc.vo.so.m30.rule.EditableAndRewiteItems;
import nc.vo.so.m30.util.Transfer30and30RVOTool;
import nc.vo.so.pub.util.ListUtil;

/**
 * @description
 *              销售订单修订审批后更新销售订单数据
 * @scene
 *        销售订单审批通过后
 * @param 无
 * @since 6.3
 * @version 2014-12-16 下午3:11:04
 * @author wangshu6
 */
public class SaleOrderReviseApproveAfterRule implements IRule {

  @Override
  public void process(Object[] vos) {

    SaleOrderHistoryVO[] revisebills = (SaleOrderHistoryVO[]) vos;

    // 先判断审批流状态 只有当选择“审批时”进行操作，选择“驳回、不审批”不进行
    for (SaleOrderHistoryVO bill : revisebills) {
      if (bill.getParentVO().getFpfstatusflag() == BillStatusEnum.APPROVED
          .toIntValue()) {
        SaleOrderVO[] saleOrderbills;
        try {
          // 1.获取修订完整vo
          revisebills = getOrderVOs(revisebills);
          // 2.从数据库查询 修订前版本VO
          saleOrderbills = query30BeforeRevise(revisebills);
          // 3.将可修订字段的值组合成新的销售订单vo
          SaleOrderVO[] newVO =
              this.copyChangedValueTo30VO(revisebills, saleOrderbills);
          // 4.更新到数据库
          this.updateSaleOrderByOrderHistoryVO(newVO, saleOrderbills);
        }
        catch (BusinessException ex) {
          ExceptionUtils.wrappException(ex);
        }
      }
    }
  }

  /**
   * 将销售订单修订数据更新到数据库销售订单表
   * 
   * @param newVO 由修订字段组成新销售订单vo
   * @param saleOrderbills 修订前销售订单vo
   */
  private void updateSaleOrderByOrderHistoryVO(SaleOrderVO[] newVO,
      SaleOrderVO[] saleOrderbills) {
    BillTransferTool<SaleOrderVO> transferTool =
        new BillTransferTool<SaleOrderVO>(newVO);
    SaleOrderVO[] updatebills = transferTool.getClientFullInfoBill();
    SaleOrderVO[] originBills = transferTool.getOriginBills();
    if (!ArrayUtil.isEmpty(saleOrderbills)) {
      UpdateSaleOrderBP udpateBP = new UpdateSaleOrderBP();
      this.beforeUpdateSaleOrder(updatebills);
      udpateBP.update(updatebills, originBills);
    }
  }

  /**
   * 销售订单修订更新销售订单之前需补充执行
   * 
   * @param updatebills 界面vo
   */
  protected void beforeUpdateSaleOrder(SaleOrderVO[] updatebills) {

    // 当单据没有做任何修改时，前台没有把VOStatus设置成修改状态，导致单据其他信息不更新。
    for (SaleOrderVO sobill : updatebills) {
      sobill.getParent().setStatus(VOStatus.UPDATED);
      for (SaleOrderBVO item : sobill.getChildrenVO()) {
        if (VOStatus.NEW == item.getStatus()) {
          // 修订时对新增行的处理
          this.processNewItem(item);
        }
      }
    }
  }

  /**
   * 查询销售订单修订前销售订单vo
   * 
   * @param bills 销售订单修订单据
   * @return 销售订单
   * @throws BusinessException
   */
  private SaleOrderVO[] query30BeforeRevise(SaleOrderHistoryVO[] bills)
      throws BusinessException {
    if (ArrayUtil.isEmpty(bills)) {
      return null;
    }
    // 获取修订vo上的销售订单id
    String[] ids = getSaleOrderPKs(bills);
    // 查询销售订单vo
    BillQuery<SaleOrderVO> query =
        new BillQuery<SaleOrderVO>(SaleOrderVO.class);
    SaleOrderVO[] saleorderbills = query.query(ids);

    return saleorderbills;

  }

  /**
   * 查询销售订单修订vo主键
   * 
   * @param bills
   * @return
   */
  private String[] getOrderPKs(SaleOrderHistoryVO[] bills) {
    List<String> hids = new ArrayList<String>();
    for (SaleOrderHistoryVO bill : bills) {
      String hid = bill.getParentVO().getCorderhistoryid();
      hids.add(hid);
    }
    return ListUtil.toArray(hids);
  }

  /**
   * 获取审批的完整修订vo
   * 
   * @param bills
   * @return
   */
  private SaleOrderHistoryVO[] getOrderVOs(SaleOrderHistoryVO[] bills) {
    // 查询销售订单修订vo主键
    String[] ids = getOrderPKs(bills);
    BillQuery<SaleOrderHistoryVO> query =
        new BillQuery<SaleOrderHistoryVO>(SaleOrderHistoryVO.class);
    // 查询完整修订vo
    SaleOrderHistoryVO[] orderHistoryVOs = query.query(ids);
    return orderHistoryVOs;
  }

  /**
   * 查询销售订单vo主键
   * 
   * @param bills
   * @return
   */
  private String[] getSaleOrderPKs(SaleOrderHistoryVO[] bills) {
    String[] ids = getOrderPKs(bills);
    SqlBuilder sql = new SqlBuilder();
    sql.append("select csaleorderid ");
    sql.append("from so_orderhistory where ");
    sql.append(SaleOrderHistoryHVO.CORDERHISTORYID, ids);
    sql.append(" and dr = 0");

    DataAccessUtils utils = new DataAccessUtils();
    IRowSet set = utils.query(sql.toString());
    if (set.size() == 0) {
      return null;
    }
    return set.toOneDimensionStringArray();
  }

  /**
   * 将可修订字段的值组合成新的销售订单vo
   * 
   * @param revisebills 修订vo
   * @param beforeRevise30Bills 修订前销售订单vo
   * @return 组合后的新销售订单vo
   */
  private SaleOrderVO[] copyChangedValueTo30VO(
      SaleOrderHistoryVO[] revisebills, SaleOrderVO[] beforeRevise30Bills) {
    List<SaleOrderVO> list = new ArrayList<SaleOrderVO>();
    SaleOrderVO newVO = null;
    for (SaleOrderHistoryVO revisebill : revisebills) {
      String csaleorderidBy30R = revisebill.getParentVO().getCsaleorderid();
      for (SaleOrderVO beforeRevise30Bill : beforeRevise30Bills) {
        String csaleorderidBy30 = beforeRevise30Bill.getPrimaryKey();
        if (csaleorderidBy30R.equals(csaleorderidBy30)) {
          // 将销售订单修订vo中的可修订字段组成一个新的vo
          newVO = getAttributesFor30By30R(revisebill, beforeRevise30Bill);
          list.add(newVO);
        }
      }
    }
    return ListUtil.toArray(list);
  }

  /**
   * 将销售订单修订vo中的可修订字段组成一个新的vo
   * 
   * @param revisebill 销售订单修订vo
   * @param beforeRevise30Bill 销售订单修订前 销售订单vo
   * @return 组合后的销售订单vo
   */
  private SaleOrderVO getAttributesFor30By30R(SaleOrderHistoryVO revisebill,
      SaleOrderVO beforeRevise30Bill) {
    SaleOrderVO saleorder = new SaleOrderVO();
    // 复制表头
    SaleOrderHVO soHead =
        this.getHeadAttributes(revisebill, beforeRevise30Bill);
    // 复制表体
    List<SaleOrderBVO> saleOrderList =
        getBodyAttributes(revisebill, beforeRevise30Bill);
    saleorder.setParentVO(soHead);
    saleorder.setChildrenVO(ListUtil.toArray(saleOrderList));
    return saleorder;
  }

  /**
   * 将销售订单修订vo中的表体可修订字段整合到销售订单bvo上
   * 
   * @param revisebill 销售订单修订vo
   * @param beforeRevise30Bill 销售订单修订前 销售订单vo
   * @return 组合后的销售订单bvo
   */
  private List<SaleOrderBVO> getBodyAttributes(SaleOrderHistoryVO revisebill,
      SaleOrderVO beforeRevise30Bill) {
    SaleOrderHistoryBVO[] revisebody = revisebill.getChildrenVO();
    SaleOrderBVO[] soBody = beforeRevise30Bill.getChildrenVO();

    // 循环销售订单表体置成形成一个saleorderMap<bid,bvo>
    Map<String, SaleOrderBVO> saleorderMap =
        new HashMap<String, SaleOrderBVO>();
    for (SaleOrderBVO vo : soBody) {
      saleorderMap.put(vo.getCsaleorderbid(), vo);
    }
    List<SaleOrderBVO> addList = new ArrayList<SaleOrderBVO>();
    for (int i = 0; i < revisebody.length; i++) {

      // 1. 判断表体id是否相同，相同复制属性值， 不相同放在一个addlist,说明是新增行。
      SaleOrderBVO newSaleOrderBVO =
          saleorderMap.get(revisebody[i].getCsaleorderbid());
      // 2. 销售订单修订bvo对应的销售订单bvo为空，说明该行为新增行，放在addList中
      if (newSaleOrderBVO == null) {
        Transfer30and30RVOTool trans = new Transfer30and30RVOTool();
        SaleOrderBVO[] newbvo = trans.transferVOS(new SaleOrderHistoryBVO[] {
          revisebody[i]
        }, SaleOrderBVO.class);
        newbvo[0].setStatus(VOStatus.NEW);
        addList.add(newbvo[0]);
      }
      else {
        // 3. 循环表体可修订字段 复制属性
        for (String str : EditableAndRewiteItems.BODYEDITABLEITEMKEY) {
          newSaleOrderBVO.setAttributeValue(str,
              revisebody[i].getAttributeValue(str));
        }
        // 4. 循环 回写影响字段 复制属性
        for (String str : EditableAndRewiteItems.BODYREWRITEMKEY) {
          newSaleOrderBVO.setAttributeValue(str,
              revisebody[i].getAttributeValue(str));
        }
      }
      // 5. 设置bvo状态
      this.setBVOStatus(revisebill, beforeRevise30Bill);
    }

    List<SaleOrderBVO> saleOrderList = new ArrayList<SaleOrderBVO>();
    saleOrderList.addAll(saleorderMap.values());
    // 处理新增行
    for (int i = 0; i < addList.size(); i++) {
      saleOrderList.add(addList.get(i));
    }
    return saleOrderList;
  }

  /**
   * 将销售订单修订vo中的表头可修订字段整合到销售订单hvo上
   * 
   * @param revisebill 销售订单修订vo
   * @param beforeRevise30Bill 销售订单修订前 销售订单vo
   * @return 组合后的销售订单hvo
   */
  private SaleOrderHVO getHeadAttributes(SaleOrderHistoryVO revisebill,
      SaleOrderVO beforeRevise30Bill) {

    SaleOrderHistoryHVO revisehead = revisebill.getParentVO();
    SaleOrderHVO soHead = beforeRevise30Bill.getParentVO();
    // 1. 循环表头可修订字段 复制属性
    for (String str : EditableAndRewiteItems.HEADEDITABLEITEMKEY) {
      soHead.setAttributeValue(str, revisehead.getAttributeValue(str));
    }
    // 2. 循环 回写影响字段 复制属性
    for (String str : EditableAndRewiteItems.HEADREWRITEMKEY) {
      soHead.setAttributeValue(str, revisehead.getAttributeValue(str));
    }
    // 3. 设置hvo状态
    soHead.setStatus(VOStatus.UPDATED);
    return soHead;
  }

  private void setBVOStatus(SaleOrderHistoryVO bill,
      SaleOrderVO beforeRevise30Bill) {

    // 获取销售订单修订表体主键
    List<String> reviseBIDs = getReviseBIDs(bill);
    // 获取销售订单修订表体主键
    List<String> saleorderBIDs = getSaleOrderBIDs(beforeRevise30Bill);

    // 1. saleorderBIDs和reviseBVOIDs一致，修改态,
    for (int i = 0; i < saleorderBIDs.size(); i++) {
      if (reviseBIDs.contains(saleorderBIDs.get(i))) {
        beforeRevise30Bill.getChildrenVO()[i].setStatus(VOStatus.UPDATED);
      }
    }
    // 2. 修订bids空，新增态。
    for (int i = 0; i < reviseBIDs.size(); i++) {
      if (reviseBIDs.get(i) == null) {
        bill.getChildrenVO()[i].setStatus(VOStatus.NEW);
      }
    }
    // 3. reviseBIDs不包含saleorderBIDs,删除态
    for (int i = 0; i < saleorderBIDs.size(); i++) {
      if (!reviseBIDs.contains(saleorderBIDs.get(i))) {
        beforeRevise30Bill.getChildrenVO()[i].setStatus(VOStatus.DELETED);
      }
    }
  }

  private List<String> getSaleOrderBIDs(SaleOrderVO beforeRevise30Bill) {
    List<String> saleorderBVOIDs = new ArrayList<String>();
    SaleOrderBVO[] saleorderBVOs = beforeRevise30Bill.getChildrenVO();
    for (SaleOrderBVO saleorderBVO : saleorderBVOs) {
      String saleorderBVOID = saleorderBVO.getCsaleorderbid();
      saleorderBVOIDs.add(saleorderBVOID);
    }
    return saleorderBVOIDs;
  }

  private List<String> getReviseBIDs(SaleOrderHistoryVO bill) {
    List<String> reviseBVOIDs = new ArrayList<String>();
    SaleOrderHistoryBVO[] reviseBVOs = bill.getChildrenVO();

    for (SaleOrderHistoryBVO reviseBVO : reviseBVOs) {
      String reviseBVOID = reviseBVO.getCsaleorderbid();
      reviseBVOIDs.add(reviseBVOID);

    }
    return reviseBVOIDs;
  }

  private void processNewItem(SaleOrderBVO item) {
    // 劳务类和折扣类物料，不发货，不出库，不成本结算
    if (UFBoolean.TRUE.equals(item.getBdiscountflag())
        || UFBoolean.TRUE.equals(item.getBlaborflag())) {
      item.setBbsendendflag(UFBoolean.TRUE);
      item.setBbcostsettleflag(UFBoolean.TRUE);
      item.setBboutendflag(UFBoolean.TRUE);
    }
  }

}
