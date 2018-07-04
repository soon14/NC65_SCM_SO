package nc.pubimpl.so.m30.so.m33;

import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;

import nc.impl.pubapp.pattern.data.view.EfficientViewQuery;
import nc.impl.pubapp.pattern.data.view.ViewQuery;
import nc.itf.so.m30.ref.ct.mz3.CTmZ3ServicesUtil;
import nc.pubitf.so.m30.so.m33.For33SquareInvVO;
import nc.pubitf.so.m30.so.m33.For33SquareOutVO;
import nc.pubitf.so.m30.so.m33.ISaleOrderFor33;
import nc.vo.pub.BusinessException;
import nc.vo.pub.lang.UFDate;
import nc.vo.pubapp.pattern.exception.ExceptionUtils;
import nc.vo.pubapp.pattern.pub.PubAppTool;
import nc.vo.so.m30.entity.SaleOrderBVO;
import nc.vo.so.m30.entity.SaleOrderHVO;
import nc.vo.so.m30.entity.SaleOrderViewVO;

public class SaleOrderFor33Impl implements ISaleOrderFor33 {

  @Override
  public Map<String, UFDate> get30BusiDateBy30Bids(String[] bids)
      throws BusinessException {
    String[] fields = new String[] {
      SaleOrderBVO.CSALEORDERBID, SaleOrderHVO.DBILLDATE
    };
    EfficientViewQuery<SaleOrderViewVO> viewqry =
        new EfficientViewQuery<SaleOrderViewVO>(SaleOrderViewVO.class, fields);
    SaleOrderViewVO[] views = viewqry.query(bids);
    Map<String, UFDate> mret = new HashMap<String, UFDate>();
    for (SaleOrderViewVO view : views) {
      mret.put(view.getBody().getCsaleorderbid(), view.getBody().getDbilldate());
    }
    return mret;
  }

  @Override
  public Map<String, UFDate> getZ3BusiDateBy30Bids(String[] bids)
      throws BusinessException {
    String[] fields = new String[] {
      SaleOrderBVO.CSALEORDERBID, SaleOrderBVO.CCTMANAGEBID
    };
    EfficientViewQuery<SaleOrderViewVO> viewqry =
        new EfficientViewQuery<SaleOrderViewVO>(SaleOrderViewVO.class, fields);
    SaleOrderViewVO[] views = viewqry.query(bids);
    Set<String> set = new HashSet<String>();
    for (SaleOrderViewVO view : views) {
      String ctbid = view.getBody().getCctmanagebid();
      if (!PubAppTool.isNull(ctbid)) {
        set.add(view.getBody().getCctmanagebid());
      }
    }
    // Map<String, UFDate> mz3bid_date = new HashMap<String, UFDate>();
    if (set.size() == 0) {
      ExceptionUtils
          .wrappBusinessException(nc.vo.ml.NCLangRes4VoTransl.getNCLangRes()
              .getStrByID("4006011_0", "04006011-0188")/*@res "销售订单没有来源于销售合同！"*/);
    }

    Map<String, UFDate> mz3bid_date =
        CTmZ3ServicesUtil.queryValidateDayForGatherBill(set
            .toArray(new String[set.size()]));

    Map<String, UFDate> mret = new HashMap<String, UFDate>();
    for (SaleOrderViewVO view : views) {
      String ctbid = view.getBody().getCctmanagebid();
      UFDate date = mz3bid_date.get(ctbid);
      mret.put(view.getBody().getCsaleorderbid(), date);
    }
    return mret;
  }

  @Override
  public Map<String, For33SquareInvVO> query30ViewInfoForSquareInv(String[] bids)
      throws BusinessException {
    // Map<销售订单bid, For33SquareOutVO>
    Map<String, For33SquareInvVO> returnMap =
        new HashMap<String, For33SquareInvVO>();
    if (bids == null || bids.length == 0) {
      return returnMap;
    }

    // 查询销售订单view信息
    ViewQuery<SaleOrderViewVO> viewQuery =
        new ViewQuery<SaleOrderViewVO>(SaleOrderViewVO.class);
    SaleOrderViewVO[] views = viewQuery.query(bids);
    if (views != null && views.length > 0) {
      for (SaleOrderViewVO view : views) {
        returnMap.put(view.getBody().getCsaleorderbid(),
            this.createFor33SquareInvVO(view));
      }
    }
    return returnMap;
  }

  @Override
  public Map<String, For33SquareOutVO> query30ViewInfoForSquareOut(String[] bids)
      throws BusinessException {
    // Map<销售订单bid, For33SquareOutVO>
    Map<String, For33SquareOutVO> returnMap =
        new HashMap<String, For33SquareOutVO>();
    if (bids == null || bids.length == 0) {
      return returnMap;
    }

    // 查询销售订单view信息
    ViewQuery<SaleOrderViewVO> viewQuery =
        new ViewQuery<SaleOrderViewVO>(SaleOrderViewVO.class);
    SaleOrderViewVO[] views = viewQuery.query(bids);
    if (views != null && views.length > 0) {
      for (SaleOrderViewVO view : views) {
        returnMap.put(view.getBody().getCsaleorderbid(),
            this.createFor33SquareOutVO(view));
      }
    }
    return returnMap;
  }

  private For33SquareInvVO createFor33SquareInvVO(SaleOrderViewVO view) {
    For33SquareInvVO retVO = new For33SquareInvVO();
    retVO.setCsaleorderbid(view.getBody().getCsaleorderbid());
    retVO.setVctcode(view.getBody().getVctcode());
    return retVO;
  }

  private For33SquareOutVO createFor33SquareOutVO(SaleOrderViewVO view) {
    For33SquareOutVO retVO = new For33SquareOutVO();
    retVO.setCsaleorderbid(view.getBody().getCsaleorderbid());
    retVO.setVctcode(view.getBody().getVctcode());
    return retVO;
  }

}
