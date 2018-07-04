package nc.vo.so.mbuylargess.view;

import nc.vo.pubapp.pattern.model.entity.view.AbstractDataView;
import nc.vo.pubapp.pattern.model.meta.entity.view.DataViewMetaFactory;
import nc.vo.pubapp.pattern.model.meta.entity.view.IDataViewMeta;
import nc.vo.so.mbuylargess.entity.BuyLargessBVO;
import nc.vo.so.mbuylargess.entity.BuyLargessHVO;
import nc.vo.so.mbuylargess.entity.BuyLargessVO;

/**
 * 买赠政策展示的视图VO
 * 
 * @since 6.1
 * @version 2012-10-30 16:02:06
 * @author 冯加彬
 */
public class BuyLargessShowViewVO extends AbstractDataView {

  /**
   * 
   */
  private static final long serialVersionUID = 9220656358685217471L;

  @Override
  public IDataViewMeta getMetaData() {
    IDataViewMeta viewmeta =
        DataViewMetaFactory.getInstance().getDataViewMeta(
            BuyLargessShowViewMeta.class);
    return viewmeta;

  }

  /**
   * 获得买赠设置主实体VO
   * 
   * @return BuyLargessHVO
   */
  public BuyLargessHVO getHead() {
    return (BuyLargessHVO) this.getVO(BuyLargessHVO.class);
  }

  /**
   * 设置买赠设置主实体VO
   * 
   * @param head
   */
  public void setHead(BuyLargessHVO head) {
    this.setVO(head);
  }

  /**
   * 获得买赠设置子实体VO
   * 
   * @return BuyLargessBVO
   */
  public BuyLargessBVO getBody() {
    return (BuyLargessBVO) this.getVO(BuyLargessBVO.class);
  }

  /**
   * 设置买赠设置子实体VO
   * 
   * @param body
   */
  public void setBody(BuyLargessBVO body) {
    this.setVO(body);
  }

  /**
   * 从视图VO转换为单据VO
   * 
   * @return BuyLargessVO
   */
  public BuyLargessVO changeToBuyLargessVO() {
    BuyLargessVO billvo = new BuyLargessVO();
    billvo.setParent(this.getHead());
    BuyLargessBVO[] bodys = new BuyLargessBVO[] {
      this.getBody()
    };
    billvo.setChildrenVO(bodys);
    return billvo;
  }

  /**
   * 设置序列号
   * 
   * @param paraindex
   */
  public void setParaindex(Integer paraindex) {
    this.setAttributeValue(BuyLargessMatchViewMeta.PARAINDEX, paraindex);
  }

  /**
   * 获得序列号
   * 
   * @return paraindex
   */
  public Integer getParaindex() {
    return (Integer) this.getAttributeValue(BuyLargessMatchViewMeta.PARAINDEX);
  }
}
