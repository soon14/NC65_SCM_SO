package nc.pf.so.function.para;

import java.util.ArrayList;
import java.util.List;

import nc.pubitf.so.deptmatrel.DeptMatRelParaVO;
import nc.vo.pub.VOStatus;
import nc.vo.pubapp.pattern.pub.ListToArrayTool;
import nc.vo.so.m30.entity.SaleOrderBVO;
import nc.vo.so.m30.entity.SaleOrderHVO;
import nc.vo.so.m30.entity.SaleOrderVO;

/**
 * 部门与物料关系检查
 * 
 * @since 6.0
 * @version 2011-5-17 下午06:05:05
 * @author 祝会征
 */
public class ParaForDeptMat {
  public DeptMatRelParaVO[] getParavos(SaleOrderVO vo) {
    SaleOrderHVO hvo = vo.getParentVO();
    SaleOrderBVO[] bvos = vo.getChildrenVO();
    List<SaleOrderBVO> bvotemp=new ArrayList<SaleOrderBVO>();
    for (SaleOrderBVO bvo : bvos){
    	if(bvo.getStatus()!=VOStatus.DELETED){
    		bvotemp.add(bvo);
    	}
    }
    SaleOrderBVO[] bvosnodel=new ListToArrayTool<SaleOrderBVO>().convertToArray(bvotemp);
    DeptMatRelParaVO[] paras = new DeptMatRelParaVO[bvosnodel.length];
    int i = 0;
    for (SaleOrderBVO bvo : bvosnodel) {
      paras[i] = new DeptMatRelParaVO();
      paras[i].setCrowno(bvo.getCrowno());
      paras[i].setPk_material(bvo.getCmaterialvid());
      paras[i].setPk_dept(hvo.getCdeptid());
      paras[i].setPk_org(hvo.getPk_org());
      paras[i].setCemployeeid(hvo.getCemployeeid());
      i++;
    }
    return paras;
  }
}
