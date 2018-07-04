package nc.pubimpl.so.m33.ic.m4c;

import java.util.HashMap;
import java.util.Map;

import nc.bs.so.m33.maintain.m4c.query.QuerySquare4CVOBizBP;
import nc.itf.so.m33.ref.ic.m4c.ICM4CServiceUtil;
import nc.pubitf.so.m33.ic.m4c.ISquareOutRushLinkQuery;
import nc.vo.ic.general.define.MetaNameConst;
import nc.vo.ic.m4c.entity.SaleOutBodyVO;
import nc.vo.ic.pub.define.ICPubMetaNameConst;
import nc.vo.pub.BusinessException;
import nc.vo.so.m33.m4c.entity.SquareOutDetailVO;
import nc.vo.so.m33.m4c.linkqryoutrush.entity.LinkQueryOutRushVO;
import nc.vo.so.m33.m4c.linkqryoutrush.entity.OutRushExeInfoVO;
import nc.vo.so.pub.util.SOVOChecker;
import nc.vo.so.pub.votools.SoVoTools;

public class SquareOutRushLinkQueryImpl implements ISquareOutRushLinkQuery {

  @Override
  public OutRushExeInfoVO[] outRushLinkQuery(LinkQueryOutRushVO[] paravo)
      throws BusinessException {
    // 查询销售出库单对冲信息
    Map<String, LinkQueryOutRushVO> moubbids =
        new HashMap<String, LinkQueryOutRushVO>();
    for (LinkQueryOutRushVO vo : paravo) {
      moubbids.put(vo.getOutbid(), vo);
    }
    String[] outbids = moubbids.keySet().toArray(new String[moubbids.size()]);
    SquareOutDetailVO[] sdvos =
        new QuerySquare4CVOBizBP().queryOutRushInfoForLinkQuery(outbids);
    OutRushExeInfoVO[] retvos = null;
    if (!SOVOChecker.isEmpty(sdvos)) {
      // 对冲出库单行id
      String[] rushoutbids =
          SoVoTools.getVOsOnlyValues(sdvos, SquareOutDetailVO.CRUSHGENERALBID);
      Map<String, SaleOutBodyVO> mapout =
          ICM4CServiceUtil.queryBodyItems(rushoutbids, new String[] {
            ICPubMetaNameConst.CROWNO, MetaNameConst.CGENERALBID
          });

      retvos = new OutRushExeInfoVO[sdvos.length];
      int i = 0;
      for (SquareOutDetailVO dvo : sdvos) {
        retvos[i] = new OutRushExeInfoVO();
        String outbid = dvo.getCsquarebillbid();
        LinkQueryOutRushVO para = moubbids.get(outbid);
        retvos[i].setCmaterialvid(para.getCmaterialvid());
        retvos[i].setCrowno(para.getOutRowNo());
        retvos[i].setCsquarebillbid(outbid);
        retvos[i].setCunitid(dvo.getCunitid());
        retvos[i].setNnum(dvo.getNnum());
        retvos[i].setNoutrushnum(dvo.getNsquarenum());
        retvos[i].setVrushbillcode(dvo.getVrushbillcode());
        SaleOutBodyVO outbvo = mapout.get(dvo.getCrushgeneralbid());
        retvos[i].setCrushbillrowno(outbvo.getCrowno());
        i++;
      }
    }
    return retvos;
  }

}
