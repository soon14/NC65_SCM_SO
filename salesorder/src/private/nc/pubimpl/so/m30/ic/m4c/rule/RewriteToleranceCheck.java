package nc.pubimpl.so.m30.ic.m4c.rule;

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
import nc.pubitf.so.m30.ic.m4453.Rewrite4453Para;
import nc.pubitf.so.m30.ic.m4c.Rewrite4CPara;
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
 * 销售出库回写销售订单执行前的规则类(before)
 * @scene
 * 根据参数SO04处理：检查是否出库数量在订单出库容差范围内
 * @param
 * index4453 途损单回写销售订单服务接口参数类
 * index4C 销售出库单回写销售订单的回写参数类
 * mMaterialVOs key:物料ID value:物料VO
 * sSO04 组织参数
 * tranTypeMap key:交易类型ID value:交易类型VO
 * tranTypeService 交易类型查询服务
 *
 * @since 6.3
 * @version 2010-01-28 下午13:49:07
 * @author 刘志伟
 */
public class RewriteToleranceCheck {
  private Map<String, Rewrite4453Para> index4453;

  private Map<String, Rewrite4CPara> index4C;

  private Map<String, MaterialVO> mMaterialVOs;

  private String sSO04;

  private Map<String, M30TranTypeVO> tranTypeMap;

  private IM30TranTypeService tranTypeService;

  public void process(SaleOrderViewVO[] views) throws BusinessException {

    this.initPara(views);

    if ("不控制".equals(this.sSO04)) {/*-=notranslate=-*/
      return;
    }
    else if ("提示".equals(this.sSO04)) {/*-=notranslate=-*/
      this.hintCheckOutRange(views);
    }
    else if ("严格控制".equals(this.sSO04)) {/*-=notranslate=-*/
      this.strictCheckOutRange(views);
    }
  }

  /**
   * 检查物料出库容差
   * 
   * @author 刘志伟
   * @time 2010-8-17 下午08:07:15
   */
  private String checkOutRange(SaleOrderViewVO[] views) {
    StringBuffer errMsg = new StringBuffer();
    for (SaleOrderViewVO view : views) {
      SaleOrderBVO body = view.getBody();
      Rewrite4CPara para4c = this.index4C.get(body.getCsaleorderbid());
      Rewrite4453Para para4453 = this.index4453.get(body.getCsaleorderbid());
      MaterialVO materialVO = this.mMaterialVOs.get(body.getCmaterialvid());
      // 物料出库容差
      UFDouble tolerance = materialVO.getOuttolerance();
      // 数量容差范围
      UFDouble range = UFDouble.ONE_DBL.add(tolerance.multiply(0.01));
      // 允许数量
      UFDouble rangeNum = body.getNnum().multiply(range);
      boolean isLossReNew = this.isLossReNew(view.getHead().getCtrantypeid());
      UFDouble nchangelossnum =
          para4453 == null ? UFDouble.ZERO_DBL : para4453.getNtranslossnum();
      UFDouble ntranslossnum =
          isLossReNew ? MathTool.add(body.getNtranslossnum(), nchangelossnum)
              : UFDouble.ZERO_DBL;
      UFDouble nchangenum =
          para4c == null ? UFDouble.ZERO_DBL : para4c.getNchangenum();
      UFDouble nchangenotoutnum =
          para4c == null ? UFDouble.ZERO_DBL : para4c.getNchangenotoutnum();
      // 实际累计出库数量
      UFDouble tempNum =
          MathTool.sub(MathTool.add(
              MathTool.add(body.getNtotaloutnum(), nchangenum),
              MathTool.add(body.getNtotalnotoutnum(), nchangenotoutnum)),
              ntranslossnum);

      // 订单数量*(1+容差比率)>=(累计出库数量+累计应发未出库数量-is途损补货(累计途损数量,0))+(当前出库实发+当前出库应发)
      if (MathTool.absCompareTo(rangeNum, tempNum) < 0) {
        errMsg.append(NCLangResOnserver.getInstance().getStrByID("4006011_0",
            "04006011-0349")/*不可超订单出库容差比率出库:*/);
        errMsg.append(":\n");
        errMsg.append(NCLangResOnserver.getInstance().getStrByID("4006011_0",
            "04006011-0340", null, new String[] {
              view.getHead().getVbillcode(), body.getCrowno()
            })/*销售订单{0}第{1}行*/);
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

  private void hintCheckOutRange(SaleOrderViewVO[] views)
      throws OrderToleranceException {
    Object o =
        BSContext.getInstance().getSession(
            BusinessCheck.OrderToleranceCheck.getCheckCode());
    if (null != o && !Boolean.parseBoolean(o.toString())) {
      return;
    }
    // 检查容差
    String errMsg = this.checkOutRange(views);
    if (errMsg.length() > 0) {
      throw new OrderToleranceException(NCLangResOnserver.getInstance()
          .getStrByID("4006011_0", "04006011-0350", null, new String[] {
            errMsg.toString()
          })/*{0}是否继续?*/);
    }
    // 此处释放session变量，以免浪费内存
    BSContext.getInstance().removeSession(
        BusinessCheck.OrderToleranceCheck.getCheckCode());
  }

  /**
   * 初始参数...
   * <ul>
   * <li>Rewrite4CPara
   * <li>SO04：超订单发货容差控制方式
   * <li>物料基本页签：出库容差
   * </ul>
   * 
   * @author 刘志伟
   * @time 2010-8-17 下午08:07:15
   */
  @SuppressWarnings("unchecked")
  private void initPara(SaleOrderViewVO[] views) {

    // 1.初始回写参数
    this.index4C =
        (Map<String, Rewrite4CPara>) BSContext.getInstance().getSession(
            Rewrite4CPara.class.getName());
    if (this.index4C == null) {
      this.index4C = new HashMap<String, Rewrite4CPara>();
    }
    this.index4453 =
        (Map<String, Rewrite4453Para>) BSContext.getInstance().getSession(
            Rewrite4453Para.class.getName());
    if (this.index4453 == null) {
      this.index4453 = new HashMap<String, Rewrite4453Para>();
    }
    // 2.超订单出库容差控制方式:下游发货库存组织SO04
    this.sSO04 =
        SysParaInitQuery.getParaString(views[0].getBody().getPk_org(),
            ParameterList.SO04.getCode());

    // 3.严格控制/提示时获得物料基本信息
    if ("不控制".equals(this.sSO04)) {/*-=notranslate=-*/
      return;
    }
    Set<String> sPk_materials = new HashSet<String>();
    for (SaleOrderViewVO view : views) {
      sPk_materials.add(view.getBody().getCmaterialvid());
    }
    this.mMaterialVOs =
        MaterialPubService.queryMaterialBaseInfo(
            sPk_materials.toArray(new String[0]), new String[] {
              MaterialVO.OUTTOLERANCE
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

  private void strictCheckOutRange(SaleOrderViewVO[] views)
      throws BusinessException {
    // 检查容差
    String errMsg = this.checkOutRange(views);
    if (errMsg.length() > 0) {
      throw new BusinessException(errMsg.toString());
    }
  }
}
