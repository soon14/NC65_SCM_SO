package nc.ui.so.m33.pub;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import nc.md.model.impl.MDEnum;
import nc.ui.ml.NCLangRes;
import nc.ui.pub.bill.BillListPanel;
import nc.ui.pub.bill.BillModel;
import nc.ui.pubapp.uif2app.model.BillManageModel;
import nc.ui.pubapp.uif2app.view.ShowUpableBillListView;
import nc.ui.pubapp.util.ListPanelValueUtils;
import nc.ui.so.pub.keyvalue.ListKeyValue;
import nc.ui.uif2.IShowMsgConstant;
import nc.ui.uif2.ShowStatusBarMsgUtil;
import nc.vo.pub.CircularlyAccessibleValueObject;
import nc.vo.pub.lang.UFDouble;
import nc.vo.pubapp.pattern.data.ValueUtils;
import nc.vo.pubapp.pattern.pub.MathTool;
import nc.vo.so.m33.enumeration.SquareType;
import nc.vo.so.m33.m4c.entity.SquareOutBVO;
import nc.vo.so.m33.m4c.entity.SquareOutHVO;
import nc.vo.so.m33.m4c.entity.SquareOutVOUtils;
import nc.vo.so.m33.m4c.entity.SquareOutViewVO;
import nc.vo.so.pub.enumeration.ListTemplateType;
import nc.vo.so.pub.keyvalue.IKeyValue;
import nc.vo.trade.checkrule.VOChecker;

public class SquareUIUtils {

  private SquareUIUtils() {
    super();
  }

  /**
   * 清空当前界面数据
   * 
   * @param listView
   */
  public static void clearUIData(ShowUpableBillListView listView) {
    BillListPanel billlistpanel = listView.getBillListPanel();
    billlistpanel.getBodyBillModel().clearBodyData();
  }

  /**
   * 结算完成后删除原行更新界面
   * 
   * @param listView
   */
  public static void deleteVoAfterAction(ShowUpableBillListView listView) {
    BillListPanel billlistpanel = listView.getBillListPanel();
    int[] selRows = SquareUIUtils.getSelectedRows(billlistpanel);
    billlistpanel.getBodyBillModel().delLine(selRows);
  }

  public static SquareOutViewVO[] getSelectVOs(ShowUpableBillListView listView) {
    SquareOutViewVO[] svos =
        (SquareOutViewVO[]) listView.getBillListPanel().getBodyBillModel()
            .getBodySelectedVOs(SquareOutViewVO.class.getName());
    return svos;
  }

  /**
   * 方法功能描述：如果成本结算数量或者收入结算数量==结算单数量，此VO删除
   * <p>
   * <b>参数说明</b>
   * 
   * @param listView
   * @return <p>
   * @author zhangcheng
   * @time 2010-6-10 下午08:13:48
   */
  public static SquareOutViewVO[] getSubVOs(ShowUpableBillListView listView) {
    SquareOutViewVO[] vos = SquareUIUtils.getSelectVOs(listView);
    List<SquareOutViewVO> list = new ArrayList<SquareOutViewVO>();
    UFDouble nnum;
    UFDouble totalianum;
    UFDouble totalarnum;
    int sIAType = 0;
    int sARType = 0;
    for (SquareOutViewVO svo : vos) {
      // 结算单数量
      nnum = svo.getItem().getNnum();

      // 重新设置累计结算数量
      totalianum =
          svo.getItem().getNsquareianum() == null ? UFDouble.ZERO_DBL : svo
              .getItem().getNsquareianum();
      totalarnum =
          svo.getItem().getNsquarearnum() == null ? UFDouble.ZERO_DBL : svo
              .getItem().getNsquarearnum();
      totalianum = totalianum.add(svo.getItem().getNthisnum());
      totalarnum = totalarnum.add(svo.getItem().getNthisnum());
      sIAType = svo.getItem().getFpreiatype().intValue();
      sARType = svo.getItem().getFpreartype().intValue();

      //
      if (sIAType == SquareType.SQUARETYPE_IA.getIntValue()) {
        svo.getItem().setNsquareianum(totalianum);
        if (nnum.compareTo(totalianum) == 0) {
          list.add(svo);
          continue;
        }
      }
      if (sARType == SquareType.SQUARETYPE_AR.getIntValue()) {
        svo.getItem().setNsquarearnum(totalarnum);
        if (nnum.compareTo(totalarnum) == 0) {
          list.add(svo);
          continue;
        }
      }

    }
    return list.toArray(new SquareOutViewVO[0]);
  }

  /**
   * 方法功能描述：设置已结算主数量
   * <p>
   * <b>examples:</b>
   * <p>
   * 使用示例
   * <p>
   * <b>参数说明</b>
   * 
   * @param listPanel
   *          <p>
   * @author zhangcheng
   * @time 2010-6-17 下午04:47:03
   */
  public static void setNtotalsquarenum(BillListPanel listPanel) {
    int len = listPanel.getBodyBillModel().getRowCount();
    Integer[] rows = new Integer[len];
    for (int i = 0; i < len; i++) {
      rows[i] = Integer.valueOf(i);
    }
    SquareUIUtils.setNtotalsquarenum(new ListPanelValueUtils(listPanel), rows);
  }

  /**
   * 方法功能描述：设置已结算主数量
   * <p>
   * <b>examples:</b>
   * <p>
   * 使用示例
   * <p>
   * <b>参数说明</b>
   * 
   * @param listPanel
   *          <p>
   * @author zhangcheng
   * @time 2010-6-17 下午04:47:03
   */
  public static void setNtotalsquarenum(ListPanelValueUtils lutils,
      Integer[] rows) {
    // 累计成本、应收、结算、结算单数量
    Object osquareianum;
    Object osquarearnum;
    int len = rows.length;
    for (int i = 0; i < len; i++) {
      osquareianum =
          lutils.getBodyValueAt(rows[i].intValue(), SquareOutBVO.NSQUAREIANUM);
      osquarearnum =
          lutils.getBodyValueAt(rows[i].intValue(), SquareOutBVO.NSQUAREARNUM);
      UFDouble nsquareianum = ValueUtils.getUFDouble(osquareianum);
      UFDouble nsquarearnum = ValueUtils.getUFDouble(osquarearnum);
      SquareUIUtils.setNtotalsquarenum(rows[i].intValue(), lutils,
          nsquareianum, nsquarearnum);
    }
  }

  /**
   * 行一次全部结算后调用
   * 
   * @param listView
   */
  public static void setVOAfterByDID(ShowUpableBillListView listView) {
    SquareOutViewVO[] allvos = SquareUIUtils.getAllVOs(listView);
    SquareOutViewVO[] vos = SquareUIUtils.getSelectVOs(listView);
    SquareOutViewVO[] leftvos =
        SquareOutVOUtils.getInstance().subByDID(allvos, vos);
    listView.getModel().initModel(leftvos);
  }

  /**
   * 方法功能描述：当只需要更新界面上的结算纪录时调用，不会删除
   * 
   * @param listView
   * @param updatevos
   *          <p>
   * @author zhangcheng
   * @time 2010-6-29 下午04:12:50
   */
  public static void setVOAfterSquare(ShowUpableBillListView listView,
      SquareOutViewVO[] returnvos) {
    // 如果返回vo为空，则说明此行全部结算，调用取消结算后处理删除该行
    if (returnvos == null || returnvos.length == 0) {
      SquareUIUtils.deleteVoAfterAction(listView);
      return;
    }

    // 结算后返回的vo<bid,SquareOutViewVO>
    Map<String, SquareOutViewVO> msvo = new HashMap<String, SquareOutViewVO>();
    for (SquareOutViewVO svo : returnvos) {
      msvo.put(svo.getItem().getCsalesquarebid(), svo);
    }
    // 更新界面累计结算数量、本次结算数量、ts
    BillListPanel listpanel = listView.getBillListPanel();
    int[] rows = SquareUIUtils.getSelectedRows(listpanel);
    int len = rows.length;
    // 累计成本、应收、结算、结算单数量
    ListPanelValueUtils lutils = new ListPanelValueUtils(listpanel);
    List<Integer> ldelrow = new ArrayList<Integer>();
    for (int i = 0; i < len; i++) {
      String bid =
          ValueUtils.getString(lutils.getBodyValueAt(rows[i],
              SquareOutBVO.CSALESQUAREBID));
      SquareOutViewVO view = msvo.get(bid);
      if (null == view) {
        ldelrow.add(Integer.valueOf(rows[i]));
      }
      else {
        SquareOutBVO bvo = view.getItem();
        lutils.setBodyValueAt(bvo.getTs(), rows[i], SquareOutBVO.TS);
        UFDouble nthisnum = MathTool.nvl(bvo.getNthisnum());
        lutils.setBodyValueAt(nthisnum, rows[i], SquareOutBVO.NTHISNUM);
        UFDouble nastnum = MathTool.nvl(bvo.getNastnum());
        lutils.setBodyValueAt(nastnum, rows[i], SquareOutBVO.NASTNUM);
        String vchangerate = bvo.getVchangerate();
        lutils.setBodyValueAt(vchangerate, rows[i], SquareOutBVO.VCHANGERATE);
        UFDouble nsquareianum = MathTool.nvl(bvo.getNsquareianum());
        UFDouble nsquarearnum = MathTool.nvl(bvo.getNsquarearnum());
        lutils.setBodyValueAt(nsquarearnum, rows[i], SquareOutBVO.NSQUAREARNUM);
        lutils.setBodyValueAt(nsquareianum, rows[i], SquareOutBVO.NSQUAREIANUM);
        SquareUIUtils.setNtotalsquarenum(rows[i], lutils, nsquareianum,
            nsquarearnum);
        // 设置后台尾差处理后的金额
        lutils.setBodyValueAt(bvo.getNorigtaxmny(), rows[i],
            SquareOutBVO.NORIGTAXMNY);
        lutils
            .setBodyValueAt(bvo.getNorigmny(), rows[i], SquareOutBVO.NORIGMNY);
        // TODO 2012.02.16 fbinly v61删除原币税额字段
        // lutils
        // .setBodyValueAt(bvo.getNorigtax(), rows[i], SquareOutBVO.NORIGTAX);
        lutils.setBodyValueAt(bvo.getNtaxmny(), rows[i], SquareOutBVO.NTAXMNY);
        lutils.setBodyValueAt(bvo.getNmny(), rows[i], SquareOutBVO.NMNY);
        lutils.setBodyValueAt(bvo.getNtax(), rows[i], SquareOutBVO.NTAX);
        // 2012.02.16 fbinly v61新增字段
        lutils.setBodyValueAt(bvo.getNcaltaxmny(), rows[i],
            SquareOutBVO.NCALTAXMNY);
      }
    }

    // 删除已经结算完成的行
    int size = ldelrow.size();
    int[] selrows = new int[size];
    if (size > 0) {
      int i = 0;
      for (Integer irow : ldelrow) {
        selrows[i] = irow.intValue();
        i++;
      }
      listpanel.getBodyBillModel().delLine(selrows);
    }
  }

  /**
   * 方法功能描述：当只有删除界面上的结算数据时调用
   * 
   * @param listView
   *          <p>
   * @param retvos -- 取消结算后返回的vo，如果单行已经全部取消结算此retvos为null
   *          <p>
   * @author zhangcheng
   * @time 2010-6-29 下午04:12:47
   */
  public static void setVOAfterUnSquare(ShowUpableBillListView listView,
      SquareOutViewVO[] retvos) {
    BillListPanel listPanel = listView.getBillListPanel();
    // 删除界面上选中的行
    SquareUIUtils.deleteVoAfterAction(listView);
    // 如果界面全部取消结算或者单行已经全部取消结算，直接退出
    int rowCount = listPanel.getBodyBillModel().getRowCount();
    if (rowCount == 0 || VOChecker.isEmpty(retvos)) {
      return;
    }

    // 结算后返回的vo<bid,SquareOutViewVO>
    Map<String, SquareOutViewVO> msvo = new HashMap<String, SquareOutViewVO>();
    for (SquareOutViewVO svo : retvos) {
      msvo.put(svo.getItem().getCsalesquarebid(), svo);
    }

    // 更新TS和累计结算信息
    IKeyValue lkeyvalue = new ListKeyValue(listPanel, ListTemplateType.SUB);
    for (int i = 0; i < rowCount; i++) {
      String bid = lkeyvalue.getBodyStringValue(i, SquareOutBVO.CSALESQUAREBID);
      SquareOutViewVO retvo = msvo.get(bid);
      if (VOChecker.isEmpty(retvo)) {
        continue;
      }
      lkeyvalue.setBodyValue(i, SquareOutBVO.TS, retvo.getItem().getTs());
      lkeyvalue.setBodyValue(i, SquareOutBVO.NSQUAREARNUM, retvo.getItem()
          .getNsquarearnum());
      lkeyvalue.setBodyValue(i, SquareOutBVO.NSQUAREIANUM, retvo.getItem()
          .getNsquareianum());

    }
  }

  public static void setVOInList(ShowUpableBillListView listView,
      CircularlyAccessibleValueObject[] selectedData, BillManageModel model) {
    BillListPanel listPanel = listView.getBillListPanel();
    listPanel.getBillListData().setBodyValueVO(selectedData);
    listPanel.getBillListData().getBodyBillModel().loadLoadRelationItemValue();
    listPanel.getBillListData().getBodyBillModel().execLoadFormula();
    int size = selectedData == null ? 0 : selectedData.length;
    if (size > 0) {
      listPanel.getBodyTable().setRowSelectionInterval(0, 0);
      ShowStatusBarMsgUtil.showStatusBarMsg(
          NCLangRes.getInstance().getStrByID("4006010_0", "04006010-0083",
              null, new String[] {
                Integer.toString(size)
              })/*查询成功，已查到{0}张单据。*/, model.getContext());
    }
    else {
      ShowStatusBarMsgUtil.showStatusBarMsg(
          IShowMsgConstant.getQueryNullInfo(), model.getContext());
    }
  }

  private static SquareOutViewVO[] getAllVOs(ShowUpableBillListView listView) {
    SquareOutViewVO[] vos =
        (SquareOutViewVO[]) listView.getBillListPanel().getBodyBillModel()
            .getBodyValueVOs(SquareOutViewVO.class.getName());
    return vos;
  }

  private static int[] getSelectedRows(BillListPanel listpanel) {
    BillModel bm = listpanel.getBodyBillModel();
    int rowcnt = bm.getRowCount();
    List<Integer> lrow = new ArrayList<Integer>();
    for (int row = 0; row < rowcnt; ++row) {
      int state = bm.getRowState(row);
      if (BillModel.SELECTED == state) {
        lrow.add(Integer.valueOf(row));
      }
    }
    int size = lrow.size();
    int[] selrows = new int[size];
    if (size > 0) {
      int i = 0;
      for (Integer irow : lrow) {
        selrows[i] = irow.intValue();
        i++;
      }
    }
    return selrows;
  }

  private static void setNtotalsquarenum(int row, ListPanelValueUtils lutils,
      UFDouble nsquareianum, UFDouble nsquarearnum) {
    boolean manualar =
        !lutils.getBodyUFBooleanValueAt(row, SquareOutHVO.BAUTOSQUAREINCOME)
            .booleanValue();
    boolean manualia =
        !lutils.getBodyUFBooleanValueAt(row, SquareOutHVO.BAUTOSQUARECOST)
            .booleanValue();
    SquareType iaKey =
        MDEnum.valueOf(SquareType.class,
            lutils.getBodyValueAt(row, SquareOutBVO.FPREIATYPE));
    SquareType arKey =
        MDEnum.valueOf(SquareType.class,
            lutils.getBodyValueAt(row, SquareOutBVO.FPREARTYPE));

    // 用累计成本或应收数量设置已结算数量
    if (manualia
        && SquareType.SQUARETYPE_IA.getIntValue() == iaKey.getIntValue()
        && manualar
        && SquareType.SQUARETYPE_AR.getIntValue() == arKey.getIntValue()) {
      if (MathTool.absCompareTo(nsquareianum, nsquarearnum) > 0) {
        lutils.setBodyValueAt(nsquareianum, row, SquareOutBVO.NTOTALSQUARENUM);
      }
      else {
        lutils.setBodyValueAt(nsquarearnum, row, SquareOutBVO.NTOTALSQUARENUM);
      }
    }
    else if (manualia
        && SquareType.SQUARETYPE_IA.getIntValue() == iaKey.getIntValue()) {
      lutils.setBodyValueAt(nsquareianum, row, SquareOutBVO.NTOTALSQUARENUM);
    }
    else if (manualar
        && SquareType.SQUARETYPE_AR.getIntValue() == arKey.getIntValue()) {
      lutils.setBodyValueAt(nsquarearnum, row, SquareOutBVO.NTOTALSQUARENUM);
    }
  }

}
