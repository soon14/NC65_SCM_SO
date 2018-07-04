package nc.ui.so.m30.billui.cash.model;

import java.util.HashMap;
import java.util.Map;

import nc.ui.pubapp.uif2app.model.BatchBillTableModel;
import nc.vo.so.m35.entity.ArsubViewVO;
import nc.vo.so.m35.paravo.OffsetParaVO;

public class CashSaleArsubModel extends BatchBillTableModel {

  private Map<Integer, OffsetParaVO> offsetParaMap;

  private Map<String, ArsubViewVO> arsubViewVOMap;

  public Map<Integer, OffsetParaVO> getOffsetParaMap() {
    return this.offsetParaMap;
  }

  public void setOffsetParaMap(Map<Integer, OffsetParaVO> offsetParaMap) {
    this.offsetParaMap = offsetParaMap;
  }

  public Map<String, ArsubViewVO> getArsubViewVOMap() {
    if (this.arsubViewVOMap == null) {
      this.arsubViewVOMap = new HashMap<String, ArsubViewVO>();
    }
    return this.arsubViewVOMap;
  }

  @Override
  public void initModel(Object data) {
    super.initModel(data);

    if (data != null) {
      // 缓存最初的数据
      ArsubViewVO[] arsubviewvos = (ArsubViewVO[]) data;
      for (ArsubViewVO view : arsubviewvos) {
        this.getArsubViewVOMap().put(view.getItem().getCarsubbid(), view);
      }
    }
  }
}
