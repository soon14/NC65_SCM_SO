package nc.pubimpl.so.m30.balend;

import java.util.HashMap;
import java.util.Map;

import nc.bs.framework.common.NCLocator;
import nc.impl.pubapp.pattern.data.vo.VOQuery;
import nc.pubitf.ic.m4453.m30.IWastageServiceFor30;
import nc.pubitf.ic.m4c.I4CQueryPubService;
import nc.pubitf.so.m32.so.m30.IQuery32For30;
import nc.pubitf.so.m33.so.m30.ISquare32QryFor30SqEnd;
import nc.pubitf.so.m33.so.m30.ISquare4CQryFor30SqEnd;
import nc.pubitf.so.m33.so.m30.RetVOFor30;
import nc.vo.pub.BusinessException;
import nc.vo.pub.lang.UFBoolean;
import nc.vo.pubapp.pattern.data.ValueUtils;
import nc.vo.pubapp.pattern.exception.ExceptionUtils;
import nc.vo.scmpub.res.billtype.ICBillType;
import nc.vo.scmpub.res.billtype.SOBillType;
import nc.vo.so.m30.balend.entity.BalCheckPara;
import nc.vo.so.m30.balend.entity.InvoiceBalVO;
import nc.vo.so.m30.balend.entity.SaleOutBalVO;
import nc.vo.so.m30.balend.enumeration.VirtualBalType;
import nc.vo.so.m30.entity.SaleOrderBVO;
import nc.vo.so.m33.m32.entity.SquareInvBVO;
import nc.vo.so.m33.m32.entity.SquareInvHVO;
import nc.vo.so.m33.m32.entity.SquareInvViewVO;
import nc.vo.so.m33.m4c.entity.SquareOutBVO;
import nc.vo.so.m33.m4c.entity.SquareOutHVO;
import nc.vo.so.m33.m4c.entity.SquareOutViewVO;
import nc.vo.trade.checkrule.VOChecker;

public class BalEndDataAccess {

  /**
   * 
   * 方法功能描述：查询销售订单行下游billtype类型单据是否已全部审核。
   * <p>
   * <b>参数说明</b>
   * 
   * @param billtype
   * @param orderbids
   * @return
   *         <p>
   * @author fengjb
   * @time 2010-9-8 下午01:41:54
   */
  public UFBoolean[] queryBalBillApprove(String billtype, String[] orderids,
      String[] orderbids) {
    UFBoolean[] isapprove = null;
    try {
      if (SOBillType.Invoice.getCode().equals(billtype)) {
        // 调用销售发票接口
        IQuery32For30 querysrv =
            NCLocator.getInstance().lookup(IQuery32For30.class);
        isapprove = querysrv.isInvoiceAllApprove(orderids, orderbids);
      }
      else if (ICBillType.SaleOut.getCode().equals(billtype)) {
        // 调用销售出库单接口
        I4CQueryPubService querysrv =
            NCLocator.getInstance().lookup(I4CQueryPubService.class);
        isapprove = querysrv.queryIsAllSigned(orderbids);
      }
      else if (ICBillType.WastageBill.getCode().equals(billtype)) {
        // 调用途损单接口
        IWastageServiceFor30 querysrv =
            NCLocator.getInstance().lookup(IWastageServiceFor30.class);
        isapprove = querysrv.queryWastageSigned(orderbids);
      }
    }
    catch (BusinessException e) {
      ExceptionUtils.wrappException(e);
    }
    return isapprove;
  }

  /**
   * 方法功能描述：返回订单行应收结算关闭和成本结算关闭标志位。
   * <p>
   * <b>参数说明</b>
   * 
   * @param orderbids
   * @return <p>
   * @author fengjb
   * @time 2010-7-14 下午04:10:21
   */
  public Map<String, UFBoolean[]> queryBalEndFlag(String[] orderbids) {
    String[] names =
        new String[] {
          SaleOrderBVO.CSALEORDERBID, SaleOrderBVO.BBARSETTLEFLAG,
          SaleOrderBVO.BBCOSTSETTLEFLAG
        };
    VOQuery<SaleOrderBVO> querysrv =
        new VOQuery<SaleOrderBVO>(SaleOrderBVO.class, names);
    SaleOrderBVO[] bvos = querysrv.query(orderbids);
    Map<String, UFBoolean[]> hmEndflag = new HashMap<String, UFBoolean[]>();
    for (SaleOrderBVO bvo : bvos) {
      hmEndflag.put(bvo.getCsaleorderbid(), new UFBoolean[] {
        bvo.getBbarsettleflag(), bvo.getBbcostsettleflag()
      });
    }
    return hmEndflag;
  }

  /**
   * 
   * 方法功能描述：查询销售订单下游billtype类型单据是否已结算关闭。
   * <p>
   * <b>参数说明</b>
   * 
   * @param billtype
   * @param orderbids
   * @return
   *         <p>
   * @author fengjb
   * @time 2010-9-8 下午01:56:25
   */
  public Map<String, UFBoolean[]> queryBillBalClose(String billtype,
      String[] orderbids) {
    Map<String, UFBoolean[]> mapBalClose = new HashMap<String, UFBoolean[]>();
    RetVOFor30[] retvos = null;
    if (SOBillType.Invoice.getCode().equals(billtype)) {
      ISquare32QryFor30SqEnd querysrv =
          NCLocator.getInstance().lookup(ISquare32QryFor30SqEnd.class);
      retvos = querysrv.querySqEndByOrdBID(orderbids);
    }
    else {
      ISquare4CQryFor30SqEnd querysrv =
          NCLocator.getInstance().lookup(ISquare4CQryFor30SqEnd.class);
      retvos = querysrv.querySqEndByOrdBID(orderbids);
    }
    if (null == retvos || retvos.length == 0) {
      return mapBalClose;
    }
    for (RetVOFor30 retvo : retvos) {
      UFBoolean[] balcloseflag = new UFBoolean[] {
        retvo.getBsquarearfinish(), retvo.getBsquareiafinish()
      };
      mapBalClose.put(retvo.getBid(), balcloseflag);
    }
    return mapBalClose;
  }

  /**
   * 方法功能描述：返回订单行应收/成本结算关闭标志位。
   * <p>
   * <b>参数说明</b>
   * 
   * @param orderbids
   * @param flagkey
   * @return <p>
   * @author fengjb
   * @time 2010-7-14 上午10:10:34
   */
  public Map<String, UFBoolean> queryOrderEndFlag(String[] orderbids,
      String flagkey) {
    String[] names = new String[] {
      SaleOrderBVO.CSALEORDERBID, flagkey
    };
    VOQuery<SaleOrderBVO> querysrv =
        new VOQuery<SaleOrderBVO>(SaleOrderBVO.class, names);
    SaleOrderBVO[] bvos = querysrv.query(orderbids);
    int ilength = bvos.length;
    Map<String, UFBoolean> map = new HashMap<String, UFBoolean>();
    for (int i = 0; i < ilength; i++) {
      Object value = bvos[i].getAttributeValue(flagkey);
      UFBoolean flag = ValueUtils.getUFBoolean(value);
      map.put(bvos[i].getCsaleorderbid(), flag);
    }
    return map;
  }

  /**
   * 方法功能描述：查询订单下游发票/出库单明细结算信息。
   * <p>
   * <b>参数说明</b>
   * 
   * @param orderbids
   * @return <p>
   * @author fengjb
   * @time 2010-7-21 下午03:50:32
   */
  public Map<String, BalCheckPara> querySquare(String[] orderbids) {

    // 查询发票结算信息
    InvoiceBalVO[] voicebalvos = this.getVoiceBalVOs(orderbids);
    // 查询出库单结算信息
    SaleOutBalVO[] outbalvos = this.getOutBalVOs(orderbids);

    return this.processBalInfo(voicebalvos, outbalvos);
  }

  public VirtualBalType[] queryVirtualBalType(String[] orderbids) {

    VirtualBalType[] virtualtype = new VirtualBalType[orderbids.length];

    ISquare4CQryFor30SqEnd querysrv =
        NCLocator.getInstance().lookup(ISquare4CQryFor30SqEnd.class);

    Map<String, VirtualBalType> mapType =
        querysrv.query4CVirtualBalType(orderbids);

    int iloop = orderbids.length;
    for (int i = 0; i < iloop; i++) {
      virtualtype[i] = mapType.get(orderbids[i]);
    }
    return virtualtype;
  }

  /**
   * 
   * 方法功能描述：查询销售订单行下游出库结算明细。
   * <p>
   * <b>参数说明</b>
   * 
   * @param orderbids
   * @return
   *         <p>
   * @author fengjb
   * @time 2010-9-8 下午01:39:36
   */
  private SaleOutBalVO[] getOutBalVOs(String[] orderbids) {
    ISquare4CQryFor30SqEnd outquerysrv =
        NCLocator.getInstance().lookup(ISquare4CQryFor30SqEnd.class);
    SquareOutViewVO[] outsqviews = outquerysrv.queryViewVOByOrdBID(orderbids);
    if (VOChecker.isEmpty(outsqviews)) {
      return new SaleOutBalVO[0];
    }

    int ilength = outsqviews.length;
    SaleOutBalVO[] balvos = new SaleOutBalVO[ilength];
    for (int i = 0; i < ilength; i++) {
      // 结算单据类型
      SquareOutHVO head = outsqviews[i].getHead();
      SquareOutBVO body = outsqviews[i].getItem();

      balvos[i] = new SaleOutBalVO();
      balvos[i].setOrderbid(body.getCfirstbid());
      balvos[i].setOrderid(body.getCfirstid());
      balvos[i].setBautoincome(head.getBautosquareincome());
      balvos[i].setBautocost(head.getBautosquarecost());
      balvos[i].setPreartype(body.getFpreartype());
      balvos[i].setPreiatype(body.getFpreiatype());
    }
    return balvos;
  }

  /**
   * 
   * 方法功能描述：查询销售订单行下游发票结算明细。
   * <p>
   * <b>参数说明</b>
   * 
   * @param orderbids
   * @return
   *         <p>
   * @author fengjb
   * @time 2010-9-8 下午01:39:06
   */
  private InvoiceBalVO[] getVoiceBalVOs(String[] orderbids) {

    ISquare32QryFor30SqEnd voicequerysrv =
        NCLocator.getInstance().lookup(ISquare32QryFor30SqEnd.class);
    SquareInvViewVO[] voicesqviews =
        voicequerysrv.queryViewVOByOrdBID(orderbids);
    if (VOChecker.isEmpty(voicesqviews)) {
      return new InvoiceBalVO[0];
    }

    int ilength = voicesqviews.length;
    InvoiceBalVO[] balvos = new InvoiceBalVO[ilength];
    for (int i = 0; i < ilength; i++) {
      // 结算单据类型
      SquareInvHVO head = voicesqviews[i].getHead();
      SquareInvBVO body = voicesqviews[i].getItem();

      balvos[i] = new InvoiceBalVO();
      balvos[i].setOrderbid(body.getCfirstbid());
      balvos[i].setOrderid(body.getCfirstid());
      balvos[i].setBautoincome(head.getBautosquareincome());
      balvos[i].setBautocost(head.getBautosquarecost());
      balvos[i].setPreartype(body.getFpreartype());
      balvos[i].setPreiatype(body.getFpreiatype());
    }
    return balvos;
  }

  /**
   * 
   * 方法功能描述：组织查询到的发票和出库单结算信息，把对应同一订单行的下游结算信息组合在一起。
   * <p>
   * <b>参数说明</b>
   * 
   * @param voicebalvos
   * @param outbalvos
   * @return
   *         <p>
   * @author fengjb
   * @time 2010-9-8 下午01:39:59
   */
  private Map<String, BalCheckPara> processBalInfo(InvoiceBalVO[] voicebalvos,
      SaleOutBalVO[] outbalvos) {
    Map<String, BalCheckPara> mapPara = new HashMap<String, BalCheckPara>();
    for (InvoiceBalVO voicebal : voicebalvos) {
      String orderbid = voicebal.getOrderbid();
      BalCheckPara para = new BalCheckPara(orderbid);
      para.setInvoicebal(voicebal);
      para.setOrderid(voicebal.getOrderid());
      mapPara.put(orderbid, para);
    }

    for (SaleOutBalVO outbal : outbalvos) {
      String orderbid = outbal.getOrderbid();
      if (mapPara.containsKey(orderbid)) {
        mapPara.get(orderbid).setOutbal(outbal);
      }
      else {
        BalCheckPara para = new BalCheckPara(orderbid);
        para.setOutbal(outbal);
        para.setOrderid(outbal.getOrderid());
        mapPara.put(orderbid, para);
      }
    }
    return mapPara;
  }
}
