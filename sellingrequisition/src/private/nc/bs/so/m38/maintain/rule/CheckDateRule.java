package nc.bs.so.m38.maintain.rule;

import nc.bs.ml.NCLangResOnserver;
import nc.impl.pubapp.pattern.rule.IRule;
import nc.vo.pub.VOStatus;
import nc.vo.pub.lang.UFDate;
import nc.vo.pubapp.pattern.exception.ExceptionUtils;
import nc.vo.so.m38.entity.PreOrderBVO;
import nc.vo.so.m38.entity.PreOrderHVO;
import nc.vo.so.m38.entity.PreOrderVO;

/**
 * @description
 * 预定单保存前日期检查
 * @scene
 * 销售预订单新增、修改保存前
 * @param
 * 无
 */
public class CheckDateRule implements IRule<PreOrderVO> {

  @Override
  public void process(PreOrderVO[] vos) {
    for (PreOrderVO vo : vos) {
      this.checkDate(vo);
    }
  }

  /**
   * 校验单据上日期合法性
   * 
   * @param vo
   */
  private void checkDate(PreOrderVO vo) {
    PreOrderHVO head = vo.getParentVO();
    UFDate dbilldate = head.getDbilldate();
    UFDate dabdatedate = head.getDabatedate();

    if (dabdatedate.before(dbilldate)) {
      ExceptionUtils
          .wrappBusinessException(nc.vo.ml.NCLangRes4VoTransl.getNCLangRes()
              .getStrByID("4006012_0", "04006012-0020")/* @res
                                                        * "<失效日期>不可小于<订货日期>" */);
    }
    PreOrderBVO[] bodys = vo.getChildrenVO();
    if (null == bodys || bodys.length == 0) {
      return;
    }
    StringBuilder sendrow = new StringBuilder();
    StringBuilder recerow = new StringBuilder();
    for (PreOrderBVO bvo : bodys) {
      if (VOStatus.DELETED == bvo.getStatus()
          || VOStatus.UNCHANGED == bvo.getStatus()) {
        continue;
      }
      UFDate senddate = bvo.getDsenddate();
      if (senddate.before(dbilldate)) {
        sendrow.append(NCLangResOnserver.getInstance().getStrByID("4006012_0",
            "04006012-000004", null, new String[] {
              bvo.getCrowno()
            })/* [{0}]、 */);
      }
      UFDate receivedate = bvo.getDreceivedate();
      if (receivedate.before(senddate)) {
        recerow.append(NCLangResOnserver.getInstance().getStrByID("4006012_0",
            "04006012-000004", null, new String[] {
              bvo.getCrowno()
            })/* [{0}]、 */);
      }
    }
    if (sendrow.length() > 0) {
      sendrow.deleteCharAt(sendrow.length() - 1);
      ExceptionUtils.wrappBusinessException(NCLangResOnserver.getInstance()
          .getStrByID("4006012_0", "04006012-000005", null, new String[] {
            sendrow.toString()
          })/* 下列行：{0}　计划发货日期不能早于订货日期 */);
    }
    if (recerow.length() > 0) {
      recerow.deleteCharAt(recerow.length() - 1);
      ExceptionUtils.wrappBusinessException(NCLangResOnserver.getInstance()
          .getStrByID("4006012_0", "04006012-000006", null, new String[] {
            recerow.toString()
          })/* 下列行：{0}　要求到货日期不能早于计划发货日期 */);
    }
  }

}
