package nc.pf.so.function.para;

import java.util.ArrayList;
import java.util.List;

import nc.pubitf.so.m30.ReturnAssignMatchVO;
import nc.vo.pub.VOStatus;
import nc.vo.pub.lang.UFDouble;
import nc.vo.pubapp.pattern.pub.ListToArrayTool;
import nc.vo.so.m30.entity.SaleOrderBVO;
import nc.vo.so.m30.entity.SaleOrderHVO;
import nc.vo.so.m30.entity.SaleOrderVO;

/**
 * 退货政策检查
 * 
 * @since 6.0
 * @version 2011-5-17 下午06:29:11
 * @author 祝会征
 */
public class ParaForReturn {
  public ReturnAssignMatchVO[] getParavos(SaleOrderVO vo) {
    SaleOrderHVO hvo = vo.getParentVO();
    SaleOrderBVO[] bvos = vo.getChildrenVO();
    List<ReturnAssignMatchVO> paras = new ArrayList<ReturnAssignMatchVO>();

    for (SaleOrderBVO bvo : bvos) {
      // 只有退货需要检查和匹配退货政策
      if (bvo.getNnum() != null
          && bvo.getNnum().compareTo(UFDouble.ZERO_DBL) > 0) {
        continue;
      }
      if (VOStatus.DELETED == bvo.getStatus()) {
        continue;
      }
      ReturnAssignMatchVO para = new ReturnAssignMatchVO();
      para.setCrowno(bvo.getCrowno());
      para.setPk_customer(hvo.getCcustomerid());
      para.setPk_material(bvo.getCmaterialvid());
      para.setPk_productline(bvo.getCprodlineid());
      para.setPk_saleorg(hvo.getPk_org());
      para.setCsaleorderbid(bvo.getCsaleorderbid());
      para.setCfirstid(bvo.getCfirstid());
      para.setCretreasonid(bvo.getCretreasonid());
      para.setCretpolicyid(bvo.getCretpolicyid());
      para.setCsrcid(bvo.getCsrcid());
      para.setDbilldate(hvo.getDbilldate());
      para.setVfirsttype(bvo.getVfirsttype());
      para.setVsrctype(bvo.getVsrctype());
      para.setNorigtaxmny(bvo.getNorigtaxmny());
      para.setNnum(bvo.getNnum());
      para.setBlargessflag(bvo.getBlargessflag());
      para.setPk_group(bvo.getPk_group());
      paras.add(para);
    }

    if (paras.isEmpty()) {
      return null;
    }

    return new ListToArrayTool<ReturnAssignMatchVO>().convertToArray(paras);

  }
}
