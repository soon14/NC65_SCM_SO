package nc.ui.so.m30.closemanage.model;
import javax.swing.SwingUtilities;

import nc.ui.pub.beans.MessageDialog;
import nc.ui.pubapp.uif2app.query2.model.ModelDataManager;
import nc.ui.querytemplate.querytree.IQueryScheme;
import nc.ui.uif2.model.ModelDataDescriptor;
import nc.vo.jcom.lang.StringUtil;
import nc.vo.pub.BusinessRuntimeException;
import nc.vo.so.pub.SOConstant;

public class CloseManageModelDataManager extends ModelDataManager{
  
  @Override
  public void initModelByQueryScheme(IQueryScheme qryScheme) {
    // modify by zhangby5 没有将qryScheme放到model中 刷新按钮不可用且刷新报错
    this.setQueryScheme(qryScheme);
    try {
      Object[] objs = this.getQryService().queryByQueryScheme(qryScheme);
      if (objs != null && objs.length == SOConstant.CLOSEMANAGEMAXROWS) {
        SwingUtilities.invokeLater(new Runnable(){
          @Override
          public void run() {
            String hint = nc.vo.ml.NCLangRes4VoTransl.getNCLangRes().getStrByID("pubapp_0",
                        "0pubapp-0268", null, new String[] {"" + SOConstant.CLOSEMANAGEMAXROWS
                        })/*@res "查询结果太大，只返回了{0}条数据，请缩小范围再次查询"*/;
            MessageDialog.showHintDlg(getModel().getContext().getEntranceUI(), null, hint);
          }       
        }
        );
      }
    
      if (this.getModelDataProcessor() != null) {
        objs = this.getModelDataProcessor().processModelData(qryScheme, objs);
      }
      String schemeName = qryScheme.getName();
      if (!StringUtil.isEmptyWithTrim(schemeName)) {
        ModelDataDescriptor modelDataDescriptor =
            new ModelDataDescriptor(schemeName/* + "("
                                              + (objs == null ? 0 : objs.length) + ")"*/);
        this.getModel().initModel(objs, modelDataDescriptor);
      }
      else {
        ModelDataDescriptor modelDataDescriptor =
            new ModelDataDescriptor(nc.vo.ml.NCLangRes4VoTransl.getNCLangRes()
                .getStrByID("pubapp_0", "0pubapp-0144")/*@res "查询结果"*/);
        this.getModel().initModel(objs, modelDataDescriptor);
      }

    }
    catch (Exception e) {
      throw new BusinessRuntimeException(
          nc.vo.ml.NCLangRes4VoTransl.getNCLangRes().getStrByID("pubapp_0",
              "0pubapp-0007")/*@res "查询单据发生异常"*/, e);
    }
  }

}
