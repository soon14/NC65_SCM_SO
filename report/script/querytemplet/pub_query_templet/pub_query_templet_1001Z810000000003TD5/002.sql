insert into pub_query_condition(id,consult_code,data_type,disp_sequence,disp_type,disp_value,dr,field_code,field_name,guideline,if_attrrefused,if_autocheck,if_datapower,if_default,if_desc,if_group,if_immobility,if_multicorpref,if_must,if_notmdcondition,if_order,if_subincluded,if_sum,if_sysfuncrefused,if_used,instrumentsql,iscondition,limits,max_length,opera_code,opera_name,order_sequence,pk_corp,pk_templet,prerestrict,resid,return_type,table_code,table_name,ts,userdefflag,value) values('1001Z810000000003TD6','销售组织',5,0,1,null,null,'csaleorgoid',null,null,'N','Y','N','Y','N','N','N','N','Y','N','N','N','N','Y','Y',null,'Y',9999,null,'=','等于@',0,'@@@@','1001Z810000000003TD5',null,'2UC000-000997',2,null,null,'2013-03-26 21:47:28','N','#mainorg#')
go

insert into pub_query_condition(id,consult_code,data_type,disp_sequence,disp_type,disp_value,dr,field_code,field_name,guideline,if_attrrefused,if_autocheck,if_datapower,if_default,if_desc,if_group,if_immobility,if_multicorpref,if_must,if_notmdcondition,if_order,if_subincluded,if_sum,if_sysfuncrefused,if_used,instrumentsql,iscondition,limits,max_length,opera_code,opera_name,order_sequence,pk_corp,pk_templet,prerestrict,resid,return_type,table_code,table_name,ts,userdefflag,value) values('1001Z810000000003TD7','-99',3,1,1,null,null,'cgeneralbid.dbizdate',null,null,'N','Y','N','Y','N','N','N','N','Y','N','N','N','N','Y','Y',null,'Y',9999,null,'between@=@','介于@等于@',0,'@@@@','1001Z810000000003TD5',null,'2UC000-000150',2,null,null,'2013-03-26 21:47:28','N','#month(-1)#,#finalDayOfLastMonth#')
go

insert into pub_query_condition(id,consult_code,data_type,disp_sequence,disp_type,disp_value,dr,field_code,field_name,guideline,if_attrrefused,if_autocheck,if_datapower,if_default,if_desc,if_group,if_immobility,if_multicorpref,if_must,if_notmdcondition,if_order,if_subincluded,if_sum,if_sysfuncrefused,if_used,instrumentsql,iscondition,limits,max_length,opera_code,opera_name,order_sequence,pk_corp,pk_templet,prerestrict,resid,return_type,table_code,table_name,ts,userdefflag,value) values('1001Z810000000003TD8','-99',0,2,1,null,0,'vbillcode',null,null,'N','Y','N','Y','N','N','N','N','N','N','N','N','N','N','Y',null,'Y',9999,null,'=@','等于@',0,'@@@@','1001Z810000000003TD5',null,'2UC000-000186',2,null,null,'2013-03-26 21:47:28','N',null)
go

insert into pub_query_condition(id,consult_code,data_type,disp_sequence,disp_type,disp_value,dr,field_code,field_name,guideline,if_attrrefused,if_autocheck,if_datapower,if_default,if_desc,if_group,if_immobility,if_multicorpref,if_must,if_notmdcondition,if_order,if_subincluded,if_sum,if_sysfuncrefused,if_used,instrumentsql,iscondition,limits,max_length,opera_code,opera_name,order_sequence,pk_corp,pk_templet,prerestrict,resid,return_type,table_code,table_name,ts,userdefflag,value) values('1001Z810000000003TD9','客户档案',5,3,1,null,0,'ccustomerid',null,null,'N','Y','N','Y','N','N','N','N','N','N','N','N','N','N','Y',null,'Y',9999,null,'=','等于@',0,'@@@@','1001Z810000000003TD5',null,'2UC000-000878',2,null,null,'2013-03-26 21:47:28','N',null)
go

insert into pub_query_condition(id,consult_code,data_type,disp_sequence,disp_type,disp_value,dr,field_code,field_name,guideline,if_attrrefused,if_autocheck,if_datapower,if_default,if_desc,if_group,if_immobility,if_multicorpref,if_must,if_notmdcondition,if_order,if_subincluded,if_sum,if_sysfuncrefused,if_used,instrumentsql,iscondition,limits,max_length,opera_code,opera_name,order_sequence,pk_corp,pk_templet,prerestrict,resid,return_type,table_code,table_name,ts,userdefflag,value) values('1001Z810000000003TDA','客户基本分类',5,5,1,null,null,'ccustomerid.pk_custclass',null,null,'N','Y','N','Y','N','N','N','N','N','N','N','N','N','N','Y',null,'Y',9999,null,'=','等于@',0,'@@@@','1001Z810000000003TD5',null,'2custome-000013',2,null,null,'2013-03-26 21:47:28','N',null)
go

insert into pub_query_condition(id,consult_code,data_type,disp_sequence,disp_type,disp_value,dr,field_code,field_name,guideline,if_attrrefused,if_autocheck,if_datapower,if_default,if_desc,if_group,if_immobility,if_multicorpref,if_must,if_notmdcondition,if_order,if_subincluded,if_sum,if_sysfuncrefused,if_used,instrumentsql,iscondition,limits,max_length,opera_code,opera_name,order_sequence,pk_corp,pk_templet,prerestrict,resid,return_type,table_code,table_name,ts,userdefflag,value) values('1001Z810000000003TDC','地区分类',5,6,1,null,0,'ccustomerid.pk_areacl',null,null,'N','Y','N','Y','N','N','N','N','N','N','N','N','N','N','Y',null,'Y',9999,null,'=','等于@',0,'@@@@','1001Z810000000003TD5',null,'2custome-000027',2,null,null,'2013-03-26 21:47:28','N',null)
go

insert into pub_query_condition(id,consult_code,data_type,disp_sequence,disp_type,disp_value,dr,field_code,field_name,guideline,if_attrrefused,if_autocheck,if_datapower,if_default,if_desc,if_group,if_immobility,if_multicorpref,if_must,if_notmdcondition,if_order,if_subincluded,if_sum,if_sysfuncrefused,if_used,instrumentsql,iscondition,limits,max_length,opera_code,opera_name,order_sequence,pk_corp,pk_templet,prerestrict,resid,return_type,table_code,table_name,ts,userdefflag,value) values('1001Z810000000003TDD','部门',5,7,1,null,0,'cdptid',null,null,'N','Y','N','Y','N','N','N','N','N','N','N','N','N','N','Y',null,'Y',9999,null,'=','等于@',0,'@@@@','1001Z810000000003TD5',null,'2UC000-000974',2,null,null,'2013-03-26 21:47:28','N',null)
go

insert into pub_query_condition(id,consult_code,data_type,disp_sequence,disp_type,disp_value,dr,field_code,field_name,guideline,if_attrrefused,if_autocheck,if_datapower,if_default,if_desc,if_group,if_immobility,if_multicorpref,if_must,if_notmdcondition,if_order,if_subincluded,if_sum,if_sysfuncrefused,if_used,instrumentsql,iscondition,limits,max_length,opera_code,opera_name,order_sequence,pk_corp,pk_templet,prerestrict,resid,return_type,table_code,table_name,ts,userdefflag,value) values('1001Z810000000003TDE','人员',5,8,1,null,0,'cbizid',null,null,'N','Y','N','Y','N','N','N','N','N','N','N','N','N','N','Y',null,'Y',9999,null,'=','等于@',0,'@@@@','1001Z810000000003TD5',null,'2UC000-000013',2,null,null,'2013-03-26 21:47:28','N',null)
go

insert into pub_query_condition(id,consult_code,data_type,disp_sequence,disp_type,disp_value,dr,field_code,field_name,guideline,if_attrrefused,if_autocheck,if_datapower,if_default,if_desc,if_group,if_immobility,if_multicorpref,if_must,if_notmdcondition,if_order,if_subincluded,if_sum,if_sysfuncrefused,if_used,instrumentsql,iscondition,limits,max_length,opera_code,opera_name,order_sequence,pk_corp,pk_templet,prerestrict,resid,return_type,table_code,table_name,ts,userdefflag,value) values('1001Z810000000003TDF','产品线',5,9,1,null,0,'cgeneralbid.cprodlineid',null,null,'N','Y','N','Y','N','N','N','N','N','N','N','N','N','N','Y',null,'Y',9999,null,'=','等于@',0,'@@@@','1001Z810000000003TD5',null,'2UC000-000044',2,null,null,'2013-03-26 21:47:28','N',null)
go

insert into pub_query_condition(id,consult_code,data_type,disp_sequence,disp_type,disp_value,dr,field_code,field_name,guideline,if_attrrefused,if_autocheck,if_datapower,if_default,if_desc,if_group,if_immobility,if_multicorpref,if_must,if_notmdcondition,if_order,if_subincluded,if_sum,if_sysfuncrefused,if_used,instrumentsql,iscondition,limits,max_length,opera_code,opera_name,order_sequence,pk_corp,pk_templet,prerestrict,resid,return_type,table_code,table_name,ts,userdefflag,value) values('1001Z810000000003TDG','物料',5,10,0,null,0,'cgeneralbid.cmaterialoid.code',null,null,'N','N','N','Y','N','N','N','N','N','N','N','N','N','N','Y',null,'Y',9999,null,'=@left like@','等于@左包含@',0,'@@@@','1001Z810000000003TD5',null,'2UC000-000608',0,null,null,'2013-03-26 21:47:28','N',null)
go

insert into pub_query_condition(id,consult_code,data_type,disp_sequence,disp_type,disp_value,dr,field_code,field_name,guideline,if_attrrefused,if_autocheck,if_datapower,if_default,if_desc,if_group,if_immobility,if_multicorpref,if_must,if_notmdcondition,if_order,if_subincluded,if_sum,if_sysfuncrefused,if_used,instrumentsql,iscondition,limits,max_length,opera_code,opera_name,order_sequence,pk_corp,pk_templet,prerestrict,resid,return_type,table_code,table_name,ts,userdefflag,value) values('1001Z810000000003TDH','-99',9,11,1,null,0,'cgeneralbid.cmaterialoid.name',null,null,'N','Y','N','Y','N','N','N','N','N','N','N','N','N','N','Y',null,'Y',9999,null,'=@left like@','包含@等于@左包含@右包含@',0,'@@@@','1001Z810000000003TD5',null,'2materia-000006',2,null,null,'2013-03-26 21:47:28','N',null)
go

insert into pub_query_condition(id,consult_code,data_type,disp_sequence,disp_type,disp_value,dr,field_code,field_name,guideline,if_attrrefused,if_autocheck,if_datapower,if_default,if_desc,if_group,if_immobility,if_multicorpref,if_must,if_notmdcondition,if_order,if_subincluded,if_sum,if_sysfuncrefused,if_used,instrumentsql,iscondition,limits,max_length,opera_code,opera_name,order_sequence,pk_corp,pk_templet,prerestrict,resid,return_type,table_code,table_name,ts,userdefflag,value) values('1001Z810000000003TDI','-99',0,12,1,null,null,'cgeneralbid.vbatchcode',null,null,'N','Y','N','Y','N','N','N','N','N','N','N','N','N','N','Y',null,'Y',9999,null,'=@','等于@',0,'@@@@','1001Z810000000003TD5',null,'2UC000-000375',2,null,null,'2013-03-26 21:47:28','N',null)
go

insert into pub_query_condition(id,consult_code,data_type,disp_sequence,disp_type,disp_value,dr,field_code,field_name,guideline,if_attrrefused,if_autocheck,if_datapower,if_default,if_desc,if_group,if_immobility,if_multicorpref,if_must,if_notmdcondition,if_order,if_subincluded,if_sum,if_sysfuncrefused,if_used,instrumentsql,iscondition,limits,max_length,opera_code,opera_name,order_sequence,pk_corp,pk_templet,prerestrict,resid,return_type,table_code,table_name,ts,userdefflag,value) values('1001Z810000000003TDJ','物料基本分类',5,13,1,null,0,'cgeneralbid.cmaterialoid.pk_marbasclass',null,null,'N','Y','N','Y','N','N','N','N','N','N','N','N','N','N','Y',null,'Y',9999,null,'=','等于@',0,'@@@@','1001Z810000000003TD5',null,'2UC000-000599',2,null,null,'2013-03-26 21:47:28','N',null)
go

insert into pub_query_condition(id,consult_code,data_type,disp_sequence,disp_type,disp_value,dr,field_code,field_name,guideline,if_attrrefused,if_autocheck,if_datapower,if_default,if_desc,if_group,if_immobility,if_multicorpref,if_must,if_notmdcondition,if_order,if_subincluded,if_sum,if_sysfuncrefused,if_used,instrumentsql,iscondition,limits,max_length,opera_code,opera_name,order_sequence,pk_corp,pk_templet,prerestrict,resid,return_type,table_code,table_name,ts,userdefflag,value) values('1001Z810000000003U4A','SM,52c4ce26-d96d-4ff7-b07f-3de2bcfffe2b',6,14,1,null,null,'summarykey','汇总条件',null,'N','Y','N','Y','N','N','N','N','Y','Y','N','N','N','N','Y',null,'N',9999,null,'=@','等于@',0,'@@@@','1001Z810000000003TD5',null,'1400609090020',2,null,null,'2013-03-26 21:47:28','N','ccustomerid,cmaterialvid')
go

insert into pub_query_condition(id,consult_code,data_type,disp_sequence,disp_type,disp_value,dr,field_code,field_name,guideline,if_attrrefused,if_autocheck,if_datapower,if_default,if_desc,if_group,if_immobility,if_multicorpref,if_must,if_notmdcondition,if_order,if_subincluded,if_sum,if_sysfuncrefused,if_used,instrumentsql,iscondition,limits,max_length,opera_code,opera_name,order_sequence,pk_corp,pk_templet,prerestrict,resid,return_type,table_code,table_name,ts,userdefflag,value) values('1001Z810000000004EYE','IX,1400609090022=1,1400609090023=2,1400609090024=3,1400609090025=4,1400609090026=5,1400609090027=6,1400609090028=0',6,1,1,null,null,'saleorglevel','销售组织级次',null,'N','Y','N','Y','N','N','N','N','N','Y','N','N','N','N','Y',null,'N',9999,null,'==@','等于@',0,'@@@@','1001Z810000000003TD5',null,'1400609090019',2,null,null,'2013-03-28 14:10:22','N','0')
go

insert into pub_query_condition(id,consult_code,data_type,disp_sequence,disp_type,disp_value,dr,field_code,field_name,guideline,if_attrrefused,if_autocheck,if_datapower,if_default,if_desc,if_group,if_immobility,if_multicorpref,if_must,if_notmdcondition,if_order,if_subincluded,if_sum,if_sysfuncrefused,if_used,instrumentsql,iscondition,limits,max_length,opera_code,opera_name,order_sequence,pk_corp,pk_templet,prerestrict,resid,return_type,table_code,table_name,ts,userdefflag,value) values('1001Z810000000004EYF','IX,1400609090022=1,1400609090023=2,1400609090024=3,1400609090025=4,1400609090026=5,1400609090027=6,1400609090028=0',6,1,1,null,null,'cmaterialmarbasclasslevel','物料分类级次',null,'N','Y','N','Y','N','N','N','N','N','Y','N','N','N','N','Y',null,'N',9999,null,'==@','等于@',0,'@@@@','1001Z810000000003TD5',null,'1400609090021',2,null,null,'2013-03-28 14:10:22','N','0')
go

insert into pub_query_condition(id,consult_code,data_type,disp_sequence,disp_type,disp_value,dr,field_code,field_name,guideline,if_attrrefused,if_autocheck,if_datapower,if_default,if_desc,if_group,if_immobility,if_multicorpref,if_must,if_notmdcondition,if_order,if_subincluded,if_sum,if_sysfuncrefused,if_used,instrumentsql,iscondition,limits,max_length,opera_code,opera_name,order_sequence,pk_corp,pk_templet,prerestrict,resid,return_type,table_code,table_name,ts,userdefflag,value) values('1001Z810000000005LEC','渠道类型',5,4,1,null,null,'cchanneltypeid','销售渠道类型',null,'N','Y','N','Y','N','N','N','N','N','Y','N','N','N','N','Y',null,'N',9999,null,'=@','等于@',0,'@@@@','1001Z810000000003TD5',null,'1400609090003',2,null,null,'2013-03-26 21:47:28','N',null)
go

insert into pub_query_condition(id,consult_code,data_type,disp_sequence,disp_type,disp_value,dr,field_code,field_name,guideline,if_attrrefused,if_autocheck,if_datapower,if_default,if_desc,if_group,if_immobility,if_multicorpref,if_must,if_notmdcondition,if_order,if_subincluded,if_sum,if_sysfuncrefused,if_used,instrumentsql,iscondition,limits,max_length,opera_code,opera_name,order_sequence,pk_corp,pk_templet,prerestrict,resid,return_type,table_code,table_name,ts,userdefflag,value) values('1001Z810000000007VYM','物料',5,17,1,null,0,'cgeneralbid.cmaterialoid',null,null,'N','Y','N','N','N','N','N','N','N','N','N','N','N','N','Y',null,'Y',9999,null,'=','等于@',0,'@@@@','1001Z810000000003TD5',null,null,2,null,null,'2015-05-23 13:17:48','N',null)
go
