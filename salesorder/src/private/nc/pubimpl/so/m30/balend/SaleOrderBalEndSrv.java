package nc.pubimpl.so.m30.balend;

import nc.pubitf.so.m30.balend.BalEndPara;
import nc.pubitf.so.m30.balend.BalOpenPara;
import nc.pubitf.so.m30.balend.ISaleOrderBalEndSrv;
import nc.vo.pub.BusinessException;
import nc.vo.pub.lang.UFBoolean;
import nc.vo.pubapp.pattern.exception.ExceptionUtils;

public class SaleOrderBalEndSrv implements ISaleOrderBalEndSrv {

  @Override
  public UFBoolean[] isCostBalEnd(String[] saleorderbids)
      throws BusinessException {
    try{   
    return new SaleOrderBalEndSrvAction().isCostBalEnd(saleorderbids);
    }catch(Exception ex){
      ExceptionUtils.marsh(ex);
    }
    return new UFBoolean[0];
  }

  @Override
  public UFBoolean[] isIncomeBalEnd(String[] saleorderbids)
      throws BusinessException {
    try{ 
    return new SaleOrderBalEndSrvAction().isIncomeBalEnd(saleorderbids);
    }catch(Exception ex){
      ExceptionUtils.marsh(ex);
    }
    return new UFBoolean[0];
  }

  @Override
  public void processAutoBalEnd(BalEndPara para) throws BusinessException {
    try{ 
    new SaleOrderBalEndSrvAction().processAutoBalEnd(para);
    }catch(Exception ex){
      ExceptionUtils.marsh(ex);
    }
  }

  @Override
  public void processAutoBalOpen(BalOpenPara para) throws BusinessException {
    try{ 
new SaleOrderBalEndSrvAction().processAutoBalOpen(para);
    }catch(Exception ex){
      ExceptionUtils.marsh(ex);
    }
  }
  
  
}
