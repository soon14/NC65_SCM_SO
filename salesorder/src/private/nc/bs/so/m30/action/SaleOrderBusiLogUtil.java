package nc.bs.so.m30.action;

import nc.bs.ml.NCLangResOnserver;
import nc.impl.pubapp.bill.convertor.ViewToBillConvertor;
import nc.vo.pub.BusinessException;
import nc.vo.pubapp.pattern.data.IObjectConvert;
import nc.vo.pubapp.pattern.exception.ExceptionUtils;
import nc.vo.so.m30.entity.SaleOrderVO;
import nc.vo.so.m30.entity.SaleOrderViewVO;
import nc.vo.so.pub.enumeration.ActionType;
import nc.vo.so.pub.enumeration.FuncodeType;
import nc.vo.so.pub.util.BusinessLogUtil;

public class SaleOrderBusiLogUtil {

  public SaleOrderBusiLogUtil() {
    /*
     * 空构造函数
     */
  }

  /**
   * 销售订单审批记录业务日志规则
   *
   * @since 6.0
   * @version 2011-7-12 上午10:02:42
   * @author 旷宗义
   */
  public void addApproveBusiLog(SaleOrderVO[] vos) {
    if (null == vos) {
      return;
    }
    BusinessLogUtil util = new BusinessLogUtil();
    util.setActiontype(ActionType.APPROVE);
    util.setFuncnode(FuncodeType.SALEORDER);
    util.setBusiobjname(NCLangResOnserver.getInstance().getStrByID("4006011_0", "04006011-0048")/*销售订单审批*/);
    util.setLogmsg(nc.vo.ml.NCLangRes4VoTransl.getNCLangRes().getStrByID("4006011_0","04006011-0048")/*@res "销售订单审批"*/);
    util.setFuncletInitData(null);
    try {
      util.insertBusiLog(vos, true);
    }
    catch (BusinessException e) {
      ExceptionUtils.wrappException(e);
    }
  }

  /**
   * 销售订单整单关闭
   */
  public void addBillCloseBusiLog(SaleOrderVO[] vos) {
    if (null == vos) {
      return;
    }
    BusinessLogUtil util = new BusinessLogUtil();
    util.setActiontype(ActionType.BILLCLOSE);
    util.setFuncnode(FuncodeType.SALEORDER);
    util.setBusiobjname(NCLangResOnserver.getInstance().getStrByID("4006011_0", "04006011-0049")/*销售订单整单关闭*/);
    util.setLogmsg(nc.vo.ml.NCLangRes4VoTransl.getNCLangRes().getStrByID("4006011_0","04006011-0049")/*@res "销售订单整单关闭"*/);
    util.setFuncletInitData(null);
    try {
      util.insertBusiLog(vos, true);
    }
    catch (BusinessException e) {
      ExceptionUtils.wrappException(e);
    }
  }

  public void addBillOpenBusiLog(SaleOrderVO[] vos) {
    if (null == vos) {
      return;
    }
    BusinessLogUtil util = new BusinessLogUtil();
    util.setActiontype(ActionType.BILLOPEN);
    util.setFuncnode(FuncodeType.SALEORDER);
    util.setBusiobjname(NCLangResOnserver.getInstance().getStrByID("4006011_0", "04006011-0050")/*销售订单整单打开*/);
    util.setLogmsg(nc.vo.ml.NCLangRes4VoTransl.getNCLangRes().getStrByID("4006011_0","04006011-0050")/*@res "销售订单整单打开"*/);
    util.setFuncletInitData(null);
    try {
      util.insertBusiLog(vos, true);
    }
    catch (BusinessException e) {
      ExceptionUtils.wrappException(e);
    }
  }

  /**
   * 销售订单行关闭
   */
  public void addBillRowClose(SaleOrderViewVO[] vos) {
    if ((null == vos) || (vos.length == 0)) {
      return;
    }
    IObjectConvert<SaleOrderViewVO, SaleOrderVO> billconvert =
        new ViewToBillConvertor<SaleOrderViewVO, SaleOrderVO>(SaleOrderVO.class);
    SaleOrderVO[] newbills = billconvert.convert(vos);
    BusinessLogUtil util = new BusinessLogUtil();
    util.setActiontype(ActionType.ROWCLOSE);
    util.setFuncnode(FuncodeType.SALEORDER);
    util.setBusiobjname(NCLangResOnserver.getInstance().getStrByID("4006011_0", "04006011-0051")/*销售订单行关闭*/);
    util.setLogmsg(nc.vo.ml.NCLangRes4VoTransl.getNCLangRes().getStrByID("4006011_0","04006011-0051")/*@res "销售订单行关闭"*/);
    util.setFuncletInitData(null);
    try {
      util.insertBusiLog(newbills, false);
    }
    catch (BusinessException e) {
      ExceptionUtils.wrappException(e);
    }
  }

  /**
   * 销售订单行关闭
   */
  public void addBillRowOpen(SaleOrderViewVO[] vos) {
    if (null == vos) {
      return;
    }
    IObjectConvert<SaleOrderViewVO, SaleOrderVO> billconvert =
        new ViewToBillConvertor<SaleOrderViewVO, SaleOrderVO>(SaleOrderVO.class);
    SaleOrderVO[] newbills = billconvert.convert(vos);
    BusinessLogUtil util = new BusinessLogUtil();
    util.setActiontype(ActionType.ROWOPEN);
    util.setFuncnode(FuncodeType.SALEORDER);
    util.setBusiobjname(NCLangResOnserver.getInstance().getStrByID("4006011_0", "04006011-0052")/*销售订单行打开*/);
    util.setLogmsg(nc.vo.ml.NCLangRes4VoTransl.getNCLangRes().getStrByID("4006011_0","04006011-0052")/*@res "销售订单行打开"*/);
    util.setFuncletInitData(null);
    try {
      util.insertBusiLog(newbills, false);
    }
    catch (BusinessException e) {
      ExceptionUtils.wrappException(e);
    }
  }

  /**
   * 销售订单删除记录业务日志规则
   *
   * @since 6.0
   * @version 2011-7-12 上午10:02:42
   * @author 旷宗义
   */
  public void addDeleteBusiLog(SaleOrderVO[] vos) {
    if (null == vos) {
      return;
    }
    BusinessLogUtil util = new BusinessLogUtil();
    util.setActiontype(ActionType.DELETE);
    util.setFuncnode(FuncodeType.SALEORDER);
    util.setBusiobjname(NCLangResOnserver.getInstance().getStrByID("4006011_0", "04006011-0053")/*销售订单删除*/);
    util.setLogmsg(nc.vo.ml.NCLangRes4VoTransl.getNCLangRes().getStrByID("4006011_0","04006011-0053")/*@res "销售订单删除"*/);
    util.setFuncletInitData(null);
    try {
      util.insertBusiLog(vos, true);
    }
    catch (BusinessException e) {
      ExceptionUtils.wrappException(e);
    }
  }

  /**
   * 销售订单冻结
   */
  public void addFreezeBusiLog(SaleOrderVO[] vos) {
    if (null == vos) {
      return;
    }
    BusinessLogUtil util = new BusinessLogUtil();
    util.setActiontype(ActionType.FREEZE);
    util.setFuncnode(FuncodeType.SALEORDER);
    util.setBusiobjname(NCLangResOnserver.getInstance().getStrByID("4006011_0", "04006011-0054")/*销售订单冻结*/);
    util.setLogmsg(nc.vo.ml.NCLangRes4VoTransl.getNCLangRes().getStrByID("4006011_0","04006011-0054")/*@res "销售订单冻结"*/);
    util.setFuncletInitData(null);
    try {
      util.insertBusiLog(vos, true);
    }
    catch (BusinessException e) {
      ExceptionUtils.wrappException(e);
    }
  }

  /**
   * 销售订单行开票关闭
   */
  public void addInvoiceCloseBusiLog(SaleOrderViewVO[] vos) {
    if (null == vos) {
      return;
    }
    IObjectConvert<SaleOrderViewVO, SaleOrderVO> billconvert =
        new ViewToBillConvertor<SaleOrderViewVO, SaleOrderVO>(SaleOrderVO.class);
    SaleOrderVO[] newbills = billconvert.convert(vos);
    BusinessLogUtil util = new BusinessLogUtil();
    util.setActiontype(ActionType.VOICECLOSE);
    util.setFuncnode(FuncodeType.SALEORDER);
    util.setBusiobjname(NCLangResOnserver.getInstance().getStrByID("4006011_0", "04006011-0055")/*销售订单行开票关闭*/);
    util.setLogmsg(nc.vo.ml.NCLangRes4VoTransl.getNCLangRes().getStrByID("4006011_0","04006011-0055")/*@res "销售订单行开票关闭"*/);
    util.setFuncletInitData(null);
    try {
      util.insertBusiLog(newbills, false);
    }
    catch (BusinessException e) {
      ExceptionUtils.wrappException(e);
    }
  }

  /**
   * 销售订单行开票打开
   */
  public void addInvoiceOpenBusiLog(SaleOrderViewVO[] vos) {
    if (null == vos) {
      return;
    }
    IObjectConvert<SaleOrderViewVO, SaleOrderVO> billconvert =
        new ViewToBillConvertor<SaleOrderViewVO, SaleOrderVO>(SaleOrderVO.class);
    SaleOrderVO[] newbills = billconvert.convert(vos);
    BusinessLogUtil util = new BusinessLogUtil();
    util.setActiontype(ActionType.VOICEOPEN);
    util.setFuncnode(FuncodeType.SALEORDER);
    util.setBusiobjname(NCLangResOnserver.getInstance().getStrByID("4006011_0", "04006011-0056")/*销售订单行开票打开*/);
    util.setLogmsg(nc.vo.ml.NCLangRes4VoTransl.getNCLangRes().getStrByID("4006011_0","04006011-0056")/*@res "销售订单行开票打开"*/);
    util.setFuncletInitData(null);
    try {
      util.insertBusiLog(newbills, false);
    }
    catch (BusinessException e) {
      ExceptionUtils.wrappException(e);
    }
  }

  /**
   * 销售订单行出库关闭
   */
  public void addOutCloseBusiLog(SaleOrderViewVO[] vos) {
    if (null == vos) {
      return;
    }
    IObjectConvert<SaleOrderViewVO, SaleOrderVO> billconvert =
        new ViewToBillConvertor<SaleOrderViewVO, SaleOrderVO>(SaleOrderVO.class);
    SaleOrderVO[] newbills = billconvert.convert(vos);
    BusinessLogUtil util = new BusinessLogUtil();
    util.setActiontype(ActionType.OUTCLOSE);
    util.setFuncnode(FuncodeType.SALEORDER);
    util.setBusiobjname(NCLangResOnserver.getInstance().getStrByID("4006011_0", "04006011-0057")/*销售订单行出库关闭*/);
    util.setLogmsg(nc.vo.ml.NCLangRes4VoTransl.getNCLangRes().getStrByID("4006011_0","04006011-0057")/*@res "销售订单行出库关闭"*/);
    util.setFuncletInitData(null);
    try {
      util.insertBusiLog(newbills, false);
    }
    catch (BusinessException e) {
      ExceptionUtils.wrappException(e);
    }
  }

  /**
   * 销售订单行出库打开
   */
  public void addOutOpenBusiLog(SaleOrderViewVO[] vos) {
    if (null == vos) {
      return;
    }
    IObjectConvert<SaleOrderViewVO, SaleOrderVO> billconvert =
        new ViewToBillConvertor<SaleOrderViewVO, SaleOrderVO>(SaleOrderVO.class);
    SaleOrderVO[] newbills = billconvert.convert(vos);
    BusinessLogUtil util = new BusinessLogUtil();
    util.setActiontype(ActionType.OUTOPEN);
    util.setFuncnode(FuncodeType.SALEORDER);
    util.setBusiobjname(NCLangResOnserver.getInstance().getStrByID("4006011_0", "04006011-0058")/*销售订单行出库打开*/);
    util.setLogmsg(nc.vo.ml.NCLangRes4VoTransl.getNCLangRes().getStrByID("4006011_0","04006011-0058")/*@res "销售订单行出库打开"*/);
    util.setFuncletInitData(null);
    try {
      util.insertBusiLog(newbills, false);
    }
    catch (BusinessException e) {
      ExceptionUtils.wrappException(e);
    }
  }

  /**
   * 销售订单保存记录业务日志规则
   *
   * @since 6.0
   * @version 2011-7-12 上午10:02:42
   * @author 旷宗义
   */

  public void addSaveBusiLog(SaleOrderVO[] vos) {
    if (null == vos) {
      return;
    }
    BusinessLogUtil util = new BusinessLogUtil();
    util.setActiontype(ActionType.SAVE);
    util.setFuncnode(FuncodeType.SALEORDER);
    util.setBusiobjname(NCLangResOnserver.getInstance().getStrByID("4006011_0", "04006011-0059")/*销售订单保存*/);
    util.setLogmsg(nc.vo.ml.NCLangRes4VoTransl.getNCLangRes().getStrByID("4006011_0","04006011-0059")/*@res "销售订单保存"*/);
    util.setFuncletInitData(null);
    try {
      util.insertBusiLog(vos, true);
    }
    catch (BusinessException e) {
      ExceptionUtils.wrappException(e);
    }
  }

  /**
   * 销售订单行发货关闭
   */
  public void addSendCloseBusiLog(SaleOrderViewVO[] vos) {
    if (null == vos) {
      return;
    }
    IObjectConvert<SaleOrderViewVO, SaleOrderVO> billconvert =
        new ViewToBillConvertor<SaleOrderViewVO, SaleOrderVO>(SaleOrderVO.class);
    SaleOrderVO[] newbills = billconvert.convert(vos);
    BusinessLogUtil util = new BusinessLogUtil();
    util.setActiontype(ActionType.SENDCLOSE);
    util.setFuncnode(FuncodeType.SALEORDER);
    util.setBusiobjname(NCLangResOnserver.getInstance().getStrByID("4006011_0", "04006011-0060")/*销售订单行发货关闭*/);
    util.setLogmsg(nc.vo.ml.NCLangRes4VoTransl.getNCLangRes().getStrByID("4006011_0","04006011-0060")/*@res "销售订单行发货关闭"*/);
    util.setFuncletInitData(null);
    try {
      util.insertBusiLog(newbills, false);
    }
    catch (BusinessException e) {
      ExceptionUtils.wrappException(e);
    }
  }

  /**
   * 销售订单行发货打开
   */
  public void addSendOpenBusiLog(SaleOrderViewVO[] vos) {
    if (null == vos) {
      return;
    }
    IObjectConvert<SaleOrderViewVO, SaleOrderVO> billconvert =
        new ViewToBillConvertor<SaleOrderViewVO, SaleOrderVO>(SaleOrderVO.class);
    SaleOrderVO[] newbills = billconvert.convert(vos);
    BusinessLogUtil util = new BusinessLogUtil();
    util.setActiontype(ActionType.SENDOPEN);
    util.setFuncnode(FuncodeType.SALEORDER);
    util.setBusiobjname(NCLangResOnserver.getInstance().getStrByID("4006011_0", "04006011-0061")/*销售订单行发货打开*/);
    util.setLogmsg(nc.vo.ml.NCLangRes4VoTransl.getNCLangRes().getStrByID("4006011_0","04006011-0061")/*@res "销售订单行发货打开"*/);
    util.setFuncletInitData(null);
    try {
      util.insertBusiLog(newbills, false);
    }
    catch (BusinessException e) {
      ExceptionUtils.wrappException(e);
    }
  }

  /**
   * 销售订单行结算关闭
   */
  public void addSetCloseBusiLog(SaleOrderViewVO[] vos) {
    if (null == vos) {
      return;
    }
    IObjectConvert<SaleOrderViewVO, SaleOrderVO> billconvert =
        new ViewToBillConvertor<SaleOrderViewVO, SaleOrderVO>(SaleOrderVO.class);
    SaleOrderVO[] newbills = billconvert.convert(vos);
    BusinessLogUtil util = new BusinessLogUtil();
    util.setActiontype(ActionType.SETCLOSE);
    util.setFuncnode(FuncodeType.SALEORDER);
    util.setBusiobjname(NCLangResOnserver.getInstance().getStrByID("4006011_0", "04006011-0062")/*销售订单行结算关闭*/);
    util.setLogmsg(nc.vo.ml.NCLangRes4VoTransl.getNCLangRes().getStrByID("4006011_0","04006011-0062")/*@res "销售订单行结算关闭"*/);
    util.setFuncletInitData(null);
    try {
      util.insertBusiLog(newbills, false);
    }
    catch (BusinessException e) {
      ExceptionUtils.wrappException(e);
    }
  }

  /**
   * 销售订单行结算打开
   */
  public void addSetOpenBusiLog(SaleOrderViewVO[] vos) {
    if (null == vos) {
      return;
    }
    IObjectConvert<SaleOrderViewVO, SaleOrderVO> billconvert =
        new ViewToBillConvertor<SaleOrderViewVO, SaleOrderVO>(SaleOrderVO.class);
    SaleOrderVO[] newbills = billconvert.convert(vos);
    BusinessLogUtil util = new BusinessLogUtil();
    util.setActiontype(ActionType.SETOPEN);
    util.setFuncnode(FuncodeType.SALEORDER);
    util.setBusiobjname(NCLangResOnserver.getInstance().getStrByID("4006011_0", "04006011-0063")/*销售订单行结算打开*/);
    util.setLogmsg(nc.vo.ml.NCLangRes4VoTransl.getNCLangRes().getStrByID("4006011_0","04006011-0063")/*@res "销售订单行结算打开"*/);
    util.setFuncletInitData(null);
    try {
      util.insertBusiLog(newbills, false);
    }
    catch (BusinessException e) {
      ExceptionUtils.wrappException(e);
    }
  }

  /**
   * 销售订单审批记录业务日志规则
   *
   * @since 6.0
   * @version 2011-7-12 上午10:02:42
   * @author 旷宗义
   */

  public void addUnApproveBusiLog(SaleOrderVO[] vos) {
    if (null == vos) {
      return;
    }
    BusinessLogUtil util = new BusinessLogUtil();
    util.setActiontype(ActionType.UNAPPROVE);
    util.setFuncnode(FuncodeType.SALEORDER);
    util.setBusiobjname(NCLangResOnserver.getInstance().getStrByID("4006011_0", "04006011-0064")/*销售订单弃审*/);
    util.setLogmsg(nc.vo.ml.NCLangRes4VoTransl.getNCLangRes().getStrByID("4006011_0","04006011-0064")/*@res "销售订单弃审"*/);
    util.setFuncletInitData(null);
    try {
      util.insertBusiLog(vos, true);
    }
    catch (BusinessException e) {
      ExceptionUtils.wrappException(e);
    }
  }

  /**
   * 销售订单解冻 书写业务日志
   *
   * @since 6.0
   * @version 2011-5-19 下午01:43:19
   * @author 祝会征
   */
  public void addUnFreezeBusiLog(SaleOrderVO[] vos) {
    if (null == vos) {
      return;
    }
    BusinessLogUtil util = new BusinessLogUtil();
    util.setActiontype(ActionType.UNFREEZE);
    util.setFuncnode(FuncodeType.SALEORDER);
    util.setBusiobjname(NCLangResOnserver.getInstance().getStrByID("4006011_0", "04006011-0065")/*销售订单解冻 */);
    util.setLogmsg(nc.vo.ml.NCLangRes4VoTransl.getNCLangRes().getStrByID("4006011_0","04006011-0065")/*@res "销售订单解冻 "*/);
    util.setFuncletInitData(null);
    try {
      util.insertBusiLog(vos, true);
    }
    catch (BusinessException e) {
      ExceptionUtils.wrappException(e);
    }
  }

}