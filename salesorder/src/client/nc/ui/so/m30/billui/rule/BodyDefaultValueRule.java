package nc.ui.so.m30.billui.rule;

import nc.vo.pub.lang.UFDate;
import nc.vo.pub.lang.UFDouble;
import nc.vo.so.m30.entity.SaleOrderBVO;
import nc.vo.so.m30.entity.SaleOrderHVO;
import nc.vo.so.pub.SOConstant;
import nc.vo.so.pub.keyvalue.IKeyValue;

import nc.ui.pubapp.AppUiContext;

public class BodyDefaultValueRule {

  private IKeyValue keyValue;

  public BodyDefaultValueRule(IKeyValue keyValue) {
    this.keyValue = keyValue;
  }

  /**
   * 根据传入的reservOrigValue判断是否需要重新赋值（针对编辑公式）
   * 
   * @since 2013.07.17
   * @author dongli2
   * @param rows
   * @param reservOrigValue
   */
  public void setBodyDefValue(int[] rows, boolean reservOrigValue) {

    // 主表ID
    String orderid =
        this.keyValue.getHeadStringValue(SaleOrderHVO.CSALEORDERID);
    // 主组织
    String pk_org = this.keyValue.getHeadStringValue(SaleOrderHVO.PK_ORG);
    // 集团
    String pk_group = AppUiContext.getInstance().getPkGroup();
    // 单据日期
    UFDate dbilldate = this.keyValue.getHeadUFDateValue(SaleOrderHVO.DBILLDATE);
    // 业务日期
    UFDate busidate = AppUiContext.getInstance().getBusiDate();
    busidate = busidate.asLocalEnd();
    // 整单折扣
    UFDouble ndiscountrate =
        this.keyValue.getHeadUFDoubleValue(SaleOrderHVO.NDISCOUNTRATE);
    if (null == ndiscountrate) {
      ndiscountrate = SOConstant.ONEHUNDRED;
    }
    for (int row : rows) {
      // 根据传入的reservOrigValue及表体字段是否为空 判断是否需要重新赋值 dongli2 2013.07.17
      if (!reservOrigValue
          || this.keyValue.getBodyValue(row, SaleOrderBVO.CSALEORDERID) == null) {
        this.keyValue.setBodyValue(row, SaleOrderBVO.CSALEORDERID, orderid);
      }
      if (!reservOrigValue
          || this.keyValue.getBodyValue(row, SaleOrderBVO.PK_ORG) == null) {
        this.keyValue.setBodyValue(row, SaleOrderBVO.PK_ORG, pk_org);
      }
      if (!reservOrigValue
          || this.keyValue.getBodyValue(row, SaleOrderBVO.PK_GROUP) == null) {
        this.keyValue.setBodyValue(row, SaleOrderBVO.PK_GROUP, pk_group);
      }

      // 收发货日期
      if (!reservOrigValue
          || this.keyValue.getBodyValue(row, SaleOrderBVO.DBILLDATE) == null) {
        this.keyValue.setBodyValue(row, SaleOrderBVO.DBILLDATE, dbilldate);
      }
      /*      if (!reservOrigValue
                || this.keyValue.getBodyValue(row, SaleOrderBVO.DSENDDATE) == null) {
              this.keyValue.setBodyValue(row, SaleOrderBVO.DSENDDATE, busidate);
            }
            if (!reservOrigValue
                || this.keyValue.getBodyValue(row, SaleOrderBVO.DRECEIVEDATE) == null) {
              this.keyValue.setBodyValue(row, SaleOrderBVO.DRECEIVEDATE, busidate);
            }*/
      // 单品折扣
      if (!reservOrigValue
          || this.keyValue.getBodyValue(row, SaleOrderBVO.NITEMDISCOUNTRATE) == null) {
        this.keyValue.setBodyValue(row, SaleOrderBVO.NITEMDISCOUNTRATE,
            SOConstant.ONEHUNDRED);
      }
      // 整单折扣
      if (!reservOrigValue
          || this.keyValue.getBodyValue(row, SaleOrderBVO.NDISCOUNTRATE) == null) {
        this.keyValue.setBodyValue(row, SaleOrderBVO.NDISCOUNTRATE,
            ndiscountrate);
      }
    }
  }

  public void setBodyDefValue(int[] rows) {
    this.setBodyDefValue(rows, false);
  }
}
