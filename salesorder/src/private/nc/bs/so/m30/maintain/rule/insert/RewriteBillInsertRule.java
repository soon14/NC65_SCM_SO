package nc.bs.so.m30.maintain.rule.insert;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import nc.bs.framework.common.NCLocator;
import nc.bs.so.m30.maintain.util.RewriteBillUtil;
import nc.impl.pubapp.bill.rewrite.BillRewriter;
import nc.impl.pubapp.bill.rewrite.ItemKeyMapping;
import nc.impl.pubapp.bill.rewrite.RewritePara;
import nc.impl.pubapp.pattern.rule.IRule;
import nc.pubitf.ct.saledaily.so.IReWriteZ3For30;
import nc.vo.ct.entity.CtWriteBackForOrderVO;
import nc.vo.ct.saledaily.entity.CtSaleBVO;
import nc.vo.ct.saledaily.entity.CtSaleVO;
import nc.vo.pub.BusinessException;
import nc.vo.pub.VOStatus;
import nc.vo.pub.lang.UFDouble;
import nc.vo.pubapp.pattern.exception.ExceptionUtils;
import nc.vo.scmpub.res.billtype.CTBillType;
import nc.vo.scmpub.res.billtype.ICBillType;
import nc.vo.scmpub.res.billtype.OPCBillType;
import nc.vo.scmpub.res.billtype.POBillType;
import nc.vo.scmpub.res.billtype.SOBillType;
import nc.vo.so.m30.entity.SaleOrderBVO;
import nc.vo.so.m30.entity.SaleOrderVO;
import nc.vo.trade.checkrule.VOChecker;

/**
 * @description
 * 回写来源和源头（回写报价单、合同、预订单、电子销售、销售订单、出库单、库存借出）
 * @scene
 * 销售订单新增保存后
 * @param 
 * 无
 * 
 * @since 6.0
 * @version 2012-4-21 上午11:05:55
 * @author 么贵敬
 */
public class RewriteBillInsertRule implements IRule<SaleOrderVO> {

  @Override
  public void process(SaleOrderVO[] vos) {
    /****** 回写来源单据 ***********/
    RewriteBillUtil rewriteUtil = new RewriteBillUtil();
    BillRewriter srctool = rewriteUtil.getSrcBillRewriter();
    Map<String, List<RewritePara>> srcParaIndex = srctool.splitForInsert(vos);

    // 回写订单
    List<RewritePara> srcSaleOrder =
        srcParaIndex.get(SOBillType.Order.getCode());
    if (!VOChecker.isEmpty(srcSaleOrder)) {
      rewriteUtil.reWriteSrc30(srcSaleOrder);
    }
    // 出库单
    List<RewritePara> srcpara = srcParaIndex.get(ICBillType.SaleOut.getCode());
    if (!VOChecker.isEmpty(srcpara)) {
      rewriteUtil.reWriteSrc4C(srcpara);
    }
    // 预订单
    srcpara = srcParaIndex.get(SOBillType.PreOrder.getCode());
    if (!VOChecker.isEmpty(srcpara)) {
      rewriteUtil.reWriteSrc38(srcpara);
    }
    // 报价单
    srcpara = srcParaIndex.get(SOBillType.SaleQuotation.getCode());
    if (!VOChecker.isEmpty(srcpara)) {
      //
      rewriteUtil.reWriteSrc4310(srcpara, Integer.valueOf(VOStatus.NEW));
    }
    // 电子订单
    srcpara = srcParaIndex.get(OPCBillType.OPCORDER.getCode());
    if (!VOChecker.isEmpty(srcpara)) {
      rewriteUtil.reWriteSrcOPC(srcpara);
    }
    // 库存借出单
    srcpara = srcParaIndex.get(ICBillType.BorrowOut.getCode());
    if (!VOChecker.isEmpty(srcpara)) {
      rewriteUtil.reWriteSrc4H(srcpara);
    }
    // 回写采购订单
    rewriteUtil.reWrite21(vos);
    // 销售合同
    this.rewriteZ3(vos);
    /****** 回写来源单据 ***********/

    /** ------------回写源头单据---------- */
    BillRewriter firsttool = rewriteUtil.getFirstBillRewriter();
    Map<String, List<RewritePara>> firstParaIndex =
        firsttool.splitForInsert(vos);
    List<RewritePara> firstSaleOrder =
        firstParaIndex.get(SOBillType.Order.getCode());
    // 过滤参数
    firstSaleOrder = rewriteUtil.filtrateSrc30(firstSaleOrder, srcSaleOrder);

    // 如果来源是采购订单，源头是销售订单（销售订单-采购订单-协同生成销售订单）则不回写累计退货数量
    srcpara = srcParaIndex.get(POBillType.Order.getCode());
    if (!VOChecker.isEmpty(firstSaleOrder) && VOChecker.isEmpty(srcpara)) {
      rewriteUtil.reWriteFirst30(firstSaleOrder);
    }
    /** ------------回写源头单据---------- */
  }

  /**
   * 回写合同
   * 当VCTTYPE、Vsrctype 是合同的时候 把合同当来源来会写
   * 当VCTTYPE记录的是合同、Vsrctype 记录的不是合同 把合同当源头来会写，源头是回写在这里不用做并发控制
   * 
   * @param vos
   */
  private void rewriteZ3(SaleOrderVO[] vos) {
    SaleOrderVO[] newVOs = this.fillCtType(vos);
    if (newVOs.length == 0) {
      return;
    }

    SaleOrderVO[] firstVOs = this.fillFirstCtType(vos);

    // 设置上游单据类型、ID、BID、数量字段
    ItemKeyMapping mapping = new ItemKeyMapping();
    mapping.setVsrctypeKey(SaleOrderBVO.VCTTYPE);
    mapping.setCsrcidKey(SaleOrderBVO.CCTMANAGEID);
    mapping.setCsrcbidKey(SaleOrderBVO.CCTMANAGEBID);
    mapping.setNnumKey(SaleOrderBVO.NNUM);
    mapping.setNnum2Key(SaleOrderBVO.NORIGTAXMNY);
    // 销售订单没有子子表,用来放下合同需要的行号
    mapping.setCsrcbbidKey(SaleOrderBVO.CROWNO);
    if (firstVOs.length == 0) {
      mapping.setSrcTSKey(SaleOrderBVO.SRCTS);
    }

    // 添加上游单据类型
    BillRewriter tool = new BillRewriter(mapping);
    // 设置上游单据VO类型
    tool.addSRCHeadClazz(CTBillType.SaleDaily.getCode(), CtSaleVO.class);
    tool.addSRCItemClazz(CTBillType.SaleDaily.getCode(), CtSaleBVO.class);

    Map<String, UFDouble> bodymap = new HashMap<String, UFDouble>();
    // 取新的主原币含税单价
    this.buildSaleOrderBVOData(bodymap, newVOs);
    // 分单
    Map<String, List<RewritePara>> paraIndex = tool.splitForInsert(newVOs);

    // 回写
    List<RewritePara> paraList = paraIndex.get(CTBillType.SaleDaily.getCode());
    if (null != paraList && paraList.size() > 0) {
      int size = paraList.size();
      CtWriteBackForOrderVO[] paras = new CtWriteBackForOrderVO[size];
      for (int i = 0; i < size; i++) {
        String id = paraList.get(i).getCsrcid();
        String bid = paraList.get(i).getCsrcbid();
        UFDouble nnum = paraList.get(i).getNnum();
        UFDouble norigtaxmny = paraList.get(i).getNnum2();
        String crowno = paraList.get(i).getCsrcbbid();
        paras[i] = new CtWriteBackForOrderVO();
        paras[i].setPk_ctpu(id);
        paras[i].setPk_ctpu_b(bid);
        paras[i].setNum(nnum);
        paras[i].setPriceNum(norigtaxmny);
        paras[i].setcRowNum(crowno);
        if (bodymap.containsKey(paraList.get(i).getCbill_bid())) {
          // 主单位原币含税单价
          UFDouble price = bodymap.get(paraList.get(i).getCbill_bid());
          paras[i].setPrice(price);
        }
      }
      IReWriteZ3For30 api =
          NCLocator.getInstance().lookup(IReWriteZ3For30.class);
      try {
        api.rewriteBackZ3For30(paras);
      }
      catch (BusinessException ex) {
        ExceptionUtils.wrappException(ex);
      }
    }
  }

  private SaleOrderVO[] fillFirstCtType(SaleOrderVO[] vos) {
    List<SaleOrderVO> z3vos = new ArrayList<SaleOrderVO>();
    for (SaleOrderVO vo : vos) {
      SaleOrderBVO[] bodys = vo.getChildrenVO();
      List<SaleOrderBVO> z3bvos = new ArrayList<SaleOrderBVO>();
      for (SaleOrderBVO body : bodys) {
        if (!CTBillType.SaleDaily.getCode().equals(body.getVsrctype())) {
          body.setVcttype(CTBillType.SaleDaily.getCode());
          z3bvos.add(body);
        }
      }
      if (z3bvos.size() > 0) {
        SaleOrderVO newvo = new SaleOrderVO();
        newvo.setParentVO(vo.getParentVO());
        newvo.setChildrenVO(z3bvos.toArray(new SaleOrderBVO[z3bvos.size()]));
        z3vos.add(newvo);
      }
    }
    return z3vos.toArray(new SaleOrderVO[z3vos.size()]);
  }

  private SaleOrderVO[] fillCtType(SaleOrderVO[] vos) {
    List<SaleOrderVO> z3vos = new ArrayList<SaleOrderVO>();
    for (SaleOrderVO vo : vos) {
      SaleOrderBVO[] bodys = vo.getChildrenVO();
      List<SaleOrderBVO> z3bvos = new ArrayList<SaleOrderBVO>();
      for (SaleOrderBVO body : bodys) {
        if (body.getCctmanagebid() != null
            && body.getCctmanagebid().trim().length() > 0) {
          body.setVcttype(CTBillType.SaleDaily.getCode());
          z3bvos.add(body);
        }
      }
      if (z3bvos.size() > 0) {
        SaleOrderVO newvo = new SaleOrderVO();
        newvo.setParentVO(vo.getParentVO());
        newvo.setChildrenVO(z3bvos.toArray(new SaleOrderBVO[z3bvos.size()]));
        z3vos.add(newvo);
      }
    }
    return z3vos.toArray(new SaleOrderVO[z3vos.size()]);
  }

  private void buildSaleOrderBVOData(Map<String, UFDouble> bodymap,
      SaleOrderVO[] billvos) {
    for (SaleOrderVO billvo : billvos) {
      SaleOrderBVO[] childvos = billvo.getChildrenVO();
      for (SaleOrderBVO bodyvo : childvos) {
        if (!bodymap.containsKey(bodyvo.getPrimaryKey())) {
          bodymap.put(bodyvo.getPrimaryKey(), bodyvo.getNorigtaxprice());
        }
      }
    }
  }

}
