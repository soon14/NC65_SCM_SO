package nc.impl.so.m32;

import nc.impl.pubapp.env.BSContext;
import nc.impl.so.m32.action.DeleteSaleInvoiceAction;
import nc.impl.so.m32.action.InsertSaleInvoiceAction;
import nc.impl.so.m32.action.UpdateSaleInvoiceAction;
import nc.itf.so.m32.ISaleInvoiceScriptMaintain;
import nc.vo.pub.BusinessException;
import nc.vo.pubapp.pattern.exception.ExceptionUtils;
import nc.vo.pubapp.pflow.PfUserObject;
import nc.vo.scmpub.res.BusinessCheck;
import nc.vo.so.m30.entity.OffsetTempVO;
import nc.vo.so.m32.entity.SaleInvoiceVO;
import nc.vo.so.m32.entity.SaleinvoiceUserObject;
import nc.vo.so.m32.scale.SaleInvoiceScaleProcessor;
import nc.vo.so.m32.util.RemoteFormSOUtil;
import nc.vo.so.m32.util.SaleInvoiceVOUtil;
import nc.vo.so.m35.entity.ArsubInterfaceVO;
import nc.vo.uap.pf.PFBusinessException;

/**
 * 销售发票操作接口实现类
 * 
 * @since 6.3
 * @version 2012-12-21 上午10:05:22
 * @author yaogj
 */
public class SaleInvoiceScriptMaintainImpl implements
    ISaleInvoiceScriptMaintain {

  @Override
  public SaleInvoiceVO[] saleInvoiceUpdate(SaleInvoiceVO[] vos,
      PfUserObject userObj, SaleInvoiceVO[] originBills)
      throws BusinessException {
    try {
      // 精度检查
      checkScale(userObj, vos);

      SaleInvoiceVO[] retvos = null;
      UpdateSaleInvoiceAction action = new UpdateSaleInvoiceAction();
      retvos = action.update(vos, originBills);

      // 保存冲抵关系并回写销售费用单
      if (userObj != null && userObj.getUserObject() != null) {
    	SaleinvoiceUserObject invoiceuserobj =
    			  (SaleinvoiceUserObject) userObj.getUserObject();
    	if(invoiceuserobj.getTempvo() == null){
    		return retvos;
    	}
        SaleInvoiceVOUtil voutil = new SaleInvoiceVOUtil();
        ArsubInterfaceVO[] arsubvo = voutil.changeToArsubInterfaceVO(retvos);
        OffsetTempVO[] tempvo = new OffsetTempVO[] {
          invoiceuserobj.getTempvo()
        };
        RemoteFormSOUtil.writeArsubRelation(arsubvo, tempvo);
      }
      return retvos;
    }
    catch (Exception e) {
      if (e instanceof BusinessException) {
        throw (BusinessException) e;
      }
      throw new PFBusinessException(e.getMessage(), e);
    }
    finally {
      // 此处释放session变量，以免浪费内存
      BSContext.getInstance().removeSession(BusinessCheck.class.getName());
    }
  }

  private void checkScale(PfUserObject userObj, SaleInvoiceVO[] vos) {
    // 外部交换平台的单据才进行精度检查。
    if (userObj == null||userObj.getUserObject() == null) {
      new SaleInvoiceScaleProcessor().checkBillPrecision(vos);
    }
    else {
      SaleinvoiceUserObject invoiceuserobj =
          (SaleinvoiceUserObject) userObj.getUserObject();
      if (!invoiceuserobj.isIsclientsave()) {
        new SaleInvoiceScaleProcessor().checkBillPrecision(vos);
      }
    }
  }

  @Override
  public void saleInvoiceDelete(SaleInvoiceVO[] vos) throws BusinessException {
    try {
      DeleteSaleInvoiceAction action = new DeleteSaleInvoiceAction();
      action.delete(vos);
    }
    catch (Exception e) {
      ExceptionUtils.marsh(e);
    }
  }

  @Override
  public SaleInvoiceVO[] saleInvoiceInsert(SaleInvoiceVO[] vos,
      PfUserObject userObj) throws BusinessException {
    SaleInvoiceVO[] retvos = null;
    try {

      // 精度检查
      checkScale(userObj, vos);

      InsertSaleInvoiceAction action = new InsertSaleInvoiceAction();
      retvos = action.insert(vos);
      // 保存冲抵关系并回写销售费用单
      if (userObj != null && userObj.getUserObject() != null) {
        SaleinvoiceUserObject invoiceuserobj =
            (SaleinvoiceUserObject) userObj.getUserObject();
        if(invoiceuserobj.getTempvo() == null){
    		return retvos;
    	}
        SaleInvoiceVOUtil voutil = new SaleInvoiceVOUtil();
        ArsubInterfaceVO[] arsubvo = voutil.changeToArsubInterfaceVO(retvos);
        OffsetTempVO[] tempvo = new OffsetTempVO[] {
          invoiceuserobj.getTempvo()
        };
        RemoteFormSOUtil.writeArsubRelation(arsubvo, tempvo);
      }
    }
    catch (Exception ex) {
      ExceptionUtils.marsh(ex);
    }
    return retvos;
  }

}
