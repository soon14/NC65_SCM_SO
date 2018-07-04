package nc.pubimpl.so.tranmatrel;

import java.util.List;
import java.util.Map;

import nc.impl.pubapp.env.BSContext;
import nc.pubimpl.so.tranmatrel.rule.TranMatRelCheckRule;
import nc.pubimpl.so.tranmatrel.rule.TranMatRelFillIndexRule;
import nc.pubimpl.so.tranmatrel.rule.TranMatRelMatExtendRule;
import nc.pubimpl.so.tranmatrel.rule.TranMatRelMatchResultRule;
import nc.pubimpl.so.tranmatrel.rule.TranMatRelNullValueChgRule;
import nc.pubimpl.so.tranmatrel.rule.TranMatRelOrgExtendRule;
import nc.pubimpl.so.tranmatrel.rule.TranMatRelQueryRule;
import nc.pubimpl.so.tranmatrel.rule.TranMatRelTableCreateRule;
import nc.pubimpl.so.tranmatrel.rule.TranMatRelTranExtendRule;
import nc.pubimpl.so.tranmatrel.rule.TranMatRelValidateRule;
import nc.pubitf.so.tanmatrel.ITranMatRelFor30;
import nc.pubitf.so.tanmatrel.TranMatRelParaVO;
import nc.vo.pub.BusinessException;
import nc.vo.pub.lang.UFBoolean;
import nc.vo.so.pub.util.BaseSaleClassUtil;

/**
 * 部门、业务员与物料关系定义
 * 
 * @since 6.0
 * @version 2011-4-20 下午01:43:11
 * @author 祝会征
 */
public class TranMatRelFor30Impl implements ITranMatRelFor30 {
  @Override
  public UFBoolean checkTranMatRel(TranMatRelParaVO[] paravos)
      throws BusinessException {
    if (null == paravos) {
      return UFBoolean.TRUE;
    }
    this.addRule(paravos);
    return UFBoolean.TRUE;
  }

  private void addRule(TranMatRelParaVO[] paravos) {
    // 1.检查匹配参数合法性
    new TranMatRelValidateRule().validate(paravos);
    // 2.填充数据，给每个参数填充一个唯一标识
    new TranMatRelFillIndexRule().fillIndex(paravos);
    // 3.扩展匹配参数
    TranMatRelParaVO[] extendparas = this.extendParas(paravos);
    // 4.空值转换
    new TranMatRelNullValueChgRule().changeNullValue(extendparas);
    // 创建临时表
    String tempTable =
        new TranMatRelTableCreateRule().createParaTempTable(extendparas);
    // 查询单据类型与物料关系的结果
    TranMatRelParaVO[] results =
        new TranMatRelQueryRule().queryTranMatRelParaVO(tempTable);
    // 匹配最优结果
    Map<Integer, TranMatRelParaVO> voMap =
        new TranMatRelMatchResultRule().match(results);
    // 检查匹配结果
    new TranMatRelCheckRule().checkTranMatRel(voMap, paravos);
  }

  /**
   * 扩展数据信息
   * 
   * @param csaleorgid
   * @param paravos
   * @return
   */
  private TranMatRelParaVO[] extendParas(TranMatRelParaVO[] paravos) {
    List<TranMatRelParaVO> extendpara = null;
    String pk_group = BSContext.getInstance().getGroupID();
    // 扩展物料
    if (BaseSaleClassUtil.isMarBaseClass(pk_group)) {
      extendpara = new TranMatRelMatExtendRule().extendBaseClass(paravos);
    }
    else {
      extendpara = new TranMatRelMatExtendRule().extendMarSaleClass(paravos);
    }
    // 扩展销售组织
    String pk_org = paravos[0].getPk_org();
    new TranMatRelOrgExtendRule().extendSaleOrg(pk_org, extendpara);
    new TranMatRelTranExtendRule().extendTrans(extendpara);
    TranMatRelParaVO[] extendparas = new TranMatRelParaVO[extendpara.size()];
    extendpara.toArray(extendparas);
    return extendparas;
  }
}
