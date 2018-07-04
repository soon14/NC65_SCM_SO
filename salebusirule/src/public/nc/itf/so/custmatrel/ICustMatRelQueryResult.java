package nc.itf.so.custmatrel;

import nc.vo.pubapp.query2.sql.process.QuerySchemeProcessor;

/**
 * @author quyt
 * 
 */
public interface ICustMatRelQueryResult {
	/**
	 * 将查询条件赋值到表头相应字段
	 * 
	 * @return
	 */
	String custMatRelQueryResult(String[] queryConditionValue);
}
