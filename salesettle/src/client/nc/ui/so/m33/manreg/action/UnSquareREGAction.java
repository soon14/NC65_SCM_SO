package nc.ui.so.m33.manreg.action;

import java.awt.event.ActionEvent;

import nc.bs.framework.common.NCLocator;
import nc.itf.so.m33.maintain.m4c.ISaleOutREGMaintain;
import nc.ui.pubapp.uif2app.view.ShowUpableBillListView;
import nc.ui.scmpub.action.SCMActionInitializer;
import nc.ui.so.m33.pub.SquareUIUtils;
import nc.ui.uif2.NCAction;
import nc.ui.uif2.ShowStatusBarMsgUtil;
import nc.ui.uif2.model.AbstractAppModel;
import nc.vo.pubapp.pattern.exception.ExceptionUtils;
import nc.vo.scmpub.res.SCMActionCode;
import nc.vo.so.m33.m4c.entity.SquareOutViewVO;
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
public class UnSquareREGAction extends NCAction {

  private static final long serialVersionUID = 3886582906408885888L;

  private ShowUpableBillListView listView;

  private AbstractAppModel model;

  public UnSquareREGAction() {
    SCMActionInitializer.initializeAction(this, SCMActionCode.SO_UNREGISTER);
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
    ISaleOutREGMaintain bo =
        NCLocator.getInstance().lookup(ISaleOutREGMaintain.class);
    bo.manualUnRegister(vos);

    // 刷新界面的显示
    SquareUIUtils.deleteVoAfterAction(this.listView);

    // 显示成功信息
    ShowStatusBarMsgUtil
        .showStatusBarMsg(nc.vo.ml.NCLangRes4VoTransl.getNCLangRes()
            .getStrByID("4006010_0", "04006010-0004")/*@res "取消发出商品成功！"*/,
            this.getModel().getContext());
  }

  public ShowUpableBillListView getListView() {
    return this.listView;
  }

  public AbstractAppModel getModel() {
    return this.model;
  }

  public void setListView(ShowUpableBillListView listView1) {
    this.listView = listView1;
  }

  public void setModel(AbstractAppModel model1) {
    this.model = model1;
    this.model.addAppEventListener(this);
  }

  @Override
  protected boolean isActionEnable() {
    boolean flag = false;
    Object[] datas =
        this.getListView().getBillListPanel().getBodyBillModel()
            .getBodySelectedVOs(SquareOutViewVO.class.getName());
    if (datas != null && datas.length > 0) {
      if (!VOChecker.isEmpty(datas[0])) {
        SquareOutViewVO svo = (SquareOutViewVO) datas[0];
        flag = svo.ifCanCancelREG();
      }
    }
    return flag;
  }
}
