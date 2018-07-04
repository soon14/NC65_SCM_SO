/* tablename: 销售出库结算单子实体 */
create table so_squareout_b (
csalesquarebid nchar(20) not null 
/*销售出库结算单子实体*/,
pk_org nvarchar(20) null 
/*结算财务组织*/,
dbilldate nchar(19) null 
/*销售出库单单据日期*/,
vctcode nvarchar(40) null 
/*合同号*/,
deffectdate nchar(19) null 
/*应收单起效日期*/,
dbizdate nvarchar(19) null 
/*销售出库单业务日期*/,
csquarebillid nvarchar(20) null 
/*销售出库单主实体*/,
csquarebillbid nvarchar(20) null 
/*销售出库单子实体*/,
vfirstcode nvarchar(40) null 
/*源头单据号*/,
vfirsttrantype nvarchar(20) null 
/*源头单据交易类型*/,
vfirsttype nvarchar(20) null 
/*源头单据类型*/,
cfirstid nvarchar(20) null 
/*源头单据主表*/,
cfirstbid nvarchar(20) null 
/*源头单据子表*/,
vfirstrowno nvarchar(20) null 
/*源头单据行号*/,
vsrccode nvarchar(40) null 
/*来源单据号*/,
vsrctype nvarchar(20) null 
/*来源单据类型*/,
vsrctrantype nvarchar(20) null 
/*来源单据交易类型*/,
csrcid nvarchar(20) null 
/*来源单据主表*/,
csrcbid nvarchar(20) null 
/*来源单据子表*/,
vsrcrowno nvarchar(20) null 
/*来源单据行号*/,
ccustmaterialid nvarchar(20) null 
/*客户物料码*/,
cmaterialid nvarchar(20) null 
/*物料*/,
cmaterialvid nvarchar(20) null 
/*物料版本*/,
cvendorid nvarchar(20) null 
/*供应商*/,
cproductorid nvarchar(20) null 
/*生产厂商*/,
cprojectid nvarchar(20) null 
/*项目ID*/,
ccustbankaccid nvarchar(20) null 
/*开户银行账户*/,
cordercustid nvarchar(20) null 
/*订单客户*/,
cinvoicecustid nvarchar(20) null 
/*订单开票客户*/,
cfreecustid nvarchar(20) null 
/*散户*/,
cpaytermid nvarchar(20) null 
/*订单收付款协议*/,
cchanneltypeid nvarchar(20) null 
/*订单渠道类型*/,
blargessflag nchar(1) null 
/*是否赠品*/,
carorgid nvarchar(20) null 
/*应收组织最新版本*/,
carorgvid nvarchar(20) null 
/*应收组织版本*/,
cprofitcenterid nvarchar(20) null 
/*结算利润中心最新版本*/,
cprofitcentervid nvarchar(20) null 
/*结算利润中心版本*/,
ccostorgid nvarchar(20) null 
/*成本域*/,
cdeptid nvarchar(20) null 
/*销售部门最新版本*/,
cdeptvid nvarchar(20) null 
/*销售部门版本*/,
csaleorgid nvarchar(20) null 
/*销售组织*/,
csaleorgvid nvarchar(20) null 
/*销售组织版本*/,
cemployeeid nvarchar(20) null 
/*销售业务员*/,
pk_batchcode nvarchar(20) null 
/*批次号档案*/,
vbatchcode nvarchar(40) null 
/*批次号*/,
cprolineid nvarchar(20) null 
/*产品线*/,
vrownote nvarchar(181) null 
/*行备注*/,
bsquarearfinish nchar(1) null 
/*是否应收结算完成*/,
bsquareiafinish nchar(1) null 
/*是否成本结算完成*/,
bincome nchar(1) null 
/*是否可以收入结算*/,
bcost nchar(1) null 
/*是否可以成本结算*/,
fpreartype int null 
/*待收入结算类型*/,
fpreiatype int null 
/*待成本结算类型*/,
nsquarearnum decimal(28,8) null 
/*累计确认应收数量*/,
nsquareestnum decimal(28,8) null 
/*累计暂估应收数量*/,
narrushnum decimal(28,8) null 
/*累计回冲应收数量*/,
nsquareianum decimal(28,8) null 
/*累计成本结算数量*/,
nsquareregnum decimal(28,8) null 
/*累计发出商品数量*/,
nrushnum decimal(28,8) null 
/*累计出库对冲数量*/,
ntotalpaymny decimal(28,8) null 
/*累计出库及下游发票收款核销金额*/,
ndownarmny decimal(28,8) null 
/*累计下游发票确认应收金额*/,
ndownarnum decimal(28,8) null 
/*累计下游发票确认应收数量*/,
ndownianum decimal(28,8) null 
/*累计下游发票成本结算数量*/,
nsquarearmny decimal(28,8) null 
/*累计确认应收金额*/,
corigcurrencyid nvarchar(20) null 
/*原币*/,
ccurrencyid nvarchar(20) null 
/*本币*/,
ntaxrate decimal(28,8) null 
/*税率*/,
nexchangerate decimal(28,8) null 
/*折本汇率*/,
cunitid nvarchar(20) null 
/*主单位*/,
castunitid nvarchar(20) null 
/*单位*/,
vchangerate nvarchar(60) null 
/*单位换算率*/,
nnum decimal(28,8) null 
/*主单位数量*/,
nastnum decimal(28,8) null 
/*单位数量*/,
nitemdiscountrate decimal(28,8) null 
/*单品折扣率*/,
norigtaxprice decimal(28,8) null 
/*原币含税单价*/,
norigprice decimal(28,8) null 
/*原币无税单价*/,
norigtaxnetprice decimal(28,8) null 
/*原币含税净价*/,
norignetprice decimal(28,8) null 
/*原币无税净价*/,
norigtax decimal(28,8) null 
/*原币税额*/,
norigmny decimal(28,8) null 
/*原币无税金额*/,
norigtaxmny decimal(28,8) null 
/*原币价税合计*/,
norigdiscount decimal(28,8) null 
/*原币折扣额*/,
ntaxprice decimal(28,8) null 
/*本币含税单价*/,
nprice decimal(28,8) null 
/*本币无税单价*/,
ntaxnetprice decimal(28,8) null 
/*本币含税净价*/,
nnetprice decimal(28,8) null 
/*本币无税净价*/,
ntax decimal(28,8) null 
/*本币税额*/,
nmny decimal(28,8) null 
/*本币无税金额*/,
ntaxmny decimal(28,8) null 
/*本币价税合计*/,
ndiscount decimal(28,8) null 
/*本币折扣额*/,
nglobaltaxmny decimal(28,8) null 
/*全局本币价税合计*/,
nglobalmny decimal(28,8) null 
/*全局本币无税金额*/,
ngrouptaxmny decimal(28,8) null 
/*集团本币价税合计*/,
ngroupmny decimal(28,8) null 
/*集团本币无税金额*/,
nglobalexchgrate decimal(28,8) null 
/*全局本位币汇率*/,
ngroupexchgrate decimal(28,8) null 
/*集团本位币汇率*/,
vfree1 nvarchar(101) null 
/*自由项1*/,
vfree2 nvarchar(101) null 
/*自由项2*/,
vfree3 nvarchar(101) null 
/*自由项3*/,
vfree4 nvarchar(101) null 
/*自由项4*/,
vfree5 nvarchar(101) null 
/*自由项5*/,
vfree6 nvarchar(101) null 
/*自由项6*/,
vfree7 nvarchar(101) null 
/*自由项7*/,
vfree8 nvarchar(101) null 
/*自由项8*/,
vfree9 nvarchar(101) null 
/*自由项9*/,
vfree10 nvarchar(101) null 
/*自由项10*/,
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
csalesquareid nvarchar(20) null 
/*销售出库结算单主实体_主键*/,
pk_group nvarchar(20) null 
/*集团*/,
ctaxcodeid nvarchar(20) null 
/*税码*/,
ftaxtypeflag int null 
/*扣税类别*/,
ncaltaxmny decimal(28,8) null 
/*计税金额*/,
csprofitcentervid nvarchar(20) null 
/*发货利润中心*/,
csprofitcenterid nvarchar(20) null 
/*发货利润中心最新版本*/,
cmffileid nvarchar(20) null 
/*特征码*/,
 constraint pk_so_squareout_b primary key (csalesquarebid),
 ts char(19) null default convert(char(19),getdate(),20),
dr smallint null default 0
)
go

/* tablename: 销售出库结算单主实体 */
create table so_squareout (
csalesquareid nchar(20) not null 
/*销售出库结算单主实体*/,
pk_org nvarchar(20) null 
/*结算财务组织*/,
pk_org_v nvarchar(20) null 
/*结算财务组织版本*/,
pk_group nvarchar(20) null 
/*集团*/,
cbiztypeid nvarchar(20) null 
/*业务流程*/,
csquarebillid nvarchar(20) null 
/*销售出库单主实体*/,
vbillcode nvarchar(40) null 
/*销售出库单单据号*/,
vtrantypecode nvarchar(20) null 
/*销售出库单交易类型*/,
dbilldate nchar(19) null 
/*销售出库单单据日期*/,
dbillsigndate nchar(19) null 
/*销售出库单签字日期*/,
cwhsmanagerid nvarchar(20) null 
/*销售出库单库管员*/,
csendstockorgid nvarchar(20) null 
/*库存组织最新版本*/,
csendstockorgvid nvarchar(20) null 
/*库存组织版本*/,
csendstordocid nvarchar(20) null 
/*仓库*/,
bautosquareincome nchar(1) null 
/*是否自动收入结算*/,
bautosquarecost nchar(1) null 
/*是否自动成本结算*/,
breturnoutstock nchar(1) null 
/*基于签收开票的退回出库单标志*/,
vnote nvarchar(181) null 
/*备注*/,
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
/*销售出库单交易类型主键*/,
csendcountryid nvarchar(20) null 
/*发货国家/地区*/,
crececountryid nvarchar(20) null 
/*收货国家/地区*/,
ctaxcountryid nvarchar(20) null 
/*报税国家/地区*/,
fbuysellflag int null 
/*购销类型*/,
btriatradeflag nchar(1) null 
/*三角贸易*/,
 constraint pk_so_squareout primary key (csalesquareid),
 ts char(19) null default convert(char(19),getdate(),20),
dr smallint null default 0
)
go

/* tablename: 销售出库结算单明细实体 */
create table so_squareout_d (
csalesquaredid nchar(20) not null 
/*销售出库结算单明细实体*/,
pk_org nvarchar(20) null 
/*结算财务组织*/,
csalesquareid nvarchar(20) null 
/*销售出库结算单主实体*/,
csalesquarebid nvarchar(20) null 
/*销售出库结算单子实体*/,
csquarebillid nvarchar(20) null 
/*销售出库单主实体*/,
csquarebillbid nvarchar(20) null 
/*销售出库单子实体*/,
dbilldate nchar(19) null 
/*销售出库单单据日期*/,
processeid nvarchar(20) null 
/*结算批次号*/,
fsquaretype int null 
/*结算类型*/,
bautosquareflag nchar(1) null 
/*是否自动结算*/,
nsquarenum decimal(28,8) null 
/*本次结算数量*/,
boutrushflag nchar(1) null 
/*是否出库对冲*/,
vrushbillcode nvarchar(40) null 
/*对冲出库单号*/,
crushgeneralbid nvarchar(20) null 
/*对冲出库单子表id*/,
crushoutbatchid nvarchar(20) null 
/*对冲批次号*/,
nastnum decimal(28,8) null 
/*单位数量*/,
nnum decimal(28,8) null 
/*主单位数量*/,
vchangerate nvarchar(60) null 
/*单位换算率*/,
castunitid nvarchar(20) null 
/*单位*/,
cunitid nvarchar(20) null 
/*主单位*/,
nexchangerate decimal(28,8) null 
/*折本汇率*/,
ntaxrate decimal(28,8) null 
/*税率*/,
ccurrencyid nvarchar(20) null 
/*本币*/,
corigcurrencyid nvarchar(20) null 
/*原币*/,
nitemdiscountrate decimal(28,8) null 
/*单品折扣率*/,
norigtaxprice decimal(28,8) null 
/*原币含税单价*/,
norigprice decimal(28,8) null 
/*原币无税单价*/,
norigtaxnetprice decimal(28,8) null 
/*原币含税净价*/,
norignetprice decimal(28,8) null 
/*原币无税净价*/,
norigtax decimal(28,8) null 
/*原币税额*/,
norigmny decimal(28,8) null 
/*原币无税金额*/,
norigtaxmny decimal(28,8) null 
/*原币价税合计*/,
norigdiscount decimal(28,8) null 
/*原币折扣额*/,
ntaxprice decimal(28,8) null 
/*本币含税单价*/,
nprice decimal(28,8) null 
/*本币无税单价*/,
ntaxnetprice decimal(28,8) null 
/*本币含税净价*/,
nnetprice decimal(28,8) null 
/*本币无税净价*/,
ntax decimal(28,8) null 
/*本币税额*/,
nmny decimal(28,8) null 
/*本币无税金额*/,
ntaxmny decimal(28,8) null 
/*本币价税合计*/,
ndiscount decimal(28,8) null 
/*本币折扣额*/,
nglobaltaxmny decimal(28,8) null 
/*全局本币价税合计*/,
nglobalmny decimal(28,8) null 
/*全局本币无税金额*/,
ngrouptaxmny decimal(28,8) null 
/*集团本币价税合计*/,
ngroupmny decimal(28,8) null 
/*集团本币无税金额*/,
nglobalexchgrate decimal(28,8) null 
/*全局本位币汇率*/,
ngroupexchgrate decimal(28,8) null 
/*集团本位币汇率*/,
pk_group nvarchar(20) null 
/*集团*/,
ctaxcodeid nvarchar(20) null 
/*税码*/,
ftaxtypeflag int null 
/*??税类别*/,
ncaltaxmny decimal(28,8) null 
/*计税金额*/,
ncostprice decimal(28,8) null 
/*成本单价*/,
ncostmny decimal(28,8) null 
/*成本金额*/,
 constraint pk_so_squareout_d primary key (csalesquaredid),
 ts char(19) null default convert(char(19),getdate(),20),
dr smallint null default 0
)
go

/* tablename: 销售发票结算单子实体 */
create table so_squareinv_b (
csalesquarebid nchar(20) not null 
/*销售发票结算单子实体*/,
pk_org nvarchar(20) null 
/*结算财务组织*/,
csquarebillid nvarchar(20) null 
/*销售发票主实体*/,
csquarebillbid nvarchar(20) null 
/*销售发票子实体*/,
vctcode nvarchar(40) null 
/*合同号*/,
deffectdate nchar(19) null 
/*应收单起效日期*/,
cprojectid nvarchar(20) null 
/*项目*/,
dbilldate nchar(19) null 
/*销售发票单据日期*/,
vfirstcode nvarchar(40) null 
/*源头单据号*/,
vfirsttrantype nvarchar(20) null 
/*源头单据交易类型*/,
vfirsttype nvarchar(20) null 
/*源头单据类型*/,
cfirstid nvarchar(20) null 
/*源头单据主表*/,
cfirstbid nvarchar(20) null 
/*源头单据子表*/,
vfirstrowno nvarchar(20) null 
/*源头单据行号*/,
vsrccode nvarchar(40) null 
/*来源单据号*/,
vsrctype nvarchar(20) null 
/*来源单据类型*/,
vsrctrantype nvarchar(20) null 
/*来源单据交易类型*/,
csrcid nvarchar(20) null 
/*来源单据主表*/,
csrcbid nvarchar(20) null 
/*来源单据子表*/,
vsrcrowno nvarchar(20) null 
/*来源单据行号*/,
ccustmaterialid nvarchar(20) null 
/*客户物料码*/,
cmaterialid nvarchar(20) null 
/*物料*/,
cmaterialvid nvarchar(20) null 
/*物料版本*/,
cvendorid nvarchar(20) null 
/*供应商*/,
cproductorid nvarchar(20) null 
/*生产厂商*/,
cordercustid nvarchar(20) null 
/*订单客户*/,
cchanneltypeid nvarchar(20) null 
/*订单渠道类型*/,
cfreecustid nvarchar(20) null 
/*散户*/,
blargessflag nchar(1) null 
/*是否赠品*/,
blaborflag nchar(1) null 
/*是否费用类*/,
bdiscountflag nchar(1) null 
/*是否折扣类*/,
carorgid nvarchar(20) null 
/*应收组织最新版本*/,
carorgvid nvarchar(20) null 
/*应收组织版本*/,
cprofitcenterid nvarchar(20) null 
/*利润中心最新版本*/,
cprofitcentervid nvarchar(20) null 
/*利润中心版本*/,
ccostorgid nvarchar(20) null 
/*成本域*/,
csendstockorgid nvarchar(20) null 
/*库存组织最新版本*/,
csendstockorgvid nvarchar(20) null 
/*库存组织版本*/,
csendstordocid nvarchar(20) null 
/*仓库*/,
cdeptid nvarchar(20) null 
/*销售部门最新版本*/,
cdeptvid nvarchar(20) null 
/*销售部门版本*/,
csaleorgid nvarchar(20) null 
/*销售组织最新版本*/,
csaleorgvid nvarchar(20) null 
/*销售组织版本*/,
cemployeeid nvarchar(20) null 
/*销售业务员*/,
pk_batchcode nvarchar(20) null 
/*批次号档案*/,
vbatchcode nvarchar(40) null 
/*批次号*/,
cprolineid nvarchar(20) null 
/*产品线*/,
vrownote nvarchar(181) null 
/*行备注*/,
bsquarearfinish nchar(1) null 
/*是否应收结算完成*/,
bsquareiafinish nchar(1) null 
/*是否成本结算完成*/,
bincome nchar(1) null 
/*是否可以收入结算*/,
bcost nchar(1) null 
/*是否可以成本结算*/,
fpreartype int null 
/*待收入结算类型*/,
fpreiatype int null 
/*待成本结算类型*/,
nsquarearnum decimal(28,8) null 
/*累计确认应收数量*/,
narrushnum decimal(28,8) null 
/*累计回冲应收数量*/,
nsquareianum decimal(28,8) null 
/*累计成本结算数量*/,
nsquareregnum decimal(28,8) null 
/*累计发出商品数量*/,
corigcurrencyid nvarchar(20) null 
/*原币*/,
ccurrencyid nvarchar(20) null 
/*本币*/,
ntaxrate decimal(28,8) null 
/*税率*/,
nexchangerate decimal(28,8) null 
/*折本汇率*/,
cunitid nvarchar(20) null 
/*主单位*/,
castunitid nvarchar(20) null 
/*单位*/,
vchangerate nvarchar(60) null 
/*单位换算率*/,
nnum decimal(28,8) null 
/*主单位数量*/,
nastnum decimal(28,8) null 
/*单位数量*/,
nitemdiscountrate decimal(28,8) null 
/*单品折扣率*/,
norigtaxprice decimal(28,8) null 
/*原币含税单价*/,
norigprice decimal(28,8) null 
/*原币无税单价*/,
norigtaxnetprice decimal(28,8) null 
/*原币含税净价*/,
norignetprice decimal(28,8) null 
/*原币无税净价*/,
norigtax decimal(28,8) null 
/*原币税额*/,
norigmny decimal(28,8) null 
/*原币无税金额*/,
norigtaxmny decimal(28,8) null 
/*原币价税合计*/,
norigdiscount decimal(28,8) null 
/*原币折扣额*/,
ntaxprice decimal(28,8) null 
/*本币含税单价*/,
nprice decimal(28,8) null 
/*本币无税单价*/,
ntaxnetprice decimal(28,8) null 
/*本币含税净价*/,
nnetprice decimal(28,8) null 
/*本币无税净价*/,
ntax decimal(28,8) null 
/*本币税额*/,
nmny decimal(28,8) null 
/*本币无税金额*/,
ntaxmny decimal(28,8) null 
/*本币价税合计*/,
ndiscount decimal(28,8) null 
/*本币折扣额*/,
nglobaltaxmny decimal(28,8) null 
/*全局本币价税合计*/,
nglobalmny decimal(28,8) null 
/*全局本币无税金额*/,
ngrouptaxmny decimal(28,8) null 
/*集团本币价税合计*/,
ngroupmny decimal(28,8) null 
/*集团本币无税金额*/,
nglobalexchgrate decimal(28,8) null 
/*全局本位币汇率*/,
ngroupexchgrate decimal(28,8) null 
/*集团本位币汇率*/,
vfree1 nvarchar(101) null 
/*自由项1*/,
vfree2 nvarchar(101) null 
/*自由项2*/,
vfree3 nvarchar(101) null 
/*自由项3*/,
vfree4 nvarchar(101) null 
/*自由项4*/,
vfree5 nvarchar(101) null 
/*自由项5*/,
vfree6 nvarchar(101) null 
/*自由项6*/,
vfree7 nvarchar(101) null 
/*自由项7*/,
vfree8 nvarchar(101) null 
/*自由项8*/,
vfree9 nvarchar(101) null 
/*自由项9*/,
vfree10 nvarchar(101) null 
/*自由项10*/,
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
csalesquareid nvarchar(20) null 
/*销售发票结算单主实体_主键*/,
pk_group nvarchar(20) null 
/*集团*/,
ctaxcodeid nvarchar(20) null 
/*税码*/,
ftaxtypeflag int null 
/*扣税类别*/,
ncaltaxmny decimal(28,8) null 
/*计税金额*/,
ccostsubjid nvarchar(20) null 
/*收支项目*/,
csprofitcentervid nvarchar(20) null 
/*发货利润中心*/,
csprofitcenterid nvarchar(20) null 
/*发货利润中心最新版*/,
cmffileid nvarchar(20) null 
/*特征码*/,
 constraint pk_so_squareinv_b primary key (csalesquarebid),
 ts char(19) null default convert(char(19),getdate(),20),
dr smallint null default 0
)
go

/* tablename: 销售发票结算单主实体 */
create table so_squareinv (
csalesquareid nchar(20) not null 
/*销售发票结算单主实体*/,
pk_org nvarchar(20) null 
/*结算财务组织*/,
pk_org_v nvarchar(20) null 
/*结算财务组织版本*/,
pk_group nvarchar(20) null 
/*集团*/,
cbiztypeid nvarchar(20) null 
/*业务流程*/,
ccustbankaccid nvarchar(20) null 
/*开户银行账户*/,
csquarebillid nvarchar(20) null 
/*销售发票主实体*/,
cinvoicecustid nvarchar(20) null 
/*开票客户*/,
vbillcode nvarchar(40) null 
/*销售发票单据号*/,
vtrantypecode nvarchar(20) null 
/*销售发票类型编码*/,
dbilldate nchar(19) null 
/*销售发票单据日期*/,
dbillapprovedate nchar(19) null 
/*销售发票审核日期*/,
cpaytermid nvarchar(20) null 
/*发票收付款协议*/,
bautosquareincome nchar(1) null 
/*是否自动收入结算*/,
bautosquarecost nchar(1) null 
/*是否自动成本结算*/,
vnote nvarchar(181) null 
/*备注*/,
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
/*销售发票类型*/,
csendcountryid nvarchar(20) null 
/*发货国家/地区*/,
crececountryid nvarchar(20) null 
/*收货国家/地区*/,
ctaxcountryid nvarchar(20) null 
/*报税国家/地区*/,
fbuysellflag int null 
/*购销类型*/,
btriatradeflag nchar(1) null 
/*三角贸易*/,
vvatcode nvarchar(40) null 
/*VAT注册码*/,
vcustvatcode nvarchar(40) null 
/*客户VAT注册码*/,
cbalancetypeid nvarchar(20) null 
/*结算方式*/,
 constraint pk_so_squareinv primary key (csalesquareid),
 ts char(19) null default convert(char(19),getdate(),20),
dr smallint null default 0
)
go

/* tablename: 销售发票结算单明细实体 */
create table so_squareinv_d (
csalesquaredid nchar(20) not null 
/*销售发票结算单明细实体*/,
pk_org nvarchar(20) null 
/*结算财务组织*/,
csalesquareid nvarchar(20) null 
/*销售发票结算单主实体*/,
csalesquarebid nvarchar(20) null 
/*销售发票结算单子实体*/,
csquarebillid nvarchar(20) null 
/*销售发票主实体*/,
csquarebillbid nvarchar(20) null 
/*销售发票子实体*/,
dbilldate nchar(19) null 
/*销售发票单据日期*/,
processeid nvarchar(20) null 
/*结算批次号*/,
fsquaretype int null 
/*结算类型*/,
nsquarenum decimal(28,8) null 
/*本次结算数量*/,
nastnum decimal(28,8) null 
/*单位数量*/,
nnum decimal(28,8) null 
/*主单位数量*/,
vchangerate nvarchar(60) null 
/*单位换算率*/,
castunitid nvarchar(20) null 
/*单位*/,
cunitid nvarchar(20) null 
/*主单位*/,
nexchangerate decimal(28,8) null 
/*折本汇率*/,
ntaxrate decimal(28,8) null 
/*税率*/,
ccurrencyid nvarchar(20) null 
/*本币*/,
corigcurrencyid nvarchar(20) null 
/*原币*/,
nitemdiscountrate decimal(28,8) null 
/*单品折扣率*/,
norigtaxprice decimal(28,8) null 
/*原币含税单价*/,
norigprice decimal(28,8) null 
/*原币无税单价*/,
norigtaxnetprice decimal(28,8) null 
/*原币含税净价*/,
norignetprice decimal(28,8) null 
/*原币无税净价*/,
norigtax decimal(28,8) null 
/*原币税额*/,
norigmny decimal(28,8) null 
/*原币无税金额*/,
norigtaxmny decimal(28,8) null 
/*原币价税合计*/,
norigdiscount decimal(28,8) null 
/*原币折扣额*/,
ntaxprice decimal(28,8) null 
/*本币含税单价*/,
nprice decimal(28,8) null 
/*本币无税单价*/,
ntaxnetprice decimal(28,8) null 
/*本币含税净价*/,
nnetprice decimal(28,8) null 
/*本币无税净价*/,
ntax decimal(28,8) null 
/*本币税额*/,
nmny decimal(28,8) null 
/*本币无税金额*/,
ntaxmny decimal(28,8) null 
/*本币价税合计*/,
ndiscount decimal(28,8) null 
/*本币折扣额*/,
nglobaltaxmny decimal(28,8) null 
/*全局本币价税合计*/,
nglobalmny decimal(28,8) null 
/*全局本币无税金额*/,
ngrouptaxmny decimal(28,8) null 
/*集团本币价税合计*/,
ngroupmny decimal(28,8) null 
/*集团本币无税金额*/,
nglobalexchgrate decimal(28,8) null 
/*全局本位币汇率*/,
ngroupexchgrate decimal(28,8) null 
/*集团本位币汇率*/,
pk_group nvarchar(20) null 
/*集团*/,
ctaxcodeid nvarchar(20) null 
/*税码*/,
ftaxtypeflag int null 
/*扣税类别*/,
ncaltaxmny decimal(28,8) null 
/*计税金额*/,
 constraint pk_so_squareinv_d primary key (csalesquaredid),
 ts char(19) null default convert(char(19),getdate(),20),
dr smallint null default 0
)
go

/* tablename: 途损结算单子实体 */
create table so_squarewas_b (
csalesquarebid nchar(20) not null 
/*途损结算单子实体*/,
pk_org nvarchar(20) null 
/*结算财务组织*/,
dbilldate nchar(19) null 
/*途损单单据日期*/,
dbizdate nchar(19) null 
/*销售出库单业务日期*/,
vctcode nvarchar(40) null 
/*合同号*/,
deffectdate nchar(19) null 
/*应收单起效日期*/,
csquarebillid nvarchar(20) null 
/*途损单主实体*/,
csquarebillbid nvarchar(20) null 
/*途损单子实体*/,
vfirstcode nvarchar(40) null 
/*源头单据号*/,
vfirsttrantype nvarchar(20) null 
/*源头单据交易类型*/,
vfirsttype nvarchar(20) null 
/*源头单据类型*/,
cfirstid nvarchar(20) null 
/*源头单据主表*/,
cfirstbid nvarchar(20) null 
/*源头单据子表*/,
vfirstrowno nvarchar(20) null 
/*源头单据行号*/,
vsrccode nvarchar(40) null 
/*来源单据号*/,
vsrctype nvarchar(20) null 
/*来源单据类型*/,
vsrctrantype nvarchar(20) null 
/*来源单据交易类型*/,
csrcid nvarchar(20) null 
/*来源单据主表*/,
csrcbid nvarchar(20) null 
/*来源单据子表*/,
vsrcrowno nvarchar(20) null 
/*来源单据行号*/,
ccustmaterialid nvarchar(20) null 
/*客户物料码*/,
cmaterialid nvarchar(20) null 
/*物料*/,
cmaterialvid nvarchar(20) null 
/*物料版本*/,
cvendorid nvarchar(20) null 
/*供应商*/,
cproductorid nvarchar(20) null 
/*生产厂商*/,
cprojectid nvarchar(20) null 
/*项目ID*/,
cordercustid nvarchar(20) null 
/*订单客户*/,
ccustbankaccid nvarchar(20) null 
/*开户银行账户*/,
cinvoicecustid nvarchar(20) null 
/*订单开票客户*/,
cfreecustid nchar(20) null 
/*散户*/,
cpaytermid nvarchar(20) null 
/*订单收付款协议*/,
cchanneltypeid nvarchar(20) null 
/*订单渠道类型*/,
blargessflag nchar(1) null 
/*是否赠品*/,
carorgid nvarchar(20) null 
/*应收组织最新版本*/,
carorgvid nvarchar(20) null 
/*应收组织版本*/,
cprofitcenterid nvarchar(20) null 
/*利润中心最新版本*/,
cprofitcentervid nvarchar(20) null 
/*利润中心版本*/,
ccostorgid nvarchar(20) null 
/*成本域*/,
cdeptid nvarchar(20) null 
/*销售部门最新版本*/,
cdeptvid nvarchar(20) null 
/*销售部门版本*/,
csaleorgid nvarchar(20) null 
/*销售组织最新版本*/,
csaleorgvid nvarchar(20) null 
/*销售组织版本*/,
cemployeeid nvarchar(20) null 
/*销售业务员*/,
pk_batchcode nvarchar(20) null 
/*批次号档案*/,
vbatchcode nvarchar(40) null 
/*批次号*/,
cprolineid nvarchar(20) null 
/*产品线*/,
vrownote nvarchar(181) null 
/*行备注*/,
bsquarearfinish nchar(1) null 
/*是否应收结算完成*/,
bsquareiafinish nchar(1) null 
/*是否成本结算完成*/,
bincome nchar(1) null 
/*是否可以收入结算*/,
bcost nchar(1) null 
/*是否可以成本结算*/,
fpreartype int null 
/*待收入结算类型*/,
fpreiatype int null 
/*待成本结算类型*/,
nsquarearnum decimal(28,8) null 
/*累计确认应收数量*/,
narrushnum decimal(28,8) null 
/*累计回冲应收数量*/,
nsquareianum decimal(28,8) null 
/*累计成本结算数量*/,
nsquareregnum decimal(28,8) null 
/*累计发出商品数量*/,
corigcurrencyid nvarchar(20) null 
/*原币*/,
ccurrencyid nvarchar(20) null 
/*本币*/,
ntaxrate decimal(28,8) null 
/*税率*/,
nexchangerate decimal(28,8) null 
/*折本汇率*/,
cunitid nvarchar(20) null 
/*主单位*/,
castunitid nvarchar(20) null 
/*单位*/,
vchangerate nvarchar(60) null 
/*单位换算率*/,
nnum decimal(28,8) null 
/*主单位数量*/,
nastnum decimal(28,8) null 
/*单位数量*/,
nitemdiscountrate decimal(28,8) null 
/*单品折扣率*/,
norigtaxprice decimal(28,8) null 
/*原币含税单价*/,
norigprice decimal(28,8) null 
/*原币无税单价*/,
norigtaxnetprice decimal(28,8) null 
/*原币含税净价*/,
norignetprice decimal(28,8) null 
/*原币无税净价*/,
norigtax decimal(28,8) null 
/*原币税额*/,
norigmny decimal(28,8) null 
/*原币无税金额*/,
norigtaxmny decimal(28,8) null 
/*原币价税合计*/,
norigdiscount decimal(28,8) null 
/*原币折扣额*/,
ntaxprice decimal(28,8) null 
/*本币含税单价*/,
nprice decimal(28,8) null 
/*本币无税单价*/,
ntaxnetprice decimal(28,8) null 
/*本币含税净价*/,
nnetprice decimal(28,8) null 
/*本币无税净价*/,
ntax decimal(28,8) null 
/*本币税额*/,
nmny decimal(28,8) null 
/*本币无税金额*/,
ntaxmny decimal(28,8) null 
/*本币价税合计*/,
ndiscount decimal(28,8) null 
/*本币折扣额*/,
nglobaltaxmny decimal(28,8) null 
/*全局本币价税合计*/,
nglobalmny decimal(28,8) null 
/*全局本币无税金额*/,
ngrouptaxmny decimal(28,8) null 
/*集团本币价税合计*/,
ngroupmny decimal(28,8) null 
/*集团本币无税金额*/,
nglobalexchgrate decimal(28,8) null 
/*全局本位币汇率*/,
ngroupexchgrate decimal(28,8) null 
/*集团本位币汇率*/,
vfree1 nvarchar(101) null 
/*自由项1*/,
vfree2 nvarchar(101) null 
/*自由项2*/,
vfree3 nvarchar(101) null 
/*自由项3*/,
vfree4 nvarchar(101) null 
/*自由项4*/,
vfree5 nvarchar(101) null 
/*自由项5*/,
vfree6 nvarchar(101) null 
/*自由项6*/,
vfree7 nvarchar(101) null 
/*自由项7*/,
vfree8 nvarchar(101) null 
/*自由项8*/,
vfree9 nvarchar(101) null 
/*自由项9*/,
vfree10 nvarchar(101) null 
/*自由项10*/,
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
csalesquareid nvarchar(20) null 
/*途损结算单主实体_主键*/,
pk_group nvarchar(20) null 
/*集团*/,
ctaxcodeid nvarchar(20) null 
/*税码*/,
ftaxtypeflag int null 
/*扣税类别*/,
ncaltaxmny decimal(28,8) null 
/*计税金额*/,
cmffileid nvarchar(20) null 
/*特征码*/,
 constraint pk_so_squarewas_b primary key (csalesquarebid),
 ts char(19) null default convert(char(19),getdate(),20),
dr smallint null default 0
)
go

/* tablename: 途损结算单主实体 */
create table so_squarewas (
csalesquareid nchar(20) not null 
/*途损结算单主实体*/,
pk_org nvarchar(20) null 
/*结算财务组织*/,
pk_org_v nvarchar(20) null 
/*结算财务组织版本*/,
pk_group nvarchar(20) null 
/*集团*/,
cbiztypeid nvarchar(20) null 
/*业务流程*/,
csquarebillid nvarchar(20) null 
/*途损单主实体*/,
vbillcode nvarchar(40) null 
/*途损单单据号*/,
vtrantypecode nvarchar(20) null 
/*途损单交易类型*/,
dbilldate nchar(19) null 
/*途损单单据日期*/,
cwhsmanagerid nvarchar(20) null 
/*销售出库单库管员*/,
bautosquareincome nchar(1) null 
/*是否自动收入结算*/,
bautosquarecost nchar(1) null 
/*是否自动成本结算*/,
vnote nvarchar(181) null 
/*备注*/,
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
csendstockorgid nvarchar(20) null 
/*库存组织最新版本*/,
csendstockorgvid nvarchar(20) null 
/*库存组织版本*/,
csendstordocid nvarchar(20) null 
/*仓库*/,
btriatradeflag nchar(1) null 
/*三角贸易*/,
crececountryid nvarchar(20) null 
/*收货国家/地区*/,
ctaxcountryid nvarchar(20) null 
/*报税国家/地区*/,
ctrantypeid nvarchar(20) null 
/*途损单交易类型主键*/,
csendcountryid nvarchar(20) null 
/*发货国家/地区*/,
fbuysellflag int null 
/*购销类型*/,
 constraint pk_so_sqwas primary key (csalesquareid),
 ts char(19) null default convert(char(19),getdate(),20),
dr smallint null default 0
)
go

/* tablename: 途损结算单明细实体 */
create table so_squarewas_d (
csalesquaredid nchar(20) not null 
/*途损结算单明细实体*/,
pk_org nvarchar(20) null 
/*结算财务组织*/,
csalesquareid nvarchar(20) null 
/*途损结算单主实体*/,
csalesquarebid nvarchar(20) null 
/*途损结算单子实体*/,
csquarebillid nvarchar(20) null 
/*途损单主实体*/,
csquarebillbid nvarchar(20) null 
/*途损单子实体*/,
dbilldate nchar(19) null 
/*途损单单据日期*/,
processeid nvarchar(20) null 
/*结算批次号*/,
fsquaretype int null 
/*结算类型*/,
nsquarenum decimal(28,8) null 
/*本次结算数量*/,
nastnum decimal(28,8) null 
/*单位数量*/,
nnum decimal(28,8) null 
/*主单位数量*/,
vchangerate nvarchar(60) null 
/*单位换算率*/,
castunitid nvarchar(20) null 
/*单位*/,
cunitid nvarchar(20) null 
/*主单位*/,
nexchangerate decimal(28,8) null 
/*折本汇率*/,
ntaxrate decimal(28,8) null 
/*税率*/,
ccurrencyid nvarchar(20) null 
/*本币*/,
corigcurrencyid nvarchar(20) null 
/*原币*/,
nitemdiscountrate decimal(28,8) null 
/*单品折扣率*/,
norigtaxprice decimal(28,8) null 
/*原币含税单价*/,
norigprice decimal(28,8) null 
/*原币无税单价*/,
norigtaxnetprice decimal(28,8) null 
/*原币含税净价*/,
norignetprice decimal(28,8) null 
/*原币无税净价*/,
norigtax decimal(28,8) null 
/*原币税额*/,
norigmny decimal(28,8) null 
/*原币无税金额*/,
norigtaxmny decimal(28,8) null 
/*原币价税合计*/,
norigdiscount decimal(28,8) null 
/*原币折扣额*/,
ntaxprice decimal(28,8) null 
/*本币含税单价*/,
nprice decimal(28,8) null 
/*本币无税单价*/,
ntaxnetprice decimal(28,8) null 
/*本币含税净价*/,
nnetprice decimal(28,8) null 
/*本币无税净价*/,
ntax decimal(28,8) null 
/*本币税额*/,
nmny decimal(28,8) null 
/*本币无税金额*/,
ntaxmny decimal(28,8) null 
/*本币价税合计*/,
ndiscount decimal(28,8) null 
/*本币折扣额*/,
nglobaltaxmny decimal(28,8) null 
/*全局本币价税合计*/,
nglobalmny decimal(28,8) null 
/*全局本币无税金额*/,
ngrouptaxmny decimal(28,8) null 
/*集团本币价税合计*/,
ngroupmny decimal(28,8) null 
/*集团本币无税金额*/,
nglobalexchgrate decimal(28,8) null 
/*全局本位币汇率*/,
ngroupexchgrate decimal(28,8) null 
/*集团本位币汇率*/,
pk_group nvarchar(20) null 
/*集团*/,
ctaxcodeid nvarchar(20) null 
/*税码*/,
ftaxtypeflag int null 
/*扣税类别*/,
ncaltaxmny decimal(28,8) null 
/*计税金额*/,
 constraint pk_so_squarewas_d primary key (csalesquaredid),
 ts char(19) null default convert(char(19),getdate(),20),
dr smallint null default 0
)
go

