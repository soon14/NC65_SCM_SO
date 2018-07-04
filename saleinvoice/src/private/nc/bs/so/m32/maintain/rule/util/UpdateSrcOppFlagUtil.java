package nc.bs.so.m32.maintain.rule.util;

import java.util.ArrayList;
import java.util.List;

import nc.impl.pubapp.pattern.data.vo.VOQuery;
import nc.impl.pubapp.pattern.data.vo.VOUpdate;
import nc.impl.pubapp.pattern.data.vo.tool.VOConcurrentTool;
import nc.vo.so.m32.entity.SaleInvoiceHVO;
import nc.vo.so.m32.entity.SaleInvoiceVO;
import nc.vo.so.m32.enumeration.OpposeFlag;

/**
 * 对冲发票保存更新来源单据
 * 
 * @since 6.0
 * @version 2011-11-11 上午10:50:09
 * @author 么贵敬
 */
public class UpdateSrcOppFlagUtil {

  /**
   * 更新来源单据的状态
   * 
   * @param voInvoices 发票VOS
   * @param oppflag 需要更新成的标志
   */
  public void updateSrcOppFlag(SaleInvoiceVO[] voInvoices, OpposeFlag oppflag) {

    // 过滤得到对冲生成的销售发票
    List<String> aloppid = new ArrayList<String>();
    for (SaleInvoiceVO invoice : voInvoices) {
      if (OpposeFlag.GENERAL
          .equalsValue(invoice.getParentVO().getFopposeflag())) {
        aloppid.add(invoice.getParentVO().getCopposesrcid());
      }
    }
    // 没有对冲生成的销售发票,直接返回
    if (aloppid.size() <= 0) {
      return;
    }
    // 对冲生成发票的来源发票加锁
    String[] oppids = aloppid.toArray(new String[aloppid.size()]);
    // 更新数据库中来源发票的对冲标志位
    String[] updateKeys = new String[] {
      SaleInvoiceHVO.FOPPOSEFLAG
    };

    String[] selectKeys =
        new String[] {
          SaleInvoiceHVO.CSALEINVOICEID, SaleInvoiceHVO.FOPPOSEFLAG,
          SaleInvoiceHVO.TS
        };
    VOQuery<SaleInvoiceHVO> querysrv =
        new VOQuery<SaleInvoiceHVO>(SaleInvoiceHVO.class, selectKeys);
    SaleInvoiceHVO[] hvos = querysrv.query(oppids);
    VOConcurrentTool tool = new VOConcurrentTool();
    tool.checkTSWithDB(hvos);
    for (SaleInvoiceHVO hvo : hvos) {
      hvo.setFopposeflag((Integer) oppflag.value());
    }
    VOUpdate<SaleInvoiceHVO> bo = new VOUpdate<SaleInvoiceHVO>();
    bo.update(hvos, updateKeys);

  }
}
