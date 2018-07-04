package nc.impl.so.m38.migrate.action;

import java.util.List;
import java.util.Map;

import nc.impl.pubapp.pattern.database.DataAccessUtils;
import nc.impl.so.m38.migrate.constant.SQLCenter;
import nc.vo.pub.BusinessException;

/**
 * 预订单迁移完成后需要更新影响到的下游单据
 * @author liylr
 *
 */
public class PreOrderDestBillUpdateAction{
	public void update(Map<String, String> oldNewTrantypeIdMap) throws BusinessException{
		SQLCenter center = new SQLCenter();
		List<String> list = center.getUpdateSqls(oldNewTrantypeIdMap);
		
		for(String sql : list){			
			DataAccessUtils dau = new DataAccessUtils();
			dau.update(sql);
		}
	}
}
