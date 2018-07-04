/* tablename: 买赠设置 */
create table so_buylargess (
pk_buylargess nchar(20) not null 
/*买赠主表id*/,
pk_org nvarchar(20) null 
/*销售组织*/,
pk_group nvarchar(20) null 
/*集团*/,
cbuymarid nvarchar(20) null 
/*物料编码*/,
cbuyunitid nvarchar(20) null 
/*单位*/,
pk_marbasclass nvarchar(20) null 
/*物料基本分类*/,
pk_marsaleclass nvarchar(20) null 
/*物料销售分类*/,
pk_customer nvarchar(20) null 
/*客户*/,
pk_custclass nvarchar(20) null 
/*客户基本分类*/,
pk_custsaleclass nvarchar(20) null 
/*客户销售分类*/,
nbuynum decimal(28,8) null 
/*购买数量*/,
pk_currinfo nvarchar(20) null 
/*币种*/,
islow nchar(1) null 
/*适用下级*/,
cprioritycode nvarchar(40) null 
/*优先码*/,
cpromottypeid nvarchar(20) null 
/*促销类型*/,
cmarketactid nvarchar(20) null 
/*营销活动*/,
 constraint pk_so_buylargess primary key (pk_buylargess),
 ts char(19) null default convert(char(19),getdate(),20),
dr smallint null default 0
)
go

/* tablename: 买赠子表 */
create table so_buylargess_b (
pk_buylargess_b nchar(20) not null 
/*买赠子表id*/,
pk_material nvarchar(20) null 
/*物料编码*/,
pk_measdoc nvarchar(20) null 
/*单位*/,
nnum decimal(28,8) null 
/*赠送数量*/,
nprice decimal(28,8) null 
/*单价*/,
nmny decimal(28,8) null 
/*金额*/,
ftoplimittype int null 
/*上限类型*/,
ntoplimitvalue decimal(20,8) null 
/*上限值*/,
dbegdate nvarchar(19) null 
/*开始日期*/,
denddate nvarchar(19) null 
/*截止日期*/,
pk_buylargess nvarchar(20) not null 
/*买赠主表id*/,
pk_group nvarchar(20) null 
/*集团*/,
 constraint pk_so_buylargess_b primary key (pk_buylargess_b),
 ts char(19) null default convert(char(19),getdate(),20),
dr smallint null default 0
)
go

