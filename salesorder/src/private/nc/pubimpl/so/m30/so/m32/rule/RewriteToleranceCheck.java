package nc.pubimpl.so.m30.so.m32.rule;

import java.util.Map;

import nc.bs.ml.NCLangResOnserver;
import nc.impl.pubapp.env.BSContext;
import nc.pubitf.so.m30.so.m32.Rewrite32Para;
import nc.vo.pub.BusinessException;
import nc.vo.pub.lang.UFDouble;
import nc.vo.pubapp.pattern.pub.MathTool;
import nc.vo.scmpub.res.BusinessCheck;
import nc.vo.so.m30.entity.SaleOrderBVO;
import nc.vo.so.m30.entity.SaleOrderViewVO;
import nc.vo.so.pub.exeception.OrderToleranceException;
import nc.vo.so.pub.util.SOSysParaInitUtil;

/**
 * 
 * @description
 * 销售发票回写销售订单执行前的规则类(before)：
 * @scene
 * 根据参数SO07处理：检查是否开票数量在订单数量范围内
 * @param
 * 无
 *
 * @since 6.0
 * @version 2010-01-28 下午13:49:07
 * @author 刘志伟
 */
public class RewriteToleranceCheck {
  private Map<String, Rewrite32Para> index;

  private String sSO07;

  public void process(SaleOrderViewVO[] views) throws BusinessException {

    this.initPara(views);

    if ("不控制".equals(this.sSO07)) {/*-=notranslate=-*/
      return;
    }
    else if ("提示".equals(this.sSO07)) {/*-=notranslate=-*/
      this.hintCheckSendRange(views);
    }
    else if ("严格控制".equals(this.sSO07)) {/*-=notranslate=-*/
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
      // 数量容差范围
      UFDouble range = UFDouble.ONE_DBL;
      // 允许数量
      UFDouble rangeNum =
          body.getNnum() == null ? UFDouble.ZERO_DBL : body.getNnum().multiply(
              range);
      // 实际开票数量
      UFDouble tempNum =
          MathTool
              .add(
                  MathTool.add(body.getNtotalrushnum(),
                      body.getNtotalinvoicenum()),
                  this.index.get(body.getCsaleorderbid()).getNchangenum());

      // 订单数量*容差值>=累计出库对冲数量+累计开票数量+当前开票数量
      if (MathTool.absCompareTo(rangeNum, tempNum) < 0) {
        errMsg.append(NCLangResOnserver.getInstance().getStrByID("4006011_0",
            "04006011-0355")/*不可超订单数量开票:*/);
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
   * <li>Rewrite32Para
   * <li>SO07：订单开票控制方式
   * </ul>
   * 
   * @author 刘志伟
   * @time 2010-8-17 下午08:07:15
   */
  @SuppressWarnings("unchecked")
  private void initPara(SaleOrderViewVO[] views) {

    // 1.初始回写参数
    this.index =
        (Map<String, Rewrite32Para>) BSContext.getInstance().getSession(
            Rewrite32Para.class.getName());

    // 2.订单开票控制方式：销售组织SO07
    this.sSO07 = SOSysParaInitUtil.getSO07(views[0].getHead().getPk_org());

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
