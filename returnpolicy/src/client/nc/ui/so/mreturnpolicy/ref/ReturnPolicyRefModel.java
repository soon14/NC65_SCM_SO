package nc.ui.so.mreturnpolicy.ref;

import nc.ui.bd.ref.AbstractRefModel;
import nc.ui.ml.NCLangRes;
import nc.ui.pubapp.AppUiContext;
import nc.ui.scmf.dm.pub.DMBaseDocUtils;
import nc.vo.pub.lang.UFDate;
import nc.vo.pubapp.pattern.pub.SqlBuilder;
import nc.vo.pubapp.util.TimeUtils;
import nc.vo.so.mreturnpolicy.entity.ReturnPolicyVO;

public class ReturnPolicyRefModel extends AbstractRefModel {

  public ReturnPolicyRefModel(String refNodeName) {
    this.setRefNodeName(refNodeName);
  }

  @Override
  public void setRefNodeName(String refNodeName) {
    this.m_strRefNodeName = refNodeName;
    this.setFieldCode(new String[] {
        ReturnPolicyVO.VPOLICYCODE, ReturnPolicyVO.VPOLICYNAME,
        ReturnPolicyVO.VEXPRESSNAME, ReturnPolicyVO.VPOLICYDETAIL
    });

    this.setFieldName(new String[] {
        NCLangRes.getInstance().getStrByID("4006006_0", "04006006l-0000")/*退货政策编码*/,
        NCLangRes.getInstance().getStrByID("4006006_0", "04006006l-0001")/*退货政策名称*/,
        NCLangRes.getInstance().getStrByID("4006006_0", "04006006l-0002")/*退货政策表达式*/,
        NCLangRes.getInstance().getStrByID("4006006_0", "04006006l-0003")
        /*退货政策说明*/
    });
    this.setHiddenFieldCode(new String[] {
        "pk_returnpolicy"
    });
    this.setPkFieldCode(ReturnPolicyVO.PK_RETURNPOLICY);
    this.setRefCodeField(ReturnPolicyVO.VPOLICYCODE);
    this.setRefNameField(ReturnPolicyVO.VPOLICYNAME);

    this.setTableName("so_returnpolicy");
    this.setRefTitle(NCLangRes.getInstance().getStrByID("4006006_0",
        "04006006l-0004")/*退货政策*/);

    this.resetFieldName();
  }

  @Override
  protected String getEnvWherePart() {
    String pk_group = this.getPk_group();
    String pk_org = this.getPk_org();
    // zhangby5修改 原因：scmpub改动代码
    String visible =
        DMBaseDocUtils.getVisibleForRef(pk_group, pk_org, ReturnPolicyVO.class,
            null);
    visible = visible.replace("pk_org", "so_returnpolicy.pk_org");
    UFDate date = AppUiContext.getInstance().getBusiDate();
    UFDate startDate = TimeUtils.getStartDate(date);
    UFDate endDate = TimeUtils.getEndDate(date);
    SqlBuilder sb = new SqlBuilder();
    sb.append("so_returnpolicy.dr = 0 and so_returnpolicy."
        + ReturnPolicyVO.DSTARTDATE + "<='" + startDate + "'");
    sb.append(" and so_returnpolicy." + ReturnPolicyVO.DENDDATE + ">='"
        + endDate + "' and ");
    sb.append(visible);
    return sb.toString();
  }
}
