package nc.pubimpl.so.m30.pub;

import java.util.ArrayList;
import java.util.List;

import nc.impl.pubapp.bill.rewrite.BillRewriteObject;
import nc.impl.pubapp.bill.rewrite.PfRewriteParam;
import nc.impl.pubapp.bill.rewrite.RewritePara;
import nc.vo.pub.ISuperVO;
import nc.vo.pub.lang.UFDouble;
import nc.vo.pubapp.pattern.pub.MathTool;
import nc.vo.scmpub.res.billtype.SOBillType;
import nc.vo.so.m30.entity.SaleOrderBVO;
import nc.vo.so.m30.entity.SaleOrderVO;
import nc.vo.so.m30.entity.SaleOrderViewVO;
import nc.vo.so.m30.util.SaleOrderVOUtil;
import nc.vo.so.pub.SOItemKey;
import nc.vo.so.pub.util.ListUtil;

/**
 * 流程平台回写销售订单时，将单据回写框架的对象转换成要回写的销售订单视图VO
 * 
 * @author zhangby5
 *
 */
public class SORewriteParaForPFUtil {

  /**
   * 初始化回写销售订单的视图VO
   * 
   * @param rewriteObjs
   * @return
   */
  public static SaleOrderViewVO[] initRewriteViewVOMap(
      BillRewriteObject rewriteObjs, SaleOrderVO[] vos) {
    List<SaleOrderViewVO> viewVOList = new ArrayList<>();
    SaleOrderViewVO[] viewVOs =
        SaleOrderVOUtil.constructVOToViewVO(vos);
    List<String> rewriteSrcBidList = constructRownoList(rewriteObjs);
    for (SaleOrderViewVO viewVO : viewVOs) {
      String crowno = viewVO.getBody().getCsaleorderbid();
      // 传入的Bill是全VO，需要过滤掉不用回写的视图VO
      if (!rewriteSrcBidList.contains(crowno)) {
        continue;
      }
      viewVOList.add(viewVO);
    }
    return ListUtil.toArray(viewVOList);
  }
  
  private static List<String> constructRownoList(BillRewriteObject rewriteObjs) {
    List<String> rewriteSrcBidList = new ArrayList<>();
    PfRewriteParam[] rewriteParas = rewriteObjs.getRewriteParas();
    for (PfRewriteParam rewritePara : rewriteParas) {
      rewriteSrcBidList.add(rewritePara.getTargetVO().getPrimaryKey());
    }
    return rewriteSrcBidList;
  }
  
  public static  RewritePara[] builderRewritePara(BillRewriteObject rewriteObjs) {
    PfRewriteParam[] pfParams = rewriteObjs.getRewriteParas();
    if (pfParams == null || pfParams.length == 0) {
      return new RewritePara[0];
    }
    RewritePara[] rewriteParas = new RewritePara[pfParams.length];
    int i = 0;
    for (PfRewriteParam pfParam : pfParams) {
      RewritePara para = constructPara(pfParam.getTargetVO());
      UFDouble nnum = calRewriteNum(pfParam);
      para.setNnum(nnum);
      rewriteParas[i] = para;
      i++;
    }
    return rewriteParas;
  }

  /**
   * 计算回写数量
   * 
   * @param pfParam
   * @return
   */
  private static UFDouble calRewriteNum(PfRewriteParam pfParam) {
    UFDouble nnum = null;
    ISuperVO srcVO = pfParam.getSrcVO();
    ISuperVO originSrcVO = pfParam.getOriginSrcVO();
    // 当前按钮动作为保存时
    if (PfRewriteParam.WRTIE_ACTION.equalsIgnoreCase(pfParam.getActionType())) {
      // 若原始VO为空：新增行
      if (originSrcVO == null) {
        nnum = (UFDouble) srcVO.getAttributeValue(SOItemKey.NNUM);
      }
      // 若当前VO为空：删除行
      else if (srcVO == null) {
        nnum = MathTool.oppose((UFDouble) originSrcVO.getAttributeValue(SOItemKey.NNUM));
      }
      // 修改行
      else {
        nnum =
            MathTool.sub((UFDouble) srcVO.getAttributeValue(SOItemKey.NNUM),
                (UFDouble) (originSrcVO.getAttributeValue(SOItemKey.NNUM)));
      }
    }
    // 按钮动作为删除时
    else {
      nnum =
          MathTool.oppose((UFDouble) originSrcVO
              .getAttributeValue(SOItemKey.NNUM));
    }
    return nnum;
  }

  /**
   * 根据回写目标VO组织回写参数
   * 
   * @param vo
   * @return
   */
  private static RewritePara constructPara(ISuperVO vo) {
    RewritePara para = new RewritePara();
    String vsrctype = SOBillType.Order.getCode();
    String csrcid = (String) vo.getAttributeValue(SaleOrderBVO.CSALEORDERID);
    String csrcbid = (String) vo.getPrimaryKey();
    para.setVsrctype(vsrctype);
    para.setCsrcid(csrcid);
    para.setCsrcbid(csrcbid);
    return para;
  }

}
