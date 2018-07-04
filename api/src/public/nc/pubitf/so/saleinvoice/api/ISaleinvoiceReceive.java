package nc.pubitf.so.saleinvoice.api;

import java.util.List;
import java.util.Map;

import nc.vo.lm.pgjdjjjsxx.AggCgag000003HVO;
import nc.vo.so.m32.entity.SaleInvoiceHVO;
import nc.vo.so.m32.entity.SaleInvoiceVO;
import nc.vo.so.saleinvoice.MsgCGAG000003;


public interface ISaleinvoiceReceive {
	
//	public Map<String, Object> SaleInvoice(List<MsgCGAG000003> list);
//	
//	
//	public Map<String, Object> SaleInvoiceLj(List<MsgCGAG000003> list);
	
	//接收数据
	public String receiveMsgCGAG000003();
	//根据采购合同号、采购合同行号获取销售出库单上的物料编码、规格、型号、计量单位字段
	public List<Map<String, Object>> getSaleInfo(String bpoId,String bpoLineId);
	//发送数据
	public boolean sendMsgAGCG000003(SaleInvoiceVO saleInvoiceVO);
	//回写中间表
	public void writeBack(AggCgag000003HVO aggCgag000003HVO);
	//发送数据后向中间表插入
	public void sendZjbInsert(SaleInvoiceVO saleInvoiceVO); 
}
