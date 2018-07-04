package nc.pubimpl.so.m30.pfxx;

import java.util.ArrayList;
import java.util.List;

import nc.impl.pubapp.pattern.data.bill.BillQuery;
import nc.impl.pubapp.pattern.data.bill.tool.BillConcurrentTool;
import nc.impl.pubapp.pattern.rule.IRule;
import nc.impl.so.m30.action.main.InsertSaleOrderAction;
import nc.impl.so.m30.action.main.UpdateSaleOrderAction;
import nc.pubimpl.so.pfxx.AbstractSOPfxxPlugin;
import nc.pubimpl.so.pfxx.check.BillFreeStatusCheckRule;
import nc.pubimpl.so.pfxx.check.MnyTaxCheckRule;
import nc.pubimpl.so.pfxx.check.WriteBackInfoCheckRule;
import nc.vo.pub.AggregatedValueObject;
import nc.vo.so.m30.entity.SaleOrderBVO;
import nc.vo.so.m30.entity.SaleOrderVO;

public class M30PfxxPlugin extends AbstractSOPfxxPlugin {

  @Override
  public List<IRule<AggregatedValueObject>> getCheckers() {
    List<IRule<AggregatedValueObject>> rules =
        new ArrayList<IRule<AggregatedValueObject>>();
    // 单据状态检查，非自由态不可以导入
    rules.add(new BillFreeStatusCheckRule());
    // 价税合计=无税金额+税额（本币）
    rules.add(new MnyTaxCheckRule(SaleOrderBVO.NTAX, SaleOrderBVO.NMNY,
        SaleOrderBVO.NTAXMNY));
    // 累计数量字段
    rules.add(new WriteBackInfoCheckRule(new String[] {
      SaleOrderBVO.NTOTALSENDNUM,// 累计发货主数量
      SaleOrderBVO.NTOTALINVOICENUM,// 累计开票主数量
      SaleOrderBVO.NTOTALOUTNUM,// 累计出库主数量
      SaleOrderBVO.NTOTALNOTOUTNUM,// 累计应发未出库主数量
      SaleOrderBVO.NTOTALSIGNNUM,// 累计签收主数量
      SaleOrderBVO.NTRANSLOSSNUM,// 累计途损主数量
      SaleOrderBVO.NTOTALRUSHNUM,// 累计出库对冲主数量
      SaleOrderBVO.NTOTALESTARNUM,// 累计暂估应收主数量
      SaleOrderBVO.NTOTALARNUM,// 累计确认应收主数量
      SaleOrderBVO.NTOTALCOSTNUM,// 累计成本结算主数量
      SaleOrderBVO.NTOTALESTARMNY,// 累计暂估应收金额
      SaleOrderBVO.NTOTALARMNY,// 累计确认应收金额
      SaleOrderBVO.NTOTALPAYMNY,// 累计收款核销金额
      SaleOrderBVO.NORIGSUBMNY,// 累计冲抵金额
      SaleOrderBVO.NARRANGESCORNUM,// 累计安排委外订单主数量
      SaleOrderBVO.NARRANGEPOAPPNUM,// 累计安排请购单主数量
      SaleOrderBVO.NARRANGETOORNUM,// 累计安排调拨订单主数量
      SaleOrderBVO.NARRANGETOAPPNUM,// 累计安排调拨申请主数量
      SaleOrderBVO.NARRANGEMONUM,// 累计安排生产订单主数量
      SaleOrderBVO.NARRANGEPONUM,// 累计安排采购订单主数量
      SaleOrderBVO.NTOTALPLONUM,// 累计生成计划订单主数量
      SaleOrderBVO.NTOTALRETURNNUM,// 累计退货主数量
      SaleOrderBVO.NTOTALTRADENUM
    // 累计发出商品主数量
        }));
    return rules;
  }

  @Override
  protected AggregatedValueObject insert(AggregatedValueObject vo) {

    SaleOrderVO[] insertvo = new SaleOrderVO[] {
      (SaleOrderVO) vo
    };
    InsertSaleOrderAction insertact = new InsertSaleOrderAction();
    SaleOrderVO[] retvos = insertact.insert(insertvo);
    if (null == retvos || retvos.length == 0) {
      return null;
    }
    return retvos[0];
  }

  @Override
  protected AggregatedValueObject update(AggregatedValueObject vo, String vopk) {

    SaleOrderVO[] updatevo = new SaleOrderVO[] {
      (SaleOrderVO) vo
    };
    BillQuery<SaleOrderVO> billquery =
        new BillQuery<SaleOrderVO>(SaleOrderVO.class);
    SaleOrderVO[] origvos = billquery.query(new String[] {
      vopk
    });
    BillConcurrentTool tool = new BillConcurrentTool();
    tool.lockBill(origvos);
    UpdateSaleOrderAction insertact = new UpdateSaleOrderAction();
    SaleOrderVO[] retvos = insertact.update(updatevo, origvos);
    if (null == retvos || retvos.length == 0) {
      return null;
    }
    return retvos[0];
  }

}
