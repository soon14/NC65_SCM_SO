package nc.impl.so.mreturnassign.rule;

import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.List;

import nc.impl.pubapp.pattern.rule.IRule;
import nc.itf.scmpub.reference.uap.bd.refcheck.ReferenceCheck;
import nc.vo.bd.meta.BatchOperateVO;
import nc.vo.pubapp.pattern.exception.ExceptionUtils;
import nc.vo.so.mreturnassign.entity.ReturnAssignVO;

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
    ReturnAssignVO[] delVOs = this.convertArrayType(delObjs);
    List<String> pklist = new ArrayList<String>();
    for (ReturnAssignVO delVO : delVOs) {
      String pk_reason = delVO.getPk_returnassign();
      pklist.add(pk_reason);
    }
    try {
      if (ReferenceCheck.isReferenced("so_returnassign",
          pklist.toArray(new String[pklist.size()]))) {
        ExceptionUtils.wrappBusinessException(nc.vo.ml.NCLangRes4VoTransl.getNCLangRes().getStrByID("4006006_0","04006006-0031")/*@res "要删除的退货政策分配中，存在已被引用的行，请检查。"*/);
      }
    }
    catch (Exception e) {
      ExceptionUtils.wrappException(e);
    }
  }

  private ReturnAssignVO[] convertArrayType(Object[] vos) {
    ReturnAssignVO[] smartVOs =
        (ReturnAssignVO[]) Array.newInstance(vos[0].getClass(), vos.length);
    System.arraycopy(vos, 0, smartVOs, 0, vos.length);
    return smartVOs;
  }
}