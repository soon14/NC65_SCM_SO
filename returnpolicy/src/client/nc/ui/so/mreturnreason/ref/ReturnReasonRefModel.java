package nc.ui.so.mreturnreason.ref;

import nc.ui.bd.ref.AbstractRefModel;
import nc.ui.ml.NCLangRes;
import nc.ui.scmf.dm.pub.DMBaseDocUtils;
import nc.vo.pubapp.pattern.pub.SqlBuilder;
import nc.vo.so.mreturnreason.entity.ReturnReasonVO;

/**
 * 退货原因参照
 */
public class ReturnReasonRefModel extends AbstractRefModel {

  private static final long serialVersionUID = 1L;

  public ReturnReasonRefModel(String refNodeName) {
    this.setRefNodeName(refNodeName);
  }

  @Override
  public void setRefNodeName(String refNodeName) {
    this.m_strRefNodeName = refNodeName;
    this.setFieldCode(new String[] {
        ReturnReasonVO.VREASONCODE, ReturnReasonVO.VREASONNAME,
        ReturnReasonVO.FREASONTYPE, ReturnReasonVO.VRETURNMODE
    });
    // 需要多语言处理？？？
    this.setFieldName(new String[] {
        NCLangRes.getInstance().getStrByID("4006006_0", "04006006-0122")/*退货原因编码*/, NCLangRes.getInstance().getStrByID("4006006_0", "04006006-0123")/*退货原因名称*/, NCLangRes.getInstance().getStrByID("4006006_0", "04006006-0065")/*退货原因类型*/, NCLangRes.getInstance().getStrByID("4006006_0", "04006006-0124")/*默认退货责任处理方式*/
    });
    this.setHiddenFieldCode(new String[] {
        ReturnReasonVO.PK_RETURNREASON
    });
    this.setPkFieldCode(ReturnReasonVO.PK_RETURNREASON);
    this.setRefCodeField(ReturnReasonVO.VREASONCODE);
    this.setRefNameField(ReturnReasonVO.VREASONNAME);

    this.setTableName("so_returnreason");
    this.setRefTitle(NCLangRes.getInstance().getStrByID("4006006_0", "04006006l-0006")/*退货原因*/); /*-=notranslate=-*/

    this.resetFieldName();
  }

  @Override
  protected String getEnvWherePart() {
    String pk_group = this.getPk_group();
    String pk_org = this.getPk_org();
    // zhangby5修改 原因：scmpub改动代码
    String visible =
        DMBaseDocUtils.getVisibleForRef(pk_group, pk_org, ReturnReasonVO.class,
            null);
    visible = visible.replace("pk_org", "so_returnreason.pk_org");
    SqlBuilder sb = new SqlBuilder();
    sb.append("so_returnreason.dr = 0 and ");
    sb.append(visible);
    return sb.toString();
  }
}
