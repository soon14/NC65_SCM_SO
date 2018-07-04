package nc.bs.so.m30.rule.feature;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import nc.bs.framework.common.NCLocator;
import nc.impl.pubapp.pattern.rule.ICompareRule;
import nc.pubitf.bd.feature.ffile.ic.IPubFFileUpdateService4SO;
import nc.vo.bd.feature.ffile.param.FFileICParam;
import nc.vo.pubapp.pattern.pub.PubAppTool;
import nc.vo.so.m30.entity.SaleOrderBVO;
import nc.vo.so.m30.entity.SaleOrderVO;
import nc.vo.so.pub.util.ListUtil;

/**
 * @description
 * 订单修改保存时重置特征码档案行号、单据号
 * @scene
 * 修改保存后规则调用
 * @param
 *
 *
 * @since 6.5
 * @version 2015-11-3 上午10:02:48
 * @author 刘景
 */
public class RestMffileSRCRule implements ICompareRule<SaleOrderVO> {

  @Override
  public void process(SaleOrderVO[] vos, SaleOrderVO[] originVOs) {
    IPubFFileUpdateService4SO updateffile =
        NCLocator.getInstance().lookup(IPubFFileUpdateService4SO.class);
    FFileICParam[] upffileparam = this.getFFileICParam(vos, originVOs);

    // 重置行号和单据号
    if (upffileparam != null && upffileparam.length > 0) {
      updateffile.updateFFile(upffileparam);
    }
  }

  private FFileICParam[] getFFileICParam(SaleOrderVO[] vos,
      SaleOrderVO[] originVOs) {
    List<FFileICParam> billIdList = new ArrayList<>();
    int i = 0;
    for (SaleOrderVO vo : vos) {
      SaleOrderBVO[] bvos = vo.getChildrenVO();
      SaleOrderBVO[] originbvos = originVOs[i].getChildrenVO();
      Map<String, SaleOrderBVO> bvoMap = this.getAllBVOs(originbvos);
      for (SaleOrderBVO bvo : bvos) {
        String cmffileid = bvo.getCmffileid();
        if (PubAppTool.isNull(cmffileid)) {
          continue;
        }
        SaleOrderBVO originbvo = bvoMap.get(bvo.getCsaleorderbid());
        if (originbvo == null) {
          continue;
        }
        if (!PubAppTool.isEqual(originVOs[i].getParentVO().getVbillcode(), vo
            .getParentVO().getVbillcode())
            || !PubAppTool.isEqual(originbvo.getCrowno(), bvo.getCrowno())) {
          FFileICParam param = new FFileICParam();
          param.setVbillcode(vo.getParentVO().getVbillcode());
          param.setVrowno(bvo.getCrowno());
          param.setCffileid(cmffileid);
          billIdList.add(param);
        }
      }
      i++;
    }
    return ListUtil.toArray(billIdList);
  }

  private Map<String, SaleOrderBVO> getAllBVOs(SaleOrderBVO[] bvos) {
    Map<String, SaleOrderBVO> allbvos = new HashMap<>();
    for (SaleOrderBVO bvo : bvos) {
      allbvos.put(bvo.getCsaleorderbid(), bvo);
    }
    return allbvos;
  }

}
