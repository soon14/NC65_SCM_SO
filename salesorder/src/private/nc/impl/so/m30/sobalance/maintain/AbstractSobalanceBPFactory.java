/**
 * 
 */
package nc.impl.so.m30.sobalance.maintain;

/**
 * @author gdsjw
 *
 */
public abstract class AbstractSobalanceBPFactory {

  public abstract IDeleteBP getDeleteBP();

  public abstract IInsertBP getInsertBP();

  public abstract IUpdateBP getUpdateBP();

}
