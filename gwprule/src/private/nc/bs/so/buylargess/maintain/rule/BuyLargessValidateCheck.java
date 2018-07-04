package nc.bs.so.buylargess.maintain.rule;

import nc.bs.ml.NCLangResOnserver;
import nc.impl.pubapp.pattern.rule.IRule;
import nc.vo.pub.VOStatus;
import nc.vo.pub.lang.UFDate;
import nc.vo.pub.lang.UFDouble;
import nc.vo.pubapp.pattern.exception.ExceptionUtils;
import nc.vo.pubapp.pattern.pub.PubAppTool;
import nc.vo.so.mbuylargess.entity.BuyLargessBVO;
import nc.vo.so.mbuylargess.entity.BuyLargessHVO;
import nc.vo.so.mbuylargess.entity.BuyLargessVO;
import nc.vo.so.pub.util.BaseSaleClassUtil;
import nc.vo.trade.checkrule.VOChecker;

/**
 * @description
 * 买赠设置保存时数据合法性校验包括非空校验和业务校验
 * @scene
 * 买赠设置保存前规则
 * @param
 * 无
 * @since 6.3
 * @version 1.0 2010-1-12
 * @author 祝会征
 */
public class BuyLargessValidateCheck implements IRule<BuyLargessVO> {

  @Override
  public void process(BuyLargessVO[] bills) {
    for (BuyLargessVO bill : bills) {
      this.process(bill);
    }
  }

  private void checkBody(BuyLargessBVO[] bodys) {
    if (null == bodys || bodys.length == 0) {
      return;
    }
    int length = bodys.length;
    for (int i = 0; i < length; i++) {
      BuyLargessBVO body = bodys[i];
      
      if (VOStatus.DELETED == body.getStatus()) {
        continue;
      }
      if (VOChecker.isEmpty(body.getNnum())
          || body.getNnum().compareTo(new UFDouble(0)) <= 0) {
        ExceptionUtils
            .wrappBusinessException(nc.vo.ml.NCLangRes4VoTransl.getNCLangRes()
                .getStrByID("4006003_0", "04006003-0004")/*@res "赠送数量不能为空，必须大于0。"*/);
      }
      if (VOChecker.isEmpty(body.getDbegdate())) {
        ExceptionUtils
            .wrappBusinessException(nc.vo.ml.NCLangRes4VoTransl.getNCLangRes()
                .getStrByID("4006003_0", "04006003-0005")/*@res "开始日期不能为空。"*/);
      }
      if (VOChecker.isEmpty(body.getDenddate())) {
        ExceptionUtils
            .wrappBusinessException(nc.vo.ml.NCLangRes4VoTransl.getNCLangRes()
                .getStrByID("4006003_0", "04006003-0006")/*@res "截止日期不能为空。"*/);
      }
      if (!VOChecker.isEmpty(body.getDbegdate())
          && !VOChecker.isEmpty(body.getDenddate())
          && body.getDbegdate().compareTo(body.getDenddate()) > 0) {
        ExceptionUtils
            .wrappBusinessException(nc.vo.ml.NCLangRes4VoTransl.getNCLangRes()
                .getStrByID("4006003_0", "04006003-0007")/*@res "截止日期不能小于开始日期。"*/);
      }
      if (!VOChecker.isEmpty(body.getFtoplimittype())
          && body.getFtoplimittype().intValue() != 2
          && VOChecker.isEmpty(body.getNtoplimitvalue())) {
        ExceptionUtils
            .wrappBusinessException(nc.vo.ml.NCLangRes4VoTransl.getNCLangRes()
                .getStrByID("4006003_0", "04006003-0008")/*@res "上限值不能为空。"*/);
      }
      if (!VOChecker.isEmpty(body.getFtoplimittype())
          && body.getFtoplimittype().intValue() == 1
          && VOChecker.isEmpty(body.getNprice())) {
        ExceptionUtils
            .wrappBusinessException(nc.vo.ml.NCLangRes4VoTransl.getNCLangRes()
                .getStrByID("4006003_0", "04006003-0009")/*@res "上限类型为金额，单价不能为空。"*/);
      }
      this.checkDate(body, i, bodys);
    }
  }

  private void checkDate(BuyLargessBVO body, int i, BuyLargessBVO[] bodys) {
    for (int j = 0; j < i; j++) {
      if (VOStatus.DELETED == bodys[j].getStatus()) {
        continue;
      }
      
   
      if (body.getPk_material().equals(bodys[j].getPk_material())) {
        if (this.isAfter(body.getDenddate(), bodys[j].getDbegdate())
            && this.isAfter(bodys[j].getDenddate(), body.getDbegdate())) {
          ExceptionUtils
              .wrappBusinessException(nc.vo.ml.NCLangRes4VoTransl
                  .getNCLangRes().getStrByID("4006003_0", "04006003-0010")/*@res "相同赠品物料，赠品时间段有交叉重叠，请重新输入。"*/);
        }
      }
    }
  }

  private void checkHead(BuyLargessHVO head, boolean isbas) {
    StringBuilder errmsg = new StringBuilder();
    if (isbas) {
      if (VOChecker.isEmpty(head.getCbuymarid())
          && VOChecker.isEmpty(head.getPk_marbasclass())) {
        errmsg.append(NCLangResOnserver.getInstance().getStrByID("4006003_0",
            "04006003-0022")/*物料编码和物料分类不能同时为空.*/);
        errmsg.append("\n");
      }

    }
    if (!isbas) {
      if (VOChecker.isEmpty(head.getCbuymarid())
          && VOChecker.isEmpty(head.getPk_marsaleclass())) {
        errmsg.append(NCLangResOnserver.getInstance().getStrByID("4006003_0",
            "04006003-0024")/*物料编码和物料销售分类不能同时为空.*/);
        errmsg.append("\n");
      }
    }
    // 单位
    if (PubAppTool.isNull(head.getCbuyunitid())) {
      errmsg.append(NCLangResOnserver.getInstance().getStrByID("4006003_0",
          "04006003-0025")/*单位不能为空.*/);
      errmsg.append("\n");
    }
    // 购买数量
    if (VOChecker.isEmpty(head.getNbuynum())
        || head.getNbuynum().compareTo(new UFDouble(0)) <= 0) {
      errmsg.append(NCLangResOnserver.getInstance().getStrByID("4006003_0",
          "04006003-0026")/*购买数量不能为空，必须大于0.*/);
      errmsg.append("\n");
    }
    if (errmsg.length() > 0) {
      ExceptionUtils.wrappBusinessException(errmsg.toString());
    }
  }

  /**
   * 方法功能描述：判断日期date1和date2先后顺序：若date1或date2有一为空返回true；否则返回date1是否晚于date2。
   * <b>参数说明</b>
   * 
   * @param date1
   * @param date2
   * @return
   * @author fengjb
   * @time 2009-6-4 下午07:22:48
   */
  private boolean isAfter(UFDate date1, UFDate date2) {
    if (null == date1 || null == date2) {
      return true;
    }
    return date1.after(date2) || date1.equals(date2);
  }

  private void process(BuyLargessVO bill) {
    // 表头数据合法性校验
    BuyLargessHVO head = bill.getParentVO();
    String pk_group = head.getPk_group();
    // 物料、物料分类不能同时为空
    boolean isbas = BaseSaleClassUtil.isMarBaseClass(pk_group);
    this.checkHead(head, isbas);
    BuyLargessBVO[] bodys = bill.getChildrenVO();
    this.checkBody(bodys);
  }

}
