package nc.vo.so.m30.rule;

import java.util.HashSet;
import java.util.Map;
import java.util.Set;

import nc.itf.scmpub.reference.uap.bd.material.MaterialPubService;
import nc.vo.bd.material.MaterialVO;
import nc.vo.pub.lang.UFDouble;
import nc.vo.pubapp.pattern.pub.PubAppTool;
import nc.vo.so.m30.entity.SaleOrderBVO;
import nc.vo.so.pub.keyvalue.IKeyValue;

/**
 * 销售订单重量、体积计算
 * 
 * @since 6.0
 * @version 2011-7-14 下午04:49:47
 * @author fengjb
 */
public class WeightVolumeCalRule {

  private IKeyValue keyValue;

  public WeightVolumeCalRule(IKeyValue keyValue) {
    this.keyValue = keyValue;
  }

  public void calculateWeightVolume(int row) {

    int[] rows = new int[] {
      row
    };
    this.calculateWeightVolume(rows);
  }

  public void calculateWeightVolume(int[] rows) {
    if (null == rows || rows.length == 0) {
      return;
    }
    Set<String> setmarvid = new HashSet<String>();
    for (int row : rows) {
      String cmaterialvid =
          this.keyValue.getBodyStringValue(row, SaleOrderBVO.CMATERIALVID);
      UFDouble num = this.keyValue.getBodyUFDoubleValue(row, SaleOrderBVO.NNUM);
      if (PubAppTool.isNull(cmaterialvid) || null == num) {
        continue;
      }
      setmarvid.add(cmaterialvid);
    }
    String[] marvids = new String[setmarvid.size()];
    setmarvid.toArray(marvids);

    String[] fields = new String[] {
      MaterialVO.UNITWEIGHT, MaterialVO.UNITVOLUME
    };

    Map<String, MaterialVO> mapmaterial =
        MaterialPubService.queryMaterialBaseInfo(marvids, fields);

    for (int row : rows) {

      String cmaterialvid =
          this.keyValue.getBodyStringValue(row, SaleOrderBVO.CMATERIALVID);
      UFDouble num = this.keyValue.getBodyUFDoubleValue(row, SaleOrderBVO.NNUM);
      // 获取物料信息
      if (PubAppTool.isNull(cmaterialvid) || null == num
          || !mapmaterial.containsKey(cmaterialvid)) {
        this.keyValue.setBodyValue(row, SaleOrderBVO.NVOLUME, null);
        this.keyValue.setBodyValue(row, SaleOrderBVO.NWEIGHT, null);
        continue;
      }
      UFDouble volume = null;
      UFDouble weight = null;
      MaterialVO materialvo = mapmaterial.get(cmaterialvid);
      if (null != materialvo) {
        UFDouble unitvolume = materialvo.getUnitvolume();
        if (null != unitvolume) {
          volume = num.multiply(unitvolume);
        }
        UFDouble unitweight = materialvo.getUnitweight();
        if (null != unitweight) {
          weight = num.multiply(unitweight);
        }
      }
      this.keyValue.setBodyValue(row, SaleOrderBVO.NVOLUME, volume);
      this.keyValue.setBodyValue(row, SaleOrderBVO.NWEIGHT, weight);
    }
  }
}
