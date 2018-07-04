package nc.pubimpl.so.sobalance.arap.listener;

import nc.bs.businessevent.IBusinessEvent;
import nc.bs.businessevent.IBusinessListener;
import nc.vo.pub.BusinessException;
import nc.vo.pubapp.pattern.exception.ExceptionUtils;


/**
 * 收款单-新增后-订单收款核销监听
 * 
 * <p>注册：开发平台-开发配置工具-业务插件注册-应收管理-客户收款单-新增后</p>
 * <p>相应表：pub_eventlistener</p>
 * 
 * @since 6.0
 * @version 2011-3-31 下午04:37:49
 * @author 刘志伟
 */
public class GatheringAddAfterListener implements IBusinessListener {
  
  @Override
  public void doAction(IBusinessEvent event) throws BusinessException{
    try{
      new GatheringAddAfterListenerAction().doAction(event);
    }catch(Exception ex){
      ExceptionUtils.marsh(ex);
    }
  }
  
}
