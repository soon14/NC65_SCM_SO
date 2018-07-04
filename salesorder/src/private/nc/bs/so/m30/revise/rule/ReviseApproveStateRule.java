package nc.bs.so.m30.revise.rule;

import nc.bs.so.m30.rule.approve.ApproveStateRule;

/**
 * 
 * @description
 * 销售订单修订审批后
 * @scene
 * 销售订单修订审批后对新增行订单状态的处理（特殊处理）
 * @param
 * 无
 *
 * @since 6.5
 * @version 2015-10-19 下午1:47:43
 * @author zhangby5
 */
public class ReviseApproveStateRule extends ApproveStateRule {

/*	@Override
	protected SaleOrderViewVO[] billToViewConvertor(SaleOrderVO[] vos) {
		List<SaleOrderViewVO> viewList = new ArrayList<>();
		for(SaleOrderVO vo : vos){
			SaleOrderBVO[] bvos = vo.getChildrenVO();
			for(SaleOrderBVO bvo : bvos){
				if (VOStatus.NEW == bvo.getStatus()) {
					SaleOrderViewVO viewVO = new SaleOrderViewVO();
					viewVO.setHead(vo.getParentVO());
					viewVO.setBody(bvo);
					viewList.add(viewVO);
				}
			}
		}
		return ListUtil.toArray(viewList);
	}*/
}
