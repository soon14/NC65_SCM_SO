package nc.bs.so.m30.rule.feature;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import nc.bs.framework.common.NCLocator;
import nc.impl.pubapp.pattern.rule.ICompareRule;
import nc.pubitf.bd.feature.ffile.IPubFFileBusiService;
import nc.pubitf.bd.feature.ffile.ic.IPubFFileUpdateService4SO;
import nc.vo.bd.feature.ffile.param.FFileICParam;
import nc.vo.bd.feature.ffile.param.FFilleResetSrcParam;
import nc.vo.pub.BusinessException;
import nc.vo.pub.VOStatus;
import nc.vo.pubapp.pattern.exception.ExceptionUtils;
import nc.vo.pubapp.pattern.pub.PubAppTool;
import nc.vo.so.m30.entity.SaleOrderBVO;
import nc.vo.so.m30.entity.SaleOrderVO;
import nc.vo.so.pub.util.ListUtil;

/**
 * 
 * @description
 * 销售订单修改保存前规则删除行如果特征码有值，则清空表体对应特征码档案的来源信息
 * （生产制造会判断当前单据是否是对应的来源）
 * @scene
 * 销售订单修改保存前规则调用，
 * @param
 * 无
 *
 * @since 6.5
 * @version 2015-10-19 下午3:14:04
 * @author zhangby5
 */
public class ClearMffileSRCRule implements ICompareRule<SaleOrderVO> {

  @Override
  public void process(SaleOrderVO[] vos, SaleOrderVO[] originVOs) {

    IPubFFileBusiService ffileService =
        NCLocator.getInstance().lookup(IPubFFileBusiService.class);
    FFilleResetSrcParam[] paramList = this.getResetSrcParams(vos);
    try {
      // 清空特征码行号和单据号
      if (paramList != null && paramList.length > 0) {
        ffileService.resetSrcAggFFileVO2(paramList);
      }

    }
    catch (BusinessException ex) {
      ExceptionUtils.wrappBusinessException(ex.getMessage());
    }
  }

  /**
   * 获取表体所有非空特征码
   * 
   * @param vos
   * @return
   */
  private FFilleResetSrcParam[] getResetSrcParams(SaleOrderVO[] vos) {
    List<FFilleResetSrcParam> billIdList = new ArrayList<>();
    for (SaleOrderVO vo : vos) {
      SaleOrderBVO[] bvos = vo.getChildrenVO();
      for (SaleOrderBVO bvo : bvos) {
        String cmffileid = bvo.getCmffileid();
        if (PubAppTool.isNull(cmffileid)) {
          continue;
        }
        if (bvo.getStatus() == VOStatus.DELETED) {
          FFilleResetSrcParam param = new FFilleResetSrcParam();
          param.setSrcBid(bvo.getCsaleorderbid());
          param.setSrcId(bvo.getCsaleorderid());
          param.setSrcType(cmffileid);
          billIdList.add(param);
        }
      }
    }
    return ListUtil.toArray(billIdList);
  }
}
