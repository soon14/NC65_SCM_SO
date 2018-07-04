package nc.bs.so.tranmatrel.rule;

import nc.impl.pubapp.env.BSContext;
import nc.impl.pubapp.pattern.rule.IRule;
import nc.itf.so.pub.para.IPriorityCode;
import nc.vo.so.pub.para.MarBaseclPriorityCode;
import nc.vo.so.pub.para.MarSaleclPriorityCode;
import nc.vo.so.pub.para.SinglePriorityCode;
import nc.vo.so.pub.util.BaseSaleClassUtil;
import nc.vo.so.pub.util.PriorityCodeGenUtil;
import nc.vo.so.tranmatrel.entity.TranMatRelBVO;
import nc.vo.so.tranmatrel.entity.TranMatRelVO;

/**
 * @description
 * 销售订单物料关系保存前设置优先码(销售组织 (00) + 物料(0) + 物料分类(00) + 订单(0) + 订单分类)
 * @scene
 * 销售订单物料关系新增、修改保存前
 * @param
 * 无
 */
public class TranMatRelPriorityRule implements IRule<TranMatRelVO> {

  @Override
  public void process(TranMatRelVO[] vos) {
    for (TranMatRelVO vo : vos) {
      this.setPriority(vo);
    }
  }

  private IPriorityCode[] getPriorityCodeItems(TranMatRelBVO bvo,
      boolean ismarbase) {
    String pk_org = bvo.getPk_org();
    if (null == pk_org) {
      pk_org = BSContext.getInstance().getGroupID();
    }
    IPriorityCode[] codeitems = new IPriorityCode[3];
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
    codeitems[2] = new SinglePriorityCode(bvo.getTrantype());
    return codeitems;
  }

  /**
   * 设置优先码
   * 
   * @param vo
   */
  private void setPriority(TranMatRelVO vo) {
    // 优先码生成规则 销售组织 (00) + 物料(0) + 物料分类(00) + 客户(0) + 客户分类
    String pk_group = BSContext.getInstance().getGroupID();
    boolean ismarbase = BaseSaleClassUtil.isMarBaseClass(pk_group);
    TranMatRelBVO[] bvos = vo.getChildrenVO();
    if (null == bvos) {
      return;
    }
    for (TranMatRelBVO bvo : bvos) {
      IPriorityCode[] pricodeitems = this.getPriorityCodeItems(bvo, ismarbase);
      String pricode = PriorityCodeGenUtil.genPriorityCode(pricodeitems);
      bvo.setCprioritycode(pricode);
    }
  }

}
