package nc.bs.so.m30.revise.rule;

import nc.bs.ml.NCLangResOnserver;
import nc.impl.pubapp.pattern.data.bill.BillInsert;
import nc.impl.pubapp.pattern.data.bill.BillQuery;
import nc.impl.pubapp.pattern.database.DataAccessUtils;
import nc.impl.pubapp.pattern.rule.ICompareRule;
import nc.vo.pubapp.pattern.data.IRowSet;
import nc.vo.pubapp.pattern.exception.ExceptionUtils;
import nc.vo.pubapp.pattern.pub.SqlBuilder;
import nc.vo.scmpub.util.ArrayUtil;
import nc.vo.so.m30.entity.SaleOrderHVO;
import nc.vo.so.m30.entity.SaleOrderVO;
import nc.vo.so.m30.revise.entity.SaleOrderHistoryVO;
import nc.vo.so.m30.util.Transfer30and30RVOTool;

/**
 * @description
 *              销售订单修订保存前版本号更新规则、保存最原始版本
 * @scene
 *        销售订单修订保存前
 * @param 无
 * @since 6.0
 * @version 2011-6-9 下午04:13:15
 * @author 刘志伟
 */
public class UpdateVersionNumRule implements ICompareRule<SaleOrderHistoryVO> {

  @Override
  public void process(SaleOrderHistoryVO[] vos, SaleOrderHistoryVO[] originVOs) {
    for (SaleOrderVO vo : vos) {
      // modify by wangshu6 for 销售订单修订支持审批流 版本号更新时查询数据库，从最版本进行加1
      SaleOrderHVO head = vo.getParentVO();
      String csaleorderid = head.getCsaleorderid();
      SqlBuilder sql = new SqlBuilder();
      sql.append("select max(iversion) iversion ");
      sql.append("from so_orderhistory where ");
      sql.append(SaleOrderHVO.CSALEORDERID, csaleorderid);
      sql.append(" and dr = 0");

      DataAccessUtils dataUtil = new DataAccessUtils();
      IRowSet set = dataUtil.query(sql.toString());
      String[] iversions = set.toOneDimensionStringArray();

      int iMaxVersion = 0;
      if (!ArrayUtil.isEmpty(iversions)&&iversions[0]!=null) {
        iMaxVersion = Integer.valueOf(iversions[0]);
      }
      else {
        // 没有查询到修订版本的就认为还没有修订，所以需要保存最原始版本
        // modify by zhangby5 销售订单修订ReviseSaveSaleOrderAction中取道的originVOs为当前修改后的，需要查询数据库中最新的销售订单
        BillQuery<SaleOrderVO> query =
            new BillQuery<SaleOrderVO>(SaleOrderVO.class);
        SaleOrderVO[] originSaleOrderVOs = query.query(new String[]{csaleorderid});
        if(ArrayUtil.isEmpty(originSaleOrderVOs)){
          ExceptionUtils
              .wrappBusinessException(NCLangResOnserver.getInstance()
                  .getStrByID("4006011_0", "04006011-0512")/*该销售订单正在被他人操作，请稍后刷新并重新操作！*/);
        }
        Transfer30and30RVOTool trans = new Transfer30and30RVOTool();
        SaleOrderHistoryVO hisVO = trans.transfer30TOOrderhisVO(originSaleOrderVOs[0]);
        hisVO.getParentVO().setCorderhistoryid(vo.getPrimaryKey());
        hisVO.getParentVO().setIversion(iMaxVersion);
        BillInsert<SaleOrderHistoryVO> bo =
            new BillInsert<SaleOrderHistoryVO>();
        bo.insert(new SaleOrderHistoryVO[] {
            hisVO
        });
      }
      head.setIversion(iMaxVersion + 1);
    }
  }
}
