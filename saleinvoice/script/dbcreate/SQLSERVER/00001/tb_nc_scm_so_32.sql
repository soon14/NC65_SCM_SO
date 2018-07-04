/* tablename: 发票交易类型 */
create table so_m32trantype (
pk_trantype nchar(20) not null 
/*发票交易类型主键*/,
ctrantypeid nvarchar(20) null 
/*交易类型*/,
pk_group nvarchar(20) null 
/*集团ID*/,
vtrantypecode nvarchar(20) null 
/*交易类型编码*/,
fadjuster smallint null 
/*调整项*/,
 constraint pk_m32trantype primary key (pk_trantype),
 ts char(19) null default convert(char(19),getdate(),20),
dr smallint null default 0
)
go

/* tablename: 销售发票子表 */
create table so_saleinvoice_b (
csaleinvoicebid nchar(20) not null 
/*发票子表ID*/,
csaleinvoiceid nvarchar(20) null 
/*发票主表ID*/,
pk_group nvarchar(20) null 
/*集团*/,
pk_org nvarchar(20) null 
/*开票组织*/,
dbilldate nchar(19) null 
/*开票日期*/,
crowno nvarchar(20) null 
/*行号*/,
ccustmaterialid nvarchar(20) null 
/*客户物料码*/,
cmaterialid nvarchar(20) null 
/*物料*/,
cmaterialvid nvarchar(20) null 
/*物料版本*/,
cvendorid nvarchar(20) null 
/*供应商*/,
cprojectid nvarchar(20) null 
/*项目*/,
cproductorid nvarchar(20) null 
/*生产厂商*/,
cunitid nvarchar(20) null 
/*主单位*/,
castunitid nvarchar(20) null 
/*单位*/,
vchangerate nvarchar(60) null 
/*换算率*/,
nnum decimal(28,8) null 
/*主数量*/,
nastnum decimal(28,8) null 
/*数量*/,
cqtunitid nvarchar(20) null 
/*报价单位*/,
vqtunitrate nvarchar(60) null 
/*报价单位换算率*/,
nqtunitnum decimal(28,8) null 
/*报价数量*/,
bdiscountflag nchar(1) null 
/*折扣类*/,
blaborflag nchar(1) null 
/*服务类*/,
blargessflag nchar(1) null 
/*赠品*/,
pk_batchcode nvarchar(20) null 
/*批次档案*/,
vbatchcode nvarchar(40) null 
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
vfirsttype nvarchar(20) null 
/*源头单据类型*/,
vfirstcode nvarchar(40) null 
/*源头单据号*/,
vfirsttrantype nvarchar(20) null 
/*源头交易类型*/,
vfirstrowno nvarchar(20) null 
/*源头单据行号*/,
cfirstid nvarchar(20) null 
/*源头单据表头ID*/,
cfirstbid nvarchar(20) null 
/*源头单据表体ID*/,
vsrctype nvarchar(20) null 
/*来源单据类型*/,
vsrccode nvarchar(40) null 
/*来源单据号*/,
vsrctrantype nvarchar(20) null 
/*来源交易类型*/,
vsrcrowno nvarchar(20) null 
/*来源单据行号*/,
csrcid nvarchar(20) null 
/*来源单据表头ID*/,
csrcbid nvarchar(20) null 
/*来源单据表体ID*/,
copposesrcbid nvarchar(20) null 
/*对冲来源表体ID*/,
csaleorgid nvarchar(20) null 
/*销售组织*/,
csaleorgvid nvarchar(20) null 
/*销售组织版本*/,
cprofitcenterid nvarchar(20) null 
/*结算利润中心*/,
cprofitcentervid nvarchar(20) null 
/*结算利润中心版本*/,
carorgid nvarchar(20) null 
/*应收组织*/,
carorgvid nvarchar(20) null 
/*应收组织版本*/,
cordercustid nvarchar(20) null 
/*订单客户*/,
bfreecustflag nchar(1) null 
/*是否散户*/,
cfreecustid nvarchar(20) null 
/*散户*/,
cdeptid nvarchar(20) null 
/*销售部门*/,
cdeptvid nvarchar(20) null 
/*销售部门版本*/,
cemployeeid nvarchar(20) null 
/*销售业务员*/,
creceivecustid nvarchar(20) null 
/*收货客户*/,
creceiveaddrid nvarchar(20) null 
/*收货地址*/,
ctransporttypeid nvarchar(20) null 
/*运输方式*/,
csendstockorgid nvarchar(20) null 
/*库存组织*/,
csendstockorgvid nvarchar(20) null 
/*库存组织版本*/,
csendstordocid nvarchar(20) null 
/*仓库*/,
cprodlineid nvarchar(20) null 
/*产品线*/,
ccostsubjid nvarchar(20) null 
/*收支项目*/,
cctmanageid nvarchar(20) null 
/*合同*/,
vsumcode nvarchar(40) null 
/*消耗汇总号*/,
csumid nvarchar(20) null 
/*消耗汇总主键*/,
cvmivenderid nvarchar(20) null 
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
vrownote nvarchar(181) null 
/*备注*/,
vfree1 nvarchar(101) null 
/*物料辅助属性1*/,
vfree2 nvarchar(101) null 
/*物料辅助属性2*/,
vfree3 nvarchar(101) null 
/*物料辅助属性3*/,
vfree4 nvarchar(101) null 
/*物料辅助属性4*/,
vfree5 nvarchar(101) null 
/*物料辅助属性5*/,
vfree6 nvarchar(101) null 
/*物料辅助属性6*/,
vfree7 nvarchar(101) null 
/*物料辅助属性7*/,
vfree8 nvarchar(101) null 
/*物料辅助属性8*/,
vfree9 nvarchar(101) null 
/*物料辅助属性9*/,
vfree10 nvarchar(101) null 
/*物料辅助属性10*/,
vbdef1 nvarchar(101) null 
/*自定义项1*/,
vbdef2 nvarchar(101) null 
/*自定义项2*/,
vbdef3 nvarchar(101) null 
/*自定义项3*/,
vbdef4 nvarchar(101) null 
/*自定义项4*/,
vbdef5 nvarchar(101) null 
/*自定义项5*/,
vbdef6 nvarchar(101) null 
/*自定义项6*/,
vbdef7 nvarchar(101) null 
/*自定义项7*/,
vbdef8 nvarchar(101) null 
/*自定义项8*/,
vbdef9 nvarchar(101) null 
/*自定义项9*/,
vbdef10 nvarchar(101) null 
/*自定义项10*/,
vbdef11 nvarchar(101) null 
/*自定义项11*/,
vbdef12 nvarchar(101) null 
/*自定义项12*/,
vbdef13 nvarchar(101) null 
/*自定义项13*/,
vbdef14 nvarchar(101) null 
/*自定义项14*/,
vbdef15 nvarchar(101) null 
/*自定义项15*/,
vbdef16 nvarchar(101) null 
/*自定义项16*/,
vbdef17 nvarchar(101) null 
/*自定义项17*/,
vbdef18 nvarchar(101) null 
/*自定义项18*/,
vbdef19 nvarchar(101) null 
/*自定义项19*/,
vbdef20 nvarchar(101) null 
/*自定义项20*/,
ctaxcodeid nvarchar(20) null 
/*税码*/,
ftaxtypeflag int null 
/*扣税类别*/,
ncaltaxmny decimal(28,8) null 
/*计税金额*/,
cchanneltypeid nvarchar(20) null 
/*销售渠道类型*/,
csprofitcentervid nvarchar(20) null 
/*发货利润中心*/,
csprofitcenterid nvarchar(20) null 
/*发货利润中心最新版本*/,
cmffileid nvarchar(20) null 
/*特征码*/,
 constraint pk_saleinvoice_b primary key (csaleinvoicebid),
 ts char(19) null default convert(char(19),getdate(),20),
dr smallint null default 0
)
go

/* tablename: 销售发票主表 */
create table so_saleinvoice (
csaleinvoiceid nchar(20) not null 
/*发票主表ID*/,
pk_group nvarchar(20) null 
/*集团*/,
pk_org nvarchar(20) null 
/*开票组织*/,
pk_org_v nvarchar(20) null 
/*开票组织版本*/,
vbillcode nvarchar(40) null 
/*发票号*/,
cbiztypeid nvarchar(20) null 
/*业务流程*/,
ctrantypeid nvarchar(20) null 
/*发票类型*/,
vtrantypecode nvarchar(20) null 
/*发票类型编码*/,
cinvoicecustid nvarchar(20) null 
/*开票客户*/,
dbilldate nchar(19) null 
/*开票日期*/,
vprintcustname nvarchar(50) null 
/*客户打印名称*/,
ccustbankid nvarchar(20) null 
/*客户开户银行*/,
ccustbankaccid nvarchar(20) null 
/*客户银行账号*/,
cpaytermid nvarchar(20) null 
/*收付款协议*/,
vcreditnum nvarchar(20) null 
/*信用证号*/,
vgoldtaxcode nvarchar(100) null 
/*金税票号*/,
btogoldtaxflag nchar(1) null 
/*是否已经传金税*/,
tgoldtaxtime nvarchar(19) null 
/*最后传金税时间*/,
corigcurrencyid nvarchar(20) null 
/*原币*/,
nexchangerate decimal(28,8) null 
/*折本汇率*/,
ccurrencyid nvarchar(20) null 
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
bsubunitflag nchar(1) null 
/*冲抵标记*/,
fopposeflag int null 
/*对冲标记*/,
vopposesrccode nvarchar(40) null 
/*对冲来源发票号*/,
copposesrcid nvarchar(20) null 
/*对冲来源表头ID*/,
vnote nvarchar(181) null 
/*备注*/,
fstatusflag int null 
/*单据状态*/,
creator nvarchar(20) null 
/*创建人*/,
billmaker nvarchar(20) null 
/*制单人*/,
creationtime nchar(19) null 
/*创建时间*/,
modifier nvarchar(20) null 
/*最后修改人*/,
modifiedtime nchar(19) null 
/*最后修改时间*/,
approver nvarchar(20) null 
/*审批人*/,
taudittime nvarchar(19) null 
/*审核日期*/,
iprintcount int null default 0 
/*打印次数*/,
vdef1 nvarchar(101) null 
/*自定义项1*/,
vdef2 nvarchar(101) null 
/*自定义项2*/,
vdef3 nvarchar(101) null 
/*自定义项3*/,
vdef4 nvarchar(101) null 
/*自定义项4*/,
vdef5 nvarchar(101) null 
/*自定义项5*/,
vdef6 nvarchar(101) null 
/*自定义项6*/,
vdef7 nvarchar(101) null 
/*自定义项7*/,
vdef8 nvarchar(101) null 
/*自定义项8*/,
vdef9 nvarchar(101) null 
/*自定义项9*/,
vdef10 nvarchar(101) null 
/*自定义项10*/,
vdef11 nvarchar(101) null 
/*自定义项11*/,
vdef12 nvarchar(101) null 
/*自定义项12*/,
vdef13 nvarchar(101) null 
/*自定义项13*/,
vdef14 nvarchar(101) null 
/*自定义项14*/,
vdef15 nvarchar(101) null 
/*自定义项15*/,
vdef16 nvarchar(101) null 
/*自定义项16*/,
vdef17 nvarchar(101) null 
/*自定义项17*/,
vdef18 nvarchar(101) null 
/*自定义项18*/,
vdef19 nvarchar(101) null 
/*自定义项19*/,
vdef20 nvarchar(101) null 
/*自定义项20*/,
dmakedate nchar(19) null 
/*制单日期*/,
crececountryid nvarchar(20) null 
/*收货国家/地区*/,
csendcountryid nvarchar(20) null 
/*发货国家/地区*/,
ctaxcountryid nvarchar(20) null 
/*报税国家/地区*/,
fbuysellflag int null 
/*购销类型*/,
btriatradeflag nchar(1) null 
/*三角贸易*/,
ctradewordid nvarchar(20) null 
/*贸易术语*/,
vvatcode nvarchar(40) null 
/*VAT注册码*/,
vcustvatcode nvarchar(40) null 
/*客户VAT注册码*/,
cbalancetypeid nvarchar(20) null 
/*结算方式*/,
 constraint pk_saleinvoice primary key (csaleinvoiceid),
 ts char(19) null default convert(char(19),getdate(),20),
dr smallint null default 0
)
go

