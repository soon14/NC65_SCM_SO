/* indexcode: i_so_38_code */
create  index i_so_38_code on so_preorder (vbillcode)
go

/* indexcode: i_so_38_date */
create  index i_so_38_date on so_preorder (dbilldate,
pk_org)
go

/* indexcode: i_so_38_cust */
create  index i_so_38_cust on so_preorder (ccustomerid,
pk_org)
go

/* indexcode: i_so_38_b_fk */
create  index i_so_38_b_fk on so_preorder_b (cpreorderid)
go

/* indexcode: i_so_38_b_date */
create  index i_so_38_b_date on so_preorder_b (dbilldate,
pk_org)
go

