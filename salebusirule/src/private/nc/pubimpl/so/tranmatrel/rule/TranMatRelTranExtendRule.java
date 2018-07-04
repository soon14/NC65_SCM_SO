package nc.pubimpl.so.tranmatrel.rule;

import java.util.List;

import nc.pubitf.so.tanmatrel.TranMatRelParaVO;
import nc.vo.so.pub.util.ObjectUtil;

public class TranMatRelTranExtendRule {
  public void extendTrans(List<TranMatRelParaVO> extendpara) {
    TranMatRelParaVO[] vos = new TranMatRelParaVO[extendpara.size()];
    extendpara.toArray(vos);
    for (TranMatRelParaVO vo : vos) {
      String pk = vo.getTrantype();
      if (null == pk || "".equals(pk)) {
        continue;
      }
      TranMatRelParaVO newvo =
          (TranMatRelParaVO) ObjectUtil.serializableClone(vo);
      vo.setTrantype(null);
      extendpara.add(newvo);
    }
  }
}
