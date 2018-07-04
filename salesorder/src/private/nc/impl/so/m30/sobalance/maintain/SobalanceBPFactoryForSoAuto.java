/**
 * 
 */
package nc.impl.so.m30.sobalance.maintain;

/**
 * @author gdsjw
 * 
 */
public class SobalanceBPFactoryForSoAuto extends AbstractSobalanceBPFactory {

  private static SobalanceBPFactoryForSoAuto instance =
      new SobalanceBPFactoryForSoAuto();

  private SobalanceBPFactoryForSoAuto() {
    //
  }

  public static SobalanceBPFactoryForSoAuto getInstance() {

    return SobalanceBPFactoryForSoAuto.instance;
  }

  /*
   * (non-Javadoc)
   * @see
   * nc.impl.so.m30.sobalance.maintain.AbstractSobalanceBPFactory#getDeleteBP()
   */
  @Override
  public IDeleteBP getDeleteBP() {
    return new DeleteBPForSoAuto();
  }

  /*
   * (non-Javadoc)
   * @see
   * nc.impl.so.m30.sobalance.maintain.AbstractSobalanceBPFactory#getInsertBP()
   */
  @Override
  public IInsertBP getInsertBP() {
    return new InsertBPForSoAuto();
  }

  /*
   * (non-Javadoc)
   * @see
   * nc.impl.so.m30.sobalance.maintain.AbstractSobalanceBPFactory#getUpdateBP()
   */
  @Override
  public IUpdateBP getUpdateBP() {
    return new UpdateBPForSoAuto();
  }

}
