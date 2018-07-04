package nc.vo.so.pub.rule;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import nc.vo.pub.lang.UFDate;
import nc.vo.pub.lang.UFDouble;
import nc.vo.pubapp.AppContext;
import nc.vo.pubapp.pattern.pub.MathTool;
import nc.vo.pubapp.pattern.pub.PubAppTool;
import nc.vo.so.m32.entity.SaleInvoiceHVO;
import nc.vo.so.pub.SOItemKey;
import nc.vo.so.pub.keyvalue.IKeyValue;
import nc.vo.so.pub.util.SOCurrencyUtil;
import nc.vo.trade.checkrule.VOChecker;

public class SOExchangeRateRule {

  private IKeyValue keyValue;

  /**
   * 折本汇率改变的行
   */
  private List<Integer> listratechgerow;

  private Map<String, UFDouble> mapexrate = new HashMap<String, UFDouble>();

  public SOExchangeRateRule(IKeyValue keyValue) {
    this.keyValue = keyValue;
  }

  public void calcAllBodyExchangeRate() {
    BodyValueRowRule countutil = new BodyValueRowRule(this.keyValue);
    int[] rows = countutil.getMarNotNullRows();
    this.calcBodyExchangeRates(rows);
  }

  public void calcBodyExchangeRate(int row) {
    int[] rows = new int[] {
      row
    };
    this.calcBodyExchangeRates(rows);
  }

  /**
   * 获得当期折本汇率。
   * 
   * @param rows
   */
  public void calcCurrentBodyExchangeRates(int[] rows) {
    UFDate busidate = AppContext.getInstance().getBusiDate();
    this.calcBodyExchangeRate(busidate, rows);
  }

  /**
   * 获得当前单据日期对应的折本汇率。
   * 
   * @param rows
   */
  public void calcBodyExchangeRates(int[] rows) {
    UFDate dbilldate = this.keyValue.getHeadUFDateValue(SOItemKey.DBILLDATE);
    this.calcBodyExchangeRate(dbilldate, rows);
  }

  private void calcBodyExchangeRate(UFDate dbilldate, int[] rows) {
    this.listratechgerow = new ArrayList<Integer>();
    String corigcurrencyid =
        this.keyValue.getHeadStringValue(SOItemKey.CORIGCURRENCYID);

    for (int row : rows) {
      String ccurrencyorgid =
          this.keyValue.getBodyStringValue(row, SOItemKey.CCURRENCYID);
      String csettleorgid =
          this.keyValue.getBodyStringValue(row, SOItemKey.CSETTLEORGID);

      UFDouble oldchangerate =
          this.keyValue.getBodyUFDoubleValue(row,SOItemKey.NEXCHANGERATE);

      UFDouble exchangerate = null;

      if (null != dbilldate && !PubAppTool.isNull(corigcurrencyid)
          && !PubAppTool.isNull(ccurrencyorgid)
          && !PubAppTool.isNull(csettleorgid)) {
        String key = csettleorgid + ccurrencyorgid;
        if (this.mapexrate.containsKey(key)) {
          exchangerate = this.mapexrate.get(key);
        }
        else {
          exchangerate =
              SOCurrencyUtil.getInCurrencyRateByOrg(csettleorgid,
                  corigcurrencyid, ccurrencyorgid, dbilldate);
          this.mapexrate.put(key, exchangerate);
        }

      }
      // 折本汇率改变了，才设值
      if (!MathTool.equals(oldchangerate, exchangerate)) {
        listratechgerow.add(row);
      }
      this.keyValue.setBodyValue(row, SOItemKey.NEXCHANGERATE, exchangerate);
    }
  }

  /**
   * 折本汇率改变的行
   * 
   * @return 行
   */
  public int[] getRateChangeRow() {
    if (null == this.listratechgerow || this.listratechgerow.size() == 0) {
      return new int[0];
    }
    int[] chgrows = new int[this.listratechgerow.size()];
    int i = 0;
    for (Integer chgrow : this.listratechgerow) {
      chgrows[i] = chgrow;
      i++;
    }
    return chgrows;
  }

  /**
   * 方法功能描述：获得当前单据日期对应的折本汇率。
   * <p>
   * <b>参数说明</b>
   * <p>
   * 
   * @author 冯加滨
   * @time 2010-4-21 上午11:48:13
   */
  public void calcHeadExchangeRate() {
    // 单据日期
    UFDate billdate =
        this.keyValue.getHeadUFDateValue(SaleInvoiceHVO.DBILLDATE);
    // 原币币种
    String orgcurrency =
        this.keyValue.getHeadStringValue(SaleInvoiceHVO.CORIGCURRENCYID);
    // 本位币
    String currency =
        this.keyValue.getHeadStringValue(SaleInvoiceHVO.CCURRENCYID);

    UFDouble changestrate = null;
    if (!VOChecker.isEmpty(orgcurrency) && !VOChecker.isEmpty(currency)) {
      String pk_org = this.keyValue.getHeadStringValue(SaleInvoiceHVO.PK_ORG);
      changestrate =
          SOCurrencyUtil.getInCurrencyRateByOrg(pk_org, orgcurrency, currency,
              billdate);
    }
    this.keyValue.setHeadValue(SaleInvoiceHVO.NEXCHANGERATE, changestrate);
  }

}
