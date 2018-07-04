package nc.impl.so.m30trantype.tool;

import java.util.ArrayList;
import java.util.List;

import nc.bs.dao.BaseDAO;
import nc.bs.dao.DAOException;
import nc.bs.ml.NCLangResOnserver;
import nc.impl.pubapp.pattern.data.vo.VOQuery;
import nc.jdbc.framework.processor.ColumnListProcessor;
import nc.jdbc.framework.processor.ResultSetProcessor;
import nc.vo.pub.IAttributeMeta;
import nc.vo.pub.ISuperVO;
import nc.vo.pub.ITableMeta;
import nc.vo.pub.IVOMeta;
import nc.vo.pub.IVOMetaStatisticInfo;
import nc.vo.pubapp.pattern.exception.ExceptionUtils;
import nc.vo.pubapp.pattern.model.tool.MetaTool;
import nc.vo.pubapp.pattern.pub.SqlBuilder;
import nc.vo.scmpub.util.StringUtil;
import nc.vo.so.m30trantype.enumeration.SaleMode;
import nc.vo.so.pub.SOItemKey;
import nc.vo.so.pub.util.ListUtil;

/**
 * @description
 *  校验交易类型指定属性是否可修改
 * @scene
 *  交易类型修改保存前规则
 * @param
 *  voClass 要查询的单据实体
 *  tranTypeVOClass 交易类型vo 如销售订单交易类型vo
 *  key 要查询的字段 如销售模式fsalemode
 * 
 * @since 6.36
 * @version 2015-1-14 下午6:26:20
 * @author wangshu6
 */
public class CheckKeyEditableTool<E extends ISuperVO, T extends ISuperVO> {

  /**
   * 要查询的单据实体Class
   */
  private Class<E> voClass;

  /**
   * 
   */
  private Class<T> tranTypeVOClass;

  /**
   * 要查询的字段
   */
  private String key;

  public CheckKeyEditableTool(Class<E> voClass, Class<T> tranTypeVOClass,
      String key) {
    this.voClass = voClass;
    this.tranTypeVOClass = tranTypeVOClass;
    this.key = key;
  }

  /**
   * 校验指定字段是否可被修改
   * 
   * @param updateTransTypeVOs 修改后的交易类型vo
   * @param originVOs 原交易类型vo
   */
  public void checkKeySuccessModify(T[] updateTransTypeVOs, T[] originVOs) {
    // 1. 是否有单据引用该交易类型
    boolean isReferenced = isTranTypeReferenced(originVOs);
    // 2. 交易类型被引用后能否修改
    if (isReferenced) {
      checkSuccessModify(updateTransTypeVOs, originVOs);
    }
  }

  /**
   * 是否有单据引用该交易类型
   * 
   * @param originVOs 原交易类型vo
   * @return true 已被引用 false 未被引用
   */
  private boolean isTranTypeReferenced(T[] originVOs) {
    // 1.查询引用该交易类型的单据vos
    int flag = queryBillVOsLengthByTranTypeVOs(originVOs);
    // 2.根据查询出的引用
    if (flag == 0) {
      return false;
    }
    return true;
  }

  /**
   * 交易类型被引用后能否修改
   * 
   * @param updateTransTypeVOs 修改后的交易类型vo
   * @param originVOs 原交易类型vo
   */
  private void checkSuccessModify(T[] updateTransTypeVOs, T[] originVOs) {
    for (int i = 0; i < originVOs.length; i++) {
      // 1. 取到需要判断的key属性的原值和修改值
      Object origValue = originVOs[i].getAttributeValue(this.key);
      Object updateValue = updateTransTypeVOs[i].getAttributeValue(key);
      // 2. 进行是否可修改的判断
      if (StringUtil.isEmptyTrimSpace(origValue)
          || StringUtil.isEmptyTrimSpace(updateValue)) {
        continue;
      }
      // 没有变化不判断
      if (origValue.equals(updateValue)) {
        continue;
      }
      // 检查交易类型被引用后是否支持修改key属性
      this.checkEditableForKey(origValue, updateValue);
    }
  }

  /**
   * 查询是否有单据引用了该交易类型
   * 
   * @param originVOs 原交易类型vo
   * @return 如果有，则返回引用的单据的个数，没有则返回0
   */
  private int queryBillVOsLengthByTranTypeVOs(T[] originVOs) {
    int flag = 0;
    String[] ctrantypeidArrs = this.getCtrantypeID(originVOs);
    SqlBuilder sql = new SqlBuilder();
    ITableMeta tableName =  this.getTableName();
    sql.append(" select 1 from ");
    sql.append(tableName.toString());
    sql.append(" where dr = 0 and ");
    sql.append(SOItemKey.CTRANTYPEID, ctrantypeidArrs);
    BaseDAO dao = new BaseDAO();
    ResultSetProcessor processor = new ColumnListProcessor();
    List<Object> results  = new ArrayList<Object>();
    try {
      results = (List<Object>)dao.executeQuery(sql.toString(), processor);
    }
    catch (DAOException e) {
      ExceptionUtils.wrappException(e);
    }
    if (ListUtil.isEmpty(results) && results.size() == 0){
      return flag ;
    }
    flag = results.size();
    return flag;
  }

  /**
   * 参照VOQuery 根据voclass 得到表名
   * 
   * @return 表
   */
  private ITableMeta getTableName() {
    IVOMeta voMeta = MetaTool.getVOMeta(this.voClass);
    IVOMetaStatisticInfo statisticInfo = voMeta.getStatisticInfo();
    ITableMeta[] tables = statisticInfo.getTables();
    return tables[0];
  }

  /**
   * 根据交易类型vo获取交易类型编码
   * 
   * @param originVOs vos
   * @return 交易类型编码数组
   */
  private String[] getCtrantypeID(T[] originVOs) {
    List<String> ctrantypeidList = new ArrayList<String>();
    for (T originVO : originVOs) {
      String ctrantypeid = (String) originVO.getAttributeValue(SOItemKey.CTRANTYPEID);
      ctrantypeidList.add(ctrantypeid);
    }
    return ListUtil.toArray(ctrantypeidList);
  }

  /**
   * 按照已经预置好的范围 判断销售模式是否可修改;
   * 
   * @param origValue
   * @param updateValue
   * @return 是否可修改
   */
  private void checkEditableForKey(Object origValue, Object updateValue) {
    SaleModeFactoryStrage parseServise = new SaleModeFactoryStrage();
    // 1. 根据修改前的属性值 查询是否在可修改范围内
    ISaleModeStrategy saleMode = parseServise.creatSaleModeStrategy(origValue);
    // 为空，则说明没有可修改的范围
    if (saleMode == null) {
      ExceptionUtils.wrappBusinessException(nc.vo.ml.NCLangRes4VoTransl
          .getNCLangRes().getStrByID("4006004_0", "04006004-0011")
      /*@res "交易类型已经被单据引用，禁止修改！"*/);

    }
    if (saleMode != null) {
      // 2. 取到修改的允许范围
      SaleMode[] editableSaleModes = saleMode.getParseSaleMode();
      // 3. 判断updateValue是否在允许的范围内
      boolean isEditable =
          isEditableForSaleMode(editableSaleModes, updateValue);
      // 4. 不在修改的允许范围内 构造提示信息，将可修改的范围提示给用户
      if (!isEditable) {
        createErrMsg(editableSaleModes);
      }

    }
  }

  /**
   * 构造错误提示信息，将可修改的范围提示给用户
   * 
   * @param editableSaleModes 可修改的允许范围
   */
  private void createErrMsg(SaleMode[] editableSaleModes) {
    StringBuilder errMsg = new StringBuilder();
    List<String> listValiField = new ArrayList<String>();
    // 可修改的允许范围的每个枚举名称
    for (SaleMode editableSaleMode : editableSaleModes) {
      listValiField.add(editableSaleMode.getName());
    }

    if (listValiField.size() > 0) {
      errMsg.append(NCLangResOnserver.getInstance().getStrByID("4006011_0", "04006011-0505")/*交易类型已经被单据引用，销售模式只可以修改成：\n*/);
      for (String field : listValiField) {
        errMsg
            .append("[")
            .append(field)
            .append("]")
            .append(
                NCLangResOnserver.getInstance().getStrByID("4006011_0",
                    "04006011-0284")/* 、 */);
      }
      errMsg.deleteCharAt(errMsg.length() - 1);
    }
    if (errMsg.length() > 0) {
      ExceptionUtils.wrappBusinessException(errMsg.toString());
    }
  }

  /**
   * 根据修改前的交易模式取对应的可修改的目的交易模式，如果页面上的交易模式在范围内，可修改；
   * 
   * @param arrays
   * @param updateValue
   * @return
   */
  private boolean isEditableForSaleMode(SaleMode[] editableSaleModes,
      Object updateValue) {

    for (SaleMode editableSaleMode : editableSaleModes) {
      // 如果修改后的销售模式不在允许的范围内，则不允许修改
      if (updateValue.equals(editableSaleMode.getIntValue())) {
        return true;
      }
    }
    return false;
  }

}
