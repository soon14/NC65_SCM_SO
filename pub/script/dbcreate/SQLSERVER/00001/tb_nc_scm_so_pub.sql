/* tablename: 业务类型设置 */
create table so_busitypeset (
pk_busitype nchar(20) null 
/*业务类型ID*/,
fgeninvtype nchar(1) null 
/*开票类型*/,
pk_org nvarchar(20) null 
/*组织*/,
  ts char(19) null default convert(char(19),getdate(),20),
dr smallint null default 0
)
go

