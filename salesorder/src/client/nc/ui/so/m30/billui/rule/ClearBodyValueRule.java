package nc.ui.so.m30.billui.rule;

import java.util.Map;

import nc.vo.ct.business.enumeration.Ninvctlstyle;
import nc.vo.ct.entity.CtBusinessVO;
import nc.vo.scmpub.res.billtype.CTBillType;
import nc.vo.so.m30.entity.SaleOrderBVO;
import nc.vo.so.m30.entity.SaleOrderHVO;
import nc.vo.so.m30.enumeration.Largesstype;
import nc.vo.so.pub.SOConstant;
import nc.vo.so.pub.keyvalue.IKeyValue;

public class ClearBodyValueRule {

  private static final String[] FORCECLEAR_KEY = new String[] {
    // 自由辅助属性
    SaleOrderBVO.CVENDORID,
    //    SaleOrderBVO.CPROJECTID,  // 不清空项目 modify by jilu for EHP1合到633，貌似是因为项目从合同上带下来的，所以不清空
    SaleOrderBVO.CPRODUCTORID,
    SaleOrderBVO.CFACTORYID, SaleOrderBVO.CQUALITYLEVELID, SaleOrderBVO.VFREE1,
    SaleOrderBVO.VFREE2, SaleOrderBVO.VFREE3,
    SaleOrderBVO.VFREE4,
    SaleOrderBVO.VFREE5,
    SaleOrderBVO.VFREE6,
    SaleOrderBVO.VFREE7,
    SaleOrderBVO.VFREE8,
    SaleOrderBVO.VFREE9,
    SaleOrderBVO.VFREE10,
    // 询价信息
    SaleOrderBVO.NASKQTORIGTAXPRC,
    SaleOrderBVO.NASKQTORIGPRICE,
    SaleOrderBVO.NASKQTORIGTXNTPRC,
    SaleOrderBVO.NASKQTORIGNETPRICE,
    SaleOrderBVO.CPRICEPOLICYID,
    SaleOrderBVO.CPRICEITEMID,
    SaleOrderBVO.CPRICEITEMTABLEID,
    SaleOrderBVO.CPRICEFORMID,
    // 赠品标志位
    // SaleOrderBVO.BLARGESSFLAG,
    // 捆绑买赠
    SaleOrderBVO.CLARGESSSRCID, SaleOrderBVO.CBINDSRCID,
    SaleOrderBVO.CBUYPROMOTTYPEID, SaleOrderBVO.CBUYLARGESSACTID,

    // 退货信息
    SaleOrderBVO.CRETREASONID, SaleOrderBVO.VRETURNMODE,
    SaleOrderBVO.CRETPOLICYID,
    // 价格赠品分摊
    SaleOrderBVO.NLARGESSMNY, SaleOrderBVO.NLARGESSTAXMNY,
    // 特征码
    SaleOrderBVO.CMFFILEID,SaleOrderBVO.NMFFILEPRICE,
    SOConstant.AGGFFILEVO,
    // 删除批次档案、批次号
    SaleOrderBVO.PK_BATCHCODE,
    SaleOrderBVO.VBATCHCODE
  };
  
  private static final String[] HEAD_PRICE_KEY = new String[]{
	  SaleOrderHVO.NTOTALORIGSUBMNY, SaleOrderHVO.NTOTALORIGMNY,
	  SaleOrderHVO.NTOTALMNY
  };
  
  private static final String[] BODY_FFILE_KEY = new String[]{
	  	// 特征码
	    SaleOrderBVO.CMFFILEID,SaleOrderBVO.NMFFILEPRICE,
	    SOConstant.AGGFFILEVO
  };
  
  private static final String[] BODY_PRICE_KEY = new String[]{
	  	// 原币单价
	    SaleOrderBVO.NQTORIGTAXPRICE,
	    SaleOrderBVO.NQTORIGPRICE,
	    SaleOrderBVO.NQTORIGTAXNETPRC,
	    SaleOrderBVO.NQTORIGNETPRICE,
	    // 主原币单价
	    SaleOrderBVO.NORIGPRICE,
	    SaleOrderBVO.NORIGTAXPRICE,
	    SaleOrderBVO.NORIGNETPRICE,
	    SaleOrderBVO.NORIGTAXNETPRICE,
	    // 金额
	    SaleOrderBVO.NORIGMNY,
	    SaleOrderBVO.NORIGTAXMNY,
	    SaleOrderBVO.NORIGDISCOUNT,
	    // 本币单价
	    SaleOrderBVO.NQTTAXNETPRICE, SaleOrderBVO.NQTNETPRICE,
	    SaleOrderBVO.NQTTAXPRICE,
	    SaleOrderBVO.NQTPRICE,
	    // 主本币单价
	    SaleOrderBVO.NPRICE, SaleOrderBVO.NTAXPRICE, SaleOrderBVO.NNETPRICE,
	    SaleOrderBVO.NTAXNETPRICE,
	    // 本币金额
	    SaleOrderBVO.NTAX, SaleOrderBVO.NMNY, SaleOrderBVO.NTAXMNY,
	    SaleOrderBVO.NDISCOUNT,
	    // 集团金额
	    SaleOrderBVO.NGROUPMNY, SaleOrderBVO.NGROUPTAXMNY,
	    // 全局金额
	    SaleOrderBVO.NGLOBALMNY, SaleOrderBVO.NGLOBALTAXMNY,
	    SaleOrderBVO.NBFORIGSUBMNY,SaleOrderBVO.NORIGSUBMNY
  };

  private static final String[] NUMPRICE_KEY = new String[] {
    // 数量
    SaleOrderBVO.NNUM,
    SaleOrderBVO.NASTNUM,
    SaleOrderBVO.NQTUNITNUM,
    // 原币单价
    SaleOrderBVO.NQTORIGTAXPRICE,
    SaleOrderBVO.NQTORIGPRICE,
    SaleOrderBVO.NQTORIGTAXNETPRC,
    SaleOrderBVO.NQTORIGNETPRICE,
    // 主原币单价
    SaleOrderBVO.NORIGPRICE,
    SaleOrderBVO.NORIGTAXPRICE,
    SaleOrderBVO.NORIGNETPRICE,
    SaleOrderBVO.NORIGTAXNETPRICE,
    // 金额
    SaleOrderBVO.NORIGMNY,
    SaleOrderBVO.NORIGTAXMNY,
    SaleOrderBVO.NORIGDISCOUNT,
    // 本币单价
    SaleOrderBVO.NQTTAXNETPRICE, SaleOrderBVO.NQTNETPRICE,
    SaleOrderBVO.NQTTAXPRICE,
    SaleOrderBVO.NQTPRICE,
    // 主本币单价
    SaleOrderBVO.NPRICE, SaleOrderBVO.NTAXPRICE, SaleOrderBVO.NNETPRICE,
    SaleOrderBVO.NTAXNETPRICE,
    // 本币金额
    SaleOrderBVO.NTAX, SaleOrderBVO.NMNY, SaleOrderBVO.NTAXMNY,
    SaleOrderBVO.NDISCOUNT,
    // 集团金额
    SaleOrderBVO.NGROUPMNY, SaleOrderBVO.NGROUPTAXMNY,
    // 全局金额
    SaleOrderBVO.NGLOBALMNY, SaleOrderBVO.NGLOBALTAXMNY
  };

  private static final String[] SRCCLEAR_KEY = new String[] {
    // 来源单据信息
    SaleOrderBVO.VSRCTYPE, SaleOrderBVO.CSRCID, SaleOrderBVO.CSRCBID,
    SaleOrderBVO.VSRCCODE, SaleOrderBVO.VSRCROWNO,
    SaleOrderBVO.VSRCTRANTYPE,
    // 合同信息 修改：zhangby5 参照合同生成销售订单时，关联合同的行修改物料时，合同的项目不能清空
    SaleOrderBVO.CCTMANAGEBID, SaleOrderBVO.CCTMANAGEID,
    SaleOrderBVO.VCTCODE,
    SaleOrderBVO.CPROJECTID,
    // 源头单据信息
    SaleOrderBVO.VFIRSTTYPE, SaleOrderBVO.VFIRSTCODE, SaleOrderBVO.CFIRSTID,
    SaleOrderBVO.CFIRSTBID, SaleOrderBVO.VFIRSTROWNO,
    SaleOrderBVO.VFIRSTTRANTYPE
  };

  Map<String, CtBusinessVO> ctMap;

  private IKeyValue keyValue;

  public ClearBodyValueRule(IKeyValue keyValue, Map<String, CtBusinessVO> ctMap) {
    this.ctMap = ctMap;
    this.keyValue = keyValue;
  }

  public void clearBodyNoNumPriceMnyValue(int editrow) {
    for (String key : ClearBodyValueRule.FORCECLEAR_KEY) {
      this.keyValue.setBodyValue(editrow, key, null);
    }

    this.keyValue.setBodyValue(editrow, SaleOrderBVO.FLARGESSTYPEFLAG,
        Largesstype.NOAPPORTION.getIntValue());

    this.clearBodySrcValue(editrow);
  }

  public void clearBodyNumPirceMnyValue(int editrow) {
    if (this.isRefCTRows(editrow)) {
      return;
    }
    if(isRefMatappRows(editrow)){
    	return;
    }
    for (String key : ClearBodyValueRule.NUMPRICE_KEY) {
      this.keyValue.setBodyValue(editrow, key, null);
    }
  }

  private void clearBodySrcValue(int editrow) {
    if (this.isRefCTRows(editrow)) {
      return;
    }
    if(isRefMatappRows(editrow)){
    	return;
    }
    for (String key : ClearBodyValueRule.SRCCLEAR_KEY) {
      this.keyValue.setBodyValue(editrow, key, null);
    }
  }
  
  private boolean isRefMatappRows(int row){
	  String vsrctype =
		        this.keyValue.getBodyStringValue(row, SaleOrderBVO.VSRCTYPE);
	return "4648".equals(vsrctype);
  }

  /**
   * 来源合同物料分类行——编辑物料都为当前物料分类中物料，都会回写物料分类行。所以不应清空，即需保留数据
   */
  private boolean isRefCTRows(int row) {

    String vsrctype =
        this.keyValue.getBodyStringValue(row, SaleOrderBVO.VSRCTYPE);
    String cctmanagebid =
        this.keyValue.getBodyStringValue(row, SaleOrderBVO.CCTMANAGEBID);
    if (CTBillType.SaleDaily.getCode().equals(vsrctype) && null != this.ctMap
        && this.ctMap.containsKey(cctmanagebid)) {

      CtBusinessVO busiVO = this.ctMap.get(cctmanagebid);
      if (null != busiVO) {
        boolean isMarbasclass =
            Ninvctlstyle.MARBASCLASS.value().equals(busiVO.getNinvctlstyle());
        boolean isWithOut =
            Ninvctlstyle.WITHOUT.value().equals(busiVO.getNinvctlstyle());
        if (isMarbasclass || isWithOut) {
          return true;
        }
      }
    }
    return false;
  }
  
  /**
   * 清除表头和表体所有金额相关字段
   * 
   * @param rows
   */
  public void clearAllPriceKey(int[] rows){
	  
	  if(rows==null||rows.length==0){
		  return;
	  }
	  for(int row : rows){
		  for (String key : ClearBodyValueRule.BODY_PRICE_KEY) {
		      this.keyValue.setBodyValue(row, key, null);
		  }
	  }
	  for (String key : ClearBodyValueRule.HEAD_PRICE_KEY) {
	      this.keyValue.setHeadValue(key, null);
	  }
  }
  
  /**
   * 清空特征码相关字段
   * 
   * @param rows
   */
  public void clearAllFfileKey(int[] rows){
	  if(rows==null||rows.length==0){
		  return;
	  }
	  
	  for(int row : rows){
		  for (String key : ClearBodyValueRule.BODY_FFILE_KEY) {
		      this.keyValue.setBodyValue(row, key, null);
		  }
	  }
	  
  }

}
