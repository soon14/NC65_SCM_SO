package nc.vo.so.m33.m4c.entity;

import nc.md.model.impl.MDEnum;
import nc.vo.pub.lang.UFDouble;
import nc.vo.pubapp.pattern.exception.ExceptionUtils;
import nc.vo.pubapp.pattern.model.entity.view.AbstractDataView;
import nc.vo.pubapp.pattern.model.meta.entity.view.DataViewMetaFactory;
import nc.vo.pubapp.pattern.model.meta.entity.view.IDataViewMeta;
import nc.vo.pubapp.pattern.pub.MathTool;
import nc.vo.pubapp.pattern.pub.PubAppTool;
import nc.vo.so.m33.enumeration.SquareType;

public class SquareOutViewVO extends AbstractDataView {

  private static final long serialVersionUID = -680949127956266342L;

  /**
   * @return 转换为聚合VO
   */
  public SquareOutVO changeToSquareOutVO() {
    SquareOutVO bill = new SquareOutVO();
    bill.setParent(this.getHead());
    SquareOutBVO[] items = new SquareOutBVO[] {
      this.getItem()
    };
    bill.setChildrenVO(items);
    return bill;
  }

  @Override
  public Object getAttributeValue(String name) {
    Object value = super.getAttributeValue(name);
    if (null == value) {
      value = this.getHead().getAttributeValue(name);
      if (null == value) {
        value = this.getItem().getAttributeValue(name);
      }
    }
    return value;
  }

  public SquareOutHVO getHead() {
    return (SquareOutHVO) this.getVO(SquareOutHVO.class);
  }

  public SquareOutBVO getItem() {
    return (SquareOutBVO) this.getVO(SquareOutBVO.class);
  }

  @Override
  public IDataViewMeta getMetaData() {
    return DataViewMetaFactory.getInstance().getBillViewMeta(SquareOutVO.class);
  }

  public boolean ifCanCancelET() {
    UFDouble etnum = MathTool.nvl(this.getItem().getNsquareestnum());
    String csalesquaredid = this.getItem().getCsalesquaredid();
    boolean isEnable = false;
    if (!PubAppTool.isNull(csalesquaredid) && !MathTool.isZero(etnum)) {
      isEnable = true;
    }
    return isEnable;
  }

  /**
   * 方法功能描述：是否可以取消出库对冲
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
  public boolean ifCanCancelOutRush() {
    UFDouble nrushnum = this.getItem().getNrushnum();
    boolean isEnable = false;
    if (nrushnum != null && nrushnum.compareTo(UFDouble.ZERO_DBL) != 0) {
      isEnable = true;
    }
    return isEnable;
  }

  public boolean ifCanCancelREG() {
    UFDouble regnum = MathTool.nvl(this.getItem().getNsquareregnum());
    String csalesquaredid = this.getItem().getCsalesquaredid();
    boolean isEnable = false;
    if (!PubAppTool.isNull(csalesquaredid) && !MathTool.isZero(regnum)) {
      isEnable = true;
    }
    return isEnable;
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
    UFDouble nsquareianum = MathTool.nvl(this.getItem().getNsquareianum());
    UFDouble nsquarearnum = MathTool.nvl(this.getItem().getNsquarearnum());
    String csalesquaredid = this.getItem().getCsalesquaredid();
    boolean isEnable = false;
    if (!PubAppTool.isNull(csalesquaredid)
        && (!MathTool.isZero(nsquareianum) || !MathTool.isZero(nsquarearnum))) {
      isEnable = true;
    }
    return isEnable;
  }

  public boolean ifCanET() {
    UFDouble etnum = MathTool.nvl(this.getItem().getNsquareestnum());
    String csalesquaredid = this.getItem().getCsalesquaredid();
    boolean isEnable = false;
    if (PubAppTool.isNull(csalesquaredid) && MathTool.isZero(etnum)) {
      isEnable = true;
    }
    return isEnable;
  }

  public boolean ifCanOutRush() {
    UFDouble nrushnum = MathTool.nvl(this.getItem().getNrushnum());
    UFDouble nnum = MathTool.nvl(this.getItem().getNnum());
    boolean isEnable = false;
    if (MathTool.greaterThan(nnum, nrushnum)) {
      isEnable = true;
    }
    return isEnable;
  }

  /**
   * 方法功能描述：是否可以发出商品
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
  public boolean ifCanREG() {
    UFDouble regnum = MathTool.nvl(this.getItem().getNsquareregnum());
    String csalesquaredid = this.getItem().getCsalesquaredid();
    boolean isEnable = false;
    if (PubAppTool.isNull(csalesquaredid) && MathTool.isZero(regnum)) {
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
    UFDouble thisnum = MathTool.nvl(this.getItem().getNthisnum());
    String csalesquaredid = this.getItem().getCsalesquaredid();
    boolean isEnable = false;
    if (PubAppTool.isNull(csalesquaredid) && !MathTool.isZero(thisnum)) {
      isEnable = true;
    }
    return isEnable;
  }

  public void setHead(SquareOutHVO head) {
    this.setVO(head);
  }

  public void setItem(SquareOutBVO item) {
    this.setVO(item);
  }

  public void setNthisnumForET() {
    UFDouble ndownarnum = this.getItem().getNdownarnum();
    UFDouble noutrushnum = this.getItem().getNrushnum();
    UFDouble nnum = this.getItem().getNnum();
    this.getItem().setNthisnum(
        nnum.sub(MathTool.nvl(ndownarnum)).sub(MathTool.nvl(noutrushnum)));
  }

  /**
   * 方法功能描述：设置本次收入成本结算数量、累计结算数量
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
  public void setNthisnumForManualSquare() {
    UFDouble nsquareianum = MathTool.nvl(this.getItem().getNsquareianum());
    UFDouble nsquarearnum = MathTool.nvl(this.getItem().getNsquarearnum());
    UFDouble nnum = this.getItem().getNnum();
    boolean manualar = !this.getHead().getBautosquareincome().booleanValue();
    boolean manualia = !this.getHead().getBautosquarecost().booleanValue();
    SquareType iaKey =
        MDEnum.valueOf(SquareType.class, this.getItem().getFpreiatype());
    SquareType arKey =
        MDEnum.valueOf(SquareType.class, this.getItem().getFpreartype());

    manualar =
        manualar
            && SquareType.SQUARETYPE_AR.getIntValue() == arKey.getIntValue();

    manualia =
        manualia
            && SquareType.SQUARETYPE_IA.getIntValue() == iaKey.getIntValue();
    if (manualar && manualia) {
      UFDouble maxsquarenum = nsquareianum;
      if (MathTool.absCompareTo(maxsquarenum, nsquarearnum) < 0) {
        maxsquarenum = nsquarearnum;
      }
      this.getItem().setNthisnum(nnum.sub(maxsquarenum));
      this.getItem().setNtotalsquarenum(maxsquarenum);
    }
    else if (manualar) {
      this.getItem().setNthisnum(nnum.sub(nsquarearnum));
      this.getItem().setNtotalsquarenum(nsquarearnum);
    }
    else if (manualia) {
      this.getItem().setNthisnum(nnum.sub(nsquareianum));
      this.getItem().setNtotalsquarenum(nsquareianum);
    }
    else {
      ExceptionUtils.unSupported();
    }
  }

  public void setNthisnumForREG() {
    UFDouble ndownianum = this.getItem().getNdownianum();
    UFDouble noutrushnum = this.getItem().getNrushnum();
    UFDouble nnum = this.getItem().getNnum();
    this.getItem().setNthisnum(
        nnum.sub(MathTool.nvl(ndownianum)).sub(MathTool.nvl(noutrushnum)));
  }

}
