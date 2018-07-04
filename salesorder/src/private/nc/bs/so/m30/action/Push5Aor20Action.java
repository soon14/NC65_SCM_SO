package nc.bs.so.m30.action;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import nc.bs.framework.common.NCLocator;
import nc.impl.pubapp.bill.convertor.BillToViewConvertor;
import nc.itf.scmpub.reference.uap.org.StockOrgPubService;
import nc.itf.scmpub.reference.uap.pf.PfServiceScmUtil;
import nc.itf.so.m30.ref.po.m20.POm20ServicesUtil;
import nc.itf.so.m30.ref.to.m5a.TOm5AServicesUtil;
import nc.pubitf.scmf.sourcing.sour4so.ISourceSOService;
import nc.pubitf.so.m30.pub.M30TranTypeUtil;
import nc.vo.pu.m20.entity.PraybillVO;
import nc.vo.pub.BusinessException;
import nc.vo.pubapp.pattern.data.IObjectConvert;
import nc.vo.pubapp.pattern.exception.ExceptionUtils;
import nc.vo.pubapp.pattern.pub.MapList;
import nc.vo.scmf.sourcing.entity.SORetVO;
import nc.vo.scmpub.res.billtype.POBillType;
import nc.vo.scmpub.res.billtype.SOBillType;
import nc.vo.scmpub.res.billtype.TOBillType;
import nc.vo.so.m30.entity.SaleOrderBVO;
import nc.vo.so.m30.entity.SaleOrderHVO;
import nc.vo.so.m30.entity.SaleOrderVO;
import nc.vo.so.m30.entity.SaleOrderViewVO;
import nc.vo.so.m30trantype.enumeration.DirectType;
import nc.vo.so.pub.util.AggVOUtil;
import nc.vo.to.m5a.entity.TransInVO;

/**
 * 销售转需求动作aciton
 * <p>
 * <b>本类主要完成以下功能：</b>
 * 
 * <ul>
 * <li>跨公司销售转需求：销售转调入申请
 * <li>单公司销售转需求：销售转请购
 * <li>...
 * </ul>
 * 
 * @version 6.0
 * @author zhangcheng
 * @time 2010-6-7 下午06:04:22
 */
public class Push5Aor20Action {

  private List<SaleOrderViewVO> alPo;

  private List<SaleOrderViewVO> alTo;

  private Map<String, SORetVO> sourceMap;

  public void process(SaleOrderVO[] vos) throws BusinessException {

    // 转换成view处理
    IObjectConvert<SaleOrderVO, SaleOrderViewVO> viewConvert =
        new BillToViewConvertor<SaleOrderVO, SaleOrderViewVO>(
            SaleOrderViewVO.class);
    SaleOrderViewVO[] views = viewConvert.convert(vos);

    // 寻源
    this.querySource(views);

    // 过滤VO
    this.filterVOs(views);

    if (this.alTo != null && this.alTo.size() > 0) {
      this.processPush5A();
    }
    if (this.alPo != null && this.alPo.size() > 0) {
      this.processPush20();
    }
  }

  private SaleOrderVO[] convertViewToBill(SaleOrderViewVO[] views) {
    MapList<String, SaleOrderViewVO> viewMapList =
        new MapList<String, SaleOrderViewVO>();
    for (SaleOrderViewVO view : views) {
      viewMapList.put(view.getHead().getPrimaryKey(), view);
    }
    int mapSize = viewMapList.size();
    SaleOrderVO[] bills = new SaleOrderVO[mapSize];
    String[] csaleorderids = viewMapList.keySet().toArray(new String[mapSize]);
    for (int i = 0; i < mapSize; i++) {
      // 组织表体vo
      List<SaleOrderViewVO> viewList = viewMapList.get(csaleorderids[i]);
      int listSize = viewList.size();
      SaleOrderBVO[] bodys = new SaleOrderBVO[listSize];
      SaleOrderViewVO[] viewsTemp =
          viewList.toArray(new SaleOrderViewVO[listSize]);
      for (int j = 0; j < listSize; j++) {
        bodys[j] = viewsTemp[j].getBody();
      }
      // 组装bill
      bills[i] = new SaleOrderVO();
      bills[i].setParentVO(viewList.get(0).getHead());
      bills[i].setChildrenVO(bodys);
    }
    return bills;
  }

  /**
   * 按需求转调入申请/请购单过滤
   */
  private void filterVOs(SaleOrderViewVO[] views) {
    // 获取库存组织对应的结算财务组织
    String[] sendstocks =
        AggVOUtil.getDistinctFieldArray(views, SaleOrderBVO.CSENDSTOCKORGID,
            String.class);
    Map<String, String> m_st_fin = null;
    m_st_fin = StockOrgPubService.queryFinanceOrgIDByStockOrgID(sendstocks);
    if (m_st_fin == null || m_st_fin.size() == 0) {
      return;
    }

    int directType = DirectType.DIRECTTRAN_NO.getIntValue();
    this.alPo = new ArrayList<SaleOrderViewVO>();
    this.alTo = new ArrayList<SaleOrderViewVO>();
    for (SaleOrderViewVO view : views) {
      directType = this.getDirecttype(view.getHead());
      String settleorg = view.getBody().getCsettleorgid();
      String finorgBystock = m_st_fin.get(view.getBody().getCsendstockorgid());
      // 不支持跨组织销售
      if (!settleorg.equals(finorgBystock)) {
        continue;
      }
      // 过滤掉劳务折扣行
      boolean laborflag =
          view.getBody().getBlaborflag() == null ? false : view.getBody()
              .getBlaborflag().booleanValue();
      boolean discountflag =
          view.getBody().getBdiscountflag() == null ? false : view.getBody()
              .getBdiscountflag().booleanValue();
      if (laborflag || discountflag) {
        continue;
      }
      // 根据直运类型分组
      if (DirectType.DIRECTTRAN_TO.getIntValue() == directType) {
        this.alTo.add(view);
      }
      else if (DirectType.DIRECTTRAN_PO.getIntValue() == directType) {
        this.alPo.add(view);
      }
      // 非直运的销售订单，基于寻源算法确定转请购还是转调入申请
      else if (DirectType.DIRECTTRAN_NO.getIntValue() == directType) {
        String materialid = view.getBody().getCmaterialid();
        String sendstockid = view.getBody().getCsendstockorgid();
        SORetVO sourceVO = this.sourceMap.get(materialid + sendstockid);
        if (sourceVO != null
            && TOBillType.TransIn.getCode().equals(
                sourceVO.getBillType().getCode())) {
          this.alTo.add(view);
        }
        else if (sourceVO != null
            && POBillType.PrayBill.getCode().equals(
                sourceVO.getBillType().getCode())) {
          this.alPo.add(view);
        }
      }
    }
  }

  private int getDirecttype(SaleOrderHVO hvo) {
    String ctrantypeid = hvo.getCtrantypeid();
    int flag = 0;
    Map<String, Integer> directMap =
        M30TranTypeUtil.getInstance().queryDirectType(new String[] {
          ctrantypeid
        });
    flag =
        directMap.get(ctrantypeid) == null ? flag : directMap.get(ctrantypeid)
            .intValue();
    return flag;
  }

  private void processPush20() {
    try {
      SaleOrderViewVO[] sopoview =
          this.alPo.toArray(new SaleOrderViewVO[this.alPo.size()]);
      SaleOrderVO[] sopovos = this.convertViewToBill(sopoview);
      // vo交换：30--->20
      PraybillVO[] bills =
          (PraybillVO[]) PfServiceScmUtil.executeVOChange(
              SOBillType.Order.getCode(), POBillType.PrayBill.getCode(),
              sopovos);

      // 推式生成请购单
      POm20ServicesUtil.push20ByPo(bills);
    }
    catch (BusinessException e) {
      ExceptionUtils.wrappException(e);
    }
  }

  private void processPush5A() {
    try {
      SaleOrderViewVO[] sotoview =
          this.alTo.toArray(new SaleOrderViewVO[this.alTo.size()]);
      SaleOrderVO[] sotovos = this.convertViewToBill(sotoview);
      // vo交换：30--->5A
      TransInVO[] bills =
          (TransInVO[]) PfServiceScmUtil
              .executeVOChange(SOBillType.Order.getCode(),
                  TOBillType.TransIn.getCode(), sotovos);

      // 推式生成调入申请
      TOm5AServicesUtil.push5AByTo(bills);
    }
    catch (BusinessException e) {
      ExceptionUtils.wrappException(e);
    }
  }

  /**
   * 寻源算法要求：
   * 1.物料[]区分：只关心采购件、分销补货件的物料
   * 2.采购件要寻源到采购组织和供应商:采购件VO{组织=采购组织、供应商=供应商、补货类型=请购单}
   * 3.分销补货件要寻源到调出库存组织:分销补货件VO{组织=调出库存组织、供应商=null、补货类型=调入申请}
   * 4.其他件VO{组织=null、供应商=null、补货类型=null}
   * 注：由于上下游寻源算法重复：采购组织、供应商、调出库存组织由下游寻源算法处理，此处只关心过滤物料。
   * 
   * @throws BusinessException
   */
  private void querySource(SaleOrderViewVO[] views) throws BusinessException {
    List<String> materialIDList = new ArrayList<String>();
    List<String> sendStockIDList = new ArrayList<String>();
    for (SaleOrderViewVO view : views) {
      materialIDList.add(view.getBody().getCmaterialid());
      sendStockIDList.add(view.getBody().getCsendstockorgid());
    }
    String[] materialIDs =
        materialIDList.toArray(new String[materialIDList.size()]);
    String[] sendStockIDs =
        sendStockIDList.toArray(new String[sendStockIDList.size()]);
    // 调用寻源接口查询
    ISourceSOService service =
        NCLocator.getInstance().lookup(ISourceSOService.class);
    SORetVO[] sourceVOs = null;
    try {
      sourceVOs = service.query(materialIDs, sendStockIDs);

      // 调整返回值存储结构
      this.sourceMap = new HashMap<String, SORetVO>();
      List<String> orgIDList = new ArrayList<String>();
      if (sourceVOs != null && sourceVOs.length > 0) {
        for (int i = 0; i < sourceVOs.length; i++) {
          // 前提是返回值和参数顺序是一一对应的
          if (sourceVOs[i] == null) {
            continue;
          }
          this.sourceMap.put(materialIDs[i] + sendStockIDs[i], sourceVOs[i]);
          orgIDList.add(sourceVOs[i].getPk_org());
        }
      }
    }
    catch (BusinessException e) {
      ExceptionUtils.marsh(e);
    }
  }
}
