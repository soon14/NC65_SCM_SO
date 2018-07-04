/* tablename: 买赠设置 */
create table so_buylargess (pk_buylargess char(20) not null 
/*买赠主表id*/,
pk_org varchar2(20) null 
/*销售组织*/,
pk_group varchar2(20) null 
/*集团*/,
cbuymarid varchar2(20) null 
/*物料编码*/,
cbuyunitid varchar2(20) null 
/*单位*/,
pk_marbasclass varchar2(20) null 
/*物料基本分类*/,
pk_marsaleclass varchar2(20) null 
/*物料销售分类*/,
pk_customer varchar2(20) null 
/*客户*/,
pk_custclass varchar2(20) null 
/*客户基本分类*/,
pk_custsaleclass varchar2(20) null 
/*客户销售分类*/,
nbuynum number(28,8) null 
/*购买数量*/,
pk_currinfo varchar2(20) null 
/*币种*/,
islow char(1) null 
/*适用下级*/,
cprioritycode varchar2(40) null 
/*优先码*/,
cpromottypeid varchar2(20) null 
/*促销类型*/,
cmarketactid varchar2(20) null 
/*营销活动*/,
 constraint pk_so_buylargess primary key (pk_buylargess),
 ts char(19) default to_char(sysdate,'yyyy-mm-dd hh24:mi:ss'),
dr number(10) default 0
)
/

/* tablename: 买赠子表 */
create table so_buylargess_b (pk_buylargess_b char(20) not null 
/*买赠子表id*/,
pk_material varchar2(20) null 
/*物料编码*/,
pk_measdoc varchar2(20) null 
/*单位*/,
nnum number(28,8) null 
/*赠送数量*/,
nprice number(28,8) null 
/*单价*/,
nmny number(28,8) null 
/*金额*/,
ftoplimittype integer null 
/*上限类型*/,
ntoplimitvalue number(20,8) null 
/*上限值*/,
dbegdate varchar2(19) null 
/*开始日期*/,
denddate varchar2(19) null 
/*截止日期*/,
pk_buylargess varchar2(20) not null 
/*买赠主表id*/,
pk_group varchar2(20) null 
/*集团*/,
 constraint pk_so_buylargess_b primary key (pk_buylargess_b),
 ts char(19) default to_char(sysdate,'yyyy-mm-dd hh24:mi:ss'),
dr number(10) default 0
)
/

