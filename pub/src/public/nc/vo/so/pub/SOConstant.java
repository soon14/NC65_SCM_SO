package nc.vo.so.pub;

import nc.vo.pub.lang.UFDate;
import nc.vo.pub.lang.UFDouble;

/**
 * 销售管理常量类
 * 
 * @since 6.0
 * @version 2011-12-2 上午09:03:34
 * @author fengjb
 */
public class SOConstant {

  /**
   * 订单关闭管理查询最大的行数
   */
  public static final int CLOSEMANAGEMAXROWS = 5001;

  /**
   * 审批动作脚本标识
   */
  public static final String APPROVE = "APPROVE";

  /**
   * 保存动作标示
   */
  public static final String WRITE = "WRITE";

  /** 删除动作标示*/
  public static final String DELETE = "DELETE";

  /** 标准重量单位 */
  public static final String BD305 = "BD305";
  
  /**
   * 标准体积单位
   */
  public static final String BD304="BD304";

  public static final String AGGFFILEVO = "aggffilevo";

  public static final String SO_BATCHCODE_PATH =
      "nc/ui/so/pub/model/so_batchcode.xml";

  public static final String[] ONHANDDLG_BODY_KEY = new String[] {
    SOItemKey.CMATERIALVID, SOItemKey.CASTUNITID, SOItemKey.VBATCHCODE,
    SOItemKey.VCHANGERATE, SOItemKey.CPROJECTID, SOItemKey.CPRODUCTORID,
    SOItemKey.CVENDORID, SOItemKey.CMFFILEID, SOItemKey.VFREE1,
    SOItemKey.VFREE2, SOItemKey.VFREE3, SOItemKey.VFREE4, SOItemKey.VFREE5,
    SOItemKey.VFREE6, SOItemKey.VFREE7, SOItemKey.VFREE8, SOItemKey.VFREE9,
    SOItemKey.VFREE10,
  };

  /**
   * 销售发票支持批改的字段
   */
  public static final String[] DELIVERYFILLENABLEDKEY = new String[] {
    // 仓库
    SOItemKey.CSENDSTORDOCID,
    // 发货日期、押运员、承运商、车型、车辆
    SOItemKey.DSENDDATE, SOItemKey.CSUPERCARGOID, SOItemKey.CTRANSCUSTID,
    SOItemKey.CVEHICLETYPEID, SOItemKey.CVEHICLEID
  };

  /**
   * 销售订单和预定支持批改的字段
   */
  public static final String[] FILLENABLEDKEY = new String[] {
    // 赠品标志(V635新增-索芙特项目)
    SOItemKey.BLARGESSFLAG,
    // 汇率、
    SOItemKey.NEXCHANGERATE,
    // 税率
    SOItemKey.NTAXRATE,
    // 单品折扣、主含税单价、主无税单价
    SOItemKey.NITEMDISCOUNTRATE,
    SOItemKey.NORIGTAXPRICE,
    SOItemKey.NORIGPRICE,
    // 主含税净价、主无税净价、含税单价
    SOItemKey.NORIGTAXNETPRICE,
    SOItemKey.NORIGNETPRICE,
    SOItemKey.NQTORIGTAXPRICE,
    // 无税单价、含税净价、无税净价
    SOItemKey.NQTORIGPRICE,
    SOItemKey.NQTORIGTAXNETPRC,
    SOItemKey.NQTORIGNETPRICE,

    // 发货库存组织、仓库、发货日期、收货客户、收货地区、收货地点、收货地址、到货日期
    // 项目、备注
    SOItemKey.CSENDSTOCKORGVID, SOItemKey.CSENDSTORDOCID, SOItemKey.DSENDDATE,
    SOItemKey.CRECEIVECUSTID, SOItemKey.CRECEIVEAREAID,
    SOItemKey.CRECEIVEADDDOCID, SOItemKey.CRECEIVEADDRID,
    SOItemKey.DRECEIVEDATE, SOItemKey.CPROJECTID, SOItemKey.VROWNOTE
  };

  /** 我的订单列表模板ID */
  public static final String MYORDER_BILLTEMPLET = "1005Z8100000000054EZ";

  /** 折扣默认值100 */
  public static final UFDouble ONEHUNDRED = new UFDouble(100);

  /** 操作失败 */
  public static final String OPRETAION_FAIL = "1";

  /** 操作成功 */
  public static final String OPRETAION_SUCCESS = "0";

  /** 我的订单明细模板ID */
  public static final String ORDERDTL_BILLTEMPLET = "1005Z8100000000058A4";

  /** 点分隔符 */
  public static final String POINT = ".";

  /** 取权限需要的常量(操作编码) */
  public static final String PSNDOC = "psndoc";

  /**
   * 销售发票支持批改的字段
   */
  public static final String[] SALEINVOICEFILLENABLEDKEY = new String[] {
    // 主含税单价、主无税单价
    SOItemKey.NORIGTAXPRICE,
    SOItemKey.NORIGPRICE,
    // 主含税净价、主无税净价、含税单价
    SOItemKey.NORIGTAXNETPRICE, SOItemKey.NORIGNETPRICE,
    SOItemKey.NQTORIGTAXPRICE,
    // 无税单价、含税净价、无税净价
    SOItemKey.NQTORIGPRICE, SOItemKey.NQTORIGTAXNETPRC,
    SOItemKey.NQTORIGNETPRICE,

    // 仓库
    SOItemKey.CSENDSTORDOCID,
  };

  /** 取权限需要的常量(资源权限实体编码) */
  public static final String SCMDEFAULT = "SCMDefault";

  /**
   * 需要触发单价金额算法的字段
   */
  public static final String[] STRNEEDCALKEY = new String[] {
    // 数量、主数量、换算率
    SOItemKey.NASTNUM,
    SOItemKey.NNUM,
    SOItemKey.VCHANGERATE,
    // 单位
    SOItemKey.CASTUNITID,
    // 报价单位数量、报价换算率、税率
    SOItemKey.NQTUNITNUM,
    SOItemKey.VQTUNITRATE,
    SOItemKey.NTAXRATE,
    /**
     * add by lyw 2017-6-9
     * 代理费率、单价取整、汇率、采购价格、采购币种
     */
    SOItemKey.DLFL,
    SOItemKey.DJQZ,
    SOItemKey.EXCHANGE_RATE,
    SOItemKey.CGJG,
    SOItemKey.BUYCCURRENCYID,
    // 整单折扣、单品折扣、主含税单价、主无税单价
    SOItemKey.NDISCOUNTRATE,
    SOItemKey.NITEMDISCOUNTRATE,
    SOItemKey.NORIGTAXPRICE,
    SOItemKey.NORIGPRICE,
    // 主含税净价、主无税净价、含税单价
    SOItemKey.NORIGTAXNETPRICE,
    SOItemKey.NORIGNETPRICE,
    SOItemKey.NQTORIGTAXPRICE,
    // 无税单价、含税净价、无税净价
    SOItemKey.NQTORIGPRICE,
    SOItemKey.NQTORIGTAXNETPRC,
    SOItemKey.NQTORIGNETPRICE,
    // 税额、无税金额、价税合计
    SOItemKey.NTAX, SOItemKey.NORIGMNY, SOItemKey.NORIGTAXMNY, SOItemKey.NMNY,
    SOItemKey.NTAXMNY,
    // 折扣额、折本汇率
    SOItemKey.NORIGDISCOUNT, SOItemKey.NEXCHANGERATE,
    // 集团本位币汇率、全局本位币汇率、扣税类别、计税金额
    SOItemKey.NGROUPEXCHGRATE, SOItemKey.NGLOBALEXCHGRATE,
    SOItemKey.FTAXTYPEFLAG, SOItemKey.NCALTAXMNY
  };

  /** 结束日期默认值 */
  public static final UFDate SYSENDDATE = new UFDate("2099-12-31");

  /** 报表展示的单据日期变量 */
  public static final String VAR_DBILLDATE = "var_dbilldate";

  /** 报表展示的业务日期变量 */
  // public static final String VAR_DBUSIDATE = "var_dbusidate";

  /** 表体自定义项前缀 */
  public static final String VBDEF = "vbdef";

  /** 表头自定义项前缀 */
  public static final String VDEF = "vdef";

}
