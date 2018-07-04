package nc.pubimpl.so.deptmatrel;

import java.util.List;
import java.util.Map;

import nc.impl.pubapp.env.BSContext;
import nc.pubimpl.so.deptmatrel.rule.DeptMatRelCheckRule;
import nc.pubimpl.so.deptmatrel.rule.DeptMatRelDeptExtendRule;
import nc.pubimpl.so.deptmatrel.rule.DeptMatRelEmpliyerExtendRule;
import nc.pubimpl.so.deptmatrel.rule.DeptMatRelFillIndexRule;
import nc.pubimpl.so.deptmatrel.rule.DeptMatRelMatExtendRule;
import nc.pubimpl.so.deptmatrel.rule.DeptMatRelMatchResultRule;
import nc.pubimpl.so.deptmatrel.rule.DeptMatRelNullValueChgRule;
import nc.pubimpl.so.deptmatrel.rule.DeptMatRelQueryRule;
import nc.pubimpl.so.deptmatrel.rule.DeptMatRelTableCreateRule;
import nc.pubimpl.so.deptmatrel.rule.DeptMatRelValidateRule;
import nc.pubitf.so.deptmatrel.DeptMatRelParaVO;
import nc.pubitf.so.deptmatrel.IDeptMatRelFor30;
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
public class DeptMatRelFor30Impl implements IDeptMatRelFor30 {
  @Override
  public UFBoolean checkDeptMatRel(DeptMatRelParaVO[] paravos)
      throws BusinessException {
    if (null == paravos) {
      return UFBoolean.TRUE;
    }
    this.addRule(paravos);
    return UFBoolean.TRUE;
  }

  private void addRule(DeptMatRelParaVO[] paravos) {
    // 1.检查匹配参数合法性
    new DeptMatRelValidateRule().validate(paravos);
    // 2.填充数据，给每个参数填充一个唯一标识
    new DeptMatRelFillIndexRule().fillIndex(paravos);
    // 3.扩展匹配参数
    DeptMatRelParaVO[] extendparas = this.extendParas(paravos);
    // 4.空值转换
    new DeptMatRelNullValueChgRule().changeNullValue(extendparas);
    // 创建临时表
    String tempTable =
        new DeptMatRelTableCreateRule().createParaTempTable(extendparas);
    // 查询部门、业务员与物料关系的结果
    DeptMatRelParaVO[] results =
        new DeptMatRelQueryRule().queryDeptMatRelParaVO(tempTable);
    // 匹配最优结果
    Map<Integer, DeptMatRelParaVO> voMap =
        new DeptMatRelMatchResultRule().match(results);
    // 检查匹配结果
    new DeptMatRelCheckRule().checkDeptMatRel(voMap, paravos);
  }

  /**
   * 扩展数据信息
   * 
   * @param paravos
   * 
   * @param csaleorgid
   * @param paravos
   * @return
   */
  private DeptMatRelParaVO[] extendParas(DeptMatRelParaVO[] paravos) {
    List<DeptMatRelParaVO> extendpara = null;
    String pk_group = BSContext.getInstance().getGroupID();
    // 扩展物料
    if (BaseSaleClassUtil.isMarBaseClass(pk_group)) {
      extendpara = new DeptMatRelMatExtendRule().extendBaseClass(paravos);
    }
    else {
      extendpara = new DeptMatRelMatExtendRule().extendMarSaleClass(paravos);
    }
    DeptMatRelEmpliyerExtendRule employer = new DeptMatRelEmpliyerExtendRule();
    employer.extendEmployer(extendpara);
    DeptMatRelDeptExtendRule dept = new DeptMatRelDeptExtendRule();
    dept.extendDept(extendpara);
    DeptMatRelParaVO[] extendparas = new DeptMatRelParaVO[extendpara.size()];
    extendpara.toArray(extendparas);
    return extendparas;
  }
}
