package nc.ui.so.m38.billui.editor.bodyevent;

import nc.vo.pubapp.pattern.exception.ExceptionUtils;
import nc.vo.pubapp.pattern.pub.PubAppTool;
import nc.vo.so.m38.entity.PreOrderBVO;
import nc.vo.so.m38.entity.PreOrderHVO;
import nc.vo.so.pub.keyvalue.IKeyValue;

import nc.ui.pub.bill.BillCardPanel;
import nc.ui.pubapp.uif2app.event.IAppEventHandler;
import nc.ui.pubapp.uif2app.event.card.CardBodyBeforeEditEvent;
import nc.ui.so.m38.billui.view.PreOrderEditor;
import nc.ui.so.pub.keyvalue.CardKeyValue;

/**
 * 预订单表体编辑前事件派发类
 * 
 * @since 6.0
 * @version 2011-6-9 上午09:56:28
 * @author fengjb
 */
public class BodyBeforeEditHandler implements
    IAppEventHandler<CardBodyBeforeEditEvent> {

  private PreOrderEditor editor;

  /**
   * 
   * 
   * @return gg
   */
  public PreOrderEditor getEditor() {
    return this.editor;
  }

  @Override
  public void handleAppEvent(CardBodyBeforeEditEvent e) {
    // 默认为可编辑
    e.setReturnValue(true);
    String editKey = e.getKey();
    // 检查交易类型是否为空
    BillCardPanel cardPanel = e.getBillCardPanel();
    IKeyValue keyValue = new CardKeyValue(cardPanel);
    String trantypeid = keyValue.getHeadStringValue(PreOrderHVO.CTRANTYPEID);
    if (PubAppTool.isNull(trantypeid)) {
      ExceptionUtils
          .wrappBusinessException(nc.vo.ml.NCLangRes4VoTransl.getNCLangRes()
              .getStrByID("4006012_0", "04006012-0012")/*@res "请先录入交易类型"*/);
    }
    // 价格项
    else if (PreOrderBVO.CPRICEITEMID.equals(editKey)) {
      PriceItemEditHandler handler = new PriceItemEditHandler();
      handler.beforeEdit(e);
    }
    // 客户物料码（V63新加）
    else if (PreOrderBVO.CCUSTMATERIALID.equals(editKey)) {
      CustMaterialEditHandler handler = new CustMaterialEditHandler();
      handler.beforeEdit(e);
    }
    // 物料
    if (PreOrderBVO.CMATERIALVID.equals(editKey)) {
      MaterialEditHandler handler = new MaterialEditHandler();
      handler.beforeEdit(e);
    }
    // 业务单位
    else if (PreOrderBVO.CASTUNITID.equals(editKey)) {
      AstUnitEditHandler handler = new AstUnitEditHandler();
      handler.beforeEdit(e);
    }
    // 业务单位换算率
    else if (PreOrderBVO.VCHANGERATE.equals(editKey)) {
      ChangeRateEditHandler handler = new ChangeRateEditHandler();
      handler.beforeEdit(e);
    }
    // 报价单位
    else if (PreOrderBVO.CQTUNITID.equals(editKey)) {
      QtUnitEditHandler handler = new QtUnitEditHandler();
      handler.beforeEdit(e);
    }
    // 报价单位换算率
    else if (PreOrderBVO.VQTUNITRATE.equals(editKey)) {
      QtUnitRateEditHandler handler = new QtUnitRateEditHandler();
      handler.beforeEdit(e);
    }
    // 赠品标志
    else if (PreOrderBVO.BLARGESSFLAG.equals(editKey)) {
      LargessFlagEditHandler handler = new LargessFlagEditHandler();
      handler.beforeEdit(e);
    }
    // 单品折扣
    else if (PreOrderBVO.NITEMDISCOUNTRATE.equals(editKey)) {
      ItemDisRateEditHandler handler = new ItemDisRateEditHandler();
      handler.beforeEdit(e);
    }
    // 发货库存组织
    else if (PreOrderBVO.CSENDSTOCKORGVID.equals(editKey)) {
      SendStockOrgEditHandler handler = new SendStockOrgEditHandler();
      handler.beforeEdit(e);
    }
    // 发货仓库
    else if (PreOrderBVO.CSENDSTORDOCID.equals(editKey)) {
      SendStordocEditHandler handler = new SendStordocEditHandler();
      handler.beforeEdit(e);
    }
    // 物流组织
    else if (PreOrderBVO.CTRAFFICORGVID.equals(editKey)) {
      TrafficOrgEditHandler handler = new TrafficOrgEditHandler();
      handler.beforeEdit(e);
    }
    // 折本汇率
    else if (PreOrderBVO.NEXCHANGERATE.equals(editKey)) {
      ExchangerateEditHandler handler = new ExchangerateEditHandler();
      handler.beforeEdit(e);
    }
    // 全局折本汇率
    else if (PreOrderBVO.NGLOBALEXCHGRATE.equals(editKey)) {
      GlobalExchgRateEditHandler handler = new GlobalExchgRateEditHandler();
      handler.beforeEdit(e);
    }
    // 集团折本汇率
    else if (PreOrderBVO.NGROUPEXCHGRATE.equals(editKey)) {
      GroupExchgRateEditHandler handler = new GroupExchgRateEditHandler();
      handler.beforeEdit(e);
    }
    // 计税金额
    else if (PreOrderBVO.NCALTAXMNY.equals(editKey)) {
      CalTaxMnyEditHandler handler = new CalTaxMnyEditHandler();
      handler.beforeEdit(e);
    }
    // 税码
    else if (PreOrderBVO.CTAXCODEID.equals(editKey)) {
      TaxCodeEditHandler handler = new TaxCodeEditHandler();
      handler.beforeEdit(e);
    }
    // 批次号
    else if (PreOrderBVO.VBATCHCODE.equals(editKey)) {
      BatchCodeEditHandler handler = new BatchCodeEditHandler();
      handler.setEditor(this.editor);
      handler.beforeEdit(e);
    }
    // 收货地址
    else if (PreOrderBVO.CRECEIVEADDRID.equals(editKey)) {
      ReceiveAddressEditHandler handler = new ReceiveAddressEditHandler();
      handler.beforeEdit(e);
    }

  }

  /**
   * 
   * 
   * @param editor
   */
  public void setEditor(PreOrderEditor editor) {
    this.editor = editor;
  }

}
