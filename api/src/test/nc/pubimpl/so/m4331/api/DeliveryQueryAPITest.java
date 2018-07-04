package nc.pubimpl.so.m4331.api;

import nc.pubimpl.so.pub.api.SOTestCase;

import org.junit.Test;

public class DeliveryQueryAPITest extends SOTestCase {

  @Test
  public void test() {
    DeliveryQuery query = new DeliveryQuery();
    query.queryVOByIDs();
    query.queryViewVOByBIDs();
    query.queryVOByScheme();
    query.queryViewVOByScheme();
    query.queryViewVOBySourceBIDs();
  }

  @Override
  protected String getPwd() {
    return super.getPwd();
  }

  @Override
  protected String getUser() {
    return super.getUser();
  }

  @Override
  protected void setUp() throws Exception {
    super.setUp();
  }

}
