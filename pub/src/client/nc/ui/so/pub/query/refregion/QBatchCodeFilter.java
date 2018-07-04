package nc.ui.so.pub.query.refregion;

import nc.ui.pubapp.uif2app.query2.QueryConditionDLGDelegator;
import nc.ui.pubapp.uif2app.query2.refedit.IFieldValueElementEditorCreator;
import nc.ui.querytemplate.meta.FilterMeta;
import nc.ui.querytemplate.valueeditor.IFieldValueElementEditor;
import nc.ui.scmf.ic.batchcode.ref.ScmBatchQueryEditor;
import nc.ui.scmf.ic.batchcode.ref.ScmBatchRef;
import nc.vo.scmf.ic.batchcode.entity.BatchRefInfo;

public class QBatchCodeFilter {
  /**
   * 方法功能描述：批次参照的过滤
   * <p>
   * <b>参数说明</b>
   * <p>
   * 
   * @since 6.36
   * @author liylr
   * @time 2015-05-13 上午10:06:35
   */
  public void filter(QueryConditionDLGDelegator delegator, String vbatchcode) {
    delegator.setFieldValueElementEditor(null , vbatchcode,
        new IFieldValueElementEditorCreator() {
          @Override
          public IFieldValueElementEditor createFVEditor(FilterMeta meta) {
            // 每次创建新实例，否则候选条件选择批次后，原有批次参照出错
            BatchRefInfo config = new BatchRefInfo(null, true, true);
            ScmBatchRef batchRef = new ScmBatchRef(config);

            IFieldValueElementEditor refEditor =
                new ScmBatchQueryEditor(batchRef);
            return refEditor;
          }
        });
  }
}
