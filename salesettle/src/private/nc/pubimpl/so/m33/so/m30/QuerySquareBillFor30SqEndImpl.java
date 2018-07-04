package nc.pubimpl.so.m33.so.m30;

import java.util.HashMap;
import java.util.Map;

import nc.vo.scmpub.res.billtype.ICBillType;
import nc.vo.scmpub.res.billtype.SOBillType;
import nc.vo.so.m33.enumeration.SquareType;
import nc.vo.so.m33.m32.entity.SquareInvViewVO;
import nc.vo.so.m33.m4c.entity.SquareOutBVO;
import nc.vo.so.m33.m4c.entity.SquareOutHVO;
import nc.vo.so.m33.m4c.entity.SquareOutViewVO;
import nc.vo.so.pub.SOItemKey;
import nc.vo.so.pub.SOTable;

import nc.pubitf.so.m33.so.m30.IQuerySquareBillFor30SqEnd;

import nc.impl.pubapp.pattern.data.view.EfficientViewQuery;
import nc.impl.pubapp.pattern.database.IDExQueryBuilder;

/**
 * 销售结算提供给销售订单结算关闭查询接口实现类
 * 
 * @since 6.1
 * @version 2012-11-29 11:09:47
 * @author 冯加彬
 */
public class QuerySquareBillFor30SqEndImpl implements
    IQuerySquareBillFor30SqEnd {

  @Override
  public Map<String, String[]> querySquareBillFor30SqEnd(String[] orderbids,
      String[] busiids) {
    StringBuilder wheresql = new StringBuilder(" and ");
    IDExQueryBuilder iq = new IDExQueryBuilder(SOTable.TMP_SO_ID1.getName());
    wheresql.append(iq.buildSQL(SOItemKey.CFIRSTBID, orderbids));
    wheresql.append(" and ");
    iq = new IDExQueryBuilder(SOTable.TMP_SO_ID2.getName());
    wheresql.append(iq.buildSQL(SOItemKey.CBIZTYPEID, busiids));

    String[] names = new String[] {
      SquareOutHVO.CBIZTYPEID, SquareOutBVO.FPREARTYPE, SquareOutBVO.FPREIATYPE
    };
    EfficientViewQuery<SquareOutViewVO> outviewQuery =
        new EfficientViewQuery<SquareOutViewVO>(SquareOutViewVO.class, names);
    SquareOutViewVO[] outviews = outviewQuery.query(wheresql.toString());
    EfficientViewQuery<SquareInvViewVO> invviewQuery =
        new EfficientViewQuery<SquareInvViewVO>(SquareInvViewVO.class, names);
    SquareInvViewVO[] invviews = invviewQuery.query(wheresql.toString());
    Map<String, String[]> map = this.processBiz(outviews, invviews);
    return map;
  }

  private Map<String, String[]> processBiz(SquareOutViewVO[] outviews,
      SquareInvViewVO[] invviews) {
    Map<String, String[]> map = new HashMap<String, String[]>();
    String outBillType = ICBillType.SaleOut.getCode();
    // 销售出库
    for (SquareOutViewVO view : outviews) {
      String biz = view.getHead().getCbiztypeid();
      int artype = view.getItem().getFpreartype().intValue();
      int iatype = view.getItem().getFpreiatype().intValue();
      String[] billTypes = map.get(biz);
      if (null == billTypes) {
        billTypes = new String[] {
          null, null
        };
        if (SquareType.SQUARETYPE_AR.getIntValue() == artype) {
          billTypes[0] = outBillType;
        }
        if (SquareType.SQUARETYPE_IA.getIntValue() == iatype) {
          billTypes[1] = outBillType;
        }
        map.put(biz, billTypes);
      }
    }
    // 销售发票
    String invBillType = SOBillType.Invoice.getCode();
    for (SquareInvViewVO view : invviews) {
      String biz = view.getHead().getCbiztypeid();
      int artype =
          view.getItem().getFpreartype() == null ? SquareType.SQUARETYPE_NULL
              .getIntValue() : view.getItem().getFpreartype().intValue();
      int iatype =
          view.getItem().getFpreiatype() == null ? SquareType.SQUARETYPE_NULL
              .getIntValue() : view.getItem().getFpreiatype().intValue();
      String[] billTypes = map.get(biz);
      if (null == billTypes) {
        billTypes = new String[] {
          null, null
        };
        map.put(biz, billTypes);
      }
      if (SquareType.SQUARETYPE_AR.getIntValue() == artype) {
        billTypes[0] = invBillType;
      }
      if (SquareType.SQUARETYPE_IA.getIntValue() == iatype) {
        billTypes[1] = invBillType;
      }
    }
    return map;
  }

}
