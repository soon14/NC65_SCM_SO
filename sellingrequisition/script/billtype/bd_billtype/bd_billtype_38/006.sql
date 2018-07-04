insert into pub_function(pk_function,arguments,classname,code,dr,functionnote,functionnote_resid,hintmessage,iscomp,methodname,pk_billtype,pk_billtypeid,returntype,ts,functype) values('1001Z81000000002RKCL','nc.vo.pub.AggregatedValueObject:01','nc.pf.so.function.PreOrderFunction','SO38-005',null,'订单表体行的净价/询到净价的最大值','4006function-005',null,null,'getMaxPriceRate','38','38','DOUBLE','2012-06-25 15:19:51',null)
go

insert into pub_function(pk_function,arguments,classname,code,dr,functionnote,functionnote_resid,hintmessage,iscomp,methodname,pk_billtype,pk_billtypeid,returntype,ts,functype) values('1001Z81000000002RKCP','nc.vo.pub.AggregatedValueObject:01','nc.pf.so.function.PreOrderFunction','SO38-006',null,'订单表体行的净价/询到净价的最小值','4006function-006',null,null,'getMinPriceRate','38','38','DOUBLE','2012-06-25 15:19:51',null)
go

insert into pub_function(pk_function,arguments,classname,code,dr,functionnote,functionnote_resid,hintmessage,iscomp,methodname,pk_billtype,pk_billtypeid,returntype,ts,functype) values('1001Z81000000002RKCS','nc.vo.pub.AggregatedValueObject:01','nc.pf.so.function.PreOrderFunction','SO38-003',null,'表体行销售净价不能小于物料档案的物料最低售价*客户档案的物料最低售价比例','4006function-003',null,null,'examSaleNetprice','38','38','BOOLEAN','2012-06-25 15:19:51',null)
go

insert into pub_function(pk_function,arguments,classname,code,dr,functionnote,functionnote_resid,hintmessage,iscomp,methodname,pk_billtype,pk_billtypeid,returntype,ts,functype) values('1001Z81000000002RKCW','nc.vo.pub.AggregatedValueObject:01','nc.pf.so.function.PreOrderFunction','SO38-004',null,'表体行整单折扣*单品折扣的最大值','4006function-004',null,null,'getMaxDiscountRate','38','38','DOUBLE','2012-06-25 15:19:51',null)
go

insert into pub_function(pk_function,arguments,classname,code,dr,functionnote,functionnote_resid,hintmessage,iscomp,methodname,pk_billtype,pk_billtypeid,returntype,ts,functype) values('1001Z81000000002RKD0','nc.vo.pub.AggregatedValueObject:01','nc.pf.so.function.PreOrderFunction','SO38-007',null,'表体行整单折扣*单品折扣的最小值','4006function-007',null,null,'getMinDiscountRate','38','38','DOUBLE','2012-06-25 15:19:51',null)
go

insert into pub_function(pk_function,arguments,classname,code,dr,functionnote,functionnote_resid,hintmessage,iscomp,methodname,pk_billtype,pk_billtypeid,returntype,ts,functype) values('1002Z8100000000016FT','nc.vo.pub.AggregatedValueObject:01','nc.pf.so.function.M38For30SplitFunc','SO38-008',null,'预订单到销售订单按库存组织分单','4006function-008',null,null,'splitBySendStockOrg','38','38','ARRAYLIST','2012-06-25 15:19:51',null)
go

insert into pub_function(pk_function,arguments,classname,code,dr,functionnote,functionnote_resid,hintmessage,iscomp,methodname,pk_billtype,pk_billtypeid,returntype,ts,functype) values('1002Z8100000000016FU','nc.vo.pub.AggregatedValueObject:01','nc.pf.so.function.M38For30SplitFunc','SO38-009',null,'预订单到销售订单按物流组织分单','4006function-009',null,null,'splitByTrafficOrg','38','38','ARRAYLIST','2012-06-25 15:19:51',null)
go

insert into pub_function(pk_function,arguments,classname,code,dr,functionnote,functionnote_resid,hintmessage,iscomp,methodname,pk_billtype,pk_billtypeid,returntype,ts,functype) values('1002Z8100000000016FV','nc.vo.pub.AggregatedValueObject:01','nc.pf.so.function.M38For30SplitFunc','SO38-001',null,'预订单到销售订单按结算组织分单','4006function-001',null,null,'splitBySettleOrg','38','38','ARRAYLIST','2012-06-25 15:19:51',null)
go

insert into pub_function(pk_function,arguments,classname,code,dr,functionnote,functionnote_resid,hintmessage,iscomp,methodname,pk_billtype,pk_billtypeid,returntype,ts,functype) values('1002Z8100000000016FW','nc.vo.pub.AggregatedValueObject:01','nc.pf.so.function.M38For30SplitFunc','SO38-002',null,'预订单到销售订单按应收组织分单','4006function-002',null,null,'splitByArOrg','38','38','ARRAYLIST','2012-06-25 15:19:51',null)
go

