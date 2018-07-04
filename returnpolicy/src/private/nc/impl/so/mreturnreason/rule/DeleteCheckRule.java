package nc.impl.so.mreturnreason.rule;

import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.List;

import nc.impl.pubapp.pattern.rule.IRule;
import nc.itf.scmpub.reference.uap.bd.refcheck.ReferenceCheck;
import nc.vo.bd.meta.BatchOperateVO;
import nc.vo.pubapp.pattern.exception.ExceptionUtils;
import nc.vo.so.mreturnreason.entity.ReturnReasonVO;

public class DeleteCheckRule implements IRule<BatchOperateVO> {
  @Override
  public void process(BatchOperateVO[] vos) {
    if ((vos == null) || (vos.length == 0)) {
      return;
    }
    Object[] delObjs = vos[0].getDelObjs();
    if ((delObjs == null) || (delObjs.length == 0)) {
      return;
    }
    ReturnReasonVO[] delVOs = this.convertArrayType(delObjs);
    List<String> pklist = new ArrayList<String>();
    for (ReturnReasonVO delVO : delVOs) {
      String pk_reason = delVO.getPk_returnreason();
      pklist.add(pk_reason);
    }
    try {
      if (ReferenceCheck.isReferenced("so_returnreason",
          pklist.toArray(new String[pklist.size()]))) {
        ExceptionUtils.wrappBusinessException(nc.vo.ml.NCLangRes4VoTransl.getNCLangRes().getStrByID("4006006_0","04006006-0034")/*@res "退货原因已被引用，不允许删除！"*/);
      }
    }
    catch (Exception e) {
      ExceptionUtils.wrappException(e);
    }
  }

  private ReturnReasonVO[] convertArrayType(Object[] vos) {
    ReturnReasonVO[] smartVOs =
        (ReturnReasonVO[]) Array.newInstance(vos[0].getClass(), vos.length);
    System.arraycopy(vos, 0, smartVOs, 0, vos.length);
    return smartVOs;
  }
}