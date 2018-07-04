package nc.vo.so.m32.rule;

import nc.impl.pubapp.pattern.rule.IRule;
import nc.itf.ecpubapp.pattern.database.DataAccessUtils;
import nc.vo.pub.VOStatus;
import nc.vo.pub.lang.UFDate;
import nc.vo.so.m32.entity.SaleInvoiceVO;

public class SaveHyfjsdAfterRule implements IRule<SaleInvoiceVO>{

	@Override
	public void process(SaleInvoiceVO[] vos) {
		// TODO 自动生成的方法存根
		//and is_next='2'  1 生成 2 未生成
		if(null != vos){
			DataAccessUtils queryUtil = new DataAccessUtils();
			for(SaleInvoiceVO ivo : vos){
				if(null == ivo.getChildrenVO() || ivo.getChildrenVO().length == 0) continue;
				String src_type = (String) ivo.getChildrenVO()[0].getVsrctype();
				if(null == src_type) continue;
				if(!"HY01".equals(src_type)) continue;
				String src_pk = (String) ivo.getChildrenVO()[0].getCsrcid();
				String sql = null;
				if(ivo.getParentVO().getStatus() == VOStatus.NEW){
					sql = "update LM_SEASETT set is_next='1', ts = '"+ new UFDate() +"' where pk_seasett = '" + src_pk +"'";
				}else if(ivo.getParentVO().getStatus() == VOStatus.DELETED){
					sql = "update LM_SEASETT set is_next='2', ts = '"+ new UFDate() +"' where pk_seasett = '" + src_pk +"'";
				}
				if(null == sql) continue;
				queryUtil.update(sql);
			}
			for(SaleInvoiceVO ivo : vos){
				if(null == ivo.getChildrenVO() || ivo.getChildrenVO().length == 0) continue;
				String src_type = (String) ivo.getChildrenVO()[0].getVsrctype();
				if(null == src_type) continue;
				if(!"LM21".equals(src_type)) continue;
				String src_pk = (String) ivo.getChildrenVO()[0].getCsrcid();
				String sql = null;
				if(ivo.getParentVO().getStatus() == VOStatus.NEW){
					sql = "update lm_yffyjsd set is_next='Y', ts = '"+ new UFDate() +"' where pk_sett_payable = '" + src_pk +"'";
				}else if(ivo.getParentVO().getStatus() == VOStatus.DELETED){
					sql = "update lm_yffyjsd set is_next='N', ts = '"+ new UFDate() +"' where pk_sett_payable = '" + src_pk +"'";
				}
				if(null == sql) continue;
				queryUtil.update(sql);
			}
		}
	}

}
