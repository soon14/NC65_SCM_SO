insert into pub_msgtemp(pk_templet,dr,langcode,messagetitle,pk_org,pk_outtemplet,tempcode,tempdscrpt,tempname,textcontent,ts,typecode,attachdoctype,attachtype,attdeftype,tempname2,tempname3,tempname4,tempname5,tempname6,pk_temptype) values('0001Z810000000001GHD',0,'simpchn','预订单失效预警@&s DATE@@&s TIME@','~','1001Z81000000000111G','preorderPreAlertMsgTemp','即将失效关闭的预订单预警','预订单失效预警消息模板','预订单失效预警@&s DATE@@&s TIME@','2013-05-09 17:52:29','nc.bs.so.m38.plugin.task.PreorderAlertPlugin',null,null,null,null,null,null,null,null,null)
go

insert into pub_msgtemp(pk_templet,dr,langcode,messagetitle,pk_org,pk_outtemplet,tempcode,tempdscrpt,tempname,textcontent,ts,typecode,attachdoctype,attachtype,attdeftype,tempname2,tempname3,tempname4,tempname5,tempname6,pk_temptype) values('0001Z810000000001GHE',0,'simpchn','报价单失效预警@&s DATE@@&s TIME@','~','1001Z8100000000011CR','salequotationAlertMsgTemp','即将失效关闭的的报价单预警','报价单自动失效预警消息模板','报价单失效预警@&s DATE@@&s TIME@','2013-05-09 17:53:25','nc.bs.so.salequotation.task.SalequoAlertPlugin',null,null,null,null,null,null,null,null,null)
go

insert into pub_msgtemp(pk_templet,dr,langcode,messagetitle,pk_org,pk_outtemplet,tempcode,tempdscrpt,tempname,textcontent,ts,typecode,attachdoctype,attachtype,attdeftype,tempname2,tempname3,tempname4,tempname5,tempname6,pk_temptype) values('1001Z8100000000016FU',0,'tradchn','預警單失效預警@&s DATE@@&s TIME@','~','1001Z8100000000024NY','preorderPreAlertMsgTemp','即將失效關閉的預訂單預警','預訂單失效預警消息模板','預警單失效預警@&s DATE@@&s TIME@','2013-05-09 17:52:29','nc.bs.so.m38.plugin.task.PreorderAlertPlugin',null,null,null,null,null,null,null,null,null)
go

insert into pub_msgtemp(pk_templet,dr,langcode,messagetitle,pk_org,pk_outtemplet,tempcode,tempdscrpt,tempname,textcontent,ts,typecode,attachdoctype,attachtype,attdeftype,tempname2,tempname3,tempname4,tempname5,tempname6,pk_temptype) values('1001Z8100000000024E2',0,'tradchn','報价單失效預警@&s DATE@@&s TIME@','~','1001Z8100000000024F3','salequotationAlertMsgTemp','即將失效關閉的報价單失效預警','報价單自動失效預警消息模板','報价單失效預警@&s DATE@@&s TIME@','2013-05-09 17:53:25','nc.bs.so.salequotation.task.SalequoAlertPlugin',null,null,null,null,null,null,null,null,null)
go

insert into pub_msgtemp(pk_templet,dr,langcode,messagetitle,pk_org,pk_outtemplet,tempcode,tempdscrpt,tempname,textcontent,ts,typecode,attachdoctype,attachtype,attdeftype,tempname2,tempname3,tempname4,tempname5,tempname6,pk_temptype) values('1001Z8100000000024E3',0,'english','The Prealert Message for validating Salequotation@&s DATE@@&s TIME@','~','1001Z8100000000024I9','salequotationAlertMsgTemp','The Prealert Message for validating Salequotation','SalequoValidateAlertMsgTemp','The Prealert Message for validating Salequotation@&s DATE@@&s TIME@','2013-05-09 17:53:25','nc.bs.so.salequotation.task.SalequoAlertPlugin',null,null,null,null,null,null,null,null,null)
go

insert into pub_msgtemp(pk_templet,dr,langcode,messagetitle,pk_org,pk_outtemplet,tempcode,tempdscrpt,tempname,textcontent,ts,typecode,attachdoctype,attachtype,attdeftype,tempname2,tempname3,tempname4,tempname5,tempname6,pk_temptype) values('1001Z8100000000024E5',0,'english','The Prealert Message for validating Preorder','~','1001Z8100000000024L2','preorderPreAlertMsgTemp','The Prealert Message for validating Preorder','PreorderValidateAlertMsgTemp','The Prealert Message for validating Preorder','2013-05-09 17:52:29','nc.bs.so.m38.plugin.task.PreorderAlertPlugin',null,null,null,null,null,null,null,null,null)
go

insert into pub_msgtemp(pk_templet,dr,langcode,messagetitle,pk_org,pk_outtemplet,tempcode,tempdscrpt,tempname,textcontent,ts,typecode,attachdoctype,attachtype,attdeftype,tempname2,tempname3,tempname4,tempname5,tempname6,pk_temptype) values('1001Z81000000000E8DM',0,'simpchn','@&b sender@ 提交的销售订单待审批！','GLOBLE00000000000000','1002Z81000000000LGE7','SO_MP_30',null,'销售订单','<div class="divtext">
<span  class="labeltext">销售组织:</span >
<span  class="normaltext">@&m pk_org.name@</span >
</div>
 <div class="divtext">
<span  class="labeltext">交易类型:</span >
<span  class="normaltext">@&m ctrantypeid.billtypename@</span >
</div>
 <div class="divtext">
<span  class="labeltext">订单客户:</span >
<span  class="keytext">@&m ccustomerid.name@</span >
</div>
<div class="divtext">
<span  class="labeltext">销售部门:</span >
<span  class="normaltext">@&m cdeptvid.name@</span >
</div>
 <div class="divtext">
<span  class="labeltext">总数量:</span >
<span  class="normaltext">@&m ntotalnum@</span >
</div>
 <div class="divtext">
<span  class="labeltext">价税合计:</span >
<span  class="keytext">@&m ntotalorigmny@</span >
</div>','2015-11-04 10:40:46','30',1,0,'1',null,null,null,null,null,'1001Z81000000000E8CK')
go

insert into pub_msgtemp(pk_templet,dr,langcode,messagetitle,pk_org,pk_outtemplet,tempcode,tempdscrpt,tempname,textcontent,ts,typecode,attachdoctype,attachtype,attdeftype,tempname2,tempname3,tempname4,tempname5,tempname6,pk_temptype) values('1001Z81000000000E8F9',0,'simpchn','@&b sender@ 提交的发货单待审批！','GLOBLE00000000000000','1001Z810000000007UEP','SO_MP_4331',null,'发货单','<div class="divtext">
<span  class="labeltext">物流组织:</span >
<span  class="normaltext">@&m pk_org.name@</span >
</div>
 <div class="divtext">
<span  class="labeltext">交易类型:</span >
<span  class="normaltext">@&m ctrantypeid.billtypename@</span >
</div>
<div class="divtext">
<span  class="labeltext">发货部门:</span >
<span  class="normaltext">@&m csenddeptvid.name@</span >
</div>
 <div class="divtext">
<span  class="labeltext">总数量:</span >
<span  class="normaltext">@&m ntotalastnum@</span >
</div>','2015-11-04 11:18:16','4331',1,0,'1',null,null,null,null,null,'1001Z81000000000E8EW')
go

insert into pub_msgtemp(pk_templet,dr,langcode,messagetitle,pk_org,pk_outtemplet,tempcode,tempdscrpt,tempname,textcontent,ts,typecode,attachdoctype,attachtype,attdeftype,tempname2,tempname3,tempname4,tempname5,tempname6,pk_temptype) values('1001Z81000000000E8FI',0,'simpchn','@&b sender@ 提交的销售发票待审批！','GLOBLE00000000000000','1002Z81000000000LH9H','SO_MP_32',null,'销售发票','<div class="divtext">
<span  class="labeltext">财务组织:</span >
<span  class="normaltext">@&m pk_org.name@</span >
</div>
 <div class="divtext">
<span  class="labeltext">交易类型:</span >
<span  class="normaltext">@&m ctrantypeid.billtypename@</span >
</div>
<div class="divtext">
<span  class="labeltext">开票客户:</span >
<span  class="normaltext">@&m cinvoicecustid.name@</span >
</div>
 <div class="divtext">
<span  class="labeltext">价税合计:</span >
<span  class="normaltext">@&m ntotalorigmny@</span >
</div>','2015-11-04 11:14:00','32',1,0,'1',null,null,null,null,null,'1001Z81000000000E8FC')
go

insert into pub_msgtemp(pk_templet,dr,langcode,messagetitle,pk_org,pk_outtemplet,tempcode,tempdscrpt,tempname,textcontent,ts,typecode,attachdoctype,attachtype,attdeftype,tempname2,tempname3,tempname4,tempname5,tempname6,pk_temptype) values('1001Z81000000000E8FR',0,'simpchn','@&b sender@ 提交的报价单待审批！','GLOBLE00000000000000','1001Z8100000000081CP','SO_MP_4310',null,'报价单','<div class="divtext">
<span  class="labeltext">销售组织:</span >
<span  class="normaltext">@&m pk_org.name@</span >
</div>
 <div class="divtext">
<span  class="labeltext">交易类型:</span >
<span  class="normaltext">@&m ctrantypeid.billtypename@</span >
</div>
 <div class="divtext">
<span  class="labeltext">客户:</span >
<span  class="keytext">@&m pk_customer.name@</span >
</div>
 <div class="divtext">
<span  class="labeltext">部门:</span >
<span  class="normaltext">@&m pk_dept.name@</span >
</div>','2015-11-06 09:45:01','4310',1,0,'1',null,null,null,null,null,'1001Z81000000000E8FL')
go

insert into pub_msgtemp(pk_templet,dr,langcode,messagetitle,pk_org,pk_outtemplet,tempcode,tempdscrpt,tempname,textcontent,ts,typecode,attachdoctype,attachtype,attdeftype,tempname2,tempname3,tempname4,tempname5,tempname6,pk_temptype) values('1001Z81000000000E8G1',0,'simpchn','@&b sender@ 提交的销售订单修订待审批！','GLOBLE00000000000000','1002Z81000000000LFO2','SO_MP_30R',null,'销售订单修订','<div class="divtext">
<span  class="labeltext">销售组织:</span >
<span  class="normaltext">@&m pk_org.name@</span >
</div>
 <div class="divtext">
<span  class="labeltext">交易类型:</span >
<span  class="normaltext">@&m ctrantypeid.billtypename@</span >
</div>
 <div class="divtext">
<span  class="labeltext">订单客户:</span >
<span  class="keytext">@&m ccustomerid.name@</span >
</div>
<div class="divtext">
<span  class="labeltext">销售部门:</span >
<span  class="normaltext">@&m cdeptvid.name@</span >
</div>
 <div class="divtext">
<span  class="labeltext">总数量:</span >
<span  class="normaltext">@&m ntotalnum@</span >
</div>
 <div class="divtext">
<span  class="labeltext">价税合计:</span >
<span  class="keytext">@&m ntotalorigmny@</span >
</div>','2015-11-04 10:42:49','30R',1,0,'1',null,null,null,null,null,'1001Z81000000000E8DU')
go

