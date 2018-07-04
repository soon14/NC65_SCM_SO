package nc.pubimpl.so.custmatrel.opc.mecc;

import java.util.ArrayList;
import java.util.List;

import nc.bs.framework.common.NCLocator;
import nc.pubitf.so.custmatrel.CustMatRelParaVO;
import nc.pubitf.so.custmatrel.ICustMatRelFor30;
import nc.pubitf.so.custmatrel.opc.mecc.CustMatRelParaForOPCVO;
import nc.pubitf.so.custmatrel.opc.mecc.ICustMatRelForOPC;
import nc.vo.pub.BusinessException;
import nc.vo.pub.lang.UFBoolean;
import nc.vo.pubapp.pattern.exception.ExceptionUtils;

/**
 * 销售客户物料关系定义给订单统一处理中心提供的接口
 * 根据输入的销售组织+客户+物料集合得到允许销售的客户+物料集合
 * 
 * @since 6.0
 * @version 2012-2-28 上午16:20:21
 * @author 刘景
 */
public class CustMatRelForOPCImpl implements ICustMatRelForOPC {

  @Override
  public CustMatRelParaForOPCVO[] filterData(CustMatRelParaForOPCVO[] paravos)
      throws BusinessException {
    List<CustMatRelParaForOPCVO> custMatRel =
        new ArrayList<CustMatRelParaForOPCVO>();
    try {
      // 转换VO对象
      int len = paravos.length;
      CustMatRelParaVO[] cstmrlVO = new CustMatRelParaVO[len];
      for (int i = 0; i < len; i++) {
        cstmrlVO[i] = new CustMatRelParaVO();
        cstmrlVO[i].setPk_org(paravos[i].getPk_org());
        cstmrlVO[i].setPk_material(paravos[i].getPk_material());
        cstmrlVO[i].setPk_customer(paravos[i].getPk_customer());
      }
      // 检查客户和物料关系
      ICustMatRelFor30 service =
          NCLocator.getInstance().lookup(ICustMatRelFor30.class);
      UFBoolean[] flagArray = service.getCustMatRelSaleFlag(cstmrlVO);
      for (int i = 0; i < flagArray.length; i++) {
        // 装载合法的客户和物料
        if (flagArray[i].booleanValue()) {
          custMatRel.add(paravos[i]);
        }
      }
    }
    catch (Exception e) {
      ExceptionUtils.marsh(e);
    }
    return custMatRel.toArray(new CustMatRelParaForOPCVO[custMatRel.size()]);
  }
}
