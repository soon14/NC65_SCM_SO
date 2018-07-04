package nc.bs.so.m30.maintain.rule.update;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import nc.bs.framework.common.NCLocator;
import nc.bs.so.m30.maintain.util.RewriteBillUtil;
import nc.impl.pubapp.bill.rewrite.BillRewriter;
import nc.impl.pubapp.bill.rewrite.ItemKeyMapping;
import nc.impl.pubapp.bill.rewrite.RewritePara;
import nc.impl.pubapp.pattern.rule.ICompareRule;
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
import nc.vo.scmpub.res.billtype.SOBillType;
import nc.vo.so.m30.entity.SaleOrderBVO;
import nc.vo.so.m30.entity.SaleOrderVO;
import nc.vo.trade.checkrule.VOChecker;

/**
 * @description
 * 销售订单修改后更新回写来源和源头（回写报价单、合同、预订单、电子销售、销售订单、出库单、库存借出）
 * @scene
 * 销售订单修改保存后
 * @param 
 * 无
 * @since 6.0
 * @version 2012-4-21 上午11:05:18
 * @author 么贵敬
 */
public class RewriteBillUpdateRule implements ICompareRule<SaleOrderVO> {

  @Override
  public void process(SaleOrderVO[] vos, SaleOrderVO[] originVOs) {
    /****** 回写来源单据 ***********/
    RewriteBillUtil rewriteUtil = new RewriteBillUtil();
    BillRewriter srctool = rewriteUtil.getSrcBillRewriter();
    Map<String, List<RewritePara>> srcParaIndex =
        srctool.splitForUpdate(vos, originVOs);

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
      rewriteUtil.reWriteSrc4310(srcpara, Integer.valueOf(VOStatus.UPDATED));
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
    // 销售合同
    this.rewriteZ3(vos, originVOs);
    /****** 回写来源单据 ***********/

    /** ------------回写源头单据---------- */
    BillRewriter firsttool = rewriteUtil.getFirstBillRewriter();
    Map<String, List<RewritePara>> firstParaIndex =
        firsttool.splitForUpdate(vos, originVOs);
    List<RewritePara> firstSaleOrder =
        firstParaIndex.get(SOBillType.Order.getCode());
    // 过滤参数
    firstSaleOrder = rewriteUtil.filtrateSrc30(firstSaleOrder, srcSaleOrder);
    if (!VOChecker.isEmpty(firstSaleOrder)) {
      rewriteUtil.reWriteFirst30(firstSaleOrder);
    }
    /** ------------回写源头单据---------- */
  }

  private void rewriteZ3(SaleOrderVO[] vos, SaleOrderVO[] originVOs) {
    SaleOrderVO[] newOriginVOs = this.fillCtType(originVOs);
    SaleOrderVO[] newVOs = this.fillCtType(vos);
    if (newVOs.length == 0 && newOriginVOs.length == 0) {
      return;
    }
    // 设置上游单据类型、ID、BID、数量字段
    ItemKeyMapping mapping = new ItemKeyMapping();
    mapping.setVsrctypeKey(SaleOrderBVO.VCTTYPE);
    mapping.setCsrcidKey(SaleOrderBVO.CCTMANAGEID);
    mapping.setCsrcbidKey(SaleOrderBVO.CCTMANAGEBID);
    mapping.setNnumKey(SaleOrderBVO.NNUM);
    mapping.setNnum2Key(SaleOrderBVO.NORIGTAXMNY);
    mapping.setSrcTSKey(SaleOrderBVO.SRCTS);
    // 销售订单没有子子表,用来放下合同需要的行号
    mapping.setCsrcbbidKey(SaleOrderBVO.CROWNO);
    // 添加上游单据类型
    BillRewriter tool = new BillRewriter(mapping);
    // 设置上游单据VO类型
    tool.addSRCHeadClazz(CTBillType.SaleDaily.getCode(), CtSaleVO.class);
    tool.addSRCItemClazz(CTBillType.SaleDaily.getCode(), CtSaleBVO.class);

    Map<String, UFDouble> bodymap = new HashMap<String, UFDouble>();
    // 取新的主原币含税单价
    this.buildSaleOrderBVOData(bodymap, newVOs);
    this.buildSaleOrderBVOData(bodymap, newOriginVOs);
    SaleOrderVO[] destVOS = this.buildSplitVO(vos, originVOs);
    // 分单
    Map<String, List<RewritePara>> paraIndex =
        tool.splitForUpdate(destVOS, originVOs);

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

  /**
   * jilu add for 633 山西晋煤_销售订单修改物料回写销售合同,合并补丁,销售订单来源销售合同，修改物料不回写销售合同;
   * 变通处理方法,判断当前要回写的vo是否是来源合同的,如果是,则把clone出来的VO的pk清空,这样就会回写上游合同数量.
   * 
   * @param vos
   * @return
   */
  private SaleOrderVO[] buildSplitVO(SaleOrderVO[] vos, SaleOrderVO[] originVOs) {
    Map<String, SaleOrderBVO> voIndex = new HashMap<String, SaleOrderBVO>();
    for (SaleOrderVO originVO : originVOs) {
      for (SaleOrderBVO bvo : originVO.getChildrenVO()) {
        voIndex.put(bvo.getPrimaryKey(), bvo);
      }
    }
    List<SaleOrderVO> destVOList = new ArrayList<SaleOrderVO>();
    for (SaleOrderVO vo : vos) {
      SaleOrderVO destvo = new SaleOrderVO();
      List<SaleOrderBVO> bvoList = new ArrayList<SaleOrderBVO>();
      destvo.setParentVO(vo.getParentVO());
      for (SaleOrderBVO bvo : vo.getChildrenVO()) {
        SaleOrderBVO destbvo = (SaleOrderBVO) bvo.clone();
        if (voIndex.containsKey(destbvo.getPrimaryKey())) {
          String originsrctype =
              voIndex.get(destbvo.getPrimaryKey()).getVcttype();
          String originctcode =
              voIndex.get(destbvo.getPrimaryKey()).getVctcode();
          if (null != originctcode
              && CTBillType.SaleDaily.getCode().equals(originsrctype)) {
            String originctbid =
                voIndex.get(destbvo.getPrimaryKey()).getCctmanagebid();
            if (!originctbid.equals(destbvo.getCctmanagebid())) {
              destbvo.setPrimaryKey(null);
            }
          }
          if (null == originctcode && null != destbvo.getVctcode()) {
            destbvo.setPrimaryKey(null);
          }
        }
        bvoList.add(destbvo);
      }
      destvo.setChildrenVO(bvoList.toArray(new SaleOrderBVO[bvoList.size()]));
      destVOList.add(destvo);
    }
    return destVOList.toArray(new SaleOrderVO[destVOList.size()]);
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
