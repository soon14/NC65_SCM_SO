package nc.pubitf.so.tbb;

import nc.itf.scmpub.tbb.ITbbDateParaMetaPath;

public class SODateMetaPath implements ITbbDateParaMetaPath {

  @Override
  public String getDateMetaPath(String selectdatetype) {
    return selectdatetype;
  }
}
