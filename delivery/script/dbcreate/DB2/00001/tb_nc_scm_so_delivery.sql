/* tablename: 发货单主表 */
create table so_delivery (cdeliveryid char(20) not null 
/*发货单主表ID*/,
pk_group varchar(20) null 
/*集团*/,
pk_org varchar(20) null 
/*物流组织*/,
pk_org_v varchar(20) null 
/*物流组织版本*/,
vbillcode varchar(40) null 
/*单据号*/,
cbiztypeid varchar(20) null 
/*业务流程*/,
vtrantypecode varchar(20) null 
/*发货类型编码*/,
dbilldate char(19) null 
/*单据日期*/,
csendemployeeid varchar(20) null 
/*发货计划员*/,
csenddeptid varchar(20) null 
/*发货部门最新版本*/,
csenddeptvid varchar(20) null 
/*发货部门*/,
ctransporttypeid varchar(20) null 
/*运输方式*/,
ctransportrouteid varchar(50) null 
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
vnote varchar(181) null 
/*备注*/,
creator varchar(20) null 
/*创建人*/,
billmaker varchar(20) null 
/*制单人*/,
creationtime char(19) null 
/*创建时间*/,
approver varchar(20) null 
/*审批人*/,
taudittime varchar(19) null 
/*审核日期*/,
modifier varchar(20) null 
/*最后修改人*/,
modifiedtime char(19) null 
/*最后修改时间*/,
iprintcount integer null 
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
ctrantypeid varchar(20) null 
/*发货类型*/,
dmakedate char(19) null 
/*制单日期*/,
ctradewordid varchar(20) null 
/*贸易术语*/,
 constraint pk_so_delivery primary key (cdeliveryid),
 ts char(19) null,
dr smallint null default 0
)
;

/* tablename: 发货单子表 */
create table so_delivery_b (cdeliverybid char(20) not null 
/*发货单子表ID*/,
cdeliveryid varchar(20) null 
/*发货单主表ID*/,
pk_org varchar(20) null 
/*物流组织*/,
dbilldate char(19) null 
/*单据日期*/,
crowno varchar(20) null 
/*行号*/,
ccustmaterialid varchar(20) null 
/*客户物料码*/,
cordercustid varchar(20) null 
/*订单客户*/,
cfreecustid varchar(20) null 
/*散户*/,
cinvoicecustid varchar(20) null 
/*开票客户*/,
cmaterialid varchar(20) null 
/*物料档案*/,
cmaterialvid varchar(20) null 
/*物料编码*/,
cvendorid varchar(20) null 
/*供应商*/,
cprojectid varchar(20) null 
/*项目*/,
cproductorid varchar(20) null 
/*生产厂商*/,
castunitid varchar(20) null 
/*单位*/,
nastnum decimal(28,8) null 
/*数量*/,
cunitid varchar(20) null 
/*主单位*/,
nnum decimal(28,8) null 
/*主数量*/,
vchangerate varchar(60) null 
/*换算率*/,
cqtunitid varchar(20) null 
/*报价单位*/,
nqtunitnum decimal(28,8) null 
/*报价数量*/,
vqtunitrate varchar(60) null 
/*报价换算率*/,
bcheckflag char(1) null 
/*是否已报检*/,
busecheckflag char(1) null 
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
blargessflag char(1) null 
/*赠品*/,
pk_batchcode varchar(20) null 
/*批次档案*/,
vbatchcode varchar(40) null 
/*批次号*/,
corigcurrencyid varchar(20) null 
/*原币*/,
nexchangerate decimal(28,8) null 
/*折本汇率*/,
ccurrencyid varchar(20) null 
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
vfirsttype varchar(20) null 
/*源头单据类型*/,
vfirstcode varchar(40) null 
/*源头单据号*/,
vfirsttrantype varchar(40) null 
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
csaleorgid varchar(20) null 
/*销售组织最新版本*/,
csaleorgvid varchar(20) null 
/*销售组织*/,
csendstockorgid varchar(20) null 
/*发货库存组织最新版本*/,
csendareaid varchar(20) null 
/*发货地区*/,
csendadddocid varchar(20) null 
/*发货地点*/,
csendaddrid varchar(20) null 
/*发货地址*/,
csendstordocid varchar(20) null 
/*发货仓库*/,
csendstockorgvid varchar(20) null 
/*发货库存组织*/,
csendpersonid varchar(20) null 
/*发货联系人*/,
vsendtel varchar(30) null 
/*发货联系电话*/,
creceivecustid varchar(20) null 
/*收货客户*/,
creceiveareaid varchar(20) null 
/*收货地区*/,
creceiveadddocid varchar(20) null 
/*收货地点*/,
creceiveaddrid varchar(20) null 
/*收货地址*/,
cinstockorgid varchar(20) null 
/*收货库存组织最新版*/,
vreceivetel varchar(30) null 
/*收货联系电话*/,
creceivepersonid varchar(20) null 
/*收货联系人*/,
cinstordocid varchar(20) null 
/*收货仓库*/,
cinstockorgvid varchar(20) null 
/*收货库存组织*/,
dsenddate varchar(19) null 
/*发货日期*/,
dreceivedate varchar(19) null 
/*要求收货日期*/,
csupercargoid varchar(20) null 
/*押运员*/,
ctranscustid varchar(20) null 
/*承运商*/,
cvehicletypeid varchar(20) null 
/*车型*/,
cvehicleid varchar(20) null 
/*车辆*/,
cchauffeurid varchar(20) null 
/*司机*/,
cspaceid varchar(50) null 
/*货位*/,
cprodlineid varchar(20) null 
/*产品线*/,
ntotaltransnum decimal(28,8) null 
/*累计运输数量*/,
btransendflag char(1) null 
/*运输关闭*/,
ntotaloutnum decimal(28,8) null 
/*累计出库数量*/,
bbarsettleflag char(1) null 
/*收入结算关闭*/,
boutendflag char(1) null 
/*出库关闭*/,
frownote varchar(181) null 
/*备注*/,
vfree1 varchar(101) null 
/*自由辅助属性1*/,
vfree2 varchar(101) null 
/*自由辅助属性2*/,
vfree3 varchar(101) null 
/*自由辅助属性3*/,
vfree4 varchar(101) null 
/*自由辅助属性4*/,
vfree5 varchar(101) null 
/*自由辅助属性5*/,
vfree6 varchar(101) null 
/*自由辅助属性6*/,
vfree7 varchar(101) null 
/*自由辅助属性7*/,
vfree8 varchar(101) null 
/*自由辅助属性8*/,
vfree9 varchar(101) null 
/*自由辅助属性9*/,
vfree10 varchar(101) null 
/*自由辅助属性10*/,
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
cqualitylevelid varchar(20) null 
/*质量等级*/,
cprofitcenterid varchar(20) null 
/*结算利润中心最新版本*/,
cprofitcentervid varchar(20) null 
/*结算利润中心*/,
carorgid varchar(20) null 
/*应收组织最新版本*/,
carorgvid varchar(20) null 
/*应收组织*/,
csettleorgid varchar(20) null 
/*结算财务组织最新版本*/,
cdeptid varchar(20) null 
/*销售部门最新版本*/,
cdeptvid varchar(20) null 
/*销售部门*/,
cemployeeid varchar(20) null 
/*销售业务员*/,
csettleorgvid varchar(20) null 
/*结算财务组织*/,
cchanneltypeid varchar(20) null 
/*销售渠道*/,
vfirstbilldate varchar(19) null 
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
bqualityflag char(1) null 
/*是否已质检*/,
badvfeeflag char(1) null 
/*待垫运费*/,
pk_group varchar(20) null 
/*所属集团*/,
cpriceformid char(20) null 
/*价格组成*/,
cretreasonid char(20) null 
/*退货原因*/,
vreturnmode char(20) null 
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
crececountryid varchar(20) null 
/*收货国家/地区*/,
csendcountryid varchar(20) null 
/*发货国/地区*/,
ctaxcountryid varchar(20) null 
/*报税国/地区*/,
fbuysellflag integer null 
/*购销类型*/,
btriatradeflag char(1) null 
/*三角贸易*/,
ctaxcodeid varchar(20) null 
/*税码*/,
ftaxtypeflag integer null 
/*扣税类别*/,
corigcountryid varchar(20) null 
/*原产国*/,
corigareaid varchar(20) null 
/*原产地区*/,
ncaltaxmny decimal(28,8) null 
/*计税金额*/,
csprofitcentervid varchar(20) null 
/*发货利润中心*/,
csprofitcenterid varchar(20) null 
/*发货利润中心最新版本*/,
crprofitcentervid varchar(20) null 
/*收货利润中心*/,
crprofitcenterid varchar(20) null 
/*收货利润中心最新版本*/,
cmffileid varchar(20) null 
/*特征码*/,
 constraint pk_so_delivery_b primary key (cdeliverybid),
 ts char(19) null,
dr smallint null default 0
)
;

/* tablename: 发货单质检表 */
create table so_delivery_check (cdeliverycid char(20) not null 
/*发货单质检表ID*/,
cdeliverybid varchar(20) null 
/*发货单子表ID*/,
crowno varchar(20) null 
/*行号*/,
cmaterialid varchar(20) null 
/*物料*/,
cmaterialvid varchar(20) null 
/*物料版本*/,
cvendorid varchar(20) null 
/*供应商*/,
cprojectid varchar(20) null 
/*项目*/,
cqualitylevelid varchar(20) null 
/*质量等级*/,
cproductorid varchar(20) null 
/*生产厂商*/,
vfree1 varchar(101) null 
/*自由辅助属性1*/,
vfree2 varchar(101) null 
/*自由辅助属性2*/,
vfree3 varchar(101) null 
/*自由辅助属性3*/,
vfree4 varchar(101) null 
/*自由辅助属性4*/,
vfree5 varchar(101) null 
/*自由辅助属性5*/,
vfree6 varchar(101) null 
/*自由辅助属性6*/,
vfree7 varchar(101) null 
/*自由辅助属性7*/,
vfree8 varchar(101) null 
/*自由辅助属性8*/,
vfree9 varchar(101) null 
/*自由辅助属性9*/,
vfree10 varchar(101) null 
/*自由辅助属性10*/,
pk_batchcode varchar(20) null 
/*批次档案*/,
vbatchcode varchar(40) null 
/*批次号*/,
castunitid varchar(20) null 
/*单位*/,
nastnum decimal(28,8) null 
/*质检数量*/,
cunitid varchar(20) null 
/*主单位*/,
nnum decimal(28,8) null 
/*质检主数量*/,
vchangerate varchar(60) null 
/*换算率*/,
cqtunitid varchar(20) null 
/*报价单位*/,
nqtunitnum decimal(28,8) null 
/*质检报价数量*/,
vqtunitrate varchar(60) null 
/*报价换算率*/,
vcheckcode varchar(40) null 
/*质检单据号*/,
dcheckdate varchar(19) null 
/*质检日期*/,
ccheckstatebid varchar(20) null 
/*质检状态*/,
cdefectprocessid varchar(20) null 
/*建议处理方式*/,
beligflag char(1) null 
/*是否合格*/,
bcheckinflag char(1) null 
/*质检报告是否可入库*/,
blargessflag char(1) null 
/*赠品*/,
bpricechgflag char(1) null 
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
corigcurrencyid varchar(20) null 
/*原币*/,
nexchangerate decimal(28,8) null 
/*折本汇率*/,
ccurrencyid varchar(20) null 
/*本位币*/,
ntotaltransnum decimal(28,8) null 
/*累计运输数量*/,
btransendflag char(1) null 
/*运输关闭*/,
ntotaloutnum decimal(28,8) null 
/*累计出库数量*/,
boutendflag char(1) null 
/*出库关闭*/,
vrownote varchar(181) null 
/*备注*/,
vsrcrowno varchar(20) null 
/*接收单行号*/,
pk_org varchar(20) null 
/*物流组织*/,
pk_group varchar(20) null 
/*所属集团*/,
ntotalnotoutnum decimal(28,8) null 
/*累计应发未出库主数量*/,
csrcid char(20) null 
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
crececountryid varchar(20) null 
/*收货国家/地区*/,
csendcountryid varchar(20) null 
/*发货国/地区*/,
ctaxcountryid varchar(20) null 
/*报税国/地区*/,
fbuysellflag integer null 
/*购销类型*/,
btriatradeflag char(1) null 
/*三角贸易*/,
ctaxcodeid varchar(20) null 
/*税码*/,
ftaxtypeflag integer null 
/*扣税类别*/,
corigcountryid varchar(20) null 
/*原产国*/,
corigareaid varchar(20) null 
/*原产地区*/,
ncaltaxmny decimal(28,8) null 
/*计税金额*/,
 constraint pk__delivery_check primary key (cdeliverycid),
 ts char(19) null,
dr smallint null default 0
)
;

/* tablename: 发货单交易类型 */
create table so_m4331trantype (ctrantypeid char(20) null 
/*发货单交易类型*/,
pk_group varchar(20) null 
/*集团*/,
vtrantypecode varchar(20) null 
/*交易类型*/,
bonceoutflag char(1) null 
/*只一次出库*/,
pk_trantype varchar(20) not null 
/*交易类型主键*/,
 constraint pk_o_m4331trantype primary key (pk_trantype),
 ts char(19) null,
dr smallint null default 0
)
;

