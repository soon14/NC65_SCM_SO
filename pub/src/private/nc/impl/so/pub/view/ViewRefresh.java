package nc.impl.so.pub.view;

import java.util.HashMap;
import java.util.Map;

import nc.impl.pubapp.pattern.data.view.ViewQuery;
import nc.vo.pubapp.pattern.log.TimeLog;
import nc.vo.pubapp.pattern.model.entity.view.IDataView;

public class ViewRefresh<E extends IDataView> {

  /**
   * 返回当前数据库中的view
   * 
   * @param views
   * @return <E的主键,E>
   */

  public Map<String, E> getViewsMapFromDB(E[] views) {
    E[] originViews = this.getViewsFromDB(views);
    int length = views.length;
    Map<String, E> map = new HashMap<String, E>();
    for (int i = 0; i < length; i++) {
      map.put(originViews[i].getPrimaryKey(), originViews[i]);
    }
    return map;
  }

  /**
   * 返回当前数据库中的view
   * 
   * @param views
   * @return
   */

  public E[] getViewsVOFromDB(E[] views) {
    return this.getViewsFromDB(views);
  }

  @SuppressWarnings({
    "unchecked", "rawtypes"
  })
  private E[] getViewsFromDB(E[] views) {
    TimeLog.logStart();
    String[] ids = new String[views.length];
    int length = views.length;
    for (int i = 0; i < length; i++) {
      ids[i] = views[i].getPrimaryKey();
    }
    TimeLog.info("获取视图主健"); /*-=notranslate=-*/

    TimeLog.logStart();
    ViewQuery<E> query = new ViewQuery(views[0].getClass());
    E[] originViews = query.query(ids);
    TimeLog.info("查询原始视图VO"); /*-=notranslate=-*/

    return originViews;
  }

}
