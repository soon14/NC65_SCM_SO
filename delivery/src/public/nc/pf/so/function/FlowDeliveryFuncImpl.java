package nc.pf.so.function;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import nc.bs.framework.common.NCLocator;
import nc.itf.scmpub.reference.uap.bd.material.MaterialPubService;
import nc.itf.uap.pf.IPFMetaModel;
import nc.vo.bd.material.stock.MaterialStockVO;
import nc.vo.pub.AggregatedValueObject;
import nc.vo.pub.BusinessException;
import nc.vo.pub.lang.UFBoolean;
import nc.vo.pub.pf.PfUtilActionConstrictVO;
import nc.vo.pubapp.pattern.exception.ExceptionUtils;
import nc.vo.so.m4331.entity.DeliveryBVO;
import nc.vo.so.m4331.entity.DeliveryVO;
import nc.vo.so.m4331.eum.DeliveryCheckFuncEnum;
import nc.vo.so.m4331.eum.M4331EngRossActionEnum;
import nc.vo.trade.checkrule.VOChecker;

/**
 * 发货单检查函数
 *
 * @since 6.0
 * @version 2010-11-9 下午07:08:40
 * @author 祝会征
 */
public class FlowDeliveryFuncImpl {

  public UFBoolean examBatchInv(AggregatedValueObject aggVO) {
    return this.exam(aggVO);
  }

  /*
   * 检查函数配置位置
   */
  private void checkFlow(AggregatedValueObject vo, String action, String method) {
    DeliveryVO aggVO = (DeliveryVO) vo;
    IPFMetaModel service = NCLocator.getInstance().lookup(IPFMetaModel.class);
    try {
      PfUtilActionConstrictVO[] constrictVOs =
          service.queryActionConstricts(aggVO.getParentVO().getVtrantypecode(),
              aggVO.getParentVO().getCbiztypeid(), action, aggVO.getParentVO()
                  .getBillmaker(), aggVO.getParentVO().getPk_org());
      for (PfUtilActionConstrictVO constrictVO : constrictVOs) {
        if (constrictVO.getMethod().equals(method)) {
          if ("Y".equals(constrictVO.getIsBefore())) {
            ExceptionUtils.wrappBusinessException(nc.vo.ml.NCLangRes4VoTransl.getNCLangRes().getStrByID("4006002_0","04006002-0101")/*@res "物料批次管理检查函数只能配置为动作后检查。"*/);
          }
        }
      }
    }
    catch (BusinessException e) {
      ExceptionUtils.wrappException(e);
    }
  }

  /*
   * 检查物料是否是批次管理并且批次是否为空
   */
  private UFBoolean checkInv(Map<String, MaterialStockVO> mapInv,
      AggregatedValueObject aggVO) {
    DeliveryBVO[] bvos = (DeliveryBVO[]) aggVO.getChildrenVO();
    MaterialStockVO stockvo = null;
    for (DeliveryBVO bvo : bvos) {
      String cmatrialid = bvo.getCmaterialvid();
      String batch = bvo.getPk_batchcode();
      stockvo = mapInv.get(cmatrialid);
      if (stockvo.getWholemanaflag().booleanValue() && VOChecker.isEmpty(batch)) {
        return UFBoolean.FALSE;
      }
    }
    return UFBoolean.TRUE;
  }

  private UFBoolean exam(AggregatedValueObject aggVO) {
    this.checkFlow(aggVO, M4331EngRossActionEnum.M4331Write.getValue(),
        DeliveryCheckFuncEnum.DeliverySave.getValue());
    Map<String, MaterialStockVO> mapInv = this.getBatchInv(aggVO);
    return this.checkInv(mapInv, aggVO);
  }

  /*
   * 获得指定发货库存组织下物料是否是批次管理
   */
  private Map<String, MaterialStockVO> getBatchInv(AggregatedValueObject aggVO) {
    DeliveryBVO[] bvos = (DeliveryBVO[]) aggVO.getChildrenVO();
    List<String> invList = new ArrayList<String>();
    for (DeliveryBVO bvo : bvos) {
      if ((invList.size() == 0) || !invList.contains(bvo.getCmaterialvid())) {
        invList.add(bvo.getCmaterialvid());
      }
    }
    String[] invIds = new String[invList.size()];
    invIds = invList.toArray(invIds);
    String[] wholeflag = new String[] {
      MaterialStockVO.WHOLEMANAFLAG
    };
    return MaterialPubService.queryMaterialStockInfo(invIds,
        bvos[0].getCsendstockorgid(), wholeflag);
  }
}