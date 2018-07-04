package nc.pubimpl.so.pub.api.fill;

import java.util.HashMap;
import java.util.Map;

import nc.itf.scmpub.reference.uap.bd.material.MaterialPubService;
import nc.vo.bd.material.stock.MaterialStockVO;
import nc.vo.pub.BusinessException;
import nc.vo.pub.lang.UFBoolean;
import nc.vo.pubapp.pattern.exception.ExceptionUtils;
import nc.vo.pubapp.pattern.model.entity.bill.AbstractBill;
import nc.vo.pubapp.pattern.model.entity.bill.IBill;
import nc.vo.pubapp.pattern.pub.PubAppTool;
import nc.vo.scmpub.fill.BillVOsFillRule;
import nc.vo.scmpub.fill.billfill.BatchCodeBillFill;
import nc.vo.so.m30.entity.SaleOrderBVO;
import nc.vo.so.pub.SOItemKey;
import nc.vo.so.pub.keyvalue.IKeyValue;
import nc.vo.so.pub.keyvalue.VOKeyValue;

/**
 * @description
 * 销售批次生成规则
 * @scene
 *
 * @param
 *
 *
 * @since 6.5
 * @version 2015-12-17 下午2:20:02
 * @author 刘景
 */
public class BatchCodeBillRule<E extends IBill> {

  /**
   * 批次生成
   * 
   * @param vos 单据VO
   */
  public void fillValue(E[] vos) {

    // 1.清空物料没有勾选批次管理的批次
    this.clearBatchCode(vos);

    // 2. 补全批次主键
    BillVOsFillRule filler = new BillVOsFillRule();
    filler.addBillFillRule(new BatchCodeBillFill(SOItemKey.CMATERIALVID,
        SOItemKey.VBATCHCODE, SaleOrderBVO.PK_BATCHCODE));
    try {
      filler.fillValue((AbstractBill[]) vos);
    }
    catch (BusinessException e) {
      ExceptionUtils.wrappBusinessException(e.getMessage());
    }

    // 3.检查批次pk和code是否都在（批次翻译时没有处理，因为库存默认没有找到直接插入，而销售没有找却是认为数据不合法）
    checkBatchCode(vos);
  }

  private void checkBatchCode(E[] vos) {
    StringBuffer errbatchcode = new StringBuffer();
    for (E vo : vos) {
      IKeyValue keyValue = new VOKeyValue<E>(vo);
      for (int i = 0; i < keyValue.getBodyCount(); i++) {
        String pk_batchcode =
            keyValue.getBodyStringValue(i, SOItemKey.VBATCHCODE);
        String vbatchcode =
            keyValue.getBodyStringValue(i, SaleOrderBVO.PK_BATCHCODE);
        if (PubAppTool.isNull(pk_batchcode) && PubAppTool.isNull(vbatchcode)) {
          continue;
        }

        if (PubAppTool.isNull(pk_batchcode) || PubAppTool.isNull(vbatchcode)) {
          if (!PubAppTool.isNull(pk_batchcode)) {
            errbatchcode.append(pk_batchcode);
            errbatchcode.append("、");
          }
          else if (!PubAppTool.isNull(vbatchcode)) {
            errbatchcode.append(pk_batchcode);
            errbatchcode.append("、");
          }
        }
      }
    }
    if (errbatchcode.length() > 0) {
      String message = "下列批次不合法：";/* -=notranslate=- */
      errbatchcode.deleteCharAt(errbatchcode.length() - 1);
      ExceptionUtils.wrappBusinessException(message + errbatchcode.toString());
    }
  }

  private void clearBatchCode(E[] vos) {
    Map<String, MaterialStockVO> marorgmap = new HashMap<>();
    for (E vo : vos) {
      IKeyValue keyValue = new VOKeyValue<E>(vo);
      for (int i = 0; i < keyValue.getBodyCount(); i++) {
        String materialvid =
            keyValue.getBodyStringValue(i, SOItemKey.CMATERIALVID);
        String sendstock =
            keyValue.getBodyStringValue(i, SOItemKey.CSENDSTOCKORGID);

        String pk_batchcode =
            keyValue.getBodyStringValue(i, SOItemKey.VBATCHCODE);
        String vbatchcode =
            keyValue.getBodyStringValue(i, SaleOrderBVO.PK_BATCHCODE);

        if (PubAppTool.isNull(pk_batchcode) && PubAppTool.isNull(vbatchcode)) {
          continue;
        }

        if (PubAppTool.isNull(materialvid) || PubAppTool.isNull(sendstock)) {
          this.clear(keyValue, i);
        }
        String[] wholeflag = new String[] {
          MaterialStockVO.WHOLEMANAFLAG
        };

        String marsendorg = materialvid + sendstock;
        if (!marorgmap.containsKey(marsendorg)) {
          Map<String, MaterialStockVO> mStockmap =
              MaterialPubService.queryMaterialStockInfo(new String[] {
                materialvid
              }, sendstock, wholeflag);
          marorgmap.put(marsendorg, mStockmap.get(materialvid));
        }
        MaterialStockVO marstockvo = marorgmap.get(marsendorg);

        UFBoolean flag = null;
        if (null != marstockvo) {
          flag = marstockvo.getWholemanaflag();
        }
        if (null == flag || !flag.booleanValue()) {
          this.clear(keyValue, i);
        }
      }
    }
  }

  private void clear(IKeyValue keyValue, int row) {
    keyValue.setBodyValue(row, SOItemKey.VBATCHCODE, null);
    keyValue.setBodyValue(row, SaleOrderBVO.PK_BATCHCODE, null);
  }
}
