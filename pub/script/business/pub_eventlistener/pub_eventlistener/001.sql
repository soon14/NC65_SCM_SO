insert into pub_eventlistener(pk_eventlistener,dr,enabled,implclassname,industrytype,localtype,name,note,operindex,owner,pk_eventtype,ts,comp) values('0001Z81000000008CEUS',0,'Y','nc.impl.so.m38.listener.DestBillUpdateAfterListener','0','~','~',null,null,'4006','1001Z810000000000JAJ','2015-06-01 11:38:42',null)
go

insert into pub_eventlistener(pk_eventlistener,dr,enabled,implclassname,industrytype,localtype,name,note,operindex,owner,pk_eventtype,ts,comp) values('1001Z81000000000070R',0,'Y','nc.pubimpl.so.sobalance.arap.listener.GatheringUpdateBeforeListener','0','~','~',null,null,'4006','1001Z31000000001EDE9','2013-12-04 16:36:12',null)
go

insert into pub_eventlistener(pk_eventlistener,dr,enabled,implclassname,industrytype,localtype,name,note,operindex,owner,pk_eventtype,ts,comp) values('1001Z81000000000070S',0,'Y','nc.pubimpl.so.sobalance.arap.listener.GatheringDelBeforeListener','0','~','~',null,null,'4006','1001Z31000000001EDEC','2013-12-04 16:36:12',null)
go

insert into pub_eventlistener(pk_eventlistener,dr,enabled,implclassname,industrytype,localtype,name,note,operindex,owner,pk_eventtype,ts,comp) values('1001Z81000000000070W',0,'Y','nc.pubimpl.so.sobalance.arap.listener.VerifyAfterListener','0','~','~',null,null,'4006','1002Z310000000009DZ4','2013-12-04 16:36:12',null)
go

insert into pub_eventlistener(pk_eventlistener,dr,enabled,implclassname,industrytype,localtype,name,note,operindex,owner,pk_eventtype,ts,comp) values('1001Z81000000000070X',0,'Y','nc.pubimpl.so.sobalance.arap.listener.UnVerifyAfterListener','0','~','~',null,null,'4006','1002Z310000000009DZ6','2013-12-04 16:36:12',null)
go

insert into pub_eventlistener(pk_eventlistener,dr,enabled,implclassname,industrytype,localtype,name,note,operindex,owner,pk_eventtype,ts,comp) values('1001Z8100000000008PV',0,'Y','nc.pubimpl.so.sobalance.arap.listener.GatheringAddAfterListener','0','~','~',null,null,'4006','1001Z3100000000088C4','2013-12-04 16:36:12',null)
go

insert into pub_eventlistener(pk_eventlistener,dr,enabled,implclassname,industrytype,localtype,name,note,operindex,owner,pk_eventtype,ts,comp) values('1001Z8100000000008PW',0,'Y','nc.pubimpl.so.sobalance.arap.listener.GatheringAddAfterListener','0','~','~',null,null,'4006','1001Z3100000000088C7','2013-12-04 16:36:12',null)
go

insert into pub_eventlistener(pk_eventlistener,dr,enabled,implclassname,industrytype,localtype,name,note,operindex,owner,pk_eventtype,ts,comp) values('1001Z810000000009ZKI',0,'Y','nc.pubimpl.so.m35.arap.handler.GatheringAddAfterHandler','0','~','~','收款单新增后回写客户费用单',null,'4006','1001Z3100000000088C4','2014-12-31 10:44:22',null)
go

insert into pub_eventlistener(pk_eventlistener,dr,enabled,implclassname,industrytype,localtype,name,note,operindex,owner,pk_eventtype,ts,comp) values('1001Z810000000009ZKJ',0,'Y','nc.pubimpl.so.m35.arap.handler.GatheringUpdateAfterHandler','0','~','~','收款单修改后回写客户费用单',null,'4006','1001Z3100000000088C7','2014-12-31 10:45:36',null)
go

insert into pub_eventlistener(pk_eventlistener,dr,enabled,implclassname,industrytype,localtype,name,note,operindex,owner,pk_eventtype,ts,comp) values('1001Z810000000009ZKK',0,'Y','nc.pubimpl.so.m35.arap.handler.GatheringDeleteAfterHandler','0','~','~','客户收款单删除后回写客户费用单',10,'4006','1001Z3100000000088CB','2014-12-31 10:46:32',null)
go

insert into pub_eventlistener(pk_eventlistener,dr,enabled,implclassname,industrytype,localtype,name,note,operindex,owner,pk_eventtype,ts,comp) values('1001Z81000000001C3YI',0,'Y','nc.pubimpl.so.custmatrel.mergelistener.SOCustMaterMergeListener','0','~','~','删除涉及被合并客户的客户与关系定义记录',null,'4006','1008Z010000000003CDZ','2013-12-04 16:36:12',null)
go

insert into pub_eventlistener(pk_eventlistener,dr,enabled,implclassname,industrytype,localtype,name,note,operindex,owner,pk_eventtype,ts,comp) values('1001Z81000000003370W',0,'Y','nc.pubimpl.so.m33.arap.ar.SquareCtrlAfterARVerify','0','~','~','财务核销后-回写销售结算核销金额',null,'4006','1002Z310000000009DZ4','2013-12-04 16:36:12',null)
go

insert into pub_eventlistener(pk_eventlistener,dr,enabled,implclassname,industrytype,localtype,name,note,operindex,owner,pk_eventtype,ts,comp) values('1001Z81000000013370X',0,'Y','nc.pubimpl.so.m33.arap.ar.SquareCtrlAfterARUnVerify','0','~','~','财务反核销后-回写销售结算核销金额',null,'4006','1002Z310000000009DZ6','2013-12-04 16:36:12',null)
go

insert into pub_eventlistener(pk_eventlistener,dr,enabled,implclassname,industrytype,localtype,name,note,operindex,owner,pk_eventtype,ts,comp) values('1002Z810000000003N7A',0,'Y','nc.pubimpl.so.m35.arap.handler.ReceivableAfterDelHandler','0','~','~','客户应收单删除后删除回写销售费用单',10,'4006','1001Z3100000000087J5','2013-12-04 16:36:12',null)
go

insert into pub_eventlistener(pk_eventlistener,dr,enabled,implclassname,industrytype,localtype,name,note,operindex,owner,pk_eventtype,ts,comp) values('1002Z810000000003N7B',0,'Y','nc.pubimpl.so.m35.arap.handler.ReceivableAfterAddHandler','0','~','~','客户应收单新增后删除回写销售费用单',10,'4006','1001Z31000000000R42Y','2013-12-04 16:36:12',null)
go

insert into pub_eventlistener(pk_eventlistener,dr,enabled,implclassname,industrytype,localtype,name,note,operindex,owner,pk_eventtype,ts,comp) values('1002Z81000000000QD2P',0,'Y','nc.pubimpl.so.m35.arap.handler.ReceivableAfterEditHandler','0','~','~','应收单-修改后-销售费用单',10,'4006','1001Z31000000000R43Y','2013-12-04 16:36:12',null)
go

insert into pub_eventlistener(pk_eventlistener,dr,enabled,implclassname,industrytype,localtype,name,note,operindex,owner,pk_eventtype,ts,comp) values('1002Z81000033DELM1ZW',0,'Y','nc.pubimpl.so.m33.arap.ar.SquareCtrlARBillBeforeDelHandler','0','~','~','来源于销售结算的应收单不能手工删除',null,'4006','1001Z31000000001EDDA','2013-12-04 16:36:12',null)
go

insert into pub_eventlistener(pk_eventlistener,dr,enabled,implclassname,industrytype,localtype,name,note,operindex,owner,pk_eventtype,ts,comp) values('1006Z81000000000A5QS',0,'Y','nc.pubimpl.so.m33.so.m32.ArapTermDateQueryServiceImpl','0','~','~',null,null,'4006','0001Z310000000003MEN','2015-03-10 09:30:28',null)
go

