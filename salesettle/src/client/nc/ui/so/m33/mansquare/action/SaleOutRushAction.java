package nc.ui.so.m33.mansquare.action;

import java.awt.event.ActionEvent;
import java.util.ArrayList;
import java.util.List;

import nc.bs.framework.common.NCLocator;
import nc.pubitf.so.m33.ic.m4c.ISquareAcionFor4C;
import nc.ui.pubapp.billref.src.action.SuperAction;
import nc.ui.pubapp.uif2app.query2.model.IRefQueryService;
import nc.ui.querytemplate.querytree.IQueryScheme;
import nc.ui.scmpub.action.SCMActionInitializer;
import nc.vo.ic.m4c.entity.SaleOutBodyVO;
import nc.vo.ic.m4c.entity.SaleOutVO;
import nc.vo.pub.AggregatedValueObject;
import nc.vo.pub.lang.UFDouble;
import nc.vo.pubapp.pattern.exception.ExceptionUtils;
import nc.vo.pubapp.pattern.pub.MathTool;
import nc.vo.scmpub.res.SCMActionCode;
import nc.vo.trade.checkrule.VOChecker;

/**
 * 出库对冲
 * 销售发票参照销售出库单转单界面上使用
 * 
 * @since 6.0
 * @version 2010-12-7 上午10:38:00
 * @author zhangcheng
 */

public class SaleOutRushAction extends SuperAction {

  private static final long serialVersionUID = -4995201754887027841L;

  public SaleOutRushAction() {
    super();
    SCMActionInitializer.initializeAction(this, SCMActionCode.IC_SALEOUTRUSH);
  }

  @Override
  public void doAction(ActionEvent e) throws Exception {
    SaleOutVO[] selectVOs =
        (SaleOutVO[]) this.getRefContext().getRefBill().getSelectVOs();
    if (VOChecker.isEmpty(selectVOs)) {
      ExceptionUtils
          .wrappBusinessException(nc.vo.ml.NCLangRes4VoTransl.getNCLangRes()
              .getStrByID("4006010_0", "04006010-0005")/*@res "请先选择需要对冲的单据!"*/);
      return;
    }
    // 蓝字单据表体ID
    List<String> blueBids = new ArrayList<String>();
    // 红字单据表体ID
    List<String> redBids = new ArrayList<String>();
    for (SaleOutVO vo : selectVOs) {
      this.splitForBlueAndRed(vo, blueBids, redBids);
    }
    if (VOChecker.isEmpty(blueBids) || VOChecker.isEmpty(redBids)) {
      ExceptionUtils
          .wrappBusinessException(nc.vo.ml.NCLangRes4VoTransl.getNCLangRes()
              .getStrByID("4006010_0", "04006010-0006")/*@res "对冲的单据不能只有蓝字或红字!"*/);
      return;
    }
    ISquareAcionFor4C rush =
        NCLocator.getInstance().lookup(ISquareAcionFor4C.class);
    rush.outRush(blueBids.toArray(new String[blueBids.size()]),
        redBids.toArray(new String[redBids.size()]));

    // 对冲完成后刷新转单界面
    IRefQueryService refQueryService =
        (IRefQueryService) this.getRefContext().getRefInfo().getQueryService();
    IQueryScheme queryScheme =
        this.getRefContext().getBillReferQuery().getQueryScheme();
    Object[] billvos = refQueryService.queryByQueryScheme(queryScheme);
    this.getRefBillModel().setBillVOs((AggregatedValueObject[]) billvos);
  }

  /**
   * 从选择的单据中过滤出红蓝单据的bid
   * 
   * @param vo
   * @param blueBids 蓝字bid
   * @param redBids 红字bid
   */
  private void splitForBlueAndRed(SaleOutVO vo, List<String> blueBids,
      List<String> redBids) {
    if (VOChecker.isEmpty(vo)) {
      return;
    }
    SaleOutBodyVO[] bodys = vo.getBodys();
    if (VOChecker.isEmpty(bodys)) {
      return;
    }
    for (SaleOutBodyVO body : bodys) {
      // 多次对冲时,body可能为空
      if (VOChecker.isEmpty(body)) {
        continue;
      }
      if (MathTool.greaterThan(body.getNnum(), UFDouble.ZERO_DBL)) {
        blueBids.add(body.getCgeneralbid());
      }
      else {
        redBids.add(body.getCgeneralbid());
      }
    }
  }

}
