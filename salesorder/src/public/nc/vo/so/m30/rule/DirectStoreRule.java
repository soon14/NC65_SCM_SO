package nc.vo.so.m30.rule;

import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;

import nc.itf.scmpub.reference.uap.bd.stordoc.StordocPubService;
import nc.vo.pubapp.pattern.pub.PubAppTool;
import nc.vo.so.m30.entity.SaleOrderBVO;
import nc.vo.so.m30.entity.SaleOrderHVO;
import nc.vo.so.m30.util.SaleOrderTranTypeUtil;
import nc.vo.so.pub.keyvalue.IKeyValue;

/**
 * 直运仓库相关规则
 * 
 * @since 6.0
 * @version 2011-7-5 下午06:30:02
 * @author fengjb
 */
public class DirectStoreRule {

  private IKeyValue keyValue;

  public DirectStoreRule(IKeyValue keyValue) {
    this.keyValue = keyValue;
  }

  public void setSendStordoc(int row) {
    int[] rows = new int[] {
      row
    };
    this.setSendStordoc(rows);
  }

  /**
   * 如果交易类型是直运类型，设置直运仓，否则不处理
   * 
   * @param rows
   */
  public void setDirectStore(int[] rows) {

    if (null == rows || rows.length == 0) {
      return;
    }
    String trantypeid =
        this.keyValue.getHeadStringValue(SaleOrderHVO.CTRANTYPEID);
    SaleOrderTranTypeUtil dirutil = new SaleOrderTranTypeUtil();
    if (dirutil.isDirectTran(trantypeid)) {
      this.setNewDirectStore(rows);
    }
  }

  /**
   * 根据交易类型是否直运设置仓库值,如果不是直运的清空原有仓库值，否则设置为直运仓
   * 
   * @param rows
   */
  public void setSendStordoc(int[] rows) {
    if (null == rows || rows.length == 0) {
      return;
    }
    String trantypeid =
        this.keyValue.getHeadStringValue(SaleOrderHVO.CTRANTYPEID);
    SaleOrderTranTypeUtil dirutil = new SaleOrderTranTypeUtil();
    if (dirutil.isDirectTran(trantypeid)) {
      this.setNewDirectStore(rows);
    }
    else {
      this.clearDirectStore(rows);
    }
  }

  private void clearDirectStore(int[] rows) {
    for (int row : rows) {
      this.keyValue.setBodyValue(row, SaleOrderBVO.CSENDSTORDOCID, null);
    }
  }

  private void setNewDirectStore(int[] rows) {

    Set<String> setstockorg = new HashSet<String>();
    for (int row : rows) {
      String stockorg =
          this.keyValue.getBodyStringValue(row, SaleOrderBVO.CSENDSTOCKORGID);
      if (PubAppTool.isNull(stockorg)) {
        continue;
      }
      setstockorg.add(stockorg);
    }
    String[] stockorgs = new String[setstockorg.size()];
    setstockorg.toArray(stockorgs);

    Map<String, String> mapdirstore = this.getDirectStore(stockorgs);

    for (int row : rows) {
      String stockorg =
          this.keyValue.getBodyStringValue(row, SaleOrderBVO.CSENDSTOCKORGID);
      if (PubAppTool.isNull(stockorg)) {
        this.keyValue.setBodyValue(row, SaleOrderBVO.CSENDSTORDOCID, null);
        continue;
      }
      String dirstore = mapdirstore.get(stockorg);
      this.keyValue.setBodyValue(row, SaleOrderBVO.CSENDSTORDOCID, dirstore);
    }
  }

  private Map<String, String> getDirectStore(String[] stockorgs) {
    Map<String, String> mapdirstore = new HashMap<String, String>();
    if (null == stockorgs || stockorgs.length == 0) {
      return mapdirstore;
    }
    for (String org : stockorgs) {
      String direcstore = StordocPubService.getDirectstore(org);
      mapdirstore.put(org, direcstore);
    }
    return mapdirstore;
  }
}
