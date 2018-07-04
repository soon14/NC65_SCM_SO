package nc.pubimpl.so.m4331.opc.mecc1;

import nc.impl.pubapp.pattern.data.vo.VOQuery;
import nc.pubitf.so.m4331.opc.mecc1.IQuerySendInfo;
import nc.pubitf.so.m4331.opc.mecc1.ReturnSendInfoVO;
import nc.vo.pub.BusinessException;
import nc.vo.pubapp.pattern.exception.ExceptionUtils;
import nc.vo.so.m4331.entity.DeliveryBVO;

/**
 * 根据发货单表体id查询发货单：发货联系人、发货联系电话、要求收货日期
 * 
 * @since 6.0
 * @version 2011-12-29 上午09:12:05
 * @author 刘景
 */
public class QuerySendInfoImpl implements IQuerySendInfo {

  @Override
  public ReturnSendInfoVO[] query(String[] bids) throws BusinessException {

    ReturnSendInfoVO[] rsivo = null;
    if (null == bids || bids.length == 0) {
      ExceptionUtils.wrappBusinessException("当前发货单子表ID为空。");/*-=notranslate=-*/
    }
    try {
      // 要查询的实体属性名
      String[] entityNames =
          new String[] {
            DeliveryBVO.CDELIVERYBID, DeliveryBVO.CSENDPERSONID,
            DeliveryBVO.DRECEIVEDATE, DeliveryBVO.VSENDTEL
          };
      VOQuery<DeliveryBVO> query =
          new VOQuery<DeliveryBVO>(DeliveryBVO.class, entityNames);
      // 根据发货单子表ID（主键）查询
      DeliveryBVO[] debvo = query.query(bids);
      if (null == debvo || debvo.length == 0) {
        return new ReturnSendInfoVO[0];
      }
      int len = debvo.length;
      rsivo = new ReturnSendInfoVO[len];
      // 把原对象数组赋值新对象数组
      for (int i = 0; i < len; i++) {
        rsivo[i] = new ReturnSendInfoVO();
        // 发货联系人
        rsivo[i].setCsendpersonid(debvo[i].getCsendpersonid());
        // 发货联系电话
        rsivo[i].setVsendtel(debvo[i].getVsendtel());
        // 预计到货日期
        rsivo[i].setDreceivedate(debvo[i].getDreceivedate().toString());
        // 发货单子表ID
        rsivo[i].setCdeliverybid(debvo[i].getCdeliverybid());
      }

    }
    catch (Exception e) {
      ExceptionUtils.marsh(e);
    }
    return rsivo;
  }
}
