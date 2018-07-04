package nc.ui.so.m38.billui.pub;

import nc.vo.so.m38.entity.PreOrderBVO;
import nc.vo.so.pub.keyvalue.IKeyValue;

/**
 * 销售预订单清空表体字段的值
 * 
 * @since 6.0
 * @version 2012-2-9 下午10:55:15
 * @author 刘景
 */
public class ClearBodyValueRule {

  /**
   * 表体需要清空的字段
   */
  private static final String[] DEMANDCLEAR_KEY = new String[] {
    // 自由辅助属性
    PreOrderBVO.CVENDORID,
    PreOrderBVO.CPROJECTID,
    PreOrderBVO.CQUALITYLEVELID,
    PreOrderBVO.CPRODUCTORID,
    PreOrderBVO.VFREE1,
    PreOrderBVO.VFREE2,
    PreOrderBVO.VFREE3,
    PreOrderBVO.VFREE4,
    PreOrderBVO.VFREE5,
    PreOrderBVO.VFREE6,
    PreOrderBVO.VFREE7,
    PreOrderBVO.VFREE8,
    PreOrderBVO.VFREE9,
    PreOrderBVO.VFREE10,
    // 询价信息
    PreOrderBVO.NASKQTORIGNETPRICE,
    PreOrderBVO.NASKQTORIGPRICE,
    PreOrderBVO.NASKQTORIGTAXPRC,
    PreOrderBVO.NASKQTORIGTXNTPRC,
    PreOrderBVO.CPRICEPOLICYID,
    PreOrderBVO.CPRICEITEMID,
    PreOrderBVO.CPRICEITEMTABLEID,
    PreOrderBVO.CPRICEFORMID,
    // 赠品标志位
    PreOrderBVO.BLARGESSFLAG,

    // 主原币单价
    PreOrderBVO.NORIGNETPRICE,
    PreOrderBVO.NORIGPRICE,
    PreOrderBVO.NORIGTAXPRICE,
    PreOrderBVO.NORIGTAXNETPRICE,
    // 主本币单价
    PreOrderBVO.NNETPRICE,
    PreOrderBVO.NPRICE,
    PreOrderBVO.NTAXPRICE,
    PreOrderBVO.NTAXNETPRICE,
    // 本币单价
    PreOrderBVO.NQTNETPRICE,
    PreOrderBVO.NQTPRICE,
    PreOrderBVO.NQTTAXNETPRICE,
    PreOrderBVO.NQTTAXPRICE,
    // 原币单价
    PreOrderBVO.NQTORIGTAXNETPRC, PreOrderBVO.NQTORIGTAXPRICE,
    PreOrderBVO.NQTORIGPRICE,
    PreOrderBVO.NQTORIGNETPRICE,
    // 金额
    PreOrderBVO.NGLOBALTAXMNY, PreOrderBVO.NGROUPTAXMNY,
    PreOrderBVO.NORIGTAXMNY, PreOrderBVO.NORIGMNY, PreOrderBVO.NORIGDISCOUNT,
    // 本币金额
    PreOrderBVO.NTAX, PreOrderBVO.NMNY, PreOrderBVO.NTAXMNY,
    PreOrderBVO.NDISCOUNT,
    // 集团金额
    PreOrderBVO.NGROUPMNY, PreOrderBVO.NGROUPTAXMNY,
    // 全局金额
    PreOrderBVO.NGLOBALMNY, PreOrderBVO.NGLOBALTAXMNY,
  };

  private IKeyValue keyValue;

  public ClearBodyValueRule(IKeyValue keyValue) {
    this.keyValue = keyValue;
  }

  /**
   * 清空表体字段的值
   * 
   * @param editrow 行号数组
   */
  public void clearBodyValue(int[] rows) {
    for (int row : rows) {
      for (String key : ClearBodyValueRule.DEMANDCLEAR_KEY) {
        this.keyValue.setBodyValue(row, key, null);
      }
    }
  }
}
