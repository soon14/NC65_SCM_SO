package nc.bs.so.m30.rule.rewrite.m4h;

import nc.pubitf.ic.m4h.m30.IParameter4HFor30;
import nc.vo.pub.lang.UFDouble;
import nc.vo.pubapp.pattern.exception.ExceptionUtils;
import nc.vo.pubapp.pattern.pub.PubAppTool;

/**
 * 回写库存借出单参数
 *
 * @version 6.0
 * @since 6.0
 * @author 刘志伟
 * @time 2010-9-16 上午09:16:52
 */
public class Rewrite4HPara implements IParameter4HFor30 {

  private String bid;

  private String hid;

  private UFDouble ntranoutnum;

  public Rewrite4HPara(String hid, String bid, UFDouble nnum) {
    if (PubAppTool.isNull(hid)) {
      ExceptionUtils.wrappBusinessException(nc.vo.ml.NCLangRes4VoTransl.getNCLangRes().getStrByID("4006011_0","04006011-0113")/*@res "要回写库存借出单主表id不可为空。"*/);
    }
    this.hid = hid;
    if (PubAppTool.isNull(bid)) {
      ExceptionUtils.wrappBusinessException(nc.vo.ml.NCLangRes4VoTransl.getNCLangRes().getStrByID("4006011_0","04006011-0114")/*@res "要回写库存借出单表体行id不可为空。"*/);
    }
    this.bid = bid;

    if (nnum == null) {
      ExceptionUtils.wrappBusinessException(nc.vo.ml.NCLangRes4VoTransl.getNCLangRes().getStrByID("4006011_0","04006011-0115")/*@res "销售订单数量不可为空。"*/);
    }
    this.ntranoutnum = nnum;
  }

  @Override
  public String getBid() {
    return this.bid;
  }

  @Override
  public String getHid() {
    return this.hid;
  }

  @Override
  public UFDouble getNtranoutnum() {
    return this.ntranoutnum;
  }

  @Override
  public void setBid(String bid) {
    this.bid = bid;
  }

  @Override
  public void setHid(String hid) {
    this.hid = hid;
  }

  @Override
  public void setNtranoutnum(UFDouble nnum) {
    this.ntranoutnum = nnum;
  }

}