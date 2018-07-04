package nc.ui.so.m32.billui.action.add;

import java.awt.event.ActionEvent;

import nc.itf.scmpub.reference.uap.group.SysInitGroupQuery;
import nc.ui.ml.NCLangRes;
import nc.vo.pubapp.pattern.exception.ExceptionUtils;
import nc.vo.so.m32.entity.SaleInvoiceVO;

/**
 * <p>
 * <b>本类主要完成以下功能：</b>
 * <ul>
 * <li>销售发票参照销售出库单新增类继承销售发票转单公共处理类，如有特殊处理可复写相关方法
 * </ul>
 * <p>
 * 
 * @version 本版本号 6.0
 * @since 上一版本号 5.6
 * @author 冯加滨
 * @time 2010-4-29 下午07:36:02
 */
public class RefOutAddAction extends RefAddAction {
  /**
     * 
     */
  private static final long serialVersionUID = 6994676476366579989L;
  
  @Override
  public void doAction(ActionEvent e) throws Exception {
    if(!SysInitGroupQuery.isICEnabled()){
      ExceptionUtils.wrappBusinessException(NCLangRes.getInstance().getStrByID("4006008_0", "04006008-0160")/*库存模块没有启用,请先启用库存模块！*/);
    }
    super.doAction(e);
  }
  
  @Override
	protected void beforeTranProcessor(SaleInvoiceVO[] newvos) {
		super.beforeTranProcessor(newvos);
	}
}
