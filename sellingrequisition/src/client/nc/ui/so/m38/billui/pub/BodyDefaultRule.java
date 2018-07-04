package nc.ui.so.m38.billui.pub;

import nc.ui.pubapp.AppUiContext;
import nc.vo.pub.lang.UFDate;
import nc.vo.pub.lang.UFDouble;
import nc.vo.so.m38.entity.PreOrderBVO;
import nc.vo.so.m38.entity.PreOrderHVO;
import nc.vo.so.pub.SOConstant;
import nc.vo.so.pub.SOItemKey;
import nc.vo.so.pub.keyvalue.IKeyValue;

public class BodyDefaultRule {

  private IKeyValue keyValue;

  public BodyDefaultRule(IKeyValue keyValue) {
    this.keyValue = keyValue;
  }

  public void setBodyDefaultValue(int[] rows) {
    // 表头币种
    String headorigcur =
        this.keyValue.getHeadStringValue(PreOrderHVO.CORIGCURRENCYID);
    // 订货日期(冗余单据日期)
    UFDate billdate = this.keyValue.getHeadUFDateValue(PreOrderHVO.DBILLDATE);
    // 整单折扣
    UFDouble discountrate =
        this.keyValue.getHeadUFDoubleValue(PreOrderHVO.NDISCOUNTRATE);
    if (null == discountrate) {
      discountrate = SOConstant.ONEHUNDRED;
    }
    // 业务日期
    UFDate busidate = AppUiContext.getInstance().getBusiDate();
    busidate = busidate.asLocalEnd();

    for (int row : rows) {

      // 币种
      this.keyValue.setBodyValue(row, SOItemKey.CORIGCURRENCYID, headorigcur);

      this.keyValue.setBodyValue(row, PreOrderBVO.DBILLDATE, billdate);

      // 整单折扣
      this.keyValue.setBodyValue(row, PreOrderBVO.NDISCOUNTRATE, discountrate);
      // 单品折扣
      this.keyValue.setBodyValue(row, PreOrderBVO.NITEMDISCOUNTRATE,
          SOConstant.ONEHUNDRED);
      // 计划发货日期、要求收货日期
      this.keyValue.setBodyValue(row, PreOrderBVO.DSENDDATE, busidate);
      this.keyValue.setBodyValue(row, PreOrderBVO.DRECEIVEDATE, busidate);

      // 数量
      this.keyValue.setBodyValue(row, PreOrderBVO.NNUM, null);
      this.keyValue.setBodyValue(row, PreOrderBVO.NASTNUM, null);
      this.keyValue.setBodyValue(row, PreOrderBVO.NQTUNITNUM, null);
    }

  }
}
