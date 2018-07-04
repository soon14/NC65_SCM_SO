package nc.vo.so.m30.util;

import java.util.HashMap;
import java.util.Map;

import nc.bs.framework.common.NCLocator;
import nc.itf.so.m30trantype.IM30TranTypeService;
import nc.vo.ct.entity.CtBusinessVO;
import nc.vo.pub.BusinessException;
import nc.vo.pub.lang.UFDouble;
import nc.vo.pubapp.pattern.exception.ExceptionUtils;
import nc.vo.pubapp.pattern.pub.PubAppTool;
import nc.vo.so.m30.entity.OffsetTempVO;
import nc.vo.so.m30.sobalance.entity.SoBalanceVO;
import nc.vo.so.m30trantype.entity.M30TranTypeVO;
import nc.vo.trade.checkrule.VOChecker;

/**
 * 销售订单前台缓存类
 * 用于缓存前台经常使用的数据信息
 * 
 * @since 6.0
 * @version 2011-3-25 上午09:39:03
 * @author zhangcheng
 */
public class SaleOrderClientContext {
	
  // 合同信息Map<String, CtBusinessVO>
  private Map<String, CtBusinessVO> ctMap;

  // 是否已做过现销处理
  private boolean isCashSale;

  // 销售订单交易类型<销售订单交易类型code,销售订单交易类型vo>
  private Map<String, M30TranTypeVO> m_transType =
      new HashMap<String, M30TranTypeVO>();

  // 用于销售费用单冲抵销售订单缓存数据
  private OffsetTempVO offsettempvo;

  // 对单对应的收款核销数据
  private SoBalanceVO sobalancevo;

  // 本次收款金额
  private UFDouble thisGatheringMny;

  private IM30TranTypeService tranTypeService;

  public Map<String, CtBusinessVO> getCtMap() {
    return this.ctMap;
  }

  public boolean getIsCashSale() {
    return this.isCashSale;
  }

  public OffsetTempVO getOffsetTempVO() {
    return this.offsettempvo;
  }

  public SoBalanceVO getSobalancevo() {
    return this.sobalancevo;
  }

  public UFDouble getThisGatheringMny() {
    return this.thisGatheringMny;
  }

  public Map<String, M30TranTypeVO> getTransType() {
    return this.m_transType;
  }

  /**
   * 从销售订单前台缓存中获取交易类型，如果不存在从后台获取，并缓存之
   * 
   * @param tranTypeCode
   * @return
   */
  public M30TranTypeVO getTransType(String tranTypeCode, String pk_group) {
    M30TranTypeVO tranType = this.m_transType.get(tranTypeCode);
    // 缓存中没有销售订单交易类型从后台查询
    //性能优化：add by zhangby5 若code为空，则不需要查询
    if (VOChecker.isEmpty(tranType) && !PubAppTool.isNull(tranTypeCode)) {
      try {
        tranType =
            this.getTranTypeService().queryTranType(pk_group, tranTypeCode);
        this.setTransType(tranTypeCode, tranType);
      }
      catch (BusinessException e) {
        ExceptionUtils.wrappBusinessException(e.getMessage());
      }
    }
    return tranType;
  }
  
  public void setCtMap(Map<String, CtBusinessVO> ctMap) {
    this.ctMap = ctMap;
  }

  public void setIsCashSale(boolean isCashSale) {
    this.isCashSale = isCashSale;
  }

  public void setOffsetTempVO(OffsetTempVO offvo) {
    this.offsettempvo = offvo;
  }

  public void setSobalancevo(SoBalanceVO sobalancevo) {
    this.sobalancevo = sobalancevo;
  }

  public void setThisGatheringMny(UFDouble thisGatheringMny) {
    this.thisGatheringMny = thisGatheringMny;
  }

  public void setTransType(String transTypeCode, M30TranTypeVO mvo) {
    this.m_transType.put(transTypeCode, mvo);
  }

  private IM30TranTypeService getTranTypeService() {
    if (this.tranTypeService == null) {
      this.tranTypeService =
          NCLocator.getInstance().lookup(IM30TranTypeService.class);
    }
    return this.tranTypeService;
  }
}
