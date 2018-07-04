package nc.vo.so.salequotation.keyrela;

import nc.vo.so.pub.keyvalue.SOKeyRela;
import nc.vo.so.salequotation.entity.SalequotationBVO;
import nc.vo.so.salequotation.entity.SalequotationHVO;

public class SalequoKeyRela extends SOKeyRela{
	
	/** 单据日期, 取报价日期 */
	@Override
    public String getDbilldateKey() {
		return SalequotationHVO.DQUOTEDATE;
	}
	/** 开票客户，取客户*/
	@Override
	public String getCinvoicecustidKey() {
	    return SalequotationHVO.PK_CUSTOMER;
	}
	
	@Override
	public String getCmaterialvidKey() {
	    return SalequotationBVO.PK_MATERIAL_V;
	}

}
