/* indexcode: i_so_30_code */
create  index i_so_30_code on so_saleorder (vbillcode asc)
/

/* indexcode: i_so_30_date */
create  index i_so_30_date on so_saleorder (dbilldate asc,
pk_org asc)
/

/* indexcode: i_so_30_cust */
create  index i_so_30_cust on so_saleorder (ccustomerid asc,
pk_org asc)
/

/* indexcode: i_so_30_invcust */
create  index i_so_30_invcust on so_saleorder (cinvoicecustid asc,
pk_org asc)
/

/* indexcode: i_so_30_b_fk */
create  index i_so_30_b_fk on so_saleorder_b (csaleorderid asc)
/

/* indexcode: i_so_30_b_date */
create  index i_so_30_b_date on so_saleorder_b (dbilldate asc,
pk_org asc)
/

/* indexcode: i_so_30_b_src */
create  index i_so_30_b_src on so_saleorder_b (csrcid asc)
/

/* indexcode: i_so_30_b_fdate */
create  index i_so_30_b_fdate on so_saleorder_b (dsenddate asc)
/

/* indexcode: i_so_30_b_src_ct */
create  index i_so_30_b_src_ct on so_saleorder_b (cctmanageid asc,
cctmanagebid asc)
/

/* indexcode: i_so_bal_orderid */
create  index i_so_bal_orderid on so_balance (csaleorderid asc)
/

/* indexcode: i_so_bal_b_fk */
create  index i_so_bal_b_fk on so_balance_b (csobalanceid asc)
/

/* indexcode: i_so_bal_b_paybid */
create  index i_so_bal_b_paybid on so_balance_b (cpaybillrowid asc)
/

/* indexcode: i_so_his_b_fk */
create  index i_so_his_b_fk on so_orderhistory_b (corderhistoryid asc)
/

/* indexcode: i_so_mb_mo_org */
create  index i_so_mb_mo_org on so_mb_myorder (pk_org asc,
cemployeeid asc)
/

/* indexcode: i_so_mb_analy */
create  index i_so_mb_analy on so_mb_orderanaly (dbilldate asc,
pk_org asc)
/

