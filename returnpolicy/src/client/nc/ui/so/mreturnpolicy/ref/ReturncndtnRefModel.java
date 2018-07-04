package nc.ui.so.mreturnpolicy.ref;

import nc.ui.ml.NCLangRes;

/**
 * 此处插入类型说明。 创建日期：(01-8-22 11:31:01)
 * 
 * @author：王建华 2004-5-9 王森阳 修改bug wsy-begin/wsy-end
 */
public class ReturncndtnRefModel extends nc.ui.bd.ref.AbstractRefModel {
  private java.lang.String pk_group;

  /**
   * AccsubjTypeRefModel 构造子注解。
   */
  public ReturncndtnRefModel() {
    super();
  }

  /**
   * AccsubjTypeRefModel 构造子注解。
   */
  public ReturncndtnRefModel(String strPk_Corp) {
    super();
    this.setPk_group(strPk_Corp);
  }

  /**
   * getDefaultFieldCount 方法注解。
   */
  @Override
  public int getDefaultFieldCount() {
    return 4;
  }

  /**
   * 参照数据库字段名数组 创建日期：(01-4-4 0:57:23)
   * 
   * @return java.lang.String
   */
  @Override
  public java.lang.String[] getFieldCode() {
    return new String[] {
      "vconditioncode", "vconditionname", "vexpressname", "vexpressdetail"
    };
  }

  /**
   * 和数据库字段名数组对应的中文名称数组 创建日期：(01-4-4 0:57:23)
   * 
   * @return java.lang.String
   */
  @Override
  public java.lang.String[] getFieldName() {
    return new String[] {
      NCLangRes.getInstance().getStrByID("4006006_0", "04006006-0115")/*退货条件编码*/, NCLangRes.getInstance().getStrByID("4006006_0", "04006006-0116")/*退货条件名称*/, NCLangRes.getInstance().getStrByID("4006006_0", "04006006-0119")/*退货条件表达式名称*/, NCLangRes.getInstance().getStrByID("4006006_0", "04006006-0120")/*退货条件表述*/
    };
  }

  /**
   * 不显示字段列表
   * 
   * @return java.lang.String
   */
  @Override
  public java.lang.String[] getHiddenFieldCode() {
    return new String[] {
      "pk_returncndtn"
    };
  }

  /**
   * 此处插入方法说明。 创建日期：(2001-7-10 13:10:42)
   * 
   * @return java.lang.String
   */
  @Override
  public java.lang.String getPk_corp() {
    return this.pk_group;
  }

  /**
   * 要返回的主键字段名i.e. pk_deptdoc 创建日期：(01-4-4 0:57:23)
   * 
   * @return java.lang.String
   */
  @Override
  public String getPkFieldCode() {
    return "pk_returncndtn";
  }

  /**
   * 参照标题 创建日期：(01-4-4 0:57:23)
   * 
   * @return java.lang.String
   */
  @Override
  public String getRefTitle() {
    return NCLangRes.getInstance().getStrByID("4006006_0", "04006006-0128")/*退货条件*/;
  }

  /**
   * 参照数据库表或者视图名 创建日期：(01-4-4 0:57:23)
   * 
   * @return java.lang.String
   */
  @Override
  public String getTableName() {
    return "so_returncndtn";
  }

  /**
   * 此处添加公司编码 创建日期：(2001-7-9 15:13:48)
   * 
   * @return java.lang.String
   */
  @Override
  public String getWherePart() {
    String strWherePart = super.getWherePart();
    if ((null == strWherePart) || ("".equals(strWherePart.trim()))) {
      strWherePart = " pk_group='" + this.getPk_group().trim() + "' and dr =0 ";
    }
    else {
      strWherePart +=
          " and pk_group='" + this.getPk_group().trim() + "' and dr =0 ";
    }
    return strWherePart;
  }

  /**
   * 此处插入方法说明。 创建日期：(2001-7-10 13:10:42)
   * 
   * @param newPk_corp
   *          java.lang.String
   */
  @Override
  public void setPk_group(java.lang.String newPk_corp) {
    this.pk_group = newPk_corp;
  }
}
