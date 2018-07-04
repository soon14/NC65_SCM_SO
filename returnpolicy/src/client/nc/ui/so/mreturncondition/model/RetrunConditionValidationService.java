package nc.ui.so.mreturncondition.model;

import java.util.ArrayList;
import java.util.List;

import nc.bs.uif2.validation.ValidationException;
import nc.bs.uif2.validation.ValidationFailure;
import nc.ui.ml.NCLangRes;
import nc.ui.uif2.model.DefaultBatchValidationService;
import nc.vo.bd.meta.BatchOperateVO;
import nc.vo.pubapp.pattern.data.ValueUtils;
import nc.vo.pubapp.pattern.exception.ExceptionUtils;
import nc.vo.so.mreturncondition.entity.ReturnConditionVO;

/**
 * 退货条件前台校验类
 *
 * @author jianghp
 * @since V60
 */
public class RetrunConditionValidationService extends
    DefaultBatchValidationService {
  @Override
  public int[] unNecessaryData(List<Object> rows) {
    if (this.getEditor().isBodyAutoAddLine()) {
      int size = rows.size();
      List<Integer> list = new ArrayList<Integer>();
      for (int i = 0; i < size; i++) {
        ReturnConditionVO vo = (ReturnConditionVO) rows.get(i);
        if ((vo.getVconditioncode() == null)
            && (vo.getVconditionname() == null)) {
          list.add(Integer.valueOf(i));
        }
      }
      int[] del = new int[list.size()];
      if (list.size() > 0) {
        for (int i = 0; i < list.size(); i++) {
          Object obj = list.get(i);

          del[i] = ValueUtils.getInt(obj);
        }
      }
      return del;
    }
    return null;
  }

  @Override
  protected void modelValidate(Object obj) throws ValidationException {
    BatchOperateVO batchVO = (BatchOperateVO) obj;
    Object[] addVOs = batchVO.getAddObjs();
    Object[] updateVOs = batchVO.getUpdObjs();
    Object[] newVOs = new Object[updateVOs.length + addVOs.length];
    System.arraycopy(addVOs, 0, newVOs, 0, addVOs.length);
    System.arraycopy(updateVOs, 0, newVOs, addVOs.length, updateVOs.length);
    List<ValidationFailure> list = new ArrayList<ValidationFailure>();
    /* 校验最新数据 */
    for (int i = 0; i < newVOs.length; i++) {
      ReturnConditionVO conditionVO1 = (ReturnConditionVO) newVOs[i];
      if (conditionVO1.getVconditioncode() == null) {
        ExceptionUtils.wrappBusinessException(nc.vo.ml.NCLangRes4VoTransl.getNCLangRes().getStrByID("4006006_0","04006006-0012")/*@res "退货条件编码不允许为空，请检查！"*/);
      }
      else if (conditionVO1.getVconditionname() == null) {
        ExceptionUtils.wrappBusinessException(nc.vo.ml.NCLangRes4VoTransl.getNCLangRes().getStrByID("4006006_0","04006006-0013")/*@res "退货条件名称不允许为空，请检查！"*/);
      }
      for (int j = i + 1; j < newVOs.length; j++) {
        ReturnConditionVO conditionVO2 = (ReturnConditionVO) newVOs[j];
        if (conditionVO2.getVconditioncode() == null) {
          ExceptionUtils.wrappBusinessException(nc.vo.ml.NCLangRes4VoTransl.getNCLangRes().getStrByID("4006006_0","04006006-0012")/*@res "退货条件编码不允许为空，请检查！"*/);
        }
        else if (conditionVO2.getVconditionname() == null) {
          ExceptionUtils.wrappBusinessException(nc.vo.ml.NCLangRes4VoTransl.getNCLangRes().getStrByID("4006006_0","04006006-0013")/*@res "退货条件名称不允许为空，请检查！"*/);
        }
        if (conditionVO1.getVconditioncode().equals(
            conditionVO2.getVconditioncode())) {
          list.add(new ValidationFailure(NCLangRes.getInstance().getStrByID("4006006_0", "04006006-0040", null, new String[]{conditionVO2.getVconditioncode()})/*退货条件编码[{0}]不能重复，请修改*/));
        }
        if ((conditionVO1.getVconditionname() != null)
            && conditionVO1.getVconditionname().equals(
                conditionVO2.getVconditionname())) {
          list.add(new ValidationFailure(NCLangRes.getInstance().getStrByID("4006006_0", "04006006-0041", null, new String[]{conditionVO1.getVconditionname()})/*退货条件名称[{0}]不能重复，请修改*/));
        }
      }
    }
    if (list.size() > 0) {
      throw new ValidationException(list);
    }
  }

}