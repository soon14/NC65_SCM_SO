/* tablename: 销售订单交易类型 */
create table so_m30trantype (
ctrantypeid nvarchar(20) null 
/*交易类型*/,
pk_trantype nchar(20) not null 
/*销售订单交易类型主键*/,
fdirecttype smallint null 
/*直运类型标记*/,
fsalemode smallint null 
/*销售模式*/,
bmorerows nchar(1) null 
/*同一货物可否列多行*/,
bcanchangeout nchar(1) null 
/*退货入库之后才能换货出库*/,
faskqtrule smallint null 
/*询价规则*/,
bmodifyaskedqt nchar(1) null 
/*询到价格是否可改*/,
bmodifyunaskedqt nchar(1) null 
/*未询到价格是否可改*/,
flargessgetqtrule smallint null 
/*赠品取价规则*/,
bmodifydiscount nchar(1) null 
/*允许修改折扣*/,
vtrantypecode nvarchar(20) null 
/*交易类型编码*/,
pk_group nvarchar(20) null 
/*集团ID*/,
fmodifymny smallint null 
/*调整金额影响折扣还是单价*/,
breviseprice nchar(1) null 
/*修订询价*/,
bredorderpay nchar(1) null 
/*红字订单支持订单收款*/,
flargessdistype smallint null 
/*赠品价格分摊方式*/,
blargesspriceno nchar(1) null 
/*赠品行价格保持不变*/,
barrangeinv nchar(1) null 
/*只能安排一次发货*/,
barrangeout nchar(1) null 
/*只能安排一次出库*/,
bnofindpricehit nchar(1) null 
/*未询到价格是否提示*/,
blossrenew nchar(1) null 
/*途损补货*/,
blrgcashflag nchar(1) null 
/*赠品兑付*/,
naccpricerule smallint null 
/*主记账单价取价规则*/,
bcopyiseprice nchar(1) null 
/*复制是否询价*/,
 constraint pk_so_m30trantype primary key (pk_trantype),
 ts char(19) null default convert(char(19),getdate(),20),
dr smallint null default 0
)
go

/* tablename: 销售订单主表 */
create table so_saleorder (
csaleorderid nchar(20) not null 
/*销售主表ID*/,
pk_org nvarchar(20) null 
/*销售组织*/,
pk_group nvarchar(20) null 
/*集团*/,
cbiztypeid nvarchar(20) null 
/*业务流程*/,
ctrantypeid nvarchar(20) null 
/*交易类型*/,
vtrantypecode nvarchar(20) null 
/*交易类型编码*/,
vbillcode nvarchar(40) null 
/*单据号*/,
ccustomerid nvarchar(20) null 
/*客户*/,
dbilldate nchar(19) null 
/*单据日期*/,
cdeptid nvarchar(20) null 
/*部门*/,
corigcurrencyid nvarchar(20) null 
/*原币币种*/,
cemployeeid nvarchar(20) null 
/*业务员*/,
cpaytermid nvarchar(20) null 
/*收款协议*/,
cinvoicecustid nvarchar(20) null 
/*开票客户*/,
ccustbankid nvarchar(20) null 
/*开户银行*/,
ccustbankaccid nvarchar(20) null 
/*开户银行账号*/,
ctransporttypeid nvarchar(20) null 
/*运输方式*/,
ndiscountrate decimal(28,8) null 
/*整单折扣*/,
vrevisereason nvarchar(181) null 
/*修订理由*/,
badvfeeflag nchar(1) null 
/*代垫运费*/,
bfreecustflag nchar(1) null 
/*是否散户*/,
vcreditnum nvarchar(20) null 
/*信用证号*/,
cfreecustid nvarchar(20) null 
/*散户*/,
billmaker nvarchar(20) null 
/*制单人*/,
creationtime nchar(19) null 
/*创建时间*/,
approver nvarchar(20) null 
/*审批人*/,
taudittime nvarchar(19) null 
/*审核日期*/,
modifiedtime nchar(19) null 
/*修改时间*/,
fstatusflag smallint null 
/*单据状态*/,
vnote nvarchar(181) null 
/*备注*/,
boutendflag nchar(1) null 
/*出库关闭标记*/,
binvoicendflag nchar(1) null 
/*开票关闭标记*/,
bcostsettleflag nchar(1) null 
/*成本结算关闭标记*/,
bsendendflag nchar(1) null 
/*发货关闭标记*/,
npreceiverate decimal(28,8) null 
/*订单收款比例*/,
npreceivequota decimal(28,8) null 
/*订单收款限额*/,
bpreceiveflag nchar(1) null 
/*收款限额控制预收*/,
npreceivemny decimal(28,8) null 
/*实际预收款金额*/,
nreceivedmny decimal(28,8) null 
/*实际收款金额*/,
iprintcount int null 
/*打印次数*/,
ntotalorigmny decimal(28,8) null 
/*金额合计*/,
ntotalorigsubmny decimal(28,8) null 
/*冲抵金额*/,
boffsetflag nchar(1) null 
/*是否冲抵*/,
bcooptopoflag nchar(1) null 
/*是否已协同生成采购订单*/,
bpocooptomeflag nchar(1) null 
/*是否由采购订单协同生成*/,
vcooppohcode nvarchar(40) null 
/*对方订单号*/,
iversion int null 
/*修订版本号*/,
trevisetime nchar(19) null 
/*修订时???*/,
creviserid nvarchar(20) null 
/*修订人*/,
cbalancetypeid nvarchar(20) null 
/*结算方式*/,
cchanneltypeid nvarchar(20) null 
/*销售渠道类型*/,
ntotalnum decimal(28,8) null 
/*合计数量*/,
ntotalweight decimal(28,8) null 
/*合计重量*/,
ntotalvolume decimal(28,8) null 
/*合计体积*/,
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
modifier nvarchar(20) null 
/*修改人*/,
pk_org_v nvarchar(20) null 
/*销售组织最新版本*/,
cdeptvid nvarchar(20) null 
/*部门最新版本*/,
barsettleflag nchar(1) null 
/*收入结算关闭标记*/,
creator nchar(20) null 
/*创建人*/,
ntotalpiece decimal(28,8) null 
/*总???数*/,
dmakedate nchar(19) null 
/*制单日期*/,
fpfstatusflag smallint null 
/*审批流状态*/,
ctradewordid nvarchar(20) null 
/*贸易术语*/,
vbillsrctype nvarchar(20) null 
/*整单来源单据类型*/,
cbillsrcid nvarchar(20) null 
/*整单来源单据ID*/,
nlrgtotalorigmny decimal(28,8) null 
/*赠品价税合计*/,
carsubtypeid nvarchar(20) null 
/*赠品兑付类型*/,
chreceivecustid nvarchar(20) null 
/*收货客户*/,
chreceiveaddid nvarchar(20) null 
/*收货地址*/,
 constraint pk_so_saleorder primary key (csaleorderid),
 ts char(19) null default convert(char(19),getdate(),20),
dr smallint null default 0
)
go

/* tablename: 销售订单附表 */
create table so_saleorder_b (
csaleorderid nvarchar(20) null 
/*销售订单主表*/,
csaleorderbid nchar(20) not null 
/*销售订单附表*/,
pk_group nvarchar(20) null 
/*集团*/,
pk_org nvarchar(20) null 
/*销售组织*/,
dbilldate nchar(19) null 
/*单据日期*/,
crowno nvarchar(20) null 
/*行号*/,
ccustmaterialid nvarchar(20) null 
/*客户物料码*/,
cmaterialvid nvarchar(20) null 
/*物料*/,
cproductorid nvarchar(20) null 
/*生产厂商*/,
cmaterialid nvarchar(20) null 
/*物料最新??本*/,
cvendorid nvarchar(20) null 
/*供应商*/,
cfactoryid nvarchar(20) null 
/*工厂*/,
cqualitylevelid nvarchar(20) null 
/*质量等级*/,
cunitid nvarchar(20) null 
/*主单位*/,
cprojectid nvarchar(20) null 
/*项目*/,
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
/*报价单位数量*/,
ntaxrate decimal(28,8) null 
/*税率*/,
nitemdiscountrate decimal(28,8) null 
/*单品折扣*/,
ndiscountrate decimal(28,8) null 
/*整单折扣*/,
ccurrencyid nvarchar(20) null 
/*本位币*/,
nexchangerate decimal(28,8) null 
/*折本汇率*/,
nqtorigtaxprice decimal(28,8) null 
/*含税单价*/,
nqtorigprice decimal(28,8) null 
/*无税单价*/,
nqtorigtaxnetprc decimal(28,8) null 
/*含税净价*/,
nqtorignetprice decimal(28,8) null 
/*无税净价*/,
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
nqttaxnetprice decimal(28,8) null 
/*本币含税净价*/,
nqtnetprice decimal(28,8) null 
/*本币无税净价*/,
nqttaxprice decimal(28,8) null 
/*本币含税单价*/,
nqtprice decimal(28,8) null 
/*本币无税单价*/,
nprice decimal(28,8) null 
/*主本币无税单价*/,
ntaxprice decimal(28,8) null 
/*主本币含税单价*/,
nnetprice decimal(28,8) null 
/*主本币无税净价*/,
ntaxnetprice decimal(28,8) null 
/*主本币含税净价*/,
ntax decimal(28,8) null 
/*本币税额*/,
nmny decimal(28,8) null 
/*本币无税金额*/,
ntaxmny decimal(28,8) null 
/*本币价税合计*/,
ndiscount decimal(28,8) null 
/*本币折扣额*/,
nweight decimal(28,8) null 
/*重量*/,
nvolume decimal(28,8) null 
/*体积*/,
npiece decimal(28,8) null 
/*件数*/,
ngroupexchgrate decimal(28,8) null 
/*集团本位币汇率*/,
ngroupmny decimal(28,8) null 
/*集团本币无税金额*/,
ngrouptaxmny decimal(28,8) null 
/*集团本币价税合计*/,
nglobalexchgrate decimal(28,8) null 
/*全局本位币汇率*/,
nglobalmny decimal(28,8) null 
/*全局本币无税金额*/,
nglobaltaxmny decimal(28,8) null 
/*全局本币价税合计*/,
naskqtorigtaxprc decimal(28,8) null 
/*询价原币含税单价*/,
naskqtorigprice decimal(28,8) null 
/*询价原币无税单价*/,
naskqtorigtxntprc decimal(28,8) null 
/*询价原币含税净价*/,
naskqtorignetprice decimal(28,8) null 
/*询价原币无税净价*/,
cpricepolicyid nvarchar(20) null 
/*价格政策*/,
cpriceitemid nvarchar(20) null 
/*价格项目*/,
cpriceitemtableid nvarchar(20) null 
/*价目表*/,
cpriceformid nvarchar(20) null 
/*价格组成*/,
blargessflag nchar(1) null 
/*是否赠品*/,
cprodlineid nvarchar(20) null 
/*产品线*/,
blaborflag nchar(1) null 
/*服务类物料*/,
bdiscountflag nchar(1) null 
/*折扣类物料*/,
vbatchcode nvarchar(40) null 
/*批次*/,
pk_batchcode nvarchar(20) null 
/*批次档案*/,
dsenddate nchar(19) null 
/*计划发货日期*/,
dreceivedate nchar(19) null 
/*要求到货日期*/,
creceivecustid nvarchar(20) null 
/*收货客户*/,
creceiveareaid nvarchar(20) null 
/*收货地区*/,
creceiveaddrid nvarchar(20) null 
/*收货地址*/,
creceiveadddocid nvarchar(20) null 
/*收货地点*/,
csendstockorgvid nvarchar(20) null 
/*发货库存组织*/,
csendstockorgid nvarchar(20) null 
/*发货库存组织最新版本*/,
csendstordocid nvarchar(20) null 
/*发货仓库*/,
csettleorgvid nvarchar(20) null 
/*结算财务组织*/,
csettleorgid nvarchar(20) null 
/*结算财务组织结算财务组织*/,
carorgvid nvarchar(20) null 
/*应收组织*/,
carorgid nvarchar(20) null 
/*应收组织最新版本*/,
ctrafficorgvid nvarchar(20) null 
/*物流组织*/,
ctrafficorgid nvarchar(20) null 
/*物流组织最新版本*/,
cprofitcentervid nvarchar(20) null 
/*结算利润中心*/,
cprofitcenterid nvarchar(20) null 
/*结算利润中心最新版本*/,
bbindflag nchar(1) null 
/*是否??绑存货*/,
clargesssrcid nvarchar(20) null 
/*赠品行对应来源订单行ID*/,
cbindsrcid nvarchar(20) null 
/*捆绑件对应来源订单行ID*/,
flargesstypeflag int null 
/*赠品价格分摊方式*/,
nlargessmny decimal(28,8) null 
/*赠品价格分摊前无税金额*/,
nlargesstaxmny decimal(28,8) null 
/*赠品价格分摊前价税合计*/,
vbrevisereason nvarchar(181) null 
/*修订理由*/,
cretreasonid nvarchar(20) null 
/*退货原因ID*/,
vreturnmode nvarchar(181) null 
/*退货责任处理方式*/,
cretpolicyid nvarchar(20) null 
/*退货政策ID*/,
barrangedflag nchar(1) null 
/*是否货源安排完毕*/,
carrangepersonid nvarchar(20) null 
/*最后货源安排人*/,
tlastarrangetime nchar(19) null 
/*最后货源安排时间*/,
vclosereason nvarchar(181) null 
/*关闭原因*/,
cctmanageid nvarchar(20) null 
/*合同ID*/,
cctmanagebid nvarchar(20) null 
/*合同附表ID*/,
vctcode nvarchar(40) null 
/*销售合同号*/,
vfirsttype nvarchar(20) null 
/*源头单据类型*/,
vfirsttrantype nvarchar(20) null 
/*源头交易类型*/,
vfirstcode nvarchar(40) null 
/*源头单据号*/,
cfirstid nvarchar(20) null 
/*源头单据主表主键*/,
cfirstbid nvarchar(20) null 
/*源头单据表体主键*/,
vfirstrowno nvarchar(20) null 
/*源头单据行号*/,
vsrctype nvarchar(20) null 
/*来源单??类型*/,
vsrctrantype nvarchar(20) null 
/*来源交易类型*/,
csrcid nvarchar(20) null 
/*来源单据主表*/,
csrcbid nvarchar(20) null 
/*来源单据附表*/,
vsrccode nvarchar(40) null 
/*来源单据号*/,
vsrcrowno nvarchar(20) null 
/*来源单据行号*/,
fretexchange int null 
/*退换货标记*/,
cexchangesrcretid nvarchar(20) null 
/*换货来源订单*/,
bjczxsflag nchar(1) null 
/*借出转销售*/,
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
/*自定义???1*/,
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
/*自定??项14*/,
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
bbsendendflag nchar(1) null 
/*发货关闭*/,
bboutendflag nchar(1) null 
/*出库关闭*/,
bbinvoicendflag nchar(1) null 
/*开票关闭*/,
bbcostsettleflag nchar(1) null 
/*成本结算关闭*/,
bbarsettleflag nchar(1) null 
/*收入结算关闭*/,
frowstatus int null 
/*行状态*/,
vrownote nvarchar(181) null 
/*行备注*/,
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
ctaxcodeid nvarchar(20) null 
/*税码*/,
ftaxtypeflag int null 
/*扣税类别*/,
ncaltaxmny decimal(28,8) null 
/*计税金额*/,
corigcountryid nvarchar(20) null 
/*原产国*/,
corigareaid nvarchar(20) null 
/*原产地区*/,
cbuypromottypeid nvarchar(20) null 
/*买赠促销类型*/,
cprcpromottypeid nvarchar(20) null 
/*询价促销类型*/,
vcustombillcode nvarchar(40) null 
/*客户订单号*/,
cbuylargessactid nvarchar(20) null 
/*买赠活动*/,
cpricepromtactid nvarchar(20) null 
/*价格促销活动*/,
cbuylargessid nvarchar(20) null 
/*买赠设置*/,
csprofitcentervid nvarchar(20) null 
/*发货利润中心*/,
csprofitcenterid nvarchar(20) null 
/*发货利润中心最新版本*/,
blrgcashflag nchar(1) null 
/*赠品兑付*/,
naccprice decimal(28,8) null 
/*主记账单价*/,
cpromotpriceid nvarchar(20) null 
/*促销价格表行*/,
cmffileid nvarchar(20) null 
/*特征码*/,
nmffileprice decimal(28,8) null 
/*特征价*/,
 constraint pk_so_saleorder_b primary key (csaleorderbid),
 ts char(19) null default convert(char(19),getdate(),20),
dr smallint null default 0
)
go

/* tablename: so_saleorder_exe_扩展表 */
create table so_saleorder_exe (
ntotalsendnum decimal(28,8) null 
/*累计发货数量*/,
ntotalinvoicenum decimal(28,8) null 
/*累计开票数量*/,
ntotaloutnum decimal(28,8) null 
/*累计出库数量*/,
ntotalnotoutnum decimal(28,8) null 
/*累计应发未出库数量*/,
ntotalsignnum decimal(28,8) null 
/*累计签收数量*/,
ntranslossnum decimal(28,8) null 
/*累计途损数量*/,
ntotalrushnum decimal(28,8) null 
/*累计出库对冲数量*/,
ntotalestarnum decimal(28,8) null 
/*累计暂估应收数量*/,
ntotalarnum decimal(28,8) null 
/*累计确认应收数量*/,
ntotalcostnum decimal(28,8) null 
/*累计成本结算数量*/,
ntotalestarmny decimal(28,8) null 
/*累计暂估应收金额*/,
ntotalarmny decimal(28,8) null 
/*累计确认应收金额*/,
ntotalpaymny decimal(28,8) null 
/*累计财务核销金额*/,
norigsubmny decimal(28,8) null 
/*累计冲抵金额*/,
narrangescornum decimal(28,8) null 
/*累计安排委外订单数量*/,
narrangepoappnum decimal(28,8) null 
/*累计安排请购单数量*/,
narrangetoornum decimal(28,8) null 
/*累计安排调拨订单数量*/,
narrangetoappnum decimal(28,8) null 
/*累计安排调拨申请数量*/,
narrangemonum decimal(28,8) null 
/*累计安排生产订单数量*/,
narrangeponum decimal(28,8) null 
/*累计安排采购订单数量*/,
ntotalplonum decimal(28,8) null 
/*累计生成计划订单主数量*/,
ntotalreturnnum decimal(28,8) null 
/*累计退货数量*/,
ntotaltradenum decimal(28,8) null 
/*累计发出商品数量*/,
nreqrsnum decimal(28,8) null 
/*预留数量*/,
csaleorderbid nchar(20) not null 
/*扩展表主键*/,
narrangeitcnum decimal(28,8) null 
/*累计安排进口合同主数量*/,
 constraint pk_o_saleorder_exe primary key (csaleorderbid),
 ts char(19) null default convert(char(19),getdate(),20),
dr smallint null default 0
)
go

/* tablename: 订单收款核销主实体 */
create table so_balance (
csobalanceid nchar(20) not null 
/*订单收款核销主实体*/,
csaleorderid nvarchar(20) null 
/*销售订单主实体*/,
pk_org nvarchar(20) null 
/*销售组织*/,
pk_org_v nvarchar(20) null 
/*销售组织最新版本*/,
pk_group nvarchar(20) null 
/*集团*/,
ccustomerid nvarchar(20) null 
/*订单客户*/,
cinvoicecustid nvarchar(20) null 
/*开票客户*/,
vbillcode nvarchar(40) null 
/*订单号*/,
corigcurrencyid nvarchar(20) null 
/*币种*/,
ntotalorigtaxmny decimal(28,8) null 
/*价税合计*/,
ntotalpaymny decimal(28,8) null 
/*订单已收款金额*/,
ntotalorigbalmny decimal(28,8) null 
/*订单已核销金额*/,
cpaytermid nvarchar(20) null 
/*收款协议*/,
cemployeeid nvarchar(20) null 
/*业务员*/,
cdeptid nvarchar(20) null 
/*部门*/,
carorgid nvarchar(20) null 
/*应收组织*/,
vtrantypecode nvarchar(20) null 
/*销售订单类型*/,
cchanneltypeid nvarchar(20) null 
/*销售渠道类型*/,
dbilldate nvarchar(19) null 
/*单据日期*/,
 constraint pk_so_balance primary key (csobalanceid),
 ts char(19) null default convert(char(19),getdate(),20),
dr smallint null default 0
)
go

/* tablename: 订单收款核销子实体 */
create table so_balance_b (
csobalancebid nchar(20) not null 
/*订单收款核销子实体*/,
pk_org nvarchar(20) null 
/*销售组织*/,
fibaltype int null 
/*核销类型*/,
cpaybillid nvarchar(20) null 
/*收款单主实体*/,
cpaybillrowid nvarchar(20) null 
/*收款单子实体*/,
varbillcode nvarchar(40) null 
/*单据号*/,
darbilldate nvarchar(19) null 
/*单据日期*/,
norigarmny decimal(28,8) null 
/*单据行金额*/,
carorigcurrencyid nvarchar(20) null 
/*币种*/,
darbalancedate nvarchar(19) null 
/*核销日期*/,
cprodlineid nvarchar(20) null 
/*产品线*/,
norigordbalmny decimal(28,8) null 
/*订单核销金额*/,
norigaccbalmny decimal(28,8) null 
/*收款单已财务核销金额*/,
csobalanceid nvarchar(20) null 
/*订单收款核销主实体_主键*/,
bpreceiveflag nchar(1) null 
/*收款限额控制预收*/,
 constraint pk_so_balance_b primary key (csobalancebid),
 ts char(19) null default convert(char(19),getdate(),20),
dr smallint null default 0
)
go

/* tablename: 销售订单修订主表 */
create table so_orderhistory (
corderhistoryid nchar(20) not null 
/*销售订单修订主表ID*/,
csaleorderid nchar(20) not null 
/*销售主表ID*/,
pk_org nvarchar(20) null 
/*销售组织*/,
pk_group nvarchar(20) null 
/*集团*/,
cbiztypeid nvarchar(20) null 
/*业务流程*/,
ctrantypeid nvarchar(20) null 
/*交易类型*/,
vtrantypecode nvarchar(20) null 
/*交易类型编码*/,
vbillcode nvarchar(40) null 
/*单据号*/,
ccustomerid nvarchar(20) null 
/*客户*/,
dbilldate nchar(19) null 
/*单据日期*/,
cdeptid nvarchar(20) null 
/*部门*/,
corigcurrencyid nvarchar(20) null 
/*原币币种*/,
cemployeeid nvarchar(20) null 
/*业务员*/,
cpaytermid nvarchar(20) null 
/*收款协议*/,
cinvoicecustid nvarchar(20) null 
/*开票客户*/,
ccustbankid nvarchar(20) null 
/*开户银行*/,
ccustbankaccid nvarchar(20) null 
/*开户银行账号*/,
ctransporttypeid nvarchar(20) null 
/*运输方式*/,
ndiscountrate decimal(28,8) null 
/*整单折扣*/,
vrevisereason nvarchar(181) null 
/*修订理由*/,
badvfeeflag nchar(1) null 
/*代垫运费*/,
bfreecustflag nchar(1) null 
/*是否散户*/,
vcreditnum nvarchar(20) null 
/*信用证号*/,
cfreecustid nvarchar(20) null 
/*散户*/,
billmaker nvarchar(20) null 
/*制单人*/,
creationtime nchar(19) null 
/*创建时间*/,
approver nvarchar(20) null 
/*审批人*/,
taudittime nvarchar(19) null 
/*审核日期*/,
modifiedtime nchar(19) null 
/*修改时间*/,
fstatusflag smallint null 
/*单据状态*/,
vnote nvarchar(181) null 
/*备注*/,
boutendflag nchar(1) null 
/*出库关闭标记*/,
binvoicendflag nchar(1) null 
/*开票关闭标记*/,
bcostsettleflag nchar(1) null 
/*成本结算关闭标记*/,
bsendendflag nchar(1) null 
/*发货关闭标记*/,
npreceiverate decimal(28,8) null 
/*订单收款比例*/,
npreceivequota decimal(28,8) null 
/*订单收款限额*/,
bpreceiveflag nchar(1) null 
/*收款限额控制预收*/,
npreceivemny decimal(28,8) null 
/*实际预收款金额*/,
nreceivedmny decimal(28,8) null 
/*实际收款金额*/,
iprintcount int null 
/*打印次数*/,
ntotalorigmny decimal(28,8) null 
/*金额合计*/,
ntotalorigsubmny decimal(28,8) null 
/*冲抵金额*/,
boffsetflag nchar(1) null 
/*是否冲抵*/,
bcooptopoflag nchar(1) null 
/*是否已协同生成采购订单*/,
bpocooptomeflag nchar(1) null 
/*是否由采购订单协同生成*/,
vcooppohcode nvarchar(40) null 
/*对方订单号*/,
iversion int null 
/*修订版本号*/,
trevisetime nchar(19) null 
/*修订时间*/,
creviserid nvarchar(20) null 
/*修订人*/,
cbalancetypeid nvarchar(20) null 
/*结算方式*/,
cchanneltypeid nvarchar(20) null 
/*销售渠道类型*/,
ntotalnum decimal(28,8) null 
/*合计数量*/,
ntotalweight decimal(28,8) null 
/*合计重量*/,
ntotalvolume decimal(28,8) null 
/*合计体积*/,
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
modifier nvarchar(20) null 
/*修改人*/,
pk_org_v nvarchar(20) null 
/*销售组织最新版本*/,
cdeptvid nvarchar(20) null 
/*部门最新版本*/,
barsettleflag nchar(1) null 
/*收入结算关闭标记*/,
creator nchar(20) null 
/*创建人*/,
ntotalpiece decimal(28,8) null 
/*总件数*/,
dmakedate nchar(19) null 
/*制单日期*/,
fpfstatusflag smallint null 
/*审批流状态*/,
ctradewordid nvarchar(20) null 
/*贸易术语*/,
chistrantypeid nvarchar(20) null 
/*修订交易类型*/,
vhistrantypecode nvarchar(20) null 
/*修订交易类型编码*/,
carsubtypeid nvarchar(20) null 
/*赠品兑付类型*/,
chreceivecustid nvarchar(20) null 
/*收货客户*/,
chreceiveaddid nvarchar(20) null 
/*收货地址*/,
vbillsrctype nvarchar(20) null 
/*整单来源单据类型*/,
cbillsrcid nvarchar(20) null 
/*整单来源单据ID*/,
nlrgtotalorigmny decimal(28,8) null 
/*赠品价税合计*/,
 constraint pk_so_orderhist primary key (corderhistoryid),
 ts char(19) null default convert(char(19),getdate(),20),
dr smallint null default 0
)
go

/* tablename: 销售订单修订附表 */
create table so_orderhistory_b (
corderhistorybid nchar(20) not null 
/*销售订单修订附表ID*/,
corderhistoryid nvarchar(20) not null 
/*销售订单修订主表ID*/,
csaleorderid nvarchar(20) null 
/*销售订单主表*/,
csaleorderbid nchar(20) not null 
/*销售订单附表*/,
pk_group nvarchar(20) null 
/*集团*/,
pk_org nvarchar(20) null 
/*销售组织*/,
dbilldate nchar(19) null 
/*单据日期*/,
crowno nvarchar(20) null 
/*行号*/,
ccustmaterialid nvarchar(20) null 
/*客户物料码*/,
cmaterialvid nvarchar(20) null 
/*物料*/,
cproductorid nvarchar(20) null 
/*生产厂商*/,
cmaterialid nvarchar(20) null 
/*物料最新版本*/,
cvendorid nvarchar(20) null 
/*供应商*/,
cfactoryid nvarchar(20) null 
/*工厂*/,
cqualitylevelid nvarchar(20) null 
/*质量等级*/,
cunitid nvarchar(20) null 
/*主单位*/,
cprojectid nvarchar(20) null 
/*项目*/,
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
/*报价单位数量*/,
ntaxrate decimal(28,8) null 
/*税率*/,
nitemdiscountrate decimal(28,8) null 
/*单品折扣*/,
ndiscountrate decimal(28,8) null 
/*整单折扣*/,
ccurrencyid nvarchar(20) null 
/*本位币*/,
nexchangerate decimal(28,8) null 
/*折本汇率*/,
nqtorigtaxprice decimal(28,8) null 
/*含税单价*/,
nqtorigprice decimal(28,8) null 
/*无税单价*/,
nqtorigtaxnetprc decimal(28,8) null 
/*含税净价*/,
nqtorignetprice decimal(28,8) null 
/*无税净价*/,
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
nqttaxnetprice decimal(28,8) null 
/*本币含税净价*/,
nqtnetprice decimal(28,8) null 
/*本币无税净价*/,
nqttaxprice decimal(28,8) null 
/*本币含税单价*/,
nqtprice decimal(28,8) null 
/*本币无税单价*/,
nprice decimal(28,8) null 
/*主本币??税单价*/,
ntaxprice decimal(28,8) null 
/*主本币含税单价*/,
nnetprice decimal(28,8) null 
/*主本币无税净价*/,
ntaxnetprice decimal(28,8) null 
/*主本币含税净价*/,
ntax decimal(28,8) null 
/*本币税额*/,
nmny decimal(28,8) null 
/*本币无税金额*/,
ntaxmny decimal(28,8) null 
/*本币价税合计*/,
ndiscount decimal(28,8) null 
/*本币折扣额*/,
nweight decimal(28,8) null 
/*重量*/,
nvolume decimal(28,8) null 
/*体积*/,
npiece decimal(28,8) null 
/*件数*/,
ngroupexchgrate decimal(28,8) null 
/*集团本位币汇率*/,
ngroupmny decimal(28,8) null 
/*集团本币无税金额*/,
ngrouptaxmny decimal(28,8) null 
/*集团本币价税合计*/,
nglobalexchgrate decimal(28,8) null 
/*全局本位币汇率*/,
nglobalmny decimal(28,8) null 
/*全局本币无税金额*/,
nglobaltaxmny decimal(28,8) null 
/*全局本币价税合计*/,
naskqtorigtaxprc decimal(28,8) null 
/*询价原币含税单价*/,
naskqtorigprice decimal(28,8) null 
/*询价原币无税单价*/,
naskqtorigtxntprc decimal(28,8) null 
/*询价原币含税净价*/,
naskqtorignetprice decimal(28,8) null 
/*询价原币无税净价*/,
cpricepolicyid nvarchar(20) null 
/*价格政策*/,
cpriceitemid nvarchar(20) null 
/*价格项目*/,
cpriceitemtableid nvarchar(20) null 
/*价目表*/,
cpriceformid nvarchar(20) null 
/*价格组成*/,
blargessflag nchar(1) null 
/*是否赠品*/,
cprodlineid nvarchar(20) null 
/*产品线*/,
blaborflag nchar(1) null 
/*服务类物料*/,
bdiscountflag nchar(1) null 
/*折扣类物料*/,
vbatchcode nvarchar(40) null 
/*批次*/,
pk_batchcode nvarchar(20) null 
/*批次档案*/,
dsenddate nchar(19) null 
/*计划发货日期*/,
dreceivedate nchar(19) null 
/*要求到货日期*/,
creceivecustid nvarchar(20) null 
/*收货客户*/,
creceiveareaid nvarchar(20) null 
/*收货地区*/,
creceiveaddrid nvarchar(20) null 
/*收货地址*/,
creceiveadddocid nvarchar(20) null 
/*收货地点*/,
csendstockorgvid nvarchar(20) null 
/*发货库存组织*/,
csendstockorgid nvarchar(20) null 
/*发货库存组织最新版本*/,
csendstordocid nvarchar(20) null 
/*发货仓库*/,
csettleorgvid nvarchar(20) null 
/*结算财务组织*/,
csettleorgid nvarchar(20) null 
/*结算财务组织结算财务组织*/,
carorgvid nvarchar(20) null 
/*应收组织*/,
carorgid nvarchar(20) null 
/*应收组织最新版本*/,
ctrafficorgvid nvarchar(20) null 
/*物流组织*/,
ctrafficorgid nvarchar(20) null 
/*物流组织最新版本*/,
cprofitcentervid nvarchar(20) null 
/*利润中心*/,
cprofitcenterid nvarchar(20) null 
/*利润中心最新版本*/,
bbindflag nchar(1) null 
/*是否捆绑存货*/,
clargesssrcid nvarchar(20) null 
/*赠品行对应来源订单行ID*/,
cbindsrcid nvarchar(20) null 
/*捆绑件对应来源订单行ID*/,
flargesstypeflag int null 
/*赠品价格分摊方式*/,
nlargessmny decimal(28,8) null 
/*赠品价格分摊前无税金额*/,
nlargesstaxmny decimal(28,8) null 
/*赠品价格分摊前价税合计*/,
vbrevisereason nvarchar(181) null 
/*修订理由*/,
cretreasonid nvarchar(20) null 
/*退货原因ID*/,
vreturnmode nvarchar(181) null 
/*退货责任处理方式*/,
cretpolicyid nvarchar(20) null 
/*退货政策ID*/,
barrangedflag nchar(1) null 
/*是否货源安排完毕*/,
carrangepersonid nvarchar(20) null 
/*最后货源安排人*/,
tlastarrangetime nchar(19) null 
/*最后货源安排时间*/,
vclosereason nvarchar(181) null 
/*关闭原因*/,
cctmanageid nvarchar(20) null 
/*合同ID*/,
cctmanagebid nvarchar(20) null 
/*合同附表ID*/,
vctcode nvarchar(40) null 
/*销售合同号*/,
vfirsttype nvarchar(20) null 
/*源头单据类型*/,
vfirsttrantype nvarchar(20) null 
/*源头交易类型*/,
vfirstcode nvarchar(40) null 
/*源头单据号*/,
cfirstid nvarchar(20) null 
/*源头单据主表主键*/,
cfirstbid nvarchar(20) null 
/*源头单据表体主键*/,
vfirstrowno nvarchar(20) null 
/*源头单据行号*/,
vsrctype nvarchar(20) null 
/*来源单据类型*/,
vsrctrantype nvarchar(20) null 
/*来??交易类型*/,
csrcid nvarchar(20) null 
/*来源单据主表*/,
csrcbid nvarchar(20) null 
/*来源单据附表*/,
vsrccode nvarchar(40) null 
/*来源单据号*/,
vsrcrowno nvarchar(20) null 
/*来源单据行号*/,
fretexchange int null 
/*退换货标记*/,
cexchangesrcretid nvarchar(20) null 
/*换货来源订单*/,
bjczxsflag nchar(1) null 
/*借出转销售*/,
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
/*自由???助属性8*/,
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
/*自定???项18*/,
vbdef19 nvarchar(101) null 
/*自定义项19*/,
vbdef20 nvarchar(101) null 
/*自定义项20*/,
bbsendendflag nchar(1) null 
/*发货关闭*/,
bboutendflag nchar(1) null 
/*出库关闭*/,
bbinvoicendflag nchar(1) null 
/*开票关闭*/,
bbcostsettleflag nchar(1) null 
/*成本结算关闭*/,
bbarsettleflag nchar(1) null 
/*收入结算关闭*/,
frowstatus int null 
/*行状态*/,
vrownote nvarchar(181) null 
/*行备注*/,
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
ctaxcodeid nvarchar(20) null 
/*税码*/,
ftaxtypeflag int null 
/*扣税类别*/,
ncaltaxmny decimal(28,8) null 
/*计税金额*/,
corigcountryid nvarchar(20) null 
/*原产国*/,
corigareaid nvarchar(20) null 
/*原产地区*/,
cbuypromottypeid nvarchar(20) null 
/*买赠促销类型*/,
cprcpromottypeid nvarchar(20) null 
/*询价促销类型*/,
vcustombillcode nvarchar(40) null 
/*客户订单号*/,
cbuylargessactid nvarchar(20) null 
/*买赠活动*/,
cpricepromtactid nvarchar(20) null 
/*价格促销活动*/,
cbuylargessid nvarchar(20) null 
/*买赠设置*/,
csprofitcentervid nvarchar(20) null 
/*发货利润中心*/,
csprofitcenterid nvarchar(20) null 
/*发货利润中心最新版本*/,
cmffileid nvarchar(20) null 
/*特征码*/,
nmffileprice decimal(28,8) null 
/*特征价*/,
cpromotpriceid nvarchar(20) null 
/*促销价格表行*/,
blrgcashflag nchar(1) null 
/*赠品兑付*/,
naccprice decimal(28,8) null 
/*主记账单价*/,
 constraint pk_so_orderhist_b primary key (corderhistorybid),
 ts char(19) null default convert(char(19),getdate(),20),
dr smallint null default 0
)
go

/* tablename: 销售订单我的订单转储表 */
create table so_mb_myorder (
csaleorderid nchar(20) not null 
/*订单主实体*/,
pk_group nvarchar(20) null 
/*集团*/,
dbilldate nchar(19) null 
/*单据日期*/,
pk_org nvarchar(20) null 
/*销售组织*/,
vbillcode nvarchar(40) null 
/*订单号*/,
ccustomerid nvarchar(20) null 
/*客户*/,
ntotalorigmny decimal(28,8) null 
/*价税合计*/,
cemployeeid nvarchar(20) null 
/*业务员*/,
 constraint pk_so_mb_myorder primary key (csaleorderid),
 ts char(19) null default convert(char(19),getdate(),20),
dr smallint null default 0
)
go

/* tablename: 销售订单移动分析表 */
create table so_mb_orderanaly (
pk_group nvarchar(20) null 
/*集团*/,
dbilldate nchar(19) null 
/*单据日期*/,
pk_org nvarchar(20) null 
/*销售组织*/,
vbillcode nvarchar(40) null 
/*订单号*/,
ccustomerid nvarchar(20) null 
/*客户*/,
cdeptid nvarchar(20) null 
/*部门*/,
cemployeeid nvarchar(20) null 
/*业务员*/,
cchanneltypeid nvarchar(20) null 
/*渠道类型*/,
cmaterialid nvarchar(20) null 
/*物料*/,
cprodlineid nvarchar(20) null 
/*产品线*/,
cbrandid nvarchar(20) null 
/*品牌*/,
nnum decimal(28,8) null 
/*销售主数量*/,
norigtaxmny decimal(28,8) null 
/*价税合计*/,
corigcurrencyid nvarchar(20) null 
/*原币*/,
ntaxmny decimal(28,8) null 
/*本币价税合计*/,
ngrouptaxmny decimal(28,8) null 
/*集团本币价税合计*/,
csaleorderid nvarchar(20) null 
/*销售主表ID*/,
  ts char(19) null default convert(char(19),getdate(),20),
dr smallint null default 0
)
go

