/* indexcode: i_so_30_code */
create  index i_so_30_code on so_saleorder (vbillcode)
go

/* indexcode: i_so_30_date */
create  index i_so_30_date on so_saleorder (dbilldate,
pk_org)
go

/* indexcode: i_so_30_cust */
create  index i_so_30_cust on so_saleorder (ccustomerid,
pk_org)
go

/* indexcode: i_so_30_invcust */
create  index i_so_30_invcust on so_saleorder (cinvoicecustid,
pk_org)
go

/* indexcode: i_so_30_b_fk */
create  index i_so_30_b_fk on so_saleorder_b (csaleorderid)
go

/* indexcode: i_so_30_b_date */
create  index i_so_30_b_date on so_saleorder_b (dbilldate,
pk_org)
go

/* indexcode: i_so_30_b_src */
create  index i_so_30_b_src on so_saleorder_b (csrcid)
go

/* indexcode: i_so_30_b_fdate */
create  index i_so_30_b_fdate on so_saleorder_b (dsenddate)
go

/* indexcode: i_so_30_b_src_ct */
create  index i_so_30_b_src_ct on so_saleorder_b (cctmanageid,
cctmanagebid)
go

/* indexcode: i_so_bal_orderid */
create  index i_so_bal_orderid on so_balance (csaleorderid)
go

/* indexcode: i_so_bal_b_fk */
create  index i_so_bal_b_fk on so_balance_b (csobalanceid)
go

/* indexcode: i_so_bal_b_paybid */
create  index i_so_bal_b_paybid on so_balance_b (cpaybillrowid)
go

/* indexcode: i_so_his_b_fk */
create  index i_so_his_b_fk on so_orderhistory_b (corderhistoryid)
go

/* indexcode: i_so_mb_mo_org */
create  index i_so_mb_mo_org on so_mb_myorder (pk_org,
cemployeeid)
go

/* indexcode: i_so_mb_analy */
create  index i_so_mb_analy on so_mb_orderanaly (dbilldate,
pk_org)
go

