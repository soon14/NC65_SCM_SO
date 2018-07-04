package nc.pubimpl.so.m30.so.m4331.rule;

import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;

import nc.bs.framework.common.NCLocator;
import nc.bs.ml.NCLangResOnserver;
import nc.impl.pubapp.env.BSContext;
import nc.itf.scmpub.reference.uap.bd.material.MaterialPubService;
import nc.itf.scmpub.reference.uap.para.SysParaInitQuery;
import nc.itf.so.m30trantype.IM30TranTypeService;
import nc.pubitf.so.m30.so.m4331.Rewrite4331Para;
import nc.vo.bd.material.MaterialVO;
import nc.vo.pub.BusinessException;
import nc.vo.pub.lang.UFDouble;
import nc.vo.pubapp.pattern.exception.ExceptionUtils;
import nc.vo.pubapp.pattern.pub.MathTool;
import nc.vo.scmpub.res.BusinessCheck;
import nc.vo.so.m30.entity.SaleOrderBVO;
import nc.vo.so.m30.entity.SaleOrderViewVO;
import nc.vo.so.m30trantype.entity.M30TranTypeVO;
import nc.vo.so.pub.exeception.OrderToleranceException;
import nc.vo.so.pub.res.ParameterList;

/**
 * 
 * @description
 * 发货单回写销售订单执行后规则类(before):
 * @scene
 * 根据参数SO05处理：检查是否发货数量在订单发货容差范围内
 * @param
 * 
 *
 * @since 6.3
 * @version 2010-01-28 下午13:49:07
 * @author 刘志伟
 */
public class RewriteToleranceCheck {
  private Map<String, Rewrite4331Para> index;

  private Map<String, MaterialVO> mMaterialVOs;

  private String sSO05;

  private Map<String, M30TranTypeVO> tranTypeMap;

  private IM30TranTypeService tranTypeService;

  public void process(SaleOrderViewVO[] views) throws BusinessException {

    this.initPara(views);

    if ("不控制".equals(this.sSO05)) {/*-=notranslate=-*/
      return;
    }
    else if ("提示".equals(this.sSO05)) {/*-=notranslate=-*/
      this.hintCheckSendRange(views);
    }
    else if ("严格控制".equals(this.sSO05)) {/*-=notranslate=-*/
      this.strictCheckSendRange(views);
    }
  }

  /**
   * 检查物料出库容差
   * 
   * @author 刘志伟
   * @time 2010-8-17 下午08:07:15
   */
  private String checkSendRange(SaleOrderViewVO[] views) {
    StringBuffer errMsg = new StringBuffer();
    for (SaleOrderViewVO view : views) {
      SaleOrderBVO body = view.getBody();
      MaterialVO materialVO = this.mMaterialVOs.get(body.getCmaterialvid());
      // 物料出库容差
      UFDouble tolerance = materialVO.getOuttolerance();
      // 数量容差范围
      UFDouble range = UFDouble.ONE_DBL.add(tolerance.multiply(0.01));
      // 允许数量
      UFDouble rangeNum = body.getNnum().multiply(range);
      // 累计发货数量
      UFDouble sendnum =
          MathTool.add(body.getNtotalsendnum(),
              this.index.get(body.getCsaleorderbid()).getNchangenum());
      boolean isLossReNew = this.isLossReNew(view.getHead().getCtrantypeid());
      UFDouble ntranslossnum =
          isLossReNew ? body.getNtranslossnum() : UFDouble.ZERO_DBL;
      UFDouble tempNum = MathTool.sub(sendnum, ntranslossnum);
      // 订单数量* (1 + 容差比率) < 累计发货数量 - is途损补货(累计途损数量,0)
      if (MathTool.absCompareTo(rangeNum, tempNum) < 0) {
        errMsg.append(NCLangResOnserver.getInstance().getStrByID("4006011_0", "04006011-0389")/*不可超订单发货容差比率发货:*/);
        errMsg.append("\n");
        errMsg.append(NCLangResOnserver.getInstance().getStrByID("4006011_0", "04006011-0340", null, new String[]{view.getHead().getVbillcode(),body.getCrowno()})/*销售订单{0}第{1}行*/);
        errMsg.append("\n");
      }
    }
    return errMsg.toString();
  }

  private IM30TranTypeService getTranTypeService() {
    if (this.tranTypeService == null) {
      this.tranTypeService =
          NCLocator.getInstance().lookup(IM30TranTypeService.class);
    }
    return this.tranTypeService;
  }

  private M30TranTypeVO getTranTypeVOByID(String ctrantypeid) {
    if (this.tranTypeMap == null) {
      this.tranTypeMap = new HashMap<String, M30TranTypeVO>();
    }
    if (this.tranTypeMap.get(ctrantypeid) == null) {
      M30TranTypeVO[] tranTypeVOs = null;
      try {
        tranTypeVOs = this.getTranTypeService().queryTranTypeVOs(new String[] {
          ctrantypeid
        });
      }
      catch (BusinessException e) {
        ExceptionUtils.wrappException(e);
      }
      if (tranTypeVOs != null && tranTypeVOs.length > 0) {
        this.tranTypeMap.put(tranTypeVOs[0].getCtrantypeid(), tranTypeVOs[0]);
      }
    }
    return this.tranTypeMap.get(ctrantypeid);
  }

  private void hintCheckSendRange(SaleOrderViewVO[] views)
      throws OrderToleranceException {
    Object o =
        BSContext.getInstance().getSession(
            BusinessCheck.OrderToleranceCheck.getCheckCode());
    if (null != o && !Boolean.parseBoolean(o.toString())) {
      return;
    }
    // 检查容差
    String errMsg = this.checkSendRange(views);
    if (errMsg.length() > 0) {
      throw new OrderToleranceException(NCLangResOnserver.getInstance().getStrByID("4006011_0", "04006011-0350", null, new String[]{errMsg.toString()})/*{0}是否继续?*/);
    }
    // 此处释放session变量，以免浪费内存
    BSContext.getInstance().removeSession(
        BusinessCheck.OrderToleranceCheck.getCheckCode());
  }

  /**
   * 初始参数...
   * <ul>
   * <li>Rewrite4331Para
   * <li>SO05：超订单发货容差控制方式
   * <li>物料基本页签：出库容差
   * </ul>
   * 
   * @author 刘志伟
   * @time 2010-8-17 下午08:07:15
   */
  @SuppressWarnings("unchecked")
  private void initPara(SaleOrderViewVO[] views) {

    // 1.初始回写参数
    this.index =
        (Map<String, Rewrite4331Para>) BSContext.getInstance().getSession(
            Rewrite4331Para.class.getName());

    // 2.超订单发货容差控制方式：下游物流组织SO05
    this.sSO05 =
        SysParaInitQuery.getParaString(views[0].getBody().getPk_org(),
            ParameterList.SO05.getCode());

    // 3.获得物料基本信息
    Set<String> sPk_materials = new HashSet<String>();
    for (SaleOrderViewVO view : views) {
      sPk_materials.add(view.getBody().getCmaterialvid());
    }
    this.mMaterialVOs =
        MaterialPubService.queryMaterialBaseInfo(
            sPk_materials.toArray(new String[0]), new String[] {
              MaterialVO.OUTTOLERANCE,
            });

  }

  /**
   * 交易类型——是否途损补货
   * 
   * @author 刘志伟
   * @time 2010-01-28 下午13:49:07
   */
  private boolean isLossReNew(String ctrantypeid) {
    M30TranTypeVO tranTypeVO = this.getTranTypeVOByID(ctrantypeid);
    return tranTypeVO.getBlossrenew() == null ? false : tranTypeVO
        .getBlossrenew().booleanValue();
  }

  private void strictCheckSendRange(SaleOrderViewVO[] views)
      throws BusinessException {
    // 检查容差
    String errMsg = this.checkSendRange(views);
    if (errMsg.length() > 0) {
      throw new BusinessException(errMsg.toString());
    }
  }
}
