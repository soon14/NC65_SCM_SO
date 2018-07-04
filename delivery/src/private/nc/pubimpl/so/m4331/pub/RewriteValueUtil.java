package nc.pubimpl.so.m4331.pub;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import nc.bs.framework.common.NCLocator;
import nc.impl.pubapp.env.BSContext;
import nc.itf.so.m4331trantype.IM4331TranTypeService;
import nc.pubitf.so.m4331.ic.m4c.RewritePara4331For4C;
import nc.pubitf.so.m4331.ic.m4y.RewritePara4331For4Y;
import nc.vo.pub.BusinessException;
import nc.vo.pub.lang.UFBoolean;
import nc.vo.pub.lang.UFDouble;
import nc.vo.pubapp.pattern.exception.ExceptionUtils;
import nc.vo.scmpub.res.billtype.SOBillType;
import nc.vo.scmpub.res.billtype.TOBillType;
import nc.vo.so.m4331.entity.DeliveryViewVO;
import nc.vo.trade.checkrule.VOChecker;

/**
 * 获得下游回写发货单变量值
 * bid下游回写id srcbilltype上游单据类型
 * 
 * @since 6.0
 * @version 2011-2-18 上午09:09:01
 * @author 祝会征
 */
public class RewriteValueUtil {
  // 缓存物料信息
  // private Map<String, MaterialVO> materialMap =
  // new HashMap<String, MaterialVO>();

  // 缓存销售出库单回写参数
  private Map<String, RewritePara4331For4C> saleMap;

  // 缓存调拨出库单回写参数
  private Map<String, RewritePara4331For4Y> tranMap;

  private Map<String, UFBoolean> typeInfoMap;

  public RewriteValueUtil() {
    // TODO
  }

  public RewriteValueUtil(Map<String, RewritePara4331For4C> salemap) {
    this.saleMap = salemap;
  }

  /**
   * 方法功能描述：获得发货单单据类型的属性是否一次出库关闭
   * 
   * @author 祝会征
   * @time 2010-9-29 上午11:43:57
   */
  public Map<String, UFBoolean> getBilltypeInfo(RewriteVOUtil util) {
    if (null != this.typeInfoMap && this.typeInfoMap.size() == 0) {
      return this.typeInfoMap;
    }
    this.typeInfoMap = new HashMap<String, UFBoolean>();
    DeliveryViewVO[] views = util.getAllRewriteViewVO();
    String pk_group = views[0].getHead().getPk_group();
    List<String> list = new ArrayList<String>();
    for (DeliveryViewVO view : views) {
      String billtype = view.getHead().getVtrantypecode();
      if (list.size() == 0 || !list.contains(billtype)) {
        list.add(billtype);
      }
    }
    String[] billtypes = new String[list.size()];
    billtypes = list.toArray(billtypes);
    IM4331TranTypeService service =
        NCLocator.getInstance().lookup(IM4331TranTypeService.class);
    try {
      this.typeInfoMap = service.queryTranTypes(pk_group, billtypes);
    }
    catch (BusinessException e) {
      ExceptionUtils.wrappBusinessException(e.getMessage());
    }
    return this.typeInfoMap;
  }

  /**
   * 获得下游回写发货单的应发出库数量的变化量
   * 
   * @param bid
   * @param srcBilltype
   * @return
   */
  public UFDouble getRewriteNoNum(String bid, String srcBilltype) {
    UFDouble value = null;
    if (SOBillType.Order.getCode().equals(srcBilltype)) {
      if (!VOChecker.isEmpty(this.getSaleMap())) {
        value = this.getSaleMap().get(bid).getNoOutnum();
      }
    }
    else if (TOBillType.TransOrder.getCode().equals(srcBilltype)) {
      if (!VOChecker.isEmpty(this.getTranMap())) {
        value = this.getTranMap().get(bid).getNoOutnum();
      }
    }
    return value;
  }

  /**
   * 获得下游回写发货单的出库数量的变化量
   * 
   * @param bid
   * @param srcBilltype
   * @return
   */
  public UFDouble getRewriteNum(String bid, String srcBilltype) {
    UFDouble value = null;
    if (SOBillType.Order.getCode().equals(srcBilltype)) {
      if (!VOChecker.isEmpty(this.getSaleMap())) {
        value = this.getSaleMap().get(bid).getOutnum();
      }
    }
    else if (TOBillType.TransOrder.getCode().equals(srcBilltype)) {
      if (!VOChecker.isEmpty(this.getTranMap())) {
        value = this.getTranMap().get(bid).getOutnum();
      }
    }
    return value;
  }

  @SuppressWarnings("unchecked")
  private Map<String, RewritePara4331For4C> getSaleMap() {
    if (VOChecker.isEmpty(this.saleMap)) {
      this.saleMap =
          (Map<String, RewritePara4331For4C>) BSContext.getInstance()
              .getSession(RewritePara4331For4C.class.getName());
    }
    return this.saleMap;
  }

  @SuppressWarnings("unchecked")
  private Map<String, RewritePara4331For4Y> getTranMap() {
    if (VOChecker.isEmpty(this.saleMap)) {
      this.tranMap =
          (Map<String, RewritePara4331For4Y>) BSContext.getInstance()
              .getSession(RewritePara4331For4Y.class.getName());
    }
    return this.tranMap;
  }
}
