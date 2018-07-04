package nc.ui.so.m30.billui.largessapportion;

import java.util.List;

import nc.vo.so.pub.keyvalue.IKeyValue;

/**
 * 赠品价格分摊分组方式
 * 
 * @since 6.0
 * @version 2010-12-2 上午09:18:14
 * @author 苏建文
 */

public interface IApportionGroup {

  List<List<Integer>> getApportionGroupRows(IKeyValue keyValue,
      List<Integer> rowlist);

}
