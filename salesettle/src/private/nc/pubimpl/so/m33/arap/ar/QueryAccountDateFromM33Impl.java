package nc.pubimpl.so.m33.arap.ar;

import java.util.List;
import java.util.Map;

import nc.pubitf.so.m33.arap.AccountDateType;
import nc.pubitf.so.m33.arap.IQueryAccountDateFromM33;
import nc.vo.pub.BusinessException;
import nc.vo.pub.lang.UFDate;

public class QueryAccountDateFromM33Impl implements IQueryAccountDateFromM33 {

  @Override
  public Map<String, UFDate[]> queryAccountDate(
      Map<String, List<AccountDateType>> map, String billType)
      throws BusinessException {
    Map<String, UFDate[]> ret =
        new QueryAccountDateFromM33Action().queryAccountDate(map, billType);
    return ret;
  }

}
