package nc.impl.so.mreturnassign.service;

import java.util.Map;
import java.util.Set;

import nc.impl.pubapp.pattern.rule.IRule;
import nc.impl.pubapp.pub.smart.BatchSaveAction;
import nc.impl.pubapp.pub.smart.SmartServiceImpl;
import nc.impl.so.mreturnassign.rule.DataValidate;
import nc.impl.so.mreturnassign.rule.DeleteCheckRule;
import nc.impl.so.mreturnassign.rule.ReturnPriorityCodeRule;
import nc.itf.so.mreturnassign.IReturnAssignMaintain;
import nc.vo.bd.meta.BatchOperateVO;
import nc.vo.pub.BusinessException;
import nc.vo.pub.ISuperVO;
import nc.vo.so.m30.entity.SaleOrderVO;

public class ReturnAssignMaintainImpl extends SmartServiceImpl implements
    IReturnAssignMaintain {
  @Override
  public BatchOperateVO batchSave(BatchOperateVO batchVO)
      throws BusinessException {
    BatchOperateVO operVO = null;
    IRule<BatchOperateVO> refrence = new DeleteCheckRule();
    IRule<BatchOperateVO> unique = new DataValidate();
    refrence.process(new BatchOperateVO[] {
      batchVO
    });
    unique.process(new BatchOperateVO[] {
      batchVO
    });
    IRule<BatchOperateVO> priorityRule = new ReturnPriorityCodeRule();
    priorityRule.process(new BatchOperateVO[] {
      batchVO
    });
    BatchSaveAction<ISuperVO> action = new BatchSaveAction<ISuperVO>();
    operVO = action.batchSave(batchVO);
    return operVO;
  }

  @Override
  public Map<String, Set<String>> getAssignedPolicy(SaleOrderVO aggVO)
      throws BusinessException {
    return null;
  }

}
