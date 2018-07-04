package nc.impl.so.m38;

import nc.bs.so.m38.state.PreOrderStateMachine;
import nc.bs.so.m38.state.bill.BillCloseState;
import nc.bs.so.m38.state.bill.BillOpenState;
import nc.bs.so.m38.state.row.RowCloseState;
import nc.bs.so.m38.state.row.RowOpenState;
import nc.impl.pubapp.bill.convertor.BillToViewConvertor;
import nc.impl.pubapp.bill.convertor.ViewToBillConvertor;
import nc.impl.pubapp.bill.state.IState;
import nc.impl.pubapp.pattern.data.bill.tool.BillTransferTool;
import nc.impl.pubapp.pattern.data.view.tool.ViewTransferTool;
import nc.itf.so.m38.IPreOrderAssitFunc;
import nc.vo.pub.BusinessException;
import nc.vo.pubapp.pattern.data.IObjectConvert;
import nc.vo.pubapp.pattern.exception.ExceptionUtils;
import nc.vo.pubapp.pattern.log.TimeLog;
import nc.vo.so.m38.entity.PreOrderBVO;
import nc.vo.so.m38.entity.PreOrderVO;
import nc.vo.so.m38.entity.PreOrderViewVO;

public class PreOrderAssitFuncImpl implements IPreOrderAssitFunc {

  @Override
  public PreOrderVO[] closePreOrder(PreOrderVO[] bills)
      throws BusinessException {
    try {
      BillCloseState state = new BillCloseState();
      return this.setState(state, bills);
    }
    catch (Exception ex) {
      ExceptionUtils.marsh(ex);
    }
    return null;
  }

  @Override
  public PreOrderVO[] closePreOrderRows(PreOrderVO originBill, int[] rows)
      throws BusinessException {
    PreOrderVO bill = this.getRowSelectedBill(originBill, rows);
    TimeLog.logStart();
    IObjectConvert<PreOrderVO, PreOrderViewVO> convert =
        new BillToViewConvertor<PreOrderVO, PreOrderViewVO>(
            PreOrderViewVO.class);
    PreOrderViewVO[] views = convert.convert(new PreOrderVO[] {
      bill
    });
    IState<PreOrderViewVO> state = new RowCloseState();

    views = this.setRowStatus(state, views);
    IObjectConvert<PreOrderViewVO, PreOrderVO> billconvert =
        new ViewToBillConvertor<PreOrderViewVO, PreOrderVO>(PreOrderVO.class);
    PreOrderVO[] bills = billconvert.convert(views);
    return bills;
  }

  @Override
  public PreOrderVO[] openPreOrder(PreOrderVO[] bills) throws BusinessException {
    try {
      BillOpenState state = new BillOpenState();
      return this.setState(state, bills);
    }
    catch (Exception ex) {
      ExceptionUtils.marsh(ex);
    }
    return null;
  }

  @Override
  public PreOrderVO[] openPreOrderRows(PreOrderVO originBill, int[] rows)
      throws BusinessException {
    PreOrderVO bill = this.getRowSelectedBill(originBill, rows);
    TimeLog.logStart();
    IObjectConvert<PreOrderVO, PreOrderViewVO> convert =
        new BillToViewConvertor<PreOrderVO, PreOrderViewVO>(
            PreOrderViewVO.class);
    PreOrderViewVO[] views = convert.convert(new PreOrderVO[] {
      bill
    });
    IState<PreOrderViewVO> state = new RowOpenState();

    views = this.setRowStatus(state, views);
    IObjectConvert<PreOrderViewVO, PreOrderVO> billconvert =
        new ViewToBillConvertor<PreOrderViewVO, PreOrderVO>(PreOrderVO.class);
    PreOrderVO[] bills = billconvert.convert(views);
    return bills;
  }

  /**
   * 方法功能描述：获得选中VO = 表头 + 选中行items
   * 
   * @param bill 单据全VO
   * @param rows 选中行
   * @throws BusinessException
   * @since 6.0
   */
  private PreOrderVO getRowSelectedBill(PreOrderVO originBill, int[] rows) {
    int length = rows.length;
    PreOrderBVO[] originItems = originBill.getChildrenVO();
    PreOrderVO bill = new PreOrderVO();
    PreOrderBVO[] items = new PreOrderBVO[length];
    for (int i = 0; i < length; i++) {
      int row = rows[i];
      items[i] = originItems[row];
    }
    bill.setParentVO(originBill.getParentVO());
    bill.setChildrenVO(items);
    return bill;
  }

  private PreOrderViewVO[] setRowStatus(IState<PreOrderViewVO> state,
      PreOrderViewVO[] originviews) {
    ViewTransferTool<PreOrderViewVO> tool =
        new ViewTransferTool<PreOrderViewVO>(originviews);
    PreOrderViewVO[] views = tool.getOriginViews();

    PreOrderStateMachine machine = new PreOrderStateMachine();
    machine.setState(state, views);

    // 返回轻量级VO
    views = tool.getViewForToClient(views);

    return views;
  }

  private PreOrderVO[] setState(IState<PreOrderVO> state, PreOrderVO[] bills) {
    TimeLog.logStart();
    BillTransferTool<PreOrderVO> transferTool =
        new BillTransferTool<PreOrderVO>(bills);
    PreOrderVO[] fullbills = transferTool.getClientFullInfoBill();
    TimeLog.info("补全前台VO、加锁、检查时间戳"); /*-=notranslate=-*/

    PreOrderStateMachine bo = new PreOrderStateMachine();
    bo.setState(state, fullbills);

    return transferTool.getBillForToClient(fullbills);
  }

}
