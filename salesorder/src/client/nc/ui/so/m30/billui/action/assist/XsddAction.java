/**
 * 
 */
package nc.ui.so.m30.billui.action.assist;

import java.awt.event.ActionEvent;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;



import java.util.Map;







//import nc.vo.lm.intertable.AggLmpgjkHVO;
import nc.bs.framework.common.NCLocator;
import nc.bs.pub.pf.PfUtilTools;
import nc.bs.trade.business.HYPubBO;
import nc.itf.scmpub.reference.uap.pf.PfServiceScmUtil;
import nc.md.data.access.NCObject;
import nc.md.persist.framework.MDPersistenceService;
import nc.ui.ls.MessageBox;
import nc.ui.uif2.NCAction;
import nc.uif.pub.exception.UifException;

import nc.vo.bd.material.marbasclass.MarBasClassVO;
import nc.vo.pub.AggregatedValueObject;
import nc.vo.pub.VOStatus;
import nc.vo.pub.lang.UFDouble;
import nc.vo.pubapp.pattern.exception.ExceptionUtils;
import nc.vo.so.m30.entity.SaleOrderBVO;
import nc.vo.so.m30.entity.SaleOrderHVO;
import nc.vo.so.m30.entity.SaleOrderVO;

/**
 * @author  wangzym
 * @version 2017年3月16日 下午2:49:32
 */
public class XsddAction extends NCAction{

	/**
	 * 
	 */
	private nc.ui.pubapp.uif2app.model.BillManageModel model;
	private nc.ui.so.m30.billui.view.SaleOrderBillForm editor;
	public XsddAction() {
		// TODO 自动生成的构造函数存根
		this.setBtnName("销售订单接口");
		this.setCode("yyy");
	}

	/* （非 Javadoc）
	 * @see nc.ui.uif2.NCAction#doAction(java.awt.event.ActionEvent)
	 */
	@Override
	public void doAction(ActionEvent e) throws Exception {
		/*// TODO 自动生成的方法存根
		SaleOrderVO aggSaleOrderVO = null;
		SaleOrderHVO saleOrderHVO = null;
		MsgAGXSHT0001Head msgAGXSHT0001Head = new MsgAGXSHT0001Head();
		MsgAGXSHT0001 msgAGXSHT0001 = new MsgAGXSHT0001();
		Agxsht001HVO agxsht001HVO =new Agxsht001HVO();
		aggSaleOrderVO = (SaleOrderVO) this.model.getSelectedData();
		String pkContract = aggSaleOrderVO.getParent().getAttributeValue("csaleorderid").toString();//销售订单主实体
		//2017-04-19增加判断合同是否已经发送过订货信息
    	NCObject[] ncObjects = MDPersistenceService.lookupPersistenceQueryService().queryBillOfNCObjectByCond(Agxsht001HVO.class , "expordercardno= '"+pkContract+"'", false);
    	if(ncObjects!=null){
    		AggAgxsht001HVO aggHVO = (AggAgxsht001HVO)ncObjects[0].getContainmentObject();
        	Agxsht001HVO axHVO = (Agxsht001HVO)aggHVO.getParentVO(); 
        	String msflage=axHVO.getMsgflag().toString();
        	if("1".equals(msflage)){
        		MessageBox.showMessageDialog("OK", "该合同已订货，订货成功！");
        		return;
        	}
        	if("2".equals(msflage)){
        		MessageBox.showMessageDialog("TIPS", "该合同已订货，订货失败！请联系系统维护人员！");
        		return;
        	}    		
    	}///
		
		
		
	    try {
	    	
	    	String vbillcode = aggSaleOrderVO.getParent().getAttributeValue("vbillcode").toString();//单据号
//	    	String pkContract = aggSaleOrderVO.getParent().getAttributeValue("csaleorderid").toString();//销售订单主实体
 	    	String ccustomerid = aggSaleOrderVO.getParent().getAttributeValue("ccustomerid").toString();
//	    	String tranMode = null == aggSaleOrderVO.getParent().getAttributeValue("ctransporttypeid")? "": aggSaleOrderVO.getParent().getAttributeValue("ctransporttypeid").toString();//运输方式
	    	//买方名称 ，根据客户代码查找客户名称  	
		    nc.pubitf.uapbd.ICustomerPubService service = (nc.pubitf.uapbd.ICustomerPubService)NCLocator.getInstance().lookup(nc.pubitf.uapbd.ICustomerPubService.class);
		    CustomerVO[] customerVOs =service.getCustomerVO(new String[]{ccustomerid}, new String[]{"name"});
		    String customerName="";
		    if(customerVOs[0] == null){
		    	customerName=null;
		    }else{
		    	customerName = customerVOs[0].getName().toString();
		    } 
			//运输方式需要在档案表中查询
	    	String ctransporttypeid = null == aggSaleOrderVO.getParent().getAttributeValue("ctransporttypeid")? null: aggSaleOrderVO.getParent().getAttributeValue("ctransporttypeid").toString();//运输方式
			String tranMode = null;
			String tranModeName = null;
	    	if(null == ctransporttypeid ){
	    		tranMode = null;
	    		tranModeName = null;
	    	}else{
	        	List<Map<String, Object>> tranType =iMsgAGXSHT0001.TransTypeQuery(ctransporttypeid);
	    		tranMode = tranType.get(0).get("code").toString();
	    		tranModeName = tranType.get(0).get("name").toString();
	    	}
	    	//	    	String tranModeName = null == aggSaleOrderVO.getParent().getAttributeValue("ctransporttypeid")? "":aggSaleOrderVO.getParent().getAttributeValue("ctransporttypeid").toString();//运输方式名称
	    	String cmarbaseclassidh = null == aggSaleOrderVO.getParent().getAttributeValue("cmarbaseclassid")? "":aggSaleOrderVO.getParent().getAttributeValue("cmarbaseclassid").toString();//物料编码
	    	nc.pubitf.uapbd.IMaterialBaseClassPubService serviceMatl = (nc.pubitf.uapbd.IMaterialBaseClassPubService)NCLocator.getInstance().lookup(nc.pubitf.uapbd.IMaterialBaseClassPubService.class);
	    	MarBasClassVO[] marBasClassVOs =serviceMatl.queryMaterialBaseClassByPk(cmarbaseclassidh, false);
	    	String prodCode="";
	    	String prodCodeName="";
		    if(marBasClassVOs == null){
		    	prodCode=null;
		    	prodCodeName=null;
		    }else{
		    	prodCode = marBasClassVOs[0].getCode().toString();
		    	prodCodeName =marBasClassVOs[0].getName().toString();
		    }     	
//	    	String prodCode = null == aggSaleOrderVO.getParent().getAttributeValue("cmaterialvid")? "":aggSaleOrderVO.getParent().getAttributeValue("cmaterialvid").toString();//物料编码
//	    	String prodCodeName = null == aggSaleOrderVO.getParent().getAttributeValue("cmaterialvid")? "":aggSaleOrderVO.getParent().getAttributeValue("cmaterialvid").toString();//物料编码
		    String corigcurrencyid = null == aggSaleOrderVO.getParent().getAttributeValue("corigcurrencyid")? "":aggSaleOrderVO.getParent().getAttributeValue("corigcurrencyid").toString();//币种
	    	//根据主键查找币种代码和比重名称
	    	List<Map<String, Object>> curr =iMsgAGXSHT0001.CurrtypeQuery(corigcurrencyid);
			String currency = curr.get(0).get("code").toString();
			String currencyName = curr.get(0).get("name").toString();
//		    String currency = null == aggSaleOrderVO.getParent().getAttributeValue("corigcurrencyid")? "":aggSaleOrderVO.getParent().getAttributeValue("corigcurrencyid").toString();//币种
//	    	String currencyName = null == aggSaleOrderVO.getParent().getAttributeValue("corigcurrencyid")? "":aggSaleOrderVO.getParent().getAttributeValue("corigcurrencyid").toString();//币种名称
			//根据结算方式主键查找结算方式代码和名称
	    	String cbalancetypeid = null == aggSaleOrderVO.getParent().getAttributeValue("cbalancetypeid")? null:aggSaleOrderVO.getParent().getAttributeValue("cbalancetypeid").toString();//结算方式代码
			String settleMode = null;
			String settleModeName = null; 	
	    	if(null == cbalancetypeid ){
	    		settleMode = null;
	    		settleModeName = null; 	
	    	}else{
	        	List<Map<String, Object>> settles =iMsgAGXSHT0001.SettletypeQuery(cbalancetypeid);
	    		settleMode = settles.get(0).get("code").toString();
	    		settleModeName = settles.get(0).get("name").toString(); 		
	    	}
//			String settleMode = null == aggSaleOrderVO.getParent().getAttributeValue("cbalancetypeid")? "":aggSaleOrderVO.getParent().getAttributeValue("cbalancetypeid").toString();//结算方式代码
//	    	String settleModeName = null == aggSaleOrderVO.getParent().getAttributeValue("cbalancetypeid")? "":aggSaleOrderVO.getParent().getAttributeValue("cbalancetypeid").toString();//结算方式名称
	    	String tolWeight = null == aggSaleOrderVO.getParent().getAttributeValue("ntotalnum")? "":aggSaleOrderVO.getParent().getAttributeValue("ntotalnum").toString();//总数量
	    	UFDouble tolMoney = null == aggSaleOrderVO.getParent().getAttributeValue("ntotalorigmny")? null:(UFDouble)aggSaleOrderVO.getParent().getAttributeValue("ntotalorigmny");//总金额
	    	
	    	 * 获取子表数据
	    	 
	    	SaleOrderBVO[] saleOrderBVOs = (SaleOrderBVO[])aggSaleOrderVO.getChildren(SaleOrderBVO.class);
	    	for (SaleOrderBVO saleOrderBVOitm:saleOrderBVOs){
	    		  List<MsgAGXSHT0001> lsMsgAGXSHT0001 = new ArrayList<>();
	    		
	    		 * 主表信息
	    		 
	        	msgAGXSHT0001.setExpOrderCardNo(vbillcode);//订货卡号-国贸
	        	msgAGXSHT0001.setExpOrderCardNo1(pkContract);//订货卡主键-国贸
	        	msgAGXSHT0001.setInExpFlag("N");//内外贸标志
	        	msgAGXSHT0001.setBuyNameChn(customerName);//买方名称
	        	msgAGXSHT0001.setBuyTele(null);//买方电话
	        	msgAGXSHT0001.setSaleMode(null);;//销售方式编码
	        	msgAGXSHT0001.setSaleModeName(null);//销售方式名称
	        	msgAGXSHT0001.setTranMode(tranMode);//运输方式
	        	msgAGXSHT0001.setTranModeName(tranModeName);//运输方式名称
	        	msgAGXSHT0001.setProdCode(prodCode);//销售品种编码
	        	msgAGXSHT0001.setProdCodeName(prodCodeName);//销售品种编码
	        	msgAGXSHT0001.setCurrency(currency);//币种
	        	msgAGXSHT0001.setCurrencyName(currencyName);//币种名称
	        	msgAGXSHT0001.setSettleMode(settleMode);//结算方式代码
	        	msgAGXSHT0001.setSettleModeName(settleModeName);//结算方式名称
	        	msgAGXSHT0001.setTolWeight(tolWeight);//总重量
	        	msgAGXSHT0001.setTolMoney(BigDecimal.valueOf(tolMoney.doubleValue()));//总金额
	        	
	        	 * 子表信息
	        	 	
	    		msgAGXSHT0001.setExpOrderCardItemNo(null == saleOrderBVOitm.getAttributeValue("csaleorderbid")?"":saleOrderBVOitm.getAttributeValue("csaleorderbid").toString());//出口合同子实体->订货卡子项号-国贸
//	    		msgAGXSHT0001.setExpOrderCardNo(null == saleOrderBVOitm.getAttributeValue("vbillcode")?"":saleOrderBVOitm.getAttributeValue("vbillcode").toString());//出口合同号->订货卡号-国贸
	    		UFDouble orderWt = null == saleOrderBVOitm.getAttributeValue("nastnum")?UFDouble.ZERO_DBL:(UFDouble)saleOrderBVOitm.getAttributeValue("nastnum");
	    		msgAGXSHT0001.setOrderWt(BigDecimal.valueOf(orderWt.doubleValue()));//数量->订货重量
	    		msgAGXSHT0001.setOrderQty(BigDecimal.valueOf(orderWt.doubleValue()));//数量->订货数量
	    		msgAGXSHT0001.setSpecDesc(null == saleOrderBVOitm.getAttributeValue("agtexture")?"":saleOrderBVOitm.getAttributeValue("agtexture").toString());//钢轨规格->规格描述
	    		UFDouble thick = null == saleOrderBVOitm.getAttributeValue("thicknessmm")?UFDouble.ZERO_DBL:(UFDouble)saleOrderBVOitm.getAttributeValue("thicknessmm");
	    		msgAGXSHT0001.setThick(BigDecimal.valueOf(thick.doubleValue()));//厚度/壁厚公制->厚度
	    		UFDouble width = null ==saleOrderBVOitm.getAttributeValue("widthmm")?UFDouble.ZERO_DBL:(UFDouble)saleOrderBVOitm.getAttributeValue("widthmm");
	    		msgAGXSHT0001.setWidth(BigDecimal.valueOf(width.doubleValue()));//宽度公制->宽度
	    		UFDouble lengthMin = null ==saleOrderBVOitm.getAttributeValue("lengthmm")?UFDouble.ZERO_DBL:(UFDouble)saleOrderBVOitm.getAttributeValue("lengthmm");
	    		msgAGXSHT0001.setLengthMin(BigDecimal.valueOf(lengthMin.doubleValue()));//长度公制->长度下限
	    		msgAGXSHT0001.setLengthMax(null);//长度上限
	    		UFDouble actUnitprice = null ==saleOrderBVOitm.getAttributeValue("nqtorigtaxprice")?UFDouble.ZERO_DBL:(UFDouble)saleOrderBVOitm.getAttributeValue("nqtorigtaxprice");
	    		msgAGXSHT0001.setActUnitprice(BigDecimal.valueOf(actUnitprice.doubleValue()));//主单价->销售单价
	    		UFDouble orderDpstAmt = null ==saleOrderBVOitm.getAttributeValue("norigtaxmny")?UFDouble.ZERO_DBL:(UFDouble)saleOrderBVOitm.getAttributeValue("norigtaxmny");
	    		msgAGXSHT0001.setOrderDpstAmt(BigDecimal.valueOf(orderDpstAmt.doubleValue()));//金额->金额
	    		msgAGXSHT0001.setRemark(null);//备注
	    		String cmaterialvid1 = null == saleOrderBVOitm.getAttributeValue("cmaterialvid")?"":saleOrderBVOitm.getAttributeValue("cmaterialvid").toString();
	    		String shopSign =iMsgAGXSHT0001.ShopSignQuery(cmaterialvid1);
	    		msgAGXSHT0001.setShopSign(shopSign);//物料编码->牌号
//	    		msgAGXSHT0001.setShopSign(null == saleOrderBVOitm.getAttributeValue("cmaterialvid")?"":saleOrderBVOitm.getAttributeValue("cmaterialvid").toString());//物料编码->牌号		   		
	    		msgAGXSHT0001.setProdStd(null == saleOrderBVOitm.getAttributeValue("agproductstandard")?"":saleOrderBVOitm.getAttributeValue("agproductstandard").toString());//生产标准->产品技术标准	
	    		Integer deliveryTolLower = null == saleOrderBVOitm.getAttributeValue("widthtolerance")? 0:Integer.valueOf(saleOrderBVOitm.getAttributeValue("widthtolerance").toString());
	    		msgAGXSHT0001.setDeliveryTolLower(deliveryTolLower);//宽度公差->交货公差下限
	    		Integer deliveryTolUpper = null == saleOrderBVOitm.getAttributeValue("lengthtolerance")? 0:Integer.valueOf(saleOrderBVOitm.getAttributeValue("lengthtolerance").toString());
	    		msgAGXSHT0001.setDeliveryTolUpper(deliveryTolUpper);//长度公差->交货公差上限
	    		msgAGXSHT0001.setShortSizeRate(null);//订货短尺率
	    		msgAGXSHT0001.setShortSizeLow(null);//订货短尺下限
	    		msgAGXSHT0001.setShortSizeUp(null);//订货短尺上限
	    		msgAGXSHT0001.setMultiSizeNum(0);//倍尺数
	    		UFDouble temp=UFDouble.ZERO_DBL;
	    		msgAGXSHT0001.setSingleLength(BigDecimal.valueOf(temp.doubleValue()));//单倍尺长度
	    		
	    		 * 拼成msgAGXSHT0001Head
	    		 
	    		lsMsgAGXSHT0001.add(msgAGXSHT0001);
	    		msgAGXSHT0001Head.setMsgId("MsgAGXSHT0001");
	    		msgAGXSHT0001Head.setResourceId("GMERP");
	    		msgAGXSHT0001Head.setMsgBody(lsMsgAGXSHT0001);
	    		 //调用服务器中间层方法
	    	    IMsgAGXSHT0001sat is = NCLocator.getInstance().lookup(IMsgAGXSHT0001sat.class);
	    	   
	    	    try {
	    	    	//调用单据转换规则，写入中间表,executeVOChange（交易类型，下游单据类型）
	    	    	AggregatedValueObject[] srcVosAfterFilter = new AggregatedValueObject[] {
	    	    			aggSaleOrderVO //中间表
							    };
	    	    	//中间表AGGVO
	    	    	AggAgxsht001HVO aggAgxsht001HVO;
	    	    	aggAgxsht001HVO = (AggAgxsht001HVO) PfUtilTools.runChangeData("30-Cxx-pgdh", "DW40", srcVosAfterFilter[0]);//5720-Cxx-pgdh是出口合同（上游）单据交易类型，DW40是下游单据类型
	    	    	aggAgxsht001HVO.getParent().setStatus(VOStatus.NEW);
	    			//调用单据转换规则，写入中间表
//	    			AggLmpgjkHVO[] aggLmpgjkHVO = PfServiceScmUtil.executeVOChange("5720-Cxx-004", "5730", new SaleOrderVO[]{aggSaleOrderVO});//5720-Cxx-004是上游单据交易类型，5730是下游单据类型
	    			String result=is.MsgAGXSHT0001(msgAGXSHT0001Head);
	    			if ("1".equals(result)){
	    				aggAgxsht001HVO.getParent().setAttributeValue("msgflag", 2);
	    				aggAgxsht001HVO.getParent().setAttributeValue("dr", 0);
	    				HYPubBO bo = new HYPubBO();
							try {
								bo.saveBill(aggAgxsht001HVO);
							} catch (UifException e2) {
								// TODO 自动生成的 catch 块
								e2.printStackTrace();
							}
						
	    			}else{
	    				aggAgxsht001HVO.getParent().setAttributeValue("msgflag", 1);
	    				aggAgxsht001HVO.getParent().setAttributeValue("dr", 0);
	    				HYPubBO bo = new HYPubBO();
						try {
							bo.saveBill(aggAgxsht001HVO);
						} catch (UifException e1) {
							// TODO 自动生成的 catch 块
							e1.printStackTrace();
						}
						
	    			}
	    		}catch (Exception e1) {
	    			// TODO 自动生成的 catch 块
	    			e1.printStackTrace();
	    		}
	    	}
	    	MessageBox.showMessageDialog("OK", "该合同订货成功！");
	    }catch (Exception be) {
	      ExceptionUtils.wrappException(be);
	    }*/
	    
	}

	/**
	 * @return model
	 */
	public nc.ui.pubapp.uif2app.model.BillManageModel getModel() {
		return model;
	}

	/**
	 * @param model 要设置的 model
	 */
	public void setModel(nc.ui.pubapp.uif2app.model.BillManageModel model) {
		this.model = model;
	}

	/**
	 * @return editor
	 */
	public nc.ui.so.m30.billui.view.SaleOrderBillForm getEditor() {
		return editor;
	}

	/**
	 * @param editor 要设置的 editor
	 */
	public void setEditor(nc.ui.so.m30.billui.view.SaleOrderBillForm editor) {
		this.editor = editor;
	}

}
