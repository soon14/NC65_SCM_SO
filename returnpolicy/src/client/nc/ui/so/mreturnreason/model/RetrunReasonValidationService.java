package nc.ui.so.mreturnreason.model;

import java.util.ArrayList;
import java.util.List;

import nc.bs.uif2.validation.ValidationException;
import nc.bs.uif2.validation.ValidationFailure;
import nc.ui.ml.NCLangRes;
import nc.ui.uif2.model.DefaultBatchValidationService;
import nc.vo.bd.meta.BatchOperateVO;
import nc.vo.pubapp.pattern.data.ValueUtils;
import nc.vo.so.mreturnreason.entity.ReturnReasonVO;

public class RetrunReasonValidationService extends
    DefaultBatchValidationService {
  @Override
  public int[] unNecessaryData(List<Object> rows) {
    if (this.getEditor().isBodyAutoAddLine()) {
      int size = rows.size();
      List<Integer> list = new ArrayList<Integer>();

      for (int i = 0; i < size; i++) {
        ReturnReasonVO vo = (ReturnReasonVO) rows.get(i);
        if ((vo.getVreasoncode() == null) && (vo.getVreasonname() == null)) {
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
      ReturnReasonVO reasonVO1 = (ReturnReasonVO) newVOs[i];
      for (int j = i + 1; j < newVOs.length; j++) {
        ReturnReasonVO reasonVO2 = (ReturnReasonVO) newVOs[j];
        if (reasonVO1.getVreasoncode().trim()
            .equals(reasonVO2.getVreasoncode().trim())) {
          list.add(new ValidationFailure(NCLangRes.getInstance().getStrByID("4006006_0", "04006006-0112", null, new String[]{reasonVO2.getVreasoncode()})/*退货原因编码[{0}]不能重复，请修改*/));
        }
        else if ((reasonVO1.getVreasonname() != null)
            && reasonVO1.getVreasonname().equals(reasonVO2.getVreasonname())) {
          list.add(new ValidationFailure(NCLangRes.getInstance().getStrByID("4006006_0", "04006006-0113", null, new String[]{reasonVO1.getVreasonname()})/*退货原因名称[{0}]不能重复，请修改*/));
        }
      }
    }
    if (list.size() > 0) {
      throw new ValidationException(list);
    }
  }

}
