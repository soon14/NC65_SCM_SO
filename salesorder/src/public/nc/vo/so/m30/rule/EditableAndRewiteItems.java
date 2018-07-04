package nc.vo.so.m30.rule;

import nc.vo.so.m30.entity.SaleOrderBVO;
import nc.vo.so.m30.entity.SaleOrderHVO;
import nc.vo.so.m30.revise.entity.SaleOrderHistoryBVO;

/**
 * 销售订单修订字段 修订影响字段 集合
 * 
 * @since 6.36
 * @version 2015-1-6 下午5:37:30
 * @author wangshu6
 */
public class EditableAndRewiteItems {

	// 注册表头修订可以编辑的字段
	public static final String[] HEADEDITABLEITEMKEY = new String[] {
	/** ---------- 表头 ---------- */
	SaleOrderHVO.CTRADEWORDID, SaleOrderHVO.VREVISEREASON, SaleOrderHVO.VNOTE,
	/** -------表头自定义项------ **/
	SaleOrderHVO.VDEF1, SaleOrderHVO.VDEF2, SaleOrderHVO.VDEF3,
			SaleOrderHVO.VDEF4, SaleOrderHVO.VDEF5, SaleOrderHVO.VDEF6,
			SaleOrderHVO.VDEF7, SaleOrderHVO.VDEF8, SaleOrderHVO.VDEF9,
			SaleOrderHVO.VDEF10, SaleOrderHVO.VDEF11, SaleOrderHVO.VDEF12,
			SaleOrderHVO.VDEF13, SaleOrderHVO.VDEF14,
			SaleOrderHVO.VDEF15,
			SaleOrderHVO.VDEF16,
			SaleOrderHVO.VDEF17,
			SaleOrderHVO.VDEF18,
			SaleOrderHVO.VDEF19,
			SaleOrderHVO.VDEF20,
			// 王梓懿 新增元数据
			"jzrq", "jhq", "zcjhq", "dlfl", "djqz", "cmarbaseclassid",
			"chvendorid", "agdef1", "agdef2", "agdef3", "agdef4", "agdef5",
			"agdef6", "agdef7", "agdef8", "agdef9", "agdef10", "agdef11",
			"exchange_rate ", "buyccurrencyid", "sqg", "vbilllcode",
			"signinstructions", "dlxybz", "hblob1", "hblob2", "hblob3",
			"hblob4", "hblob5", };

	// 注册表体修订可以编辑的字段
	public static final String[] BODYEDITABLEITEMKEY = new String[] {

			/** ---------- 表体 ---------- */
			// 客户物料编码、物料编码、
			SaleOrderBVO.CCUSTMATERIALID,
			SaleOrderBVO.CMATERIALVID,

			// 件数、体积、重量
			SaleOrderBVO.NVOLUME,
			SaleOrderBVO.NWEIGHT,
			SaleOrderBVO.NPIECE,

			// 销售组织、报价单位、单位
			SaleOrderBVO.PK_ORG,
			SaleOrderBVO.CQTUNITID,
			SaleOrderBVO.CASTUNITID,
			// 赠品、质量等级、税码
			SaleOrderBVO.BLARGESSFLAG,
			SaleOrderBVO.CQUALITYLEVELID,
			SaleOrderBVO.CTAXCODEID,

			// 数量、主数量、换算率
			SaleOrderBVO.NASTNUM,
			SaleOrderBVO.NNUM,

			// 报价单位数量、报价换算率、税率
			SaleOrderBVO.NQTUNITNUM,
			SaleOrderBVO.NTAXRATE,
			// 主含税单价、主无税单价、主含税净价、主无税净价、
			SaleOrderBVO.NORIGTAXPRICE,
			SaleOrderBVO.NORIGPRICE,
			SaleOrderBVO.NORIGTAXNETPRICE,
			SaleOrderBVO.NORIGNETPRICE,
			// 含税单价 、无税单价、含税净价、无税净价
			SaleOrderBVO.NQTORIGTAXPRICE,
			SaleOrderBVO.NQTORIGPRICE,
			SaleOrderBVO.NQTORIGTAXNETPRC,
			SaleOrderBVO.NQTORIGNETPRICE,
			// 无税金额、价税合计、计税金额
			SaleOrderBVO.NORIGMNY,
			SaleOrderBVO.NORIGTAXMNY,
			// 本币税额
			SaleOrderBVO.NTAX,
			// 折扣额、折本汇率
			SaleOrderBVO.NORIGDISCOUNT,
			SaleOrderBVO.NEXCHANGERATE,
			// 修订理由
			SaleOrderBVO.VBREVISEREASON,
			// 库存组织、物流组织
			SaleOrderBVO.CSENDSTOCKORGVID,
			SaleOrderBVO.CTRAFFICORGVID,
			// 发货仓库
			SaleOrderBVO.CSENDSTORDOCID,
			// 结算财务组织、应收组织
			SaleOrderBVO.CSETTLEORGVID,
			SaleOrderBVO.CARORGVID,
			// 利润中心
			SaleOrderBVO.CPROFITCENTERVID,
			// 发货利润中心
			SaleOrderBVO.CSPROFITCENTERVID,
			// 发货、到货日期
			SaleOrderBVO.DSENDDATE,
			SaleOrderBVO.DRECEIVEDATE,
			// 发货国家地区、收货国家地区、报税国家地区、购销类型、三角贸易、
			SaleOrderBVO.CSENDCOUNTRYID,
			SaleOrderBVO.CRECECOUNTRYID,
			SaleOrderBVO.CTAXCOUNTRYID,
			SaleOrderBVO.FBUYSELLFLAG,
			SaleOrderBVO.BTRIATRADEFLAG,
			// 行备注
			SaleOrderBVO.VROWNOTE, SaleOrderBVO.CMFFILEID,
			SaleOrderBVO.NMFFILEPRICE,
			/** 固定辅助属性 */
			SaleOrderBVO.CPRODUCTORID, SaleOrderBVO.CPROJECTID,
			SaleOrderBVO.CVENDORID, SaleOrderBVO.CMFFILEID,
			/** 辅助属性 */
			SaleOrderBVO.VFREE1, SaleOrderBVO.VFREE2, SaleOrderBVO.VFREE3,
			SaleOrderBVO.VFREE4, SaleOrderBVO.VFREE5, SaleOrderBVO.VFREE6,
			SaleOrderBVO.VFREE7, SaleOrderBVO.VFREE9, SaleOrderBVO.VFREE8,
			SaleOrderBVO.VFREE10,
			/** -------表体自定义项------ **/
			SaleOrderBVO.VBDEF1, SaleOrderBVO.VBDEF2, SaleOrderBVO.VBDEF3,
			SaleOrderBVO.VBDEF4, SaleOrderBVO.VBDEF5, SaleOrderBVO.VBDEF6,
			SaleOrderBVO.VBDEF7, SaleOrderBVO.VBDEF8, SaleOrderBVO.VBDEF9,
			SaleOrderBVO.VBDEF10, SaleOrderBVO.VBDEF11, SaleOrderBVO.VBDEF12,
			SaleOrderBVO.VBDEF13, SaleOrderBVO.VBDEF14, SaleOrderBVO.VBDEF15,
			SaleOrderBVO.VBDEF16, SaleOrderBVO.VBDEF17, SaleOrderBVO.VBDEF18,
			SaleOrderBVO.VBDEF19, SaleOrderBVO.VBDEF20,

	};

	// 已生成下游单据可修订字段
	public static final String[] EDITABLEITEMKEYFOROUT = new String[] {

			// 数量、主数量、
			SaleOrderBVO.NASTNUM,
			SaleOrderBVO.NNUM,
			// 报价单位数量、
			SaleOrderBVO.NQTUNITNUM,
			// 无税单价、含税单价、无税净价、含税净价
			SaleOrderBVO.NQTORIGPRICE,
			SaleOrderBVO.NQTORIGTAXPRICE,
			SaleOrderBVO.NQTORIGNETPRICE,
			SaleOrderBVO.NQTORIGTAXNETPRC,
			// 主含税单价、主无税单价
			SaleOrderBVO.NORIGTAXPRICE,
			SaleOrderBVO.NORIGPRICE,
			// 主含税净价、主无税净价、
			SaleOrderBVO.NORIGTAXNETPRICE,
			SaleOrderBVO.NORIGNETPRICE,
			// 价税合计、
			SaleOrderBVO.NORIGTAXMNY,
			// 无税金额、税额、折扣额
			SaleOrderBVO.NORIGMNY,
			SaleOrderBVO.NTAX,
			// 折本汇率
			SaleOrderBVO.NEXCHANGERATE,
			// 备注
			SaleOrderBVO.VROWNOTE,
			SaleOrderHVO.VNOTE,
			/** -------表头自定义项------ **/
			SaleOrderHVO.VDEF1,
			SaleOrderHVO.VDEF2,
			SaleOrderHVO.VDEF3,
			SaleOrderHVO.VDEF4,
			SaleOrderHVO.VDEF5,
			SaleOrderHVO.VDEF6,
			SaleOrderHVO.VDEF7,
			SaleOrderHVO.VDEF8,
			SaleOrderHVO.VDEF9,
			SaleOrderHVO.VDEF10,
			SaleOrderHVO.VDEF11,
			SaleOrderHVO.VDEF12,
			SaleOrderHVO.VDEF13,
			SaleOrderHVO.VDEF14,
			SaleOrderHVO.VDEF15,
			SaleOrderHVO.VDEF16,
			SaleOrderHVO.VDEF17,
			SaleOrderHVO.VDEF18,
			SaleOrderHVO.VDEF19,
			SaleOrderHVO.VDEF20,
			// 王梓懿增加 测试
			"vbilllcode",
			/** -------表体自定义项------ **/
			SaleOrderBVO.VBDEF1,
			SaleOrderBVO.VBDEF2,
			SaleOrderBVO.VBDEF3,
			SaleOrderBVO.VBDEF4,
			SaleOrderBVO.VBDEF5,
			SaleOrderBVO.VBDEF6,
			SaleOrderBVO.VBDEF7,
			SaleOrderBVO.VBDEF8,
			SaleOrderBVO.VBDEF9,
			SaleOrderBVO.VBDEF10,
			SaleOrderBVO.VBDEF11,
			SaleOrderBVO.VBDEF12,
			SaleOrderBVO.VBDEF13,
			SaleOrderBVO.VBDEF14,
			SaleOrderBVO.VBDEF15,
			SaleOrderBVO.VBDEF16,
			SaleOrderBVO.VBDEF17,
			SaleOrderBVO.VBDEF18,
			SaleOrderBVO.VBDEF19,
			SaleOrderBVO.VBDEF20,
			// 王梓懿增加可修订的数据
			/******************** 表头 ******************************************/
			"jzrq",
			"jhq",
			"zcjhq",
			"dlfl",
			"djqz",
			"cmarbaseclassid",
			"chvendorid",
			"agdef1",
			"agdef2",
			"agdef3",
			"agdef4",
			"agdef5",
			"agdef6",
			"agdef7",
			"agdef8",
			"agdef9",
			"agdef10",
			"agdef11",
			"exchange_rate ",
			"buyccurrencyid",
			"sqg",
			"vbilllcode",
			"signinstructions",
			"dlxybz",
			"hblob1",
			"hblob2",
			"hblob3",
			"hblob4",
			"hblob5",
			/******************** 表体 ******************************************/
			// 王梓懿增加可以修订的数据
			"bidding_no", "project_name", "project_content",
			"supplier_requirements", "sumplannum", "sumnum", "typeplan",
			"typebuy", "ratereply", "bid_evaluation", "combination_standard",
			"procurementplan", "num_bj", "swq_bj", "offer_type",
			"qualification_way", "payment", "business_types", "procurement",
			"estimate", "delivery_term", "requirements", "supplier_code",
			"supplier", "no_delegate", "swqq_delegate", "ccategoryid",
			"projectexecutor", "no_pasdoc", "customer_no", "customer_name",
			"plan_num", "request_date", "host_name", "material", "rated_life",
			"manufacturer", "plan_pricea", "plan_priceb", "plansum_pricea",
			"plansum_priceb", "factory_plan", "factory_code", "factory_name",
			"plan", "application_no", "application_line", "number_code",
			"tally", "plan_time", "freight", "added_tax", "exchangeb_rate",
			"currency", "unit_leaders", "unit_dales", "unit_charge", "epein",
			"slysfs", "xlysfs", "ysfs", "htqdsj", "bjwlmc", "materialnamex",
			"chand", "cgjg", "csjhq", "jiaohuodate", "thicknessmm",
			"thicknessinch", "widthmm", "widthinch", "lengthmm", "lengthinch",
			"specificationmm", "specificationinch", "thicknesstolerance",
			"widthtolerance", "lengthtolerance", "sizekg", "unitweight",
			"minunitweight", "maxunitweight", "nmorerate", "nlessrate", "pcs",
			"tolerancepcs", "promethod", "agdef1", "agdef2", "agdef3",
			"agdef4", "agdef5", "agdef6", "agdef7", "agdef8", "agdef9",
			"agdef10", "agdef11", "agproductstandard", "agtexture", "mrid",
			"mrlineid", "req_group", "unit_pric", "code_facty", "ggxhhth",
			"cgjshj", "cgjgbhs", "cgse",

	};

	// 需回写表头字段
	public static final String[] HEADREWRITEMKEY = new String[] {
			/** ---------- 表头 ---------- */
			SaleOrderHVO.VREVISEREASON,
			SaleOrderHVO.VNOTE,
			SaleOrderHVO.TREVISETIME,
			/** -------表头自定义项------ **/
			SaleOrderHVO.VDEF1,
			SaleOrderHVO.VDEF2,
			SaleOrderHVO.VDEF3,
			SaleOrderHVO.VDEF4,
			SaleOrderHVO.VDEF5,
			SaleOrderHVO.VDEF6,
			SaleOrderHVO.VDEF7,
			SaleOrderHVO.VDEF8,
			SaleOrderHVO.VDEF9,
			SaleOrderHVO.VDEF10,
			SaleOrderHVO.VDEF11,
			SaleOrderHVO.VDEF12,
			SaleOrderHVO.VDEF13,
			SaleOrderHVO.VDEF14,
			SaleOrderHVO.VDEF15,
			SaleOrderHVO.VDEF16,
			SaleOrderHVO.VDEF17,
			SaleOrderHVO.VDEF18,
			SaleOrderHVO.VDEF19,
			SaleOrderHVO.VDEF20,
			/** -------修订信息------- */
			SaleOrderHVO.CREVISERID,
			SaleOrderHVO.APPROVER,
			SaleOrderHVO.IVERSION,
			// 王梓懿 新增元数据
			"jzrq", "jhq", "zcjhq", "dlfl",
			"djqz",
			"cmarbaseclassid",
			"chvendorid",
			"agdef1",
			"agdef2",
			"agdef3",
			"agdef4",
			"agdef5",
			"agdef6",
			"agdef7",
			"agdef8",
			"agdef9",
			"agdef10",
			"agdef11",
			"exchange_rate ",
			"buyccurrencyid",
			"sqg",
			"vbilllcode",
			"signinstructions",
			"dlxybz",
			"hblob1",
			"hblob2",
			"hblob3",
			"hblob4",
			"hblob5",
			// 王梓懿 2018-04-02表头字段全可以回写
			"approver", "badvfeeflag", "barsettleflag", "bcooptopoflag",
			"bcostsettleflag", "bfreecustflag", "billmaker", "binvoicendflag",
			"boffsetflag", "boutendflag", "bpocooptomeflag", "bpreceiveflag",
			"bsendendflag", "carsubtypeid", "cbalancetypeid", "cbillsrcid",
			"cbiztypeid", "cchanneltypeid", "ccustbankaccid", "ccustbankid",
			"ccustomerid", "cdeptid", "cdeptvid", "cemployeeid", "cfreecustid",
			"chistrantypeid", "chreceiveaddid", "chreceivecustid",
			"cinvoicecustid", "corderhistoryid", "corigcurrencyid",
			"cpaytermid", "creationtime", "creator", "creviserid",
			"csaleorderid", "ctradewordid", "ctransporttypeid", "ctrantypeid",
			"dbilldate", "dmakedate", "fpfstatusflag", "fstatusflag",
			"iprintcount", "iversion", "modifiedtime", "modifier",
			"ndiscountrate", "nlrgtotalorigmny", "npreceivemny",
			"npreceivequota", "npreceiverate", "nreceivedmny", "ntotalnum",
			"ntotalorigmny", "ntotalorigsubmny", "ntotalpiece", "ntotalvolume",
			"ntotalweight", "pk_group", "pk_org", "pk_org_v", "taudittime",
			"trevisetime", "vbillcode", "vbillsrctype", "vcooppohcode",
			"vcreditnum", "vdef1", "vdef10", "vdef11", "vdef12", "vdef13",
			"vdef14", "vdef15", "vdef16", "vdef17", "vdef18", "vdef19",
			"vdef2", "vdef20", "vdef3", "vdef4", "vdef5", "vdef6", "vdef7",
			"vdef8", "vdef9", "vhistrantypecode", "vnote", "vrevisereason",
			"vtrantypecode", };

	// 表体回写
	public static final String[] BODYREWRITEMKEY = new String[] {

			/** ---------- 表体 ---------- */
			// 计量单位
			// 单位、报价单位
			SaleOrderBVO.CASTUNITID,
			SaleOrderBVO.CQTUNITID,
			// 换算率 报价单位换算率
			SaleOrderBVO.VCHANGERATE,
			SaleOrderBVO.VQTUNITRATE,
			// 汇率
			// 折本汇率、集团本币汇率、全局本币汇率
			SaleOrderBVO.NEXCHANGERATE,
			SaleOrderBVO.NGROUPEXCHGRATE,
			SaleOrderBVO.NGLOBALTAXMNY,
			// 　计税金额
			SaleOrderBVO.NCALTAXMNY,
			// 税率
			SaleOrderBVO.NTAXRATE,
			// 折扣率
			SaleOrderBVO.NORIGDISCOUNT,

			// 件数、体积、重量
			SaleOrderBVO.NVOLUME,
			SaleOrderBVO.NWEIGHT,
			SaleOrderBVO.NPIECE,

			// 发货库存组织、物流组织、
			SaleOrderBVO.CSENDSTOCKORGID,
			SaleOrderBVO.CSENDSTOCKORGVID,
			SaleOrderBVO.CTRAFFICORGID,
			SaleOrderBVO.CTRAFFICORGVID,
			// 结算财务组织
			SaleOrderBVO.CSETTLEORGVID,
			SaleOrderBVO.CSETTLEORGID,
			// 发货利润中心、结算利润中心
			SaleOrderBVO.CSPROFITCENTERID,
			SaleOrderBVO.CSPROFITCENTERVID,
			SaleOrderBVO.CPROFITCENTERID,
			SaleOrderBVO.CPROFITCENTERVID,
			// 整单折扣、单品折扣
			SaleOrderBVO.NDISCOUNTRATE,
			SaleOrderBVO.NITEMDISCOUNTRATE,
			// 数量
			// 数量、主数量、报价数量
			SaleOrderBVO.NASTNUM,
			SaleOrderBVO.NNUM,
			SaleOrderBVO.NQTUNITNUM,

			// 单价
			// 无税单价、含税单价、无税净价、含税净价
			SaleOrderBVO.NQTORIGPRICE,
			SaleOrderBVO.NQTORIGTAXPRICE,
			SaleOrderBVO.NQTORIGNETPRICE,
			SaleOrderBVO.NQTORIGTAXNETPRC,

			// 主单位含税单价、主单位无税单价、主单位含税净价、主单位无税净价
			SaleOrderBVO.NORIGTAXPRICE,
			SaleOrderBVO.NORIGPRICE,
			SaleOrderBVO.NORIGTAXNETPRICE,
			SaleOrderBVO.NORIGNETPRICE,
			// 本币无税单价、本币含税单价、本币无税净价、本币含税净价、
			SaleOrderBVO.NQTPRICE,
			SaleOrderBVO.NQTTAXPRICE,
			SaleOrderBVO.NQTNETPRICE,
			SaleOrderBVO.NQTTAXNETPRICE,
			// 主单位本币含税单价、主单位本币无税单价、主单位本币含税净价、主单位本币无税净价、
			SaleOrderBVO.NTAXPRICE,
			SaleOrderBVO.NPRICE,
			SaleOrderBVO.NTAXNETPRICE,
			SaleOrderBVO.NNETPRICE,
			// 询价原币含税单价、询价原币无税单价
			SaleOrderBVO.NASKQTORIGTAXPRC,
			SaleOrderBVO.NASKQTORIGPRICE,
			// 金额
			// 无税金额、价税合计、税额、折扣额
			SaleOrderBVO.NORIGMNY,
			SaleOrderBVO.NORIGTAXMNY,
			SaleOrderBVO.NTAX,
			SaleOrderBVO.NORIGDISCOUNT,
			// 本币无税金额、本币价税合计、本币税额、本币折扣额、
			SaleOrderBVO.NMNY,
			SaleOrderBVO.NTAXMNY,
			SaleOrderBVO.NTAX,
			SaleOrderBVO.NDISCOUNT,
			// 集团本币无税金额、集团本币价税合计、全局本币无税金额、全局本币价税合计
			SaleOrderBVO.NGROUPMNY,
			SaleOrderBVO.NGROUPTAXMNY,
			SaleOrderBVO.NGLOBALMNY,
			SaleOrderBVO.NGLOBALTAXMNY,
			// 价格相关：价格组成、价格项等
			SaleOrderBVO.CPRICEFORMID,
			SaleOrderBVO.CPRICEITEMID,
			SaleOrderBVO.CPRICEITEMTABLEID,
			SaleOrderBVO.CPRICEPOLICYID,
			// 国贸新增的业务数据 王梓懿 2018-03-29
			"bidding_no", "project_name", "project_content",
			"supplier_requirements", "sumplannum", "sumnum", "typeplan",
			"typebuy", "ratereply", "bid_evaluation", "combination_standard",
			"procurementplan", "num_bj", "swq_bj", "offer_type",
			"qualification_way", "payment", "business_types", "procurement",
			"estimate", "delivery_term", "requirements", "supplier_code",
			"supplier", "no_delegate", "swqq_delegate", "ccategoryid",
			"projectexecutor", "no_pasdoc", "customer_no", "customer_name",
			"plan_num", "request_date", "host_name", "material", "rated_life",
			"manufacturer", "plan_pricea", "plan_priceb", "plansum_pricea",
			"plansum_priceb", "factory_plan", "factory_code", "factory_name",
			"plan", "application_no",
			"application_line",
			"number_code",
			"tally",
			"plan_time",
			"freight",
			"added_tax",
			"exchangeb_rate",
			"currency",
			"unit_leaders",
			"unit_dales",
			"unit_charge",
			"epein",
			"slysfs",
			"xlysfs",
			"ysfs",
			"htqdsj",
			"bjwlmc",
			"materialnamex",
			"chand",
			"cgjg",
			"csjhq",
			"jiaohuodate",
			"thicknessmm",
			"thicknessinch",
			"widthmm",
			"widthinch",
			"lengthmm",
			"lengthinch",
			"specificationmm",
			"specificationinch",
			"thicknesstolerance",
			"widthtolerance",
			"lengthtolerance",
			"sizekg",
			"unitweight",
			"minunitweight",
			"maxunitweight",
			"nmorerate",
			"nlessrate",
			"pcs",
			"tolerancepcs",
			"promethod",
			"agdef1",
			"agdef2",
			"agdef3",
			"agdef4",
			"agdef5",
			"agdef6",
			"agdef7",
			"agdef8",
			"agdef9",
			"agdef10",
			"agdef11",
			"agproductstandard",
			"agtexture",
			"mrid",
			"mrlineid",
			"req_group",
			"unit_pric",
			"code_facty",
			"ggxhhth",
			"cgjshj",
			"cgjgbhs",
			"cgse",
			// 2018-04-02所有表体全部回写
			"barrangedflag", "bbarsettleflag", "bbcostsettleflag", "bbindflag",
			"bbinvoicendflag", "bboutendflag", "bbsendendflag",
			"bbsettleendflag", "bdiscountflag", "bjczxsflag", "blaborflag",
			"blargessflag", "blrgcashflag", "bprerowcloseflag",
			"btriatradeflag", "carorgid", "carorgvid", "carrangepersonid",
			"castunitid", "cbindsrcid", "cbuylargessactid", "cbuylargessid",
			"cbuypromottypeid", "cctmanagebid", "cctmanageid", "ccurrencyid",
			"ccustmaterialid", "cexchangesrcretid", "cfactoryid", "cfirstbid",
			"cfirstid", "clargesssrcid", "cmaterialid", "cmaterialvid",
			"cmffileid", "corderhistorybid", "corigareaid", "corigcountryid",
			"cprcpromottypeid", "cpriceformid", "cpriceitemid",
			"cpriceitemtableid", "cpricepolicyid", "cpricepromtactid",
			"cprodlineid", "cproductorid", "cprofitcenterid",
			"cprofitcentervid", "cprojectid", "cpromotpriceid", "cqtunitid",
			"cqualitylevelid", "crececountryid", "creceiveadddocid",
			"creceiveaddrid", "creceiveareaid", "creceivecustid",
			"cretpolicyid", "cretreasonid", "crowno", "csaleorderbid",
			"csaleorderid", "csendcountryid", "csendstockorgid",
			"csendstockorgvid", "csendstordocid", "csettleorgid",
			"csettleorgvid", "csprofitcenterid", "csprofitcentervid",
			"csrcbid", "csrcid", "ctaxcodeid", "ctaxcountryid",
			"ctrafficorgid", "ctrafficorgvid", "cunitid", "cvendorid",
			"dbilldate", "dreceivedate", "dsenddate", "fbuysellflag",
			"flargesstypeflag", "fretexchange", "frowstatus", "ftaxtypeflag",
			"naccprice", "narrangemonum", "narrangepoappnum", "narrangeponum",
			"narrangescornum", "narrangetoappnum", "narrangetoornum",
			"naskqtorignetprice", "naskqtorigprice", "naskqtorigtaxprc",
			"naskqtorigtxntprc", "nastnum", "nbforigsubmny", "ncaltaxmny",
			"ndiscount", "ndiscountrate", "nexchangerate", "nglobalexchgrate",
			"nglobalmny", "nglobaltaxmny", "ngroupexchgrate", "ngroupmny",
			"ngrouptaxmny", "nitemdiscountrate", "nlargessmny",
			"nlargesstaxmny", "nmffileprice", "nmny", "nnetprice", "nnum",
			"norigdiscount", "norigmny", "norignetprice", "norigprice",
			"norigsubmny", "norigtaxmny", "norigtaxnetprice", "norigtaxprice",
			"npiece", "nprice", "nqtnetprice", "nqtorignetprice",
			"nqtorigprice", "nqtorigtaxnetprc", "nqtorigtaxprice", "nqtprice",
			"nqttaxnetprice", "nqttaxprice", "nqtunitnum", "ntax", "ntaxmny",
			"ntaxnetprice", "ntaxprice", "ntaxrate", "ntotalarmny",
			"ntotalarnum", "ntotalcostnum", "ntotalestarmny", "ntotalestarnum",
			"ntotalinvoicenum", "ntotalnotoutnum", "ntotaloutnum",
			"ntotalreturnnum", "ntotalrushnum", "ntotalsendnum",
			"ntotalsignnum", "ntotaltradenum", "ntranslossnum", "nvolume",
			"nweight", "pk_batchcode", "pk_group", "pk_org", "srcbts",
			"srcorgid", "srcts", "tlastarrangetime", "vbatchcode", "vbdef1",
			"vbdef10", "vbdef11", "vbdef12", "vbdef13", "vbdef14", "vbdef15",
			"vbdef16", "vbdef17", "vbdef18", "vbdef19", "vbdef2", "vbdef20",
			"vbdef3", "vbdef4", "vbdef5", "vbdef6", "vbdef7", "vbdef8",
			"vbdef9", "vbrevisereason", "vchangerate", "vclosereason",
			"vctcode", "vcttype", "vcustombillcode", "vfirstcode",
			"vfirstrowno", "vfirsttrantype", "vfirsttype", "vfree1", "vfree10",
			"vfree2", "vfree3", "vfree4", "vfree5", "vfree6", "vfree7",
			"vfree8", "vfree9", "vqtunitrate", "vreturnmode", "vrownote",
			"vsrccode", "vsrcrowno", "vsrctrantype", "vsrctype",

	};

	// 累计下游数量
	public static final String[] TOTALNUMKEY = new String[] {
			// 累计发货数量、累计开票数量
			SaleOrderHistoryBVO.NTOTALSENDNUM,
			SaleOrderHistoryBVO.NTOTALINVOICENUM,
			// 累计出库数量、 累计应发未出库数量
			SaleOrderHistoryBVO.NTOTALOUTNUM,
			SaleOrderHistoryBVO.NTOTALNOTOUTNUM,
			// 累计签收数量、 累计途损数量
			SaleOrderHistoryBVO.NTOTALSIGNNUM,
			SaleOrderHistoryBVO.NTRANSLOSSNUM,
			// 累计出库对冲数量、累计暂估应收数量
			SaleOrderHistoryBVO.NTOTALRUSHNUM,
			SaleOrderHistoryBVO.NTOTALESTARNUM,
			// 累计确认应收数量、累计成本结算数量
			SaleOrderHistoryBVO.NTOTALARNUM,
			SaleOrderHistoryBVO.NTOTALCOSTNUM,
			// 累计暂估应收金额、 累计确认应收金额
			SaleOrderHistoryBVO.NTOTALESTARMNY,
			SaleOrderHistoryBVO.NTOTALARMNY,
			// 累计安排委外订单数量、累计安排请购单数量
			SaleOrderHistoryBVO.NARRANGESCORNUM,
			SaleOrderHistoryBVO.NARRANGEPOAPPNUM,
			// 累计安排调拨订单数量、累计安排调入申请数量
			SaleOrderHistoryBVO.NARRANGETOORNUM,
			SaleOrderHistoryBVO.NARRANGETOAPPNUM,
			// 累计安排生产订单数量、累计安排采购订单数量
			SaleOrderHistoryBVO.NARRANGEMONUM,
			SaleOrderHistoryBVO.NARRANGEPONUM,
			// 累计发出商品、 累计退货数量
			SaleOrderHistoryBVO.NTOTALRETURNNUM,
			SaleOrderHistoryBVO.NTOTALTRADENUM };

	// 单价字段
	// 累计下游数量
	public static final String[] PRICE = new String[] {

			// 无税单价、含税单价、无税净价、含税净价
			SaleOrderBVO.NQTORIGPRICE,
			SaleOrderBVO.NQTORIGTAXPRICE,
			SaleOrderBVO.NQTORIGNETPRICE,
			SaleOrderBVO.NQTORIGTAXNETPRC,
			// 主单位含税单价、主单位无税单价、主单位含税净价、主单位无税净价
			SaleOrderBVO.NORIGTAXPRICE, SaleOrderBVO.NORIGPRICE,
			SaleOrderBVO.NORIGTAXNETPRICE,
			SaleOrderBVO.NORIGNETPRICE,
			// 本币无税单价、本币含税单价、本币无税净价、本币含税净价、
			SaleOrderBVO.NQTPRICE, SaleOrderBVO.NQTTAXPRICE,
			SaleOrderBVO.NQTNETPRICE, SaleOrderBVO.NQTTAXNETPRICE,
			// 主单位本币含税单价、主单位本币无税单价、主单位本币含税净价、主单位本币无税净价、
			SaleOrderBVO.NTAXPRICE, SaleOrderBVO.NPRICE,
			SaleOrderBVO.NTAXNETPRICE, SaleOrderBVO.NNETPRICE,
			// 询价原币含税单价、询价原币无税单价
			SaleOrderBVO.NASKQTORIGTAXPRC, SaleOrderBVO.NASKQTORIGPRICE };

}
