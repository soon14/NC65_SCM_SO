package nc.ui.so.pub.actions;

import nc.vo.ic.onhand.entity.OnhandDimVO;
import nc.vo.pub.CircularlyAccessibleValueObject;
import nc.vo.pub.lang.UFDouble;
import nc.vo.pubapp.AppContext;
import nc.vo.pubapp.pattern.model.meta.entity.view.DataViewMeta;
import nc.vo.pubapp.pattern.pub.PubAppTool;
import nc.vo.so.entry.SOOnhandDlgHeadVO;
import nc.vo.so.pub.SOConstant;
import nc.vo.so.pub.SOItemKey;

public class SOFillOnhandDlgHeadVO implements IFillOnhandDlgHeadVO {

  @Override
  public SOOnhandDlgHeadVO fillOnhandVO(CircularlyAccessibleValueObject hvo,
      CircularlyAccessibleValueObject bvo) {
    if (null == bvo.getAttributeValue(SOItemKey.CMATERIALID)
        || null == hvo.getAttributeValue(SOItemKey.PK_ORG)
        || PubAppTool.isNull((String) bvo
            .getAttributeValue(SOItemKey.CMATERIALID))) {
      return null;
    }
    OnhandDimVO dimVO = new OnhandDimVO();
    dimVO.setPk_org((String) bvo.getAttributeValue(SOItemKey.CSENDSTOCKORGID));
    dimVO.setPk_group(AppContext.getInstance().getPkGroup());
    dimVO.setCffileid((String) bvo.getAttributeValue(SOItemKey.CMFFILEID));
    for (String key : SOConstant.ONHANDDLG_BODY_KEY) {
      dimVO.setAttributeValue(key, bvo.getAttributeValue(key));
    }
    dimVO.setAttributeValue(OnhandDimVO.CWAREHOUSEID,
        bvo.getAttributeValue(SOItemKey.CSENDSTORDOCID));
    dimVO.setAttributeValue(OnhandDimVO.CMATERIALOID,
        bvo.getAttributeValue(SOItemKey.CMATERIALID));
    dimVO.setAttributeValue(OnhandDimVO.CASSCUSTID,
        hvo.getAttributeValue(SOItemKey.CCUSTOMERID));
    DataViewMeta dataViewMeta = new DataViewMeta(dimVO.getClass());
    SOOnhandDlgHeadVO onHandVO = new SOOnhandDlgHeadVO();
    onHandVO.setDataViewMeta(dataViewMeta);
    onHandVO.setVO(dimVO);
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
