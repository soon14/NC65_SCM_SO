package nc.vo.so.m30.vochange;

import java.awt.event.ActionEvent;
import java.util.ArrayList;
import java.util.List;

import nc.bs.framework.common.NCLocator;
import nc.itf.bd.material.baseinfo.IMaterialBaseInfoService;
import nc.itf.uap.IUAPQueryBS;
import nc.jdbc.framework.processor.BeanProcessor;
import nc.jdbc.framework.processor.ColumnProcessor;
import nc.pubitf.para.SysInitQuery;
import nc.ui.so.m30.billrefForLS41.AddLS41Action;
import nc.ui.trade.business.HYPubBO_Client;
import nc.vo.bd.material.MaterialVO;
import nc.vo.bd.material.marbasclass.MarBasClassVO;
import nc.vo.bd.material.measdoc.MeasdocVO;
import nc.vo.lm.lsdlxy.AggLsxywtHVO;
import nc.vo.lm.lsdlxy.LsxywtbBVO;
import nc.vo.pf.change.ChangeVOAdjustContext;
import nc.vo.pf.change.IChangeVOAdjust;
import nc.vo.pu.m20.entity.PraybillItemVO;
import nc.vo.pub.AggregatedValueObject;
import nc.vo.pub.BusinessException;
import nc.vo.pub.lang.UFBoolean;
import nc.vo.pub.lang.UFDateTime;
import nc.vo.pub.lang.UFDouble;
import nc.vo.pub.para.SysInitVO;
import nc.vo.so.m30.entity.SaleOrderBVO;
import nc.vo.so.m30.entity.SaleOrderVO;

public class M4LSDLXYChangeVOAdjust implements IChangeVOAdjust{

	@Override
	public AggregatedValueObject adjustAfterChange(AggregatedValueObject srcVO,
			AggregatedValueObject destVO, ChangeVOAdjustContext adjustContext)
			throws BusinessException {
		// TODO 自动生成的方法存根
		
		AggregatedValueObject[] srcVOs = new AggregatedValueObject[] {
			 srcVO
	    };
		AggregatedValueObject[] destVOs = new AggregatedValueObject[] {
			 destVO
		};
		AggregatedValueObject[] bills =
			 this.batchAdjustAfterChange(srcVOs, destVOs, adjustContext);
		return bills[0];		
	}

	@Override
	public AggregatedValueObject adjustBeforeChange(AggregatedValueObject arg0,
			ChangeVOAdjustContext arg1) throws BusinessException {
		// TODO 自动生成的方法存根
		return null;
	}
	
	/*
	 * 1. 根据销售订单的来源单据主键查中间表里的物料编码，计量单位名称，组织，
	 * 2.通过中间件表物料编码，计量单位名称，查出对应的相关档案主键，赋值给请购单对应的字段
	 */
	@Override
	public AggregatedValueObject[] batchAdjustAfterChange(
			AggregatedValueObject[] srcVOs, AggregatedValueObject[] destVOs,
			ChangeVOAdjustContext adjustContext) throws BusinessException {
		// TODO 自动生成的方法存根
		IUAPQueryBS bs = NCLocator.getInstance().lookup(IUAPQueryBS.class);
		if (destVOs == null || destVOs.length == 0) {
			return destVOs;
		}
		SaleOrderVO newaggvo = new SaleOrderVO();
		newaggvo.setParentVO(destVOs[0].getParentVO());
		List<SaleOrderBVO> newparbvos = new ArrayList<SaleOrderBVO>();

		for (AggregatedValueObject aggvo : destVOs) {
			SaleOrderBVO[] sobvo=(SaleOrderBVO[]) aggvo.getChildrenVO();
			for(SaleOrderBVO bvo:sobvo){

				String zwmc=bvo.getCmaterialid();//中文名稱 
				String ggxh=bvo.getCmaterialvid();// 規格型號
				String name="";
				if(ggxh==null){
					name=zwmc;
				}else{
					name=zwmc+"-"+ggxh;
				}
				String sqlmact = "select * from bd_material where name ='"+name+"' and nvl(dr,0)=0 ";
				MaterialVO matervo = (MaterialVO) bs.executeQuery(sqlmact,
						new BeanProcessor(MaterialVO.class));
				if (matervo != null) {
					bvo.setCmaterialid(matervo.getPk_material());// 销售订单子表物料编码
					bvo.setCmaterialvid(matervo.getPk_material());// 销售订单子表物料编码
					bvo.setCunitid(matervo.getPk_measdoc());// 计量单位
					bvo.setCastunitid(matervo.getPk_measdoc());// 单位
				}
				int size = 0;
				if (newparbvos != null) {
					size = newparbvos.size();
				}
				bvo.setCrowno((size + 1) + "0");// 重新赋值行号
				newparbvos.add(bvo);
			}
		}		
		newaggvo.setChildrenVO(newparbvos.toArray(new SaleOrderBVO[newparbvos.size()]));
		return new AggregatedValueObject[] { newaggvo };			
	}

	@Override
	public AggregatedValueObject[] batchAdjustBeforeChange(
			AggregatedValueObject[] arg0, ChangeVOAdjustContext arg1)
			throws BusinessException {
		// TODO 自动生成的方法存根
		return null;
	}	

}
