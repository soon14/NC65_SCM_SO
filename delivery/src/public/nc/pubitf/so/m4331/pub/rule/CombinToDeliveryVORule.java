package nc.pubitf.so.m4331.pub.rule;

import java.util.HashMap;
import java.util.Map;

import nc.bs.framework.common.NCLocator;
import nc.itf.so.m4331.IDeliverycheckMaintain;
import nc.vo.pub.lang.UFBoolean;
import nc.vo.pub.lang.UFDouble;
import nc.vo.pubapp.pattern.pub.MathTool;
import nc.vo.pubapp.pattern.pub.SqlBuilder;
import nc.vo.so.m4331.entity.DeliveryBVO;
import nc.vo.so.m4331.entity.DeliveryCheckVO;
import nc.vo.so.m4331.entity.DeliveryHVO;
import nc.vo.so.m4331.entity.DeliveryVO;
import nc.vo.so.pub.util.AggVOUtil;

/**
 * 根据发货单vo信息获得质检信息并把质检vo中的数据信息
 * 并合并到发货单表体上，并形成新的聚合vo
 * 
 * @since 6.0
 * @version 2011-1-10 下午02:25:16
 * @author 祝会征
 */
public class CombinToDeliveryVORule {

  // 缓存发货单表体id
  private Map<String, String> bidMap;

  // 缓存不需要查询质检表的数据就可以出库的数据
  private Map<String, DeliveryBVO> bvoMap;

  // 必须根据质检结果进行出
  private Map<String, DeliveryBVO> bvoMustOutByCheckMap;

  private Map<String, DeliveryHVO> hvoMap;

  /**
   * 合并
   * 
   * @param vo
   */
  public DeliveryVO[] combin(DeliveryVO[] vos) {
    if (null == vos) {
      return null;
    }
    this.initMap(vos);
    if (this.bvoMustOutByCheckMap.size() == 0) {
      return vos;
    }
    if (!this.initData()) {
      // 剔除vos中bvoMustOutByCheckMap对应的记录
      if (this.bvoMap.size() == 0) {
        return null;
      }
      // return vos;
    }
    if (this.bvoMap.size() == 0) {
      return null;
    }
    DeliveryBVO[] bvos = new DeliveryBVO[this.bvoMap.size()];
    bvos = this.bvoMap.values().toArray(bvos);
    DeliveryHVO[] hvos = this.getHvos();
    DeliveryVO[] newbills =
        (DeliveryVO[]) AggVOUtil.createBillVO(hvos, bvos, DeliveryVO.class);
    this.setDeliveryBid(newbills);
    return newbills;
  }

  /*
   * 根据发货单表体id查询出相应的质检信息id
   * @return
   */
  private DeliveryCheckVO[] getCheckInfo() {
    String sql = this.getQuerySql();
    IDeliverycheckMaintain service =
        NCLocator.getInstance().lookup(IDeliverycheckMaintain.class);
    return service.queryDeliveryCheckVO(sql);
  }

  private DeliveryHVO[] getHvos() {
    DeliveryBVO[] bvos = new DeliveryBVO[this.bvoMap.size()];
    this.bvoMap.values().toArray(bvos);
    Map<String, DeliveryHVO> tempMap = new HashMap<String, DeliveryHVO>();
    for (DeliveryBVO bvo : bvos) {
      String hid = bvo.getCdeliveryid();
      DeliveryHVO hvo = this.hvoMap.get(hid);
      if (tempMap.size() == 0 || !tempMap.containsKey(hid)) {
        tempMap.put(hid, hvo);
      }
    }
    DeliveryHVO[] hvos = new DeliveryHVO[tempMap.size()];
    tempMap.values().toArray(hvos);
    return hvos;
  }

  /*
   * 获得查询质检信息的sql 
   * @return
   */
  private String getQuerySql() {
    String[] bids = new String[this.bvoMustOutByCheckMap.size()];
    bids = this.bvoMustOutByCheckMap.keySet().toArray(bids);
    StringBuffer sql = new StringBuffer();
    sql.append("select distinct(" + DeliveryCheckVO.CDELIVERYCID + ")");
    sql.append(" from so_delivery_check where dr=0 and ");
    SqlBuilder sqlBuilder = new SqlBuilder();
    sqlBuilder.append(DeliveryCheckVO.BCHECKINFLAG, UFBoolean.TRUE);
    sqlBuilder.append(" and ");
    sqlBuilder.append(DeliveryCheckVO.CDELIVERYBID, bids);
    sqlBuilder.append(" and ");
    sqlBuilder.append(DeliveryCheckVO.BOUTENDFLAG, UFBoolean.FALSE);
    sql.append(sqlBuilder.toString());
    return sql.toString();
  }

  private boolean initData() {
    if (this.bvoMustOutByCheckMap.size() == 0) {
      return false;
    }
    DeliveryCheckVO[] checkInfos = this.getCheckInfo();
    if (null == checkInfos) {
      return false;
    }
    this.bidMap = new HashMap<String, String>();
    // 缓存具有质检信息的发货单表体id
    for (DeliveryCheckVO info : checkInfos) {
      String srcbid = info.getCdeliverybid();
      if (this.bvoMustOutByCheckMap.containsKey(srcbid)) {
        DeliveryBVO bvo = this.bvoMustOutByCheckMap.get(srcbid);
        DeliveryBVO newBvo = (DeliveryBVO) bvo.clone();
        this.replaceBvoDatas(newBvo, info);
        this.bidMap.put(newBvo.getCdeliverybid(), bvo.getCdeliverybid());
        this.bvoMap.put(newBvo.getCdeliverybid(), newBvo);
      }
    }
    this.bvoMustOutByCheckMap.clear();
    return true;
  }

  /*
   * 缓存发货单信息
   */
  private void initMap(DeliveryVO[] vos) {
    this.bvoMap = new HashMap<String, DeliveryBVO>();
    this.bvoMustOutByCheckMap = new HashMap<String, DeliveryBVO>();
    this.hvoMap = new HashMap<String, DeliveryHVO>();
    for (DeliveryVO vo : vos) {
      this.hvoMap.put(vo.getParentVO().getCdeliveryid(), vo.getParentVO());
      DeliveryBVO[] bvos = vo.getChildrenVO();
      // 只有红字发货单才有可能具有质检信息
      for (DeliveryBVO bvo : bvos) {
        boolean ishavequality = this.isHavaQulity(bvo);
        if (!ishavequality) {
          this.bvoMap.put(bvo.getCdeliverybid(), bvo);
          continue;
        }
        this.bvoMustOutByCheckMap.put(bvo.getCdeliverybid(), bvo);
      }
    }
  }

  /**
   * 判断表体vo是否进行质检
   * 
   * @param bvo
   * @return
   */
  private boolean isHavaQulity(DeliveryBVO bvo) {
    UFDouble num = bvo.getNnum();
    if (MathTool.compareTo(num, UFDouble.ZERO_DBL) < 0) {
      UFBoolean busecheckflag = bvo.getBusecheckflag();
      if (null == busecheckflag || !busecheckflag.booleanValue()) {
        return false;
      }
      return true;
    }
    return false;
  }

  private void replaceBvoDatas(DeliveryBVO newBvo, DeliveryCheckVO info) {
    // 填充物料信息
    this.setMaterial(newBvo, info);
    // 填充标志
    this.setFlag(newBvo, info);
    // 设置原币价格
    this.setOrigPrice(newBvo, info);
    // 设置本币价格
    this.setPrice(newBvo, info);
    // 设置率
    this.setRate(newBvo, info);
    // 设置自由项
    this.setFree(newBvo, info);
    // 设置金额信息
    this.setMny(newBvo, info);
    // 设置其它各项的信息
    this.setOther(newBvo, info);
  }

  private void setDeliveryBid(DeliveryVO[] newbills) {
    if (null == this.bidMap || this.bidMap.size() == 0) {
      return;
    }
    for (DeliveryVO vo : newbills) {
      DeliveryBVO[] bvos = vo.getChildrenVO();
      for (DeliveryBVO bvo : bvos) {
        String bid = bvo.getCdeliverybid();
        if (this.bidMap.containsKey(bid)) {
          String newbid = this.bidMap.get(bid);
          bvo.setCdeliverybid(newbid);
        }
      }
    }
  }

  /*
   * 填充默认的标志信息
   */
  private void setFlag(DeliveryBVO newBvo, DeliveryCheckVO info) {
    newBvo.setBlargessflag(info.getBlargessflag());
    newBvo.setBoutendflag(info.getBoutendflag());
    newBvo.setBtransendflag(info.getBtransendflag());
  }

  /*
   * 设置自由项
   */
  private void setFree(DeliveryBVO newBvo, DeliveryCheckVO info) {
    newBvo.setPk_batchcode(info.getPk_batchcode());
    newBvo.setVbatchcode(info.getVbatchcode());
    newBvo.setVfree1(info.getVfree1());
    newBvo.setVfree2(info.getVfree2());
    newBvo.setVfree3(info.getVfree3());
    newBvo.setVfree4(info.getVfree4());
    newBvo.setVfree5(info.getVfree5());
    newBvo.setVfree6(info.getVfree6());
    newBvo.setVfree7(info.getVfree7());
    newBvo.setVfree8(info.getVfree8());
    newBvo.setVfree9(info.getVfree9());
    newBvo.setVfree10(info.getVfree10());
  }

  /*
   * 填充物料等相应信息
   */
  private void setMaterial(DeliveryBVO newBvo, DeliveryCheckVO info) {
    newBvo.setCmaterialid(info.getCmaterialid());
    newBvo.setCmaterialvid(info.getCmaterialvid());
    newBvo.setCunitid(info.getCunitid());
    newBvo.setCastunitid(info.getCastunitid());
    newBvo.setCqtunitid(info.getCqtunitid());
  }

  /*
   * 设置金额信息
   * @param bvo
   * @param info
   */
  private void setMny(DeliveryBVO newBvo, DeliveryCheckVO info) {
    newBvo.setNorigtaxmny(info.getNorigtaxmny());
    newBvo.setNorigmny(info.getNorigmny());
    newBvo.setNorigdiscount(info.getNorigdiscount());
    newBvo.setNtaxmny(info.getNtaxmny());
    newBvo.setNmny(info.getNmny());
    newBvo.setNdiscount(info.getNdiscount());
    newBvo.setNtax(info.getNtax());
  }

  /*
   * 发货单原币价格赋值到
   */
  private void setOrigPrice(DeliveryBVO newBvo, DeliveryCheckVO info) {
    newBvo.setNorignetprice(info.getNorignetprice());
    newBvo.setNorigprice(info.getNorigprice());
    newBvo.setNorigtaxnetprice(info.getNorigtaxnetprice());
    newBvo.setNorigtaxprice(info.getNorigtaxprice());
    newBvo.setNqtorignetprice(info.getNqtorignetprice());
    newBvo.setNqtorigprice(info.getNqtorigprice());
    newBvo.setNqtorigtaxnetprc(info.getNqtorigtaxnetprc());
    newBvo.setNqtorigtaxprice(info.getNqtorigtaxprice());
  }

  /*
   * 其它值的赋值
   */
  private void setOther(DeliveryBVO newBvo, DeliveryCheckVO info) {
    newBvo.setCrowno(info.getCrowno());
    newBvo.setCproductorid(info.getCproductorid());
    newBvo.setCprojectid(info.getCprojectid());
    newBvo.setCvendorid(info.getCvendorid());
    newBvo.setPk_org(info.getPk_org());
    newBvo.setCdeliverybbid(info.getCdeliverycid());
    newBvo.setCdeliverybid(info.getCdeliverycid());
    newBvo.setTts(info.getTs());
  }

  /*
   * 发货单表体的本币价格设置到质检表
   */
  private void setPrice(DeliveryBVO newBvo, DeliveryCheckVO info) {
    newBvo.setNnetprice(info.getNnetprice());
    newBvo.setNprice(info.getNprice());
    newBvo.setNqtnetprice(info.getNqtnetprice());
    newBvo.setNqtprice(info.getNqtprice());
    newBvo.setNqttaxnetprice(info.getNqttaxnetprice());
    newBvo.setNqttaxprice(info.getNqttaxprice());
    newBvo.setNtaxnetprice(info.getNtaxnetprice());
    newBvo.setNtaxprice(info.getNtaxprice());
  }

  /*
   * 发货单表体的税率、换算率等设置到质检表
   */
  private void setRate(DeliveryBVO newBvo, DeliveryCheckVO info) {
    newBvo.setCcurrencyid(info.getCcurrencyid());
    newBvo.setCorigcurrencyid(info.getCorigcurrencyid());
    newBvo.setNdiscountrate(info.getNdiscountrate());
    newBvo.setNexchangerate(info.getNexchangerate());
    newBvo.setNitemdiscountrate(info.getNitemdiscountrate());
    newBvo.setNtaxrate(info.getNtaxrate());
    newBvo.setVchangerate(info.getVchangerate());
    newBvo.setVqtunitrate(info.getVqtunitrate());
    newBvo.setNnum(info.getNnum());
    newBvo.setNtotaloutnum(info.getNtotaloutnum());
    newBvo.setNtotalnotoutnum(info.getNtotalnotoutnum());
    newBvo.setNastnum(info.getNastnum());
    newBvo.setNqtunitnum(info.getNqtunitnum());
  }
}
