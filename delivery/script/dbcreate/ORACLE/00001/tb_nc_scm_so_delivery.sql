/* tablename: 发货单主表 */
create table so_delivery (cdeliveryid char(20) not null 
/*发货单主表ID*/,
pk_group varchar2(20) null 
/*集团*/,
pk_org varchar2(20) null 
/*物流组织*/,
pk_org_v varchar2(20) null 
/*物流组织版本*/,
vbillcode varchar2(40) null 
/*单据号*/,
cbiztypeid varchar2(20) null 
/*业务流程*/,
vtrantypecode varchar2(20) null 
/*发货类型编码*/,
dbilldate char(19) null 
/*单据日期*/,
csendemployeeid varchar2(20) null 
/*发货计划员*/,
csenddeptid varchar2(20) null 
/*发货部门最新版本*/,
csenddeptvid varchar2(20) null 
/*发货部门*/,
ctransporttypeid varchar2(20) null 
/*运输方式*/,
ctransportrouteid varchar2(50) null 
/*运输路线*/,
ntotalastnum number(28,8) null 
/*总数量*/,
ntotalweight number(28,8) null 
/*总重量*/,
ntotalvolume number(28,8) null 
/*总体积*/,
ntotalpiece number(28,8) null 
/*总件数*/,
fstatusflag smallint null 
/*状态*/,
vnote varchar2(181) null 
/*备注*/,
creator varchar2(20) null 
/*创建人*/,
billmaker varchar2(20) null 
/*制单人*/,
creationtime char(19) null 
/*创建时间*/,
approver varchar2(20) null 
/*审批人*/,
taudittime varchar2(19) null 
/*审核日期*/,
modifier varchar2(20) null 
/*最后修改人*/,
modifiedtime char(19) null 
/*最后修改时间*/,
iprintcount integer null 
/*打印次数*/,
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
/*发货类型*/,
dmakedate char(19) null 
/*制单日期*/,
ctradewordid varchar2(20) null 
/*贸易术语*/,
 constraint pk_so_delivery primary key (cdeliveryid),
 ts char(19) default to_char(sysdate,'yyyy-mm-dd hh24:mi:ss'),
dr number(10) default 0
)
/

/* tablename: 发货单子表 */
create table so_delivery_b (cdeliverybid char(20) not null 
/*发货单子表ID*/,
cdeliveryid varchar2(20) null 
/*发货单主表ID*/,
pk_org varchar2(20) null 
/*物流组织*/,
dbilldate char(19) null 
/*单据日期*/,
crowno varchar2(20) null 
/*行号*/,
ccustmaterialid varchar2(20) null 
/*客户物料码*/,
cordercustid varchar2(20) null 
/*订单客户*/,
cfreecustid varchar2(20) null 
/*散户*/,
cinvoicecustid varchar2(20) null 
/*开票客户*/,
cmaterialid varchar2(20) null 
/*物料档案*/,
cmaterialvid varchar2(20) null 
/*物料编码*/,
cvendorid varchar2(20) null 
/*供应商*/,
cprojectid varchar2(20) null 
/*项目*/,
cproductorid varchar2(20) null 
/*生产厂商*/,
castunitid varchar2(20) null 
/*单位*/,
nastnum number(28,8) null 
/*数量*/,
cunitid varchar2(20) null 
/*主单位*/,
nnum number(28,8) null 
/*主数量*/,
vchangerate varchar2(60) null 
/*换算率*/,
cqtunitid varchar2(20) null 
/*报价单位*/,
nqtunitnum number(28,8) null 
/*报价数量*/,
vqtunitrate varchar2(60) null 
/*报价换算率*/,
bcheckflag char(1) null 
/*是否已报检*/,
busecheckflag char(1) null 
/*是否根据质检结果入库*/,
ntotalreportnum number(28,8) null 
/*累计报检数量*/,
ntotalelignum number(28,8) null 
/*累计合格数量*/,
ntotalunelignum number(28,8) null 
/*累计不合格数量*/,
nweight number(28,8) null 
/*重量*/,
nvolume number(28,8) null 
/*体积*/,
npiece number(28,8) null 
/*件数*/,
blargessflag char(1) null 
/*赠品*/,
pk_batchcode varchar2(20) null 
/*批次档案*/,
vbatchcode varchar2(40) null 
/*批次号*/,
corigcurrencyid varchar2(20) null 
/*原币*/,
nexchangerate number(28,8) null 
/*折本汇率*/,
ccurrencyid varchar2(20) null 
/*本位币*/,
ntaxrate number(28,8) null 
/*税率*/,
ndiscountrate number(28,8) null 
/*整单折扣*/,
nitemdiscountrate number(28,8) null 
/*单品折扣*/,
norigtaxprice number(28,8) null 
/*主含税单价*/,
norigprice number(28,8) null 
/*主无税单价*/,
norigtaxnetprice number(28,8) null 
/*主含税净价*/,
norignetprice number(28,8) null 
/*主无税净价*/,
nqtorigtaxprice number(28,8) null 
/*含税单价*/,
nqtorigprice number(28,8) null 
/*无税单价*/,
nqtorigtaxnetprc number(28,8) null 
/*含税净价*/,
nqtorignetprice number(28,8) null 
/*无税净价*/,
norigmny number(28,8) null 
/*无税金额*/,
norigtaxmny number(28,8) null 
/*价税合计*/,
norigdiscount number(28,8) null 
/*折扣额*/,
ntaxprice number(28,8) null 
/*本币主含税单价*/,
nprice number(28,8) null 
/*本币主无税单价*/,
ntaxnetprice number(28,8) null 
/*本币主含税净价*/,
nnetprice number(28,8) null 
/*本币主无税净价*/,
nqttaxprice number(28,8) null 
/*本币含税单价*/,
nqtprice number(28,8) null 
/*本币无税单价*/,
nqttaxnetprice number(28,8) null 
/*本币含税净价*/,
nqtnetprice number(28,8) null 
/*本币无税净价*/,
ntax number(28,8) null 
/*税额*/,
nmny number(28,8) null 
/*本币无税金额*/,
ntaxmny number(28,8) null 
/*本币价税合计*/,
ndiscount number(28,8) null 
/*本币折扣额*/,
vfirsttype varchar2(20) null 
/*源头单据类型*/,
vfirstcode varchar2(40) null 
/*源头单据号*/,
vfirsttrantype varchar2(40) null 
/*源头交易类型*/,
vfirstrowno varchar2(20) null 
/*源头单据行号*/,
cfirstid varchar2(20) null 
/*源头单据表头ID*/,
cfirstbid varchar2(20) null 
/*源头单据表体ID*/,
vsrctype varchar2(20) null 
/*来源单据类型*/,
vsrccode varchar2(40) null 
/*来源单据号*/,
vsrctrantype varchar2(20) null 
/*来源交易类型*/,
vsrcrowno varchar2(20) null 
/*来源单据行号*/,
csrcid varchar2(20) null 
/*来源单据表头ID*/,
csrcbid varchar2(20) null 
/*来源单据表体ID*/,
csaleorgid varchar2(20) null 
/*销售组织最新版本*/,
csaleorgvid varchar2(20) null 
/*销售组织*/,
csendstockorgid varchar2(20) null 
/*发货库存组织最新版本*/,
csendareaid varchar2(20) null 
/*发货地区*/,
csendadddocid varchar2(20) null 
/*发货地点*/,
csendaddrid varchar2(20) null 
/*发货地址*/,
csendstordocid varchar2(20) null 
/*发货仓库*/,
csendstockorgvid varchar2(20) null 
/*发货库存组织*/,
csendpersonid varchar2(20) null 
/*发货联系人*/,
vsendtel varchar2(30) null 
/*发货联系电话*/,
creceivecustid varchar2(20) null 
/*收货客户*/,
creceiveareaid varchar2(20) null 
/*收货地区*/,
creceiveadddocid varchar2(20) null 
/*收货地点*/,
creceiveaddrid varchar2(20) null 
/*收货地址*/,
cinstockorgid varchar2(20) null 
/*收货库存组织最新版*/,
vreceivetel varchar2(30) null 
/*收货联系电话*/,
creceivepersonid varchar2(20) null 
/*收货联系人*/,
cinstordocid varchar2(20) null 
/*收货仓库*/,
cinstockorgvid varchar2(20) null 
/*收货库存组织*/,
dsenddate varchar2(19) null 
/*发货日期*/,
dreceivedate varchar2(19) null 
/*要求收货日期*/,
csupercargoid varchar2(20) null 
/*押运员*/,
ctranscustid varchar2(20) null 
/*承运商*/,
cvehicletypeid varchar2(20) null 
/*车型*/,
cvehicleid varchar2(20) null 
/*车辆*/,
cchauffeurid varchar2(20) null 
/*司机*/,
cspaceid varchar2(50) null 
/*货位*/,
cprodlineid varchar2(20) null 
/*产品线*/,
ntotaltransnum number(28,8) null 
/*累计运输数量*/,
btransendflag char(1) null 
/*运输关闭*/,
ntotaloutnum number(28,8) null 
/*累计出库数量*/,
bbarsettleflag char(1) null 
/*收入结算关闭*/,
boutendflag char(1) null 
/*出库关闭*/,
frownote varchar2(181) null 
/*备注*/,
vfree1 varchar2(101) null 
/*自由辅助属性1*/,
vfree2 varchar2(101) null 
/*自由辅助属性2*/,
vfree3 varchar2(101) null 
/*自由辅助属性3*/,
vfree4 varchar2(101) null 
/*自由辅助属性4*/,
vfree5 varchar2(101) null 
/*自由辅助属性5*/,
vfree6 varchar2(101) null 
/*自由辅助属性6*/,
vfree7 varchar2(101) null 
/*自由辅助属性7*/,
vfree8 varchar2(101) null 
/*自由辅助属性8*/,
vfree9 varchar2(101) null 
/*自由辅助属性9*/,
vfree10 varchar2(101) null 
/*自由辅助属性10*/,
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
cqualitylevelid varchar2(20) null 
/*质量等级*/,
cprofitcenterid varchar2(20) null 
/*结算利润中心最新版本*/,
cprofitcentervid varchar2(20) null 
/*结算利润中心*/,
carorgid varchar2(20) null 
/*应收组织最新版本*/,
carorgvid varchar2(20) null 
/*应收组织*/,
csettleorgid varchar2(20) null 
/*结算财务组织最新版本*/,
cdeptid varchar2(20) null 
/*销售部门最新版本*/,
cdeptvid varchar2(20) null 
/*销售部门*/,
cemployeeid varchar2(20) null 
/*销售业务员*/,
csettleorgvid varchar2(20) null 
/*结算财务组织*/,
cchanneltypeid varchar2(20) null 
/*销售渠道*/,
vfirstbilldate varchar2(19) null 
/*源头单据日期*/,
ntranslossnum number(28,8) null 
/*累计途损数量*/,
ntotalrushnum number(28,8) null 
/*累计出库对冲数量*/,
ntotalestarnum number(28,8) null 
/*累计暂估应收数量*/,
ntotalarnum number(28,8) null 
/*累计确认应收数量*/,
nreqrsnum number(28,8) null 
/*预留数量*/,
bqualityflag char(1) null 
/*是否已质检*/,
badvfeeflag char(1) null 
/*待垫运费*/,
pk_group varchar2(20) null 
/*所属集团*/,
cpriceformid char(20) null 
/*价格组成*/,
cretreasonid char(20) null 
/*退货原因*/,
vreturnmode char(20) null 
/*退货责任处理方式*/,
ntotalnotoutnum number(28,8) null 
/*累计应发未出库主数量*/,
nglobaltaxmny number(28,8) null 
/*全局本币价税合计*/,
nglobalmny number(28,8) null 
/*全局本币无税金额*/,
nglobalexchgrate number(28,8) null 
/*全局本位币汇率*/,
ngrouptaxmny number(28,8) null 
/*集团本币价税合计*/,
ngroupmny number(28,8) null 
/*集团本币无税金额*/,
ngroupexchgrate number(28,8) null 
/*集团本位币汇率*/,
crececountryid varchar2(20) null 
/*收货国家/地区*/,
csendcountryid varchar2(20) null 
/*发货国/地区*/,
ctaxcountryid varchar2(20) null 
/*报税国/地区*/,
fbuysellflag integer null 
/*购销类型*/,
btriatradeflag char(1) null 
/*三角贸易*/,
ctaxcodeid varchar2(20) null 
/*税码*/,
ftaxtypeflag integer null 
/*扣税类别*/,
corigcountryid varchar2(20) null 
/*原产国*/,
corigareaid varchar2(20) null 
/*原产地区*/,
ncaltaxmny number(28,8) null 
/*计税金额*/,
csprofitcentervid varchar2(20) null 
/*发货利润中心*/,
csprofitcenterid varchar2(20) null 
/*发货利润中心最新版本*/,
crprofitcentervid varchar2(20) null 
/*收货利润中心*/,
crprofitcenterid varchar2(20) null 
/*收货利润中心最新版本*/,
cmffileid varchar2(20) null 
/*特征码*/,
 constraint pk_so_delivery_b primary key (cdeliverybid),
 ts char(19) default to_char(sysdate,'yyyy-mm-dd hh24:mi:ss'),
dr number(10) default 0
)
/

/* tablename: 发货单质检表 */
create table so_delivery_check (cdeliverycid char(20) not null 
/*发货单质检表ID*/,
cdeliverybid varchar2(20) null 
/*发货单子表ID*/,
crowno varchar2(20) null 
/*行号*/,
cmaterialid varchar2(20) null 
/*物料*/,
cmaterialvid varchar2(20) null 
/*物料版本*/,
cvendorid varchar2(20) null 
/*供应商*/,
cprojectid varchar2(20) null 
/*项目*/,
cqualitylevelid varchar2(20) null 
/*质量等级*/,
cproductorid varchar2(20) null 
/*生产厂商*/,
vfree1 varchar2(101) null 
/*自由辅助属性1*/,
vfree2 varchar2(101) null 
/*自由辅助属性2*/,
vfree3 varchar2(101) null 
/*自由辅助属性3*/,
vfree4 varchar2(101) null 
/*自由辅助属性4*/,
vfree5 varchar2(101) null 
/*自由辅助属性5*/,
vfree6 varchar2(101) null 
/*自由辅助属性6*/,
vfree7 varchar2(101) null 
/*自由辅助属性7*/,
vfree8 varchar2(101) null 
/*自由辅助属性8*/,
vfree9 varchar2(101) null 
/*自由辅助属性9*/,
vfree10 varchar2(101) null 
/*自由辅助属性10*/,
pk_batchcode varchar2(20) null 
/*批次档案*/,
vbatchcode varchar2(40) null 
/*批次号*/,
castunitid varchar2(20) null 
/*单位*/,
nastnum number(28,8) null 
/*质检数量*/,
cunitid varchar2(20) null 
/*主单位*/,
nnum number(28,8) null 
/*质检主数量*/,
vchangerate varchar2(60) null 
/*换算率*/,
cqtunitid varchar2(20) null 
/*报价单位*/,
nqtunitnum number(28,8) null 
/*质检报价数量*/,
vqtunitrate varchar2(60) null 
/*报价换算率*/,
vcheckcode varchar2(40) null 
/*质检单据号*/,
dcheckdate varchar2(19) null 
/*质检日期*/,
ccheckstatebid varchar2(20) null 
/*质检状态*/,
cdefectprocessid varchar2(20) null 
/*建议处理方式*/,
beligflag char(1) null 
/*是否合格*/,
bcheckinflag char(1) null 
/*质检报告是否可入库*/,
blargessflag char(1) null 
/*赠品*/,
bpricechgflag char(1) null 
/*是否发生改判*/,
ntaxrate number(28,8) null 
/*税率*/,
ndiscountrate number(28,8) null 
/*整单折扣*/,
nitemdiscountrate number(28,8) null 
/*单品折扣*/,
norigtaxprice number(28,8) null 
/*主含税单价*/,
norigprice number(28,8) null 
/*主无税单价*/,
norigtaxnetprice number(28,8) null 
/*主含税净价*/,
norignetprice number(28,8) null 
/*主无税净价*/,
nqtorigtaxprice number(28,8) null 
/*含税单价*/,
nqtorigprice number(28,8) null 
/*无税单价*/,
nqtorigtaxnetprc number(28,8) null 
/*含税净价*/,
nqtorignetprice number(28,8) null 
/*无税净价*/,
norigmny number(28,8) null 
/*无税金额*/,
norigtaxmny number(28,8) null 
/*价税合计*/,
norigdiscount number(28,8) null 
/*折扣额*/,
ntaxprice number(28,8) null 
/*本币主含税单价*/,
nprice number(28,8) null 
/*本币主无税单价*/,
ntaxnetprice number(28,8) null 
/*本币主含税净价*/,
nnetprice number(28,8) null 
/*本币主无税净价*/,
nqttaxprice number(28,8) null 
/*本币含税单价*/,
nqtprice number(28,8) null 
/*本币无税单价*/,
nqttaxnetprice number(28,8) null 
/*本币含税净价*/,
nqtnetprice number(28,8) null 
/*本币无税净价*/,
ntax number(28,8) null 
/*税额*/,
nmny number(28,8) null 
/*本币无税金额*/,
ntaxmny number(28,8) null 
/*本币价税合计*/,
ndiscount number(28,8) null 
/*本币折扣额*/,
corigcurrencyid varchar2(20) null 
/*原币*/,
nexchangerate number(28,8) null 
/*折本汇率*/,
ccurrencyid varchar2(20) null 
/*本位币*/,
ntotaltransnum number(28,8) null 
/*累计运输数量*/,
btransendflag char(1) null 
/*运输关闭*/,
ntotaloutnum number(28,8) null 
/*累计出库数量*/,
boutendflag char(1) null 
/*出库关闭*/,
vrownote varchar2(181) null 
/*备注*/,
vsrcrowno varchar2(20) null 
/*接收单行号*/,
pk_org varchar2(20) null 
/*物流组织*/,
pk_group varchar2(20) null 
/*所属集团*/,
ntotalnotoutnum number(28,8) null 
/*累计应发未出库主数量*/,
csrcid char(20) null 
/*来源单据id*/,
nglobaltaxmny number(28,8) null 
/*全局本币价税合计*/,
nglobalmny number(28,8) null 
/*全局本币无税金额*/,
nglobalexchgrate number(28,8) null 
/*全局本位币汇率*/,
ngrouptaxmny number(28,8) null 
/*集团本币价税合计*/,
ngroupmny number(28,8) null 
/*集团本币无税金额*/,
ngroupexchgrate number(28,8) null 
/*集团本位币汇率*/,
crececountryid varchar2(20) null 
/*收货国家/地区*/,
csendcountryid varchar2(20) null 
/*发货国/地区*/,
ctaxcountryid varchar2(20) null 
/*报税国/地区*/,
fbuysellflag integer null 
/*购销类型*/,
btriatradeflag char(1) null 
/*三角贸易*/,
ctaxcodeid varchar2(20) null 
/*税码*/,
ftaxtypeflag integer null 
/*扣税类别*/,
corigcountryid varchar2(20) null 
/*原产国*/,
corigareaid varchar2(20) null 
/*原产地区*/,
ncaltaxmny number(28,8) null 
/*计税金额*/,
 constraint pk__delivery_check primary key (cdeliverycid),
 ts char(19) default to_char(sysdate,'yyyy-mm-dd hh24:mi:ss'),
dr number(10) default 0
)
/

/* tablename: 发货单交易类型 */
create table so_m4331trantype (ctrantypeid char(20) null 
/*发货单交易类型*/,
pk_group varchar2(20) null 
/*集团*/,
vtrantypecode varchar2(20) null 
/*交易类型*/,
bonceoutflag char(1) null 
/*只一次出库*/,
pk_trantype varchar2(20) not null 
/*交易类型主键*/,
 constraint pk_o_m4331trantype primary key (pk_trantype),
 ts char(19) default to_char(sysdate,'yyyy-mm-dd hh24:mi:ss'),
dr number(10) default 0
)
/

