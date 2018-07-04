/* tablename: 退货政策分配 */
create table so_returnassign (pk_returnassign char(20) not null 
/*退货政策分配主键*/,
pk_group varchar(20) null 
/*集团*/,
pk_saleorg varchar(20) null 
/*销售组织*/,
pk_custclass varchar(20) null 
/*客户基本分类编码*/,
pk_custsaleclass varchar(20) null 
/*客户销售分类编码*/,
pk_customer varchar(20) null 
/*客户编码*/,
pk_marbasclass varchar(20) null 
/*物料基本分类编码*/,
pk_marsaleclass varchar(20) null 
/*物料销售分类编码*/,
pk_material varchar(20) null 
/*物料编码*/,
pk_productline varchar(20) null 
/*产品线编码*/,
pk_returnpolicy varchar(20) null 
/*退货政策编码*/,
cprioritycode varchar(40) null 
/*优先码*/,
 constraint pk_so_rtnassign primary key (pk_returnassign),
 ts char(19) null,
dr smallint null default 0
)
;

/* tablename: 退货原因 */
create table so_returnreason (vreasonname2 varchar(300) null 
/*退货原因名称2*/,
vreasonname6 varchar(300) null 
/*退货原因名称6*/,
vreasonname5 varchar(300) null 
/*退货原因名称5*/,
vreasonname4 varchar(300) null 
/*退货原因名称4*/,
vreasonname3 varchar(300) null 
/*退货原因名称3*/,
pk_returnreason char(20) not null 
/*退货原因主键*/,
vreasonname varchar(300) null 
/*退货原因名称*/,
vreasoncode varchar(50) null 
/*退货原因编码*/,
freasontype smallint null 
/*退货原因类型*/,
vreturnmode varchar(181) null 
/*默认退货责任处理方式*/,
vnote varchar(181) null 
/*备注*/,
pk_group varchar(20) null default '~' 
/*集团*/,
pk_org varchar(20) null default '~' 
/*业务单元*/,
 constraint pk_so_returnreason primary key (pk_returnreason),
 ts char(19) null,
dr smallint null default 0
)
;

/* tablename: 退货政策设置 */
create table so_returnpolicy (vpolicyname6 varchar(300) null 
/*退货政策名称6*/,
vpolicyname5 varchar(300) null 
/*退货政策名称5*/,
vpolicyname4 varchar(300) null 
/*退货政策名称4*/,
vpolicyname3 varchar(300) null 
/*退货政策名称3*/,
vpolicyname varchar(300) null 
/*退货政策名称*/,
vpolicyname2 varchar(300) null 
/*退货政策名称2*/,
pk_returnpolicy char(20) not null 
/*退货政策主键*/,
vpolicycode varchar(40) null 
/*退货政策编码*/,
vexpressname varchar(50) null 
/*退货政策逻辑表达式*/,
vpolicydetail varchar(181) null 
/*退货政策说明*/,
dstartdate char(19) null 
/*执行开始日期*/,
denddate char(19) null 
/*执行结束日期*/,
pk_group varchar(20) null default '~' 
/*集团*/,
pk_org varchar(20) null default '~' 
/*业务单元*/,
vexpresscode varchar(50) null 
/*退货政策表达式编码*/,
 constraint pk_so_returnpolicy primary key (pk_returnpolicy),
 ts char(19) null,
dr smallint null default 0
)
;

/* tablename: 退货条件 */
create table so_returncndtn (vconditionname4 varchar(300) null 
/*退货条件名称4*/,
vconditionname6 varchar(300) null 
/*退货条件名称6*/,
vconditionname5 varchar(300) null 
/*退货条件名称5*/,
pk_returncndtn char(20) not null 
/*退货条件主键*/,
vconditionname3 varchar(300) null 
/*退货条件名称3*/,
vconditionname2 varchar(300) null 
/*退货条件名称2*/,
vconditionname varchar(300) null 
/*退货条件名称*/,
vconditioncode varchar(40) null 
/*退货条件编码*/,
vexpressname varchar(50) null 
/*退货条件表达式名称*/,
vexpressdetail varchar(181) null 
/*退货条件说明*/,
pk_group varchar(20) null default '~' 
/*集团*/,
pk_org varchar(20) null default '~' 
/*业务单元*/,
vexpresscode varchar(50) null 
/*退货条件表达式编码*/,
 constraint pk_so_returncndtn primary key (pk_returncndtn),
 ts char(19) null,
dr smallint null default 0
)
;

