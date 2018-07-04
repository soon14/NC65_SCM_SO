package nc.ui.so.custmatrel.action;

import nc.vo.so.custmatrel.entity.CustMatRelHVO;

import nc.ui.trade.excelimport.ExportTempItemSeleStrategy;
import nc.ui.trade.excelimport.InputItem;

/**
 * 扩展导出模板字段
 * 
 * @since 6.1
 * @version 2013-12-10 14:39:59
 * @author liujingn
 */
public class CustMaterExportDataItemSeleStrategy extends
    ExportTempItemSeleStrategy {

  @Override
  public boolean accept(InputItem item) {
    // 导出模块必须项显示销售组织
    if (item.getItemKey().equals(CustMatRelHVO.PK_ORG)) {
      return true;
    }
    return super.accept(item);
  }

}
