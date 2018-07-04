package nc.ui.so.m30.arrange.action;

import java.awt.event.ActionEvent;

import nc.pubitf.initgroup.InitGroupQuery;
import nc.ui.pubapp.billref.push.OpenNodePushActionAdapter;
import nc.vo.pubapp.AppContext;
import nc.vo.pubapp.pattern.exception.ExceptionUtils;
import nc.vo.pubapp.res.NCModule;

public class OpenNodePushMMActionAdapter extends OpenNodePushActionAdapter {

  @Override
  public void doAction(ActionEvent e) throws Exception {
    // 安排生产的时候，有可能是离散生产订单或流程生产订单
    boolean isEnable =
        InitGroupQuery.isEnabled(AppContext.getInstance().getPkGroup(),
            NCModule.MMPPAC.getCode())
            || InitGroupQuery.isEnabled(AppContext.getInstance().getPkGroup(),
                NCModule.MMDPAC.getCode());
    if (!isEnable) {
      ExceptionUtils
          .wrappBusinessException(nc.vo.ml.NCLangRes4VoTransl.getNCLangRes()
              .getStrByID("pubapp_0", "0pubapp-0305")/* @res "模块未启用。" */);
    }
    super.doAction(e);
  }
}
