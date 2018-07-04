package nc.bs.so.m4331.extend.backgroud;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import nc.bs.dao.BaseDAO;
import nc.bs.dao.DAOException;
import nc.bs.pub.pa.PreAlertObject;
import nc.bs.pub.pa.PreAlertReturnType;
import nc.bs.pub.taskcenter.BgWorkingContext;
import nc.bs.pub.taskcenter.IBackgroundWorkPlugin;
import nc.jdbc.framework.processor.BeanListProcessor;
import nc.jdbc.framework.processor.ResultSetProcessor;
import nc.vo.lm.erpsbbjjhjk.ErpsbbjjhjkHVO;
import nc.vo.pub.BusinessException;

/**
 * <p>本类主要实现功能：</p>
 *
 * <li>功能描述：</li>
 * <li></li>
 * <li></li>
 * 
 * @author lyw
 * @version 6.5
 * @time 2017年7月5日下午2:15:20
 */
public abstract class M4331ToSaleoutBgPlugin implements IBackgroundWorkPlugin {

	/* （非 Javadoc）
	 * @see nc.bs.pub.taskcenter.IBackgroundWorkPlugin#executeTask(nc.bs.pub.taskcenter.BgWorkingContext)
	 */
	@Override
	public PreAlertObject executeTask(BgWorkingContext bgwc)
			throws BusinessException {
		// TODO 自动生成的方法存根
		PreAlertObject retObj = new PreAlertObject();
		retObj.setReturnType(PreAlertReturnType.RETURNMESSAGE);
		retObj.setMsgTitle("ERP设备交货明细生成销售出库单后台任务处理结果反馈");
		StringBuffer retinfo = new StringBuffer();
		Map<String, ArrayList<ErpsbbjjhjkHVO>> mapvos = getDestTableInfo();
		Map<String, List<String[]>> map_mess = genSaleouttBillInfo(mapvos);
		this.updateErpData(map_mess);
		if (map_mess != null && map_mess.size() > 0) {
			List<String[]> error = map_mess.get("error");
			List<String[]> success = map_mess.get("success");
			if (error != null && error.size() > 0) {
				for (int i = 0; i < error.size(); i++) {
					String str[] = error.get(i);
					retinfo.append(str[1]);
				}
			} 
			if (success != null && success.size() > 0 ) {
				for (int i = 0; i < success.size(); i++) {
					String str[] = success.get(i);
					retinfo.append(str[1]);
				}
			}
		} else {
			retinfo.append("无需处理的验收单据！");
		}
		retObj.setReturnObj(retinfo.toString());
		return retObj;
	}

	/**
	 * 更新备件验收单，处理状态及处理信息
	 * 处理状态：hdef1 0-未处理，1-处理成功，2-处理失败
	 * 处理结果信息：hdef2
	 * @param map_mess
	 * @throws DAOException 
	 */
	private void updateErpData(Map<String, List<String[]>> map_mess) throws DAOException {
		// TODO 自动生成的方法存根
		String upstr = null;
		if (map_mess != null && map_mess.size() > 0) {
			List<String[]> error = map_mess.get("error");
			List<String[]> success = map_mess.get("success");
			if (error != null && error.size() > 0) {
				for (int i = 0; i < error.size(); i++) {
					String str[] = error.get(i);
					upstr = "update MSG_ERPSBBJJHJK set hdef1='2',hdef2='处理失败' where ysd = '" + str[0] +"'";
					getBD().executeUpdate(upstr);
				}
			}
			if (success != null && success.size() > 0 ) {
				for (int i = 0; i < success.size(); i++) {
					String str[] = success.get(i);
					upstr = "update MSG_ERPSBBJJHJK set hdef1='1',hdef2='处理成功' where ysd = '" + str[0] +"'";
					getBD().executeUpdate(upstr);
				}
			}
		}
	}

	/**
	 * 生成销售出库单
	 * @param Map<String, ArrayList<ErpsbbjjhjkHVO>> vos
	 * @return Map<String, List<String[]>> 数据处理信息
	 * @throws BusinessException 
	 * @throws DAOException 
	 */
	public abstract Map<String, List<String[]>> genSaleouttBillInfo(Map<String, ArrayList<ErpsbbjjhjkHVO>> mapvos) throws DAOException, BusinessException;

	/**
	 * 获取目标数据,获取未处理的验收单信息
	 * @return SuperVO[]
	 * @throws DAOException 
	 */
	private Map<String, ArrayList<ErpsbbjjhjkHVO>> getDestTableInfo() throws DAOException {
		// TODO 自动生成的方法存根
		/**
		 * 获取未处理的验收单号，做为生成销售出库单的依据，每个验收单号对应一个销售出库单
		 */
		StringBuffer recenosql = new StringBuffer();
		recenosql.append("select distinct ysd from msg_erpsbbjjhjk");
		recenosql.append(" where dr = 0 and hdef1 = 0 ");
		ArrayList<String> receno = (ArrayList<String>) getBD().executeQuery(recenosql.toString(), new ResultSetProcessor() {
			@Override
			public Object handleResultSet(ResultSet rs) throws SQLException {
				// TODO 自动生成的方法存根
				ArrayList<String>  list = new ArrayList<String>();
				while (rs.next()) {
					list.add(rs.getString(1));
				}
				return list;
			}
		});
		/**
		 * 根据未处理的能收单单号，获取验收单信息
		 */
		Map<String,ArrayList<ErpsbbjjhjkHVO>> mapvos = new HashMap<String,ArrayList<ErpsbbjjhjkHVO>>();
		for (int i=0; i<receno.size();i++) {
			StringBuffer querysql = new StringBuffer();
			querysql.append( "select * from msg_erpsbbjjhjk ");
			querysql.append(" where dr = 0 and hdef1 = 0 and ysd='");
			querysql.append( receno.get(i).toString());
			querysql.append("'");
			ArrayList<ErpsbbjjhjkHVO> vos = (ArrayList<ErpsbbjjhjkHVO>) getBD().executeQuery(querysql.toString(), new BeanListProcessor(ErpsbbjjhjkHVO.class));
			mapvos.put( receno.get(i).toString(), vos);
		}
		return mapvos;
	}
	BaseDAO bd = null;
	public BaseDAO getBD() {
		if (bd == null) {
			bd = new BaseDAO();
		}
		return bd;
		
	}
}
