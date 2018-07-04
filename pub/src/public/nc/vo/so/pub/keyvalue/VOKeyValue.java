package nc.vo.so.pub.keyvalue;

import nc.vo.pub.IAttributeMeta;
import nc.vo.pub.IVOMeta;
import nc.vo.pub.VOStatus;
import nc.vo.pubapp.pattern.model.entity.bill.IBill;

/**
 * 
 * <p>
 * <b>本类主要完成以下功能：</b>
 * 
 * <ul>
 * <li>VO端适配抽象类，根据key来获取和设置对象属性值
 * </ul>
 * 
 * @param <E>
 *          <p>
 * @version 本版本号 6.0
 * @since
 * @author fengjb
 * @time 2010-8-9 下午03:52:03
 */
public class VOKeyValue<E extends IBill> extends AbstractKeyValue {
  private E vo;

  public VOKeyValue(E vo) {
    this.vo = vo;
  }

  @Override
  public int getBodyCount() {
    IVOMeta[] metas = this.vo.getMetaData().getChildren();
    return this.vo.getChildren(metas[0]) == null ? 0 : this.vo
        .getChildren(metas[0]).length;
  }

  @Override
  public Object getBodyValue(int index, String key) {
    IVOMeta[] metas = this.vo.getMetaData().getChildren();
    for (IVOMeta meta : metas) {
      IAttributeMeta ameta = meta.getAttribute(key);
      if (ameta != null) {
        return this.vo.getChildren(meta)[index].getAttributeValue(key);
      }
    }
    return null;

  }

  @Override
  public Object getHeadValue(String key) {
    return this.vo.getParent().getAttributeValue(key);
  }

  @Override
  public RowStatus getRowStatus(int index) {

    RowStatus res = RowStatus.UNCHANGED;

    IVOMeta[] metas = this.vo.getMetaData().getChildren();
    for (IVOMeta meta : metas) {
      int vostatus = this.vo.getChildren(meta)[index].getStatus();
      if (VOStatus.UNCHANGED == vostatus) {
        res = RowStatus.UNCHANGED;
      }
      else if (VOStatus.UPDATED == vostatus) {
        res = RowStatus.UPDATED;
      }
      else if (VOStatus.NEW == vostatus) {
        res = RowStatus.NEW;
      }
      else if (VOStatus.DELETED == vostatus) {
        res = RowStatus.DELETED;
      }
    }
    return res;
  }

  @Override
  public void setBodyValue(int index, String key, Object value) {
    IVOMeta[] metas = this.vo.getMetaData().getChildren();
    for (IVOMeta meta : metas) {
      IAttributeMeta ameta = meta.getAttribute(key);
      if (ameta != null) {
        this.vo.getChildren(meta)[index].setAttributeValue(key, value);
      }
      break;
    }
  }

  @Override
  public void setHeadValue(String key, Object value) {
    this.vo.getParent().setAttributeValue(key, value);

  }

}
