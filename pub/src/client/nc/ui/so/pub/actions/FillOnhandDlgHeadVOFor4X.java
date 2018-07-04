package nc.ui.so.pub.actions;

import nc.vo.ic.onhand.entity.OnhandDimVO;
import nc.vo.pub.CircularlyAccessibleValueObject;
import nc.vo.pub.lang.UFDouble;
import nc.vo.pubapp.pattern.model.meta.entity.view.DataViewMeta;
import nc.vo.so.entry.SOOnhandDlgHeadVO;
import nc.vo.so.pub.SOItemKey;

public class FillOnhandDlgHeadVOFor4X implements IFillOnhandDlgHeadVO {

  @Override
  public SOOnhandDlgHeadVO fillOnhandVO(CircularlyAccessibleValueObject hvo,
      CircularlyAccessibleValueObject bvo) {
    String[] bodyDims = OnhandDimVO.getDimContentFields();
    OnhandDimVO dimvo = new OnhandDimVO();
    for (String dim : bodyDims) {

      Object dimValue = null;
      // 库存组织
      if (dim.equals(OnhandDimVO.PK_ORG)) {
        dimValue = hvo.getAttributeValue("ctoutstockorgid");
        if(dimValue == null){
          return null;
        }
      }
      // 仓库
      else if (dim.equals(OnhandDimVO.CWAREHOUSEID)) {
        dimValue = bvo.getAttributeValue("ctoutstordocid");
      }
      // 物料ID
      else if (dim.equals(OnhandDimVO.CMATERIALVID)) {
        dimValue = bvo.getAttributeValue("cinventoryvid");
        if(null == dimValue){
          return null;
        }
      }
      // 物料VID
      else if (dim.equals(OnhandDimVO.CMATERIALOID)) {
        dimValue = bvo.getAttributeValue("cinventoryid");
      }
      // 货位
      else if (dim.equals(OnhandDimVO.CLOCATIONID)) {
        dimValue = bvo.getAttributeValue("ctoutspaceid");
      }
      // 寄存供应商
      else if (dim.equals(OnhandDimVO.CVMIVENDERID)) {
        dimValue = bvo.getAttributeValue("cconsignvendorid");
      }
      else {
        dimValue = bvo.getAttributeValue(dim);// 默认现存量维度字段同表体字段相同
      }
      dimvo.setAttributeValue(dim, dimValue);
    }
    DataViewMeta dataViewMeta = new DataViewMeta(dimvo.getClass());
    SOOnhandDlgHeadVO onHandVO = new SOOnhandDlgHeadVO();
    onHandVO.setDataViewMeta(dataViewMeta);
    onHandVO.setVO(dimvo);
    onHandVO.setCrowno((String) bvo.getAttributeValue(SOItemKey.CROWNO));
    onHandVO.setOnhandshouldnum((UFDouble) bvo
        .getAttributeValue(SOItemKey.NNUM));
    onHandVO.setOnhandshouldassnum((UFDouble) bvo
        .getAttributeValue(SOItemKey.NASTNUM));
    onHandVO.setVbillcode((String) hvo.getAttributeValue(SOItemKey.VBILLCODE));
    onHandVO.setCunitid((String) bvo.getAttributeValue(SOItemKey.CUNITID));
    return onHandVO;
  }

}
