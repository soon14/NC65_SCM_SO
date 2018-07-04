package nc.bs.so.m33.biz.m4453.rule.ar;

import java.util.Map;

import nc.impl.pubapp.pattern.rule.IRule;
import nc.itf.scmpub.reference.uap.org.OrgUnitPubService;
import nc.vo.so.m33.m4453.entity.SquareWasBVO;
import nc.vo.so.m33.m4453.entity.SquareWasVO;
import nc.vo.so.m33.m4453.entity.SquareWasVOUtils;
import nc.vo.so.pub.votools.SoVoTools;

/**
 * @description
 * 传应收前 获取应收组织最新组织版本
 * @scene
 * 销售途损应收结算、回冲应收结算前
 * @param
 * 无
 * @author zhangcheng
 * 
 */
public class GetNewARorgVidFor4453Rule implements IRule<SquareWasVO> {

  @Override
  public void process(SquareWasVO[] vos) {
    SquareWasBVO[] bvos = SquareWasVOUtils.getInstance().getSquareWasBVO(vos);

    String[] arids = SoVoTools.getVOsOnlyValues(bvos, SquareWasBVO.CARORGID);

    if (arids == null || arids.length == 0) {
      return;
    }

    Map<String, String> omap = OrgUnitPubService.getOrgVid(arids);

    for (SquareWasBVO bvo : bvos) {
      bvo.setCarorgvid(omap.get(bvo.getCarorgid()));
    }

  }

}
