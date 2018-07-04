package nc.tc.nc.vo.so.m32.entity;
import org.testng.*;
import nc.vo.pub.IVOMeta;
import nc.vo.pub.SuperVO;
import nc.vo.pub.lang.UFBoolean;
import nc.vo.pub.lang.UFDate;
import nc.vo.pub.lang.UFDateTime;
import nc.vo.pub.lang.UFDouble;
import nc.vo.pubapp.pattern.model.meta.entity.vo.VOMetaFactory;
import java.util.ArrayList;
import java.io.IOException;
import java.lang.reflect.Method;
import java.lang.reflect.InvocationTargetException;
import java.util.Iterator;
import java.util.Map;
import java.util.List;
import java.util.HashMap;
import java.io.Serializable;
import jxl.read.biff.BiffException;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.AfterClass;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;
import com.yonyou.uat.util.ExcelDataProvider;
import com.yonyou.uat.util.DBDataProvider;
import com.yonyou.uat.dbmanagement.DBManage;
import com.yonyou.uat.dbmanagement.QueryInfoVO;
import nc.vo.so.m32.entity.SaleInvoiceHVO;
import nc.bs.framework.common.NCLocator;
import com.yonyou.uat.framework.BaseTestCase;
import nc.vo.pub.lang.UFDateTime;
import nc.vo.pub.lang.UFDateTime;
import nc.vo.pub.IVOMeta;
public class SaleInvoiceHVOTest extends BaseTestCase {
  SaleInvoiceHVO saleInvoiceHVO=null;
  DBManage dbManage=null;
  
  @BeforeClass 
  public void BeforeClass(){
    saleInvoiceHVO=NCLocator.getInstance().lookup(SaleInvoiceHVO.class);
  }
  
  @AfterClass 
  public void AfterClass(){
  }
  
  @BeforeMethod 
  public void BeforeMethod(){
    List<String> tableList=new ArrayList<String>();
    tableList.add("pub_wfexptlog");
    dbManage=new DBManage();
    dbManage.setTableList(tableList);
    dbManage.tableExport();
  }
  
  @AfterMethod 
  public void AfterMethod(){
    dbManage.tableRollBack();
  }
  
  @Test(description="",dependsOnMethods={},groups="",timeOut=100000,dataProvider="dp") 
  public void getTs(  Map<String,ArrayList<String>> dp){
    
    //Construct method parameters
    
    //Invoke tested method
    UFDateTime retObj=null;
    retObj=saleInvoiceHVO.getTs();
    
    //Verify result is ok
    
    //Verify Object1 == Object2
    Assert.assertNotNull(retObj);
    Assert.assertNotNull(retObj.getBeginDate());
    Assert.assertNotNull(retObj.getDate());
    Assert.assertNotNull(retObj.getDay());
    Assert.assertEquals(retObj.getDay(),0);
    Assert.assertNotNull(retObj.getDaysMonth());
    Assert.assertEquals(retObj.getDaysMonth(),0);
    Assert.assertNotNull(retObj.getEnMonth());
    Assert.assertEquals(retObj.getEnMonth(),"expectValue");
    Assert.assertNotNull(retObj.getEnWeek());
    Assert.assertEquals(retObj.getEnWeek(),"expectValue");
    Assert.assertNotNull(retObj.getEndDate());
    Assert.assertNotNull(retObj.getHour());
    Assert.assertEquals(retObj.getHour(),0);
    Assert.assertNotNull(retObj.getLocalDay());
    Assert.assertEquals(retObj.getLocalDay(),0);
    Assert.assertNotNull(retObj.getLocalHour());
    Assert.assertEquals(retObj.getLocalHour(),0);
    Assert.assertNotNull(retObj.getLocalMinute());
    Assert.assertEquals(retObj.getLocalMinute(),0);
    Assert.assertNotNull(retObj.getLocalMonth());
    Assert.assertEquals(retObj.getLocalMonth(),0);
    Assert.assertNotNull(retObj.getLocalYear());
    Assert.assertEquals(retObj.getLocalYear(),0);
    Assert.assertNotNull(retObj.getMillis());
    Assert.assertEquals(retObj.getMillis(),0);
    Assert.assertNotNull(retObj.getMinute());
    Assert.assertEquals(retObj.getMinute(),0);
    Assert.assertNotNull(retObj.getMonth());
    Assert.assertEquals(retObj.getMonth(),0);
    Assert.assertNotNull(retObj.getSecond());
    Assert.assertEquals(retObj.getSecond(),0);
    Assert.assertNotNull(retObj.getStrDay());
    Assert.assertEquals(retObj.getStrDay(),"expectValue");
    Assert.assertNotNull(retObj.getStrMonth());
    Assert.assertEquals(retObj.getStrMonth(),"expectValue");
    Assert.assertNotNull(retObj.getTime());
    Assert.assertEquals(retObj.getTime(),"expectValue");
    Assert.assertNotNull(retObj.getUFTime());
    Assert.assertNotNull(retObj.getWeek());
    Assert.assertEquals(retObj.getWeek(),0);
    Assert.assertNotNull(retObj.getWeekOfYear());
    Assert.assertEquals(retObj.getWeekOfYear(),0);
    Assert.assertNotNull(retObj.getYear());
    Assert.assertEquals(retObj.getYear(),0);
    
    //Verify Return or middle Object == expect Object(from object file)
    Object expectedObj=super.getExpectResultObject("caseName");
    if (expectedObj != null) {
      Assert.assertEquals(retObj,expectedObj);
    }
 else {
      super.saveResultObject((Serializable)retObj,"caseName");
    }
    
    //Verify DB result is ok
    QueryInfoVO queryInfoVerify=new QueryInfoVO();
    queryInfoVerify.setDatasource("datasourceName");
    queryInfoVerify.setTableName("tableName");
    queryInfoVerify.setCondition("where condition");
    List<Object> actualObjects=super.getDBObjectClass(Object.class,queryInfoVerify);
    Object actualObject=(Object)actualObjects.get(0);
    Assert.assertEquals("actualObject.getxxx()",dp.get("colName").get(0));
    
    //Verify whether have exception information in log 
    super.verifyLog("Error key word");
  }
  
  @Test(description="",dependsOnMethods={},groups="",timeOut=100000,dataProvider="dp") 
  public void setTs(  Map<String,ArrayList<String>> dp){
    
    //Construct method parameters
    UFDateTime ts=(UFDateTime)super.getSpringObj("ufdatetime");
    
    //Invoke tested method
    saleInvoiceHVO.setTs(ts);
    
    //Verify result is ok
    
    //Verify Object1 == Object2
    Assert.assertEquals("actual","expected");
    Assert.assertNotNull("actual");
    
    //Verify DB result is ok
    QueryInfoVO queryInfoVerify=new QueryInfoVO();
    queryInfoVerify.setDatasource("datasourceName");
    queryInfoVerify.setTableName("tableName");
    queryInfoVerify.setCondition("where condition");
    List<Object> actualObjects=super.getDBObjectClass(Object.class,queryInfoVerify);
    Object actualObject=(Object)actualObjects.get(0);
    Assert.assertEquals("actualObject.getxxx()",dp.get("colName").get(0));
    
    //Verify whether have exception information in log 
    super.verifyLog("Error key word");
  }
  
  @Test(description="",dependsOnMethods={},groups="",timeOut=100000,dataProvider="dp") 
  public void getMetaData(  Map<String,ArrayList<String>> dp){
    
    //Construct method parameters
    
    //Invoke tested method
    IVOMeta retObj=null;
    retObj=saleInvoiceHVO.getMetaData();
    
    //Verify result is ok
    
    //Verify Object1 == Object2
    Assert.assertNotNull(retObj);
    Assert.assertNotNull(retObj.getAttributes());
    Assert.assertNotNull(retObj.getBusinessAttribute());
    Assert.assertEquals(retObj.getBusinessAttribute().size(),0);
    Assert.assertNotNull(retObj.getEntityName());
    Assert.assertEquals(retObj.getEntityName(),"expectValue");
    Assert.assertNotNull(retObj.getLabel());
    Assert.assertEquals(retObj.getLabel(),"expectValue");
    Assert.assertNotNull(retObj.getPrimaryAttribute());
    Assert.assertNotNull(retObj.getStatisticInfo());
    
    //Verify DB result is ok
    QueryInfoVO queryInfoVerify=new QueryInfoVO();
    queryInfoVerify.setDatasource("datasourceName");
    queryInfoVerify.setTableName("tableName");
    queryInfoVerify.setCondition("where condition");
    List<Object> actualObjects=super.getDBObjectClass(Object.class,queryInfoVerify);
    Object actualObject=(Object)actualObjects.get(0);
    Assert.assertEquals("actualObject.getxxx()",dp.get("colName").get(0));
    
    //Verify whether have exception information in log 
    super.verifyLog("Error key word");
  }
}
