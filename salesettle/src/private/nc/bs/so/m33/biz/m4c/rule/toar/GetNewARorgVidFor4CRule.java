package nc.bs.so.m33.biz.m4c.rule.toar;

import java.util.Map;

import nc.impl.pubapp.pattern.rule.IRule;
import nc.itf.scmpub.reference.uap.org.OrgUnitPubService;
import nc.vo.so.m33.m4c.entity.SquareOutBVO;
import nc.vo.so.m33.m4c.entity.SquareOutVO;
import nc.vo.so.m33.m4c.entity.SquareOutVOUtils;
import nc.vo.so.pub.votools.SoVoTools;

/**
 * @description
 * 传应收前 获取应收组织最新组织版本
 * @scene
 * 销售出库单传应收、传回冲应收、出库对冲传回冲应收前 
 * @param 
 * 无
 * @author zhangcheng
 * 
 */
public class GetNewARorgVidFor4CRule implements IRule<SquareOutVO> {

  @Override
  public void process(SquareOutVO[] vos) {
    SquareOutBVO[] bvos = SquareOutVOUtils.getInstance().getSquareOutBVO(vos);

    String[] arids = SoVoTools.getVOsOnlyValues(bvos, SquareOutBVO.CARORGID);

    if (arids == null || arids.length == 0) {
      return;
    }

    Map<String, String> omap = OrgUnitPubService.getOrgVid(arids);

    for (SquareOutBVO bvo : bvos) {
      bvo.setCarorgvid(omap.get(bvo.getCarorgid()));
    }
  }

}
