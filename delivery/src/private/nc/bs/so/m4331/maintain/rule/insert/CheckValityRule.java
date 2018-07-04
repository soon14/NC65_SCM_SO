package nc.bs.so.m4331.maintain.rule.insert;

import java.util.ArrayList;
import java.util.List;

import nc.bs.ml.NCLangResOnserver;
import nc.bs.so.pub.rule.SOProfitCenterUtil;
import nc.impl.pubapp.pattern.rule.IRule;
import nc.vo.pub.VOStatus;
import nc.vo.pub.lang.UFDouble;
import nc.vo.pubapp.pattern.exception.ExceptionUtils;
import nc.vo.pubapp.pattern.pub.MathTool;
import nc.vo.scmpub.res.billtype.TOBillType;
import nc.vo.so.m4331.entity.DeliveryBVO;
import nc.vo.so.m4331.entity.DeliveryHVO;
import nc.vo.so.m4331.entity.DeliveryVO;
import nc.vo.trade.checkrule.VOChecker;

/**
 * @description
 * 销售发货单保存前数据合法性校验
 * 1.表头合法性校验、表体数据合法性校验
 * 2.来源调拨订单，表体国家、税码、税率等字段可空
 * 3.检查数值的合法性
 * @scene
 * 销售发货单新增、修改保存前
 * @param
 * 无
 */
public class CheckValityRule implements IRule<DeliveryVO> {

  @Override
  public void process(DeliveryVO[] invoices) {

    for (DeliveryVO vo : invoices) {
      // 表头合法性校验
      this.checkHeadValidity(vo.getParentVO());
      // 表体数据合法性校验
      this.checkBodyValidity(vo.getChildrenVO());
      // 来源调拨订单，表体国家、税码、税率等字段可空
      this.checkVAT(vo.getChildrenVO());
      // 检查数值的合法性
      this.checkValue(vo);
      SOProfitCenterUtil.checkProfitCenterValue(vo);
    }

  }

  private void checkBodyValidity(DeliveryBVO[] childrenVOs) {
    List<String> errField = new ArrayList<String>();
    for (DeliveryBVO bvo : childrenVOs) {
      // 过滤删除行
      if (bvo.getStatus() == VOStatus.DELETED) {
        continue;
      }
      if (VOChecker.isEmpty(bvo.getCmaterialid())) {
        errField.add(NCLangResOnserver.getInstance().getStrByID("4006002_0", "04006002-0118")/*物料*/);
      }
      if (VOChecker.isEmpty(bvo.getCastunitid())) {
        errField.add(NCLangResOnserver.getInstance().getStrByID("4006002_0", "04006002-0119")/*单位*/);
      }
      if (VOChecker.isEmpty(bvo.getNnum())) {
        errField.add(NCLangResOnserver.getInstance().getStrByID("4006002_0", "04006002-0120")/*数量*/);
      }
      if (VOChecker.isEmpty(bvo.getCunitid())) {
        errField.add(NCLangResOnserver.getInstance().getStrByID("4006002_0", "04006002-0121")/*主单位*/);
      }
      if (errField.size() > 0) {
        StringBuilder errMsg =
            new StringBuilder(NCLangResOnserver.getInstance().getStrByID("4006002_0", "04006002-0122", null, new String[]{bvo.getCrowno()})/*发货单第[{0}]行下列字段不能为空：*/);
        errMsg.append(errField.get(0));
        for (int i = 1; i < errField.size(); i++) {
          errMsg.append(NCLangResOnserver.getInstance().getStrByID("4006002_0", "04006002-0123")/*、*/).append(errField.get(i));
        }
        ExceptionUtils.wrappBusinessException(errMsg.toString());
      }
    }
  }

  private void checkVAT(DeliveryBVO[] childrenVOs) {
	  List<String> errField = new ArrayList<String>();
	    for (DeliveryBVO bvo : childrenVOs) {
	      // 过滤删除行
	      if (bvo.getStatus() == VOStatus.DELETED || TOBillType.TransOrder.getCode().equals(bvo.getVsrctype())) {
	        continue;
	      }
	      if (VOChecker.isEmpty(bvo.getCsendcountryid())) {
	        errField.add(NCLangResOnserver.getInstance().getStrByID("4006002_0", "04006002-0179")/*收货国家/地区*/);
	      }
	      if (VOChecker.isEmpty(bvo.getCrececountryid())) {
	        errField.add(NCLangResOnserver.getInstance().getStrByID("4006002_0", "04006002-0180")/*发货国家/地区*/);
	      }
	      if (VOChecker.isEmpty(bvo.getCtaxcountryid())) {
	        errField.add(NCLangResOnserver.getInstance().getStrByID("4006002_0", "04006002-0181")/*报税国家/地区*/);
	      }
	      if (VOChecker.isEmpty(bvo.getFbuysellflag())) {
	        errField.add(NCLangResOnserver.getInstance().getStrByID("4006002_0", "04006002-0182")/*购销类型*/);
	      }
	      if (VOChecker.isEmpty(bvo.getBtriatradeflag())) {
		        errField.add(NCLangResOnserver.getInstance().getStrByID("4006002_0", "04006002-0183")/*三角贸易*/);
		  }
	      if (VOChecker.isEmpty(bvo.getCtaxcodeid())) {
		        errField.add(NCLangResOnserver.getInstance().getStrByID("4006002_0", "04006002-0184")/*税码*/);
		  }
	      if (VOChecker.isEmpty(bvo.getFtaxtypeflag())) {
		        errField.add(NCLangResOnserver.getInstance().getStrByID("4006002_0", "04006002-0185")/*扣税类别*/);
		  }
	      if (VOChecker.isEmpty(bvo.getNcaltaxmny())) {
		        errField.add(NCLangResOnserver.getInstance().getStrByID("4006002_0", "04006002-0186")/*计税金额*/);
		  }
	      if (errField.size() > 0) {
	        StringBuilder errMsg =
	            new StringBuilder(NCLangResOnserver.getInstance().getStrByID("4006002_0", "04006002-0122", null, new String[]{bvo.getCrowno()})/*发货单第[{0}]行下列字段不能为空：*/);
	        errMsg.append(errField.get(0));
	        for (int i = 1; i < errField.size(); i++) {
	          errMsg.append(NCLangResOnserver.getInstance().getStrByID("4006002_0", "04006002-0123")/*、*/).append(errField.get(i));
	        }
	        ExceptionUtils.wrappBusinessException(errMsg.toString());
	      }
	    }
  }
  
  
  private void checkHeadValidity(DeliveryHVO head) {
    List<String> errField = new ArrayList<String>();

    if (VOChecker.isEmpty(head.getPk_org())) {
      errField.add(NCLangResOnserver.getInstance().getStrByID("4006002_0", "04006002-0114")/*物流组织*/);
    }
    if (VOChecker.isEmpty(head.getDbilldate())) {
      errField.add(NCLangResOnserver.getInstance().getStrByID("4006002_0", "04006002-0124")/*单据日期*/);
    }
    if (VOChecker.isEmpty(head.getCtrantypeid())) {
      errField.add(NCLangResOnserver.getInstance().getStrByID("4006002_0", "04006002-0125")/*发货类型*/);
    }
    if (VOChecker.isEmpty(head.getCbiztypeid())) {
      errField.add(NCLangResOnserver.getInstance().getStrByID("4006002_0", "04006002-0126")/*业务类型*/);
    }
    if (errField.size() > 0) {
      StringBuilder errMsg = new StringBuilder(NCLangResOnserver.getInstance().getStrByID("4006002_0", "04006002-0127")/*发货单表头下列字段不能为空：*/);
      errMsg.append(errField.get(0));
      for (int i = 1; i < errField.size(); i++) {
        errMsg.append(NCLangResOnserver.getInstance().getStrByID("4006002_0", "04006002-0123")/*、*/).append(errField.get(i));
      }
      ExceptionUtils.wrappBusinessException(errMsg.toString());
    }
  }

  private void checkValue(DeliveryVO vo) {
    StringBuffer errMsg = new StringBuffer();
    for (DeliveryBVO bvo : vo.getChildrenVO()) {
      if (MathTool.compareTo(UFDouble.ZERO_DBL, bvo.getNnum()) == 0) {
        errMsg.append(NCLangResOnserver.getInstance().getStrByID("4006002_0", "04006002-0128", null, new String[]{bvo.getCrowno()})/*发货单第[{0}]行的主数量不能为0*/);
      }
      UFDouble exchangerate = bvo.getNexchangerate();
      if (MathTool.compareTo(exchangerate, UFDouble.ZERO_DBL) <= 0) {
        ExceptionUtils.wrappBusinessException(nc.vo.ml.NCLangRes4VoTransl.getNCLangRes().getStrByID("4006002_0","04006002-0041")/*@res "组织折本汇率不能小于等于0！"*/);
      }
    }
    if (errMsg.length() > 0) {
      ExceptionUtils.wrappBusinessException(errMsg.toString());
    }
  }
}