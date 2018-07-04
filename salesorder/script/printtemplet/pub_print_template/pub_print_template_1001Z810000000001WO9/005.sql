insert into pub_print_variable(cvariableid,ctemplateid,dr,iformulatype,pk_corp,prepare1,prepare2,prepare3,prepare4,ts,vaimfieldname,vbasetablename,vdatakeyword,vexpress,vtablepkname,vvariablename) values('0001Z8100000000ADCFO','1001Z810000000001WO9',0,1,'0001',null,null,null,null,'2015-07-06 13:39:26',null,null,null,'so_saleorder_b.cpriceformid.priceformstr->getColValue(prm_priceform, priceformstr,pk_priceform,so_saleorder_b.cpriceformid); so_saleorder_b.cpriceformid.priceformstr->iif(isempty(so_saleorder_b.cpriceformid.priceformstr),getColValue(prm_priceform_p, priceformstr,pk_priceform,so_saleorder_b.cpriceformid),so_saleorder_b.cpriceformid.priceformstr)',null,'价格组成字符串')
go

insert into pub_print_variable(cvariableid,ctemplateid,dr,iformulatype,pk_corp,prepare1,prepare2,prepare3,prepare4,ts,vaimfieldname,vbasetablename,vdatakeyword,vexpress,vtablepkname,vvariablename) values('0001Z8100000000ADCFP','1001Z810000000001WO9',0,1,'0001',null,null,null,null,'2015-07-06 13:39:26',null,null,null,'formataddress(so_saleorder_b.creceiveaddrid )',null,'收货地址')
go

