package nc.vo.so.report.util;

import java.io.Serializable;

import nc.itf.iufo.freereport.extend.IAreaCondition;
import nc.itf.iufo.freereport.extend.IReportAdjustor;
import nc.pub.smart.metadata.Field;
import nc.pub.smart.model.SmartModel;
import nc.ui.querytemplate.querytree.QueryScheme;
import nc.vo.pub.query.ConditionVO;
import nc.vo.so.report.paravo.ProfitContext;
import nc.vo.so.report.paravo.ProfitQryInfoParaVO;

/**
 * 模型調節器
 * 
 * @since 6.0
 * @version 2011-7-28 下午06:41:09
 * @author 么贵敬
 */
public class ProfitReportAdjustor implements IReportAdjustor, Serializable {

  private static final long serialVersionUID = -7209287854191003525L;

  @Override
  public void doAreaAdjust(com.ufida.dataset.IContext context, String areaPK,
      IAreaCondition areaCond,
      com.ufida.report.anareport.model.AbsAnaReportModel reportModel) {
    SmartModel smart = reportModel.getAreaData(areaPK).getSmartModel();
    String key =
        com.ufida.report.anareport.FreeReportContextKey.KEY_IQUERYCONDITION;
    com.ufida.report.anareport.base.BaseQueryCondition condition =
        (com.ufida.report.anareport.base.BaseQueryCondition) context
            .getAttribute(key);
    QueryScheme scheme = (QueryScheme) condition.getUserObject();
    ConditionVO[] conds = (ConditionVO[]) scheme.get("logicalcondition");
    ProfitQryInfoParaVO para = new ProfitQryInfoParaVO();
    for (ConditionVO cond : conds) {
      if (cond.getFieldCode().equals(ProfitContext.SUMMARYCONDITIONS)) {
        para.setGroupcondtion(cond.getValue());
      }
    }

    // ExtendAreaModel exModel =
    // ExtendAreaModel.getInstance(reportModel.getFormatModel());
    // Field[] flds =
    // AnaReportFieldUtil.getFieldnInExtendArea(exModel.getExAreaByPK(areaPK),
    // reportModel.getFormatModel());
    //
    // List<AreaFieldSet> list = new ArrayList<AreaFieldSet>();
    // String[] keys = para.getHideKeys();
    // for (Field f : flds) {
    // if (ArrayUtils.contains(keys, f.getExpression())) {
    // AreaFieldSet af = new AreaFieldSet(f);
    // list.add(af);
    // }
    // }

    String[] keys = para.getHideKeys();
    int length = keys.length;
    com.ufida.report.anareport.areaset.AreaFieldSet[] detailinfo =
        new com.ufida.report.anareport.areaset.AreaFieldSet[length];
    for (int i = 0; i < length; ++i) {
      Field fld = new Field();
      fld.setFldname(keys[i]);
      detailinfo[i] = new com.ufida.report.anareport.areaset.AreaFieldSet(fld);
    }

    com.ufida.report.anareport.areaset.AreaContentSet contentSet =
        new com.ufida.report.anareport.areaset.AreaContentSet();
    contentSet.setAreaPk(areaPK);
    contentSet.setSmartModelDefID(smart.getId());
    contentSet.setDetailFldNames(detailinfo);

    // AreaContentSetUtil.resetExCellByHideFields(contentSet, true,
    // reportModel);
    com.ufida.report.anareport.areaset.AreaContentSetUtil
        .resetExCellByHideRelatedFields(contentSet, true, reportModel);
  }

  @Override
  public void doReportAdjust(com.ufida.dataset.IContext context,
      com.ufida.report.anareport.model.AnaReportModel reportModel) {
    // TODO
  }

}
