/* tablename: 报价单交易类型 */
create table so_salequotationtype (pk_trantype char(20) not null 
/*主键*/,
vtrantype varchar(20) null default '~' 
/*报价单交易类型*/,
fsourceflag varchar(50) not null 
/*报价数据来源*/,
fhistoryflag varchar(50) not null 
/*历史报价源*/,
iavgmonth integer null 
/*平均报价月数*/,
vmatchrule varchar(100) null 
/*历史报价匹配规则*/,
bautocloseflag char(1) null 
/*生成合同/订单后自动关闭报价单*/,
pk_group varchar(20) null default '~' 
/*集团*/,
bsuccqteditable char(1) null 
/*询到价格可改*/,
bfailqteditable char(1) null 
/*未询到价格可改*/,
bdiscounteditable char(1) null 
/*允许修改折扣*/,
bcustmatrule char(1) null 
/*客户*/,
bchanmatrule char(1) null 
/*渠道类型*/,
bquotypematrule char(1) null 
/*报价单类型*/,
bdeptmatrule char(1) null 
/*部门*/,
bempmatrule char(1) null 
/*业务员*/,
bpaytermmatrule char(1) null 
/*收款协议*/,
bbalatypematrule char(1) null 
/*结算方式*/,
bcurrtypematrule char(1) null 
/*币种匹配*/,
bunitmatrule char(1) null 
/*单位匹配*/,
bsendtypematrule char(1) null 
/*运输方式*/,
bquallevelmatrule char(1) null 
/*质量等级*/,
bareamatrule char(1) null 
/*收货地区*/,
fmodifymny smallint null 
/*调整金额影响单价*/,
ctrantypeid varchar(20) null 
/*交易类型*/,
bmorerows char(1) null default 'N' 
/*同一货物可否列多行*/,
 constraint pk_lequotationtype primary key (pk_trantype),
 ts char(19) null,
dr smallint null default 0
)
;

/* tablename: 报价单表体 */
create table so_salequotation_b (pk_salequotation_b char(20) not null 
/*销售报价单子表主键*/,
pk_org_v varchar(20) null default '~' 
/*销售组织版本信息*/,
pk_org varchar(20) null default '~' 
/*销售组织*/,
crowno varchar(20) null 
/*行号*/,
ccustmaterialid varchar(20) null 
/*客户物料码*/,
pk_material_v varchar(20) null default '~' 
/*物料编码*/,
pk_material varchar(20) null default '~' 
/*物料最新版本*/,
pk_qualitylevel varchar(20) null default '~' 
/*质量等级*/,
pk_project varchar(20) null default '~' 
/*项目*/,
pk_productor varchar(20) null default '~' 
/*生产厂商*/,
pk_supplier varchar(20) null default '~' 
/*供应商*/,
castunitid varchar(20) null default '~' 
/*单位*/,
nassistnum decimal(28,8) null 
/*数量*/,
nchangerate varchar(60) null 
/*换算率*/,
pk_unit varchar(20) null default '~' 
/*主单位*/,
nnum decimal(28,8) null 
/*主数量*/,
pk_areacl varchar(20) null default '~' 
/*收货地区*/,
vfree1 varchar(100) null 
/*自由辅助属性1*/,
vfree2 varchar(100) null 
/*自由辅助属性2*/,
vfree3 varchar(100) null 
/*自由辅助属性3*/,
vfree4 varchar(100) null 
/*自由辅助属性4*/,
vfree5 varchar(100) null 
/*自由辅助属性5*/,
vfree6 varchar(100) null 
/*自由辅助属性6*/,
vfree7 varchar(100) null 
/*自由辅助属性7*/,
vfree8 varchar(100) null 
/*自由辅助属性8*/,
vfree9 varchar(100) null 
/*自由辅助属性9*/,
vfree10 varchar(100) null 
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
blargessflag char(1) null 
/*赠品*/,
pk_pricepolicy varchar(20) null default '~' 
/*定价策略*/,
pk_tariffdef varchar(20) null default '~' 
/*价目表*/,
pk_pricetype varchar(20) null default '~' 
/*价格项*/,
vpricedetail varchar(20) null default '~' 
/*价格组成*/,
vbnote varchar(181) null 
/*备注*/,
norigprice decimal(28,8) null 
/*主无税单价*/,
norigtaxprice decimal(28,8) null 
/*主含税单价*/,
norignetprice decimal(28,8) null 
/*主无税净价*/,
norigtaxnetprice decimal(28,8) null 
/*主含税净价*/,
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
nordernum decimal(28,8) null 
/*累计生成订单主数量*/,
ncontractnum decimal(28,8) null 
/*累计生成合同主数量*/,
cqtunitid varchar(20) null default '~' 
/*报价单位*/,
nqtnum decimal(28,8) null 
/*报价数量*/,
nqtchangerate varchar(60) null 
/*报价换算率*/,
pk_salequotation varchar(20) null 
/*报价单表头_主键*/,
pk_group varchar(20) null 
/*集团*/,
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
vsrctype varchar(20) null 
/*来源单据类型*/,
vsrctrantype varchar(20) null 
/*来源交易类型*/,
vsrccode varchar(40) null 
/*来源单据号*/,
vsrcrowno varchar(20) null 
/*来源单据行号*/,
csrcid varchar(20) null 
/*来源单据主表*/,
csrcbid varchar(20) null 
/*来源单据附表*/,
vfirsttype varchar(20) null 
/*源头单据类型*/,
vfirsttrantype varchar(20) null 
/*源头交易类型*/,
vfirstcode varchar(40) null 
/*源头单据号*/,
cfirstid varchar(20) null 
/*源头单据主表*/,
cfirstbid varchar(20) null 
/*源头单据子表*/,
vfirstrowno varchar(20) null 
/*源头单据行号*/,
 constraint pk_salequotation_b primary key (pk_salequotation_b),
 ts char(19) null,
dr smallint null default 0
)
;

/* tablename: 报价单表头 */
create table so_salequotation (pk_salequotation char(20) not null 
/*销售报价单主表主键*/,
pk_group varchar(20) null default '~' 
/*集团*/,
pk_org_v varchar(20) null default '~' 
/*销售组织*/,
pk_org varchar(20) null default '~' 
/*销售组织最新版本*/,
vbillcode varchar(40) null 
/*报价单号*/,
vtrantype varchar(20) null default '~' 
/*报价单类型*/,
dquotedate char(19) null 
/*报价日期*/,
denddate varchar(19) null 
/*失效日期*/,
pk_customer varchar(20) null default '~' 
/*客户*/,
pk_channeltype varchar(20) null default '~' 
/*渠道类型*/,
cemployeeid varchar(20) null default '~' 
/*业务员*/,
pk_dept_v varchar(20) null default '~' 
/*部门*/,
pk_dept varchar(20) null default '~' 
/*部门最新版本*/,
pk_payterm varchar(20) null default '~' 
/*收款协议*/,
pk_balatype varchar(20) null default '~' 
/*结算方式*/,
ndiscount decimal(28,8) null 
/*整单折扣(%)*/,
pk_currtype varchar(20) null default '~' 
/*币种*/,
csendtypeid varchar(20) null default '~' 
/*运输方式*/,
ntotalnum decimal(28,8) null 
/*总数量*/,
ntotalmny decimal(28,8) null 
/*价税合计*/,
fstatusflag integer null 
/*单据状态*/,
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
billmaker varchar(20) null default '~' 
/*制单人*/,
dbilldate char(19) null 
/*制单时间*/,
approver varchar(20) null default '~' 
/*审批人*/,
taudittime varchar(19) null 
/*审核日期*/,
modifier varchar(20) null default '~' 
/*最后修改人*/,
modifiedtime char(19) null 
/*最后修改时间*/,
ctrantypeid varchar(20) null 
/*交易类型*/,
creator varchar(20) null 
/*创建人*/,
creationtime char(19) null 
/*创建时间*/,
dmakedate char(19) null 
/*制单日期*/,
vbillsrctype varchar(20) null 
/*整单来源单据类型*/,
cbillsrcid varchar(20) null 
/*整单来源单据ID*/,
 constraint pk_m_salequotation primary key (pk_salequotation),
 ts char(19) null,
dr smallint null default 0
)
;

