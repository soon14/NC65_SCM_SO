package nc.ui.so.m38.arrange.pub;

import nc.vo.querytemplate.queryscheme.SimpleQuerySchemeVO;

import nc.funcnode.ui.FuncletInitData;

import nc.ui.pubapp.billref.push.BillContext;
import nc.ui.pubapp.billref.push.IBillPush;
import nc.ui.pubapp.billref.push.PushQueryAction;
import nc.ui.pubapp.uif2app.model.DefaultFuncNodeInitDataListener;

/**
 * 预订单安排initData监听
 * 
 * @since 6.1
 * @version 2013-07-06 08:54:01
 * @author yixl
 */
public class M38ArrangeInitDataListener extends DefaultFuncNodeInitDataListener
    implements IBillPush {

  private BillContext billContext = null;

  @Override
  public void initData(FuncletInitData data) {
    // 目前只处理快捷方式通过查询方案进行查询的情况
    if (data != null && data.getInitData() instanceof SimpleQuerySchemeVO) {
      PushQueryAction query = (PushQueryAction) this.getQueryAction();
      if (query.getBillContext() == null) {
        query.setBillContext(this.getBillContext());
      }
      this.doInitDataByQueryScheme(data);
    }
  }

  @Override
  public BillContext getBillContext() {
    return this.billContext;
  }

  @Override
  public void setBillContext(BillContext context) {
    this.billContext = context;
  }
}
