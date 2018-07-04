package nc.pubitf.so.tbb.saleorder;

import nc.itf.scmpub.tbb.ITbbDateParaMetaPath;
import nc.pubitf.so.tbb.SOTbbFieldConst;

public class SaleOrderDateMetaPath implements ITbbDateParaMetaPath {

  @Override
  public String getDateMetaPath(String selectdatetype) {
    if (SOTbbFieldConst.DBILLDATE.equals(selectdatetype)) {
      return "so_saleorder_b.dbilldate";
    }
    return selectdatetype;
  }

}
