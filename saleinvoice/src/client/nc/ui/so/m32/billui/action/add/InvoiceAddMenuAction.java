package nc.ui.so.m32.billui.action.add;

import java.util.Map;

import nc.ui.pubapp.uif2app.actions.AddMenuAction;
import nc.vo.pub.pf.PfAddInfo;
import nc.vo.scmpub.res.billtype.SOBillType;

/**
 * 销售发票新增按钮组
 * 
 * @since 6.0
 * @version 2011-6-10 下午06:09:00
 * @author 么贵敬
 */
public class InvoiceAddMenuAction extends AddMenuAction {

  private static final long serialVersionUID = 1L;

  @Override
  protected Map<String, PfAddInfo> getSourceBillTypes() {
    Map<String, PfAddInfo> ret = super.getSourceBillTypes();
    if (!ret.containsKey(SOBillType.Order.getCode())) {
      PfAddInfo pfaddinfo = new PfAddInfo();
      pfaddinfo.setSrc_billtype(SOBillType.Order.getCode());
      pfaddinfo.setSrc_billtypename(SOBillType.Order.getName());
      pfaddinfo.setMakeflag(false);
      ret.put(SOBillType.Order.getCode(), pfaddinfo);
    }
//    应收费用结算单、原料销售结算单、成套销售结算单、钢材代理结算单、非钢代理结算单、进口原料报账单
    PfAddInfo pfaddinfo = new PfAddInfo();
    pfaddinfo.setSrc_billtype("IT02");
    pfaddinfo.setSrc_billtypename("原料销售结算单");
    pfaddinfo.setMakeflag(false);
    ret.put("IT02", pfaddinfo);
    
    PfAddInfo pfaddinfo1 = new PfAddInfo();
    pfaddinfo.setSrc_billtype("IT01");
    pfaddinfo.setSrc_billtypename("成套销售结算单");
    pfaddinfo.setMakeflag(false);
    ret.put("IT01", pfaddinfo1);

    PfAddInfo pfaddinfo2 = new PfAddInfo();
    pfaddinfo.setSrc_billtype("ET02");
    pfaddinfo.setSrc_billtypename("非钢代理结算单");
    pfaddinfo.setMakeflag(false);
    ret.put("ET02", pfaddinfo2);
    
    PfAddInfo pfaddinfo3 = new PfAddInfo();
    pfaddinfo.setSrc_billtype("ET03");
    pfaddinfo.setSrc_billtypename("钢材代理结算单");
    pfaddinfo.setMakeflag(false);
    ret.put("ET03", pfaddinfo3);
    
    PfAddInfo pfaddinfo4 = new PfAddInfo();
    pfaddinfo.setSrc_billtype("LM40");
    pfaddinfo.setSrc_billtypename("进口原料报账单");
    pfaddinfo.setMakeflag(false);
    ret.put("LM40", pfaddinfo4);
    
    PfAddInfo pfaddinfo5 = new PfAddInfo();
    pfaddinfo.setSrc_billtype("LM21");
    pfaddinfo.setSrc_billtypename("应付费用结算单");
    pfaddinfo.setMakeflag(false);
    ret.put("LM21", pfaddinfo5);
    
    
    return ret;
  }

}
