insert into pub_print_variable(cvariableid,ctemplateid,dr,iformulatype,pk_corp,prepare1,prepare2,prepare3,prepare4,ts,vaimfieldname,vbasetablename,vdatakeyword,vexpress,vtablepkname,vvariablename) values('1001Z81000000000EOPB','1002Z81000000000LFO2',0,1,'0001',null,null,null,null,'2015-11-30 13:58:37',null,null,null,'so_saleorder_b.cpriceformid.priceformstr->getColValue(prm_priceform, priceformstr,pk_priceform,so_saleorder_b.cpriceformid); so_saleorder_b.cpriceformid.priceformstr->iif(isempty(so_saleorder_b.cpriceformid.priceformstr),getColValue(prm_priceform_p, priceformstr,pk_priceform,so_saleorder_b.cpriceformid),so_saleorder_b.cpriceformid.priceformstr)',null,'价格组成字符串')
go

insert into pub_print_variable(cvariableid,ctemplateid,dr,iformulatype,pk_corp,prepare1,prepare2,prepare3,prepare4,ts,vaimfieldname,vbasetablename,vdatakeyword,vexpress,vtablepkname,vvariablename) values('1001Z81000000000EOPC','1002Z81000000000LFO2',0,1,'0001',null,null,null,null,'2015-11-30 13:58:37',null,null,null,'setThMark(so_saleorder_history_b.norigtaxmny)',null,'千分位[价税合计]')
go

insert into pub_print_variable(cvariableid,ctemplateid,dr,iformulatype,pk_corp,prepare1,prepare2,prepare3,prepare4,ts,vaimfieldname,vbasetablename,vdatakeyword,vexpress,vtablepkname,vvariablename) values('1001Z81000000000EOPD','1002Z81000000000LFO2',0,1,'0001',null,null,null,null,'2015-11-30 13:58:37',null,null,null,'setThMark(so_saleorder_history_b.norigdiscount)',null,'千分位[折扣额]')
go

insert into pub_print_variable(cvariableid,ctemplateid,dr,iformulatype,pk_corp,prepare1,prepare2,prepare3,prepare4,ts,vaimfieldname,vbasetablename,vdatakeyword,vexpress,vtablepkname,vvariablename) values('1001Z81000000000EOPE','1002Z81000000000LFO2',0,1,'0001',null,null,null,null,'2015-11-30 13:58:37',null,null,null,'setThMark(so_saleorder_history_b.ntaxrate)',null,'千分位[税率]')
go

insert into pub_print_variable(cvariableid,ctemplateid,dr,iformulatype,pk_corp,prepare1,prepare2,prepare3,prepare4,ts,vaimfieldname,vbasetablename,vdatakeyword,vexpress,vtablepkname,vvariablename) values('1001Z81000000000EOPF','1002Z81000000000LFO2',0,1,'0001',null,null,null,null,'2015-11-30 13:58:37',null,null,null,'setThMark(so_saleorder_history_b.ndiscountrate)',null,'千分位[整单折扣]')
go

insert into pub_print_variable(cvariableid,ctemplateid,dr,iformulatype,pk_corp,prepare1,prepare2,prepare3,prepare4,ts,vaimfieldname,vbasetablename,vdatakeyword,vexpress,vtablepkname,vvariablename) values('1001Z81000000000EOPG','1002Z81000000000LFO2',0,1,'0001',null,null,null,null,'2015-11-30 13:58:37',null,null,null,'setThMark(so_saleorder_history_b.nitemdiscountrate)',null,'千分位[单品折扣]')
go

insert into pub_print_variable(cvariableid,ctemplateid,dr,iformulatype,pk_corp,prepare1,prepare2,prepare3,prepare4,ts,vaimfieldname,vbasetablename,vdatakeyword,vexpress,vtablepkname,vvariablename) values('1001Z81000000000EOPH','1002Z81000000000LFO2',0,1,'0001',null,null,null,null,'2015-11-30 13:58:37',null,null,null,'setThMark(so_saleorder_history_b.nqtorignetprice)',null,'千分位[无税净价]')
go

insert into pub_print_variable(cvariableid,ctemplateid,dr,iformulatype,pk_corp,prepare1,prepare2,prepare3,prepare4,ts,vaimfieldname,vbasetablename,vdatakeyword,vexpress,vtablepkname,vvariablename) values('1001Z81000000000EOPI','1002Z81000000000LFO2',0,1,'0001',null,null,null,null,'2015-11-30 13:58:37',null,null,null,'setThMark(so_saleorder_history_b.nastnum)',null,'千分位[数量]')
go

insert into pub_print_variable(cvariableid,ctemplateid,dr,iformulatype,pk_corp,prepare1,prepare2,prepare3,prepare4,ts,vaimfieldname,vbasetablename,vdatakeyword,vexpress,vtablepkname,vvariablename) values('1001Z81000000000EOPJ','1002Z81000000000LFO2',0,1,'0001',null,null,null,null,'2015-11-30 13:58:37',null,null,null,'setThMark(so_saleorder_history_b.nqtorigtaxnetprc)',null,'千分位[含税净价]')
go

insert into pub_print_variable(cvariableid,ctemplateid,dr,iformulatype,pk_corp,prepare1,prepare2,prepare3,prepare4,ts,vaimfieldname,vbasetablename,vdatakeyword,vexpress,vtablepkname,vvariablename) values('1001Z81000000000EOPK','1002Z81000000000LFO2',0,1,'0001',null,null,null,null,'2015-11-30 13:58:37',null,null,null,'setThMark(so_saleorder_history_b.norigmny)',null,'千分位[无税金额]')
go

insert into pub_print_variable(cvariableid,ctemplateid,dr,iformulatype,pk_corp,prepare1,prepare2,prepare3,prepare4,ts,vaimfieldname,vbasetablename,vdatakeyword,vexpress,vtablepkname,vvariablename) values('1001Z81000000000EOPL','1002Z81000000000LFO2',0,1,'0001',null,null,null,null,'2015-11-30 13:58:37',null,null,null,'setThMark(so_saleorder_history_b.nexchangerate)',null,'千分位[折本汇率]')
go

insert into pub_print_variable(cvariableid,ctemplateid,dr,iformulatype,pk_corp,prepare1,prepare2,prepare3,prepare4,ts,vaimfieldname,vbasetablename,vdatakeyword,vexpress,vtablepkname,vvariablename) values('1001Z81000000000EOPM','1002Z81000000000LFO2',0,1,'0001',null,null,null,null,'2015-11-30 13:58:37',null,null,null,'setThMark(ntotalnum)',null,'千分位[总数量]')
go

insert into pub_print_variable(cvariableid,ctemplateid,dr,iformulatype,pk_corp,prepare1,prepare2,prepare3,prepare4,ts,vaimfieldname,vbasetablename,vdatakeyword,vexpress,vtablepkname,vvariablename) values('1001Z81000000000EOPN','1002Z81000000000LFO2',0,1,'0001',null,null,null,null,'2015-11-30 13:58:37',null,null,null,'setThMark(ntotalorigmny)',null,'千分位[价税合计]')
go

insert into pub_print_variable(cvariableid,ctemplateid,dr,iformulatype,pk_corp,prepare1,prepare2,prepare3,prepare4,ts,vaimfieldname,vbasetablename,vdatakeyword,vexpress,vtablepkname,vvariablename) values('1001Z81000000000EOPO','1002Z81000000000LFO2',0,1,'0001',null,null,null,null,'2015-11-30 13:58:37',null,null,null,'setThMark(npreceivequota)',null,'千分位[订单收款限额]')
go

insert into pub_print_variable(cvariableid,ctemplateid,dr,iformulatype,pk_corp,prepare1,prepare2,prepare3,prepare4,ts,vaimfieldname,vbasetablename,vdatakeyword,vexpress,vtablepkname,vvariablename) values('1001Z81000000000EOPP','1002Z81000000000LFO2',0,1,'0001',null,null,null,null,'2015-11-30 13:58:37',null,null,null,'setThMark(nreceivedmny)',null,'千分位[实际收款金额]')
go

insert into pub_print_variable(cvariableid,ctemplateid,dr,iformulatype,pk_corp,prepare1,prepare2,prepare3,prepare4,ts,vaimfieldname,vbasetablename,vdatakeyword,vexpress,vtablepkname,vvariablename) values('1001Z81000000000EOPQ','1002Z81000000000LFO2',0,1,'0001',null,null,null,null,'2015-11-30 13:58:37',null,null,null,'setThMark(bpreceiveflag)',null,'千分位[收款限额控制预收]')
go

insert into pub_print_variable(cvariableid,ctemplateid,dr,iformulatype,pk_corp,prepare1,prepare2,prepare3,prepare4,ts,vaimfieldname,vbasetablename,vdatakeyword,vexpress,vtablepkname,vvariablename) values('1001Z81000000000EOPR','1002Z81000000000LFO2',0,1,'0001',null,null,null,null,'2015-11-30 13:58:37',null,null,null,'setThMark(so_saleorder_b.nastnum)',null,'千分位[数量]')
go

insert into pub_print_variable(cvariableid,ctemplateid,dr,iformulatype,pk_corp,prepare1,prepare2,prepare3,prepare4,ts,vaimfieldname,vbasetablename,vdatakeyword,vexpress,vtablepkname,vvariablename) values('1001Z81000000000EOPS','1002Z81000000000LFO2',0,1,'0001',null,null,null,null,'2015-11-30 13:58:37',null,null,null,'setThMark(so_saleorder_history_b.nnum)',null,'千分位[主数量]')
go

insert into pub_print_variable(cvariableid,ctemplateid,dr,iformulatype,pk_corp,prepare1,prepare2,prepare3,prepare4,ts,vaimfieldname,vbasetablename,vdatakeyword,vexpress,vtablepkname,vvariablename) values('1001Z81000000000EOPT','1002Z81000000000LFO2',0,1,'0001',null,null,null,null,'2015-11-30 13:58:37',null,null,null,'setThMark(so_saleorder_history_b.nqtorigprice)',null,'千分位[无税单价]')
go

insert into pub_print_variable(cvariableid,ctemplateid,dr,iformulatype,pk_corp,prepare1,prepare2,prepare3,prepare4,ts,vaimfieldname,vbasetablename,vdatakeyword,vexpress,vtablepkname,vvariablename) values('1001Z81000000000EOPU','1002Z81000000000LFO2',0,1,'0001',null,null,null,null,'2015-11-30 13:58:37',null,null,null,'setThMark(npreceiverate)',null,'千分位[订单收款比例]')
go

insert into pub_print_variable(cvariableid,ctemplateid,dr,iformulatype,pk_corp,prepare1,prepare2,prepare3,prepare4,ts,vaimfieldname,vbasetablename,vdatakeyword,vexpress,vtablepkname,vvariablename) values('1001Z81000000000EOPV','1002Z81000000000LFO2',0,1,'0001',null,null,null,null,'2015-11-30 13:58:37',null,null,null,'setThMark(npreceivemny)',null,'千分位[实际预收款金额]')
go

insert into pub_print_variable(cvariableid,ctemplateid,dr,iformulatype,pk_corp,prepare1,prepare2,prepare3,prepare4,ts,vaimfieldname,vbasetablename,vdatakeyword,vexpress,vtablepkname,vvariablename) values('1001Z81000000000EOPW','1002Z81000000000LFO2',0,1,'0001',null,null,null,null,'2015-11-30 13:58:37',null,null,null,'setThMark(ndiscountrate)',null,'千分位[整单折扣]')
go

insert into pub_print_variable(cvariableid,ctemplateid,dr,iformulatype,pk_corp,prepare1,prepare2,prepare3,prepare4,ts,vaimfieldname,vbasetablename,vdatakeyword,vexpress,vtablepkname,vvariablename) values('1001Z81000000000EOPX','1002Z81000000000LFO2',0,1,'0001',null,null,null,null,'2015-11-30 13:58:37',null,null,null,'formataddress(so_saleorder_history_b.creceiveaddrid )',null,'收货地址')
go

insert into pub_print_variable(cvariableid,ctemplateid,dr,iformulatype,pk_corp,prepare1,prepare2,prepare3,prepare4,ts,vaimfieldname,vbasetablename,vdatakeyword,vexpress,vtablepkname,vvariablename) values('1001Z81000000000EOPY','1002Z81000000000LFO2',0,1,'0001',null,null,null,null,'2015-11-30 13:58:37',null,null,null,'setThMark(so_saleorder_history_b.nqtorigtaxprice)',null,'千分位[含税单价]')
go

