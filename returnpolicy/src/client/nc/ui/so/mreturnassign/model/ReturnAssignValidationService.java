package nc.ui.so.mreturnassign.model;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import nc.bs.uif2.validation.ValidationException;
import nc.ui.ml.NCLangRes;
import nc.ui.uif2.model.DefaultBatchValidationService;
import nc.vo.bd.meta.BatchOperateVO;
import nc.vo.pubapp.pattern.data.ValueUtils;
import nc.vo.pubapp.pattern.exception.ExceptionUtils;
import nc.vo.so.mreturnassign.entity.ReturnAssignVO;

public class ReturnAssignValidationService extends
    DefaultBatchValidationService {
  @Override
  public int[] unNecessaryData(List<Object> rows) {
    if (this.getEditor().isBodyAutoAddLine()) {
      int size = rows.size();
      List<Integer> list = new ArrayList<Integer>();

      for (int i = 0; i < size; i++) {
        ReturnAssignVO vo = (ReturnAssignVO) rows.get(i);
        String pk_customer = vo.getPk_customer();
        String pk_custclass = vo.getPk_custclass();
        String pk_saleclass = vo.getPk_custsaleclass();
        if (((pk_customer == null) && (pk_custclass == null))
            || ((pk_customer == null) && (pk_saleclass == null))) {
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
    for (int i = 0; i < newVOs.length; i++) {
      ReturnAssignVO vo = (ReturnAssignVO) newVOs[i];
      this.chechVO(vo);
    }
    this.checkDuplicate(newVOs);
  }

  private void chechVO(ReturnAssignVO vo) {
    if ((vo.getPk_productline() != null) && (vo.getPk_material() != null)
        && (vo.getPk_marbasclass() != null)) {
      ExceptionUtils.wrappBusinessException(nc.vo.ml.NCLangRes4VoTransl
          .getNCLangRes().getStrByID("4006006_0", "04006006-0003")
      /*@res "产品线、物料基本分类、物料不能同时存在。"*/);
    }
    if ((vo.getPk_productline() != null) && (vo.getPk_material() != null)
        && (vo.getPk_marsaleclass() != null)) {
      ExceptionUtils.wrappBusinessException(nc.vo.ml.NCLangRes4VoTransl
          .getNCLangRes().getStrByID("4006006_0", "04006006-0004")
      /*@res "产品线+物料+物料销售分类不能同时存在。"*/);
    }
    if ((vo.getPk_productline() != null) && (vo.getPk_material() != null)) {
      ExceptionUtils
          .wrappBusinessException(nc.vo.ml.NCLangRes4VoTransl.getNCLangRes()
              .getStrByID("4006006_0", "04006006-0005")/*@res "产品线和物料不能同时存在。"*/);
    }
    if ((vo.getPk_productline() != null) && (vo.getPk_marbasclass() != null)) {
      ExceptionUtils.wrappBusinessException(nc.vo.ml.NCLangRes4VoTransl
          .getNCLangRes().getStrByID("4006006_0", "04006006-0006")
      /*@res "产品线和物料基本分类不能同时存在。"*/);
    }
    if ((vo.getPk_productline() != null) && (vo.getPk_marsaleclass() != null)) {
      ExceptionUtils.wrappBusinessException(nc.vo.ml.NCLangRes4VoTransl
          .getNCLangRes().getStrByID("4006006_0", "04006006-0007")
      /*@res "产品线和物料销售分类不能同时存在。"*/);
    }
    if ((vo.getPk_marbasclass() != null) && (vo.getPk_material() != null)) {
      ExceptionUtils.wrappBusinessException(nc.vo.ml.NCLangRes4VoTransl
          .getNCLangRes().getStrByID("4006006_0", "04006006-0008")
      /*@res "物料基本分类和物料不能同时存在。"*/);
    }
    if ((vo.getPk_marsaleclass() != null) && (vo.getPk_material() != null)) {
      ExceptionUtils.wrappBusinessException(nc.vo.ml.NCLangRes4VoTransl
          .getNCLangRes().getStrByID("4006006_0", "04006006-0009")
      /*@res "物料销售分类和物料不能同时存在。"*/);
    }
    if ((vo.getPk_customer() != null) && (vo.getPk_custclass() != null)) {
      ExceptionUtils.wrappBusinessException(nc.vo.ml.NCLangRes4VoTransl
          .getNCLangRes().getStrByID("4006006_0", "04006006-0010")
      /*@res "客户+客户基本分类只能输入一个值。"*/);
    }
    if ((vo.getPk_customer() != null) && (vo.getPk_custsaleclass() != null)) {
      ExceptionUtils.wrappBusinessException(nc.vo.ml.NCLangRes4VoTransl
          .getNCLangRes().getStrByID("4006006_0", "04006006-0011")
      /*@res "客户+客户销售分类只能输入一个值。"*/);
    }
  }

  private void checkDuplicate(Object[] vos) {
    /** 销售组织+产品线编码+物料分类编码+物料销售分类编码+物料编码+客户分类编码+客户销售分类编码+客户编码* */
    Set<String> keys = new HashSet<String>();
    String key;
    for (Object item : vos) {
      key = this.getKey((ReturnAssignVO) item);
      if (keys.contains(key)) {
        ExceptionUtils.wrappBusinessException(NCLangRes.getInstance()
            .getStrByID("4006006_0", "04006006-0039", null, new String[] {})
        /*[销售组织+产品线编码+物料分类编码+物料销售分类编码+物料编码+客户分类编码+客户销售分类编码+客户编码]必须唯一*/);
      }
      keys.add(key);
    }
  }

  private String getKey(ReturnAssignVO item) {
    StringBuffer key = new StringBuffer();
    key.append(item.getPk_saleorg());
    if (item.getPk_productline() != null) {
      key.append(item.getPk_productline());
    }
    if (item.getPk_marbasclass() != null) {
      key.append(item.getPk_marbasclass());
    }
    if (item.getPk_marsaleclass() != null) {
      key.append(item.getPk_marsaleclass());
    }
    key.append(item.getPk_material());

    if (item.getPk_custclass() != null) {
      key.append(item.getPk_custclass());
    }
    if (item.getPk_custsaleclass() != null) {
      key.append(item.getPk_custsaleclass());
    }
    key.append(item.getPk_customer());

    return key.toString();
  }
}
