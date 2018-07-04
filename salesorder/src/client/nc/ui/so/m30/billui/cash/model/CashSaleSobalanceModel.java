package nc.ui.so.m30.billui.cash.model;

import java.util.HashMap;
import java.util.Map;

import nc.ui.pubapp.uif2app.model.BatchBillTableModel;
import nc.vo.so.m30.sobalance.entity.SoBalanceViewVO;

public class CashSaleSobalanceModel extends BatchBillTableModel {
  private Map<String, SoBalanceViewVO> soBalanceViewVOMap;

  @Override
  public void initModel(Object data) {
    super.initModel(data);

    if (data != null) {
      // 缓存最初的数据
      SoBalanceViewVO[] soBalanceViewVOs = (SoBalanceViewVO[]) data;
      for (SoBalanceViewVO view : soBalanceViewVOs) {
        this.getSoBalanceViewVOMap().put(view.getBody().getCsobalancebid(),
            view);
      }
    }
  }

  public Map<String, SoBalanceViewVO> getSoBalanceViewVOMap() {
    if (this.soBalanceViewVOMap == null) {
      this.soBalanceViewVOMap = new HashMap<String, SoBalanceViewVO>();
    }
    return this.soBalanceViewVOMap;
  }

  public void setSoBalanceViewVOMap(
      Map<String, SoBalanceViewVO> soBalanceViewVOMap) {
    this.soBalanceViewVOMap = soBalanceViewVOMap;
  }
}
