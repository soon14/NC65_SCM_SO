/* indexcode: i_so_38_code */
create  index i_so_38_code on so_preorder (vbillcode asc)
;

/* indexcode: i_so_38_date */
create  index i_so_38_date on so_preorder (dbilldate asc,
pk_org asc)
;

/* indexcode: i_so_38_cust */
create  index i_so_38_cust on so_preorder (ccustomerid asc,
pk_org asc)
;

/* indexcode: i_so_38_b_fk */
create  index i_so_38_b_fk on so_preorder_b (cpreorderid asc)
;

/* indexcode: i_so_38_b_date */
create  index i_so_38_b_date on so_preorder_b (dbilldate asc,
pk_org asc)
;

