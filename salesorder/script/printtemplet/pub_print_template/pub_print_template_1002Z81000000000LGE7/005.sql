insert into pub_print_variable(cvariableid,ctemplateid,dr,iformulatype,pk_corp,prepare1,prepare2,prepare3,prepare4,ts,vaimfieldname,vbasetablename,vdatakeyword,vexpress,vtablepkname,vvariablename) values('0001Z8100000000ADDQV','1002Z81000000000LGE7',0,1,'0001',null,null,null,null,'2015-07-06 14:01:05',null,null,null,'setThMark(so_saleorder_b.nexchangerate)',null,'千分位[折本汇率]')
go

insert into pub_print_variable(cvariableid,ctemplateid,dr,iformulatype,pk_corp,prepare1,prepare2,prepare3,prepare4,ts,vaimfieldname,vbasetablename,vdatakeyword,vexpress,vtablepkname,vvariablename) values('0001Z8100000000ADDQW','1002Z81000000000LGE7',0,1,'0001',null,null,null,null,'2015-07-06 14:01:05',null,null,null,'setThMark(so_saleorder_b.nqtorignetprice)',null,'千分位[无税净价]')
go

insert into pub_print_variable(cvariableid,ctemplateid,dr,iformulatype,pk_corp,prepare1,prepare2,prepare3,prepare4,ts,vaimfieldname,vbasetablename,vdatakeyword,vexpress,vtablepkname,vvariablename) values('0001Z8100000000ADDQX','1002Z81000000000LGE7',0,1,'0001',null,null,null,null,'2015-07-06 14:01:05',null,null,null,'setThMark(so_saleorder_b.nqtorigtaxnetprc)',null,'千分位[含税净价]')
go

insert into pub_print_variable(cvariableid,ctemplateid,dr,iformulatype,pk_corp,prepare1,prepare2,prepare3,prepare4,ts,vaimfieldname,vbasetablename,vdatakeyword,vexpress,vtablepkname,vvariablename) values('0001Z8100000000ADDQY','1002Z81000000000LGE7',0,1,'0001',null,null,null,null,'2015-07-06 14:01:05',null,null,null,'setThMark(so_saleorder_b.norigtaxmny)',null,'千分位[价税合计]')
go

insert into pub_print_variable(cvariableid,ctemplateid,dr,iformulatype,pk_corp,prepare1,prepare2,prepare3,prepare4,ts,vaimfieldname,vbasetablename,vdatakeyword,vexpress,vtablepkname,vvariablename) values('0001Z8100000000ADDQZ','1002Z81000000000LGE7',0,1,'0001',null,null,null,null,'2015-07-06 14:01:05',null,null,null,'setThMark(ntotalnum)',null,'千分位[总数量]')
go

insert into pub_print_variable(cvariableid,ctemplateid,dr,iformulatype,pk_corp,prepare1,prepare2,prepare3,prepare4,ts,vaimfieldname,vbasetablename,vdatakeyword,vexpress,vtablepkname,vvariablename) values('0001Z8100000000ADDR0','1002Z81000000000LGE7',0,1,'0001',null,null,null,null,'2015-07-06 14:01:05',null,null,null,'setThMark(npreceivequota)',null,'千分位[订单收款限额]')
go

insert into pub_print_variable(cvariableid,ctemplateid,dr,iformulatype,pk_corp,prepare1,prepare2,prepare3,prepare4,ts,vaimfieldname,vbasetablename,vdatakeyword,vexpress,vtablepkname,vvariablename) values('0001Z8100000000ADDR1','1002Z81000000000LGE7',0,1,'0001',null,null,null,null,'2015-07-06 14:01:05',null,null,null,'setThMark(nreceivedmny)',null,'千分位[实际收款金额]')
go

insert into pub_print_variable(cvariableid,ctemplateid,dr,iformulatype,pk_corp,prepare1,prepare2,prepare3,prepare4,ts,vaimfieldname,vbasetablename,vdatakeyword,vexpress,vtablepkname,vvariablename) values('0001Z8100000000ADDR2','1002Z81000000000LGE7',0,1,'0001',null,null,null,null,'2015-07-06 14:01:05',null,null,null,'setThMark(bpreceiveflag)',null,'千分位[收款限额控制预收]')
go

insert into pub_print_variable(cvariableid,ctemplateid,dr,iformulatype,pk_corp,prepare1,prepare2,prepare3,prepare4,ts,vaimfieldname,vbasetablename,vdatakeyword,vexpress,vtablepkname,vvariablename) values('0001Z8100000000ADDR3','1002Z81000000000LGE7',0,1,'0001',null,null,null,null,'2015-07-06 14:01:05',null,null,null,'setThMark(so_saleorder_b.ndiscountrate)',null,'千分位[整单折扣]')
go

insert into pub_print_variable(cvariableid,ctemplateid,dr,iformulatype,pk_corp,prepare1,prepare2,prepare3,prepare4,ts,vaimfieldname,vbasetablename,vdatakeyword,vexpress,vtablepkname,vvariablename) values('0001Z8100000000ADDR4','1002Z81000000000LGE7',0,1,'0001',null,null,null,null,'2015-07-06 14:01:05',null,null,null,'setThMark(so_saleorder_b.nitemdiscountrate)',null,'千分位[单品折扣]')
go

insert into pub_print_variable(cvariableid,ctemplateid,dr,iformulatype,pk_corp,prepare1,prepare2,prepare3,prepare4,ts,vaimfieldname,vbasetablename,vdatakeyword,vexpress,vtablepkname,vvariablename) values('0001Z8100000000ADDR5','1002Z81000000000LGE7',0,1,'0001',null,null,null,null,'2015-07-06 14:01:05',null,null,null,'so_saleorder_b.cpriceformid.priceformstr->getColValue(prm_priceform, priceformstr,pk_priceform,so_saleorder_b.cpriceformid); so_saleorder_b.cpriceformid.priceformstr->iif(isempty(so_saleorder_b.cpriceformid.priceformstr),getColValue(prm_priceform_p, priceformstr,pk_priceform,so_saleorder_b.cpriceformid),so_saleorder_b.cpriceformid.priceformstr)',null,'价格组成字符串')
go

insert into pub_print_variable(cvariableid,ctemplateid,dr,iformulatype,pk_corp,prepare1,prepare2,prepare3,prepare4,ts,vaimfieldname,vbasetablename,vdatakeyword,vexpress,vtablepkname,vvariablename) values('0001Z8100000000ADDR6','1002Z81000000000LGE7',0,1,'0001',null,null,null,null,'2015-07-06 14:01:05',null,null,null,'setThMark(so_saleorder_b.nastnum)',null,'千分位[数量]')
go

insert into pub_print_variable(cvariableid,ctemplateid,dr,iformulatype,pk_corp,prepare1,prepare2,prepare3,prepare4,ts,vaimfieldname,vbasetablename,vdatakeyword,vexpress,vtablepkname,vvariablename) values('0001Z8100000000ADDR7','1002Z81000000000LGE7',0,1,'0001',null,null,null,null,'2015-07-06 14:01:05',null,null,null,'setThMark(ntotalorigmny)',null,'千分位[价税合计]')
go

insert into pub_print_variable(cvariableid,ctemplateid,dr,iformulatype,pk_corp,prepare1,prepare2,prepare3,prepare4,ts,vaimfieldname,vbasetablename,vdatakeyword,vexpress,vtablepkname,vvariablename) values('0001Z8100000000ADDR8','1002Z81000000000LGE7',0,1,'0001',null,null,null,null,'2015-07-06 14:01:05',null,null,null,'setThMark(ndiscountrate)',null,'千分位[整单折扣]')
go

insert into pub_print_variable(cvariableid,ctemplateid,dr,iformulatype,pk_corp,prepare1,prepare2,prepare3,prepare4,ts,vaimfieldname,vbasetablename,vdatakeyword,vexpress,vtablepkname,vvariablename) values('0001Z8100000000ADDR9','1002Z81000000000LGE7',0,1,'0001',null,null,null,null,'2015-07-06 14:01:05',null,null,null,'formataddress(so_saleorder_b.creceiveaddrid )',null,'收货地址')
go

insert into pub_print_variable(cvariableid,ctemplateid,dr,iformulatype,pk_corp,prepare1,prepare2,prepare3,prepare4,ts,vaimfieldname,vbasetablename,vdatakeyword,vexpress,vtablepkname,vvariablename) values('0001Z8100000000ADDRA','1002Z81000000000LGE7',0,1,'0001',null,null,null,null,'2015-07-06 14:01:05',null,null,null,'setThMark(so_saleorder_b)',null,'千分位[销售订单附表]')
go

insert into pub_print_variable(cvariableid,ctemplateid,dr,iformulatype,pk_corp,prepare1,prepare2,prepare3,prepare4,ts,vaimfieldname,vbasetablename,vdatakeyword,vexpress,vtablepkname,vvariablename) values('0001Z8100000000ADDRB','1002Z81000000000LGE7',0,1,'0001',null,null,null,null,'2015-07-06 14:01:05',null,null,null,'setThMark(so_saleorder_b.nqtorigtaxprice)',null,'千分位[含税单价]')
go

insert into pub_print_variable(cvariableid,ctemplateid,dr,iformulatype,pk_corp,prepare1,prepare2,prepare3,prepare4,ts,vaimfieldname,vbasetablename,vdatakeyword,vexpress,vtablepkname,vvariablename) values('0001Z8100000000ADDRC','1002Z81000000000LGE7',0,1,'0001',null,null,null,null,'2015-07-06 14:01:05',null,null,null,'setThMark(so_saleorder_b.norigdiscount)',null,'千分位[折扣额]')
go

insert into pub_print_variable(cvariableid,ctemplateid,dr,iformulatype,pk_corp,prepare1,prepare2,prepare3,prepare4,ts,vaimfieldname,vbasetablename,vdatakeyword,vexpress,vtablepkname,vvariablename) values('0001Z8100000000ADDRD','1002Z81000000000LGE7',0,1,'0001',null,null,null,null,'2015-07-06 14:01:05',null,null,null,'setThMark(so_saleorder_b.norigmny)',null,'千分位[无税金额]')
go

insert into pub_print_variable(cvariableid,ctemplateid,dr,iformulatype,pk_corp,prepare1,prepare2,prepare3,prepare4,ts,vaimfieldname,vbasetablename,vdatakeyword,vexpress,vtablepkname,vvariablename) values('0001Z8100000000ADDRE','1002Z81000000000LGE7',0,1,'0001',null,null,null,null,'2015-07-06 14:01:05',null,null,null,'setThMark(so_saleorder_b)',null,'千分位[销售订单附表]')
go

insert into pub_print_variable(cvariableid,ctemplateid,dr,iformulatype,pk_corp,prepare1,prepare2,prepare3,prepare4,ts,vaimfieldname,vbasetablename,vdatakeyword,vexpress,vtablepkname,vvariablename) values('0001Z8100000000ADDRF','1002Z81000000000LGE7',0,1,'0001',null,null,null,null,'2015-07-06 14:01:05',null,null,null,'setThMark(so_saleorder_b.nnum)',null,'千分位[主数量]')
go

insert into pub_print_variable(cvariableid,ctemplateid,dr,iformulatype,pk_corp,prepare1,prepare2,prepare3,prepare4,ts,vaimfieldname,vbasetablename,vdatakeyword,vexpress,vtablepkname,vvariablename) values('0001Z8100000000ADDRG','1002Z81000000000LGE7',0,1,'0001',null,null,null,null,'2015-07-06 14:01:05',null,null,null,'setThMark(so_saleorder_b.ntaxrate)',null,'千分位[税率]')
go

insert into pub_print_variable(cvariableid,ctemplateid,dr,iformulatype,pk_corp,prepare1,prepare2,prepare3,prepare4,ts,vaimfieldname,vbasetablename,vdatakeyword,vexpress,vtablepkname,vvariablename) values('0001Z8100000000ADDRH','1002Z81000000000LGE7',0,1,'0001',null,null,null,null,'2015-07-06 14:01:05',null,null,null,'setThMark(so_saleorder_b.nqtorigprice)',null,'千分位[无税单价]')
go

insert into pub_print_variable(cvariableid,ctemplateid,dr,iformulatype,pk_corp,prepare1,prepare2,prepare3,prepare4,ts,vaimfieldname,vbasetablename,vdatakeyword,vexpress,vtablepkname,vvariablename) values('0001Z8100000000ADDRI','1002Z81000000000LGE7',0,1,'0001',null,null,null,null,'2015-07-06 14:01:05',null,null,null,'setThMark(npreceiverate)',null,'千分位[订单收款比例]')
go

insert into pub_print_variable(cvariableid,ctemplateid,dr,iformulatype,pk_corp,prepare1,prepare2,prepare3,prepare4,ts,vaimfieldname,vbasetablename,vdatakeyword,vexpress,vtablepkname,vvariablename) values('0001Z8100000000ADDRJ','1002Z81000000000LGE7',0,1,'0001',null,null,null,null,'2015-07-06 14:01:05',null,null,null,'setThMark(npreceivemny)',null,'千分位[实际预收款金额]')
go

