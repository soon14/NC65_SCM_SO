package nc.pubimpl.so.mbuylargess.pub;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import nc.impl.pubapp.pattern.data.bill.BillQuery;
import nc.impl.pubapp.pattern.data.bill.EfficientBillQuery;
import nc.impl.pubapp.pattern.database.IDQueryBuilder;
import nc.impl.so.mbuylargess.action.DeleteBuyLargessAction;
import nc.impl.so.mbuylargess.action.InsertBuyLargessAction;
import nc.pubitf.so.mbuylargess.pub.IBuylargessOperate;
import nc.vo.pub.BusinessException;
import nc.vo.pubapp.pattern.exception.ExceptionUtils;
import nc.vo.scmpub.util.ArrayUtil;
import nc.vo.so.mbuylargess.entity.BuyLargessHVO;
import nc.vo.so.mbuylargess.entity.BuyLargessVO;

/**
 * 买赠设置对外提供的接口实现
 * 
 * @since 6.3
 * @version 2014-04-09 15:37:11
 * @author 刘景
 */
public class BuylargessOperateImpl implements IBuylargessOperate {

  @Override
  public Map<Integer, String> checkBuylargessVO(BuyLargessVO[] buylargessVOs)
      throws BusinessException {
    Map<Integer, String> indexAndErroMsgMap = null;
    try {
      indexAndErroMsgMap =
          new BuyLargessSaveDataCheck().getCheckSaveErroMsgMap(buylargessVOs);
    }
    catch (Exception exception) {
      ExceptionUtils.marsh(exception);
    }
    return indexAndErroMsgMap;
  }

  @Override
  public BuyLargessVO[] saveBuylargessVO(BuyLargessVO[] buylargessVOs)
      throws BusinessException {
    BuyLargessVO[] buyLargessVOs = null;
    try {
      buyLargessVOs =
          new InsertBuyLargessAction().insertBuylargess(buylargessVOs);
    }
    catch (Exception exception) {
      ExceptionUtils.marsh(exception);
    }
    return buyLargessVOs;
  }

  @Override
  public void deleteBuylargessVO(String[] pk_buylargess)
      throws BusinessException {
    try {
      BillQuery<BuyLargessVO> query =
          new BillQuery<BuyLargessVO>(BuyLargessVO.class);
      BuyLargessVO[] buyLargessVOs = query.query(pk_buylargess);

      DeleteBuyLargessAction delete = new DeleteBuyLargessAction();
      delete.deleteBuylargess(buyLargessVOs);
    }
    catch (Exception exception) {
      ExceptionUtils.marsh(exception);
    }
  }
  
	@Override
	public void deleteBuylargessVOs(BuyLargessVO[] buyLargessVOs)
			throws BusinessException {
		try {
			DeleteBuyLargessAction delete = new DeleteBuyLargessAction();
			delete.deleteBuylargess(buyLargessVOs);
		} catch (Exception exception) {
			ExceptionUtils.marsh(exception);
		}
	}

	@Override
	public Map<String, List<BuyLargessVO>> queryBuylargessVOsByActIDs(
			String[] cmarketactids, String whereCon) throws BusinessException {
		try {
			if (ArrayUtil.isEmpty(cmarketactids)) {
				return null;
			}

			IDQueryBuilder buile = new IDQueryBuilder();
			String insql = buile.buildSQL("so_buylargess."
					+ BuyLargessHVO.CMARKETACTID, cmarketactids);

			EfficientBillQuery<BuyLargessVO> query = new EfficientBillQuery<BuyLargessVO>(
					BuyLargessVO.class);
			String sql = " from so_buylargess so_buylargess where so_buylargess.dr = 0 and "
					+ insql;
			if (whereCon != null && whereCon.trim().length() > 0) {
				sql = sql + " and " + whereCon;
			}

			BuyLargessVO[] results = query.query(sql);

			if (ArrayUtil.isEmpty(results)) {
				return null;
			}

			Map<String, List<BuyLargessVO>> resultMap = new HashMap<String, List<BuyLargessVO>>();
			for (BuyLargessVO buyLargessVO : results) {
				String cactid = buyLargessVO.getParentVO().getCmarketactid();
				if (cactid == null) {
					continue;
				}
				if (resultMap.get(cactid) == null) {
					resultMap.put(cactid, new ArrayList<BuyLargessVO>());
				}
				resultMap.get(cactid).add(buyLargessVO);
			}
			return resultMap;
		} catch (Exception exception) {
			ExceptionUtils.marsh(exception);
		}
		return null;
	}
}
