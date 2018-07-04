package nc.impl.so.m4331.action.maintain;

import java.util.Arrays;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;

import nc.bs.scmpub.pf.PfResumeExceptionUtils;
import nc.bs.so.pub.rule.rowno.SORowNoUtil;
import nc.impl.pubapp.bill.convertor.BillToViewConvertor;
import nc.impl.pubapp.env.BSContext;
import nc.impl.so.m4331.action.maintain.rule.pushwrite.RewriteSrcRule;
import nc.itf.scmpub.reference.uap.pf.PfServiceScmUtil;
import nc.vo.pub.BusinessException;
import nc.vo.pub.lang.UFBoolean;
import nc.vo.pub.lang.UFDouble;
import nc.vo.pubapp.pattern.log.TimeLog;
import nc.vo.pubapp.pflow.PfUserObject;
import nc.vo.pubapp.pflow.SplitParameter;
import nc.vo.scmpub.exp.IResumeExceptionEx;
import nc.vo.scmpub.res.billtype.SOBillType;
import nc.vo.so.m4331.entity.DeliveryBVO;
import nc.vo.so.m4331.entity.DeliveryHVO;
import nc.vo.so.m4331.entity.DeliveryVO;
import nc.vo.so.m4331.entity.DeliveryViewVO;
import nc.vo.so.pub.SOParameterVO;
import nc.vo.so.pub.comparator.BillNOandRowNoComparator;

/**
 * <p>
 * <b>本类主要完成以下功能：</b>
 * <ul>
 * <li>发货单推式保存功能后台实现
 * </ul>
 * <p>
 * 
 * @version 本版本号 6.0
 * @since
 * @author fengjb
 * @time 2010-5-31 下午03:01:20
 */
public class DeliveryPushWriteAction {

  private Set<DeliveryViewVO> reSrc;

  public DeliveryVO[] pushwrite(SOParameterVO paravo) throws BusinessException {
    TimeLog.logStart();
    DeliveryVO[] newvos = this.getDeliveryvos(paravo);
    
    TimeLog.info("发货安排时分单合单"); /*-=notranslate=-*/
    TimeLog.logStart();
    if (this.reSrc.size() > 0) {
      this.addRewriteRule();
    }
    if (null == newvos) {
      return (DeliveryVO[]) paravo.getVos();
    }
    
    
    // 根据来源单据号和行号排序，保证与上游单据的行顺序 （处理安排界面排序）
    for (DeliveryVO vo : newvos) {
      BillNOandRowNoComparator c = new BillNOandRowNoComparator();
      Arrays.sort(vo.getChildrenVO(), c);
    }
    
    
    PfUserObject[] userobjs = new PfUserObject[newvos.length];
    for (int i = 0, iloop = newvos.length; i < iloop; i++) {
      userobjs[i] = new PfUserObject();
      userobjs[i].setBusinessCheckMap(paravo.getBusinessCheckMap());
    }
    // 交互异常处理 add by zhangby5
    Map<String, Object> resumeResult =
        (Map<String, Object>) ((Map<String, Object>) paravo.getUserObject())
            .get(IResumeExceptionEx.RESUME_EXCEPTION_RESULT);

    HashMap hmPfExParams = new HashMap();
    if (resumeResult != null && resumeResult.size() > 0) {
      hmPfExParams
          .put(IResumeExceptionEx.RESUME_EXCEPTION_RESULT, resumeResult);
      PfResumeExceptionUtils.processBusinessCheck(hmPfExParams);
    }
    // 交互异常处理结束

    this.fillRownoRule(newvos);

    // 调用销售订单保存脚本
    return (DeliveryVO[]) PfServiceScmUtil.processBatch("WRITE",
        SOBillType.Delivery.getCode(), newvos, userobjs, null);
  }

  private void fillRownoRule(DeliveryVO[] newvos) {

    for (DeliveryVO vo : newvos) {
      Set<String> crownoSet = new HashSet<>();
      DeliveryBVO[] bvos = vo.getChildrenVO();
      for (DeliveryBVO bvo : bvos) {
        crownoSet.add(bvo.getCrowno());
      }
     /* if (crownoSet.size() == bvos.length) {
        continue;
      }*/

      String sZeroRowno = new Double(0).toString();

      for (DeliveryBVO bvo : bvos) {
        bvo.setCrowno(sZeroRowno);
      }
      SORowNoUtil.fillupRowNo(vo);
    }
  }

  private void addRewriteRule() {
    if (this.reSrc.size() == 0) {
      return;
    }
    DeliveryViewVO[] views = new DeliveryViewVO[this.reSrc.size()];
    this.reSrc.toArray(views);
    RewriteSrcRule rewrite = new RewriteSrcRule();
    rewrite.rewriteSrc(views);
  }

  /**
   * 安排处理需要在保存前进行分单合单，根据vo对照上的分单条件进行分单合单
   * 
   * @param bill
   * @return
   */
  private DeliveryVO[] billSplitCombine(DeliveryVO[] bill) {
    // 转化view
    BillToViewConvertor<DeliveryVO, DeliveryViewVO> convertor =
        new BillToViewConvertor<DeliveryVO, DeliveryViewVO>(
            DeliveryViewVO.class);
    DeliveryViewVO[] retViews = convertor.convert(bill);
    DeliveryViewVO[] views = this.getCombineViews(retViews);
    if (null == views) {
      return null;
    }
    int len = views.length;
    SplitParameter[] splitParameters = new SplitParameter[len];
    for (int i = 0; i < len; i++) {
      DeliveryHVO head = views[i].getHead();
      DeliveryBVO body = views[i].getItem();
      splitParameters[i] = new SplitParameter();
      DeliveryVO svo = new DeliveryVO();
      svo.setParentVO(head);
      svo.setChildrenVO(new DeliveryBVO[] {
        body
      });
      splitParameters[i].setData(svo);
      splitParameters[i].setSrcTrantype(body.getVsrctrantype());
      splitParameters[i].setDestTrantype(head.getCtrantypeid());
    }
    // 调用公共方法根据vo对照上的分单条件进行分单合单
    String pk_group = BSContext.getInstance().getGroupID();
    DeliveryVO[] vos =
        (DeliveryVO[]) nc.vo.pubapp.bill.pf.BillSplitUtils.splitBills(
            SOBillType.Order.getCode(), SOBillType.Delivery.getCode(),
            splitParameters, pk_group);
    return vos;
  }

  private DeliveryViewVO[] getCombineViews(DeliveryViewVO[] retViews) {
    this.reSrc = new HashSet<DeliveryViewVO>();
    Set<DeliveryViewVO> tempSet = new HashSet<DeliveryViewVO>();
    for (DeliveryViewVO view : retViews) {
      UFDouble num = view.getItem().getNnum();
      UFBoolean bclosesrc = view.getItem().getBclosesrcflag();
      boolean expr1 = null == num || UFDouble.ZERO_DBL.compareTo(num) == 0;
      boolean expr2 = null != bclosesrc && bclosesrc.booleanValue();
      if (expr1 && expr2) {
        this.reSrc.add(view);
        continue;
      }
      tempSet.add(view);
    }
    if (tempSet.size() == 0) {
      return null;
    }
    DeliveryViewVO[] views = new DeliveryViewVO[tempSet.size()];
    return tempSet.toArray(views);
  }

  private DeliveryVO[] getDeliveryvos(SOParameterVO paravo) {
    DeliveryVO[] vos = (DeliveryVO[]) paravo.getVos();
    return this.billSplitCombine(vos);
  }
}
