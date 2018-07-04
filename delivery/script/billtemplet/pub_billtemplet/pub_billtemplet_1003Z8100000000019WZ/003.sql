insert into pub_billtemplet_t(pk_billtemplet_t,basetab,dr,metadataclass,metadatapath,mixindex,pk_billtemplet,pk_layout,pos,position,resid,tabcode,tabindex,tabname,ts,vdef1,vdef2,vdef3) values('0001Z81000000009MQCG',null,0,'so.delivery','delivery',1,'1003Z8100000000019WZ','~',2,2,'1400604020031','approve',0,'表尾信息','2015-06-23 10:23:17',null,null,null)
go

insert into pub_billtemplet_t(pk_billtemplet_t,basetab,dr,metadataclass,metadatapath,mixindex,pk_billtemplet,pk_layout,pos,position,resid,tabcode,tabindex,tabname,ts,vdef1,vdef2,vdef3) values('0001Z81000000009MQCH',null,0,'so.delivery_b','cdeliverybid',null,'1003Z8100000000019WZ','~',1,1,'1400604020028','cdeliverybid',0,'物料信息','2015-06-23 10:23:17',null,null,null)
go

insert into pub_billtemplet_t(pk_billtemplet_t,basetab,dr,metadataclass,metadatapath,mixindex,pk_billtemplet,pk_layout,pos,position,resid,tabcode,tabindex,tabname,ts,vdef1,vdef2,vdef3) values('0001Z81000000009MQCI',null,0,'so.delivery','delivery',null,'1003Z8100000000019WZ','~',0,0,'1400604020026','delivery',0,'发货单主表','2015-06-23 10:23:17',null,null,null)
go

insert into pub_billtemplet_t(pk_billtemplet_t,basetab,dr,metadataclass,metadatapath,mixindex,pk_billtemplet,pk_layout,pos,position,resid,tabcode,tabindex,tabname,ts,vdef1,vdef2,vdef3) values('0001Z81000000009MQCJ','cdeliverybid',0,'so.delivery_b','cdeliverybid',null,'1003Z8100000000019WZ','~',1,1,'1400604020030','send',1,'发货信息','2015-06-23 10:23:17',null,null,null)
go

insert into pub_billtemplet_t(pk_billtemplet_t,basetab,dr,metadataclass,metadatapath,mixindex,pk_billtemplet,pk_layout,pos,position,resid,tabcode,tabindex,tabname,ts,vdef1,vdef2,vdef3) values('0001Z81000000009MQCK','approve',0,'so.delivery',null,2,'1003Z8100000000019WZ','~',2,2,'UC001-0000095','tail',1,'审计信息','2015-06-23 10:23:17',null,null,null)
go

insert into pub_billtemplet_t(pk_billtemplet_t,basetab,dr,metadataclass,metadatapath,mixindex,pk_billtemplet,pk_layout,pos,position,resid,tabcode,tabindex,tabname,ts,vdef1,vdef2,vdef3) values('0001Z81000000009MQCL','cdeliverybid',0,'so.delivery_b','cdeliverybid',null,'1003Z8100000000019WZ','~',1,1,'1400604020027','transport',2,'运输信息','2015-06-23 10:23:17',null,null,null)
go

