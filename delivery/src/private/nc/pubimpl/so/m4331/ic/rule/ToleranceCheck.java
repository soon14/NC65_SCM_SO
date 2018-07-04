package nc.pubimpl.so.m4331.ic.rule;

import java.util.Map;

import nc.bs.ml.NCLangResOnserver;
import nc.impl.pubapp.env.BSContext;
import nc.itf.scmpub.reference.uap.bd.material.MaterialPubService;
import nc.pubimpl.so.m4331.pub.RewriteVOUtil;
import nc.pubimpl.so.m4331.pub.RewriteValueUtil;
import nc.vo.bd.material.MaterialVO;
import nc.vo.pub.BusinessException;
import nc.vo.pub.lang.UFDouble;
import nc.vo.pubapp.pattern.exception.ExceptionUtils;
import nc.vo.pubapp.pattern.pub.MathTool;
import nc.vo.scmf.pub.SCMCtrlType;
import nc.vo.scmpub.res.BusinessCheck;
import nc.vo.scmpub.res.billtype.SOBillType;
import nc.vo.so.m4331.entity.DeliveryBVO;
import nc.vo.so.m4331.entity.DeliveryCheckVO;
import nc.vo.so.m4331.entity.DeliveryViewVO;
import nc.vo.so.pub.SOItemKey;
import nc.vo.so.pub.exeception.DeliveryToleranceException;
import nc.vo.so.pub.util.SOSysParaInitUtil;
import nc.vo.so.pub.util.ViewVOUtil;
import nc.vo.to.pub.para.ParaUtilsForTo;

/**
 * 下游回写发货单容差控制
 * 
 * @since 6.0
 * @version 2011-1-28 下午03:32:45
 * @author 祝会征
 */
public class ToleranceCheck {

  // private Map<String, MaterialVO> materialMap;

  /**
   * 是否允许超发货单出库检查
   * 
   * @author 祝会征
   * @time 2010-8-17 上午10:49:24
   */
  private final UFDouble percent = new UFDouble(0.01);

  private RewriteVOUtil util;

  private RewriteValueUtil valueUtil;

  public void examOverToleranceSaveBusi(RewriteVOUtil util1,
      RewriteValueUtil valueutil) throws BusinessException {
    this.util = util1;
    if (valueutil == null) {
      this.valueUtil = new RewriteValueUtil();
    }
    else {
      this.valueUtil = valueutil;
    }
    // this.materialMap = this.valueUtil.getMaterialInfo(util1);
    // 回写发货单质检表的数量的检查
    this.examDeliveryCheckInfo();
    // 检查回写发货单的容差控制
    this.valueUtil = new RewriteValueUtil();
    this.examDeliveryInfo();
  }

  /*
   * 回写发货单质检表的数量的检查
   * 出库数量只能小于或者等于发货数量。
   */
  private void examDeliveryCheckInfo() {
    DeliveryCheckVO[] vos = this.util.getRewriteCheckVO();
    if (null == vos) {
      return;
    }
    Map<String, DeliveryViewVO> map = this.util.getRewriteViewVOOnCheck();
    StringBuffer errMsg = new StringBuffer();
    for (DeliveryCheckVO vo : vos) {
      String cid = vo.getCdeliverycid();
      String bid = vo.getCdeliverybid();
      DeliveryViewVO view = map.get(bid);
      // 来源单据
      String billtype = view.getItem().getVsrctype();
      // 下游回写数量变化量
      UFDouble reNum = this.valueUtil.getRewriteNum(cid, billtype);
      // 发货数量
      UFDouble num = vo.getNnum();
      // 已出库数量
      UFDouble outNum = vo.getNtotaloutnum();
      // 变化后的出库数量
      if (outNum == null) {
        outNum = UFDouble.ZERO_DBL;
      }
      if (reNum == null) {
        reNum = UFDouble.ZERO_DBL;
      }
      if (num == null) {
        num = UFDouble.ZERO_DBL;
      }
      outNum = outNum.add(reNum);
      if (outNum.abs().compareTo(num.abs()) > 0) {
        errMsg.append(NCLangResOnserver.getInstance().getStrByID("4006002_0",
            "04006002-0150")/*发货单:*/);
        errMsg.append(":\n");
        errMsg.append(NCLangResOnserver.getInstance().getStrByID("4006002_0",
            "04006002-0151", null, new String[] {
              view.getItem().getCrowno()
            })/*第{0}行*/);
        errMsg.append("\n");
      }
    }
    if (errMsg.length() > 0) {
      errMsg.append(NCLangResOnserver.getInstance().getStrByID("4006002_0",
          "04006002-0152")/*为质检信息，不能超发货单出库。*/);
      ExceptionUtils.wrappBusinessException(errMsg.toString());
    }
  }

  public Map<String, MaterialVO> getMaterials(String[] pk_materials) {
    try {
      String[] str = new String[2];
      // 物料出库关闭下限
      str[0] = MaterialVO.OUTCLOSELOWERLIMIT;
      // 物料关闭容差
      str[1] = MaterialVO.OUTTOLERANCE;
      Map<String, MaterialVO> map =
          MaterialPubService.queryMaterialBaseInfo(pk_materials, str);
      return map;
    }
    catch (Exception e) {
      ExceptionUtils.wrappBusinessException(e.getMessage());
    }
    return null;
  }

  private void examDeliveryInfo() throws BusinessException {
    DeliveryViewVO[] views = this.util.getRewriteViewVO();
    // 严格控制错误信息
    StringBuffer errCtrlMsg = new StringBuffer();
    // 提示控制信息
    StringBuffer errInfoMsg = new StringBuffer();

    String[] cmaterialvids =
        ViewVOUtil.getDistinctFieldArray(views, DeliveryBVO.class,
            SOItemKey.CMATERIALVID, String.class);
    Map<String, MaterialVO> materialMap = this.getMaterials(cmaterialvids);
    for (DeliveryViewVO view : views) {
      String para = null;
      String billtype = view.getItem().getVsrctype();
      if (SOBillType.Order.getCode().equals(billtype)) {
        String saleorg = view.getItem().getCsaleorgid();
        para = SOSysParaInitUtil.getSO06(saleorg);
      }
      else {
        // 发货库存组织
        String pk_senstorc = view.getItem().getCsendstockorgid();
        Integer to11 = ParaUtilsForTo.getInstance().getTO11(pk_senstorc);
        if (SCMCtrlType.CTRL.equalsValue(to11)) {
          para = "严格控制";/*-=notranslate=-*/
        }
        else if (SCMCtrlType.NOCTRL.equalsValue(to11)) {
          para = "不控制";/*-=notranslate=-*/
        }
        else {
          para = "提示";/*-=notranslate=-*/
        }

      }

      if ("不控制".equals(para)) {/*-=notranslate=-*/
        return;
      }

      if ("严格控制".equals(para)) {/*-=notranslate=-*/
        errCtrlMsg.append(this.getErrMsg(view, materialMap));
      }
      if ("提示".equals(para)) {/*-=notranslate=-*/
        // 判断是否做可容量检查，用于“提示”时和用户交互
        Object o =
            BSContext.getInstance().getSession(
                BusinessCheck.DeliveryToleranceCheck.getCheckCode());
        if (null != o && !Boolean.parseBoolean(o.toString())) {
          return;
        }

        errInfoMsg.append(this.getErrMsg(view, materialMap));

      }
    }
    // 严格控制超发货单出库
    if (errCtrlMsg.length() > 0) {
      ExceptionUtils.wrappBusinessException(errCtrlMsg.toString());
    }
    // 当参数为提示时，对回写操作进行处理
    if (errInfoMsg.length() > 0) {
      errInfoMsg.append(NCLangResOnserver.getInstance().getStrByID("4006002_0",
          "04006002-0149")/*是否继续？*/);
      throw new DeliveryToleranceException(errInfoMsg.toString());
    }
    BSContext.getInstance().removeSession(
        BusinessCheck.DeliveryToleranceCheck.getCheckCode());
  }

  /*
   * 获得异常信息
   */
  private StringBuffer getErrMsg(DeliveryViewVO view,
      Map<String, MaterialVO> materialMap) {
    StringBuffer errMsg = new StringBuffer();

    // 物料id
    String pk_mertrial = view.getItem().getCmaterialvid();
    // 物料信息vo
    MaterialVO materialVO = materialMap.get(pk_mertrial);
    // 容差
    UFDouble tolerance = materialVO.getOuttolerance();
    tolerance = tolerance.multiply(this.percent);
    tolerance = MathTool.add(UFDouble.ONE_DBL, tolerance);
    // 发货数量
    UFDouble nnum = view.getItem().getNnum();
    // 最大出库数量
    UFDouble maxNum = nnum.multiply(tolerance);
    // 已出库数量
    UFDouble outNum = view.getItem().getNtotaloutnum();
    // 表体id
    String bid = view.getItem().getCdeliverybid();
    // 来源单据类型
    String billtype = view.getItem().getVsrctype();
    // 下游回写发货单数量
    UFDouble reNum = this.valueUtil.getRewriteNum(bid, billtype);
    // 最新累计出库数量
    outNum = MathTool.add(outNum, reNum);
    if (MathTool.compareTo(outNum.abs(), maxNum.abs()) > 0) {
      errMsg.append(NCLangResOnserver.getInstance().getStrByID("4006002_0",
          "04006002-0153")/*下列物料不能超容差出库:*/);
      errMsg.append("\n");
      errMsg.append(NCLangResOnserver.getInstance().getStrByID("4006002_0",
          "04006002-0151", null, new String[] {
            view.getItem().getCrowno()
          })/*第{0}行*/);
      errMsg.append("\n");

    }
    return errMsg;
  }
}
