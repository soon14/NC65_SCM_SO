package nc.itf.so.salequotation;

import nc.bs.pub.compiler.AbstractCompiler2;
import nc.vo.pub.BusinessException;
import nc.vo.so.salequotation.entity.AggSalequotationHVO;

public interface ISalequotationService {

  /**
   * 审批单据
   * 
   * @param vos
   * @param script
   * @return
   * @throws BusinessException
   */
  Object approve(AggSalequotationHVO[] vos, AbstractCompiler2 script)
      throws BusinessException;

  /**
   * 关闭单据
   * 
   * @param aggVO
   * @return
   * @throws Exception
   */
  AggSalequotationHVO[] close(AggSalequotationHVO[] aggVOs) throws BusinessException;

  /**
   * 提交单据
   * 
   * @param vos
   * @param script
   * @return
   * @throws BusinessException
   */
  AggSalequotationHVO[] commit(AggSalequotationHVO[] vos,
      AbstractCompiler2 script) throws BusinessException;

  /**
   * 删除
   * 
   * @param object
   * @throws Exception
   */
  void delete(AggSalequotationHVO[] object) throws Exception;

  /**
   * 作废单据
   * 
   * @param aggVO
   * @return
   * @throws Exception
   */
  AggSalequotationHVO[] invalidate(AggSalequotationHVO[] aggVOs)
      throws Exception;

  /**
   * 打开单据
   * 
   * @param aggVO
   * @return
   * @throws Exception
   */
  AggSalequotationHVO[] open(AggSalequotationHVO[] aggVOs) throws Exception;

  AggSalequotationHVO[] saveBase(AggSalequotationHVO[] aggVOs) throws Exception;

  /**
   * 弃审单据
   * 
   * @param vos
   * @param script
   * @return
   * @throws BusinessException
   */
  AggSalequotationHVO[] unApprove(AggSalequotationHVO[] vos,
      AbstractCompiler2 script) throws BusinessException;
}
