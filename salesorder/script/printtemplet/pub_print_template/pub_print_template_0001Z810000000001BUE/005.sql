insert into pub_print_variable(cvariableid,ctemplateid,dr,iformulatype,pk_corp,prepare1,prepare2,prepare3,prepare4,ts,vaimfieldname,vbasetablename,vdatakeyword,vexpress,vtablepkname,vvariablename) values('0001Z8100000000B7D5E','0001Z810000000001BUE',0,1,'0001',null,null,null,null,'2015-07-20 11:00:27',null,null,null,'iif(fstatusflag=="关闭","是","否")',null,'bclose')
go

insert into pub_print_variable(cvariableid,ctemplateid,dr,iformulatype,pk_corp,prepare1,prepare2,prepare3,prepare4,ts,vaimfieldname,vbasetablename,vdatakeyword,vexpress,vtablepkname,vvariablename) values('0001Z8100000000B7D5F','0001Z810000000001BUE',0,1,'0001',null,null,null,null,'2015-07-20 11:00:27',null,null,null,'so_saleorder_b.nsendunfinisednum->iif(so_saleorder_b.ntotalsendnum==null , tonumber(so_saleorder_b.nnum), sub(tonumber(so_saleorder_b.nnum),tonumber(so_saleorder_b.ntotalsendnum)) );',null,'发货未完成量')
go

insert into pub_print_variable(cvariableid,ctemplateid,dr,iformulatype,pk_corp,prepare1,prepare2,prepare3,prepare4,ts,vaimfieldname,vbasetablename,vdatakeyword,vexpress,vtablepkname,vvariablename) values('0001Z8100000000B7D5G','0001Z810000000001BUE',0,1,'0001',null,null,null,null,'2015-07-20 11:00:27',null,null,null,'so_saleorder_b.noutunfinisednum->iif(so_saleorder_b.ntotaloutnum==null , tonumber(so_saleorder_b.nnum), sub(tonumber(so_saleorder_b.nnum),tonumber(so_saleorder_b.ntotaloutnum)) );',null,'出库未完成量')
go

insert into pub_print_variable(cvariableid,ctemplateid,dr,iformulatype,pk_corp,prepare1,prepare2,prepare3,prepare4,ts,vaimfieldname,vbasetablename,vdatakeyword,vexpress,vtablepkname,vvariablename) values('0001Z8100000000B7D5H','0001Z810000000001BUE',0,1,'0001',null,null,null,null,'2015-07-20 11:00:27',null,null,null,'so_saleorder_b.ninvunfinisednum->iif(so_saleorder_b.ntotalinvoicenum==null , tonumber(so_saleorder_b.nnum), sub(tonumber(so_saleorder_b.nnum),tonumber(so_saleorder_b.ntotalinvoicenum)) );',null,'发票未完成量')
go

insert into pub_print_variable(cvariableid,ctemplateid,dr,iformulatype,pk_corp,prepare1,prepare2,prepare3,prepare4,ts,vaimfieldname,vbasetablename,vdatakeyword,vexpress,vtablepkname,vvariablename) values('0001Z8100000000B7D5I','0001Z810000000001BUE',0,1,'0001',null,null,null,null,'2015-07-20 11:00:27',null,null,null,'so_saleorder_b.bbsettleendflag->iif(equalsignorecase( tostring( so_saleorder_b.bbarsettleflag),"是" ), iif(equalsignorecase( tostring( so_saleorder_b.bbcostsettleflag),"是"),"是","否" ) ,"否");',null,'结算关闭')
go

