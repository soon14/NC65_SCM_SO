package nc.vo.so.m33.m4c.linkqryoutrush.entity;

import nc.vo.pub.IVOMeta;
import nc.vo.pub.SuperVO;
import nc.vo.pub.lang.UFDouble;
import nc.vo.pubapp.pattern.model.meta.entity.vo.VOMetaFactory;

public class OutRushExeInfoVO extends SuperVO {

  public static final String CMATERIALVID = "cmaterialvid";

  public static final String CROWNO = "crowno";

  public static final String CRUSHBILLROWNO = "crushbillrowno";

  public static final String CSQUAREBILLBID = "csquarebillbid";

  public static final String CUNITID = "cunitid";

  public static final String ENTITYNAME = "so.OutRushExeInfoVO";

  public static final String NNUM = "nnum";

  public static final String NOUTRUSHNUM = "noutrushnum";

  public static final String VRUSHBILLCODE = "vrushbillcode";

  private static final long serialVersionUID = 1L;

  public String getCmaterialvid() {
    return (String) this.getAttributeValue(OutRushExeInfoVO.CMATERIALVID);
  }

  public String getCrowno() {
    return (String) this.getAttributeValue(OutRushExeInfoVO.CROWNO);
  }

  public String getCrushbillrowno() {
    return (String) this.getAttributeValue(OutRushExeInfoVO.CRUSHBILLROWNO);
  }

  public String getCsquarebillbid() {
    return (String) this.getAttributeValue(OutRushExeInfoVO.CSQUAREBILLBID);
  }

  public String getCunitid() {
    return (String) this.getAttributeValue(OutRushExeInfoVO.CUNITID);
  }

  @Override
  public IVOMeta getMetaData() {
    IVOMeta meta =
        VOMetaFactory.getInstance().getVOMeta(OutRushExeInfoVO.ENTITYNAME);
    return meta;
  }

  public UFDouble getNnum() {
    return (UFDouble) this.getAttributeValue(OutRushExeInfoVO.NNUM);
  }

  public UFDouble getNoutrushnum() {
    return (UFDouble) this.getAttributeValue(OutRushExeInfoVO.NOUTRUSHNUM);
  }

  public String getVrushbillcode() {
    return (String) this.getAttributeValue(OutRushExeInfoVO.VRUSHBILLCODE);
  }

  public void setCmaterialvid(String cmaterialvid) {
    this.setAttributeValue(OutRushExeInfoVO.CMATERIALVID, cmaterialvid);
  }

  public void setCrowno(String crowno) {
    this.setAttributeValue(OutRushExeInfoVO.CROWNO, crowno);
  }

  public void setCrushbillrowno(String crushbillrowno) {
    this.setAttributeValue(OutRushExeInfoVO.CRUSHBILLROWNO, crushbillrowno);
  }

  public void setCsquarebillbid(String csquarebillbid) {
    this.setAttributeValue(OutRushExeInfoVO.CSQUAREBILLBID, csquarebillbid);
  }

  public void setCunitid(String cunitid) {
    this.setAttributeValue(OutRushExeInfoVO.CUNITID, cunitid);
  }

  public void setNnum(UFDouble nnum) {
    this.setAttributeValue(OutRushExeInfoVO.NNUM, nnum);
  }

  public void setNoutrushnum(UFDouble noutrushnum) {
    this.setAttributeValue(OutRushExeInfoVO.NOUTRUSHNUM, noutrushnum);
  }

  public void setVrushbillcode(String vrushbillcode) {
    this.setAttributeValue(OutRushExeInfoVO.VRUSHBILLCODE, vrushbillcode);
  }

}
