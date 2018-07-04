package nc.ui.so.custmatrel.action;

import java.util.ArrayList;
import java.util.List;

import nc.bs.framework.common.NCLocator;
import nc.bs.ml.NCLangResOnserver;
import nc.itf.so.custmatrel.ICustMatRelMaintain;
import nc.ui.so.custmatrel.model.CustMatRelBillManageModel;
import nc.ui.trade.excelimport.Uif2BodyOnlyImportablePanel;
import nc.ui.trade.excelimport.vo.CommonAggVO2;
import nc.ui.trade.excelimport.vo.DataRowVO;
import nc.ui.uif2.UIState;
import nc.vo.pub.CircularlyAccessibleValueObject;
import nc.vo.pub.ExtendedAggregatedValueObject;
import nc.vo.pubapp.pattern.exception.ExceptionUtils;
import nc.vo.so.base.entity.AllowSale;
import nc.vo.so.custmatrel.entity.CustMatRelBVO;
import nc.vo.so.custmatrel.entity.CustMatRelHVO;
import nc.vo.so.custmatrel.entity.CustMatRelVO;

/**
 * 导入处理类
 * 
 * @since 6.3
 * @version 2013-05-17 13:57:37
 * @author liujingn
 */
@SuppressWarnings("restriction")
public class CustMaterImportableEditor extends Uif2BodyOnlyImportablePanel {

  /**
   * jilu for 爱婴岛 暂存下需要保存的VO
   */
  private List<ExtendedAggregatedValueObject> toSaveVOs =
      new ArrayList<ExtendedAggregatedValueObject>();

  @Override
  public void addNew() {
    ((CustMatRelBillManageModel) this.getAppModel()).initModel(null);
    ((CustMatRelBillManageModel) this.getAppModel()).setUiState(UIState.ADD);
  }

  /**
   *
   */
  public CustMaterImportableEditor() {

    super(null, null, null);
  }

  /**
   * 
   * @param title
   * @param funCode
   * @param configFilePath
   */
  public CustMaterImportableEditor(
      String title, String funCode, String configFilePath) {
    super(title, funCode, configFilePath);
  }

  @Override
  protected void setProcessedVO(ExtendedAggregatedValueObject eavo) {
    // // 使用模板设值
    // this.getBillData().setImportBillValueVO(eavo);
    // // 设置必输项
    // this.setDefaultValue(eavo);
    // // 导入修改处理：如果该组织下存在客户物料关系，那么就认为是导入修改操作，否则就是新增
    // String pkorg = this.getAppModel().getContext().getPk_org();
    // ICustMatRelMaintain service =
    // NCLocator.getInstance().lookup(ICustMatRelMaintain.class);
    // CustMatRelVO custmtvo = null;
    // try {
    // custmtvo = service.queryByOrg(pkorg);
    // }
    // catch (BusinessException ex) {
    // ExceptionUtils.wrappException(ex);
    // }
    //
    // this.importUpdate(custmtvo);

  }

  @Override
  protected String getAppModelBeanName() {
    return null;
  }

  @Override
  protected String getAddActionBeanName() {
    return null;
  }

  @Override
  protected String getSaveActionBeanName() {
    return null;
  }

  @Override
  protected String getCancelActionBeanName() {
    return null;
  }

  @Override
  protected String getBillCardEditorBeanName() {
    return null;
  }

  /**
   * 保存操作 jilu for 爱婴岛 不再走前台的保存，而是将VO直接传到后台进行保存。
   */
  public void save() throws Exception {
    if (toSaveVOs == null) {
      return;
    }
    for (ExtendedAggregatedValueObject aggVO : toSaveVOs) {
      if (aggVO.getParentVO() == null) {
        ExceptionUtils.wrappBusinessException(NCLangResOnserver.getInstance()
            .getStrByID("4006007_0", "04006007-0065")/* 导入失败！导入的表头数据不能为空。 */);
      }
      if (aggVO.getTableCodes() == null) {
        ExceptionUtils.wrappBusinessException(NCLangResOnserver.getInstance()
            .getStrByID("4006007_0", "04006007-0066")/* 导入失败！导入的表体数据不能为空。 */);
      }
      Object allowsale =
          aggVO.getParentVO().getAttributeValue(CustMatRelHVO.ALLOWSALE);

      if (AllowSale.ALLOW_SALE.getName().equals(allowsale)) {
        aggVO.getParentVO().setAttributeValue(CustMatRelHVO.ALLOWSALE,
            AllowSale.ALLOW_SALE.getIntValue());
      }
      else if (AllowSale.FORBID_SALE.getName().equals(allowsale)) {
        aggVO.getParentVO().setAttributeValue(CustMatRelHVO.ALLOWSALE,
            AllowSale.FORBID_SALE.getIntValue());
      }
      
      if (!AllowSale.ALLOW_SALE.equalsValue(allowsale)
          && !AllowSale.FORBID_SALE.equalsValue(allowsale)
          && !AllowSale.ALLOW_SALE.getName().equals(allowsale)
          && !AllowSale.FORBID_SALE.getName().equals(allowsale)) {
        ExceptionUtils.wrappBusinessException(NCLangResOnserver.getInstance()
            .getStrByID("4006007_0", "04006007-0062")/*
                                                      * 允许销售/禁止销售输入错误，请录入1或2。(1,允许销售
                                                      * ;2,禁止销售)
                                                      */);
      }

    }
    CustMatRelVO[] aggVOs = this.transExtendedAggVOToAggVO(getToSaveVOs());

    // 对前台导入的文件进行校验，把导入文件的编码转为pk并存到数据库内
    NCLocator.getInstance().lookup(ICustMatRelMaintain.class).importXLS(aggVOs);
  }

  /**
   * 构造多个aggVO集合
   * 
   * @param toSaveVOs2
   * @return
   */
  private CustMatRelVO[] transExtendedAggVOToAggVO(
      List<ExtendedAggregatedValueObject> toSaveVOs2) {
    CustMatRelVO[] CustMatRelVOs = new CustMatRelVO[toSaveVOs2.size()];
    // 这里将导入文件根据销售组织的不同合成多个聚合VO，这里在导入文件内限定只能导入一个销售组织的文件，故这里返回的聚合vo只有一张单据
    for (int i = 0; i < toSaveVOs2.size(); i++) {
      CommonAggVO2 commonAggVO = (CommonAggVO2) toSaveVOs2.get(i);
      CustMatRelVOs[i] = this.createCustAggVOByCommonAggVO(commonAggVO);
    }
    return CustMatRelVOs;
  }

  /**
   * 聚合成aggVO
   * 
   * @param commonAggVO
   * @return
   */
  private CustMatRelVO createCustAggVOByCommonAggVO(CommonAggVO2 commonAggVO) {
    // 构造表头vo
    CustMatRelHVO relHVO = this.createCustMatRelHVO(commonAggVO);
    // 构造表体vo
    CustMatRelBVO[] relBVO = this.createCustMatRelBVO(commonAggVO);
    CustMatRelVO custMatRelVO = new CustMatRelVO();
    // 构造聚合vo
    custMatRelVO.setParentVO(relHVO);
    custMatRelVO.setChildrenVO(relBVO);
    return custMatRelVO;
  }

  /**
   * 取出表格中表体对应数据，构建BVO
   * 
   * @param toSaveVOs2
   * @return
   */
  private CustMatRelBVO[] createCustMatRelBVO(CommonAggVO2 commonAggVO) {
    CircularlyAccessibleValueObject[] dataRowVOs =
        commonAggVO.getTableVO(commonAggVO.getTableCodes()[0]);
    CustMatRelBVO[] custMatRelBVOs = new CustMatRelBVO[dataRowVOs.length];
    // 表体存在多行，使用双重for循环，给每一行的表体赋值
    for (int i = 0; i < dataRowVOs.length; i++) {
      // 获取dataRowVO中的fiield（字段名），再根据field获取该字段的编码
      DataRowVO dataRowVO = (DataRowVO) dataRowVOs[i];
      for (String field : dataRowVO.getAttributeNames()) {
        String value = dataRowVO.getAttributeValue(field).toString();
        if (value != null) {
          value = value.equals("") ? null : value;
        }
        if (custMatRelBVOs[i] == null) {
          custMatRelBVOs[i] = new CustMatRelBVO();
        }

        if (CustMatRelBVO.EXCLUDE.equals(field)) {
          if (!"Y".equals(value) && !"N".equals(value)) {
            ExceptionUtils.wrappBusinessException(NCLangResOnserver
                .getInstance().getStrByID("4006007_0", "04006007-0064")/*
                                                                        * 不包含字段为勾选框导入请输入N
                                                                        * 、Y。
                                                                        */);
          }
        }
        // 将获取的编码存到bvo内 并返回bvo
        custMatRelBVOs[i].setAttributeValue(field, value);
      }
    }
    return custMatRelBVOs;
  }

  /**
   * 取出表格中表头对应数据，构建HVO
   * 
   * @param toSaveVOs2
   * @return
   */
  private CustMatRelHVO createCustMatRelHVO(CommonAggVO2 commonAggVO) {
    CustMatRelHVO custMatRelHVO = new CustMatRelHVO();
    DataRowVO dataRowVO = (DataRowVO) commonAggVO.getParentVO();
    // 获取dataRowVO中的fiield（字段名），再根据field获取该字段的编码
    for (String field : dataRowVO.getAttributeNames()) {
      String value = dataRowVO.getAttributeValue(field).toString();
      if (value != null) {
        value = value.equals("") ? null : value;
      }
      // 将获取的编码存到hvo内 并返回hvo
      custMatRelHVO.setAttributeValue(field, value);
    }
    return custMatRelHVO;
  }

  public List<ExtendedAggregatedValueObject> getToSaveVOs() {
    return this.toSaveVOs;
  }

  public void setToSaveVOs(List<ExtendedAggregatedValueObject> toSaveVOs) {
    this.toSaveVOs = toSaveVOs;
  }
}
