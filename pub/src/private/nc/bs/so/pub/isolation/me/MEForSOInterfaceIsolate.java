package nc.bs.so.pub.isolation.me;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;
import java.util.Set;

import nc.bs.framework.common.NCLocator;
import nc.impl.pubapp.pattern.data.vo.VOQuery;
import nc.pubimpl.so.m30.ic.pub.VoucherData;
import nc.pubitf.me.m35meext.so.IArsubVoucherForSaleOrder;
import nc.pubitf.me.m35meext.so.IVoucherData;
import nc.pubitf.so.m30.ic.pub.ArsubToVoucherData;
import nc.vo.pub.BusinessException;
import nc.vo.pub.lang.UFDouble;
import nc.vo.pubapp.pattern.exception.ExceptionUtils;
import nc.vo.pubapp.pattern.pub.MathTool;
import nc.vo.so.m30.entity.SaleOrderBVO;

/**
 * 销售管理调用营销费用接口的隔离层
 * 
 * @author zhangby5
 * 
 */
public class MEForSOInterfaceIsolate {

  /**
   * 销售出库单签字影响客户费用单对账金额及凭证
   * 
   * @param datas 回写数据
   * @throws BusinessException
   */
  public static void onSaleOutSign(ArsubToVoucherData[] data)
      throws BusinessException {

    try {

      ArsubToVoucherData[] combindata = getcombinData(data);
      IArsubVoucherForSaleOrder toVoucher =
          NCLocator.getInstance().lookup(IArsubVoucherForSaleOrder.class);
      SaleOrderBVO[] salebvos = getSaleorderBody(combindata);
      List<IVoucherData> datas = new ArrayList<IVoucherData>();
      int i = 0;
      for (SaleOrderBVO salebvo : salebvos) {
        if (!canArsubToVoucher(salebvo)) {
          i++;
          continue;
        }
        datas.add(getVoucherData(salebvo, combindata[i].getNnum()));
        i++;
      }
      if (datas.size() > 0) {
        toVoucher.onSaleOutSign(datas.toArray(new IVoucherData[0]));
      }
    }
    catch (Exception ex) {
      ExceptionUtils.marsh(ex);
    }
  }

  /**
   * 销售出库单取消签字影响客户费用单对账金额及凭证
   * 
   * @param datas 回写数据
   * @throws BusinessException
   */
  public static void onSaleOutUnSign(ArsubToVoucherData[] data)
      throws BusinessException {
    try {

      ArsubToVoucherData[] combindata = getcombinData(data);

      IArsubVoucherForSaleOrder toVoucher =
          NCLocator.getInstance().lookup(IArsubVoucherForSaleOrder.class);
      SaleOrderBVO[] salebvos = getSaleorderBody(combindata);
      List<IVoucherData> datas = new ArrayList<IVoucherData>();
      int i = 0;
      for (SaleOrderBVO salebvo : salebvos) {
        if (!canArsubToVoucher(salebvo)) {
          i++;
          continue;
        }
        datas.add(getVoucherData(salebvo, combindata[i].getNnum()));
        i++;
      }
      if (datas.size() > 0) {
        toVoucher.onSaleOutUnSign(datas.toArray(new IVoucherData[0]));
      }
    }
    catch (Exception ex) {
      ExceptionUtils.marsh(ex);
    }
  }

  /**
   * 签收途损审批影响客户费用单对账金额及凭证
   * 
   * @param datas 回写数据
   * @throws BusinessException
   */
  public static void onWastageSign(ArsubToVoucherData[] data)
      throws BusinessException {
    try {

      ArsubToVoucherData[] combindata = getcombinData(data);

      IArsubVoucherForSaleOrder toVoucher =
          NCLocator.getInstance().lookup(IArsubVoucherForSaleOrder.class);
      SaleOrderBVO[] salebvos = getSaleorderBody(combindata);
      List<IVoucherData> datas = new ArrayList<IVoucherData>();
      int i = 0;
      for (SaleOrderBVO salebvo : salebvos) {
        if (!canArsubToVoucher(salebvo)) {
          i++;
          continue;
        }
        datas.add(getVoucherData(salebvo, combindata[i].getNnum()));
        i++;
      }
      if (datas.size() > 0) {
        toVoucher.onWastageSign(datas.toArray(new IVoucherData[0]));
      }
    }
    catch (Exception ex) {
      ExceptionUtils.marsh(ex);
    }
  }

  /**
   * 签收途损单取消审批影响客户费用单对账金额及凭证
   * 
   * @param datas 回写数据
   * @throws BusinessException
   */
  public static void onWastageUnSign(ArsubToVoucherData[] data)
      throws BusinessException {
    try {
      ArsubToVoucherData[] combindata = getcombinData(data);

      IArsubVoucherForSaleOrder toVoucher =
          NCLocator.getInstance().lookup(IArsubVoucherForSaleOrder.class);
      SaleOrderBVO[] salebvos = getSaleorderBody(combindata);
      List<IVoucherData> datas = new ArrayList<IVoucherData>();
      int i = 0;
      for (SaleOrderBVO salebvo : salebvos) {
        if (!canArsubToVoucher(salebvo)) {
          i++;
          continue;
        }
        datas.add(getVoucherData(salebvo, combindata[i].getNnum()));
        i++;
      }
      if (datas.size() > 0) {
        toVoucher.onWastageUnSign(datas.toArray(new IVoucherData[0]));
      }
    }
    catch (Exception ex) {
      ExceptionUtils.marsh(ex);
    }
  }

  private static boolean canArsubToVoucher(SaleOrderBVO bvo) {
    if (null != bvo.getBlrgcashflag() && bvo.getBlrgcashflag().booleanValue()) {
      return true;
    }
    if (!MathTool.isZero(bvo.getNorigsubmny())) {
      return true;
    }
    return false;
  }

  private static ArsubToVoucherData[] getcombinData(ArsubToVoucherData[] datas) {
    Map<String, UFDouble> datamap = new HashMap<String, UFDouble>();
    // 汇总数量
    for (ArsubToVoucherData data : datas) {
      String salebid = data.getSaleorderbid();
      UFDouble nnum = MathTool.add(datamap.get(salebid), data.getNnum());
      datamap.put(salebid, nnum);
    }
    ArsubToVoucherData[] retdatas = new ArsubToVoucherData[datamap.size()];
    Set<Entry<String, UFDouble>> datasets = datamap.entrySet();
    int i = 0;
    for (Entry<String, UFDouble> entry : datasets) {
      retdatas[i] = new ArsubToVoucherData();
      retdatas[i].setSaleorderbid(entry.getKey());
      retdatas[i].setNnum(entry.getValue());
      i++;
    }
    return retdatas;
  }

  private static SaleOrderBVO[] getSaleorderBody(ArsubToVoucherData[] datas) {
    String[] bids = new String[datas.length];
    int i = 0;
    for (ArsubToVoucherData data : datas) {
      bids[i] = new String();
      bids[i] = data.getSaleorderbid();
      i++;
    }
    String[] names =
        {
          SaleOrderBVO.CSALEORDERID, SaleOrderBVO.CSALEORDERBID,
          SaleOrderBVO.CSETTLEORGID, SaleOrderBVO.CCURRENCYID,
          SaleOrderBVO.NNUM, SaleOrderBVO.NORIGSUBMNY, SaleOrderBVO.NACCPRICE,
          SaleOrderBVO.BLRGCASHFLAG, SaleOrderBVO.NORIGTAXMNY
        };
    VOQuery<SaleOrderBVO> voquery =
        new VOQuery<SaleOrderBVO>(SaleOrderBVO.class, names);
    return voquery.query(bids);
  }

  private static IVoucherData getVoucherData(SaleOrderBVO bvo, UFDouble signnum) {
    VoucherData data = new VoucherData();
    data.setSaleorderid(bvo.getCsaleorderid());
    data.setSaleorderbid(bvo.getCsaleorderbid());
    data.setCcurrencyid(bvo.getCcurrencyid());
    data.setFinanceorg(bvo.getCsettleorgid());
    data.setNnum(bvo.getNnum());
    data.setNeffectnum(signnum);
    if (null != bvo.getBlrgcashflag() && bvo.getBlrgcashflag().booleanValue()) {
      data.setNorigsubmny(bvo.getNorigtaxmny());
    }
    else {
      data.setNorigsubmny(bvo.getNorigsubmny());
    }
    data.setIslrgcash(Boolean.valueOf(bvo.getBlrgcashflag().booleanValue()));
    if (bvo.getBlrgcashflag().booleanValue()) {
      data.setNorigcaccountmny(MathTool.nvl(bvo.getNaccprice()).multiply(
          MathTool.nvl(bvo.getNnum())));
    }
    else {
      data.setNorigcaccountmny(bvo.getNorigsubmny());
    }

    return data;
  }

}
