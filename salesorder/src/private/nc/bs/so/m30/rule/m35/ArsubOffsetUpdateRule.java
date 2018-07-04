package nc.bs.so.m30.rule.m35;

import java.util.ArrayList;
import java.util.List;

import nc.bs.framework.common.NCLocator;
import nc.bs.ml.NCLangResOnserver;
import nc.impl.pubapp.pattern.rule.IRule;
import nc.itf.scmpub.reference.uap.group.SysInitGroupQuery;
import nc.pubitf.so.m35.so.m30.IArsubToSaleorder;
import nc.vo.pub.BusinessException;
import nc.vo.pubapp.pattern.exception.ExceptionUtils;
import nc.vo.so.m30.entity.OffsetTempVO;
import nc.vo.so.m30.entity.SaleOrderHVO;
import nc.vo.so.m30.entity.SaleOrderVO;
import nc.vo.so.m30.util.SaleOrderVOUtil;
import nc.vo.so.m35.entity.ArsubInterfaceVO;

/**
 * 
 * @description
 * 销售订单修改保存时
 * @scene
 * 销售订单修改保存时赠品兑付费用单冲抵删除
 * @param
 * 无
 *
 * @since 6.5
 * @version 2015-10-19 下午3:10:01
 * @author zhangby5
 */
public class ArsubOffsetUpdateRule implements IRule<SaleOrderVO> {

  @Override
  public void process(SaleOrderVO[] vos) {
    List<OffsetTempVO> tempvos = new ArrayList<OffsetTempVO>();
    SaleOrderVOUtil voutil = new SaleOrderVOUtil();
    List<SaleOrderVO> arordervo = new ArrayList<SaleOrderVO>();
    for (SaleOrderVO bill : vos) {
      SaleOrderHVO header = bill.getParentVO();
      String arsubtypeid = header.getCarsubtypeid();
      if (null != arsubtypeid) {
        // 恢复订单数据
        // this.resetBillVO(bill);
        // 删除冲抵关系
        arordervo.add(bill);
        OffsetTempVO tempvo = new OffsetTempVO();
        tempvo.setIsCancelOffset(true);
        tempvos.add(tempvo);
      }
    }
    
    if(tempvos.size() == 0){
    	return;
    }
    
    if (!SysInitGroupQuery.isMeEnabled()) {
		ExceptionUtils
				.wrappBusinessException(NCLangResOnserver.getInstance()
						.getStrByID("4006011_0", "04006011-0519")/*请启用营销费用模块！*/);
    }
    
    // 设置冲抵关系
    SaleOrderVO[] forwirteoder =
        arordervo.toArray(new SaleOrderVO[arordervo.size()]);
    OffsetTempVO[] forwritetemp =
        tempvos.toArray(new OffsetTempVO[tempvos.size()]);
    ArsubInterfaceVO[] arsubvo = voutil.changeToArsubInterfaceVO(forwirteoder);
    IArsubToSaleorder service =
        NCLocator.getInstance().lookup(IArsubToSaleorder.class);
    try {
      service.writeArsubRelationForArsub(arsubvo, forwritetemp);
    }
    catch (BusinessException e) {
      ExceptionUtils.wrappBusinessException(e.getMessage());
    }
  }

}
