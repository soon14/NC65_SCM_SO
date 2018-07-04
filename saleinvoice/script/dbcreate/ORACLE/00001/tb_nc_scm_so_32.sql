/* tablename: 发票交易类型 */
create table so_m32trantype (pk_trantype char(20) not null 
/*发票交易类型主键*/,
ctrantypeid varchar2(20) null 
/*交易类型*/,
pk_group varchar2(20) null 
/*集团ID*/,
vtrantypecode varchar2(20) null 
/*交易类型编码*/,
fadjuster smallint null 
/*调整项*/,
 constraint pk_m32trantype primary key (pk_trantype),
 ts char(19) default to_char(sysdate,'yyyy-mm-dd hh24:mi:ss'),
dr number(10) default 0
)
/

/* tablename: 销售发票子表 */
create table so_saleinvoice_b (csaleinvoicebid char(20) not null 
/*发票子表ID*/,
csaleinvoiceid varchar2(20) null 
/*发票主表ID*/,
pk_group varchar2(20) null 
/*集团*/,
pk_org varchar2(20) null 
/*开票组织*/,
dbilldate char(19) null 
/*开票日期*/,
crowno varchar2(20) null 
/*行号*/,
ccustmaterialid varchar2(20) null 
/*客户物料码*/,
cmaterialid varchar2(20) null 
/*物料*/,
cmaterialvid varchar2(20) null 
/*物料版本*/,
cvendorid varchar2(20) null 
/*供应商*/,
cprojectid varchar2(20) null 
/*项目*/,
cproductorid varchar2(20) null 
/*生产厂商*/,
cunitid varchar2(20) null 
/*主单位*/,
castunitid varchar2(20) null 
/*单位*/,
vchangerate varchar2(60) null 
/*换算率*/,
nnum number(28,8) null 
/*主数量*/,
nastnum number(28,8) null 
/*数量*/,
cqtunitid varchar2(20) null 
/*报价单位*/,
vqtunitrate varchar2(60) null 
/*报价单位换算率*/,
nqtunitnum number(28,8) null 
/*报价数量*/,
bdiscountflag char(1) null 
/*折扣类*/,
blaborflag char(1) null 
/*服务类*/,
blargessflag char(1) null 
/*赠品*/,
pk_batchcode varchar2(20) null 
/*批次档案*/,
vbatchcode varchar2(40) null 
/*批次号*/,
ntaxrate number(28,8) null 
/*税率*/,
ndiscountrate number(28,8) null 
/*整单折扣*/,
nitemdiscountrate number(28,8) null 
/*单品折扣*/,
ninvoicedisrate number(28,8) null 
/*发票折扣*/,
norigtaxprice number(20,8) null 
/*主含税单价*/,
norigprice number(28,8) null 
/*主无税单价*/,
norigtaxnetprice number(28,8) null 
/*主含税净价*/,
norignetprice number(28,8) null 
/*主无税净价*/,
ntaxprice number(28,8) null 
/*主本币含税单价*/,
nprice number(28,8) null 
/*主本币无税单价*/,
ntaxnetprice number(28,8) null 
/*主本币含税净价*/,
nnetprice number(28,8) null 
/*主本币无税净价*/,
nqtorigtaxprice number(28,8) null 
/*含税单价*/,
nqtorigprice number(28,8) null 
/*无税单价*/,
nqtorigtaxnetprc number(28,8) null 
/*含税净价*/,
nqtorignetprice number(28,8) null 
/*无税净价*/,
nqttaxprice number(28,8) null 
/*本币含税单价*/,
nqtprice number(28,8) null 
/*本币无税单价*/,
nqttaxnetprice number(28,8) null 
/*本币含税净价*/,
nqtnetprice number(28,8) null 
/*本币无税净价*/,
ntax number(28,8) null 
/*本币税额*/,
nmny number(28,8) null 
/*本币无税金额*/,
ntaxmny number(28,8) null 
/*本币价税合计*/,
ndiscount number(28,8) null 
/*本币折扣额*/,
norigmny number(28,8) null 
/*无税金额*/,
norigtaxmny number(28,8) null 
/*价税合计*/,
norigdiscount number(28,8) null 
/*折扣额*/,
norigsubmny number(28,8) null 
/*冲抵金额*/,
ngroupmny number(28,8) null 
/*集团本币无税金额*/,
ngrouptaxmny number(28,8) null 
/*集团本币价税合计*/,
nglobalmny number(28,8) null 
/*全局本币无税金额*/,
nglobaltaxmny number(28,8) null 
/*全局本币价税合计*/,
vfirsttype varchar2(20) null 
/*源头单据类型*/,
vfirstcode varchar2(40) null 
/*源头单据号*/,
vfirsttrantype varchar2(20) null 
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
copposesrcbid varchar2(20) null 
/*对冲来源表体ID*/,
csaleorgid varchar2(20) null 
/*销售组织*/,
csaleorgvid varchar2(20) null 
/*销售组织版本*/,
cprofitcenterid varchar2(20) null 
/*结算利润中心*/,
cprofitcentervid varchar2(20) null 
/*结算利润中心版本*/,
carorgid varchar2(20) null 
/*应收组织*/,
carorgvid varchar2(20) null 
/*应收组织版本*/,
cordercustid varchar2(20) null 
/*订单客户*/,
bfreecustflag char(1) null 
/*是否散户*/,
cfreecustid varchar2(20) null 
/*散户*/,
cdeptid varchar2(20) null 
/*销售部门*/,
cdeptvid varchar2(20) null 
/*销售部门版本*/,
cemployeeid varchar2(20) null 
/*销售业务员*/,
creceivecustid varchar2(20) null 
/*收货客户*/,
creceiveaddrid varchar2(20) null 
/*收货地址*/,
ctransporttypeid varchar2(20) null 
/*运输方式*/,
csendstockorgid varchar2(20) null 
/*库存组织*/,
csendstockorgvid varchar2(20) null 
/*库存组织版本*/,
csendstordocid varchar2(20) null 
/*仓库*/,
cprodlineid varchar2(20) null 
/*产品线*/,
ccostsubjid varchar2(20) null 
/*收支项目*/,
cctmanageid varchar2(20) null 
/*合同*/,
vsumcode varchar2(40) null 
/*消耗汇总号*/,
csumid varchar2(20) null 
/*消耗汇总主键*/,
cvmivenderid varchar2(20) null 
/*寄存供应商*/,
nshouldoutnum number(28,8) null 
/*累计应发未出库数量*/,
ntotaloutnum number(28,8) null 
/*累计出库数量*/,
ntotalincomenum number(28,8) null 
/*累计确认应收数量*/,
ntotalincomemny number(28,8) null 
/*累计确认应收金额*/,
ntotalcostnum number(28,8) null 
/*累计成本结算数量*/,
ntotalpaymny number(28,8) null 
/*累计收款金额*/,
vrownote varchar2(181) null 
/*备注*/,
vfree1 varchar2(101) null 
/*物料辅助属性1*/,
vfree2 varchar2(101) null 
/*物料辅助属性2*/,
vfree3 varchar2(101) null 
/*物料辅助属性3*/,
vfree4 varchar2(101) null 
/*物料辅助属性4*/,
vfree5 varchar2(101) null 
/*物料辅助属性5*/,
vfree6 varchar2(101) null 
/*物料辅助属性6*/,
vfree7 varchar2(101) null 
/*物料辅助属性7*/,
vfree8 varchar2(101) null 
/*物料辅助属性8*/,
vfree9 varchar2(101) null 
/*物料辅助属性9*/,
vfree10 varchar2(101) null 
/*物料辅助属性10*/,
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
ctaxcodeid varchar2(20) null 
/*税码*/,
ftaxtypeflag integer null 
/*扣税类别*/,
ncaltaxmny number(28,8) null 
/*计税金额*/,
cchanneltypeid varchar2(20) null 
/*销售渠道类型*/,
csprofitcentervid varchar2(20) null 
/*发货利润中心*/,
csprofitcenterid varchar2(20) null 
/*发货利润中心最新版本*/,
cmffileid varchar2(20) null 
/*特征码*/,
 constraint pk_saleinvoice_b primary key (csaleinvoicebid),
 ts char(19) default to_char(sysdate,'yyyy-mm-dd hh24:mi:ss'),
dr number(10) default 0
)
/

/* tablename: 销售发票主表 */
create table so_saleinvoice (csaleinvoiceid char(20) not null 
/*发票主表ID*/,
pk_group varchar2(20) null 
/*集团*/,
pk_org varchar2(20) null 
/*开票组织*/,
pk_org_v varchar2(20) null 
/*开票组织版本*/,
vbillcode varchar2(40) null 
/*发票号*/,
cbiztypeid varchar2(20) null 
/*业务流程*/,
ctrantypeid varchar2(20) null 
/*发票类型*/,
vtrantypecode varchar2(20) null 
/*发票类型编码*/,
cinvoicecustid varchar2(20) null 
/*开票客户*/,
dbilldate char(19) null 
/*开票日期*/,
vprintcustname varchar2(50) null 
/*客户打印名称*/,
ccustbankid varchar2(20) null 
/*客户开户银行*/,
ccustbankaccid varchar2(20) null 
/*客户银行账号*/,
cpaytermid varchar2(20) null 
/*收付款协议*/,
vcreditnum varchar2(20) null 
/*信用证号*/,
vgoldtaxcode varchar2(100) null 
/*金税票号*/,
btogoldtaxflag char(1) null 
/*是否已经传金税*/,
tgoldtaxtime varchar2(19) null 
/*最后传金税时间*/,
corigcurrencyid varchar2(20) null 
/*原币*/,
nexchangerate number(28,8) null 
/*折本汇率*/,
ccurrencyid varchar2(20) null 
/*本币币种*/,
ngroupexchgrate number(28,8) null 
/*集团本位币汇率*/,
nglobalexchgrate number(28,8) null 
/*全局本位币汇率*/,
nhvoicedisrate number(28,8) null 
/*发票折扣*/,
ntotalastnum number(28,8) null 
/*总数量*/,
ntotalorigmny number(28,8) null 
/*价税合计*/,
ntotalorigsubmny number(28,8) null 
/*冲抵金额*/,
bsubunitflag char(1) null 
/*冲抵标记*/,
fopposeflag integer null 
/*对冲标记*/,
vopposesrccode varchar2(40) null 
/*对冲来源发票号*/,
copposesrcid varchar2(20) null 
/*对冲来源表头ID*/,
vnote varchar2(181) null 
/*备注*/,
fstatusflag integer null 
/*单据状态*/,
creator varchar2(20) null 
/*创建人*/,
billmaker varchar2(20) null 
/*制单人*/,
creationtime char(19) null 
/*创建时间*/,
modifier varchar2(20) null 
/*最后修改人*/,
modifiedtime char(19) null 
/*最后修改时间*/,
approver varchar2(20) null 
/*审批人*/,
taudittime varchar2(19) null 
/*审核日期*/,
iprintcount integer default 0 null 
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
dmakedate char(19) null 
/*制单日期*/,
crececountryid varchar2(20) null 
/*收货国家/地区*/,
csendcountryid varchar2(20) null 
/*发货国家/地区*/,
ctaxcountryid varchar2(20) null 
/*报税国家/地区*/,
fbuysellflag integer null 
/*购销类型*/,
btriatradeflag char(1) null 
/*三角贸易*/,
ctradewordid varchar2(20) null 
/*贸易术语*/,
vvatcode varchar2(40) null 
/*VAT注册码*/,
vcustvatcode varchar2(40) null 
/*客户VAT注册码*/,
cbalancetypeid varchar2(20) null 
/*结算方式*/,
 constraint pk_saleinvoice primary key (csaleinvoiceid),
 ts char(19) default to_char(sysdate,'yyyy-mm-dd hh24:mi:ss'),
dr number(10) default 0
)
/

