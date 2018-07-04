/**
 * $文件说明$
 * 
 * @author gdsjw
 * @version
 * @see
 * @since
 * @time 2010-6-7 下午03:13:57
 */
package nc.ui.so.m30.billui.tranferbill;

import nc.bs.framework.common.NCLocator;
import nc.itf.so.m30trantype.IM30TranTypeService;
import nc.ui.pub.bill.BillCardPanel;
import nc.ui.pub.pf.PfUtilClient;
import nc.ui.pubapp.AppUiContext;
import nc.ui.pubapp.billref.dest.DefaultBillDataLogic;
import nc.ui.so.pub.keyvalue.CardKeyValue;
import nc.vo.pub.BusinessException;
import nc.vo.pubapp.pattern.exception.ExceptionUtils;
import nc.vo.pubapp.pattern.pub.PubAppTool;
import nc.vo.scmpub.res.billtype.SOBillType;
import nc.vo.so.m30.entity.SaleOrderHVO;
import nc.vo.so.m30.rule.HeadTotalCalculateRule;
import nc.vo.so.m30trantype.entity.M30TranTypeVO;
import nc.vo.so.pub.keyvalue.IKeyValue;

/**
 * <p>
 * <b>本类主要完成以下功能：</b>
 * <ul>
 * <li>功能条目1
 * <li>功能条目2
 * <li>...
 * </ul>
 * <p>
 * <b>变更历史（可选）：</b>
 * <p>
 * XXX版本增加XXX的支持。
 * <p>
 * <p>
 * 
 * @version 本版本号
 * @since 上一版本号
 * @author gdsjw
 * @time 2010-6-7 下午03:13:57
 */
public class M30Ref30TransferBillDataLogic extends DefaultBillDataLogic {

  @Override
  public void doTransferAddLogic(Object selectedData) {

    // 把数据设置在界面上
    super.doTransferAddLogic(selectedData);

    // 基于界面卡片填充值运算
    BillCardPanel cardPanel = this.getBillForm().getBillCardPanel();
    IKeyValue keyValue = new CardKeyValue(cardPanel);

    // 1.退换货订单节点填充交易类型
    String ctrantypeid = keyValue.getHeadStringValue(SaleOrderHVO.CTRANTYPEID);
    if (!PubAppTool.isNull(ctrantypeid)) {
      M30TranTypeVO newtrantypevo = null;
      try {
        IM30TranTypeService m30srv =
            NCLocator.getInstance().lookup(IM30TranTypeService.class);
        newtrantypevo = m30srv.queryTranTypeVO(ctrantypeid);
      }
      catch (BusinessException e1) {
        ExceptionUtils.wrappException(e1);
      }
      if (newtrantypevo==null) {
        ExceptionUtils.wrappBusinessException("根据交易类型ID查询交易VO失败！");/*-=notranslate=-*/
      }
      String trantypecode = newtrantypevo.getVtrantypecode();
      keyValue.setHeadValue(SaleOrderHVO.VTRANTYPECODE, trantypecode);

      // 自动匹配业务流程
      String csaleorgid = keyValue.getHeadStringValue(SaleOrderHVO.PK_ORG);
      String userid = AppUiContext.getInstance().getPkUser();
      String cbiztypeid = null;
      try {
        cbiztypeid =
            PfUtilClient.retBusitypeCanStart(SOBillType.Order.getCode(),
                trantypecode, csaleorgid, userid);
      }
      catch (BusinessException ex) {
        ExceptionUtils.wrappException(ex);
      }
      // 设置业务流程
      keyValue.setHeadValue(SaleOrderHVO.CBIZTYPEID, cbiztypeid);
    }
    // 表头合计
    HeadTotalCalculateRule totalrule = new HeadTotalCalculateRule(keyValue);
    totalrule.calculateHeadTotal();

  }
}
