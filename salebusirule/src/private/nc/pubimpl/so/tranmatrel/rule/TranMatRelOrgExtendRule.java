package nc.pubimpl.so.tranmatrel.rule;

import java.util.List;

import nc.impl.pubapp.env.BSContext;
import nc.itf.scmpub.reference.uap.org.SaleOrgPubService;
import nc.pubitf.so.tanmatrel.TranMatRelParaVO;
import nc.vo.bd.accessor.IBDData;
import nc.vo.so.pub.util.ObjectUtil;

/**
 * 订单类型与物料关系：扩展销售组织信息
 * 
 * @since 6.0
 * @version 2011-4-14 下午06:38:55
 * @author 祝会征
 */
public class TranMatRelOrgExtendRule {
  /**
   * 扩展销售组织信息
   * 
   * @param csaleorgid
   * @param matchparas
   * @return
   */
  public List<TranMatRelParaVO> extendSaleOrg(String csaleorgid,
      List<TranMatRelParaVO> listpara) {
    String pk_group = BSContext.getInstance().getGroupID();
    List<IBDData> fathersaleorgs =
        SaleOrgPubService.queryFatherSale(pk_group, csaleorgid, false);

    if (null == fathersaleorgs || fathersaleorgs.size() == 0) {
      return listpara;
    }
    TranMatRelParaVO[] vos = new TranMatRelParaVO[listpara.size()];
    listpara.toArray(vos);
    for (IBDData bddata : fathersaleorgs) {
      String fatherorg = bddata.getPk();
      for (TranMatRelParaVO vo : vos) {
        TranMatRelParaVO fatherpara =
            (TranMatRelParaVO) ObjectUtil.serializableClone(vo);
        vo.setPk_org(null);
        vo.setPk_fatherorg(fatherorg);
        listpara.add(fatherpara);
      }
    }
    return listpara;
  }
}
