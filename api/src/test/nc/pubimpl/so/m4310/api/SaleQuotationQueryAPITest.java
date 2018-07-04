package nc.pubimpl.so.m4310.api;

import nc.pubimpl.so.pub.api.SOTestCase;

import org.junit.Test;

public class SaleQuotationQueryAPITest  extends SOTestCase {

  @Test
  public void test() {
    SaleQuotationQuery query = new SaleQuotationQuery();
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
