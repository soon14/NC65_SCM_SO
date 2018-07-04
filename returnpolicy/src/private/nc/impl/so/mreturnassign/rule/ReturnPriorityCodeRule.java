package nc.impl.so.mreturnassign.rule;

import nc.impl.pubapp.env.BSContext;
import nc.impl.pubapp.pattern.rule.IRule;
import nc.itf.so.pub.para.IPriorityCode;
import nc.vo.bd.meta.BatchOperateVO;
import nc.vo.pub.VOStatus;
import nc.vo.so.mreturnassign.entity.ReturnAssignVO;
import nc.vo.so.pub.para.CustBaseclPriorityCode;
import nc.vo.so.pub.para.CustSaleclPriorityCode;
import nc.vo.so.pub.para.MarBaseclPriorityCode;
import nc.vo.so.pub.para.MarSaleclPriorityCode;
import nc.vo.so.pub.para.SinglePriorityCode;
import nc.vo.so.pub.util.BaseSaleClassUtil;
import nc.vo.so.pub.util.PriorityCodeGenUtil;
import nc.vo.trade.checkrule.VOChecker;

public class ReturnPriorityCodeRule implements IRule<BatchOperateVO> {

  @Override
  public void process(BatchOperateVO[] vos) {
    for (BatchOperateVO vo : vos) {
      this.setPriority(vo);
    }
  }

  private IPriorityCode[] getPriorityCodeItems(ReturnAssignVO vo,
      boolean ismarbase, boolean iscustbase) {
    String pk_org = vo.getPk_saleorg();
    if (null == pk_org) {
      pk_org = BSContext.getInstance().getGroupID();
    }
    IPriorityCode[] codeitems = new IPriorityCode[5];
    // 客户
    codeitems[0] = new SinglePriorityCode(vo.getPk_customer());
    // 客户分类
    if (iscustbase) {
      codeitems[1] = new CustBaseclPriorityCode(vo.getPk_custclass(), pk_org);
    }
    else {
      codeitems[1] =
          new CustSaleclPriorityCode(vo.getPk_custsaleclass(), pk_org);
    }
    // 物料
    codeitems[2] = new SinglePriorityCode(vo.getPk_productline());
    codeitems[3] = new SinglePriorityCode(vo.getPk_material());
    // 物料分类
    if (ismarbase) {
      codeitems[4] = new MarBaseclPriorityCode(vo.getPk_marbasclass(), pk_org);
    }
    else {
      codeitems[4] = new MarSaleclPriorityCode(vo.getPk_marsaleclass(), pk_org);
    }
   
    return codeitems;
  }

  /**
   * 设置优先码
   * 
   * @param vo
   */
  private void setPriority(BatchOperateVO vo) {
    Object[] addVOs = vo.getAddObjs();
    Object[] updateVOs = vo.getUpdObjs();
    if ((VOChecker.isEmpty(updateVOs) || updateVOs.length == 0)
        && (addVOs == null || addVOs.length == 0)) {
      return;
    }
    Object[] newVOs = new Object[updateVOs.length + addVOs.length];
    System.arraycopy(addVOs, 0, newVOs, 0, addVOs.length);
    System.arraycopy(updateVOs, 0, newVOs, addVOs.length, updateVOs.length);
    // 优先码生成规则 销售组织 (00)+ 客户(0) + 客户分类 + 物料(0) + 物料分类(00) 
    String pk_group = BSContext.getInstance().getGroupID();
    boolean ismarbase = BaseSaleClassUtil.isMarBaseClass(pk_group);
    boolean iscustbase = BaseSaleClassUtil.isCustBaseClass(pk_group);

    for (Object obj : newVOs) {
      ReturnAssignVO assignVO = (ReturnAssignVO) obj;
      if (VOStatus.UNCHANGED == assignVO.getStatus()) {
        continue;
      }
      IPriorityCode[] pricodeitems =
          this.getPriorityCodeItems(assignVO, ismarbase, iscustbase);
      String pricode = PriorityCodeGenUtil.genPriorityCode(pricodeitems);
      assignVO.setCprioritycode(pricode);
    }
  }
}
