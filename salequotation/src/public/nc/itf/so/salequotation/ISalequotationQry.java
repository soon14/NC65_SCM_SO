package nc.itf.so.salequotation;

import nc.ui.querytemplate.querytree.IQueryScheme;
import nc.vo.so.salequotation.entity.AggSalequotationHVO;
import nc.vo.so.salequotation.entity.SalequotationBVO;

/**
 * 
 * @author chenyyb
 * 
 */
public interface ISalequotationQry {

  AggSalequotationHVO[] queryAllInvalidateBill(String pk_org) throws Exception;

  SalequotationBVO[] queryBodyByHeadPk(String headPk) throws Exception;

  AggSalequotationHVO queryByPk(String pk) throws Exception;

  AggSalequotationHVO[] queryByPks(String[] pks) throws Exception;

  /**
   * 销售订单参照报价单根据查询方案查找数据
   * 
   * @param querySheme 查询方案
   * @return
   * @throws Exception
   */
  AggSalequotationHVO[] queryByQuerySchemeFor30(IQueryScheme querySheme)
      throws Exception;

  /**
   * 根据查询方案查找数据
   * 
   * @param querySheme 查询方案
   * @return
   * @throws Exception
   */
  AggSalequotationHVO[] queryByQuerySchemeForZ3(IQueryScheme querySheme)
      throws Exception;

  /**
   * 根据sql查找数据
   * 
   * @param sql
   * @return
   * @throws Exception
   */
  AggSalequotationHVO[] queryVOsBySql(String sql) throws Exception;
}
