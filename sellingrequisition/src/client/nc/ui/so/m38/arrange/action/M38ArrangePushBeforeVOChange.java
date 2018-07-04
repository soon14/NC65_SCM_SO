package nc.ui.so.m38.arrange.action;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;

import nc.ui.pub.bill.BillListPanel;
import nc.ui.pub.bill.BillModel;
import nc.ui.pubapp.billref.push.IPushBeforeVOChange;
import nc.vo.pub.lang.UFBoolean;
import nc.vo.pub.lang.UFDouble;
import nc.vo.pubapp.pattern.data.ValueUtils;
import nc.vo.pubapp.pattern.model.entity.view.AbstractDataView;
import nc.vo.pubapp.pattern.pub.MathTool;
import nc.vo.pubapp.pattern.pub.PubAppTool;
import nc.vo.so.m30.entity.SaleOrderBVO;
import nc.vo.so.m38.entity.PreOrderViewVO;

public class M38ArrangePushBeforeVOChange implements IPushBeforeVOChange {

  // 不能安排的行
  private List<Integer> listnoaryrow;

  @Override
  public AbstractDataView[] beforeVOChange(AbstractDataView[] srcVos,
      BillListPanel destPanel) {
    // 循环取下游累计安排数量
    Map<String, UFDouble> maparnum = new HashMap<String, UFDouble>();
    Set<String> setclose = new HashSet<String>();
    BillModel model = destPanel.getBodyBillModel();
    int bodycount = model.getRowCount();
    for (int i = 0; i < bodycount; i++) {
      String srcbid = (String) model.getValueAt(i, SaleOrderBVO.CSRCBID);

      Object objvalue = model.getValueAt(i, SaleOrderBVO.BPREROWCLOSEFLAG);
      UFBoolean bpreclose = ValueUtils.getUFBoolean(objvalue);
      if (null != bpreclose && bpreclose.booleanValue()) {
        setclose.add(srcbid);
        continue;
      }
      UFDouble curarnum = (UFDouble) model.getValueAt(i, SaleOrderBVO.NNUM);
      if (maparnum.containsKey(srcbid)) {
        UFDouble newarnum = MathTool.add(curarnum, maparnum.get(srcbid));
        maparnum.put(srcbid, newarnum);
      }
      else {
        maparnum.put(srcbid, curarnum);
      }
    }
    PreOrderViewVO[] srcpreordervies = (PreOrderViewVO[]) srcVos;
    this.listnoaryrow = new ArrayList<Integer>();
    List<PreOrderViewVO> retList = new ArrayList<PreOrderViewVO>();
    int i = 0;
    for (PreOrderViewVO view : srcpreordervies) {
      i++;
      String preorderbid = view.getItem().getCpreorderbid();
      // 　BOM行的bid为空
      if (PubAppTool.isNull(preorderbid)) {
        continue;
      }
      // 行关闭的
      UFBoolean lineclose = view.getItem().getBlineclose();
      if (setclose.contains(preorderbid)
          || (null != lineclose && lineclose.booleanValue())) {
        this.listnoaryrow.add(Integer.valueOf(i));
        continue;
      }
      // 累计安排数量 >= 预订单数量
      UFDouble nnum = view.getItem().getNnum();
      UFDouble narrnum = view.getItem().getNarrnum();
      if (maparnum.containsKey(preorderbid)) {
        narrnum = MathTool.add(narrnum, maparnum.get(preorderbid));
      }
      if (MathTool.absCompareTo(nnum, narrnum) <= 0) {
        this.listnoaryrow.add(Integer.valueOf(i));
        continue;
      }
      view.getItem().setNarrnum(narrnum);
      retList.add(view);
    }
    PreOrderViewVO[] arviews = new PreOrderViewVO[retList.size()];
    retList.toArray(arviews);
    return arviews;
  }

  public List<Integer> getNoArrangeRowList() {
    return this.listnoaryrow;
  }
}
