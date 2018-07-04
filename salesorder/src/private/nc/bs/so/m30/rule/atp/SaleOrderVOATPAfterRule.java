package nc.bs.so.m30.rule.atp;

import java.util.ArrayList;
import java.util.List;

import nc.impl.pubapp.pattern.rule.IRule;
import nc.itf.so.pub.ref.ic.m4c.SOATPprocess;
import nc.vo.pub.BusinessException;
import nc.vo.pubapp.pattern.exception.ExceptionUtils;
import nc.vo.scmpub.res.billtype.SOBillType;
import nc.vo.so.m30.entity.SaleOrderVO;

/**
 * @description 销售订单动作后物料库存可用量检查
 * @scene 销售订单审批、新增、修改、删除保存动作、冻结动作后
 * @param 无
 * modify by wangzy 王梓懿 2017-04-22
 */
public class SaleOrderVOATPAfterRule implements IRule<SaleOrderVO> {

	@Override
	public void process(SaleOrderVO[] vos) {
		try {
			// 判断是否攀钢接口传过来的
			List<String> transtype = new ArrayList<String>();
			for (SaleOrderVO saleOrderVO : vos) {
				String ctranstypecode = (String) saleOrderVO.getParent()
						.getAttributeValue("vtrantypecode");
				transtype.add(ctranstypecode);
			}
			if (transtype.contains("30-Cxx-05")) {
				return;
			} else {
				SOATPprocess.modifyATPAfter(SOBillType.Order.getCode(), vos);
			}
		} catch (BusinessException e) {

			ExceptionUtils.wrappException(e);
		}
	}

}
