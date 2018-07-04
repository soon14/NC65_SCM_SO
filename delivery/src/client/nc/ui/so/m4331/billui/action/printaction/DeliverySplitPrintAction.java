package nc.ui.so.m4331.billui.action.printaction;

import java.awt.event.ActionEvent;
import java.util.HashSet;
import java.util.Set;

import nc.ui.pubapp.action.SplitPrintAction;
import nc.vo.so.m4331.entity.DeliveryHVO;
import nc.vo.so.m4331.entity.DeliveryVO;
import nc.vo.so.pub.util.SOSysParaInitUtil;

public class DeliverySplitPrintAction extends SplitPrintAction {

  /**
     * 
     */
  private static final long serialVersionUID = -7060279075801042976L;

  public DeliverySplitPrintAction() {
    super();
  }

  @Override
  public void doAction(ActionEvent e) throws Exception {
    Object obj = this.getModel().getSelectedData();
    DeliveryVO vo = (DeliveryVO) obj;

    String pk_org = vo.getParentVO().getPk_org();
    String[] splitattribute = null;

    splitattribute = SOSysParaInitUtil.getSO19(pk_org);
    Set<String> tempSet = new HashSet<String>();
    if (splitattribute != null) {
      for (String att : splitattribute) {
        tempSet.add(att);
      }
    }
    Set<String> set = new HashSet<String>();
    if (tempSet.contains(DeliveryHVO.CTRANTYPEID)) {
      set.add(DeliveryHVO.CTRANTYPEID);
      tempSet.remove(DeliveryHVO.CTRANTYPEID);
    }
    if (tempSet.contains(DeliveryHVO.VBILLCODE)) {
      set.add(DeliveryHVO.VBILLCODE);
      tempSet.remove(DeliveryHVO.VBILLCODE);
    }
    if (tempSet.contains(DeliveryHVO.VTRANTYPECODE)) {
      set.add(DeliveryHVO.VTRANTYPECODE);
      tempSet.remove(DeliveryHVO.VTRANTYPECODE);
    }
    String[] newatr = new String[tempSet.size()];
    tempSet.toArray(newatr);

    super.setSplitAttributes(newatr);

    super.setPrintdataSplitProcessor(new DeliveryPrintDataSplitProcessor());

    if (set.size() > 0) {
      String[] headAttr = new String[set.size()];
      set.toArray(headAttr);
      super.setSplitHeadAttributes(headAttr);
    }
    super.doAction(e);
  }

  @Override
  protected boolean isActionEnable() {
    return super.isActionEnable();
  }
}
