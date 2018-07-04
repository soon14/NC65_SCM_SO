package nc.impl.so.mbuylargess.action;

import nc.bs.so.buylargess.maintain.DeleteMblargessInBP;
import nc.vo.so.mbuylargess.entity.BuyLargessVO;

public class DeleteBuyLargessAction {
	public void deleteBuylargess(BuyLargessVO[] todelvos) {
		DeleteMblargessInBP action = new DeleteMblargessInBP();
		action.delete(todelvos);
	}
}
