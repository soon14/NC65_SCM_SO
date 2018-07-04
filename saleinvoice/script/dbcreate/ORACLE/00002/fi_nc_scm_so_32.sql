/* indexcode: i_so_32_b_dorg */
create  index i_so_32_b_dorg on so_saleinvoice_b (dbilldate asc,
csaleorgid asc)
/

/* indexcode: i_so_32_b_fk */
create  index i_so_32_b_fk on so_saleinvoice_b (csaleinvoiceid asc)
/

/* indexcode: i_so_32_b_date */
create  index i_so_32_b_date on so_saleinvoice_b (dbilldate asc,
pk_org asc)
/

/* indexcode: i_so_32_b_csrcid */
create  index i_so_32_b_csrcid on so_saleinvoice_b (csrcid asc)
/

/* indexcode: i_so_32_b_fid */
create  index i_so_32_b_fid on so_saleinvoice_b (cfirstid asc)
/

/* indexcode: i_so_32_code */
create  index i_so_32_code on so_saleinvoice (vbillcode asc)
/

/* indexcode: i_so_32_date */
create  index i_so_32_date on so_saleinvoice (dbilldate asc,
pk_org asc)
/

/* indexcode: i_so_32_cust */
create  index i_so_32_cust on so_saleinvoice (cinvoicecustid asc)
/

