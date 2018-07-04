package nc.bs.so.m30.rule.feature;

import java.util.ArrayList;
import java.util.List;

import nc.bs.framework.common.NCLocator;
import nc.impl.pubapp.pattern.database.DBTool;
import nc.impl.pubapp.pattern.rule.IRule;
import nc.pubitf.bd.feature.fselect.IFeatureSelectSaveProcess;
import nc.vo.bd.feature.ffile.entity.AggFFileVO;
import nc.vo.bd.feature.ffile.entity.FFileHeadVO;
import nc.vo.pub.BusinessException;
import nc.vo.pub.VOStatus;
import nc.vo.pubapp.pattern.exception.ExceptionUtils;
import nc.vo.pubapp.pattern.pub.PubAppTool;
import nc.vo.scmpub.res.billtype.SOBillType;
import nc.vo.so.m30.entity.SaleOrderBVO;
import nc.vo.so.m30.entity.SaleOrderHVO;
import nc.vo.so.m30.entity.SaleOrderVO;
import nc.vo.so.pub.util.ArrayUtil;
import nc.vo.so.pub.util.ListUtil;

/**
 * 
 * @description
 * 销售订单保存前规则
 * @scene
 * 销售订单特征码选配保存规则
 * @param
 * 无
 *
 * @since 6.5
 * @version 2015-10-19 下午3:15:45
 * @author zhangby5
 */
public class FeatureSelectSaveRule implements IRule<SaleOrderVO> {

  @Override
  public void process(SaleOrderVO[] vos) {
    // 为了配合特征码选配功能，此处需要对销售订单生成表体ID
    this.fillOIDs(vos);
    AggFFileVO[] aggffilevo = this.getFeatrueSelect(vos);
    AggFFileVO[] returnffilevo = this.fileSaveProcess(aggffilevo);
    this.processReturnffileVO(vos, returnffilevo);
  }

  private void processReturnffileVO(SaleOrderVO[] vos,
      AggFFileVO[] returnffilevo) {
    if (ArrayUtil.isEmpty(returnffilevo)) {
      return;
    }
    int aggindex = 0;
    for (SaleOrderVO vo : vos) {
      SaleOrderBVO[] bvos = vo.getChildrenVO();
      if (bvos == null || bvos.length == 0) {
        continue;
      }
      for (SaleOrderBVO bvo : bvos) {
        if (null == bvo.getAggffilevo()) {
          continue;
        }
        bvo.setCmffileid(returnffilevo[aggindex++].getPrimaryKey());
      }
    }
  }

  private AggFFileVO[] fileSaveProcess(AggFFileVO[] aggffilevo) {
    if (ArrayUtil.isEmpty(aggffilevo)) {
      return null;
    }
    AggFFileVO[] returnffilevo = null;
    try {
      returnffilevo =
          NCLocator.getInstance().lookup(IFeatureSelectSaveProcess.class)
              .fileSaveProcess(aggffilevo);
    }
    catch (BusinessException e) {
      ExceptionUtils.wrappBusinessException(e.getMessage());
    }
    return returnffilevo;
  }

  private AggFFileVO[] getFeatrueSelect(SaleOrderVO[] vos) {
    List<AggFFileVO> aggffilevos = new ArrayList<AggFFileVO>();
    for (SaleOrderVO vo : vos) {
      SaleOrderBVO[] bvos = vo.getChildrenVO();
      if (bvos == null || bvos.length == 0) {
        continue;
      }
      SaleOrderHVO hvo = vo.getParentVO();
      for (SaleOrderBVO bvo : bvos) {
        if (null == bvo.getAggffilevo()) {
          continue;
        }
        AggFFileVO aggffilevo = bvo.getAggffilevo();
        this.fillSrcInfo(hvo, bvo, aggffilevo);
        aggffilevos.add(aggffilevo);
      }
    }
    return ListUtil.toArray(aggffilevos);
  }

  private void fillSrcInfo(SaleOrderHVO hvo, SaleOrderBVO bvo,
      AggFFileVO aggffilevo) {
    FFileHeadVO headVO = aggffilevo.getParentVO();
    headVO.setVsrcbid(bvo.getCsaleorderbid());
    headVO.setVsrccode(hvo.getVbillcode());
    headVO.setVsrcid(bvo.getCsaleorderid());
    headVO.setVsrcrowno(bvo.getCrowno());
    headVO.setVsrctype(SOBillType.Order.getCode());
    headVO.setPk_org(bvo.getPk_group());
    headVO.setPk_org_src(bvo.getCsendstockorgid());
  }

  /**
   * 补全表体ID
   * 
   * @param vos
   */
  private void fillOIDs(SaleOrderVO[] vos) {
    List<SaleOrderBVO> newBVOs = new ArrayList<SaleOrderBVO>();
    for (SaleOrderVO vo : vos) {
      SaleOrderBVO[] bvos = vo.getChildrenVO();
      if (bvos == null || bvos.length == 0) {
        continue;
      }
      for (SaleOrderBVO bvo : bvos) {
        if (isNewBVO(bvo)) {
          newBVOs.add(bvo);
        }
      }
    }
    int size = newBVOs.size();
    if (size == 0) {
      return;
    }
    DBTool dao = new DBTool();
    String[] ids = dao.getOIDs(size);
    for (int i = 0; i < size; i++) {
      newBVOs.get(i).setCsaleorderbid(ids[i]);
    }
  }

  private boolean isNewBVO(SaleOrderBVO bvo) {
    return VOStatus.NEW == bvo.getStatus()
        && PubAppTool.isNull(bvo.getCsaleorderbid());
  }
}
