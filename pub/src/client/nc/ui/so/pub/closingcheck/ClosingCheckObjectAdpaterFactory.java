package nc.ui.so.pub.closingcheck;

import nc.vo.bd.meta.IBDObject;
import nc.vo.bd.meta.IBDObjectAdapterFactory;
import nc.vo.uap.rbac.BusiFuncItemVO;

/**
 * @description 对象转换器工厂，由此获取操作对象的特征信息
 * 
 * @since 6.3
 * @version 2014-11-17
 * @author 谷志飞
 * 
 */

public class ClosingCheckObjectAdpaterFactory implements
    IBDObjectAdapterFactory {

  @Override
  public IBDObject createBDObject(Object obj) {
    if (obj != null && obj instanceof BusiFuncItemVO) {
      BusiFuncItemVO vo = (BusiFuncItemVO) obj;
      return new BDObjectForBusiFuncItem(vo);
    }
    return null;
  }

  private class BDObjectForBusiFuncItem implements IBDObject {

    private BusiFuncItemVO vo;

    public BDObjectForBusiFuncItem(BusiFuncItemVO vo) {
      this.vo = vo;
    }

    @Override
    public Object getId() {
      return this.vo.getPk_busifunc();
    }

    @Override
    public Object getPId() {
      return this.vo.getParentid();
    }

    @Override
    public Object getPk_org() {
      return null;
    }

    @Override
    public Object getCode() {
      return this.vo.getCode();
    }

    @Override
    public Object getName() {
      return this.vo.getName();
    }

    @Override
    public Object getPk_group() {
      return null;
    }
  }
}
