package nc.pubimpl.so.custmatrel.rule;

import java.util.HashSet;
import java.util.List;
import java.util.Set;

import nc.pubitf.so.custmatrel.CustMatRelParaVO;
import nc.vo.so.pub.util.ObjectUtil;

public class CustMatRelOtherExtendRule {
  public void extendOther(List<CustMatRelParaVO> extendpara) {
    CustMatRelParaVO[] vos = new CustMatRelParaVO[extendpara.size()];
    extendpara.toArray(vos);
    Set<Integer> tempSet = new HashSet<Integer>();
    for (CustMatRelParaVO vo : vos) {
      Integer index = vo.getParaindex();
      if (tempSet.contains(index)) {
        continue;
      }
      tempSet.add(index);
      CustMatRelParaVO newvo =
          (CustMatRelParaVO) ObjectUtil.serializableClone(vo);
      vo.setPk_customer(null);
      vo.setPk_custbaseclass(null);
      vo.setPk_custsaleclass(null);
      extendpara.add(newvo);
    }
  }
}
