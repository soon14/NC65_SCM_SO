package nc.bs.so.depmatrel.rule;

import nc.impl.pubapp.env.BSContext;
import nc.impl.pubapp.pattern.rule.IRule;
import nc.itf.so.pub.para.IPriorityCode;
import nc.vo.so.depmatrel.entity.DepMatRelBVO;
import nc.vo.so.depmatrel.entity.DepMatRelVO;
import nc.vo.so.pub.para.MarBaseclPriorityCode;
import nc.vo.so.pub.para.MarSaleclPriorityCode;
import nc.vo.so.pub.para.SinglePriorityCode;
import nc.vo.so.pub.util.BaseSaleClassUtil;
import nc.vo.so.pub.util.PriorityCodeGenUtil;

/**
 * @description
 * 销售部门物料关系保存前设置优先码(销售组织 (00) + 物料(0) + 物料分类(00) + 部门(0) + 部门分类)
 * @scene
 * 销售部门物料关系新增、修改保存前
 * @param
 * 无
 */
public class DeptMatRelPriorityRule implements IRule<DepMatRelVO> {

  @Override
  public void process(DepMatRelVO[] vos) {
    for (DepMatRelVO vo : vos) {
      this.setPriority(vo);
    }
  }

  private IPriorityCode[] getPriorityCodeItems(DepMatRelBVO bvo,
      boolean ismarbase) {
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
    codeitems[2] = new SinglePriorityCode(bvo.getCemployeeid());
    codeitems[3] = new SinglePriorityCode(bvo.getPk_dept());
    return codeitems;
  }

  /**
   * 设置优先码
   * 
   * @param vo
   */
  private void setPriority(DepMatRelVO vo) {
    // 优先码生成规则 销售组织 (00) + 物料(0) + 物料分类(00) + 客户(0) + 客户分类
    String pk_group = BSContext.getInstance().getGroupID();
    boolean ismarbase = BaseSaleClassUtil.isMarBaseClass(pk_group);
    DepMatRelBVO[] bvos = vo.getChildrenVO();
    if (null == bvos) {
      return;
    }
    for (DepMatRelBVO bvo : bvos) {
      IPriorityCode[] pricodeitems = this.getPriorityCodeItems(bvo, ismarbase);
      String pricode = PriorityCodeGenUtil.genPriorityCode(pricodeitems);
      bvo.setCprioritycode(pricode);
    }
  }

}
