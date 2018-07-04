/**   
 * Copyright  2018 Yonyou. All rights reserved.
 * @Description: TODO
 * @author: wangzy   
 * @date: 2018年3月22日 下午3:33:51 
 * @version: V6.5   
 */
package nc.ui.pub.bill.fixblob;

import java.awt.event.ActionEvent;
import java.util.List;

import nc.bs.framework.common.NCLocator;
import nc.itf.pubapp.pub.smart.IBillQueryService;
import nc.ui.pubapp.uif2app.actions.pflow.CommitScriptAction;
import nc.ui.uif2.model.BillManageModel;
import nc.vo.bd.meta.IBDObject;
import nc.vo.pubapp.pattern.model.entity.bill.AbstractBill;

/**
 * @Description: 由于提交或者审核后显示到界面的blob值不对，所以调刷新方法
 *               （实际需要改底层基于元数据的MD查询引擎，系统存在反序列化的致命bug ，涉及到的问题太多，先不改了）
 * @author: wangzy
 * @date: 2018年3月22日 下午3:33:51
 */
public class ReQuery2FixBlob {

	/**
	 * @Title: reFreshDate
	 * @Description: 根据传入的Model进行重新查询
	 * @throws Exception
	 * @return: void
	 */
	public static void reFreshDate(BillManageModel bmModel) throws Exception {
		Object data = bmModel.getSelectedData();
		if (data == null) {
			return;
		}
		String pk = "";
		int i = 0;
		Class<AbstractBill> clazz = null;
		IBDObject target = bmModel.getBusinessObjectAdapterFactory()
				.createBDObject(data);
		pk = (String) target.getId();
		clazz = (Class<AbstractBill>) data.getClass();
		if (clazz == null) {
			return;
		}

		// 注意：下面的写法只是暂时的写法，为了暂时完成CQ问题，这段代码以后肯定要修改的，在等待批查的接口
		// 否则会影响效率，产生很多连接数
		IBillQueryService billQuery = NCLocator.getInstance().lookup(
				IBillQueryService.class);
		AbstractBill bills = billQuery.querySingleBillByPk(clazz, pk);

		if (bills == null) {
			return;
		}
		bmModel.directlyUpdate(bills);
	}

}
