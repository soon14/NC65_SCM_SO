package nc.ui.so.m30.refmodel;

import nc.ui.bd.ref.AbstractRefModel;

public class SaleOrderRefModel extends AbstractRefModel {
	

	public SaleOrderRefModel() {
		super();
		init();
	}
	
	private void init(){
	
		setRefNodeName("销售订单");
		setRefTitle("销售订单");
		setFieldCode(new String[] {
				//"csaleorderid",
				"vbillcode",
				//"pk_org",
				//"pk_org_v",
				"orgname",
				//"cdeptid",
				//"cdeptvid",
				"deptname",
				//"ccustomerid",
				"customername",
				//"pk_currencyid",
				"currtypename"
				});
		setFieldName(new String[] {
				//"主键",
				"销售订单号",
				//"销售组织id",
				//"销售组织版本id",
				"销售组织",
				//"部门id",
				//"部门版本id",
				"部门",
				//"客户id",
				"客户",
				//"币种id",
				"币种"
				});
		setHiddenFieldCode(new String[] {
				"csaleorderid",
				"pk_org",
				"pk_org_v",
				"cdeptid",
				"cdeptvid",
				"ccustomerid",
				"pk_currencyid"
			});
		setPkFieldCode("csaleorderid");
		setWherePart("1=1");
		setTableName("v_so_saleorder");
		setRefCodeField("vbillcode");
		setRefNameField("vbillcode");
	
	}
	
}
