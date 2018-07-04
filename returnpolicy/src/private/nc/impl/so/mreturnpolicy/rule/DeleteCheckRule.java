package nc.impl.so.mreturnpolicy.rule;

import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.List;

import nc.impl.pubapp.pattern.rule.IRule;
import nc.itf.scmpub.reference.uap.bd.refcheck.ReferenceCheck;
import nc.vo.bd.meta.BatchOperateVO;
import nc.vo.pubapp.pattern.exception.ExceptionUtils;
import nc.vo.so.mreturnpolicy.entity.ReturnPolicyVO;

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
    ReturnPolicyVO[] delVOs = this.convertArrayType(delObjs);
    List<String> pklist = new ArrayList<String>();
    for (ReturnPolicyVO delVO : delVOs) {
      String pk_returnpolicy = delVO.getPk_returnpolicy();
      pklist.add(pk_returnpolicy);
    }
    try {
      if (ReferenceCheck.isReferenced("so_returnpolicy",
          pklist.toArray(new String[pklist.size()]))) {
        ExceptionUtils.wrappBusinessException(nc.vo.ml.NCLangRes4VoTransl.getNCLangRes().getStrByID("4006006_0","04006006-0033")/*@res "要删除的退货政策设置已经被引用，请检查。"*/);
      }
    }
    catch (Exception e) {
      ExceptionUtils.wrappException(e);
    }
  }

  private ReturnPolicyVO[] convertArrayType(Object[] vos) {
    ReturnPolicyVO[] smartVOs =
        (ReturnPolicyVO[]) Array.newInstance(vos[0].getClass(), vos.length);
    System.arraycopy(vos, 0, smartVOs, 0, vos.length);
    return smartVOs;
  }
}