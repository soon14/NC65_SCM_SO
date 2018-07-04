package nc.bs.so.depmatrel.rule;

import java.util.HashSet;
import java.util.Set;

import nc.bs.ml.NCLangResOnserver;
import nc.impl.pubapp.pattern.rule.IRule;
import nc.vo.pub.VOStatus;
import nc.vo.pubapp.pattern.exception.ExceptionUtils;
import nc.vo.pubapp.pattern.pub.PubAppTool;
import nc.vo.so.depmatrel.entity.DepMatRelBVO;
import nc.vo.so.depmatrel.entity.DepMatRelHVO;
import nc.vo.so.depmatrel.entity.DepMatRelVO;
import nc.vo.trade.checkrule.VOChecker;

 /**
 * @description
 * 销售部门物料关系保存前检查（非空项物料分类与物料不能全部为空、不可重复[部门+业务员+物料基本分类+物料销售分类+物料]不允许重复）
 * @scene
 * 销售部门物料关系新增、修改保存前
 * @param
 * 无
 * @author gdsjw
 */
public class CheckSaveBillRule implements IRule<DepMatRelVO> {
  public CheckSaveBillRule() {
    //
  }

  @Override
  public void process(DepMatRelVO[] vos) {
    for (DepMatRelVO vo : vos) {
      // 这个是补全VO，校验时可能需要区分行状态
      this.checkNotNull(vo);
      this.checkUnique(vo);
    }
  }

  private void checkNotNull(DepMatRelVO bill) {
    DepMatRelHVO header = bill.getParentVO();
    Integer allowsale = header.getAllowsale();
    if (VOChecker.isEmpty(allowsale)) {
      ExceptionUtils.wrappBusinessException(nc.vo.ml.NCLangRes4VoTransl.getNCLangRes().getStrByID("4006007_0","04006007-0004")/*@res "允许销售/禁止销售不可为空。"*/);
    }
    String pk_org = header.getPk_org();
    if (VOChecker.isEmpty(pk_org)) {
      ExceptionUtils.wrappBusinessException(nc.vo.ml.NCLangRes4VoTransl.getNCLangRes().getStrByID("4006007_0","04006007-0005")/*@res "销售组织不可为空。"*/);
    }
    DepMatRelBVO[] items = bill.getChildrenVO();
    if ((items != null) && (items.length > 0)) {
      for (DepMatRelBVO item : items) {
        int vostatus = item.getStatus();
        if ((vostatus == VOStatus.DELETED) || (vostatus == VOStatus.UNCHANGED)) {
          // 不检查删除、没变化的行
          continue;
        }
        String materialbaseclass = item.getPk_materialbaseclass();
        boolean ismaterialbaseclassnull = false;
        if (PubAppTool.isNull(materialbaseclass)) {
          ismaterialbaseclassnull = true;
        }
        String materialsaleclass = item.getPk_materialsaleclass();
        boolean ismaterialsaleclassnull = false;
        if (PubAppTool.isNull(materialsaleclass)) {
          ismaterialsaleclassnull = true;
        }
        String material_v = item.getPk_material_v();
        boolean ismaterial_vnull = false;
        if (PubAppTool.isNull(material_v)) {
          ismaterial_vnull = true;
        }
        if (ismaterialbaseclassnull && ismaterialsaleclassnull
            && ismaterial_vnull) {
          ExceptionUtils.wrappBusinessException(nc.vo.ml.NCLangRes4VoTransl.getNCLangRes().getStrByID("4006007_0","04006007-0006")/*@res "物料分类与物料不能全部为空。"*/);
        }
      }
    }
  }

  private void checkUnique(DepMatRelVO bill) {
    Set<String> relCollections = new HashSet<String>();
    DepMatRelBVO[] items = bill.getChildrenVO();
    if ((items != null) && (items.length > 0)) {
      String nullstring = "NULL";
      StringBuilder sbd = new StringBuilder();
      for (DepMatRelBVO item : items) {
        int vostatus = item.getStatus();
        if (vostatus == VOStatus.DELETED) {
          // 不检查删除的行
          continue;
        }
        String materialbaseclass = item.getPk_materialbaseclass();
        if (PubAppTool.isNull(materialbaseclass)) {
          materialbaseclass = nullstring;
        }
        String materialsaleclass = item.getPk_materialsaleclass();
        if (PubAppTool.isNull(materialsaleclass)) {
          materialsaleclass = nullstring;
        }
        String material_v = item.getPk_material_v();
        if (PubAppTool.isNull(material_v)) {
          material_v = nullstring;
        }
        String dept_v = item.getPk_dept_v();
        if (PubAppTool.isNull(dept_v)) {
          dept_v = nullstring;
        }
        String employeeid = item.getCemployeeid();
        if (PubAppTool.isNull(employeeid)) {
          employeeid = nullstring;
        }
        sbd.delete(0, sbd.length());
        sbd.append(materialbaseclass).append(materialsaleclass)
            .append(material_v).append(dept_v).append(employeeid);
        if (relCollections.contains(sbd.toString())) {
          ExceptionUtils
              .wrappBusinessException(NCLangResOnserver.getInstance().getStrByID("4006007_0", "04006007-0012")/*[部门+业务员+物料基本分类+物料销售分类+物料]不允许重复。*/);
        }
        else {
          relCollections.add(sbd.toString());
        }
      }
    }
  }

}