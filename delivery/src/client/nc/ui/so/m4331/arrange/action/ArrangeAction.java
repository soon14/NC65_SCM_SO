package nc.ui.so.m4331.arrange.action;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

import nc.bs.framework.common.NCLocator;
import nc.desktop.ui.WorkbenchEnvironment;
import nc.md.model.MetaDataException;
import nc.md.persist.framework.MDPersistenceService;
import nc.pubitf.so.m30.pub.ISaleOrderForPub;
import nc.ui.ml.NCLangRes;
import nc.ui.pubapp.billref.push.PushAction;
import nc.ui.scmpub.action.SCMActionInitializer;
import nc.vo.pub.AggregatedValueObject;
import nc.vo.pub.BusinessException;
import nc.vo.pub.CircularlyAccessibleValueObject;
import nc.vo.pub.lang.UFBoolean;
import nc.vo.pub.lang.UFDouble;
import nc.vo.pubapp.pattern.exception.ExceptionUtils;
import nc.vo.pubapp.pflow.PfServiceUtil;
import nc.vo.scmpub.res.SCMActionCode;
import nc.vo.scmpub.res.billtype.SOBillType;
import nc.vo.so.m30.entity.SaleOrderBVO;
import nc.vo.so.m30.entity.SaleOrderHVO;
import nc.vo.so.m30.entity.SaleOrderVO;
import nc.vo.so.m30.entity.SaleOrderViewVO;
import nc.vo.so.m4331.entity.DeliveryBVO;
import nc.vo.so.m4331.entity.DeliveryVO;
import nc.vo.to.m5x.entity.BillItemVO;
import nc.vo.to.m5x.entity.BillVO;

/**
 * <p>
 * <b>本类主要完成以下功能：</b>
 * <ul>
 * <li>发货安排的安排功能实现
 * </ul>
 * <p>
 * 
 * @version 本版本号 6.0
 * @since
 * @author fengjb
 * @time 2010-5-27 下午01:55:45
 */
public class ArrangeAction extends PushAction {

  private static final long serialVersionUID = -4404042322034244013L;

  /**
   * 父类方法重写
   */
  @Override
  public void intActionUI() {
    super.intActionUI();
    SCMActionInitializer.initializeAction(this, SCMActionCode.SCM_BILLPUSH);
  }

  /**
   * 父类方法重写
   */
  @Override
  protected void afterVOChange(AggregatedValueObject[] srcVos,
      AggregatedValueObject[] destVos) {
    if (null == srcVos || srcVos.length == 0) {
      return;
    }
    List<String> sobids = new ArrayList<String>();
    StringBuffer errMsg = new StringBuffer();
    for (AggregatedValueObject srcvo : srcVos) {
      if (srcvo instanceof SaleOrderVO) {
    	CircularlyAccessibleValueObject[] bvos = srcvo.getChildrenVO();
        for (CircularlyAccessibleValueObject bvo : bvos) {
          sobids
              .add((String) bvo.getAttributeValue(SaleOrderBVO.CSALEORDERBID));
        }
      }
      else if (srcvo instanceof BillVO) {
        BillVO vo = (BillVO) srcvo;
        BillItemVO[] items = vo.getChildrenVO();
        for (BillItemVO item : items) {
          UFBoolean flag = item.getBsendendflag();
          if (null != flag && flag.booleanValue()) {
            errMsg.append(NCLangRes.getInstance().getStrByID("4006002_0",
                "04006002-0105", null, new String[] {
                  vo.getParentVO().getVbillcode(), item.getCrowno()
                })/*单据号{0}的订单表体行{1}已经发货关闭，不能安排。*/);
            errMsg.append("\n");
          }
        }
      }
    }
    if (sobids.size() > 0) {
        ISaleOrderForPub squaresrv =
            NCLocator.getInstance().lookup(ISaleOrderForPub.class);
        try {
          SaleOrderViewVO[] viewvos =
              squaresrv.querySaleOrderViewVOs(
                  sobids.toArray(new String[sobids.size()]), new String[] {
                    SaleOrderBVO.CSALEORDERBID, SaleOrderBVO.BBSENDENDFLAG,SaleOrderHVO.VBILLCODE,
                    SaleOrderBVO.CROWNO
                  });

          for (SaleOrderViewVO viewvo : viewvos) {
        	UFBoolean flag = viewvo.getBody().getBbsendendflag();
            if (flag != null && flag.booleanValue()) {
              errMsg.append(NCLangRes.getInstance().getStrByID("4006002_0",
                  "04006002-0105", null, new String[] {
            		  viewvo.getHead().getVbillcode(), viewvo.getBody().getCrowno()
                  })/*单据号{0}的订单表体行{1}已经发货关闭，不能安排。*/);
              errMsg.append("\n");
            }
          }

        }
        catch (BusinessException e) {
          ExceptionUtils.wrappException(e);
        }
      }
    if (errMsg.length() > 0) {
      ExceptionUtils.wrappBusinessException(errMsg.toString());
    }
    String pk_group =
        WorkbenchEnvironment.getInstance().getGroupVO().getPk_group();
    PfServiceUtil.processDestBillTranType(destVos,
        SOBillType.Delivery.getCode(), pk_group);
    //按照鞍钢国贸赵子龙要求将数量在转单的时候就改成剩余可发货的数量，而不是总数量；
    //add by 王梓懿 2017-11-15
    /*filterNumber(destVos);*/
    
  }

  /**
   * @author 王梓懿
   * 将数量计算时减去已经发货的数量
   * <b>公式待确认：</b>
   */
private void filterNumber(AggregatedValueObject[] destVos) {
	// TODO 自动生成的方法存根
	for (int i = 0; i < destVos.length; i++) {
		DeliveryVO deAggvo = (DeliveryVO)destVos[i];
		UFDouble Ntotalastnum = deAggvo.getParentVO().getNtotalastnum();
		//查询该单据已经做出了多少发货
		UFDouble hasDone=queryForHasDone(deAggvo);
		//还有多少没有做出货处理
		UFDouble res=Ntotalastnum.sub(hasDone);
		//给VO赋值，也就是给界面赋值
		deAggvo.getParentVO().setNtotalastnum(res);
	}
	
}

/** 
 * @Title: queryForHasDone 
 * @Description: TODO
 * @param deAggvo
 * @return
 * @return: UFDouble
 * @throws MetaDataException 
 */
private UFDouble queryForHasDone(DeliveryVO deAggvo)  {
	// TODO 自动生成的方法存根
	//通过条件找到该张销售订单做了多少出库
	String csrcid=deAggvo.getChildrenVO()[0].getCsrcid();
	String wherePart=" and csrcid ='"+csrcid+"'";
	Collection<DeliveryBVO> ret = null;
	try {
		ret=MDPersistenceService.lookupPersistenceQueryService().queryBillOfVOByCond(DeliveryBVO.class, wherePart, false);
	} catch (MetaDataException e) {
		// TODO 自动生成的 catch 块
		e.printStackTrace();
	}
	
	if(ret==null){
		return UFDouble.ZERO_DBL;
	}
	DeliveryBVO [] res=ret.toArray(new DeliveryBVO[ret.size()]);
	UFDouble num=UFDouble.ZERO_DBL;
	for (int i = 0; i < res.length; i++) {
		DeliveryBVO bvo=res[i];
		num.add(bvo.getNastnum());
	}
	return num;
}
}
