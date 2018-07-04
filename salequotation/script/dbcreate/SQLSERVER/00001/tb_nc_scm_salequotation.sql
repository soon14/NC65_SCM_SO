/* tablename: 报价单交易类型 */
create table so_salequotationtype (
pk_trantype nchar(20) not null 
/*主键*/,
vtrantype nvarchar(20) null default '~' 
/*报价单交易类型*/,
fsourceflag nvarchar(50) not null 
/*报价数据来源*/,
fhistoryflag nvarchar(50) not null 
/*历史报价源*/,
iavgmonth int null 
/*平均报价月数*/,
vmatchrule nvarchar(100) null 
/*历史报价匹配规则*/,
bautocloseflag nchar(1) null 
/*生成合同/订单后自动关闭报价单*/,
pk_group nvarchar(20) null default '~' 
/*集团*/,
bsuccqteditable nchar(1) null 
/*询到价格可改*/,
bfailqteditable nchar(1) null 
/*未询到价格可改*/,
bdiscounteditable nchar(1) null 
/*允许修改折扣*/,
bcustmatrule nchar(1) null 
/*客户*/,
bchanmatrule nchar(1) null 
/*渠道类型*/,
bquotypematrule nchar(1) null 
/*报价单类型*/,
bdeptmatrule nchar(1) null 
/*部门*/,
bempmatrule nchar(1) null 
/*业务员*/,
bpaytermmatrule nchar(1) null 
/*收款协议*/,
bbalatypematrule nchar(1) null 
/*结算方式*/,
bcurrtypematrule nchar(1) null 
/*币种匹配*/,
bunitmatrule nchar(1) null 
/*单位匹配*/,
bsendtypematrule nchar(1) null 
/*运输方式*/,
bquallevelmatrule nchar(1) null 
/*质量等级*/,
bareamatrule nchar(1) null 
/*收货地区*/,
fmodifymny smallint null 
/*调整金额影响单价*/,
ctrantypeid nvarchar(20) null 
/*交易类型*/,
bmorerows nchar(1) null default 'N' 
/*同一货物可否列多行*/,
 constraint pk_lequotationtype primary key (pk_trantype),
 ts char(19) null default convert(char(19),getdate(),20),
dr smallint null default 0
)
go

/* tablename: 报价单表体 */
create table so_salequotation_b (
pk_salequotation_b nchar(20) not null 
/*销售报价单子表主键*/,
pk_org_v nvarchar(20) null default '~' 
/*销售组织版本信息*/,
pk_org nvarchar(20) null default '~' 
/*销售组织*/,
crowno nvarchar(20) null 
/*行号*/,
ccustmaterialid nvarchar(20) null 
/*客户物料码*/,
pk_material_v nvarchar(20) null default '~' 
/*物料编码*/,
pk_material nvarchar(20) null default '~' 
/*物料最新版本*/,
pk_qualitylevel nvarchar(20) null default '~' 
/*质量等级*/,
pk_project nvarchar(20) null default '~' 
/*项目*/,
pk_productor nvarchar(20) null default '~' 
/*生产厂商*/,
pk_supplier nvarchar(20) null default '~' 
/*供应商*/,
castunitid nvarchar(20) null default '~' 
/*单位*/,
nassistnum decimal(28,8) null 
/*数量*/,
nchangerate nvarchar(60) null 
/*换算率*/,
pk_unit nvarchar(20) null default '~' 
/*主单位*/,
nnum decimal(28,8) null 
/*主数量*/,
pk_areacl nvarchar(20) null default '~' 
/*收货地区*/,
vfree1 nvarchar(100) null 
/*自由辅助属性1*/,
vfree2 nvarchar(100) null 
/*自由辅助属性2*/,
vfree3 nvarchar(100) null 
/*自由辅助属性3*/,
vfree4 nvarchar(100) null 
/*自由辅助属性4*/,
vfree5 nvarchar(100) null 
/*自由辅助属性5*/,
vfree6 nvarchar(100) null 
/*自由辅助属性6*/,
vfree7 nvarchar(100) null 
/*自由辅助属性7*/,
vfree8 nvarchar(100) null 
/*自由辅助属性8*/,
vfree9 nvarchar(100) null 
/*自由辅助属性9*/,
vfree10 nvarchar(100) null 
/*自由辅助属性10*/,
nqtorigprice decimal(28,8) null 
/*无税单价*/,
ntaxrate decimal(28,8) null 
/*税率(%)*/,
nqtorigtaxprice decimal(28,8) null 
/*含税单价*/,
ndiscountrate decimal(28,8) null 
/*整单折扣(%)*/,
nitemdiscountrate decimal(28,8) null 
/*单品折扣(%)*/,
nqtorignetprice decimal(28,8) null 
/*无税净价*/,
nqtorigtaxnetprc decimal(28,8) null 
/*含税净价*/,
norigmny decimal(28,8) null 
/*无税金额*/,
norigtaxmny decimal(28,8) null 
/*价税合计*/,
norigdiscount decimal(28,8) null 
/*折扣额*/,
blargessflag nchar(1) null 
/*赠品*/,
pk_pricepolicy nvarchar(20) null default '~' 
/*定价策略*/,
pk_tariffdef nvarchar(20) null default '~' 
/*价目表*/,
pk_pricetype nvarchar(20) null default '~' 
/*价格项*/,
vpricedetail nvarchar(20) null default '~' 
/*价格组成*/,
vbnote nvarchar(181) null 
/*备注*/,
norigprice decimal(28,8) null 
/*主无税单价*/,
norigtaxprice decimal(28,8) null 
/*主含税单价*/,
norignetprice decimal(28,8) null 
/*主无税净价*/,
norigtaxnetprice decimal(28,8) null 
/*主含税净价*/,
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
nordernum decimal(28,8) null 
/*累计生成订单主数量*/,
ncontractnum decimal(28,8) null 
/*累计生成合同主数量*/,
cqtunitid nvarchar(20) null default '~' 
/*报价单位*/,
nqtnum decimal(28,8) null 
/*报价数量*/,
nqtchangerate nvarchar(60) null 
/*报价换算率*/,
pk_salequotation nvarchar(20) null 
/*报价单表头_主键*/,
pk_group nvarchar(20) null 
/*集团*/,
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
vsrctype nvarchar(20) null 
/*来源单据类型*/,
vsrctrantype nvarchar(20) null 
/*来源交易类型*/,
vsrccode nvarchar(40) null 
/*来源单据号*/,
vsrcrowno nvarchar(20) null 
/*来源单据行号*/,
csrcid nvarchar(20) null 
/*来源单据主表*/,
csrcbid nvarchar(20) null 
/*来源单据附表*/,
vfirsttype nvarchar(20) null 
/*源头单据类型*/,
vfirsttrantype nvarchar(20) null 
/*源头交易类型*/,
vfirstcode nvarchar(40) null 
/*源头单据号*/,
cfirstid nvarchar(20) null 
/*源头单据主表*/,
cfirstbid nvarchar(20) null 
/*源头单据子表*/,
vfirstrowno nvarchar(20) null 
/*源头单据行号*/,
 constraint pk_salequotation_b primary key (pk_salequotation_b),
 ts char(19) null default convert(char(19),getdate(),20),
dr smallint null default 0
)
go

/* tablename: 报价单表头 */
create table so_salequotation (
pk_salequotation nchar(20) not null 
/*销售报价单主表主键*/,
pk_group nvarchar(20) null default '~' 
/*集团*/,
pk_org_v nvarchar(20) null default '~' 
/*销售组织*/,
pk_org nvarchar(20) null default '~' 
/*销售组织最新版本*/,
vbillcode nvarchar(40) null 
/*报价单号*/,
vtrantype nvarchar(20) null default '~' 
/*报价单类型*/,
dquotedate nchar(19) null 
/*报价日期*/,
denddate nvarchar(19) null 
/*失效日期*/,
pk_customer nvarchar(20) null default '~' 
/*客户*/,
pk_channeltype nvarchar(20) null default '~' 
/*渠道类型*/,
cemployeeid nvarchar(20) null default '~' 
/*业务员*/,
pk_dept_v nvarchar(20) null default '~' 
/*部门*/,
pk_dept nvarchar(20) null default '~' 
/*部门最新版本*/,
pk_payterm nvarchar(20) null default '~' 
/*收款协议*/,
pk_balatype nvarchar(20) null default '~' 
/*结算方式*/,
ndiscount decimal(28,8) null 
/*整单折扣(%)*/,
pk_currtype nvarchar(20) null default '~' 
/*币种*/,
csendtypeid nvarchar(20) null default '~' 
/*运输方式*/,
ntotalnum decimal(28,8) null 
/*总数量*/,
ntotalmny decimal(28,8) null 
/*价税合计*/,
fstatusflag int null 
/*单据状态*/,
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
billmaker nvarchar(20) null default '~' 
/*制单人*/,
dbilldate nchar(19) null 
/*制单时间*/,
approver nvarchar(20) null default '~' 
/*审批人*/,
taudittime nvarchar(19) null 
/*审核日期*/,
modifier nvarchar(20) null default '~' 
/*最后修改人*/,
modifiedtime nchar(19) null 
/*最后修改时间*/,
ctrantypeid nvarchar(20) null 
/*交易类型*/,
creator nvarchar(20) null 
/*创建人*/,
creationtime nchar(19) null 
/*创建时间*/,
dmakedate nchar(19) null 
/*制单日期*/,
vbillsrctype nvarchar(20) null 
/*整单来源单据类型*/,
cbillsrcid nvarchar(20) null 
/*整单来源单据ID*/,
 constraint pk_m_salequotation primary key (pk_salequotation),
 ts char(19) null default convert(char(19),getdate(),20),
dr smallint null default 0
)
go

