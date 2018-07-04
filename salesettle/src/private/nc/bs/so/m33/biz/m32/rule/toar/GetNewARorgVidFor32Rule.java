package nc.bs.so.m33.biz.m32.rule.toar;

import java.util.Map;

import nc.impl.pubapp.pattern.rule.IRule;
import nc.itf.scmpub.reference.uap.org.OrgUnitPubService;
import nc.vo.so.m33.m32.entity.SquareInvBVO;
import nc.vo.so.m33.m32.entity.SquareInvVO;
import nc.vo.so.m33.m32.entity.SquareInvVOUtils;
import nc.vo.so.pub.votools.SoVoTools;

/**
 * @description
 * 获取应收组织最新组织版本
 * @scene
 * 应收结算前
 * @param 
 * 无
 * @author zhangcheng
 * 
 */
public class GetNewARorgVidFor32Rule implements IRule<SquareInvVO> {

  @Override
  public void process(SquareInvVO[] vos) {
    SquareInvBVO[] bvos = SquareInvVOUtils.getInstance().getSquareInvBVO(vos);
    String[] arids = SoVoTools.getVOsOnlyValues(bvos, SquareInvBVO.CARORGID);
    if (arids == null || arids.length == 0) {
      return;
    }
    Map<String, String> omap = OrgUnitPubService.getOrgVid(arids);

    for (SquareInvBVO bvo : bvos) {
      bvo.setCarorgvid(omap.get(bvo.getCarorgid()));
    }
  }
}
