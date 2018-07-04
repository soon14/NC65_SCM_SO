package nc.vo.so.m30.util;

import java.util.List;
import java.util.Map;

import nc.bs.framework.common.NCLocator;
import nc.bs.logging.Logger;
import nc.itf.uap.IUAPQueryBS;
import nc.jdbc.framework.processor.ArrayListProcessor;
import nc.pubitf.org.IDeptPubService;
import nc.pubitf.rbac.IUserPubService;
import nc.pubitf.uapbd.IPsndocPubService;
import nc.vo.org.DeptVO;
import nc.vo.pub.BusinessException;
import nc.vo.pubapp.AppContext;

/**
 * 鞍钢特殊业务工具类
 *
 */
public class SpecialBusiUtil {

	public boolean isTheDept(String[] deptids){
		if(deptids != null && deptids.length > 0){
			IDeptPubService deptSer = NCLocator.getInstance().lookup(IDeptPubService.class);
			DeptVO[] deptvos = null;
			try {
				deptvos = deptSer.queryDeptVOsByPKS(deptids);
			} catch (BusinessException e) {
				Logger.error(e.getMessage(), e);
			}
			
			//根据部门编码判断是否是 非钢产品贸易部
			if(deptvos != null && deptvos.length > 0){
				for(DeptVO deptvo : deptvos){
					String code = deptvo.getCode();
					if("00067420".equals(code)){
						return true;
					}
				}
			}
		}
		
		return false;
	}
	
	/**
	   * 判断当前登录人是否是 非钢产品贸易部  
	   */
	public boolean isTheDept(){

		//1:获取当前登陆用户
		String pk_user = AppContext.getInstance().getPkUser();
		
		try {
			//2:根据用户获取人员
			IUserPubService userSer = NCLocator.getInstance().lookup(IUserPubService.class);
			String psndocid = userSer.queryPsndocByUserid(pk_user);
			
			if(psndocid != null){
				
				//3:根据人员获取部门
				IPsndocPubService psndocSer = NCLocator.getInstance().lookup(IPsndocPubService.class);
				Map<String, List<String>> psndocInfoMap = psndocSer.queryDeptIDByPsndocIDs(new String[]{psndocid});
			
				if(psndocInfoMap != null && psndocInfoMap.get(psndocid) != null && psndocInfoMap.get(psndocid).size() > 0){
					//部门ids
					List<String> deptids = psndocInfoMap.get(psndocid);
					
					if(deptids != null && deptids.size() > 0){
						IDeptPubService deptSer = NCLocator.getInstance().lookup(IDeptPubService.class);
						DeptVO[] deptvos = deptSer.queryDeptVOsByPKS(deptids.toArray(new String[]{}));
						
						//根据部门编码判断是否是 非钢产品贸易部
						if(deptvos != null && deptvos.length > 0){
							for(DeptVO deptvo : deptvos){
								String code = deptvo.getCode();
								if("00067420".equals(code)){
									return true;
								}
							}
							
						}
					}
				}
			}
		} catch (BusinessException e1) {
			Logger.error(e1.getMessage(), e1);
		}
		
		
		return false;
	}
	
	  /**
	   * 
	   * TODO 查询销售订单是否生成下游进口合同
	   */
	public boolean hasLowerBill(String saleorderId){
		
		if(saleorderId != null){
			String sql = " select pk_contract from it_contract_b "
					+ " where csrcid = '" + saleorderId + "' and dr = 0 ";
			
			List<?> queryDatas = null;
			IUAPQueryBS uapquery = NCLocator.getInstance().lookup(IUAPQueryBS.class);
			try {
	    		queryDatas = (List<?>) uapquery.executeQuery(sql, new ArrayListProcessor());
	    		
	    		if(queryDatas != null && queryDatas.size() > 0){
	    			return true;
	    		}
			} catch (BusinessException e) {
				Logger.error(e.getMessage(), e);
			}
		}
		return false;
		  
	}
	
	
}
