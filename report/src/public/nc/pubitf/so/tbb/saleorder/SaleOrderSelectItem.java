package nc.pubitf.so.tbb.saleorder;

import java.util.HashMap;
import java.util.Map;

import nc.itf.scmpub.tbb.ITbbSelectItem;
import nc.vo.so.m30.entity.SaleOrderBVO;

public class SaleOrderSelectItem implements ITbbSelectItem {

  private String getTaxMnyKey(String currtype) {

    int type = Integer.valueOf(currtype).intValue();
    if (type == 0) {
      return SaleOrderBVO.NGLOBALTAXMNY;
    }
    else if (type == 1) {
      return SaleOrderBVO.NGROUPTAXMNY;
    }
    else if (type == 2) {
      return SaleOrderBVO.NTAXMNY;
    }
    else {
      return SaleOrderBVO.NORIGTAXMNY;
    }
  }

  private String getMnyKey(String currtype) {

    int type = Integer.valueOf(currtype).intValue();
    if (type == 0) {
      return SaleOrderBVO.NGLOBALMNY;
    }
    else if (type == 1) {
      return SaleOrderBVO.NGROUPMNY;
    }
    else if (type == 2) {
      return SaleOrderBVO.NMNY;
    }
    else {
      return SaleOrderBVO.NORIGMNY;
    }

  }

  @Override
  public int[] getSumKeyIndex(String controlobj, String currtype) {
    int[] index = new int[1];
    index[0] = Integer.valueOf(currtype).intValue();
    return index;
  }

  @Override
  public String[] getSumKey(String controlobj, String currtype) {

    String[] metapath = new String[1];
    if (SaleOrderBVO.NORIGMNY.equals(controlobj)) {
      metapath[0] = this.getMnyKey(currtype);
    }
    else if (SaleOrderBVO.NORIGTAXMNY.equals(controlobj)) {
      metapath[0] = this.getTaxMnyKey(currtype);
    }
    else {
      metapath[0] = SaleOrderBVO.NNUM;
    }
    return metapath;

  }

  @Override
  public String[] getGroupKey(String controlobj, String currtype) {
    return null;
  }

  @Override
  public Map<String, String> getSelectItemMetaPathMap(String controlobj,
      String currtype) {
    Map<String, String> mapmaetpath = new HashMap<String, String>();
    String[] sumkey = this.getSumKey(controlobj, currtype);
    for (String key : sumkey) {
      mapmaetpath.put(key, SaleOrderBVO.METAPATH + key);
    }
    return mapmaetpath;
  }
}
