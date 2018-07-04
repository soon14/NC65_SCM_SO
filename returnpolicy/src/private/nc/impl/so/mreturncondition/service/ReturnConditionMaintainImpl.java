package nc.impl.so.mreturncondition.service;

import nc.impl.pubapp.pattern.rule.IRule;
import nc.impl.pubapp.pub.smart.BatchSaveAction;
import nc.impl.pubapp.pub.smart.SmartServiceImpl;
import nc.impl.so.mreturncondition.rule.DeleteCheckRule;
import nc.impl.so.mreturncondition.rule.UniqueValidate;
import nc.itf.so.mreturncondition.IReturnConditionMaintain;
import nc.vo.bd.meta.BatchOperateVO;
import nc.vo.pub.BusinessException;
import nc.vo.pub.ISuperVO;
import nc.vo.pubapp.pattern.exception.ExceptionUtils;
import nc.vo.so.mreturncondition.entity.ReturnConditionVO;
import nc.vo.so.pub.util.SOVOChecker;

public class ReturnConditionMaintainImpl extends SmartServiceImpl implements
    IReturnConditionMaintain {

  @Override
  public BatchOperateVO batchSave(BatchOperateVO batchVO)
      throws BusinessException {
    BatchSaveAction<ISuperVO> batchSaveAction = new BatchSaveAction<ISuperVO>();
    IRule<BatchOperateVO> refrence = new DeleteCheckRule();
    IRule<BatchOperateVO> unique = new UniqueValidate();
    refrence.process(new BatchOperateVO[] {
      batchVO
    });
    unique.process(new BatchOperateVO[] {
      batchVO
    });

    Object[] obsadds = batchVO.getAddObjs();
    Object[] obsupdates = batchVO.getUpdObjs();
    if (!SOVOChecker.isEmpty(obsadds)) {
      this.checkVO(obsadds);
    }
    if (!SOVOChecker.isEmpty(obsupdates)) {
      this.checkVO(obsupdates);
    }

    return batchSaveAction.batchSave(batchVO);
  }

  private void checkVO(Object[] obsadds) {
    for (Object obs : obsadds) {
      ReturnConditionVO rvo = (ReturnConditionVO) obs;
      int len = rvo.getVexpresscode().length();
      if (len > 50) {
        ExceptionUtils
            .wrappBusinessException(nc.vo.ml.NCLangRes4VoTransl.getNCLangRes()
                .getStrByID("4006006_0", "04006006l-0005")/*@res "只能设置一条退货条件"*/);
      }
    }
  }

}
