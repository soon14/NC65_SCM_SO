/**
 * 
 */
package nc.impl.so.m30.sobalance.maintain;

/**
 * @author gdsjw
 * 
 */
public class SobalanceBPFactoryForAcc extends AbstractSobalanceBPFactory {

  private static SobalanceBPFactoryForAcc instance =
      new SobalanceBPFactoryForAcc();

  private SobalanceBPFactoryForAcc() {
    //
  }

  public static SobalanceBPFactoryForAcc getInstance() {
    return SobalanceBPFactoryForAcc.instance;
  }

  /*
   * (non-Javadoc)
   * @see
   * nc.impl.so.m30.sobalance.maintain.AbstractSobalanceBPFactory#getDeleteBP()
   */
  @Override
  public IDeleteBP getDeleteBP() {
    return null;
  }

  /*
   * (non-Javadoc)
   * @see
   * nc.impl.so.m30.sobalance.maintain.AbstractSobalanceBPFactory#getInsertBP()
   */
  @Override
  public IInsertBP getInsertBP() {
    return new InsertBPForAccBalance();
  }

  /*
   * (non-Javadoc)
   * @see
   * nc.impl.so.m30.sobalance.maintain.AbstractSobalanceBPFactory#getUpdateBP()
   */
  @Override
  public IUpdateBP getUpdateBP() {
    return new UpdateBPForAccBalance();
  }

}
