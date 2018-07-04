package nc.ui.so.m4331.arrange.scale;

import nc.desktop.ui.WorkbenchEnvironment;
import nc.ui.pub.bill.BillModel;
import nc.ui.pubapp.billref.push.IBillModelDigitSetter;
import nc.ui.pubapp.scale.BillModelScaleProcessor;
import nc.vo.pubapp.scale.TableScaleProcessor;
import nc.vo.to.m5x.entity.BillHeaderVO;
import nc.vo.to.m5x.entity.BillItemVO;

public class M5XDigitSetter implements IBillModelDigitSetter {

  @Override
  public void setDigit(BillModel billmodel) {
    String pk_group =
        WorkbenchEnvironment.getInstance().getGroupVO().getPk_group();
    TableScaleProcessor scale =
        new BillModelScaleProcessor(pk_group, billmodel);
    // 主单位数量
    String[] numKeys = new String[] {
        BillItemVO.NINNUM, BillItemVO.NIOSETTLENUM, BillItemVO.NMMORDERNUM,
        BillItemVO.NNUM, BillItemVO.NOTSETTLENUM, BillItemVO.NOUTNUM,
        BillItemVO.NPOAPPLYNUM, BillItemVO.NPOORDERNUM, BillItemVO.NRETURNNUM,
        BillItemVO.NSCORDERNUM, BillItemVO.NSENDNUM, BillItemVO.NSHOULDOUTNUM,
        BillItemVO.NTOAPPLEYNUM, BillItemVO.NTOORDERNUM, BillItemVO.NWAYLOSSNUM,
        "nonwaynum"//在途主数量
    };
    scale.setNumCtlInfo(numKeys, BillItemVO.CUNITID);
    
    // 业务单位数量
    String[] astNumKeys = new String[] {
        BillItemVO.NASTNUM, "nastoutnum", "nastinnum", "nastonwaynum",
        "nastsendnum", "nastwaylossnum"
    };
    scale.setNumCtlInfo(astNumKeys, BillItemVO.CASTUNITID);
    
    // 报价单位数量
    String[] qtNumKeys = new String[] {
      BillItemVO.NQTUNITNUM
    };
    scale.setNumCtlInfo(qtNumKeys,  BillItemVO.CQTUNITID);
    
    // 全局本位币金额
    String[] globalmnykeys = new String[] {
        BillItemVO.NGLOBALMNY, BillItemVO.NGLOBALTAXMNY
    };
    scale.setGlobalLocMnyCtlInfo(globalmnykeys);

    // 集团本位币金额
    String[] groupmnykeys = new String[] {
        BillItemVO.NGROUPMNY, BillItemVO.NGROUPTAXMNY
    };
    scale.setGroupLocMnyCtlInfo(groupmnykeys);

    // 原币金额
    String[] origmnykeys = new String[] {
        BillItemVO.NORIGMNY, BillItemVO.NORIGTAXMNY,
        BillItemVO.NIOSETTLEMNY, BillItemVO.NOTSETTLEMNY
    };
    scale.setMnyCtlInfo(origmnykeys,
        BillHeaderVO.CORIGCURRENCYID);
    
    // 表头价税合计
    scale.setMnyCtlInfo(new String[] {
      BillHeaderVO.NTOTALORIGMNY
    }, BillHeaderVO.CORIGCURRENCYID);
    
    // 组织本位币金额
    String[] mnykeys = new String[] {
        BillItemVO.NMNY, BillItemVO.NTAXMNY, BillItemVO.NTAX
    };
    scale.setMnyCtlInfo(mnykeys, BillHeaderVO.CCURRENCYID);
    
    // 单价
    String[] priceKeys = new String[] {
        BillItemVO.NASKQTORIGPRICE, BillItemVO.NASKQTORIGTAXPRC,
        BillItemVO.NNETPRICE, BillItemVO.NORIGNETPRICE,
        BillItemVO.NORIGTAXNETPRICE, BillItemVO.NQTNETPRICE,
        BillItemVO.NQTORIGNETPRICE, BillItemVO.NQTORIGTAXNETPRC,
        BillItemVO.NQTTAXNETPRICE, BillItemVO.NTAXNETPRICE
    };
    scale.setPriceCtlInfo(priceKeys,BillHeaderVO.CORIGCURRENCYID);
    
    // 体积
    String[] volumnKeys_b = new String[] {
      BillItemVO.NVOLUME
    };
    scale.setVolumnCtlInfo(volumnKeys_b);

    // 重量
    String[] weightKeys_b = new String[] {
      BillItemVO.NWEIGHT
    };
    scale.setWeightCtlInfo(weightKeys_b);
    
    // 件数
    String[] unitKeys_b = new String[] {
      BillItemVO.NPIECE
    };
    scale.setUnitCtlInfo(unitKeys_b,BillItemVO.CINVENTORYID);

    scale.process();
  }
}
