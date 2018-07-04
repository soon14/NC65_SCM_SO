/* tablename: 销售出库结算单子实体 */
create table so_squareout_b (csalesquarebid char(20) not null 
/*销售出库结算单子实体*/,
pk_org varchar(20) null 
/*结算财务组织*/,
dbilldate char(19) null 
/*销售出库单单据日期*/,
vctcode varchar(40) null 
/*合同号*/,
deffectdate char(19) null 
/*应收单起效日期*/,
dbizdate varchar(19) null 
/*销售出库单业务日期*/,
csquarebillid varchar(20) null 
/*销售出库单主实体*/,
csquarebillbid varchar(20) null 
/*销售出库单子实体*/,
vfirstcode varchar(40) null 
/*源头单据号*/,
vfirsttrantype varchar(20) null 
/*源头单据交易类型*/,
vfirsttype varchar(20) null 
/*源头单据类型*/,
cfirstid varchar(20) null 
/*源头单据主表*/,
cfirstbid varchar(20) null 
/*源头单据子表*/,
vfirstrowno varchar(20) null 
/*源头单据行号*/,
vsrccode varchar(40) null 
/*来源单据号*/,
vsrctype varchar(20) null 
/*来源单据类型*/,
vsrctrantype varchar(20) null 
/*来源单据交易类型*/,
csrcid varchar(20) null 
/*来源单据主表*/,
csrcbid varchar(20) null 
/*来源单据子表*/,
vsrcrowno varchar(20) null 
/*来源单据行号*/,
ccustmaterialid varchar(20) null 
/*客户物料码*/,
cmaterialid varchar(20) null 
/*物料*/,
cmaterialvid varchar(20) null 
/*物料版本*/,
cvendorid varchar(20) null 
/*供应商*/,
cproductorid varchar(20) null 
/*生产厂商*/,
cprojectid varchar(20) null 
/*项目ID*/,
ccustbankaccid varchar(20) null 
/*开户银行账户*/,
cordercustid varchar(20) null 
/*订单客户*/,
cinvoicecustid varchar(20) null 
/*订单开票客户*/,
cfreecustid varchar(20) null 
/*散户*/,
cpaytermid varchar(20) null 
/*订单收付款协议*/,
cchanneltypeid varchar(20) null 
/*订单渠道类型*/,
blargessflag char(1) null 
/*是否赠品*/,
carorgid varchar(20) null 
/*应收组织最新版本*/,
carorgvid varchar(20) null 
/*应收组织版本*/,
cprofitcenterid varchar(20) null 
/*结算利润中心最新版本*/,
cprofitcentervid varchar(20) null 
/*结算利润中心版本*/,
ccostorgid varchar(20) null 
/*成本域*/,
cdeptid varchar(20) null 
/*销售部门最新版本*/,
cdeptvid varchar(20) null 
/*销售部门版本*/,
csaleorgid varchar(20) null 
/*销售组织*/,
csaleorgvid varchar(20) null 
/*销售组织版本*/,
cemployeeid varchar(20) null 
/*销售业务员*/,
pk_batchcode varchar(20) null 
/*批次号档案*/,
vbatchcode varchar(40) null 
/*批次号*/,
cprolineid varchar(20) null 
/*产品线*/,
vrownote varchar(181) null 
/*行备注*/,
bsquarearfinish char(1) null 
/*是否应收结算完成*/,
bsquareiafinish char(1) null 
/*是否成本结算完成*/,
bincome char(1) null 
/*是否可以收入结算*/,
bcost char(1) null 
/*是否可以成本结算*/,
fpreartype integer null 
/*待收入结算类型*/,
fpreiatype integer null 
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
corigcurrencyid varchar(20) null 
/*原币*/,
ccurrencyid varchar(20) null 
/*本币*/,
ntaxrate decimal(28,8) null 
/*税率*/,
nexchangerate decimal(28,8) null 
/*折本汇率*/,
cunitid varchar(20) null 
/*主单位*/,
castunitid varchar(20) null 
/*单位*/,
vchangerate varchar(60) null 
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
vfree1 varchar(101) null 
/*自由项1*/,
vfree2 varchar(101) null 
/*自由项2*/,
vfree3 varchar(101) null 
/*自由项3*/,
vfree4 varchar(101) null 
/*自由项4*/,
vfree5 varchar(101) null 
/*自由项5*/,
vfree6 varchar(101) null 
/*自由项6*/,
vfree7 varchar(101) null 
/*自由项7*/,
vfree8 varchar(101) null 
/*自由项8*/,
vfree9 varchar(101) null 
/*自由项9*/,
vfree10 varchar(101) null 
/*自由项10*/,
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
csalesquareid varchar(20) null 
/*销售出库结算单主实体_主键*/,
pk_group varchar(20) null 
/*集团*/,
ctaxcodeid varchar(20) null 
/*税码*/,
ftaxtypeflag integer null 
/*扣税类别*/,
ncaltaxmny decimal(28,8) null 
/*计税金额*/,
csprofitcentervid varchar(20) null 
/*发货利润中心*/,
csprofitcenterid varchar(20) null 
/*发货利润中心最新版本*/,
cmffileid varchar(20) null 
/*特征码*/,
 constraint pk_so_squareout_b primary key (csalesquarebid),
 ts char(19) null,
dr smallint null default 0
)
;

/* tablename: 销售出库结算单主实体 */
create table so_squareout (csalesquareid char(20) not null 
/*销售出库结算单主实体*/,
pk_org varchar(20) null 
/*结算财务组织*/,
pk_org_v varchar(20) null 
/*结算财务组织版本*/,
pk_group varchar(20) null 
/*集团*/,
cbiztypeid varchar(20) null 
/*业务流程*/,
csquarebillid varchar(20) null 
/*销售出库单主实体*/,
vbillcode varchar(40) null 
/*销售出库单单据号*/,
vtrantypecode varchar(20) null 
/*销售出库单交易类型*/,
dbilldate char(19) null 
/*销售出库单单据日期*/,
dbillsigndate char(19) null 
/*销售出库单签字日期*/,
cwhsmanagerid varchar(20) null 
/*销售出库单库管员*/,
csendstockorgid varchar(20) null 
/*库存组织最新版本*/,
csendstockorgvid varchar(20) null 
/*库存组织版本*/,
csendstordocid varchar(20) null 
/*仓库*/,
bautosquareincome char(1) null 
/*是否自动收入结算*/,
bautosquarecost char(1) null 
/*是否自动成本结算*/,
breturnoutstock char(1) null 
/*基于签收开票的退回出库单标志*/,
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
ctrantypeid varchar(20) null 
/*销售出库单交易类型主键*/,
csendcountryid varchar(20) null 
/*发货国家/地区*/,
crececountryid varchar(20) null 
/*收货国家/地区*/,
ctaxcountryid varchar(20) null 
/*报税国家/地区*/,
fbuysellflag integer null 
/*购销类型*/,
btriatradeflag char(1) null 
/*三角贸易*/,
 constraint pk_so_squareout primary key (csalesquareid),
 ts char(19) null,
dr smallint null default 0
)
;

/* tablename: 销售出库结算单明细实体 */
create table so_squareout_d (csalesquaredid char(20) not null 
/*销售出库结算单明细实体*/,
pk_org varchar(20) null 
/*结算财务组织*/,
csalesquareid varchar(20) null 
/*销售出库结算单主实体*/,
csalesquarebid varchar(20) null 
/*销售出库结算单子实体*/,
csquarebillid varchar(20) null 
/*销售出库单主实体*/,
csquarebillbid varchar(20) null 
/*销售出库单子实体*/,
dbilldate char(19) null 
/*销售出库单单据日期*/,
processeid varchar(20) null 
/*结算批次号*/,
fsquaretype integer null 
/*结算类型*/,
bautosquareflag char(1) null 
/*是否自动结算*/,
nsquarenum decimal(28,8) null 
/*本次结算数量*/,
boutrushflag char(1) null 
/*是否出库对冲*/,
vrushbillcode varchar(40) null 
/*对冲出库单号*/,
crushgeneralbid varchar(20) null 
/*对冲出库单子表id*/,
crushoutbatchid varchar(20) null 
/*对冲批次号*/,
nastnum decimal(28,8) null 
/*单位数量*/,
nnum decimal(28,8) null 
/*主单位数量*/,
vchangerate varchar(60) null 
/*单位换算率*/,
castunitid varchar(20) null 
/*单位*/,
cunitid varchar(20) null 
/*主单位*/,
nexchangerate decimal(28,8) null 
/*折本汇率*/,
ntaxrate decimal(28,8) null 
/*税率*/,
ccurrencyid varchar(20) null 
/*本币*/,
corigcurrencyid varchar(20) null 
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
pk_group varchar(20) null 
/*集团*/,
ctaxcodeid varchar(20) null 
/*税码*/,
ftaxtypeflag integer null 
/*??税类别*/,
ncaltaxmny decimal(28,8) null 
/*计税金额*/,
ncostprice decimal(28,8) null 
/*成本单价*/,
ncostmny decimal(28,8) null 
/*成本金额*/,
 constraint pk_so_squareout_d primary key (csalesquaredid),
 ts char(19) null,
dr smallint null default 0
)
;

/* tablename: 销售发票结算单子实体 */
create table so_squareinv_b (csalesquarebid char(20) not null 
/*销售发票结算单子实体*/,
pk_org varchar(20) null 
/*结算财务组织*/,
csquarebillid varchar(20) null 
/*销售发票主实体*/,
csquarebillbid varchar(20) null 
/*销售发票子实体*/,
vctcode varchar(40) null 
/*合同号*/,
deffectdate char(19) null 
/*应收单起效日期*/,
cprojectid varchar(20) null 
/*项目*/,
dbilldate char(19) null 
/*销售发票单据日期*/,
vfirstcode varchar(40) null 
/*源头单据号*/,
vfirsttrantype varchar(20) null 
/*源头单据交易类型*/,
vfirsttype varchar(20) null 
/*源头单据类型*/,
cfirstid varchar(20) null 
/*源头单据主表*/,
cfirstbid varchar(20) null 
/*源头单据子表*/,
vfirstrowno varchar(20) null 
/*源头单据行号*/,
vsrccode varchar(40) null 
/*来源单据号*/,
vsrctype varchar(20) null 
/*来源单据类型*/,
vsrctrantype varchar(20) null 
/*来源单据交易类型*/,
csrcid varchar(20) null 
/*来源单据主表*/,
csrcbid varchar(20) null 
/*来源单据子表*/,
vsrcrowno varchar(20) null 
/*来源单据行号*/,
ccustmaterialid varchar(20) null 
/*客户物料码*/,
cmaterialid varchar(20) null 
/*物料*/,
cmaterialvid varchar(20) null 
/*物料版本*/,
cvendorid varchar(20) null 
/*供应商*/,
cproductorid varchar(20) null 
/*生产厂商*/,
cordercustid varchar(20) null 
/*订单客户*/,
cchanneltypeid varchar(20) null 
/*订单渠道类型*/,
cfreecustid varchar(20) null 
/*散户*/,
blargessflag char(1) null 
/*是否赠品*/,
blaborflag char(1) null 
/*是否费用类*/,
bdiscountflag char(1) null 
/*是否折扣类*/,
carorgid varchar(20) null 
/*应收组织最新版本*/,
carorgvid varchar(20) null 
/*应收组织版本*/,
cprofitcenterid varchar(20) null 
/*利润中心最新版本*/,
cprofitcentervid varchar(20) null 
/*利润中心版本*/,
ccostorgid varchar(20) null 
/*成本域*/,
csendstockorgid varchar(20) null 
/*库存组织最新版本*/,
csendstockorgvid varchar(20) null 
/*库存组织版本*/,
csendstordocid varchar(20) null 
/*仓库*/,
cdeptid varchar(20) null 
/*销售部门最新版本*/,
cdeptvid varchar(20) null 
/*销售部门版本*/,
csaleorgid varchar(20) null 
/*销售组织最新版本*/,
csaleorgvid varchar(20) null 
/*销售组织版本*/,
cemployeeid varchar(20) null 
/*销售业务员*/,
pk_batchcode varchar(20) null 
/*批次号档案*/,
vbatchcode varchar(40) null 
/*批次号*/,
cprolineid varchar(20) null 
/*产品线*/,
vrownote varchar(181) null 
/*行备注*/,
bsquarearfinish char(1) null 
/*是否应收结算完成*/,
bsquareiafinish char(1) null 
/*是否成本结算完成*/,
bincome char(1) null 
/*是否可以收入结算*/,
bcost char(1) null 
/*是否可以成本结算*/,
fpreartype integer null 
/*待收入结算类型*/,
fpreiatype integer null 
/*待成本结算类型*/,
nsquarearnum decimal(28,8) null 
/*累计确认应收数量*/,
narrushnum decimal(28,8) null 
/*累计回冲应收数量*/,
nsquareianum decimal(28,8) null 
/*累计成本结算数量*/,
nsquareregnum decimal(28,8) null 
/*累计发出商品数量*/,
corigcurrencyid varchar(20) null 
/*原币*/,
ccurrencyid varchar(20) null 
/*本币*/,
ntaxrate decimal(28,8) null 
/*税率*/,
nexchangerate decimal(28,8) null 
/*折本汇率*/,
cunitid varchar(20) null 
/*主单位*/,
castunitid varchar(20) null 
/*单位*/,
vchangerate varchar(60) null 
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
vfree1 varchar(101) null 
/*自由项1*/,
vfree2 varchar(101) null 
/*自由项2*/,
vfree3 varchar(101) null 
/*自由项3*/,
vfree4 varchar(101) null 
/*自由项4*/,
vfree5 varchar(101) null 
/*自由项5*/,
vfree6 varchar(101) null 
/*自由项6*/,
vfree7 varchar(101) null 
/*自由项7*/,
vfree8 varchar(101) null 
/*自由项8*/,
vfree9 varchar(101) null 
/*自由项9*/,
vfree10 varchar(101) null 
/*自由项10*/,
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
csalesquareid varchar(20) null 
/*销售发票结算单主实体_主键*/,
pk_group varchar(20) null 
/*集团*/,
ctaxcodeid varchar(20) null 
/*税码*/,
ftaxtypeflag integer null 
/*扣税类别*/,
ncaltaxmny decimal(28,8) null 
/*计税金额*/,
ccostsubjid varchar(20) null 
/*收支项目*/,
csprofitcentervid varchar(20) null 
/*发货利润中心*/,
csprofitcenterid varchar(20) null 
/*发货利润中心最新版*/,
cmffileid varchar(20) null 
/*特征码*/,
 constraint pk_so_squareinv_b primary key (csalesquarebid),
 ts char(19) null,
dr smallint null default 0
)
;

/* tablename: 销售发票结算单主实体 */
create table so_squareinv (csalesquareid char(20) not null 
/*销售发票结算单主实体*/,
pk_org varchar(20) null 
/*结算财务组织*/,
pk_org_v varchar(20) null 
/*结算财务组织版本*/,
pk_group varchar(20) null 
/*集团*/,
cbiztypeid varchar(20) null 
/*业务流程*/,
ccustbankaccid varchar(20) null 
/*开户银行账户*/,
csquarebillid varchar(20) null 
/*销售发票主实体*/,
cinvoicecustid varchar(20) null 
/*开票客户*/,
vbillcode varchar(40) null 
/*销售发票单据号*/,
vtrantypecode varchar(20) null 
/*销售发票类型编码*/,
dbilldate char(19) null 
/*销售发票单据日期*/,
dbillapprovedate char(19) null 
/*销售发票审核日期*/,
cpaytermid varchar(20) null 
/*发票收付款协议*/,
bautosquareincome char(1) null 
/*是否自动收入结算*/,
bautosquarecost char(1) null 
/*是否自动成本结算*/,
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
ctrantypeid varchar(20) null 
/*销售发票类型*/,
csendcountryid varchar(20) null 
/*发货国家/地区*/,
crececountryid varchar(20) null 
/*收货国家/地区*/,
ctaxcountryid varchar(20) null 
/*报税国家/地区*/,
fbuysellflag integer null 
/*购销类型*/,
btriatradeflag char(1) null 
/*三角贸易*/,
vvatcode varchar(40) null 
/*VAT注册码*/,
vcustvatcode varchar(40) null 
/*客户VAT注册码*/,
cbalancetypeid varchar(20) null 
/*结算方式*/,
 constraint pk_so_squareinv primary key (csalesquareid),
 ts char(19) null,
dr smallint null default 0
)
;

/* tablename: 销售发票结算单明细实体 */
create table so_squareinv_d (csalesquaredid char(20) not null 
/*销售发票结算单明细实体*/,
pk_org varchar(20) null 
/*结算财务组织*/,
csalesquareid varchar(20) null 
/*销售发票结算单主实体*/,
csalesquarebid varchar(20) null 
/*销售发票结算单子实体*/,
csquarebillid varchar(20) null 
/*销售发票主实体*/,
csquarebillbid varchar(20) null 
/*销售发票子实体*/,
dbilldate char(19) null 
/*销售发票单据日期*/,
processeid varchar(20) null 
/*结算批次号*/,
fsquaretype integer null 
/*结算类型*/,
nsquarenum decimal(28,8) null 
/*本次结算数量*/,
nastnum decimal(28,8) null 
/*单位数量*/,
nnum decimal(28,8) null 
/*主单位数量*/,
vchangerate varchar(60) null 
/*单位换算率*/,
castunitid varchar(20) null 
/*单位*/,
cunitid varchar(20) null 
/*主单位*/,
nexchangerate decimal(28,8) null 
/*折本汇率*/,
ntaxrate decimal(28,8) null 
/*税率*/,
ccurrencyid varchar(20) null 
/*本币*/,
corigcurrencyid varchar(20) null 
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
pk_group varchar(20) null 
/*集团*/,
ctaxcodeid varchar(20) null 
/*税码*/,
ftaxtypeflag integer null 
/*扣税类别*/,
ncaltaxmny decimal(28,8) null 
/*计税金额*/,
 constraint pk_so_squareinv_d primary key (csalesquaredid),
 ts char(19) null,
dr smallint null default 0
)
;

/* tablename: 途损结算单子实体 */
create table so_squarewas_b (csalesquarebid char(20) not null 
/*途损结算单子实体*/,
pk_org varchar(20) null 
/*结算财务组织*/,
dbilldate char(19) null 
/*途损单单据日期*/,
dbizdate char(19) null 
/*销售出库单业务日期*/,
vctcode varchar(40) null 
/*合同号*/,
deffectdate char(19) null 
/*应收单起效日期*/,
csquarebillid varchar(20) null 
/*途损单主实体*/,
csquarebillbid varchar(20) null 
/*途损单子实体*/,
vfirstcode varchar(40) null 
/*源头单据号*/,
vfirsttrantype varchar(20) null 
/*源头单据交易类型*/,
vfirsttype varchar(20) null 
/*源头单据类型*/,
cfirstid varchar(20) null 
/*源头单据主表*/,
cfirstbid varchar(20) null 
/*源头单据子表*/,
vfirstrowno varchar(20) null 
/*源头单据行号*/,
vsrccode varchar(40) null 
/*来源单据号*/,
vsrctype varchar(20) null 
/*来源单据类型*/,
vsrctrantype varchar(20) null 
/*来源单据交易类型*/,
csrcid varchar(20) null 
/*来源单据主表*/,
csrcbid varchar(20) null 
/*来源单据子表*/,
vsrcrowno varchar(20) null 
/*来源单据行号*/,
ccustmaterialid varchar(20) null 
/*客户物料码*/,
cmaterialid varchar(20) null 
/*物料*/,
cmaterialvid varchar(20) null 
/*物料版本*/,
cvendorid varchar(20) null 
/*供应商*/,
cproductorid varchar(20) null 
/*生产厂商*/,
cprojectid varchar(20) null 
/*项目ID*/,
cordercustid varchar(20) null 
/*订单客户*/,
ccustbankaccid varchar(20) null 
/*开户银行账户*/,
cinvoicecustid varchar(20) null 
/*订单开票客户*/,
cfreecustid char(20) null 
/*散户*/,
cpaytermid varchar(20) null 
/*订单收付款协议*/,
cchanneltypeid varchar(20) null 
/*订单渠道类型*/,
blargessflag char(1) null 
/*是否赠品*/,
carorgid varchar(20) null 
/*应收组织最新版本*/,
carorgvid varchar(20) null 
/*应收组织版本*/,
cprofitcenterid varchar(20) null 
/*利润中心最新版本*/,
cprofitcentervid varchar(20) null 
/*利润中心版本*/,
ccostorgid varchar(20) null 
/*成本域*/,
cdeptid varchar(20) null 
/*销售部门最新版本*/,
cdeptvid varchar(20) null 
/*销售部门版本*/,
csaleorgid varchar(20) null 
/*销售组织最新版本*/,
csaleorgvid varchar(20) null 
/*销售组织版本*/,
cemployeeid varchar(20) null 
/*销售业务员*/,
pk_batchcode varchar(20) null 
/*批次号档案*/,
vbatchcode varchar(40) null 
/*批次号*/,
cprolineid varchar(20) null 
/*产品线*/,
vrownote varchar(181) null 
/*行备注*/,
bsquarearfinish char(1) null 
/*是否应收结算完成*/,
bsquareiafinish char(1) null 
/*是否成本结算完成*/,
bincome char(1) null 
/*是否可以收入结算*/,
bcost char(1) null 
/*是否可以成本结算*/,
fpreartype integer null 
/*待收入结算类型*/,
fpreiatype integer null 
/*待成本结算类型*/,
nsquarearnum decimal(28,8) null 
/*累计确认应收数量*/,
narrushnum decimal(28,8) null 
/*累计回冲应收数量*/,
nsquareianum decimal(28,8) null 
/*累计成本结算数量*/,
nsquareregnum decimal(28,8) null 
/*累计发出商品数量*/,
corigcurrencyid varchar(20) null 
/*原币*/,
ccurrencyid varchar(20) null 
/*本币*/,
ntaxrate decimal(28,8) null 
/*税率*/,
nexchangerate decimal(28,8) null 
/*折本汇率*/,
cunitid varchar(20) null 
/*主单位*/,
castunitid varchar(20) null 
/*单位*/,
vchangerate varchar(60) null 
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
vfree1 varchar(101) null 
/*自由项1*/,
vfree2 varchar(101) null 
/*自由项2*/,
vfree3 varchar(101) null 
/*自由项3*/,
vfree4 varchar(101) null 
/*自由项4*/,
vfree5 varchar(101) null 
/*自由项5*/,
vfree6 varchar(101) null 
/*自由项6*/,
vfree7 varchar(101) null 
/*自由项7*/,
vfree8 varchar(101) null 
/*自由项8*/,
vfree9 varchar(101) null 
/*自由项9*/,
vfree10 varchar(101) null 
/*自由项10*/,
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
csalesquareid varchar(20) null 
/*途损结算单主实体_主键*/,
pk_group varchar(20) null 
/*集团*/,
ctaxcodeid varchar(20) null 
/*税码*/,
ftaxtypeflag integer null 
/*扣税类别*/,
ncaltaxmny decimal(28,8) null 
/*计税金额*/,
cmffileid varchar(20) null 
/*特征码*/,
 constraint pk_so_squarewas_b primary key (csalesquarebid),
 ts char(19) null,
dr smallint null default 0
)
;

/* tablename: 途损结算单主实体 */
create table so_squarewas (csalesquareid char(20) not null 
/*途损结算单主实体*/,
pk_org varchar(20) null 
/*结算财务组织*/,
pk_org_v varchar(20) null 
/*结算财务组织版本*/,
pk_group varchar(20) null 
/*集团*/,
cbiztypeid varchar(20) null 
/*业务流程*/,
csquarebillid varchar(20) null 
/*途损单主实体*/,
vbillcode varchar(40) null 
/*途损单单据号*/,
vtrantypecode varchar(20) null 
/*途损单交易类型*/,
dbilldate char(19) null 
/*途损单单据日期*/,
cwhsmanagerid varchar(20) null 
/*销售出库单库管员*/,
bautosquareincome char(1) null 
/*是否自动收入结算*/,
bautosquarecost char(1) null 
/*是否自动成本结算*/,
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
csendstockorgid varchar(20) null 
/*库存组织最新版本*/,
csendstockorgvid varchar(20) null 
/*库存组织版本*/,
csendstordocid varchar(20) null 
/*仓库*/,
btriatradeflag char(1) null 
/*三角贸易*/,
crececountryid varchar(20) null 
/*收货国家/地区*/,
ctaxcountryid varchar(20) null 
/*报税国家/地区*/,
ctrantypeid varchar(20) null 
/*途损单交易类型主键*/,
csendcountryid varchar(20) null 
/*发货国家/地区*/,
fbuysellflag integer null 
/*购销类型*/,
 constraint pk_so_sqwas primary key (csalesquareid),
 ts char(19) null,
dr smallint null default 0
)
;

/* tablename: 途损结算单明细实体 */
create table so_squarewas_d (csalesquaredid char(20) not null 
/*途损结算单明细实体*/,
pk_org varchar(20) null 
/*结算财务组织*/,
csalesquareid varchar(20) null 
/*途损结算单主实体*/,
csalesquarebid varchar(20) null 
/*途损结算单子实体*/,
csquarebillid varchar(20) null 
/*途损单主实体*/,
csquarebillbid varchar(20) null 
/*途损单子实体*/,
dbilldate char(19) null 
/*途损单单据日期*/,
processeid varchar(20) null 
/*结算批次号*/,
fsquaretype integer null 
/*结算类型*/,
nsquarenum decimal(28,8) null 
/*本次结算数量*/,
nastnum decimal(28,8) null 
/*单位数量*/,
nnum decimal(28,8) null 
/*主单位数量*/,
vchangerate varchar(60) null 
/*单位换算率*/,
castunitid varchar(20) null 
/*单位*/,
cunitid varchar(20) null 
/*主单位*/,
nexchangerate decimal(28,8) null 
/*折本汇率*/,
ntaxrate decimal(28,8) null 
/*税率*/,
ccurrencyid varchar(20) null 
/*本币*/,
corigcurrencyid varchar(20) null 
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
pk_group varchar(20) null 
/*集团*/,
ctaxcodeid varchar(20) null 
/*税码*/,
ftaxtypeflag integer null 
/*扣税类别*/,
ncaltaxmny decimal(28,8) null 
/*计税金额*/,
 constraint pk_so_squarewas_d primary key (csalesquaredid),
 ts char(19) null,
dr smallint null default 0
)
;

