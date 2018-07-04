package nc.ui.so.tranmatrel.rule;

import nc.ui.ml.NCLangRes;
import nc.vo.pubapp.pattern.exception.ExceptionUtils;
import nc.vo.so.tranmatrel.entity.TranMatRelBVO;
import nc.vo.so.tranmatrel.entity.TranMatRelVO;

/**
 * 保存时，单据界面前台非空校验
 * 
 * @since 6.0
 * @version 2011-7-6 下午02:02:08
 * @author 祝会征
 */
public class BillDataValidateRule {
  public void validate(TranMatRelVO vo) {
    StringBuffer errMsg = new StringBuffer();
    TranMatRelBVO[] bvos = vo.getChildrenVO();
    if (null == bvos) {
      return;
    }
    for (TranMatRelBVO bvo : bvos) {
      this.checkMaterial(bvo, errMsg);
      if (errMsg.length() > 1) {
        ExceptionUtils.wrappBusinessException(errMsg.toString());
      }
    }
  }

  private void checkMaterial(TranMatRelBVO bvo, StringBuffer errMsg) {
    String pk_mat = bvo.getPk_material_v();
    String pk_base = bvo.getPk_materialbaseclass();
    String pk_sale = bvo.getPk_materialsaleclass();
    if (null == pk_mat && null == pk_base && null == pk_sale) {
      errMsg.append(NCLangRes.getInstance().getStrByID("4006007_0", "04006007-0003")/*物料和物料分类不能都为空。*/);
      errMsg.append("\n");
    }
  }
}
