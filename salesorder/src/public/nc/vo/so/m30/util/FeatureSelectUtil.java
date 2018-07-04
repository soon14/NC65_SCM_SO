package nc.vo.so.m30.util;

import java.util.HashMap;
import java.util.Map;

import nc.vo.bd.feature.ffile.entity.AggFFileVO;
import nc.vo.pub.lang.UFDouble;
import nc.vo.pubapp.pattern.pub.MathTool;
import nc.vo.pubapp.pflow.PfUserObject;
import nc.vo.so.m30.entity.SaleOrderBVO;
import nc.vo.so.m30.entity.SaleOrderUserObject;
import nc.vo.so.m30.entity.SaleOrderVO;
import nc.vo.so.m30.revise.entity.SaleOrderHistoryBVO;
import nc.vo.so.m30.rule.EditableAndRewiteItems;
import nc.vo.so.pub.SOConstant;
import nc.vo.so.pub.keyvalue.IKeyValue;

/**
 * 销售特征选配工具类
 * @author zhangby5
 *
 */
public class FeatureSelectUtil {

  /**
   * 取表体所有行的暂存特征选配VO
   * @param keyValue 取值工具
   * @return <行号，值>
   */
  public static Map<String, AggFFileVO> getAllRowAggFFileVO(IKeyValue keyValue) {
    int count = keyValue.getBodyCount();
    Map<String, AggFFileVO> aggffilevomap = new HashMap<>();
    for (int index = 0; index < count; index++) {
      if (null == keyValue.getBodyValue(index, SOConstant.AGGFFILEVO)) {
        continue;
      }
      aggffilevomap.put(
          keyValue.getBodyStringValue(index, SaleOrderBVO.CROWNO),
          (AggFFileVO) keyValue.getBodyValue(index, SOConstant.AGGFFILEVO));
    }
    return aggffilevomap;
  }
  
  /**
   * 清空表体所有指定字段的值
   * @param keyValue 取值工具
   */
  public static void clearAllRowValue(IKeyValue keyValue,String sItemKey) {
    setAllRowsValue(keyValue, sItemKey, null);
  }
  
  /**
   * 清空指定行的指定字段的值
   * @param keyValue 取值工具
   * @param rows 指定行
   * @param sItemKey 指定字段
   */
  public static void clearRowsValue(IKeyValue keyValue,int[] rows,String sItemKey){
    setRowsValue(keyValue, rows, sItemKey, null);
  }
  
  /**
   * 设置所有行的指定字段的值
   * @param keyValue
   * @param sItemKey
   * @param value
   */
  public static void setAllRowsValue(IKeyValue keyValue,String sItemKey,Object value){
    int count = keyValue.getBodyCount();
    for (int index = 0; index < count; index++) {
      keyValue.setBodyValue(index, sItemKey, null);
    }
  }
  
  /**
   * 设置指定行的指定字段的值
   * @param keyValue 取值工具
   * @param rows 指定行
   * @param sItemKey 指定字段
   * @param value 要赋的值
   */
  public static void setRowsValue(IKeyValue keyValue,int[] rows,String sItemKey,Object value){
    int length = rows.length; 
    for (int index = 0; index < length; index++) {
      keyValue.setBodyValue(rows[index], sItemKey, value);
    }
  }
  
  /**
   * 判断是否生成下游
   * 下游只有发货单，则允许做特征选配，制造的单据(累计安排生产订单数量)不需要判断
   * 
   * @param keyValue
   * @param row
   * @return
   */
  public static boolean isOut(IKeyValue keyValue,int row) {
    UFDouble value = null;
    int reviseForOutlength = EditableAndRewiteItems.TOTALNUMKEY.length;
    for (int i = 0; i < reviseForOutlength; i++) {
      String key = EditableAndRewiteItems.TOTALNUMKEY[i];
      value = keyValue.getBodyUFDoubleValue(row, key);
      if (MathTool.absCompareTo(value, UFDouble.ZERO_DBL) > 0) {
        if(key.equalsIgnoreCase(SaleOrderHistoryBVO.NARRANGEMONUM)){
          continue;
        }
        if(key.equalsIgnoreCase(SaleOrderHistoryBVO.NTOTALSENDNUM)){
          continue;
        }
        // 已经生成下游单据
        return true;
      }
    }
    return false;
  }
  
  /**
   * 判断是否生成下游
   * 下游只有发货单，则允许做特征选配，制造的单据(累计安排生产订单数量)不需要判断
   * 
   * @param keyValue
   * @param row
   * @return
   */
  public static boolean isOut(SaleOrderBVO bvo) {
    UFDouble value = null;
    int reviseForOutlength = EditableAndRewiteItems.TOTALNUMKEY.length;
    for (int i = 0; i < reviseForOutlength; i++) {
      String key = EditableAndRewiteItems.TOTALNUMKEY[i];
      value = (UFDouble) bvo.getAttributeValue(key);
      if (MathTool.absCompareTo(value, UFDouble.ZERO_DBL) > 0) {
        if(key.equalsIgnoreCase(SaleOrderHistoryBVO.NARRANGEMONUM)){
          continue;
        }
        if(key.equalsIgnoreCase(SaleOrderHistoryBVO.NTOTALSENDNUM)){
          continue;
        }
        // 已经生成下游单据
        return true;
      }
    }
    return false;
  }
  
  /**
   * 特征选配的处理，后台获取前台传过来的特征码选配暂存VO
   * 
   * @param inCurVOs
   * @param userObj
   */
  public static void fillAggffileVO(SaleOrderVO[] inCurVOs, PfUserObject userObj) {
    if (userObj == null) {
      return;
    }
    Object saleUserObj = userObj.getUserObject();
    if (saleUserObj == null) {
      return;
    }
    Map<String, AggFFileVO> aggffilemapvo =
        ((SaleOrderUserObject) saleUserObj).getAggffilevomap();
    if (aggffilemapvo == null || aggffilemapvo.size() == 0) {
      return;
    }
    SaleOrderBVO[] bvos = inCurVOs[0].getChildrenVO();
    for (SaleOrderBVO bvo : bvos) {
      AggFFileVO aggffilevo = aggffilemapvo.get(bvo.getCrowno());
      if (aggffilevo == null) {
        continue;
      }
      bvo.setAggffilevo(aggffilevo);
    }
  }
  
}
