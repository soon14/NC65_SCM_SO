/* tablename: 预订单主表 */
create table so_preorder (cpreorderid char(20) not null 
/*预订单主表*/,
vbillcode varchar(40) null 
/*单据号*/,
pk_org varchar(20) null 
/*销售组织*/,
pk_org_v varchar(20) null 
/*销售组织版本*/,
pk_group varchar(20) null 
/*集团*/,
ctrantypeid varchar(20) null 
/*预订单类型*/,
vtrantypecode varchar(20) null 
/*预订单类型编码*/,
cdeptid varchar(20) null 
/*销售部门最新版本*/,
cdeptvid varchar(20) null 
/*销售部门*/,
cemployeeid varchar(20) null 
/*业务员*/,
ccustomerid varchar(20) null 
/*客户*/,
cchanneltypeid varchar(20) null 
/*销售渠道类型*/,
cpaytermid varchar(20) null 
/*收款协议*/,
ndiscountrate decimal(28,8) null 
/*整单折扣*/,
cinvoicecustid varchar(20) null 
/*开票客户*/,
ctransporttypeid varchar(20) null 
/*运输方式*/,
corigcurrencyid varchar(20) null 
/*币种*/,
ntotalnum decimal(28,8) null 
/*总数量*/,
nheadsummny decimal(28,8) null 
/*价税合计*/,
dbilldate char(19) null 
/*订货日期*/,
dabatedate varchar(19) null 
/*失效日期*/,
fstatusflag integer null default 1 
/*单据状态*/,
vsrctype varchar(20) null 
/*来源类型*/,
cweboperatorid varchar(20) null 
/*网上订单制单人*/,
billmaker varchar(20) null 
/*制单人*/,
creator varchar(20) null 
/*创建人*/,
creationtime char(19) null 
/*创建时间*/,
approver varchar(20) null 
/*审批人*/,
taudittime char(19) null 
/*审批日期*/,
modifier varchar(20) null 
/*最后修改人*/,
modifiedtime char(19) null 
/*最后修改时间*/,
iprintcount integer null default 0 
/*打印次数*/,
vnote varchar(181) null 
/*备注*/,
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
 constraint pk_so_preorder primary key (cpreorderid),
 ts char(19) null,
dr smallint null default 0
)
;

/* tablename: 预订单子表 */
create table so_preorder_b (cpreorderbid char(20) not null 
/*预订单子表主键*/,
pk_group varchar(20) null 
/*集团*/,
pk_org varchar(20) null 
/*销售组织*/,
ccustmaterialid varchar(20) null 
/*客户物料码*/,
cmaterialid varchar(20) null 
/*物料档案*/,
cmaterialvid varchar(20) null 
/*物料编码*/,
dbilldate char(19) null 
/*订货日期*/,
cunitid varchar(20) null 
/*主单位*/,
castunitid varchar(20) null 
/*单位*/,
nnum decimal(28,8) null 
/*主数量*/,
nastnum decimal(28,8) null 
/*数量*/,
vchangerate varchar(60) null 
/*换算率*/,
cqtunitid varchar(20) null 
/*报价单位*/,
nqtunitnum decimal(28,8) null 
/*报价数量*/,
vqtunitrate varchar(60) null 
/*报价换算率*/,
cvendorid varchar(20) null 
/*供应商*/,
cprojectid varchar(20) null 
/*项目*/,
cqualitylevelid varchar(20) null 
/*质量等级*/,
cproductorid varchar(20) null 
/*生产厂商*/,
ndiscountrate decimal(28,8) null 
/*整单折扣*/,
nitemdiscountrate decimal(28,8) null 
/*单品折扣*/,
csendstockorgid varchar(20) null 
/*发货库存组织最新版本*/,
csendstockorgvid varchar(20) null 
/*发货库存组织*/,
csendstordocid varchar(20) null 
/*发货仓库*/,
ctrafficorgid varchar(20) null 
/*物流组织最新版本*/,
ctrafficorgvid varchar(20) null 
/*物流组织*/,
csettleorgid varchar(20) null 
/*结算财务组织最新版本*/,
csettleorgvid varchar(20) null 
/*结算财务组织*/,
carorgid varchar(20) null 
/*应收组织最新版本*/,
carorgvid varchar(20) null 
/*应收组织*/,
cprofitcenterid varchar(20) null 
/*利润中心最新版本*/,
cprofitcentervid varchar(20) null 
/*利润中心*/,
creceivecustid varchar(20) null 
/*收货客户*/,
creceiveareaid varchar(20) null 
/*收货地区*/,
creceivesiteid varchar(20) null 
/*收货地点*/,
creceiveaddrid varchar(20) null 
/*收货地址*/,
cpriceformid varchar(20) null 
/*价格组成*/,
ccurrencyid varchar(20) null 
/*本位币*/,
corigcurrencyid varchar(20) null 
/*币种*/,
nexchangerate decimal(28,8) null 
/*折本汇率*/,
ngroupexchgrate decimal(28,8) null 
/*集团本位币汇率*/,
nglobalexchgrate decimal(28,8) null 
/*全局本位币汇率*/,
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
vbatchcode varchar(40) null 
/*批次*/,
pk_batchcode varchar(20) null 
/*批次档案*/,
ntaxrate decimal(28,8) null 
/*税率*/,
norigprice decimal(28,8) null 
/*主无税单价*/,
norigtaxprice decimal(28,8) null 
/*主含税单价*/,
norignetprice decimal(28,8) null 
/*主无税净价*/,
norigtaxnetprice decimal(28,8) null 
/*主含税净价*/,
norigmny decimal(28,8) null 
/*无税金额*/,
norigtaxmny decimal(28,8) null 
/*价税合计*/,
norigdiscount decimal(28,8) null 
/*折扣额*/,
nprice decimal(28,8) null 
/*主本币无税单价*/,
ntaxprice decimal(28,8) null 
/*主本币含税单价*/,
nnetprice decimal(28,8) null 
/*主本币无税净价*/,
ntaxnetprice decimal(28,8) null 
/*主本币含税净价*/,
ntax decimal(28,8) null 
/*税额*/,
nmny decimal(28,8) null 
/*本币无税金额*/,
ntaxmny decimal(28,8) null 
/*本币价税合计*/,
ndiscount decimal(28,8) null 
/*本币折扣额*/,
nqtorigtaxprice decimal(28,8) null 
/*含税单价*/,
nqtorigprice decimal(28,8) null 
/*无税单价*/,
nqtorigtaxnetprc decimal(28,8) null 
/*含税净价*/,
nqtorignetprice decimal(28,8) null 
/*无税净价*/,
nqttaxnetprice decimal(28,8) null 
/*本币含税净价*/,
nqtnetprice decimal(28,8) null 
/*本币无税净价*/,
nqttaxprice decimal(28,8) null 
/*本币含税单价*/,
nqtprice decimal(28,8) null 
/*本币无税单价*/,
naskqtorigtaxprc decimal(28,8) null 
/*询价主含税单价*/,
naskqtorigprice decimal(28,8) null 
/*询价主无税单价*/,
naskqtorigtxntprc decimal(28,8) null 
/*询价主含税净价*/,
naskqtorignetprice decimal(28,8) null 
/*询价主无税净价*/,
ngroupmny decimal(28,8) null 
/*集团本币无税金额*/,
ngrouptaxmny decimal(28,8) null 
/*集团本币价税合计*/,
nglobalmny decimal(28,8) null 
/*全局本币无税金额*/,
nglobaltaxmny decimal(28,8) null 
/*全局本币价税合计*/,
blargessflag char(1) null 
/*赠品*/,
crowno varchar(20) null 
/*行号*/,
vrownote varchar(181) null 
/*行备注*/,
frowstatus integer null 
/*行状态*/,
dsenddate varchar(19) null 
/*计划发货日期*/,
dreceivedate varchar(19) null 
/*要求到货日期*/,
cpricepolicyid varchar(20) null 
/*价格政策*/,
cpriceitemid varchar(20) null 
/*价格项目*/,
cpriceitemtableid varchar(50) null 
/*价目表*/,
carrangeid varchar(20) null 
/*最后安排人*/,
darrdate varchar(19) null 
/*最后安排日期*/,
narrnum decimal(28,8) null 
/*累计安排数量*/,
blineclose char(1) null 
/*行关闭*/,
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
cpreorderid varchar(20) null 
/*预订单主表_主键*/,
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
ncaltaxmny decimal(28,8) null 
/*计税金额*/,
 constraint pk_so_preorder_b primary key (cpreorderbid),
 ts char(19) null,
dr smallint null default 0
)
;

/* tablename: 预订单交易类型 */
create table so_m38trantype (pk_trantype char(20) not null 
/*预订单交易类型主键*/,
bmorerows char(1) null 
/*同一货物可否列多行*/,
faskqtrule integer null default 1 
/*询价规则*/,
bmodifyaskedqt char(1) null 
/*询到价格是否可改*/,
bmodifyunaskedqt char(1) null 
/*未询到价格是否可改*/,
flargessgetqtrule integer null 
/*赠品取价规则*/,
bmodifydiscount char(1) null 
/*允许修改折扣*/,
barrange char(1) null 
/*只能安排一次*/,
pk_group varchar(20) null 
/*集团*/,
vtrantypecode varchar(20) null 
/*交易类型编码*/,
bnofindpricehit char(1) null 
/*未询到价格是否提示*/,
fmodifymny integer null 
/*调整金额影响折扣还是单价*/,
ctrantypeid varchar(20) null 
/*交易类型*/,
 constraint pk_so_m38trantype primary key (pk_trantype),
 ts char(19) null,
dr smallint null default 0
)
;

/* tablename: 预订单迁移日志表 */
create table so_m38miglog (pk_miglog char(20) not null 
/*主键*/,
fmigstatus integer null 
/*迁移状态*/,
 constraint pk_so_m38miglog primary key (pk_miglog),
 ts char(19) null,
dr smallint null default 0
)
;

