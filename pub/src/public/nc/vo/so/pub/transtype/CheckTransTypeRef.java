package nc.vo.so.pub.transtype;

import nc.impl.pubapp.pattern.data.vo.VOQuery;
import nc.vo.pub.ISuperVO;
import nc.vo.pubapp.pattern.exception.ExceptionUtils;
import nc.vo.pubapp.pattern.pub.SqlBuilder;
import nc.vo.so.pub.SOItemKey;
import nc.vo.trade.checkrule.VOChecker;

/**
 * 销售管理交易类型校验工具类
 * E 交易类型所在的单据表对应vo,例如销售订单主表vo:SaleOrderHVO
 * E 交易类型所对应的扩展表vo,例如销售订单交易类型扩展表vo:M30TranTypeVO
 * 
 * @since 6.0
 * @version 2011-6-14 下午07:35:33
 * @author zhangcheng
 */
public class CheckTransTypeRef<E extends ISuperVO, T extends ISuperVO> {

  /**
   * 要查询的单据实体Class
   */
  private Class<E> voClass;

  /**
   * 要查询的交易类型扩展实体Class
   */
  private Class<T> voTransTypeClass;

  public CheckTransTypeRef(Class<E> voClass, Class<T> voTransTypeClass) {
    this.voClass = voClass;
    this.voTransTypeClass = voTransTypeClass;
  }

  /**
   * 交易类型已经被单据引用，禁止修改和删除
   * 
   * @param transTypeIDKey -- 单据交易类型ID字段名
   * @param transTypeIDValues -- 单据交易类型ID值
   */
  public void checkRef(String transTypeIDKey, String transTypeIDValues) {
    SqlBuilder condition = new SqlBuilder();
    condition.append(" and ");
    condition.append(transTypeIDKey, transTypeIDValues);
    E[] vos = new VOQuery<E>(this.voClass).query(condition.toString(), null);
    if (!VOChecker.isEmpty(vos)) {
      ExceptionUtils.wrappBusinessException(nc.vo.ml.NCLangRes4VoTransl
          .getNCLangRes().getStrByID("4006004_0", "04006004-0010")
      /*@res "交易类型已经被单据引用，禁止修改和删除！"*/);
    }
  }

  /**
   * 交易类型已经被单据引用，禁止修改keys中指明的字段
   * 
   * @param transTypeIDKey -- 单据交易类型ID字段名
   * @param transTypeIDValues -- 单据交易类型ID值
   * @param transTypeIDValues -- 待比较的字段key
   */
  public void checkRefByFields(String transTypeIDKey, String transTypeIDValues,
      T updateTransTypeVO, String[] keys) {
    SqlBuilder condition = new SqlBuilder();
    condition.append(" and ");
    condition.append(transTypeIDKey, transTypeIDValues);
    E[] vos = new VOQuery<E>(this.voClass,new String[]{
    		SOItemKey.VBILLCODE
    }).query(condition.toString(), null);
    // 已经有单据引用
    if (!VOChecker.isEmpty(vos)) {
      // 查询原始交易类型数据
      T[] transvos =
          new VOQuery<T>(this.voTransTypeClass).query(condition.toString(),
              null);
      T origTransTypeVO = transvos[0];
      for (String key : keys) {
        Object origValue = origTransTypeVO.getAttributeValue(key);
        Object updateValue = updateTransTypeVO.getAttributeValue(key);
        // 属性不为空
        if (!VOChecker.isEmpty(origValue) && !VOChecker.isEmpty(updateValue)) {
          if (!origValue.equals(updateValue)) {
            ExceptionUtils.wrappBusinessException(nc.vo.ml.NCLangRes4VoTransl
                .getNCLangRes().getStrByID("4006004_0", "04006004-0011")
            /*@res "交易类型已经被单据引用，禁止修改！"*/);
          }
        }
        // 属性都为空
        else if (VOChecker.isEmpty(origValue) && VOChecker.isEmpty(updateValue)) {
          continue;
        }
        else {
          ExceptionUtils.wrappBusinessException(nc.vo.ml.NCLangRes4VoTransl
              .getNCLangRes().getStrByID("4006004_0", "04006004-0011")
          /*@res "交易类型已经被单据引用，禁止修改！"*/);
        }
      }
    }
  }

}
