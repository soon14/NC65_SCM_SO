package nc.pubimpl.so.custmatrel;

import java.util.List;
import java.util.Map;

import nc.impl.pubapp.env.BSContext;
import nc.pubimpl.so.custmatrel.rule.CustMatRelCheckRule;
import nc.pubimpl.so.custmatrel.rule.CustMatRelCustExtendRule;
import nc.pubimpl.so.custmatrel.rule.CustMatRelFillIndexRule;
import nc.pubimpl.so.custmatrel.rule.CustMatRelMatExtendRule;
import nc.pubimpl.so.custmatrel.rule.CustMatRelMatchResultRule;
import nc.pubimpl.so.custmatrel.rule.CustMatRelNullValueChgRule;
import nc.pubimpl.so.custmatrel.rule.CustMatRelOtherExtendRule;
import nc.pubimpl.so.custmatrel.rule.CustMatRelQueryRule;
import nc.pubimpl.so.custmatrel.rule.CustMatRelTableCreateRule;
import nc.pubimpl.so.custmatrel.rule.CustMatRelValidateRule;
import nc.pubitf.so.custmatrel.CustMatRelParaVO;
import nc.pubitf.so.custmatrel.ICustMatRelFor30;
import nc.vo.pub.BusinessException;
import nc.vo.pub.lang.UFBoolean;
import nc.vo.so.pub.util.BaseSaleClassUtil;

public class CustMatRelFor30Impl implements ICustMatRelFor30 {

  @Override
  public void checkCustMatRel(CustMatRelParaVO[] paravos)
      throws BusinessException {

    // 填充数据，给每个参数填充一个唯一标识
    new CustMatRelFillIndexRule().fillIndex(paravos);
    // 检查匹配参数合法性，记录合法标志
    new CustMatRelValidateRule().validate(paravos);
    // 扩展匹配参数
    CustMatRelParaVO[] extendparas = this.extendParas(paravos);
    // 空值转换
    new CustMatRelNullValueChgRule().changeNullValue(extendparas);
    // 创建临时表
    String tempTable =
        new CustMatRelTableCreateRule().createParaTempTable(extendparas);
    // 查询客户与物料关系的结果
    CustMatRelParaVO[] results =
        new CustMatRelQueryRule().queryCustMatRelParaVO(tempTable);
    // 匹配最优结果
    Map<Integer, CustMatRelParaVO> voMap =
        new CustMatRelMatchResultRule().match(results);
    // 检查匹配结果
    CustMatRelCheckRule checkrule = new CustMatRelCheckRule();
    checkrule.checkCustMatRel(voMap, paravos);

  }

  /**
   * 检查客户和物料关系,记录是否允许销售客户和物料
   * 
   * @param paravos 客户和物料集合
   * @return UFBoolean[] 客户和物料是否允许销售
   */
  @Override
  public UFBoolean[] getCustMatRelSaleFlag(CustMatRelParaVO[] paravos) {

    // 填充数据，给每个参数填充一个唯一标识
    new CustMatRelFillIndexRule().fillIndex(paravos);
    // 检查匹配参数合法性，记录合法标志
    UFBoolean[] validateNull = new CustMatRelValidateRule().validate(paravos);
    // 扩展匹配参数
    CustMatRelParaVO[] extendparas = this.extendParas(paravos);
    // 空值转换
    new CustMatRelNullValueChgRule().changeNullValue(extendparas);
    // 创建临时表
    String tempTable =
        new CustMatRelTableCreateRule().createParaTempTable(extendparas);
    // 查询客户与物料关系的结果
    CustMatRelParaVO[] results =
        new CustMatRelQueryRule().queryCustMatRelParaVO(tempTable);
    // 匹配最优结果
    Map<Integer, CustMatRelParaVO> voMap =
        new CustMatRelMatchResultRule().match(results);
    // 检查匹配结果，记录合法标志
    CustMatRelCheckRule checkrule = new CustMatRelCheckRule();
    UFBoolean[] matchCstMtrl =
        checkrule.getCustMatRelCheckResult(voMap, paravos);
    // 合并检查匹配结果,和空值效验结果
    for (int i = 0; i < validateNull.length; i++) {
      if (null == matchCstMtrl[i]) {
        matchCstMtrl[i] = UFBoolean.TRUE;
      }
      if (!validateNull[i].booleanValue() || !matchCstMtrl[i].booleanValue()) {
        matchCstMtrl[i] = UFBoolean.FALSE;
      }
      else {
        matchCstMtrl[i] = UFBoolean.TRUE;
      }
    }
    return matchCstMtrl;
  }

  /**
   * 扩展数据信息
   * 
   * @param csaleorgid
   * @param paravos
   * @return
   */
  private CustMatRelParaVO[] extendParas(CustMatRelParaVO[] paravos) {
    List<CustMatRelParaVO> extendpara = null;
    String pk_group = BSContext.getInstance().getGroupID();
    String pk_org = paravos[0].getPk_org();
    // 扩展物料
    if (BaseSaleClassUtil.isMarBaseClass(pk_group)) {
      extendpara = new CustMatRelMatExtendRule().extendBaseClass(paravos);
    }
    else {
      extendpara = new CustMatRelMatExtendRule().extendMarSaleClass(paravos);
    }
    // 扩展客户
    if (BaseSaleClassUtil.isCustBaseClass(pk_group)) {
      new CustMatRelCustExtendRule().extendCustBaseClass(pk_org, extendpara);
    }
    else {
      new CustMatRelCustExtendRule().extendCustSaleClass(pk_org, extendpara);
    }
    new CustMatRelOtherExtendRule().extendOther(extendpara);
    CustMatRelParaVO[] extendparas = new CustMatRelParaVO[extendpara.size()];
    extendpara.toArray(extendparas);
    return extendparas;
  }
}
