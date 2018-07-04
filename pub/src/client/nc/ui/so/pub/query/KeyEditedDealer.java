package nc.ui.so.pub.query;

import java.util.HashSet;
import java.util.Set;

import nc.ui.pubapp.uif2app.query2.totalvo.IQueryConditionVODealer;
import nc.vo.pub.lang.UFBoolean;
import nc.vo.pub.query.QueryConditionVO;

/**
 * 查询模板字段编辑性
 * 调一下 setEditKey 就可以
 * 
 * @since 6.0
 * @version 2011-12-31 上午10:26:57
 * @author 么贵敬
 */
public class KeyEditedDealer implements IQueryConditionVODealer {

  private Set<String> keyset = new HashSet<String>();

  @Override
  public QueryConditionVO[] deal(QueryConditionVO[] conds) {
    for (QueryConditionVO qcvo : conds) {
      if (this.keyset.contains(qcvo.getFieldCode())) {
        qcvo.setIfImmobility(UFBoolean.TRUE);
      }
    }
    return conds;
  }

  public void setEditKey(String key) {
    this.keyset.add(key);
  }
}
