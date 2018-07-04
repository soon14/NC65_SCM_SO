package nc.bs.so.custmatrel.rule;

import nc.impl.pubapp.env.BSContext;
import nc.impl.pubapp.pattern.rule.IRule;
import nc.itf.so.pub.para.IPriorityCode;
import nc.vo.so.custmatrel.entity.CustMatRelBVO;
import nc.vo.so.custmatrel.entity.CustMatRelVO;
import nc.vo.so.pub.para.CustBaseclPriorityCode;
import nc.vo.so.pub.para.CustSaleclPriorityCode;
import nc.vo.so.pub.para.MarBaseclPriorityCode;
import nc.vo.so.pub.para.MarSaleclPriorityCode;
import nc.vo.so.pub.para.SinglePriorityCode;
import nc.vo.so.pub.util.BaseSaleClassUtil;
import nc.vo.so.pub.util.PriorityCodeGenUtil;

/**
 * @description
 * 销售客户物料关系保存前设置优先码(销售组织 (00) + 物料(0) + 物料分类(00) + 客户(0) + 客户分类)
 * @scene
 * 销售客户物料关系新增、修改保存前
 * @param
 * 无
 */
public class CustMatRelPriorityRule implements IRule<CustMatRelVO> {

  @Override
  public void process(CustMatRelVO[] vos) {
    for (CustMatRelVO vo : vos) {
      this.setPriority(vo);
    }
  }

  private IPriorityCode[] getPriorityCodeItems(CustMatRelBVO bvo,
      boolean ismarbase, boolean iscustbase) {
    String pk_org = bvo.getPk_org();
    if (null == pk_org) {
      pk_org = BSContext.getInstance().getGroupID();
    }
    IPriorityCode[] codeitems = new IPriorityCode[4];
    // 物料
    codeitems[0] = new SinglePriorityCode(bvo.getPk_material());
    // 物料分类
    if (ismarbase) {
      codeitems[1] =
          new MarBaseclPriorityCode(bvo.getPk_materialbaseclass(), pk_org);
    }
    else {
      codeitems[1] =
          new MarSaleclPriorityCode(bvo.getPk_materialsaleclass(), pk_org);
    }
    // 客户
    codeitems[2] = new SinglePriorityCode(bvo.getPk_customer());
    // 客户分类
    if (iscustbase) {
      codeitems[3] =
          new CustBaseclPriorityCode(bvo.getPk_custbaseclass(), pk_org);
    }
    else {
      codeitems[3] =
          new CustSaleclPriorityCode(bvo.getPk_custsaleclass(), pk_org);
    }
    return codeitems;
  }

  /**
   * 设置优先码
   * 
   * @param vo
   */
  private void setPriority(CustMatRelVO vo) {
    // 优先码生成规则 销售组织 (00) + 物料(0) + 物料分类(00) + 客户(0) + 客户分类
    String pk_group = BSContext.getInstance().getGroupID();
    boolean ismarbase = BaseSaleClassUtil.isMarBaseClass(pk_group);
    boolean iscustbase = BaseSaleClassUtil.isCustBaseClass(pk_group);
    CustMatRelBVO[] bvos = vo.getChildrenVO();
    if (null == bvos) {
      return;
    }
    for (CustMatRelBVO bvo : bvos) {
      IPriorityCode[] pricodeitems =
          this.getPriorityCodeItems(bvo, ismarbase, iscustbase);
      String pricode = PriorityCodeGenUtil.genPriorityCode(pricodeitems);
      bvo.setCprioritycode(pricode);
    }
  }

}
