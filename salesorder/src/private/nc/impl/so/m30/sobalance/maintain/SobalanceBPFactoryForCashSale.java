/**
 * 
 */
package nc.impl.so.m30.sobalance.maintain;

/**
 * @author gdsjw
 * 
 */
public class SobalanceBPFactoryForCashSale extends AbstractSobalanceBPFactory {

  private static SobalanceBPFactoryForCashSale instance =
      new SobalanceBPFactoryForCashSale();

  private SobalanceBPFactoryForCashSale() {
    //
  }

  public static SobalanceBPFactoryForCashSale getInstance() {

    return SobalanceBPFactoryForCashSale.instance;
  }

  /*
   * (non-Javadoc)
   * @see
   * nc.impl.so.m30.sobalance.maintain.AbstractSobalanceBPFactory#getDeleteBP()
   */
  @Override
  public IDeleteBP getDeleteBP() {
    return new DeleteBPForCashSale();
  }

  /*
   * (non-Javadoc)
   * @see
   * nc.impl.so.m30.sobalance.maintain.AbstractSobalanceBPFactory#getInsertBP()
   */
  @Override
  public IInsertBP getInsertBP() {
    return new InsertBPForCashSale();
  }

  /*
   * (non-Javadoc)
   * @see
   * nc.impl.so.m30.sobalance.maintain.AbstractSobalanceBPFactory#getUpdateBP()
   */
  @Override
  public IUpdateBP getUpdateBP() {
    return null;
  }

}
