package nc.vo.so.m30.util;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import nc.bs.framework.common.NCLocator;
import nc.impl.pubapp.pattern.data.view.ViewQuery;
import nc.itf.so.m30.profit.ISaleOrderProfitCal;
import nc.vo.pub.BusinessException;
import nc.vo.pub.lang.UFDouble;
import nc.vo.pubapp.bill.CombineBill;
import nc.vo.pubapp.pattern.model.entity.bill.IBill;
import nc.vo.pubapp.pattern.pub.PubAppTool;
import nc.vo.scmpub.res.billtype.SOBillType;
import nc.vo.so.entry.ProfitVO;
import nc.vo.so.m30.entity.SaleOrderBVO;
import nc.vo.so.m30.entity.SaleOrderHVO;
import nc.vo.so.m30.entity.SaleOrderVO;
import nc.vo.so.m30.entity.SaleOrderViewVO;
import nc.vo.so.m35.entity.ArsubInterfaceVO;
import nc.vo.so.pub.enumeration.BillStatus;
import nc.vo.so.pub.util.ArrayUtil;
import nc.vo.so.pub.util.ListUtil;
import nc.vo.so.pub.util.ProfitCaculateUtil;
import nc.vo.so.pub.util.SOVOChecker;
import nc.vo.so.pub.votools.SoVoTools;

/**
 * 销售订单VO工具类
 * 
 * @since 6.0
 * @version 2011-2-21 下午02:17:26
 * @author 么贵敬
 */
public class SaleOrderVOUtil {

  /**
   * 将订单VO转化为销售费用单接口VO
   * 
   * @param vos
   * @return
   */
  public ArsubInterfaceVO[] changeToArsubInterfaceVO(SaleOrderVO[] vos) {
    ArsubInterfaceVO[] arsubvo = new ArsubInterfaceVO[vos.length];
    for (int i = 0; i < arsubvo.length; i++) {
      arsubvo[i] = new ArsubInterfaceVO();
      arsubvo[i].setCsalebillid(vos[i].getParentVO().getCsaleorderid());
      arsubvo[i].setVbillcode(vos[i].getParentVO().getVbillcode());
      arsubvo[i].setVbilltype(SOBillType.Order.getCode());
    }
    return arsubvo;

  }

  /**
   * 毛利计算需要批量获取成本域和成本单价
   * 
   * @param vos 销售订单VO数组
   * @return 计算好的毛利VO
   * @throws BusinessException
   * @see
   */
  public ProfitVO[] changeToProfitVO(SaleOrderVO[] vos) {
    List<ProfitVO> vievowlist = new ArrayList<ProfitVO>();

    for (SaleOrderVO vo : vos) {
      SaleOrderBVO[] bvos = vo.getChildrenVO();
      SaleOrderHVO hvos=vo.getParentVO();
      for (SaleOrderBVO bvo : bvos) {
        String marterialvid = bvo.getCmaterialvid();
        String sendstockorgid = bvo.getCsendstockorgid();

        if (PubAppTool.isNull(marterialvid)
            || PubAppTool.isNull(sendstockorgid)) {
          continue;
        }
        ProfitVO profitvo = new ProfitVO();
        profitvo.setCstockorgid(sendstockorgid);
        profitvo.setCstordocid(bvo.getCsendstordocid());
        profitvo.setCmaterialid(bvo.getCmaterialid());
        profitvo.setCmaterialvid(marterialvid);
        profitvo.setNastnum(bvo.getNnum());
        profitvo.setVbatchcode(bvo.getVbatchcode());
        profitvo.setNqtorignetprice(bvo.getNnetprice());
        profitvo.setCastunitid(bvo.getCastunitid());
        profitvo.setCorigcurrencyid(bvo.getCcurrencyid());
        profitvo.setOrdertantype(hvos.getCtrantypeid());

        UFDouble totalincome = bvo.getNmny();

        if (bvo.getBlargessflag().booleanValue()) {
          totalincome = UFDouble.ZERO_DBL;
        }
        profitvo.setNtotalincome(totalincome);
        profitvo.setPk_org(bvo.getPk_org());
        profitvo.setCfinanceorg(bvo.getCsettleorgid());

        profitvo.setBlargessflag(bvo.getBlargessflag());
        vievowlist.add(profitvo);
      }
    }
    // 批量调用取成本域和成本单价接口计算毛利
    ProfitCaculateUtil cacProfigUtil = new ProfitCaculateUtil(vievowlist);
    cacProfigUtil.caculateProfit();

    ProfitVO[] profitvos = vievowlist.toArray(new ProfitVO[vievowlist.size()]);
    return profitvos;

  }

  /**
   * 毛利计算需要批量获取成本域和成本单价
   * 
   * @param hids 销售订单头ID数组
   * @return 计算好的毛利VO
   * @throws BusinessException
   * @see
   */
  public ProfitVO[] changeToProfitVO(String[] hids) throws BusinessException {
    ISaleOrderProfitCal service =
        NCLocator.getInstance().lookup(ISaleOrderProfitCal.class);
    return service.caculate30Profit(hids);
  }

  /**
   * 试图VO转化为单据实体VO
   * 
   * @param viewvos
   * @return
   */
  public SaleOrderVO[] chgViewToBill(SaleOrderViewVO[] viewvos) {

    List<SaleOrderVO> list = new ArrayList<SaleOrderVO>();
    for (SaleOrderViewVO svo : viewvos) {
      list.add(svo.changeToSaleOrderVO());
    }

    CombineBill<SaleOrderVO> tool = new CombineBill<SaleOrderVO>();
    tool.appendKey(SaleOrderHVO.CSALEORDERID);
    return tool.combine(list.toArray(new SaleOrderVO[0]));

  }

  /**
   * 结算关闭刷新ts，结算关闭标志
   * 
   * @param views
   */
  public void refreshViewForSettleClose(SaleOrderViewVO[] views) {
    String[] bids =
        SoVoTools.getVOsOnlyValues(views, SaleOrderBVO.CSALEORDERBID);
    SaleOrderViewVO[] rets =
        new ViewQuery<SaleOrderViewVO>(SaleOrderViewVO.class).query(bids);
    Map<String, SaleOrderViewVO> map = new HashMap<String, SaleOrderViewVO>();
    for (SaleOrderViewVO view : rets) {
      map.put(view.getBody().getCsaleorderbid(), view);
    }
    for (SaleOrderViewVO view : views) {
      SaleOrderViewVO dbview = map.get(view.getBody().getCsaleorderbid());
      SaleOrderHVO dbhvo = dbview.getHead();
      SaleOrderBVO dbbvo = dbview.getBody();
      view.getHead().setTs(dbhvo.getTs());
      view.getBody().setTs(dbbvo.getTs());
      view.getBody().setBbarsettleflag(dbbvo.getBbarsettleflag());
      view.getBody().setBbcostsettleflag(dbbvo.getBbcostsettleflag());
    }
  }

  /**
   * 单据状态集合（值）
   * 
   * @return
   */
  public Integer[] getBillStatusValue() {
    Integer[] statusvalues =
        new Integer[] {
          BillStatus.FREE.getIntegerValue(),
          BillStatus.AUDITING.getIntegerValue(),
          BillStatus.AUDIT.getIntegerValue(),
          BillStatus.NOPASS.getIntegerValue(),
          BillStatus.FREEZE.getIntegerValue(),
          BillStatus.CLOSED.getIntegerValue()
        };
    return statusvalues;
  }

  /**
   * 单据状态集合（名称）
   * 
   * @return
   */
  public String[] getBillStatusName() {
    String[] statusName =
        new String[] {
          BillStatus.FREE.getName(), BillStatus.AUDITING.getName(),
          BillStatus.AUDIT.getName(), BillStatus.NOPASS.getName(),
          BillStatus.FREEZE.getName(), BillStatus.CLOSED.getName()

        };
    return statusName;
  }
  
  /**
   * 销售订单VO转换成销售订单视图VO
   * 
   * @param vos
   * @return
   */
  public static SaleOrderViewVO[] constructVOToViewVO(IBill[] vos) {

    if (SOVOChecker.isEmpty(vos)) {
      return new SaleOrderViewVO[0];
    }
    List<SaleOrderViewVO> viewVOList = new ArrayList<>();
    for (IBill vo : vos) {
      SaleOrderHVO hvo = ((SaleOrderVO) vo).getParentVO();
      SaleOrderBVO[] bvos = ((SaleOrderVO) vo).getChildrenVO();
      if (ArrayUtil.isEmpty(bvos)) {
        continue;
      }
      for (SaleOrderBVO bvo : bvos) {
        SaleOrderViewVO viewVO = new SaleOrderViewVO();
        viewVO.setHead(hvo);
        viewVO.setBody(bvo);
        viewVOList.add(viewVO);
      }
    }
    return ListUtil.toArray(viewVOList);
  }
}
