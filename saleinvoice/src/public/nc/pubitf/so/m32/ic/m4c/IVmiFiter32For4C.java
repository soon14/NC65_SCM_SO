package nc.pubitf.so.m32.ic.m4c;

import java.util.List;

import nc.vo.pub.BusinessException;

public interface IVmiFiter32For4C {

  /**
   * 过滤销售发票已经做过汇总的记录
   * 
   * @param listBids 表体IDS
   * @param ids 表头ids
   * @return
   * @throws BusinessException
   */
  List<String> getSumBids(List<String> listBids, String[] ids)
      throws BusinessException;

}
