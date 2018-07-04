package nc.impl.so.mreturnpolicy.service;

import nc.impl.pubapp.pattern.rule.IRule;
import nc.impl.pubapp.pub.smart.BatchSaveAction;
import nc.impl.pubapp.pub.smart.SmartServiceImpl;
import nc.impl.so.mreturnpolicy.rule.DeleteCheckRule;
import nc.impl.so.mreturnpolicy.rule.UniqueValidate;
import nc.itf.so.mreturnpolicy.IReturnPolicyMaintain;
import nc.vo.bd.meta.BatchOperateVO;
import nc.vo.pub.BusinessException;
import nc.vo.pub.ISuperVO;

public class ReturnPolicyMaintainImpl extends SmartServiceImpl implements
    IReturnPolicyMaintain {
  @Override
  public BatchOperateVO batchSave(BatchOperateVO batchVO)
      throws BusinessException {
    BatchOperateVO operVO = null;
    IRule<BatchOperateVO> refrence = new DeleteCheckRule();
    IRule<BatchOperateVO> unique = new UniqueValidate();
    refrence.process(new BatchOperateVO[] {
      batchVO
    });
    unique.process(new BatchOperateVO[] {
      batchVO
    });
    BatchSaveAction<ISuperVO> action = new BatchSaveAction<ISuperVO>();
    operVO = action.batchSave(batchVO);
    return operVO;
  }
}
