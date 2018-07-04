package nc.vo.so.pub.function;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import nc.itf.scmpub.reference.uap.bd.customer.CustomerPubService;
import nc.itf.scmpub.reference.uap.bd.material.MaterialPubService;
import nc.vo.bd.cust.saleinfo.CustsaleVO;
import nc.vo.bd.material.sale.MaterialSaleVO;
import nc.vo.pub.AggregatedValueObject;
import nc.vo.pub.CircularlyAccessibleValueObject;
import nc.vo.pub.lang.UFBoolean;
import nc.vo.pub.lang.UFDouble;
import nc.vo.pubapp.pattern.pub.MathTool;
import nc.vo.so.pub.SOConstant;
import nc.vo.so.pub.SOItemKey;
import nc.vo.so.pub.util.SOSysParaInitUtil;

public class SOFunctionUtil {

  public UFBoolean examSaleNetprice(AggregatedValueObject bill) {
    // --1.SO23基价含税、含税优先
    boolean bSO23 = this.getParameterSO23(bill);
    // --2.物料最低售价
    Map<String, MaterialSaleVO> mMaterialSaleVOs =
        this.getMaterialSaleInfo(bill);
    // --3.客户档案的物料最低售价比例
    CustsaleVO custsaleVO = this.getCustSaleVO(bill);

    // 无税净价
    String netPriceKey = SOItemKey.NNETPRICE;
    // 含税净价
    if (bSO23) {
      netPriceKey = SOItemKey.NTAXNETPRICE;
    }
    if (mMaterialSaleVOs != null && mMaterialSaleVOs.size() > 0
        && custsaleVO != null) {
      CircularlyAccessibleValueObject[] bodys = bill.getChildrenVO();
      for (CircularlyAccessibleValueObject body : bodys) {
        // 销售净价不能小于物料档案的最低售价 * 客户档案的物料最低售价比例
        UFDouble minPrice = UFDouble.ZERO_DBL;
        String materialvid =
            (String) body.getAttributeValue(SOItemKey.CMATERIALVID);
        if (mMaterialSaleVOs.containsKey(materialvid)) {
          minPrice = mMaterialSaleVOs.get(materialvid).getMinprice();
        }
        Integer stockpriceratio = SOConstant.ONEHUNDRED.intValue();
        if (null != custsaleVO.getStockpriceratio()) {
          stockpriceratio = custsaleVO.getStockpriceratio();
        }
        Object netPrice = body.getAttributeValue(netPriceKey);
        UFDouble temp =
            minPrice.multiply(new UFDouble(stockpriceratio)).multiply(0.01);
        if (netPrice != null
            && MathTool.compareTo((UFDouble) netPrice, temp) < 0) {
          return UFBoolean.FALSE;
        }
      }
    }
    return UFBoolean.TRUE;
  }

  public UFDouble getMaxDiscountRate(AggregatedValueObject bill) {
    CircularlyAccessibleValueObject[] bodys = bill.getChildrenVO();
    UFDouble discountRate = null;
    UFDouble itemDiscountRate = null;
    UFDouble tempDiscountRate = null;
    UFDouble maxDiscountRate = UFDouble.ZERO_DBL;
    for (CircularlyAccessibleValueObject body : bodys) {
      if (body.getAttributeValue(SOItemKey.NDISCOUNTRATE) == null) {
        discountRate = UFDouble.ZERO_DBL;
      }
      else {
        discountRate =
            (UFDouble) body.getAttributeValue(SOItemKey.NDISCOUNTRATE);
      }
      if (body.getAttributeValue(SOItemKey.NITEMDISCOUNTRATE) == null) {
        itemDiscountRate = UFDouble.ZERO_DBL;
      }
      else {
        itemDiscountRate =
            (UFDouble) body.getAttributeValue(SOItemKey.NITEMDISCOUNTRATE);
      }
      tempDiscountRate =
          discountRate.multiply(itemDiscountRate)
              .multiply(new UFDouble(0.0001));
      if (MathTool.compareTo(maxDiscountRate, tempDiscountRate) < 0) {
        maxDiscountRate = tempDiscountRate;
      }
    }
    return maxDiscountRate;
  }

  public UFDouble getMaxPriceRate(AggregatedValueObject bill) {
    UFDouble[] dPriceRates = this.getPriceRate(bill);
    UFDouble dMaxPriceRate = dPriceRates[0];
    for (int i = 0; i < dPriceRates.length; i++) {
      if (MathTool.compareTo(dPriceRates[i], dMaxPriceRate) > 0) {
        dMaxPriceRate = dPriceRates[i];
      }
    }
    return dMaxPriceRate;
  }

  public UFDouble getMinDiscountRate(AggregatedValueObject bill) {
    CircularlyAccessibleValueObject[] bodys = bill.getChildrenVO();
    UFDouble discountRate = null;
    UFDouble itemDiscountRate = null;
    UFDouble tempDiscountRate = null;
    UFDouble minDiscountRate = new UFDouble(100.0);
    for (CircularlyAccessibleValueObject body : bodys) {
      if (body.getAttributeValue(SOItemKey.NDISCOUNTRATE) == null) {
        discountRate = UFDouble.ZERO_DBL;
      }
      else {
        discountRate =
            (UFDouble) body.getAttributeValue(SOItemKey.NDISCOUNTRATE);
      }
      if (body.getAttributeValue(SOItemKey.NITEMDISCOUNTRATE) == null) {
        itemDiscountRate = UFDouble.ZERO_DBL;
      }
      else {
        itemDiscountRate =
            (UFDouble) body.getAttributeValue(SOItemKey.NITEMDISCOUNTRATE);
      }
      tempDiscountRate =
          discountRate.multiply(itemDiscountRate)
              .multiply(new UFDouble(0.0001));
      if (MathTool.compareTo(minDiscountRate, tempDiscountRate) > 0) {
        minDiscountRate = tempDiscountRate;
      }
    }
    return minDiscountRate;
  }

  public UFDouble getMinPriceRate(AggregatedValueObject vo) {
    UFDouble[] dPriceRates = this.getPriceRate(vo);
    UFDouble dMinPriceRate = dPriceRates[0];
    for (int i = 0; i < dPriceRates.length; i++) {
      if (MathTool.compareTo(dPriceRates[i], dMinPriceRate) < 0) {
        dMinPriceRate = dPriceRates[i];
      }
    }
    return dMinPriceRate;
  }

  private CustsaleVO getCustSaleVO(AggregatedValueObject bill) {
    CustsaleVO custsaleVO = null;
    String customerid =
        (String) bill.getParentVO().getAttributeValue(SOItemKey.CCUSTOMERID);
    String pk_org =
        (String) bill.getParentVO().getAttributeValue(SOItemKey.PK_ORG);
    custsaleVO =
        CustomerPubService.getCustSaleVOByPk(customerid, pk_org, new String[] {
          CustsaleVO.STOCKPRICERATIO
        });
    return custsaleVO;
  }

  private Map<String, MaterialSaleVO> getMaterialSaleInfo(
      AggregatedValueObject bill) {
    Map<String, MaterialSaleVO> mMaterialSaleVOs = null;
    CircularlyAccessibleValueObject[] bodys = bill.getChildrenVO();
    List<String> alMaterial = new ArrayList<String>();
    Object cmaterialid = null;
    for (CircularlyAccessibleValueObject body : bodys) {
      cmaterialid = body.getAttributeValue(SOItemKey.CMATERIALVID);
      if (cmaterialid != null && cmaterialid.toString().length() > 0) {
        alMaterial.add(cmaterialid.toString());
      }
    }
    if (alMaterial.size() > 0) {
      String[] materials = alMaterial.toArray(new String[0]);
      String pk_saleorg =
          (String) bill.getParentVO().getAttributeValue(SOItemKey.PK_ORG);
      String[] fields = new String[] {
        MaterialSaleVO.MINPRICE
      };
      mMaterialSaleVOs =
          MaterialPubService.queryMaterialSaleInfoByPks(materials, pk_saleorg,
              fields);
    }
    return mMaterialSaleVOs;
  }

  private boolean getParameterSO23(AggregatedValueObject bill) {
    String pk_group =
        (String) bill.getParentVO().getAttributeValue(SOItemKey.PK_GROUP);
    UFBoolean bSO23 = SOSysParaInitUtil.getSO23(pk_group);
    return bSO23.booleanValue();
  }

  private UFDouble[] getPriceRate(AggregatedValueObject bill) {
    // --1.参数SO23基价含税、含税优先
    boolean bSO23 = this.getParameterSO23(bill);
    CircularlyAccessibleValueObject[] bodys = bill.getChildrenVO();
    // 存储表体每一行的函数计算结果
    UFDouble[] dPriceRates = new UFDouble[bodys.length];
    // 订单净价(原币)
    Object qtorignetprice = null;
    // 询到净价(原币)
    Object askqtorignetprice = null;

    for (int i = 0; i < bodys.length; i++) {
      // 过滤赠品、劳务、折扣行
      if (!this.isNeedCal(bodys[i])) {
        continue;
      }
      // 净价=(SO23基价含税 ？含税净价 ：无税净价)
      qtorignetprice =
          bSO23 ? bodys[i].getAttributeValue(SOItemKey.NQTORIGTAXNETPRC)
              : bodys[i].getAttributeValue(SOItemKey.NQTORIGNETPRICE);
      if (qtorignetprice == null) {
        continue;
      }
      // 询到净价=(SO23基价含税 ？含税净价 ：原币无税净价)
      askqtorignetprice =
          bSO23 ? bodys[i].getAttributeValue(SOItemKey.NASKQTORIGTXNTPRC)
              : bodys[i].getAttributeValue(SOItemKey.NASKQTORIGNETPRICE);
      if (askqtorignetprice == null) {
        continue;
      }
      dPriceRates[i] =
          ((UFDouble) qtorignetprice).div((UFDouble) askqtorignetprice);
    }
    return dPriceRates;
  }

  private boolean isNeedCal(CircularlyAccessibleValueObject body) {
    boolean ret = true;
    Object blargessflag = body.getAttributeValue(SOItemKey.BLARGESSFLAG);
    Object laborflag = body.getAttributeValue(SOItemKey.BLABORFLAG);
    Object discountflag = body.getAttributeValue(SOItemKey.BDISCOUNTFLAG);
    // 赠品不参加检查
    if (blargessflag != null && ((UFBoolean) blargessflag).booleanValue()) {
      ret = false;
    }
    // 是否劳务类存货，不参加检查
    else if (laborflag != null && ((UFBoolean) laborflag).booleanValue()) {
      ret = false;
    }
    // 存货折扣属性，不参加检查
    else if (discountflag != null && ((UFBoolean) discountflag).booleanValue()) {
      ret = false;
    }
    return ret;
  }
}
