package nc.vo.so.pf;

import nc.impl.pubapp.pattern.data.bill.BillQuery;
import nc.md.model.IBusinessEntity;
import nc.ui.pub.print.IMetaDataDataSource;
import nc.vo.pf.change.PfUtilBaseTools;
import nc.vo.pub.ISuperVO;
import nc.vo.pubapp.pattern.exception.ExceptionUtils;
import nc.vo.pubapp.pattern.model.entity.bill.IBill;
import nc.vo.pubapp.util.MetaUtils;

/**
 * 供应链邮件审批
 * 
 * @since 6.0
 * @version 2012-1-12 上午10:18:36
 * @author 么贵敬
 */
public class SOPrintDataSourceForMailAudit implements IMetaDataDataSource {

  private static final long serialVersionUID = -7753845714456267446L;

  // 单据主表ID
  private String billId = null;

  // 单据类型
  private String billType = null;

  /*
   * 构造子，初始化单据数据
   */
  public SOPrintDataSourceForMailAudit(String billType, String billId) {
    this.setBillType(billType);
    this.setBillId(billId);
  }

  @Override
  public String[] getAllDataItemExpress() {
    return null;
  }

  @Override
  public String[] getAllDataItemNames() {
    return null;
  }

  public String getBillId() {
    return this.billId;
  }

  public String getBillType() {
    return this.billType;
  }

  @Override
  public String[] getDependentItemExpressByExpress(String itemExpress) {
    return null;
  }

  @Override
  public String[] getItemValuesByExpress(String itemExpress) {
    return null;
  }

  @SuppressWarnings("unchecked")
  @Override
  public Object[] getMDObjects() {
    Class<?> clazz = null;
    try {
      String realbtcode = PfUtilBaseTools.getRealBilltype(this.getBillType());
      IBusinessEntity bizbean =
          MetaUtils.getBusinessEntityByBillType(realbtcode);
      clazz =
          MetaUtils.getContainerClass((ISuperVO) Class.forName(
              bizbean.getFullClassName()).newInstance());

    }
    catch (Exception e) {
      ExceptionUtils.wrappException(e);
      return null;
    }
    return new BillQuery<IBill>((Class<IBill>) clazz).query(new String[] {
      this.getBillId()
    });
  }

  /**
   * 按字段属性返回VO值 父类方法重写
   * 
   * @see nc.ui.pub.print.IDataSource#getItemValuesByExpress(java.lang.String)
   */

  @Override
  public String getModuleName() {
    return null;
  }

  @Override
  public boolean isNumber(String itemExpress) {
    return false;
  }

  public void setBillId(String billId) {
    this.billId = billId;
  }

  public void setBillType(String billType) {
    this.billType = billType;
  }

}
