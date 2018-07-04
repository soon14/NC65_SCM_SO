/**
 * 
 */
package nc.impl.so.m30.sobalance.maintain;

/**
 * @author gdsjw
 * 
 */
public class SobalanceBPFactoryForSoManual extends AbstractSobalanceBPFactory {

  private static SobalanceBPFactoryForSoManual instance =
      new SobalanceBPFactoryForSoManual();

  private SobalanceBPFactoryForSoManual() {
    //
  }

  public static SobalanceBPFactoryForSoManual getInstance() {
    return SobalanceBPFactoryForSoManual.instance;
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
    return new InsertBPForSoManual();
  }

  /*
   * (non-Javadoc)
   * @see
   * nc.impl.so.m30.sobalance.maintain.AbstractSobalanceBPFactory#getUpdateBP()
   */
  @Override
  public IUpdateBP getUpdateBP() {
    return new UpdateBPForSoManual();
  }

}
