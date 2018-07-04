/* indexcode: i_so_434C_b_fk */
create  index i_so_434C_b_fk on so_squareout_b (csalesquareid)
go

/* indexcode: i_so_434C_b_4cbid */
create  index i_so_434C_b_4cbid on so_squareout_b (csquarebillbid)
go

/* indexcode: i_so_434C_b_bfrt */
create  index i_so_434C_b_bfrt on so_squareout_b (cfirstbid)
go

/* indexcode: i_so_434C_b_date */
create  index i_so_434C_b_date on so_squareout_b (dbilldate,
pk_org)
go

/* indexcode: i_so_434C_4cid */
create  index i_so_434C_4cid on so_squareout (csquarebillid)
go

/* indexcode: i_so_434C_date */
create  index i_so_434C_date on so_squareout (dbilldate,
pk_org)
go

/* indexcode: i_so_434C_code */
create  index i_so_434C_code on so_squareout (vbillcode)
go

/* indexcode: i_so_434C_d_fbid */
create  index i_so_434C_d_fbid on so_squareout_d (csalesquarebid)
go

/* indexcode: i_so_434C_d_orbid */
create  index i_so_434C_d_orbid on so_squareout_d (crushgeneralbid)
go

/* indexcode: i_so_434C_d_4cbid */
create  index i_so_434C_d_4cbid on so_squareout_d (csquarebillbid)
go

/* indexcode: i_so_4332_b_fk */
create  index i_so_4332_b_fk on so_squareinv_b (csalesquareid)
go

/* indexcode: i_so_4332_b_32bid */
create  index i_so_4332_b_32bid on so_squareinv_b (csquarebillbid)
go

/* indexcode: i_so_4332_b_bsrc */
create  index i_so_4332_b_bsrc on so_squareinv_b (csrcbid)
go

/* indexcode: i_so_4332_b_bfrt */
create  index i_so_4332_b_bfrt on so_squareinv_b (cfirstbid)
go

/* indexcode: i_so_4332_h_32id */
create  index i_so_4332_h_32id on so_squareinv (csquarebillid)
go

/* indexcode: i_so_4332_d_fid */
create  index i_so_4332_d_fid on so_squareinv_d (csalesquareid)
go

/* indexcode: i_so_4332_d_sbid */
create  index i_so_4332_d_sbid on so_squareinv_d (csquarebillbid)
go

/* indexcode: i_so_4353_b_bsrc */
create  index i_so_4353_b_bsrc on so_squarewas_b (csrcbid)
go

/* indexcode: i_so_4353_b_wbid */
create  index i_so_4353_b_wbid on so_squarewas_b (csquarebillbid)
go

/* indexcode: i_so_4353_b_fk */
create  index i_so_4353_b_fk on so_squarewas_b (csalesquareid)
go

/* indexcode: i_so_4353_h_wasid */
create  index i_so_4353_h_wasid on so_squarewas (csquarebillid)
go

/* indexcode: i_so_4353_d_fid */
create  index i_so_4353_d_fid on so_squarewas_d (csalesquareid)
go

/* indexcode: i_so_4353_d_wbid */
create  index i_so_4353_d_wbid on so_squarewas_d (csquarebillbid)
go

