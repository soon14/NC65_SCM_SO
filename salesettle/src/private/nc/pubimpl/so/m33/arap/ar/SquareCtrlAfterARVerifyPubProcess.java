package nc.pubimpl.so.m33.arap.ar;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;

import nc.bs.so.m33.maintain.m32.query.QuerySquare32VOBP;
import nc.bs.so.m33.maintain.m4c.query.QuerySquare4CVOBP;
import nc.impl.pubapp.pattern.data.view.ViewUpdate;
import nc.itf.arap.forso.IDataFromVerifyForM33;
import nc.itf.so.m33.ref.so.m32.SOSaleInvoiceServicesUtil;
import nc.pubitf.so.m32.so.m33.RewritePara32For33OnVerify;
import nc.pubitf.so.m33.arap.SqCtrlARVerifyVO;
import nc.vo.pub.lang.UFDouble;
import nc.vo.pubapp.pattern.pub.MathTool;
import nc.vo.scmpub.res.billtype.ICBillType;
import nc.vo.scmpub.res.billtype.SOBillType;
import nc.vo.so.m33.m32.entity.SquareInvDetailVO;
import nc.vo.so.m33.m32.entity.SquareInvViewVO;
import nc.vo.so.m33.m4c.entity.SquareOutBVO;
import nc.vo.so.m33.m4c.entity.SquareOutDetailVO;
import nc.vo.so.m33.m4c.entity.SquareOutViewVO;
import nc.vo.so.pub.util.SOVOChecker;
import nc.vo.so.pub.votools.SoVoTools;

public class SquareCtrlAfterARVerifyPubProcess {

  public void doAction(IDataFromVerifyForM33[] datas) {
    // 根据应收单来源进行分组：销售发票结算单、销售出库单结算单、途损结算单
    Map<String, SqCtrlARVerifyVO[]> map = this.processGroup(datas);

    // 处理发票
    this.processInvoice(map.get(SOBillType.SquareInvoice.getCode()));

    // 处理销售出库
    this.processOut(map.get(SOBillType.SquareOut.getCode()));
  }

  /**
   * 根据应收单来源进行分组：销售发票结算单、销售出库单结算单
   * 
   * @param datas
   * @return <待结算单单据类型,SqCtrlARVerifyVO>
   */
  private Map<String, SqCtrlARVerifyVO[]> processGroup(
      IDataFromVerifyForM33[] datas) {
    Map<String, SqCtrlARVerifyVO[]> map =
        new HashMap<String, SqCtrlARVerifyVO[]>();

    // <销售结算单id,财务核销金额>
    Map<String, UFDouble> minvoice = new HashMap<String, UFDouble>();
    Map<String, UFDouble> mout = new HashMap<String, UFDouble>();
    this.processGroup(minvoice, mout, datas);

    // 处理发票待结算单
    int size = minvoice.size();
    List<SqCtrlARVerifyVO[]> lsq = new ArrayList<SqCtrlARVerifyVO[]>();
    if (size > 0) {
      lsq = this.processInvoice(minvoice);
      map.put(SOBillType.SquareInvoice.getCode(), lsq.get(0));
    }

    // 处理出库待结算单
    size = mout.size();
    if (size > 0
        || (lsq.size() > 1 && lsq.get(1) != null && lsq.get(1).length > 0)) {
      SqCtrlARVerifyVO[] outVOs = null;
      if (lsq.size() > 1) {
        outVOs = lsq.get(1);
      }
      SqCtrlARVerifyVO[] vos = this.processOut(outVOs, mout);
      map.put(SOBillType.SquareOut.getCode(), vos);
    }

    return map;
  }

  private void processGroup(Map<String, UFDouble> minvoice,
      Map<String, UFDouble> mout, IDataFromVerifyForM33[] datas) {
    for (IDataFromVerifyForM33 idata : datas) {
      String billType = idata.getSrcBillType();
      String srcbid = idata.getSrcBID();
      UFDouble newvalue = idata.getPayBillmny();
      if (SOBillType.Invoice.getCode().equals(billType)) {
        UFDouble oldvalue = minvoice.get(srcbid);
        if (SOVOChecker.isEmpty(oldvalue)) {
          minvoice.put(srcbid, newvalue);
        }
        minvoice.put(srcbid, MathTool.add(oldvalue, newvalue));
      }
      if (ICBillType.SaleOut.getCode().equals(billType)) {
        UFDouble oldvalue = mout.get(srcbid);
        if (SOVOChecker.isEmpty(oldvalue)) {
          mout.put(srcbid, newvalue);
        }
        mout.put(srcbid, MathTool.add(oldvalue, newvalue));
      }
    }
  }

  /**
   * 
   * @param minvoice
   * @return List[0] -- 销售发票参数vo List[1] -- 销售发票上游出库单参数vo
   */
  private List<SqCtrlARVerifyVO[]> processInvoice(Map<String, UFDouble> minvoice) {
    // 查询销售发票结算单
    int size = minvoice.size();
    String[] dids = minvoice.keySet().toArray(new String[size]);
    QuerySquare32VOBP invqry = new QuerySquare32VOBP();
    SquareInvDetailVO[] invdvos = invqry.querySquareInvDetailVOByPK(dids);
    SqCtrlARVerifyVO[] invvos = new SqCtrlARVerifyVO[invdvos.length];
    // <销售发票待结算单bid,核销金额>
    Map<String, UFDouble> minvbidmny = new HashMap<String, UFDouble>();
    for (int i = 0; i < invdvos.length; i++) {
      SqCtrlARVerifyVO vo = new SqCtrlARVerifyVO();
      vo.setCsalesquarebid(invdvos[i].getCsalesquarebid());
      vo.setCsquarebillbid(invdvos[i].getCsquarebillbid());
      UFDouble paymny = minvoice.get(invdvos[i].getCsalesquaredid());
      vo.setNpayBillmny(paymny);
      invvos[i] = vo;
      minvbidmny.put(invdvos[i].getCsalesquarebid(), paymny);
    }
    List<SqCtrlARVerifyVO[]> list = new ArrayList<SqCtrlARVerifyVO[]>();
    list.add(invvos);

    // 查询销售发票结算单上游销售出库待结算单信息
    String[] sqinvbids =
        SoVoTools.getVOsOnlyValues(invdvos, SquareInvDetailVO.CSALESQUAREBID);
    SquareInvViewVO[] invviews = invqry.querySquareInvViewVOByBID(sqinvbids);
    // <销售出库单bid,核销金额>
    Map<String, UFDouble> moutbidmny = new HashMap<String, UFDouble>();
    for (SquareInvViewVO view : invviews) {
      // 销售发票来源于销售出库单
      if (ICBillType.SaleOut.getCode().equals(view.getItem().getVsrctype())) {
        String srcbid = view.getItem().getCsrcbid();
        UFDouble newvalue = minvbidmny.get(view.getItem().getCsalesquarebid());
        UFDouble oldvalue = moutbidmny.get(srcbid);
        if (SOVOChecker.isEmpty(oldvalue)) {
          moutbidmny.put(srcbid, newvalue);
        }
        moutbidmny.put(srcbid, MathTool.add(oldvalue, newvalue));
      }
    }
    int soutize = moutbidmny.size();
    if (soutize > 0) {
      SqCtrlARVerifyVO[] outvos = new SqCtrlARVerifyVO[soutize];
      int i = 0;
      for (Entry<String, UFDouble> entry : moutbidmny.entrySet()) {
        outvos[i] = new SqCtrlARVerifyVO();
        outvos[i].setCsquarebillbid(entry.getKey());
        outvos[i].setNpayBillmny(entry.getValue());
        i++;
      }
      list.add(outvos);
    }

    return list;
  }

  /**
   * 回写发票
   * 
   * @param invoiceVO
   */
  private void processInvoice(SqCtrlARVerifyVO[] invoiceVOs) {
    if (invoiceVOs == null) {
      return;
    }
    int len = invoiceVOs.length;
    RewritePara32For33OnVerify[] paras = new RewritePara32For33OnVerify[len];
    for (int i = 0; i < len; i++) {
      paras[i] =
          new RewritePara32For33OnVerify(invoiceVOs[i].getCsquarebillbid(),
              invoiceVOs[i].getNpayBillmny());
    }
    SOSaleInvoiceServicesUtil.reWritePaymnyOnVerfy(paras);
  }

  /**
   * 回写销售出库待结算单
   * 
   * @param outVO
   */
  private void processOut(SqCtrlARVerifyVO[] outVOs) {
    if (outVOs == null) {
      return;
    }
    Map<String, SqCtrlARVerifyVO> map = new HashMap<String, SqCtrlARVerifyVO>();
    for (SqCtrlARVerifyVO vo : outVOs) {
      map.put(vo.getCsquarebillbid(), vo);
    }
    String[] outbids = map.keySet().toArray(new String[outVOs.length]);
    // 销售出库待结算单
    SquareOutViewVO[] views =
        new QuerySquare4CVOBP().querySquareOutViewVOBy4CBID(outbids);
    for (SquareOutViewVO view : views) {
      SquareOutBVO bvo = view.getItem();
      String outbid = bvo.getCsquarebillbid();
      SqCtrlARVerifyVO cvo = map.get(outbid);
      bvo.setNtotalpaymny(MathTool.add(bvo.getNtotalpaymny(),
          cvo.getNpayBillmny()));
    }
    ViewUpdate<SquareOutViewVO> viewupdate = new ViewUpdate<SquareOutViewVO>();
    viewupdate.update(views, SquareOutBVO.class, new String[] {
      SquareOutBVO.NTOTALPAYMNY
    });
  }

  /**
   * 
   * @param outVOs -- 销售发票上游对应的销售出库单
   *          mout ---- <销售出库结算单id,核销金额>
   * @return 出库单参数vo
   */
  private SqCtrlARVerifyVO[] processOut(SqCtrlARVerifyVO[] outVOs,
      Map<String, UFDouble> mout) {
    // <销售出库单行id,SqCtrlARVerifyVO>
    Map<String, SqCtrlARVerifyVO> mapinvupout =
        new HashMap<String, SqCtrlARVerifyVO>();
    if (!SOVOChecker.isEmpty(outVOs)) {
      for (SqCtrlARVerifyVO vo : outVOs) {
        mapinvupout.put(vo.getCsquarebillbid(), vo);
      }
    }

    // <销售出库单行id,SqCtrlARVerifyVO>
    Map<String, SqCtrlARVerifyVO> mretout =
        new HashMap<String, SqCtrlARVerifyVO>();

    // 销售出库单直接传应收
    int size = mout.size();
    if (size > 0) {
      String[] dids = mout.keySet().toArray(new String[size]);
      QuerySquare4CVOBP outqry = new QuerySquare4CVOBP();
      SquareOutDetailVO[] outdvos = outqry.querySquareOutDetailVOByPK(dids);
      for (int i = 0; i < outdvos.length; i++) {
        String outbid = outdvos[i].getCsquarebillbid();
        UFDouble newpaymny = mout.get(outdvos[i].getCsalesquaredid());
        SqCtrlARVerifyVO vo = mretout.get(outbid);
        if (vo == null) {
          vo = new SqCtrlARVerifyVO();
          vo.setCsquarebillbid(outbid);
          vo.setNpayBillmny(newpaymny);
          mretout.put(outbid, vo);
        }
        else {
          UFDouble oldpaymny = vo.getNpayBillmny();
          vo.setNpayBillmny(MathTool.add(oldpaymny, newpaymny));
        }
      }
    }
    // 累加上销售发票上游的出库单
    int mapsize = mapinvupout.size();
    if (mapsize > 0) {
      for (Entry<String, SqCtrlARVerifyVO> entry : mapinvupout.entrySet()) {
        mretout.put(entry.getKey(), entry.getValue());
      }
    }

    int len = mretout.size();
    return mretout.values().toArray(new SqCtrlARVerifyVO[len]);
  }

}
