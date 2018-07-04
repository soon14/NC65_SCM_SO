/* tablename: 退货政策分配 */
create table so_returnassign (
pk_returnassign nchar(20) not null 
/*退货政策分配主键*/,
pk_group nvarchar(20) null 
/*集团*/,
pk_saleorg nvarchar(20) null 
/*销售组织*/,
pk_custclass nvarchar(20) null 
/*客户基本分类编码*/,
pk_custsaleclass nvarchar(20) null 
/*客户销售分类编码*/,
pk_customer nvarchar(20) null 
/*客户编码*/,
pk_marbasclass nvarchar(20) null 
/*物料基本分类编码*/,
pk_marsaleclass nvarchar(20) null 
/*物料销售分类编码*/,
pk_material nvarchar(20) null 
/*物料编码*/,
pk_productline nvarchar(20) null 
/*产品线编码*/,
pk_returnpolicy nvarchar(20) null 
/*退货政策编码*/,
cprioritycode nvarchar(40) null 
/*优先码*/,
 constraint pk_so_rtnassign primary key (pk_returnassign),
 ts char(19) null default convert(char(19),getdate(),20),
dr smallint null default 0
)
go

/* tablename: 退货原因 */
create table so_returnreason (
vreasonname2 nvarchar(300) null 
/*退货原因名称2*/,
vreasonname6 nvarchar(300) null 
/*退货原因名称6*/,
vreasonname5 nvarchar(300) null 
/*退货原因名称5*/,
vreasonname4 nvarchar(300) null 
/*退货原因名称4*/,
vreasonname3 nvarchar(300) null 
/*退货原因名称3*/,
pk_returnreason nchar(20) not null 
/*退货原因主键*/,
vreasonname nvarchar(300) null 
/*退货原因名称*/,
vreasoncode nvarchar(50) null 
/*退货原因编码*/,
freasontype smallint null 
/*退货原因类型*/,
vreturnmode nvarchar(181) null 
/*默认退货责任处理方式*/,
vnote nvarchar(181) null 
/*备注*/,
pk_group nvarchar(20) null default '~' 
/*集团*/,
pk_org nvarchar(20) null default '~' 
/*业务单元*/,
 constraint pk_so_returnreason primary key (pk_returnreason),
 ts char(19) null default convert(char(19),getdate(),20),
dr smallint null default 0
)
go

/* tablename: 退货政策设置 */
create table so_returnpolicy (
vpolicyname6 nvarchar(300) null 
/*退货政策名称6*/,
vpolicyname5 nvarchar(300) null 
/*退货政策名称5*/,
vpolicyname4 nvarchar(300) null 
/*退货政策名称4*/,
vpolicyname3 nvarchar(300) null 
/*退货政策名称3*/,
vpolicyname nvarchar(300) null 
/*退货政策名称*/,
vpolicyname2 nvarchar(300) null 
/*退货政策名称2*/,
pk_returnpolicy nchar(20) not null 
/*退货政策主键*/,
vpolicycode nvarchar(40) null 
/*退货政策编码*/,
vexpressname nvarchar(50) null 
/*退货政策逻辑表达式*/,
vpolicydetail nvarchar(181) null 
/*退货政策说明*/,
dstartdate nchar(19) null 
/*执行开始日期*/,
denddate nchar(19) null 
/*执行结束日期*/,
pk_group nvarchar(20) null default '~' 
/*集团*/,
pk_org nvarchar(20) null default '~' 
/*业务单元*/,
vexpresscode nvarchar(50) null 
/*退货政策表达式编码*/,
 constraint pk_so_returnpolicy primary key (pk_returnpolicy),
 ts char(19) null default convert(char(19),getdate(),20),
dr smallint null default 0
)
go

/* tablename: 退货条件 */
create table so_returncndtn (
vconditionname4 nvarchar(300) null 
/*退货条件名称4*/,
vconditionname6 nvarchar(300) null 
/*退货条件名称6*/,
vconditionname5 nvarchar(300) null 
/*退货条件名称5*/,
pk_returncndtn nchar(20) not null 
/*退货条件主键*/,
vconditionname3 nvarchar(300) null 
/*退货条件名称3*/,
vconditionname2 nvarchar(300) null 
/*退货条件名称2*/,
vconditionname nvarchar(300) null 
/*退货条件名称*/,
vconditioncode nvarchar(40) null 
/*退货条件编码*/,
vexpressname nvarchar(50) null 
/*退货条件表达式名称*/,
vexpressdetail nvarchar(181) null 
/*退货条件说明*/,
pk_group nvarchar(20) null default '~' 
/*集团*/,
pk_org nvarchar(20) null default '~' 
/*业务单元*/,
vexpresscode nvarchar(50) null 
/*退货条件表达式编码*/,
 constraint pk_so_returncndtn primary key (pk_returncndtn),
 ts char(19) null default convert(char(19),getdate(),20),
dr smallint null default 0
)
go

