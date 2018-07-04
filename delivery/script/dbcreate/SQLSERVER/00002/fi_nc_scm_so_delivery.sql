/* indexcode: i_so_4331_date */
create  index i_so_4331_date on so_delivery (dbilldate,
pk_org)
go

/* indexcode: i_so_4331_code */
create  index i_so_4331_code on so_delivery (vbillcode)
go

/* indexcode: i_so_4331_b_fk */
create  index i_so_4331_b_fk on so_delivery_b (cdeliveryid)
go

/* indexcode: i_so_4331_b_date */
create  index i_so_4331_b_date on so_delivery_b (dbilldate,
pk_org)
go

/* indexcode: i_so_4331_b_srcb */
create  index i_so_4331_b_srcb on so_delivery_b (csrcbid)
go

/* indexcode: i_so_4331_b_src */
create  index i_so_4331_b_src on so_delivery_b (csrcid)
go

/* indexcode: i_so_4331_b_sdate */
create  index i_so_4331_b_sdate on so_delivery_b (dsenddate)
go

/* indexcode: i_so_4331_b_cust */
create  index i_so_4331_b_cust on so_delivery_b (pk_org,
cordercustid)
go

/* indexcode: i_so_4331_b_dorg */
create  index i_so_4331_b_dorg on so_delivery_b (dbilldate,
csaleorgid)
go

/* indexcode: i_so_4331_c_fk */
create  index i_so_4331_c_fk on so_delivery_check (cdeliverybid)
go

