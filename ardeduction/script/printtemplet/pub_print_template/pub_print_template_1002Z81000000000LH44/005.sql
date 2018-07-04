insert into pub_print_variable(cvariableid,ctemplateid,dr,iformulatype,pk_corp,prepare1,prepare2,prepare3,prepare4,ts,vaimfieldname,vbasetablename,vdatakeyword,vexpress,vtablepkname,vvariablename) values('1001Z81000000000BDY1','1002Z81000000000LH44',0,1,'0001',null,null,null,null,'2015-07-09 15:10:35',null,null,null,'setThMark(carsubbid.nordersubmny)',null,'千分位[订单冲抵金额]')
go

insert into pub_print_variable(cvariableid,ctemplateid,dr,iformulatype,pk_corp,prepare1,prepare2,prepare3,prepare4,ts,vaimfieldname,vbasetablename,vdatakeyword,vexpress,vtablepkname,vvariablename) values('1001Z81000000000BDY2','1002Z81000000000LH44',0,1,'0001',null,null,null,null,'2015-07-09 15:10:35',null,null,null,'setThMark(carsubbid.nredarsubmny)',null,'千分位[红字应收金额]')
go

insert into pub_print_variable(cvariableid,ctemplateid,dr,iformulatype,pk_corp,prepare1,prepare2,prepare3,prepare4,ts,vaimfieldname,vbasetablename,vdatakeyword,vexpress,vtablepkname,vvariablename) values('1001Z81000000000BDY3','1002Z81000000000LH44',0,1,'0001',null,null,null,null,'2015-07-09 15:10:35',null,null,null,'carsubbid.nremainmny->sub(sub(sub(sub(sub(iif( isempty(carsubbid.norigarsubmny),0 ,tonumber(carsubbid.norigarsubmny)), iif(  isempty(carsubbid.nordersubmny),0 ,tonumber(carsubbid.nordersubmny)) ),iif( isempty(carsubbid.ninvoicesubmny),0 ,tonumber (carsubbid.ninvoicesubmny))),iif( isempty(carsubbid.nredarsubmny),0 ,tonumber(carsubbid.nredarsubmny)) ),iif( isempty (carsubbid.nlrgcashmny),0 ,tonumber(carsubbid.nlrgcashmny))),iif( isempty(carsubbid.ngatheringmny),0 ,tonumber (carsubbid.ngatheringmny)));',null,'千分位[余额]')
go

insert into pub_print_variable(cvariableid,ctemplateid,dr,iformulatype,pk_corp,prepare1,prepare2,prepare3,prepare4,ts,vaimfieldname,vbasetablename,vdatakeyword,vexpress,vtablepkname,vvariablename) values('1001Z81000000000BDY4','1002Z81000000000LH44',0,1,'0001',null,null,null,null,'2015-07-09 15:10:35',null,null,null,'setThMark(carsubbid.norigarsubmny)',null,'千分位[金额]')
go

insert into pub_print_variable(cvariableid,ctemplateid,dr,iformulatype,pk_corp,prepare1,prepare2,prepare3,prepare4,ts,vaimfieldname,vbasetablename,vdatakeyword,vexpress,vtablepkname,vvariablename) values('1001Z81000000000BDY5','1002Z81000000000LH44',0,1,'0001',null,null,null,null,'2015-07-09 15:10:35',null,null,null,'setThMark(carsubbid.ninvoicesubmny)',null,'千分位[发票冲抵金额]')
go

insert into pub_print_variable(cvariableid,ctemplateid,dr,iformulatype,pk_corp,prepare1,prepare2,prepare3,prepare4,ts,vaimfieldname,vbasetablename,vdatakeyword,vexpress,vtablepkname,vvariablename) values('1001Z81000000000BDY6','1002Z81000000000LH44',0,1,'0001',null,null,null,null,'2015-07-09 15:10:35',null,null,null,'carsubbid.nremainmny->iif(carsubbid.nordersubmny==null , tonumber(carsubbid.norigarsubmny), sub(tonumber(carsubbid.norigarsubmny),tonumber(carsubbid.nordersubmny)) );  carsubbid.nremainmny->iif(carsubbid.ninvoicesubmny==null , tonumber(carsubbid.nremainmny), sub(tonumber(carsubbid.nremainmny),tonumber(carsubbid.ninvoicesubmny)) ); carsubbid.nremainmny->iif(carsubbid.nredarsubmny==null , tonumber(carsubbid.nremainmny), sub(tonumber(carsubbid.nremainmny),tonumber(carsubbid.nredarsubmny)) ); carsubbid.nremainmny->iif(carsubbid.nlrgcashmny==null , tonumber(carsubbid.nremainmny), sub(tonumber(carsubbid.nremainmny),tonumber(carsubbid.nlrgcashmny)) ); carsubbid.nremainmny->iif(carsubbid.ngatheringmny==null , tonumber(carsubbid.nremainmny), sub(tonumber(carsubbid.nremainmny),tonumber(carsubbid.ngatheringmny)) );',null,'余额')
go

