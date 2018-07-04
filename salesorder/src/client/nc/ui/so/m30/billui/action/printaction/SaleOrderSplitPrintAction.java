package nc.ui.so.m30.billui.action.printaction;

import java.awt.event.ActionEvent;

import nc.ui.pubapp.action.SplitPrintAction;
import nc.vo.so.m30.entity.SaleOrderVO;
import nc.vo.so.pub.util.SOSysParaInitUtil;

/**
 * 销售订单分单打印
 * 
 * @since 6.0
 * @version 2011-7-14 下午02:30:54
 * @author 么贵敬
 */
public class SaleOrderSplitPrintAction extends SplitPrintAction {

  /**
     * 
     */
  private static final long serialVersionUID = -7060279075801042976L;

  public SaleOrderSplitPrintAction() {
    super();
  }

  @Override
  public void doAction(ActionEvent e) throws Exception {
    Object obj = this.getModel().getSelectedData();
    SaleOrderVO vo = (SaleOrderVO) obj;

    String pk_org = vo.getParentVO().getPk_org();
    String[] splitattribute = null;

    splitattribute = SOSysParaInitUtil.getSO18(pk_org);

    super.setPrintdataSplitProcessor(new SaleOrderPrintDataSplitProcessor());

    super.setSplitAttributes(splitattribute);

    super.doAction(e);
  }

  @Override
  protected boolean isActionEnable() {

    return super.isActionEnable();
  }

}
