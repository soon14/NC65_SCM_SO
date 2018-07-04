/* indexcode: i_so_32_b_dorg */
create  index i_so_32_b_dorg on so_saleinvoice_b (dbilldate,
csaleorgid)
go

/* indexcode: i_so_32_b_fk */
create  index i_so_32_b_fk on so_saleinvoice_b (csaleinvoiceid)
go

/* indexcode: i_so_32_b_date */
create  index i_so_32_b_date on so_saleinvoice_b (dbilldate,
pk_org)
go

/* indexcode: i_so_32_b_csrcid */
create  index i_so_32_b_csrcid on so_saleinvoice_b (csrcid)
go

/* indexcode: i_so_32_b_fid */
create  index i_so_32_b_fid on so_saleinvoice_b (cfirstid)
go

/* indexcode: i_so_32_code */
create  index i_so_32_code on so_saleinvoice (vbillcode)
go

/* indexcode: i_so_32_date */
create  index i_so_32_date on so_saleinvoice (dbilldate,
pk_org)
go

/* indexcode: i_so_32_cust */
create  index i_so_32_cust on so_saleinvoice (cinvoicecustid)
go

