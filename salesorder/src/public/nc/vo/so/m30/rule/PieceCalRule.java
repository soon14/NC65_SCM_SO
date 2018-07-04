package nc.vo.so.m30.rule;

import java.util.Collection;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;

import nc.itf.scmpub.reference.uap.bd.material.MaterialPubService;
import nc.itf.scmpub.reference.uap.bd.measuredoc.MeasureDocService;
import nc.vo.pub.lang.UFDouble;
import nc.vo.pubapp.calculator.HslParseUtil;
import nc.vo.pubapp.pattern.pub.PubAppTool;
import nc.vo.so.m30.entity.SaleOrderBVO;
import nc.vo.so.pub.keyvalue.IKeyValue;

/**
 * 件数计算规则类
 * 
 * @since 6.0
 * @version 2011-7-14 下午04:20:00
 * @author fengjb
 */
public class PieceCalRule {

  private IKeyValue keyValue;

  public PieceCalRule(IKeyValue keyValue) {
    this.keyValue = keyValue;
  }

  public void calcPiece(int row) {
    int[] rows = new int[] {
      row
    };
    this.calcPiece(rows);
  }

  public void calcPiece(int[] rows) {

    Set<String> setmarid = new HashSet<String>();
    for (int row : rows) {
      String marvid =
          this.keyValue.getBodyStringValue(row, SaleOrderBVO.CMATERIALVID);
      UFDouble num = this.keyValue.getBodyUFDoubleValue(row, SaleOrderBVO.NNUM);
      if (PubAppTool.isNull(marvid) || null == num
          || num.compareTo(UFDouble.ZERO_DBL) == 0) {
        continue;
      }
      setmarid.add(marvid);
    }

    Map<String, String> mappiece = new HashMap<String, String>();
    if (setmarid.size() > 0) {
      String[] marvids = new String[setmarid.size()];
      setmarid.toArray(marvids);
      mappiece = MaterialPubService.queryPieceMeasdocIDByPks(marvids);
    }
    Map<String, Integer> mappower = new HashMap<String, Integer>();
    if (mappiece.size() > 0) {
      Collection<String> colmeas = mappiece.values();
      mappower = this.getMeasDocPower(colmeas);
    }
    for (int row : rows) {
      String marvid =
          this.keyValue.getBodyStringValue(row, SaleOrderBVO.CMATERIALVID);
      UFDouble num = this.keyValue.getBodyUFDoubleValue(row, SaleOrderBVO.NNUM);
      // 物料为空或者没有件数管理单位
      if (PubAppTool.isNull(marvid) || null == num || null == mappiece
          || !mappiece.containsKey(marvid)) {
        this.keyValue.setBodyValue(row, SaleOrderBVO.NPIECE, null);
        continue;
      }
      String pieceunit = mappiece.get(marvid);
      String unit = this.keyValue.getBodyStringValue(row, SaleOrderBVO.CUNITID);

      String changerate =
          MaterialPubService.queryMainMeasRateByMaterialAndMeasdoc(marvid,
              unit, pieceunit);

      int power = mappower.get(pieceunit).intValue();
      UFDouble piecenum =
          HslParseUtil.hslDivUFDouble(changerate, num).setScale(power,
              UFDouble.ROUND_HALF_UP);
      this.keyValue.setBodyValue(row, SaleOrderBVO.NPIECE, piecenum);
    }
  }

  private Map<String, Integer> getMeasDocPower(Collection<String> unitids) {

    Map<String, Integer> mappower = new HashMap<String, Integer>();

    Set<String> setmeasdoc = new HashSet<String>();
    for (String unitid : unitids) {
      setmeasdoc.add(unitid);
    }
    String[] measdocs = new String[setmeasdoc.size()];
    setmeasdoc.toArray(measdocs);
    Integer[] powers = MeasureDocService.getMeasPrecision(measdocs);
    int i = 0;
    for (String meas : measdocs) {
      mappower.put(meas, powers[i]);
      i++;
    }
    return mappower;
  }
}
