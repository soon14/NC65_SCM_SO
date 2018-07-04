package nc.ui.so.salequotation.model;

import java.util.HashMap;
import java.util.Map;

import nc.ui.pubapp.uif2app.model.BillManageModel;
import nc.vo.price.pub.context.PriceContext;
import nc.vo.pub.BusinessException;
import nc.vo.pubapp.pattern.exception.ExceptionUtils;
import nc.vo.so.m4310trantype.entity.M4310TranTypeVO;

public class SalequoModel extends BillManageModel {

  private Map<String, M4310TranTypeVO> m4310TranTypeBuffer =null;

  private SalequoModelService salequoService;

  public Map<String, M4310TranTypeVO> getM4310TranTypeBuffer() {
    //第一次使用的时候加载交易类型
    this.initM4310TranTypeBuffer();
    return this.m4310TranTypeBuffer;
  }

  public SalequoModelService getSalequoService() {
    return this.salequoService;
  }

  @Override
  public void initModel(Object data) {
    super.initModel(data);
    if (m4310TranTypeBuffer !=null){
    //每次打开节点清空缓存的交易类型
    this.m4310TranTypeBuffer.clear();
    }
  }

  public void setSalequoService(SalequoModelService salequoService) {
    this.salequoService = salequoService;
  }

  @Override
  protected void dbDeleteMultiRows(Object... deletedObjects) throws Exception {
    this.getService().delete(deletedObjects);
  }

  private void initM4310TranTypeBuffer() {
    try {
      if(m4310TranTypeBuffer!=null){
        return ;
      }
      M4310TranTypeVO[] m4310TranTypeVOs =
          this.getSalequoService().queryAllTranType(
              this.getContext().getPk_group(), PriceContext.SALEQUOBILLTYPE);
      m4310TranTypeBuffer=new HashMap<String,M4310TranTypeVO>();
      for (M4310TranTypeVO m4310TranTypeVO : m4310TranTypeVOs) {
        this.m4310TranTypeBuffer.put(m4310TranTypeVO.getVtrantype(),
            m4310TranTypeVO);
      }
    }
    catch (BusinessException e) {
      ExceptionUtils.wrappException(e);
    }
  }
}
