/* indexcode: i_so_4310_b_fk */
create  index i_so_4310_b_fk on so_salequotation_b (pk_salequotation asc)
/

/* indexcode: i_so_4310_code */
create  index i_so_4310_code on so_salequotation (vbillcode asc)
/

/* indexcode: i_so_4310_date */
create  index i_so_4310_date on so_salequotation (dquotedate asc,
pk_org asc)
/

/* indexcode: i_so_4310_cust */
create  index i_so_4310_cust on so_salequotation (pk_customer asc,
pk_org asc)
/

