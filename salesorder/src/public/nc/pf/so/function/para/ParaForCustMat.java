package nc.pf.so.function.para;

import java.util.ArrayList;
import java.util.List;

import nc.pubitf.so.custmatrel.CustMatRelParaVO;
import nc.vo.pub.VOStatus;
import nc.vo.pubapp.pattern.pub.ListToArrayTool;
import nc.vo.so.m30.entity.SaleOrderBVO;
import nc.vo.so.m30.entity.SaleOrderHVO;
import nc.vo.so.m30.entity.SaleOrderVO;

/**
 * 客户与物料关系检查函数
 * 
 * @since 6.0
 * @version 2011-5-17 下午04:56:57
 * @author 祝会征
 */
public class ParaForCustMat {
  public CustMatRelParaVO[] getParavos(SaleOrderVO vo) {
    SaleOrderHVO hvo = vo.getParentVO();
    SaleOrderBVO[] bvos = vo.getChildrenVO();
    List<SaleOrderBVO> bvotemp = new ArrayList<SaleOrderBVO>();
    for (SaleOrderBVO bvo : bvos) {
      if (bvo.getStatus() != VOStatus.DELETED) {
        bvotemp.add(bvo);
      }
    }
    SaleOrderBVO[] bvosnodel =
        new ListToArrayTool<SaleOrderBVO>().convertToArray(bvotemp);
    CustMatRelParaVO[] paras = new CustMatRelParaVO[bvosnodel.length];
    int i = 0;
    for (SaleOrderBVO bvo : bvosnodel) {
      paras[i] = new CustMatRelParaVO();
      paras[i].setCrowno(bvo.getCrowno());
      paras[i].setPk_customer(hvo.getCcustomerid());
      paras[i].setPk_material(bvo.getCmaterialvid());
      paras[i].setPk_org(hvo.getPk_org());
      i++;
    }
    return paras;
  }
}
