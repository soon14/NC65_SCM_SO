/* tablename: 销售出库结算单子实体 */
create table so_squareout_b (csalesquarebid char(20) not null 
/*销售出库结算单子实体*/,
pk_org varchar2(20) null 
/*结算财务组织*/,
dbilldate char(19) null 
/*销售出库单单据日期*/,
vctcode varchar2(40) null 
/*合同号*/,
deffectdate char(19) null 
/*应收单起效日期*/,
dbizdate varchar2(19) null 
/*销售出库单业务日期*/,
csquarebillid varchar2(20) null 
/*销售出库单主实体*/,
csquarebillbid varchar2(20) null 
/*销售出库单子实体*/,
vfirstcode varchar2(40) null 
/*源头单据号*/,
vfirsttrantype varchar2(20) null 
/*源头单据交易类型*/,
vfirsttype varchar2(20) null 
/*源头单据类型*/,
cfirstid varchar2(20) null 
/*源头单据主表*/,
cfirstbid varchar2(20) null 
/*源头单据子表*/,
vfirstrowno varchar2(20) null 
/*源头单据行号*/,
vsrccode varchar2(40) null 
/*来源单据号*/,
vsrctype varchar2(20) null 
/*来源单据类型*/,
vsrctrantype varchar2(20) null 
/*来源单据交易类型*/,
csrcid varchar2(20) null 
/*来源单据主表*/,
csrcbid varchar2(20) null 
/*来源单据子表*/,
vsrcrowno varchar2(20) null 
/*来源单据行号*/,
ccustmaterialid varchar2(20) null 
/*客户物料码*/,
cmaterialid varchar2(20) null 
/*物料*/,
cmaterialvid varchar2(20) null 
/*物料版本*/,
cvendorid varchar2(20) null 
/*供应商*/,
cproductorid varchar2(20) null 
/*生产厂商*/,
cprojectid varchar2(20) null 
/*项目ID*/,
ccustbankaccid varchar2(20) null 
/*开户银行账户*/,
cordercustid varchar2(20) null 
/*订单客户*/,
cinvoicecustid varchar2(20) null 
/*订单开票客户*/,
cfreecustid varchar2(20) null 
/*散户*/,
cpaytermid varchar2(20) null 
/*订单收付款协议*/,
cchanneltypeid varchar2(20) null 
/*订单渠道类型*/,
blargessflag char(1) null 
/*是否赠品*/,
carorgid varchar2(20) null 
/*应收组织最新版本*/,
carorgvid varchar2(20) null 
/*应收组织版本*/,
cprofitcenterid varchar2(20) null 
/*结算利润中心最新版本*/,
cprofitcentervid varchar2(20) null 
/*结算利润中心版本*/,
ccostorgid varchar2(20) null 
/*成本域*/,
cdeptid varchar2(20) null 
/*销售部门最新版本*/,
cdeptvid varchar2(20) null 
/*销售部门版本*/,
csaleorgid varchar2(20) null 
/*销售组织*/,
csaleorgvid varchar2(20) null 
/*销售组织版本*/,
cemployeeid varchar2(20) null 
/*销售业务员*/,
pk_batchcode varchar2(20) null 
/*批次号档案*/,
vbatchcode varchar2(40) null 
/*批次号*/,
cprolineid varchar2(20) null 
/*产品线*/,
vrownote varchar2(181) null 
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
nsquarearnum number(28,8) null 
/*累计确认应收数量*/,
nsquareestnum number(28,8) null 
/*累计暂估应收数量*/,
narrushnum number(28,8) null 
/*累计回冲应收数量*/,
nsquareianum number(28,8) null 
/*累计成本结算数量*/,
nsquareregnum number(28,8) null 
/*累计发出商品数量*/,
nrushnum number(28,8) null 
/*累计出库对冲数量*/,
ntotalpaymny number(28,8) null 
/*累计出库及下游发票收款核销金额*/,
ndownarmny number(28,8) null 
/*累计下游发票确认应收金额*/,
ndownarnum number(28,8) null 
/*累计下游发票确认应收数量*/,
ndownianum number(28,8) null 
/*累计下游发票成本结算数量*/,
nsquarearmny number(28,8) null 
/*累计确认应收金额*/,
corigcurrencyid varchar2(20) null 
/*原币*/,
ccurrencyid varchar2(20) null 
/*本币*/,
ntaxrate number(28,8) null 
/*税率*/,
nexchangerate number(28,8) null 
/*折本汇率*/,
cunitid varchar2(20) null 
/*主单位*/,
castunitid varchar2(20) null 
/*单位*/,
vchangerate varchar2(60) null 
/*单位换算率*/,
nnum number(28,8) null 
/*主单位数量*/,
nastnum number(28,8) null 
/*单位数量*/,
nitemdiscountrate number(28,8) null 
/*单品折扣率*/,
norigtaxprice number(28,8) null 
/*原币含税单价*/,
norigprice number(28,8) null 
/*原币无税单价*/,
norigtaxnetprice number(28,8) null 
/*原币含税净价*/,
norignetprice number(28,8) null 
/*原币无税净价*/,
norigtax number(28,8) null 
/*原币税额*/,
norigmny number(28,8) null 
/*原币无税金额*/,
norigtaxmny number(28,8) null 
/*原币价税合计*/,
norigdiscount number(28,8) null 
/*原币折扣额*/,
ntaxprice number(28,8) null 
/*本币含税单价*/,
nprice number(28,8) null 
/*本币无税单价*/,
ntaxnetprice number(28,8) null 
/*本币含税净价*/,
nnetprice number(28,8) null 
/*本币无税净价*/,
ntax number(28,8) null 
/*本币税额*/,
nmny number(28,8) null 
/*本币无税金额*/,
ntaxmny number(28,8) null 
/*本币价税合计*/,
ndiscount number(28,8) null 
/*本币折扣额*/,
nglobaltaxmny number(28,8) null 
/*全局本币价税合计*/,
nglobalmny number(28,8) null 
/*全局本币无税金额*/,
ngrouptaxmny number(28,8) null 
/*集团本币价税合计*/,
ngroupmny number(28,8) null 
/*集团本币无税金额*/,
nglobalexchgrate number(28,8) null 
/*全局本位币汇率*/,
ngroupexchgrate number(28,8) null 
/*集团本位币汇率*/,
vfree1 varchar2(101) null 
/*自由项1*/,
vfree2 varchar2(101) null 
/*自由项2*/,
vfree3 varchar2(101) null 
/*自由项3*/,
vfree4 varchar2(101) null 
/*自由项4*/,
vfree5 varchar2(101) null 
/*自由项5*/,
vfree6 varchar2(101) null 
/*自由项6*/,
vfree7 varchar2(101) null 
/*自由项7*/,
vfree8 varchar2(101) null 
/*自由项8*/,
vfree9 varchar2(101) null 
/*自由项9*/,
vfree10 varchar2(101) null 
/*自由项10*/,
vbdef1 varchar2(101) null 
/*自定义项1*/,
vbdef2 varchar2(101) null 
/*自定义项2*/,
vbdef3 varchar2(101) null 
/*自定义项3*/,
vbdef4 varchar2(101) null 
/*自定义项4*/,
vbdef5 varchar2(101) null 
/*自定义项5*/,
vbdef6 varchar2(101) null 
/*自定义项6*/,
vbdef7 varchar2(101) null 
/*自定义项7*/,
vbdef8 varchar2(101) null 
/*自定义项8*/,
vbdef9 varchar2(101) null 
/*自定义项9*/,
vbdef10 varchar2(101) null 
/*自定义项10*/,
vbdef11 varchar2(101) null 
/*自定义项11*/,
vbdef12 varchar2(101) null 
/*自定义项12*/,
vbdef13 varchar2(101) null 
/*自定义项13*/,
vbdef14 varchar2(101) null 
/*自定义项14*/,
vbdef15 varchar2(101) null 
/*自定义项15*/,
vbdef16 varchar2(101) null 
/*自定义项16*/,
vbdef17 varchar2(101) null 
/*自定义项17*/,
vbdef18 varchar2(101) null 
/*自定义项18*/,
vbdef19 varchar2(101) null 
/*自定义项19*/,
vbdef20 varchar2(101) null 
/*自定义项20*/,
csalesquareid varchar2(20) null 
/*销售出库结算单主实体_主键*/,
pk_group varchar2(20) null 
/*集团*/,
ctaxcodeid varchar2(20) null 
/*税码*/,
ftaxtypeflag integer null 
/*扣税类别*/,
ncaltaxmny number(28,8) null 
/*计税金额*/,
csprofitcentervid varchar2(20) null 
/*发货利润中心*/,
csprofitcenterid varchar2(20) null 
/*发货利润中心最新版本*/,
cmffileid varchar2(20) null 
/*特征码*/,
 constraint pk_so_squareout_b primary key (csalesquarebid),
 ts char(19) default to_char(sysdate,'yyyy-mm-dd hh24:mi:ss'),
dr number(10) default 0
)
/

/* tablename: 销售出库结算单主实体 */
create table so_squareout (csalesquareid char(20) not null 
/*销售出库结算单主实体*/,
pk_org varchar2(20) null 
/*结算财务组织*/,
pk_org_v varchar2(20) null 
/*结算财务组织版本*/,
pk_group varchar2(20) null 
/*集团*/,
cbiztypeid varchar2(20) null 
/*业务流程*/,
csquarebillid varchar2(20) null 
/*销售出库单主实体*/,
vbillcode varchar2(40) null 
/*销售出库单单据号*/,
vtrantypecode varchar2(20) null 
/*销售出库单交易类型*/,
dbilldate char(19) null 
/*销售出库单单据日期*/,
dbillsigndate char(19) null 
/*销售出库单签字日期*/,
cwhsmanagerid varchar2(20) null 
/*销售出库单库管员*/,
csendstockorgid varchar2(20) null 
/*库存组织最新版本*/,
csendstockorgvid varchar2(20) null 
/*库存组织版本*/,
csendstordocid varchar2(20) null 
/*仓库*/,
bautosquareincome char(1) null 
/*是否自动收入结算*/,
bautosquarecost char(1) null 
/*是否自动成本结算*/,
breturnoutstock char(1) null 
/*基于签收开票的退回出库单标志*/,
vnote varchar2(181) null 
/*备注*/,
vdef1 varchar2(101) null 
/*自定义项1*/,
vdef2 varchar2(101) null 
/*自定义项2*/,
vdef3 varchar2(101) null 
/*自定义项3*/,
vdef4 varchar2(101) null 
/*自定义项4*/,
vdef5 varchar2(101) null 
/*自定义项5*/,
vdef6 varchar2(101) null 
/*自定义项6*/,
vdef7 varchar2(101) null 
/*自定义项7*/,
vdef8 varchar2(101) null 
/*自定义项8*/,
vdef9 varchar2(101) null 
/*自定义项9*/,
vdef10 varchar2(101) null 
/*自定义项10*/,
vdef11 varchar2(101) null 
/*自定义项11*/,
vdef12 varchar2(101) null 
/*自定义项12*/,
vdef13 varchar2(101) null 
/*自定义项13*/,
vdef14 varchar2(101) null 
/*自定义项14*/,
vdef15 varchar2(101) null 
/*自定义项15*/,
vdef16 varchar2(101) null 
/*自定义项16*/,
vdef17 varchar2(101) null 
/*自定义项17*/,
vdef18 varchar2(101) null 
/*自定义项18*/,
vdef19 varchar2(101) null 
/*自定义项19*/,
vdef20 varchar2(101) null 
/*自定义项20*/,
ctrantypeid varchar2(20) null 
/*销售出库单交易类型主键*/,
csendcountryid varchar2(20) null 
/*发货国家/地区*/,
crececountryid varchar2(20) null 
/*收货国家/地区*/,
ctaxcountryid varchar2(20) null 
/*报税国家/地区*/,
fbuysellflag integer null 
/*购销类型*/,
btriatradeflag char(1) null 
/*三角贸易*/,
 constraint pk_so_squareout primary key (csalesquareid),
 ts char(19) default to_char(sysdate,'yyyy-mm-dd hh24:mi:ss'),
dr number(10) default 0
)
/

/* tablename: 销售出库结算单明细实体 */
create table so_squareout_d (csalesquaredid char(20) not null 
/*销售出库结算单明细实体*/,
pk_org varchar2(20) null 
/*结算财务组织*/,
csalesquareid varchar2(20) null 
/*销售出库结算单主实体*/,
csalesquarebid varchar2(20) null 
/*销售出库结算单子实体*/,
csquarebillid varchar2(20) null 
/*销售出库单主实体*/,
csquarebillbid varchar2(20) null 
/*销售出库单子实体*/,
dbilldate char(19) null 
/*销售出库单单据日期*/,
processeid varchar2(20) null 
/*结算批次号*/,
fsquaretype integer null 
/*结算类型*/,
bautosquareflag char(1) null 
/*是否自动结算*/,
nsquarenum number(28,8) null 
/*本次结算数量*/,
boutrushflag char(1) null 
/*是否出库对冲*/,
vrushbillcode varchar2(40) null 
/*对冲出库单号*/,
crushgeneralbid varchar2(20) null 
/*对冲出库单子表id*/,
crushoutbatchid varchar2(20) null 
/*对冲批次号*/,
nastnum number(28,8) null 
/*单位数量*/,
nnum number(28,8) null 
/*主单位数量*/,
vchangerate varchar2(60) null 
/*单位换算率*/,
castunitid varchar2(20) null 
/*单位*/,
cunitid varchar2(20) null 
/*主单位*/,
nexchangerate number(28,8) null 
/*折本汇率*/,
ntaxrate number(28,8) null 
/*税率*/,
ccurrencyid varchar2(20) null 
/*本币*/,
corigcurrencyid varchar2(20) null 
/*原币*/,
nitemdiscountrate number(28,8) null 
/*单品折扣率*/,
norigtaxprice number(28,8) null 
/*原币含税单价*/,
norigprice number(28,8) null 
/*原币无税单价*/,
norigtaxnetprice number(28,8) null 
/*原币含税净价*/,
norignetprice number(28,8) null 
/*原币无税净价*/,
norigtax number(28,8) null 
/*原币税额*/,
norigmny number(28,8) null 
/*原币无税金额*/,
norigtaxmny number(28,8) null 
/*原币价税合计*/,
norigdiscount number(28,8) null 
/*原币折扣额*/,
ntaxprice number(28,8) null 
/*本币含税单价*/,
nprice number(28,8) null 
/*本币无税单价*/,
ntaxnetprice number(28,8) null 
/*本币含税净价*/,
nnetprice number(28,8) null 
/*本币无税净价*/,
ntax number(28,8) null 
/*本币税额*/,
nmny number(28,8) null 
/*本币无税金额*/,
ntaxmny number(28,8) null 
/*本币价税合计*/,
ndiscount number(28,8) null 
/*本币折扣额*/,
nglobaltaxmny number(28,8) null 
/*全局本币价税合计*/,
nglobalmny number(28,8) null 
/*全局本币无税金额*/,
ngrouptaxmny number(28,8) null 
/*集团本币价税合计*/,
ngroupmny number(28,8) null 
/*集团本币无税金额*/,
nglobalexchgrate number(28,8) null 
/*全局本位币汇率*/,
ngroupexchgrate number(28,8) null 
/*集团本位币汇率*/,
pk_group varchar2(20) null 
/*集团*/,
ctaxcodeid varchar2(20) null 
/*税码*/,
ftaxtypeflag integer null 
/*??税类别*/,
ncaltaxmny number(28,8) null 
/*计税金额*/,
ncostprice number(28,8) null 
/*成本单价*/,
ncostmny number(28,8) null 
/*成本金额*/,
 constraint pk_so_squareout_d primary key (csalesquaredid),
 ts char(19) default to_char(sysdate,'yyyy-mm-dd hh24:mi:ss'),
dr number(10) default 0
)
/

/* tablename: 销售发票结算单子实体 */
create table so_squareinv_b (csalesquarebid char(20) not null 
/*销售发票结算单子实体*/,
pk_org varchar2(20) null 
/*结算财务组织*/,
csquarebillid varchar2(20) null 
/*销售发票主实体*/,
csquarebillbid varchar2(20) null 
/*销售发票子实体*/,
vctcode varchar2(40) null 
/*合同号*/,
deffectdate char(19) null 
/*应收单起效日期*/,
cprojectid varchar2(20) null 
/*项目*/,
dbilldate char(19) null 
/*销售发票单据日期*/,
vfirstcode varchar2(40) null 
/*源头单据号*/,
vfirsttrantype varchar2(20) null 
/*源头单据交易类型*/,
vfirsttype varchar2(20) null 
/*源头单据类型*/,
cfirstid varchar2(20) null 
/*源头单据主表*/,
cfirstbid varchar2(20) null 
/*源头单据子表*/,
vfirstrowno varchar2(20) null 
/*源头单据行号*/,
vsrccode varchar2(40) null 
/*来源单据号*/,
vsrctype varchar2(20) null 
/*来源单据类型*/,
vsrctrantype varchar2(20) null 
/*来源单据交易类型*/,
csrcid varchar2(20) null 
/*来源单据主表*/,
csrcbid varchar2(20) null 
/*来源单据子表*/,
vsrcrowno varchar2(20) null 
/*来源单据行号*/,
ccustmaterialid varchar2(20) null 
/*客户物料码*/,
cmaterialid varchar2(20) null 
/*物料*/,
cmaterialvid varchar2(20) null 
/*物料版本*/,
cvendorid varchar2(20) null 
/*供应商*/,
cproductorid varchar2(20) null 
/*生产厂商*/,
cordercustid varchar2(20) null 
/*订单客户*/,
cchanneltypeid varchar2(20) null 
/*订单渠道类型*/,
cfreecustid varchar2(20) null 
/*散户*/,
blargessflag char(1) null 
/*是否赠品*/,
blaborflag char(1) null 
/*是否费用类*/,
bdiscountflag char(1) null 
/*是否折扣类*/,
carorgid varchar2(20) null 
/*应收组织最新版本*/,
carorgvid varchar2(20) null 
/*应收组织版本*/,
cprofitcenterid varchar2(20) null 
/*利润中心最新版本*/,
cprofitcentervid varchar2(20) null 
/*利润中心版本*/,
ccostorgid varchar2(20) null 
/*成本域*/,
csendstockorgid varchar2(20) null 
/*库存组织最新版本*/,
csendstockorgvid varchar2(20) null 
/*库存组织版本*/,
csendstordocid varchar2(20) null 
/*仓库*/,
cdeptid varchar2(20) null 
/*销售部门最新版本*/,
cdeptvid varchar2(20) null 
/*销售部门版本*/,
csaleorgid varchar2(20) null 
/*销售组织最新版本*/,
csaleorgvid varchar2(20) null 
/*销售组织版本*/,
cemployeeid varchar2(20) null 
/*销售业务员*/,
pk_batchcode varchar2(20) null 
/*批次号档案*/,
vbatchcode varchar2(40) null 
/*批次号*/,
cprolineid varchar2(20) null 
/*产品线*/,
vrownote varchar2(181) null 
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
nsquarearnum number(28,8) null 
/*累计确认应收数量*/,
narrushnum number(28,8) null 
/*累计回冲应收数量*/,
nsquareianum number(28,8) null 
/*累计成本结算数量*/,
nsquareregnum number(28,8) null 
/*累计发出商品数量*/,
corigcurrencyid varchar2(20) null 
/*原币*/,
ccurrencyid varchar2(20) null 
/*本币*/,
ntaxrate number(28,8) null 
/*税率*/,
nexchangerate number(28,8) null 
/*折本汇率*/,
cunitid varchar2(20) null 
/*主单位*/,
castunitid varchar2(20) null 
/*单位*/,
vchangerate varchar2(60) null 
/*单位换算率*/,
nnum number(28,8) null 
/*主单位数量*/,
nastnum number(28,8) null 
/*单位数量*/,
nitemdiscountrate number(28,8) null 
/*单品折扣率*/,
norigtaxprice number(28,8) null 
/*原币含税单价*/,
norigprice number(28,8) null 
/*原币无税单价*/,
norigtaxnetprice number(28,8) null 
/*原币含税净价*/,
norignetprice number(28,8) null 
/*原币无税净价*/,
norigtax number(28,8) null 
/*原币税额*/,
norigmny number(28,8) null 
/*原币无税金额*/,
norigtaxmny number(28,8) null 
/*原币价税合计*/,
norigdiscount number(28,8) null 
/*原币折扣额*/,
ntaxprice number(28,8) null 
/*本币含税单价*/,
nprice number(28,8) null 
/*本币无税单价*/,
ntaxnetprice number(28,8) null 
/*本币含税净价*/,
nnetprice number(28,8) null 
/*本币无税净价*/,
ntax number(28,8) null 
/*本币税额*/,
nmny number(28,8) null 
/*本币无税金额*/,
ntaxmny number(28,8) null 
/*本币价税合计*/,
ndiscount number(28,8) null 
/*本币折扣额*/,
nglobaltaxmny number(28,8) null 
/*全局本币价税合计*/,
nglobalmny number(28,8) null 
/*全局本币无税金额*/,
ngrouptaxmny number(28,8) null 
/*集团本币价税合计*/,
ngroupmny number(28,8) null 
/*集团本币无税金额*/,
nglobalexchgrate number(28,8) null 
/*全局本位币汇率*/,
ngroupexchgrate number(28,8) null 
/*集团本位币汇率*/,
vfree1 varchar2(101) null 
/*自由项1*/,
vfree2 varchar2(101) null 
/*自由项2*/,
vfree3 varchar2(101) null 
/*自由项3*/,
vfree4 varchar2(101) null 
/*自由项4*/,
vfree5 varchar2(101) null 
/*自由项5*/,
vfree6 varchar2(101) null 
/*自由项6*/,
vfree7 varchar2(101) null 
/*自由项7*/,
vfree8 varchar2(101) null 
/*自由项8*/,
vfree9 varchar2(101) null 
/*自由项9*/,
vfree10 varchar2(101) null 
/*自由项10*/,
vbdef1 varchar2(101) null 
/*自定义项1*/,
vbdef2 varchar2(101) null 
/*自定义项2*/,
vbdef3 varchar2(101) null 
/*自定义项3*/,
vbdef4 varchar2(101) null 
/*自定义项4*/,
vbdef5 varchar2(101) null 
/*自定义项5*/,
vbdef6 varchar2(101) null 
/*自定义项6*/,
vbdef7 varchar2(101) null 
/*自定义项7*/,
vbdef8 varchar2(101) null 
/*自定义项8*/,
vbdef9 varchar2(101) null 
/*自定义项9*/,
vbdef10 varchar2(101) null 
/*自定义项10*/,
vbdef11 varchar2(101) null 
/*自定义项11*/,
vbdef12 varchar2(101) null 
/*自定义项12*/,
vbdef13 varchar2(101) null 
/*自定义项13*/,
vbdef14 varchar2(101) null 
/*自定义项14*/,
vbdef15 varchar2(101) null 
/*自定义项15*/,
vbdef16 varchar2(101) null 
/*自定义项16*/,
vbdef17 varchar2(101) null 
/*自定义项17*/,
vbdef18 varchar2(101) null 
/*自定义项18*/,
vbdef19 varchar2(101) null 
/*自定义项19*/,
vbdef20 varchar2(101) null 
/*自定义项20*/,
csalesquareid varchar2(20) null 
/*销售发票结算单主实体_主键*/,
pk_group varchar2(20) null 
/*集团*/,
ctaxcodeid varchar2(20) null 
/*税码*/,
ftaxtypeflag integer null 
/*扣税类别*/,
ncaltaxmny number(28,8) null 
/*计税金额*/,
ccostsubjid varchar2(20) null 
/*收支项目*/,
csprofitcentervid varchar2(20) null 
/*发货利润中心*/,
csprofitcenterid varchar2(20) null 
/*发货利润中心最新版*/,
cmffileid varchar2(20) null 
/*特征码*/,
 constraint pk_so_squareinv_b primary key (csalesquarebid),
 ts char(19) default to_char(sysdate,'yyyy-mm-dd hh24:mi:ss'),
dr number(10) default 0
)
/

/* tablename: 销售发票结算单主实体 */
create table so_squareinv (csalesquareid char(20) not null 
/*销售发票结算单主实体*/,
pk_org varchar2(20) null 
/*结算财务组织*/,
pk_org_v varchar2(20) null 
/*结算财务组织版本*/,
pk_group varchar2(20) null 
/*集团*/,
cbiztypeid varchar2(20) null 
/*业务流程*/,
ccustbankaccid varchar2(20) null 
/*开户银行账户*/,
csquarebillid varchar2(20) null 
/*销售发票主实体*/,
cinvoicecustid varchar2(20) null 
/*开票客户*/,
vbillcode varchar2(40) null 
/*销售发票单据号*/,
vtrantypecode varchar2(20) null 
/*销售发票类型编码*/,
dbilldate char(19) null 
/*销售发票单据日期*/,
dbillapprovedate char(19) null 
/*销售发票审核日期*/,
cpaytermid varchar2(20) null 
/*发票收付款协议*/,
bautosquareincome char(1) null 
/*是否自动收入结算*/,
bautosquarecost char(1) null 
/*是否自动成本结算*/,
vnote varchar2(181) null 
/*备注*/,
vdef1 varchar2(101) null 
/*自定义项1*/,
vdef2 varchar2(101) null 
/*自定义项2*/,
vdef3 varchar2(101) null 
/*自定义项3*/,
vdef4 varchar2(101) null 
/*自定义项4*/,
vdef5 varchar2(101) null 
/*自定义项5*/,
vdef6 varchar2(101) null 
/*自定义项6*/,
vdef7 varchar2(101) null 
/*自定义项7*/,
vdef8 varchar2(101) null 
/*自定义项8*/,
vdef9 varchar2(101) null 
/*自定义项9*/,
vdef10 varchar2(101) null 
/*自定义项10*/,
vdef11 varchar2(101) null 
/*自定义项11*/,
vdef12 varchar2(101) null 
/*自定义项12*/,
vdef13 varchar2(101) null 
/*自定义项13*/,
vdef14 varchar2(101) null 
/*自定义项14*/,
vdef15 varchar2(101) null 
/*自定义项15*/,
vdef16 varchar2(101) null 
/*自定义项16*/,
vdef17 varchar2(101) null 
/*自定义项17*/,
vdef18 varchar2(101) null 
/*自定义项18*/,
vdef19 varchar2(101) null 
/*自定义项19*/,
vdef20 varchar2(101) null 
/*自定义项20*/,
ctrantypeid varchar2(20) null 
/*销售发票类型*/,
csendcountryid varchar2(20) null 
/*发货国家/地区*/,
crececountryid varchar2(20) null 
/*收货国家/地区*/,
ctaxcountryid varchar2(20) null 
/*报税国家/地区*/,
fbuysellflag integer null 
/*购销类型*/,
btriatradeflag char(1) null 
/*三角贸易*/,
vvatcode varchar2(40) null 
/*VAT注册码*/,
vcustvatcode varchar2(40) null 
/*客户VAT注册码*/,
cbalancetypeid varchar2(20) null 
/*结算方式*/,
 constraint pk_so_squareinv primary key (csalesquareid),
 ts char(19) default to_char(sysdate,'yyyy-mm-dd hh24:mi:ss'),
dr number(10) default 0
)
/

/* tablename: 销售发票结算单明细实体 */
create table so_squareinv_d (csalesquaredid char(20) not null 
/*销售发票结算单明细实体*/,
pk_org varchar2(20) null 
/*结算财务组织*/,
csalesquareid varchar2(20) null 
/*销售发票结算单主实体*/,
csalesquarebid varchar2(20) null 
/*销售发票结算单子实体*/,
csquarebillid varchar2(20) null 
/*销售发票主实体*/,
csquarebillbid varchar2(20) null 
/*销售发票子实体*/,
dbilldate char(19) null 
/*销售发票单据日期*/,
processeid varchar2(20) null 
/*结算批次号*/,
fsquaretype integer null 
/*结算类型*/,
nsquarenum number(28,8) null 
/*本次结算数量*/,
nastnum number(28,8) null 
/*单位数量*/,
nnum number(28,8) null 
/*主单位数量*/,
vchangerate varchar2(60) null 
/*单位换算率*/,
castunitid varchar2(20) null 
/*单位*/,
cunitid varchar2(20) null 
/*主单位*/,
nexchangerate number(28,8) null 
/*折本汇率*/,
ntaxrate number(28,8) null 
/*税率*/,
ccurrencyid varchar2(20) null 
/*本币*/,
corigcurrencyid varchar2(20) null 
/*原币*/,
nitemdiscountrate number(28,8) null 
/*单品折扣率*/,
norigtaxprice number(28,8) null 
/*原币含税单价*/,
norigprice number(28,8) null 
/*原币无税单价*/,
norigtaxnetprice number(28,8) null 
/*原币含税净价*/,
norignetprice number(28,8) null 
/*原币无税净价*/,
norigtax number(28,8) null 
/*原币税额*/,
norigmny number(28,8) null 
/*原币无税金额*/,
norigtaxmny number(28,8) null 
/*原币价税合计*/,
norigdiscount number(28,8) null 
/*原币折扣额*/,
ntaxprice number(28,8) null 
/*本币含税单价*/,
nprice number(28,8) null 
/*本币无税单价*/,
ntaxnetprice number(28,8) null 
/*本币含税净价*/,
nnetprice number(28,8) null 
/*本币无税净价*/,
ntax number(28,8) null 
/*本币税额*/,
nmny number(28,8) null 
/*本币无税金额*/,
ntaxmny number(28,8) null 
/*本币价税合计*/,
ndiscount number(28,8) null 
/*本币折扣额*/,
nglobaltaxmny number(28,8) null 
/*全局本币价税合计*/,
nglobalmny number(28,8) null 
/*全局本币无税金额*/,
ngrouptaxmny number(28,8) null 
/*集团本币价税合计*/,
ngroupmny number(28,8) null 
/*集团本币无税金额*/,
nglobalexchgrate number(28,8) null 
/*全局本位币汇率*/,
ngroupexchgrate number(28,8) null 
/*集团本位币汇率*/,
pk_group varchar2(20) null 
/*集团*/,
ctaxcodeid varchar2(20) null 
/*税码*/,
ftaxtypeflag integer null 
/*扣税类别*/,
ncaltaxmny number(28,8) null 
/*计税金额*/,
 constraint pk_so_squareinv_d primary key (csalesquaredid),
 ts char(19) default to_char(sysdate,'yyyy-mm-dd hh24:mi:ss'),
dr number(10) default 0
)
/

/* tablename: 途损结算单子实体 */
create table so_squarewas_b (csalesquarebid char(20) not null 
/*途损结算单子实体*/,
pk_org varchar2(20) null 
/*结算财务组织*/,
dbilldate char(19) null 
/*途损单单据日期*/,
dbizdate char(19) null 
/*销售出库单业务日期*/,
vctcode varchar2(40) null 
/*合同号*/,
deffectdate char(19) null 
/*应收单起效日期*/,
csquarebillid varchar2(20) null 
/*途损单主实体*/,
csquarebillbid varchar2(20) null 
/*途损单子实体*/,
vfirstcode varchar2(40) null 
/*源头单据号*/,
vfirsttrantype varchar2(20) null 
/*源头单据交易类型*/,
vfirsttype varchar2(20) null 
/*源头单据类型*/,
cfirstid varchar2(20) null 
/*源头单据主表*/,
cfirstbid varchar2(20) null 
/*源头单据子表*/,
vfirstrowno varchar2(20) null 
/*源头单据行号*/,
vsrccode varchar2(40) null 
/*来源单据号*/,
vsrctype varchar2(20) null 
/*来源单据类型*/,
vsrctrantype varchar2(20) null 
/*来源单据交易类型*/,
csrcid varchar2(20) null 
/*来源单据主表*/,
csrcbid varchar2(20) null 
/*来源单据子表*/,
vsrcrowno varchar2(20) null 
/*来源单据行号*/,
ccustmaterialid varchar2(20) null 
/*客户物料码*/,
cmaterialid varchar2(20) null 
/*物料*/,
cmaterialvid varchar2(20) null 
/*物料版本*/,
cvendorid varchar2(20) null 
/*供应商*/,
cproductorid varchar2(20) null 
/*生产厂商*/,
cprojectid varchar2(20) null 
/*项目ID*/,
cordercustid varchar2(20) null 
/*订单客户*/,
ccustbankaccid varchar2(20) null 
/*开户银行账户*/,
cinvoicecustid varchar2(20) null 
/*订单开票客户*/,
cfreecustid char(20) null 
/*散户*/,
cpaytermid varchar2(20) null 
/*订单收付款协议*/,
cchanneltypeid varchar2(20) null 
/*订单渠道类型*/,
blargessflag char(1) null 
/*是否赠品*/,
carorgid varchar2(20) null 
/*应收组织最新版本*/,
carorgvid varchar2(20) null 
/*应收组织版本*/,
cprofitcenterid varchar2(20) null 
/*利润中心最新版本*/,
cprofitcentervid varchar2(20) null 
/*利润中心版本*/,
ccostorgid varchar2(20) null 
/*成本域*/,
cdeptid varchar2(20) null 
/*销售部门最新版本*/,
cdeptvid varchar2(20) null 
/*销售部门版本*/,
csaleorgid varchar2(20) null 
/*销售组织最新版本*/,
csaleorgvid varchar2(20) null 
/*销售组织版本*/,
cemployeeid varchar2(20) null 
/*销售业务员*/,
pk_batchcode varchar2(20) null 
/*批次号档案*/,
vbatchcode varchar2(40) null 
/*批次号*/,
cprolineid varchar2(20) null 
/*产品线*/,
vrownote varchar2(181) null 
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
nsquarearnum number(28,8) null 
/*累计确认应收数量*/,
narrushnum number(28,8) null 
/*累计回冲应收数量*/,
nsquareianum number(28,8) null 
/*累计成本结算数量*/,
nsquareregnum number(28,8) null 
/*累计发出商品数量*/,
corigcurrencyid varchar2(20) null 
/*原币*/,
ccurrencyid varchar2(20) null 
/*本币*/,
ntaxrate number(28,8) null 
/*税率*/,
nexchangerate number(28,8) null 
/*折本汇率*/,
cunitid varchar2(20) null 
/*主单位*/,
castunitid varchar2(20) null 
/*单位*/,
vchangerate varchar2(60) null 
/*单位换算率*/,
nnum number(28,8) null 
/*主单位数量*/,
nastnum number(28,8) null 
/*单位数量*/,
nitemdiscountrate number(28,8) null 
/*单品折扣率*/,
norigtaxprice number(28,8) null 
/*原币含税单价*/,
norigprice number(28,8) null 
/*原币无税单价*/,
norigtaxnetprice number(28,8) null 
/*原币含税净价*/,
norignetprice number(28,8) null 
/*原币无税净价*/,
norigtax number(28,8) null 
/*原币税额*/,
norigmny number(28,8) null 
/*原币无税金额*/,
norigtaxmny number(28,8) null 
/*原币价税合计*/,
norigdiscount number(28,8) null 
/*原币折扣额*/,
ntaxprice number(28,8) null 
/*本币含税单价*/,
nprice number(28,8) null 
/*本币无税单价*/,
ntaxnetprice number(28,8) null 
/*本币含税净价*/,
nnetprice number(28,8) null 
/*本币无税净价*/,
ntax number(28,8) null 
/*本币税额*/,
nmny number(28,8) null 
/*本币无税金额*/,
ntaxmny number(28,8) null 
/*本币价税合计*/,
ndiscount number(28,8) null 
/*本币折扣额*/,
nglobaltaxmny number(28,8) null 
/*全局本币价税合计*/,
nglobalmny number(28,8) null 
/*全局本币无税金额*/,
ngrouptaxmny number(28,8) null 
/*集团本币价税合计*/,
ngroupmny number(28,8) null 
/*集团本币无税金额*/,
nglobalexchgrate number(28,8) null 
/*全局本位币汇率*/,
ngroupexchgrate number(28,8) null 
/*集团本位币汇率*/,
vfree1 varchar2(101) null 
/*自由项1*/,
vfree2 varchar2(101) null 
/*自由项2*/,
vfree3 varchar2(101) null 
/*自由项3*/,
vfree4 varchar2(101) null 
/*自由项4*/,
vfree5 varchar2(101) null 
/*自由项5*/,
vfree6 varchar2(101) null 
/*自由项6*/,
vfree7 varchar2(101) null 
/*自由项7*/,
vfree8 varchar2(101) null 
/*自由项8*/,
vfree9 varchar2(101) null 
/*自由项9*/,
vfree10 varchar2(101) null 
/*自由项10*/,
vbdef1 varchar2(101) null 
/*自定义项1*/,
vbdef2 varchar2(101) null 
/*自定义项2*/,
vbdef3 varchar2(101) null 
/*自定义项3*/,
vbdef4 varchar2(101) null 
/*自定义项4*/,
vbdef5 varchar2(101) null 
/*自定义项5*/,
vbdef6 varchar2(101) null 
/*自定义项6*/,
vbdef7 varchar2(101) null 
/*自定义项7*/,
vbdef8 varchar2(101) null 
/*自定义项8*/,
vbdef9 varchar2(101) null 
/*自定义项9*/,
vbdef10 varchar2(101) null 
/*自定义项10*/,
vbdef11 varchar2(101) null 
/*自定义项11*/,
vbdef12 varchar2(101) null 
/*自定义项12*/,
vbdef13 varchar2(101) null 
/*自定义项13*/,
vbdef14 varchar2(101) null 
/*自定义项14*/,
vbdef15 varchar2(101) null 
/*自定义项15*/,
vbdef16 varchar2(101) null 
/*自定义项16*/,
vbdef17 varchar2(101) null 
/*自定义项17*/,
vbdef18 varchar2(101) null 
/*自定义项18*/,
vbdef19 varchar2(101) null 
/*自定义项19*/,
vbdef20 varchar2(101) null 
/*自定义项20*/,
csalesquareid varchar2(20) null 
/*途损结算单主实体_主键*/,
pk_group varchar2(20) null 
/*集团*/,
ctaxcodeid varchar2(20) null 
/*税码*/,
ftaxtypeflag integer null 
/*扣税类别*/,
ncaltaxmny number(28,8) null 
/*计税金额*/,
cmffileid varchar2(20) null 
/*特征码*/,
 constraint pk_so_squarewas_b primary key (csalesquarebid),
 ts char(19) default to_char(sysdate,'yyyy-mm-dd hh24:mi:ss'),
dr number(10) default 0
)
/

/* tablename: 途损结算单主实体 */
create table so_squarewas (csalesquareid char(20) not null 
/*途损结算单主实体*/,
pk_org varchar2(20) null 
/*结算财务组织*/,
pk_org_v varchar2(20) null 
/*结算财务组织版本*/,
pk_group varchar2(20) null 
/*集团*/,
cbiztypeid varchar2(20) null 
/*业务流程*/,
csquarebillid varchar2(20) null 
/*途损单主实体*/,
vbillcode varchar2(40) null 
/*途损单单据号*/,
vtrantypecode varchar2(20) null 
/*途损单交易类型*/,
dbilldate char(19) null 
/*途损单单据日期*/,
cwhsmanagerid varchar2(20) null 
/*销售出库单库管员*/,
bautosquareincome char(1) null 
/*是否自动收入结算*/,
bautosquarecost char(1) null 
/*是否自动成本结算*/,
vnote varchar2(181) null 
/*备注*/,
vdef1 varchar2(101) null 
/*自定义项1*/,
vdef2 varchar2(101) null 
/*自定义项2*/,
vdef3 varchar2(101) null 
/*自定义项3*/,
vdef4 varchar2(101) null 
/*自定义项4*/,
vdef5 varchar2(101) null 
/*自定义项5*/,
vdef6 varchar2(101) null 
/*自定义项6*/,
vdef7 varchar2(101) null 
/*自定义项7*/,
vdef8 varchar2(101) null 
/*自定义项8*/,
vdef9 varchar2(101) null 
/*自定义项9*/,
vdef10 varchar2(101) null 
/*自定义项10*/,
vdef11 varchar2(101) null 
/*自定义项11*/,
vdef12 varchar2(101) null 
/*自定义项12*/,
vdef13 varchar2(101) null 
/*自定义项13*/,
vdef14 varchar2(101) null 
/*自定义项14*/,
vdef15 varchar2(101) null 
/*自定义项15*/,
vdef16 varchar2(101) null 
/*自定义项16*/,
vdef17 varchar2(101) null 
/*自定义项17*/,
vdef18 varchar2(101) null 
/*自定义项18*/,
vdef19 varchar2(101) null 
/*自定义项19*/,
vdef20 varchar2(101) null 
/*自定义项20*/,
csendstockorgid varchar2(20) null 
/*库存组织最新版本*/,
csendstockorgvid varchar2(20) null 
/*库存组织版本*/,
csendstordocid varchar2(20) null 
/*仓库*/,
btriatradeflag char(1) null 
/*三角贸易*/,
crececountryid varchar2(20) null 
/*收货国家/地区*/,
ctaxcountryid varchar2(20) null 
/*报税国家/地区*/,
ctrantypeid varchar2(20) null 
/*途损单交易类型主键*/,
csendcountryid varchar2(20) null 
/*发货国家/地区*/,
fbuysellflag integer null 
/*购销类型*/,
 constraint pk_so_sqwas primary key (csalesquareid),
 ts char(19) default to_char(sysdate,'yyyy-mm-dd hh24:mi:ss'),
dr number(10) default 0
)
/

/* tablename: 途损结算单明细实体 */
create table so_squarewas_d (csalesquaredid char(20) not null 
/*途损结算单明细实体*/,
pk_org varchar2(20) null 
/*结算财务组织*/,
csalesquareid varchar2(20) null 
/*途损结算单主实体*/,
csalesquarebid varchar2(20) null 
/*途损结算单子实体*/,
csquarebillid varchar2(20) null 
/*途损单主实体*/,
csquarebillbid varchar2(20) null 
/*途损单子实体*/,
dbilldate char(19) null 
/*途损单单据日期*/,
processeid varchar2(20) null 
/*结算批次号*/,
fsquaretype integer null 
/*结算类型*/,
nsquarenum number(28,8) null 
/*本次结算数量*/,
nastnum number(28,8) null 
/*单位数量*/,
nnum number(28,8) null 
/*主单位数量*/,
vchangerate varchar2(60) null 
/*单位换算率*/,
castunitid varchar2(20) null 
/*单位*/,
cunitid varchar2(20) null 
/*主单位*/,
nexchangerate number(28,8) null 
/*折本汇率*/,
ntaxrate number(28,8) null 
/*税率*/,
ccurrencyid varchar2(20) null 
/*本币*/,
corigcurrencyid varchar2(20) null 
/*原币*/,
nitemdiscountrate number(28,8) null 
/*单品折扣率*/,
norigtaxprice number(28,8) null 
/*原币含税单价*/,
norigprice number(28,8) null 
/*原币无税单价*/,
norigtaxnetprice number(28,8) null 
/*原币含税净价*/,
norignetprice number(28,8) null 
/*原币无税净价*/,
norigtax number(28,8) null 
/*原币税额*/,
norigmny number(28,8) null 
/*原币无税金额*/,
norigtaxmny number(28,8) null 
/*原币价税合计*/,
norigdiscount number(28,8) null 
/*原币折扣额*/,
ntaxprice number(28,8) null 
/*本币含税单价*/,
nprice number(28,8) null 
/*本币无税单价*/,
ntaxnetprice number(28,8) null 
/*本币含税净价*/,
nnetprice number(28,8) null 
/*本币无税净价*/,
ntax number(28,8) null 
/*本币税额*/,
nmny number(28,8) null 
/*本币无税金额*/,
ntaxmny number(28,8) null 
/*本币价税合计*/,
ndiscount number(28,8) null 
/*本币折扣额*/,
nglobaltaxmny number(28,8) null 
/*全局本币价税合计*/,
nglobalmny number(28,8) null 
/*全局本币无税金额*/,
ngrouptaxmny number(28,8) null 
/*集团本币价税合计*/,
ngroupmny number(28,8) null 
/*集团本币无税金额*/,
nglobalexchgrate number(28,8) null 
/*全局本位币汇率*/,
ngroupexchgrate number(28,8) null 
/*集团本位币汇率*/,
pk_group varchar2(20) null 
/*集团*/,
ctaxcodeid varchar2(20) null 
/*税码*/,
ftaxtypeflag integer null 
/*扣税类别*/,
ncaltaxmny number(28,8) null 
/*计税金额*/,
 constraint pk_so_squarewas_d primary key (csalesquaredid),
 ts char(19) default to_char(sysdate,'yyyy-mm-dd hh24:mi:ss'),
dr number(10) default 0
)
/

