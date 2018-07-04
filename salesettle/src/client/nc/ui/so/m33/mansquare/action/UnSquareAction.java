package nc.ui.so.m33.mansquare.action;

import java.awt.event.ActionEvent;

import nc.bs.framework.common.NCLocator;
import nc.itf.so.m33.maintain.m4c.ISaleOutSettleMaintain;
import nc.ui.scmpub.action.SCMActionInitializer;
import nc.ui.so.m33.mansquare.view.M33BillListView;
import nc.ui.so.m33.pub.SquareUIUtils;
import nc.ui.so.m33.pub.action.SquareCreditExceptionProcess;
import nc.ui.uif2.NCAction;
import nc.ui.uif2.ShowStatusBarMsgUtil;
import nc.ui.uif2.model.AbstractAppModel;
import nc.vo.credit.exception.CreditCheckException;
import nc.vo.pubapp.pattern.exception.ExceptionUtils;
import nc.vo.scmpub.res.SCMActionCode;
import nc.vo.so.m33.m4c.entity.SquareOutViewVO;
import nc.vo.so.m33.pub.constant.QueryFlag;
import nc.vo.trade.checkrule.VOChecker;

/**
 * <p>
 * 类名
 * <p>
 * <p>
 * 描述
 * <p>
 * 
 * @author zhangcheng
 * @time 2010-4-6 下午07:31:35
 */
public class UnSquareAction extends NCAction {

  private static final long serialVersionUID = 3886582906408885888L;

  private M33BillListView listView;

  private AbstractAppModel model;

  public UnSquareAction() {
    SCMActionInitializer.initializeAction(this, SCMActionCode.SO_UNSQUARE);
  }

  @Override
  public void doAction(ActionEvent e) throws Exception {
    SquareOutViewVO[] vos = SquareUIUtils.getSelectVOs(this.listView);
    if (vos == null || vos.length == 0) {

      ExceptionUtils
          .wrappBusinessException(nc.vo.ml.NCLangRes4VoTransl.getNCLangRes()
              .getStrByID("4006010_0", "04006010-0000")/*@res "无选中行"*/);
    }

    // 手工取消结算
    ISaleOutSettleMaintain bo =
        NCLocator.getInstance().lookup(ISaleOutSettleMaintain.class);
    try {
      SquareOutViewVO[] retvos = bo.manualUnSquare(vos);
      // 刷新界面的显示
      SquareUIUtils.setVOAfterUnSquare(this.listView, retvos);
    }
    catch (Exception e2) {
      if (e2 instanceof CreditCheckException) {
        new SquareCreditExceptionProcess()
            .processCreditCheckException((CreditCheckException) e2);
      }
      else {
        ExceptionUtils.wrappException(e2);
      }
    }

    // 显示成功信息
    ShowStatusBarMsgUtil.showStatusBarMsg(
        nc.vo.ml.NCLangRes4VoTransl.getNCLangRes().getStrByID("4006010_0",
            "04006010-0010")/*@res "取消结算成功！"*/, this.getModel().getContext());
  }

  public M33BillListView getListView() {
    return this.listView;
  }

  public AbstractAppModel getModel() {
    return this.model;
  }

  public void setListView(M33BillListView listView1) {
    this.listView = listView1;
  }

  public void setModel(AbstractAppModel model1) {
    this.model = model1;
    this.model.addAppEventListener(this);
  }

  @Override
  protected boolean isActionEnable() {
    boolean flag = false;

    // 当前查询的结算类型
    int queryflag = this.getListView().getQueryFlag();
    if (queryflag == QueryFlag.UNOUTRUSHBLUE
        || queryflag == QueryFlag.UNOUTRUSHRED) {
      return flag;
    }

    Object[] datas =
        this.getListView().getBillListPanel().getBodyBillModel()
            .getBodySelectedVOs(SquareOutViewVO.class.getName());
    if (datas != null && datas.length > 0) {
      if (!VOChecker.isEmpty(datas[0])) {
        SquareOutViewVO svo = (SquareOutViewVO) datas[0];
        flag = svo.ifCanCancelSquare();
      }
    }
    return flag;
  }
}
