package nc.vo.so.m30.entity;

import nc.vo.pub.IVOMeta;
import nc.vo.pub.SuperVO;
import nc.vo.pub.lang.UFBoolean;
import nc.vo.pub.lang.UFDate;
import nc.vo.pub.lang.UFDateTime;
import nc.vo.pub.lang.UFDouble;
import nc.vo.pubapp.pattern.model.meta.entity.vo.VOMetaFactory;

/**
 * 销售订单主表VO
 * 
 * @since 6.0
 * @version 2011-6-10 下午01:50:34
 * @author fengjb
 */

public class SaleOrderHVO extends SuperVO {

	/**
	 * 赠品兑付类型
	 */
	public static final String CARSUBTYPEID = "carsubtypeid";

	/**
	 * 设置赠品兑付类型
	 * 
	 * @param carsubtypeid
	 * 
	 */
	public void setCarsubtypeid(String carsubtypeid) {
		this.setAttributeValue(SaleOrderHVO.CARSUBTYPEID, carsubtypeid);
	}

	/**
	 * 获取赠品兑付类型
	 * 
	 * @return 赠品兑付类型
	 */
	public String getCarsubtypeid() {
		return (String) this.getAttributeValue(SaleOrderHVO.CARSUBTYPEID);
	}

	/**
	 * 审批人
	 */
	public static final String APPROVER = "approver";

	/**
	 * 代垫运费
	 */
	public static final String BADVFEEFLAG = "badvfeeflag";

	/**
	 * 收入结算关闭
	 */
	public static final String BARSETTLEFLAG = "barsettleflag";

	/**
	 * 已协同生成采购订单
	 */
	public static final String BCOOPTOPOFLAG = "bcooptopoflag";

	/**
	 * 成本结算关闭
	 */
	public static final String BCOSTSETTLEFLAG = "bcostsettleflag";

	/**
	 * 是否散户
	 */
	public static final String BFREECUSTFLAG = "bfreecustflag";

	/**
	 * 制单人
	 */
	public static final String BILLMAKER = "billmaker";

	/**
	 * 开票关闭
	 */
	public static final String BINVOICENDFLAG = "binvoicendflag";

	/**
	 * 是否冲抵
	 */
	public static final String BOFFSETFLAG = "boffsetflag";

	/**
	 * 出库关闭
	 */
	public static final String BOUTENDFLAG = "boutendflag";

	/**
	 * 由采购订单协同生成
	 */
	public static final String BPOCOOPTOMEFLAG = "bpocooptomeflag";

	/**
	 * 收款限额控制预收
	 */
	public static final String BPRECEIVEFLAG = "bpreceiveflag";

	/**
	 * 发货关闭
	 */
	public static final String BSENDENDFLAG = "bsendendflag";

	/**
	 * 结算方式
	 */
	public static final String CBALANCETYPEID = "cbalancetypeid";

	/**
	 * 业务流程
	 */
	public static final String CBIZTYPEID = "cbiztypeid";

	/**
	 * 销售渠道类型
	 */
	public static final String CCHANNELTYPEID = "cchanneltypeid";

	/**
	 * 开户银行账户
	 */
	public static final String CCUSTBANKACCID = "ccustbankaccid";

	/**
	 * 开户银行
	 */
	public static final String CCUSTBANKID = "ccustbankid";

	/**
	 * 客户
	 */
	public static final String CCUSTOMERID = "ccustomerid";

	/**
	 * 部门最新版本
	 */
	public static final String CDEPTID = "cdeptid";

	/**
	 * 部门
	 */
	public static final String CDEPTVID = "cdeptvid";

	/**
	 * 业务员
	 */
	public static final String CEMPLOYEEID = "cemployeeid";

	/**
	 * 散户
	 */
	public static final String CFREECUSTID = "cfreecustid";

	/**
	 * 开票客户
	 */
	public static final String CINVOICECUSTID = "cinvoicecustid";

	/**
	 * 原币
	 */
	public static final String CORIGCURRENCYID = "corigcurrencyid";

	/**
	 * 收款协议
	 */
	public static final String CPAYTERMID = "cpaytermid";

	/**
	 * 创建时间
	 */
	public static final String CREATIONTIME = "creationtime";

	/**
	 * 创建人
	 */
	public static final String CREATOR = "creator";

	/**
	 * 修订人
	 */
	public static final String CREVISERID = "creviserid";

	/**
	 * 销售主表ID
	 */
	public static final String CSALEORDERID = "csaleorderid";

	/**
	 * 贸易术语（61）
	 */
	public static final String CTRADEWORDID = "ctradewordid";

	/**
	 * 运输方式
	 */
	public static final String CTRANSPORTTYPEID = "ctransporttypeid";

	/**
	 * 交易类型
	 */
	public static final String CTRANTYPEID = "ctrantypeid";

	/**
	 * 单据日期
	 */
	public static final String DBILLDATE = "dbilldate";

	/**
	 * 计算属性：下游目的单据主组织
	 */
	public static final String DEST_PK_ORG = "dest_pk_org";

	/**
	 * 制单日期
	 */
	public static final String DMAKEDATE = "dmakedate";

	/**
	 * dr
	 */
	public static final String DR = "dr";

	/**
	 * 审批流状态
	 */
	public static final String FPFSTATUSFLAG = "fpfstatusflag";

	/**
	 * 单据状态
	 */
	public static final String FSTATUSFLAG = "fstatusflag";

	/**
	 * 打印次数
	 */
	public static final String IPRINTCOUNT = "iprintcount";

	/**
	 * 修订版本号
	 */
	public static final String IVERSION = "iversion";

	/**
	 * 修改时间
	 */
	public static final String MODIFIEDTIME = "modifiedtime";

	/**
	 * 修改人
	 */
	public static final String MODIFIER = "modifier";

	/**
	 * 整单折扣
	 */
	public static final String NDISCOUNTRATE = "ndiscountrate";

	/**
	 * 实际预收款金额
	 */
	public static final String NPRECEIVEMNY = "npreceivemny";

	/**
	 * 订单收款限额
	 */
	public static final String NPRECEIVEQUOTA = "npreceivequota";

	/**
	 * 订单收款比例
	 */
	public static final String NPRECEIVERATE = "npreceiverate";

	/**
	 * 实际收款金额
	 */
	public static final String NRECEIVEDMNY = "nreceivedmny";

	/**
	 * 计算属性：本次收款金额
	 */
	public static final String NTHISRECEIVEMNY = "nthisreceivemny";

	/**
	 * ntotalmny
	 */
	public static final String NTOTALMNY = "ntotalmny";

	/**
	 * 合计数量
	 */
	public static final String NTOTALNUM = "ntotalnum";

	/**
	 * 金额合计(价税合计)
	 */
	public static final String NTOTALORIGMNY = "ntotalorigmny";

	/**
	 * 赠品的金额合计(赠品的价税合计)
	 */
	public static final String NLRGTOTALORIGMNY = "nlrgtotalorigmny";

	/**
	 * 费用冲抵金额
	 */
	public static final String NTOTALORIGSUBMNY = "ntotalorigsubmny";

	/**
	 * 总件数
	 */
	public static final String NTOTALPIECE = "ntotalpiece";

	/**
	 * 合计体积
	 */
	public static final String NTOTALVOLUME = "ntotalvolume";

	/**
	 * 合计重量
	 */
	public static final String NTOTALWEIGHT = "ntotalweight";

	/**
	 * 集团
	 */
	public static final String PK_GROUP = "pk_group";

	/**
	 * 销售组织
	 */
	public static final String PK_ORG = "pk_org";

	/**
	 * 销售组织版本
	 */
	public static final String PK_ORG_V = "pk_org_v";

	private static final long serialVersionUID = -567754544473734276L;

	/**
	 * 审批时间
	 */
	public static final String TAUDITTIME = "taudittime";

	/**
	 * 修订时间
	 */
	public static final String TREVISETIME = "trevisetime";

	/**
	 * 时间戳
	 */
	public static final String TS = "ts";

	/**
	 * 单据号
	 */
	public static final String VBILLCODE = "vbillcode";

	/**
	 * 对方订单号
	 */
	public static final String VCOOPPOHCODE = "vcooppohcode";

	/**
	 * 信用证号
	 */
	public static final String VCREDITNUM = "vcreditnum";

	/**
	 * 自定义项1
	 */
	public static final String VDEF1 = "vdef1";

	/**
	 * 自定义项10
	 */
	public static final String VDEF10 = "vdef10";

	/**
	 * 自定义项11
	 */
	public static final String VDEF11 = "vdef11";

	/**
	 * 自定义项12
	 */
	public static final String VDEF12 = "vdef12";

	/**
	 * 自定义项13
	 */
	public static final String VDEF13 = "vdef13";

	/**
	 * 自定义项14
	 */
	public static final String VDEF14 = "vdef14";

	/**
	 * 自定义项15
	 */
	public static final String VDEF15 = "vdef15";

	/**
	 * 自定义项16
	 */
	public static final String VDEF16 = "vdef16";

	/**
	 * 自定义项17
	 */
	public static final String VDEF17 = "vdef17";

	/**
	 * 自定义项18
	 */
	public static final String VDEF18 = "vdef18";

	/**
	 * 自定义项19
	 */
	public static final String VDEF19 = "vdef19";

	/**
	 * 自定义项2
	 */
	public static final String VDEF2 = "vdef2";

	/**
	 * 自定义项20
	 */
	public static final String VDEF20 = "vdef20";

	/**
	 * 自定义项3
	 */
	public static final String VDEF3 = "vdef3";

	/**
	 * 自定义项4
	 */
	public static final String VDEF4 = "vdef4";

	/**
	 * 自定义项5
	 */
	public static final String VDEF5 = "vdef5";

	/**
	 * 自定义项6
	 */
	public static final String VDEF6 = "vdef6";

	/**
	 * 自定义项7
	 */
	public static final String VDEF7 = "vdef7";

	/**
	 * 自定义项8
	 */
	public static final String VDEF8 = "vdef8";

	/**
	 * 自定义项9
	 */
	public static final String VDEF9 = "vdef9";

	/**
	 * 备注
	 */
	public static final String VNOTE = "vnote";

	/**
	 * 修订理由
	 */
	public static final String VREVISEREASON = "vrevisereason";

	/**
	 * 交易类型编码
	 */
	public static final String VTRANTYPECODE = "vtrantypecode";

	/**
	 * 整单来源单据类型
	 */
	public static final String VBILLSRCTYPE = "vbillsrctype";

	/**
	 * 整单来源单据ID
	 */
	public static final String CBILLSRCID = "cbillsrcid";

	/**
	 * 表头收货客户
	 */
	public static final String CHRECEIVECUSTID = "chreceivecustid";

	/**
	 * 表头客户收货地址
	 */
	public static final String CHRECEIVEADDID = "chreceiveaddid";
	/**
	 * add by lyw 2017-6-9 代理费率
	 */
	public static final String DLFL = "dlfl";
	/**
	 * add by lyw 2017-6-9 单价取整 枚举
	 */
	public static final String DJQZ = "djqz";

	/**
	 * add by lyw 2017-6-9 汇率
	 */
	public static final String EXCHANGERATE = "exchange_rate";

	public static UFDouble exchange_rate;
	/**
	 * add by lyw 2017-6-12 采购币种
	 */
	public static final String BUYCCURRENCYID = "buyccurrencyid";

	public static String  buyccurrencyid;
	/**
	 * add by lyw 2017-6-12 授权给
	 */
	public static final String SQG = "sqg";

	public static String sqg;
	

	/**
	 * 获取审批人
	 * 
	 * @return 审批人
	 */
	public String getApprover() {
		return (String) this.getAttributeValue(SaleOrderHVO.APPROVER);
	}

	/**
	 * 获取代垫运费
	 * 
	 * @return 代垫运费
	 */
	public UFBoolean getBadvfeeflag() {
		return (UFBoolean) this.getAttributeValue(SaleOrderHVO.BADVFEEFLAG);
	}

	/**
	 * add by wangzym 基准日期 2017-01-18
	 */
	public static UFDate jzrq;

	public static UFDate getJzrq() {
		return jzrq;
	}

	public static void setJzrq(UFDate jzrq) {
		SaleOrderHVO.jzrq = jzrq;
	}

	public static Integer getJhq() {
		return jhq;
	}

	public static void setJhq(Integer jhq) {
		SaleOrderHVO.jhq = jhq;
	}

	/********************* The End *********************/

	/**
	 * add by wangzym 基准日期 2017-01-18
	 */
	public static Integer jhq;

	public static UFDate zcjhq;
	public static UFDouble dlfl;
	public static Integer djqz;

	/**
	 * 获取收入结算关闭
	 * 
	 * @return 收入结算关闭
	 */
	public UFBoolean getBarsettleflag() {
		return (UFBoolean) this.getAttributeValue(SaleOrderHVO.BARSETTLEFLAG);
	}

	/**
	 * 获取已协同生成采购订单
	 * 
	 * @return 已协同生成采购订单
	 */
	public UFBoolean getBcooptopoflag() {
		return (UFBoolean) this.getAttributeValue(SaleOrderHVO.BCOOPTOPOFLAG);
	}

	/**
	 * 获取成本结算关闭
	 * 
	 * @return 成本结算关闭
	 */
	public UFBoolean getBcostsettleflag() {
		return (UFBoolean) this.getAttributeValue(SaleOrderHVO.BCOSTSETTLEFLAG);
	}

	/**
	 * 获取是否散户
	 * 
	 * @return 是否散户
	 */
	public UFBoolean getBfreecustflag() {
		return (UFBoolean) this.getAttributeValue(SaleOrderHVO.BFREECUSTFLAG);
	}

	/**
	 * 获取制单人
	 * 
	 * @return 制单人
	 */
	public String getBillmaker() {
		return (String) this.getAttributeValue(SaleOrderHVO.BILLMAKER);
	}

	/**
	 * 获取开票关闭
	 * 
	 * @return 开票关闭
	 */
	public UFBoolean getBinvoicendflag() {
		return (UFBoolean) this.getAttributeValue(SaleOrderHVO.BINVOICENDFLAG);
	}

	/**
	 * 获取是否冲抵
	 * 
	 * @return 是否冲抵
	 */
	public UFBoolean getBoffsetflag() {
		return (UFBoolean) this.getAttributeValue(SaleOrderHVO.BOFFSETFLAG);
	}

	/**
	 * 获取出库关闭
	 * 
	 * @return 出库关闭
	 */
	public UFBoolean getBoutendflag() {
		return (UFBoolean) this.getAttributeValue(SaleOrderHVO.BOUTENDFLAG);
	}

	/**
	 * 获取由采购订单协同生成
	 * 
	 * @return 由采购订单协同生成
	 */
	public UFBoolean getBpocooptomeflag() {
		return (UFBoolean) this.getAttributeValue(SaleOrderHVO.BPOCOOPTOMEFLAG);
	}

	/**
	 * 获取收款限额控制预收
	 * 
	 * @return 收款限额控制预收
	 */
	public UFBoolean getBpreceiveflag() {
		return (UFBoolean) this.getAttributeValue(SaleOrderHVO.BPRECEIVEFLAG);
	}

	/**
	 * 获取发货关闭
	 * 
	 * @return 发货关闭
	 */
	public UFBoolean getBsendendflag() {
		return (UFBoolean) this.getAttributeValue(SaleOrderHVO.BSENDENDFLAG);
	}

	/**
	 * 获取结算方式
	 * 
	 * @return 结算方式
	 */
	public String getCbalancetypeid() {
		return (String) this.getAttributeValue(SaleOrderHVO.CBALANCETYPEID);
	}

	/**
	 * 获取业务流程
	 * 
	 * @return 业务流程
	 */
	public String getCbiztypeid() {
		return (String) this.getAttributeValue(SaleOrderHVO.CBIZTYPEID);
	}

	/**
	 * 获取销售渠道类型
	 * 
	 * @return 销售渠道类型
	 */
	public String getCchanneltypeid() {
		return (String) this.getAttributeValue(SaleOrderHVO.CCHANNELTYPEID);
	}

	/**
	 * 获取开户银行账户
	 * 
	 * @return 开户银行账户
	 */
	public String getCcustbankaccid() {
		return (String) this.getAttributeValue(SaleOrderHVO.CCUSTBANKACCID);
	}

	/**
	 * 获取开户银行
	 * 
	 * @return 开户银行
	 */
	public String getCcustbankid() {
		return (String) this.getAttributeValue(SaleOrderHVO.CCUSTBANKID);
	}

	/**
	 * 获取客户
	 * 
	 * @return 客户
	 */
	public String getCcustomerid() {
		return (String) this.getAttributeValue(SaleOrderHVO.CCUSTOMERID);
	}

	/**
	 * 获取部门最新版本
	 * 
	 * @return 部门最新版本
	 */
	public String getCdeptid() {
		return (String) this.getAttributeValue(SaleOrderHVO.CDEPTID);
	}

	/**
	 * 获取部门
	 * 
	 * @return 部门
	 */
	public String getCdeptvid() {
		return (String) this.getAttributeValue(SaleOrderHVO.CDEPTVID);
	}

	/**
	 * 获取业务员
	 * 
	 * @return 业务员
	 */
	public String getCemployeeid() {
		return (String) this.getAttributeValue(SaleOrderHVO.CEMPLOYEEID);
	}

	/**
	 * 获取散户
	 * 
	 * @return 散户
	 */
	public String getCfreecustid() {
		return (String) this.getAttributeValue(SaleOrderHVO.CFREECUSTID);
	}

	/**
	 * 获取开票客户
	 * 
	 * @return 开票客户
	 */
	public String getCinvoicecustid() {
		return (String) this.getAttributeValue(SaleOrderHVO.CINVOICECUSTID);
	}

	/**
	 * 获取原币
	 * 
	 * @return 原币
	 */
	public String getCorigcurrencyid() {
		return (String) this.getAttributeValue(SaleOrderHVO.CORIGCURRENCYID);
	}

	/**
	 * 获取收款协议
	 * 
	 * @return 收款协议
	 */
	public String getCpaytermid() {
		return (String) this.getAttributeValue(SaleOrderHVO.CPAYTERMID);
	}

	/**
	 * 获取创建时间
	 * 
	 * @return 创建时间
	 */
	public UFDateTime getCreationtime() {
		return (UFDateTime) this.getAttributeValue(SaleOrderHVO.CREATIONTIME);
	}

	/**
	 * 获取创建人
	 * 
	 * @return 创建人
	 */
	public String getCreator() {
		return (String) this.getAttributeValue(SaleOrderHVO.CREATOR);
	}

	/**
	 * 获取修订人
	 * 
	 * @return 修订人
	 */
	public String getCreviserid() {
		return (String) this.getAttributeValue(SaleOrderHVO.CREVISERID);
	}

	/**
	 * 获取销售主表ID
	 * 
	 * @return 销售主表ID
	 */
	public String getCsaleorderid() {
		return (String) this.getAttributeValue(SaleOrderHVO.CSALEORDERID);
	}

	/**
	 * 获取贸易术语
	 * 
	 * @return 贸易术语
	 */
	public String getCtradewordid() {
		return (String) this.getAttributeValue(SaleOrderHVO.CTRADEWORDID);
	}

	/**
	 * 获取运输方式
	 * 
	 * @return 运输方式
	 */
	public String getCtransporttypeid() {
		return (String) this.getAttributeValue(SaleOrderHVO.CTRANSPORTTYPEID);
	}

	/**
	 * 获取交易类型
	 * 
	 * @return 交易类型
	 */
	public String getCtrantypeid() {
		return (String) this.getAttributeValue(SaleOrderHVO.CTRANTYPEID);
	}

	/**
	 * 获取单据日期
	 * 
	 * @return 单据日期
	 */
	public UFDate getDbilldate() {
		return (UFDate) this.getAttributeValue(SaleOrderHVO.DBILLDATE);
	}

	/**
	 * 获取下游目的单据主组织
	 * 
	 * @return dest_pk_org
	 */
	public String getDest_pk_org() {
		return (String) this.getAttributeValue(SaleOrderHVO.DEST_PK_ORG);
	}

	/**
	 * 获取制单日期
	 * 
	 * @return 制单日期
	 */
	public UFDate getDmakedate() {
		return (UFDate) this.getAttributeValue(SaleOrderHVO.DMAKEDATE);
	}

	public Integer getDr() {
		return (Integer) this.getAttributeValue(SaleOrderHVO.DR);
	}

	/**
	 * 获取审批流状态
	 * 
	 * @return 审批流状态
	 */
	public Integer getFpfstatusflag() {
		return (Integer) this.getAttributeValue(SaleOrderHVO.FPFSTATUSFLAG);
	}

	/**
	 * 获取单据状态
	 * 
	 * @return 单据状态
	 * @see BillStatus
	 */
	public Integer getFstatusflag() {
		return (Integer) this.getAttributeValue(SaleOrderHVO.FSTATUSFLAG);
	}

	/**
	 * 获取打印次数
	 * 
	 * @return 打印次数
	 */
	public Integer getIprintcount() {
		return (Integer) this.getAttributeValue(SaleOrderHVO.IPRINTCOUNT);
	}

	/**
	 * 获取修订版本号
	 * 
	 * @return 修订版本号
	 */
	public Integer getIversion() {
		return (Integer) this.getAttributeValue(SaleOrderHVO.IVERSION);
	}

	@Override
	public IVOMeta getMetaData() {
		IVOMeta meta = VOMetaFactory.getInstance().getVOMeta("so.so_saleorder");
		return meta;
	}

	/**
	 * 获取修改时间
	 * 
	 * @return 修改时间
	 */
	public UFDateTime getModifiedtime() {
		return (UFDateTime) this.getAttributeValue(SaleOrderHVO.MODIFIEDTIME);
	}

	/**
	 * 获取修改人
	 * 
	 * @return 修改人
	 */
	public String getModifier() {
		return (String) this.getAttributeValue(SaleOrderHVO.MODIFIER);
	}

	/**
	 * 获取整单折扣
	 * 
	 * @return 整单折扣
	 */
	public UFDouble getNdiscountrate() {
		return (UFDouble) this.getAttributeValue(SaleOrderHVO.NDISCOUNTRATE);
	}

	/**
	 * 获取实际预收款金额
	 * 
	 * @return 实际预收款金额
	 */
	public UFDouble getNpreceivemny() {
		return (UFDouble) this.getAttributeValue(SaleOrderHVO.NPRECEIVEMNY);
	}

	/**
	 * 获取订单收款限额
	 * 
	 * @return 订单收款限额
	 */
	public UFDouble getNpreceivequota() {
		return (UFDouble) this.getAttributeValue(SaleOrderHVO.NPRECEIVEQUOTA);
	}

	/**
	 * 获取订单收款比例
	 * 
	 * @return 订单收款比例
	 */
	public UFDouble getNpreceiverate() {
		return (UFDouble) this.getAttributeValue(SaleOrderHVO.NPRECEIVERATE);
	}

	/**
	 * 获取实际收款金额
	 * 
	 * @return 实际收款金额
	 */
	public UFDouble getNreceivedmny() {
		return (UFDouble) this.getAttributeValue(SaleOrderHVO.NRECEIVEDMNY);
	}

	/**
	 * 获取nthisreceivemny
	 * 
	 * @return nthisreceivemny
	 */
	public UFDouble getNthisreceivemny() {
		return (UFDouble) this.getAttributeValue(SaleOrderHVO.NTHISRECEIVEMNY);
	}

	/**
	 * 获取ntotalmny
	 * 
	 * @return ntotalmny
	 */
	public UFDouble getNtotalmny() {
		return (UFDouble) this.getAttributeValue(SaleOrderHVO.NTOTALMNY);
	}

	/**
	 * 获取合计数量
	 * 
	 * @return 合计数量
	 */
	public UFDouble getNtotalnum() {
		return (UFDouble) this.getAttributeValue(SaleOrderHVO.NTOTALNUM);
	}

	/**
	 * 获取金额合计
	 * 
	 * @return 金额合计
	 */
	public UFDouble getNtotalorigmny() {
		return (UFDouble) this.getAttributeValue(SaleOrderHVO.NTOTALORIGMNY);
	}

	/**
	 * 获取赠品金额合计
	 * 
	 * @return 金额合计
	 */
	public UFDouble getNlrgtotalorigmny() {
		return (UFDouble) this.getAttributeValue(SaleOrderHVO.NLRGTOTALORIGMNY);
	}

	/**
	 * 获取费用冲抵金额
	 * 
	 * @return 费用冲抵金额
	 */
	public UFDouble getNtotalorigsubmny() {
		return (UFDouble) this.getAttributeValue(SaleOrderHVO.NTOTALORIGSUBMNY);
	}

	/**
	 * 获取总件数
	 * 
	 * @return 总件数
	 */
	public UFDouble getNtotalpiece() {
		return (UFDouble) this.getAttributeValue(SaleOrderHVO.NTOTALPIECE);
	}

	/**
	 * 获取合计体积
	 * 
	 * @return 合计体积
	 */
	public UFDouble getNtotalvolume() {
		return (UFDouble) this.getAttributeValue(SaleOrderHVO.NTOTALVOLUME);
	}

	/**
	 * 获取合计重量
	 * 
	 * @return 合计重量
	 */
	public UFDouble getNtotalweight() {
		return (UFDouble) this.getAttributeValue(SaleOrderHVO.NTOTALWEIGHT);
	}

	/**
	 * 获取集团
	 * 
	 * @return 集团
	 */
	public String getPk_group() {
		return (String) this.getAttributeValue(SaleOrderHVO.PK_GROUP);
	}

	/**
	 * 获取销售组织
	 * 
	 * @return 销售组织
	 */
	public String getPk_org() {
		return (String) this.getAttributeValue(SaleOrderHVO.PK_ORG);
	}

	/**
	 * 获取销售组织版本
	 * 
	 * @return 销售组织版本
	 */
	public String getPk_org_v() {
		return (String) this.getAttributeValue(SaleOrderHVO.PK_ORG_V);
	}

	/**
	 * 获取审批时间
	 * 
	 * @return 审批时间
	 */
	public UFDate getTaudittime() {
		return (UFDate) this.getAttributeValue(SaleOrderHVO.TAUDITTIME);
	}

	/**
	 * 获取修订时间
	 * 
	 * @return 修订时间
	 */
	public UFDate getTrevisetime() {
		return (UFDate) this.getAttributeValue(SaleOrderHVO.TREVISETIME);
	}

	/**
	 * 获取时间戳
	 * 
	 * @return 时间戳
	 */
	public UFDateTime getTs() {
		return (UFDateTime) this.getAttributeValue(SaleOrderHVO.TS);
	}

	/**
	 * 获取单据号
	 * 
	 * @return 单据号
	 */
	public String getVbillcode() {
		return (String) this.getAttributeValue(SaleOrderHVO.VBILLCODE);
	}

	/**
	 * 获取对方订单号
	 * 
	 * @return 对方订单号
	 */
	public String getVcooppohcode() {
		return (String) this.getAttributeValue(SaleOrderHVO.VCOOPPOHCODE);
	}

	/**
	 * 获取信用证号
	 * 
	 * @return 信用证号
	 */
	public String getVcreditnum() {
		return (String) this.getAttributeValue(SaleOrderHVO.VCREDITNUM);
	}

	/**
	 * 获取自定义项1
	 * 
	 * @return 自定义项1
	 */
	public String getVdef1() {
		return (String) this.getAttributeValue(SaleOrderHVO.VDEF1);
	}

	/**
	 * 获取自定义项10
	 * 
	 * @return 自定义项10
	 */
	public String getVdef10() {
		return (String) this.getAttributeValue(SaleOrderHVO.VDEF10);
	}

	/**
	 * 获取自定义项11
	 * 
	 * @return 自定义项11
	 */
	public String getVdef11() {
		return (String) this.getAttributeValue(SaleOrderHVO.VDEF11);
	}

	/**
	 * 获取自定义项12
	 * 
	 * @return 自定义项12
	 */
	public String getVdef12() {
		return (String) this.getAttributeValue(SaleOrderHVO.VDEF12);
	}

	/**
	 * 获取自定义项13
	 * 
	 * @return 自定义项13
	 */
	public String getVdef13() {
		return (String) this.getAttributeValue(SaleOrderHVO.VDEF13);
	}

	/**
	 * 获取自定义项14
	 * 
	 * @return 自定义项14
	 */
	public String getVdef14() {
		return (String) this.getAttributeValue(SaleOrderHVO.VDEF14);
	}

	/**
	 * 获取自定义项15
	 * 
	 * @return 自定义项15
	 */
	public String getVdef15() {
		return (String) this.getAttributeValue(SaleOrderHVO.VDEF15);
	}

	/**
	 * 获取自定义项16
	 * 
	 * @return 自定义项16
	 */
	public String getVdef16() {
		return (String) this.getAttributeValue(SaleOrderHVO.VDEF16);
	}

	/**
	 * 获取自定义项17
	 * 
	 * @return 自定义项17
	 */
	public String getVdef17() {
		return (String) this.getAttributeValue(SaleOrderHVO.VDEF17);
	}

	/**
	 * 获取自定义项18
	 * 
	 * @return 自定义项18
	 */
	public String getVdef18() {
		return (String) this.getAttributeValue(SaleOrderHVO.VDEF18);
	}

	/**
	 * 获取自定义项19
	 * 
	 * @return 自定义项19
	 */
	public String getVdef19() {
		return (String) this.getAttributeValue(SaleOrderHVO.VDEF19);
	}

	/**
	 * 获取自定义项2
	 * 
	 * @return 自定义项2
	 */
	public String getVdef2() {
		return (String) this.getAttributeValue(SaleOrderHVO.VDEF2);
	}

	/**
	 * 获取自定义项20
	 * 
	 * @return 自定义项20
	 */
	public String getVdef20() {
		return (String) this.getAttributeValue(SaleOrderHVO.VDEF20);
	}

	/**
	 * 获取自定义项3
	 * 
	 * @return 自定义项3
	 */
	public String getVdef3() {
		return (String) this.getAttributeValue(SaleOrderHVO.VDEF3);
	}

	/**
	 * 获取自定义项4
	 * 
	 * @return 自定义项4
	 */
	public String getVdef4() {
		return (String) this.getAttributeValue(SaleOrderHVO.VDEF4);
	}

	/**
	 * 获取自定义项5
	 * 
	 * @return 自定义项5
	 */
	public String getVdef5() {
		return (String) this.getAttributeValue(SaleOrderHVO.VDEF5);
	}

	/**
	 * 获取自定义项6
	 * 
	 * @return 自定义项6
	 */
	public String getVdef6() {
		return (String) this.getAttributeValue(SaleOrderHVO.VDEF6);
	}

	/**
	 * 获取自定义项7
	 * 
	 * @return 自定义项7
	 */
	public String getVdef7() {
		return (String) this.getAttributeValue(SaleOrderHVO.VDEF7);
	}

	/**
	 * 获取自定义项8
	 * 
	 * @return 自定义项8
	 */
	public String getVdef8() {
		return (String) this.getAttributeValue(SaleOrderHVO.VDEF8);
	}

	/**
	 * 获取自定义项9
	 * 
	 * @return 自定义项9
	 */
	public String getVdef9() {
		return (String) this.getAttributeValue(SaleOrderHVO.VDEF9);
	}

	/**
	 * 获取备注
	 * 
	 * @return 备注
	 */
	public String getVnote() {
		return (String) this.getAttributeValue(SaleOrderHVO.VNOTE);
	}

	/**
	 * 获取修订理由
	 * 
	 * @return 修订理由
	 */
	public String getVrevisereason() {
		return (String) this.getAttributeValue(SaleOrderHVO.VREVISEREASON);
	}

	/**
	 * 获取交易类型编码
	 * 
	 * @return 交易类型编码
	 */
	public String getVtrantypecode() {
		return (String) this.getAttributeValue(SaleOrderHVO.VTRANTYPECODE);
	}

	/**
	 * 获取表头收货客户
	 * 
	 * @return 表头收货客户
	 */
	public String getChreceivecustid() {
		return (String) this.getAttributeValue(SaleOrderHVO.CHRECEIVECUSTID);
	}

	/**
	 * 获取表头收货地址
	 * 
	 * @return 收货地址
	 */
	public String getChreceiveaddid() {
		return (String) this.getAttributeValue(SaleOrderHVO.CHRECEIVEADDID);
	}

	/**
	 * 获取整单来源单据类型
	 * 
	 * @return 整单来源单据类型
	 */
	public String getVbillsrctype() {
		return (String) this.getAttributeValue(SaleOrderHVO.VBILLSRCTYPE);
	}

	/**
	 * 获取整单来源单据ID
	 * 
	 * @return 整单来源单据ID
	 */
	public String getCbillsrcid() {
		return (String) this.getAttributeValue(SaleOrderHVO.CBILLSRCID);
	}

	/**
	 * 设置审批人
	 * 
	 * @param approver
	 *            审批人
	 */
	public void setApprover(String approver) {
		this.setAttributeValue(SaleOrderHVO.APPROVER, approver);
	}

	/**
	 * 设置代垫运费
	 * 
	 * @param badvfeeflag
	 *            代垫运费
	 */
	public void setBadvfeeflag(UFBoolean badvfeeflag) {
		this.setAttributeValue(SaleOrderHVO.BADVFEEFLAG, badvfeeflag);
	}

	/**
	 * 设置收入结算关闭
	 * 
	 * @param barsettleflag
	 *            收入结算关闭
	 */
	public void setBarsettleflag(UFBoolean barsettleflag) {
		this.setAttributeValue(SaleOrderHVO.BARSETTLEFLAG, barsettleflag);
	}

	/**
	 * 设置已协同生成采购订单
	 * 
	 * @param bcooptopoflag
	 *            已协同生成采购订单
	 */
	public void setBcooptopoflag(UFBoolean bcooptopoflag) {
		this.setAttributeValue(SaleOrderHVO.BCOOPTOPOFLAG, bcooptopoflag);
	}

	/**
	 * 设置成本结算关闭
	 * 
	 * @param bcostsettleflag
	 *            成本结算关闭
	 */
	public void setBcostsettleflag(UFBoolean bcostsettleflag) {
		this.setAttributeValue(SaleOrderHVO.BCOSTSETTLEFLAG, bcostsettleflag);
	}

	/**
	 * 设置是否散户
	 * 
	 * @param bfreecustflag
	 *            是否散户
	 */
	public void setBfreecustflag(UFBoolean bfreecustflag) {
		this.setAttributeValue(SaleOrderHVO.BFREECUSTFLAG, bfreecustflag);
	}

	/**
	 * 设置制单人
	 * 
	 * @param billmaker
	 *            制单人
	 */
	public void setBillmaker(String billmaker) {
		this.setAttributeValue(SaleOrderHVO.BILLMAKER, billmaker);
	}

	/**
	 * 设置开票关闭
	 * 
	 * @param binvoicendflag
	 *            开票关闭
	 */
	public void setBinvoicendflag(UFBoolean binvoicendflag) {
		this.setAttributeValue(SaleOrderHVO.BINVOICENDFLAG, binvoicendflag);
	}

	/**
	 * 设置是否冲抵
	 * 
	 * @param boffsetflag
	 *            是否冲抵
	 */
	public void setBoffsetflag(UFBoolean boffsetflag) {
		this.setAttributeValue(SaleOrderHVO.BOFFSETFLAG, boffsetflag);
	}

	/**
	 * 设置出库关闭
	 * 
	 * @param boutendflag
	 *            出库关闭
	 */
	public void setBoutendflag(UFBoolean boutendflag) {
		this.setAttributeValue(SaleOrderHVO.BOUTENDFLAG, boutendflag);
	}

	/**
	 * 设置由采购订单协同生成
	 * 
	 * @param bpocooptomeflag
	 *            由采购订单协同生成
	 */
	public void setBpocooptomeflag(UFBoolean bpocooptomeflag) {
		this.setAttributeValue(SaleOrderHVO.BPOCOOPTOMEFLAG, bpocooptomeflag);
	}

	/**
	 * 设置收款限额控制预收
	 * 
	 * @param bpreceiveflag
	 *            收款限额控制预收
	 */
	public void setBpreceiveflag(UFBoolean bpreceiveflag) {
		this.setAttributeValue(SaleOrderHVO.BPRECEIVEFLAG, bpreceiveflag);
	}

	/**
	 * 设置发货关闭
	 * 
	 * @param bsendendflag
	 *            发货关闭
	 */
	public void setBsendendflag(UFBoolean bsendendflag) {
		this.setAttributeValue(SaleOrderHVO.BSENDENDFLAG, bsendendflag);
	}

	/**
	 * 设置结算方式
	 * 
	 * @param cbalancetypeid
	 *            结算方式
	 */
	public void setCbalancetypeid(String cbalancetypeid) {
		this.setAttributeValue(SaleOrderHVO.CBALANCETYPEID, cbalancetypeid);
	}

	/**
	 * 设置业务流程
	 * 
	 * @param cbiztypeid
	 *            业务流程
	 */
	public void setCbiztypeid(String cbiztypeid) {
		this.setAttributeValue(SaleOrderHVO.CBIZTYPEID, cbiztypeid);
	}

	/**
	 * 设置销售渠道类型
	 * 
	 * @param cchanneltypeid
	 *            销售渠道类型
	 */
	public void setCchanneltypeid(String cchanneltypeid) {
		this.setAttributeValue(SaleOrderHVO.CCHANNELTYPEID, cchanneltypeid);
	}

	/**
	 * 设置开户银行账户
	 * 
	 * @param ccustbankaccid
	 *            开户银行账户
	 */
	public void setCcustbankaccid(String ccustbankaccid) {
		this.setAttributeValue(SaleOrderHVO.CCUSTBANKACCID, ccustbankaccid);
	}

	/**
	 * 设置开户银行
	 * 
	 * @param ccustbankid
	 *            开户银行
	 */
	public void setCcustbankid(String ccustbankid) {
		this.setAttributeValue(SaleOrderHVO.CCUSTBANKID, ccustbankid);
	}

	/**
	 * 设置客户
	 * 
	 * @param ccustomerid
	 *            客户
	 */
	public void setCcustomerid(String ccustomerid) {
		this.setAttributeValue(SaleOrderHVO.CCUSTOMERID, ccustomerid);
	}

	/**
	 * 设置部门最新版本
	 * 
	 * @param cdeptid
	 *            部门最新版本
	 */
	public void setCdeptid(String cdeptid) {
		this.setAttributeValue(SaleOrderHVO.CDEPTID, cdeptid);
	}

	/**
	 * 设置部门
	 * 
	 * @param cdeptvid
	 *            部门
	 */
	public void setCdeptvid(String cdeptvid) {
		this.setAttributeValue(SaleOrderHVO.CDEPTVID, cdeptvid);
	}

	/**
	 * 设置业务员
	 * 
	 * @param cemployeeid
	 *            业务员
	 */
	public void setCemployeeid(String cemployeeid) {
		this.setAttributeValue(SaleOrderHVO.CEMPLOYEEID, cemployeeid);
	}

	/**
	 * 设置散户
	 * 
	 * @param cfreecustid
	 *            散户
	 */
	public void setCfreecustid(String cfreecustid) {
		this.setAttributeValue(SaleOrderHVO.CFREECUSTID, cfreecustid);
	}

	/**
	 * 设置开票客户
	 * 
	 * @param cinvoicecustid
	 *            开票客户
	 */
	public void setCinvoicecustid(String cinvoicecustid) {
		this.setAttributeValue(SaleOrderHVO.CINVOICECUSTID, cinvoicecustid);
	}

	/**
	 * 设置原币
	 * 
	 * @param corigcurrencyid
	 *            原币
	 */
	public void setCorigcurrencyid(String corigcurrencyid) {
		this.setAttributeValue(SaleOrderHVO.CORIGCURRENCYID, corigcurrencyid);
	}

	/**
	 * 设置收款协议
	 * 
	 * @param cpaytermid
	 *            收款协议
	 */
	public void setCpaytermid(String cpaytermid) {
		this.setAttributeValue(SaleOrderHVO.CPAYTERMID, cpaytermid);
	}

	/**
	 * 设置创建时间
	 * 
	 * @param creationtime
	 *            创建时间
	 */
	public void setCreationtime(UFDateTime creationtime) {
		this.setAttributeValue(SaleOrderHVO.CREATIONTIME, creationtime);
	}

	/**
	 * 设置创建人
	 * 
	 * @param creator
	 *            创建人
	 */
	public void setCreator(String creator) {
		this.setAttributeValue(SaleOrderHVO.CREATOR, creator);
	}

	/**
	 * 设置修订人
	 * 
	 * @param creviserid
	 *            修订人
	 */
	public void setCreviserid(String creviserid) {
		this.setAttributeValue(SaleOrderHVO.CREVISERID, creviserid);
	}

	/**
	 * 设置销售主表ID
	 * 
	 * @param csaleorderid
	 *            销售主表ID
	 */
	public void setCsaleorderid(String csaleorderid) {
		this.setAttributeValue(SaleOrderHVO.CSALEORDERID, csaleorderid);
	}

	/**
	 * 设置贸易数据
	 * 
	 * @param ctradewordid
	 *            贸易属于
	 */
	public void setCtradewordid(String ctradewordid) {
		this.setAttributeValue(SaleOrderHVO.CTRADEWORDID, ctradewordid);
	}

	/**
	 * 设置运输方式
	 * 
	 * @param ctransporttypeid
	 *            运输方式
	 */
	public void setCtransporttypeid(String ctransporttypeid) {
		this.setAttributeValue(SaleOrderHVO.CTRANSPORTTYPEID, ctransporttypeid);
	}

	/**
	 * 设置交易类型
	 * 
	 * @param ctrantypeid
	 *            交易类型
	 */
	public void setCtrantypeid(String ctrantypeid) {
		this.setAttributeValue(SaleOrderHVO.CTRANTYPEID, ctrantypeid);
	}

	/**
	 * 设置单据日期
	 * 
	 * @param dbilldate
	 *            单据日期
	 */
	public void setDbilldate(UFDate dbilldate) {
		this.setAttributeValue(SaleOrderHVO.DBILLDATE, dbilldate);
	}

	/**
	 * 设置下游目的单据主组织
	 * 
	 * @param dest_pk_org
	 *            dest_pk_org
	 */
	public void setDest_pk_org(String dest_pk_org) {
		this.setAttributeValue(SaleOrderHVO.DEST_PK_ORG, dest_pk_org);
	}

	/**
	 * 设置制单日期
	 * 
	 * @param Dmakedate
	 *            制单日期
	 */
	public void setDmakedate(UFDate dmakedate) {
		this.setAttributeValue(SaleOrderHVO.DMAKEDATE, dmakedate);
	}

	public void setDr(Integer dr) {
		this.setAttributeValue(SaleOrderHVO.DR, dr);
	}

	/**
	 * 设置审批流状态
	 * 
	 * @param fpfstatusflag
	 *            审批流状态
	 */
	public void setFpfstatusflag(Integer fpfstatusflag) {
		this.setAttributeValue(SaleOrderHVO.FPFSTATUSFLAG, fpfstatusflag);
	}

	/**
	 * 设置单据状态
	 * 
	 * @param fstatusflag
	 *            单据状态
	 * @see BillStatus
	 */
	public void setFstatusflag(Integer fstatusflag) {
		this.setAttributeValue(SaleOrderHVO.FSTATUSFLAG, fstatusflag);
	}

	/**
	 * 设置打印次数
	 * 
	 * @param iprintcount
	 *            打印次数
	 */
	public void setIprintcount(Integer iprintcount) {
		this.setAttributeValue(SaleOrderHVO.IPRINTCOUNT, iprintcount);
	}

	/**
	 * 设置修订版本号
	 * 
	 * @param iversion
	 *            修订版本号
	 */
	public void setIversion(Integer iversion) {
		this.setAttributeValue(SaleOrderHVO.IVERSION, iversion);
	}

	/**
	 * 设置修改时间
	 * 
	 * @param modifiedtime
	 *            修改时间
	 */
	public void setModifiedtime(UFDateTime modifiedtime) {
		this.setAttributeValue(SaleOrderHVO.MODIFIEDTIME, modifiedtime);
	}

	/**
	 * 设置修改人
	 * 
	 * @param modifier
	 *            修改人
	 */
	public void setModifier(String modifier) {
		this.setAttributeValue(SaleOrderHVO.MODIFIER, modifier);
	}

	/**
	 * 设置整单折扣
	 * 
	 * @param ndiscountrate
	 *            整单折扣
	 */
	public void setNdiscountrate(UFDouble ndiscountrate) {
		this.setAttributeValue(SaleOrderHVO.NDISCOUNTRATE, ndiscountrate);
	}

	/**
	 * 设置实际预收款金额
	 * 
	 * @param npreceivemny
	 *            实际预收款金额
	 */
	public void setNpreceivemny(UFDouble npreceivemny) {
		this.setAttributeValue(SaleOrderHVO.NPRECEIVEMNY, npreceivemny);
	}

	/**
	 * 设置订单收款限额
	 * 
	 * @param npreceivequota
	 *            订单收款限额
	 */
	public void setNpreceivequota(UFDouble npreceivequota) {
		this.setAttributeValue(SaleOrderHVO.NPRECEIVEQUOTA, npreceivequota);
	}

	/**
	 * 设置订单收款比例
	 * 
	 * @param npreceiverate
	 *            订单收款比例
	 */
	public void setNpreceiverate(UFDouble npreceiverate) {
		this.setAttributeValue(SaleOrderHVO.NPRECEIVERATE, npreceiverate);
	}

	/**
	 * 设置实际收款金额
	 * 
	 * @param nreceivedmny
	 *            实际收款金额
	 */
	public void setNreceivedmny(UFDouble nreceivedmny) {
		this.setAttributeValue(SaleOrderHVO.NRECEIVEDMNY, nreceivedmny);
	}

	/**
	 * 设置nthisreceivemny
	 * 
	 * @param nthisreceivemny
	 *            nthisreceivemny
	 */
	public void setNthisreceivemny(UFDouble nthisreceivemny) {
		this.setAttributeValue(SaleOrderHVO.NTHISRECEIVEMNY, nthisreceivemny);
	}

	/**
	 * 设置ntotalmny
	 * 
	 * @param ntotalmny
	 *            ntotalmny
	 */
	public void setNtotalmny(UFDouble ntotalmny) {
		this.setAttributeValue(SaleOrderHVO.NTOTALMNY, ntotalmny);
	}

	/**
	 * 设置合计数量
	 * 
	 * @param ntotalnum
	 *            合计数量
	 */
	public void setNtotalnum(UFDouble ntotalnum) {
		this.setAttributeValue(SaleOrderHVO.NTOTALNUM, ntotalnum);
	}

	/**
	 * 设置金额合计
	 * 
	 * @param ntotalorigmny
	 *            金额合计
	 */
	public void setNtotalorigmny(UFDouble ntotalorigmny) {
		this.setAttributeValue(SaleOrderHVO.NTOTALORIGMNY, ntotalorigmny);
	}

	/**
	 * 设置赠品金额合计
	 * 
	 * @param nlrgtotalorigmny
	 */
	public void setNlrgtotalorigmny(UFDouble nlrgtotalorigmny) {
		this.setAttributeValue(SaleOrderHVO.NLRGTOTALORIGMNY, nlrgtotalorigmny);
	}

	/**
	 * 设置费用冲抵金额
	 * 
	 * @param ntotalorigsubmny
	 *            费用冲抵金额
	 */
	public void setNtotalorigsubmny(UFDouble ntotalorigsubmny) {
		this.setAttributeValue(SaleOrderHVO.NTOTALORIGSUBMNY, ntotalorigsubmny);
	}

	/**
	 * 设置总件数
	 * 
	 * @param ntotalpiece
	 *            总件数
	 */
	public void setNtotalpiece(UFDouble ntotalpiece) {
		this.setAttributeValue(SaleOrderHVO.NTOTALPIECE, ntotalpiece);
	}

	/**
	 * 设置合计体积
	 * 
	 * @param ntotalvolume
	 *            合计体积
	 */
	public void setNtotalvolume(UFDouble ntotalvolume) {
		this.setAttributeValue(SaleOrderHVO.NTOTALVOLUME, ntotalvolume);
	}

	/**
	 * 设置合计重量
	 * 
	 * @param ntotalweight
	 *            合计重量
	 */
	public void setNtotalweight(UFDouble ntotalweight) {
		this.setAttributeValue(SaleOrderHVO.NTOTALWEIGHT, ntotalweight);
	}

	/**
	 * 设置集团
	 * 
	 * @param pk_group
	 *            集团
	 */
	public void setPk_group(String pk_group) {
		this.setAttributeValue(SaleOrderHVO.PK_GROUP, pk_group);
	}

	/**
	 * 设置销售组织
	 * 
	 * @param pk_org
	 *            销售组织
	 */
	public void setPk_org(String pk_org) {
		this.setAttributeValue(SaleOrderHVO.PK_ORG, pk_org);
	}

	/**
	 * 设置销售组织版本
	 * 
	 * @param pk_org_v
	 *            销售组织版本
	 */
	public void setPk_org_v(String pk_org_v) {
		this.setAttributeValue(SaleOrderHVO.PK_ORG_V, pk_org_v);
	}

	/**
	 * 设置审批时间
	 * 
	 * @param taudittime
	 *            审批时间
	 */
	public void setTaudittime(UFDate taudittime) {
		this.setAttributeValue(SaleOrderHVO.TAUDITTIME, taudittime);
	}

	/**
	 * 设置修订时间
	 * 
	 * @param trevisetime
	 *            修订时间
	 */
	public void setTrevisetime(UFDate trevisetime) {
		this.setAttributeValue(SaleOrderHVO.TREVISETIME, trevisetime);
	}

	/**
	 * 设置时间戳
	 * 
	 * @param ts
	 *            时间戳
	 */
	public void setTs(UFDateTime ts) {
		this.setAttributeValue(SaleOrderHVO.TS, ts);
	}

	/**
	 * 设置单据号
	 * 
	 * @param vbillcode
	 *            单据号
	 */
	public void setVbillcode(String vbillcode) {
		this.setAttributeValue(SaleOrderHVO.VBILLCODE, vbillcode);
	}

	/**
	 * 设置对方订单号
	 * 
	 * @param vcooppohcode
	 *            对方订单号
	 */
	public void setVcooppohcode(String vcooppohcode) {
		this.setAttributeValue(SaleOrderHVO.VCOOPPOHCODE, vcooppohcode);
	}

	/**
	 * 设置信用证号
	 * 
	 * @param vcreditnum
	 *            信用证号
	 */
	public void setVcreditnum(String vcreditnum) {
		this.setAttributeValue(SaleOrderHVO.VCREDITNUM, vcreditnum);
	}

	/**
	 * 设置自定义项1
	 * 
	 * @param vdef1
	 *            自定义项1
	 */
	public void setVdef1(String vdef1) {
		this.setAttributeValue(SaleOrderHVO.VDEF1, vdef1);
	}

	/**
	 * 设置自定义项10
	 * 
	 * @param vdef10
	 *            自定义项10
	 */
	public void setVdef10(String vdef10) {
		this.setAttributeValue(SaleOrderHVO.VDEF10, vdef10);
	}

	/**
	 * 设置自定义项11
	 * 
	 * @param vdef11
	 *            自定义项11
	 */
	public void setVdef11(String vdef11) {
		this.setAttributeValue(SaleOrderHVO.VDEF11, vdef11);
	}

	/**
	 * 设置自定义项12
	 * 
	 * @param vdef12
	 *            自定义项12
	 */
	public void setVdef12(String vdef12) {
		this.setAttributeValue(SaleOrderHVO.VDEF12, vdef12);
	}

	/**
	 * 设置自定义项13
	 * 
	 * @param vdef13
	 *            自定义项13
	 */
	public void setVdef13(String vdef13) {
		this.setAttributeValue(SaleOrderHVO.VDEF13, vdef13);
	}

	/**
	 * 设置自定义项14
	 * 
	 * @param vdef14
	 *            自定义项14
	 */
	public void setVdef14(String vdef14) {
		this.setAttributeValue(SaleOrderHVO.VDEF14, vdef14);
	}

	/**
	 * 设置自定义项15
	 * 
	 * @param vdef15
	 *            自定义项15
	 */
	public void setVdef15(String vdef15) {
		this.setAttributeValue(SaleOrderHVO.VDEF15, vdef15);
	}

	/**
	 * 设置自定义项16
	 * 
	 * @param vdef16
	 *            自定义项16
	 */
	public void setVdef16(String vdef16) {
		this.setAttributeValue(SaleOrderHVO.VDEF16, vdef16);
	}

	/**
	 * 设置自定义项17
	 * 
	 * @param vdef17
	 *            自定义项17
	 */
	public void setVdef17(String vdef17) {
		this.setAttributeValue(SaleOrderHVO.VDEF17, vdef17);
	}

	/**
	 * 设置自定义项18
	 * 
	 * @param vdef18
	 *            自定义项18
	 */
	public void setVdef18(String vdef18) {
		this.setAttributeValue(SaleOrderHVO.VDEF18, vdef18);
	}

	/**
	 * 设置自定义项19
	 * 
	 * @param vdef19
	 *            自定义项19
	 */
	public void setVdef19(String vdef19) {
		this.setAttributeValue(SaleOrderHVO.VDEF19, vdef19);
	}

	/**
	 * 设置自定义项2
	 * 
	 * @param vdef2
	 *            自定义项2
	 */
	public void setVdef2(String vdef2) {
		this.setAttributeValue(SaleOrderHVO.VDEF2, vdef2);
	}

	/**
	 * 设置自定义项20
	 * 
	 * @param vdef20
	 *            自定义项20
	 */
	public void setVdef20(String vdef20) {
		this.setAttributeValue(SaleOrderHVO.VDEF20, vdef20);
	}

	/**
	 * 设置自定义项3
	 * 
	 * @param vdef3
	 *            自定义项3
	 */
	public void setVdef3(String vdef3) {
		this.setAttributeValue(SaleOrderHVO.VDEF3, vdef3);
	}

	/**
	 * 设置自定义项4
	 * 
	 * @param vdef4
	 *            自定义项4
	 */
	public void setVdef4(String vdef4) {
		this.setAttributeValue(SaleOrderHVO.VDEF4, vdef4);
	}

	/**
	 * 设置自定义项5
	 * 
	 * @param vdef5
	 *            自定义项5
	 */
	public void setVdef5(String vdef5) {
		this.setAttributeValue(SaleOrderHVO.VDEF5, vdef5);
	}

	/**
	 * 设置自定义项6
	 * 
	 * @param vdef6
	 *            自定义项6
	 */
	public void setVdef6(String vdef6) {
		this.setAttributeValue(SaleOrderHVO.VDEF6, vdef6);
	}

	/**
	 * 设置自定义项7
	 * 
	 * @param vdef7
	 *            自定义项7
	 */
	public void setVdef7(String vdef7) {
		this.setAttributeValue(SaleOrderHVO.VDEF7, vdef7);
	}

	/**
	 * 设置自定义项8
	 * 
	 * @param vdef8
	 *            自定义项8
	 */
	public void setVdef8(String vdef8) {
		this.setAttributeValue(SaleOrderHVO.VDEF8, vdef8);
	}

	/**
	 * 设置自定义项9
	 * 
	 * @param vdef9
	 *            自定义项9
	 */
	public void setVdef9(String vdef9) {
		this.setAttributeValue(SaleOrderHVO.VDEF9, vdef9);
	}

	/**
	 * 设置备注
	 * 
	 * @param vnote
	 *            备注
	 */
	public void setVnote(String vnote) {
		this.setAttributeValue(SaleOrderHVO.VNOTE, vnote);
	}

	/**
	 * 设置修订理由
	 * 
	 * @param vrevisereason
	 *            修订理由
	 */
	public void setVrevisereason(String vrevisereason) {
		this.setAttributeValue(SaleOrderHVO.VREVISEREASON, vrevisereason);
	}

	/**
	 * 设置交易类型编码
	 * 
	 * @param vtrantypecode
	 *            交易类型编码
	 */
	public void setVtrantypecode(String vtrantypecode) {
		this.setAttributeValue(SaleOrderHVO.VTRANTYPECODE, vtrantypecode);
	}

	/**
	 * 设置整单来源单据ID
	 * 
	 * @param cbillsrcid
	 *            整单来源单据ID
	 */
	public void setCbillsrcid(String cbillsrcid) {
		this.setAttributeValue(SaleOrderHVO.CBILLSRCID, cbillsrcid);
	}

	/**
	 * 设置整单来源单据类型
	 * 
	 * @param vbillsrctype
	 *            整单来源单据类型
	 */
	public void setVbillsrctype(String vbillsrctype) {
		this.setAttributeValue(SaleOrderHVO.VBILLSRCTYPE, vbillsrctype);
	}

	/**
	 * 设置表头收货客户
	 * 
	 * @param chreceivecustid
	 *            收货客户
	 */
	public void setChreceivecustid(String chreceivecustid) {
		this.setAttributeValue(SaleOrderHVO.CHRECEIVECUSTID, chreceivecustid);
	}

	/**
	 * 设置表头收货地址
	 * 
	 * @param chreceiveaddid
	 *            客户收货地址
	 */
	public void setChreceiveaddid(String chreceiveaddid) {
		this.setAttributeValue(SaleOrderHVO.CHRECEIVEADDID, chreceiveaddid);
	}

	/**
	 * 2017-03-01新增
	 * 
	 * @return zcjhq
	 */
	public static UFDate getZcjhq() {
		return zcjhq;
	}

	/**
	 * @param zcjhq
	 *            要设置的 zcjhq
	 */
	public static void setZcjhq(UFDate zcjhq) {
		SaleOrderHVO.zcjhq = zcjhq;
	}

	/**
	 * @return dlfl
	 */
	public static UFDouble getDlfl() {
		return dlfl;
	}

	/**
	 * @param dlfl
	 *            要设置的 dlfl
	 */
	public static void setDlfl(UFDouble dlfl) {
		SaleOrderHVO.dlfl = dlfl;
	}

	/**
	 * @return djqz
	 */
	public static Integer getDjqz() {
		return djqz;
	}

	/**
	 * @param djqz
	 *            要设置的 djqz
	 */
	public static void setDjqz(Integer djqz) {
		SaleOrderHVO.djqz = djqz;
	}

	/**
	 * @return exchange_rate
	 */
	public UFDouble getExchange_rate() {
		return exchange_rate;
	}

	/**
	 * @param exchange_rate
	 *            要设置的 exchange_rate
	 */
	public void setExchange_rate(UFDouble exchange_rate) {
		SaleOrderHVO.exchange_rate = exchange_rate;
	}

	/**
	 * @return buyccurrencyid
	 */
	public static String getBuyccurrencyid() {
		return buyccurrencyid;
	}

	/**
	 * @param buyccurrencyid
	 *            要设置的 buyccurrencyid
	 */
	public static void setBuyccurrencyid(String buyccurrencyid) {
		SaleOrderHVO.buyccurrencyid = buyccurrencyid;
	}

	/**
	 * @return sqg
	 */
	public static String getSqg() {
		return sqg;
	}

	/**
	 * @param sqg 要设置的 sqg
	 */
	public static void setSqg(String sqg) {
		SaleOrderHVO.sqg = sqg;
	}

}
