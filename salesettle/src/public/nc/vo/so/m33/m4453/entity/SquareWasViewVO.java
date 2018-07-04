package nc.vo.so.m33.m4453.entity;

import nc.vo.pub.lang.UFDouble;
import nc.vo.pubapp.pattern.model.entity.view.AbstractDataView;
import nc.vo.pubapp.pattern.model.meta.entity.view.DataViewMetaFactory;
import nc.vo.pubapp.pattern.model.meta.entity.view.IDataViewMeta;

public class SquareWasViewVO extends AbstractDataView {

  private static final long serialVersionUID = -680949127956266342L;

  /**
   * @return 转换为聚合VO
   */
  public SquareWasVO changeToSquareWasVO() {
    SquareWasVO bill = new SquareWasVO();
    bill.setParent(this.getHead());
    SquareWasBVO[] items = new SquareWasBVO[] {
      this.getItem()
    };
    bill.setChildrenVO(items);
    return bill;
  }

  public SquareWasHVO getHead() {
    return (SquareWasHVO) this.getVO(SquareWasHVO.class);
  }

  public SquareWasBVO getItem() {
    return (SquareWasBVO) this.getVO(SquareWasBVO.class);
  }

  @Override
  public IDataViewMeta getMetaData() {
    return DataViewMetaFactory.getInstance().getBillViewMeta(SquareWasVO.class);
  }

  /**
   * 方法功能描述：是否可以取消结算
   * <p>
   * <b>examples:</b>
   * <p>
   * 使用示例
   * <p>
   * <b>参数说明</b>
   * 
   * @return
   *         <p>
   * @author zhangcheng
   * @time 2010-6-17 下午04:54:41
   */
  public boolean ifCanCancelSquare() {
    UFDouble nsquareianum;
    UFDouble nsquarearnum;
    nsquareianum = this.getItem().getNsquareianum();
    nsquarearnum = this.getItem().getNsquarearnum();
    boolean isEnable = false;
    if (((nsquareianum != null) 
        && (nsquareianum.compareTo(UFDouble.ZERO_DBL) != 0))
        || ((nsquarearnum != null) && (nsquarearnum
            .compareTo(UFDouble.ZERO_DBL) != 0))) {
      isEnable = true;
    }
    return isEnable;
  }

  /**
   * 方法功能描述：是否可以结算
   * <p>
   * <b>examples:</b>
   * <p>
   * 使用示例
   * <p>
   * <b>参数说明</b>
   * 
   * @return
   *         <p>
   * @author zhangcheng
   * @time 2010-6-17 下午04:54:41
   */
  public boolean ifCanSquare() {
    UFDouble thisnum = this.getItem().getNthisnum();
    boolean isEnable = false;
    if ((thisnum != null) && (thisnum.compareTo(UFDouble.ZERO_DBL) != 0)) {
      isEnable = true;
    }
    return isEnable;
  }

  public void setHead(SquareWasHVO head) {
    this.setVO(head);
  }

  public void setItem(SquareWasBVO item) {
    this.setVO(item);
  }

  /**
   * 方法功能描述：设置本次可结算数量
   * <p>
   * <b>examples:</b>
   * <p>
   * 使用示例
   * <p>
   * <b>参数说明</b>
   * <p>
   * 
   * @author zhangcheng
   * @time 2010-6-17 下午06:26:02
   */
  public void setNthisnum() {
    UFDouble nsquareianum = this.getItem().getNsquareianum();
    UFDouble nsquarearnum = this.getItem().getNsquarearnum();
    UFDouble nnum = this.getItem().getNnum();
    if (nsquareianum != null) {
      this.getItem().setNthisnum(nnum.sub(nsquareianum));
    }
    else if (nsquarearnum != null) {
      this.getItem().setNthisnum(nnum.sub(nsquarearnum));
    }
    else {
      this.getItem().setNthisnum(nnum);
    }
  }

}
