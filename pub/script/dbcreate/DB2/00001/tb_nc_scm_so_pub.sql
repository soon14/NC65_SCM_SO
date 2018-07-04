/* tablename: 业务类型设置 */
create table so_busitypeset (pk_busitype char(20) null 
/*业务类型ID*/,
fgeninvtype char(1) null 
/*开票类型*/,
pk_org varchar(20) null 
/*组织*/,
  ts char(19) null,
dr smallint null default 0
)
;

