package nc.vo.so.m33.pub.util;

import nc.vo.arap.basebill.BaseAggVO;
import nc.vo.arap.basebill.BaseBillVO;
import nc.vo.arap.basebill.BaseItemVO;
import nc.vo.arap.pub.BillEnumCollection;
import nc.vo.arap.receivable.AggReceivableBillVO;
import nc.vo.pubapp.pattern.pub.MapList;
import nc.vo.pubapp.pattern.pub.PubAppTool;
import nc.vo.scmpub.res.billtype.ICBillType;
import nc.vo.scmpub.res.billtype.SOBillType;

public class ARBillUtil {

  private static ARBillUtil instance = new ARBillUtil();

  private ARBillUtil() {
    super();
  }

  public static ARBillUtil getInstance() {
    return ARBillUtil.instance;
  }

  /**
   * 方法功能描述：应收单据表体行是否源头是销售订单
   * <p>
   * <b>examples:</b>
   * <p>
   * 使用示例
   * <p>
   * <b>参数说明</b>
   * 
   * @param itemVO
   * @return
   *         <p>
   * @author zhangcheng
   * @time 2010-8-16 上午11:24:58
   */
  public boolean isFirstFromSaleOrder(BaseItemVO itemVO) {
    boolean isFirstFromSaleOrder = false;
    String src_billtype = itemVO.getSrc_billtype();
    if (!PubAppTool.isNull(src_billtype)) {
      if (SOBillType.Order.getCode().equals(src_billtype.trim())) {
        isFirstFromSaleOrder = true;
      }
    }
    return isFirstFromSaleOrder;
  }

  /**
   * 方法功能描述：应收单据是否来源于销售
   * <p>
   * <b>examples:</b>
   * <p>
   * 使用示例
   * <p>
   * <b>参数说明</b>
   * 
   * @param billvo
   * @return
   *         <p>
   * @author zhangcheng
   * @time 2010-8-16 上午11:17:25
   */
  public boolean isFromSO(BaseAggVO billvo) {
    boolean isFromSO = false;
    BaseBillVO parent = (BaseBillVO) billvo.getParentVO();
    int syscode = parent.getSrc_syscode().intValue();
    if (BillEnumCollection.FromSystem.SO.VALUE.intValue() == syscode) {
      isFromSO = true;
    }
    return isFromSO;
  }

  /**
   * 方法功能描述：应收单据是否是销售结算生成的单据
   * 应收单据是否来源于销售系统
   * 要求表体行全部源头为销售订单，并且来源于销售发票或者销售出库单
   * <p>
   * <b>examples:</b>
   * <p>
   * 使用示例
   * <p>
   * <b>参数说明</b>
   * 
   * @param billvo
   * @return
   *         <p>
   * @author zhangcheng
   * @time 2010-8-16 上午11:19:47
   */
  public boolean isSOSquareDriveARBill(BaseAggVO billvo) {
    boolean isSOSquareDriveARBill = false;
    boolean isFromSO = this.isFromSO(billvo);

    // 来源于销售系统
    if (isFromSO) {
      BaseItemVO[] items = (BaseItemVO[]) billvo.getChildrenVO();
      for (BaseItemVO itemVO : items) {
        if (!this.isFirstFromSaleOrder(itemVO)) {
          isSOSquareDriveARBill = false;
          break;
        }
        if (!this.isSrcFrom4Cor32(itemVO)) {
          isSOSquareDriveARBill = false;
          break;
        }
        isSOSquareDriveARBill = true;
      }
    }

    return isSOSquareDriveARBill;
  }

  /**
   * 方法功能描述：应收单据表体行是否来源于销售出库单或者销售发票
   * <p>
   * <b>examples:</b>
   * <p>
   * 使用示例
   * <p>
   * <b>参数说明</b>
   * 
   * @param itemVO
   * @return
   *         <p>
   * @author zhangcheng
   * @time 2010-8-16 上午11:32:43
   */
  public boolean isSrcFrom4Cor32(BaseItemVO itemVO) {
    boolean isSrcFrom4Cor32 = false;
    String top_billtype = itemVO.getTop_billtype();
    if (!PubAppTool.isNull(top_billtype)) {
      if (SOBillType.Invoice.getCode().equals(top_billtype.trim())
          || ICBillType.SaleOut.getCode().equals(top_billtype.trim())) {
        isSrcFrom4Cor32 = true;
      }
    }
    return isSrcFrom4Cor32;
  }
  
  public MapList<String, AggReceivableBillVO> splitArapVO(
      AggReceivableBillVO[] arapvos) {
    MapList<String, AggReceivableBillVO> arapvoMapList =
        new MapList<String, AggReceivableBillVO>();
    for (AggReceivableBillVO billVO : arapvos) {
      arapvoMapList.put(billVO.getHeadVO().getPk_org(), billVO);
    }
    return arapvoMapList;
  }

}
