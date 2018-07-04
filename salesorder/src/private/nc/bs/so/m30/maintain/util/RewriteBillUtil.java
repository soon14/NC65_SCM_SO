package nc.bs.so.m30.maintain.util;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;

import nc.bs.framework.common.NCLocator;
import nc.bs.so.m30.rule.rewrite.m4h.Rewrite4HPara;
import nc.impl.pubapp.bill.rewrite.BillRewriter;
import nc.impl.pubapp.bill.rewrite.ItemKeyMapping;
import nc.impl.pubapp.bill.rewrite.RewritePara;
import nc.impl.pubapp.pattern.data.vo.VOQuery;
import nc.impl.pubapp.pattern.data.vo.VOUpdate;
import nc.itf.scmpub.reference.uap.group.SysInitGroupQuery;
import nc.pubitf.ic.m4c.m30.IRewrite4CFor30;
import nc.pubitf.ic.m4c.m30.Parameter4CFor30;
import nc.pubitf.ic.m4h.m30.IBorrowOutRewriteFor30;
import nc.pubitf.opc.opcapi.so.IRewriteExecuteInfo;
import nc.pubitf.pu.m21.so.m30.IOrderUpdateCoopFor30;
import nc.pubitf.so.m30.so.withdraw.IRewriteSaleOrderByWithdraw;
import nc.pubitf.so.m30.so.withdraw.Rewrite30Para;
import nc.pubitf.so.m38.so.m30.IRewrite38For30;
import nc.pubitf.so.salequotation.so.ISaleOrderCallBack;
import nc.vo.ct.saledaily.entity.CtSaleBVO;
import nc.vo.ct.saledaily.entity.CtSaleVO;
import nc.vo.ic.m4c.entity.SaleOutBodyVO;
import nc.vo.ic.m4c.entity.SaleOutHeadVO;
import nc.vo.ic.m4h.entity.BorrowOutBodyVO;
import nc.vo.ic.m4h.entity.BorrowOutHeadVO;
import nc.vo.it.m5805.entity.DetailBVO;
import nc.vo.it.m5805.entity.DetailHVO;
import nc.vo.opc.param.so.RewriteCustomerPOPara;
import nc.vo.pub.BusinessException;
import nc.vo.pub.lang.UFBoolean;
import nc.vo.pub.lang.UFDouble;
import nc.vo.pubapp.pattern.exception.ExceptionUtils;
import nc.vo.pubapp.pattern.pub.MathTool;
import nc.vo.scmpub.res.billtype.CTBillType;
import nc.vo.scmpub.res.billtype.ICBillType;
import nc.vo.scmpub.res.billtype.SOBillType;
import nc.vo.so.m30.entity.SaleOrderBVO;
import nc.vo.so.m30.entity.SaleOrderHVO;
import nc.vo.so.m30.entity.SaleOrderVO;
import nc.vo.so.m32.entity.SaleInvoiceBVO;
import nc.vo.so.m38.entity.PreOrderBVO;
import nc.vo.so.m38.entity.PreOrderHVO;
import nc.vo.so.salequotation.entity.QuatationRewritePara;
import nc.vo.so.salequotation.entity.SalequotationBVO;
import nc.vo.so.salequotation.entity.SalequotationHVO;
import nc.vo.trade.checkrule.VOChecker;

public class RewriteBillUtil {

  /**
   * 
   * RewriteBillUtil 的构造子
   */
  public RewriteBillUtil() {
    // TODO
  }

  public List<RewritePara> filtrateSrc30(List<RewritePara> firstParaList,
      List<RewritePara> srcParaList) {
    // 如果来源为空，直接返回传入源头
    if (VOChecker.isEmpty(srcParaList)) {
      return firstParaList;
    }
    if (VOChecker.isEmpty(firstParaList)) {
      return null;
    }
    Set<String> hsBid = new HashSet<String>();
    for (RewritePara para : srcParaList) {
      hsBid.add(para.getCsrcbid());
    }

    List<RewritePara> filter = new ArrayList<RewritePara>();

    for (RewritePara para : firstParaList) {
      if (!hsBid.contains(para.getCsrcbid())) {
        filter.add(para);
      }
    }
    return filter;
  }

  public BillRewriter getFirstBillRewriter() {
    // 设置发票源头单据类型、ID、BID、数量字段
    ItemKeyMapping mapping = new ItemKeyMapping();
    mapping.setVsrctypeKey(SaleInvoiceBVO.VFIRSTTYPE);
    mapping.setCsrcidKey(SaleInvoiceBVO.CFIRSTID);
    mapping.setCsrcbidKey(SaleInvoiceBVO.CFIRSTBID);
    mapping.setNnumKey(SaleInvoiceBVO.NNUM);

    // 添加发票源头单据类型
    BillRewriter tool = new BillRewriter(mapping);

    // 销售订单
    tool.addSRCHeadClazz(SOBillType.Order.getCode(), SaleOrderHVO.class);
    tool.addSRCItemClazz(SOBillType.Order.getCode(), SaleOrderBVO.class);

    return tool;
  }

  public BillRewriter getSrcBillRewriter() {

    // 设置订单上游单据类型、ID、BID、数量字段
    ItemKeyMapping mapping = new ItemKeyMapping();
    mapping.setVsrctypeKey(SaleOrderBVO.VSRCTYPE);
    mapping.setCsrcidKey(SaleOrderBVO.CSRCID);
    mapping.setCsrcbidKey(SaleOrderBVO.CSRCBID);
    mapping.setNnumKey(SaleOrderBVO.NNUM);
    mapping.setSrcTSKey(SaleOrderBVO.SRCTS);
    // 添加订单上游单据类型
    BillRewriter tool = new BillRewriter(mapping);
    // 销售订单
    tool.addSRCHeadClazz(SOBillType.Order.getCode(), SaleOrderHVO.class);
    tool.addSRCItemClazz(SOBillType.Order.getCode(), SaleOrderBVO.class);

    // 销售出库单
    tool.addSRCHeadClazz(ICBillType.SaleOut.getCode(), SaleOutHeadVO.class);
    tool.addSRCItemClazz(ICBillType.SaleOut.getCode(), SaleOutBodyVO.class);

    // 预订单
    tool.addSRCHeadClazz(SOBillType.PreOrder.getCode(), PreOrderHVO.class);
    tool.addSRCItemClazz(SOBillType.PreOrder.getCode(), PreOrderBVO.class);

    // 报价单
    tool.addSRCHeadClazz(SOBillType.SaleQuotation.getCode(),
        SalequotationHVO.class);
    tool.addSRCItemClazz(SOBillType.SaleQuotation.getCode(),
        SalequotationBVO.class);

    // 库存借出单
    tool.addSRCHeadClazz(ICBillType.BorrowOut.getCode(), BorrowOutHeadVO.class);
    tool.addSRCItemClazz(ICBillType.BorrowOut.getCode(), BorrowOutBodyVO.class);
    // 销售合同
    tool.addSRCHeadClazz(CTBillType.SaleDaily.getCode(), CtSaleVO.class);
    tool.addSRCItemClazz(CTBillType.SaleDaily.getCode(), CtSaleBVO.class);

    // 电子订单
    if(SysInitGroupQuery.isOPCEnabled()){
    	SORewritePubBillTool.addOPCSrcBillRewriter(tool);
    }
    // 采购订单
    if(SysInitGroupQuery.isPOEnabled()){
      SORewritePubBillTool.addPOSrcBillRewriter(tool);
    }
    return tool;
  }

  public void reWriteFirst30(List<RewritePara> paraList) {

    int size = paraList.size();
    Rewrite30Para[] paras = new Rewrite30Para[size];
    for (int i = 0; i < size; i++) {
      String bid = paraList.get(i).getCsrcbid();
      UFDouble nnum = paraList.get(i).getNnum();
      paras[i] = new Rewrite30Para(bid, nnum);
    }
    IRewriteSaleOrderByWithdraw api =
        NCLocator.getInstance().lookup(IRewriteSaleOrderByWithdraw.class);
    try {
      api.rewrite30NumForWithdraw(paras);
    }
    catch (BusinessException ex) {

      ExceptionUtils.wrappException(ex);
    }

  }

  /**
   * 回写来源销售订单
   * 
   * @param paraList
   */
  public void reWriteSrc30(List<RewritePara> paraList) {

    int size = paraList.size();
    Rewrite30Para[] paras = new Rewrite30Para[size];
    for (int i = 0; i < size; i++) {
      String bid = paraList.get(i).getCsrcbid();
      UFDouble nnum = paraList.get(i).getNnum();
      paras[i] = new Rewrite30Para(bid, nnum);
    }

    IRewriteSaleOrderByWithdraw api =
        NCLocator.getInstance().lookup(IRewriteSaleOrderByWithdraw.class);
    try {
      api.rewrite30NumForWithdraw(paras);
    }
    catch (BusinessException ex) {

      ExceptionUtils.wrappException(ex);
    }
  }

  /**
   * 回写采购订单
   * 
   * @param vos
   */
  public void reWrite21(SaleOrderVO[] vos) {
    Map<String, String> map = new HashMap<String, String>();
    for (SaleOrderVO vo : vos) {
      SaleOrderHVO hvo = vo.getParentVO();
      UFBoolean iscoop = hvo.getBpocooptomeflag();
      if (null != iscoop && iscoop.booleanValue()) {
        String srcHid = vo.getChildrenVO()[0].getCsrcid();
        String billcode = hvo.getVbillcode();
        map.put(srcHid, billcode);
      }
    }
    if (map.size() > 0) {
      IOrderUpdateCoopFor30 service =
          NCLocator.getInstance().lookup(IOrderUpdateCoopFor30.class);
      try {
        service.updateCoopFlag(true, map);
      }
      catch (BusinessException e) {
        ExceptionUtils.wrappException(e);
      }
    }
  }

  /**
   * 回写预订单
   * 
   * @param paraList
   */
  public void reWriteSrc38(List<RewritePara> paraList) {
    int size = paraList.size();
    nc.pubitf.so.m38.so.m30.Rewrite30Para[] paras =
        new nc.pubitf.so.m38.so.m30.Rewrite30Para[size];
    for (int i = 0; i < size; i++) {
      String bid = paraList.get(i).getCsrcbid();
      UFDouble nnum = paraList.get(i).getNnum();
      paras[i] = new nc.pubitf.so.m38.so.m30.Rewrite30Para(bid, nnum);
    }
    IRewrite38For30 api = NCLocator.getInstance().lookup(IRewrite38For30.class);
    try {
      api.rewrite38NarrnumFor30(paras);
    }
    catch (BusinessException ex) {

      ExceptionUtils.wrappException(ex);
    }
  }

  public void reWriteSrcOPC(List<RewritePara> paraList) {
    int size = paraList.size();
    RewriteCustomerPOPara[] paras = new RewriteCustomerPOPara[size];
    for (int i = 0; i < size; i++) {
      String bid = paraList.get(i).getCsrcbid();
      UFDouble nnum = paraList.get(i).getNnum();
      paras[i] = new RewriteCustomerPOPara(bid, nnum);
    }
    try {
      IRewriteExecuteInfo rewriteinfo =
          NCLocator.getInstance().lookup(IRewriteExecuteInfo.class);
      rewriteinfo.rewriteTotalArrangeNum(paras);
    }
    catch (BusinessException ex) {

      ExceptionUtils.wrappException(ex);
    }
  }

  /**
   * 回写来源报价单
   * 
   * @param paraList
   */
  public void reWriteSrc4310(List<RewritePara> paraList, Integer status) {
    int size = paraList.size();
    QuatationRewritePara[] paras = new QuatationRewritePara[size];
    for (int i = 0; i < size; i++) {
      String id = paraList.get(i).getCsrcid();
      String bid = paraList.get(i).getCsrcbid();
      UFDouble nnum = paraList.get(i).getNnum();
      paras[i] = new QuatationRewritePara();
      paras[i].setPk_salequobill(id);
      paras[i].setPk_salequobill_b(bid);
      paras[i].setNnum(nnum);
      // paras[i].setOperateFlag(Integer.valueOf(paraList.get(i).getStatus()));
      paras[i].setOperateFlag(status);
    }
    ISaleOrderCallBack api =
        NCLocator.getInstance().lookup(ISaleOrderCallBack.class);
    try {
      api.saleOrderCallBack(paras);
    }
    catch (BusinessException ex) {

      ExceptionUtils.wrappException(ex);
    }
  }

  /**
   * 回写来源借出转销售
   * 
   * @param paraList
   */
  public void reWriteSrc4H(List<RewritePara> paraList) {
    int size = paraList.size();
    Rewrite4HPara[] paras = new Rewrite4HPara[size];
    for (int i = 0; i < size; i++) {
      String hid = paraList.get(i).getCsrcid();
      String bid = paraList.get(i).getCsrcbid();
      UFDouble nnum = paraList.get(i).getNnum();
      paras[i] = new Rewrite4HPara(hid, bid, nnum);
    }
    IBorrowOutRewriteFor30 api =
        NCLocator.getInstance().lookup(IBorrowOutRewriteFor30.class);
    try {
      api.rewriteTranoutNum(paras);
    }
    catch (BusinessException ex) {

      ExceptionUtils.wrappException(ex);
    }
  }

  /**
   * 回写来源销售出库单
   * 
   * @param paraList
   */
  public void reWriteSrc4C(List<RewritePara> paraList) {
    int size = paraList.size();
    Parameter4CFor30[] paras = new Parameter4CFor30[size];
    for (int i = 0; i < size; i++) {
      String hid = paraList.get(i).getCsrcid();
      String bid = paraList.get(i).getCsrcbid();
      UFDouble nnum = paraList.get(i).getNnum();
      paras[i] = new Parameter4CFor30(hid, bid);
      // 取反：出库单累计退货数量为正
      paras[i].setNreplenishednum(MathTool.sub(UFDouble.ZERO_DBL, nnum));
    }

    IRewrite4CFor30 api = NCLocator.getInstance().lookup(IRewrite4CFor30.class);
    try {
      api.rewriteNreplenishednum(paras);
    }
    catch (BusinessException ex) {

      ExceptionUtils.wrappException(ex);
    }
  }
  
	/**
	 * 回写进口明细工具 add by zhangjjs 2018-3-19
	 * @return
	 */
	public BillRewriter getSrc5805BillRewriter() {
		// 设置订单上游单据类型、ID、BID、数量字段
		ItemKeyMapping mapping = new ItemKeyMapping();
		// 来源单据类型
		mapping.setVsrctypeKey(SaleOrderBVO.VSRCTYPE);
		// 来源单据主表主键
		mapping.setCsrcidKey(SaleOrderBVO.CSRCID);
		// 来源单据表体主键
		mapping.setCsrcbidKey(SaleOrderBVO.CSRCBID);
		// 数量
		mapping.setNnum2Key(SaleOrderBVO.NASTNUM);
		
		BillRewriter tool = new BillRewriter(mapping);
		// 进口明细单
		tool.addSRCHeadClazz("5805", DetailHVO.class);
		tool.addSRCItemClazz("5805", DetailBVO.class);

		return tool;
	}

	/**
	 * 回写进口明细 add by zhangjjs 2018-3-19
	 * @param paraList
	 */
	public void reWriteSrc5805(List<RewritePara> paraList) {
		if (paraList.size() == 0) {
			return;
		}
		String[] names = new String[] { SaleOrderBVO.VBDEF14 };
		DetailBVO[] vos = new DetailBVO[paraList.size()];
		for (int i = 0; i < paraList.size(); i++) {
			//子表id
			String bpk = paraList.get(i).getCsrcbid();
			//变化量
			UFDouble num = paraList.get(i).getNnum2();
			//为校验数量，查询原有值
			VOQuery<DetailBVO> query = new VOQuery<DetailBVO>(DetailBVO.class);
			DetailBVO[] old = query.query(new String[] { bpk });
			UFDouble oldSum = new UFDouble(old[0].getVbdef14());
			UFDouble nastnum = old[0].getNastnum();
			UFDouble newSum = MathTool.add(oldSum, num);
			if (newSum.doubleValue() > nastnum.doubleValue()) {
				ExceptionUtils.wrappBusinessException("修改后的数量超出上游最大可用量");
			}
			DetailBVO bvo = new DetailBVO();
			bvo.setPrimaryKey(bpk);
			bvo.setVbdef14(newSum.toString());
			vos[i] = bvo;
		}
		VOUpdate<DetailBVO> bo = new VOUpdate<DetailBVO>();
		bo.update(vos, names);
	}

}
