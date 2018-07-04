/**
 * 
 */
package nc.vo.so.m30.sobalance.util;

import nc.vo.arap.agiotage.ArapBusiDataVO;
import nc.vo.arap.verify.AggverifyVO;
import nc.vo.arap.verify.VerifyDetailVO;
import nc.vo.arap.verify.VerifyMainVO;
import nc.vo.pub.lang.UFDate;
import nc.vo.pub.lang.UFDouble;
import nc.vo.scmpub.res.billtype.SOBillType;
import nc.vo.so.m30.sobalance.entity.SoBalanceBVO;
import nc.vo.so.m30.sobalance.enumeration.SoBalanceType;

/**
 * @author gdsjw
 * 
 */
public class VerifybillUtil {
  private static final int FROMSALEFLAG = 3;

  private static VerifybillUtil instance = new VerifybillUtil();

  private VerifybillUtil() {
    //
  }

  public static VerifybillUtil getInstance() {
    return VerifybillUtil.instance;
  }

  public boolean isFromSaleorder(AggverifyVO aggverifyvo) {
    boolean isFromSaleorder = false;
    ArapBusiDataVO ararbusidatavo = this.getYsbusivo(aggverifyvo);
    if (ararbusidatavo == null) {
      isFromSaleorder = false;
    }
    else {
      int syscode = ararbusidatavo.getSrc_syscode().intValue();
      if (syscode == VerifybillUtil.FROMSALEFLAG) {
        isFromSaleorder = true;
      }
    }
    return isFromSaleorder;
  }

  public String getSaleOrderID(AggverifyVO aggverifyvo) {
    String salorderid = null;
    ArapBusiDataVO ararbusidatavo = this.getYsbusivo(aggverifyvo);
    if (ararbusidatavo != null) {
      String srcbilltype = ararbusidatavo.getTop_billtype();
      if (srcbilltype.equals(SOBillType.Order.getCode())) {
        salorderid = ararbusidatavo.getTop_billid();
      }
    }
    return salorderid;
  }

  public boolean isSkVerify(AggverifyVO aggverifyvo) {
    boolean skverify = false;
    VerifyDetailVO[] detailvos = (VerifyDetailVO[]) aggverifyvo.getChildrenVO();
    for (VerifyDetailVO detailvo : detailvos) {
      String pk_billtype = detailvo.getPk_billtype();
      if ("F2".equals(pk_billtype)) {
        skverify = true;
      }
    }
    return skverify;
  }

  public String getPaybillrowid(AggverifyVO aggverifyvo) {
    String paybillrowid = null;
    VerifyDetailVO detailvo = this.getSkdetailvo(aggverifyvo);
    if (detailvo != null) {
      paybillrowid = detailvo.getPk_item();
    }

    return paybillrowid;
  }

  public UFDate getArBalancedate(AggverifyVO aggverifyvo) {
    UFDate darbalancedate = null;
    VerifyMainVO mainvo = this.getVerifyMainVO(aggverifyvo);
    darbalancedate = mainvo.getBusidate();

    return darbalancedate;
  }

  public UFDouble getOrigaccbalmny(AggverifyVO aggverifyvo) {
    UFDouble norigaccbalmny = null;
    VerifyDetailVO detailvo = this.getSkdetailvo(aggverifyvo);
    if (detailvo != null) {
      norigaccbalmny = detailvo.getMoney_cr();
    }

    return norigaccbalmny;
  }

  public SoBalanceBVO createSoBalanceBVOByVerifyVO(AggverifyVO aggverifyvo) {
    SoBalanceBVO bodyvo = null;
    VerifyDetailVO detailvo = this.getSkdetailvo(aggverifyvo);
    ArapBusiDataVO busivo = this.getSkbusivo(aggverifyvo);
    if (detailvo != null && busivo != null) {
      bodyvo = new SoBalanceBVO();
      bodyvo.setCpaybillid(detailvo.getPk_bill());
      bodyvo.setCpaybillrowid(detailvo.getPk_item());
      bodyvo.setVarbillcode(detailvo.getBillno());
      bodyvo.setDarbilldate(detailvo.getBusidate());
      bodyvo.setCarorigcurrencyid(detailvo.getPk_currtype());
      bodyvo.setCprodlineid(busivo.getProductline());
      // bodyvo.setNorigordbalmny(new UFDouble(10));
      bodyvo.setFibaltype(Integer.valueOf(SoBalanceType.SOBALANCE_FINBAL
          .getIntValue()));

      // bodyvo.setDr(Integer.valueOf(0));
      // bodyvo.setStatus(VOStatus.NEW);
    }
    return bodyvo;
  }

  private ArapBusiDataVO getYsbusivo(AggverifyVO aggverifyvo) {
    ArapBusiDataVO ararbusidatavo = null;
    VerifyDetailVO[] detailvos = (VerifyDetailVO[]) aggverifyvo.getChildrenVO();
    for (VerifyDetailVO detailvo : detailvos) {
      String pk_billtype = detailvo.getPk_billtype();
      if ("F0".equals(pk_billtype)) {
        ararbusidatavo = detailvo.getM_jfverifyVO();
        if (ararbusidatavo == null) {
          ararbusidatavo = detailvo.getM_dfverifyVO();
        }

      }
    }
    return ararbusidatavo;
  }

  private ArapBusiDataVO getSkbusivo(AggverifyVO aggverifyvo) {
    ArapBusiDataVO ararbusidatavo = null;
    VerifyDetailVO[] detailvos = (VerifyDetailVO[]) aggverifyvo.getChildrenVO();
    for (VerifyDetailVO detailvo : detailvos) {
      String pk_billtype = detailvo.getPk_billtype();
      if ("F2".equals(pk_billtype)) {
        ararbusidatavo = detailvo.getM_dfverifyVO();
        if (ararbusidatavo == null) {
          ararbusidatavo = detailvo.getM_jfverifyVO();
        }

      }
    }
    return ararbusidatavo;
  }

  private VerifyDetailVO getSkdetailvo(AggverifyVO aggverifyvo) {
    VerifyDetailVO retvo = null;
    VerifyDetailVO[] detailvos = (VerifyDetailVO[]) aggverifyvo.getChildrenVO();
    for (VerifyDetailVO detailvo : detailvos) {
      String pk_billtype = detailvo.getPk_billtype();
      if ("F2".equals(pk_billtype)) {
        retvo = detailvo;
      }
    }
    return retvo;
  }

  private VerifyMainVO getVerifyMainVO(AggverifyVO aggverifyvo) {
    VerifyMainVO retvo = (VerifyMainVO) aggverifyvo.getParentVO();
    return retvo;
  }
}
