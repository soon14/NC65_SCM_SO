/* tablename: 发货单主表 */
create table so_delivery (
cdeliveryid nchar(20) not null 
/*发货单主表ID*/,
pk_group nvarchar(20) null 
/*集团*/,
pk_org nvarchar(20) null 
/*物流组织*/,
pk_org_v nvarchar(20) null 
/*物流组织版本*/,
vbillcode nvarchar(40) null 
/*单据号*/,
cbiztypeid nvarchar(20) null 
/*业务流程*/,
vtrantypecode nvarchar(20) null 
/*发货类型编码*/,
dbilldate nchar(19) null 
/*单据日期*/,
csendemployeeid nvarchar(20) null 
/*发货计划员*/,
csenddeptid nvarchar(20) null 
/*发货部门最新版本*/,
csenddeptvid nvarchar(20) null 
/*发货部门*/,
ctransporttypeid nvarchar(20) null 
/*运输方式*/,
ctransportrouteid nvarchar(50) null 
/*运输路线*/,
ntotalastnum decimal(28,8) null 
/*总数量*/,
ntotalweight decimal(28,8) null 
/*总重量*/,
ntotalvolume decimal(28,8) null 
/*总体积*/,
ntotalpiece decimal(28,8) null 
/*总件数*/,
fstatusflag smallint null 
/*状态*/,
vnote nvarchar(181) null 
/*备注*/,
creator nvarchar(20) null 
/*创建人*/,
billmaker nvarchar(20) null 
/*制单人*/,
creationtime nchar(19) null 
/*创建时间*/,
approver nvarchar(20) null 
/*审批人*/,
taudittime nvarchar(19) null 
/*审核日期*/,
modifier nvarchar(20) null 
/*最后修改人*/,
modifiedtime nchar(19) null 
/*最后修改时间*/,
iprintcount int null 
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
ctrantypeid nvarchar(20) null 
/*发货类型*/,
dmakedate nchar(19) null 
/*制单日期*/,
ctradewordid nvarchar(20) null 
/*贸易术语*/,
 constraint pk_so_delivery primary key (cdeliveryid),
 ts char(19) null default convert(char(19),getdate(),20),
dr smallint null default 0
)
go

/* tablename: 发货单子表 */
create table so_delivery_b (
cdeliverybid nchar(20) not null 
/*发货单子表ID*/,
cdeliveryid nvarchar(20) null 
/*发货单主表ID*/,
pk_org nvarchar(20) null 
/*物流组织*/,
dbilldate nchar(19) null 
/*单据日期*/,
crowno nvarchar(20) null 
/*行号*/,
ccustmaterialid nvarchar(20) null 
/*客户物料码*/,
cordercustid nvarchar(20) null 
/*订单客户*/,
cfreecustid nvarchar(20) null 
/*散户*/,
cinvoicecustid nvarchar(20) null 
/*开票客户*/,
cmaterialid nvarchar(20) null 
/*物料档案*/,
cmaterialvid nvarchar(20) null 
/*物料编码*/,
cvendorid nvarchar(20) null 
/*供应商*/,
cprojectid nvarchar(20) null 
/*项目*/,
cproductorid nvarchar(20) null 
/*生产厂商*/,
castunitid nvarchar(20) null 
/*单位*/,
nastnum decimal(28,8) null 
/*数量*/,
cunitid nvarchar(20) null 
/*主单位*/,
nnum decimal(28,8) null 
/*主数量*/,
vchangerate nvarchar(60) null 
/*换算率*/,
cqtunitid nvarchar(20) null 
/*报价单位*/,
nqtunitnum decimal(28,8) null 
/*报价数量*/,
vqtunitrate nvarchar(60) null 
/*报价换算率*/,
bcheckflag nchar(1) null 
/*是否已报检*/,
busecheckflag nchar(1) null 
/*是否根据质检结果入库*/,
ntotalreportnum decimal(28,8) null 
/*累计报检数量*/,
ntotalelignum decimal(28,8) null 
/*累计合格数量*/,
ntotalunelignum decimal(28,8) null 
/*累计不合格数量*/,
nweight decimal(28,8) null 
/*重量*/,
nvolume decimal(28,8) null 
/*体积*/,
npiece decimal(28,8) null 
/*件数*/,
blargessflag nchar(1) null 
/*赠品*/,
pk_batchcode nvarchar(20) null 
/*批次档案*/,
vbatchcode nvarchar(40) null 
/*批次号*/,
corigcurrencyid nvarchar(20) null 
/*原币*/,
nexchangerate decimal(28,8) null 
/*折本汇率*/,
ccurrencyid nvarchar(20) null 
/*本位币*/,
ntaxrate decimal(28,8) null 
/*税率*/,
ndiscountrate decimal(28,8) null 
/*整单折扣*/,
nitemdiscountrate decimal(28,8) null 
/*单品折扣*/,
norigtaxprice decimal(28,8) null 
/*主含税单价*/,
norigprice decimal(28,8) null 
/*主无税单价*/,
norigtaxnetprice decimal(28,8) null 
/*主含税净价*/,
norignetprice decimal(28,8) null 
/*主无税净价*/,
nqtorigtaxprice decimal(28,8) null 
/*含税单价*/,
nqtorigprice decimal(28,8) null 
/*无税单价*/,
nqtorigtaxnetprc decimal(28,8) null 
/*含税净价*/,
nqtorignetprice decimal(28,8) null 
/*无税净价*/,
norigmny decimal(28,8) null 
/*无税金额*/,
norigtaxmny decimal(28,8) null 
/*价税合计*/,
norigdiscount decimal(28,8) null 
/*折扣额*/,
ntaxprice decimal(28,8) null 
/*本币主含税单价*/,
nprice decimal(28,8) null 
/*本币主无税单价*/,
ntaxnetprice decimal(28,8) null 
/*本币主含税净价*/,
nnetprice decimal(28,8) null 
/*本币主无税净价*/,
nqttaxprice decimal(28,8) null 
/*本币含税单价*/,
nqtprice decimal(28,8) null 
/*本币无税单价*/,
nqttaxnetprice decimal(28,8) null 
/*本币含税净价*/,
nqtnetprice decimal(28,8) null 
/*本币无税净价*/,
ntax decimal(28,8) null 
/*税额*/,
nmny decimal(28,8) null 
/*本币无税金额*/,
ntaxmny decimal(28,8) null 
/*本币价税合计*/,
ndiscount decimal(28,8) null 
/*本币折扣额*/,
vfirsttype nvarchar(20) null 
/*源头单据类型*/,
vfirstcode nvarchar(40) null 
/*源头单据号*/,
vfirsttrantype nvarchar(40) null 
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
csaleorgid nvarchar(20) null 
/*销售组织最新版本*/,
csaleorgvid nvarchar(20) null 
/*销售组织*/,
csendstockorgid nvarchar(20) null 
/*发货库存组织最新版本*/,
csendareaid nvarchar(20) null 
/*发货地区*/,
csendadddocid nvarchar(20) null 
/*发货地点*/,
csendaddrid nvarchar(20) null 
/*发货地址*/,
csendstordocid nvarchar(20) null 
/*发货仓库*/,
csendstockorgvid nvarchar(20) null 
/*发货库存组织*/,
csendpersonid nvarchar(20) null 
/*发货联系人*/,
vsendtel nvarchar(30) null 
/*发货联系电话*/,
creceivecustid nvarchar(20) null 
/*收货客户*/,
creceiveareaid nvarchar(20) null 
/*收货地区*/,
creceiveadddocid nvarchar(20) null 
/*收货地点*/,
creceiveaddrid nvarchar(20) null 
/*收货地址*/,
cinstockorgid nvarchar(20) null 
/*收货库存组织最新版*/,
vreceivetel nvarchar(30) null 
/*收货联系电话*/,
creceivepersonid nvarchar(20) null 
/*收货联系人*/,
cinstordocid nvarchar(20) null 
/*收货仓库*/,
cinstockorgvid nvarchar(20) null 
/*收货库存组织*/,
dsenddate nvarchar(19) null 
/*发货日期*/,
dreceivedate nvarchar(19) null 
/*要求收货日期*/,
csupercargoid nvarchar(20) null 
/*押运员*/,
ctranscustid nvarchar(20) null 
/*承运商*/,
cvehicletypeid nvarchar(20) null 
/*车型*/,
cvehicleid nvarchar(20) null 
/*车辆*/,
cchauffeurid nvarchar(20) null 
/*司机*/,
cspaceid nvarchar(50) null 
/*货位*/,
cprodlineid nvarchar(20) null 
/*产品线*/,
ntotaltransnum decimal(28,8) null 
/*累计运输数量*/,
btransendflag nchar(1) null 
/*运输关闭*/,
ntotaloutnum decimal(28,8) null 
/*累计出库数量*/,
bbarsettleflag nchar(1) null 
/*收入结算关闭*/,
boutendflag nchar(1) null 
/*出库关闭*/,
frownote nvarchar(181) null 
/*备注*/,
vfree1 nvarchar(101) null 
/*自由辅助属性1*/,
vfree2 nvarchar(101) null 
/*自由辅助属性2*/,
vfree3 nvarchar(101) null 
/*自由辅助属性3*/,
vfree4 nvarchar(101) null 
/*自由辅助属性4*/,
vfree5 nvarchar(101) null 
/*自由辅助属性5*/,
vfree6 nvarchar(101) null 
/*自由辅助属性6*/,
vfree7 nvarchar(101) null 
/*自由辅助属性7*/,
vfree8 nvarchar(101) null 
/*自由辅助属性8*/,
vfree9 nvarchar(101) null 
/*自由辅助属性9*/,
vfree10 nvarchar(101) null 
/*自由辅助属性10*/,
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
cqualitylevelid nvarchar(20) null 
/*质量等级*/,
cprofitcenterid nvarchar(20) null 
/*结算利润中心最新版本*/,
cprofitcentervid nvarchar(20) null 
/*结算利润中心*/,
carorgid nvarchar(20) null 
/*应收组织最新版本*/,
carorgvid nvarchar(20) null 
/*应收组织*/,
csettleorgid nvarchar(20) null 
/*结算财务组织最新版本*/,
cdeptid nvarchar(20) null 
/*销售部门最新版本*/,
cdeptvid nvarchar(20) null 
/*销售部门*/,
cemployeeid nvarchar(20) null 
/*销售业务员*/,
csettleorgvid nvarchar(20) null 
/*结算财务组织*/,
cchanneltypeid nvarchar(20) null 
/*销售渠道*/,
vfirstbilldate nvarchar(19) null 
/*源头单据日期*/,
ntranslossnum decimal(28,8) null 
/*累计途损数量*/,
ntotalrushnum decimal(28,8) null 
/*累计出库对冲数量*/,
ntotalestarnum decimal(28,8) null 
/*累计暂估应收数量*/,
ntotalarnum decimal(28,8) null 
/*累计确认应收数量*/,
nreqrsnum decimal(28,8) null 
/*预留数量*/,
bqualityflag nchar(1) null 
/*是否已质检*/,
badvfeeflag nchar(1) null 
/*待垫运费*/,
pk_group nvarchar(20) null 
/*所属集团*/,
cpriceformid nchar(20) null 
/*价格组成*/,
cretreasonid nchar(20) null 
/*退货原因*/,
vreturnmode nchar(20) null 
/*退货责任处理方式*/,
ntotalnotoutnum decimal(28,8) null 
/*累计应发未出库主数量*/,
nglobaltaxmny decimal(28,8) null 
/*全局本币价税合计*/,
nglobalmny decimal(28,8) null 
/*全局本币无税金额*/,
nglobalexchgrate decimal(28,8) null 
/*全局本位币汇率*/,
ngrouptaxmny decimal(28,8) null 
/*集团本币价税合计*/,
ngroupmny decimal(28,8) null 
/*集团本币无税金额*/,
ngroupexchgrate decimal(28,8) null 
/*集团本位币汇率*/,
crececountryid nvarchar(20) null 
/*收货国家/地区*/,
csendcountryid nvarchar(20) null 
/*发货国/地区*/,
ctaxcountryid nvarchar(20) null 
/*报税国/地区*/,
fbuysellflag int null 
/*购销类型*/,
btriatradeflag nchar(1) null 
/*三角贸易*/,
ctaxcodeid nvarchar(20) null 
/*税码*/,
ftaxtypeflag int null 
/*扣税类别*/,
corigcountryid nvarchar(20) null 
/*原产国*/,
corigareaid nvarchar(20) null 
/*原产地区*/,
ncaltaxmny decimal(28,8) null 
/*计税金额*/,
csprofitcentervid nvarchar(20) null 
/*发货利润中心*/,
csprofitcenterid nvarchar(20) null 
/*发货利润中心最新版本*/,
crprofitcentervid nvarchar(20) null 
/*收货利润中心*/,
crprofitcenterid nvarchar(20) null 
/*收货利润中心最新版本*/,
cmffileid nvarchar(20) null 
/*特征码*/,
 constraint pk_so_delivery_b primary key (cdeliverybid),
 ts char(19) null default convert(char(19),getdate(),20),
dr smallint null default 0
)
go

/* tablename: 发货单质检表 */
create table so_delivery_check (
cdeliverycid nchar(20) not null 
/*发货单质检表ID*/,
cdeliverybid nvarchar(20) null 
/*发货单子表ID*/,
crowno nvarchar(20) null 
/*行号*/,
cmaterialid nvarchar(20) null 
/*物料*/,
cmaterialvid nvarchar(20) null 
/*物料版本*/,
cvendorid nvarchar(20) null 
/*供应商*/,
cprojectid nvarchar(20) null 
/*项目*/,
cqualitylevelid nvarchar(20) null 
/*质量等级*/,
cproductorid nvarchar(20) null 
/*生产厂商*/,
vfree1 nvarchar(101) null 
/*自由辅助属性1*/,
vfree2 nvarchar(101) null 
/*自由辅助属性2*/,
vfree3 nvarchar(101) null 
/*自由辅助属性3*/,
vfree4 nvarchar(101) null 
/*自由辅助属性4*/,
vfree5 nvarchar(101) null 
/*自由辅助属性5*/,
vfree6 nvarchar(101) null 
/*自由辅助属性6*/,
vfree7 nvarchar(101) null 
/*自由辅助属性7*/,
vfree8 nvarchar(101) null 
/*自由辅助属性8*/,
vfree9 nvarchar(101) null 
/*自由辅助属性9*/,
vfree10 nvarchar(101) null 
/*自由辅助属性10*/,
pk_batchcode nvarchar(20) null 
/*批次档案*/,
vbatchcode nvarchar(40) null 
/*批次号*/,
castunitid nvarchar(20) null 
/*单位*/,
nastnum decimal(28,8) null 
/*质检数量*/,
cunitid nvarchar(20) null 
/*主单位*/,
nnum decimal(28,8) null 
/*质检主数量*/,
vchangerate nvarchar(60) null 
/*换算率*/,
cqtunitid nvarchar(20) null 
/*报价单位*/,
nqtunitnum decimal(28,8) null 
/*质检报价数量*/,
vqtunitrate nvarchar(60) null 
/*报价换算率*/,
vcheckcode nvarchar(40) null 
/*质检单据号*/,
dcheckdate nvarchar(19) null 
/*质检日期*/,
ccheckstatebid nvarchar(20) null 
/*质检状态*/,
cdefectprocessid nvarchar(20) null 
/*建议处理方式*/,
beligflag nchar(1) null 
/*是否合格*/,
bcheckinflag nchar(1) null 
/*质检报告是否可入库*/,
blargessflag nchar(1) null 
/*赠品*/,
bpricechgflag nchar(1) null 
/*是否发生改判*/,
ntaxrate decimal(28,8) null 
/*税率*/,
ndiscountrate decimal(28,8) null 
/*整单折扣*/,
nitemdiscountrate decimal(28,8) null 
/*单品折扣*/,
norigtaxprice decimal(28,8) null 
/*主含税单价*/,
norigprice decimal(28,8) null 
/*主无税单价*/,
norigtaxnetprice decimal(28,8) null 
/*主含税净价*/,
norignetprice decimal(28,8) null 
/*主无税净价*/,
nqtorigtaxprice decimal(28,8) null 
/*含税单价*/,
nqtorigprice decimal(28,8) null 
/*无税单价*/,
nqtorigtaxnetprc decimal(28,8) null 
/*含税净价*/,
nqtorignetprice decimal(28,8) null 
/*无税净价*/,
norigmny decimal(28,8) null 
/*无税金额*/,
norigtaxmny decimal(28,8) null 
/*价税合计*/,
norigdiscount decimal(28,8) null 
/*折扣额*/,
ntaxprice decimal(28,8) null 
/*本币主含税单价*/,
nprice decimal(28,8) null 
/*本币主无税单价*/,
ntaxnetprice decimal(28,8) null 
/*本币主含税净价*/,
nnetprice decimal(28,8) null 
/*本币主无税净价*/,
nqttaxprice decimal(28,8) null 
/*本币含税单价*/,
nqtprice decimal(28,8) null 
/*本币无税单价*/,
nqttaxnetprice decimal(28,8) null 
/*本币含税净价*/,
nqtnetprice decimal(28,8) null 
/*本币无税净价*/,
ntax decimal(28,8) null 
/*税额*/,
nmny decimal(28,8) null 
/*本币无税金额*/,
ntaxmny decimal(28,8) null 
/*本币价税合计*/,
ndiscount decimal(28,8) null 
/*本币折扣额*/,
corigcurrencyid nvarchar(20) null 
/*原币*/,
nexchangerate decimal(28,8) null 
/*折本汇率*/,
ccurrencyid nvarchar(20) null 
/*本位币*/,
ntotaltransnum decimal(28,8) null 
/*累计运输数量*/,
btransendflag nchar(1) null 
/*运输关闭*/,
ntotaloutnum decimal(28,8) null 
/*累计出库数量*/,
boutendflag nchar(1) null 
/*出库关闭*/,
vrownote nvarchar(181) null 
/*备注*/,
vsrcrowno nvarchar(20) null 
/*接收单行号*/,
pk_org nvarchar(20) null 
/*物流组织*/,
pk_group nvarchar(20) null 
/*所属集团*/,
ntotalnotoutnum decimal(28,8) null 
/*累计应发未出库主数量*/,
csrcid nchar(20) null 
/*来源单据id*/,
nglobaltaxmny decimal(28,8) null 
/*全局本币价税合计*/,
nglobalmny decimal(28,8) null 
/*全局本币无税金额*/,
nglobalexchgrate decimal(28,8) null 
/*全局本位币汇率*/,
ngrouptaxmny decimal(28,8) null 
/*集团本币价税合计*/,
ngroupmny decimal(28,8) null 
/*集团本币无税金额*/,
ngroupexchgrate decimal(28,8) null 
/*集团本位币汇率*/,
crececountryid nvarchar(20) null 
/*收货国家/地区*/,
csendcountryid nvarchar(20) null 
/*发货国/地区*/,
ctaxcountryid nvarchar(20) null 
/*报税国/地区*/,
fbuysellflag int null 
/*购销类型*/,
btriatradeflag nchar(1) null 
/*三角贸易*/,
ctaxcodeid nvarchar(20) null 
/*税码*/,
ftaxtypeflag int null 
/*扣税类别*/,
corigcountryid nvarchar(20) null 
/*原产国*/,
corigareaid nvarchar(20) null 
/*原产地区*/,
ncaltaxmny decimal(28,8) null 
/*计税金额*/,
 constraint pk__delivery_check primary key (cdeliverycid),
 ts char(19) null default convert(char(19),getdate(),20),
dr smallint null default 0
)
go

/* tablename: 发货单交易类型 */
create table so_m4331trantype (
ctrantypeid nchar(20) null 
/*发货单交易类型*/,
pk_group nvarchar(20) null 
/*集团*/,
vtrantypecode nvarchar(20) null 
/*交易类型*/,
bonceoutflag nchar(1) null 
/*只一次出库*/,
pk_trantype nvarchar(20) not null 
/*交易类型主键*/,
 constraint pk_o_m4331trantype primary key (pk_trantype),
 ts char(19) null default convert(char(19),getdate(),20),
dr smallint null default 0
)
go

