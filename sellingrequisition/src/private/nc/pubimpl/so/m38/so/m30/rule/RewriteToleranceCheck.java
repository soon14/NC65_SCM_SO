package nc.pubimpl.so.m38.so.m30.rule;

import java.util.Map;

import nc.bs.ml.NCLangResOnserver;
import nc.impl.pubapp.env.BSContext;
import nc.itf.scmpub.reference.uap.para.SysParaInitQuery;
import nc.pubitf.so.m38.so.m30.Rewrite30Para;
import nc.vo.pub.BusinessException;
import nc.vo.pub.lang.UFDouble;
import nc.vo.pubapp.pattern.pub.MathTool;
import nc.vo.scmpub.res.BusinessCheck;
import nc.vo.so.m38.entity.PreOrderBVO;
import nc.vo.so.m38.entity.PreOrderViewVO;
import nc.vo.so.pub.exeception.PreOrderToleranceException;
import nc.vo.so.pub.res.ParameterList;

/**
 * @description
 * 销售订单回写预订单执行前(before)：检查是否订单数量在预订单安排容差范围内
 * @scene
 * 销售订单回写预订单执行前
 * @param
 * index  key: 预订单子表主键  value:销售订单回写预订单的回写参数类
 * sSO26 超预订单安排数量控制方式
 * @author 刘志伟
 * @time 2010-01-28 下午13:49:07
 */
public class RewriteToleranceCheck {

  private Map<String, Rewrite30Para> index;

  private String sSO26;

  public void process(PreOrderViewVO[] views) throws BusinessException {

    this.initPara(views);

    if ("不控制".equals(this.sSO26)) {/*-=notranslate=-*/
      return;
    }
    else if ("提示".equals(this.sSO26)) {/*-=notranslate=-*/
      this.hintCheckArrangeRange(views);
    }
    else if ("严格控制".equals(this.sSO26)) {/*-=notranslate=-*/
      this.strictCheckArrangeRange(views);
    }
  }

  /**
   * 检查物料预订单安排容差(实际上咩有容差，只有预订单主数量)
   * 
   * @author 刘志伟
   * @time 2010-8-17 下午08:07:15
   */
  private String checkArrangeRange(PreOrderViewVO[] views) {
    StringBuffer errMsg = new StringBuffer();
    for (PreOrderViewVO view : views) {
      PreOrderBVO body = view.getItem();
      // 允许数量(预订单主数量)
      UFDouble rangeNum = body.getNnum();
      // 累计安排数量 = 累计安排数量 + 当前安排数量
      UFDouble arrnum =
          MathTool.add(body.getNarrnum(), this.index
              .get(body.getCpreorderbid()).getNnum());
      // 预订单数量 < 累计安排数量
      if (MathTool.absCompareTo(rangeNum, arrnum) < 0) {
        errMsg.append(NCLangResOnserver.getInstance().getStrByID("4006012_0", "04006012-0064")/*不可超预订单数量安排订单:*/);
        errMsg.append("\n");
        errMsg.append(NCLangResOnserver.getInstance().getStrByID("4006012_0", "04006012-0065", null, new String[]{view.getHead().getVbillcode(),body.getCrowno()})/*预订单{0}第{1}行*/);
        errMsg.append("\n");
      }
    }
    return errMsg.toString();
  }

  private void hintCheckArrangeRange(PreOrderViewVO[] views)
      throws PreOrderToleranceException {
    Object o =
        BSContext.getInstance().getSession(
            BusinessCheck.PreOrderToleranceCheck.getCheckCode());
    if (null != o && !Boolean.parseBoolean(o.toString())) {
      return;
    }
    // 检查容差
    String errMsg = this.checkArrangeRange(views);
    if (errMsg.length() > 0) {
      throw new PreOrderToleranceException(NCLangResOnserver.getInstance().getStrByID("4006012_0", "04006012-0066", null, new String[]{errMsg.toString()})/*{0}是否继续?*/);
    }
    // 此处释放session变量，以免浪费内存
    BSContext.getInstance().removeSession(
        BusinessCheck.PreOrderToleranceCheck.getCheckCode());
  }

  /**
   * 初始参数...
   * <ul>
   * <li>Rewrite30Para
   * <li>SO26：超预订单安排订单控制方式
   * </ul>
   * 
   * @author 刘志伟
   * @time 2010-8-17 下午08:07:15
   */
  @SuppressWarnings("unchecked")
  private void initPara(PreOrderViewVO[] views) {

    // 1.初始回写参数
    this.index =
        (Map<String, Rewrite30Para>) BSContext.getInstance().getSession(
            Rewrite30Para.class.getName());

    // 2.超预订单安排数量控制方式：SO05
    this.sSO26 =
        SysParaInitQuery.getParaString(views[0].getHead().getPk_org(),
            ParameterList.SO26.getCode());

  }

  private void strictCheckArrangeRange(PreOrderViewVO[] views)
      throws BusinessException {
    // 检查容差
    String errMsg = this.checkArrangeRange(views);
    if (errMsg.length() > 0) {
      throw new BusinessException(errMsg.toString());
    }
  }

}
