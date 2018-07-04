/* tablename: 发票交易类型 */
create table so_m32trantype (pk_trantype char(20) not null 
/*发票交易类型主键*/,
ctrantypeid varchar(20) null 
/*交易类型*/,
pk_group varchar(20) null 
/*集团ID*/,
vtrantypecode varchar(20) null 
/*交易类型编码*/,
fadjuster smallint null 
/*调整项*/,
 constraint pk_m32trantype primary key (pk_trantype),
 ts char(19) null,
dr smallint null default 0
)
;

/* tablename: 销售发票子表 */
create table so_saleinvoice_b (csaleinvoicebid char(20) not null 
/*发票子表ID*/,
csaleinvoiceid varchar(20) null 
/*发票主表ID*/,
pk_group varchar(20) null 
/*集团*/,
pk_org varchar(20) null 
/*开票组织*/,
dbilldate char(19) null 
/*开票日期*/,
crowno varchar(20) null 
/*行号*/,
ccustmaterialid varchar(20) null 
/*客户物料码*/,
cmaterialid varchar(20) null 
/*物料*/,
cmaterialvid varchar(20) null 
/*物料版本*/,
cvendorid varchar(20) null 
/*供应商*/,
cprojectid varchar(20) null 
/*项目*/,
cproductorid varchar(20) null 
/*生产厂商*/,
cunitid varchar(20) null 
/*主单位*/,
castunitid varchar(20) null 
/*单位*/,
vchangerate varchar(60) null 
/*换算率*/,
nnum decimal(28,8) null 
/*主数量*/,
nastnum decimal(28,8) null 
/*数量*/,
cqtunitid varchar(20) null 
/*报价单位*/,
vqtunitrate varchar(60) null 
/*报价单位换算率*/,
nqtunitnum decimal(28,8) null 
/*报价数量*/,
bdiscountflag char(1) null 
/*折扣类*/,
blaborflag char(1) null 
/*服务类*/,
blargessflag char(1) null 
/*赠品*/,
pk_batchcode varchar(20) null 
/*批次档案*/,
vbatchcode varchar(40) null 
/*批次号*/,
ntaxrate decimal(28,8) null 
/*税率*/,
ndiscountrate decimal(28,8) null 
/*整单折扣*/,
nitemdiscountrate decimal(28,8) null 
/*单品折扣*/,
ninvoicedisrate decimal(28,8) null 
/*发票折扣*/,
norigtaxprice decimal(20,8) null 
/*主含税单价*/,
norigprice decimal(28,8) null 
/*主无税单价*/,
norigtaxnetprice decimal(28,8) null 
/*主含税净价*/,
norignetprice decimal(28,8) null 
/*主无税净价*/,
ntaxprice decimal(28,8) null 
/*主本币含税单价*/,
nprice decimal(28,8) null 
/*主本币无税单价*/,
ntaxnetprice decimal(28,8) null 
/*主本币含税净价*/,
nnetprice decimal(28,8) null 
/*主本币无税净价*/,
nqtorigtaxprice decimal(28,8) null 
/*含税单价*/,
nqtorigprice decimal(28,8) null 
/*无税单价*/,
nqtorigtaxnetprc decimal(28,8) null 
/*含税净价*/,
nqtorignetprice decimal(28,8) null 
/*无税净价*/,
nqttaxprice decimal(28,8) null 
/*本币含税单价*/,
nqtprice decimal(28,8) null 
/*本币无税单价*/,
nqttaxnetprice decimal(28,8) null 
/*本币含税净价*/,
nqtnetprice decimal(28,8) null 
/*本币无税净价*/,
ntax decimal(28,8) null 
/*本币税额*/,
nmny decimal(28,8) null 
/*本币无税金额*/,
ntaxmny decimal(28,8) null 
/*本币价税合计*/,
ndiscount decimal(28,8) null 
/*本币折扣额*/,
norigmny decimal(28,8) null 
/*无税金额*/,
norigtaxmny decimal(28,8) null 
/*价税合计*/,
norigdiscount decimal(28,8) null 
/*折扣额*/,
norigsubmny decimal(28,8) null 
/*冲抵金额*/,
ngroupmny decimal(28,8) null 
/*集团本币无税金额*/,
ngrouptaxmny decimal(28,8) null 
/*集团本币价税合计*/,
nglobalmny decimal(28,8) null 
/*全局本币无税金额*/,
nglobaltaxmny decimal(28,8) null 
/*全局本币价税合计*/,
vfirsttype varchar(20) null 
/*源头单据类型*/,
vfirstcode varchar(40) null 
/*源头单据号*/,
vfirsttrantype varchar(20) null 
/*源头交易类型*/,
vfirstrowno varchar(20) null 
/*源头单据行号*/,
cfirstid varchar(20) null 
/*源头单据表头ID*/,
cfirstbid varchar(20) null 
/*源头单据表体ID*/,
vsrctype varchar(20) null 
/*来源单据类型*/,
vsrccode varchar(40) null 
/*来源单据号*/,
vsrctrantype varchar(20) null 
/*来源交易类型*/,
vsrcrowno varchar(20) null 
/*来源单据行号*/,
csrcid varchar(20) null 
/*来源单据表头ID*/,
csrcbid varchar(20) null 
/*来源单据表体ID*/,
copposesrcbid varchar(20) null 
/*对冲来源表体ID*/,
csaleorgid varchar(20) null 
/*销售组织*/,
csaleorgvid varchar(20) null 
/*销售组织版本*/,
cprofitcenterid varchar(20) null 
/*结算利润中心*/,
cprofitcentervid varchar(20) null 
/*结算利润中心版本*/,
carorgid varchar(20) null 
/*应收组织*/,
carorgvid varchar(20) null 
/*应收组织版本*/,
cordercustid varchar(20) null 
/*订单客户*/,
bfreecustflag char(1) null 
/*是否散户*/,
cfreecustid varchar(20) null 
/*散户*/,
cdeptid varchar(20) null 
/*销售部门*/,
cdeptvid varchar(20) null 
/*销售部门版本*/,
cemployeeid varchar(20) null 
/*销售业务员*/,
creceivecustid varchar(20) null 
/*收货客户*/,
creceiveaddrid varchar(20) null 
/*收货地址*/,
ctransporttypeid varchar(20) null 
/*运输方式*/,
csendstockorgid varchar(20) null 
/*库存组织*/,
csendstockorgvid varchar(20) null 
/*库存组织版本*/,
csendstordocid varchar(20) null 
/*仓库*/,
cprodlineid varchar(20) null 
/*产品线*/,
ccostsubjid varchar(20) null 
/*收支项目*/,
cctmanageid varchar(20) null 
/*合同*/,
vsumcode varchar(40) null 
/*消耗汇总号*/,
csumid varchar(20) null 
/*消耗汇总主键*/,
cvmivenderid varchar(20) null 
/*寄存供应商*/,
nshouldoutnum decimal(28,8) null 
/*累计应发未出库数量*/,
ntotaloutnum decimal(28,8) null 
/*累计出库数量*/,
ntotalincomenum decimal(28,8) null 
/*累计确认应收数量*/,
ntotalincomemny decimal(28,8) null 
/*累计确认应收金额*/,
ntotalcostnum decimal(28,8) null 
/*累计成本结算数量*/,
ntotalpaymny decimal(28,8) null 
/*累计收款金额*/,
vrownote varchar(181) null 
/*备注*/,
vfree1 varchar(101) null 
/*物料辅助属性1*/,
vfree2 varchar(101) null 
/*物料辅助属性2*/,
vfree3 varchar(101) null 
/*物料辅助属性3*/,
vfree4 varchar(101) null 
/*物料辅助属性4*/,
vfree5 varchar(101) null 
/*物料辅助属性5*/,
vfree6 varchar(101) null 
/*物料辅助属性6*/,
vfree7 varchar(101) null 
/*物料辅助属性7*/,
vfree8 varchar(101) null 
/*物料辅助属性8*/,
vfree9 varchar(101) null 
/*物料辅助属性9*/,
vfree10 varchar(101) null 
/*物料辅助属性10*/,
vbdef1 varchar(101) null 
/*自定义项1*/,
vbdef2 varchar(101) null 
/*自定义项2*/,
vbdef3 varchar(101) null 
/*自定义项3*/,
vbdef4 varchar(101) null 
/*自定义项4*/,
vbdef5 varchar(101) null 
/*自定义项5*/,
vbdef6 varchar(101) null 
/*自定义项6*/,
vbdef7 varchar(101) null 
/*自定义项7*/,
vbdef8 varchar(101) null 
/*自定义项8*/,
vbdef9 varchar(101) null 
/*自定义项9*/,
vbdef10 varchar(101) null 
/*自定义项10*/,
vbdef11 varchar(101) null 
/*自定义项11*/,
vbdef12 varchar(101) null 
/*自定义项12*/,
vbdef13 varchar(101) null 
/*自定义项13*/,
vbdef14 varchar(101) null 
/*自定义项14*/,
vbdef15 varchar(101) null 
/*自定义项15*/,
vbdef16 varchar(101) null 
/*自定义项16*/,
vbdef17 varchar(101) null 
/*自定义项17*/,
vbdef18 varchar(101) null 
/*自定义项18*/,
vbdef19 varchar(101) null 
/*自定义项19*/,
vbdef20 varchar(101) null 
/*自定义项20*/,
ctaxcodeid varchar(20) null 
/*税码*/,
ftaxtypeflag integer null 
/*扣税类别*/,
ncaltaxmny decimal(28,8) null 
/*计税金额*/,
cchanneltypeid varchar(20) null 
/*销售渠道类型*/,
csprofitcentervid varchar(20) null 
/*发货利润中心*/,
csprofitcenterid varchar(20) null 
/*发货利润中心最新版本*/,
cmffileid varchar(20) null 
/*特征码*/,
 constraint pk_saleinvoice_b primary key (csaleinvoicebid),
 ts char(19) null,
dr smallint null default 0
)
;

/* tablename: 销售发票主表 */
create table so_saleinvoice (csaleinvoiceid char(20) not null 
/*发票主表ID*/,
pk_group varchar(20) null 
/*集团*/,
pk_org varchar(20) null 
/*开票组织*/,
pk_org_v varchar(20) null 
/*开票组织版本*/,
vbillcode varchar(40) null 
/*发票号*/,
cbiztypeid varchar(20) null 
/*业务流程*/,
ctrantypeid varchar(20) null 
/*发票类型*/,
vtrantypecode varchar(20) null 
/*发票类型编码*/,
cinvoicecustid varchar(20) null 
/*开票客户*/,
dbilldate char(19) null 
/*开票日期*/,
vprintcustname varchar(50) null 
/*客户打印名称*/,
ccustbankid varchar(20) null 
/*客户开户银行*/,
ccustbankaccid varchar(20) null 
/*客户银行账号*/,
cpaytermid varchar(20) null 
/*收付款协议*/,
vcreditnum varchar(20) null 
/*信用证号*/,
vgoldtaxcode varchar(100) null 
/*金税票号*/,
btogoldtaxflag char(1) null 
/*是否已经传金税*/,
tgoldtaxtime varchar(19) null 
/*最后传金税时间*/,
corigcurrencyid varchar(20) null 
/*原币*/,
nexchangerate decimal(28,8) null 
/*折本汇率*/,
ccurrencyid varchar(20) null 
/*本币币种*/,
ngroupexchgrate decimal(28,8) null 
/*集团本位币汇率*/,
nglobalexchgrate decimal(28,8) null 
/*全局本位币汇率*/,
nhvoicedisrate decimal(28,8) null 
/*发票折扣*/,
ntotalastnum decimal(28,8) null 
/*总数量*/,
ntotalorigmny decimal(28,8) null 
/*价税合计*/,
ntotalorigsubmny decimal(28,8) null 
/*冲抵金额*/,
bsubunitflag char(1) null 
/*冲抵标记*/,
fopposeflag integer null 
/*对冲标记*/,
vopposesrccode varchar(40) null 
/*对冲来源发票号*/,
copposesrcid varchar(20) null 
/*对冲来源表头ID*/,
vnote varchar(181) null 
/*备注*/,
fstatusflag integer null 
/*单据状态*/,
creator varchar(20) null 
/*创建人*/,
billmaker varchar(20) null 
/*制单人*/,
creationtime char(19) null 
/*创建时间*/,
modifier varchar(20) null 
/*最后修改人*/,
modifiedtime char(19) null 
/*最后修改时间*/,
approver varchar(20) null 
/*审批人*/,
taudittime varchar(19) null 
/*审核日期*/,
iprintcount integer null default 0 
/*打印次数*/,
vdef1 varchar(101) null 
/*自定义项1*/,
vdef2 varchar(101) null 
/*自定义项2*/,
vdef3 varchar(101) null 
/*自定义项3*/,
vdef4 varchar(101) null 
/*自定义项4*/,
vdef5 varchar(101) null 
/*自定义项5*/,
vdef6 varchar(101) null 
/*自定义项6*/,
vdef7 varchar(101) null 
/*自定义项7*/,
vdef8 varchar(101) null 
/*自定义项8*/,
vdef9 varchar(101) null 
/*自定义项9*/,
vdef10 varchar(101) null 
/*自定义项10*/,
vdef11 varchar(101) null 
/*自定义项11*/,
vdef12 varchar(101) null 
/*自定义项12*/,
vdef13 varchar(101) null 
/*自定义项13*/,
vdef14 varchar(101) null 
/*自定义项14*/,
vdef15 varchar(101) null 
/*自定义项15*/,
vdef16 varchar(101) null 
/*自定义项16*/,
vdef17 varchar(101) null 
/*自定义项17*/,
vdef18 varchar(101) null 
/*自定义项18*/,
vdef19 varchar(101) null 
/*自定义项19*/,
vdef20 varchar(101) null 
/*自定义项20*/,
dmakedate char(19) null 
/*制单日期*/,
crececountryid varchar(20) null 
/*收货国家/地区*/,
csendcountryid varchar(20) null 
/*发货国家/地区*/,
ctaxcountryid varchar(20) null 
/*报税国家/地区*/,
fbuysellflag integer null 
/*购销类型*/,
btriatradeflag char(1) null 
/*三角贸易*/,
ctradewordid varchar(20) null 
/*贸易术语*/,
vvatcode varchar(40) null 
/*VAT注册码*/,
vcustvatcode varchar(40) null 
/*客户VAT注册码*/,
cbalancetypeid varchar(20) null 
/*结算方式*/,
 constraint pk_saleinvoice primary key (csaleinvoiceid),
 ts char(19) null,
dr smallint null default 0
)
;

