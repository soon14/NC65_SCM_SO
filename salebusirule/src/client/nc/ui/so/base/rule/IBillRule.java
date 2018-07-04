package nc.ui.so.base.rule;

import nc.ui.uif2.model.AbstractAppModel;
import nc.vo.so.pub.keyvalue.IKeyValue;

public interface IBillRule {

  void process(IKeyValue keyvalue, AbstractAppModel model);

}
