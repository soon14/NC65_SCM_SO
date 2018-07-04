package nc.impl.so.mreturnreason.service;

import nc.vo.bd.meta.BatchOperateVO;
import nc.vo.pub.BusinessException;
import nc.vo.pub.ISuperVO;

import nc.itf.so.mreturnreason.IReturnReasonMaintain;

import nc.bs.so.returnpolicy.plugin.ReturnpolicyPlugInPoint;

import nc.impl.pubapp.pattern.rule.IRule;
import nc.impl.pubapp.pub.smart.BatchSaveAction;
import nc.impl.pubapp.pub.smart.SmartServiceImpl;
import nc.impl.so.mreturnreason.rule.DeleteCheckRule;
import nc.impl.so.mreturnreason.rule.UniqueValidate;

/**
 * 退货管理保存、删除等操作实现类
 * 
 * @since 6.3
 * @version 2013-05-27 15:06:04
 * @author yixl
 */
public class ReturnReasonMaintainImpl extends SmartServiceImpl implements
    IReturnReasonMaintain {

  @Override
  public BatchOperateVO batchSave(BatchOperateVO batchVO)
      throws BusinessException {

    BatchOperateVO operVO = null;
    IRule<BatchOperateVO> unique = new UniqueValidate();
    unique.process(new BatchOperateVO[] {
      batchVO
    });
    IRule<BatchOperateVO> refrence = new DeleteCheckRule();
    refrence.process(new BatchOperateVO[] {
      batchVO
    });

    BatchSaveAction<ISuperVO> action = null;
    if (null != batchVO.getUpdObjs() && batchVO.getUpdObjs().length > 0) {
      action = new BatchSaveAction<ISuperVO>(ReturnpolicyPlugInPoint.UpdateBP);
      action.getUpdateProcesser();
    }
    else if (null != batchVO.getAddObjs() && batchVO.getAddObjs().length > 0) {
      action = new BatchSaveAction<ISuperVO>(ReturnpolicyPlugInPoint.InsertBP);
      action.getInsertProcesser();
    }
    else if (null != batchVO.getDelObjs() && batchVO.getDelObjs().length > 0) {
      action = new BatchSaveAction<ISuperVO>(ReturnpolicyPlugInPoint.DeleteBP);
      action.getDeleteProcesser();
    }
    else {
      action = new BatchSaveAction<ISuperVO>();
    }

    operVO = action.batchSave(batchVO);
    return operVO;
  }
}
