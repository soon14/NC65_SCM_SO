insert into pub_print_variable(cvariableid,ctemplateid,dr,iformulatype,pk_corp,prepare1,prepare2,prepare3,prepare4,ts,vaimfieldname,vbasetablename,vdatakeyword,vexpress,vtablepkname,vvariablename) values('1001Z81000000000AZNT','0001Z81000000001S6QE',0,1,'0001',null,null,null,null,'2015-07-04 16:23:23',null,null,null,'add( _total_(so_saleorder_b.norigtaxmny),_total_(so_saleorder_b.ntax))',null,'合计[价税合计]')
go

insert into pub_print_variable(cvariableid,ctemplateid,dr,iformulatype,pk_corp,prepare1,prepare2,prepare3,prepare4,ts,vaimfieldname,vbasetablename,vdatakeyword,vexpress,vtablepkname,vvariablename) values('1001Z81000000000AZNU','0001Z81000000001S6QE',0,1,'0001',null,null,null,null,'2015-07-04 16:23:23',null,null,null,'_total_(so_saleorder_b.norigmny)',null,'合计[无税金额]')
go

insert into pub_print_variable(cvariableid,ctemplateid,dr,iformulatype,pk_corp,prepare1,prepare2,prepare3,prepare4,ts,vaimfieldname,vbasetablename,vdatakeyword,vexpress,vtablepkname,vvariablename) values('1001Z81000000000AZNV','0001Z81000000001S6QE',0,1,'0001',null,null,null,null,'2015-07-04 16:23:23',null,null,null,'_total_(so_saleorder_b.ntax)',null,'合计[税额]')
go

insert into pub_print_variable(cvariableid,ctemplateid,dr,iformulatype,pk_corp,prepare1,prepare2,prepare3,prepare4,ts,vaimfieldname,vbasetablename,vdatakeyword,vexpress,vtablepkname,vvariablename) values('1001Z81000000000AZNW','0001Z81000000001S6QE',0,1,'0001',null,null,null,null,'2015-07-04 16:23:23',null,null,null,'_total_(so_saleorder_b.nmny)',null,'合计[本币无税金额]')
go

insert into pub_print_variable(cvariableid,ctemplateid,dr,iformulatype,pk_corp,prepare1,prepare2,prepare3,prepare4,ts,vaimfieldname,vbasetablename,vdatakeyword,vexpress,vtablepkname,vvariablename) values('1001Z81000000000AZNX','0001Z81000000001S6QE',0,1,'0001',null,null,null,null,'2015-07-04 16:23:23',null,null,null,'_total_(so_saleorder_b.ntaxmny)',null,'合计[本币价税合计]')
go

insert into pub_print_variable(cvariableid,ctemplateid,dr,iformulatype,pk_corp,prepare1,prepare2,prepare3,prepare4,ts,vaimfieldname,vbasetablename,vdatakeyword,vexpress,vtablepkname,vvariablename) values('1001Z81000000000AZNY','0001Z81000000001S6QE',0,1,'0001',null,null,null,null,'2015-07-04 16:23:23',null,null,null,'formataddress(getcolvalue( bd_custaddress, pk_address, pk_custaddress, _pop_(so_saleorder_b.creceiveaddrid)))',null,'收货地址-第一行')
go

insert into pub_print_variable(cvariableid,ctemplateid,dr,iformulatype,pk_corp,prepare1,prepare2,prepare3,prepare4,ts,vaimfieldname,vbasetablename,vdatakeyword,vexpress,vtablepkname,vvariablename) values('1001Z81000000000AZNZ','0001Z81000000001S6QE',0,1,'0001',null,null,null,null,'2015-07-04 16:23:23',null,null,null,'formataddress(ccustomerid.corpaddress)',null,'企业地址')
go

