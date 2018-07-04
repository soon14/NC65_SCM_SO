package nc.pubimpl.so.sobalance.arap.listener;

import nc.bs.businessevent.IBusinessEvent;
import nc.bs.businessevent.IBusinessListener;
import nc.vo.pub.BusinessException;
import nc.vo.pubapp.pattern.exception.ExceptionUtils;

/**
 * 核销后-订单收款核销监听
 * 
 * <p>注册：开发平台-开发配置工具-业务插件注册-应收管理-反核销前-核销后</p>
 * <p>相应表：pub_eventlistener</p>
 * 
 * @since 6.0
 * @version 2011-3-31 下午04:37:49
 * @author 刘志伟
 */
public class VerifyAfterListener implements IBusinessListener {
  
  /**
   * 将所有核销记录按销售订单ID分组，并构建出相应SoBalanceVO[]进行插入或更新
   */
  @Override
  public void doAction(IBusinessEvent event) throws BusinessException {
    try{
      new VerifyAfterListenerAction().doAction(event);
    }catch(Exception ex){
      ExceptionUtils.marsh(ex);
    }
  }
  
}
