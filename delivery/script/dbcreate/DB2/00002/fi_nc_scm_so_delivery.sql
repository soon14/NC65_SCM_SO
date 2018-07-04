/* indexcode: i_so_4331_date */
create  index i_so_4331_date on so_delivery (dbilldate asc,
pk_org asc)
;

/* indexcode: i_so_4331_code */
create  index i_so_4331_code on so_delivery (vbillcode asc)
;

/* indexcode: i_so_4331_b_fk */
create  index i_so_4331_b_fk on so_delivery_b (cdeliveryid asc)
;

/* indexcode: i_so_4331_b_date */
create  index i_so_4331_b_date on so_delivery_b (dbilldate asc,
pk_org asc)
;

/* indexcode: i_so_4331_b_srcb */
create  index i_so_4331_b_srcb on so_delivery_b (csrcbid asc)
;

/* indexcode: i_so_4331_b_src */
create  index i_so_4331_b_src on so_delivery_b (csrcid asc)
;

/* indexcode: i_so_4331_b_sdate */
create  index i_so_4331_b_sdate on so_delivery_b (dsenddate asc)
;

/* indexcode: i_so_4331_b_cust */
create  index i_so_4331_b_cust on so_delivery_b (pk_org asc,
cordercustid asc)
;

/* indexcode: i_so_4331_b_dorg */
create  index i_so_4331_b_dorg on so_delivery_b (dbilldate asc,
csaleorgid asc)
;

/* indexcode: i_so_4331_c_fk */
create  index i_so_4331_c_fk on so_delivery_check (cdeliverybid asc)
;

