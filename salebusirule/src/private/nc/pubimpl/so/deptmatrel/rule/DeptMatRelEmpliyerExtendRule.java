package nc.pubimpl.so.deptmatrel.rule;

import java.util.List;

import nc.pubitf.so.deptmatrel.DeptMatRelParaVO;
import nc.vo.so.pub.util.ObjectUtil;

/**
 * 扩展业务员
 * 
 * @since 6.0
 * @version 2011-7-6 下午04:05:32
 * @author 祝会征
 */
public class DeptMatRelEmpliyerExtendRule {
  public void extendEmployer(List<DeptMatRelParaVO> extendpara) {
    DeptMatRelParaVO[] vos = new DeptMatRelParaVO[extendpara.size()];
    extendpara.toArray(vos);
    for (DeptMatRelParaVO vo : vos) {
      String psn = vo.getCemployeeid();
      if (null == psn || "".equals(psn)) {
        continue;
      }
      DeptMatRelParaVO newvo =
          (DeptMatRelParaVO) ObjectUtil.serializableClone(vo);
      vo.setCemployeeid(null);
      extendpara.add(newvo);
    }
  }
}
