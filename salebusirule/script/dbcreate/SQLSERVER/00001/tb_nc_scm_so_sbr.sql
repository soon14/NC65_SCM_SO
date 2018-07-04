/* tablename: 客户物料关系主实体 */
create table so_custmatrel (
pk_custmatrel nchar(20) not null 
/*主实体主键*/,
pk_org nvarchar(20) null 
/*销售组织*/,
pk_org_v nvarchar(20) null 
/*销售组织版本*/,
pk_group nvarchar(20) null 
/*集团*/,
allowsale int null 
/*允许销售*/,
 constraint pk_so_custmatrel primary key (pk_custmatrel),
 ts char(19) null default convert(char(19),getdate(),20),
dr smallint null default 0
)
go

/* tablename: 客户物料关系子实体 */
create table so_custmatrel_b (
pk_custmatrel_b nchar(20) not null 
/*子实体主键*/,
pk_org nvarchar(20) null 
/*销售组织*/,
pk_custbaseclass nvarchar(20) null 
/*客户基本分类*/,
pk_custsaleclass nvarchar(20) null 
/*客户销售分类*/,
pk_customer nvarchar(20) null 
/*客户*/,
pk_materialbaseclass nvarchar(20) null 
/*物料基本分类*/,
pk_materialsaleclass nvarchar(20) null 
/*物料销售分类*/,
pk_material nvarchar(20) null 
/*物料最新版本*/,
pk_material_v nvarchar(20) null 
/*物料编码*/,
exclude nchar(1) null 
/*不包含*/,
vnote nvarchar(181) null 
/*行备注*/,
pk_custmatrel nvarchar(20) null 
/*客户物料关系主实体_主键*/,
pk_group nvarchar(20) null 
/*集团*/,
cprioritycode nvarchar(40) null 
/*优先码*/,
 constraint pk_so_custmatrel_b primary key (pk_custmatrel_b),
 ts char(19) null default convert(char(19),getdate(),20),
dr smallint null default 0
)
go

/* tablename: 部门物料关系主实体 */
create table so_depmatrel (
pk_depmatrel nchar(20) not null 
/*主实体主键*/,
pk_org nvarchar(20) null 
/*销售组织*/,
pk_org_v nvarchar(20) null 
/*销售组织版本*/,
pk_group nvarchar(20) null 
/*集团*/,
allowsale int null 
/*允许销售*/,
 constraint pk_so_depmatrel primary key (pk_depmatrel),
 ts char(19) null default convert(char(19),getdate(),20),
dr smallint null default 0
)
go

/* tablename: 部门物料关系子实体 */
create table so_depmatrel_b (
pk_org nvarchar(20) null 
/*销售组织*/,
pk_depmatrel_b nchar(20) not null 
/*子实体主键*/,
pk_dept nvarchar(20) null 
/*部门*/,
pk_dept_v nvarchar(20) null 
/*部门版本*/,
cemployeeid nvarchar(20) null 
/*业务员*/,
pk_materialbaseclass nvarchar(20) null 
/*物料基本分类*/,
pk_materialsaleclass nvarchar(20) null 
/*物料销售分类*/,
pk_material nvarchar(20) null 
/*物料最新版本*/,
pk_material_v nvarchar(20) null 
/*物料编码*/,
exclude nchar(1) null 
/*不包含*/,
vnote nvarchar(181) null 
/*行备注*/,
pk_depmatrel nvarchar(20) null 
/*部门物料关系主实体_主键*/,
pk_group nvarchar(20) null 
/*集团*/,
cprioritycode nvarchar(40) null 
/*优先码*/,
 constraint pk_so_depmatrel_b primary key (pk_depmatrel_b),
 ts char(19) null default convert(char(19),getdate(),20),
dr smallint null default 0
)
go

/* tablename: 订单类型物料关系主实体 */
create table so_tranmatrel (
pk_tranmatrel nchar(20) not null 
/*主实体主键*/,
pk_org nvarchar(20) null 
/*销售组织*/,
pk_org_v nvarchar(20) null 
/*销售组织版本*/,
pk_group nvarchar(20) null 
/*集团*/,
allowsale int null 
/*允许销售*/,
applylower nchar(1) null 
/*适用下级*/,
 constraint pk_so_tranmatrel primary key (pk_tranmatrel),
 ts char(19) null default convert(char(19),getdate(),20),
dr smallint null default 0
)
go

/* tablename: 订单类型物料关系子实体 */
create table so_tranmatrel_b (
pk_tranmatrel_b nchar(20) not null 
/*子实体主键*/,
pk_org nvarchar(20) null 
/*销售组织*/,
trantype nvarchar(20) null 
/*订单类型*/,
pk_materialbaseclass nvarchar(20) null 
/*物料基本分类*/,
pk_materialsaleclass nvarchar(20) null 
/*物料销售分类*/,
pk_material nvarchar(20) null 
/*物料最新版本*/,
pk_material_v nvarchar(20) null 
/*物料编码*/,
exclude nchar(1) null 
/*不包含*/,
vnote nvarchar(181) null 
/*行备注*/,
pk_tranmatrel nvarchar(20) null 
/*订单类型物料关系主实体_主键*/,
pk_group nvarchar(20) null 
/*集团*/,
cprioritycode nvarchar(40) null 
/*优先码*/,
 constraint pk_so_tranmatrel_b primary key (pk_tranmatrel_b),
 ts char(19) null default convert(char(19),getdate(),20),
dr smallint null default 0
)
go

