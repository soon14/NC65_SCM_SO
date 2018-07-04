/* indexcode: i_so_4310_b_fk */
create  index i_so_4310_b_fk on so_salequotation_b (pk_salequotation)
go

/* indexcode: i_so_4310_code */
create  index i_so_4310_code on so_salequotation (vbillcode)
go

/* indexcode: i_so_4310_date */
create  index i_so_4310_date on so_salequotation (dquotedate,
pk_org)
go

/* indexcode: i_so_4310_cust */
create  index i_so_4310_cust on so_salequotation (pk_customer,
pk_org)
go

