package nc.pubimpl.so.m4331.so.m30.bp.rule;

import java.util.HashSet;
import java.util.Map;
import java.util.Set;

import nc.pubitf.so.m4331.so.m30.IDeliveryPriceParaFor30;
import nc.vo.pubapp.util.CombineViewToAggUtil;
import nc.vo.so.m4331.entity.DeliveryBVO;
import nc.vo.so.m4331.entity.DeliveryHVO;
import nc.vo.so.m4331.entity.DeliveryVO;
import nc.vo.so.m4331.entity.DeliveryViewVO;
import nc.vo.so.m4331.pub.DeliveryVOCalculator;
import nc.vo.so.m4331.rule.DeliveryMarginRule;

public class Renovate4331PriceRule {

  private Map<String, IDeliveryPriceParaFor30> paraMap;

  public void process(Map<String, IDeliveryPriceParaFor30> paraMap1,
      DeliveryViewVO[] views) {
    this.paraMap = paraMap1;
    DeliveryBVO[] bvos = this.getBodyVOs(views);
    this.setPrice(bvos);
    this.calculator(bvos);
    this.processMargin(views);
  }

  private void processMargin(DeliveryViewVO[] views) {
    DeliveryMarginRule margin = new DeliveryMarginRule();
    DeliveryVO[] newbills =
        new CombineViewToAggUtil<DeliveryVO>(DeliveryVO.class,
            DeliveryHVO.class, DeliveryBVO.class).combineViewToAgg(views,
            DeliveryHVO.CDELIVERYID);
    margin.process(newbills);
  }

  private void calculator(DeliveryBVO[] bvos) {
    int[] rows = this.getRows(bvos.length);
    DeliveryVOCalculator calculator = new DeliveryVOCalculator(bvos);
    calculator.calculate(rows, DeliveryBVO.NQTUNITNUM);
  }

  private DeliveryBVO[] getBodyVOs(DeliveryViewVO[] views) {
    Set<DeliveryBVO> bvoSet = new HashSet<DeliveryBVO>();
    for (DeliveryViewVO view : views) {
      bvoSet.add(view.getItem());
    }
    DeliveryBVO[] bvos = new DeliveryBVO[bvoSet.size()];
    bvoSet.toArray(bvos);
    return bvos;
  }

  /**
   * 获得要计算的行
   * 
   * @param bvos
   * @return
   */
  private int[] getRows(int len) {
    int[] rows = new int[len];
    for (int i = 0; i < len; i++) {
      rows[i] = i;
    }
    return rows;
  }

  private void setPrice(DeliveryBVO[] bvos) {
    for (DeliveryBVO bvo : bvos) {
      String srcBid = bvo.getCsrcbid();
      IDeliveryPriceParaFor30 para = this.paraMap.get(srcBid);
      this.setValue(para, bvo);
    }
  }

  /**
   * 设置价格（原币的含税净价和原币无税净价）
   * 
   * @param para
   */
  private void setValue(IDeliveryPriceParaFor30 para, DeliveryBVO bvo) {
    bvo.setNorigtaxnetprice(para.getNorigtaxnetprice());
    bvo.setNorignetprice(para.getNorignetprice());
    bvo.setNorigtaxprice(para.getNorigtaxprice());
    bvo.setNorigprice(para.getNorigprice());
    bvo.setNqtorigtaxnetprc(para.getNqtorigtaxnetprc());
    bvo.setNqtorigtaxprice(para.getNqtorigtaxprice());
    bvo.setNqtorignetprice(para.getNqtorignetprice());
    bvo.setNqtorigprice(para.getNqtorigprice());

    bvo.setNtaxnetprice(para.getNtaxnetprice());
    bvo.setNnetprice(para.getNnetprice());
    bvo.setNtaxprice(para.getNtaxprice());
    bvo.setNprice(para.getNprice());
    bvo.setNqttaxnetprice(para.getNqttaxnetprice());
    bvo.setNqttaxprice(para.getNqttaxprice());
    bvo.setNqtnetprice(para.getNqtnetprice());
    bvo.setNqtprice(para.getNqtprice());
    
    //新增特征码回写 add by zhangby5
    bvo.setCmffileid(para.getCmffileid());
  }
}
