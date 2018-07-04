package nc.ui.so.m32.billref.ic.vmi.itf;

import nc.ui.uif2.model.AbstractAppModel;
import nc.vo.ic.m50.entity.VmiSumGenerateParam;
import nc.vo.so.m32.entity.SaleInvoiceViewVO;

public interface IInvoiceToVmiControl {
  /**
   * 方法功能描述：返回选中的销售发票视图VO数组。
   * <p>
   * <b>参数说明</b>
   * 
   * @return <p>
   * @author fengjb
   * @time 2010-8-3 下午07:22:29
   */
  SaleInvoiceViewVO[] getSelectedVOs();

  /**
   * 方法功能描述：返回消耗汇总查询界面选择的消耗汇总规则。
   * <p>
   * <b>参数说明</b>
   * 
   * @return <p>
   * @author fengjb
   * @time 2010-8-6 下午01:09:22
   */
  VmiSumGenerateParam getVmiSumGenerateParam();

  /**
   * 查询并加载将要消耗汇总的销售发票记录
   * 
   * @param model model
   */
  void queryAndLoadInvoice(AbstractAppModel pmodel);
}
