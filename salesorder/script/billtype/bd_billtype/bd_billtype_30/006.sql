insert into pub_function(pk_function,arguments,classname,code,dr,functionnote,functionnote_resid,hintmessage,iscomp,methodname,pk_billtype,pk_billtypeid,returntype,ts,functype) values('1001Z810000000000NNQ','nc.vo.pub.AggregatedValueObject:01','nc.pf.so.function.SaleOrderFunction','SO30-010',0,'客户物料关系业务规则检查','4006function-017',null,null,'checkCustMatRel','30','30','BOOLEAN','2014-12-01 22:35:04',0)
go

insert into pub_function(pk_function,arguments,classname,code,dr,functionnote,functionnote_resid,hintmessage,iscomp,methodname,pk_billtype,pk_billtypeid,returntype,ts,functype) values('1001Z810000000000NNR','nc.vo.pub.AggregatedValueObject:01','nc.pf.so.function.SaleOrderFunction','SO30-011',0,'部门物料关系业务规则检查','4006function-018',null,null,'checkDeptMatRel','30','30','BOOLEAN','2014-12-01 22:35:04',0)
go

insert into pub_function(pk_function,arguments,classname,code,dr,functionnote,functionnote_resid,hintmessage,iscomp,methodname,pk_billtype,pk_billtypeid,returntype,ts,functype) values('1001Z810000000000NNS','nc.vo.pub.AggregatedValueObject:01','nc.pf.so.function.SaleOrderFunction','SO30-012',0,'订单类型物料关系业务规则检查','4006function-019',null,null,'checkTranMatRel','30','30','BOOLEAN','2014-12-01 22:35:04',0)
go

insert into pub_function(pk_function,arguments,classname,code,dr,functionnote,functionnote_resid,hintmessage,iscomp,methodname,pk_billtype,pk_billtypeid,returntype,ts,functype) values('1001Z810000000000NNT','nc.vo.pub.AggregatedValueObject:01','nc.pf.so.function.SaleOrderFunction','SO30-013',0,'退货政策检查','4006function-020',null,null,'judge','30','30','BOOLEAN','2014-12-01 22:35:04',0)
go

insert into pub_function(pk_function,arguments,classname,code,dr,functionnote,functionnote_resid,hintmessage,iscomp,methodname,pk_billtype,pk_billtypeid,returntype,ts,functype) values('1001Z81000000000300Z','nc.vo.pub.AggregatedValueObject:01','nc.pf.credit.function.CreditForM30Func','SO30-018',0,'订单超账期天数','4006function-025',null,null,'examOverPeriodDay4Order','30','30','INTEGER','2014-12-01 22:35:04',0)
go

insert into pub_function(pk_function,arguments,classname,code,dr,functionnote,functionnote_resid,hintmessage,iscomp,methodname,pk_billtype,pk_billtypeid,returntype,ts,functype) values('1001Z810000000003013','nc.vo.pub.AggregatedValueObject:01','nc.pf.credit.function.CreditForM30Func','SO30-019',0,'订单超内控账期天数','4006function-026',null,null,'examInnerDay4Order','30','30','INTEGER','2014-12-01 22:35:04',0)
go

insert into pub_function(pk_function,arguments,classname,code,dr,functionnote,functionnote_resid,hintmessage,iscomp,methodname,pk_billtype,pk_billtypeid,returntype,ts,functype) values('1001Z81000000000301Z','nc.vo.pub.AggregatedValueObject:01','nc.pf.credit.function.CreditForM30Func','SO30-033',0,'订单超账期天数(审批流使用)','4006function-056',null,null,'examOverPeriodDay4OrderApprove','30','30','INTEGER','2014-12-08 11:09:54',0)
go

insert into pub_function(pk_function,arguments,classname,code,dr,functionnote,functionnote_resid,hintmessage,iscomp,methodname,pk_billtype,pk_billtypeid,returntype,ts,functype) values('1001Z810000000005T3U','nc.vo.pub.AggregatedValueObject:01','nc.pf.so.function.SaleOrderFunction','SO30-026',0,'销售组织与客户关系检查','4006function-033',null,null,'checkOrgAndCust','30','30','BOOLEAN','2014-12-01 22:35:04',0)
go

insert into pub_function(pk_function,arguments,classname,code,dr,functionnote,functionnote_resid,hintmessage,iscomp,methodname,pk_billtype,pk_billtypeid,returntype,ts,functype) values('1001Z81000000000CTF6','nc.vo.pub.AggregatedValueObject:01','nc.pf.credit.function.CreditForM30Func','SO30-032',0,'订单超信用金额(审批流使用)','4006function-054',null,null,'examOverCreditForOrderApprove','30','30','DOUBLE','2014-1-03 11:28:54',0)
go

insert into pub_function(pk_function,arguments,classname,code,dr,functionnote,functionnote_resid,hintmessage,iscomp,methodname,pk_billtype,pk_billtypeid,returntype,ts,functype) values('1001Z81000000002RKD5','nc.vo.pub.AggregatedValueObject:01','nc.pf.so.function.SaleOrderFunction','SO30-025',0,'销售订单表头合计金额（原币含税）','4006function-032',null,null,'getHeadSumMny','30','30','DOUBLE','2014-12-01 22:35:04',0)
go

insert into pub_function(pk_function,arguments,classname,code,dr,functionnote,functionnote_resid,hintmessage,iscomp,methodname,pk_billtype,pk_billtypeid,returntype,ts,functype) values('1001Z81000000002RKD9','nc.vo.pub.AggregatedValueObject:01','nc.pf.so.function.SaleOrderFunction','SO30-001',0,'订单表体行的净价/询到净价的最大值','4006function-005',null,null,'getMaxPriceRate','30','30','DOUBLE','2014-12-01 22:35:04',0)
go

insert into pub_function(pk_function,arguments,classname,code,dr,functionnote,functionnote_resid,hintmessage,iscomp,methodname,pk_billtype,pk_billtypeid,returntype,ts,functype) values('1001Z81000000002RKDD','nc.vo.pub.AggregatedValueObject:01','nc.pf.so.function.SaleOrderFunction','SO30-002',0,'订单表体行的净价/询到净价的最小值','4006function-006',null,null,'getMinPriceRate','30','30','DOUBLE','2014-12-01 22:35:04',0)
go

insert into pub_function(pk_function,arguments,classname,code,dr,functionnote,functionnote_resid,hintmessage,iscomp,methodname,pk_billtype,pk_billtypeid,returntype,ts,functype) values('1001Z81000000002RKDH','nc.vo.pub.AggregatedValueObject:01','nc.pf.so.function.SaleOrderFunction','SO30-003',0,'订单表体行未出库合计主数量','4006function-010',null,null,'getNotNtotaloutnum','30','30','DOUBLE','2014-12-01 22:35:04',0)
go

insert into pub_function(pk_function,arguments,classname,code,dr,functionnote,functionnote_resid,hintmessage,iscomp,methodname,pk_billtype,pk_billtypeid,returntype,ts,functype) values('1001Z81000000002RKDL','nc.vo.pub.AggregatedValueObject:01','nc.pf.so.function.SaleOrderFunction','SO30-004',0,'订单表体行未发货合计主数量','4006function-011',null,null,'getNotNtotalsendnum','30','30','DOUBLE','2014-12-01 22:35:04',0)
go

insert into pub_function(pk_function,arguments,classname,code,dr,functionnote,functionnote_resid,hintmessage,iscomp,methodname,pk_billtype,pk_billtypeid,returntype,ts,functype) values('1001Z81000000002RKDP','nc.vo.pub.AggregatedValueObject:01','nc.pf.so.function.SaleOrderFunction','SO30-005',0,'订单现收款金额','4006function-012',null,null,'getOrigBalMny','30','30','DOUBLE','2014-12-01 22:35:04',0)
go

insert into pub_function(pk_function,arguments,classname,code,dr,functionnote,functionnote_resid,hintmessage,iscomp,methodname,pk_billtype,pk_billtypeid,returntype,ts,functype) values('1001Z81000000002RKDT','nc.vo.pub.AggregatedValueObject:01','nc.pf.so.function.SaleOrderFunction','SO30-006',0,'订单现收款金额不能小于订单收款限额','4006function-013',null,null,'examOrigBalMnyNotLessThanPreceiveMny','30','30','BOOLEAN','2014-12-01 22:35:04',0)
go

insert into pub_function(pk_function,arguments,classname,code,dr,functionnote,functionnote_resid,hintmessage,iscomp,methodname,pk_billtype,pk_billtypeid,returntype,ts,functype) values('1001Z81000000002RKDX','nc.vo.pub.AggregatedValueObject:01','nc.pf.so.function.SaleOrderFunction','SO30-007',0,'销售净价不能小于物料档案的最低售价*客户档案的物料最低售价比例','4006function-014',null,null,'examSaleNetprice','30','30','BOOLEAN','2014-12-01 22:35:04',0)
go

insert into pub_function(pk_function,arguments,classname,code,dr,functionnote,functionnote_resid,hintmessage,iscomp,methodname,pk_billtype,pk_billtypeid,returntype,ts,functype) values('1001Z81000000002RKE1','nc.vo.pub.AggregatedValueObject:01','nc.pf.so.function.SaleOrderFunction','SO30-008',0,'操作员对应业务员与客户专管业务员是否为同一人','4006function-015',null,null,'examRespPerson','30','30','BOOLEAN','2014-12-01 22:35:04',0)
go

insert into pub_function(pk_function,arguments,classname,code,dr,functionnote,functionnote_resid,hintmessage,iscomp,methodname,pk_billtype,pk_billtypeid,returntype,ts,functype) values('1001Z81000000002RKE6','nc.vo.pub.AggregatedValueObject:01','nc.pf.so.function.SaleOrderFunction','SO30-009',0,'表体行整单折扣*单品折扣的最大值','4006function-016',null,null,'getMaxDiscountRate','30','30','DOUBLE','2014-12-01 22:35:04',0)
go

insert into pub_function(pk_function,arguments,classname,code,dr,functionnote,functionnote_resid,hintmessage,iscomp,methodname,pk_billtype,pk_billtypeid,returntype,ts,functype) values('1001Z81000000002RKE7','nc.vo.pub.AggregatedValueObject:01','nc.pf.so.function.SaleOrderFunction','SO30-014',0,'表体行整单折扣*单品折扣的最小值','4006function-021',null,null,'getMinDiscountRate','30','30','DOUBLE','2014-12-01 22:35:04',0)
go

insert into pub_function(pk_function,arguments,classname,code,dr,functionnote,functionnote_resid,hintmessage,iscomp,methodname,pk_billtype,pk_billtypeid,returntype,ts,functype) values('1001Z81000000002RKEC','nc.vo.pub.AggregatedValueObject:01','nc.pf.so.function.SaleOrderFunction','SO30-015',0,'订单表体行发货日期与订单日期的最大差值','4006function-022',null,null,'getMaxDate','30','30','INTEGER','2014-12-01 22:35:04',0)
go

insert into pub_function(pk_function,arguments,classname,code,dr,functionnote,functionnote_resid,hintmessage,iscomp,methodname,pk_billtype,pk_billtypeid,returntype,ts,functype) values('1001Z81000000002RKED','nc.vo.pub.AggregatedValueObject:01','nc.pf.so.function.SaleOrderFunction','SO30-016',0,'订单表体行发货日期与订单日期的最小差值','4006function-023',null,null,'getMinDate','30','30','INTEGER','2014-12-01 22:35:04',0)
go

insert into pub_function(pk_function,arguments,classname,code,dr,functionnote,functionnote_resid,hintmessage,iscomp,methodname,pk_billtype,pk_billtypeid,returntype,ts,functype) values('1001Z81000000002RKEH','nc.vo.pub.AggregatedValueObject:01','nc.pf.so.function.SaleOrderFunction','SO30-017',0,'退货单价不能大于正向销售的单价','4006function-024',null,null,'examReturnPriceOverOrder','30','30','BOOLEAN','2014-12-01 22:35:04',0)
go

insert into pub_function(pk_function,arguments,classname,code,dr,functionnote,functionnote_resid,hintmessage,iscomp,methodname,pk_billtype,pk_billtypeid,returntype,ts,functype) values('1002Z8100000000067RL','nc.vo.pub.AggregatedValueObject:01','nc.pf.credit.function.CreditForM30Func','SO30-023',0,'订单超信用金额','4006function-030',null,null,'examOverCreditForOrder','30','30','DOUBLE','2014-12-01 22:35:04',0)
go

insert into pub_function(pk_function,arguments,classname,code,dr,functionnote,functionnote_resid,hintmessage,iscomp,methodname,pk_billtype,pk_billtypeid,returntype,ts,functype) values('1002Z810000000008T4Y','nc.vo.pub.AggregatedValueObject:01','nc.pf.so.function.split.M30For4CSplitFunc','SO30-031',0,'销售订单到出库单按照库管员分单函数','4006function-038',null,'N','splitByStoreAdmin','30','30','ARRAYLIST','2014-12-01 22:35:04',1)
go

insert into pub_function(pk_function,arguments,classname,code,dr,functionnote,functionnote_resid,hintmessage,iscomp,methodname,pk_billtype,pk_billtypeid,returntype,ts,functype) values('1002Z81000000000C5I1','nc.vo.pub.AggregatedValueObject:01','nc.pf.so.function.split.M30For21SplitFunc','SO30-028',0,'销售订单到采购订单按照供应商分单函数','4006function-035',null,null,'splitBSupplier','30','30','ARRAYLIST','2014-12-01 22:35:04',1)
go

insert into pub_function(pk_function,arguments,classname,code,dr,functionnote,functionnote_resid,hintmessage,iscomp,methodname,pk_billtype,pk_billtypeid,returntype,ts,functype) values('1002Z81000000000C5I2','nc.vo.pub.AggregatedValueObject:01','nc.pf.so.function.split.M30For21SplitFunc','SO30-029',0,'销售订单到采购订单按照币种分单函数','4006function-036',null,null,'splitBCcurrencyid','30','30','ARRAYLIST','2014-12-01 22:35:04',1)
go

insert into pub_function(pk_function,arguments,classname,code,dr,functionnote,functionnote_resid,hintmessage,iscomp,methodname,pk_billtype,pk_billtypeid,returntype,ts,functype) values('1002Z81000000000COSH','nc.vo.pub.AggregatedValueObject:01','nc.pf.credit.function.CreditForM30Func','SO30-022',0,'订单超账期金额','4006function-029',null,null,'examOverPeriodMoney4Order','30','30','DOUBLE','2014-12-01 22:35:04',0)
go

insert into pub_function(pk_function,arguments,classname,code,dr,functionnote,functionnote_resid,hintmessage,iscomp,methodname,pk_billtype,pk_billtypeid,returntype,ts,functype) values('1002Z81000000000CZLD','nc.vo.pub.AggregatedValueObject:01','nc.pf.so.function.split.M30For21SplitFunc','SO30-030',0,'销售订单到采购订单按照订单类型分单函数','4006function-037',null,null,'splitHorgByOrderTrans','30','30','ARRAYLIST','2014-12-01 22:35:04',1)
go

insert into pub_function(pk_function,arguments,classname,code,dr,functionnote,functionnote_resid,hintmessage,iscomp,methodname,pk_billtype,pk_billtypeid,returntype,ts,functype) values('1002Z81credit0000002','nc.vo.pub.AggregatedValueObject:01','nc.pf.credit.function.CreditForM30Func','SO30-024',0,'订单超信用金额%','4006function-031',null,null,'examOverCreditRateForOrder','30','30','DOUBLE','2014-12-01 22:35:04',0)
go

insert into pub_function(pk_function,arguments,classname,code,dr,functionnote,functionnote_resid,hintmessage,iscomp,methodname,pk_billtype,pk_billtypeid,returntype,ts,functype) values('1002Z81credit0303002','nc.vo.pub.AggregatedValueObject:01','nc.pf.so.function.SaleOrderFunction','SO30-040',0,'主记账单价不能小于物料档案的最低售价*客户档案的物料最低售价比例','4006function-199',null,null,'checkAccountPrice','30','30','BOOLEAN','2015-03-18 10:34:23',0)
go

insert into pub_function(pk_function,arguments,classname,code,dr,functionnote,functionnote_resid,hintmessage,iscomp,methodname,pk_billtype,pk_billtypeid,returntype,ts,functype) values('1003Z810000000000V0D','nc.vo.pub.AggregatedValueObject:01','nc.pf.so.function.split.M30For20SplitFunc','SO30-020',0,'销售订单到请购单按照计划岗分单函数','4006function-027',null,null,'splitByPlanPosition','30','30','ARRAYLIST','2014-12-01 22:35:04',1)
go

insert into pub_function(pk_function,arguments,classname,code,dr,functionnote,functionnote_resid,hintmessage,iscomp,methodname,pk_billtype,pk_billtypeid,returntype,ts,functype) values('1003Z810000000000V0H','nc.vo.pub.AggregatedValueObject:01','nc.pf.so.function.split.M30For20SplitFunc','SO30-021',0,'销售订单到采购订单按照采购岗分单函数','4006function-028',null,null,'splitByPurchasePosition','30','30','ARRAYLIST','2014-12-01 22:35:04',1)
go

insert into pub_function(pk_function,arguments,classname,code,dr,functionnote,functionnote_resid,hintmessage,iscomp,methodname,pk_billtype,pk_billtypeid,returntype,ts,functype) values('1003Z810000000000VWD','nc.vo.pub.AggregatedValueObject:01','nc.pf.so.function.split.M30For20SplitFunc','SO30-027',0,'销售订单到请购单按照物料类型是否委外分单','4006function-034',null,null,'splitByMaterialType','30','30','ARRAYLIST','2014-12-01 22:35:04',1)
go

