package nc.itf.so.orderprofit;

import nc.pub.smart.metadata.Field;
import nc.pub.smart.model.descriptor.AggrDescriptor;
import nc.pub.smart.model.descriptor.AggrItem;
import nc.pub.smart.model.descriptor.GroupItem;
import nc.vo.so.report.paravo.ProfitQryInfoParaVO;

import org.apache.commons.lang.ArrayUtils;

/**
 * »ã×ÜÃèÊöÆ÷
 * 
 * @since 6.0
 * @version 2011-3-21 ÉÏÎç09:48:23
 * @author Ã´¹ó¾´
 */
public class ProfitAggrDescriptor<T extends ProfitQryInfoParaVO> extends
    AggrDescriptor {

  private static final long serialVersionUID = 4712047322557475651L;

  public ProfitAggrDescriptor(T para) {
    String[] tolkeys = para.getTotalKeys();

    for (int i = 0; i < tolkeys.length; ++i) {
      String key = tolkeys[i];
      this.setAggrFields((AggrItem[]) ArrayUtils.add(this.getAggrFields(),
          new AggrItem(key, 0, key)));
    }

    String[] gpkeys = para.getDisplayKeys().toArray(new String[0]);
    for (int i = 0; i < gpkeys.length; ++i) {
      String key = gpkeys[i];
      Field field = new Field();
      field.setFldname(key);
      this.setGroupFields((GroupItem[]) ArrayUtils.add(this.getGroupFields(),
          new GroupItem(field)));
    }
  }

}
