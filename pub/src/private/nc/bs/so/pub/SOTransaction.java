package nc.bs.so.pub;


/**
 * 销售模块强制起事务的方法
 * 
 * @since 6.0
 * @version 2011-3-30 下午06:47:22
 * @author 么贵敬
 */
public class SOTransaction {
  public SOTransaction() {
    super();
  }

  /**
   * 强制开始事务。当此方法的调用者没有启动事务的时候，此方法将会强制启动一个事务。
   * 当此方法的调用者启动了事务，那么此方法将继承此事务。
   * 用途：当使用临时表查询的时候，由于调用此查询的人误以为查询不用启用事务，则临时
   * 表会在没有事务状态的情况下被插入数据。此时，数据不会被真正插入到临时表中，导
   * 致临时表与实际表关联时不会查询到任何数据。
   * 
   * @param classname
   * @param methodname
   * @param ParameterTypes
   * @param ParameterValues
   * @return
   */
  public Object mandatoryTransaction(String classname, String methodname,
      Class<?>[] ParameterTypes, Object[] ParameterValues) {
    // ServcallVO[] scd = new ServcallVO[1];
    // scd[0] = new ServcallVO();
    // scd[0].setBeanName(classname);
    // scd[0].setMethodName(methodname);
    // scd[0].setParameterTypes(ParameterTypes);
    // scd[0].setParameter(ParameterValues);
    // Object[] objects = null;
    // try {
    // objects = LocalCallService.callEJBService("so", scd);
    // }
    // catch (Exception ex) {
    // ExceptionUtils.wrappException(ex);
    // }
    // Object ret = null;
    // if (objects != null && objects.length > 0) {
    // ret = objects[0];
    // }
    // SaleOrderProfitUtil util = new SaleOrderProfitUtil();
    // Method method =
    // this.getOneBatchCalcMethod(util, methodname, ParameterTypes);
    // Object ret =
    // CMTProxySrv.delegate_RequiresNew(util, method, ParameterValues);
    // return ret;
    return null;
  }

  // private Method getOneBatchCalcMethod(Object target, String methodname,
  // Class<?>[] ParameterTypes) {
  // // Class<?>[] parameterTypes = new Class<?>[2];
  // // parameterTypes[0] = String[].class;
  // // parameterTypes[1] = Map.class;
  // Method method = null;
  // try {
  // method = target.getClass().getMethod(methodname, ParameterTypes);
  // }
  // catch (Exception e) {
  // ExceptionUtils.wrappException(e);
  // }
  // return method;
  // }

  /**
   * 开启一个新的独立事务。当独立事务中的方法结束时，此方法执行过程中涉及到的事务将
   * 会立即提交或者回滚。其事务的提交、回滚不会导致父事务的提交、回滚。
   * 
   * @param classname
   * @param methodname
   * @param ParameterTypes
   * @param ParameterValues
   * @return
   */
  // public Object newTransaction(String classname, String methodname,
  // Class<?>[] ParameterTypes, Object[] ParameterValues) {
  // ServcallVO[] scd = new ServcallVO[1];
  // scd[0] = new ServcallVO();
  // scd[0].setBeanName(classname);
  // scd[0].setMethodName(methodname);
  // scd[0].setParameterTypes(ParameterTypes);
  // scd[0].setParameter(ParameterValues);
  //
  // IScmEJBService bo =
  // (IScmEJBService) NCLocator.getInstance().lookup(
  // IScmEJBService.class.getName());
  //
  // Object[] objects = null;
  // try {
  // objects = bo.callEJBService_RequiresNew("so", scd);
  // }
  // catch (BusinessException ex) {
  // ExceptionUtils.wrappException(ex);
  // }
  // Object ret = null;
  // if (objects != null && objects.length > 0) {
  // ret = objects[0];
  // }
  // return ret;
  //
  // }

}
